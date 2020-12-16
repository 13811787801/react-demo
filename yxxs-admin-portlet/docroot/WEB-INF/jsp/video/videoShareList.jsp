<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    %>
    
    <div class="container">
    </div>
    <%if(id != 0){ %>
    <div class="container">
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="javascript:startShareKeyword(<%=id%>);">创建分享</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    <%} %>
    
    <style>
    .layerDiv{margin:0 15px; padding-top:10px;}
    </style>
    
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
	var tokenList = null;
    var groupInfo = null;/*id,groupName*/
    var accessTypeArr = [{k:'',v:'无限制'},{k:'group.wechatFans',v:'公众号关注粉丝'},{k:'group.bindUsers',v:'微信绑定用户'}];
    function initGroupInfo(){
    	$.ajax({
            url: "/yxxs-package-serv-portlet/api/package",
            type: "POST",
            dataType: "json",
            data: {
            	paramHashJsonStr:JSON.stringify({
            		url:"/yxxs-teach-material-serv-portlet/api/model/list_TeachMaterialExplanationGroup?page=0&pageSize=10",
            		//url:"/yxxs-teach-material-serv-portlet/api/getMaterialGroupList?page=0&pageSize=10&materialId=<%=id%>",
            		/*props:[
            		       {
            		    	   //url:"/yxxs-teach-material-serv-portlet/api/model/list_TeachMaterialExplanationGroup?page=0&pageSize=10",
            		    	   url:"/yxxs-teach-material-serv-portlet/api/model/get_TeachMaterialExplanationGroup_<%="${$parent.groupId}" %>",
            		    	   alias:"groupInfo",
                               parentType:"list",
                               parentExp:".obj",
            		       }
          		    ]*/
            	})
            },
            success: function (data) {
            	groupInfo = data;
            	var str = null;
            	for(var i in groupInfo.obj){
            		str = {k:groupInfo.obj[i].id,v:groupInfo.obj[i].groupName};
            		//str = {k:groupInfo.obj[i].groupInfo.id,v:groupInfo.obj[i].groupInfo.groupName};
            		accessTypeArr.push(str);
            	}
            }
        });
    }
    initGroupInfo();
        function initList() {
        	var param = {page:<%=pageNum - 1 %>,pageSize:10};
        	param.objectName = "VideoShow";
            <%if(id != 0){ %>
            param.objectId = <%=id%>;
            <%}%>
            
            var paramList = [];
            for(var k in param){
                paramList.push(k+"="+param[k]);
            }
            
            $.ajax({
                url: "/yxxs-package-serv-portlet/api/package",
                type: "POST",
                dataType: "json",
                data: {
                	paramHashJsonStr:JSON.stringify({
                		url:"/yxxs-main-serv-portlet/api/listToken?"+paramList.join('&'),
                		props:[
                		       {
                		    	   url:"/yxxs-main-serv-portlet/api/model/get_VideoShow_<%="${$parent.objectId}" %>",
                		    	   alias:"videoShowInfo",
                                   parentType:"list",
                                   parentExp:".obj",
	                           		props:[
	                           		       {
	                           		    	   url:"/yxxs-file-serv-portlet/api/getFile_<%="${$parent.fileEntityId}"%>",
	                           		    	   alias:"fileInfo",
	                           		    	   condition:"($parent.fileEntityId>0)"
	                           		       },
	                           		       {
	                           		    	   url:"/yxxs-main-serv-portlet/api/getTargetObjectTagList_VideoShow_<%="${$parent.id}"%>",
	                           		    	   alias:"tagList",
	                           		    	   condition:"($parent.id>0)",
	                                          		props:[
	                                       		       {
	                                       		    	   url:"/yxxs-main-serv-portlet/api/model/get_KeyWordTag_<%="${$parent.tagId}"%>",
	                                       		    	   alias:"keywordTag",
	                                       		    	   parentType:"list",
	                                       		    	   parentExp:""
	                                       		       }
	                                     		    ]
	                           		       }
                         		    ]
                		       }
              		    ]
                	})
                },
                success: function (data) {
                	tokenList = data.obj;
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "videoTitle", desc: "视频名称", format: function(v, ob){return ob.videoShowInfo.videoTitle} },
                            { name: "createDate", desc: "分享时间", format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} },
                            { name: "accessName", desc: "访问权限", format: function (v) {
                            	if(v.indexOf(',') > -1){
                            		var accessNameArr = v.split(',');
                            		for(var m in accessNameArr){
                            			for(var i in accessTypeArr){
                                    		if(accessNameArr[m] == accessTypeArr[i].k){
                                    			accessNameArr[m] = accessTypeArr[i].v;
                                    		}
                                    	}
                            		}
                            		return accessNameArr.join(',');
                            	}else{
                            		for(var i in accessTypeArr){
                                		if(v == accessTypeArr[i].k){
                                			return accessTypeArr[i].v;
                                		}
                                	}
                            	}
                            	
                            	/*
                            	if(v =='group.wechatFans'){
                            		return '公众号关注粉丝';
                            	}
                            	if(v =='group.bindUsers'){
                            		return '微信绑定用户';
                            	}*/
                            	return '无限制' ;
                           	} },
                            { name: "expireTime", desc: "分享过期时间", format: function (v, obj) {
                            	if(obj.flag == -1){
                            		return '强制过期';
                            	}
                            	if(v){
                                	return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;
                            	}
                            	return '-';
                           	} },
                        ],
                        operations: [{
                            name: "添加群组",
                            visable: function (obj) { return true; },
                            getUrl: function (obj) { 
                                return "javascript:addShareKeyword('"+obj.token+"')";
                            }
                        },{
                            name: "生成链接",
                            visable: function (obj) { return true; },
                            getUrl: function (obj) { 
                            	var url = "http://"+servHost+'/yxxs-wx-portlet/review/videoShow/videoReview?token='+obj.token;
                                return "javascript:showLink('"+url+"')";
                            }
                        },{
                            name: "强制失效",
                            visable: function (obj) { return (obj.flag != -1?true:false); },
                            getUrl: function (obj) { 
                                return "javascript:expireShare('"+obj.token+"')";
                            }
                        }],
                        pageUrlFunc: function(page){
	                        return '?&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
</body>
<script>

function expireShare(token){

    $.ajax({
        url: "/yxxs-main-serv-portlet/api/updateToken",
        type: "POST",
        dataType: "json",
        data: {token:token,flag:-1},
        success: function (data) {
            alert("强制失效成功");
            initList();
        }
    });
}

<%if(id != 0){ %>
function startShareKeyword(id){
    var shareInfoList = [
        { name: ['expireTimeArea', '截止时间'], mode: 'view', format:function(v, ob){
            return ''+ 
            	'<input type="text" id="expireTime" class="expireTime form-control" readonly '+
            	' onclick="WdatePicker({isShowClear:true,minDate:\'%y-%M-%d %H:%m\',dateFmt:\'yyyy-MM-dd HH:mm\',readOnly:true})" />'+
	            '';
        } },
        { name: ['accessType', '访问权限'], type: 'dropdown',
        	data: accessTypeArr
        },
        { name: ['btn', ''], mode: 'view', format:function(v, ob){
            return ''+ 
            	'<input type="hidden" class="id" value="'+(ob.id?ob.id:"")+'">'+
	            '<button onclick="saveData()" class="btn btn-default" >保存</button>'+
	            '';
        } },
    ];

    var shareTabInfoList = [
        { name: "keywordTab", desc: "分享信息", data: shareInfoList },
    ];
	
	var index = layer.open({
		  type: 1,
		  title: false,
		  area: ['650px', '300px'],
		  closeBtn: 1,
		  shadeClose: true,
		  scrollbar: false,
		  content: '<div id="shareDiv" class="layerDiv"></div>'
		});
    $('#shareDiv').attr('layerIndex',index);

    initModelEdit('shareDiv', {id:id}, shareTabInfoList, function(conf){
    });
}

function saveData(){

    var hash = {objectName:"VideoShow"};
    hash.objectId = $('#shareDiv .id').val();
    var expireTime = $('#shareDiv .expireTime').val();
    if(expireTime){
    	hash.expireTime = expireTime;
    }
    var accessType = $('#accessType').val();
    if(accessType){
        hash.accessName = accessType;
        hash.accessId = accessType;
    }
    
    $.ajax({
        url: "/yxxs-main-serv-portlet/api/createTokenByEndTime",
        type: "POST",
        dataType: "json",
        data: hash,
        success: function (data) {
            alert("保存成功");
            
            layer.closeAll();
            $('#shareDiv').attr('layerIndex',null);
            
            initList();
        }
    });
}

function addShareKeyword(token){
	
    var shareInfoList = [
        { name: ['accessType', '访问权限'], type: 'dropdown',
        	data: accessTypeArr
        },
        { name: ['btn', ''], mode: 'view', format:function(v, ob){
            return ''+ 
            	'<input type="hidden" class="id" value="'+(ob.id?ob.id:"")+'">'+
            	'<input type="hidden" class="token" value="'+(ob.token?ob.token:"")+'">'+
	            '<button onclick="addGroupData()" class="btn btn-default" >添加</button>'+
	            '';
        } },
    ];

    var shareTabInfoList = [
        { name: "keywordTab", desc: "添加群组", data: shareInfoList },
    ];
	
	var index = layer.open({
		  type: 1,
		  title: false,
		  area: ['650px', '300px'],
		  closeBtn: 1,
		  shadeClose: true,
		  scrollbar: false,
		  content: '<div id="shareDiv" class="layerDiv"></div>'
		});
    $('#shareDiv').attr('layerIndex',index);

    initModelEdit('shareDiv', {token:token}, shareTabInfoList, function(conf){
    });
}
function addGroupData(){

    var hash = {objectName:"VideoShow"};
    hash.token = $('#shareDiv .token').val();
    var accessId = "";
    var accessName = "";
	for(var i in tokenList){
		if(tokenList[i].token == hash.token){
			accessName = tokenList[i].accessName;
			accessId = tokenList[i].accessId;
			break;
		}
	}
    var accessType = $('#accessType').val();
    if(accessType){
        hash.accessName = accessName+","+accessType;
        hash.accessId = accessId+","+accessType;
    }
    
    $.ajax({
        url: "/yxxs-main-serv-portlet/api/updateToken",
        type: "POST",
        dataType: "json",
        data: hash,
        success: function (data) {
            alert("保存成功");
            
            layer.closeAll();
            $('#shareDiv').attr('layerIndex',null);
            
            initList();
        }
    });
}
<%}%>
</script>
</html>