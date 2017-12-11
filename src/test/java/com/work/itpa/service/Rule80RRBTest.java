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

import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.Assessee;
import com.work.itpa.domain.Assessment;
import com.work.itpa.domain.Income;
import com.work.itpa.itparules.ItpaApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80RRBTest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80RRB";
	
	@Rule public TestName testName = new TestName();
	
	String summarySectionName = "80RRB";
	

	@Test
	public void test80RRBResidentIndividualOnePatentIncome() {
		
		BigDecimal income = new BigDecimal("20000.00");
		
		Assessee assessee = PersonUtil.getBachelorMale();

		PersonUtil.addIncome(assessee, income, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent ");
		Assessment finResult = dService.calculateBenefits(assessee);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}

	@Test
	public void test80RRBResidentIndividualTwoPatentIncome() {
		
		BigDecimal income = new BigDecimal("20000.00");
		
		BigDecimal income2 = new BigDecimal("22000.00");
		
		BigDecimal totalIncome = new BigDecimal("42000");
		
		
		Assessee assessee = PersonUtil.getBachelorMale();

		PersonUtil.addIncome(assessee, income, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 1");
		PersonUtil.addIncome(assessee, income2, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 2 ");
		Assessment finResult = dService.calculateBenefits(assessee);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income2);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), summarySectionName, totalIncome );

		assertTrue(totalResult);
		
		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);


	}

	@Test
	public void test80RRBResidentIndividualTwoPatentIncomeFractions() {
		
		BigDecimal income = new BigDecimal("20000.14");
		
		BigDecimal income2 = new BigDecimal("22000.13");
		
		BigDecimal totalIncome = new BigDecimal("42000.27");
		
		Assessee assessee = PersonUtil.getBachelorMale();

		PersonUtil.addIncome(assessee, income, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 1");
		PersonUtil.addIncome(assessee, income2, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 2 ");
		Assessment finResult = dService.calculateBenefits(assessee);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, income2);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), summarySectionName,totalIncome );

		assertTrue(totalResult);
		
		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);


	}
	
	@Test
	public void test80RRBResidentIndividualTwoPatentIncomeFractionsExceedsLimit() {
		
		String section80RRBName = "80RRB";
		
		String summarySectionQQBRRBName = "80RRB";
		
		BigDecimal incomerrb = new BigDecimal("202000.14");
		
		BigDecimal income2rrb = new BigDecimal("322000.13");
		
		BigDecimal totalIncomeRrb = new BigDecimal("300000.00");
		
		Assessee assessee = PersonUtil.getBachelorMale();

		PersonUtil.addIncome(assessee, incomerrb, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 1");
		PersonUtil.addIncome(assessee, income2rrb, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent 2 ");
		Assessment finResult = dService.calculateBenefits(assessee);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), section80RRBName, incomerrb);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), section80RRBName, income2rrb);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), summarySectionQQBRRBName,totalIncomeRrb );

		assertTrue(totalResult);
		
		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);


	}	
}
