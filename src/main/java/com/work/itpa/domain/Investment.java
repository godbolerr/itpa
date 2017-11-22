/**
 * 
 */
package com.work.itpa.domain;

import java.math.BigDecimal;

/**
 * Investments made in various schemes in the year.
 * 
 * @author 115750
 *
 */
public class Investment {

	/**
	 * Amount invested per year.
	 * 
	 */
	public BigDecimal amount;
	
	/**
	 * Type of institution to which amount is invested
	 */
	
	public String type;
	
	/**
	 * Other details about an investment.
	 */
	
	public String details;
	
	public Investment(){}
	

	/**
	 * @param amount
	 * @param type
	 * @param details
	 */
	public Investment(BigDecimal amount, String type, String details) {
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
