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
import org.drools.template.DataProviderCompiler;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.work.itpa.rules.Deduction;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.rules.RuleData;

/**
 * Responsible for invocation of rules and calculating summary
 * 
 * @author Developer
 *
 */
@Component
public class ItpaService {

	private final Logger LOG = LoggerFactory.getLogger(ItpaService.class);

	public static final String ITPA_RULE_DATA = "itpaRuleData";

	@Autowired
	private KieContainer kc;

	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * 
	 */
	public ItpaService() {
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

		mongoTemplate.save(fPerson, "FinPersonResult");

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

	/**
	 * TODO Handle error parsing the input json data
	 * 
	 * @param data
	 * @return
	 */
	public String updateRuleData(String data) {
		String result = "SUCCESS";

		DBObject dbObject = (DBObject) JSON.parse(data);

		mongoTemplate.save(dbObject, ITPA_RULE_DATA);

		return result;
	}

	public List<RuleData> getDecisionData(int assessmentYear, String ruleTemplate) {

		Query query = new Query();
		query.addCriteria(Criteria.where("ruleTemplate").is(ruleTemplate));
		query.addCriteria(Criteria.where("assessmentYear").is(assessmentYear));

		return mongoTemplate.find(query, RuleData.class, "decisiondata");

	}

	public void getRules(int assessmentYear, String ruleTemplate, String ruleFile, String commaSeperatedList) {

		DecisionDataProvider tdp = new DecisionDataProvider(getDecisionData(assessmentYear, ruleTemplate),
				commaSeperatedList);
		final DataProviderCompiler converter = new DataProviderCompiler();
		final String drl = converter.compile(tdp, getTemplate(ruleFile));
		System.out.println(drl);

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
	 * TODO Handle exceptions and log the same.
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
