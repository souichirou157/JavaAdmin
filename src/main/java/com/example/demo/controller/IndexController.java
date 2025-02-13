package com.example.demo.controller;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.app.Login;
import com.example.demo.model.app.Views;
import com.example.demo.model.sql.Metadata.GetOperationData;

import jakarta.servlet.http.HttpSession;

@Controller
final class IndexController {

	private Login userparam = new Login();
	
	@Autowired
	private HttpSession  session ;
	
	
	@GetMapping("/index")
	private ModelAndView render(ModelAndView mv) {
		
		mv.setViewName("index");
		mv.addObject("status","ユーザ情報を入力してください");
		
		
		
		return mv;
	}
	
	

	
	
	@PostMapping("/login")
	private String loginInvalidCheck(@RequestParam("name") String username,@RequestParam("password") String pass ,@ModelAttribute Views t,Model model,ModelAndView mv) throws IllegalArgumentException{
		
		try {
	
			if(userparam.findUser(username,pass))  {
				session.invalidate();

					String currentuser = (String)session.getAttribute("currentuser");	
					currentuser = username;
					session.setAttribute("currentuser",currentuser);	
				
					@SuppressWarnings("uncheked")
					ArrayList<String> db = (ArrayList<String>)session.getAttribute("databases");
					db = GetOperationData.showDatabase();
					session.setAttribute("databases",db);
					
						return  "forward:/authority/admin";		
			}else {
				
				throw new IllegalArgumentException ();
				
				
			}
		}catch(IllegalArgumentException  ILe) {
			
			ILe.printStackTrace();
			
			mv.setViewName("error");
			mv.addObject("message","ユーザ情報の取得に失敗しました");
			
			return "forward:/error";
		}
	
					
	}	
	
		
	
}
/*
 * */