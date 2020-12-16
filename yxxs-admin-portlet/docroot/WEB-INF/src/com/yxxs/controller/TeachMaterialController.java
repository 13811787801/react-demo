package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.SystemException;

@Controller
public class TeachMaterialController extends BaseWwwController {
	
	@RequestMapping("admin/teachMaterial/materialList") 
	public String materialList(
			Model model
			){
		
		return "teachMaterial/materialList";
	}

	@RequestMapping("admin/teachMaterial/materialForm") 
	public String materialForm(
			@RequestParam(value="id",required=false) Long id,
			Model model
			) throws SystemException{
		
		return "teachMaterial/materialEdit";
	}

	
	@RequestMapping("admin/teachMaterial/materialGroupList") 
	public String materialGroupList(
			Model model
			){
		
		return "teachMaterial/materialGroupList";
	}

	@RequestMapping("admin/teachMaterial/materialGroupForm") 
	public String materialGroupForm(
			@RequestParam(value="id",required=false) Long id,
			Model model
			) throws SystemException{
		
		return "teachMaterial/materialGroupEdit";
	}
	
	@RequestMapping("admin/teachMaterial/materialGroup_UserList") 
	public String materialGroup_UserList(
			Model model
			){
		
		return "teachMaterial/materialGroup_UserList";
	}
	
	@RequestMapping("admin/teachMaterial/materialGroup_MaterialList") 
	public String materialGroup_MaterialList(
			Model model
			){
		
		return "teachMaterial/materialGroup_MaterialList";
	}
}