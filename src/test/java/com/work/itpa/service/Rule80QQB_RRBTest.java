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
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.domain.Income;
import com.work.itpa.itparules.ItpaApp;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80QQB_RRBTest {

	@Autowired
	ItpaService dService;

	String sectionNameQQB = "80QQB";
	
	String sectionNameRRB = "80RRB";
	
	String summarySectionName = "80QQB_80RRB";

	@Rule
	public TestName testName = new TestName();



	@Test
	public void test80QQBResidentIndividualAuthorIncomeDoubleExceedsLimit() {

		BigDecimal income1 = new BigDecimal("200000.00");
		BigDecimal income2 = new BigDecimal("100009.00");
		
		BigDecimal income = new BigDecimal("20000.00");

		BigDecimal expectedExemption = new BigDecimal("300000.00");

		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, income1, Income.Type.ROYALTY, Income.Source.BOOK, "Income from Authoring book 1");
		PersonUtil.addIncome(fPerson, income2, Income.Type.ROYALTY, Income.Source.BOOK, "Income from Authoring book 2");
		PersonUtil.addIncome(fPerson, income, Income.Type.ROYALTY,Income.Source.PATENT, "Income from Patent ");

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionNameQQB, income1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionNameQQB, income2);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionNameRRB, income);

		assertTrue(result);
		
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), summarySectionName,
				expectedExemption);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}

}
