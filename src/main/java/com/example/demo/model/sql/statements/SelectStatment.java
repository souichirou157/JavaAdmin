package com.example.demo.model.sql.statements;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.demo.DSN.Resource;
import com.example.demo.model.sql.CallStatement;
import com.example.demo.model.sql.perse.Perser;
public abstract class SelectStatment 

implements CallStatement 

{
	
	
	public static CallStatement select() 
	
	{
		
		return new CallStatement() 
		
		{
			
			@Override
			public  StringBuilder query(String statement)  
					
					throws SQLException 
			{
				
				
				 				
				try(Connection con = DriverManager.getConnection(Resource.getUrl(),
						Resource.getUser(),Resource.getPassword());) {
					 	
					Statement stmt = con.createStatement();
					statement = Perser.Molding(statement);	 
								
					ResultSet rs =  stmt.executeQuery(statement);

					
					return PassStatementToTemplate.setTable(rs);
				
				}catch(SQLException se) {
					se.printStackTrace();
					
					return null;
				}
	}

			
		};
		
	}
	
	
	
	
}	

