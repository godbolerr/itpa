/**
 * 
 */
package com.work.itpa.rules;

/**
 * @author 115750
 *
 */
public class PropertyDetails {
	
	
	String name;
	
	
	String city;
	
	/**
	 * Owned, Rented
	 */
	String status;

	public PropertyDetails(){}
	/**
	 * @param name
	 * @param city
	 * @param status
	 */
	public PropertyDetails(String name, String city, String status) {
		super();
		this.name = name;
		this.city = city;
		this.status = status;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
