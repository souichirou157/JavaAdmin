package com.example.demo.model.sql.perse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;

import com.example.demo.model.sql.util.Order;

public interface Perser {
	
	
	public abstract StringBuilder ExecuteCommand(String  order) 
			throws SQLException ;
	
	private static boolean OrderCheck(String statement)
	{
		
		if(statement.indexOf("!") == -1) return true; 
		
		return false ;
	}
	
	
	public static boolean StatementPersing(String statement)
			throws PersingException, IllegalAccessException,
			              InvocationTargetException
	{
		
		try {
			
			if(OrderCheck(statement)) {
			
				Class<?> clazz = Lexer.class;
				Method[] LexerMethod =  clazz.getDeclaredMethods();
			
				Arrays.sort(LexerMethod,
						Comparator.comparingInt(
								m -> m.getAnnotation(Order.class).order()
				 ));
					
				for(Method method :LexerMethod) 
				{
					System.out.println(method.getName());
					method.invoke(null,statement);
				}
			}else if(!OrderCheck(statement)){
				throw new PersingException("構文の指定に誤りがありまｓ");
			}
			
				
		}catch(PersingException pe) {
			pe.printStackTrace();
		}
		
		
		//クエリ機能のテストして問題なければ結合していく
		return true;
		
	}
	
	
	public static String Molding(String statement) 
	throws   NotFoundSymbolException
	{
		 
		 for(char c : statement.toCharArray()) {
			 
			 switch(c){
			 
			 case '#' -> statement = statement.replace("#", " ,");
			 case '!' -> statement = statement.replace("!", " ");
			 
			 };
		 }
    	return statement;
	}
	

}


//ここで例外処理QueryFormでtry-catch実装して例外投げたら別コントローラーからレスポンス