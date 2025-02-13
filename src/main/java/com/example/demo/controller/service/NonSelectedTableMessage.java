package com.example.demo.controller.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.app.Views;

@Controller
public class NonSelectedTableMessage {
	
	
	
	
	public static String handler(ModelAndView mv,@ModelAttribute Views t,Model model) {
	
		mv.setViewName("admin");
		mv.addObject("operable","SQL");
		mv.addObject("export","エクスポート");
		mv.addObject("prev","<<");
		mv.addObject("next",">>");
		mv.addObject("Insert","挿入");
		mv.addObject("Disp","編集");
		mv.addObject("Del","削除");
		mv.addObject("display","show_data");
		mv.addObject("struct","show_struct");
		mv.addObject("tableList", t.addTableList(t,model));
		mv.addObject("preview");
		mv.addObject("currentTable","テーブルは未選択です");
		
		
		return "forward:admin";
	}
	
	
}
