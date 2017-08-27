package com.work.itpa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.itparules.ItparulesApplication;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.utils.PersonUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItparulesApplication.class)
public class RuleTest {

	@Autowired
	ItpaService dService;

	@Test
	public void testResidentIndividualMaleBachelor() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		FinPersonResult result = dService.calculateBenefits(fPerson);
		// assertEquals(2, result.getMessages().size());
	}

	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeBelow10K() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 9000, FiConstants.INCOME_SAVINGINTEREST, "Interest from savings bank ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80TTA", BigDecimal.valueOf(9000));

		assertEquals(true, result);
	}

	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeEqualTo10K() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 10000, FiConstants.INCOME_SAVINGINTEREST, "Interest from savings bank ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80TTA", BigDecimal.valueOf(10000));

		assertTrue(result);
	}
	
	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeGreaterThan10K() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 10001, FiConstants.INCOME_SAVINGINTEREST, "Interest from savings bank ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80TTA", BigDecimal.valueOf(10001));

		assertFalse(result);
	}	
	
	
	@Test
	public void test80RRBResidentIndividualOnePatentIncome() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 20000, FiConstants.INCOME_PATENT, "Income from Patent ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80RRB", BigDecimal.valueOf(20000));

		assertTrue(result);
	}	
	
	@Test
	public void test80RRBResidentIndividualTwoPatentIncome() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 20000, FiConstants.INCOME_PATENT, "Income from Patent 1");
		PersonUtil.addIncome(fPerson, 22000, FiConstants.INCOME_PATENT, "Income from Patent 2 ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80RRB", BigDecimal.valueOf(Double.valueOf(20000.0d)));

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80RRB", BigDecimal.valueOf(Double.valueOf(22000.0d)));

		assertTrue(result);		
		
		//TODO : If there are more than 2 patents then final deduction should be sum of both . Confirm this.
		
		
		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getPlannedDeductions(), "80RRB", BigDecimal.valueOf(Double.valueOf(42000.0d)));

		assertTrue(totalResult);		
		
	}	
	
}
