/**
 * 
 */
package com.work.itpa.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.itpa.domain.Deduction;
import com.work.itpa.domain.Disability;
import com.work.itpa.domain.Disease;
import com.work.itpa.domain.Donation;
import com.work.itpa.domain.Expense;
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.Assessee;
import com.work.itpa.domain.Assessment;
import com.work.itpa.domain.Income;
import com.work.itpa.domain.Investment;
import com.work.itpa.domain.Person;
import com.work.itpa.domain.PropertyDetails;
import com.work.itpa.domain.StatusFlag;
import com.work.itpa.domain.SummaryDeduction;
import com.work.itpa.domain.SystemFlag;

/**
 * Generate person data based on certain conditions
 * 
 * @author Developer
 *
 */
public class PersonUtil {

	private static final Logger LOG = LoggerFactory.getLogger(PersonUtil.class);

	public static String PERSON_NAME = "Ram Kumar";
	public static String PERSON_EMAIL = "rkumartyty@gmail.com";
	public static String PERSON_CONTACT = "+91 998789 8765";
	public static String PERSON_AADHAR = "34564567890";
	public static String PERSON_PAN = "OPDID9987A";


	public static Assessee getAssessee() {

		Assessee assessee = new Assessee();

		assessee.setEmail(PERSON_EMAIL);
		assessee.setContactNumber(PERSON_CONTACT);
		assessee.setAadharNumber(PERSON_AADHAR);
		assessee.setPanNumber(PERSON_PAN);
		assessee.setAssesseeType("INDIVIDUAL");
		assessee.setResidentialStatus("RESIDENT");
		assessee.setCurrency("INR");
		assessee.setLocale("en_IN");
		assessee.setAssessmentYear("2017-2018");

		Disability disablity = new Disability("", "", "");
		assessee.setDisablity(disablity);

		Disease disease = new Disease();
		assessee.setDisease(disease);

		SystemFlag sflag = new SystemFlag();
		sflag.setHasSalary(Boolean.FALSE);
		sflag.setHasDisabilitySelf(Boolean.FALSE);

		assessee.setSystemFlag(sflag);

		StatusFlag statusFlag = new StatusFlag();

		assessee.setStatusFlag(statusFlag);

		Person self = new Person();
		self.setpId("343545");
		self.setfName("Ram");
		self.setlName("Kumar");
		self.setAge(30);
		self.setRelationType("SELF");
		self.setRelationShipCode(FiConstants.RELATIONSHIP_SELF);
		self.setGender(Person.Gender.MALE);
		self.setDateOfBirth("1970-10-04");
		assessee.addFamily(self);

		return assessee;

	}

	public static void addDonation(Assessee person, BigDecimal amount, Donation.Type type, String note) {
		person.addDonation(new Donation(amount, type, note));
	}

	public static void addDonation(Assessee person, BigDecimal amount, Donation.Type type, String schemeCode,
			String note) {
		person.addDonation(new Donation(amount, type, schemeCode, note));
	}

	public static void addDonation(Assessee person, double amount, Donation.Type type, String schemeCode, String note) {
		person.addDonation(new Donation(BigDecimal.valueOf(amount), type, note));
	}

	public static void addExpense(Assessee person, double amount, String type, String note) {
		person.addExpense(new Expense(BigDecimal.valueOf(amount), type, note));
	}

	public static void addExpense(Assessee person, double amount, String relationShipCode, String type, String note) {
		Expense exp = new Expense(BigDecimal.valueOf(amount), type, note);
		exp.setRelationShipCode(relationShipCode);
		person.addExpense(exp);
	}

	public static void addIncome(Assessee person, BigDecimal amount, Income.Type type, Income.Source source,
			String note) {
		person.addIncome(new Income(amount, type, source, note));
	}

	public static void addPropertyDetails(Assessee person, String name, String city, String status,
			double propertyValue, double loanAmount, double annualInterest, boolean firstProperty) {
		PropertyDetails details = new PropertyDetails(name, city, status);
		details.setLoanValue(BigDecimal.valueOf(loanAmount));
		details.setAnnualInterest(BigDecimal.valueOf(annualInterest));
		details.setFirstProperty(firstProperty);
		details.setPropertyValue(BigDecimal.valueOf(propertyValue));

	}

	public static Assessee getBachelorMale() {
		Assessee person = getAssessee();
		return person;
	}

	public static Assessee getBachelorMaleWithWard() {
		Assessee person = getAssessee();
		return person;
	}

