package com.yxxs.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.yxxs.common.controller.BaseController;


@Controller
public abstract class BaseWwwController  extends BaseController{
	
	public String dealError(
			Exception ex,
			Model model){
		
		model.addAttribute("ex", ex);
		
		return "commonError";
	}

	public String commonSuccess(
			HashMap<String,String> linkHash,
			String message,
			Model model){
		
		model.addAttribute("linkHash", linkHash);
		model.addAttribute("message", message);
		
		return "commonSuccess";
	}
}
