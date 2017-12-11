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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Income other = (Income) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (source != other.source)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Income [amount=" + amount + ", type=" + type + ", source=" + source + ", details=" + details + "]";
	}

}
