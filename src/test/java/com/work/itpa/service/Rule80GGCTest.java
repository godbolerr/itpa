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

import com.work.itpa.domain.Donation;
import com.work.itpa.domain.Assessee;
import com.work.itpa.domain.Assessment;
import com.work.itpa.itparules.ItpaApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80GGCTest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80GGC";
	
	@Rule public TestName testName = new TestName();


	@Test
	public void test80ggcSinglePoliticalDonations() {
		Assessee fPerson = PersonUtil.getBachelorMale();
		
		BigDecimal amount = new BigDecimal("20000.00");
		
		
		PersonUtil.addDonation(fPerson, amount, Donation.Type.POLITICAL, "Donation to policical party xyz ");
		Assessment finResult = dService.calculateBenefits(fPerson);

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
		
		String ggcSectionName = "80GGC";
		
		BigDecimal donation1ggc = new BigDecimal("20000.00");
		BigDecimal donation2ggc = new BigDecimal("35000.10");
		
		
		Assessee fPerson = PersonUtil.getBachelorMale();
		
		
		PersonUtil.addDonation(fPerson, donation1ggc, Donation.Type.POLITICAL, "Donation to policical party xyz ");
		PersonUtil.addDonation(fPerson, donation2ggc, Donation.Type.POLITICAL, "Donation to policical party abc ");

		Assessment finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), ggcSectionName, donation1ggc);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), ggcSectionName, donation2ggc);

		assertTrue(result);
		
		// Verify final deduction which is applicable.
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), ggcSectionName, donation1ggc.add(donation2ggc));

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}	

}
