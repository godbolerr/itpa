package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.itparules.ItparulesApplication;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItparulesApplication.class)
public class Rule80DTest {

	@Autowired
	ItpaService dService;

	String sectionName = "80D";
	
	@Rule public TestName testName = new TestName();
	
	@Test
	public void test80DSelfResidentIndividualBetween1And59() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setAge(45);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSectionWithDeductionType(finResult.getApplicableDeductions(), sectionName,
				FiConstants.DEDUCTION_MECICAL_INSURANCE);

		assertTrue(result);

		result = PersonUtil.hasSectionWithDeductionTypeNTimes(finResult.getDeductions(), sectionName,
				FiConstants.DEDUCTION_MECICAL_INSURANCE, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithDeductionTypeAndAmount(finResult.getApplicableDeductions(), sectionName,
				FiConstants.DEDUCTION_MECICAL_INSURANCE, 25000);

		assertTrue(result);

		result = PersonUtil.hasSectionWithDeductionTypeAndAmount(finResult.getApplicableDeductions(), sectionName,
				FiConstants.DEDUCTION_HEALTH_CHECKUP, 5000);

		assertTrue(result);
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}

	@Test
	public void test80DSelfResidentIndividualAboveSixty() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setAge(61);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSectionWithDeductionType(finResult.getApplicableDeductions(), sectionName,
				FiConstants.DEDUCTION_MECICAL_INSURANCE);

		assertTrue(result);

		result = PersonUtil.hasSectionWithDeductionTypeNTimes(finResult.getDeductions(), sectionName,
				FiConstants.DEDUCTION_MECICAL_INSURANCE, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithDeductionTypeAndAmount(finResult.getApplicableDeductions(), sectionName,
				FiConstants.DEDUCTION_MECICAL_INSURANCE, 30000);

		assertTrue(result);

		result = PersonUtil.hasSectionWithDeductionTypeAndAmount(finResult.getApplicableDeductions(), sectionName,
				FiConstants.DEDUCTION_HEALTH_CHECKUP, 5000);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}

}
