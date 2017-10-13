/**
 * 
 */
package com.work.itpa.service;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.drools.template.DataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.work.itpa.domain.ItpaRule;
import com.work.itpa.domain.RuleData;
import com.work.itpa.domain.RuleTemplate;
import com.work.itpa.repository.RuleTemplateRepository;
import com.work.itpa.rules.Deduction;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;

/**
 * Responsible for invocation of rules and calculating summary
 * 
 * @author Developer
 *
 */
@Component
public class ItpaRuleAdminService {

	private final Logger LOG = LoggerFactory.getLogger(ItpaRuleAdminService.class);

	private final RuleTemplateRepository ruleTemplateRepository;
	
	
	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * 
	 */
	public ItpaRuleAdminService(RuleTemplateRepository rtempRepository) {
		this.ruleTemplateRepository = rtempRepository;
	}
	
	
	public RuleTemplate save(RuleTemplate template){
		return ruleTemplateRepository.save(template);
	}
	
	

    public Page<RuleTemplate> findAll(Pageable pageable) {
        return ruleTemplateRepository.findAll(pageable);
    }
    
	public FinPersonResult calculateBenefits(FinPerson finPerson) {
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
