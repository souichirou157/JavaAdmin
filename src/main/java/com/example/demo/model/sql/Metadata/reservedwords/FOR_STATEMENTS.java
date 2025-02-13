package com.example.demo.model.sql.Metadata.reservedwords;



public enum FOR_STATEMENTS {
	
		USE("USE","use"),FROM("FROM","from"),JOIN("JOIN","join"),WHERE("WHERE","where"),SELECT("SELECT","select"),DATABASES("DATABASES","databases"),EXPORT("EXPORT","export"),
		TABLES("TABLES","tables"),CREATE("CREATE","create"),DEFAULT("DEFAULT","default"),INSERT("INSERT","insert"),IN("IN","in"),INTO("INTO","into"),VALUES("VALUES","values"),
		DELETE("DELETE","delete"),DROP("DROP","drop"),UPDATE("UPDATE","update"),
		TABLE("TABLE","table"),DESC("DESC","desc"),ASC("ASC","asc"),SHOW("SHOW","show"),COLUMN("COLUMN","column"),ADD("ADD","add"),_G("\\G","\\g"),MODIFY("MODIFY","modify");

	
		
		FOR_STATEMENTS(String string, String string2) {}
		
		FOR_STATEMENTS(String string) {
			// TODO 自動生成されたコンストラクター・スタブ
		}

			
	
	
}
