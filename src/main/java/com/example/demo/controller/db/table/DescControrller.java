package com.example.demo.controller.db.table;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.sql.Core;
import com.example.demo.model.sql.views.Struct;

import jakarta.servlet.http.HttpSession;

@Controller
public class DescControrller {
	
	@Autowired
	private HttpSession  session ;
	
	@GetMapping("/Struct")
	public ModelAndView tableDescHandler(ModelAndView mv,Model model) {
		
		
		mv.setViewName("admin");
		
		@SuppressWarnings("uncheked")
		ArrayList<String> database = (ArrayList<String>)session.getAttribute("databases");
		String tablename = (String)session.getAttribute("tablename");
		String username = (String) session.getAttribute("currentuser");
		String dbname = (String)session.getAttribute("dbname");
		session.setAttribute("databases", database);
		session.setAttribute("currentuser", username);
		session.setAttribute("tablename", tablename);
		session.setAttribute("dbname", dbname);
		
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("databases",database);
		mv.addObject("preview",Struct.showStruct(tablename));
		mv.addObject("test",tablename);
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("currentTable",tablename);
		mv.addObject("export","エクスポート");
		mv.addObject("ItemMessage","チェックを");

		
		
		
		
		return mv;
	}
}
