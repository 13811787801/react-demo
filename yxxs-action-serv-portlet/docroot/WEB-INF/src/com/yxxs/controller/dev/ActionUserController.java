package com.yxxs.controller.dev;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.yxxs.common.handler.CommonErrorHandler;
import com.yxxs.common.serv.CommonViewUtil;
import com.yxxs.common.serv.CommonViewUtil.CommonViewMapper;
import com.yxxs.common.util.DateUtil;
import com.yxxs.common.util.JsonUtil;
import com.yxxs.model.YxxsAction;
import com.yxxs.model.YxxsActionDealLog;
import com.yxxs.model.YxxsActionUser;
import com.yxxs.service.YxxsActionDealLogLocalServiceUtil;
import com.yxxs.service.YxxsActionLocalServiceUtil;
import com.yxxs.service.YxxsActionUserLocalServiceUtil;
import com.yxxs.service.persistence.YxxsActionUserUtil;
import com.yxxs.service.persistence.YxxsActionUtil;

@Controller
@RequestMapping("/actionuser/*")
public class ActionUserController extends BaseDevController {
	/**
	 * @Title: signinfo
	
	 * @Description: 活动签到情况查询
	
	 * @param:   
	
	 * @return:  
	   
	   @Test :https://yxxstest.iyxxs.com/yxxs-action-serv-portlet/actionuser/api/signinfo?appKey=maxinlan&openid=oX4e31L24g4oluVIQSV78Q3olIPs
	 * @throws
	 */
	@RequestMapping("api/signinfo")
	public @ResponseBody PagerResult<List<HashMap<String,Object>>> signinfo(
			@RequestParam(value="appKey") String appKey,
			@RequestParam(value="openId") String openId,
			@RequestParam(value="actionId",required=false) Long actionId,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="pageSize",defaultValue="1000") Integer pageSize,
			HttpServletRequest request,
			HttpServletResponse response
			) throws SystemException{
		PagerResult<List<HashMap<String,Object>>> rt = new PagerResult<List<HashMap<String,Object>>>(
				new PagerInfo().setPageNumber(page).setPageSize(pageSize));
		String sqlTab = 
//				"SELECT ac.title title,IF(logindate IS NULL,'未签到',logindate) logindate,ac.id_ actionid "
//					    +"    FROM " +
					    "yxxs_action ac"
					    +"  LEFT JOIN (SELECT * FROM yxxs_action_user WHERE openid = '"+openId+"' ) au"
					    +"  ON ac.id_ = au.actionid"
					    +"  WHERE ac.appkey = '"+appKey+"' and ac.flag = 1052 ";
		if(null != actionId){
			sqlTab += " and ac.id_ = "+actionId;
		}
		final String viewTable = sqlTab;
		List<HashMap<String,Object>> dataList = CommonViewUtil.searchList(new CommonViewMapper() {
		
			public String getTableName() {
				return viewTable;
			}
			
			protected Hashtable<String, String> getColumnFieldMap() {
				Hashtable<String, String> map = new Hashtable<String, String>();
				map.put("ac.title","title");
				map.put("IF(logindate IS NULL,'未签到',logindate)","logindate");
				map.put("ac.id_","actionid");
				return map;
			}
		}, null, null, " ac.id_ DESC ");
		rt.setObj(dataList);
		rt.setTotalCount(dataList.size());
		return rt;
	}
	
	@RequestMapping("api/login")
	public @ResponseBody HashMap<String ,Object> login(
			@RequestParam(value="actionId") Long actionId,
			@RequestParam(value="userId",required=false) Long userId,
			@RequestParam(value="rName",required=false) String rName,
			@RequestParam(value="rMobile",required=false) String rMobile,
			@RequestParam(value="rSchool",required=false) String rSchool,
			//@RequestParam(value="userType",required=false,defaultValue="0") Integer userType,
			@RequestParam(value="openId",required=false) String openId,
			HttpServletRequest request,
			HttpServletResponse response
			) throws SystemException{
		HashMap hash = new HashMap();
		YxxsActionUser yxxsActionUser = null;
		System.out.println("[ActionUserController][login][search]["+actionId+"]["+userId+"]["+rName+"]["+rMobile+"]["+openId+"]");
		List<YxxsActionUser> l = null;
		if(null != userId){
			l = YxxsActionUserUtil.findByuserIdAndActionId(userId, actionId, "id_ desc");
		}else{
			l = YxxsActionUserUtil.findByopenIdAndActionId(openId, actionId, "id_ desc");
		}
		
		if(l != null && l.size()>0){
			System.out.println("[ActionUserController][login][update]["+actionId+"]["+userId+"]["+rName+"]["+rMobile+"]["+openId+"]");
			//update
			yxxsActionUser = l.get(0);
			yxxsActionUser.setLoginDate(new Date());
			yxxsActionUser.setOpenId(openId != null?openId:"-1");
			yxxsActionUser.setIsLogged(1);
			yxxsActionUser.setRMobile(rMobile);
			yxxsActionUser.setRName(rName);
			yxxsActionUser.setRSchool(rSchool);
			yxxsActionUser.setUserId((userId != null && !"".equals(userId))?userId:-1l);
			YxxsActionUserUtil.update(yxxsActionUser);
			
		}else{
			//add
			yxxsActionUser = this.add((userId != null && !"".equals(userId))?userId:-1l, (userId != null && !"".equals(userId))?userId:-1l
					,actionId, 1, 1,openId != null?openId:"-1", new Date(),rName,rMobile,rSchool);
			System.out.println("[ActionUserController][login][add]["+actionId+"]["+userId+"]["+rName+"]["+rMobile+"]["+openId+"]");
		}
		if(yxxsActionUser != null){
			hash.put(yxxsActionUser.getId(), yxxsActionUser);
			hash.put("Msg", "OK");
			hash.put("Key", "true");
		}else{
			hash.put("Msg", "");
			hash.put("Key", "false");
		}
		
		return hash;
	}
	
	@RequestMapping("api/add")
	public @ResponseBody HashMap<String ,Object> addActionUser(
			@RequestParam(value="sendTarget") String userInfos,
			@RequestParam(value="createId") Long createId,
			@RequestParam(value="actionId") Long actionId,
			@RequestParam(value="userType",required=false,defaultValue="0") Integer userType,
			@RequestParam(value="rName",required=false) String rName,
			@RequestParam(value="rMobile",required=false) String rMobile,
			@RequestParam(value="rSchool",required=false) String rSchool,
			
			@RequestParam(value="isLogged",required=false) Integer isLogged,
			@RequestParam(value="openId",required=false) String openId,
			@RequestParam(value="loginDate",required=false) Date loginDate,
			HttpServletRequest request,
			HttpServletResponse response
			) throws SystemException{
		HashMap hash = new HashMap();
		Map<?,?> m = JsonUtil.Json2Map(userInfos);
		List<String> ids = new ArrayList<String>((Collection<? extends String>) m.keySet());//(ArrayList<String>)m.get("userIds");
		System.out.println("!!!!!!!!!!!!!!!!!"+userInfos+"\n"+ids.size());
		//long userId = 0;
		HashMap<String,String> u = null;
		for(String userId:ids){
			u = (HashMap<String,String>)m.get(userId);
			YxxsActionUser t = add(new Long(userId),createId, actionId, userType, isLogged, openId,
					loginDate,u.get("rName"),u.get("rMobile"),u.get("rSchool"));
			if(null != t){
				hash.put(t.getId(), t);
			}
		}
		
		if(null != ids){
			hash.put("Msg", "OK");
			hash.put("Key", "true");
		}else{
			hash.put("Msg", "");
			hash.put("Key", "false");
		}
		
		return hash;
	}

	private YxxsActionUser add(Long userId,Long createId, Long actionId, Integer userType,
			Integer isLogged, String openId, Date loginDate)
			throws SystemException {
		long id = CounterLocalServiceUtil.increment(YxxsActionUser.class.getName());
		YxxsActionUser yxxsActionUser = YxxsActionUserLocalServiceUtil.createYxxsActionUser(id);
		yxxsActionUser.setActionId(actionId);
		yxxsActionUser.setCreateId(createId);
		yxxsActionUser.setUserId(userId);
		yxxsActionUser.setUserType(userType);
		yxxsActionUser.setCreateDate(new Date());
		if(null != isLogged){
			yxxsActionUser.setIsLogged(isLogged);
		}
		if(null != openId){
			yxxsActionUser.setOpenId(openId);
		}
		if(null != loginDate){
			yxxsActionUser.setLoginDate(loginDate);
		}
		YxxsActionUser t = YxxsActionUserLocalServiceUtil.addYxxsActionUser(yxxsActionUser);
		return t;
	}

	private YxxsActionUser add(Long userId,Long createId, Long actionId, Integer userType,
			Integer isLogged, String openId, Date loginDate,String rName,String rMobile,String rSchool)
			throws SystemException {
		long id = CounterLocalServiceUtil.increment(YxxsActionUser.class.getName());
		YxxsActionUser yxxsActionUser = YxxsActionUserLocalServiceUtil.createYxxsActionUser(id);
		yxxsActionUser.setActionId(actionId);
		yxxsActionUser.setCreateId(createId);
		yxxsActionUser.setUserId(userId);
		yxxsActionUser.setUserType(userType);
		yxxsActionUser.setCreateDate(new Date());
		yxxsActionUser.setRMobile(rMobile);
		yxxsActionUser.setRName(rName);
		yxxsActionUser.setRSchool(rSchool);
		if(null != isLogged){
			yxxsActionUser.setIsLogged(isLogged);
		}
		if(null != openId){
			yxxsActionUser.setOpenId(openId);
		}
		if(null != loginDate){
			yxxsActionUser.setLoginDate(loginDate);
		}
		YxxsActionUser t = YxxsActionUserLocalServiceUtil.addYxxsActionUser(yxxsActionUser);
		return t;
	}
	@RequestMapping("api/search")
	public @ResponseBody PagerResult<List<YxxsActionUser>> getAll(
			@RequestParam(value="actionId",required=false) Long actionId,
			/*
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="createId",required=false) Long createId,
			@RequestParam(value="startDate",required=false) Date startDate,
			@RequestParam(value="endDate",required=false) Date endDate,
			@RequestParam(value="createDate",required=false) Date createDate,*/
			@RequestParam(value="isLogged",required=false) Integer isLogged,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="pageSize",defaultValue="1000") Integer pageSize,
			HttpServletRequest request,
			HttpServletResponse response
			){
		PagerResult<List<YxxsActionUser>> rt = new PagerResult<List<YxxsActionUser>>(
				new PagerInfo().setPageNumber(page).setPageSize(pageSize)
			);
		List<Object> params = new ArrayList<Object>();
		String condition = "1= 1";
		if(null != actionId && !"".equals(actionId)){
			condition += " and actionId = ? ";
			params.add(actionId);
		}
		if(null != isLogged && !"".equals(isLogged)){
			condition += " and isLogged = ? ";
			params.add(isLogged);
		}
		
		List<YxxsActionUser> l = YxxsActionUserUtil.findByCnd(condition, params.toArray(new Object[params.size()]), page*pageSize, (page+1)*pageSize);
		int cnt = YxxsActionUserUtil.count(condition, params.toArray(new Object[params.size()]));
		rt.setObj(l);
		rt.setTotalCount(cnt);
		return rt;
	}
	
	@RequestMapping("export/exportExcel")
	public String exportExcel(
			@RequestParam(value="actionId",required=false) Long actionId,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException, NoSuchYxxsActionException{
		YxxsAction action = YxxsActionUtil.findByPrimaryKey(actionId);
		String sqlTab = " (                                               "
				+"SELECT DISTINCT IF(aa.rname IS NULL,bb.rname,aa.rname) rname            "
				+",IF(aa.rmobile IS NULL,bb.rmobile,aa.rmobile) rmobile          "
				+",IF(aa.rschool IS NULL,bb.rschool,aa.rschool) rschool          "
				+",IF(aa.logindate IS NULL,bb.logindate,aa.logindate) logindate  "
				+",IF(aa.userid != -1,aa.userid,bb.userid) userid,IF(bb.usertype IS NULL,aa.usertype,bb.usertype) usertype FROM         "
				+"(SELECT *                                                      "
				+"  FROM yxxs_action_user                                        "
				+" WHERE actionid = "+actionId+"                                          "
				+"   AND islogged = 1) aa                                        "
				+"LEFT JOIN                                                      "
				+"(SELECT *                                                       "
				+"  FROM yxxs_action_user                                        "
				+" WHERE actionid = "+actionId+"                                          "
				+"   AND usertype = 0                                            "
				+") bb                                                           "
				+"ON aa.rname = bb.rname                                       "
				+"UNION ALL                                                      "
				+"SELECT rname,rmobile,rschool,logindate,userid,usertype                  "
				+"  FROM yxxs_action_user                                        "
				+" WHERE actionid = "+actionId+"                                          "
				+"   AND islogged = 0                                            "
				+"   AND usertype = 0                                            "
				+"   AND rname NOT IN (                                          "
				+"	SELECT rname                                                 "
				+"	  FROM (                                                     "
				+"		SELECT *                                                 "
				+"		  FROM yxxs_action_user                                  "
				+"		 WHERE actionid = "+actionId+"                                    "
				+"		   AND islogged = 1                                      "
				+"	  ) a                                                        "
				+"   )                                                           "
				+" ) b                                                           ";
		final String viewTable = sqlTab;
		List<HashMap<String,Object>> dataList = CommonViewUtil.searchList(new CommonViewMapper() {
			
			public String getTableName() {
				return viewTable;
			}
			
			protected Hashtable<String, String> getColumnFieldMap() {
				Hashtable<String, String> map = new Hashtable<String, String>();
				map.put("rname","rname");
				map.put("rmobile","rmobile");
				map.put("rschool","rschool");
				map.put("logindate","logindate");
				map.put("userid","userid");
				map.put("IF(usertype = 0,'本地','游客')","usertype");//IF(usertype = 0,'本地','游客') 
				
				return map;
			}
		}, null, null, " logindate DESC ");
		List<String[]> allColumns = new ArrayList<String[]>();
		HashMap<String,Object> yau = null;
		//活动编号	渠道	活动名称	用户编号	邮箱	OpenId	登录状态	用户来源	创建时间	登录时间
		allColumns.add(0, new String[]{"姓名","rname"});
		allColumns.add(1, new String[]{"电话","rmobile",});
		allColumns.add(2, new String[]{"学校","rschool"});
		allColumns.add(3, new String[]{"签到","logindate"});
		allColumns.add(4, new String[]{"用户来源","usertype"});
		List<Hashtable<String, Object>> list = new ArrayList<Hashtable<String,Object>>();
		Hashtable<String,Object> h = null;
		Object o = null;
		try{
			for(int i = 0 ;i < dataList.size();i++){
				h = new Hashtable<String, Object>();
				for(int j = 0;j < allColumns.size();j++){
					yau = dataList.get(i);
					o = yau.get(allColumns.get(j)[1]);
					if(o == null){
						o = "";
					}
					h.put(allColumns.get(j)[1], o);
				}
				list.add(i, h);
			}
		}catch(Exception e){
			System.out.println("[actionuser/export/exportExcel] ["+e.toString()+"]");
		}
		String fName = action.getTitle()+"-活动签到信息";
		
        response.setHeader("content-disposition", "attachment;filename="  
                + URLEncoder.encode(fName+".xls", "UTF-8"));
		exportExcel(allColumns,list,response.getOutputStream());
		return null;
	}
	
	
	
	/**
	 * 
	 * @param columns [["columnAlias","hashKeyName"],["columnAlias","hashKeyName"]]
	 * @param list
	 * @param out
	 */
    public static void exportExcel(List<String[]> columns,  
    		List<Hashtable<String, Object>> list, OutputStream out)  
    {  

        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet();  
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
        
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.VIOLET.index);  
        //font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
        
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.WHITE.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);  
        for (int i = 0; i < columns.size(); i++)  
        {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(columns.get(i)[0]);  
            cell.setCellValue(text);  
        }  

        // 遍历集合数据，产生数据行 
        for(int i=0;i<list.size();i++){
            row = sheet.createRow(i+1);  
            
            for (int j = 0; j < columns.size(); j++)  
            {  
                HSSFCell cell = row.createCell(j);  
                cell.setCellStyle(style2);  
                
                Object v = list.get(i).get(columns.get(j)[1]);
                
                HSSFRichTextString text = new HSSFRichTextString((null == v?"":String.valueOf(v)));
                cell.setCellValue(text);  
            }  

        }
        
        try  
        {  
            workbook.write(out);  
        }  
        catch (IOException e)  
        {  
            //e.printStackTrace(); 
			CommonErrorHandler.printException(e); 
        }  
    }
}
