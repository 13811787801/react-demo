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
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("id"));
    }catch(Exception ex){}
    %>
    
    
	<div class="container">
	    <input type="hidden" id="id" name="id" value="<%=id%>">
	    
        <div id="dataRow">
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
	
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['keyword', '关键词'] },
            { name: ['type', '类型'], type: 'dropdown', data: [{ k: 1, v: '章节名称' },{ k: 2, v: '任务类型' }] },
            { name: ['stageLevel', '学段'], type: 'dropdown', data: [{k: 0, v: "不限"},{k: 1007, v: "小学"},{k: 1008, v: "初中"},{k: 1009, v: "高中"} ] },
            { name: ['grade', '年级'], type: 'dropdown', data: [{k: 0, v: "不限"}] },
            { name: ['curriculumId', '学科'], type: 'dropdown', data: [ ] },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');

            var curriculumItemList = [{k: 0, v: "不限"}];
            $.ajax({
                url: "/yxxs-main-serv-portlet/api/model/list_BaseCurriculum?page=0&pageSize=100",
                type: "POST",
                dataType: "json",
                data: { },
                async:false,
                success: function (data) {
                	for(var i=0;i<data.obj.length;i++){
                		curriculumItemList[curriculumItemList.length] = {k:data.obj[i].cId,v:data.obj[i].cName};
                	}
                }
            });
            for(var i=0;i<fieldInfoList.length;i++){
            	if(fieldInfoList[i].name[0] == 'curriculumId'){
            		fieldInfoList[i].data = curriculumItemList;
            	}
            }
            
            $.ajax({
                url: "/yxxs-main-portlet/api/model/get_TopicKeyWordTag_<%=id %>",
                type: "POST",
                dataType: "json",
                data: { },
                success: function (data) {
                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });
                    
                    var setGrage = function(){
                        $.ajax({
                            url: "/yxxs-main-portlet/api/stage/findGrade",
                            type: "POST",
                            dataType: "json",
                            data: {stageLevel:$('#stageLevel').val()},
                            success: function (data) {
                                
                                var options = "";
                                options += "<option value='0'>不限</option>";
                                $.each(data,function(index,content){
                                    options += "<option value='" + content.grade  + "' >" + content.gradeName + "</option>";
                                });
                                $('#grade').html(options);
                            }
                        });
                    }
                    
                    $('#stageLevel').change(function(){
                    	setGrage();
                    });
                }
            });
        }
        initList();
    </script>
    <script>
    function saveData(){
        var hash = getModelHash(tabInfoList);
        hash.id = $('#id').val();

        $.ajax({
            url: "/yxxs-main-portlet/api/saveTopicKeyword",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("保存成功");
                window.location.href='/yxxs-admin-portlet/admin/topic/topicKeywordList';
            }
        });
    }
    </script>
</body>
</html>