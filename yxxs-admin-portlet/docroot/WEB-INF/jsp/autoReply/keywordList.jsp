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
              <li><a href="/yxxs-admin-portlet/admin/autoReply/autoReplyList">管理自动回复</a></li>
              <li><a href="/yxxs-admin-portlet/admin/autoReply/keywordForm">添加关键词</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <div id="dataRow" class="container">
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/javascript">
        function initList() {

            $.ajax({
                url: "/yxxs-main-portlet/api/model/list_WechatKeyWord",
                type: "POST",
                dataType: "json",
                data: {page:0,pageSize:1000},
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "keyword", desc: "关键词"},
                        ],
                        operations: [{
                            name: "编辑关键词",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/autoReply/keywordForm?id=" + obj.id; }
                        },{
                            name: "删除关键词",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:delKeyword(" + obj.id+");"; }
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
function delKeyword(id){
    if(confirm('是否删除?')){
        jQuery.ajax(
                {
                    type: "post",
                    url: "/yxxs-main-portlet/api/autoReply/delKeyword",
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