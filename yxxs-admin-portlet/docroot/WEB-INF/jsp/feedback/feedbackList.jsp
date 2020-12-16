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
    .sUserName{width:20% !important;}
    </style>
    
    <div class="container">
    </div>
    
    <%
    String sType = "";
    if(null != request.getParameter("sType")){
    	sType = request.getParameter("sType");
    }
    String sStatus = "";
    if(null != request.getParameter("sStatus")){
    	sStatus = request.getParameter("sStatus");
    }
    String sUserName = "";
    if(null != request.getParameter("sUserName")){
    	sUserName = request.getParameter("sUserName");
    }
    %>
    
    <%
    int year = DateTermUtil.getGradeStartYear();
    %>
    
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="sType">
                    <option value="" >全部类型</option>
                    <option value="100" >教师微信注册</option>
                    <option value="110" >用户重置密码</option>
                    <option value="120" >学校反馈-学校找不到</option>
                    <option value="121" >学校反馈-信息错误</option>
                    <option value="130" >建班申请</option>
                    <option value="140" >视频查看权限申请</option>
                    <option value="141" >临时会员学校权限申请</option>
                    <option value="142" >正式会员学校权限申请</option>
                  </select>
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="sStatus">
                    <option value="" >全部状态</option>
                    <option value="1051" >通过</option>
                    <option value="1052" >拒绝</option>
                    <option value="0" >未处理</option>
                  </select>
               </div>
            </div>
            
            <input id=sUserName type="text" class="form-control sUserName"  placeholder="反馈人姓名" value="" />
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
        function init(){
            $('#sType').val(<%=sType%>);
            $('#sStatus').val(<%=sStatus%>);
            $('#sUserName').val('<%=sUserName%>');
        }
        init();
    
	    function search(){
	        var page = <%=pageNum%>;
	        if('<%=sType%>'!= $('#sType').val() || '<%=sStatus%>'!= $('#sStatus').val() || '<%=sUserName%>'!= $('#sUserName').val()){
	            page = 1;
	        }
	        window.location.href = '?sType='+$('#sType').val()+'&sStatus='+$('#sStatus').val()+'&sUserName='+$('#sUserName').val()+'&page=' + page;
	    }
	    
	    function feedbackNotFindSchool(openId,feedbackId){
	    	if(!openId){
	    		alert('没有openId，不能发送方块消息！');
	    		return;
	    	}
	    	
	    	//commonFeedBack表中有openId，但用户已取消关注
	    	var rdata = getWeixinInfo(openId);
            if(rdata.subscribe == 0){
            	alert('用户已取消关注，不能发送方块消息！');
            	return;
            }
            
	    	window.location.href = "/yxxs-admin-portlet/admin/feedback/feedbackNotFindSchool?feedbackId=" + feedbackId;
	    }
	    
        function initList() {
            
            var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            if($('#sType').val()){
                param.type = $('#sType').val();
            }
            if(!isNaN($('#sStatus').val())){
                param.status = $('#sStatus').val();
            }
            if($('#sUserName').val()){
            	param.userName = $('#sUserName').val();
            }

            var paramList = [];
            for(var k in param){
                paramList.push(k+"="+param[k]);
            }
            
            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-main-portlet/api/feedback/getFeedBackList?"+paramList.join('&'),
                        props:[
                            {
                                url:"/yxxs-main-portlet/api/user_<%="${$parent.userId}"%>",
                                alias:"userInfo",
                                condition:"($parent.userId>0?true:false)",
                                parentType:"list",
                                parentExp:".obj"
                            },
                            {
                                url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?openid=<%="${$parent.openId}"%>&appKey=<%="${($parent.appKey == null?'':$parent.appKey)}"%>",
                                alias:"userWxInfo",
                                parentType:"list",
                                parentExp:".obj"
                            }
                        ]
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            {name:"channel",desc:"渠道",format:function(v,ob){
                            	var s = v;
                            	if(!s && ob.appKey){
                            		
                                    s = !ob.appKey || ob.appKey == '' || ob.appKey == null || ob.appKey == 'undefined'?'自有公众号':ob.appKey;
                            	}else{
                            		s = '自有公众号';
                            	}
                            	return s;
                            }},
                            { name: "userName", desc: "反馈人",format:function(v,ob){
                            	var s = v;
                            	if(!s && ob.userId){
                            		var wdata = ob.userInfo;
                            		if(wdata){
                                        s = wdata.screenName + "<br/>" + wdata.emailAddress;
                            		}
                            	}
                            	if(!s){
                            		s = "";
                            	}

                                if(ob.openId){
                                	if(ob.userWxInfo && ob.userWxInfo.entity){
                                        var rdata = ob.userWxInfo.entity;
                                        if(rdata.nickname && rdata.nickname != ''){
                                            s += "<br/>"+"微信:"+rdata.nickname;
                                        }
                                	}
	                            }
                           	    
                            	return s;
                            }},
                            { name: "concat", desc: "联系方式", 
                            	format: function (v, ob) { 
                            		var s = "";
                            		if(ob.phone){
                            			s+=" 手机:"+ob.phone;
                            		}
                                    if(ob.qq){
                                        s+=" qq:"+ob.qq;
                                    }
                                    if(ob.emailAddress){
                                        s+="<br/>email:"+ob.emailAddress;
                                    }
                                    if(ob.address){
                                        s+="<br/>地址:"+ob.address;
                                    }
                            		return  s ;
                            	} 
                            },
                            { name: "content", desc: "内容描述", 
                                format: function (v, ob) {
                                	
                                    if(ob.type == 110){
                                    	var cdata = JSON.parse(ob.content);
                                        return cdata.schoolName + ","+cdata.className;
                                    }
                                    if(ob.type == 120 || ob.type == 121){
                                    	var cdata = JSON.parse(ob.content);
                                    	var s = cdata.schoolName;
                                    	if(cdata.schoolStage){
                                    		s +=  "<br/>"+cdata.schoolStage;
                                    	}
                                        return  s;
                                    }
                                    if(ob.type == 130){
                                    	var s = feedBackClassAddDesc(ob);
                                    	return s;
                                    }
                                    if(ob.type == 140){
                                    	var s = parsGroupApply(ob);
                                    	return s;
                                    }
                                    if(ob.type == 141){
                                    	var s = "临时会员权限申请消息";
                                    	return s;
                                    }
                                    if(ob.type == 142){
                                    	var s = "正式会员权限申请消息";
                                    	return s;
                                    }
                                    return '--' ;
                                 } 
                            },
                            { name: "type", desc: "类型", 
                            	format: function (v) {
                            	   if(v == 100){
                            		   return '教师微信注册';
                            	   }
                                   if(v == 110){
                                       return '用户重置密码';
                                   }
                                   if(v == 120){
                                       return '学校反馈-学校找不到';
                                   }
                                   if(v == 121){
                                       return '学校反馈-信息错误';
                                   }
                                   if(v == 130){
                                       return '建班申请';
                                   }
                                   if(v == 140){
                                       return '视频查看组申请';
                                   }
                                   if(v == 141){
                                       return '临时会员权限申请消息';
                                   }
                                   if(v == 142){
                                       return '正式会员权限申请消息';
                                   }
                            	   return '--' ;
                            	} 
                            },
                            { name: "cnt", desc: "处理记录数"},
                            { name: "status", desc: "最后处理状态", format: function (v,obj) { 
                            	if(obj.type == 120 && v == 1051){
                                    return '已回复';
                                }
                                if(v == 1051){
                                    return '已通过';
                                }
                                if(v == 1052){
                                    return '已拒绝';
                                }
                            	return '--';
                           	} },
                            { name: "createTime", desc: "创建时间", format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} },
                        ],
                        operations: [{
                            name: "处理反馈",
                            visable: function (obj) { return true;},
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/feedback/feedbackForm?id=" + obj.id; }
                        },{
                        	name: "学校信息反馈",
                        	visable:function(obj){
                        		var visble = false;
                        		if(obj.type == 120){
                        			visble = true;
                        		}
                        		return visble;
                        	},
                        	getUrl: function(obj){ return "javascript:feedbackNotFindSchool('"+obj.openId+"',"+obj.id+")"; }
                        }],
                        pageUrlFunc: function(page){
                            return '?sType='+$('#sType').val()+'&sStatus='+$('#sStatus').val()+'&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
</body>
<script>
function feedBackClassAddDesc(feedback){
	var s = "";
	
	feedback.classInfo = JSON.parse(feedback.content);

	var sinfo = getSchoolInfo(feedback.classInfo.schoolId,{address:true});
	if(sinfo.addressInfo){
	    s+=sinfo.addressInfo.pName + "-" + sinfo.addressInfo.cName + "-"+sinfo.addressInfo.dName + "<br/>";
	}
    s+=sinfo.name + "<br/>";
	
    s+=feedback.classInfo.className + ","+ (<%=year%> + 1001 - feedback.classInfo.grade) + "年入学<br/>";

    return s;
}
function parsGroupApply(feedback){
	var s = "";
	
	feedback.Info = JSON.parse(feedback.content);
	
	var groupInfo = getGroupInfo(feedback.Info.groupId);
	if(groupInfo){
	    s+="群组名称:"+groupInfo.groupName + "<br/>";
	}
    s+="申请人："+feedback.Info.userName+"("+feedback.Info.email+")" + "<br/>";
	
    s+="视频："+feedback.Info.videoName+"("+feedback.Info.videoId+")" + "<br/>";

    return s;
}

function getGroupInfo(groupId){
	var temp = null;
	$.ajax({
		type: "post",
        url: "/yxxs-teach-material-serv-portlet/api/model/get_TeachMaterialExplanationGroup_"+groupId,
        data:{},
        dataType:'json',
        async:false,
        success: function(data){
        	temp = data;
        }
	});
	return temp;
}
</script>
</html>