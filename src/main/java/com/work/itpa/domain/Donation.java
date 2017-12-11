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


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((schemeCode == null) ? 0 : schemeCode.hashCode());
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
		Donation other = (Donation) obj;
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
		if (schemeCode == null) {
			if (other.schemeCode != null)
				return false;
		} else if (!schemeCode.equals(other.schemeCode))
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
		return "Donation [amount=" + amount + ", schemeCode=" + schemeCode + ", type=" + type + ", details=" + details
				+ "]";
	}
	
	
	
	
}
