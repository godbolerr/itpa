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
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.domain.Insurance;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80DTest {

	@Autowired
	ItpaService dService;

	String sectionName = "80D";
	
	@Rule public TestName testName = new TestName();
	
	@Test
	public void test80DSelfResidentIndividualBetween1And59() {
//		FinPerson fPerson = PersonUtil.getBachelorMale();
//		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
//		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
//		
//		fPerson.addInsurance(new Insurance("LIFE", "TRM_PLAN", "FAMILY", new BigDecimal("78999"), new BigDecimal("4533"), 12));
//		
//
//		FinPersonResult finResult = dService.calculateBenefits(fPerson);
//
//		boolean result = PersonUtil.hasSectionWithDeductionTypeNTimes(finResult.getDeductions(), sectionName,
//				FiConstants.DEDUCTION_MECICAL_INSURANCE, 1);
//
//		assertTrue(result);
//
//		result = PersonUtil.hasSectionWithDeductionTypeAndAmount(finResult.getDeductions(), sectionName,
//				FiConstants.DEDUCTION_MECICAL_INSURANCE, 78999);
//
//		assertTrue(result);
//
//		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}

}
