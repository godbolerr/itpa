/**
 * 
 */
package com.work.itpa.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.drools.template.DataProvider;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
public class ItpaRuleService {

	private final Logger LOG = LoggerFactory.getLogger(ItpaRuleService.class);

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	KieBase kbase;

	/**
	 * 
	 */
	public ItpaRuleService() {
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


	public FinPersonResult calculateBenefits(FinPerson finPerson) {

	
		KieSession kieSession = kbase.newKieSession();
		
		kieSession.addEventListener( new DefaultAgendaEventListener() {
		    public void afterMatchFired(AfterMatchFiredEvent event) {
		        super.afterMatchFired( event );
		        System.out.println( event );
		    }
		});		
		
		kieSession.addEventListener( new DebugRuleRuntimeEventListener() );
		
		KieRuntimeLogger logger =
				  KieServices.Factory.get().getLoggers().newFileLogger(kieSession, "itpaLog.txt");
		
		FinPersonResult result = new FinPersonResult();

		kieSession.insert(finPerson);
		kieSession.insert(result);
		kieSession.fireAllRules();
		
		kieSession.dispose();
		
		
		
		

		
		
		List<Deduction> applicableDeductions = calculateMaxPerCatetory(result.getDeductions());

		result.setApplicableDeductions(applicableDeductions);

		if (result.getDeductions() != null && result.getDeductions().size() > 0) {
			result.setStatus(true);
		}

		FinPerson fPerson = new FinPerson();

		BeanUtils.copyProperties(finPerson, fPerson);

		fPerson.setResult(result);
		
		logger.close();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			System.out.println(mapper.writeValueAsString(fPerson));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		mongoTemplate.save(fPerson, FiConstants.DB_COLLECTION_FIN_RESULT);

		return result;		
		
		

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
