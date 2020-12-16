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
    <%
    String sVideoTitle = "";
    if(null != request.getParameter("videoTitle") && !"".equals(request.getParameter("videoTitle"))){
    	sVideoTitle = request.getParameter("videoTitle");
    }
    %>
    <div class="container">
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="/yxxs-admin-portlet/admin/video/videoForm">添加视频</a></li>
              <li class="divider"></li>
              <li><a href="/yxxs-admin-portlet/admin/video/videoKeywordList">管理关键字</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="sVideoTitle" name="sVideoTitle"  value="<%=sVideoTitle %>"
                     placeholder="请输视频名">
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
    <style>
    </style>
    
    <div id="dataRow" class="container">
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	<%
	int pageNum = 1;
	try{
	    pageNum = Integer.parseInt(request.getParameter("page"));
	}catch(Exception ex){}
	%>
	
    <script type="text/javascript">
	    function search(){
	    	var page = <%=pageNum%>;
	        if('<%=sVideoTitle%>'!= $('#sVideoTitle').val()){
	            page = 1;
	        }
	        window.location.href = '?videoTitle='+$('#sVideoTitle').val()+ '&page=' + page;
	        
	    }
	    
        function initList() {
        	var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            if($('#sVideoTitle').val()){
                param.videoTitle = $('#sVideoTitle').val();
            }
            
            var paramArray = [];
            $.each(param,function(k,v){
            	paramArray.push(k+"="+v);
            });
            $.ajax({
                url: "/yxxs-package-serv-portlet/api/package",
                type: "POST",
                dataType: "json",
                data: {
                	paramHashJsonStr:JSON.stringify({
                		//url:"/yxxs-main-serv-portlet/api/model/list_VideoShow?"+paramArray.join('&'),
                		url:"/yxxs-main-serv-portlet/api/listVideoShow?"+paramArray.join('&'),
                		props:[
                		       {
                		    	   url:"/yxxs-main-serv-portlet/api/getTargetObjectTagList_VideoShow_<%="${$parent.id}"%>",
                		    	   alias:"tagList",
                                   parentType:"list",
                                   parentExp:".obj",
                               		props:[
                            		       {
                            		    	   url:"/yxxs-main-serv-portlet/api/model/get_KeyWordTag_<%="${$parent.tagId}"%>",
                            		    	   alias:"keywordTag",
                            		    	   parentType:"list",
                            		    	   parentExp:""
                            		       }
                          		    ]
                		       }
              		    ]
                	})
                },
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "videoTitle", desc: "视频名称" },
                            { name: "videoType", desc: "分类", format: function(v, ob){
                            	return ob.tagList.length?ob.tagList[0].keywordTag.keyword:'-';
                            } },
                            { name: "createDate", desc: "创建时间", format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} },
                            { name: "viewTimes", desc: "播放量" },
                            { name: "createUser", desc: "上传者" },
                        ],
                        operations: [{
                            name: "编辑视频",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/video/videoForm?id=" + obj.id; }
                        },{
                            name: "查看分享链接",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/video/videoShareList?id=" + obj.id; }
                        },{
                            name: "删除视频",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:delVideo(" + obj.id+");"; }
                        }],
                        pageUrlFunc: function(page){
	                        return '?'+'videoTitle='+$('#sVideoTitle').val()+'&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
</body>
<script>
function delVideo(id){
    if(confirm('是否删除?')){
        $.ajax({
            url: "/yxxs-main-serv-portlet/api/delVideoShow",
            type: "POST",
            dataType: "json",
            data: {id: id },
            async:false,
            success: function (data) {
                alert('删除成功!');
                initList();
            }
        });
    }
}
</script>
</html>