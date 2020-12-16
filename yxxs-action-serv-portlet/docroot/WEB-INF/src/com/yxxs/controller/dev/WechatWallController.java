package com.yxxs.controller.dev;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.yxxs.NoSuchYxxsActionException;
import com.yxxs.NoSuchYxxsWechatWallException;
import com.yxxs.common.dto.PagerInfo;
import com.yxxs.common.dto.PagerResult;
import com.yxxs.common.util.DateUtil;
import com.yxxs.common.util.JsonUtil;
import com.yxxs.model.YxxsAction;
import com.yxxs.model.YxxsActionDealLog;
import com.yxxs.model.YxxsWechatWall;
import com.yxxs.service.YxxsActionDealLogLocalServiceUtil;
import com.yxxs.service.YxxsActionLocalServiceUtil;
import com.yxxs.service.YxxsWechatWallLocalServiceUtil;
import com.yxxs.service.YxxsWechatWallServiceUtil;
import com.yxxs.service.persistence.YxxsActionUtil;
import com.yxxs.service.persistence.YxxsWechatWallUtil;

@Controller
@RequestMapping("/wechatwall/*")
public class WechatWallController extends BaseDevController {
	
	@RequestMapping("api/create")
	public @ResponseBody HashMap<String ,Object> createWall(
			@RequestParam(value="actionId",required=false,defaultValue="-1") Long actionId,
			@RequestParam(value="appKey") String appKey,
			@RequestParam(value="content") String content,
			@RequestParam(value="userId",required=false) Long userId,
			@RequestParam(value="openId",required=false) String openId,
			@RequestParam(value="createDate",required=false) Date createDate,
			@RequestParam(value="showFlag",required=false) Integer showFlag,
			@RequestParam(value="msgType",required=false) Integer msgType,
			@RequestParam(value="toId",required=false) Long toId,
			@RequestParam(value="contentType",required=false) Integer contentType,
			HttpServletRequest request,
			HttpServletResponse response
			) throws SystemException{
		HashMap hash = new HashMap();
		long id = CounterLocalServiceUtil.increment(YxxsWechatWall.class.getName());
		YxxsWechatWall wall = YxxsWechatWallLocalServiceUtil.createYxxsWechatWall(id);
		wall.setAppKey(appKey);
		wall.setActionId(actionId);
		wall.setContent(content);
		wall.setUserId(null != userId?userId:-1);
		wall.setOpenId(null != openId?openId:"");
		wall.setCreateDate(null != createDate?createDate:new Date());
		wall.setMsgType(null != msgType?msgType:1);
		wall.setToId(null != toId?toId:id);
		wall.setContentType(null != contentType?contentType:1);
		
		wall.setShowFlag(null != showFlag?showFlag:1);
		
		YxxsWechatWall t = YxxsWechatWallLocalServiceUtil.addYxxsWechatWall(wall);
		if(null != t){
			hash.put(t.getId(), t);
			hash.put("Msg", "OK");
			hash.put("Key", "true");
		}else{
			hash.put("Msg", "");
			hash.put("Key", "false");
		}
		
		return hash;
	}
	
