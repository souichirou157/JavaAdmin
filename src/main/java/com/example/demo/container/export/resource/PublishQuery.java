package com.example.demo.container.export.resource;

public abstract class PublishQuery {
	
	
	private final static String PUBLISHQUERY = " SELECT * FROM %s \n"
	 			+ "     INTO OUTFILE 'C:/Users/cl05/Desktop/%s' \n"
	 			+ "     CHARACTER SET %s \n"
	 			+ "     FIELDS TERMINATED BY ','\n"
	 			+ "     OPTIONALLY ENCLOSED BY '\"'\n"
	 			+ "     ESCAPED BY ''\n"
	 			+ "     LINES TERMINATED BY '\\n';";
	
	
	public static String getStaement() {
		
		return PUBLISHQUERY;
	}

}
