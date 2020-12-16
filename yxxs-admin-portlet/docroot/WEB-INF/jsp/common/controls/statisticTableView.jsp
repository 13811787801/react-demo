<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
columns数据,list数据,pi数据

//自定义页面跳转函数, 添加自定义变量
function pageUrl(page){
    return '?&page=' + page;
}
//自定义列宽
var columnWidth = {'columnName':100};
 
//自定义pager初始化函数
function initPagerFunc(){
   initPager("pager", pi, function (page) {
       return pageUrl(page)+ '&pageSize=<%=pageSize%>';
   });
}
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <%
    int pageNum = 1;
    try{
        pageNum = Integer.parseInt(request.getParameter("page"));
    }catch(Exception ex){}
    int pageSize = 50;
    try{
        pageSize = Integer.parseInt(request.getParameter("pageSize"));
    }catch(Exception ex){}
    
    String columnHeaderIndexes = request.getParameter("columnHeaderIndexes");
    %>
    
    <style>
       .table tr td{min-width:70px;padding:2px 6px!important;}
       
       .layerDiv{padding:15px 0px;}
       
       .headerSelectOp{float:right;position:absolute;margin-left:-60px;}
       .headerSelectOp .btn{padding:2px;}
        @media(max-width:880px){
            .headerSelectOp{margin-left:0px;margin-top:-27px;}
        }
    </style>
    
    <div class="container">
       <div class="headerSelectOp" style="">
           <a href="javascript:selectHeaders();" class="btn btn-sm btn-default" >选择表头</a>
       </div>
    </div>
    
    <div class="container ct">
	    <div class="panel panel-success" style="overflow:hidden;">
	        <table class="table table-bordered table-striped">
	          <tr>
		          <c:forEach var="c" items="${columns}">
		            <td class="td_${c[1]}" cName="${c[1]}">${c[0]}</td>
		          </c:forEach>
	          </tr>
	          <c:forEach var="l" items="${list}" varStatus="st">
	            <tr>
	             <c:forEach var="c" items="${columns}">
	                <td class="td_${c[1]}">${l[c[1]]}</td>
	             </c:forEach>
	          </tr>
	        </c:forEach>
	      </table>
	    </div> 
    </div>
    
    <link rel="stylesheet" href="/yxxs-static-portlet/js/jqueryUI/jquery-ui.min.css">
    <script src="/yxxs-static-portlet/js/jqueryUI/jquery-ui.min.js"></script>
    <div class="container">
	    <div id="slider" style="width:200px;position:fixed;right:50px; bottom:100px;">
	    </div>
    </div>

    <div class="container">
        <div id="pager" style="text-align:center;width:100%;"></div>
    </div>
    
    
    <script>
    function selectHeaders(){

        var titleDiv = '<div style="text-align:center;">设置统计表头</div>';

        var inputList = [];
        <c:forEach var="c" items="${allColumns}">
        inputList.push('<div><input name="columnCbk" id="cbk_${c[1]}" type="checkbox" value="${c[1]}"><label for="cbk_${c[1]}">${c[0]}</label></div>');
        </c:forEach>
        
        var btnDiv = '<div style="text-align:center;"><a class="btn btn-default" href="javascript:reloadHeaderTable();">确定</a></div>';
        
        var index = layer.open({
              type: 1,
              title: false,
              area: ['300px', '400px'],
              closeBtn: 1,
              shadeClose: true,
              scrollbar: false,
              content: '<div class="layerDiv">'+titleDiv+'<div style="max-height:310px; padding:0 15px;overflow-y:scroll;">'+inputList.join('')+'</div>'+btnDiv+'</div>',
              end:function(){
              }
            });
        $('.layerDiv').attr('layerIndex',index);
        
        $.each($('.table tr:first td'),function(index,obj){
        	document.getElementById('cbk_'+$(obj).attr('cName')).checked = true;
        });
    }
    function reloadHeaderTable(){
    	
    	var columnHeaderIndexes = [];
    	$.each($('input[name=columnCbk]:checked'), function(index, obj){
    		columnHeaderIndexes.push($(obj).val().replace('column_',''));
    	});
    	if(columnHeaderIndexes.length == 0){
    		alert('至少选择一列');
    		return;
    	}
    	
    	var url = ((typeof pageUrl != 'undefined')?pageUrl(<%=pageNum%>):'?&page=<%=pageNum%>') + '&pageSize=<%=pageSize%>' + '&columnHeaderIndexes='+columnHeaderIndexes.join(',');
    	window.location = url;
    }
    </script>
    
    <script>
    var pi = {
    	pageNumber:${pi.pageNumber-1},
    	pageSize:${pi.pageSize},
    	pageCount:${pi.pageCount},
    	totalCount:${pi.totalCount}
    };
    
    $(document).ready(function(){
        if((typeof columnWidth != 'undefined') || (typeof columnHidden != 'undefined')){
            $.each($('.table tr:first td'),function(index,obj){
                var c = $(obj).html();
                if((typeof columnWidth != 'undefined') && columnWidth[c]){
                    $('.table tr td.td_'+$(obj).attr('cName')).css('min-width',columnWidth[c]+'px');
                }
                if((typeof columnHidden != 'undefined') && columnHidden[c])
                    $('.table tr td.td_'+$(obj).attr('cName')).hide();
            });
        }

        if($('.table').width()>$('.table').parent().width()){
        	var scrollFunc = function(){
                $('.table').css('margin-left',0-($("#slider").slider( "value")*($('.table').width()-$('.table').parent().width())/100));
        	}
            $( "#slider" ).slider({
                max:100,
                value:0,
                slide:function(){
                	scrollFunc();
                },
                change:function(){
	                scrollFunc();
	            }
            });
        }
        
        if(typeof initPagerFunc != 'undefined'){
        	initPagerFunc();
        }else{
            initPager("pager", pi, function (page) {
                return ((typeof pageUrl != 'undefined')?pageUrl(page):'?&page=' + page ) + '&pageSize=<%=pageSize%>' + '&columnHeaderIndexes=<%=null == columnHeaderIndexes?"":columnHeaderIndexes%>';
            });
        }
    });
    </script>
