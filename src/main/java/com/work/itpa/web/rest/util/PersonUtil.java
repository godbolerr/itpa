/**
 * 
 */
package com.work.itpa.web.rest.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.itpa.rules.Deduction;
import com.work.itpa.rules.Donation;
import com.work.itpa.rules.Expense;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.rules.Income;
import com.work.itpa.rules.Investment;
import com.work.itpa.rules.Person;
import com.work.itpa.rules.PropertyDetails;

/**
 * Generate person data based on certain conditions
 * 
 * @author Developer
 *
 */
public class PersonUtil {

	public static String PERSON_NAME = "Ram Kumar";
	public static String PERSON_EMAIL = "rkumartyty@gmail.com";

	public static FinPerson getFinPerson() {

		Person pSelf = new Person(PERSON_NAME, new Date(), 40, FiConstants.GENDER_MALE, FiConstants.RELATIONSHIP_SELF,
				0, "");

		FinPerson fPerson = new FinPerson();
		fPerson.addPerson(pSelf);

		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);

		fPerson.setEmail(PERSON_EMAIL);
		fPerson.setContactNumber("+1 998 889 8888");
		fPerson.setAadharNumber("9998887878");
		fPerson.setPanNumber("OPDID9987A");
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		fPerson.setEmploymentType("GOV");
		fPerson.setSalaryBasicPerMonth(BigDecimal.valueOf(3939));
		fPerson.setSalaryPerAnum(BigDecimal.valueOf(49494949));
		fPerson.setPpfContributionPerMonth(BigDecimal.valueOf(4848));
		fPerson.setDearnessAllowancePerMonth(BigDecimal.valueOf(4949));
		fPerson.setPensionContributionPerMonth(BigDecimal.valueOf(9695));

		// fPerson.addInvestment(new Investment(BigDecimal.valueOf(35000),
		// "LIC", "LIC 1"));
		// fPerson.addInvestment(new Investment(BigDecimal.valueOf(20000),
		// "LIC", "LIC 2"));
		// fPerson.addInvestment(new Investment(BigDecimal.valueOf(35000),
		// "PPF", "PPF 1"));
		//
		// fPerson.addDonation(new Donation(BigDecimal.valueOf(1000), "XX",
		// "Money Given to charity 1"));
		// fPerson.addDonation(new Donation(BigDecimal.valueOf(500), "YY", "PM
		// Relief Fund"));
		// fPerson.addIncome(new Income(BigDecimal.valueOf(1939), "Book",
		// "Income from books"));
		// fPerson.addIncome(new Income(BigDecimal.valueOf(111), "Patent",
		// "Patent for new work"));
		// fPerson.addLoan(new Loan(BigDecimal.valueOf(34949),
		// BigDecimal.valueOf(24555), BigDecimal.valueOf(3455567),
		// "HOUSING", "sbi", "33", new Date(), new Date()));
		// fPerson.addPropertyDetails(new PropertyDetails("X House", "Pune",
		// "OWN"));
		//
		// fPerson.setAddress(new Address("510 Bldg B", "Sea Colony", "Mumbai",
		// "MH", "INDIA", "444001",
		// "Near Rly Station", "PERSONAL"));
		//
		// fPerson.addChildren(new Person("Lata", new Date(), "Female", "WIFE",
		// 0, ""));
		// fPerson.addChildren(new Person("Sham", new Date(), "Male", "SON", 0,
		// ""));
		// fPerson.addChildren(new Person("Sita", new Date(), "Female",
		// "DAUGHTER", 0, ""));
		//
		// fPerson.addDependent(new Person("Sitaram", new Date(), "Male",
		// "FATHER", 20, "xxx"));
		// fPerson.addDependent(new Person("Gauri", new Date(), "Female",
		// "MOTHER", 90, "yyy"));

