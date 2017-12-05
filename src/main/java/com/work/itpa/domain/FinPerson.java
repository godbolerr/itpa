/**
 * 
 */
package com.work.itpa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Contains information about the evaluator
 * 
 * TODO Refactoring and documentation required.
 * 
 * @author developer
 *
 */
public class FinPerson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String email;

	String contactNumber;

	String panNumber;

	String aadharNumber;

	String residentialStatus;

	String maritalStatus;

	String assesseeType;

	String assessmentYear;

	int noOfDependents;

	String currency;

	String locale;

	List<Person> family;

	Disability disablity;

	Disease disease;

	List<Income> incomes;

	List<Expense> expenses;

	public BigDecimal grossTotalIncome;

	List<Loan> loans;

	List<CapitalAsset> capitalAssets;

	List<Investment> investments;

	List<Insurance> insurances;

	List<Donation> donations;
	
	
	SystemFlag systemFlag;
	
	
	StatusFlag statusFlag;
	
	

	String hraAvailed;

	public FinPerson() {
	}

	/**
	 * @param dateOfBirth
	 * @param gender
	 * @param relationShipCode
	 * @param disabilityPercent
	 * @param disease
	 */
	public FinPerson(String name, String residentStatus, Date dateOfBirth, String gender, String relationShipCode,
			int disabilityPercent, String disease) {
		// super(name, residentStatus, dateOfBirth, gender, relationShipCode,
		// disabilityPercent, disease);
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

	public void addLoan(Loan loan) {
		if (this.loans == null) {
			this.loans = new ArrayList<Loan>();
		}
		loans.add(loan);

	}

	public void addDonation(Donation donation) {

		if (this.donations == null) {
			this.donations = new ArrayList<Donation>();
		}
		this.donations.add(donation);
	}

	public void addIncome(Income income) {

		if (this.incomes == null) {
			this.incomes = new ArrayList<Income>();
		}
		this.incomes.add(income);
	}

	public void addInvestment(Investment investment) {
		if (this.investments == null) {
			this.investments = new ArrayList<Investment>();
		}
		this.investments.add(investment);
	}

	public void addExpense(Expense expense) {
		if (this.expenses == null) {
			this.expenses = new ArrayList<Expense>();
		}
		this.expenses.add(expense);
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
	 * @return the grossTotalIncome
	 */
	public BigDecimal getGrossTotalIncome() {
		return grossTotalIncome;
	}

	/**
	 * @param grossTotalIncome
	 *            the grossTotalIncome to set
	 */
	public void setGrossTotalIncome(BigDecimal grossTotalIncome) {
		this.grossTotalIncome = grossTotalIncome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aadharNumber == null) ? 0 : aadharNumber.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((panNumber == null) ? 0 : panNumber.hashCode());

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FinPerson other = (FinPerson) obj;
		if (aadharNumber == null) {
			if (other.aadharNumber != null) {
				return false;
			}
		} else if (!aadharNumber.equals(other.aadharNumber)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (panNumber == null) {
			if (other.panNumber != null) {
				return false;
			}
		} else if (!panNumber.equals(other.panNumber)) {
			return false;
		}

		return true;
	}

	/**
	 * @return the hraAvailed
	 */
	public String getHraAvailed() {
		return hraAvailed;
	}

	/**
	 * @param hraAvailed
	 *            the hraAvailed to set
	 */
	public void setHraAvailed(String hraAvailed) {
		this.hraAvailed = hraAvailed;
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

	public void addFamily(Person person) {
		if (this.family == null) {
			this.family = new ArrayList<Person>();
		}
		family.add(person);
	}

	public void addInsurance(Insurance insurance) {
		if (this.insurances == null) {
			this.insurances = new ArrayList<Insurance>();
		}
		insurances.add(insurance);
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
	 * @return the systemFlag
	 */
	public SystemFlag getSystemFlag() {
		return systemFlag;
	}

	/**
	 * @param systemFlag the systemFlag to set
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
	 * @param statusFlag the statusFlag to set
	 */
	public void setStatusFlag(StatusFlag statusFlag) {
		this.statusFlag = statusFlag;
	}
	
	

}
