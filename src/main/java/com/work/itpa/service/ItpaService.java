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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.work.itpa.domain.Deduction;
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

		List<SummaryDeduction> summaryDeductions = new ArrayList<SummaryDeduction>();

		insertSummaryDeductions(kSession, summaryDeductions);

		kSession.fireAllRules();

		logger.close();

		// Dispose the session and release memory

		kSession.dispose();

		if (result.getDeductions() != null && result.getDeductions().size() > 0) {
			result.setStatus(true);
			result.setSummaryDeductions(summaryDeductions);
		}

		LOG.debug("Result : " + result);

		return result;
	}

	private void insertSummaryDeductions(KieSession kSession, List<SummaryDeduction> summaryDeductions) {

		summaryDeductions.add(new SummaryDeduction("80C", new BigDecimal("150000"),
				"Benefits for 80C. Max limit is 1,50,000 "));
		
		summaryDeductions.add(new SummaryDeduction("80GGA", new BigDecimal("0"),
				"All Donations for scientific research under 80GGA "));
		summaryDeductions.add(new SummaryDeduction("80GGC", new BigDecimal("0"),
				"All Donations for scientific research under 80GGC "));
		summaryDeductions.add(new SummaryDeduction("80G", new BigDecimal("0"),
				"All Donations for various schemes under 80G"));		
		summaryDeductions.add(new SummaryDeduction("80QQB_80RRB", new BigDecimal("300000"),
				"Royalty related to Books and Patents -  80QQB and 80RRB"));	
		
		summaryDeductions.add(new SummaryDeduction("80TTA", new BigDecimal("10000"),
				"Interest Income TTA"));			
		
		

		for (Iterator<SummaryDeduction> iterator = summaryDeductions.iterator(); iterator.hasNext();) {
			SummaryDeduction summaryDeduction = (SummaryDeduction) iterator.next();
			kSession.insert(summaryDeduction);

		}
	}

}
