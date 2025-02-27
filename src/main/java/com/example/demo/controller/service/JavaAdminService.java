package com.example.demo.controller.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.DSN.Resource;
import com.example.demo.container.Components;
import com.example.demo.container.GetOperationData;
import com.example.demo.container.export.FileFormat;
import com.example.demo.model.app.Views;

import jakarta.servlet.http.HttpSession;

@Service
public class JavaAdminService {
	
	@Autowired
	private Components  component;
	
	
	static class UseQuery{
		
		private static final String statement = "SELECT User,Password FROM mysql.user WHERE  User = ? && Password = PASSWORD(?) ;";
	}

	public List<String> getDatabaseList(HttpSession  session) {
		
		@SuppressWarnings("uncheked")
		ArrayList<String> db = (ArrayList<String>)session.getAttribute("databases");
		db = component.showDatabase();
		
		return db;
		
	}
	
	
	public void ExportDataFormat(String tablename,FileFormat extension,String encode) {
		component.exportDataFormat(tablename,extension, encode);
	}
	
	public List<String> getTableList(HttpSession  session,String dbname) {
		
		
		@SuppressWarnings("uncheked")
		List<String> tables = (ArrayList<String>) session.getAttribute("table");
		session.setAttribute("table",tables);
		tables = component.showtable(dbname);
		
		
		return tables;
		
	}

	public List<Views> addTableList(@ModelAttribute Views  t,Model model) 
	{
		
		GetOperationData mapper = new GetOperationData();
			
		t.setTable(new ArrayList<String>());
		
		for(int i =0; i < mapper.gettable().size(); i++) t.getTable().add(mapper.gettable(i));
		
		@SuppressWarnings("uncheked")
		List <Views>  tablelist = (List <Views> ) model.getAttribute("tableList");
		tablelist = new ArrayList<Views>();
		tablelist.add(t);
		tablelist.forEach(System.out::println);
			
			 return   tablelist;
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
	
	public  void SerialNumberReset(String tablename) {
		
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

	}
	
	
	public StringBuilder findAll(String tablename) 
	{
		 
		
		try (Connection    con =  DriverManager.getConnection(Resource.getUrl(),
					Resource.getUser(),Resource.getPassword()))
		{
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+tablename+" LIMIT 10 OFFSET 0;");
			ResultSet  rs =  ps.executeQuery();
	        java.sql.ResultSetMetaData rsmetadata  = rs.getMetaData();
	          
	    
	        StringBuilder result =  new StringBuilder();
	    	
	     result.append("<table Border=3 style=\"  margin:0 auto; text-align:center; colspan:8; color:aqua; background-color: rgb(0, 128, 192);  \" >");
	     result.append("<tr>").
	      append("<td>選択欄</td>");
	          for(int j=1; j <=rsmetadata.getColumnCount();j++) result.append("<td>").append(rsmetadata.getColumnName(j)).append("</td>");
	     result.append("</tr>");    
	          
	          
	          while (rs.next()) {
	       	    
	       	   result.append("<tr>");//name=\"value\"
	       	   result.append("<td >").append("<input type=\"checkbox\" class=\"userid\" name=\"Id\" th:with=\"ID=${").append(rs.getString(1)).append("}\"")
	       	.append("value="+rs.getString(1)).append(" >").append("</td>");
	       	    for(int i =1; i <= rsmetadata.getColumnCount();i++) {
	       	    	 result.append("<td>").append(rs.getString(rsmetadata.getColumnName(i))).append("</td>");	
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


	
	public StringBuilder getId(String tablename,int ...Id) {
  	    
		 StringBuilder  result = new StringBuilder();
  	     
  	     for(int id:Id) {
  	    	 
  	    	 result.append("<input type = \"hidden\" name=\"deleteuser\" ")
  	    	 .append("th:value="+id+" ")
  	    	 .append("  value="+id).append(">");
  	     }
  	     
  	     return result;
    	
	}
	
	


	
	
}
