package com.yxxs.controller.dev;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.yxxs.NoSuchYxxsActionException;
import com.yxxs.common.dto.PagerInfo;
import com.yxxs.common.dto.PagerResult;
import com.yxxs.common.util.DateUtil;
import com.yxxs.common.util.JsonUtil;
import com.yxxs.model.YxxsAction;
import com.yxxs.model.YxxsActionDealLog;
import com.yxxs.service.YxxsActionDealLogLocalServiceUtil;
import com.yxxs.service.YxxsActionLocalServiceUtil;
import com.yxxs.service.persistence.YxxsActionUtil;

@Controller
@RequestMapping("/action/*")
public class ActionController extends BaseDevController {
	
	@RequestMapping("api/create")
	public @ResponseBody HashMap<String ,Object> createAction(
			@RequestParam(value="appKey") String appKey,
			@RequestParam(value="title") String title,
			@RequestParam(value="describe",required=false) String describe,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="url",required=false) String url,
			@RequestParam(value="createId") long createId,
			@RequestParam(value="startDate",required=false) Date startDate,
			@RequestParam(value="endDate",required=false) Date endDate,
			@RequestParam(value="flag",required=false,defaultValue="0") int flag,
			HttpServletRequest request,
			HttpServletResponse response
			) throws SystemException{
		HashMap hash = new HashMap();
		long id = CounterLocalServiceUtil.increment(YxxsAction.class.getName());
		YxxsAction action = YxxsActionLocalServiceUtil.createYxxsAction(id);
		
		action.setAppKey(appKey == null?"":appKey);
		action.setTitle(title);
		if(null != describe && !"".equals(describe)){
			action.setDescribe_(describe);
		}else{
			//action.setDescribe_(appKey+"活动");
		}
		if(null != content && !"".equals(content)){
			action.setContent(content);
		}else{
			//action.setContent(appKey+"活动");
		}
		if(null != url && !"".equals(url)){
			action.setUrl(url+id);
		}else{
			action.setUrl("/yxxs-wx-portlet/"+(StringUtil.isEmpty(appKey)?"":appKey+"/")+"action/actionInfo?actionId="+id);
		}
		
		action.setCreateDate(new Date());
		action.setCreateId(createId);
		if(null != endDate){
			action.setEndDate(endDate);
		}
		if(null != startDate){
			action.setStartDate(startDate);
		}
		
		action.setFlag(flag);
		
		YxxsAction t = YxxsActionLocalServiceUtil.addYxxsAction(action);
		if(null != t){
			YxxsActionDealLog yxxsActionDealLog = YxxsActionDealLogLocalServiceUtil.createYxxsActionDealLog(CounterLocalServiceUtil.increment(YxxsActionDealLog.class.getName()));
			yxxsActionDealLog.setActionId(t.getId());
			yxxsActionDealLog.setCreateDate(new Date());
			yxxsActionDealLog.setCreateId(createId);
			yxxsActionDealLog.setType(1);
			yxxsActionDealLog.setContent(JsonUtil.toJsonString(action));
			YxxsActionDealLogLocalServiceUtil.addYxxsActionDealLog(yxxsActionDealLog);
			hash.put(t.getId(), t);
			hash.put("Msg", "OK");
			hash.put("Key", "true");
		}else{
			hash.put("Msg", "");
			hash.put("Key", "false");
		}
		
		return hash;
	}

	@RequestMapping("api/update")
	public @ResponseBody HashMap<String ,Object> updateAction(
			@RequestParam(value="appKey") String appKey,
			@RequestParam(value="title") String title,
			@RequestParam(value="describe",required=false) String describe,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="url") String url,
			@RequestParam(value="createId") long createId,
			@RequestParam(value="startDate",required=false) Date startDate,
			@RequestParam(value="endDate",required=false) Date endDate,
			@RequestParam(value="flag",required=false,defaultValue="0") int flag,
			@RequestParam(value="type",required=false,defaultValue="0") int type,
			@RequestParam(value="id") long id,
			HttpServletRequest request,
			HttpServletResponse response
			) throws SystemException{
		HashMap hash = new HashMap();
		//long id = CounterLocalServiceUtil.increment(YxxsAction.class.getName());
		YxxsAction action = YxxsActionLocalServiceUtil.createYxxsAction(id);
		action.setAppKey(appKey);
		action.setTitle(title);
		if(null != describe && !"".equals(describe)){
			action.setDescribe_(describe);
		}else{
			action.setDescribe_(appKey+"活动");
		}
		if(null != content && !"".equals(content)){
			action.setContent(content);
		}else{
			action.setContent(appKey+"活动");
		}
		if(null != url && !"".equals(url)){
			action.setUrl(url);
		}else{
			action.setUrl("/yxxs-wx-portlet/"+(StringUtil.isEmpty(appKey)?"maxinlan":appKey)+"/action/actionInfo");
		}
		
		action.setCreateDate(new Date());
		action.setCreateId(createId);
		if(null != endDate){
			action.setEndDate(endDate);
		}
		if(null != startDate){
			action.setStartDate(startDate);
		}
		
		action.setFlag(flag);
		
		YxxsAction t = YxxsActionLocalServiceUtil.updateYxxsAction(action);
		YxxsActionDealLog yxxsActionDealLog = YxxsActionDealLogLocalServiceUtil.createYxxsActionDealLog(CounterLocalServiceUtil.increment(YxxsActionDealLog.class.getName()));
		yxxsActionDealLog.setActionId(t.getId());
		yxxsActionDealLog.setCreateDate(new Date());
		yxxsActionDealLog.setCreateId(createId);
		yxxsActionDealLog.setType(type);
		yxxsActionDealLog.setContent(JsonUtil.toJsonString(action));
		YxxsActionDealLogLocalServiceUtil.addYxxsActionDealLog(yxxsActionDealLog);
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
			) throws NoSuchYxxsActionException{
		YxxsAction action = YxxsActionUtil.findByPrimaryKey(pk);
		return action.toHash();
	}
	@RequestMapping("api/search")
	public @ResponseBody PagerResult<List<YxxsAction>> getAll(
			@RequestParam(value="appKey",required=false) String appKey,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="createId",required=false) Long createId,
			@RequestParam(value="startDate",required=false) Date startDate,
			@RequestParam(value="endDate",required=false) Date endDate,
			@RequestParam(value="createDate",required=false) Date createDate,
			@RequestParam(value="flag",required=false) Integer flag,
			@RequestParam(value="page") Integer page,
			@RequestParam(value="pageSize") Integer pageSize,
			HttpServletRequest request,
			HttpServletResponse response
			){
		PagerResult<List<YxxsAction>> rt = new PagerResult<List<YxxsAction>>(
				new PagerInfo().setPageNumber(page).setPageSize(pageSize)
			);
		List<Object> params = new ArrayList<Object>();
		String condition = "1= 1";
		if(null != appKey){
			condition += " and appKey = ? ";
			params.add(appKey);
		}
		if(null != title){
			condition += " and title like concat('%',?,'%') ";
			params.add(title);
		}
		if(null != createId){
			condition += " and createId = ? ";
			params.add(createId.toString());
		}
		if(null != createDate){
			condition += " and createDate >= ? ";
			params.add(DateUtil.getFormatDate(createDate, "yyyy-MM-dd HH:mm:ss"));
		}
		if(null != startDate){
			condition += " and startDate >= ? ";
			params.add(DateUtil.getFormatDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		}
		if(null != endDate){
			condition += " and endDate < ? ";
			params.add(DateUtil.getFormatDate(DateUtil.getAddDayDate(endDate, 1), "yyyy-MM-dd HH:mm:ss"));
		}
		if(null != flag){
			condition += " and flag = ? ";
			params.add(flag);
		}
		List<YxxsAction> l = YxxsActionUtil.findByCnd(condition, params.toArray(new Object[params.size()])," createDate desc ", page*pageSize, (page+1)*pageSize);
		int cnt = YxxsActionUtil.countAll();
		rt.setObj(l);
		rt.setTotalCount(cnt);
		return rt;
	}
}
