<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="schoolName" value=""
                     placeholder="请输入学校">
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="userName" value=""
                     placeholder="请输入姓名">
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
	
    <script type="text/javascript">
        var PAGE_SIZE = 20;
        
        function search(page) {
            page = page?page:0;
            var param = {page:page, pageSize:PAGE_SIZE, type:11,orderCnd:'desc'};
            
            if($('#schoolName').val()){
            	param.schoolName = $('#schoolName').val();
            }
            if($('#userName').val()){
            	param.userName = $('#userName').val();
            }
            
            var paramArray = [];
            $.each(param,function(k,v){
            	paramArray.push(k+"="+v);
            });

            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-main-portlet/api/getTeachingLeaderList?"+paramArray.join('&'),
                        props:[
                            {
                                url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.teacherId}"%>",
                                alias:"userInfo",
                                parentType:"list",
                                condition:"(typeof $parent.teacherId != 'undefined' && $parent.teacherId != 0)",
                                parentExp:".obj",
                                props:[
                                       {
                                           url:"/yxxs-wx-serv-portlet/api/secure/jsonws/bindweixin/find-by-user-email?emailAddress=<%="${$parent.emailAddress}"%>",
                                           alias:"userWxInfo",
                                           parentExp:"",
                                           condition:"($parent.emailAddress?true:false)",
                                           props:[
                                                  {
                                                      url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?openid=<%="${$parent.bindWeixin.openId}"%>",
                                                      alias:"userWxInfo",
                                                      condition:"(($parent.bindWeixin && $parent.bindWeixin.openId)?true:false)",
                                                      parentExp:""
                                                  }   
                                           ],
                                           
                                       }
                                ]
                            },
                            {
                                url:"/yxxs-main-portlet/api/getSchool?orgId=<%="${$parent.schoolId}"%>&showClassCnt=false&showAddress=false&showStageLevel=false&showClasses=false",
                                alias:"schoolInfo",
                                parentType:"list",
                                condition:"(typeof $parent.schoolId != 'undefined' && $parent.schoolId != 0)",
                                parentExp:".obj"
                            }
                        ]
                    })
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                	//data.obj.reverse();
                	initTableList('dataRow', data, {
                        columns: [
                            { name: "teacherId", desc: "userId" },
                            { name: "screenName", desc: "姓名", format:function(v,ob){
                                var screenName = "";
                                if(ob.userInfo){
                                    screenName =  ob.userInfo.lastName + ob.userInfo.firstName;
                                }
                                return screenName;
                            }},
                            { name: "emailAddress", desc: "邮箱", format:function(v,ob){
                                if(ob.userInfo){
                                    return ob.userInfo.emailAddress;
                                }
                                return "";
                            } },
                            { name: "mobilePhone", desc: "电话", format:function(v,ob){
                                var mobilePhone = "--";
                                if(ob.userInfo){
                                    mobilePhone = ob.userInfo.mobilePhone;
                                }
                                return mobilePhone;
                            } },
                            { name: "school", desc: "学校", format:function(v,ob){
                                var name = "--";
                                if(ob.schoolInfo){
                                	name = ob.schoolInfo.name;
                                }
                                return name;
                            } },
                            { name: "nickName", desc: "绑定微信", format:function(v,ob){
                                var wxName = "未绑定";
                                if(ob.userInfo && ob.userInfo.userWxInfo && ob.userInfo.userWxInfo.bindWeixin){
                                    var openId = ob.userInfo.userWxInfo.bindWeixin.openId;
                                    if(openId && ob.userInfo.userWxInfo.userWxInfo.entity){
                                        wxName = ob.userInfo.userWxInfo.userWxInfo.entity.nickname;
                                    }
                                }
                                return wxName;
                            } },
                            { name: "startTime", desc: "加入时间", format:function(v,ob){
                                return new Date(v).Format("yyyy-MM-dd hh:mm:ss");
                            } },
                            { name: "status", desc: "状态", format:function(v,ob){
                            	var status = '';
                            	if(v == 1051){
                            		status = '已通过';
                            	}else{
                            		status = '未通过';
                            	}
                                return status;
                            } }
                        ],
                        operations: [],
                        pageUrlFunc: function(page){
                             return 'javascript:search('+(page-1)+');';
                        }
                    });
                }
            });
        }
        
    </script>
    
    <script>
	    $(function(){
	    	search();
	    });
    </script>
</body>
</html>