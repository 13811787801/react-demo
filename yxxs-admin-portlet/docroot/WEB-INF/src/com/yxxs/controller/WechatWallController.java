package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WechatWallController extends BaseWwwController {
	
	@RequestMapping("admin/wall/wallList") 
	public String wallList(
			Model model
			){
		
		return "wechatwall/wallList";
	}
	
	@RequestMapping("admin/wall/wallForm") 
	public String wallForm(
			Model model
			){
		
		return "wechatwall/wallEdit";
	}
	
	@RequestMapping("admin/wall/wallShow") 
	public String wallShow(
			Model model
			){
		
		return "wechatwall/wall";
	}

}