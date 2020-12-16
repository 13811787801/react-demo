package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GroupManagerController extends BaseWwwController {

	@RequestMapping("admin/group/groupList") 
	public String groupList(
			Model model
			){
		
		return "group/groupList";
	}
	@RequestMapping("admin/group/groupForm") 
	public String groupForm(
			Model model
			){
		
		return "group/groupEdit";
	}
	@RequestMapping("admin/group/groupUserList") 
	public String groupUserList(
			Model model
			){
		
		return "group/groupUserList";
	}
	@RequestMapping("admin/group/privilegeList") 
	public String privilegeList(
			Model model
			){
		
		return "group/privilegeList";
	}
	@RequestMapping("admin/group/privilegeForm") 
	public String privilegeForm(
			Model model
			){
		
		return "group/privilegeEdit";
	}
	@RequestMapping("admin/group/groupPrivilegeForm") 
	public String groupPrivilegeForm(
			Model model
			){
		
		return "group/groupPrivilegeEdit";
	}
	@RequestMapping("admin/group/groupPrivilegeList") 
	public String groupPrivilegeList(
			Model model
			){
		
		return "group/groupPrivilegeList";
	}
	@RequestMapping("admin/group/userPrivilegeForm") 
	public String groupUserPrivilegeForm(
			Model model
			){
		
		return "group/userPrivilegeEdit";
	}
	@RequestMapping("admin/group/userPrivilegeList") 
	public String groupUserPrivilegeList(
			Model model
			){
		
		return "group/userPrivilegeList";
	}
	
}
