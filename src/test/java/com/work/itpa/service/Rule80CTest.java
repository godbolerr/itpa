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
import com.work.itpa.domain.Assessee;
import com.work.itpa.domain.Assessment;
import com.work.itpa.domain.Insurance;
import com.work.itpa.domain.Investment;
import com.work.itpa.domain.Loan;
import com.work.itpa.itparules.ItpaApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80CTest {
	
	
	@Rule public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName80c = "80C";

	@Test
	public void test80c1InvestmentPpf() {
		
		Assessee fPerson = PersonUtil.getBachelorMale();
		
		BigDecimal providentFundAmount80c = new BigDecimal("40000");
		
		BigDecimal maxDeduction80c = new BigDecimal("150000");
		
		
		fPerson.addInvestment(new Investment(providentFundAmount80c,"PPF_CONTRIBUTION","Invest in PPF Funds "));
		
		Assessment finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAllAmounts(finResult.getDeductions(), sectionName80c, providentFundAmount80c,providentFundAmount80c,maxDeduction80c);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, providentFundAmount80c);

		assertEquals(true, result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}

	
	@Test
	public void test80c1InvestmentPpfExceedingMax() {
		
		Assessee fPerson = PersonUtil.getBachelorMale();
		
		BigDecimal providentFundAmount80c = new BigDecimal("151000");
		
		BigDecimal maxDeduction80c = new BigDecimal("150000");
		
		
		fPerson.addInvestment(new Investment(providentFundAmount80c,"PPF_CONTRIBUTION","Invest in PPF Funds "));
		
		Assessment finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80c);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80c, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAllAmounts(finResult.getDeductions(), sectionName80c, providentFundAmount80c,maxDeduction80c,maxDeduction80c);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80c, maxDeduction80c);

		assertEquals(true, result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
	
	
	
	
	@Test
	public void test80c1PpfAndSCCSInvestment() {
		
		
		Assessee fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		BigDecimal providentFundAmount80c = new BigDecimal("75000");
		
		BigDecimal scssAmount80c = new BigDecimal("100000");
		
		BigDecimal expectedAmount80c = new BigDecimal("150000");
		
		fPerson.addInvestment(new Investment(providentFundAmount80c,"PPF_CONTRIBUTION","Invest in PPF Funds "));
		
		fPerson.addInvestment(new Investment(scssAmount80c,"SCSS","Invest in SCSS "));
		
		Assessment finResult = dService.calculateBenefits(fPerson);

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
	public void test80c2ExpensesTutionFees() {
		Assessee fPerson = PersonUtil.getBachelorMale();
		
		BigDecimal tutionFeeExpense = new BigDecimal("75000");
		
		BigDecimal expectedAmount80c = new BigDecimal("75000");
		
		fPerson.addExpense(new Expense(tutionFeeExpense,"TUTION_FEES","Tution Fee for son "));
		
		Assessment finResult = dService.calculateBenefits(fPerson);

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
	public void test80c2ExpensesTutionFeesExceedMax() {
		Assessee fPerson = PersonUtil.getBachelorMale();
		
		BigDecimal tutionFeeExpense = new BigDecimal("175000");
		
		BigDecimal expectedAmount80c = new BigDecimal("150000");
		
		fPerson.addExpense(new Expense(tutionFeeExpense,"TUTION_FEES","Tution Fee for son "));
		
		Assessment finResult = dService.calculateBenefits(fPerson);

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
	public void test80c3LoanPropertyLoanPrincipalRepayment() {
		Assessee fPerson = PersonUtil.getBachelorMale();
		
		BigDecimal interestRepayment = new BigDecimal("7000");
		
		BigDecimal principalRepayment = new BigDecimal("75000");
		
		BigDecimal expectedAmount80c = new BigDecimal("75000");
		
		fPerson.addLoan(new Loan("PROPERTY",interestRepayment,principalRepayment,"Property Loan - Repayment of Principal "));
		
		Assessment finResult = dService.calculateBenefits(fPerson);

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
	public void test80c3ExpensePropertyLoanPrincipalRepaymentExeedsMax() {
		Assessee fPerson = PersonUtil.getBachelorMale();
	
		
		BigDecimal interestRepayment = new BigDecimal("7000");
		
		BigDecimal principalRepayment = new BigDecimal("275000");
		
		BigDecimal expectedAmount80c = new BigDecimal("150000");
		
		fPerson.addLoan(new Loan("PROPERTY",interestRepayment,principalRepayment,"Property Loan - Repayment of Principal for Big House"));
		
		Assessment finResult = dService.calculateBenefits(fPerson);

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
	public void test80c4InsuranceLifeInsurancePremiumSingle() {
		Assessee fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		
		
		BigDecimal lifeInsurancePremiumPaid = new BigDecimal("7000");
		
		BigDecimal expectedAmount80c = new BigDecimal("7000");
		
		fPerson.addInsurance(new Insurance("LIFE",lifeInsurancePremiumPaid,"Life Insurance Premium paid on First Policy"));
		
		Assessment finResult = dService.calculateBenefits(fPerson);

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
	public void test80c4InsuranceLifeInsurancePremiumTwoTimes() {
		Assessee fPerson = PersonUtil.getBachelorMale();
	
		
		BigDecimal lifeInsurancePremiumPaidLic = new BigDecimal("70000");
		
		BigDecimal lifeInsurancePremiumPaidMaxLife = new BigDecimal("80000");
		
		BigDecimal expectedAmount80c = new BigDecimal("150000");
		
		fPerson.addInsurance(new Insurance("LIFE",lifeInsurancePremiumPaidLic,"Life Insurance Premium paid on LIC Policy"));

		fPerson.addInsurance(new Insurance("LIFE",lifeInsurancePremiumPaidMaxLife,"Life Insurance Premium paid on MaxLife Policy"));
		
		Assessment finResult = dService.calculateBenefits(fPerson);

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
	public void test80c4InsuranceLifeInsuranceAndPropertyPremiumExceedsMax() {
		Assessee fPerson = PersonUtil.getBachelorMale();
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
		
		
		Assessment finResult = dService.calculateBenefits(fPerson);

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
