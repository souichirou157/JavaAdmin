package com.example.demo.model.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.DSN.Resource;

public final class TableOption {
	
	public static void SerialNumberReset(String tablename) {
		
		PreparedStatement ps = null; 
		ResultSet rs = null;
		
		
		try (Connection    con =  DriverManager.getConnection(Resource.getUrl(),
				Resource.getUser(),Resource.getPassword())){

			String selectstmt = "SELECT *  FROM   %s";
			ps = con.prepareStatement(String.format(selectstmt,tablename));
//			
			rs = ps.executeQuery();
			java.sql.ResultSetMetaData rsmetadata  = rs.getMetaData();
			
			String set = "set @n:=0;";
			ps = con.prepareStatement(String.format(set));
			ps.executeUpdate();
			
			String updateId = " update  %s set %s=@n:=@n+1 ORDER BY %s ; ";
			ps = con.prepareStatement(String.format(updateId,tablename,rsmetadata.getColumnName(1),rsmetadata.getColumnName(1)));
			ps.executeUpdate();
			
			String reset = "alter table %s auto_increment = LAST_INSERT_ID();";
			
			ps = con.prepareStatement(String.format(reset,tablename));
			ps.executeQuery();
			
		
		}catch(SQLException sqe) {
			
			sqe.printStackTrace();
		}	

//
		
////
//			String resetId = """
//					
//					alter table  %s auto_increment = @n;
//					
//					""";
//		
//			ps = con.prepareStatement(String.format(resetId,Session.getTablename()));
//			ps.executeUpdate();
//
////		
	}
}
