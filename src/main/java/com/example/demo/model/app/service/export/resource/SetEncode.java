package com.example.demo.model.app.service.export.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public sealed interface SetEncode 

permits CSVResource,JSONResource,SQLResource 
{
	
	final String query = "SET NAMES %s";
	
	public default   ResultSet SetEncodeName(PreparedStatement ps,
	
			Connection con,String encode)
			
			throws SQLException 
	{

        ps = con.prepareStatement(String.format(query,encode.toLowerCase()));
        ps.execute();
        
         return ps.executeQuery("SHOW VARIABLES LIKE 'character_set_client';");
        
	}
}
