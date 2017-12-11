/**
 * 
 */
package com.work.itpa.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for providing information for Assessee to do Assessment.
 * 
 * - Information about family - Income - Loans - Donations - Expenses - Property
 * Details
 * 
 * @author developer
 *
 */
public class Assessee {

	public enum MaritalStatus {
		SINGLE, MARRIED, NA
	};

	public enum ResidentialStatus {
		RESIDENT, NON_RESIDENT, OTHER, NA
	};

	public enum AssesseeType {
		INDIVIDUAL, HUF, OTHER, NA
	};

	/**
	 * Email of Assessee
	 */
	String email;

	/**
	 * Contact number
	 */
	String contactNumber;

	/**
	 * Permanent Account Number - PAN
	 */
	String panNumber;

	/**
	 * National Identification Number - Aadhar
	 */
	String aadharNumber;

	/**
	 * Residential Status of the Assessee - NonResident, Resident TODO Enum
	 */
	public String residentialStatus;

	/**
	 * Marital status of the person in the family
	 */
	public String maritalStatus;

	/**
	 * Type of Asssessee - Based on this rules will change.
	 * 
	 */
	String assesseeType;

	/**
	 * Year for which assessment is required.
	 */
	String assessmentYear;

	/**
	 * No of dependents, assessee has
	 */
	int noOfDependents;

	/**
	 * Currency in which deduction and other results are computed
	 */
	String currency;

	/**
	 * Locale of the Assessee
	 */
	String locale;

	/**
	 * Information about family members.
	 * 
	 */
	List<Person> family;

	/**
	 * Disability details
	 */
	Disability disablity;

	/**
	 * Details specific to disesase
	 */
	Disease disease;

	/**
	 * Earning specific details
	 */
	List<Income> incomes;

	/**
	 * Expenses in the given year
	 */
	List<Expense> expenses;

	/**
	 * Loans availed and related details
	 */
	List<Loan> loans;

	/**
	 * Details about capital asset
	 */
	List<CapitalAsset> capitalAssets;

	/**
	 * Investments made by Assessee
	 */
	List<Investment> investments;

	/**
	 * Insurance details
	 */
	List<Insurance> insurances;

	/**
	 * Details about donations made.
	 */
	List<Donation> donations;

	/**
	 * Flags used internally by the system
	 */
	SystemFlag systemFlag;

	/**
	 * Status flags used by the system
	 */
	StatusFlag statusFlag;


