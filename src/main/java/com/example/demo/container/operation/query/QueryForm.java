package com.example.demo.container.operation.query;



public interface QueryForm {
	
//	public abstract StringBuilder createForm(String tablename,String ...Id);
	public abstract  void runtime(String tablename, Object... values);
}
