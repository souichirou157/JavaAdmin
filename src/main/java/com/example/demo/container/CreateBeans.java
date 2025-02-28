package com.example.demo.container;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.container.export.ExportClient;
import com.example.demo.container.operation.dataset.GetOperationData;
import com.example.demo.container.operation.query.Delete;
import com.example.demo.container.operation.query.Disp;
import com.example.demo.container.operation.query.Insert;
//import com.example.demo.container.operation.query.SchemaData;
import com.example.demo.container.operation.query.SchemaData;

@Configuration
public class CreateBeans {
	
	
	
	@Bean  //データベースの取得
	public GetOperationData getOperationData() {
		
		return new GetOperationData();
	}
	@Bean
	public ExportClient getExportsClient() {
		
		
		return new ExportClient();
	}
	
	
	
	@Bean
	public Delete getDelite() {
		
		
		return  new Delete();
	}
	
	@Bean
	public Disp getDisp() {
		
		
		return  new Disp();
	}
	
	
	@Bean
	public Insert getInsert() {
		
		return  new Insert();
	}
	
	@Bean
	public SchemaData getStruct() {
		
		return new  SchemaData();
	}
	
	
		
	
}
