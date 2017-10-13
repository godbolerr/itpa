package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.ItpadocApp;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.web.rest.util.PersonUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpadocApp.class)
public class Rule80GTest {

	@Autowired
	ItpaRuleAdminService dService;

	String sectionName = "80G";
	
	@Rule public TestName testName = new TestName();

	@Test
	public void test80GResidentIndividualDonation() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 20000, "Prime Minister’s National Relief Fund",
				"Donation to Prime Minister’s National Relief Fund");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 20000);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}

	@Test
	public void test80GResidentIndividualDonationTwoTimesSameScheme() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 20000, "Prime Minister’s National Relief Fund",
				"Donation to Prime Minister’s National Relief Fund");
		PersonUtil.addDonation(fPerson, 20000, "Prime Minister’s National Relief Fund",
				"Donation to Prime Minister’s National Relief Fund again");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 40000);

		assertTrue(totalResult);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}

	@Test
	public void test80GResidentIndividualDonationTwoDifferentScheme() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 20000, "Prime Minister’s National Relief Fund",
				"Donation to Prime Minister’s National Relief Fund");
		PersonUtil.addDonation(fPerson, 20000, "National Sports Fund", "Donation to National Sports Fund");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 40000);

		assertTrue(totalResult);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
}
