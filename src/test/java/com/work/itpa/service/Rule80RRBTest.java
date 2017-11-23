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
import com.work.itpa.domain.Income;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80RRBTest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80RRB";
	
	@Rule public TestName testName = new TestName();
	

	@Test
	public void test80RRBResidentIndividualOnePatentIncome() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 20000, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 20000);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}

	@Test
	public void test80RRBResidentIndividualTwoPatentIncome() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 20000, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 1");
		PersonUtil.addIncome(fPerson, 22000, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 2 ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 20000);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 22000);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 42000);

		assertTrue(totalResult);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}

	@Test
	public void test80RRBResidentIndividualThreePatentIncomeFractions() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 20000.14, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 1");
		PersonUtil.addIncome(fPerson, 22000.13, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 2 ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 20000.14);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 22000.13);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 42000.27);

		assertTrue(totalResult);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
}
