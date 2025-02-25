package com.example.demo.container.export;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.DSN.Resource;
import com.example.demo.container.export.resource.CreateFile;
import com.example.demo.container.export.resource.PublishQuery;
import com.example.demo.container.export.resource.SetEncode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;




public  final class PerSeDataJSON 

 
{
	private final static String labels[] = {"column","row","rows"};
	
	public  final  void exportDataJSON(String entity,String encode) { 
		
		  try (Connection con = DriverManager.getConnection(Resource.getUrl(),
					Resource.getUser(),Resource.getPassword()))
		  {
			  java.io.File f = CreateFile.create(".JSON");
			  PreparedStatement ps =null;
				
		   	    	
		   	    System.out.println("C:/Users/cl05/Desktop/");
		     
		        
		   	   ResultSet  rs = SetEncode.SetEncodeName(ps, con, encode);
		        
		        if (rs.next()) {
		            System.out.println("Character Set Client: " + rs.getString("Value"));
		        }
		        
		   	 
		        ps = con.prepareStatement(String.format(PublishQuery.getStaement(),entity,
		        		f,encode.toLowerCase()));
		   	 	ps.executeQuery();
		   	 	
			 	try
			 	{
			 
			 		String metadata = "SELECT * FROM %s";
				 	ps = con.prepareStatement(String.format(metadata,entity),ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				 	rs = ps.executeQuery();

				 	java.sql.ResultSetMetaData rsmetadta  = rs.getMetaData();
			
				 	int colamount = rsmetadta.getColumnCount();
					

				 	//昼からこれ
					ObjectMapper mapper = new ObjectMapper();
					ArrayNode  rootNode = mapper.createArrayNode();
					ObjectNode data = rootNode.addObject();  
					ObjectNode tablename = data.putObject("name");
					ObjectNode records = data.putObject(labels[1]);
					
					

					ObjectNode table = tablename.putObject(entity);
		
						rs.beforeFirst();
						
						while(rs.next()) {
								
							ObjectNode row = records.putObject(labels[0] + rs.getRow());
						    for (int i = 1; i <= colamount; i++) {
						        row.put(rsmetadta.getColumnName(i), rs.getString(i));
						    }
							  
						}
						
						
						records.fieldNames().forEachRemaining(field ->{
							
							JsonNode node = records.get(field);
							
							System.out.println(records+":"+node.asText());
						});
							

	                   
			 			System.out.println(mapper.writeValueAsString(".JSON"));
			 
					 		
			 		
			 	    mapper.writeValue(new File("C:/Users/cl05/Desktop/"+ f),rootNode);
			 	    
			 	    
			 	    
			 	} catch (IOException ex) {
			 	    ex.printStackTrace();
			 	}
		   	 	
		}catch(SQLException e){
			e.printStackTrace();
		}	
	}

}
	
/*	
   	 	String metadata = "SELECT * FROM %s";
	 	ps = con.prepareStatement(String.format(metadata,entity,f.toString(),format));
	 	rs =			ps.executeQuery();
	 	
	 	while(rs.next()) {
	 		System.out.println(rs.getString("id"));
	 		
	 		
	 	}
	 	System.out.println(rs.getMetaData().);
	 	
	
	 	for(int i =1; i < rs.getMetaData().getColumnCount(); i++) System.out.println(rs.getMetaData().getColumnLabel(i));

	 		
	 	*/