/**
 * 
 */
package com.work.itpa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.work.itpa.rules.Deduction;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.rules.Person;

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

		List<Person> allPersons = new ArrayList<Person>();

		// Add assess himself as a person.

		Person assesseePerson = new Person();

		assesseePerson.setAge(finPerson.getAge());
		assesseePerson.setDisabilityPercent(finPerson.getDisabilityPercent());
		assesseePerson.setDisease(finPerson.getDisease());
		assesseePerson.setGender(finPerson.getGender());
		assesseePerson.setName(finPerson.getName());
		assesseePerson.setRelationShipCode(finPerson.getRelationShipCode());
		

		allPersons.add(assesseePerson);
		if (finPerson.getDependents() != null) {
			allPersons.addAll(finPerson.getDependents());
		}

		if (finPerson.getFamily() != null) {
			allPersons.addAll(finPerson.getFamily());
		}

		finPerson.setAllPersons(allPersons);
		
		LOG.debug("Firing rules for : " + finPerson);

		kSession.insert(finPerson);
		kSession.insert(result);

		kSession.fireAllRules();

		logger.close();

		List<Deduction> applicableDeductions = calculateMaxPerCatetory(result.getDeductions());

		result.setApplicableDeductions(applicableDeductions);

		// Dispose the session and release memory

		kSession.dispose();
		
		if ( result.getDeductions() != null && result.getDeductions().size() > 0) {
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
					
					//TODO Add exact comparison
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

}
