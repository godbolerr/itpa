package com.work.itpa.domain;

import java.math.BigDecimal;

/**
 * 
 * @author Developer
 *
 */
public class SummaryDeduction {

	public BigDecimal eligibleAmount;

	public String sectionType;

	public String notes;
	
	

	/**
	 * @param eligibleAmount
	 * @param sectionType
	 * @param notes
	 */
	public SummaryDeduction(BigDecimal eligibleAmount, String sectionType, String notes) {
		super();
		this.eligibleAmount = eligibleAmount;
		this.sectionType = sectionType;
		this.notes = notes;
	}

	/**
	 * @return the eligibleAmount
	 */
	public BigDecimal getEligibleAmount() {
		return eligibleAmount;
	}

	/**
	 * @param eligibleAmount the eligibleAmount to set
	 */
	public void setEligibleAmount(BigDecimal eligibleAmount) {
		this.eligibleAmount = eligibleAmount;
	}

	/**
	 * @return the sectionType
	 */
	public String getSectionType() {
		return sectionType;
	}

	/**
	 * @param sectionType the sectionType to set
	 */
	public void setSectionType(String sectionType) {
		this.sectionType = sectionType;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
}
