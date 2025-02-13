package com.example.demo.model.sql.Metadata;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData ;
import java.sql.SQLException;

import com.example.demo.DSN.Resource;

public class OperateTable {

	 static java.sql.ResultSetMetaData  rsd ;
		static ResultSet 	rs ;
		static	PreparedStatement  ps;
//データ型チェックもここでやる
	//作り直す、whileまでこっちいれてMetadata返すようにする。
	OperateTable(){}

	
	
	
	
	public static ResultSetMetaData  GetTemplatData (String tablename) 
			throws SQLException 
	{
	
		try(Connection    con =  DriverManager.getConnection(Resource.getUrl(),
				Resource.getUser(),Resource.getPassword() );){
		
			
			String selectstmt = "SELECT *  FROM   %s";
			ps = con.prepareStatement(String.format(selectstmt,tablename));

			ResultSet 	rs = ps.executeQuery();
			rsd  = rs.getMetaData();
			
			return rsd;
		
		}catch(SQLException  sq) {
			
			sq.printStackTrace();
			
			
			return null;
		}
		
	}
	
	public static PreparedStatement getPrepare() {
		return ps;
	}

}






//test
//int i =1;
//while(rs.next()&&i<=rsmetadata.getColumnCount()) {
//	
//	System.out.print(rs.getString(i));
//	System.out.println();
//	i++;
//}

//