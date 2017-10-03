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

import com.work.itpa.ItpadocApp;
import com.work.itpa.rules.Donation;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.web.rest.util.PersonUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpadocApp.class)
public class Rule80G1Test {

	@Autowired
	ItpaService dService;

	String sectionName = "80G";
	
	@Rule public TestName testName = new TestName();

	@Test
	public void test80G1ResidentIndividualDonation() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setGrossTotalIncome(BigDecimal.valueOf(300000));
		
		Donation donation = new Donation(BigDecimal.valueOf(20000), "", "Test G1 Type donations");
		donation.setSchemeCode("G1_1");
		fPerson.addDonation(donation);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 20000);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}



	@Test
	public void test80G1ResidentIndividualDonationMoreThan10PercentGti() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setGrossTotalIncome(BigDecimal.valueOf(300000));
		
		Donation donation = new Donation(BigDecimal.valueOf(31000), "", "Test G1 Type donations");
		donation.setSchemeCode("G1_1");
		fPerson.addDonation(donation);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 30000);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}

	

	@Test
	public void test80G1ResidentIndividualDonationWith50PercentDeduction() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setGrossTotalIncome(BigDecimal.valueOf(300000));
		
		Donation donation = new Donation(BigDecimal.valueOf(10000), "", "Test G1 Type donation with 50% deduction on donation value");
		donation.setSchemeCode("G1_3");
		fPerson.addDonation(donation);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 5000);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}

	
	@Test
	public void test80G1ResidentIndividualDonationWith50PercentDeductionGreaterThan10PercentGti() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setGrossTotalIncome(BigDecimal.valueOf(300000));
		
		Donation donation = new Donation(BigDecimal.valueOf(70000), "", "Test G1 Type donation with 50% deduction on donation value greater than 10% GTI");
		donation.setSchemeCode("G1_3");
		fPerson.addDonation(donation);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 30000);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}	
	
	
	
	

}
