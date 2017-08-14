/**
 * 
 */
package com.work.itpa.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		FinPersonResult result = new FinPersonResult();

		kSession.insert(person);
		kSession.insert(result);

		kSession.fireAllRules();

		return result;
	}

}
