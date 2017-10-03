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
public class Rule80QQBTest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80QQB";
	
	@Rule public TestName testName = new TestName();


	@Test
	public void test80QQBResidentIndividualAuthorIncomeSingle() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 13000, FiConstants.INCOME_AUTHOR, "Income from Authoring book 1");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 13000);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}

	@Test
	public void test80QQBResidentIndividualAuthorIncomeDouble() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 24000.20, FiConstants.INCOME_AUTHOR, "Income from Authoring book 1");
		PersonUtil.addIncome(fPerson, 25000.50, FiConstants.INCOME_AUTHOR, "Income from Authoring book 2");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 24000.20);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 25000.50);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 49000.70);

		assertTrue(totalResult);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
}
