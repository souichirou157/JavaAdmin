package com.example.demo.model.sql.statements;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract class PassStatementToTemplate {
	

	
	 static  StringBuilder setTable(ResultSet rs) 
			 
			 throws SQLException
	 
	 {

		StringBuilder template = new StringBuilder();
		//ここも分割
		java.sql.ResultSetMetaData rsmetadata  = rs.getMetaData();
		int colamount = rsmetadata.getColumnCount();
	
	
		template.append("<table  style=\"background:royalblue;\"   >");
		template.append("<tr >");
	    
		int index =1;
	    for(int j=1;j<=colamount;j++) {
	    	template.append("<td style= background-color:rgb(0, 128, 128);>").append(rsmetadata.getColumnName(index++)).append("</td>");
        }
	    template.append("</tr>");
       
          
		while (rs.next()) {
			
			template.append("<tr style= color:greenyellow;>");
			for(int j=1;j<=colamount;j++) {
				template.append("<td>").append(rs.getString(j)).append("</td>");			       
			}
			template.append("</tr>");
		}
		template.append("</table>");
		
		
		return template;
	}
}
