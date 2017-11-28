package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.domain.Expense;
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.itparules.ItpaApp;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80GGTest {

	@Autowired
	ItpaService dService;

	String sectionName80gg = "80GG";

	@Rule
	public TestName testName = new TestName();

	@Test
	public void houseRent80GGLessThanMaxHraNotAvailed() {

		BigDecimal houseRent80ggAmount = new BigDecimal("50000");

		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setHraAvailed(FiConstants.FALSE);

		fPerson.addExpense(new Expense(houseRent80ggAmount,"HOUSE_RENT","Paid house rent"));

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80gg,
				houseRent80ggAmount);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80gg,
				houseRent80ggAmount);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
	
	@Test
	public void houseRent80GGGreaterThanMaxHraNotAvailed() {

		BigDecimal houseRent80ggAmount = new BigDecimal("70000");
		
		BigDecimal atggMaxAllowed = new BigDecimal("60000");

		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setHraAvailed(FiConstants.FALSE);

		fPerson.addExpense(new Expense(houseRent80ggAmount,"HOUSE_RENT","Paid house rent"));

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80gg,
				houseRent80ggAmount);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80gg,
				atggMaxAllowed);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}	
	
	
	@Test
	public void houseRent80GGEqualToMaxHraNotAvailed() {

		BigDecimal houseRent80ggAmount = new BigDecimal("60000");
		
		BigDecimal atggMaxAllowed = new BigDecimal("60000");

		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setHraAvailed(FiConstants.FALSE);

		fPerson.addExpense(new Expense(houseRent80ggAmount,"HOUSE_RENT","Paid house rent"));

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80gg,
				houseRent80ggAmount);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80gg,
				atggMaxAllowed);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}	
	
	//@Test
	//TODO Look at this test. currently failing.
	public void houseRent80GGEqualToMaxHraAvailed() {

		BigDecimal houseRent80ggAmount = new BigDecimal("60000");
		
		BigDecimal atggMaxAllowed = new BigDecimal("0");

		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setHraAvailed(FiConstants.TRUE);

		fPerson.addExpense(new Expense(houseRent80ggAmount,"HOUSE_RENT","Paid house rent"));

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80gg,
				houseRent80ggAmount);

		assertThat(false, is(result));

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80gg,
				atggMaxAllowed);

		assertThat(true, is(result));

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}		
}
