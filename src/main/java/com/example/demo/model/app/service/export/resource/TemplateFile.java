package com.example.demo.model.app.service.export.resource;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public  sealed  interface TemplateFile 

permits CSVResource,JSONResource,SQLResource
{
	
	public default File create(String extention) 
	{
		
		  Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
	      String str = sdf.format( currentTime );
	      File f = new File(str+"."+extention.toLowerCase());
	      
	      return f;
	}
}
