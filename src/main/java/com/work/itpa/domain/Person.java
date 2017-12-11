/**
 * 
 */
package com.work.itpa.domain;

/**
 * Details about an individual
 * 
 * @author Developer
 *
 */
public class Person  {
	
	
	public enum Gender {
		MALE, FEMALE, OTHER, NA
	};
	
	/**
	 * unique id of the person
	 */
	public String pid ;
	
	/**
	 * First Name
	 */
	public String fName;
	
	/**
	 * Last Name
	 */
	public String lName;
		
	/**
	 * Age of the person.
	 * Ideally this should be derived from date of birth.
	 * As of now there is no requirement.
	 */
	public int age;
	
	/**
	 * Gender of the person
	 */
	public Gender gender;
	
	/**
	 * Relationship of the person with respect to Assessee
	 */
	String relationShipCode;
	
	/**
	 * Relation type - used by UI only
	 */
	String relationType;


	/**
	 * Date of birth of the person. - Not mandatory
	 */
	public String dateOfBirth;
	
	
	/**
	 * @return the id
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param id the id to set
	 */
	public void setpId(String pid) {
		this.pid = pid;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
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

	/**
	 * @return the relationType
	 */
	public String getRelationType() {
		return relationType;
	}

	/**
	 * @param relationType the relationType to set
	 */
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
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
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [pid=" + pid + ", fName=" + fName + ", lName=" + lName + ", age=" + age + ", gender=" + gender
				+ ", relationShipCode=" + relationShipCode + ", relationType=" + relationType + ", dateOfBirth=" + dateOfBirth + "]";
	}
		
	
}
