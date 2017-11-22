/**
 * 
 */
package com.work.itpa.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Loan taken by the Assessee
 * 
 * @author Developer
 *
 */
public class Loan {

	BigDecimal interestPaidPerAnum;

	BigDecimal principalPaidPerAnum;

	BigDecimal loanAmount;

	String type;

	String institution;

	String loanPeriod;

	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	Date loanStartDate;

	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	Date loanEndDate;
	
	
	
	public Loan(){}
	

	/**
	 * @param interestPaidPerAnum
	 * @param principalPaidPerAnum
	 * @param loanAmount
	 * @param type
	 * @param institution
	 * @param loanPeriod
	 * @param loanStartDate
	 * @param loanEndDate
	 */
	public Loan(BigDecimal interestPaidPerAnum, BigDecimal principalPaidPerAnum, BigDecimal loanAmount, String type,
			String institution, String loanPeriod, Date loanStartDate, Date loanEndDate) {
		super();
		this.interestPaidPerAnum = interestPaidPerAnum;
		this.principalPaidPerAnum = principalPaidPerAnum;
		this.loanAmount = loanAmount;
		this.type = type;
		this.institution = institution;
		this.loanPeriod = loanPeriod;
		this.loanStartDate = loanStartDate;
		this.loanEndDate = loanEndDate;
	}

	/**
	 * @return the interestPaidPerAnum
	 */
	public BigDecimal getInterestPaidPerAnum() {
		return interestPaidPerAnum;
	}

	/**
	 * @param interestPaidPerAnum
	 *            the interestPaidPerAnum to set
	 */
	public void setInterestPaidPerAnum(BigDecimal interestPaidPerAnum) {
		this.interestPaidPerAnum = interestPaidPerAnum;
	}

	/**
	 * @return the principalPaidPerAnum
	 */
	public BigDecimal getPrincipalPaidPerAnum() {
		return principalPaidPerAnum;
	}

	/**
	 * @param principalPaidPerAnum
	 *            the principalPaidPerAnum to set
	 */
	public void setPrincipalPaidPerAnum(BigDecimal principalPaidPerAnum) {
		this.principalPaidPerAnum = principalPaidPerAnum;
	}

	/**
	 * @return the loanAmount
	 */
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @param loanAmount
	 *            the loanAmount to set
	 */
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}

	/**
	 * @param institution
	 *            the institution to set
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
	}

	/**
	 * @return the loanPeriod
	 */
	public String getLoanPeriod() {
		return loanPeriod;
	}

	/**
	 * @param loanPeriod
	 *            the loanPeriod to set
	 */
	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	/**
	 * @return the loanStartDate
	 */
	public Date getLoanStartDate() {
		return loanStartDate;
	}

	/**
	 * @param loanStartDate
	 *            the loanStartDate to set
	 */
	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	/**
	 * @return the loanEndDate
	 */
	public Date getLoanEndDate() {
		return loanEndDate;
	}

	/**
	 * @param loanEndDate
	 *            the loanEndDate to set
	 */
	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

}
