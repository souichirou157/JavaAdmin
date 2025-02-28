package com.example.demo.container;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.container.export.ExportClient;
import com.example.demo.container.export.FileFormat;
import com.example.demo.container.operation.dataset.GetOperationData;
import com.example.demo.container.operation.query.Delete;
import com.example.demo.container.operation.query.SchemaData;


@Component
public class Components {
	
	@Autowired
	private GetOperationData god;
	
	@Autowired
	private ExportClient  export_client;
	
	@Autowired
	private Delete delete;
	
	@Autowired
	private SchemaData  struct;
	
//	@Autowired
//	private Disp  disp;
//	
	
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
	
	
	
	public void SelectIDToDelete(String tablename,String ...Id) {
		
		delete.SelectUserId(tablename,Id);
		
	}
	
	public StringBuilder showSchema(String tablename) {
		
		return struct.showSchema(tablename);
	}

	
	
	
//	public StringBuilder callDispField(String tablename,String Id) {
//		
//		return disp.createForm(tablename, Id);
//	}
//	
//	public void runtimeDisp(String tablename,Object ...values) {
//		
//		disp.runtime( tablename,values);
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
