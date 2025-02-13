package com.example.demo.controller.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demo.DSN.Resource;
import com.example.demo.model.sql.Metadata.GetOperationData;

import jakarta.servlet.http.HttpSession;

@Service
public class JavaAdminService {
	
	static class UseQuery{
		
		private static final String statement = "SELECT User,Password FROM mysql.user WHERE  User = ? && Password = PASSWORD(?) ;";
	}

	public ArrayList<String> getDatabaseList(HttpSession  session) {
		
		@SuppressWarnings("uncheked")
		ArrayList<String> db = (ArrayList<String>)session.getAttribute("databases");
		db = GetOperationData.showDatabase();
		
		return db;
		
	}
	

	public  boolean findUser(String username ,String password) 
			
			throws IllegalArgumentException
	{
		int i =0;
		PreparedStatement ps = null;
		ResultSet rs = null;
			
		try (Connection  con = DriverManager.getConnection(
				Resource.getUrl(),
				Resource.getUser(),Resource.getPassword()
				)){
			
			ps = con.prepareStatement(UseQuery.statement);
			
			
			 ps.setString(1,username);
			 ps.setString(2, password);
			
			 rs =  ps.executeQuery();
			 
			 
	
				String data = null;
				while (rs.next()) {
			
					data = rs.getString("User");
					System.out.println(	rs.getString("Password"));
					
					i++;
				}
			
			
				
				return Objects.nonNull(data);
			
		} catch (SQLException e) {
				e.printStackTrace();
				
				return false;
				
		}
}
}
