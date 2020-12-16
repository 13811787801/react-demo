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
              <li><a href="/yxxs-admin-portlet/admin/faq/faqForm">添加FAQ</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <style>
    .td_answerUrl{max-width:250px;word-break:break-all;}
    </style>
    
    <div id="dataRow" class="container">
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/javascript">
        function initList() {
            
            var param = {page:0,pageSize:100};
            
            $.ajax({
                url: "/yxxs-main-serv-portlet/api/model/list_QuestionAnswer",
                type: "POST",
                dataType: "json",
                data: param,
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "question", desc: "问题" },
                            { name: "answerUrl", desc: "回答链接" },
                            { name: "answerDesc", desc: "回答描述" },
                            { name: "order", desc: "排序" },
                            { name: "type", desc: "类型", format: function (v) { return v ;} },
                            { name: "status", desc: "状态", format: function (v) { return (v == 0)?"停用":"开启" ;} },
                        ],
                        operations: [{
                            name: "编辑FAQ",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/faq/faqForm?id=" + obj.id; }
                        },
                        {
                            name: "删除FAQ",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:delFaq(" + obj.id+");"; }
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
function delFaq(id){
	if(confirm('是否删除?')){
        jQuery.ajax(
                {
                    type: "post",
                    url: "/yxxs-main-serv-portlet/api/delQuestionAnswer",
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