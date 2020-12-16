<%@page import="com.yxxs.common.util.DateTermUtil"%>
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
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    
    <style>
    .td_createTime{max-width:92px;word-break:break-all;}
    .td_content{max-width:350px;word-break:break-all;}
    .sActionName{width:80% !important;}
    </style>
    
    
    <%
    String sAppKey = "";
    if(null != request.getParameter("sAppKey")){
    	sAppKey = request.getParameter("sAppKey");
    }
    String sFlag = "";
    if(null != request.getParameter("sFlag")){
    	sFlag = request.getParameter("sFlag");
    }
    String sActionName = "";
    if(null != request.getParameter("sActionName")){
    	sActionName = request.getParameter("sActionName");
    }
    %>
    
    <%
    int year = DateTermUtil.getGradeStartYear();
    %>
    
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="sAppKey">
                  <option value="" >全部渠道</option>
                  <%if(".dev".equals(_mode)){%>
                  	  <option value="maxinlan" >翼课项目</option>
                	  <option value="xcxj" >小初衔接课程改革项目</option>
                      <option value="" >翻转教学</option>
                  <%
                  }else{%>
                  		<option value="maxinlan" >马芯兰</option>
                	    <option value="xcxj" >小初衔接课题组</option>
                	    <option value="" >优学向上</option>
                  <%} %>
                  </select>
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="sFlag">
                    <option value="" >全部状态</option>
                    <option value="1051" >创建</option>
                    <option value="1052" >发布</option>
                    <option value="0" >过期</option>
                  </select>
               </div>
            </div>
            <div class="col-xs-3">
            	<div class="form-group">
	            	<input id=sActionName type="text" class="form-control sActionName"  placeholder="活动名称" value="" />
	            </div>
            </div>
            <div class="col-xs-3">
            	<div class="form-group">
                    <button type="button" onclick="createAction()" class="btn btn-default">创建活动</button>
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="search();" class="btn btn-default">搜索</button>
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
    	function createAction(){window.location.href='/yxxs-admin-portlet/admin/action/actionForm';}
    	function createShowLink(url,title,describ,id){
    		var _url = "http://"+servHost+"/yxxs-wx-portlet/maxinlan/action/actionInfo?actionId="+id;
    		if(url){
    			_url = "http://"+servHost+url;
    		}
    		
    		showLink(_url,{qrTitle:title,qrDesc:describ,showQr:true});
    	}
        function init(){
            $('#sAppKey').val('<%=sAppKey%>');
            $('#sFlag').val('<%=sFlag%>');
            $('#sActionName').val('<%=sActionName%>');
        }
        init();
    
	    function search(){
	        var page = <%=pageNum%>;
	        if('<%=sAppKey%>'!= $('#sAppKey').val() || '<%=sFlag%>'!= $('#sFlag').val() || '<%=sActionName%>'!= $('#sActionName').val()){
	            page = 1;
	        }
	        window.location.href = '?sAppKey='+$('#sAppKey').val()+'&sFlag='+$('#sFlag').val()+'&sActionName='+$('#sActionName').val()+'&page=' + page;
	    }
	    
	    
	    
        function initList() {
            
            var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            if($('#sAppKey').val()){
                param.appKey = $('#sAppKey').val();
            }
            if(!isNaN($('#sFlag').val())){
                param.flag = $('#sFlag').val();
            }
            if($('#sActionName').val()){
            	param.title = $('#sActionName').val();
            }

            var paramList = [];
            for(var k in param){
                paramList.push(k+"="+param[k]);
            }
            
            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-action-serv-portlet/action/api/search?"+paramList.join('&'),
                        props:[
                            {
                                url:"/yxxs-main-portlet/api/user_<%="${$parent.createId}"%>",
                                alias:"userInfo",
                                condition:"($parent.createId>0?true:false)",
                                parentType:"list",
                                parentExp:".obj",
                            }
                        ]
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
							{name:"id",desc:"活动编号",
								format:function(v,ob){
									return ob.id;
								}
							},
                            {name:"appKey",desc:"渠道",format:function(v,ob){
                            	var s = v;
                            	if(!s && ob.appKey){
                            		
                                    s = (ob.appKey == '' || null == ob.appKey)?'自有公众号':ob.appKey;
                            	}
                            	return s;
                            }},
                            { name: "userName", desc: "反馈人",format:function(v,ob){
                            	var s = v;
                            	if(!s && ob.createId){
                            		var wdata = ob.userInfo;
                            		if(wdata){
                                        s = wdata.screenName + "<br/>" + wdata.emailAddress;
                            		}
                            	}
                            	return s;
                            }},
                            { name: "title", desc: "标题 ", 
                            	format: function (v, ob) { 
                            		var s = ob.title;
                            		return  s ;
                            	}
                            },
                            { name: "describe_", desc: "简介", 
                                format: function (v, ob) {
                                    return ob.describe_;
                                 }
                            },
                            { name: "content", desc: "内容描述", 
                                format: function (v, ob) {
                                    return ob.content ;
                                 } 
                            },
                            { name: "flag", desc: "状态", 
                            	format: function (v) {
                            	   if(v == 1051){
                            		   return '未审核';
                            	   }
                                   if(v == 1052){
                                       return '已审核';
                                   }
                                   
                            	   return '过期' ;
                            	} 
                            },
                            { name: "cnt", desc: "处理记录数"},
                            { name: "createDate", desc: "创建时间", 
                            	format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} 
                            },
                            { name: "startDate", desc: "起始时间", 
                            	format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} 
                            },
                            { name: "endDate", desc: "结束时间", 
                            	format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} 
                            },
                        ],
                        operations: [{
                            name: "编辑活动",
                            visable: function (obj) {
                            	var curTime = new Date().Format("yyyy-MM-dd");
                            	if(curTime > new Date(obj.startDate).Format("yyyy-MM-dd")){
                            		return false;
                            	}
                            	return true; 
                            	},
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/action/actionForm?id=" + obj.id; }
                        },{
                        	name:"签到人员设置",
                        	visable:function (obj){return true;},
                        	getUrl:function(obj){
                        		return "/yxxs-admin-portlet/admin/action/actionUserForm?id=" + obj.id;
                        	}
                        },{
                        	name:"签到情况查看",
                        	visable:function (obj){
                        	/*var curTime = new Date();
                        	if(curTime > new Date(obj.startDate)){
                        		return false;
                        	}*/
                        	return true;},
                        	getUrl:function(obj){
                        		return "/yxxs-admin-portlet/admin/action/actionUserList?actionId=" + obj.id;
                        	}
                        },{
                        	name:"生成二维码",
                        	visable:function (obj){if(obj.flag == 1052){return true;}else{return false;}},
                        	getUrl:function(obj){
                        			return "javascript:createShowLink('"+obj.url+"','"+obj.title+"','"+obj.describe_+"</br>此二维码48小时内有效"+"',"+obj.id+")";
                        	}
                        }/*,{
                        	name:"微信墙",
                        	visable:function (obj){return true;},
                        	getUrl:function(obj){
                        		return "/yxxs-admin-portlet/admin/wall/wallList?id=" + obj.id+'&sAppKey='+obj.appKey;
                        	}
                        }*/
                        ],
                        pageUrlFunc: function(page){
                            return '?sAppKey='+$('#sAppKey').val()+'&sFlag='+$('#sFlag').val()+'&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
</body>
</html>