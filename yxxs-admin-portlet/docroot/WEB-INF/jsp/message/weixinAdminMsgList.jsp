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
	</div>
    
    
    <div class="container">
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="/yxxs-admin-portlet/admin/message/weixinAdminMsgForm">发新消息</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <style>
    .table>tbody>tr>td{padding:4px;}
    .td_sendDate{max-width:80px;}
    .td_createDate{max-width:90px;}
    .td_status{min-width:40px;}
    .td_sendUserIdCnt{max-width:50px;}
    .td_readUserIdCnt{max-width:65px;}
    .td_first,.td_remark{max-width:120px;}
    .td_url{max-width:130px;}
    .td_title{max-width:140px;}
    .td_content{max-width:160px;}
    .td_msgContent{max-width:300px;}
    
    .td_url,.td_first,.td_remark,.td_msgContent,.td_title,.td_content{word-break:break-all;}
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
    
        function initList() {
            
            var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            
            $.ajax({
                url: "/yxxs-main-portlet/api/listWeixinAdminBlockMsg",
                type: "POST",
                dataType: "json",
                data: param,
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "sendDate", desc: "发送时间", format: function (v,obj) {
                            	var d = obj.scheduleDate;
                                if(new Date(d) == 'Invalid Date'){
                                	d = obj.createDate;
                                }
                                return new Date(d).Format("yyyy-MM-dd hh:mm") ;
                            } },
                            //{ name: "createDate", desc: "创建时间", format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} },
                            { name: "sendTarget", desc: "发送对象", 
                                format: function (v, ob) { 
                                    var s = [];
                                    var d = JSON.parse(v);
                                    if(d.userIds.length !=0){
                                        var names = [];
                                        jQuery.ajax(
                                            {
                                                type: "get",
                                                url: "/yxxs-main-portlet/api/baseModel/list_User?pks=" + d.userIds.join(','),
                                                data:{},
                                                dataType:'json',
                                                async:false,
                                                success: function(rdata){
                                                    for(var i=0;i<rdata.length;i++){
                                                    	names[names.length] = rdata[i].emailAddress; 
                                                    }
                                                }
                                            }
                                        );
                                    	
                                    	s[s.length] = "用户:<br/>"+names.join('<br/>');
                                    }
                                    if(d.tags.length !=0){
                                    	var tags = [].concat(d.tags);
                                    	for(var i=0;i<tags.length;i++){
                                    		if(tags[i] == ''){
                                    			tags[i] = '全部';                                    			
                                    		}
                                    	}
                                    	s[s.length] = "分组:"+tags.join(',');
                                    }
                                    return  s.join('<br/>') ;
                                } 
                            },
                            { name: "msgContent", desc: "消息体", format(v, obj){
                            	
                            	var str = "开始语:"+obj.first+"<br/>标题:"+obj.title+"<br/>内容:"+obj.content+"<br/>结束语:"+obj.remark;
                            	
                            	str += "<br/><br/>链接地址:"+obj.url;
                            	
                            	return str;
                            } },
                            /*
                            { name: "first", desc: "开始语"},
                            { name: "title", desc: "标题"},
                            { name: "content", desc: "内容"},
                            { name: "remark", desc: "结束语"},
                            { name: "url", desc: "链接地址"},
                            */
                            
                            { name: "status", desc: "状态", 
                                format: function (v) {
                                   if(v == 0){
                                       return '等待发送';
                                   }
                                   if(v == 1){
                                       return '发送成功';
                                   }
                                   if(v == 10){
                                       return '发送中';
                                   }
                                   return '--' ;
                                } 
                            },
                            { name: "sendUserIdCnt", desc: "发送<br/>账号数"},
                            { name: "readUserIdCnt", desc: "打开页面<br/>账号数"},
                        ],
                        operations: [{
                            name: "修改",
                            visable: function (obj) { return true; },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/message/weixinAdminMsgForm?id=" + obj.id; }
                        },{
                            name: "删除",
                            visable: function (obj) { return (obj.status == 0?true:false) },
                            getUrl: function (obj) { return "javascript:delMsg("+obj.id+");"; }
                        }],
                        pageUrlFunc: function(page){
                            return '?page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
    <script>
    function delMsg(id){
        if(confirm('是否删除?')){
            $.ajax({
                url: "/yxxs-main-portlet/api/delWeixinAdminBlockMsg",
                type: "POST",
                dataType: "json",
                data: {id: id },
                async:false,
                success: function (data) {
                    alert('删除成功!');
                    initList();
                }
            });
        }
    }
    </script>
    
</body>
</html>