package com.work.itpa.rules;

import java.math.BigDecimal;

/**
 * 
 * @author Developer
 *
 */
public class Deduction {

	public BigDecimal amount;

	/**
	 * Sub-Type of section for which limit is utilized
	 */
	public String deductionType;

	/**
	 * Section Type under which it is allowed.
	 */

	public String sectionType;

	/**
	 * Maximum deduction under this section
	 */

	public BigDecimal maxDeduction;

	/**
	 * Eligible deduction for the assessor
	 */
	public BigDecimal eligibleDeduction;

	/**
	 * Notes associated with this deduction.
	 * 
	 */
	public String notes;
	
	/**
	 * @param amount
	 * @param deductionType
	 * @param sectionType
	 * @param maxDeduction
	 * @param eligibleDeduction
	 * @param notes
	 */
	public Deduction(BigDecimal amount, BigDecimal eligibleDeduction,  BigDecimal maxDeduction, String deductionType, String sectionType, String notes) {
		super();
		this.amount = amount;
		this.deductionType = deductionType;
		this.sectionType = sectionType;
		this.maxDeduction = maxDeduction;
		this.eligibleDeduction = eligibleDeduction;
		this.notes = notes;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the deductionType
	 */
	public String getDeductionType() {
		return deductionType;
	}

	/**
	 * @param deductionType
	 *            the deductionType to set
	 */
	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}

	/**
	 * @return the sectionType
	 */
	public String getSectionType() {
		return sectionType;
	}

	/**
	 * @param sectionType
	 *            the sectionType to set
	 */
	public void setSectionType(String sectionType) {
		this.sectionType = sectionType;
	}

	/**
	 * @return the maxDeduction
	 */
	public BigDecimal getMaxDeduction() {
		return maxDeduction;
	}

	/**
	 * @param maxDeduction
	 *            the maxDeduction to set
	 */
	public void setMaxDeduction(BigDecimal maxDeduction) {
		this.maxDeduction = maxDeduction;
	}

	/**
	 * @return the eligibleDeduction
	 */
	public BigDecimal getEligibleDeduction() {
		return eligibleDeduction;
	}

	/**
	 * @param eligibleDeduction
	 *            the eligibleDeduction to set
	 */
	public void setEligibleDeduction(BigDecimal eligibleDeduction) {
		this.eligibleDeduction = eligibleDeduction;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Deduction() {
	}

}
