
package com.example.demo.model.sql.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.DSN.Resource;
import com.example.demo.model.sql.Metadata.OperateTable;

import lombok.Getter;

@Getter

public final class InsertForm {
	
	public static String resultQuery ;
	
	static class  UseQuery{
		
		
	}
	
	
	public  static StringBuilder setForm(String tablename) {
		
		try(Connection    con =  DriverManager.getConnection(Resource.getUrl(),
				Resource.getUser(), Resource.getPassword() );){
		
	
			
		    java.sql.ResultSetMetaData rsmetadata  =  OperateTable.GetTemplatData(tablename);
			
		 	int colamount = rsmetadata.getColumnCount();
		 	
		 	StringBuilder	textfield  = new StringBuilder();		
		 	textfield.append("<table id = \"insertTable\"  border=\"1\"  style=\"margin:64px 0 64px 0px;\">");
		 	textfield.append("<tr><td>カラム</td><td>タイプ</td><td>関数</td><td>Null</td><td>値</td></tr>");
			 	for(int i =1;  i <=colamount;i++) {
			 		
			 		int nullable = rsmetadata.isNullable(i); 
			 		int datasize = rsmetadata.getPrecision(i);
			 		
	
			 		
			 		textfield.append("<tr>").append("<td style=\"width:25%;\">").append(rsmetadata.getColumnName(i)+"("+rsmetadata.getPrecision(i)+")")
			 		.append("</td>").append("<td class=\"DataType\"   style=\"width:25%;\">").append(rsmetadata.getColumnTypeName(i)).append("</td>")
			 		.append("<td style=\"width:25%;\"><select  size=\"1\"  name=\"method_field\"  ><option>NOW()</option></td>");
			 	
			 		if(nullable == rsmetadata.columnNullable) {
			 			textfield.append("<td style=\"width:25%;\"><input type=\"checkbox\" ></td>")
				 		.append("<td  style=\"width:25%;\"><input type=\"text\" name=\"value\" class=\"insertData\" maxlength="+datasize+"  >").append("</tr>");
			 		}
			 		if(nullable == rsmetadata.columnNoNulls){
			 			textfield.append("<td style=\"width:25%;\"><input type=\"checkbox\" checked></td>")
				 		.append("<td  style=\"width:25%;\"><input type=\"text\" name=\"value\" class=\"insertData\" maxlength="+datasize+"  >").append("</tr>");
			 		}
			 		
			 	}
			textfield.append("</table>");
			
		
			// isNullable(i))
			return  textfield;
			
		}catch(SQLException  sqe) {
			sqe.printStackTrace();
			return null;
		}
		
	
	}
	
	
public static void addData(String tablename ,Object ...value) {
		

		
		try(Connection    con =  DriverManager.getConnection(Resource.getUrl(), Resource.getUser(), Resource.getPassword() );){
			
			
			
			String firstst = "select * from   %s ;";
			
			PreparedStatement ps = con.prepareStatement(String.format(firstst,tablename));
			
			ResultSet rs  = ps.executeQuery();

		    java.sql.ResultSetMetaData rsmetadata  = rs.getMetaData();
			
		 	int colamount = rsmetadata.getColumnCount();

	
			

			StringBuilder stmt = new StringBuilder();
			
			for(int index = 1; index < value.length;index++) {
		
				if(value[index] instanceof  Number) continue;	
				value[index] = "\""+value[index]+"\"";
			
			}
			
			
			stmt.append("INSERT INTO  %s(");
			
			for(int i =1; i <=colamount;i++ ) {
				
				stmt.append(rsmetadata.getColumnName(i));
				if(i!=colamount) stmt.append(",");
			}
			stmt.append(") ");
			stmt.append("VALUES(");

			for(int j=0;j < value.length ;j++ ) {
				
				stmt.append(value[j]);
				
			if(j!=(value.length-1)) stmt.append(",");
			}
			stmt.append(");");
			
			
			

			resultQuery   =	String.format(stmt.toString(),tablename);
				
			ps.executeUpdate(resultQuery );



		}catch(SQLException  sqe) {
			
			sqe.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
