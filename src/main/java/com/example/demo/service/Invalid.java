package com.example.demo.service;

public interface Invalid {
	
	
	public static  boolean valLengthCheck(String value) {
				
		 return !value.matches("^(?=.*?[a-zA-Z])(?=.*?[0-9])[0-9a-zA-Z]{8,}$")  ;
		
	}

	public static  boolean emptyValCheck(String value) {
		
		return value.isEmpty();
	}
}
