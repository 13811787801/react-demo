package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.SystemException;

@Controller
public class AutoReplyController extends BaseWwwController {
	
	@RequestMapping("admin/autoReply/autoReplyList") 
	public String autoReplyList(
			Model model
			){
		
		return "autoReply/autoReplyList";
	}

	@RequestMapping("admin/autoReply/autoReplyForm") 
	public String autoReplyForm(
			@RequestParam(value="id",required=false) Long id,
			Model model
			) throws SystemException{
		
		return "autoReply/autoReplyEdit";
	}
	
	@RequestMapping("admin/autoReply/keywordList") 
	public String keywordList(
			Model model
			){
		
		return "autoReply/keywordList";
	}

	@RequestMapping("admin/autoReply/keywordForm") 
	public String keywordForm(
			@RequestParam(value="id",required=false) Long id,
			Model model
			) throws SystemException{
		
		return "autoReply/keywordEdit";
	}
}