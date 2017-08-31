/**
 * 
 */
package com.work.itpa.rules;

import java.math.BigDecimal;

/**
 * @author Developer
 *
 */
public class Expense {

	/**
	 * Amount spent Per Year.
	 * 
	 */
	public BigDecimal amount;
	
	/**
	 * Expense Type
	 */
	
	public String type;
	
	/**
	 * Other details about an expense.
	 */
	
	public String details;
	
	/**
	 * For whom this spend is done.
	 */
	
	public String relationShipCode;
	
	public Expense(){}
	

	/**
	 * @param amount
	 * @param type
	 * @param details
	 */
	public Expense(BigDecimal amount, String type, String details) {
		super();
		this.amount = amount;
		this.type = type;
		this.details = details;
	}

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
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}


	/**
	 * @return the relationShipCode
	 */
	public String getRelationShipCode() {
		return relationShipCode;
	}


	/**
	 * @param relationShipCode the relationShipCode to set
	 */
	public void setRelationShipCode(String relationShipCode) {
		this.relationShipCode = relationShipCode;
	}
	
	
	
	
}
