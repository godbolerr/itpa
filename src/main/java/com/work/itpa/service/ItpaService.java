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
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.itpa.rules.Deduction;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.rules.Person;

@Service
public class ItpaService {

	@Autowired
	private KieContainer kc;

	/**
	 * 
	 */
	public ItpaService() {
		// TODO Auto-generated constructor stub
	}

	private AgendaFilter getAgendaFilter(String agenda) {

		AgendaFilter af = new AgendaFilter() {
			public boolean accept(Match match) {
				String rulename = match.getRule().getName();

				// Execute all rules starting with some agenda

				if (rulename.startsWith(agenda))
					return true;

				return false;
			}
		};

		return af;
	}

	public FinPersonResult calculateBenefits(FinPerson finPerson) {

		KieSession kSession = kc.newKieSession("ItpaDataKs");
		KieRuntimeLogger logger = KieServices.Factory.get().getLoggers().newFileLogger(kSession, "logRules");

		kSession.addEventListener(new DebugRuleRuntimeEventListener());

		kSession.addEventListener(new DefaultAgendaEventListener() {
			public void afterMatchFired(AfterMatchFiredEvent event) {
				super.afterMatchFired(event);
				// System.out.println(" ### " + event);
			}
		});

		List<String> messages = new ArrayList<String>();

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
		assesseePerson.setId(finPerson.getId());

		allPersons.add(assesseePerson);
		if (finPerson.getDependents() != null) {
			allPersons.addAll(finPerson.getDependents());
		}

		if (finPerson.getFamily() != null) {
			allPersons.addAll(finPerson.getFamily());
		}

		finPerson.setAllPersons(allPersons);

		kSession.insert(finPerson);
		kSession.insert(result);

		kSession.fireAllRules();

		logger.close();

		List<Deduction> plannedDeductions = calculateMaxPerCatetory(result.getDeductions());

		result.setPlannedDeductions(plannedDeductions);

		// Dispose the session and release memory

		kSession.dispose();

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

					xDeduction.setNotes(""); // Blank the notes as these are
												// more than 1 deductions
					BigDecimal origDeduction = xDeduction.getAmount();
					xDeduction.setAmount(origDeduction.add(deduction.getAmount()));
				}

			}
		}

		return new ArrayList<Deduction>(dMap.values());

	}

}