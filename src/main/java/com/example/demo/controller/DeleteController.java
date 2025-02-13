package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.app.Views;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Operation")

public final class DeleteController {
	
	@Autowired
	private HttpSession  session ;
	
	
	@GetMapping("/delete")
	private  ModelAndView handler(ModelAndView mv,@ModelAttribute Views t,Model model,@RequestParam("Id") int ...Id) {
		
		
		mv.setViewName("delete");
		
		@SuppressWarnings("uncheked")
		ArrayList<String> database = (ArrayList<String>)session.getAttribute("databases");
		//テーブル
		String tablename = (String)session.getAttribute("tablename");
		
		session.setAttribute("tablename", tablename);
		session.setAttribute("databases", database);
		
		mv.addObject("databases",database);
		mv.addObject("selecttable",tablename);	
		mv.addObject("checkuserID",t.getId(tablename,Id).toString());
		
		mv.addObject("value",Id);
			
		
	
		return mv;
	}
}
