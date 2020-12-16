<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String name = "";
	try{
		name = request.getParameter("name");
	}catch(Exception e){}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    <script>
      var PAGE_SIZE = 12;
    </script>               
    <style>
         .form-inline{ margin-bottom:20px;}
         .form-control{ width:300px;display:inline; }
         .container{margin-top:30px;}
         .td_h_lastModifyTime{width:100px !important;}
         .td_h_expireDate{width:100px !important;}
   </style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
  
    <div class="container">
         <input id="name" type="text" class="form-control"  placeholder="用户名/微信昵称"/>
         <input id="search" type="button" class="btn btn-default" value="查询" onclick="search();">
    </div>
	
    <div id="dataRow" class="container">
    </div>


    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %> 
	
	<script>
	function search(page){
		 var param = {};
	     param.page = page;
	     if(!page){
	    	 param.page = 1;
	     }
	     param.pageSize = PAGE_SIZE;
	     if($('#name').val().trim()){
	    	 param.name = $('#name').val().trim(); 
	     }
	    
	     getUserList(param);
	}
	
	var appInfo = {
			getDesc:function(appId){
				var title='-';
				if(appId == 'wx545ba0f3cae9a56c'){
					title = '翻转教学';
				}
				if(appId == 'wxcfeed1086d8aeaad'){
					title = '翼课项目';
				}
				if(appId == 'wx3c846cfaf3a44191'){
					title = '马芯兰';
				}
				if(appId == 'wx62707f662bc93854'){
					title = '小初衔接项目课题组';
				}
				if(appId == 'wxf355ae82fc0c3f16'){
					title = '小初衔接项目';
				}
				if(appId == 'wxedfcdaed4712c52f'){
					title = '优学向上';
				}
				return title;
			}
	}
	function getUserList(param) {
         $.ajax({
             url: "/yxxs-main-portlet/api/listWechatUserInfo",
             type: "POST",
             dataType: "json",
             data: param,
             async:true,
             success: function (data) {
            	 
            	 data.pageNumber = data.pageNumber-1;
            	 
            	 var columnsConf = [
                     { name: "openid", desc: "openId",format: function (v, ob) { 
                         return ob.openid + '</br> 公众号：' + appInfo.getDesc(ob.appId);
                     } },
                     { name: "bindUser", desc: "绑定信息",format: function (v, ob) { 
                         if(ob.userDto){
                        	 
                             return ob.userDto.screenName + '<br/>' +ob.userDto.emailAddress;
                         }
                         return "--";
                     } },
                     { name: "subscribe", desc: "关注",format: function (v, ob) { 
                         if(ob.subscribe){
                             return '是';
                         }
                         return '否';
                     } },
                     { name: "area", desc: "地区",format: function(v, ob){
                    	 var area = [];
                    	 if(ob.country){
                    		 area.push(ob.country);
                    	 }
                    	 if(ob.province){
                    		 area.push(ob.province);
                    	 }
                         if(ob.city){
                             area.push(ob.city);
                         }
                         return area.join('<br/>');
                      }},
                     { name: "nickname", desc: "昵称"},
                     { name: "sex", desc: "性别",format: function(v, ob){
                    	var sex = '--';
                    	if(v == 1){
                    		sex = '男'
                    	}else if(v == 2){
                    		sex = '女'
                    	}
                    	return sex;
                     }},
                     { name: "lastModifyType", desc: "操作详情",format: function (v, ob) { 
                         var type = "";
                         if(ob.lastModifyType == 1){
                             type = "关注_添加";
                         }else if(ob.lastModifyType == 2){
                             type = "取关_添加";
                         }else if(ob.lastModifyType == 3){
                             type = "普通_增加";
                         }else if(ob.lastModifyType == 11){
                             type = "关注_更新";
                         }else if(ob.lastModifyType == 12){
                             type = "取关_更新";
                         }else if(ob.lastModifyType == 13){
                             type = "正常_更新";
                         }
                         return type;
                     } },
                     { name: "lastModifyTime", desc: "最后修改时间",format: function (v, ob) { 
                         if(v){
                             return (new Date(v)).Format("yyyy-MM-dd hh:mm:ss");
                         }
                         return "";
                     } },
                     { name: "expireDate", desc: "过期时间",format: function (v, ob) { 
                         if(v){
                             return (new Date(v)).Format("yyyy-MM-dd hh:mm:ss");
                         }
                         return "";
                     } },
                  ];
            	 
            	  initTableList('dataRow', data, {
                     columns: columnsConf,
                     operations: [{
                         name: "删除缓存信息",
                         visable: function (obj) { return true },
                         getUrl: function (obj) { return "javascript:delWechatUserInfo('" + obj.openid +"','"+obj.appId+"');"; }
                     }],
                     pageUrlFunc: function(page){
                         return 'javascript:search('+page+');';
                     }
                 }); 
            } 
         });
     }
	 
	 $(function(){
		 if(<%=name%>){
			 $('#name').val('<%=name%>');
		 }
		 search(1);
	 });
	</script>
	<script>
    function delWechatUserInfo(openId,appId){
        jQuery.ajax(
            {
                type: "post",
                url: "/yxxs-wechat-serv-portlet/api/delWechatUserInfo",
                data:{openId:openId,appId:appId},
                dataType:'json',
                async:false,
                success: function(data){
                	search(1);
                }
            }
        );
    }
    </script>
	
</body>
</html>