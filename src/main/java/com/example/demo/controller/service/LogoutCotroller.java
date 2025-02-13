package com.example.demo.controller.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.controller.Session;

@Controller

public final class LogoutCotroller {
	@GetMapping("/logout")
	private ModelAndView logoutHandler(ModelAndView mv) {
		
		Session.setAuthority(null);
		Session.setName(null);
		mv.setViewName("logout");
		
		
		System.out.println(Session.getAuthority());
		System.out.println(Session.getuserName()	);
		
		return mv;
	}
}
