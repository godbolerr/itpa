package com.work.itpa.rules;

import java.math.BigDecimal;

public class Deduction {

	/**
	 * Amount eligible for max deduction
	 */
	public BigDecimal amount;

	/**
	 * Type of section for which limit is utilized
	 */
	public String type;

	/**
	 * Section under which it is allowed.
	 */
	
	/**
	 * Mode indicates if deduction is unique in the collection or it has to be added.
	 */
	public String mode = FiConstants.DEDUCTION_UNIQUE;
	
	
	
	public String section;
	

	public String notes;

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
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section
	 *            the section to set
	 */
	public void setSection(String section) {
		this.section = section;
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
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @param amount
	 * @param section
	 * @param notes
	 */
	public Deduction(BigDecimal amount, String type, String mode, String section, String notes) {
		super();
		this.amount = amount;
		this.type = type;
		this.mode = mode;
		this.section = section;
		this.notes = notes;
		
	}
	
	/**
	 * Called when type is unique
	 * 
	 * @param amount
	 * @param type
	 * @param section
	 * @param notes
	 */
	public Deduction(BigDecimal amount, String type,  String section, String notes) {
		super();
		this.amount = amount;
		this.type = type;
		this.section = section;
		this.notes = notes;
		
	}

	public Deduction() {
	}

}
