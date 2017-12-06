/**
 * 
 */
package com.work.itpa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.domain.SummaryDeduction;

/**
 * Responsible for invocation of rules and calculating summary
 * 
 * @author Developer
 *
 */
@Component
public class ItpaService {

	private final Logger LOG = LoggerFactory.getLogger(ItpaService.class);

	@Autowired
	private KieContainer kc;

	/**
	 * 
	 */
	public ItpaService() {
	}

	public FinPersonResult calculateBenefits(FinPerson finPerson) {

		KieSession kSession = kc.newKieSession("ItpaDataKs");

		KieRuntimeLogger logger = KieServices.Factory.get().getLoggers().newFileLogger(kSession, "logRules");

		kSession.addEventListener(new DebugAgendaEventListener());
		kSession.addEventListener(new DebugRuleRuntimeEventListener());

		FinPersonResult result = new FinPersonResult();

		LOG.debug("Firing rules for : " + finPerson);

		kSession.insert(finPerson);
		kSession.insert(result);

		kSession.fireAllRules();

		logger.close();

		// Dispose the session and release memory
		
		List<SummaryDeduction> sDeductions = new ArrayList<SummaryDeduction>();
		QueryResults results = kSession.getQueryResults( "Get Summary Deductions" ); 
		for ( QueryResultsRow row : results ) {
		    SummaryDeduction sDeduction  = ( SummaryDeduction ) row.get( "$sd" ); //you can retrieve all the bounded variables here
		    sDeductions.add(sDeduction);
		}
		

		kSession.dispose();

		if (result.getDeductions() != null && result.getDeductions().size() > 0) {
			result.setStatus(true);
			result.setSummaryDeductions(sDeductions);
		}

		LOG.debug("Result : " + result);

		return result;
	}


}
