package com.example.demo.container.export.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface  SetEncode 

 
{	
	public static   ResultSet SetEncodeName(PreparedStatement ps,
	
			Connection con,String encode)
			
			throws SQLException 
	{

        ps = con.prepareStatement(String.format("SET NAMES %s",encode.toLowerCase()));
        ps.execute();
        
         return ps.executeQuery("SHOW VARIABLES LIKE 'character_set_client';");
        
	}
}
