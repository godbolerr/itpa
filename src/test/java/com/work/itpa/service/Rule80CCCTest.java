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
public class Rule80CCCTest {
	
	
	@Rule public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName80ccc = "80CCC";
	
	String sectionName80cCombined = "80C";

	@Test
	public void test80cccSuperAnnuation() {
		Assessee assessee = PersonUtil.getBachelorMale();
		
		BigDecimal superAnnuationAmount = new BigDecimal("40000");
		
		assessee.addInvestment(new Investment(superAnnuationAmount,"SUPERANNUATION","Super Annuation related Investment "));
		
		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccc);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccc, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ccc, superAnnuationAmount);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ccc, superAnnuationAmount);

		assertEquals(true, result);
		
		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}



	@Test
	public void test80cccSuperAnnuationExceedsMax() {
		Assessee assessee = PersonUtil.getBachelorMale();
		
		BigDecimal superAnnuationAmount = new BigDecimal("240000");
		BigDecimal expected80CCCAmount = new BigDecimal("150000");
		
		assessee.addInvestment(new Investment(superAnnuationAmount,"SUPERANNUATION","Super Annuation related Investment "));
		
		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccc);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccc, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ccc, superAnnuationAmount);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ccc, expected80CCCAmount);

		assertEquals(true, result);
		
		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}

}
