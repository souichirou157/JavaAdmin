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

import com.example.demo.container.Components;
import com.example.demo.controller.service.JavaAdminService;
import com.example.demo.model.app.Views;
import com.example.demo.model.sql.Core;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/authority")
final class LoginController {

	@Autowired
	private JavaAdminService javaadminservice;
	@Autowired
	private HttpSession  session ;
	
	@Autowired
	private Components  component;
	
	@PostMapping("/admin")
	private ModelAndView adminUserLoginHandle(ModelAndView mv,Model model,@ModelAttribute Views t) {
	
		mv.setViewName("admin");
		String username = (String)session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		session.setAttribute("databases", javaadminservice.getDatabaseList(session));
		
		
		mv.addObject("operable","SQL");
		mv.addObject("export","エクスポート");
		mv.addObject("Insert","挿入");
		mv.addObject("Struct","構造");
		mv.addObject("SelectedDB");
		mv.addObject("Server","サーバーの名前入る");
		mv.addObject("tableList",javaadminservice.addTableList(t,model));
		mv.addObject("currentTable");
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("databases",javaadminservice.getDatabaseList(session));
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
		session.setAttribute("databases", javaadminservice.getDatabaseList(session));
		
		
		mv.addObject("export","エクスポート");
		mv.addObject("struct","show_struct");
		mv.addObject("tableList",javaadminservice.addTableList(t,model));
		mv.addObject("currentTable");
		mv.addObject("Server","サーバーの名前入る");
		mv.addObject("databases",javaadminservice.getDatabaseList(session));
		mv.addObject("SelectedDB","使用中のDB");
		mv.addObject("currentuser","ユーザー\n"+username);
		session.setAttribute("currentuser", username);
		
		mv.addObject("item",Core.getDMLOrders());
		ArrayList<Views> array= (ArrayList<Views>)mv.getModel().get("tableList");
		Stream.of(mv.getModel().get("tableList").equals(array)).map(Object::toString).collect(Collectors.toList()).getFirst();
	

		
		return mv;

	}
	//ログアウト
	@GetMapping("/logout")
	private ModelAndView logoutHandler(ModelAndView mv) {
		
		Session.setAuthority(null);
		Session.setName(null);
		mv.setViewName("logout");
		
		
		System.out.println(Session.getAuthority());
		System.out.println(Session.getuserName());
		
		return mv;
	}


	
	//別画面から戻ってくるだけの時
	@GetMapping("/return_to_top")	
	private ModelAndView returnTopHandle(ModelAndView mv,Model model,@ModelAttribute Views t) {
		
		mv.setViewName("admin");
		
		ArrayList<String> database =  component.showDatabase();
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
		mv.addObject("tableList", javaadminservice.addTableList(t,model));
		mv.addObject("databases",javaadminservice.getDatabaseList(session));
		mv.addObject("Struct","構造");
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("currentTable",tablename);
		mv.addObject("preview",javaadminservice.findAll(tablename));
		mv.addObject("len",2);
		mv.addObject("databases",database);
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("item",Core.getDMLOrders());
		ArrayList<Views> array= (ArrayList<Views>)mv.getModel().get("tableList");
		Stream.of(mv.getModel().get("tableList").equals(array)).map(Object::toString).collect(Collectors.toList()).getFirst();
	
		
		return mv;
	}
	
}
