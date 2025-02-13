package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.controller.service.JavaAdminService;
import com.example.demo.model.app.Views;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Controller
public class JavaAdminController {

	private JavaAdminService javaadminservice;
private HttpSession  session ;


@GetMapping("/index")
private ModelAndView render(ModelAndView mv) {
	
	mv.setViewName("index");
	mv.addObject("status","ユーザ情報を入力してください");
	
	
	return mv;
}

@PostMapping("/index/login/{username}")
private String loginInvalidCheck(@RequestParam("name") String username,@RequestParam("password") String pass ,
		@PathVariable("username") String name,
		@ModelAttribute Views t,Model model,ModelAndView mv) throws IllegalArgumentException{
	
	try {

		if(javaadminservice.findUser(username,pass))  {
			session.invalidate();
			
				String currentuser = (String)session.getAttribute("currentuser");	
				currentuser = username;
				session.setAttribute("currentuser",currentuser);
				session.setAttribute("databases",javaadminservice.getDatabaseList(session));
				
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

//	
//	
//}
