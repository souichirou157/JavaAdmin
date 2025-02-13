package com.example.demo.model.app.service.export.resource;

public sealed interface ForFileStatement permits CSVResource,JSONResource,SQLResource{
	
	String PUBLISHQUERY = " SELECT * FROM %s \n"
	 			+ "     INTO OUTFILE 'C:/Users/cl05/Desktop/%s' \n"
	 			+ "     CHARACTER SET %s \n"
	 			+ "     FIELDS TERMINATED BY ','\n"
	 			+ "     OPTIONALLY ENCLOSED BY '\"'\n"
	 			+ "     ESCAPED BY ''\n"
	 			+ "     LINES TERMINATED BY '\\n';";

}
