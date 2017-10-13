/**
 * 
 */
package com.work.itpa.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.io.ResourceType;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.work.itpa.rules.Deduction;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.rules.ItpaRule;
import com.work.itpa.rules.RuleData;
import com.work.itpa.rules.RuleTemplate;

/**
 * Responsible for invocation of rules and calculating summary
 * 
 * @author Developer
 *
 */
@Component
public class ItpaService {

	private final Logger LOG = LoggerFactory.getLogger(ItpaService.class);

	//@Autowired
	private KieContainer kc;

	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * 
	 */
	public ItpaService() {
	}

	public FinPersonResult newBenefits(FinPerson finPerson) {
		//
		FinPersonResult result = new FinPersonResult();
		//
		//// String content = new
		// String(Files.readAllBytes(Paths.get("src/main/resources/validateApplicant.drl")),
		//// Charset.forName("UTF-8"));
		//// System.out.println("Read New Rules set from File");
		// // load up the knowledge base
		// KieServices ks = KieServices.Factory.get();
		// String inMemoryDrlFileName =
		// "src/main/resources/stateFulSessionRule.drl";
		// KieFileSystem kfs = ks.newKieFileSystem();
		// kfs.write(inMemoryDrlFileName,
		// ks.getResources().newReaderResource(new StringReader(content))
		// .setResourceType(ResourceType.DRL));
		// KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();

		// System.out.println("Put rules KieBase into Custom Cache");
		// kieBaseCache.put("validateApplicant", kbase);
		// } else {
		// System.out.println("Get existing rules KieBase from Custom Cache");
		// kieSession = kieBaseCache.get("validateApplicant").newKieSession();
		// }
		//
		//
		//
		//

		return result;

	}

	public FinPersonResult calculateBenefits(FinPerson finPerson) {

		KieSession kSession = kc.newKieSession("ItpaDataKs");

		KieRuntimeLogger logger = KieServices.Factory.get().getLoggers().newFileLogger(kSession, "logRules");

		FinPersonResult result = new FinPersonResult();

		LOG.debug("Firing rules for : " + finPerson);

		kSession.insert(finPerson);
		kSession.insert(result);

		kSession.fireAllRules();

		logger.close();

		List<Deduction> applicableDeductions = calculateMaxPerCatetory(result.getDeductions());

		result.setApplicableDeductions(applicableDeductions);

		// Dispose the session and release memory

		kSession.dispose();

		if (result.getDeductions() != null && result.getDeductions().size() > 0) {
			result.setStatus(true);
		}

		LOG.debug("Result : " + result);

		FinPerson fPerson = new FinPerson();

		BeanUtils.copyProperties(finPerson, fPerson);

		fPerson.setResult(result);

		mongoTemplate.save(fPerson, FiConstants.DB_COLLECTION_FIN_RESULT);

		return result;
	}

	/**
	 * Return planned deductions as list.
	 * 
	 * @param deductions
	 * @return
	 */
	private List<Deduction> calculateMaxPerCatetory(List<Deduction> deductions) {

		Map<String, Deduction> dMap = new HashMap<String, Deduction>();

		for (Iterator<Deduction> iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = iterator.next();
			String key = deduction.getSection() + deduction.getType();
			if (dMap.containsKey(key) == false) {
				dMap.put(key, deduction);
			} else {
				Deduction xDeduction = dMap.get(key);

				if (FiConstants.DEDUCTION_UNIQUE.equals(xDeduction.getMode())) {

					// TODO Add exact comparison
					if (xDeduction.getAmount().doubleValue() < deduction.getAmount().doubleValue()) {
						dMap.put(key, deduction);
					}
				}

				if (FiConstants.DEDUCTION_ADDITIVE.equals(xDeduction.getMode())) {

					Deduction totalDeduction = new Deduction();
					totalDeduction.setMode(xDeduction.getMode());
					totalDeduction.setType(xDeduction.getType());
					totalDeduction.setSection(xDeduction.getSection());
					BigDecimal origDeduction = xDeduction.getAmount();
					totalDeduction.setAmount(origDeduction.add(deduction.getAmount()));
					dMap.put(key, totalDeduction);

				}

			}
		}

		return new ArrayList<Deduction>(dMap.values());

	}

	public String updateRuleTemplate(RuleTemplate template) {

		String result = "SUCCESS";

		mongoTemplate.save(template, FiConstants.DB_COLLECTION_RULETEMPLATE);

		return result;
	}
	
	public String updateRule(ItpaRule rule) {

		String result = "SUCCESS";

		mongoTemplate.save(rule, FiConstants.DB_COLLECTION_RULES);

		return result;
	}	
	

	public List<RuleData> getDecisionData(int assessmentYear, String ruleTemplate) {

		Query query = new Query();
		query.addCriteria(Criteria.where(FiConstants.COL_RULETEMPPLATE).is(ruleTemplate));
		query.addCriteria(Criteria.where(FiConstants.COL_ASSESSMENT_YEAR).is(assessmentYear));
		query.addCriteria(Criteria.where(FiConstants.COL_STATUS).is(FiConstants.ACTIVE));
		System.out.println(query.toString());

		return mongoTemplate.find(query, RuleData.class, FiConstants.DB_COLLECTION_RULEDATA);

	}

