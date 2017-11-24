package com.work.itpa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
public class Rule80TTATest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80TTA";
	
	@Rule public TestName testName = new TestName();


	//@Test
	public void test80TTAResidentIndividualSavingInterestIncomeBelow10K() {
		
		BigDecimal income = new BigDecimal("9000.00");
		
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, income, Income.Type.ROYALTY,Income.Source.PATENT, "Interest from savings bank ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}

	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeEqualTo10K() {
//		FinPerson fPerson = PersonUtil.getBachelorMale();
//		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
//		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
//		PersonUtil.addIncome(fPerson, 10000, Income.Type.ROYALTY,Income.Source.PATENT, "Interest from savings bank ");
//		FinPersonResult finResult = dService.calculateBenefits(fPerson);
//
//		// Verify section and amount deducted
//
//		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 10000);
//
//		assertTrue(result);
//		
//		
//		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


		
	}

	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeGreaterThan10K() {
		
		BigDecimal income = new BigDecimal("10001.00");
		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, income , Income.Type.ROYALTY,Income.Source.PATENT, "Interest from savings bank ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income);

		assertFalse(result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
}
