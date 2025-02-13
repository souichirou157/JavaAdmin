package com.example.demo.controller.menu.sql;	
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.app.Views;
import com.example.demo.model.sql.Core;
import com.example.demo.model.sql.Metadata.GetOperationData;
import com.example.demo.model.sql.perse.PersingException;

import jakarta.servlet.http.HttpSession;

//mvn clean install
//mvn -v
//mvn dependency:tree


@Controller
final class QueryFormController {
	GetOperationData models;
	
	@Autowired
	private HttpSession  session ;
	

	@GetMapping("/queryform")
	private ModelAndView queryformHandler(@ModelAttribute Views t,Model model,ModelAndView mv) 
	{
			
			mv.setViewName("queryform");
			
			@SuppressWarnings("uncheked")
			ArrayList<String> database = (ArrayList<String>)session.getAttribute("databases");
			String tablename = (String)session.getAttribute("tablename");
			String username = (String)session.getAttribute("currentuser");
			String dbname = (String)session.getAttribute("dbname");
			
			session.setAttribute("dbname", dbname);
			session.setAttribute("currentuser", username);
			session.setAttribute("databases", database);
			session.setAttribute("tablename", tablename);
			model.addAttribute("tableList", t.addTableList(t,model)); 
			
			mv.addObject("SelectedDB",dbname);
			mv.addObject("currentTable",tablename);
			mv.addObject("databases",database);
			mv.addObject("export","エクスポート");
			mv.addObject("queryfield","実行");
			mv.addObject("Insert","挿入");
			mv.addObject("Disp","編集");
			mv.addObject("Del","削除");
			mv.addObject("Run","実行");		
			mv.addObject("currentuser","ユーザー: "+username);
	
			
			if(Objects.isNull(username)) {
				mv.setViewName("error");
				mv.addObject("message","not found  username ");
				mv.addObject("info","戻る");
		
				return mv;
			}
			return mv;
		}
	
	@PostMapping("/query")
	private ModelAndView QueryHandler(@ModelAttribute Views t,Model model,ModelAndView mv,@RequestParam("statement") StringBuilder stmt) 
			
			throws  PersingException, IllegalAccessException, 
			    
			NoSuchFieldException, SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
		
		
		
		mv.setViewName("queryform");
		ArrayList<String> database = (ArrayList<String>)session.getAttribute("databases");
		String tablename = (String)session.getAttribute("tablename");
		String username = (String)session.getAttribute("currentuser");
		String dbname = (String)session.getAttribute("dbname");
		session.setAttribute("dbname", dbname);
		session.setAttribute("currentuser", username);
		session.setAttribute("databases", database);
		session.setAttribute("tablename", tablename);
		
		model.addAttribute("tableList", t.addTableList(t,model)); 
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentTable",tablename);
		mv.addObject("databases",database);
		mv.addObject("operable","取得が正常に終了しました");
		mv.addObject("Run","実行");
		mv.addObject("currentuser","ユーザー: "+username);
		mv.addObject("continue_or_suggest","続ける");
		mv.addObject("end_or_suggest","戻る");

		//try-catch書く場所おかしい
		//入力したステートメントヘッダー
		String stateOerder =stmt.toString().substring(0,stmt.toString().indexOf("!"));
		System.out.println(stateOerder);


		
		
		
		try {
			Core.get(core->{
					try {
					
						
						mv.addObject("Result",core.ExecuteCommand(stmt.toString()));
					} catch (SQLException | IllegalAccessException | InvocationTargetException | PersingException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
			}, stateOerder);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}	
	
	
		


		return mv;
	}
	
		

	
	@GetMapping("/query_end")
	private String continueQueryMode() {
				
		return "forward:/authority/return_to_top";	
	}
	
	
	@GetMapping("/query_continue")
	private String endQueryMode() {
		
		
		
		return "forward:/queryform";
		
	}	
	
	
}
