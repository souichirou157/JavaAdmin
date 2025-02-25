package com.example.demo.container.export;

public  class ExportClient {
	
	private static final PerSeDataCSV data_csv = new PerSeDataCSV();
	private static final PerSeDataJSON data_json =  new PerSeDataJSON();
	private static final PerSeDataSQL data_sql = new PerSeDataSQL();
	
	public void export_branch(String tablename,
			FileFormat extension,String encode) 
	{
	
		switch(extension) {
		
		case FileFormat.CSV -> data_csv.exportDataCSV(tablename, encode);
		case FileFormat.JSON ->data_json.exportDataJSON(tablename, encode);
		case FileFormat.SQL -> data_sql.exportDataTableStruct(tablename, encode);
			
		
		}
	
		
	}
	

}
