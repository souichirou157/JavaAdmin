package com.example.demo.model.sql;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.function.Consumer;

import com.example.demo.model.sql.Metadata.reservedwords.FOR_STATEMENTS;
import com.example.demo.model.sql.perse.Perser;
import com.example.demo.model.sql.perse.PersingException;
import com.example.demo.model.sql.statements.DeleteStatement;
import com.example.demo.model.sql.statements.DropStatement;
import com.example.demo.model.sql.statements.InsertStatement;
import com.example.demo.model.sql.statements.SelectStatment;
import com.example.demo.model.sql.statements.UpdateStatement;
public class Core {
	
	String  InputStatementOrder;
	CallStatement query ;
	private	final static FOR_STATEMENTS  DMLORDERS[]= new FOR_STATEMENTS[]{FOR_STATEMENTS.DELETE,FOR_STATEMENTS.UPDATE,FOR_STATEMENTS.INSERT};
	
	public static FOR_STATEMENTS  []getDMLOrders() 
	{
		return DMLORDERS;
	}
		
	
	private Core(String order)
	{
		
		InputStatementOrder  = order;
		System.out.println(InputStatementOrder);
		
		
	}
	
	public static Consumer<Core>get(Consumer<Core> con,String order){
		con.accept(new Core(order));	
		
		return con;
	}
	
	
	public StringBuilder ExecuteCommand(String  order) 
			
			throws SQLException, IllegalAccessException,
			InvocationTargetException, PersingException
	{
		

			
		
		if(Perser.StatementPersing(InputStatementOrder)) {
			
			switch (InputStatementOrder)
			{
			
			case "SELECT","select" ->query = SelectStatment.select();
			
			case "INSERT","insert" ->query  = InsertStatement.insert();
			
			case "DELETE","delete" -> query = DeleteStatement.delete();
				             
			case "DROP","drop" ->   query = DropStatement.drop();
			case "UPDATE","update" -> query = UpdateStatement.update () ;
			
			}
			
			return query.query(order);
			
			
		}
			

//
		
		
		return null;
	}
	
//	// 動的呼び出しに使えそう
//	 Class<?> clazz = Sample.class;
//	 Field field [] = clazz.getDeclaredFields();
//
//	 System.out.println(clazz.getSimpleName());
//	 System.out.println(clazz.getCanonicalName().replace(clazz.getPackageName(),"").replace(".",""));
//	 System.out.println(clazz.getPackageName());
//
	//
}
