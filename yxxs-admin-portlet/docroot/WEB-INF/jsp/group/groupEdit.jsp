<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-admin-portlet/js/admin/userSearch.js?t=<%=ts%>" type="text/javascript"></script>
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    long createId = 0l;
    Cookie[] cookies = request.getCookies();
    for(int i = 0;i < cookies.length; i++){
    	if(cookies[i].getName().equals("USER_ID")){
    		createId = (new Long(cookies[i].getValue())).longValue();
    	}
    }
    %>
    
    <div class="container">
        <input type="hidden" id="id" name="id" value="<%=id%>">
        
        <div id="dataRow">
        </div>
        
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="saveData('<%=id %>');" class="btn btn-default">保存</button>
               </div>
            </div>
        </div>
    </div>
    
    <script type="text/template" id="actionTmpl">
        <div class="row">
<!--
            <div class="col-xs-2">
                专家ID
            </div>
            <div class="col-xs-6">
               <div class="form-group">
                  <input type="text" class="form-control" id="emailAddress" name="emailAddress" value=""
                     placeholder="请输入邮箱">
                    <span id="expertId" userId=""></span>
               </div>
            </div>
            <div class="col-xs-2">
               <div class="form-group">
                  <button type="button" class="btn btn-default" onclick="getUserId();">查询</button>
               </div>
            </div>
-->
        </div>
    </script>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script>
    var groupData = {};
    function getUserId(){
    	/*
        var setInfo = function(emailAddress){
            searchUserByEmail(emailAddress,function(d){
                $('#expertId').html(d.desc);
                $('#expertId').attr('userId',d.userId);
                $('#expertId').attr('emailAddress',d.emailAddress);
            });
        };*/
        var str = '';
        jQuery.ajax(
            {
                type: "post",
                url: "/yxxs-main-portlet/api/getUser",
                data:{
                	userId:'<%=createId%>'
                },
                dataType:'json',
                async:true,
                success: function(data){
                	if(data.error){
                		//alert('账户不存在');
                		str = '<%=createId%>';
                	}else{
                		str = data.lastName+data.firstName;
                	}
                    
                },
                error:function(){
                	//return '';
                }
            }
        );
        return str;
    }
    var cUser = '';
    jQuery.ajax(
        {
            type: "post",
            url: "/yxxs-main-portlet/api/getUser",
            data:{
            	userId:'<%=createId%>'
            },
            dataType:'json',
            async:false,
            success: function(data){
            	if(data.error){
            		//alert('账户不存在');
            		cUser = '<%=createId%>';
            	}else{
            		cUser = data.lastName+data.firstName;
            	}
                
            },
            error:function(){
            	//return '';
            }
        }
    );
    var cData = (new Date()).Format("yyyy-M-d h:m:s.S");
    </script>
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['title', '组名称'] },
            { name: ['code', '代码'],format:function(v,d){
            		return v;
            	}
            },
            { name: ['description', '简介'],type: 'textarea' },
            { name: ['status', '状态'],type:'dropdown',desc:'设置组是否需要校验'
            	,data: [{ k: 'valid', v: '校验' }, { k: 'invalid', v: '不校验' }]
            	,format:function(v,d){
            		if(d.status){
            			return d.status;
            		}else{
            			return 'invalid';
            		}
            	} 
            },
            
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "组信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');     

            var initFunc = function(data){
                initModelEdit('dataRow', data, tabInfoList, function(conf){
                });
            };
            
            if(<%=id %> == 0){
                initFunc({});
            }else{
            	$.ajax({
                    url:"/yxxs-package-serv-portlet/api/package",
                    dataType:"json",
                    type:"POST",
                    data:{
                        paramHashJsonStr:JSON.stringify({
                            url:"/yxxs-security-serv-portlet/api/model/get_GroupEntity_<%=id %>",
                            props:[]
                        })
                    },
                    async:false,
                    success: function (data) {
                   	 actionData = data;
                        initFunc(data);
                    }
               });
            }
        }
        initList();
    </script>
    
    <script>
    function saveData(id){
        var hash = getModelHash(tabInfoList);
        //hash.createId = '<%=createId%>';
        //hash.emailAddress = $('#expertId').attr('emailAddress');
        
        if(hash.status == null || hash.status == ''){
        	alert('请选择状态');
        	return
        }
        //hash.status = 1052;
        if(hash.title == null || hash.title == ''){
        	alert('请填写名称');
        	return
        }
        /*
        if(hash.appKey == null || hash.appKey == ''){
        	alert('请填写渠道/公众号');
        	return
        }
        
        if(hash.describe_ == null || hash.describe_ == ''){
        	alert('请填写简介');
        	return
        }
        if(hash.content == null || hash.content == ''){
        	alert('请填写活动内容描述');
        	return
        }*/
        if(hash.code == null || hash.code == ''){
        	alert('请填写代码');
        	return
        }
        var url = "/yxxs-security-serv-portlet/sec/group/api/add";
        if(id > 0){
        	//url += "?id="+id;
        	
        	if(groupData.status == hash.status  && groupData.description == hash.description 
        			&& groupData.title == hash.title && groupData.code == hash.code){
        		alert("没有任何修改！");
        		return 
        	}else{
        		hash.id = id;
        	}
        }
        $.ajax({
            url: url,
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                if(data.error){
                    alert('保存失败!');
                    return;
                }
                //alert("保存成功");
                window.location.href = '/yxxs-admin-portlet/admin/group/groupList';
            }
        });
    }
    </script>
</body>
</html>