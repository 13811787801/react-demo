package com.yxxs.controller.www;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Controller;

import com.yxxs.common.util.CookieUtil;
import com.yxxs.common.util.HttpUtil;
import com.yxxs.common.util.JsonUtil;
import com.yxxs.util.PropsConfig;


@Controller
public class WwwBaseController {

	
	
	/**
	 * code -> openid -> cookie.OPENID
	 * <br/>
	 * code/openid is new and not empty, refresh cookie.OPENID
	 * @return
	 */
	protected String getOpenId(String code, String openid, HttpServletRequest request, HttpServletResponse response){
		String openId = null;
		
		//set value
		if(!StringUtil.isEmpty(code)){
			openId = getOpenIdByCode(code);
		}
		if(null == openId){
			openId = openid;
		}

		String c_id = CookieUtil.getCookie(request, "WX_OPENID");
		if(null == openId){
			openId = c_id;
		}
		
		//compare with cookie, or cookie is null
		if(!StringUtil.isEmpty(openId) && ((null != c_id && !c_id.equals(openId)) || null == c_id)){
			Cookie cookie = new Cookie("WX_OPENID", openId);
			cookie.setMaxAge(1*30*24*60*60);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		
		return openId;
	}

	protected String getOpenIdByCode(String code){
		String openId = null;
		
		Date st = new Date();
		
//		String rt = HttpUtil.sendGet("http://"+PropsConfig.SOAP_SERVER_WECHAT_HOST+"/yxxs-wechat-serv-portlet/wechat/auth/getOpenId?code="+code);
//
//		Map hash = JsonUtil.Json2Map(rt);
//		if(null != hash){
//			Map entity = (Map)hash.get("entity");
//			if(null != entity){
//				openId = entity.get("openid").toString();
//			}
//		}
		
		//System.out.println("getOpenIdByCode["+code+"],openId:"+openId+",cost:"+(new Date().getTime() - st.getTime()));
		
		return openId;
	}
}