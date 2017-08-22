package com.work.itpa.rules;

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

}
