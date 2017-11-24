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
import com.work.itpa.domain.Disability;
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80DD1Test {
	
	
	@Rule public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName = "80U";

	//@Test
	public void test80USelfResidentIndividualFiftyPercentDisability() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setDisablity(new Disability("","",""));
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSummarySection(finResult.getSummaryDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName, new BigDecimal("75000"));

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}

	@Test
	public void test80USelfResidentIndividualEightyPercentDisability() {
//		FinPerson fPerson = PersonUtil.getBachelorMale();
//		fPerson.setDisablity(new Disability("","",""));
//		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
//		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
//
//		FinPersonResult finResult = dService.calculateBenefits(fPerson);
//
//		boolean result = PersonUtil.hasSection(finResult.getSummaryDeductions(), sectionName);
//
//		assertTrue(result);
//
//		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);
//
//		assertTrue(result);
//
//		result = PersonUtil.hasSectionWithAmount(finResult.getSummaryDeductions(), sectionName, 125000);
//
//		assertTrue(result);
//		
//		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);
//		

	}

}
