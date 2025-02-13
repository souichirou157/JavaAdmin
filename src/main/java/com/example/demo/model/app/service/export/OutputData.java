package com.example.demo.model.app.service.export;

public sealed interface OutputData 

permits PerSeDataSQL,PerSeDataCSV ,PerSeDataJSON{
	
	public abstract  void exportData(String entity,String encode);
}
