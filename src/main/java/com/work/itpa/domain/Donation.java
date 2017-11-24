/**
 * 
 */
package com.work.itpa.domain;

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
	
	/**
	 * Short scheme code if the scheme name is very large.
	 * 
	 */
	public String schemeCode;
	
	public enum Type {
		SCIENTIFIC, POLITICAL, OTHER
	};
	
	
	public Type type;
	
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
	public Donation(BigDecimal amount, Type type, String details) {
		super();
		this.amount = amount;
		this.type = type;
		this.details = details;
	}
	
	public Donation(BigDecimal amount, Type type,String schemeCode, String details) {
		super();
		this.amount = amount;
		this.type = type;
		this.schemeCode  = schemeCode;
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
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
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
	 * @return the schemeCode
	 */
	public String getSchemeCode() {
		return schemeCode;
	}


	/**
	 * @param schemeCode the schemeCode to set
	 */
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	
	
	
	
}
