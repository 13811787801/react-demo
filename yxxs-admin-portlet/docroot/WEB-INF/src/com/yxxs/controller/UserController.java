package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController extends BaseWwwController {
	
	@RequestMapping("admin/user/userList") 
	public String userList(
			@RequestParam(value="sEmail",required=false) String sEmail,
			Model model
			){
		
		return "user/userList";
	}

	@RequestMapping("admin/user/userForm") 
	public String userForm(
			@RequestParam(value="userId",required=false) Long userId,
			Model model
			){
		return "user/userEdit";
	}

	@RequestMapping("admin/user/adminList") 
	public String adminList(
			Model model
			){
		
		return "user/adminList";
	}
	
	
	@RequestMapping("admin/user/observeUserList") 
	public String observeUserList(
			Model model
			){
		
		return "user/observeUserList";
	}
	
	@RequestMapping("admin/user/wechatUserInfoList") 
	public String wechatUserInfo(
			Model model
			){
		
		return "user/wechatUserInfoList";
	}
	


	@RequestMapping("admin/user/expertForm") 
	public String expertForm(
			Model model
			){
		
		return "user/expertEdit";
	}
	
	@RequestMapping("admin/user/expertList") 
	public String expertList(
			Model model
			){
		
		return "user/expertList";
	}


	@RequestMapping("admin/user/onlineUserList") 
	public String onlineUserList(
			Model model
			){
		
		return "user/onlineUserList";
	}
}