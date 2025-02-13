
package com.example.demo.model.app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.DSN.Resource;
import com.example.demo.model.sql.Metadata.GetOperationData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public final class Views{
	
	private String admin_staff;
	private String student_information;
	private String alert_sender;
	private String attended_admin;
	private String contact_address ;
	private String subject_score;
	private GetOperationData mapper;
	private ArrayList<String>checkBoxId;
	private ArrayList<String> table;
	

	public List<Views> addTableList(@ModelAttribute Views  t,Model model) 
	{
		
		mapper = new GetOperationData();
			
		table = new ArrayList<String>();
		for(int i =0; i < mapper.gettable().size(); i++) t.table.add(mapper.gettable(i));
			  
			@SuppressWarnings("uncheked")
			List <Views>  tablelist = (List <Views> ) model.getAttribute("tableList");
			  tablelist = new ArrayList<Views>();
			  tablelist.add(t);
			  
			 tablelist.forEach(System.out::println);
			
			 return   tablelist;
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

		checkBoxId = new ArrayList<String>();
  	    
		 StringBuilder  result = new StringBuilder();
  	     
  	     for(int id:Id) {
  	    	 
  	    	 result.append("<input type = \"hidden\" name=\"deleteuser\" ")
  	    	 .append("th:value="+id+" ")
  	    	 .append("  value="+id).append(">");
  	     }
  	     
  	     return result;
    	
	}
	
	public ArrayList<String> getcheckBoxId() {
		
		return this.checkBoxId;
	}

	
}
