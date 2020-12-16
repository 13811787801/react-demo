<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
	<style>
		textarea.content{height:200px;}
		.linkItem{line-height:34px;}
		.linkItem input{display:inline;width:300px;vertical-align:middle;}
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
    <script type="text/template" id="linkItemTmpl">
        <div class="linkItem">
			标题:<input class="title form-control"/><button type="button" class="delBtn btn btn-default">移除</button><br/>
			描述:<input class="description form-control"/><br/>
			图片链接:<input class="picUrl form-control"/><br/>
			文章链接:<input class="url form-control"/><br/>
        </div>
    </script>
    <script type="text/template" id="contentTmpl">
        <div class="row">
            <div class="col-xs-2">
                回复内容
            </div>
            <div class="col-xs-8">
				<div id="contentDiv">
				</div>
				<div>关键词替换<br/>[$SERVURL$]:当前服务器连接地址</div>
            </div>
        </div>
    </script>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    
    <script type="text/javascript">
        var fieldInfoList = [
            { name: ['keywordCondition', '关键词条件'], type: 'dropdown', data: [] },
            { name: ['msgType', '回复类型'], type: 'dropdown', data: [{k:0,v:'文本消息'},{k:1,v:'链接列表'}] },
        ];

        var tabInfoList = [
            { name: "baseTab", desc: "信息", data: fieldInfoList }
        ];

        function initList() {
            
            $('#dataRow').html('');

            $.ajax({
                url: "/yxxs-main-portlet/api/model/list_WechatKeyWord",
                type: "POST",
                dataType: "json",
                async:false,
                data: {page:0,pageSize:1000},
                success: function (data) {
                	for(var i=0;i<fieldInfoList.length;i++){
                		if(fieldInfoList[i].name[0] == 'keywordCondition'){
                			fieldInfoList[i].data = [];
                			for(var j=0;j<data.obj.length;j++){
                				fieldInfoList[i].data.push({k:data.obj[j].id,v:data.obj[j].keyword});
                			}
                		}
                	}
                }
            });
            
            $.ajax({
                url: "/yxxs-main-portlet/api/model/get_WechatAutoReply_<%=id %>",
                type: "POST",
                dataType: "json",
                data: { },
                success: function (data) {
                    initModelEdit('dataRow', data, tabInfoList, function(conf){
                    });
                    
                    var cEJS = new EJS({ element: "contentTmpl" });
                    $('#baseTab').append(cEJS.render({}));

                	var appendEmptyItemFunc = function(){
                        var sEJS = new EJS({ element: "linkItemTmpl" });
                        $('#linkItemDiv').append(sEJS.render({}));
                        
                        $('#linkItemDiv').find('.linkItem .delBtn').click(function(){
                        	$(this).parents('.linkItem').remove();
                        });
                	}
                	
                    var initValueFunc = function(v){
                    	if($('#msgType').val() == '1'){
                    		if(v.length){
                        		var list = JSON.parse(v);
                        		for(var i=0;i<list.length;i++){
                        			appendEmptyItemFunc();

                                    $('#linkItemDiv').find('.linkItem:eq('+i+')').find('.title').val(list[i].title);
                                    if(list[i].description){
                                        $('#linkItemDiv').find('.linkItem:eq('+i+')').find('.description').val(list[i].description);
                                    }
                                    $('#linkItemDiv').find('.linkItem:eq('+i+')').find('.picUrl').val(list[i].picUrl);
                                    $('#linkItemDiv').find('.linkItem:eq('+i+')').find('.url').val(list[i].url);
                        		}
                    		}
                    	}else{
                    		$('#content').val(v);
                    	}
                    }
                    
                    var initContentFunc = function(){
                    	$('#contentDiv').html('');
                    	if($('#msgType').val() == '1'){
                        	$('#contentDiv').append('<div id="linkItemDiv"></div><div><button type="button" id="addItem" class="btn btn-default">添加</button></div>');
                        	$('#linkItemDiv').html('');
                    		
                    		$('#addItem').click(function(){
                    			appendEmptyItemFunc();
                    		})
                    	}else{
                    		$('#contentDiv').append('<textarea id="content" class="content form-control"></textarea>');
                    	}
                    };
                    
                    initContentFunc();
                    initValueFunc(data.content);
                    
                    $('#msgType').change(function(){
                        initContentFunc();
                        initValueFunc('');
                    })
                }
            });
        }
        initList();
    </script>
    <script>
    function saveData(){

        var hash = getModelHash(tabInfoList);
        hash.id = $('#id').val();
        
        if(hash.msgType == 1){
        	var itemData = [];
        	$.each($('#contentDiv').find('.linkItem'),function(index, obj){
        		itemData.push({
        			title:$(obj).find('.title').val(),
        			picUrl:$(obj).find('.picUrl').val(),
        			url:$(obj).find('.url').val(),
        			description:$(obj).find('.description').val(),
        		});
        	});
        	var content = JSON.stringify(itemData);
        	hash.content = content;
        }else{
        	hash.content = $('#content').val();
        }

        $.ajax({
            url: "/yxxs-main-portlet/api/autoReply/addAutoReply",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
            	if(data.already){
            		alert('相同条件已经被使用了');
            		return;
            	}
                alert("保存成功");
                window.location.href='/yxxs-admin-portlet/admin/autoReply/autoReplyList';
            }
        });
    }
    </script>
</body>
</html>