package com.example.demo.model.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.example.demo.controller.db.DSN.Resource;
import com.example.demo.controller.service.InvalidController;


public final class Login implements InvalidController{
	

	public Login() {}
	
	static class UseQuery{
		
		private static final String statement = "SELECT User,Password FROM mysql.user WHERE  User = ? && Password = PASSWORD(?) ;";
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


/*PreparedStatement ps = null; ResultSet rs = null; try ( Connection con = DriverManager.getConnection(getUrl(),getUser(),getPassword()); ){ ps = con.prepareStatement("SELECT User,Password FROM mysql.user WHERE User = ? && Password = PASSWORD(?) ;"); ps.setString(1,username); ps.setString(2, password); rs = ps.executeQuery(); java.sql.ResultSetMetaData metadata = rs.getMetaData(); while (rs.next()) { data = rs.getString("User"); System.out.println( rs.getString("Password")); i++; } return Objects.isNull(data); } catch (SQLException e) { e.printStackTrace(); return false; } mysql　の mysql.userテーブルを検索していますが存在しないユーザデータを渡すしてもtrueを返します。　実装に問題ありますか*/