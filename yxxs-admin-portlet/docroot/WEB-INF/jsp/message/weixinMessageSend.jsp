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
		<div class="row">
            <div class="col-xs-2">
                 <label for="name">微信openId</label>
		    </div>
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="emailAddress" name="emailAddress" value=""
                     placeholder="请输入邮箱">
               </div>
            </div>
            <div class="col-xs-2">
               <div class="form-group">
                  <button type="button" class="btn btn-default" onclick="getOpenId();">查询</button>
               </div>
            </div>
			<div class="col-xs-5">
			   <div class="form-group">
			      <input type="text" class="form-control" id="openId" name="openId" value=""
			         placeholder="请输入邮箱自动带出" readonly="readonly">
			   </div>
			</div>
		</div>
           <div class="row">
               <div class="col-xs-2">
                    <label for="name">微信消息模板Id</label>
               </div>
               <div class="col-xs-10">
                  <div class="form-group">
                     <input type="text" class="form-control" id="tempId" name="tempId" value="${tmplId }" 
                       readonly="readonly" placeholder="请输入tempId">
                  </div>
                  <div class="form-group">
                     <textarea rows="10" cols="150" class="form-control"  id="tmplContent" name="tmplContent" 
                       readonly="readonly" ></textarea>
                  </div>
               </div>
           </div>
           <div class="row">
               <div class="col-xs-2">
                    <label for="name">消息数据</label>
               </div>
               <div class="col-xs-10">
                  <input type="hidden" id="kv">
                  <div id="k_v">
                  </div>
               </div>
           </div>
		<div class="row">
			<div class="col-xs-12">
			   <div class="form-group">
	   				<button type="button" class="btn btn-default" onclick="sendMessage();">发送</button>
			   </div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
<script>

function sendMessage(){

	var jsonParam = {};
	
	var kv = JSON.parse($('#kv').val());
	for(var i=0;i<kv.length;i++){
		jsonParam[kv[i]] = $('#kv_'+i).val();
	}
	
    jQuery.ajax(
        {
            type: "post",
            url: "/yxxs-message-serv-portlet/api/sendWeixinMessage",
            data:{
            	openId:$('#openId').val(),
                tempId:$('#tempId').val(),
                jsonParam:JSON.stringify(jsonParam)
            },
            dataType:'json',
            async:true,
            success: function(data){
            	alert("发送成功");
            },
            error:function(){
            }
        }
    );
}

function getOpenId(){

    jQuery.ajax(
        {
            type: "post",
            url: "/yxxs-wx-serv-portlet/api/secure/jsonws/bindweixin/find-by-user-email",
            data:{
            	emailAddress:$('#emailAddress').val()
            },
            dataType:'json',
            async:true,
            success: function(data){
            	$('#openId').val(data.bindWeixin.openId);
            },
            error:function(){
            }
        }
    );
}

$(function(){
    jQuery.ajax(
        {
            type: "get",
            url: "/yxxs-message-serv-portlet/api/getWeixinMessageTmpl",
            data:{
            	tempId:$('#tempId').val()
           	},
            dataType:'json',
            async:true,
            success: function(data){
            	$('#tmplContent').val(data.tmpl);

            	var kv = [];
            	
                var tejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/EditRow/textEditRow.ejs.txt' });
            	for(var i in data.emptyHash){

                    var conf = {
                        label: i,
                        FileName: 'kv_'+kv.length,
                        value: null, desc: null, data: null,  setting: null 
                    };
                    kv.push(i);

                    $('#k_v').append(tejs.render(conf));
            	}
                $('#kv').val(JSON.stringify(kv));
                
            	
            },
            error:function(){
            }
        }
    );
})
</script>
</html>