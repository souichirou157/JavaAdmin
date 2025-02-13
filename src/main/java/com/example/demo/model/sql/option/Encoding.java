package com.example.demo.model.sql.option;

public enum Encoding {
	UTF8,SJIS,CP932,UTF8MB4;	
	static Encoding  encoding [] = {UTF8,SJIS,CP932,UTF8MB4};
	
	public static Encoding  []getEncoding() {
		
		return encoding;
	}
	
	
	public static Encoding getEncoding(int index) {
		
		return encoding[index];
	}
}
