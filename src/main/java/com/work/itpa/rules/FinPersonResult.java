package com.work.itpa.rules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FinPersonResult {

	boolean status;

	List<String> messages = new ArrayList<String>();
	
	List<Deduction> deductions = new ArrayList<Deduction>();
	
	//TODO Change this variable name
	
	List<Deduction> plannedDeductions = new ArrayList<Deduction>();

	public Deduction getDeductionForType(String type){
		
		Deduction deduction = null;
		
		boolean found = false;
		
		
		for (Iterator iterator = plannedDeductions.iterator(); iterator.hasNext();) {
			Deduction d1 = (Deduction) iterator.next();
			if ( type.equals(d1.getSection()))
				found = true; 
				
		}
		
		if ( found == false ){
			deduction = new Deduction();
			plannedDeductions.add(deduction);
		}
		
		return deduction;
		
		
	}
	
	
	
	
	
	
	public void addMessage(String message) {
		messages.add(message);
	}

	public void addDeduction(Deduction deduction) {
		deductions.add(deduction);
	}

	
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	
	

	/**
	 * @return the deductions
	 */
	public List<Deduction> getDeductions() {
		return deductions;
	}

	/**
	 * @param deductions the deductions to set
	 */
	public void setDeductions(List<Deduction> deductions) {
		this.deductions = deductions;
	}

	/**
	 * @return the plannedDeductions
	 */
	public List<Deduction> getPlannedDeductions() {
		return plannedDeductions;
	}

	/**
	 * @param plannedDeductions the plannedDeductions to set
	 */
	public void setPlannedDeductions(List<Deduction> plannedDeductions) {
		this.plannedDeductions = plannedDeductions;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FinPersonResult [status=" + status + ", messages=" + messages + "]";
	}

}
