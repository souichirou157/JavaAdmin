package com.example.demo.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.container.export.ExportClient;
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
	
	
	

		
	
}
