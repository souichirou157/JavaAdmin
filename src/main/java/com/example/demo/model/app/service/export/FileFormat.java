package com.example.demo.model.app.service.export;


public enum FileFormat {
	
	SQL,JSON,CSV;

	static FileFormat  []format = {SQL,JSON,CSV};

	
	public static FileFormat  []getformat() {
		
		return format;
	}
	
public static FileFormat  getformat(int index) {
		
		return format[index];
	}
}
