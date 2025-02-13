package com.example.demo.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.app.Views;
import com.example.demo.model.sql.Core;
import com.example.demo.model.sql.Metadata.GetOperationData;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/authority")
final class AdminPageController {

	@Autowired
	private HttpSession  session ;
	
	@PostMapping("/admin")
	private ModelAndView adminUserLoginHandle(ModelAndView mv,Model model,@ModelAttribute Views t) {
	
		mv.setViewName("admin");
		String username = (String)session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		@SuppressWarnings("uncheked")
		ArrayList<String> db = (ArrayList<String>)session.getAttribute("databases");
		session.setAttribute("databases", db);
		
		
		mv.addObject("operable","SQL");
		mv.addObject("export","エクスポート");
		mv.addObject("Insert","挿入");
		mv.addObject("Struct","構造");
		mv.addObject("SelectedDB");
		mv.addObject("Server","サーバーの名前入る");
		mv.addObject("tableList",t.addTableList(t,model));
		mv.addObject("currentTable");
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("databases",db);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("len",2);
		
		mv.addObject("item",Core.getDMLOrders());
		ArrayList<Views> array= (ArrayList<Views>)mv.getModel().get("tableList");
		Stream.of(mv.getModel().get("tableList").equals(array)).map(Object::toString).collect(Collectors.toList()).getFirst();
	
	
		return mv;
	}
	
	
	@PostMapping("/ViewOnly")
	private ModelAndView ViewOnlyUserLoginHandle(ModelAndView mv,Model model,@ModelAttribute Views t) {
		
		mv.setViewName("admin");
		
		String username = (String)session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		
		@SuppressWarnings("uncheked")
		ArrayList<String> db = (ArrayList<String>)session.getAttribute("databases");
		session.setAttribute("databases", db);
		
		
		mv.addObject("export","エクスポート");
		mv.addObject("struct","show_struct");
		mv.addObject("tableList",t.addTableList(t,model));
		mv.addObject("currentTable");
		mv.addObject("Server","サーバーの名前入る");
		mv.addObject("databases",db);
		mv.addObject("SelectedDB","使用中のDB");
		mv.addObject("currentuser","ユーザー\n"+username);
		session.setAttribute("currentuser", username);
		
		mv.addObject("item",Core.getDMLOrders());
		ArrayList<Views> array= (ArrayList<Views>)mv.getModel().get("tableList");
		Stream.of(mv.getModel().get("tableList").equals(array)).map(Object::toString).collect(Collectors.toList()).getFirst();
	

		
		return mv;
	}
	
	//別画面から戻ってくるだけの時
	@GetMapping("/return_to_top")	
	private ModelAndView returnTopHandle(ModelAndView mv,Model model,@ModelAttribute Views t) {
		
		mv.setViewName("admin");
		
		ArrayList<String> database = GetOperationData.showDatabase();
		String tablename = (String)session.getAttribute("tablename");
		String username = (String)session.getAttribute("currentuser");
		String dbname = (String)session.getAttribute("dbname");
		session.setAttribute("dbname", dbname);
		session.setAttribute("currentuser", username);
		session.setAttribute("databases", database);
		session.setAttribute("tablename", tablename);
		
	
		mv.addObject("Server","サーバーの名前入る");
		mv.addObject("operable","SQL");
		mv.addObject("export","エクスポート");
		mv.addObject("Insert","挿入");
		mv.addObject("struct","show_struct");
		mv.addObject("tablelist","<div  th:insert =\"tablelist\" style=\"width: 12%; height:12%;\"></div>");
		mv.addObject("tableList", t.addTableList(t,model));
		mv.addObject("Struct","構造");
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("currentTable",tablename);
		
		mv.addObject("len",2);
		mv.addObject("databases",database);
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("item",Core.getDMLOrders());
		ArrayList<Views> array= (ArrayList<Views>)mv.getModel().get("tableList");
		Stream.of(mv.getModel().get("tableList").equals(array)).map(Object::toString).collect(Collectors.toList()).getFirst();
	
		
		return mv;
	}
	
}
