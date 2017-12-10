package com.work.itpa.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.Assessee;
import com.work.itpa.domain.Assessment;
import com.work.itpa.domain.Income;
import com.work.itpa.itparules.ItpaApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80TTATest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80TTA";
	
	@Rule public TestName testName = new TestName();


	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeBelow10K() {
		
		BigDecimal income = new BigDecimal("9000.00");
		
		
		Assessee fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, income, Income.Type.INTEREST,Income.Source.NA, "Interest from savings bank ");
		Assessment finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income);

		assertEquals(true, result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName, income);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}

	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeEqualTo10K() {

		BigDecimal income = new BigDecimal("10000.00");
		
		
		Assessee fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, income, Income.Type.INTEREST,Income.Source.NA, "Interest from savings bank ");
		Assessment finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income);

		assertEquals(true, result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName, income);

		assertEquals(true, result);
		
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);		
	}

	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeGreaterThan10K() {
		
		String section80TTAName = "80TTA";
		
		BigDecimal income = new BigDecimal("11000.00");
		
		BigDecimal expected = new BigDecimal("10000.00");
		
		
		Assessee fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, income, Income.Type.INTEREST,Income.Source.NA, "Interest from savings bank ");
		Assessment finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), section80TTAName, income);

		assertEquals(true, result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), section80TTAName, expected);

		assertEquals(true, result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
}
