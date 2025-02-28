package com.example.demo.controller;	
import static com.example.demo.container.operation.query.Disp.*;
import static com.example.demo.container.operation.query.Disp.setForm;
import static com.example.demo.container.operation.query.Insert.*;
import static com.example.demo.container.operation.query.Insert.setForm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.container.export.FileFormat;
import com.example.demo.model.app.Views;
import com.example.demo.model.sql.Core;
import com.example.demo.model.sql.Metadata.reservedwords.FOR_STATEMENTS;
import com.example.demo.model.sql.option.Encoding;
import com.example.demo.model.sql.perse.PersingException;
import com.example.demo.service.JavaAdminService;

import jakarta.servlet.http.HttpSession;

//mvn clean install
//mvn -v
//mvn dependency:tree


@Controller
final class QueryFormController {
	@Autowired
	private HttpSession  session ;
	
	@Autowired
	private  JavaAdminService javaAdminservice;
	
	private List<String> db_obj;
	private List<Views> tb_obj;
	@GetMapping("/queryform")
	private ModelAndView queryformHandler(@ModelAttribute Views t,Model model,ModelAndView mv) 
	{
			
			mv.setViewName("queryform");
			
			String tablename = (String)session.getAttribute("tablename");
			String username = (String)session.getAttribute("currentuser");
			String dbname = (String)session.getAttribute("dbname");
			db_obj = javaAdminservice.getDatabaseList(session);
			tb_obj = javaAdminservice.addTableList(t,model);
			session.setAttribute("dbname", dbname);
			session.setAttribute("currentuser", username);
			session.setAttribute("databases",db_obj);
			session.setAttribute("tablename", tablename);
			model.addAttribute("tableList",tb_obj); 
			
			mv.addObject("SelectedDB",dbname);
			mv.addObject("currentTable",tablename);
			mv.addObject("databases",db_obj);
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
		String tablename = (String)session.getAttribute("tablename");
		String username = (String)session.getAttribute("currentuser");
		String dbname = (String)session.getAttribute("dbname");
		session.setAttribute("dbname", dbname);
		session.setAttribute("currentuser", username);
		session.setAttribute("databases",db_obj);
		session.setAttribute("tablename", tablename);
		
		model.addAttribute("tableList", tb_obj); 
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentTable",tablename);
		mv.addObject("databases",db_obj);
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
	
	
	@GetMapping("/select_operation_query")
	private String operationHandler(ModelAndView mv,@RequestParam("operation_item") FOR_STATEMENTS item) {
		
		try {

			switch(item) {
			case FOR_STATEMENTS.DELETE:return "forward:/Operation/Delete"; 
			case FOR_STATEMENTS.UPDATE:return "forward:/Operation/Disp";
			case FOR_STATEMENTS.INSERT:return "forward:/Operation/Insert";
	
			default: return "forward:/authority/return_to_top";
			
			}
		}catch(NullPointerException ne) {
			
			ne.printStackTrace();
			return null;
		}
		
		
	}
	
		
	
	

	@GetMapping("Operation/Delete")
	private  ModelAndView handler(ModelAndView mv,@ModelAttribute Views t,
			Model model,@RequestParam("Id") int ...Id) {
		
		
		mv.setViewName("delete");
		
		//テーブル
		String tablename = (String)session.getAttribute("tablename");
		
		session.setAttribute("tablename", tablename);
		session.setAttribute("databases", db_obj);
		
		mv.addObject("databases",db_obj);
		mv.addObject("selecttable",tablename);	
		mv.addObject("checkuserID",javaAdminservice.getId(tablename,Id).toString());
		
		mv.addObject("value",Id);
			
		
	
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/delete")
	private String deletehandler(ModelAndView mv,@ModelAttribute Views t,Model model,@RequestParam("deleteuser") String ...Id) {

		mv.setViewName("delete");
		mv.addObject("selecttable",Session.getTablename());	
		mv.addObject("result",(ArrayList<String>) mv.getModel().get("value"));
		
		String tablename = (String)session.getAttribute("tablename");
		session.setAttribute("tablename", tablename);

		javaAdminservice.deleteData(tablename,Id);
		
		return  "forward:/authority/return_to_top";
	}
	
	

		

	@GetMapping("Operation/Disp")
	private ModelAndView dispHanler(ModelAndView  mv,@ModelAttribute Views t,@RequestParam("Id")String ...Id) {
		mv.setViewName("disp");
		
		String username = (String) session.getAttribute("currentuser");
		String tablename = (String)session.getAttribute("tablename");
		
		session.setAttribute("databases", db_obj);
		session.setAttribute("currentuser", username);
		session.setAttribute("tablename",tablename);
		

		mv.addObject("databases",db_obj);
		mv.addObject("inputField",setForm(tablename,Id).toString());
		mv.addObject("currentTable",tablename);
		mv.addObject("currentuser","ユーザー: "+username);
		return mv;
	}
	
	@GetMapping("/disp")
	private String ResultDisp(@RequestParam("values") Object ...values) {
		
		String tablename = (String)session.getAttribute("tablename");
		session.setAttribute("tablename", tablename);

		System.out.println(tablename);
		updateData(tablename,values);
		
		
		
		return "forward:/authority/return_to_top";
		
	}


	
	@GetMapping("Operation/Insert")
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
	
	

	@GetMapping("/insert")
	private String  ResultInsert(ModelAndView mv,@RequestParam("value") Object ...value) {
		
		mv.setViewName("insert");
		String tablename = (String)session.getAttribute("tablename");
		session.setAttribute("tablename", tablename);

		addData(tablename,value);	
	
		return  "forward:/authority/return_to_top";

	}


	
	@GetMapping("/Struct")
	public ModelAndView tableDescHandler(ModelAndView mv,Model model) {
		
		
		mv.setViewName("admin");

		String tablename = (String)session.getAttribute("tablename");
		String username = (String) session.getAttribute("currentuser");
		String dbname = (String)session.getAttribute("dbname");
	
		session.setAttribute("databases", db_obj);
		session.setAttribute("currentuser", username);
		session.setAttribute("tablename", tablename);
		session.setAttribute("dbname", dbname);
		
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("databases",db_obj);
		mv.addObject("preview",javaAdminservice.showSchema(tablename));
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("currentTable",tablename);
		mv.addObject("export","エクスポート");
		mv.addObject("ItemMessage","チェックを");

		
		
		
		
		return mv;
	}
	


	
	@GetMapping("/Struct/s_t")
	public ModelAndView tableDescHandler(ModelAndView mv,Model model,
			@RequestParam(name = "struct_t") String struct) {
		
		
		mv.setViewName("admin");

		String tablename = (String)session.getAttribute("tablename");
		String username = (String) session.getAttribute("currentuser");
		String dbname = (String)session.getAttribute("dbname");
	
		session.setAttribute("databases",db_obj);
		session.setAttribute("currentuser", username);
		session.setAttribute("tablename", tablename);
		session.setAttribute("dbname", dbname);
		
		
		mv.addObject("SelectedDB",dbname);
		mv.addObject("currentuser","ユーザー\n"+username);
		mv.addObject("databases",db_obj);
		System.out.println(struct);
		mv.addObject("preview",javaAdminservice.showSchema(struct));
		mv.addObject("operable","SQL");
		mv.addObject("item",Core.getDMLOrders());
		mv.addObject("currentTable",tablename);
		mv.addObject("export","エクスポート");
		mv.addObject("ItemMessage","チェックを");

		
		
		
		
		return mv;
	}

	
	
	@GetMapping("/ResetSerialnumber")
	private String SerialnumberHandler() {
	
		String tablename = (String)session.getAttribute("tablename");
		session.setAttribute("tablename", tablename);

		javaAdminservice.SerialNumberReset(tablename);
	
		return "forward:/authority/return_to_top";
	}
	
	@GetMapping("/CreateTable")
	public void CreateTablehandler() {
		
		return ;
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
