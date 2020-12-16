<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<script src="/yxxs-admin-portlet/js/admin/userSearch.js?t=<%=ts%>"
	type="text/javascript"></script>
<script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js"
	type="text/javascript"></script>

</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp"%>

	<%
	long id = 0l;
	try {
		id = Long.parseLong(request.getParameter("id"));
	} catch (Exception ex) {
	}

	long user_id = 0l;
	try {
		user_id = Long.parseLong(request.getParameter("user_id"));
	} catch (Exception ex) {
	}

	long createId = 0l;
	Cookie[] cookies = request.getCookies();
	for (int i = 0; i < cookies.length; i++) {
		if (cookies[i].getName().equals("USER_ID")) {
			createId = (new Long(cookies[i].getValue())).longValue();
		}
	}
	%>

	<div class="container">
		<input type="hidden" id="user_id" name="user_id" value="<%=user_id%>">
		<input type="hidden" id="privilege_code" name="privilege_code"
			value=""> 已选择权限：<input type="text" id="privilegeText"
			name="privilegeText" value="" /> </br>

		<div id="dataRow"></div>

		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<button type="button" onclick="saveData('<%=user_id%>');"
						class="btn btn-default">保存</button>
				</div>
			</div>

		</div>
	</div>

	<%@ include file="/WEB-INF/jsp/footer.jsp"%>

	<script>
    function trimSpace(array){  
        for(var i = 0 ;i<array.length;i++)  
        {  
            if(array[i] == "" || array[i] == " " || array[i] == null || typeof(array[i]) == "undefined")  
            {  
                     array.splice(i,1);  
                     i= i-1;  

            }  
        }  
        return array;  
   }  
    var configData = {};
    
    var cData = (new Date()).Format("yyyy-M-d h:m:s.S");
    </script>
	<script type="text/javascript">
    /**
    *暂时不支持对群组权限的编辑；只有创建和删除功能。
    
	    var groups = null;
	    var groupsArr = null;
	    function getAllUsers(){
	    	var url = "/yxxs-security-serv-portlet/api/model/list_UserEntity?page=0&pageSize=1000";
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
	    getAllUsers();*/
	    var privilegesArr = null;
	    var privileges = null;
	    function constructTree(){
	    	var tree = {};
	    	
	    	return tree;
	    }
	    
	    function getAllPrivileges(){
	    	var url = "/yxxs-security-serv-portlet/sec/privilege/item/api/list?page=0&pageSize=1000";
	    		//"/yxxs-security-serv-portlet/api/model/list_PrivilegeItemEntity?page=0&pageSize=1000";
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
			{ name: ['privilege_title', '权限'],
				format:function(v,ob){
					return v;
				} 
			},
			{ name: ['userEntity', '用户'],
				format:function(v,ob){
					return v?v.screenName:'';
				} 
			},
			{ name: ['privilege_code', '权限编码'],type:'checkbox'
            	,data: privilegesArr
            	,format:function(v,ob){
            		return v;
            	}  
			}
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "用户-权限配置信息", data: fieldInfoList }
        ];
        function getUserPrivileges(user_id){
        	var url = "/yxxs-security-serv-portlet/sec/user/privilege/api/search?page=0&pageSize=1000";
        	var list = null;
        	$.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                data: {user_id:user_id},
                async:false,
                success: function (data) {
                	list = data.obj;
                }
            });
        	return list;
        }
        function buildTree(parentNode, datas) {
            for (var key = 0;key < datas.length;key++) {
                var data = datas[key];
                if (data.parent_id == parentNode.id) {
                    var node = {text: data.title,parent_id:data.parent_id, id: data.id,code:data.code,privilege_code:data.privilege_code,
                    		
        			        tags: [data.selected?'*已有权限':''],nodes: [], selectable: true};
                    parentNode.nodes.push(node);
                    buildTree(node, datas);
                }
            }

            if (parentNode.nodes.length == 0) {
                delete parentNode.nodes;
            }
        }
		
		/*
		解析权限父子关系，创建权限树层级关系
		返回权限树数据，以数组形式返回
		*/
        function parsePrivileges(user_id){
        	var rootTrees = [];
        	var tree1 = getUserPrivileges(user_id);
        	var tree2 = privileges.slice(0);
			for(var key = 0;key < tree1.length;key++){
				for(var k = 0;k < tree2.length;k++){
        			if(tree2[k].privilege_code == tree1[key].privilege_code){
        				tree2[k]['selected'] = true;
        			}
        		}
        	}
        	for(var i = 0;i < tree2.length;i++){
        		if(tree2[i].parent_id == 0){
        			var tree = {text: tree2[i].title, parent_id:0,id: tree2[i].id,code:tree2[i].code,privilege_code:tree2[i].privilege_code, 
        					
        			        tags: [tree2[i].selected?'*已有权限':''],nodes: [], selectable: true};
        			buildTree(tree, tree2);
        			rootTrees.push(tree);
        		}
        	}
        	
        	return rootTrees;
        }
        /*
        *构造树状权限菜单，包含checkbox功能
        *user_id = <%=user_id%>*/
        function getTree(divId,user_id) {
        	/*
        	var settedList = getUserPrivileges(user_id);
        	var tree1 = settedList;
        	var tree2 = privileges.slice(0);//clone;
        	for(var key = 0;key < tree1.length;key++){
        		for(var k = 0;k < tree2.length;k++){
        			if(tree2[k].privilege_code == tree1[key].privilege_code){
        				tree2 = tree2.slice(0,k).concat(tree2.slice(k+1));
        			}
        		}
        	}*/
        	$('#'+divId).html('<div id="knowledgeTree-set"></div><div id="knowledgeTree">未开通权限：</div>');
        	//var tree = {text: '权限', id: '0',code:'0',privilege_code:'', nodes: []};
            //buildTree(tree, tree2);
            var tree = parsePrivileges(user_id);
            $('#knowledgeTree').treeview({
                color: "#428bca",
                data: tree,
                showCheckbox: true,
                multiSelect: true,
                showTags:true,
                onNodeChecked: function (event, data) {
                	
                    console.log('dddd', data);
                    var str = $("#privilegeText").val();
                    var code = $('#privilege_code').val();
                    console.log('1', data.text+', privilege_code:'+data.privilege_code);
                    if(!data.selected){    
                    	if (str.length > 0) {
	                        $("#privilegeText").val(str + data.text + ',');
	                    } else {
	                        $("#privilegeText").val(data.text + ',');
	                    }
                    
                    	if (code.length > 0) {
                    		$("#privilege_code").val(code + data.privilege_code + ',');
	                    } else {
	                    	$("#privilege_code").val(data.privilege_code + ',');
	                    }
                    }
                    
                    console.log('2', str+', codes:'+code);
                },
                onNodeUnchecked: function (event, data) {
                    var str = $("#privilegeText").val();
                    var code = $('#privilege_code').val();
                    $("#privilegeText").val(str.replace(data.text + ",", ""));
                    $("#privilege_code").val(code.replace(data.privilege_code + ",", ""));
//                    console.log('content', str.substring(0, str.indexOf(data.text)));
                    console.log('content', str.replace(data.text + ",", "")+' code:'+code.replace(data.privilege_code+",",""));
                }
            });
        	
        	/*
            var params = {};
            params.id = '0';
            $.ajax({
                url: "/yxxs-security-serv-portlet/api/model/list_PrivilegeItemEntity?page=0&pageSize=1000", // 请求的URL
                dataType: 'json',
                type: "get",
                data: {},
                async: false,
                success: function (data) {
                    var tree = {text: '权限', id: '0', nodes: []};
                    buildTree(tree, data.data);
                    $('#knowledgeTree').treeview({
                        color: "#428bca",
                        data: [tree],
                        showCheckbox: true,
                        multiSelect: true,
                        onNodeChecked: function (event, data) {
                            console.log('dddd', data);
                            var str = $("#privilegeText").val();
                            var code = $('#privilege_code').val();
                            console.log('1', data.privilegeText+', code:'+code);
                            if (str.length > 0) {
                                $("#privilegeText").val(str + data.text + ',');
                            } else {
                                $("#privilegeText").val(data.text + ',');
                            }
                            if (code.length > 0) {
                                $("#privilege_code").val(str + data.privilege_code + ',');
                            } else {
                                $("#privilege_code").val(data.privilege_code + ',');
                            }
                            console.log('2', str+', codes:'+code);
                        },
                        onNodeUnchecked: function (event, data) {
                            var str = $("#privilegeText").val();
                            var code = $('#privilege_code').val();
                            $("#privilegeText").val(str.replace(data.title + ",", ""));
                            $("#privilege_code").val(code.replace(data.privilege_code + ",", ""));
//                            console.log('content', str.substring(0, str.indexOf(data.text)));
                            console.log('content', str.replace(data.title + ",", "")+' code:'+code.replace(data.privilege_code+",",""));
                        }
                    });
                }
            });*/
        }

        function hideDIV() {

//            $("#privilegeText").val(mdata.text);
            $("#hideDiv").hide();
        }
        function initList() {
            
            $('#dataRow').html('');     
			
            var initFunc = function(data){
                initModelEdit('dataRow', data, tabInfoList, function(conf){
                	
                });
            };
            
            if(<%=user_id%> == 0){
                initFunc({});
            }else{
                $.ajax({
                     url:"/yxxs-package-serv-portlet/api/package",
                     dataType:"json",
                     type:"POST",
                     data:{
                         paramHashJsonStr:JSON.stringify({
                             //url:"/yxxs-security-serv-portlet/api/model/get_UserPrivilegeEntity_<%=id%>",
                             url:"/yxxs-security-serv-portlet/sec/user/privilege/api/search?page=0&pageSize=100&user_id=<%=user_id%>",
                             props:[
	                               {
		                                url:"/yxxs-main-portlet/api/user_<%="${$parent.user_id}"%>",
		                                alias:"userEntity",
		                                condition:"($parent.user_id>0?true:false)",
		                                parentType:"list",
		                                parentExp:".obj",
		                            },
		                            {
		                                url:"/yxxs-security-serv-portlet/sec/privilege/item/api/search?page=0&pageSize=100&privilege_code=<%="${$parent.privilege_code}"%>",
		                                alias:"privilegeItemEntity",
		                                condition:"($parent.privilege_code?true:false)",
		                                parentType:"list",
		                                parentExp:".obj",
		                            }
                             ]
                         })
                     },
                     async:false,
                     success: function (data) {
                    	 if(data && data.obj.length > 0){
                    		 configData = data;
                             initFunc(data.obj[0]);
                    	 }else{
                    		 initFunc({});
                    	 }
                    	 
                     }
                });
            }
            $('#user_id').val(<%=user_id%>);
            if(<%=user_id%>){
            	var list1 = getUserPrivileges(<%=user_id%>);
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
            	getTree('privilege_codeDiv','<%=user_id%>');
            }
            
        }
        initList();
        /*
        function initCode(){
        	$('#user_id').onchange(function(){
        		
        	});
        }*/
    </script>

	<script>
    function saveData(id){
        var hash = getModelHash(tabInfoList);
        if(hash.user_id){
        	
        }else{
        	hash.user_id = '<%=user_id%>';
        }
        //var params = hash;
        var url = "/yxxs-security-serv-portlet/sec/user/privilege/api/add";
        var privilege_codes = $('#privilege_code').val();
        if(privilege_codes == null || privilege_codes == ''){//hash.privilege_code == null || hash.privilege_code == ''
        	alert('请选择权限');
        	return
        }else{
        	privilege_codes = privilege_codes.endsWith(',')?privilege_codes.substring(0,privilege_codes.length-1):privilege_codes;
        	var array_privilege_codes = privilege_codes.split(',');
        	var t = [];
        	for(var i in array_privilege_codes){//hash.privilege_code
        		for(var j in privilegesArr){
        			if(array_privilege_codes[i] == privilegesArr[j].k){
        				t[i] = privilegesArr[j].v;
        				//params.privilege_code = hash.privilege_code[i];
        				//params.privilege_title = privilegesArr[j].v;
        				doSave(url,{user_id:hash.user_id,privilege_code:privilegesArr[j].k,
        					privilege_title:privilegesArr[j].v});
        				break;
        			}
        		}
        	}
        	hash.privilege_title = t;
        }
        window.location.href = '/yxxs-admin-portlet/admin/group/userPrivilegeList?user_id=<%=user_id%>';
        
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
	<script type="text/javascript">
    /*权限菜单树*/
    
    </script>
</body>
</html>