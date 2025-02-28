
package com.example.demo.controller;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.container.export.FileFormat;
import com.example.demo.model.app.Views;
import com.example.demo.model.sql.option.Encoding;
import com.example.demo.service.JavaAdminService;

import jakarta.servlet.http.HttpSession;


@Controller
public final class ExportController {
	
	@Autowired
	private JavaAdminService javaadminservice;	
	@Autowired
	HttpSession session;
		
	@GetMapping("/exportData")
	protected ModelAndView ExportHandler(Model model,@ModelAttribute Views t,ModelAndView mv) {
		 
		mv.setViewName("export");
		
		String username = (String)session.getAttribute("currentuser");
		String tablename = (String)session.getAttribute("tablename");
		
		session.setAttribute("databases",javaadminservice.getDatabaseList(session));
		session.setAttribute("currentuser", username);
		session.setAttribute("tablename", tablename);


		mv.addObject("databases",javaadminservice.getDatabaseList(session));
		mv.addObject("export","エクスポート");
		mv.addObject("Insert","挿入");
		mv.addObject("Disp","編集");
		mv.addObject("Del","削除");
		mv.addObject("currentuser","ユーザー: "+username);
		mv.addObject("currentTable",tablename);
		mv.addObject("currentmessage",tablename+"をエクスポート");
		mv.addObject("format_length",(FileFormat.getformat().length/FileFormat.getformat().length));
		mv.addObject("DataFormat",FileFormat.getformat());
		mv.addObject("title","エクスポート");
		mv.addObject("format_length",(Encoding.getEncoding().length/Encoding.getEncoding().length));
		mv.addObject("Encoding",Encoding.getEncoding());
		
	
		

		if(Objects.isNull(username)) {
			mv.setViewName("error");
			mv.addObject("message","not found  username ");
			mv.addObject("info","戻る");
	
			return mv;
		}
		
				
		
		return  mv;
	}
	
	@GetMapping("/export")
	private ModelAndView ResultExport(@RequestParam("format_item") FileFormat extension,@RequestParam("encoding_item") String encode,ModelAndView mv) {
		
		mv.setViewName("export");
		String username = (String)session.getAttribute("currentuser");
		String tablename = (String)session.getAttribute("tablename");

		session.setAttribute("currentuser", username);
		session.setAttribute("tablename", tablename);
		
		
		mv.addObject("export","エクスポート");
		mv.addObject("Insert","挿入");
		mv.addObject("Disp","編集");
		mv.addObject("Del","削除");
		mv.addObject("currentuser","ユーザー: "+username);
		mv.addObject("currentTable",tablename);
		mv.addObject("currentmessage","エクスポートが完了しました");
		mv.addObject("format_length",(FileFormat.getformat().length/FileFormat.getformat().length));
		mv.addObject("DataFormat",FileFormat.getformat());
		
		mv.addObject("format_length",(Encoding.getEncoding().length/Encoding.getEncoding().length));
		mv.addObject("Encoding",Encoding.getEncoding());
		mv.addObject("continue","続ける");
		mv.addObject("end","トップページに戻る");
		mv.addObject("title","エクスポート");
		
		javaadminservice.ExportDataFormat(tablename,extension,encode.toLowerCase());
		mv.addObject("loder",null);
		//"C:/Users/cl05/Desktop/"+ExportClient.getfilepath()
		
		return mv;
	}
	
	
	
	@GetMapping("/export_continue")
	private String  onMoreExport() {
		
		return "forward:exportData";
	}
	@GetMapping("/export_end")
	private String returnToAdminpage() {
		
		return "forward:/authority/return_to_top";
	}
	
	
	
	

}
