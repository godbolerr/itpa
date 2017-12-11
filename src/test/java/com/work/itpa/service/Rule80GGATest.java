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
public class Rule80GGATest {

	@Autowired
	ItpaService dService;
	
	String sectionName80gga = "80GGA";
	
	@Rule public TestName testName = new TestName();


	@Test
	public void test80ggaSingleDonationToScientificResearch() {
		Assessee assessee = PersonUtil.getBachelorMale();
		
		BigDecimal donation = new BigDecimal("20000.00");
		
		PersonUtil.addDonation(assessee, donation, Donation.Type.SCIENTIFIC,
				"Donation to scientific research for biology");
		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80gga, donation);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80gga, donation);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);


	}
	
	@Test
	public void test80ggaMultipleDonationToScientificResearch() {
		
		String sectionName80ggagga = "80GGA";
		
		Assessee assessee = PersonUtil.getBachelorMale();
		BigDecimal donationgga = new BigDecimal("20000.00");
		
		PersonUtil.addDonation(assessee, donationgga, Donation.Type.SCIENTIFIC,
				"Donation to scientific research for biology");

		
		BigDecimal donation2gga = new BigDecimal("30000.00");
		
		PersonUtil.addDonation(assessee, donation2gga, Donation.Type.SCIENTIFIC,
				"Donation to scientific research for physics");

		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ggagga, donationgga);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ggagga, donation2gga);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ggagga, donationgga.add(donation2gga));

		assertTrue(result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);


	}	
}
