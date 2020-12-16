package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.SystemException;

@Controller
public class SliderController extends BaseWwwController {
	
	@RequestMapping("admin/slider/sliderList") 
	public String sliderList(
			Model model
			){
		
		return "slider/sliderList";
	}

	@RequestMapping("admin/slider/sliderForm") 
	public String sliderForm(
			@RequestParam(value="id",required=false) Long id,
			Model model
			) throws SystemException{
		
		return "slider/sliderEdit";
	}
}