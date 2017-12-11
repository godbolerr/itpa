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
public class Rule80QQBTest {

	@Autowired
	ItpaService dService;

	String sectionName80qqb = "80QQB";
	
	String summarysectionName80qqb = "80QQB";

	@Rule
	public TestName testName = new TestName();

	@Test
	public void test80QQBResidentIndividualAuthorIncomeSingle() {

		BigDecimal income = new BigDecimal("20000.00");

		Assessee assessee = PersonUtil.getBachelorMale();
		PersonUtil.addIncome(assessee, income, Income.Type.ROYALTY, Income.Source.BOOK, "Income from Authoring book 1");
		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80qqb, income);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}

	@Test
	public void test80QQBResidentIndividualAuthorIncomeDouble() {

		BigDecimal income1 = new BigDecimal("20000.00");
		BigDecimal income2 = new BigDecimal("30000.00");

		Assessee assessee = PersonUtil.getBachelorMale();

		PersonUtil.addIncome(assessee, income1, Income.Type.ROYALTY, Income.Source.BOOK, "Income from Authoring book 1");
		PersonUtil.addIncome(assessee, income2, Income.Type.ROYALTY, Income.Source.BOOK, "Income from Authoring book 2");

		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80qqb, income1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80qqb, income2);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), summarysectionName80qqb,
				income1.add(income2));

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}

	@Test
	public void test80QQBResidentIndividualAuthorIncomeDoubleExceedsLimit() {

		BigDecimal income1 = new BigDecimal("200000.00");
		BigDecimal income2 = new BigDecimal("100009.00");
		
		

		BigDecimal expectedExemption = new BigDecimal("300000.00");

		Assessee assessee = PersonUtil.getBachelorMale();

		PersonUtil.addIncome(assessee, income1, Income.Type.ROYALTY, Income.Source.BOOK, "Income from Authoring book 1");
		PersonUtil.addIncome(assessee, income2, Income.Type.ROYALTY, Income.Source.BOOK, "Income from Authoring book 2");

		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80qqb, income1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80qqb, income2);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), summarysectionName80qqb,
				expectedExemption);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}

}
