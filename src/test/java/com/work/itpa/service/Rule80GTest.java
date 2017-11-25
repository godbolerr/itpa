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
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80GTest {

	@Autowired
	ItpaService dService;

	String sectionName = "80G";
	
	@Rule public TestName testName = new TestName();

	@Test
	public void test80GResidentIndividualDonation() {
		
		BigDecimal donation1 = new BigDecimal("20000.00");
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, donation1, Donation.Type.OTHER,"NAT_DEF_FUND_CEN_GOVT",
				"Donation NAT_DEF_FUND_CEN_GOVT");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, donation1);

		assertTrue(result);
		
		boolean totalResult = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName,donation1 );

		assertTrue(totalResult);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
	
	@Test
	public void test80GResidentIndividualDonationWithFiftyPercentDeduction() {
		
		BigDecimal donation1 = new BigDecimal("20000.00");
		
		BigDecimal expectedExemption = new BigDecimal("10000.00");
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, donation1, Donation.Type.OTHER,"PM_DROUGHT_RELF_FND",
				"Donation PM_DROUGHT_RELF_FND. 50% exemption expected.");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, donation1);

		assertTrue(result);
		
		boolean totalResult = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName,expectedExemption );

		assertTrue(totalResult);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}	
	
	@Test
	public void test80GWithTwoDonationFiftyAndHundredPercentDeduction() {
		
		String sectionName80g = "80G";
		
		BigDecimal donation180g = new BigDecimal("20000.00");
		BigDecimal donation280g = new BigDecimal("14000.00");
		
		BigDecimal expectedExemption80g = new BigDecimal("24000.00");
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, donation180g, Donation.Type.OTHER,"PM_DROUGHT_RELF_FND",
				"Donation PM_DROUGHT_RELF_FND. 50% exemption expected.");
		PersonUtil.addDonation(fPerson, donation280g, Donation.Type.OTHER,"NAT_DEF_FUND_CEN_GOVT",
				"Donation NAT_DEF_FUND_CEN_GOVT");
		
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80g, donation180g);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80g, donation280g);

		assertTrue(result);
				
		
		boolean totalResult = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80g,expectedExemption80g );

		assertTrue(totalResult);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}		

}
