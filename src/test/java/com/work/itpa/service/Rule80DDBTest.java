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
public class Rule80DDBTest {

	@Autowired
	ItpaRuleAdminService dService;
	
	String sectionName = "80DDB";

	@Rule public TestName testName = new TestName();

	@Test
	public void test80DDBResidentIndividualHasDisease() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.selfPerson().setAge(41);
		fPerson.selfPerson().setDisease("ABCD");
		// Associated disable wife with 45 percent disability
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 40000);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


	}	
	
	@Test
	public void test80DDBResidentIndividualHasDiseaseWithAgeSixtyOne() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.selfPerson().setAge(61);
		fPerson.selfPerson().setDisease("ABCD");
		// Associated disable wife with 45 percent disability
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 60000);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}		

	@Test
	public void test80DDBResidentIndividualHasDiseaseWithAgeEightyOne() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.selfPerson().setAge(81);
		fPerson.selfPerson().setDisease("ABCD");
		// Associated disable wife with 45 percent disability
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 80000);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}		


	@Test
	public void test80DDBResidentIndividualAge58HasDiseaseWithFatherAbove60() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.selfPerson().setAge(58);
		fPerson.selfPerson().setDisease("ABCD");
		// Associated disable wife with 45 percent disability
		

		Person father = PersonUtil.getPerson();
		
		
		father.setGender(FiConstants.GENDER_MALE);
		father.setRelationShipCode(FiConstants.RELATIONSHIP_FATHER);
		father.setName("xxxx");
		father.setDisease("ABCD");
		father.setAge(62);

		fPerson.addPerson(father);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 2);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 60000);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}		
	

	@Test
	public void test80DDBResidentIndividualAge58HasDiseaseWithMotherAbove80() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.selfPerson().setAge(58);
		fPerson.selfPerson().setDisease("ABCD");
		// Associated disable wife with 45 percent disability
		

		Person mother = PersonUtil.getPerson();
		
		
		mother.setGender(FiConstants.GENDER_FEMALE);
		mother.setRelationShipCode(FiConstants.RELATIONSHIP_MOTHER);
		mother.setName("xxxx");
		mother.setDisease("ABCD");
		mother.setAge(82);

		fPerson.addPerson(mother);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 2);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 80000);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}	
}
