/**
 * 
 */
package com.work.itpa.domain;

import java.math.BigDecimal;

/**
 * @author Developer
 *
 */
public class Income {

	/**
	 * Amount received.
	 */
	public BigDecimal amount;

	/**
	 * Type of income.
	 * 
	 */
	public String type;

	/**
	 * Income source
	 */

	public String source;

	/**
	 * Other details associated with income.
	 * 
	 */
	public String details;

	public Income() {
	}

	/**
	 * @param amount
	 * @param type
	 * @param details
	 */
	public Income(BigDecimal amount, String type, String source, String details) {
		super();
		this.amount = amount;
		this.type = type;
		this.source = source;
		this.details = details;
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
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

}
