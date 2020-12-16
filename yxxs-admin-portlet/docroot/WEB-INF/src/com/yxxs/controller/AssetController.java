package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AssetController extends BaseWwwController {

	@RequestMapping("admin/asset/crossForm") 
	public String crossForm(
			Model model
			){

		return "asset/crossEdit";
	}
}