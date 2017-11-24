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
		
		BigDecimal income = new BigDecimal("20000.00");
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, income, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}

	@Test
	public void test80RRBResidentIndividualTwoPatentIncome() {
		
		BigDecimal income = new BigDecimal("20000.00");
		
		BigDecimal income2 = new BigDecimal("22000.00");
		
		BigDecimal totalIncome = new BigDecimal("42000");
		
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, income, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 1");
		PersonUtil.addIncome(fPerson, income2, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 2 ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income2);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName, totalIncome );

		assertTrue(totalResult);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}

	@Test
	public void test80RRBResidentIndividualThreePatentIncomeFractions() {
		
		BigDecimal income = new BigDecimal("20000.14");
		
		BigDecimal income2 = new BigDecimal("22000.13");
		
		BigDecimal totalIncome = new BigDecimal("42000.27");
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, income, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 1");
		PersonUtil.addIncome(fPerson, income2, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 2 ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 20000.14);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 22000.13);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName,totalIncome );

		assertTrue(totalResult);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
}
