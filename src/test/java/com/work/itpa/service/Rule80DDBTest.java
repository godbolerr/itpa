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
import com.work.itpa.rules.Disease;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.rules.Person;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80DDBTest {

	@Autowired
	ItpaService dService;
	
	String sectionName = "80DDB";

	@Rule public TestName testName = new TestName();

	@Test
	public void test80DDBResidentIndividualHasDisease() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		//fPerson.setDisease(new Disease("","",30,new BigDecimal(0),new BigDecimal(0)));
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
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		//fPerson.setDisease(new Disease("","",30,new BigDecimal(0),new BigDecimal(0)));
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
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		//fPerson.setDisease(new Disease("","",30,new BigDecimal(0),new BigDecimal(0)));
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
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setDisease(new Disease("","","30",new BigDecimal(0),new BigDecimal(0)));
		// Associated disable wife with 45 percent disability
		

		Person father = PersonUtil.getPerson();
		
		
		father.setGender(FiConstants.GENDER_MALE);
		father.setRelationShipCode(FiConstants.RELATIONSHIP_FATHER);
		father.setName("xxxx");
		//fPerson.setDisease(new Disease("","",30,new BigDecimal(0),new BigDecimal(0)));
		father.setAge(62);

		fPerson.addFamily(father);
		
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
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		//fPerson.setDisease(new Disease("","",30,new BigDecimal(0),new BigDecimal(0)));
		// Associated disable wife with 45 percent disability
		

		Person mother = PersonUtil.getPerson();
		
		
		mother.setGender(FiConstants.GENDER_FEMALE);
		mother.setRelationShipCode(FiConstants.RELATIONSHIP_MOTHER);
		mother.setName("xxxx");
		mother.setAge(82);

		fPerson.addFamily(mother);
		
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
