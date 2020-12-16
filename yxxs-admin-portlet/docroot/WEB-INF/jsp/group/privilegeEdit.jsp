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
    String op = "";
    		
	try{
	       op = request.getParameter("op");
	   }catch(Exception ex){}
    %>
    
    <div class="container">
        <input type="hidden" id="id" name="id" value="<%=id%>">
        <input type="hidden" id="parent_id" name="parent_id" value="<%=op != null && !op.equals("")?id:0l%>">
        
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
    var actionData = {};
    var op = '<%=op%>'
    var cData = (new Date()).Format("yyyy-M-d h:m:s.S");
    </script>
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['title', '名称'] },
            { name: ['description', '简介'],type: 'textarea' },
            { name: ['privilege_code', '编码'] },
            { name: ['code', '数字编码']
            },
            { name: ['url', '地址'] },
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
            { name: "baseTab", desc: "权限信息设置", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');     

            var initFunc = function(data){
                initModelEdit('dataRow', data, tabInfoList, function(conf){
                	
                });
            };
            
            if(<%=id %> == 0 || (op == 'addSub')){
                initFunc({});
                
            }else{
                $.ajax({
                     url:"/yxxs-package-serv-portlet/api/package",
                     dataType:"json",
                     type:"POST",
                     data:{
                         paramHashJsonStr:JSON.stringify({
                             url:"/yxxs-security-serv-portlet/api/model/get_PrivilegeItemEntity_<%=id %>",
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
        
        if(hash.title == null || hash.title == ''){
        	alert('请填写名称');
        	return
        }
        
        var url = "/yxxs-security-serv-portlet/sec/privilege/item/api/add";
        if(id > 0){
        	hash.id = id;
        	if(actionData.title == hash.title && actionData.privilege_code == hash.privilege_code
        			&& actionData.status == hash.status && actionData.code == hash.code
        			&& actionData.url == hash.url){
        		alert("权限没有任何修改！");
        		return 
        	}
        }
        if('<%=op%>' == 'addSub'){
        	hash.id = '';
        	hash.parent_id = $('#parent_id').val();
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
                window.location.href = '/yxxs-admin-portlet/admin/group/privilegeList';
            }
        });
    }
    </script>
</body>
</html>