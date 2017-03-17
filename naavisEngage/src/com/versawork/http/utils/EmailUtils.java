package com.versawork.http.utils;


public class EmailUtils {
	
	
	public static String maskedEmail(String email,String name) {
		
		String masked;
		if(name.length()!=0)
		{
		 masked = email.replaceFirst(name, "**");
		}
		else 
		{
			char firstChar = email.charAt(0);
			String temp = firstChar+"**";
			email = email.substring(1);
			masked = temp+email;
			
		}
		
		
		return masked;
		
		
	}

}
