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

import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.domain.Income;
import com.work.itpa.domain.Investment;
import com.work.itpa.domain.Person;
import com.work.itpa.domain.SystemFlag;
import com.work.itpa.itparules.ItpaApp;
import com.work.itpa.utils.PersonUtil;


//TODO Add Summary Section

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80CCD11Test {

	@Rule
	public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName80ccd11 = "80CCD1";

	@Test
	public void test80CCD1PensionSchemeInvestmentEmployee() {

		FinPerson fPerson = PersonUtil.getBachelorMale();
		
		Person self = PersonUtil.getPersonWithRelation(fPerson, FiConstants.RELATIONSHIP_SELF);
		self.setAge(20);
		
		SystemFlag sflag = new SystemFlag();
		
		sflag.setHasSalary(Boolean.TRUE);
		
		fPerson.setSystemFlag(sflag);

		BigDecimal basicIncome = new BigDecimal("40000");
		BigDecimal daIncome = new BigDecimal("10000");
		
		BigDecimal pensionSchemeContribution = new BigDecimal("3000");
		

		fPerson.addIncome(new Income(basicIncome,Income.Type.BASIC_SALARY,Income.Source.NA,"Basic Income"));
		
		fPerson.addIncome(new Income(daIncome,Income.Type.DEARNESS_ALLOWANCE,Income.Source.NA,"Dearness Income"));
		
		fPerson.addInvestment(new Investment(pensionSchemeContribution, "PS_EMPLOYEE", "Investment in Pension Scheme by Employee "));
		
		
		
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccd11);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccd11, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ccd11,pensionSchemeContribution);

		assertTrue(result);
		
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ccd11,
				pensionSchemeContribution);

		assertEquals(true, result);		

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
	
	

	@Test
	public void test80CCD1PensionSchemeInvestmentEmployeeMaxLimit() {

		FinPerson fPerson = PersonUtil.getBachelorMale();
		
		Person self = PersonUtil.getPersonWithRelation(fPerson, FiConstants.RELATIONSHIP_SELF);
		self.setAge(20);
		
		SystemFlag sflag = new SystemFlag();
		
		sflag.setHasSalary(Boolean.TRUE);
		
		fPerson.setSystemFlag(sflag);

		BigDecimal basicIncome = new BigDecimal("40000");
		BigDecimal daIncome = new BigDecimal("10000");
		
		BigDecimal pensionSchemeContribution = new BigDecimal("6000");
		
		BigDecimal expectedDeduction = new BigDecimal("5000");
		

		fPerson.addIncome(new Income(basicIncome,Income.Type.BASIC_SALARY,Income.Source.NA,"Basic Income"));
		
		fPerson.addIncome(new Income(daIncome,Income.Type.DEARNESS_ALLOWANCE,Income.Source.NA,"Dearness Income"));
		
		fPerson.addInvestment(new Investment(pensionSchemeContribution, "PS_EMPLOYEE", "Investment in Pension Scheme by Employee more than Max"));
		
		
		
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccd11);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccd11, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ccd11,pensionSchemeContribution);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ccd11,
				expectedDeduction);

		assertEquals(true, result);
		

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}	
}
