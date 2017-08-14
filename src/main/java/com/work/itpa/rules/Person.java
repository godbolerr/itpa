/**
 * 
 */
package com.work.itpa.rules;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 115750
 *
 */
public class Person implements Serializable {
	
	/**
	 * Uniuqe identifier for a person.
	 */
	String id = "";
	
	
	String name;
	
	/**
	 * Date of birth for a person
	 */

	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	Date dateOfBirth;
	
	
	/**
	 * Age of the person. 
	 * This is derived field.
	 */
	int age;
	
	/**
	 * Gender of a person. [ Male/Female ]
	 * 
	 * TODO Make this enum
	 * 
	 */
	String gender;
	
	/**
	 * Relationship of the person with the container entity
	 * 
	 * SELF,SON,DAUGHTER,FATERH,MOTHER,BROTHER,SISTER,OTHER
	 * 
	 */
	
	String relationShipCode;
	
	/**
	 * Percent of disability 
	 * 
	 */
	int disabilityPercent;
	
	
	/**
	 * Disease identified
	 * 
	 */
	String disease;
	
	/**
	 * Derived field. true if this person has disability greater than 0
	 */
	boolean hasDisability;
	
	/**
	 * True if this person has any diseases.
	 */
	boolean hasDisease;
	
	
	/**
	 * Represents the validity of the data provided.
	 */
	boolean valid;
	
	
	public Person(){}

	/**
	 * @param dateOfBirth
	 * @param gender
	 * @param relationShipCode
	 * @param disabilityPercent
	 * @param disease
	 */
	public Person(String name, Date dateOfBirth, String gender, String relationShipCode, int disabilityPercent, String disease) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.relationShipCode = relationShipCode;
		this.disabilityPercent = disabilityPercent;
		this.disease = disease;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		
//		if ( age == 0 && dateOfBirth != null ){
//			Date startDate = Date.of(2017, Month.AUGUST, 01);
//			long numberOfYears = ChronoUnit.YEARS.between(dateOfBirth,startDate);
//			age = new Long(numberOfYears).intValue();
//		}
		return age ;
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
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
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
	 * @return the disabilityPercent
	 */
	public int getDisabilityPercent() {
		return disabilityPercent;
	}

	/**
	 * @param disabilityPercent the disabilityPercent to set
	 */
	public void setDisabilityPercent(int disabilityPercent) {
		this.disabilityPercent = disabilityPercent;
	}

	/**
	 * @return the disease
	 */
	public String getDisease() {
		return disease;
	}

	/**
	 * @param disease the disease to set
	 */
	public void setDisease(String disease) {
		this.disease = disease;
	}

	/**
	 * @return the hasDisability
	 */
	public boolean isHasDisability() {
		return hasDisability;
	}

	/**
	 * @param hasDisability the hasDisability to set
	 */
	public void setHasDisability(boolean hasDisability) {
		this.hasDisability = hasDisability;
	}

	/**
	 * @return the hasDisease
	 */
	public boolean isHasDisease() {
		return hasDisease;
	}

	/**
	 * @param hasDisease the hasDisease to set
	 */
	public void setHasDisease(boolean hasDisease) {
		this.hasDisease = hasDisease;
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
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + disabilityPercent;
		result = prime * result + ((disease == null) ? 0 : disease.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((relationShipCode == null) ? 0 : relationShipCode.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		if (age != other.age) {
			return false;
		}
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null) {
				return false;
			}
		} else if (!dateOfBirth.equals(other.dateOfBirth)) {
			return false;
		}
		if (disabilityPercent != other.disabilityPercent) {
			return false;
		}
		if (disease == null) {
			if (other.disease != null) {
				return false;
			}
		} else if (!disease.equals(other.disease)) {
			return false;
		}
		if (gender == null) {
			if (other.gender != null) {
				return false;
			}
		} else if (!gender.equals(other.gender)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (relationShipCode == null) {
			if (other.relationShipCode != null) {
				return false;
			}
		} else if (!relationShipCode.equals(other.relationShipCode)) {
			return false;
		}
		return true;
	}
	
	
	

}
