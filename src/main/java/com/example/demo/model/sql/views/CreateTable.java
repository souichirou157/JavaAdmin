package com.example.demo.model.sql.views;

public class CreateTable {
	
	private StringBuilder  form;
	private final String items[] = {"カラム名","データ型","長さ","制約","インデックス","デフォルト"};
	
	
	public StringBuilder createForm(int colamount) {
		
		form = new StringBuilder();
		
		//colamountリクエストパラメータ用引数
		
		form.append("<form>");
		form.append("<table border = \"3\" >");
		form.append("<tbody>");
		form.append("<tr>");
		for(String item : items)form.append("<th>").append(item).append("</th>");     
		form.append("</tr>");
		form.append("/tbody");
		
		
		form.append("tbody");
		form.append("<tr>");
		for(int i  =0 ; i < colamount;i++) {
			
			form.append("<input type = text   >");
		}
		form.append("</tr>");
		form.append("</tbody>");
		form.append("</table>");
		form.append("</form>");
		return form;
		
	}
	
}
