package com.example.demo.model.app.service.export.resource;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.model.app.service.export.FileFormat;


public abstract non-sealed class JSONResource 

implements TemplateFile ,ForFileStatement,SetEncode
{
	
	protected  static String extention = FileFormat.getformat(1).toString();
	
	protected JSONResource() {}
	
	protected  File create() {	
		return TemplateFile .super.create(extention);
	}

	@Override
	public  ResultSet SetEncodeName(PreparedStatement ps,
			Connection con,String encode) throws SQLException
	{
		
		 return SetEncode.super.SetEncodeName(ps, con,encode);
	
	}

}
