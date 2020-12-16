<%@page import="com.yxxs.common.util.DateTermUtil"%>
<%@page import="com.yxxs.common.util.DateUtil"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    
     <script src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
     <script src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
     <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    
    <style>
    .td_createTime{max-width:92px;word-break:break-all;}
    .td_content{max-width:350px;word-break:break-all;}
    .sActionName{width:20% !important;}
    </style>
    
    
    <%
    String actionId = "-1";
    if(null != request.getParameter("id")){
    	actionId = request.getParameter("id");
    }
    String appKey = "maxinlan";
    if(null != request.getParameter("appKey")){
    	appKey = request.getParameter("appKey");
    }
    String wallTitle = "翼课程小学数学课改项目";
    if(null != request.getParameter("title")){
    	wallTitle = request.getParameter("title");
    }
    String startTime = request.getParameter("startTime");
    if(null == startTime){
    	startTime = DateUtil.getFormatDate(DateUtil.getAddDayDate(Calendar.getInstance().getTime(),-1),"yyyy-MM-dd");//"2017-01-01";
    }
    String endTime = request.getParameter("endTime");
    if(null == endTime){
    	endTime = DateUtil.getFormatDate(DateUtil.getAddDayDate(Calendar.getInstance().getTime(),1),"yyyy-MM-dd");//"2017-01-01";
    }
    int year = DateTermUtil.getGradeStartYear();
    %>
    <p align="center"><%=wallTitle %>留言板</p>
    <div class="container">
        <div class="row">
            
        </div>
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
               		<div class="form-inline">
               		<span class="margin15">起始时间：</span>
                    <input type="text" class="form-control" style="width:120px;" id="startTime" readonly="readonly"
                        onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                    
                    <span class="margin15">至</span>
                    <input type="text" class="form-control" style="width:120px;" id="endTime" readonly="readonly"
                        onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                    <button type="button" onclick="search();" class="btn btn-default">搜索</button>
               		</div>
               </div>
            </div>
        </div>
        
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
               	<div class="form-inline">
                    <button type="button" onclick="screenShow()" class="btn btn-default">大屏展示</button>
                    <button type="button" onclick="exportPageData()" class="btn btn-default">导出</button>
               	</div>
               </div>
            </div>
        </div>
    </div>
    
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <%
    int pageNum = 1;
    try{
        pageNum = Integer.parseInt(request.getParameter("page"));
    }catch(Exception ex){}
    %>
    
    <script type="text/javascript">
    	var pageData = {};
    	var tabcolumns = [
			{name:"id",desc:"编号",
				format:function(v,ob){
					return ob.id;
				}
			},                
            { name: "userName", desc: "姓名",format:function(v,ob){
            	var s = v;
            	if(!s && ob.userId){
            		var wdata = ob.userInfo;
            		if(wdata){
                        s = wdata.screenName;// + "<br/>" + wdata.emailAddress;
            		}
            	}
            	return s;
            }},
            { name: "title", desc: "微信 ", 
            	format: function (v, ob) { 
            		var s = ob.wechatInfo.entity.nickname;
            		return  s ;
            	}
            },
            { name: "content", desc: "内容", 
                format: function (v, ob) {
                    return v ;
                 } 
            },
            { name: "showFlag", desc: "状态", 
            	format: function (v) {
            	   if(v == 0){
            		   return '不显示（删除）';
            	   }
                   if(v == 1){
                       return '可见';
                   }
                   
            	   return '-' ;
            	} 
            },
            { name: "createDate", desc: "时间", 
            	format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} 
            },
        ];
        function init(){
        	/*
            $('#appKey').val(<%=appKey%>);
            */
        	$('#startTime').val('<%=startTime%>');
        	$('#endTime').val('<%=endTime%>');
        }
        init();
    
	    function search(){
	        var page = <%=pageNum%>;
	        if('<%=startTime%>'!= $('#startTime').val() || '<%=endTime%>'!= $('#endTime').val() ){
	            page = 1;
	        }
	        window.location.href = '?appKey=<%=appKey%>&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&page=' + page;
	    }
	    
	    function exportPageData(){
	    	var cdate = (new Date()).Format("yyyy-M-d");
	    	exportExcel(tabcolumns,pageData.obj,cdate+'微信墙留言表');
	    }
	    
        function initList() {
            
            var param = {page : <%=pageNum - 1 %>, pageSize: 1000};
            
            param.appKey = '<%=appKey%>';
            //param.startDate=$('#startTime').val();
            param.endDate=$('#endTime').val();

            var paramList = [];
            for(var k in param){
                paramList.push(k+"="+param[k]);
            }
            
            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-action-serv-portlet/wechatwall/api/search?"+paramList.join('&'),
                        props:[
                            {
                                url:"/yxxs-main-portlet/api/user_<%="${$parent.userId}"%>",
                                alias:"userInfo",
                                //condition:"($parent.userId>0?true:false)",
                                parentType:"list",
                                parentExp:".obj",
                            },
                            {
                                url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?openid=<%="${$parent.openId}"%>&appKey=<%=appKey%>",
                                alias:"wechatInfo",
                                //condition:"($parent.userId>0?true:false)",
                                parentType:"list",
                                parentExp:".obj",
                            }
                        ]
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                	pageData = data;
                    initTableList('dataRow', data, {
                        columns: tabcolumns,
                        operations: [{
                            name: "删除",
                            visable: function (obj) {
                            	if(obj.showFlag == 0){
                            		return false;
                            	}
                            	return true; 
                            },
                            getUrl: function (obj) { 
                            	return "javascript:delMsgInfo('" + obj.id +"');"; 
                            	}
                        }],
                        pageUrlFunc: function(page){
                            return '?appKey=<%=appKey%>&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
    
    <script type="text/javascript">
    function screenShow(){
    	window.open('http://'+servHost+'/yxxs-admin-portlet/admin/wall/wallShow?appKey=<%=appKey%>&title=<%=wallTitle%>');
    }
    /*
    function exportData(){
    	var _id = '<%=actionId%>';
    	if($('#actionList').val()){
    		_id = $('#actionList').val();
    	}
    	window.location.href = "http://"+servHost+"/yxxs-action-serv-portlet/actionuser/export/exportExcel?actionId="+_id;
    }*/
    </script>
    <script>
    function delMsgInfo(id){
    	$.ajax({
    		url:'/yxxs-action-serv-portlet/wechatwall/api/del?ids=' + id,
    		data:{},
    		type: "POST",
            dataType: "json",
            async:false,
            success:function(){
            	search();
            }
    	});
    	/*
        jQuery.ajax(
            {
                type: "post",
                url: "/yxxs-wechat-serv-portlet/api/delWechatUserInfo",
                data:{openId:openId,appId:appId},
                dataType:'json',
                async:false,
                success: function(data){
                	search(1);
                }
            }
        );*/
    }
    </script>
</body>
</html>