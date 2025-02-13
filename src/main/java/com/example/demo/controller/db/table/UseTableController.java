package com.example.demo.controller.db.table;

import java.util.ArrayList;

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

import jakarta.servlet.http.HttpSession;

@Controller
public final class UseTableController {

	@Autowired
	private HttpSession  session ;
	

	@GetMapping("/tb")
	private String  BranchHandler(@RequestParam("table") String tablename,RedirectAttributes redirectAttributes,ModelAndView mv,Model model) {
	
		mv.setViewName("admin");
		
		String username = (String) session.getAttribute("currentuser");
		@SuppressWarnings("uncheked")
		ArrayList<String> database = (ArrayList<String>)session.getAttribute("databases");
		String dbname = (String)session.getAttribute("dbname");
		session.setAttribute("currentuser", username);
		session.setAttribute("databases", database);
		session.setAttribute("dbname", dbname);
		redirectAttributes.addFlashAttribute("tablename",tablename);
		
		mv.addObject("SelectedDB",dbname);
		mv.addObject("databases",database);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("operable","SQL");
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("Server");
		
		
		return  "redirect:/tableschema";
	}
	
	@GetMapping("/tableschema")
	public ModelAndView tableHandler(@ModelAttribute Views t,@ModelAttribute("tablename") String tablename,ModelAndView mv,Model model) {
		
		mv.setViewName("admin");
		
		@SuppressWarnings("uncheked")
		ArrayList<String> database = (ArrayList<String>)session.getAttribute("databases");
		tablename = (String)model.getAttribute("tablename");
		String username = (String) session.getAttribute("currentuser");
		String sessiontable = (String)session.getAttribute("tablename");
		String dbname = (String)session.getAttribute("dbname");
		sessiontable = tablename;
		
		session.setAttribute("dbname", dbname);
		session.setAttribute("databases", database);
		session.setAttribute("currentuser", username);
		session.setAttribute("tablename", sessiontable);
		mv.addObject("struct","構造");
		mv.addObject("Insert","挿入");
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("databases",database);
		mv.addObject("preview",t.findAll(tablename));
		mv.addObject("test",tablename);
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("currentTable",tablename);
		mv.addObject("export","エクスポート");
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("Server");
		
		
		return mv;
	}


}