	/**
	 * Return PERSON with relationshipCode
	 * 
	 * @param assessee
	 * @return
	 */
	public static Person getPersonWithRelation(Assessee assessee, String relationshipCode) {

		Person p = null;

		List<Person> family = assessee.getFamily();

		for (Iterator<Person> iterator = family.iterator(); iterator.hasNext();) {
			Person person = iterator.next();
			if (relationshipCode.equals(person.getRelationShipCode())) {
				p = person;
			}
		}
		return p;

	}

	public static Assessee getBachelorMaleAbove60() {
		Assessee person = getAssessee();
		return person;
	}

	public static Assessee addInvestment(Assessee person, double amount, String type, String note) {
		person.addInvestment(new Investment(BigDecimal.valueOf(amount), type, note));
		return person;
	}

	public static boolean hasSectionWithAmount(List<Deduction> deductions, String section, double amount) {

		BigDecimal newAmount = BigDecimal.valueOf(amount);

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = (Deduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType())
					&& deduction.getAmount().compareTo(newAmount) == 0) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasSectionWithAmount(List<Deduction> deductions, String section, BigDecimal amount) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator<Deduction> iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType()) && deduction.getAmount().equals(amount)) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasSectionWithAllAmounts(List<Deduction> deductions, String section, BigDecimal amount,
			BigDecimal eligibleAmount, BigDecimal maxDeduction) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		boolean status = false;

		for (Iterator<Deduction> iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType()) && deduction.getAmount().equals(amount)) {
				status = true;
			} else {
				LOG.error("Amount not matching : " + deduction.getAmount() + " - " + amount);
				status = false;
			}

			if (section.equalsIgnoreCase(deduction.getSectionType())
					&& deduction.getEligibleDeduction().equals(eligibleAmount)) {
				status = true;
			} else {
				LOG.error(
						"Eligible Amount not matching : " + deduction.getEligibleDeduction() + " - " + eligibleAmount);
				status = false;
			}

			if (section.equalsIgnoreCase(deduction.getSectionType())
					&& deduction.getMaxDeduction().equals(maxDeduction)) {
				status = true;
			} else {
				LOG.error("Max Amount not matching : " + deduction.getMaxDeduction() + " - " + maxDeduction);
				status = false;
			}

		}
		return status;
	}

	public static boolean hasSection(List<Deduction> deductions, String section) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = (Deduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType())) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasSummarySection(List<SummaryDeduction> deductions, String section) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator<SummaryDeduction> iterator = deductions.iterator(); iterator.hasNext();) {
			SummaryDeduction deduction = (SummaryDeduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType())) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasSummarySectionWithAmount(List<SummaryDeduction> deductions, String sectionType,
			BigDecimal amount) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator<SummaryDeduction> iterator = deductions.iterator(); iterator.hasNext();) {
			SummaryDeduction summaryDeduction = (SummaryDeduction) iterator.next();

			if (sectionType.equalsIgnoreCase(summaryDeduction.getSectionType())
					&& summaryDeduction.getEligibleAmount().compareTo(amount) == 0) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasSectionNTimes(List<Deduction> deductions, String section, int noOfTimes) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		int count = 0;

		for (Iterator<Deduction> iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType())) {
				count++;
			}
		}

		if (count == noOfTimes) {
			return true;
		}
		return false;
	}

	public static boolean hasSectionWithDeductionTypeAndAmount(List<Deduction> deductions, String section,
			String deductionType, double amount) {

		BigDecimal newAmount = BigDecimal.valueOf(amount);

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = (Deduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType())
					&& deductionType.equalsIgnoreCase(deduction.getDeductionType())
					&& deduction.getAmount().compareTo(newAmount) == 0) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasSectionWithDeductionType(List<Deduction> deductions, String section,
			String deductionType) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = (Deduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType())
					&& deductionType.equalsIgnoreCase(deduction.getDeductionType())

			) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasSectionWithDeductionTypeNTimes(List<Deduction> deductions, String section,
			String deductionType, int noOfTimes) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		int count = 0;

		for (Iterator<Deduction> iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType())
					&& deductionType.equalsIgnoreCase(deduction.getDeductionType())

			) {
				count++;
			}
		}

		if (count == noOfTimes) {
			return true;
		}
		return false;
	}

	public static void logTestResult(String testName, Assessee finPerson, Assessment result) {

		String inputJson = testName + "_in.json";
		String outputJson = testName + "_out.json";

		ObjectMapper mapper = new ObjectMapper();

		String printJson = System.getProperty("print.json");

		if ("y".equalsIgnoreCase(printJson)) {

			try {
				mapper.writeValue(new File(inputJson), finPerson);
				mapper.writeValue(new File(outputJson), result);

			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
