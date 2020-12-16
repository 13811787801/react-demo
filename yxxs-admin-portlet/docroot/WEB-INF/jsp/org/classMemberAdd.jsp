<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
    
    <script src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
	
    <script src="/yxxs-static-portlet/js/place/place.js"></script>
	
	<style>
	#dataRow{margin-bottom:15px;}
	.error{color:red;}
	</style>
	
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("classId"));
    }catch(Exception ex){}
    %>
    
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  
               </div>
            </div>
        </div>
    </div>
    
	<div class="container">
        <input type="hidden" id="classId" name="classId" value="<%=id%>">
	
        <div id="dataRow">
            <div class="row form-group">
                <div class="col-xs-3 control-label">
                    <span>学校信息</span>
                </div>
                <div class="col-xs-6">
                    <span id="classInfo"></span>
                </div>
            </div>
	        <div class="row form-group">
			    <div class="col-xs-3 control-label">
			        <span>生成用户名邮箱后缀</span>
			    </div>
			    <div class="col-xs-6">
			        <input type="text" class="form-control" id="suffix" name="suffix"  value="iyxxs.cn"
                     placeholder="请输入用户邮箱后缀名, 如iyxxs.cn">
			    </div>
			</div>
	        <div class="row form-group">
	            <div class="col-xs-3">
	               <textarea rows="15" cols="10" id="studentNameList" class="form-control" placeholder="请输入学生名字列表, 一行一个, 如
张三
李四
王五"></textarea>
<br/>
                   <textarea rows="4" cols="10" id="teacherNameList" class="form-control"placeholder="请输入老师名字列表, 一行一个, 如
