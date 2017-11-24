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

import com.work.itpa.ItpaApp;
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80GGTest {

	@Autowired
	ItpaService dService;

	String sectionName = "80GG";
	
	@Rule public TestName testName = new TestName();

	@Test
	public void test80GGHraExemption() {
//		FinPerson fPerson = PersonUtil.getBachelorMaleWithWard();
//		fPerson.setHraAvailed(false);
//		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
//		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
//		fPerson.setGrossTotalIncome(BigDecimal.valueOf(500000));
//		PersonUtil.addExpense(fPerson, 50000, FiConstants.EXPENSE_RENT, "Rent paid per anum ");
//		
//		FinPersonResult finResult = dService.calculateBenefits(fPerson);
//
//		boolean result = PersonUtil.hasSection(finResult.getSummaryDeductions(), "80GG");
//
//		assertTrue(result);
//
//		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), "80GG", 1);
//
//		assertTrue(result);
//		
//		
//		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
}
