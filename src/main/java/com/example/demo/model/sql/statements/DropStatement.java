package com.example.demo.model.sql.statements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.demo.DSN.Resource;
import com.example.demo.model.sql.CallStatement;
import com.example.demo.model.sql.perse.Perser;

public abstract class DropStatement 

implements CallStatement{

	public static CallStatement drop() 
	{
		
		return new CallStatement() 
		
		{
			
			@Override
			public StringBuilder query(String statement) 
					throws SQLException
			{
				
				try(Connection con = DriverManager.getConnection(Resource.getUrl(),
						Resource.getUser(),Resource.getPassword());) 
				{
					 
					con.setAutoCommit(false);
					
					
					
					Statement stmt = con.createStatement();
					 
					 
					 
					statement = Perser.Molding(statement);
				
					int rowsAffected = stmt.executeUpdate(statement); 
					System.out.println("Rows affected: " + rowsAffected);
					
					System.out.println(statement);
					con.commit();
					
					
					return null;
					
					
				}catch(SQLException se) {
					
					se.printStackTrace();
					
					return null;
				}finally {
					Resource.DsnInitialization("mysql");
					
				}
			
		}
		
		};
}
}
