package com.example.demo.controller.menu.disp;

import static com.example.demo.model.sql.views.Disp.*;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.app.Views;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Operation")
public final class DispController {

	@Autowired
	private HttpSession  session ;
	
	@GetMapping("/Disp")
	private ModelAndView dispHanler(ModelAndView  mv,@ModelAttribute Views t,@RequestParam("Id")String ...Id) {
		mv.setViewName("disp");
		
		@SuppressWarnings("uncheked")
		ArrayList<String> database = (ArrayList<String>)session.getAttribute("databases");
		String username = (String) session.getAttribute("currentuser");
		String tablename = (String)session.getAttribute("tablename");
		
		session.setAttribute("databases", database);
		session.setAttribute("currentuser", username);
		session.setAttribute("tablename",tablename);
		

		mv.addObject("databases",database);
		mv.addObject("inputField",setForm(tablename,Id).toString());
		mv.addObject("currentTable",tablename);
		mv.addObject("currentuser","ユーザー: "+username);
		return mv;
	}
}
