package com.example.demo.controller;

import static com.example.demo.model.sql.views.InsertForm.*;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.app.Views;
import com.example.demo.model.app.service.export.FileFormat;
import com.example.demo.model.sql.option.Encoding;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Operation")
public final class InsertContoller {
	
	@Autowired
	private HttpSession  session ;
	
	
	@GetMapping("/Insert")
	private ModelAndView  InsertHandler(ModelAndView mv,@ModelAttribute Views t,Model model) {
			
		mv.setViewName("insert");
		String username = (String)session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		String tablename = (String)session.getAttribute("tablename");
		session.setAttribute("tablename", tablename);
		
		mv.addObject("export","エクスポート");
		mv.addObject("Insert","挿入");
		mv.addObject("Disp","編集");
		mv.addObject("Del","削除");
		mv.addObject("currentuser","ユーザー: "+username);
		mv.addObject("currentTable",tablename);
		mv.addObject("format_length",(FileFormat.getformat().length/FileFormat.getformat().length));
		mv.addObject("DataFormat",FileFormat.getformat());
		mv.addObject("title","実行");
		mv.addObject("format_length",(Encoding.getEncoding().length/Encoding.getEncoding().length));
		mv.addObject("Encoding",Encoding.getEncoding());
		mv.addObject("insertForm",setForm(tablename));
		
		StringBuilder  sb = new StringBuilder(0);
		
		sb.append("	<input id=\"insert_submit\"   type=\"submit\" th:value=\"${title}\" style=\"margin: 27px; text-align: right; border-radius:90px; background-color: darkcyan;color:rgb(0, 255, 0);\">\n");
		mv.addObject("insert_submit",sb.toString());
		
		if(Objects.isNull(username)) {
			mv.setViewName("error");
			mv.addObject("message","not found  username ");
			mv.addObject("info","戻る");
	
			return mv;
		}
		
		
		return  mv;
	}
	
}
