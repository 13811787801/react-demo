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
    
    
    <%
    Long classId = 0l;
    if(null != request.getParameter("classId")){
    	classId = Long.parseLong(request.getParameter("classId"));
    }
    %>
    
    <div class="container">
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="javascript:addClassStage();">添加下一学期</a></li>
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

        	var schoolStageList = [];

            $.ajax({
                url: "/yxxs-main-serv-portlet/api/model/list_SchoolStage?page=0&pageSize=1000",
                type: "POST",
                dataType: "json",
                data: {},
                async:false,
                success: function (data) {
                	schoolStageList = data.obj;
                }
            });
        	
            $.ajax({
                url: "/yxxs-main-portlet/api/listClassStage",
                type: "POST",
                dataType: "json",
                data: {classId:<%=classId%>},
                success: function (data) {
                    initTableList('dataRow', {
                    	   pageCount:1,
                    	   pageNumber:0,
                    	   pageSize:1000,
                    	   totalCount:data.length,
                    	   obj:data
                    	}, {
                        columns: [
                            { name: "id", desc: "id" },
                            { name: "startDate", desc: "开始时间", format: function (v) { 
                            	   var d = new Date(v);
                            	   if(d == 'Invalid Date'){
                            		   return '';
                            	   }
                            	   return d.Format("yyyy-MM-dd") ;
                            	}  
                            },
                            { name: "endDate", desc: "结束时间", format: function (v) { 
	                               var d = new Date(v);
	                               if(d == 'Invalid Date'){
	                                   return '';
	                               }
	                               return d.Format("yyyy-MM-dd") ;
                                }
                            },
                            { name: "active", desc: "启用?", format:function(v, obj){
                            	if(v == 1023){
                            		return '当前学期';
                            	}
                                return '非激活';
                            } },
                            { name: "info", desc: "学期信息", format:function(v, obj){
                            	var s = '';
                            	
                            	for(var i=0;i<schoolStageList.length;i++){
                            		if(schoolStageList[i].id == obj.schoolstageId){
                            			
                            			if(schoolStageList[i].stageLevel == 1007){
                            				s += '小学';
                            			}else if(schoolStageList[i].stageLevel == 1008){
                                            s += '初中';
                                        }else if(schoolStageList[i].stageLevel == 1009){
                                            s += '高中';
                                        }
                            			
                            			s += '-' + (schoolStageList[i].grade - 1001 + 1) + "年级";

                                        s += '-';
                            			if(schoolStageList[i].term == 1010){
                                            s += '上';
                            			}else if(schoolStageList[i].term == 1011){
                                            s += '下';
                            			}else{
                            				s += '特殊';
                            			}
                            			s+='学期';
                            			
                            			break;
                            		}
                            	}
                            	
                            	return s;
                            } },
                        ],
                        operations: [{
                            name: "编辑学期",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/classStageForm?classStageId=" + obj.id; }
                        },{
                            name: "删除学期",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:delClassStage(" + obj.id+");"; }
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
    <script>
    function addClassStage(){
        $.ajax({
            url: '/yxxs-main-portlet/api/addClassStage',
            type: "POST",
            dataType: "json",
            data: {
            	orgId: <%=classId%>,
                userId:${admin['userId']},
            },
            async:false,
            success: function (data) {
                initList();
            }
        });
    }
    function delClassStage(classStageId){
        if(confirm('是否删除?')){
            $.ajax({
                url: "/yxxs-main-portlet/api/delClassStage",
                type: "POST",
                dataType: "json",
                data: {classStageId: classStageId },
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