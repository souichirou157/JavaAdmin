
package com.example.demo.container.operation.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.demo.DSN.Resource;
import com.example.demo.model.sql.Metadata.OperateTable;

public final class Delete {

	 
        static class UseQuery {
        	
        	private final static String  statement = "DELETE FROM  %s  WHERE %s  =  %s ;";
        }
	
	public  void SelectUserId(String tablename,String ...Id) {
		
		try (Connection    con =  DriverManager.getConnection(Resource.getUrl(),
				Resource.getUser(),Resource.getPassword()))
		
		{
		
			for(String id:Id) 
			{
				PreparedStatement	ps = con.prepareStatement(
						String.format(
								UseQuery.statement,tablename,
								OperateTable.GetTemplatData(tablename).getColumnName(1)
								,id
								)
				);
					ps.executeUpdate();
			}
		
		}catch(SQLException sqe) {
			
			sqe.printStackTrace();
		}
	}
	
	
	
}
