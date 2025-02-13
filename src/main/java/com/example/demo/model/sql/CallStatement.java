package com.example.demo.model.sql;

import java.sql.SQLException;

public interface CallStatement {
	public abstract StringBuilder query(String statement) throws SQLException;

}	
