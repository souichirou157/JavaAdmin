package com.example.demo.controller.menu;

import static com.example.demo.model.app.service.TableOption.*;
import static com.example.demo.model.sql.views.Delete.*;
import static com.example.demo.model.sql.views.Disp.*;
import static com.example.demo.model.sql.views.InsertForm.*;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.controller.Session;
import com.example.demo.model.app.Views;
import com.example.demo.model.sql.Metadata.reservedwords.FOR_STATEMENTS;

import jakarta.servlet.http.HttpSession;

@Controller
public final class ResultController {
	
	@Autowired
	private HttpSession  session ;
	
	
	@GetMapping("/select_operation_query")
	private String operationHandler(ModelAndView mv,@RequestParam("operation_item") FOR_STATEMENTS item) {
		
		try {

			switch(item) {
			case FOR_STATEMENTS.DELETE:return "forward:/Operation/delete"; 
			case FOR_STATEMENTS.UPDATE:return "forward:/Operation/Disp";
			case FOR_STATEMENTS.INSERT:return "forward:/Operation/Insert";
	
			default: return "forward:/authority/return_to_top";
			
			}
		}catch(NullPointerException ne) {
			
			ne.printStackTrace();
			return null;
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/ConsentQuery")
	private String deletehandler(ModelAndView mv,@ModelAttribute Views t,Model model,@RequestParam("deleteuser") String ...Id) {

		mv.setViewName("delete");
		mv.addObject("selecttable",Session.getTablename());	
		mv.addObject("result",(ArrayList<String>) mv.getModel().get("value"));
		
		String tablename = (String)session.getAttribute("tablename");
		session.setAttribute("tablename", tablename);

		SelectUserId(tablename,Id);
		
		return  "forward:/authority/return_to_top";
	}
	
	
	
	
	@GetMapping("/insert")
	private String  ResultInsert(ModelAndView mv,@RequestParam("value") Object ...value) {
		
		mv.setViewName("insert");
		String tablename = (String)session.getAttribute("tablename");
		session.setAttribute("tablename", tablename);

		addData(tablename,value);	
	
		return  "forward:/authority/return_to_top";

	}
	
	
	@GetMapping("/disp")
	private String ResultDisp(@RequestParam("values") Object ...values) {
		
		String tablename = (String)session.getAttribute("tablename");
		session.setAttribute("tablename", tablename);

		System.out.println(tablename);
		updateData(tablename,values);
		
		
		
		return "forward:/authority/return_to_top";
		
	}
	
	
	@GetMapping("/ResetSerialnumber")
	private String SerialnumberHandler() {
	
		String tablename = (String)session.getAttribute("tablename");
		session.setAttribute("tablename", tablename);

		SerialNumberReset(tablename);
	
		return "forward:/authority/return_to_top";
	}
	
	
	
}