package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController extends BaseWwwController {

	@RequestMapping("admin/feedback/feedbackForm") 
	public String feedbackForm(
			Model model
			){
		
		return "feedback/feedbackEdit";
	}
	
	@RequestMapping("admin/feedback/feedbackList") 
	public String feedbackList(
			Model model
			){
		
		return "feedback/feedbackList";
	}
	
	@RequestMapping("admin/feedback/feedbackNotFindSchool") 
	public String feedbackNoSchool(
			@RequestParam(value="feedbackId") Long feedbackId,
			Model model
			){
		
		return "feedback/feedbackNotFindSchool";
	}

}