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

import com.work.itpa.domain.Disease;
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.domain.Person;
import com.work.itpa.domain.SystemFlag;
import com.work.itpa.itparules.ItpaApp;
import com.work.itpa.utils.PersonUtil;

//TODO Add Summary Section

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80DDB2Test {

	@Rule
	public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName80ddb = "80DDB";

	@Test
	public void testSepcifiedDiseaseForDependentBetweenSixtyToSeventyNine() {

		BigDecimal amountSpent = new BigDecimal("20000");

		BigDecimal amountRecovered = new BigDecimal("10000");

		FinPerson fPerson = PersonUtil.getBachelorMale();


		SystemFlag sflag = new SystemFlag();
		fPerson.setSystemFlag(sflag);

		Disease disease = fPerson.getDisease();

		disease.setAmountSpent(amountSpent);
		
		disease.setAmountRecovered(amountRecovered);
		
		disease.setDependentAge("60_TO_79");

		BigDecimal expectedAtDdbAmount = new BigDecimal("10000");

		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ddb);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ddb, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ddb, expectedAtDdbAmount);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ddb, expectedAtDdbAmount);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
	
	
	@Test
	public void testSepcifiedDiseaseForDependentBetweenSixtyToSeventyNineMoreThanLimit() {

		BigDecimal amountSpent = new BigDecimal("120000");

		BigDecimal amountRecovered = new BigDecimal("10000");

		FinPerson fPerson = PersonUtil.getBachelorMale();


		SystemFlag sflag = new SystemFlag();
		fPerson.setSystemFlag(sflag);

		Disease disease = fPerson.getDisease();

		disease.setAmountSpent(amountSpent);
		
		disease.setAmountRecovered(amountRecovered);
		
		disease.setDependentAge("60_TO_79");

		BigDecimal expectedAtDdbAmount = new BigDecimal("60000");

		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ddb);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ddb, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ddb, amountSpent);

		assertTrue(result);
		
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ddb, expectedAtDdbAmount);

		assertTrue(result);		

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}

}
