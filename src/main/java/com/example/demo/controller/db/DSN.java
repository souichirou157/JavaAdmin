package com.example.demo.controller.db;


//内部クラス作ってそこにゲッター作る
public  final  class DSN {
	
	private static String dbname="mysql";
	private static String url = "jdbc:mysql://localhost/"+dbname;
	private static String user = "root";
	private static String password = "";
	private static String server = "";
	
	public DSN() {}
	
	public DSN(String dbname) {
		
		DSN.dbname = dbname;
		DSN.url = "jdbc:mysql://localhost/"+dbname;
	}
	
	
	public static class Resource
	{
	
		public static String getUrl() { return url;}
		public static String getUser() { return user;}
		public static String getPassword() {return password;}
		public static String getDBName() { return dbname;}
		public static void DsnInitialization(String dbname) {   new DSN(dbname); }
			
	}
	
}
