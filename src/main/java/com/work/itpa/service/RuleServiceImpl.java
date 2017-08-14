/**
 * 
 */
package com.work.itpa.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.logger.KieRuntimeLogger;
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
    	KieRuntimeLogger logger =
  			  KieServices.Factory.get().getLoggers().newFileLogger(kSession, "logRules");
  	
    	kSession.addEventListener( new DebugRuleRuntimeEventListener() );
    	
    	kSession.addEventListener( new DefaultAgendaEventListener() {
    	    public void afterMatchFired(AfterMatchFiredEvent event) {
    	        super.afterMatchFired( event );
    	        System.out.println( " ### " +  event );
    	    }
    	});
    	
    	
    	
    	
    	
    	List<String> messages = new ArrayList<String>();
    	
		FinPersonResult result = new FinPersonResult();

		kSession.insert(person);
		kSession.insert(result);
		kSession.setGlobal("messages", messages);

		kSession.fireAllRules();

		logger.close();
	    
		
		return result;
	}

}
