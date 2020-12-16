package com.yxxs.controller.www;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yxxs.common.util.CookieUtil;
import com.yxxs.util.PropsConfig;


@Controller
public class WwwCacheController extends WwwBaseController {

	@RequestMapping("/cache/resetPageTs") 
	public void resetPageTs(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model
			) throws IOException{

		PropsConfig.TIME_TS = new Date().getTime();
	}
	
}