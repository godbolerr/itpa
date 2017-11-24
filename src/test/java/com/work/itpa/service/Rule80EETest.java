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
import com.work.itpa.domain.Loan;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80EETest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80EE";
	
	@Rule public TestName testName = new TestName();
	

	
	//@Test
	public void test80EEInterestPaid() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
	
		Loan loan = new Loan();
		loan.setType("EDUCATION");
		loan.setInterestPaidPerAnum(new BigDecimal(10000));
		fPerson.addLoan(loan);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);
		
		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName,10000);
		
		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
	
	
	@Test
	public void test80EEInterestPaidAboveLimit() {
//		FinPerson fPerson = PersonUtil.getBachelorMale();
//		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
//		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
//		Loan loan = new Loan();
//		loan.setType("EDUCATION");
//		loan.setInterestPaidPerAnum(new BigDecimal(2000000));
//		fPerson.addLoan(loan);
//		
//		FinPersonResult finResult = dService.calculateBenefits(fPerson);
//
//		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName);
//
//		assertTrue(result);
//
//		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);
//		
//		assertTrue(result);
//
//		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName,2000000);
//		
//		assertTrue(result);
//
//		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	
	}
}