	public Assessee() {
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the panNumber
	 */
	public String getPanNumber() {
		return panNumber;
	}

	/**
	 * @param panNumber
	 *            the panNumber to set
	 */
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	/**
	 * @return the aadharNumber
	 */
	public String getAadharNumber() {
		return aadharNumber;
	}

	/**
	 * @param aadharNumber
	 *            the aadharNumber to set
	 */
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	/**
	 * @return the residentialStatus
	 */
	public String getResidentialStatus() {
		return residentialStatus;
	}

	/**
	 * @param residentialStatus
	 *            the residentialStatus to set
	 */
	public void setResidentialStatus(String residentialStatus) {
		this.residentialStatus = residentialStatus;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the assesseeType
	 */
	public String getAssesseeType() {
		return assesseeType;
	}

	/**
	 * @param assesseeType
	 *            the assesseeType to set
	 */
	public void setAssesseeType(String assesseeType) {
		this.assesseeType = assesseeType;
	}

	/**
	 * @return the assessmentYear
	 */
	public String getAssessmentYear() {
		return assessmentYear;
	}

	/**
	 * @param assessmentYear
	 *            the assessmentYear to set
	 */
	public void setAssessmentYear(String assessmentYear) {
		this.assessmentYear = assessmentYear;
	}

	/**
	 * @return the noOfDependents
	 */
	public int getNoOfDependents() {
		return noOfDependents;
	}

	/**
	 * @param noOfDependents
	 *            the noOfDependents to set
	 */
	public void setNoOfDependents(int noOfDependents) {
		this.noOfDependents = noOfDependents;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the family
	 */
	public List<Person> getFamily() {
		return family;
	}

	/**
	 * @param family
	 *            the family to set
	 */
	public void setFamily(List<Person> family) {
		this.family = family;
	}

	/**
	 * @return the disablity
	 */
	public Disability getDisablity() {
		return disablity;
	}

	/**
	 * @param disablity
	 *            the disablity to set
	 */
	public void setDisablity(Disability disablity) {
		this.disablity = disablity;
	}

	/**
	 * @return the disease
	 */
	public Disease getDisease() {
		return disease;
	}

	/**
	 * @param disease
	 *            the disease to set
	 */
	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	/**
	 * @return the incomes
	 */
	public List<Income> getIncomes() {
		return incomes;
	}

	/**
	 * @param incomes
	 *            the incomes to set
	 */
	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}

	/**
	 * @return the expenses
	 */
	public List<Expense> getExpenses() {
		return expenses;
	}

	/**
	 * @param expenses
	 *            the expenses to set
	 */
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}


	/**
	 * @return the loans
	 */
	public List<Loan> getLoans() {
		return loans;
	}

	/**
	 * @param loans
	 *            the loans to set
	 */
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	/**
	 * @return the capitalAssets
	 */
	public List<CapitalAsset> getCapitalAssets() {
		return capitalAssets;
	}

	/**
	 * @param capitalAssets
	 *            the capitalAssets to set
	 */
	public void setCapitalAssets(List<CapitalAsset> capitalAssets) {
		this.capitalAssets = capitalAssets;
	}

	/**
	 * @return the investments
	 */
	public List<Investment> getInvestments() {
		return investments;
	}

	/**
	 * @param investments
	 *            the investments to set
	 */
	public void setInvestments(List<Investment> investments) {
		this.investments = investments;
	}

	/**
	 * @return the insurances
	 */
	public List<Insurance> getInsurances() {
		return insurances;
	}

	/**
	 * @param insurances
	 *            the insurances to set
	 */
	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	/**
	 * @return the donations
	 */
	public List<Donation> getDonations() {
		return donations;
	}

	/**
	 * @param donations
	 *            the donations to set
	 */
	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	/**
	 * @return the systemFlag
	 */
	public SystemFlag getSystemFlag() {
		return systemFlag;
	}

	/**
	 * @param systemFlag
	 *            the systemFlag to set
	 */
	public void setSystemFlag(SystemFlag systemFlag) {
		this.systemFlag = systemFlag;
	}

	/**
	 * @return the statusFlag
	 */
	public StatusFlag getStatusFlag() {
		return statusFlag;
	}

	/**
	 * @param statusFlag
	 *            the statusFlag to set
	 */
	public void setStatusFlag(StatusFlag statusFlag) {
		this.statusFlag = statusFlag;
	}

	/**
	 * Add Person to the family
	 * 
	 * @param person
	 */
	public void addFamily(Person person) {
		if (this.family == null) {
			this.family = new ArrayList<Person>();
		}
		family.add(person);

	}

	/**
	 * Add Loan
	 * 
	 * @param loan
	 */
	public void addLoan(Loan loan) {
		if (this.loans == null) {
			this.loans = new ArrayList<Loan>();
		}
		loans.add(loan);

	}

	/**
	 * Add Donation
	 * 
	 * @param donation
	 */
	public void addDonation(Donation donation) {

		if (this.donations == null) {
			this.donations = new ArrayList<Donation>();
		}
		this.donations.add(donation);
	}

	/**
	 * Add income
	 * 
	 * @param income
	 */
	public void addIncome(Income income) {

		if (this.incomes == null) {
			this.incomes = new ArrayList<Income>();
		}
		this.incomes.add(income);
	}

	/**
	 * Add investment
	 * 
	 * @param investment
	 */
	public void addInvestment(Investment investment) {
		if (this.investments == null) {
			this.investments = new ArrayList<Investment>();
		}
		this.investments.add(investment);
	}

	/**
	 * Add expense
	 * 
	 * @param expense
	 */
	public void addExpense(Expense expense) {
		if (this.expenses == null) {
			this.expenses = new ArrayList<Expense>();
		}
		this.expenses.add(expense);
	}

	/**
	 * Add insurance
	 * 
	 * @param insurance
	 */
	public void addInsurance(Insurance insurance) {
		if (this.insurances == null) {
			this.insurances = new ArrayList<Insurance>();
		}
		this.insurances.add(insurance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((panNumber == null) ? 0 : panNumber.hashCode());
		result = prime * result + ((residentialStatus == null) ? 0 : residentialStatus.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assessee other = (Assessee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (maritalStatus == null) {
			if (other.maritalStatus != null)
				return false;
		} else if (!maritalStatus.equals(other.maritalStatus))
			return false;
		if (panNumber == null) {
			if (other.panNumber != null)
				return false;
		} else if (!panNumber.equals(other.panNumber))
			return false;
		if (residentialStatus == null) {
			if (other.residentialStatus != null)
				return false;
		} else if (!residentialStatus.equals(other.residentialStatus))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Assessee [email=" + email + ", contactNumber=" + contactNumber + ", panNumber=" + panNumber
				+ ", aadharNumber=" + aadharNumber + ", residentialStatus=" + residentialStatus + ", maritalStatus="
				+ maritalStatus + ", assesseeType=" + assesseeType + ", assessmentYear=" + assessmentYear
				+ ", noOfDependents=" + noOfDependents + ", currency=" + currency + ", locale=" + locale + ", family="
				+ family + ", disablity=" + disablity + ", disease=" + disease + ", incomes=" + incomes + ", expenses="
				+ expenses  + ", loans=" + loans + ", capitalAssets="
				+ capitalAssets + ", investments=" + investments + ", insurances=" + insurances + ", donations="
				+ donations + ", systemFlag=" + systemFlag + ", statusFlag=" + statusFlag
				+ "]";
	}

	
	
}