	@RequestMapping("api/get_{pk}")
	public @ResponseBody HashMap getAction(
			@PathVariable(value="pk") Long pk
			) throws NoSuchYxxsWechatWallException{
		YxxsWechatWall wall = YxxsWechatWallUtil.findByPrimaryKey(pk);
		return wall.toHash();
	}
	@RequestMapping("api/search")
	public @ResponseBody PagerResult<List<YxxsWechatWall>> getAll(
			@RequestParam(value="actionId",required=false) Long actionId,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="appKey",required=false) String appKey,
			@RequestParam(value="userId",required=false) Long userId,
			@RequestParam(value="openId",required=false) String openId,
			@RequestParam(value="startDate",required=false) Date startDate,
			@RequestParam(value="endDate",required=false) Date endDate,
			@RequestParam(value="showFlag",required=false) Integer showFlag,
			@RequestParam(value="msgType",required=false) Integer msgType,
			@RequestParam(value="startId",required=false) Integer startId,
			@RequestParam(value="toId",required=false) Long toId,
			@RequestParam(value="contentType",required=false) Integer contentType,
			@RequestParam(value="sort",required=false) Integer sort,
			@RequestParam(value="page") Integer page,
			@RequestParam(value="pageSize") Integer pageSize,
			HttpServletRequest request,
			HttpServletResponse response
			){
		PagerResult<List<YxxsWechatWall>> rt = new PagerResult<List<YxxsWechatWall>>(
				new PagerInfo().setPageNumber(page).setPageSize(pageSize)
			);
		List<Object> params = new ArrayList<Object>();
		String condition = "1= 1";
		String sortDes = " id_ desc ";
		if(null != sort){
//			switch(sort){
//				case 1 :  sortDes = " id_ asc";
//			}
			if(sort == 1){
				sortDes = " id_ asc";
			}
			
		}
		if(null != appKey){
			condition += " and appKey = ? ";
			params.add(appKey);
		}
		if(null != startId){
			condition += " and id_ > ? ";
			params.add(startId);
		}
		if(null != actionId){
			condition += " and actionId = ? ";
			params.add(actionId);
		}
		if(null != content){
			condition += " and content like concat('%',?,'%') ";
			params.add(content);
		}
		if(null != userId){
			condition += " and userId = ? ";
			params.add(userId.toString());
		}
		if(null != openId){
			condition += " and openId = ? ";
			params.add(openId);
		}
		if(null != startDate){
			condition += " and createDate >= ? ";
			params.add(DateUtil.getFormatDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		}
		if(null != endDate){
			condition += " and createDate < ? ";
			params.add(DateUtil.getFormatDate(endDate, "yyyy-MM-dd HH:mm:ss"));
		}
		if(null != showFlag){
			condition += " and showFlag = ? ";
			params.add(showFlag);
		}
		if(null != msgType){
			condition += " and msgType = ? ";
			params.add(msgType);
		}
		if(null != toId){
			condition += " and toId = ? ";
			params.add(toId);
		}
		if(null != contentType){
			condition += " and contentType = ? ";
			params.add(contentType);
		}
		List<YxxsWechatWall> l = YxxsWechatWallUtil.findByCnd(condition, params.toArray(new Object[params.size()]),sortDes, page*pageSize, (page+1)*pageSize);
		String countCondition = "1= 1";
		params = new ArrayList<Object>();
		if(null != appKey){
			countCondition += " and appKey = ? ";
			params.add(appKey);
		}
		if(null != actionId){
			countCondition += " and actionId = ? ";
			params.add(actionId);
		}
		if(null != showFlag){
			countCondition += " and showFlag = ? ";
			params.add(showFlag);
		}
		if(null != startDate){
			countCondition += " and createDate >= ? ";
			params.add(DateUtil.getFormatDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		}
		if(null != endDate){
			countCondition += " and createDate < ? ";
			params.add(DateUtil.getFormatDate(endDate, "yyyy-MM-dd HH:mm:ss"));
		}
		int cnt = YxxsWechatWallUtil.count(countCondition, params.toArray(new Object[params.size()]));
//		if(null != showFlag && showFlag.equals(1)){
//			cnt = l.size();
//		}else{
//			cnt = YxxsWechatWallUtil.countAll();
//		}
		
		rt.setObj(l);
		rt.setTotalCount(cnt);
		return rt;
	}
	@RequestMapping("api/del")
	public @ResponseBody PagerResult<List<YxxsWechatWall>> del(
			@RequestParam(value="ids") String ids,
			HttpServletRequest request,
			HttpServletResponse response
			){
		String[] idArray = ids != null ?ids.split(","):null;
		PagerResult<List<YxxsWechatWall>> rt = new PagerResult<List<YxxsWechatWall>>(
				new PagerInfo().setPageNumber(0).setPageSize(idArray != null ? idArray.length:1)
			);
		for(String id:idArray){
			YxxsWechatWallServiceUtil.delete(Long.parseLong(id));
//			YxxsWechatWallUtil.remove(id);
		}
		return rt;
	}
}
