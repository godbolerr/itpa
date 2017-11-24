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
public class Rule80GGATest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80GGA";
	
	@Rule public TestName testName = new TestName();


	@Test
	public void test80ggaSingleDonationToScientificResearch() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		
		BigDecimal donation = new BigDecimal("20000.00");
		
		PersonUtil.addDonation(fPerson, donation, Donation.Type.SCIENTIFIC,
				"Donation to scientific research for biology");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, donation);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName, donation);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
	
	@Test
	public void test80ggaMultipleDonationToScientificResearch() {
		
		
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		BigDecimal donation = new BigDecimal("20000.00");
		
		PersonUtil.addDonation(fPerson, donation, Donation.Type.SCIENTIFIC,
				"Donation to scientific research for biology");

		
		BigDecimal donation2 = new BigDecimal("30000.00");
		
		PersonUtil.addDonation(fPerson, donation2, Donation.Type.SCIENTIFIC,
				"Donation to scientific research for physics");

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, donation);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, donation2);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName, donation.add(donation2));

		assertTrue(result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}	
}
