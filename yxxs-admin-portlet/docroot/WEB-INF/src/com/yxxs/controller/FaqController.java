package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.SystemException;

@Controller
public class FaqController extends BaseWwwController {
	
	@RequestMapping("admin/faq/faqList") 
	public String faqList(
			Model model
			){
		
		return "faq/faqList";
	}

	@RequestMapping("admin/faq/faqForm") 
	public String faqForm(
			@RequestParam(value="id",required=false) Long id,
			Model model
			) throws SystemException{
		
		return "faq/faqEdit";
	}
}