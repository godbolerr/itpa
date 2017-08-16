package com.work.itpa.rules;

import java.math.BigDecimal;

public class Deduction {
	
	/**
	 * Amount eligible for max deduction
	 */
	BigDecimal amount;
	
	/**
	 * Type of section for which limit is utilized
	 */
	String type;
	
	/**
	 * Section under which it is allowed.
	 */
	String section ;
	
	
	String notes;


	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
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
	 * @param section the section to set
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
	 * @param notes the notes to set
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
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @param amount
	 * @param section
	 * @param notes
	 */
	public Deduction(BigDecimal amount, String type, String section,  String notes) {
		super();
		this.amount = amount;
		this.section = section;
		this.notes = notes;
		this.type = type;
	}
	
	
	
	
	

}
