package com.example.demo.model.app.service.export.resource;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.model.app.service.export.FileFormat;

public abstract non-sealed class SQLResource  
            implements TemplateFile ,ForFileStatement,SetEncode{
	
	protected  static String extention  = FileFormat.getformat(0).toString();
	
	protected SQLResource() {}
	
	protected File create() 
	{
		
		return TemplateFile.super.create(extention);
	}
	
	@Override
	public ResultSet SetEncodeName(PreparedStatement ps,
			Connection con,String encode) throws SQLException 
	{
		
		
		return SetEncode.super.SetEncodeName(ps,con, encode);
	
	
	}
}