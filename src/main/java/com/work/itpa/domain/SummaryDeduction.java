package com.work.itpa.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Developer
 *
 */
public class SummaryDeduction {

	public String sectionType;

	public BigDecimal maxAmount;

	public BigDecimal eligibleAmount;

	List<Deduction> deductions;

	public String notes;

	/**
	 * @param sectionType
	 * @param maxAmount
	 * @param eligibleAmount
	 * @param notes
	 */
	public SummaryDeduction(String sectionType, BigDecimal maxAmount, String notes) {
		super();
		this.sectionType = sectionType;
		this.maxAmount = maxAmount;
		this.notes = notes;
		eligibleAmount = new BigDecimal("0");
		this.deductions = new ArrayList<Deduction>();
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
	 * @return the maxAmount
	 */
	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	/**
	 * @param maxAmount
	 *            the maxAmount to set
	 */
	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * @return the eligibleAmount
	 */
	public BigDecimal getEligibleAmount() {
		return eligibleAmount;
	}

	/**
	 * @param eligibleAmount
	 *            the eligibleAmount to set
	 */
	public void setEligibleAmount(BigDecimal eligibleAmount) {
		this.eligibleAmount = eligibleAmount;
	}

	/**
	 * @return the deductions
	 */
	public List<Deduction> getDeductions() {
		return deductions;
	}

	/**
	 * @param deductions
	 *            the deductions to set
	 */
	public void setDeductions(List<Deduction> deductions) {
		this.deductions = deductions;
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

	/**
	 * Summary deduction can have children from multiple sub-sections
	 * 
	 * @param deduction
	 */
	public void addDeduction(Deduction deduction) {
		deductions.add(deduction);

		/**
		 * Explore if max is reached, if not add the same.
		 * 
		 * 
		 * 
		 */

		// This condition indicates that there is no max limit for this section.

		BigDecimal result = eligibleAmount.add(deduction.getEligibleDeduction());

		if (maxAmount.compareTo(new BigDecimal("0")) == 0) {
			eligibleAmount = result;

		} else {

			if (result.compareTo(maxAmount) >= 0) {
				eligibleAmount = maxAmount;
			} else {
				eligibleAmount = result;
			}
		}

	}

}
