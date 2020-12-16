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
    var actionData = {};
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
    	var appKeys = {
    			getAppKeys:function(mode){
    				if(mode == '.dev'){
    					return [{ k: 'xcxj', v: '小初衔接课程改革项目' },{ k: 'maxinlan', v: '翼课项目' }, { k: 'local', v: '翻转教学'}];
    				}else{
    					return [{ k: 'xcxj', v: '小初衔接课题组' },{ k: 'maxinlan', v: '马芯兰翼课项目' }, { k: 'local', v: '优学向上'}];
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
            { name: ['appKey', '渠道/公众号'],type:'dropdown'
            	,data:appKeys.getAppKeys('<%=_mode%>')
            	,format:function(v,d){
            		if(d.appKey){
            			return d.appKey;
            		}else{
            			return 'maxinlan';
            		}
            	}
            },
            { name: ['describe_', '简介'],type: 'textarea' },
            { name: ['content', '内容描述'],type: 'textarea' },
            { name: ['url', '活动页面'],format:function(v,d){
            	if(d.url){
            		return d.url;
            	}else{
            		return "/yxxs-wx-portlet/maxinlan/action/actionInfo?actionId="+d.id;
            	}
            } },
            { name: ['flag', '发布状态'],type:'dropdown',desc:'活动发布状态'
            	,data: [{ k: 0, v: '未指定/过期' }, { k: 1051, v: '待审核' }, { k: 1052, v: '已审核'}]
            	,format:function(v,d){
            		if(d.flag){
            			return d.flag;
            		}else{
            			return '1051';
            		}
            	} 
            },
            { name: ['createDate', '创建时间'],desc:'yyyy-MM-dd'
            	,format:function(v,d){
	            	if(d.createDate){
	            		return new Date(v).Format("yyyy-MM-dd hh:mm:ss");//d.createDate;
	            	}else{
	            		return cData;
	            	}
            	} 
            },
            { name: ['startDate', '起始时间'],desc:'yyyy-MM-dd',data: '',format:function(v,d){
            	if(d.startDate){
            		return new Date(v).Format("yyyy-MM-dd");//d.startDate;
            	}
            }},
            { name: ['endDate', '结束时间'],desc:'yyyy-MM-dd',data: '',format:function(v,d){
            	if(d.endDate){
            		return new Date(v).Format("yyyy-MM-dd");// d.endDate;
            	}
            } },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "活动信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');     

            var initFunc = function(data){
                initModelEdit('dataRow', data, tabInfoList, function(conf){
                	if(conf.FileName == 'startDate'){
                		conf.desc = conf.data.startDate;
                	}
                	if(conf.FileName == 'endDate'){
                		conf.desc = conf.data.endDate;
                	}
                });
                $('#startDate').attr('style',"width:120px");
                //$('#startDate').attr('readonly',"readonly");
                $('#startDate').attr('type',"text");
                $('#startDate').attr('onclick',"WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:false})");
                
                $('#endDate').attr('style',"width:120px");
                $('#endDate').attr('readonly',"readonly");
                $('#endDate').attr('type',"text");
                $('#endDate').attr('onclick',"WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:false})");
                
				/*
                var uEJS = new EJS({ element: "actionTmpl" });
                $('#baseTab').append(uEJS.render({}));

                searchUserById(data.userId,function(d){
                    $('#expertId').html(d.desc);
                    $('#expertId').attr('userId',d.userId);
                    $('#expertId').attr('emailAddress',d.emailAddress);
                });*/
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
                             url:"/yxxs-action-serv-portlet/action/api/get_<%=id %>",
                             props:[
                                 {
                                     url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.createId}" %>",
                                      alias:"userInfo",
                                      parentExp:"",
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
            }
        }
        initList();
    </script>
    
    <script>
    function saveData(id){
        var hash = getModelHash(tabInfoList);
        hash.createId = '<%=createId%>';
        //hash.emailAddress = $('#expertId').attr('emailAddress');
        if(hash.flag == null || hash.flag == ''){
        	alert('请选择活动状态');
        	return
        }
        if(hash.title == null || hash.title == ''){
        	alert('请填写活动名称');
        	return
        }
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
        }
        if(hash.startDate == null || hash.startDate == ''){
        	alert('请选择活动起始时间');
        	return
        }
        if(hash.endDate == null || hash.endDate == ''){
        	alert('请选择活动结束时间');
        	return
        }
        var url = "/yxxs-action-serv-portlet/action/api/create";
        if(id > 0){
        	url = "/yxxs-action-serv-portlet/action/api/update";
        	hash.id = id;
        	if(hash.flag == actionData.flag){
        		hash.type = 0;
        	}else{
        		hash.type = 1;
        	}
        	var _startDate = new Date(actionData.startDate).Format("yyyy-MM-dd");
        	var _endDate = new Date(actionData.endDate).Format("yyyy-MM-dd");
        	if(actionData.flag == hash.flag && _startDate == hash.startDate &&  _endDate == hash.endDate 
        			&& actionData.content == hash.content && actionData.describe_ == hash.describe_ 
        			&& actionData.appKey == hash.appKey && actionData.title == hash.title){
        		alert("活动没有任何修改！");
        		return 
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
                window.location.href = '/yxxs-admin-portlet/admin/action/actionList';
            }
        });
    }
    </script>
</body>
</html>