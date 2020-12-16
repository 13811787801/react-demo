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
    .sActionName{width:20% !important;}
    </style>
    
    <div class="container">
    </div>
    
    <%
    String actionId = "";
    if(null != request.getParameter("actionId")){
    	actionId = request.getParameter("actionId");
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
                  <select class="form-control" id="sFlag">
                    <option value="" >全部状态</option>
                    <option value="1" >登录</option>
                    <option value="0" >未登录</option>
                  </select>
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                    <button type="button" onclick="exportData()" class="btn btn-default">数据导出</button>
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
        
        <div class="row">
            
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
				{name:"actionId",desc:"活动编号",
					format:function(v,ob){
						return v;
					}
				},
				/*
                {name:"appKey",desc:"渠道",format:function(v,ob){
                	var s = v;
                	if(!s && ob.actionInfo.appKey){
                		
                        s = (ob.actionInfo.appKey == '' || null == ob.actionInfo.appKey)?'自有公众号':ob.actionInfo.appKey;
                	}
                	return s;
                }},*/
                { name: "title", desc: "活动名称",format:function(v,ob){
                	var s = v;
                	if(!s && ob.actionInfo.createId){
                		s = ob.actionInfo.title;
                	}
                	return s;
                }},
                /*
                { name: "userId", desc: "用户编号 ", 
                	format: function (v, ob) { 
                		return  v ;
                	}
                },*/
                { name: "rname", desc: "姓名 ", 
                	format: function (v, ob) { 
                		if(v && '' != v){
                			return v;
                		}else{
                			if(ob.userInfo && ob.userInfo.userId){
                				return ob.userInfo.lastName+ob.userInfo.firstName;
                			}else{
                				return v;
                			}
                			
                		}
                	}
                },
                { name: "rmobile", desc: "电话 ", 
                	format: function (v, ob) { 
                		if(v && '' != v){
                			return v;
                		}else{
                			if(ob.userInfo && ob.userInfo.userId){
                				return ob.userInfo.mobilePhone;
                			}else{
                				return v;
                			}
                		}
                	}
                },
                { name: "rschool", desc: "学校 ", 
                	format: function (v, ob) { 
                		
                		if(ob.schoolInfo && ob.schoolInfo.length > 0){
                			if(v != null & v != "" && v != ob.schoolInfo[0].name){
                				
                			}else{
                				v = ob.schoolInfo[0].name;
                			}
                		}
                		return v;
                	}
                },
                { name: "emaillAddress", desc: "邮箱", 
                    format: function (v, ob) {
                        return ob.userInfo.emailAddress;
                     }
                },
                /*
                { name: "openId", desc: "OpenId", 
                    format: function (v, ob) {
                        return v ;
                     } 
                },
                { name: "isLogged", desc: "签到状态", 
                	format: function (v) {
                	   if(v == 0){
                		   return '未签到';
                	   }
                       if(v == 1){
                           return '已签到';
                       }
                       return v;
                	} 
                },
                { name: "createDate", desc: "创建时间", 
                	format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm") ;} 
                },
                */
                { name: "loginDate", desc: "签到时间", 
                	format: function (v) { return v != null ?new Date(v).Format("yyyy-MM-dd hh:mm"):'未签到' ;} 
                },
                { name: "userType", desc: "用户来源", 
                	format: function (v) {
                	   if(v == 0){
                		   return '本地导入';
                	   }
                       if(v == 1){
                           return '游客';
                       }
                       return v;
                	} 
                },
                
        ];
    	var actions = {};
    	function getAction(){
    		var param = {page : <%=pageNum - 1 %>, pageSize: 50};
    		var paramList = [];
            for(var k in param){
                paramList.push(k+"="+param[k]);
            }
    		$.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-action-serv-portlet/action/api/search?"+paramList.join('&'),
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                	actions = data.obj;
                	initActionMenu(actions);
                }
            });
    	}
    	function initActionMenu(data){
    		var str = '';
    		for(var i = 0;i < data.length;i++){
    			str += '<option value="'+data[i].id+'" >'+data[i].title+'</option>';
    		}
    		
    		$('#actionList').append(str);
    	}
        function init(){
            //$('#actionId').val(<%=actionId%>);
            $('#sFlag').val(<%=sFlag%>);
        }
        init();
    
	    function search(){
	        var page = <%=pageNum%>;
	        if('<%=sFlag%>'!= $('#sFlag').val()){
	            page = 1;
	        }
	        window.location.href = '?isLogged='+$('#sFlag').val()+'&actionId=<%=actionId%>&page=' + page;
	    }
	    
	    function exportData(){
	    	var _id = '<%=actionId%>';
	    	
	    	window.location.href = "http://"+servHost+"/yxxs-action-serv-portlet/actionuser/export/exportExcel?actionId="+_id;
	    }
	    function exportPageData(){
	    	var cdate = (new Date()).Format("yyyy-M-d");
	    	var _tabcolumns = [
   	                  { name: "rname", desc: "姓名 ", 
   	                  	format: function (v, ob) { 
   	                  		if(v && '' != v){
   	                  			return v;
   	                  		}else{
   	                  			if(ob.userInfo && ob.userInfo.userId){
   	                  				return ob.userInfo.lastName+ob.userInfo.firstName;
   	                  			}else{
   	                  				return v;
   	                  			}
   	                  			
   	                  		}
   	                  	}
   	                  },
   	                  { name: "rmobile", desc: "电话 ", 
   	                  	format: function (v, ob) { 
   	                  		if(v && '' != v){
   	                  			return v;
   	                  		}else{
   	                  			if(ob.userInfo && ob.userInfo.userId){
   	                  				return ob.userInfo.mobilePhone;
   	                  			}else{
   	                  				return v;
   	                  			}
   	                  		}
   	                  	}
   	                  },
   	                  { name: "name", desc: "学校 ", 
   	                  	format: function (v, ob) { 
   	                  		if(ob.schoolInfo && ob.schoolInfo.length > 0){
   	                  			return ob.schoolInfo[0].name;
   	                  		}else{
   	                  			return '-';
   	                  		}
   	                  	}
   	                  },
   	                  { name: "loginDate", desc: "签到时间", 
   	                  	format: function (v) { return v != null ?new Date(v).Format("yyyy-MM-dd hh:mm"):'未签到' ;} 
   	                  },
   	                  { name: "userType", desc: "用户来源", 
   	                  	format: function (v) {
   	                  	   if(v == 0){
   	                  		   return '本地导入';
   	                  	   }
   	                         if(v == 1){
   	                             return '游客';
   	                         }
   	                         return v;
   	                  	} 
   	                  },
   	                  
   	          ];
	    	exportExcel(_tabcolumns,pageData.obj,cdate+'参加活动用户信息表');
	    }
	    /*
	    function exportExcel(columns,data){
	    	var str = "";
	    	for(var i = 0;i < columns.length;i++){
	    		str += columns[i].desc+",";
	    	}
	    	str = str.substring(0, str.length-1)+"\n";
	    	for(var i = 0;i < data.length;i++){
	    		for(var j=0;j<columns.length;j++){
					var v = data[i][columns[j].name];
					if(columns[j].format){
						v= columns[j].format(v,data[i]);
					}
					str += v + '\t,';
				}
	            str+='\n';
	    	}
	    	var uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str);
	    	var link = document.createElement("a");
	        link.href = uri;
	        //对下载的文件命名
	        link.download =  "json数据表.csv";
	        document.body.appendChild(link);
	        link.click();
	        document.body.removeChild(link);
	    }*/
        function initList() {
        	getAction();
            var param = {page : <%=pageNum - 1 %>, pageSize: 1000};
            param.actionId = '<%=actionId%>';
            if($('#actionList').val()){
                param.actionId = $('#actionList').val();
            }
            

            var paramList = [];
            for(var k in param){
                paramList.push(k+"="+param[k]);
            }
            //condition:"($parent.createId>0?true:false)",
            /*
            [
    {
        "classes": {
            "20417608": "火箭班"
        },
        "id": 3829408,
        "classCnt": 1,
        "name": "北京市朝阳区慧忠里小学"
    }
]
            */
            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-action-serv-portlet/actionuser/api/search?"+paramList.join('&'),
                        props:[
                            {
                                url:"/yxxs-action-serv-portlet/action/api/get_<%="${$parent.actionId}"%>",
                                alias:"actionInfo",
                                
                                parentType:"list",
                                parentExp:".obj",
                            },
                            {
                                url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.userId}" %>",
                                alias:"userInfo",
                                parentType:"list",
                                parentExp:".obj",
                            },
                            {
                                url:"/yxxs-main-portlet/api/userClass?userId=<%="${$parent.userId}" %>",
                                alias:"schoolInfo",
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
                        
                        pageUrlFunc: function(page){
                            return '?actionId=<%=actionId%>&sFlag='+$('#sFlag').val()+'&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
</body>
</html>