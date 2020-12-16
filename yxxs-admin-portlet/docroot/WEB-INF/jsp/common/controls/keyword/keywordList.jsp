<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
int keyword_type = 0;
try{
	if(null != request.getAttribute("keyword.keywordType")){
		keyword_type = Integer.parseInt(request.getAttribute("keyword.keywordType").toString());
	}
}catch(Exception ex){}
%>
    
    <div class="container">
    </div>
    
    <style>
    .layerDiv{margin:0 15px; padding-top:10px;}
    </style>
    
    <div class="container">
       <div class="dropdown" style="float:right;">
           <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                操作
              <b class="caret"></b>
           </a>
           <ul class="dropdown-menu">
              <li><a href="javascript:startEditKeyword()">添加关键词</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <div id="dataRow" class="container">
    </div>

    <script type="text/javascript">
	    function search(){
	    	initDataRow();
	    }
	 
	    var tagList = null;
	    function initDataRow(){
	    	
	    	var data = {pageNumber:0,pageCount:1,pageSize:10000,totalCount:0,obj:[]};
	    	for(var i=0;i<tagList.length;i++){
	    		data.totalCount++;
	    		data.obj[data.obj.length] = tagList[i];
	    	}
	    	
            initTableList('dataRow', data, {
                columns: [
                    { name: "keyword", desc: "关键词" },
                ],
                operations: [{
                    name: "编辑关键词",
                    visable: function (obj) { return true },
                    getUrl: function (obj) { return "javascript:startEditKeyword("+obj.id+")"; }
                },{
                    name: "删除关键词",
                    visable: function (obj) { return false },
                    getUrl: function (obj) { return "javascript:delKeyword(" + obj.id+");"; }
                }],
                pageUrlFunc: function(page){
                    return null;
                }
            });
	    }
	    
        function initList() {
        	
            $.ajax({
                url: "/yxxs-main-serv-portlet/api/getKeywordTagList?type=<%=keyword_type%>",
                type: "POST",
                dataType: "json",
                data: {},
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
function startEditKeyword(id){
    var keywordInfoList = [
        { name: ['keyword', '关键词'] },
        { name: ['btn', ''], mode: 'view', format:function(v, ob){
            return ''+ 
            	'<input type="hidden" class="id" value="'+(ob.id?ob.id:"")+'">'+
	            '<button onclick="saveKeyword()" class="btn btn-default" >保存</button>'+
	            '';
        } },
    ];

    var keywordTabInfoList = [
        { name: "keywordTab", desc: "关键词信息", data: keywordInfoList },
    ];
	
	var index = layer.open({
		  type: 1,
		  title: false,
		  area: ['650px', '300px'],
		  closeBtn: 1,
		  shadeClose: true,
		  scrollbar: false,
		  content: '<div id="keywordDiv" class="layerDiv"></div>'
		});
    $('#keywordDiv').attr('layerIndex',index);

    var initShow = function(data){
        initModelEdit('keywordDiv', data, keywordTabInfoList, function(conf){
        });
    }
    
    if(id){
        $.ajax({
            url: "/yxxs-main-serv-portlet/api/model/get_KeyWordTag_"+id,
            type: "POST",
            dataType: "json",
            success: function (data) {
            	initShow(data);
            }
        });
    }else{
    	initShow({});
    }
}
</script>
<script>
function saveKeyword(){

    var hash = {};
    hash.id = $('#keywordDiv .id').val();
    hash.keyword = $('#keywordDiv .keyword').val();
    hash.type = <%=keyword_type%>;

    $.ajax({
        url: "/yxxs-main-serv-portlet/api/saveKeywordTag",
        type: "POST",
        dataType: "json",
        data: hash,
        success: function (data) {
            alert("保存成功");
            
            layer.closeAll();
            $('#keywordDiv').attr('layerIndex',null);
            
            initList();
        }
    });
}
function delKeyword(id){
    if(confirm('是否删除?')){
        jQuery.ajax(
                {
                    type: "post",
                    url: "/yxxs-main-serv-portlet/api/delKeywordTag",
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