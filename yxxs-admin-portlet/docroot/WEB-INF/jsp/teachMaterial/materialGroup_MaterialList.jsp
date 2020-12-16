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
    
    <style>
    </style>
    
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	<%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    
	int pageNum = 1;
	try{
	    pageNum = Integer.parseInt(request.getParameter("page"));
	}catch(Exception ex){}
	%>
	
    <script type="text/javascript">
        function initList() {
            
            $.ajax({
                url: "/yxxs-package-serv-portlet/api/package",
                type: "POST",
                dataType: "json",
                data: {
                	paramHashJsonStr:JSON.stringify({
                		url:"/yxxs-teach-material-serv-portlet/api/getMaterialGroupList?groupId=<%=id%>&page=<%=pageNum - 1 %>&pageSize=10",
                		props:[
                		       {
                		    	   url:"/yxxs-teach-material-serv-portlet/api/model/get_TeachMaterial_<%="${$parent.materialId}"%>",
                		    	   alias:"materialInfo",
                                   parentType:"list",
                                   parentExp:".obj"
                		       }
              		    ]
                	})
                },
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "materialTitle", desc: "材料标题", format:function(v, ob){
                            	return ob.materialInfo.materialTitle;
                            } },
                            { name: "createDate", desc: "创建时间", format: function (v, ob) { return new Date(ob.materialInfo.createDate).Format("yyyy-MM-dd hh:mm:ss") ;} },
                        ],
                        operations: [{
                            name: "移出群组",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:deleteMaterialGroup(" + obj.id + ");"; }
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
</script>
    <script>
    function deleteMaterialGroup(id){
        if(confirm('确认移除出当前群组?')){
            jQuery.ajax(
                {
                    type: "post",
                    url: "/yxxs-teach-material-serv-portlet/api/removeMaterialGroup",
                    data:{
                        id:id
                    },
                    dataType:'json',
                    async:false,
                    success: function(data){
                        alert("移除成功");
                        initList();
                    }
                }
            );
        };
    }
    </script>
</html>