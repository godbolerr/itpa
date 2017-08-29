package com.work.itpa.rules;

import java.math.BigDecimal;

public class FiUtil {
	
	public static boolean isAvailable(String x, String type){
		
		boolean result = false;
		
		if ( type.equalsIgnoreCase("80c")){
			
			if ( x.equalsIgnoreCase("PPF")){
				result = true;
			}
		}
		
		return result;
	}
	

	
	public static BigDecimal lessOf(BigDecimal value1,BigDecimal value2){
		
		if ( value1.compareTo(value2) == -1 ) {
			return value1;
		}
		return value2;
	}

	
	public static BigDecimal maxOf(BigDecimal value1,BigDecimal value2){
		
		if ( value1.compareTo(value2) == 1 ) {
			return value1;
		}
		return value2;
	}

	public static BigDecimal maxOf(BigDecimal value1,double anotherValue){
		
		BigDecimal value2 = BigDecimal.valueOf(anotherValue);
		
		if ( value1.compareTo(value2) == 1 ) {
			return value1;
		}
		return value2;
	}


	public static BigDecimal maxOf(double firstValue,double anotherValue){
		
		BigDecimal value1 = BigDecimal.valueOf(firstValue);
		BigDecimal value2 = BigDecimal.valueOf(anotherValue);
		
		if ( value1.compareTo(value2) == 1 ) {
			return value1;
		}
		return value2;
	}

	
	
}
