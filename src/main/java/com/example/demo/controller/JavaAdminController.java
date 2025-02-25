package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.controller.service.JavaAdminService;
import com.example.demo.model.app.Views;
import com.example.demo.model.sql.Core;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class JavaAdminController {

	
	private final JavaAdminService javaadminservice;
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
	
	@GetMapping("/db")
	private String  showtablehandle(@RequestParam("database") String dbname,RedirectAttributes redirectAttributes,
			@ModelAttribute Views t,Model model,String db,ModelAndView mv) {
			
		mv.setViewName("admin");
		String username = (String)session.getAttribute("currentuser");
		
		session.setAttribute("currentuser", username);
		session.setAttribute("databases",javaadminservice.getDatabaseList(session));
		
		
		mv.addObject("Struct","構造");
		mv.addObject("databases",javaadminservice.getDatabaseList(session));
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("ItemMessage","チェックを");
	
		redirectAttributes.addFlashAttribute("dbname",dbname);
	

		return "redirect:/db/showtable";
	}
	
	

	@GetMapping("/db/showtable")
	private ModelAndView showtableHandler(@ModelAttribute("dbname") String dbname,
			ModelAndView mv,Model model) {
		
		mv.setViewName("admin");
		String username = (String)session.getAttribute("currentuser");
	
     	dbname = (String) session.getAttribute("dbname");
		dbname = (String) model.getAttribute("dbname");
		
		session.setAttribute("currentuser", username);
		session.setAttribute("databases", javaadminservice.getDatabaseList(session));
		session.setAttribute("dbname",dbname);
		
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("SelectedDB",dbname);
		mv.addObject("databases",javaadminservice.getDatabaseList(session));
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("test",dbname);
		mv.addObject("Struct","構造");
	
		List<String> tables = javaadminservice.getTableList(session,dbname);
		session.setAttribute("table",tables);
		mv.addObject("TableList",tables);
			
		
		return  mv;
	}
	
	@GetMapping("/tb")
	private String  BranchHandler(@RequestParam("table") String tablename,RedirectAttributes redirectAttributes,ModelAndView mv,Model model) {
	
		mv.setViewName("admin");
		
		String username = (String) session.getAttribute("currentuser");
		String dbname = (String)session.getAttribute("dbname");
		session.setAttribute("currentuser", username);
		session.setAttribute("databases", javaadminservice.getDatabaseList(session));
		session.setAttribute("dbname", dbname);
		redirectAttributes.addFlashAttribute("tablename",tablename);
		
		mv.addObject("SelectedDB",dbname);
		mv.addObject("databases",javaadminservice.getDatabaseList(session));
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("operable","SQL");
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("Server");
		
		
		return  "redirect:/db/showtable/tableschema";
	}
	
	@GetMapping("/db/showtable/tableschema")
	public ModelAndView tableHandler(@ModelAttribute Views t,@ModelAttribute("tablename") String tablename,ModelAndView mv,Model model) {
		
		mv.setViewName("admin");
		
		String username = (String) session.getAttribute("currentuser");
		tablename = (String)session.getAttribute("tablename");
		String dbname = (String)session.getAttribute("dbname");
		tablename = (String)model.getAttribute("tablename");
		
		session.setAttribute("dbname", dbname);
		session.setAttribute("databases", javaadminservice.getDatabaseList(session));
		session.setAttribute("currentuser", username);
		session.setAttribute("tablename", tablename);
		mv.addObject("struct","構造");
		mv.addObject("Insert","挿入");
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("databases",javaadminservice.getDatabaseList(session));
		mv.addObject("preview",javaadminservice.findAll(tablename));
		mv.addObject("test",tablename);
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("currentTable",tablename);
		mv.addObject("export","エクスポート");
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("Server");
		
		
		return mv;
	}


	
}

//	
//	
//}
