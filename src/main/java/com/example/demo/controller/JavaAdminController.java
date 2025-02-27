package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	private List<String> db_obj;
	private List<String> tb_obj;
	
	
	@GetMapping("/index")
	private ModelAndView render(ModelAndView mv) {
		mv.setViewName("index");
		mv.addObject("status","ユーザ情報を入力してください");
		return mv;
	}
	
	
	@PostMapping("/index/login")
	private String loginInvalidCheck(@RequestParam("name") String username,@RequestParam("password") String pass 
			,@ModelAttribute Views t,Model model,ModelAndView mv) 
					throws IllegalArgumentException{
		
		try {
			
			if(javaadminservice.findUser(username,pass))  {
				session.invalidate();
			
				String currentuser = (String)session.getAttribute("currentuser");	
				currentuser = username;
				session.setAttribute("currentuser",currentuser);
				db_obj = javaadminservice.getDatabaseList(session);
				
		
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
	
	@GetMapping("/use_db") //ログイン後にここに入る
	private String  showtablehandle(@RequestParam("data_object") String dbname,RedirectAttributes redirectAttributes,
			@ModelAttribute Views t,Model model,String db,ModelAndView mv) {
			
		mv.setViewName("admin");
		String username = (String)session.getAttribute("currentuser");
		
		//sessionからdbオブジェクトとログインしたユーザをすぐ取り出せるようにしておく
		session.setAttribute("currentuser", username);
		session.setAttribute("databases",db_obj);
		
		mv.addObject("tables","データベース");
		mv.addObject("struct","構造です");
		mv.addObject("databases",db_obj);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("ItemMessage","チェックを");
		redirectAttributes.addFlashAttribute("dbname",dbname);
	

		return "redirect:/use_db/showtable";
	}
	@GetMapping("/use_db/showtable") //データベース名クリックするとテーブル一覧表示
	private ModelAndView showtableHandler(@ModelAttribute("dbname") String dbname,
			RedirectAttributes redirectAttributes,	ModelAndView mv,Model model) {
		
		mv.setViewName("admin");
		String username = (String)session.getAttribute("currentuser");
	
     	dbname = (String) session.getAttribute("dbname"); 
     	dbname = (String) model.getAttribute("dbname");
		System.out.println("データベース名テスト");
		
		tb_obj = javaadminservice.getTableList(session,dbname);
		session.setAttribute("table",tb_obj);
		
		session.setAttribute("currentuser", username);
		session.setAttribute("databases",db_obj);
		session.setAttribute("dbname",dbname);
	
	
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("struct","構造");
		mv.addObject("use_data_object",dbname);
		mv.addObject("tablename","テーブル");
		mv.addObject("tables",tb_obj);
		mv.addObject("table_list",tb_obj);
		mv.addObject("tablelist_item","テーブル名");
		mv.addObject("struct_item","構造");
		mv.addObject("display_item","表示");
		mv.addObject("view_t","取得");
		redirectAttributes.addFlashAttribute("dbname",dbname);
		return  mv;
	}
	

	@GetMapping("/use_db/showtable/tb/table_name")
	private ModelAndView viewTableHandler(@ModelAttribute("dbname") String dbname,
	@RequestParam (name="t_view")	String table,	ModelAndView mv,Model model) {
		
		mv.setViewName("admin");
		String username = (String)session.getAttribute("currentuser");
	
     	dbname = (String) session.getAttribute("dbname");
		dbname = (String) model.getAttribute("dbname");
		
		session.setAttribute("currentuser", username);
		session.setAttribute("databases",db_obj);
		session.setAttribute("dbname",dbname);
		session.setAttribute("tablename", table);
		
		mv.addObject("SelectedDB",dbname);
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("struct","構造");
		mv.addObject("use_data_object",dbname);
		mv.addObject("tablename","テーブル");
		mv.addObject("table_list",tb_obj);
		mv.addObject("preview",javaadminservice.findAll(table));
		mv.addObject("currentTable",table);
		return  mv;
	}
	
	
	
	@GetMapping("/tb")
	private String  BranchHandler(@RequestParam("table") String tablename,RedirectAttributes redirectAttributes,
			ModelAndView mv,Model model) {
	
		mv.setViewName("admin");
		
		String username = (String) session.getAttribute("currentuser");
		String dbname = (String)session.getAttribute("dbname");
		session.setAttribute("currentuser", username);
		session.setAttribute("databases", db_obj);
		session.setAttribute("dbname", dbname);
		session.setAttribute("tablename", tablename);
		redirectAttributes.addFlashAttribute("dbname",dbname);
		redirectAttributes.addFlashAttribute("tablename",tablename);
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("operable","SQL");

		
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("Server");
		mv.addObject("struct","構造");
	
		return  "redirect:/use_db/showtable/tableschema";
	}
	
	//テーブル表示
	@GetMapping("/use_db/showtable/tableschema")
	public ModelAndView tableHandler(@ModelAttribute Views t,
			@ModelAttribute("tablename") String tablename,ModelAndView mv,Model model) {
		
		mv.setViewName("admin");
		
		String username = (String) session.getAttribute("currentuser");
		tablename = (String)session.getAttribute("tablename");
		String dbname = (String)session.getAttribute("dbname");
		
		session.setAttribute("dbname", dbname);
		session.setAttribute("databases", db_obj);
		session.setAttribute("currentuser", username);
		session.setAttribute("tablename", tablename);
		
		mv.addObject("struct","構造");
		mv.addObject("Insert","挿入");
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("currentTable",tablename);
		mv.addObject("use_data_object",tablename);
		mv.addObject("tablename","テーブル");
		mv.addObject("table_list",tb_obj);
		mv.addObject("preview",javaadminservice.findAll(tablename));
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("export","エクスポート");
		mv.addObject("ItemMessage","チェックを");
		mv.addObject("Server");
		
		
		return mv;
	}


	
}

//	
//	
//}
