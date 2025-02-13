package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;

public  abstract class Session {
	
	static String username ;
	static String authority;
	static String useTable;
	@Autowired
	static HttpSession session;
	
	public static void setTablename(String tablename) {
		
		useTable = tablename;
	}
	
	public static String getTablename() {
		
		return useTable;
	}
	
	public static void setName(String name) {
		
		username = name;
	}
	
	public static String getuserName() {
		
		return username;
	}

	
	public static void setAuthority(String authority){
		
		Session.authority =  authority;
	}
	
	public static String getAuthority(){
		
		return Session.authority;
	}
}
