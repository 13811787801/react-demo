<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-static-portlet/js/autocomplete/jquery.autocomplete.js"></script>
	
	<script type="text/javascript" src="/yxxs-main-portlet/js/task/teacherCurriculum.js">
	</script>


	<style>
	.autocomplete-suggestions{overflow-y:scroll; background-color:#fff;border:1px solid gray;}.tagsList span{margin-right:5px;}.tagsList a.del{cursor:pointer}
	</style>
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
	    
        <div id="dataRow" class="teacherCurriculum">
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
	
	<script>
	function renderCurriculumDisplay(obj, list){
        var options = "";
        options += "<option value='0'>不限</option>";
        $.each(list,function(index,content){
            options += "<option value=" + content.cId + ">" + content.cName + "</option>";
        });
        $(obj).parents('.teacherCurriculum').find('.curriculumId').html(options);
	}
	</script>
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['materialTitle', '材料标题']},
            { name: ['materialDesc', '材料简介'], type: 'textarea' },
            { name: ['stageLevel', '学段'], type: 'dropdown', data: [] },
            { name: ['grade', '年级'], type: 'dropdown', data: [] },
            { name: ['curriculumId', '学科'], type: 'dropdown', data: [] },
            { name: ['videoShow', '视频材料'] },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');

    		var dataArr = [];
            $.ajax({
                url: "/yxxs-main-serv-portlet/api/model/list_VideoShow?page=0&pageSize=<%=Integer.MAX_VALUE%>",
                type: "POST",
                dataType: "json",
                async:false,
                data: {},
                success: function (data) {
                	var videoList = [];
                	for(var i=0;i<data.obj.length;i++){
                		videoList[i] = {k:data.obj[i].id, v:data.obj[i].videoTitle};
                	}
            		for(var i=0;i<videoList.length;i++){
            			dataArr.push({value:videoList[i].v, data:videoList[i].k});
            		}
                }
            });

            $.ajax({
                url: "/yxxs-package-serv-portlet/api/package",
                type: "POST",
                dataType: "json",
                data: {
                	paramHashJsonStr:JSON.stringify({
                		url:"/yxxs-teach-material-serv-portlet/api/model/get_TeachMaterial_<%=id %>",
                		props:[
                		       {
                		    	   url:"/yxxs-main-serv-portlet/api/model/get_VideoShow_<%="${$parent.videoShowId}"%>",
                		    	   alias:"videoShowInfo",
                		    	   condition:"($parent.videoShowId>0)"
                		       }
              		    ]
                	})
                },
                success: function (data) {

                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });

                	initStageLevel("dataRow");
                	$('#stageLevel').val(data.stageLevel);
                	initGrade($('#stageLevel'));
                	$('#grade').val(data.grade);
                	initCurriculum($('#grade'));
                	$('#curriculumId').val(data.curriculumId);

                	$('#stageLevel').change(function(){
                		changeStageLevel(this);
                	});
                	$('#grade').change(function(){
                		changeGrade(this);
                	});

                	if(data.videoShowId){
                	    $('#videoShow').attr('videoShowId',data.videoShowId);
        	        	$('#videoShow').val(data.videoShowInfo.videoTitle);
        	        	$('#videoShow').attr('disabled','disabled');
                	}else{
                	    $('#videoShow').autocomplete({
                	        lookup: dataArr,
                	        minChars:0,
                	        onSelect: function(suggestion) {
                	        	$('#videoShow').attr("videoShowId",suggestion.data);
                	        	if(!$('#materialTitle').val()){
                	        		$('#materialTitle').val(suggestion.value);
                	        	}
                	        },
                	        onHint: function (hint) {
                	        },
                	        onInvalidateSelection: function() {
                	        	$('#videoShow').attr("videoShowId",null);
                	        }
                	    });
                	}
            	    
                }
            });
        }
        initList();
    </script>
    <script>
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.id = $('#id').val();
        hash.videoShowId = $('#videoShow').attr('videoShowId');
        hash.userId=${admin['userId']};

        if(!hash.videoShowId){
        	alert('请先选择视频材料!');
        	return;
        }

        $.ajax({
            url: "/yxxs-teach-material-serv-portlet/api/saveTeachMaterial",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("保存成功");
                window.location.href='/yxxs-admin-portlet/admin/teachMaterial/materialList';
            }
        });
    }
    </script>
</body>
</html>