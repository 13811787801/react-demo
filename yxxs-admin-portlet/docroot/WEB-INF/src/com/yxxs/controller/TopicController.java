package com.yxxs.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yxxs.common.dto.PagerInfo;
import com.yxxs.common.dto.PagerResult;
import com.yxxs.common.util.DateUtil;
import com.yxxs.dao.CommonStatisticDao;
import com.yxxs.dao.mapper.BaseStatsMapper;
import com.yxxs.util.PropsConfig;

@Controller
public class TopicController extends BaseWwwController {

	@Autowired
	CommonStatisticDao commonStatisticsDao_;

	
	@RequestMapping("admin/topic/topicList") 
	public String topicList(
			Model model
			){
		
		
		return "topic/topicList";
	}
	
	@RequestMapping("admin/topic/qualityTopicList") 
	public String qualityTopicList(
			Model model
			){
		
		
		return "topic/qualityTopicList";
	}

	@RequestMapping("admin/topic/topicForm") 
	public String topicForm(
			@RequestParam(value="topicId",required=false) Long topicId,
			Model model
			){
		
		
		return "topic/topicEdit";
	}
	

	@RequestMapping("admin/topic/topicAppriseList") 
	public String topicAppriseList(
			Model model
			){
		
		
		return "topic/topicAppriseList";
	}
	
	/**
	 * 优质任务分享
	 * @param model
	 * @return
	 */
	@RequestMapping("admin/topic/topicAppriseShareList") 
	public String topicAppriseShareList(
			Model model
			){
		
		
		return "topic/topicAppriseShareList";
	}

	@RequestMapping("admin/topic/expertsTopicAppriseList") 
	public String expertsTopicAppriseList(
			Model model
			){
		
		
		return "topic/expertsTopicAppriseList";
	}

	@RequestMapping("admin/topic/expertsTopicAppriseDetailList") 
	public String expertsTopicAppriseDetailList(
			Model model
			){
		
		
		return "topic/expertsTopicAppriseDetailList";
	}

	@RequestMapping("admin/topic/topicAppriseForm") 
	public String topicAppriseForm(
			@RequestParam(value="praiseId",required=false) Long praiseId,
			@RequestParam(value="topicId",required=false) Long topicId,
			Model model
			){
		
		
		return "topic/topicAppriseEdit";
	}
	

	@RequestMapping("admin/topic/topicAppriseBatchForm") 
	public String topicAppriseBatchForm(
			@RequestParam(value="praiseId",required=false) Long praiseId,
			@RequestParam(value="topicIds",required=false) String topicIds,
			Model model
			){
		
		System.out.println("admin/topic/topicAppriseBatchForm topicIds:"+topicIds);
		return "topic/topicAppriseEditBatch";
	}
	
	@RequestMapping("admin/topic/topicTemplateList") 
	public String topicTemplateList(
			Model model
			){
		
		
		return "topic/topicTemplateList";
	}

	@RequestMapping("admin/topic/topicTemplateForm") 
	public String topicTemplateForm(
			@RequestParam(value="templateId",required=false) Long templateId,
			@RequestParam(value="topicId",required=false) Long topicId,
			Model model
			){
		
		
		return "topic/topicTemplateEdit";
	}
	

	@RequestMapping("admin/topic/topicKeywordList") 
	public String topicKeywordList(
			Model model
			){
		
		
		return "topic/topicKeywordList";
	}

	@RequestMapping("admin/topic/topicKeywordForm") 
	public String topicKeywordForm(
			@RequestParam(value="keywordId",required=false) Long keywordId,
			Model model
			){
		
		
		return "topic/topicKeywordEdit";
	}
	
	
	
	


	@RequestMapping("admin/topic/topicAppriseMsgSendLog") 
	public String topicAppriseMsgSendLog(
			Model model
			){
		
		
		return "topic/topicAppriseMsgSendLogList";
	}
	
	@RequestMapping("admin/topic/exportAppriseExcel") 
	public void exportAppriseExcel(
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="startTime",required=false) Date startTime,
			@RequestParam(value="endTime",required=false) Date endTime,

			@RequestParam(value="status",required=false) Integer status,
			@RequestParam(value="flowStatus",required=false) Integer flowStatus,
			
