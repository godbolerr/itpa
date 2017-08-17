/**
 * 
 */
package com.work.itpa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.itpa.rules.Deduction;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;

@Service
public class RuleServiceImpl implements RuleService {

	@Autowired
	private KieContainer kc;

	/**
	 * 
	 */
	public RuleServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public FinPersonResult calculateBenefits(FinPerson person) {

		KieSession kSession = kc.newKieSession("ItpaDataKs");
		KieRuntimeLogger logger = KieServices.Factory.get().getLoggers().newFileLogger(kSession, "logRules");

		// kSession.addEventListener( new DebugRuleRuntimeEventListener() );

		// kSession.addEventListener( new DefaultAgendaEventListener() {
		// public void afterMatchFired(AfterMatchFiredEvent event) {
		// super.afterMatchFired( event );
		// System.out.println( " ### " + event );
		// }
		// });
		//

		List<String> messages = new ArrayList<String>();

		FinPersonResult result = new FinPersonResult();

		kSession.insert(person);
		kSession.insert(result);

		kSession.fireAllRules();

		logger.close();

		List<Deduction> plannedDeductions = calculateMaxPerCatetory(result.getDeductions());

		result.setPlannedDeductions(plannedDeductions);

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
				if (xDeduction.getAmount().doubleValue() < deduction.getAmount().doubleValue()) {
					dMap.put(key, deduction);
				}
			}
		}

		return new ArrayList<Deduction>(dMap.values());

	}

}
