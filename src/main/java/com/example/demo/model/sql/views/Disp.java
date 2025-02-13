
package com.example.demo.model.sql.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.controller.db.DSN.Resource;
import com.example.demo.model.sql.Metadata.OperateTable; 

public final class Disp {
	
	
	private static String []updateid;
	
	
	
	static class UseQuery
	{

		private final static String statement[] = { 
		
				"SELECT * FROM   %s  WHERE %s =  %s;",
				"UPDATE  %s  SET  %s =  %s WHERE  %s  =   %s   ; "
		
		};

		
	}
public  static StringBuilder setForm(String tablename,String ...Id) {
		
		
		updateid = new String[Id.length];
		for(int i =0; i< Id.length;i++) updateid[i] = Id[i];

		try(Connection    con =  DriverManager.getConnection(Resource.getUrl(),
				Resource.getUser(),Resource.getPassword());)
		{
			
			java.sql.ResultSetMetaData rsmetadata  = OperateTable.GetTemplatData(tablename);
			
			
			int colamount = rsmetadata.getColumnCount();
			
			
			StringBuilder  textfield  = new StringBuilder();	
		 	 	
		
		 for(int j =0; j < Id.length;j++) {
				PreparedStatement	ps = con.prepareStatement(
						String.format(UseQuery.statement[0],tablename,
								rsmetadata.getColumnName(1),Id[j]));
				
				ResultSet rs  = ps.executeQuery();
				
			 
			 	textfield.append("<table border=\"3\" class =\"inputField\"")
			 	.append("style=\"margin:64px ;  text-align:center; \" > ");
			 	
			 	textfield.append("<tr><td>カラム</td><td>タイプ</td>")
			 	.append("<td>関数</td><td>Null</td><td>値</td></tr>");
			 							
			 	while(rs.next()) 
			 	{
			 		
			 		for(int i =1;  i <=colamount;i++) {
			 			
			 			int nullable = rsmetadata.isNullable(i); 
			 			int datasize = rsmetadata.getPrecision(i);
			 		
			 			textfield.append("<tr>").append("<td style=\"width:25%;\">").append(rsmetadata.getColumnName(i))
			 			.append("("+rsmetadata.getPrecision(i)+")")
				 		
			 			.append("</td>").append("<td class=\"DataType\"   style=\"width:25%;\">")
				 		.append(rsmetadata.getColumnTypeName(i)).append("</td>")
				 		
				 		.append("<td style=\"width:25%;\"><select  size=\"1\"  name=\"method_field\"  >")
				 		.append("<option>NOW()</option></td>");	
				 	
			 		
			 			if(nullable == rsmetadata.columnNullable) {
			 				textfield.append("<td>")
			 				.append("<input type = \"checkbox\"  > ")
			 				.append("</td>");		
			 				textfield
			 				.append("<td>").append("<input class=\"values\" type=\"text\"")
			 				.append(" maxlength =  "+datasize+" value = "+rs.getString(i)+" name=\"values\" >")
			 				.append("</td>").append("</tr>");
			 				
			 			}else if(nullable == rsmetadata.columnNoNulls){
			 				  textfield
			 				  .append("<td>")
			 				  .append("<input type = \"checkbox\"  checked> ")
			 				  .append("</td>");	 
			 				
			 				textfield
			 				.append("<td>")
			 				.append("<input class=\"values\" type=\"text\"")
			 				.append(" maxlength =  "+datasize+" value = "+rs.getString(i)+"  name=\"values\"  >")
			 				.append("</td>")
			 				.append("</tr>");
			 				
			 			}
			 			
			 		}
			 		
			 		 textfield
			 		 .append("<input type=\"hidden\" class=\"userid\" name=\"Id\"")
			 		 .append("th:with=\"ID=${").append(rs.getString(1)).append("}\"")
			 		 .append(" >"); 		
			 	}
		   
	 		textfield.append("</table>"); 		
				
			}
		 
		 return  textfield; 	
		}catch(SQLException  sqe) {
			sqe.printStackTrace();
			
			return null;
		}
		
		
	
	}


public  static void updateData(String tablename,Object ...values) {
	

		
	try(Connection    con =  DriverManager.getConnection(Resource.getUrl(),
			Resource.getUser(),Resource.getPassword())){
		
		java.sql.ResultSetMetaData rsmetadata  = 	OperateTable.GetTemplatData(tablename);
		int colamount = rsmetadata.getColumnCount();


		
	

	int j=0;
	
	for(String id :updateid) {
		
		for(int i =1; i <=colamount;i++) 
		{

			
			if(values[j] instanceof  Number) {
			
				PreparedStatement 	ps = con.prepareStatement(
						
						  String.format(
								        UseQuery.statement[1]
								        ,tablename
								        ,rsmetadata.getColumnName(i)
								        ,values[j]
										,rsmetadata.getColumnName(1)
										,id
										)
				);
				
				System.out.println(ps);
				ps.executeUpdate();

			}else 
			
			{

				PreparedStatement	ps = con.prepareStatement(
					
					      String.format(
				         			    UseQuery.statement[1]
							    		,tablename
							    		,rsmetadata.getColumnName(i)
							    		,"\""+values[j]+"\""
							    		,rsmetadata.getColumnName(1)
							    		,id
							   )
				);
				System.out.println(ps);
					
				ps.executeUpdate();
				
			}

			j++;
		}

	}
	
	}catch(SQLException sqe) {
		
		sqe.printStackTrace();
	}	

	
}

	
}
