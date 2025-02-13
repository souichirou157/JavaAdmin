package com.example.demo.controller.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.app.Views;
import com.example.demo.model.sql.Core;
import com.example.demo.model.sql.Metadata.GetOperationData;

import jakarta.servlet.http.HttpSession;

@Controller
public final class DBMappingController {
	
	@Autowired
	private HttpSession  session ;
	
	@GetMapping("/db")
	private String  showtablehandle(@RequestParam("database") String dbname,RedirectAttributes redirectAttributes,
			@ModelAttribute Views t,Model model,String db,ModelAndView mv) {
			
		mv.setViewName("admin");
		String username = (String)session.getAttribute("currentuser");
		@SuppressWarnings("uncheked")
		ArrayList<String> 
		database = (ArrayList<String>)session.getAttribute("databases");
		
		session.setAttribute("currentuser", username);
		session.setAttribute("databases", database);
		
		
		mv.addObject("Struct","構造");
		mv.addObject("databases",database);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("ItemMessage","チェックを");
	
		System.out.println("show databases ;");
		redirectAttributes.addFlashAttribute("dbname",dbname);
		
//		
//		@RequestParam("dbpointer") int dbpointer,
//		redirectAttributes.addFlashAttribute("dbpointer",dbpointer);
//		
	

		return "redirect:/showtable";
	}
	
	@GetMapping("/showtable")
	private ModelAndView showtableHandler(@ModelAttribute("dbname") String dbname,
			ModelAndView mv,Model model) {
		
		mv.setViewName("admin");
		String username = (String)session.getAttribute("currentuser");
		@SuppressWarnings("uncheked")
		ArrayList<String> db = (ArrayList<String>)session.getAttribute("databases");
		String usedb = (String) session.getAttribute("dbname");
		dbname = (String) model.getAttribute("dbname");
		usedb = dbname;

		session.setAttribute("currentuser", username);
		session.setAttribute("databases", db);
		session.setAttribute("dbname",usedb);
		System.out.println("show tables ;");
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("SelectedDB",dbname);
		mv.addObject("databases",db);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("test",dbname);
		mv.addObject("Struct","構造");
		@SuppressWarnings("uncheked")
		List<String> tables = (ArrayList<String>) session.getAttribute("table");
		session.setAttribute("table",tables);
		tables = GetOperationData.showtable(dbname);
		mv.addObject("TableList",tables);
			
		
		return  mv;
	}
	
}
