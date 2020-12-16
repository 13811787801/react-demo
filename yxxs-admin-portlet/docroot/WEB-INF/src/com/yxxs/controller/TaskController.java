package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.SystemException;

@Controller
public class TaskController extends BaseWwwController {
	
	@RequestMapping("admin/quartz/taskList") 
	public String taskList(
			Model model
			){
		
		return "quartz/taskList";
	}

	@RequestMapping("admin/quartz/taskForm") 
	public String taskForm(
			@RequestParam(value="taskId",required=false) Long taskId,
			Model model
			) throws SystemException{
		
		return "quartz/taskEdit";
	}
	
}