			Model model,
			HttpServletResponse response
			) throws IOException{

		List<Object> params = new ArrayList<Object>();
		String cnd = " 1=1 ";
		if(null != status){
			cnd += " and ta.status = ?";
			params.add(status);
		}
		if(null != flowStatus){
			cnd += " and ta.flowStatus = ?";
			params.add(flowStatus);
		}
		if(null != name){
			cnd += " and (a.title like concat('%',?,'%') or concat(b.lastName,b.firstName) like concat('%',?,'%')) ";
			params.add(name);
			params.add(name);
		}
		if(null != startTime){
			cnd += " and a.createTime >= ?";
			params.add(startTime);
		}
		if(null != endTime){
			Calendar et = Calendar.getInstance();
			et.setTime(endTime);
			et.add(Calendar.DAY_OF_YEAR, 1);//最后一天截止到23:59:59
			
			cnd += " and a.createTime <= ?";
			params.add(et.getTime());
		}
		

		List<String[]> columns = new ArrayList<String[]>();
		columns.add(new String[]{"a.topicdiscussionid", "任务ID"});
		columns.add(new String[]{"a.title", "任务主题"});
		columns.add(new String[]{"CONCAT('http://"+PropsConfig.SERVER_HOST+"/yxxs-main-portlet/web/topicdiscussion','_',a.topicDiscussionId)", "任务链接"});
		columns.add(new String[]{"CONCAT('http://"+PropsConfig.SERVER_HOST+"/yxxs-admin-portlet/admin/topic/topicAppriseForm?praiseId=',ta.praiseId)", "后台点评链接"});
		columns.add(new String[]{"replace(replace(a.content,'\n',' '),'\t',' ')", "任务说明"});
		columns.add(new String[]{"df.title", "任务资源"});
		
		columns.add(new String[]{"ta.extDesc", "任务描述"});
		
		columns.add(new String[]{"concat(b.lastName,b.firstName)", "作者"});
		//columns.add(new String[]{"b.emailAddress", "作者账号"});
		
		//columns.add(new String[]{"a.createTime", "任务发布时间"});
		//columns.add(new String[]{"ta.createTime", "申报时间"});
		
		//columns.add(new String[]{"(select count(distinct c.deliveryId) from yxxs_topicdiscussiondelivery c where a.topicDiscussionId = c.topicDiscussionId )", "班级数"});
		//columns.add(new String[]{"(length(stuList) - length(replace(stuList,',',''))+1)", "学生数"});
		/*
		columns.add(new String[]{"(" +
				" select count(userId)/(length(stuList) - length(replace(stuList,',',''))+1) from yxxs_topicdiscussion_usercounter " +
				" where topicDiscussionId=a.topicDiscussionId and FIND_IN_SET(userId,stuList) and discussionNum+homeworkNum+praiseNum+commentNum>0 " +
				")", "参与率"});
		*/

		columns.add(new String[]{"("+
				  "select cName from yxxs_basecurriculum where cId =(case when a.curriculumId = 0 then df.curriculumId else a.curriculumId end)"+
				  ")",
				  "学科"});
		columns.add(new String[]{"concat((1 + a.grade - 1001),'年级')",
		  "年级"});
		
		columns.add(new String[]{"("+
				"select (select orgp.name from organization_ orgp where orgp.organizationId =org.parentorganizationid) "+
				"from organization_ org where org .organizationId= "+
				"(select uo.organizationId from users_orgs uo where  uo.userId = a.userId and uo.organizationId in (select c.organizationId from yxxs_organization_ext c where c.orgType =1025) limit 1)"+
				")",
				"学校"});
		
		columns.add(new String[]{"("+
				"select keyword from yxxs_object_tag as ot left join yxxs_topic_keyword_tag as tkt on ot.tagId=tkt.id_ and tagType='TopicKeyWordTag' and targetType='TopicDiscussion' where targetId=a.topicDiscussionId and type_=1 limit 1"+
				")",
				"章节名称"});
		columns.add(new String[]{"("+
				"select keyword from yxxs_object_tag as ot left join yxxs_topic_keyword_tag as tkt on ot.tagId=tkt.id_ and tagType='TopicKeyWordTag' and targetType='TopicDiscussion' where targetId=a.topicDiscussionId and type_=2 limit 1"+
				")",
				"任务类型"});

		columns.add(new String[]{"ta.comment_", "专家点评"});
		columns.add(new String[]{"ta.point", "评分"});
		columns.add(new String[]{"concat(pu.lastName,pu.firstName)", "专家姓名"});
		columns.add(new String[]{"ta.praiseUserTitle", "专家职称"});
		columns.add(new String[]{"''", "备注"});

		final Hashtable<String, String> map =  StatisticController.parseColumnMap(columns);
		
		PagerResult<List<Hashtable<String, Object>>> result = commonStatisticsDao_.search(new BaseStatsMapper() {
				
				public String getTableName() {
					String SQL = " "+ 
							" yxxs_topicdiscussion_apprise as ta" +
							" left join yxxs_topicdiscussion a on ta.topicdiscussionid = a.topicdiscussionid" +
							" left join yxxs_topicdiscussionrank_ext as rank on a.topicdiscussionid = rank.topicdiscussionid"+
							" left join yxxs_dlfileentry df on a.fileEntryId=df.fileEntryId"+
							" left join user_ b on a.userId=b.userId"+
							" left join yxxs_user_ext ue on a.userId=ue.userid"+
							" left join yxxs_topicdiscussioncounter tc on a.topicDiscussionId=tc.topicDiscussionId and tc.deliveryToGroupId=0"+
							" left join user_ pu on pu.userId=ta.praiseUserId"+
							" ";
					return SQL;
				}
				
				@Override
				protected Hashtable<String, String> getColumnFieldMap() {
					return  map;
				}
			}, 
			cnd, params.toArray(), "", new PagerInfo().setPageSize(Integer.MAX_VALUE).setPageNumber(1));

		String fileName = "优质任务申报";
		if(null != startTime){
			fileName += "-" + "起"+DateUtil.getFormatDate(startTime,"yyyy-MM-dd");
		}
		if(null != endTime){
			fileName += "-" + "止"+DateUtil.getFormatDate(endTime,"yyyy-MM-dd");
		}
		
        response.setHeader("content-disposition", "attachment;filename="  
                + URLEncoder.encode(fileName+".xls", "UTF-8"));
        
        List<Hashtable<String, Object>> list = result.getObj();

        columns.add(0, new String[]{"序号", "rowNum"});
        for(int i=0;i<list.size();i++){
        	list.get(i).put("rowNum", (i+1));
        }
        
		ExcelController.exportExcel(columns,list,response.getOutputStream());
	}
}