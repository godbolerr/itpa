package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.ItpadocApp;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.rules.Person;
import com.work.itpa.web.rest.util.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpadocApp.class)
public class Rule80DDTest {

	@Autowired
	ItpaService dService;

	String sectionName = "80DD";
	
	@Rule public TestName testName = new TestName();
	
	@Test
	public void test80DDResidentIndividualMarriedWithDisabledWifeOfFortyFivePercent() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		Person wife = PersonUtil.getPerson();
		
		wife.setDisabilityPercent(45);
		wife.setGender(FiConstants.GENDER_FEMALE);
		wife.setRelationShipCode(FiConstants.RELATIONSHIP_WIFE);
		wife.setName("Aasha");
		wife.setAge(34);
		
		fPerson.addChildren(wife);
		
		// Associated disable wife with 45 percent disability
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 75000);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}	

	@Test
	public void test80DDResidentIndividualMarriedWithDisabledWifeOfEightyFivePercent() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		Person wife = PersonUtil.getPerson();
		
		wife.setDisabilityPercent(85);
		wife.setGender(FiConstants.GENDER_FEMALE);
		wife.setRelationShipCode(FiConstants.RELATIONSHIP_WIFE);
		wife.setName("Aasha");
		wife.setAge(34);
		
		fPerson.addChildren(wife);
		
		// Associated disable wife with 45 percent disability
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 125000);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}	


	@Test
	public void test80DDResidentHUFMarriedWithDisabledWifeOfEightyFivePercent() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_HUF);

		Person hufMember = PersonUtil.getPerson();
		
		hufMember.setDisabilityPercent(85);
		hufMember.setGender(FiConstants.GENDER_FEMALE);
		hufMember.setRelationShipCode(FiConstants.RELATIONSHIP_HUFMEMBER);
		hufMember.setName("Aasha");
		hufMember.setAge(34);
		
		fPerson.addChildren(hufMember);
		
		// Associated disable wife with 45 percent disability
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 125000);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}	
	
}