	public List<RuleTemplate> getRuleTemplates(int assessmentYear) {

		Query query = new Query();
		query.addCriteria(Criteria.where(FiConstants.COL_ASSESSMENT_YEAR).is(assessmentYear));
		query.addCriteria(Criteria.where(FiConstants.COL_STATUS).is(FiConstants.ACTIVE));

		return mongoTemplate.find(query, RuleTemplate.class, FiConstants.DB_COLLECTION_RULETEMPLATE);

	}

	public String getRules(int assessmentYear, String ruleTemplate, String ruleFile, String commaSeperatedList) {

		final DataProviderCompiler converter = new DataProviderCompiler();

		StringBuffer sb = new StringBuffer();

		List<RuleTemplate> ruleTemplates = getRuleTemplates(assessmentYear);

		for (RuleTemplate rt : ruleTemplates) {

			DecisionDataProvider tdp = new DecisionDataProvider(getDecisionData(assessmentYear, rt.getRuleTemplateId()),
					rt.getCommaSeperatedFields());

			InputStream is = new ByteArrayInputStream(rt.getRuleText().getBytes());

			String rules = converter.compile(tdp, is);

			// TODO Validate rules before adding it to the main collection

			sb.append(rules);
			sb.append("\n");
		}

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		kbuilder.add(ResourceFactory.newByteArrayResource(sb.toString().getBytes()), ResourceType.DRL);

		KieServices ks = KieServices.Factory.get();

		KieFileSystem kfs = ks.newKieFileSystem();

		String inMemoryDrlFileName = "src/main/resources/stateFulSessionRule.drl";

		ks.getResources().newReaderResource(new StringReader(sb.toString())).setResourceType(ResourceType.DRL);

		KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();

		if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
			System.out.println(kieBuilder.getResults().toString());
		}
		KieContainer kContainer = ks.newKieContainer(kieBuilder.getKieModule().getReleaseId());
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		KieBase kbase = kContainer.newKieBase(kbconf);
		KieSession kSession = kbase.newKieSession();

		Collection<String> baseNames = kc.getKieBaseNames();

		baseNames.forEach(name -> System.out.println(name));

		KieRuntimeLogger logger = KieServices.Factory.get().getLoggers().newFileLogger(kSession, "logRules");

		FinPersonResult result = new FinPersonResult();
		
		FinPerson finPerson= new FinPerson();

		LOG.debug("Firing rules for : " + finPerson);

		kSession.insert(finPerson);
		kSession.insert(result);

		kSession.fireAllRules();

		logger.close();

		List<Deduction> applicableDeductions = calculateMaxPerCatetory(result.getDeductions());

		result.setApplicableDeductions(applicableDeductions);

		// Dispose the session and release memory

		kSession.dispose();

		if (result.getDeductions() != null && result.getDeductions().size() > 0) {
			result.setStatus(true);
		}

		LOG.debug("Result : " + result);

		FinPerson fPerson = new FinPerson();

		BeanUtils.copyProperties(finPerson, fPerson);

		fPerson.setResult(result);

		mongoTemplate.save(fPerson, FiConstants.DB_COLLECTION_FIN_RESULT);
		
		
		return sb.toString();

	}

	private InputStream getTemplate(String ruleFile) {
		return this.getClass().getResourceAsStream(ruleFile);
	}

	private class DecisionDataProvider implements DataProvider {

		private Iterator<RuleData> iterator;

		private String commaSeperatedFields;

		DecisionDataProvider(List<RuleData> rows, String commaSeperatedFields) {
			this.iterator = rows.iterator();
			this.commaSeperatedFields = commaSeperatedFields;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		/**
		 * section residentStatus assesseeType relationShipCode deductionType
		 * minAge maxAge maxDeduction
		 */

		@Override
		public String[] next() {
			RuleData ruleDataItem = iterator.next();
			String[] row = getFields(commaSeperatedFields, ruleDataItem);
			return row;
		}
	}

	/**
	 * TODO Handle exceptions and log the same. LOG NULL VALUES so that we dont
	 * get unexpected results
	 * 
	 * @param commaSeperatedFields
	 * @param ruleData
	 * @return
	 */
	private String[] getFields(String commaSeperatedFields, RuleData ruleData) {

		String[] fields = commaSeperatedFields.split(",");

		String[] dataFields = new String[fields.length];

		for (int i = 0; i < fields.length; i++) {
			String thisField = fields[i];
			try {
				Field f = RuleData.class.getField(thisField);
				if (f != null && f.get(ruleData) != null) {
					String value = f.get(ruleData).toString();
					dataFields[i] = value;
				}
			} catch (NoSuchFieldException | SecurityException e) {
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}

		return dataFields;
	}

}
