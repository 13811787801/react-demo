<%@page import="com.yxxs.common.util.DateUtil"%>
<%@page import="com.yxxs.common.util.DateTermUtil"%>
<%@ page language="java" contentType="application/javascript; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int gradeOneSchoolYear = DateTermUtil.getGradeStartYear();
%>

<%if(false){ %>
<script>
<%}%>
/**
 * conf = {
 *    keyMap:{key1:inputId1,key2:inputId2}
 * }
 */
function ConditionHelper(conf){
    
    this._inputs = conf.keyMap || {};
    
    var _this = this;
    
    this.data = {};
    
    var getInputDataHash = function(){
        var _data = {};
        for(var k in _this._inputs){
            var v = $('#'+_this._inputs[k]).val();
            if(typeof v != 'undefined' && v != null){
                _data[k] = v;
            } 
        }
        return _data;
    }
    
    this.refreshDataHash = function(){
        _this.data = getInputDataHash();
    };
    
    this.dataChanged = function(){
        var _data = getInputDataHash();
        for(var k in _this._inputs){
            if(_data[k] != _this.data[k]){
                return true;
            }
        }
        return false;
    };
    
    this.makeUrlParamStr = function(){
        var arr = [];
        for(var k in _this.data){
            arr.push(k+'='+(_this.data[k]?_this.data[k]:""));
        }
        return arr.join('&');
    }
    
    this.refreshDataHash();
}

function iniStageLevelCondition(inputId, val){

    var htmlStr = '<option value="">全部</option>';
    htmlStr += '<option value="1007">小学</option>';
    htmlStr += '<option value="1008">初中</option>';
    htmlStr += '<option value="1009">高中</option>';
    
    $('#'+inputId).html(htmlStr);
    if(val){
        $('#'+inputId).val(val);
    }
}

function initGradeCondition(inputId, stageLevel,val){
    loadjs('/yxxs-script-portlet/js/schoolStage/schoolStage.js',function(){
        
        var htmlStr = '<option value="">全部</option>';
        if(stageLevel){
            var grades = stageLevelMap[stageLevel];
            $.each(grades,function(index,value){
                var gradeNum = getGradeNum(stageLevel,value);
                var schoolYear = <%=gradeOneSchoolYear%>-value+1001;
                htmlStr += '<option value="'+value+'">'+gradeNum+'年级('+schoolYear+'年入学)</option>';
            });
        }
        $('#'+inputId).html(htmlStr);
        if(val){
            $('#'+inputId).val(val);
        }
    });
}

function initCurriculumCondition(inputId, stageLevel,grade,val){

    var param = {stageLevel:0,grade:0};
    if(stageLevel){
        param.stageLevel = stageLevel;
    }
    if(grade){
        param.grade = grade;
    }

  jQuery.ajax(
      {
          type: "post",
          url: "/yxxs-main-portlet/api/stage/findCurriculum",
          data:param,
          dataType:'json',
          async:true,
          success: function(data){
                var htmlStr = '<option value="">不限</option>';
                $.each(data,function(index,content){
                    htmlStr += '<option value="'+content.cId+'">'+content.cName+'</option>';
                });
                $('#'+inputId).html(htmlStr);
                if(val){
                    $('#'+inputId).val(val);
                }
          }
      }
  );
}

function initTopicKeyword(keywordType,conf, func){
	var url = "/yxxs-main-portlet/api/listTopicKeyword";
	//"/yxxs-main-portlet/api/model/list_TopicKeyWordTag";
	if(conf && conf.topicInfo){
		url += '?type=1';
		if(conf.topicInfo.grades && conf.topicInfo.grades != 0){
			url += '&grade='+conf.topicInfo.grades;
		}
		if(conf.topicInfo.stagelevel && conf.topicInfo.stagelevel != 0){
			url += '&stagelevel='+conf.topicInfo.stagelevel;
		}
		if(conf.topicInfo.cid && conf.topicInfo.cid != 0){
			url += '&cid='+conf.topicInfo.cid;
		}
	}else{
		url += '?type=2';
	}
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        data: {page:0,pageSize:10000},
        success: function (data) {
            var itemList = [];
            var v = '';
            /*
            var cv = {};
            if(conf.tags){
            	var arr = [];
			    for(var i=0;i<conf.tags.length;i++){
			    	if(conf.tags[i].type == keywordType){
			    		if(JSON.stringify(data.obj).indexOf(conf.tags[i].key) < 0){
			    			cv.curriculumId = conf.tags[i].curriculumId;
				    		cv.grade = conf.tags[i].grade;
				    		cv.keyword = conf.tags[i].value;
				    		cv.scheduleEnd = conf.tags[i].scheduleEnd;
				    		cv.scheduleStart = conf.tags[i].scheduleStart;
				    		cv.stageLevel = conf.tags[i].stageLevel;
				    		cv.type = conf.tags[i].type;
				    		cv.id = conf.tags[i].key;
				    		data.obj.push(cv);
			    		};
			    	}
			    }
            }*/
            for(var j=0;j<data.obj.length;j++){
                if(keywordType && data.obj[j].type!=keywordType){
                    continue;
                }
                v = data.obj[j].keyword;
                if(data.obj[j].type == 1){
                	v += '('+getStage(data.obj[j].stageLevel)+'-'+getGrade(data.obj[j].grade)+'-'+getCurriculum(data.obj[j].curriculumId)
                    				+' '+data.obj[j].scheduleStart+'至'+data.obj[j].scheduleEnd+')';
                }
                itemList[itemList.length] = {k:data.obj[j].id,v:v};
                
            }
            
            
            if(func){
                func(itemList);
            }
            
        }
    });
}

function initTopicKeywordInput(inputId, keywordType,conf, func){
    initTopicKeyword(keywordType,conf,function(itemList){
    	var selected = 0;
    	var cv = {};
    	if(conf && conf.tags){
    		for(var i=0;i<conf.tags.length;i++){
		    	if(conf.tags[i].type == keywordType){
		    		selected = conf.tags[i].key;
		    		if(keywordType == 1 && JSON.stringify(itemList).indexOf(conf.tags[i].key) < 0){
		    			cv.k = conf.tags[i].key;
		    			cv.v=conf.tags[i].value+'('+getStage(conf.tags[i].stageLevel)
		    			+'-'+getGrade(conf.tags[i].grade)+'-'+getCurriculum(conf.tags[i].curriculumId)
		    			+' '+conf.tags[i].scheduleStart+'至'+conf.tags[i].scheduleEnd
		    			+')';
		    			itemList.push(cv);
		    		};
		    	}
		    }
    	}
    	
        var options = '<option value="">请选择</option>';
        for(var i=0;i<itemList.length;i++){
        	//if(itemList[i].k == selected){
        	//	options += '<option value="'+itemList[i].k+'" selected>'+itemList[i].v+'</option>';
        	//}else{
        		options += '<option value="'+itemList[i].k+'">'+itemList[i].v+'</option>';
        	//}
            
        }
        $('#'+inputId).html(options);
        if(func){
            func();
        }
    });
}

<%if(false){ %>
</script>
<%}%>