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
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="/yxxs-admin-portlet/admin/teachMaterial/materialForm">添加材料解读资源</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <style>
    .layerDiv{margin:0 15px; padding-top:10px;}
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
        function initList() {
            
            $.ajax({
                url: "/yxxs-package-serv-portlet/api/package",
                type: "POST",
                dataType: "json",
                data: {
                	paramHashJsonStr:JSON.stringify({
                		url:"/yxxs-teach-material-serv-portlet/api/model/list_TeachMaterial?page=<%=pageNum - 1 %>&pageSize=10",
                		props:[
                		       {
                		    	   url:"/yxxs-main-serv-portlet/api/model/get_VideoShow_<%="${$parent.videoShowId}" %>",
                		    	   alias:"videoShowInfo",
                                   parentType:"list",
                                   parentExp:".obj",
                               		props:[
                            		       {
                            		    	   url:"/yxxs-file-serv-portlet/api/getFile_<%="${$parent.fileEntityId}"%>",
                            		    	   alias:"fileInfo",
                            		    	   condition:"($parent.fileEntityId>0)"
                            		       }
                          		    ]
                		       }
              		    ]
                	})
                },
                success: function (data) {
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "videoTitle", desc: "视频名称", format:function(v, ob){
                            	return ob.videoShowInfo.videoTitle;
                            } },
                            { name: "createDate", desc: "创建时间", format: function (v) { return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;} },
                        ],
                        operations: [{
                            name: "编辑材料",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/teachMaterial/materialForm?id=" + obj.id; }
                        },{
                            name: "编辑群组",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:setGroups(" + obj.id + ");"; }
                        },{
                            name: "查看视频",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/video/videoForm?id=" + obj.videoShowInfo.id; }
                        }],
                        pageUrlFunc: function(page){
	                        return '?&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
</body>
<script>

function setGroups(materialId){

	var tlist = [];
    $.ajax({
        url: "/yxxs-package-serv-portlet/api/package",
        type: "POST",
        dataType: "json",
        data: {
        	paramHashJsonStr:JSON.stringify({
        		url:"/yxxs-teach-material-serv-portlet/api/model/list_TeachMaterialExplanationGroup?page=0&pageSize=<%=Integer.MAX_VALUE%>",
                props:[
                   {
                       url:"/yxxs-teach-material-serv-portlet/api/getMaterialGroupList?groupId=<%="${$parent.id}"%>&materialId="+materialId+"&page=0&pageSize=1",
                       alias:"materialGroupInfo",
                       parentType:"list",
                       parentExp:".obj",
                       props:[
                       ]
                   }
               ]
           })
        },
        async:false,
        success: function (data) {
        	tlist = data.obj;
        }
    });
	
    var titleDiv = '<div style="text-align:center;">设置材料群组</div>';

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
            { name: 'groupName', desc: '群组名称', format:function(v, ob){
                return v;
            } },
            { name: 'approve', desc: '操作', format:function(v, ob){
                
                if(ob.materialGroupInfo.totalCount >0){
                	return '';
                }
                
                return ''+ 
                    '<button onclick="addMaterialGroup('+materialId+','+ob.id+')" class="btn btn-default" >'+
                    "添加到群组"+
                    '</button>';
            } },
        ],
        operations: []
    }));
}
function addMaterialGroup(materialId,groupId){
     jQuery.ajax(
         {
             type: "post",
             url: "/yxxs-teach-material-serv-portlet/api/addMaterialGroup",
             data:{
            	 materialId:materialId,
            	 groupId:groupId
             },
             dataType:'json',
             async:false,
             success: function(data){
                 layer.closeAll();
                 $('#settingDiv').attr('layerIndex',null);
                 
                 setGroups(materialId);
             }
         }
     );
}
</script>
</html>