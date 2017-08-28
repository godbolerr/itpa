/**
 * 
 */
package com.work.itpa.rules;

import java.math.BigDecimal;

/**
 * @author Developer
 *
 */
public class Donation {

	/**
	 * Amount donated.
	 * 
	 */
	public BigDecimal amount;
	
	/**
	 * Type of institution to which amount is donated
	 */
	
	public String type;
	
	/**
	 * Other details about an institution.
	 */
	
	public String details;
	
	public Donation(){}
	

	/**
	 * @param amount
	 * @param type
	 * @param details
	 */
	public Donation(BigDecimal amount, String type, String details) {
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
	
	
	
	
}
