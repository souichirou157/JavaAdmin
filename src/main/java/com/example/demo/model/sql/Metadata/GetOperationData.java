package com.example.demo.model.sql.Metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.demo.controller.db.DSN.Resource;

//テーブルリストを格納する配列を可変長に変更する
public class  GetOperationData {

	static ResultSet rs = null;
	 
	public   String gettable(int index){ return this.TableList.get(index) ;}
	private  ArrayList <String> TableList = new ArrayList<>();
	public   ArrayList <String>  gettable() {return this.TableList;}

	public GetOperationData (){
		
	}
		
	
	public int rowCount(String tablename,int records) {
		 
			try(Connection    con =  DriverManager.getConnection(Resource.getUrl(), 
					Resource.getUser(), Resource.getPassword() );){

				PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS ROWCOUNT FROM "+" "+tablename);
				ResultSet rs  =  ps.executeQuery();
				
				while (rs.next()) records  = rs.getInt("ROWCOUNT");
					
				
			} catch (SQLException e) {
					e.printStackTrace();    
			} 
		
			
			return records ;
	}
	

	
public  static  ArrayList <String>  showtable(String dbname){
		
		 ArrayList <String> TableList = new ArrayList<>();
		 
		 Resource.DsnInitialization(dbname); 
		 try(Connection    con =  DriverManager.getConnection(Resource.getUrl(), Resource.getUser(), Resource.getPassword());){
			 
	
			 System.out.println("確認");
			 
			 PreparedStatement	ps = con.prepareStatement("show tables ;");
			 ResultSet rs  =  ps.executeQuery();

			java.sql.ResultSetMetaData  result = rs.getMetaData();
			
	
			while (rs.next()) {
			
				//DSN.getDBName().replace(" ;", "")
				
				for(int i =1; i<=result.getColumnCount();i++) {
					TableList.add(rs.getString(result.getColumnLabel(i)));
					System.out.println(rs.getString(result.getColumnLabel(i)));
					System.out.println();
				}
	
//				sb.append("<tr>");
//				sb.append("<td>").append(rs.getString("Tables_in_"+(DSN.dbname.replace(" ;","")))).append("</td>");
//				sb.append("</tr>");

				System.out.println("テスト");
				//System.out.println(rs.getString("Tables_in_"+DSN.getDBName().replace(" ;", "")));
			}
			
			 con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
	
	return TableList;
  }

	

	

	
public  static  ArrayList<String> showDatabase(){
		
		ArrayList<String> database = new ArrayList<String>();
		
		 
		 try(Connection    con =  DriverManager.getConnection(Resource.getUrl(), 
				 Resource.getUser(), Resource.getPassword());){
			
			 PreparedStatement ps = con.prepareStatement("show databases ;");
			 ResultSet rs  =  ps.executeQuery();
		
			
			while (rs.next()) database.add(rs.getString("Database"));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		 
		 return database;
	
	
	
 }



}	
	