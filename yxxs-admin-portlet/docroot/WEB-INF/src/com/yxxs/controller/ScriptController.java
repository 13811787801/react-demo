package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScriptController extends BaseWwwController {
	
	@RequestMapping("js/admin/feedback.js") 
	public String feedback(
			Model model
			){
		
		return "js/admin/feedback";
	}
	@RequestMapping("js/conditionHelper.js") 
	public String conditionHelper(
			Model model
			){
		
		return "js/admin/conditionHelper";
	}
}