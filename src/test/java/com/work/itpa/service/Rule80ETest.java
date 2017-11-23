package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

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
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80ETest {

	@Autowired
	ItpaService dService;

	String sectionName = "80E";
	
	@Rule public TestName testName = new TestName();

	@Test
	public void test80ESelfResidentIndividual() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		PersonUtil.addExpense(fPerson, 50000, FiConstants.RELATIONSHIP_SELF,FiConstants.EXPENSE_HIGHER_EDU_LOAN_INTEREST, "Interest Paid for loan on higher education of self");

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName,50000);

		assertTrue(result);
		

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}


	@Test
	public void test80ESelfResidentIndividualSelfAndWife() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		PersonUtil.addExpense(fPerson, 34000 , FiConstants.RELATIONSHIP_SELF,FiConstants.EXPENSE_HIGHER_EDU_LOAN_INTEREST, "Interest Paid for loan on higher education of self");
		//PersonUtil.addExpense(fPerson, 34000 , FiConstants.RELATIONSHIP_WIFE,FiConstants.EXPENSE_HIGHER_EDU_LOAN_INTEREST, "Interest Paid for loan on higher education of wife");

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName,68000);

		assertTrue(result);
		

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 2);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}

}
