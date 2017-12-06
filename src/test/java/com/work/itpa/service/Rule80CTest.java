package com.work.itpa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

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
import com.work.itpa.domain.Insurance;
import com.work.itpa.domain.Investment;
import com.work.itpa.domain.Loan;
import com.work.itpa.itparules.ItpaApp;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80CTest {
	
	
	@Rule public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName80c = "80C";

	@Test
	public void test80CPPFContribution() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		
		BigDecimal providentFundAmount80c = new BigDecimal("40000");
		
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		fPerson.addInvestment(new Investment(providentFundAmount80c,"PPF_CONTRIBUTION","Invest in PPF Funds "));
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, providentFundAmount80c);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, providentFundAmount80c);

		assertEquals(true, result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
	
	@Test
	public void test80CPPFandSCCSContribution() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		BigDecimal providentFundAmount80c = new BigDecimal("75000");
		
		BigDecimal scssAmount80c = new BigDecimal("100000");
		
		BigDecimal expectedAmount80c = new BigDecimal("150000");
		
		fPerson.addInvestment(new Investment(providentFundAmount80c,"PPF_CONTRIBUTION","Invest in PPF Funds "));
		
		fPerson.addInvestment(new Investment(scssAmount80c,"SCSS","Invest in SCSS "));
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 2);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, providentFundAmount80c);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, scssAmount80c);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, expectedAmount80c);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);
		

	}	

	
	

	@Test
	public void test80CTutionFees() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		BigDecimal tutionFeeExpense = new BigDecimal("75000");
		
		BigDecimal expectedAmount80c = new BigDecimal("75000");
		
		fPerson.addExpense(new Expense(tutionFeeExpense,"TUTION_FEES","Tution Fee for son "));
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, tutionFeeExpense);

		assertTrue(result);
				
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, expectedAmount80c);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);
		

	}	
	
	
	@Test
	public void test80CTutionFeesExceedMax() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		BigDecimal tutionFeeExpense = new BigDecimal("175000");
		
		BigDecimal expectedAmount80c = new BigDecimal("150000");
		
		fPerson.addExpense(new Expense(tutionFeeExpense,"TUTION_FEES","Tution Fee for daughter's higher education "));
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, tutionFeeExpense);

		assertTrue(result);
				
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, expectedAmount80c);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);
		

	}	
	
	
	@Test
	public void test80CPropertyLoanPrincipalRepayment() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		
		
		BigDecimal interestRepayment = new BigDecimal("7000");
		
		BigDecimal principalRepayment = new BigDecimal("75000");
		
		BigDecimal expectedAmount80c = new BigDecimal("75000");
		
		fPerson.addLoan(new Loan("PROPERTY",interestRepayment,principalRepayment,"Property Loan - Repayment of Principal "));
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, principalRepayment);

		assertTrue(result);
				
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, expectedAmount80c);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);
		

	}	
	
	
	
	@Test
	public void test80CPropertyLoanPrincipalRepaymentExeedsMax() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		
		
		BigDecimal interestRepayment = new BigDecimal("7000");
		
		BigDecimal principalRepayment = new BigDecimal("275000");
		
		BigDecimal expectedAmount80c = new BigDecimal("150000");
		
		fPerson.addLoan(new Loan("PROPERTY",interestRepayment,principalRepayment,"Property Loan - Repayment of Principal for Big House"));
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, principalRepayment);

		assertTrue(result);
				
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, expectedAmount80c);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);
		

	}	
	
		
	

	@Test
	public void test80CLifeInsurancePremiumSingle() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		
		
		BigDecimal lifeInsurancePremiumPaid = new BigDecimal("7000");
		
		BigDecimal expectedAmount80c = new BigDecimal("7000");
		
		fPerson.addInsurance(new Insurance("LIFE",lifeInsurancePremiumPaid,"Life Insurance Premium paid on First Policy"));
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, lifeInsurancePremiumPaid);

		assertTrue(result);
				
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, expectedAmount80c);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);
		

	}	
	
		

	@Test
	public void test80CLifeInsurancePremiumTwoTimes() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		
		
		BigDecimal lifeInsurancePremiumPaidLic = new BigDecimal("70000");
		
		BigDecimal lifeInsurancePremiumPaidMaxLife = new BigDecimal("80000");
		
		BigDecimal expectedAmount80c = new BigDecimal("150000");
		
		fPerson.addInsurance(new Insurance("LIFE",lifeInsurancePremiumPaidLic,"Life Insurance Premium paid on LIC Policy"));

		fPerson.addInsurance(new Insurance("LIFE",lifeInsurancePremiumPaidMaxLife,"Life Insurance Premium paid on MaxLife Policy"));
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 2);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, lifeInsurancePremiumPaidLic);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, lifeInsurancePremiumPaidMaxLife);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, expectedAmount80c);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);
		

	}	
	
	
	
	

	@Test
	public void test80CLifeInsuranceAndPropertyPremium() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		BigDecimal interestRepayment = new BigDecimal("10500");
		BigDecimal principalRepayment = new BigDecimal("43567");
		
		BigDecimal lifeInsurancePremiumPaidLic = new BigDecimal("70000");
		
		BigDecimal lifeInsurancePremiumPaidMaxLife = new BigDecimal("62000");
		
		BigDecimal expectedAmount80c = new BigDecimal("150000");
		
		fPerson.addInsurance(new Insurance("LIFE",lifeInsurancePremiumPaidLic,"Life Insurance Premium paid on LIC Policy"));

		fPerson.addInsurance(new Insurance("LIFE",lifeInsurancePremiumPaidMaxLife,"Life Insurance Premium paid on MaxLife Policy"));
		

		fPerson.addLoan(new Loan("PROPERTY",interestRepayment,principalRepayment,"Property Loan - Repayment of Principal for Big House"));
		
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 3);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, lifeInsurancePremiumPaidLic);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, lifeInsurancePremiumPaidMaxLife);

		assertTrue(result);
		

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80c, principalRepayment);

		assertTrue(result);

		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, expectedAmount80c);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);
		

	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
