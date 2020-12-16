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
    
    long group_id = 0l;
    try{
    	group_id = Long.parseLong(request.getParameter("group_id"));
    }catch(Exception ex){}
    String group_title = "";
    if(null != request.getParameter("group_title")){
    	group_title = request.getParameter("group_title");
    }
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
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script>
    var configData = {};
    
    var cData = (new Date()).Format("yyyy-M-d h:m:s.S");
    </script>
    <script type="text/javascript">
    /**
    *暂时不支持对群组权限的编辑；只有创建和删除功能。
    */
	    var groups = null;
	    var groupsArr = null;
	    function getAllGroups(){
	    	var url = "/yxxs-security-serv-portlet/api/model/list_GroupEntity?page=0&pageSize=1000";
	    	$.ajax({
	            url: url,
	            type: "POST",
	            dataType: "json",
	            data: {},
	            async:false,
	            success: function (data) {
	            	groups = data.obj;
	            	groupsArr = [groups.length];
	            	for(var i in groups){
	            		groupsArr[i] = {};
	            		groupsArr[i]['k'] = groups[i].id;
	            		groupsArr[i]['v'] = groups[i].title;
	            	}
	            }
	        });
	    }
	    getAllGroups();
	    var privilegesArr = null;
	    var privileges = null;
	    function getAllPrivileges(){
	    	var url = "/yxxs-security-serv-portlet/api/model/list_PrivilegeItemEntity?page=0&pageSize=1000";
	    	$.ajax({
	            url: url,
	            type: "POST",
	            dataType: "json",
	            data: {},
	            async:false,
	            success: function (data) {
	            	privileges = data.obj;
	            	privilegesArr = [privileges.length];
	            	for(var i in privileges){
	            		privilegesArr[i] = {};
	            		privilegesArr[i]['k'] = privileges[i].privilege_code;
	            		privilegesArr[i]['v'] = privileges[i].title;
	            	}
	            }
	        });
	    }
	    getAllPrivileges();
        var fieldInfoList = [
			{ name: ['group_id', '组'],type:'dropdown'
				,data: groupsArr
				,format:function(v,d){
					if(v == '<%=group_id%>'){
						return v;
					}
					return v;
				} 
			},
            { name: ['privilege_code', '权限编码'],type:'checkbox'
            	,data: privilegesArr
            	,format:function(v,d){
            		return v;
            	}  },
            //{ name: ['privilege_code', '权限编码']},
            
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "组-权限配置信息", data: fieldInfoList }
        ];
        function getGroupPrivileges(group_id){
        	var url = "/yxxs-security-serv-portlet/sec/group/privilege/api/search?page=0&pageSize=1000";
        	var list = null;
        	$.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                data: {group_id:group_id},
                async:false,
                success: function (data) {
                	list = data.obj;
                }
            });
        	return list;
        }
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
                             url:"/yxxs-security-serv-portlet/api/model/get_GroupPrivilegeEntity_<%=id %>",
                             props:[
	                               {
		                                url:"/yxxs-security-serv-portlet/api/model/get_GroupEntity_<%="${$parent.group_id}"%>",
		                                alias:"groupEntity",
		                                condition:"($parent.group_id>0?true:false)",
		                                parentType:"list",
		                                parentExp:".obj",
		                            },
		                            {
		                                url:"/yxxs-security-serv-portlet/sec/privilege/item/api/search?privilege_code=<%="${$parent.privilege_code}"%>",
		                                alias:"privilegeItemEntity",
		                                condition:"($parent.privilege_code>0?true:false)",
		                                parentType:"list",
		                                parentExp:".obj",
		                            }
                             ]
                         })
                     },
                     async:false,
                     success: function (data) {
                    	 configData = data;
                         initFunc(data.obj[0]);
                     }
                });
            }
            $('#group_id').val(<%=group_id%>);
            if(<%=group_id%>){
            	var list1 = getGroupPrivileges(<%=group_id%>);
            	var list2 = document.getElementsByName('privilege_code');
            	var str = "已开通权限：";
            	for(var i = 0;i < list1.length;i++ ){
            		for(var j = 0;j < list2.length;j++ ){
            			if(list1[i].privilege_code == list2[j].value){
            				str += list1[i].privilege_title+",";
            				var elem=document.getElementById(list2[j].id); // 按 id 获取要删除的元素
							var p = elem.parentNode;
            				var next = elem.nextElementSibling;
            				p.removeChild(next);
            				p.removeChild(elem);// 让 “要删除的元素” 的 “父元素” 删除 “要删除的元素”
                			//list2[i].checked = true;
                			
                			break;
                		};
            		}
            	}
            	$('#privilege_codeDiv').prepend(str+"<br>未开通权限：");
            }
            
        }
        initList();
        /*
        function initCode(){
        	$('#group_id').onchange(function(){
        		
        	});
        }*/
    </script>
    
    <script>
    function saveData(id){
        var hash = getModelHash(tabInfoList);
        //var params = hash;
        var url = "/yxxs-security-serv-portlet/sec/group/privilege/api/add";
        
        if(hash.privilege_code == null || hash.privilege_code == ''){
        	alert('请填选择权限');
        	return
        }else{
        	var t = [];
        	for(var i in hash.privilege_code){
        		for(var j in privilegesArr){
        			if(hash.privilege_code[i] == privilegesArr[j].k){
        				t[i] = privilegesArr[j].v;
        				//params.privilege_code = hash.privilege_code[i];
        				//params.privilege_title = privilegesArr[j].v;
        				doSave(url,{group_id:hash.group_id,privilege_code:hash.privilege_code[i],
        					privilege_title:privilegesArr[j].v});
        				break;
        			}
        		}
        	}
        	hash.privilege_title = t;
        }
        window.location.href = '/yxxs-admin-portlet/admin/group/groupPrivilegeList?group_id=<%=group_id%>&group_title='+hash.privilege_title;
        
    }
    function doSave(url,hash){
    	var rs = false;
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
                rs = true;
            }
        });
    	return rs;
    }
    
    </script>
</body>
</html>