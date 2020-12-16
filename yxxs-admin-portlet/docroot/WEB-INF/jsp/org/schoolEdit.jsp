<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<script src="/yxxs-script-portlet/js/org/org.js?t=<%=ts%>"></script>
    <script src="/yxxs-static-portlet/js/place/place.js"></script>
	
    <script src="/yxxs-admin-portlet/js/admin/userSearch.js?t=<%=ts%>" type="text/javascript"></script>
    
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <%
    long id = 0l;
    try{
        id = Long.parseLong(request.getParameter("schoolId"));
    }catch(Exception ex){}
    %>
    
	<div class="container">
        <input type="hidden" id="schoolId" name="schoolId" value="<%=id%>">
	
        <div id="dataRow">
        </div>
        
		<div class="row">
			<div class="col-xs-12">
			   <div class="form-group">
	   				<button type="button" onclick="saveData();" class="btn btn-default">保存</button>
	   				<button type="button" id="repairBtn" onclick="repairManagerData();" class="btn btn-default">修复运营账号数据</button>
			   </div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/template" id="adminUserIdTmpl">
        <div class="row">
            <div class="col-xs-2">
                 管理员ID
            </div>
            <div class="col-xs-6">
               <div class="form-group">
                  <input type="text" class="form-control" id="emailAddress" name="emailAddress" value=""
                     placeholder="请输入邮箱">
					<span id="defaultManagerId" userId=""></span>
               </div>
            </div>
            <div class="col-xs-2">
               <div class="form-group">
                  <button type="button" class="btn btn-default" onclick="getUserId();">查询</button>
               </div>
            </div>
        </div>
    </script>
    
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['name', '学校名称'], desc:"注意：学校若可能同时包含小学部和初中部，学校名称请使用“学校名称+（小学部）”或“学校名称+（初中部）”格式" },
            { name: ['countryId', '国家'], type: 'dropdown', data: [{ k: 2, v: '中国' }] },
            { name: ['province', '省'], type: 'dropdown', data: [] },
            { name: ['city', '市'], type: 'dropdown', data: [] },
            { name: ['district', '街道'], type: 'dropdown', data: [] },
            { name: ['stageLevel', '学段'], type: 'dropdown', data: [{k: 1007, v: "小学"},{k: 1008, v: "初中"},{k: 1009, v: "高中"} ] },
            { name: ['priority', '优先级'], type: 'dropdown', data: [{k: 1, v: "置顶"},{k: 0, v: "取消置顶或置底"},{k: -1, v: "置底"} ] },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {

        	var setInfo = function(data){
        		
                initModelEdit('dataRow', data, tabInfoList, function(conf){
                });

                $('#countryId').val(2);
                
                var conf = {};
                if(data.address){
                    conf.province = data.address.province;
                    conf.city = data.address.city;
                    conf.district = data.address.district;
                }
                
                initPlace('province','city','district', conf);

                var uEJS = new EJS({ element: "adminUserIdTmpl" });
                $('#baseTab').append(uEJS.render({}));

        		searchUserById(data.defaultManagerId,function(d){
        			$('#defaultManagerId').html(d.desc);
        			$('#defaultManagerId').attr('userId',d.userId);
        		})
                
                if(0 != <%=id%>){
                    /*
                    $('#province').attr('disabled','disabled');
                    $('#city').attr('disabled','disabled');
                    $('#district').attr('disabled','disabled');
                    */
                    
                    $('#countryId').attr('disabled','disabled');
                    $('#stageLevel').attr('disabled','disabled');
                }
        	};

            if(0 != <%=id%>){
                $.ajax({
                    url: "/yxxs-main-portlet/api/getSchool?orgId=<%=id %>",
                    type: "POST",
                    dataType: "json",
                    data: { },
                    success: function (data) {
                        setInfo(data);
                    }
                });
            }else{
                setInfo({});
            }
        }
    </script>
    <script>
    function getUserId(){
    	
    	var setInfo = function(emailAddress){
    		searchUserByEmail(emailAddress,function(d){
    			$('#defaultManagerId').html(d.desc);
    			$('#defaultManagerId').attr('userId',d.userId);
    		})
    	}
    	
        jQuery.ajax(
            {
                type: "post",
                url: "/yxxs-main-portlet/api/getUser",
                data:{
                    emailAddress:$('#emailAddress').val()
                },
                dataType:'json',
                async:true,
                success: function(data){
                	if(!data.error){
                		setInfo($('#emailAddress').val());
                	}else{
                		if(confirm("账户不存在, 立即创建并设置管理员?")){

                	        var lindex = layer.msg('正在创建账户，请耐心等待', {icon: 16, time: 300*1000,shade: [0.3,'#000']});

                            jQuery.ajax({
                                type: "post",
                                url: '/yxxs-main-portlet/api/registerUser',
                                async:true,
                                data:{
                                    emailAddress:$('#emailAddress').val(),
                                    userName:'优学老师',
                                    userType:3,
                                    autoPassword:false,
                                    sendEmail:false,
                                },
                                dataType : 'json',
                                success: function (udata){
                                    if(udata.key!=true){
                                    }else{
                                		setInfo($('#emailAddress').val());
                                        alert('创建完成');
                                    }
                                    layer.close(lindex);
                                }
                            });
                		}
                	}
                },
                error:function(){
                }
            }
        );
    }
    function searchUserInfo(){
    	
    }
    </script>
    <script>
    function repairManagerData(){
         var schoolId = $('#schoolId').val();
         var schoolInfo = getSchoolInfo(schoolId);
         if(schoolInfo && schoolInfo.defaultManagerId){
             
             var defaultManagerId = schoolInfo.defaultManagerId;
             
             $.ajax({
                     type:'GET',
                     url:"/yxxs-package-serv-portlet/api/package",
                     data:{
                         paramHashJsonStr:JSON.stringify({
                             url:"/yxxs-main-portlet/api/listClass?schoolId="+schoolId+"&page=0&pageSize=10000",
                             props:[
                                 {   
                                     url:"/yxxs-main-portlet/api/directAddUserToClass?roleId=14217&classId=<%="${$parent.organizationId}"%>&userId="+defaultManagerId,
                                     alias:"addInfo",
                                     dealFunc:"function(ob){return {};}",
                                     parentExp:".obj",
                                     parentType:"list"
                                 }
                             ]
                         })
                     },
                     async:true,
                     dataType:'json',
                     success:function(data){
                         console.log('数据修复完成');
                     }
             });
             
             alert('数据修复完成');
         }
    }
    
    function saveData(){

    	var isNew = (0 == <%=id%>);

        var hash = getModelHash(tabInfoList);
        hash.schoolId = $('#schoolId').val();
        hash.schoolName = hash.name;
        hash.defaultManagerId = $('#defaultManagerId').attr('userId');
        
        hash.page = 0;
        hash.pageSize = 1000;
        
        $.ajax({
            url: "/yxxs-main-portlet/api/listSchool",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (ldata) {
            	if(ldata.obj.length >0){
                    for(var i=0;i<ldata.obj.length;i++){
                        if(ldata.obj[i].name == hash.name && ldata.obj[i].organizationId != hash.schoolId){
                            alert('区域内学校已经存在!');
                            return;
                        }
                    }
            	}

                var url = "/yxxs-main-portlet/api/addSchool";
                if(!isNew){
                    url = "/yxxs-main-portlet/api/updateSchool";
                }
                
                $.ajax({
                    url: url,
                    type: "POST",
                    dataType: "json",
                    data: hash,
                    success: function (data) {

                        if(!hash.schoolId){
                            hash.schoolId = data.organizationId;
                        }
                        
                        alert("保存成功");
                        window.location.href='/yxxs-admin-portlet/admin/org/schoolList';
                    }
                });
            }
        });
    }
    
    
    $(function(){
    	var isNew = <%=id%>?false:true;
        if(isNew){
            $('#repairBtn').hide();
        }
        initList();
    });
    </script>
</body>
</html>