张三
李四
王五"></textarea>
	            </div>
	            <div class="col-xs-9">
			        <div id="tmpRow" class="">
			        </div>
	            </div>
	        </div>
        </div>
        
		<div class="row">
			<div class="col-xs-12">
			   <div class="form-group">
                    <button type="button" onclick="makeTable();" class="btn btn-default">生成用户表</button>
	   				<button type="button" onclick="directAddUser();" class="btn btn-default">创建班级用户</button>
			   </div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/javascript">
        function init(){
        	
            var cdata = getClassInfo(<%=id %>,{school:true});
            var data = cdata.classInfo;

            var s = '<a href="/yxxs-admin-portlet/admin/org/classList?schoolId='+cdata.school.organizationId+'">'+cdata.school.name + "</a>";
            var c = '<a href="/yxxs-admin-portlet/admin/org/classMemberForm?classId='+data.organizationId+'">'+data.name + "</a>";
            
            $('#classInfo').html(s + "-" + c + "("+data.schoolYear+"入学)");
            
            if(cdata.school.defaultManagerId){
                $.ajax({
                    url: "/yxxs-main-portlet/api/getUser?userId="+cdata.school.defaultManagerId,
                    type: "POST",
                    dataType: "json",
                    data: { },
                    success: function (udata) {
                        $('#suffix').val(udata.emailAddress.substring(0,udata.emailAddress.indexOf('@')) + ".com");
                    }
                });
            }
        }
        init();
    </script>
    <script type="text/javascript">
        function makeTable() {
        	
        	var infoList = [];
        	
        	var studentNameList = $('#studentNameList').val().split('\n');
            var teacherNameList = $('#teacherNameList').val().split('\n');
            $.each(studentNameList,function(index, obj){
            	if(obj ==''){
            		return;
            	}
                infoList[infoList.length] = {hanzi:obj,type:1};
            });
            $.each(teacherNameList,function(index, obj){
                if(obj ==''){
                    return;
                }
                infoList[infoList.length] = {hanzi:obj,type:3};
            });
            
            $.each(infoList,function(index,obj){
                jQuery.ajax(
                    {
                        type: "post",
                        url: "/yxxs-main-portlet/api/util/hanzi2pinyin?str="+obj.hanzi,
                        data:{},
                        dataType:'json',
                        async:false,
                        success: function(data){
                            obj.pinyin = data.pinyin;
                        }
                    }
                );
                obj.index = index;
            });


            var setEmail = function(index){
                infoList[index].emailAddress = null;
                
                var email = infoList[index].pinyin + (infoList[index].ext?infoList[index].ext:"") + '@'+$('#suffix').val();
                
                jQuery.ajax(
                        {
                            type: "post",
                            url: "/yxxs-main-portlet/api/getUser?emailAddress="+email,
                            data:{},
                            dataType:'json',
                            async:false,
                            success: function(data){
                                if(data.error){
                                    infoList[index].emailAddress = email;
                                }

                                if(!infoList[index].emailAddress){
                                    if(typeof infoList[index].ext == 'undefined'){
                                        infoList[index].ext = 0;
                                    }
                                    infoList[index].ext++;
                                    setEmail(index);
                                }
                            }
                        }
                    );
            }
            
            for(var i=0;i<infoList.length;i++){
                setEmail(i);
            }
            
            var userList = {page:0, pageSize:10000, pageCount:1};
            userList.obj = infoList;
            userList.totalCount = userList.obj.length;

            initTableList('tmpRow', userList, {
                columns: [
                    { name: "index", desc: "导入?", format:function(v,obj){
                        return '<input type="checkbox" class="cbk" checked value="'+v+'" />';
                    } },
                    { name: "hanzi", desc: "名字", format:function(v,obj){
                        return "<span class='hanzi'>"+obj.hanzi+"</span>";
                    } },
                    { name: "hanzi", desc: "拼音", format:function(v,obj){
                        return "<span class='pinyin'>"+obj.pinyin+"</span>";
                    } },
                    { name: "emailAddress", desc: "邮箱", format:function(v,obj){
                    	return '<input class="emailAddress form-control" value="'+v+'" />';
                    } },
                    { name: "type", desc: "用户类型", format: function(obj){
                    	var options = "";
                        if(obj == 1){
                        	options+='<option value="14214,1">学生</option>';
                        }
                        if(obj == 2){
                            options+='<option value="14215,2">家长</option>';
                        }
                        if(obj == 3){
                            options+='<option value="14216,3">老师</option>';
                            options+='<option value="14217,3">班主任</option>';
                        }
                        return '<select class="userType form-control">'+options+'</select>';
                    } },
                ],
                operations: [],
                pageUrlFunc: function(page){
                    return null;
                }
            });
            
            $('#tmpRow').find('input.emailAddress').change(function(){

            	var input = $(this);
                var email = input.val();
                jQuery.ajax(
                        {
                            type: "post",
                            url: "/yxxs-main-portlet/api/getUser?emailAddress="+email,
                            data:{},
                            dataType:'json',
                            async:false,
                            success: function(data){
                                input.removeClass('error');
                                if(!data.error){
                                    input.addClass('error');
                                }
                            }
                        }
                    );
            });
        }
    </script>
    <script>
    function directAddUser(){
        var hash = {};
        hash.classId = <%=id %>;
        
        
        var errCnt = 0;
        $.each($('#tmpRow').find('input.cbk:checked'),function(index,obj){
            if($(this).parents('tr').find('input.emailAddress.error').length >0){
            	errCnt++;
            }
        });
        
        if(errCnt>0){
        	alert('需要导入的邮箱错误, 请检查');
        	return;
        }

        var lindex = layer.msg('正在创建账户，请耐心等待', {icon: 16, time: 300*1000,shade: [0.3,'#000']});

        var cnt = $('#tmpRow').find('input.cbk:checked[done!=true]').length;
        $.each($('#tmpRow').find('input.cbk:checked[done!=true]'),function(index,obj){
        	
        	var tr = $(this).parents('tr');

            jQuery.ajax({
                type: "post",
                url: '/yxxs-main-portlet/api/registerUser',
                async:true,
                data:{
                    emailAddress:$(tr).find('input.emailAddress').val(),
                    userName:$(tr).find('span.hanzi').html(),
                    userType:$(tr).find('select.userType').val().split(',')[1],
                    autoPassword:false,
                    sendEmail:false,
                },
                dataType : 'json',
                success: function (data){
                    if(data.key!=true){
                        cnt--;
                        errCnt++;
                        if(cnt == 0){
                            layer.close(lindex);
                            alert('创建完成,'+errCnt+'失败');
                        }
                	}else{
                        var userId = data.userId;
                        
                        $.ajax({
                            url: "/yxxs-main-portlet/api/directAddUserToClass",
                            type: "POST",
                            dataType: "json",
                            data: {
                                userId:userId,
                                classId:hash.classId,
                                roleId:$(tr).find('select.userType').val().split(',')[0]
                            },
                            success: function (adata) {

                            	$(tr).find('input.cbk').attr('disabled',"true");
                                $(tr).find('input.cbk').attr('done',"true");
                                $(tr).find('input.emailAddress').attr('readonly',"readonly");
                                $(tr).find('select.userType').attr('disabled',"true");
                            	
                                cnt--;
                                if(cnt == 0){
                                    layer.close(lindex);
                                    alert('创建完成,'+errCnt+'失败');
                                }
                            }
                        });
                	}
                }
            });
        });
    }
    </script>
    
</body>
</html>