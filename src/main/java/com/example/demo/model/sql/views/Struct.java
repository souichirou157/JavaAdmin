package com.example.demo.model.sql.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.controller.db.DSN.Resource;

public abstract class Struct {
		
	static class UseQuery{
		
		private final static String statement = "DESC  %s  ;";
	}
	
	
	public static StringBuilder showStruct(String tablename) {
		
			
		try (Connection  con = DriverManager.getConnection(Resource.getUrl(),
				Resource.getUser(),Resource.getPassword()); ){
		
			StringBuilder result = new StringBuilder();
			PreparedStatement ps = con.prepareStatement(String.format(UseQuery.statement,tablename));
			ResultSet  rs =  ps.executeQuery();
			
			
			System.out.println(ps);
			 
			 
				java.sql.ResultSetMetaData metadata  = rs.getMetaData();
				int col = metadata.getColumnCount();

				
				 result.append("<table Border=3 style=\"  margin:0 auto; text-align:center; colspan:8; color:aqua; background-color: rgb(0, 128, 192);  \" >");
				 result.append("<tr>");
			     for(int j=1; j <=col;j++) result.append("<td>").append(metadata.getColumnName(j)).append("</td>");
			     result.append("</tr>");
			     
			     while (rs.next()) {
			    	 
			    	 result.append("</tr>");  	 
					for(int i=1; i <=col;i++) {
						result.append("<td>").append(rs.getString(i)).append("</td>");
					}
					result.append("</tr>");
				}
				 result.append("</table>");
			
				 return result;		
			
		} catch (SQLException e) {
				e.printStackTrace();
							
				return null;
		}
		
		
	}
}
