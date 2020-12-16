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
        <input type="hidden" id="actionId" name="actionId" value="<%=id%>">
        <div id="dataRow">
        </div>
        <div class="row">
            <div class="col-xs-2">
                 签到对象选择
            </div>
            <div class="col-xs-6">
               <div class="form-group">
	               <textarea rows="4" cols="40" id="emailAddressList" class="form-control" placeholder="请输入邮箱列表,每次发送最大人数：750人, 一行一个, 如
admin@iyxxs.com
cywgy@iyxxs.com"></textarea>
               </div>
            </div>
            <div class="col-xs-2">
               <div class="form-group">
                  <button type="button" class="btn btn-default" onclick="searchUser();">查询</button>
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2">
            </div>
            <div class="col-xs-10">
                <div id="userTargetDiv">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2">
            </div>
            <div class="col-xs-10">
                <input type="checkbox" id="sendTarget_all" name="sendTarget" value=""><label for="sendTarget_all">全部绑定账号</label><br/>
                
                <input type="checkbox" id="sendTarget_t_a" name="sendTarget" value="maxinlan"><label for="sendTarget_t_a">马芯兰翼课项目</label>
                <input type="checkbox" id="sendTarget_t_b" name="sendTarget" value="xcxj"><label for="sendTarget_t_b">小初衔接课题组</label>
                <!--  
                <input type="checkbox" id="sendTarget_t_b" name="sendTarget" value="师B"><label for="sendTarget_t_b">师B</label>
                <input type="checkbox" id="sendTarget_t_c" name="sendTarget" value="师C"><label for="sendTarget_t_c">师C</label>
                <input type="checkbox" id="sendTarget_t_d" name="sendTarget" value="师D"><label for="sendTarget_t_d">师D</label>
                <input type="checkbox" id="sendTarget_t_e" name="sendTarget" value="师E"><label for="sendTarget_t_e">师E</label>
                &nbsp;
                <input type="checkbox" id="sendTarget_s_g" name="sendTarget" value="学G"><label for="sendTarget_s_g">学G</label>
                <br/>
                <input type="checkbox" id="sendTarget_t_w_1" name="sendTarget" value="无发布1"><label for="sendTarget_t_w_1">无发布1</label>
                <input type="checkbox" id="sendTarget_t_w_2" name="sendTarget" value="无发布2"><label for="sendTarget_t_w_2">无发布2</label>
                <input type="checkbox" id="sendTarget_t_w_3" name="sendTarget" value="无发布3"><label for="sendTarget_t_w_3">无发布3</label>
                &nbsp;
                <input type="checkbox" id="sendTarget_t_y_1" name="sendTarget" value="发一次1"><label for="sendTarget_t_y_1">发一次1</label>
                <input type="checkbox" id="sendTarget_t_y_2" name="sendTarget" value="发一次2"><label for="sendTarget_t_y_2">发一次2</label>
                <input type="checkbox" id="sendTarget_t_y_3" name="sendTarget" value="发一次3"><label for="sendTarget_t_y_3">发一次3</label>
                -->
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="saveData();" class="btn btn-default">保存</button>
               </div>
            </div>
        </div>
    </div>
    
    
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script>
    var actionData = {};
	function getActionUsers(){
    	
    }
    function getUserId(){
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
    
    </script>
    <script type="text/javascript">
    	var cData = (new Date()).Format("yyyy-M-d h:m:s.S");
    	var appKeys = {
    			getAppKeys : function(mode){
    				if(mode == '.dev'){
    					return [{ k: 'xcxj', v: '小初衔接课程改革项目' },{ k: 'maxinlan', v: '翼课项目' }, { k: 'local', v: '翻转教学'}];
    				}else{
    					return [{ k: 'xcxj', v: '小初衔接课题组' },{ k: 'maxinlan', v: '马芯兰翼课项目' }, { k: 'local', v: '优学向上'}];
    				}
    			},
    			geName : function(mode,k){
    				if(mode == '.dev'){
    					if(k == 'maxinlan'){
    						return '翼课项目';
    					}
    					if(k == 'xcxj'){
    						return '小初衔接课程改革项目';
    					}
    					if(k == 'local'){
    						return '翻转教学';
    					}
    				}else{
    					if(k == 'maxinlan'){
    						return '马芯兰翼课项目';
    					}
    					if(k == 'xcxj'){
    						return '小初衔接课题组';
    					}
    					if(k == 'local'){
    						return '优学向上';
    					}
    				}
    			}
    	};
    	var fieldInfoList = [
            { name: ['title', '活动名称'] },
            { name: ['createId', '创建人'],format:function(v,d){
            	if(d.createId){
            		v = d.createId+'('+d.userInfo.lastName+d.userInfo.firstName+'/'+d.userInfo.emailAddress+')';
            	}else{
            		v = cUser;
            	}
            	return v;
            } },
            { name: ['appKey', '渠道/公众号']
            	,format:function(v,d){
            		return appKeys.geName('<%=_mode%>',d.appKey);
            	}
            },
            { name: ['describe_', '简介'],type: 'textarea' },
            { name: ['content', '内容描述'],type: 'textarea' },
            /**/{ name: ['actionUserList', '已添加签到用户'],type: 'textarea' 
            	,format : function(v,d){
            		if(v.obj.length > 0){
                		var str = '';
                		$.each(v.obj,function(index, obj){
                			str += obj.userId +'('+ obj.userInfo.lastName+obj.userInfo.firstName+'/'+obj.userInfo.emailAddress+');';
                		});
                		return str;
                	}else{
                		return '';
                	}
            	}
            },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "基础信息", data: fieldInfoList }
        ];
        function selectedTarget(id){
    		var t = $('input[id='+id+']').is(':checked');
    		if(t){
    			addTargetUser($('#'+id).val());
    		}
    	}
		function initList() {
            
            $('#dataRow').html('');
            var initFunc = function(data){
            	initModelEdit('dataRow', data, tabInfoList, function(conf){
                });
            	
            	$('#sendTarget_t_a').attr('onclick',"selectedTarget('sendTarget_t_a')"); 
            	$('#sendTarget_t_b').attr('onclick',"selectedTarget('sendTarget_t_b')");
            };
            $.ajax({
                url:"/yxxs-package-serv-portlet/api/package",
                dataType:"json",
                type:"POST",
                data:{
                    paramHashJsonStr:JSON.stringify({
                        url:"/yxxs-action-serv-portlet/action/api/get_<%=id %>",
                        props:[
                            {
                                url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.createId}" %>",
                                alias:"userInfo",
                                parentExp:"",
                            },
                            {
                                url:"/yxxs-action-serv-portlet/actionuser/api/search?page=0&actionId=<%="${$parent.id}" %>",
                                alias:"actionUserList",
                                parentExp:"",
                                props:[
                                       {
                                    	   url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.userId}" %>",
                                    	   parentType:"list",
                                           alias:"userInfo",
                                           parentExp:".obj", 
                                       }
                                ]
                            }
                        ]
                    })
                },
                async:false,
                success: function (data) {
               	 	actionData = data;
                    initFunc(data);
                }
           });
		};
		initList();
    </script>
    
    <script>
    function isExist(str){
    	for(var i = 0;i < actionData.actionUserList.obj.length;i++){
    		if(str.indexOf(actionData.actionUserList.obj[i].userId) > -1){
        		alert("用户"+actionData.actionUserList.obj[i].userInfo.emailAddress+"已经在本次活动人员列表里，请不要重复添加。");
        		return true;
        	}
    	}
    	
    	return false;
    }
    function getHash(){
        var hash = getModelHash(tabInfoList);
        if('0' != '<%=id%>'){
            hash.id = <%=id%>;
        }
        
        //var sendTarget = {userIds:[],tags:[]};
        /*$.each($('input[name=sendTarget]:checked'),function(index, obj){
            sendTarget.tags[sendTarget.tags.length] = $(obj).val();
        });
        $.each($('input[name=sendTarget_u]:checked'),function(index, obj){
            sendTarget.userIds[sendTarget.userIds.length] = $(obj).val();
        });
        hash.sendTarget = JSON.stringify(sendTarget);*/
        hash.sendTarget = JSON.stringify(userInfos);
        hash.userType = 0;
        return hash;
    }
    
    function saveData(){
    	var hash = getHash();
        hash.createId = '<%=createId%>';
        hash.actionId = $('#actionId').val();
        if(isExist(hash.sendTarget)){
        	return;
        }
        var url = "/yxxs-action-serv-portlet/actionuser/api/add";
        
        $.ajax({
            url: url,
            type: "POST",
            dataType: "json",
            data: hash,
            traditional:true,
            success: function (data) {
                if(data.error){
                    alert('保存失败!');
                    return;
                }
                window.location.href = '/yxxs-admin-portlet/admin/action/actionList';
            }
        });
    }
    </script>
    <script>
    function searchUser(){
    	var emailList = $('#emailAddressList').val().split('\n');
    	if(emailList != null && emailList.length > 750){
    		alert('您要发送的用户数为 '+emailList.length+',已经超过一次发送750人的最大限制,请分批发送。');
            return;
    	}
        $.each(emailList,function(index, obj){
        	if(obj ==''){
        		return;
        	}
        	addUser(obj);
        });

        $('#emailAddressList').val('');
    }
    var userInfos = {};
    function addUserWithAppKey(emailAddress,appKey){
    	searchUserByEmailAppKey(emailAddress,appKey, function(data){
            if(!data.userId){
                alert('无此用户:'+emailAddress);
                return;
            }

            if($('#sendTarget_u_'+data.userId).length !=0){
                alert('已经添加:'+emailAddress);
                return;
            }
			/*
            if(!data.isBind){
                //alert('未绑定用户:'+emailAddress);
                return;
            }*/
            userInfos[data.uInfo.userId]=data.uInfo;
            var h = '<div>';
            h += '<input type="checkbox" name="sendTarget_u" id="sendTarget_u_'+data.userId+'" checked value="'+data.userId+'" style="display:none;">';
            h += '<label>'+data.desc+'</label>';
            h += '<a href="javascript:delUser('+data.userId+');">X</a>';
            h +="</div>";
                
            $('#userTargetDiv').append(h);
    	});
    }
    function addUser(emailAddress){

    	searchUserByEmail(emailAddress, function(data){
            if(!data.userId){
                alert('无此用户:'+emailAddress);
                return;
            }

            if($('#sendTarget_u_'+data.userId).length !=0){
                alert('已经添加:'+emailAddress);
                return;
            }
			/*
            if(!data.isBind){
                //alert('未绑定用户:'+emailAddress);
                return;
            }*/
            userInfos[data.uInfo.userId]=data.uInfo;
            var h = '<div>';
            h += '<input type="checkbox" name="sendTarget_u" id="sendTarget_u_'+data.userId+'" checked value="'+data.userId+'" style="display:none;">';
            h += '<label>'+data.desc+'</label>';
            h += '<a href="javascript:delUser('+data.userId+');">X</a>';
            h +="</div>";
                
            $('#userTargetDiv').append(h);
    	});
    }
    var classMembers = {};
	function getResearchClassMembers(classId){
		//var userList = {obj:[]/* ,pageCount: 1,pageNumber: 0,pageSize: 1000,totalCount: 0 */};
        $.ajax({
            url:"/yxxs-package-serv-portlet/api/package",
            data:{
                paramHashJsonStr:JSON.stringify({
                    url:"/yxxs-main-portlet/api/getClassMemberList?orgId="+classId+"&showBind=false&types=teacher,student",
                    props:[
                           {
                               url:'/yxxs-wx-serv-maxinlan-portlet/api/maxinlan/getBindWeixinList?toIds=<%="${$parent.userId}"%>&appKey=maxinlan',
                               alias:"subject",
                               parentType:"list",
                               parentExp:".teacherList"
                           },
                           {
                        	   url:'/yxxs-wx-serv-maxinlan-portlet/api/maxinlan/getBindWeixinList?toIds=<%="${$parent.userId}"%>&appKey=maxinlan',
                               alias:"subject",
                               parentType:"list",
                               parentExp:".studentList"
                           }
                       ]
                })
            },
            type:'post',
            dataType:'json',
            async:false,
            success:function(data){
            	classMembers = data;
            }
        });
    }
	
	function addTargetUser(appKey){
		var t = [];
		var rClassId = 0;
		if(appKey == 'maxinlan'){
			rClassId = '21410798';
			if(".dev" == '<%=_mode%>'){
				rClassId = '20869502';
			}
			
		}
		if(appKey == 'xcxj'){
			rClassId = '21473957';
			if(".dev" == '<%=_mode%>'){
				rClassId = '21316216';
			}
		}
		getResearchClassMembers(rClassId);
		t = classMembers.teacherList;
		t = t.concat(classMembers.studentList);
		for(var i = 0;i < t.length;i++){
			addUserWithAppKey(t[i].emailAddress,appKey);
		}
	}
    function delUser(userId){
    	delete userInfos[userId];
        $('#sendTarget_u_'+userId).parent().remove();
    }
    
    </script>
</body>
</html>