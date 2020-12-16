<%@page import="com.yxxs.common.util.DateUtil"%>
<%@page import="com.yxxs.common.util.DateTermUtil"%>
<%@ page language="java" contentType="application/javascript; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int year = DateTermUtil.getGradeStartYear();
%>

<%if(false){ %>
<script>
<%}%>
/**
 * conf = {
	logContent:null,
	func:function(){
 	}
 };
 */
function feedback_agree130(feedbackId,approvalFlag,userId,conf){

        $.ajax({
            url: "/yxxs-main-portlet/api/model/get_CommonFeedBack_"+feedbackId,
            type: "POST",
            dataType: "json",
            data: { },
            success: function (data) {

                var cdata = JSON.parse(data.content);
				var appKey = '';//2019-02-10 用户反馈增加公众号标识，根据公众号处理用户反馈
                if(data.appKey){
                	appKey = data.appKey;
                }
                if(data.type == 130){
                	
	           		var feedbackUserId = data.userId;
	           		if(!feedbackUserId){
	                   jQuery.ajax(
	                       {
	                           type: "get",
	                           url: "/yxxs-wx-serv-portlet/api/secure/jsonws/bindweixin/find-by-weixin-open-id",
	                           data:{
	                               openId:data.openId,
	                               appKey:appKey
	                           },
	                           dataType:'json',
	                           async:false,
	                           success: function(wdata){
								feedbackUserId = wdata.user.userId;
	                           }
	                       });
	                 }
	               
	               var adminId = null;
                   jQuery.ajax(
                       {
                           type: "get",
                           url: "/yxxs-main-portlet/api/getUser?emailAddress=admin@iyxxs.com",
                           data:{},
                           dataType:'json',
                           async:false,
                           success: function(adata){
							adminId = adata.userId;
                           }
                       });
                	
                	var hash = {};
                	hash.name = cdata.className;
                	hash.schoolYear =  <%=year%> + 1001 - cdata.grade;
                    hash.schoolId = cdata.schoolId;
                    hash.userId = adminId;//班主任身份

                    hash.termStartDate = '<%=DateUtil.getFormatDate(DateTermUtil.getCurrentTermStartDate(),"yyyy-MM-dd")%>';
                    hash.termEndDate = '<%=DateUtil.getFormatDate(DateTermUtil.getCurrentTermEndDate(),"yyyy-MM-dd")%>';

                    saveClass(hash, function(h,already){
                    	hash = h;
                    	
                    	var saveLogFunc = function(infoHash, okFunc){
                    		
                            var fhash = {};
                            fhash.feedbackId = feedbackId;
                            fhash.userId = feedbackUserId;
                            fhash.content = infoHash.logContent;
                            fhash.status = infoHash.status;

                            $.ajax({
                                url: "/yxxs-main-portlet/api/feedback/addFeedBackDealLog",
                                type: "POST",
                                dataType: "json",
                                data: fhash,
                                success: function (fdata) {
                                	if(okFunc){
                                		okFunc();
                                	}
                                }
                            });
                    	};
                    	
                    	if(already){
                    	}
                       	//加入班级
                       	var ahash = {};
                       	ahash.classId = hash.classId;
                       	ahash.userId = feedbackUserId;
                       	ahash.roleId = 14216;
                       	ahash.appKey=appKey;
                           
                        $.ajax({
                            url: "/yxxs-main-portlet/api/directAddUserToClass",
                            type: "POST",
                            dataType: "json",
                            data: ahash,
                            async:false,
                            success: function (data) {
                            }
                        });
                       	
                       	var overFunc =function(){
                            alert("审核通过");
                            if(conf && conf.func){
                            	conf.func();
                            }
                       	};
                       	
                  		var logContent = '';
                  		if(conf && conf.logContent){
                  			logContent = conf.logContent;
                  		}
                   		saveLogFunc({logContent:logContent,status:1051}, function(){
                   			overFunc();
                   		});
                    });
                    return;
                }
            }
        });
    }
    
    function feedback_refuse130(feedbackId,approvalFlag,userId, conf){

    	var hash = {};
        hash.feedbackId = feedbackId;
        hash.userId = userId;/*From head.jsp*/
        hash.content = '';
        hash.status = approvalFlag;

  		var logContent = '';
  		if(conf && conf.logContent){
  			logContent = conf.logContent;
  		}
  		hash.content = logContent;
  		
        $.ajax({
            url: "/yxxs-main-portlet/api/feedback/addFeedBackDealLog",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("审核拒绝");
                if(conf && conf.func){
                	conf.func();
                }
            }
        });
    }
<%if(false){ %>
</script>
<%}%>