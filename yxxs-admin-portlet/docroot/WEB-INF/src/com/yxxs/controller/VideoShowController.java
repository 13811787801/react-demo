package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.SystemException;

@Controller
public class VideoShowController extends BaseWwwController {
	
	@RequestMapping("admin/video/videoList") 
	public String videoList(
			Model model
			){
		
		return "video/videoList";
	}

	@RequestMapping("admin/video/videoForm") 
	public String videoForm(
			@RequestParam(value="id",required=false) Long id,
			Model model
			) throws SystemException{
		
		return "video/videoEdit";
	}

	@RequestMapping("admin/video/videoKeywordList") 
	public String videoKeywordList(
			@RequestParam(value="id",required=false) Long id,
			Model model
			) throws SystemException{
		
		return "video/videoKeywordList";
	}

	@RequestMapping("admin/video/videoShareList") 
	public String videoShareList(
			@RequestParam(value="id",required=false) Long id,
			Model model
			) throws SystemException{
		
		return "video/videoShareList";
	}
}