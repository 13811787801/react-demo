<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-admin-portlet/js/admin/userSearch.js?t=<%=ts%>" type="text/javascript"></script>
    
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
    
    <script type="text/template" id="expertUserIdTmpl">
        <div class="row">
            <div class="col-xs-2">
                专家ID
            </div>
            <div class="col-xs-6">
               <div class="form-group">
                  <input type="text" class="form-control" id="emailAddress" name="emailAddress" value=""
                     placeholder="请输入邮箱">
                    <span id="expertId" userId=""></span>
               </div>
            </div>
            <div class="col-xs-2">
               <div class="form-group">
                  <button type="button" class="btn btn-default" onclick="getUserId();">查询</button>
               </div>
            </div>
        </div>
    </script>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['title', '用户抬头'] },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');     

            var initFunc = function(data){
                initModelEdit('dataRow', data, tabInfoList, function(conf){
                });

                var uEJS = new EJS({ element: "expertUserIdTmpl" });
                $('#baseTab').append(uEJS.render({}));

                searchUserById(data.userId,function(d){
                    $('#expertId').html(d.desc);
                    $('#expertId').attr('userId',d.userId);
                    $('#expertId').attr('emailAddress',d.emailAddress);
                });
            };
            
            if(<%=id %> == 0){
                initFunc({});
            }else{
                $.ajax({
                     url:"/yxxs-package-serv-portlet/api/package",
                     dataType:"json",
                     type:"POST",
                     data:{
                         paramHashJsonStr:JSON.stringify({
                             url:"/yxxs-main-portlet/api/model/get_Expert_<%=id %>",
                             props:[
                                 {
                                     url:"/yxxs-main-portlet/api/getUser?userId=<%="${$parent.userId}" %>",
                                      alias:"userInfo",
                                      parentExp:"",
                                 }
                             ]
                         })
                     },
                     async:false,
                     success: function (data) {
                         initFunc(data);
                     }
                });
            }
        }
        initList();
    </script>
    <script>
    function getUserId(){
        var setInfo = function(emailAddress){
            searchUserByEmail(emailAddress,function(d){
                $('#expertId').html(d.desc);
                $('#expertId').attr('userId',d.userId);
                $('#expertId').attr('emailAddress',d.emailAddress);
            })
        };
        
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
                	if(data.error){
                		alert('账户不存在');
                		return;
                	}
                    setInfo($('#emailAddress').val());
                },
                error:function(){
                }
            }
        );
    }
    </script>
    <script>
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.emailAddress = $('#expertId').attr('emailAddress');
        
        $.ajax({
            url: "/yxxs-main-portlet/api/saveExpert",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                if(data.error){
                    alert('保存失败!');
                    return;
                }
                
                //alert("保存成功");
                window.location.href = '/yxxs-admin-portlet/admin/user/expertList';
            }
        });
    }
    </script>
</body>
</html>