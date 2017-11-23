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

	public enum Source {
		NA, BOOK, PATENT
	};

	public enum Type {

		NA, INTEREST, GROSS_TOTAL, HRA, EPF, PF_EMPLOYEE, PF_EMPLOYEER, BASIC_SALARY, DEARNESS_ALLOWANCE, PS_EMPLOYEE, PS_EMPLOYEER, RENTAL_RECEPITS, ROYALTY

	};

	/**
	 * Amount received.
	 */
	public BigDecimal amount;

	/**
	 * Type of income.
	 * 
	 */
	public Type type;

	/**
	 * Income source
	 */

	public Source source = Source.NA;

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
	public Income(BigDecimal amount, Type type, Source source, String details) {
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
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
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
	 * @param details
	 *            the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return the source
	 */
	public Source getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(Source source) {
		this.source = source;
	}

}
