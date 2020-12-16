package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileController extends BaseWwwController {
	
	@RequestMapping("admin/file/fileList") 
	public String fileList(
			Model model
			){
		
		
		return "file/fileList";
	}

	@RequestMapping("admin/file/fileForm") 
	public String fileForm(
			@RequestParam(value="fileId",required=false) Long fileId,
			Model model
			){
		
		
		return "file/fileEdit";
	}
}