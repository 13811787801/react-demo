package com.yxxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController extends BaseWwwController {

	@RequestMapping("admin/message/weixinMsgTmpl") 
	public String weixinMsgTmpl(
			Model model
			){
		
		return "message/weixinMsgTmplList";
	}
	
	@RequestMapping("admin/message/sendWeixinMsg") 
	public String sendWeixinMsg(
			@RequestParam(value="tmplId") String tmplId,
			Model model
			){
		
		model.addAttribute("tmplId", tmplId);
		
		return "message/weixinMessageSend";
	}

	@RequestMapping("admin/message/weixinAdminMsgList") 
	public String weixinAdminMsgList(
			Model model
			){
		
		return "message/weixinAdminMsgList";
	}

	@RequestMapping("admin/message/weixinAdminMsgForm")
	public String weixinAdminMsgEdit(
			Model model
			){
		
		return "message/weixinAdminMsgEdit";
	}
	
	@RequestMapping("admin/message/blockMsgHistoryList") 
	public String blockMsgHistoryList(
			Model model
			) {
		
		return "message/blockMsgHistoryList";
	}
}