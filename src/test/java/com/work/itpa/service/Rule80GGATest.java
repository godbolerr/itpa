package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.ItpaApp;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80GGATest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80GGA";
	
	@Rule public TestName testName = new TestName();


	@Test
	public void test80GGCResidentIndividualResearchDonations() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		PersonUtil.addDonation(fPerson, 20000, FiConstants.DONATION_SCIENTIFIC_RESEARCH,
				"Donation to scientific research for biology");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, 20000);

		assertTrue(result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}
}
