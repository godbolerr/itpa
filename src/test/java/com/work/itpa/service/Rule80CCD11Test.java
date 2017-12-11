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
import com.work.itpa.domain.Assessee;
import com.work.itpa.domain.Assessment;
import com.work.itpa.domain.Income;
import com.work.itpa.domain.Investment;
import com.work.itpa.domain.Person;
import com.work.itpa.domain.SystemFlag;
import com.work.itpa.itparules.ItpaApp;


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

		Assessee assessee = PersonUtil.getBachelorMale();
		
		Person self = PersonUtil.getPersonWithRelation(assessee, FiConstants.RELATIONSHIP_SELF);
		self.setAge(20);
		
		SystemFlag sflag = new SystemFlag();
		
		sflag.setHasSalary(Boolean.TRUE);
		
		assessee.setSystemFlag(sflag);

		BigDecimal basicIncome = new BigDecimal("40000");
		BigDecimal daIncome = new BigDecimal("10000");
		
		BigDecimal pensionSchemeContribution = new BigDecimal("3000");
		

		assessee.addIncome(new Income(basicIncome,Income.Type.BASIC_SALARY,Income.Source.NA,"Basic Income"));
		
		assessee.addIncome(new Income(daIncome,Income.Type.DEARNESS_ALLOWANCE,Income.Source.NA,"Dearness Income"));
		
		assessee.addInvestment(new Investment(pensionSchemeContribution, "PS_EMPLOYEE", "Investment in Pension Scheme by Employee "));
		
		
		
		
		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccd11);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccd11, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ccd11,pensionSchemeContribution);

		assertTrue(result);
		
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ccd11,
				pensionSchemeContribution);

		assertEquals(true, result);		

		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}
	
	

	@Test
	public void test80CCD1PensionSchemeInvestmentEmployeeMaxLimit() {

		Assessee assessee = PersonUtil.getBachelorMale();
		
		Person self = PersonUtil.getPersonWithRelation(assessee, FiConstants.RELATIONSHIP_SELF);
		self.setAge(20);
		
		SystemFlag sflag = new SystemFlag();
		
		sflag.setHasSalary(Boolean.TRUE);
		
		assessee.setSystemFlag(sflag);

		BigDecimal basicIncome = new BigDecimal("40000");
		BigDecimal daIncome = new BigDecimal("10000");
		
		BigDecimal pensionSchemeContribution = new BigDecimal("6000");
		
		BigDecimal expectedDeduction = new BigDecimal("5000");
		

		assessee.addIncome(new Income(basicIncome,Income.Type.BASIC_SALARY,Income.Source.NA,"Basic Income"));
		
		assessee.addIncome(new Income(daIncome,Income.Type.DEARNESS_ALLOWANCE,Income.Source.NA,"Dearness Income"));
		
		assessee.addInvestment(new Investment(pensionSchemeContribution, "PS_EMPLOYEE", "Investment in Pension Scheme by Employee more than Max"));
		
		
		
		
		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccd11);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccd11, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ccd11,pensionSchemeContribution);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ccd11,
				expectedDeduction);

		assertEquals(true, result);
		

		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}	
}
