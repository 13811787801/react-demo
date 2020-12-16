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
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="sTitle" name="sTitle"  value=""
                     placeholder="请输入标题">
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
    
    <div class="container">
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="/yxxs-admin-portlet/admin/topic/topicKeywordForm">添加关键词</a></li>
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
	    function search(){
	    	initDataRow();
	    }
	 	function getStage(id){
	 		if(id == 1007){
	 			return '小学';
	 		}
	 		if(id == 1008){
	 			return '初中';
	 		}
	 		if(id == 1009){
	 			return '高中';
	 		}
	 		if(id == 0){
	 			return '学段';
	 		}
	 	}
	 	function getGrade(id){
	 		if(id == 1001){
	 			return '一年级';
	 		}
	 		if(id == 1002){
	 			return '二年级';
	 		}
	 		if(id == 1003){
	 			return '三年级';
	 		}
	 		if(id == 1004){
	 			return '四年级';
	 		}
	 		if(id == 1005){
	 			return '五年级';
	 		}
	 		if(id == 1006){
	 			return '六年级';
	 		}
	 		if(id == 0){
	 			return '年级';
	 		}
	 	}
	 	function getCurriculum(id){
	 		if(id == 1){
	 			return '语文';
	 		}
	 		if(id == 2){
	 			return '数学';
	 		}
	 		if(id == 3){
	 			return '英语';
	 		}
	 		if(id == 0){
	 			return '学科';
	 		}
	 	}
	    var tagList = null;
	    function initDataRow(){
	    	
	    	var data = {pageNumber:0,pageCount:1,pageSize:10000,totalCount:0,obj:[]};
	    	for(var i=0;i<tagList.obj.length;i++){
	    		if($('#sTitle').val().length !=0 && tagList.obj[i].keyword.indexOf($('#sTitle').val()) == -1){
	    			continue;
	    		}
	    		data.totalCount++;
	    		data.obj[data.obj.length] = tagList.obj[i];
	    	}
	    	
            initTableList('dataRow', data, {
                columns: [
                    { name: "keyword", desc: "关键词",
                    	format:function(v,obj){
                    		return v+'('+getStage(obj.stageLevel)+'-'+getGrade(obj.grade)+'-'+getCurriculum(obj.curriculumId)
                    				+' '+obj.scheduleStart+'至'+obj.scheduleEnd+')';
                    	}	
                    },
                    { 
                        name: "type", desc: "类型", 
                        format: function (v) { 
                            if(v == 1){
                                return '章节名称';
                            }
                            if(v == 2){
                                return '任务类型';
                            }
                            return '-' ;
                        } 
                    },
                ],
                operations: [{
                    name: "编辑关键词",
                    visable: function (obj) { return true },
                    getUrl: function (obj) { return "/yxxs-admin-portlet/admin/topic/topicKeywordForm?id=" + obj.id; }
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
	    
        function initList() {
            
            var param = {page:0,pageSize:10000};
            
            $.ajax({
                url: "/yxxs-main-portlet/api/model/list_TopicKeyWordTag",
                type: "POST",
                dataType: "json",
                data: param,
                success: function (data) {
                	tagList = data;
                	initDataRow();
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
                    url: "/yxxs-main-portlet/api/delTopicKeyword",
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