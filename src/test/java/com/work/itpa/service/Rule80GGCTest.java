package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.ItpaApp;
import com.work.itpa.domain.Donation;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80GGCTest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80GGC";
	
	@Rule public TestName testName = new TestName();


	@Test
	public void test80ggcSinglePoliticalDonations() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		
		BigDecimal amount = new BigDecimal("20000.00");
		
		PersonUtil.addDonation(fPerson, amount, Donation.Type.POLITICAL, "Donation to policical party xyz ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, amount);

		assertTrue(result);
		
		// Verify final deduction which is applicable.
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName, amount);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
	
	@Test
	public void test80ggcMultiplePoliticalDonations() {
		
		BigDecimal donation1 = new BigDecimal("20000.00");
		BigDecimal donation2 = new BigDecimal("35000.10");
		
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		
		
		PersonUtil.addDonation(fPerson, donation1, Donation.Type.POLITICAL, "Donation to policical party xyz ");
		PersonUtil.addDonation(fPerson, donation2, Donation.Type.POLITICAL, "Donation to policical party abc ");

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, donation1);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, donation2);

		assertTrue(result);
		
		// Verify final deduction which is applicable.
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName, donation1.add(donation2));

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}	

}
