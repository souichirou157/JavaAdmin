package com.example.demo.model.sql.perse;
import com.example.demo.model.sql.util.Order;


public abstract class Lexer  {
	 
     @Order(order = 0)
	 static boolean StartIndexCheck(String statement) throws LexerException {
		 
    	 return statement.charAt(0)== '!';
	 }
	 
     @Order(order = 1)
	 static boolean UnnownColumnCheck(String statement) throws LexerException {
		 
		 return statement.indexOf("#") == -1 && statement.indexOf("*")==-1;
	 }
	
     @Order(order = 2)
	 static boolean InvalidSpecificationCheck(String statement) throws LexerException {
		 
		 return statement.indexOf("#") != -1 && statement.indexOf("*")!=-1;
	 }
	 
     @Order(order = 3)	 
	 static boolean EntityTable(String statement) throws LexerException {
		 
		 return statement.indexOf("FROM")== -1;
	 }
     @Order(order = 4)
	 static boolean LastIndexCheck(String statement) throws LexerException {
		
    	 return statement.lastIndexOf(";")== -1;
	 }
}
