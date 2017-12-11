/**
 * 
 */
package com.work.itpa.domain;

/**
 * Property owned by Assessee
 * 
 * @author Developer
 *
 */
public class StatusFlag {
	
	Boolean rentedAccomodation;

	/**
	 * @return the rentedAccomodation
	 */
	public Boolean getRentedAccomodation() {
		return rentedAccomodation;
	}

	/**
	 * @param rentedAccomodation the rentedAccomodation to set
	 */
	public void setRentedAccomodation(Boolean rentedAccomodation) {
		this.rentedAccomodation = rentedAccomodation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StatusFlag [rentedAccomodation=" + rentedAccomodation + "]";
	}
	
	
}
