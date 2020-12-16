package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActionController extends BaseWwwController {

//	@RequestMapping("admin/feedback/feedbackForm") 
//	public String feedbackForm(
//			Model model
//			){
//		
//		return "feedback/feedbackEdit";
//	}
	
	@RequestMapping("admin/action/actionList") 
	public String actionList(
			Model model
			){
		
		return "action/actionList";
	}
	
	@RequestMapping("admin/action/actionForm") 
	public String actionForm(
			Model model
			){
		
		return "action/actionEdit";
	}
	@RequestMapping("admin/action/actionUserList") 
	public String actionUserList(
			Model model
			){
		
		return "action/actionUserList";
	}
	@RequestMapping("admin/action/actionUserForm") 
	public String actionUserForm(
			Model model
			){
		
		return "action/actionUserEdit";
	}
	
//	@RequestMapping("admin/feedback/feedbackNotFindSchool") 
//	public String feedbackNoSchool(
//			@RequestParam(value="feedbackId") Long feedbackId,
//			Model model
//			){
//		
//		return "feedback/feedbackNotFindSchool";
//	}

}