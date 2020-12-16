<%@page import="java.util.Calendar"%>
<%@page import="com.yxxs.common.util.DateTermUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    
    <script src="/yxxs-script-portlet/js/user/user.js?t=<%=ts%>"></script>
    <script src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
    <script src="/yxxs-script-portlet/js/classMember/classMember.js?t=<%=ts%>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    
    <div class="container">
    </div>
    
    <style>
    .layerDiv{margin:0 15px; padding-top:10px;}
    </style>
    
    <%
    Calendar c = Calendar.getInstance();
    c.setTime(DateTermUtil.getCurrentTermStartDate());
    int year = c.get(Calendar.YEAR);
    year = year - DateTermUtil.getCurrentTerm(); 
    %>
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="sName" name="sName"  value=""
                     placeholder="请输入学校名或教师名称">
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="sGrade" name="sGrade">
                    <option value="">全部</option>
                    <option value="1001">一年级(<%=year %>年入学)</option>
                    <option value="1002">二年级(<%=year-1 %>年入学)</option>
                    <option value="1003">三年级(<%=year-2 %>年入学)</option>
                    <option value="1004">四年级(<%=year-3 %>年入学)</option>
                    <option value="1005">五年级(<%=year-4 %>年入学)</option>
                    <option value="1006">六年级(<%=year-5 %>年入学)</option>
                  </select>
               </div>
            </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <select class="form-control" id="sCurriculum" name="sCurriculum">
                    <option value="">全部</option>
                    <option value="1">语文</option>
                    <option value="2">数学</option>
                    <option value="3">英语</option>
                  </select>
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
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>

    <script type="text/javascript">
        var PAGE_SIZE = 12;
        
	    function search(page){
	    	if(page){
	    		initList(page);
	    	}else{
	    		initList(0);
	    	}
	    }
	    
        function initList(page) {
            
            var param = {page : page, pageSize: PAGE_SIZE};
            if($('#sGrade').val()){
                param.schoolYear = <%=year%>-($('#sGrade').val()-1001);
            }
            if($('#sName').val()){
                param.name = $('#sName').val();
            }
            if($('#sCurriculum').val()){
                param.curriculumId = $('#sCurriculum').val();
            }
            
            param.showTeachers=true;
            param.showWatchTeachers=true;
            
            var paramList = [];
            for(var k in param){
               paramList.push(k+"="+param[k]);
            }
            
            $.ajax({
                url: "/yxxs-package-serv-portlet/api/package",
                type: "POST",
                dataType: "json",
                data: {
                	paramHashJsonStr:JSON.stringify({
                        url: "/yxxs-main-portlet/api/listDemonstrationClasses?"+paramList.join('&'),
                        props:[
                           {
                              url:"/yxxs-main-portlet/api/getClass?orgId=<%="${$parent.classId}"%>",
                              alias:"classInfo",
                              parentType:"list",
                              parentExp:".obj",
                              props:[
                                     {
                                         url:"/yxxs-main-portlet/api/getSchool?orgId=<%="${$parent.parentOrganizationId}"%>",
                                         alias:"schoolInfo",
                                         parentExp:"",
                                         props:[
												{
												    url:"/yxxs-main-serv-portlet/api/getAddress?district=<%="${$parent.district}"%>",
												    alias:"addressInfo",
												    parentExp:".address",
												}
                                         ]
                                     }
                              ]
                          },
                          {   
                              alias:"",
                              parentType:"list",
                              parentExp:".obj",
                              props:[
	                              {
	                            	  url:"/yxxs-main-portlet/api/user_<%="${$parent.teacherId}"%>",
	                            	  alias:"userInfo",
	                                  parentType:"list",
	                                  parentExp:".teachers"
	                              }
                              ]
                          },
                          {   
                              alias:"watchTeachers",
                        	  url:"/yxxs-demonstration-serv-portlet/api/getObserveTeachers?classId=<%="${$parent.classId}"%>",
                              parentType:"list",
                              parentExp:".obj"
                          }
                       ]
                   })
                },
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
							{ name: "area", desc: "区域", format:function(v, obj){
								var sdata = obj.classInfo.schoolInfo.address.addressInfo;
							    var s = sdata.pName+'-'+sdata.cName+'-'+sdata.dName;
							    return s;
							} },
                            { name: "school", desc: "学校", format:function(v, obj){
                                return obj.classInfo.schoolInfo.name;
                            } },
                            { name: "name", desc: "班级名称", format:function(v, obj){
                            	return obj.classInfo.name;
                            } },
                            { name: "grade", desc: "年级", format:function(v, obj){
                            	var s = (<%=year%>-obj.classInfo.schoolYear+1)+'年级';
                            	s +='('+obj.classInfo.schoolYear+'年入学'+')';
                                return s;
                            } },
                            { name: "teachers", desc: "被观摩教师", format:function(v, obj){
                                var l = [];
                                if(obj.teachers){
                                    for(var i=0;i<obj.teachers.length;i++){
                                        var u = obj.teachers[i].userInfo;
                                        l.push(u.screenName);
                                    }
                                }
                                return '<a href="javascript:setTeachers('+obj.classId+');">'+(l.length?l.join(','):0)+'</a>';
                            } },
                            { name: "curriculums", desc: "观摩学科", format:function(v, obj){
                            	var curriculunNames = "";
                            	if(v){
                            		if(v.length > 0){
                            			curriculunNames = v[0].cName;
                            		}
                            	}
                            	return curriculunNames;
                            } },
                            { name: "watchTeachers", desc: "观摩教师", format:function(v, obj){
                            	var num = 0;
                            	if(obj.watchTeachers){
 					num = obj.watchTeachers.length;
                                }
                            	return '<a href="javascript:getObserveUser('+obj.classId+');">'+num+'</a>';
                            } },
                            { name: "manage", desc: "管理", format: function(v,obj){
                            	var s = ''; 
                            	s += '<a class="btn btn-default" href="javascript:cancelDemonstrationClass('+obj.classId+',0);">取消观摩</a>';
                            	s += '　';
                            	var observeTeacherIds = [];

                            	if(obj.watchTeachers){
	                            	for(var i=0; i<obj.watchTeachers.length; i++){
	                            		observeTeacherIds.push(obj.watchTeachers[i].userId);
	                            	}
                            	}
                            	
                            	s += '<a class="btn btn-default" href="javascript:sendMsg(\''+observeTeacherIds.join(',')+'\');">发送消息</a>';
                            	return s;
                            } },
                        ],
                        operations: [],
                        pageUrlFunc: function(page){
                            return "javascript:search("+(page-1)+");"
                        }
                    });
                }
            });
        }
    </script>
    <script>
    
    function sendMsg(observeTeacherIds){
    	var url = '/yxxs-admin-portlet/admin/message/weixinAdminMsgForm?';
    	if(observeTeacherIds){
    		url += '&userIds='+observeTeacherIds;
    	}
        window.location.href = url;
    }
    
    function setDemonstrationClassTeacher(classId, teacherId){
        $.ajax({
            url: "/yxxs-demonstration-serv-portlet/api/addDemonstrationClass",
            type: "POST",
            dataType: "json",
            data: {
            	classId: classId, 
            	teacherId:teacherId
           	},
            async:false,
            success: function (data) {
                alert('设置成功!');
                
                layer.closeAll();
                $('#settingDiv').attr('layerIndex',null);
                
                setTeachers(classId);
                search();
            }
        });
    }
    function cancelDemonstrationClassTeacher(classId, teacherId){
    	cancelDemonstrationClass(classId,teacherId, function(){
            layer.closeAll();
            $('#settingDiv').attr('layerIndex',null);
            
            setTeachers(classId);
            search();
    	});
    }
    
    
    function cancelDemonstrationClass(classId, teacherId, func){
	        var commonFunc = function(){
	    		$.ajax({
		            url: "/yxxs-main-portlet/api/cancelDemonstrationClass",
		            type: "POST",
		            dataType: "json",
		            data: {
		                classId: classId, 
		                teacherId:teacherId
		            },
		            async:false,
		            success: function (data) {
		            	layer.msg('设置成功！', {shift: 6});
		                if(func){
		                	func();
		                }
		                search();
		            }
	           });
	      };
      
	      if(teacherId == 0){
	          var title = '取消观摩后，观摩班级将不会在观摩列表中出现，现有观摩教师也将强制结束观摩，确认关闭吗？';
		  
	      	  layerConfirm(title,
		  function(){
	        	  commonFunc();
	          },
		  function(){
	              layer.closeAll();
	          });
	      }else{
	    	  commonFunc();
	      }
    	
    }
    </script>
    <script>
    
    $(function(){
    	search();
    });
    
    function getObserveUser(classId){
    	window.location.href = '/yxxs-admin-portlet/admin/user/observeUserList?classId='+classId;
    }
    
    function setPriority(classId,teacherId,priority){
    	var bool = false;
    	$.ajax({
            url: "/yxxs-demonstration-serv-portlet/api/setDemonstrationTeacherPriority",
            type: "POST",
            dataType: "json",
            async:false,
            data: {
                classId: classId, 
                teacherId:teacherId,
                priority:priority
            },
            success:function(data){
            	bool = true;
            	layer.closeAll();
            }
    	});
    	return bool;
    }
    
    function setDemonstrationTeacherPriority(classId,teacherId){
    	openMessageCommonFunc(function(){
            return setPriority(classId,teacherId,1);
        },'设置置顶');
    }
    
    function cancelDemonstrationTeacherPriority(classId,teacherId){
    	openMessageCommonFunc(function(){
    		return setPriority(classId,teacherId,0);
    	},'取消置顶');
    }
    
    function openMessageCommonFunc(func,msgPrefix){
        var bool = func();
        if(msgPrefix){
            var msg = msgPrefix;
            msg+=(bool?"成功":"失败")+"！";
            alert(msg);
        }
        search();
    }
    
    function getDemonstrationClassTeachers(classId){
    	 var teachers = null;
    	 $.ajax({
             url: "/yxxs-main-portlet/api/getDemonstrationClass",
             type: "POST",
             dataType: "json",
             data: {
                 classId: classId, 
                 showTeachers:true 
             },
             async:false,
             success: function (data) {
                 if(data.teachers){
                	 teachers = data.teachers;
                 }
             }
         });
    	 return teachers;
    }
    
    function setTeachers(classId){

    	var tlist = [];
        $.ajax({
            url: "/yxxs-package-serv-portlet/api/package",
            type: "POST",
            dataType: "json",
            data: {
            	paramHashJsonStr:JSON.stringify({
                    url: "/yxxs-main-portlet/api/getClassMemberList?orgId="+classId+"&types=teacher&showBind=false",
                    props:[
                       {
                           url:"/yxxs-wx-serv-portlet/api/secure/jsonws/bindweixin/find-by-user-email?emailAddress=<%="${$parent.emailAddress}"%>",
                           alias:"bindInfo",
                           parentType:"list",
                           parentExp:".teacherList",
                           props:[
                                  {
                               	   url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?openid=<%="${$parent.bindWeixin.openId}"%>",
                               	   alias:"weixinInfo",
                               	   condition:"($parent.bindWeixin.openId && $parent.bindWeixin.openId.length !=0)"
                                  }
                           ]
                       }
                   ]
               })
            },
            async:false,
            success: function (data) {
            	tlist = dealClassMemberListAddRole(data);
            }
        });
    	
    	var teacherIds = [];
    	var teachersPriority = {};
    	var teachers = getDemonstrationClassTeachers(classId);
    	if(teachers){
    		$.each(teachers,function(index,obj){
    			teacherIds.push(obj.teacherId);
    			teachersPriority[obj.teacherId] = obj.priority;
    		});
    	}
    	
        var cdata = getClassInfo(classId,{school:true});
        var titleDiv = '<div style="text-align:center;">设置被观摩教师</div><div>'+cdata.school.name+' '+ cdata.classInfo.name+'</div>';

        var index = layer.open({
              type: 1,
              title: false,
              area: ['650px', '400px'],
              closeBtn: 1,
              shadeClose: true,
              scrollbar: false,
              content: '<div class="layerDiv">'+titleDiv+'<div id="settingDiv"></div>'+'</div>',
              end:function(){
              }
            });
        $('#settingDiv').attr('layerIndex',index);

        var tejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/viewTable/tableView.ejs.txt' });

        $('#settingDiv').html(tejs.render({
            list: tlist,
            columns: [
                { name: 'teacher', desc: '教师', format:function(v, ob){
                    var a = ob.screenName + "<br/>" + ob.emailAddress;
                    return a;
                } },
                { name: 'nickName', desc: '微信', format:function(v,ob){
                    var wxName = "未绑定";
                	if(ob.bindInfo.bindWeixin.openId && ob.bindInfo.weixinInfo){
                		wxName = ob.bindInfo.weixinInfo.nickname;
                	}
                    return wxName;
                } },
                { name: 'isOpen', desc: '观摩', format:function(v, ob){
                    return (teacherIds.indexOf(ob.userId) == -1?"否":"是");
                }  },
                { name: 'approve', desc: '操作', format:function(v, ob){
                    var isOpen = (teacherIds.indexOf(ob.userId) != -1);
                    
                    if(teacherIds.length != 0 && !isOpen){
                    	return '';
                    }
                    
                    var priority = teachersPriority[ob.userId];
                    
                    return ''+ 
                        '<button onclick="'+(isOpen?"cancel":"set")+'DemonstrationClassTeacher('+classId+','+ob.userId+')" class="btn btn-default" >'+
                        (isOpen?"解除观摩教师":"设为观摩教师")+
                        '</button>'+
                        '&nbsp&nbsp'+
                        '<button onclick="'+(priority?"cancel":"set")+'DemonstrationTeacherPriority('+classId+','+ob.userId+')" class="btn btn-default" >'+
                        (priority?"取消置顶":"置顶")+
                        '</button>';
                } },
            ],
            operations: []
        }));
    }
    </script>
</body>
</html>