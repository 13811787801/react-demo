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
              <li><a href="/yxxs-admin-portlet/admin/autoReply/keywordList">管理关键词</a></li>
              <li><a href="/yxxs-admin-portlet/admin/autoReply/autoReplyForm">添加自动回复</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <style>
    .td_content{max-width:650px;word-break:break-all;}
    .td_keywordCondition{max-width:140px;}
    </style>
    
    <div id="dataRow" class="container">
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/javascript">
        var keywords = {};
    
        function initList() {

            $.ajax({
                url: "/yxxs-main-portlet/api/model/list_WechatKeyWord",
                type: "POST",
                dataType: "json",
                async:false,
                data: {page:0,pageSize:1000},
                success: function (data) {
                    for(var j=0;j<data.obj.length;j++){
                    	keywords[data.obj[j].id]=data.obj[j].keyword;
                    }
                }
            });
            
            var param = {page:0,pageSize:1000};
            
            $.ajax({
                url: "/yxxs-main-portlet/api/model/list_WechatAutoReply",
                type: "POST",
                dataType: "json",
                data: param,
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "keywordCondition", desc: "条件",
                                format: function (v) {
                                    var str = "";
                                    if(v){
                                        var ks = v.split('+');
                                        for(var i=0;i<ks.length;i++){
                                            if(i!=0){
                                                str +="<br/>";
                                            }
                                            str += keywords[ks[i]];
                                        }
                                    }
                                    return str ;
                                 } 
                            },
                            { name: "content", desc: "回复内容",
                                format: function (v, obj) {
                                	var content = "";
                                	if(obj.msgType == 1){
                                		var list = JSON.parse(v);
                                		for(var i=0;i<list.length;i++){
                                			if(i!=0){
                                    			content += "<hr/>";
                                			}
                                			content += "标题:"+list[i].title + "<br/>";
                                			content += "描述:"+list[i].description + "<br/>";
                                			content += "图片链接:"+list[i].picUrl + "<br/>";
                                			content += "文章链接:"+list[i].url + "<br/>";
                                		}
                                		return content;
                                	}
                                	if(v){
                                		content = v.replace(/</g,"&lt;").replace(/>/g,"&gt;");
                                		content = content.replace(/\n/g,'<br/>');
                                	}
                                	return content;
                                 } 
                            },
                            { name: "msgType", desc: "回复类型",
                                format: function (v) {
                                	if(v == 1){
                                		return '链接列表';
                                	}
                                	return '文本消息';
                                 } 
                            },
                        ],
                        operations: [{
                            name: "编辑自动回复",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/autoReply/autoReplyForm?id=" + obj.id; }
                        },{
                            name: "删除自动回复",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:delAutoReply(" + obj.id+");"; }
                        }],
                        pageUrlFunc: function(page){
                            return null;
                        }
                    });
                }
            });
        }
        initList();
    </script>
</body>
<script>
function delAutoReply(id){
    if(confirm('是否删除?')){
        jQuery.ajax(
                {
                    type: "post",
                    url: "/yxxs-main-portlet/api/autoReply/delAutoReply",
                    data:{id:id},
                    dataType:'json',
                    async:false,
                    success: function(data){
                        initList();
                    }
                }
            );
    }
}
</script>
</html>