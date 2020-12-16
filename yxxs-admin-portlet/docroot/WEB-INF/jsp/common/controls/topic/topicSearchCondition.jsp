<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="/yxxs-admin-portlet/js/conditionHelper.js?t=<%=ts%>"></script>
	
<%
   String sTitle = "";
   if(null != request.getParameter("title")){
   	sTitle = request.getParameter("title");
   }
   String sSchool = "";
   if(null != request.getParameter("schoolName")){
	sSchool = request.getParameter("schoolName");
   }
   String sStageLevel = "";
   if(null != request.getParameter("stageLevel")){
   	sStageLevel = request.getParameter("stageLevel");
   }
   String sGrade = "";
   if(null != request.getParameter("grade")){
   	sGrade = request.getParameter("grade");
   }
   String sCurriculumId = "";
   if(null != request.getParameter("curriculumId")){
   	sCurriculumId = request.getParameter("curriculumId");
   }
%>
    
    <div class="container">
    </div>
	
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
               <div class="form-group form-inline">
                   <select class="form-control" id="sStageLevel" name="sStageLevel">
                    </select>
                   <select class="form-control" id="sGrade" name="sGrade"></select>
                   <select class="form-control" id="sCurriculumId" name="sCurriculumId"></select>
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="sTitle" name="sTitle" 
                     placeholder="请输入标题或发布人">
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="sSchool" name="sSchool" 
                     placeholder="请输入学校名称">
               </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
               <div class="form-group">
                    <button type="button" onclick="search();" class="btn btn-default">搜索</button>
               </div>
            </div>
        </div>
    </div>

    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%
	int pageNum = 1;
	try{
	    pageNum = Integer.parseInt(request.getParameter("page"));
	}catch(Exception ex){}
	%>
	
	<script>
	window.searchConf = {
		getUrlFunc:function(param){
			return null;
		},
		getPackageDataFunc:function(param){
			return null;
		},
		columnsData:{}
	};
	</script>
	
    <script type="text/javascript">

	    var conditionHelper = null;
	    function initCondition(){
	    	conditionHelper = new ConditionHelper({
	    		keyMap:{
	    			title:"sTitle",
	    			schoolName:"sSchool",
	    			curriculumId:"sCurriculumId",
	    			stageLevel:"sStageLevel",
	    			grade:"sGrade",
	    		}
	    	});
	    	conditionHelper.data.title = '<%=sTitle%>';
	    	conditionHelper.data.schoolName = '<%=sSchool%>';
	    	conditionHelper.data.curriculumId = '<%=sCurriculumId%>';
	    	conditionHelper.data.stageLevel = '<%=sStageLevel%>';
	    	conditionHelper.data.grade = '<%=sGrade%>';
	    	
	    	iniStageLevelCondition("sStageLevel",conditionHelper.data.stageLevel);
	    	initGradeCondition("sGrade",conditionHelper.data.stageLevel,conditionHelper.data.grade);
	    	initCurriculumCondition('sCurriculumId',conditionHelper.data.stageLevel,conditionHelper.data.grade,conditionHelper.data.curriculumId);
	    	
	    	$('#sTitle').val(conditionHelper.data.title);
	    	$('#sSchool').val(conditionHelper.data.schoolName);
	    	
	    	$('#sStageLevel').change(function(){
		    	initGradeCondition("sGrade",$(this).val());
		    	initCurriculumCondition('sCurriculumId',$(this).val(),null);
	    	});
	    	$('#sGrade').change(function(){
		    	initCurriculumCondition('sCurriculumId',$('#sStageLevel').val(),$(this).val());
	    	});
	    }
	    
    </script>
</body>
