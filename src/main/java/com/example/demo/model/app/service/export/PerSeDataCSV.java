package com.example.demo.model.app.service.export;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.controller.db.DSN.Resource;
import com.example.demo.model.app.service.export.resource.CSVResource;


final   non-sealed  class PerSeDataCSV  

extends CSVResource  
     implements  OutputData
{
	
	protected PerSeDataCSV () {}
	
	@Override
	public   void  exportData(String entity,String encode)
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		java.io.File f = super.create();
		
		try (Connection con = DriverManager.getConnection(Resource.getUrl(),
				Resource.getUser(),Resource.getPassword())){
	   	    	
    
	   	    rs = super.SetEncodeName(ps, con, encode);
	   	    
	   	    if (rs.next()) System.out.println("Character Set Client: " + rs.getString("Value"));
	   	    ps = con.prepareStatement(String.format(PUBLISHQUERY,entity,f,encode.toLowerCase()));
	   	 	ps.executeQuery();
	 
		
	}catch(SQLException e){
				e.printStackTrace();
	}
		}
	

}

