package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.SystemException;

@Controller
public class AdvShowController extends BaseWwwController {
	
	@RequestMapping("admin/adv/advList") 
	public String advList(
			Model model
			){
		
		return "adv/advList";
	}

	@RequestMapping("admin/adv/advForm") 
	public String advForm(
			@RequestParam(value="id",required=false) Long id,
			Model model
			) throws SystemException{
		
		return "adv/advEdit";
	}
}