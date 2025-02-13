package com.example.demo.model.app.service.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.controller.db.DSN.Resource;
import com.example.demo.model.app.service.export.resource.SQLResource;
 
final   non-sealed class  PerSeDataSQL 
extends SQLResource 
   implements  OutputData
 {

	protected PerSeDataSQL (){}
	
	static class  Parse
	{
		private final static String  statement = "SHOW CREATE TABLE %s";
		private static String  ParseData(Connection con ,PreparedStatement ps,ResultSet rs,String entity) 
		       throws SQLException 
		{
			
		   	  
	   	  	ps = con.prepareStatement(String.format(statement ,entity));
	   	  	rs = ps.executeQuery();
		  
	   	  	
   	    	java.sql.ResultSetMetaData rsmetadta  = rs.getMetaData();
   	    	int colamount = rsmetadta.getColumnCount();
   	    	StringBuilder sb = new StringBuilder();
		    
   	    	
		        while(rs.next())
		        {
		        	
		        	for(int i=2; i <=colamount;i++) 
		        	{
		        		sb.append(rs.getString(i));
		        		System.out.println(rsmetadta.getColumnName(i));
		        	}
		        	
		        }   
		      
		        return sb.toString();
		    
		}
	}
	
	
	@Override
	public   void  exportData(String entity,String encode) 
	{
			
	   	     PreparedStatement ps = null;
	   	     ResultSet rs = null;
	   	     
	   	     java.io.File f = super.create();

	   
	   	    try (Connection con = DriverManager.getConnection(Resource.getUrl(),
					Resource.getUser(),Resource.getPassword()))
	   	   {
	   	    	
	      
	         rs = super.SetEncodeName(ps, con, encode);
	       
	         if (rs.next()) System.out.println("Character Set Client: " + rs.getString("Value"));
	        
	        
	   	 	ps = con.prepareStatement(String.format(PUBLISHQUERY,entity,f,encode.toLowerCase()));
	   	 	ps.executeQuery();
	   	 	
	   	 	try (FileWriter fw = new FileWriter(new File("C:/Users/cl05/Desktop/"+f));)
	   	 	{
	   		 
			  
		        fw.write(Parse.ParseData(con ,ps,rs,entity));      
		        
	   	    }catch(IOException ie) {
	   	    	
	   	    	ie.printStackTrace();
	   	    }
	   	 	
	   	   }catch(SQLException e){
				e.printStackTrace();
			}	
	}
	
}
