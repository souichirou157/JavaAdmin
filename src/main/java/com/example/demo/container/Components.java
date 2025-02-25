package com.example.demo.container;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.container.export.ExportClient;
import com.example.demo.container.export.FileFormat;


@Component
public class Components {
	
	@Autowired
	private GetOperationData god;
	
	@Autowired
	private ExportClient  export_client;
	
	public ArrayList<String> showDatabase(){
		
		return god.showDatabase();
	}
	
	
	public    ArrayList <String>  showtable(String dbname){
		
		
		return god.showtable(dbname);
	}
	


	public void exportDataFormat(String tablename,
			FileFormat extension,String encode) {
		
		export_client.export_branch(tablename,extension,encode);
		
	}
	
	
}
