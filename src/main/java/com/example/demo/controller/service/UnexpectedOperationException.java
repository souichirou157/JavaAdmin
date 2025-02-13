package com.example.demo.controller.service;

import org.springframework.web.servlet.ModelAndView;
	
public class UnexpectedOperationException {
	
	

	
		
	
	public static ModelAndView  unAuthorityDetection(ModelAndView mv,String authority) {
		

		if(authority.equals("ViewOnly")) {
			mv.setViewName("error");
			mv.addObject("message","un authorized request");
			mv.addObject("info");
			
		}
		
		return mv;
		
		
	}
	
	

}
