package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServInfoController extends BaseWwwController {
	
	@RequestMapping("admin/server/servInfo") 
	public String servInfo(
			Model model
			){
		
		return "serv/servInfo";
	}
}