
package com.example.demo.container.export;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.DSN.Resource;
import com.example.demo.container.export.resource.CreateFile;
import com.example.demo.container.export.resource.PublishQuery;
import com.example.demo.container.export.resource.SetEncode;


public  final class  PerSeDataCSV  

{
	
	public  final  void exportDataCSV(String entity,String encode)
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		java.io.File f = CreateFile.create(".CSV");
		
		try (Connection con = DriverManager.getConnection(Resource.getUrl(),
				Resource.getUser(),Resource.getPassword())){
	   	    	
    
	   	    rs = SetEncode.SetEncodeName(ps, con, encode);
	   	    
	   	    if (rs.next()) System.out.println("Character Set Client: " + rs.getString("Value"));
	   	    ps = con.prepareStatement(String.format(PublishQuery.getStaement(),entity,
	   	    		f,encode.toLowerCase()));
	   	 	ps.executeQuery();
	 
		
	}catch(SQLException e){
				e.printStackTrace();
	}
		}
	

}