		return fPerson;

	}

	public static void addDonation(FinPerson person, double amount, String type, String note) {
		person.addDonation(new Donation(BigDecimal.valueOf(amount), type, note));
	}

	public static void addExpense(FinPerson person, double amount, String type, String note) {
		person.addExpense(new Expense(BigDecimal.valueOf(amount), type, note));
	}

	public static void addExpense(FinPerson person, double amount, String relationShipCode, String type, String note) {
		Expense exp = new Expense(BigDecimal.valueOf(amount), type, note);
		exp.setRelationShipCode(relationShipCode);
		person.addExpense(exp);
	}

	public static void addIncome(FinPerson person, double amount, String reasonCode, String note) {
		person.addIncome(new Income(BigDecimal.valueOf(amount), reasonCode, note));
	}

	public static void addPropertyDetails(FinPerson person, String name, String city, String status,
			double propertyValue, double loanAmount, double annualInterest, boolean firstProperty) {
		PropertyDetails details = new PropertyDetails(name, city, status);
		details.setLoanValue(BigDecimal.valueOf(loanAmount));
		details.setAnnualInterest(BigDecimal.valueOf(annualInterest));
		details.setFirstProperty(firstProperty);
		details.setPropertyValue(BigDecimal.valueOf(propertyValue));
		person.addPropertyDetails(details);
	}

	public static FinPerson getBachelorMale() {
		FinPerson person = getFinPerson();
		return person;
	}

	public static FinPerson getBachelorMaleWithWard() {
		FinPerson person = getFinPerson();
		person.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		Person ward = new Person("Sham", new Date(), 25, FiConstants.GENDER_FEMALE, FiConstants.RELATIONSHIP_WARD, 0,
				"");
		person.addPerson(ward);
		return person;
	}

	public static Person getPerson() {

		return new Person("XXX", new Date(), 40, FiConstants.GENDER_FEMALE, FiConstants.RELATIONSHIP_HUFMEMBER, 0, "");
	}

	public static FinPerson getBachelorMaleWithHUFMember() {
		FinPerson person = getFinPerson();
		person.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		Person hufMember = new Person("Laxman", new Date(), 40, FiConstants.GENDER_FEMALE,
				FiConstants.RELATIONSHIP_HUFMEMBER, 0, "");
		person.addPerson(hufMember);
		return person;
	}

	public static FinPerson getBachelorMaleAbove60() {
		FinPerson person = getFinPerson();
		person.getSelf().setAge(61);
		return person;
	}

	public static FinPerson getMarriedMale() {
		FinPerson fPerson = getFinPerson();

		fPerson.getSelf().setMaritalStatus(FiConstants.MARITAL_MARRIED);

		Person wife = new Person("Lata", new Date(), 40, FiConstants.GENDER_FEMALE, FiConstants.RELATIONSHIP_WIFE, 0,
				"");
		wife.setAge(37);
		fPerson.addPerson(wife);

		return fPerson;
	}

	public static FinPerson getMarriedMaleWithOneDaughter() {
		FinPerson person = getMarriedMale();
		Person daughter = new Person("Aasha", new Date(), 40, FiConstants.GENDER_FEMALE,
				FiConstants.RELATIONSHIP_DAUGHTER, 0, "");
		daughter.setAge(17);
		person.addPerson(daughter);
		return person;
	}

	public static FinPerson addInvestment(FinPerson person, double amount, String type, String note) {
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

			if (section.equalsIgnoreCase(deduction.getSection()) && deduction.getAmount().compareTo(newAmount) == 0) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasSection(List<Deduction> deductions, String section) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = (Deduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSection())) {
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

			if (section.equalsIgnoreCase(deduction.getSection())) {
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

			if (section.equalsIgnoreCase(deduction.getSection()) && deductionType.equalsIgnoreCase(deduction.getType())
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

			if (section.equalsIgnoreCase(deduction.getSection()) && deductionType.equalsIgnoreCase(deduction.getType())

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

			if (section.equalsIgnoreCase(deduction.getSection()) && deductionType.equalsIgnoreCase(deduction.getType())

			) {
				count++;
			}
		}

		if (count == noOfTimes) {
			return true;
		}
		return false;
	}

	public static void logTestResult(String testName, FinPerson finPerson, FinPersonResult result) {

		String inputJson = testName + "_in.json";
		String outputJson = testName + "_out.json";

		ObjectMapper mapper = new ObjectMapper();
		
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
