package com.example.demo.model.app.service.export;


public  interface ExportClient {
	
	
	public static void export(String tablename,
			FileFormat extension,String encode) 
	{
		switch(extension) {
		
		case FileFormat.CSV ->new  PerSeDataCSV().exportData(tablename,encode);
		
		case FileFormat.JSON ->new  PerSeDataJSON().exportData(tablename,encode);
		
		case FileFormat.SQL -> new PerSeDataSQL().exportData(tablename,encode);
			
		
		}
	
		
	}
	
}
