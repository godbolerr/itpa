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
public class Rule80G1Test {

	@Autowired
	ItpaService dService;

	String sectionName80g = "80G";
	
	@Rule public TestName testName = new TestName();

	@Test
	public void test80G1OtherDonationLessThanGtiPercent() {
		
		BigDecimal grossTotalIncome = new BigDecimal("300000");
		
		BigDecimal donationAmount = new BigDecimal("20000");
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setGrossTotalIncome(grossTotalIncome);
		
		Donation donation = new Donation(donationAmount, Donation.Type.OTHER, "GOVT_APPRVD_FAMLY_PLNG", "Test GOVT_APPRVD_FAMLY_PLNG Type donations");

		fPerson.addDonation(donation);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80g, donationAmount);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80g, donationAmount);

		assertTrue(result);
		
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
	
	@Test
	public void test80G1DonationGreaterThanGtiPercent() {
		
		
		BigDecimal grossTotalIncome = new BigDecimal("300000");
		
		BigDecimal donationAmount = new BigDecimal("40000");
		
		BigDecimal eligibleDonation = new BigDecimal("30000");
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		
		
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		fPerson.setGrossTotalIncome(grossTotalIncome);
		
		Donation donation = new Donation(donationAmount, Donation.Type.OTHER,"GOVT_APPRVD_FAMLY_PLNG", "Test GOVT_APPRVD_FAMLY_PLNG Type donations");

		fPerson.addDonation(donation);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80g, eligibleDonation);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80g, eligibleDonation);

		assertTrue(result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}	
	
	
	
	@Test
	public void test80G1and80GDonationGreaterThanGtiPercent() {
		
		
		BigDecimal grossTotalIncome = new BigDecimal("300000");
		
		BigDecimal donationAmount = new BigDecimal("40000");
		
		BigDecimal donationAmount2 = new BigDecimal("50000");
		
		BigDecimal eligibleDonation = new BigDecimal("30000");
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		
		
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		fPerson.setGrossTotalIncome(grossTotalIncome);
		
		Donation donation2 = new Donation(donationAmount2, Donation.Type.OTHER,"PM_NAT_REL_FUND", "Test PM_NAT_REL_FUND Type donations");

		Donation donation = new Donation(donationAmount, Donation.Type.OTHER,"GOVT_APPRVD_FAMLY_PLNG", "Test GOVT_APPRVD_FAMLY_PLNG Type donations");

		fPerson.addDonation(donation);
		fPerson.addDonation(donation2);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80g, eligibleDonation);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80g, donationAmount2);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80g, eligibleDonation.add(donationAmount2));

		assertTrue(result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}		

}
