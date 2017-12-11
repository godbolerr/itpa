package com.work.itpa.domain;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((deductionType == null) ? 0 : deductionType.hashCode());
		result = prime * result + ((maxDeduction == null) ? 0 : maxDeduction.hashCode());
		result = prime * result + ((sectionType == null) ? 0 : sectionType.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Deduction other = (Deduction) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (deductionType == null) {
			if (other.deductionType != null)
				return false;
		} else if (!deductionType.equals(other.deductionType))
			return false;
		if (maxDeduction == null) {
			if (other.maxDeduction != null)
				return false;
		} else if (!maxDeduction.equals(other.maxDeduction))
			return false;
		if (sectionType == null) {
			if (other.sectionType != null)
				return false;
		} else if (!sectionType.equals(other.sectionType))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Deduction [amount=" + amount + ", deductionType=" + deductionType + ", sectionType=" + sectionType
				+ ", maxDeduction=" + maxDeduction + ", eligibleDeduction=" + eligibleDeduction + ", notes=" + notes
				+ "]";
	}

}
