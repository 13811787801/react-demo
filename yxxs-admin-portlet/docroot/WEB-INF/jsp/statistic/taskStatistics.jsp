<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%
   String province = request.getParameter("province");
	if(null == province){
		province = "";
	}
   String city = request.getParameter("city");
    if(null == city){
    	city = "";
    }
    String district = request.getParameter("district");
    if(null == district){
    	district = "";
    }
    String school = request.getParameter("school");
    if(null == school){
    	school = "";
    }
    String name = request.getParameter("name");
    if(null == name){
    	name = "";
    }
    String startTime = request.getParameter("startTime");
    if(null == startTime){
    	startTime = "2017-01-01";
    }
   String interactRate = request.getParameter("interactRate");
   if(null == interactRate){
	   interactRate = "0";
   }
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="/yxxs-static-portlet/js/place/place.js"></script>
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
	<style>
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <script>
    function pageUrl(page){
        return '?&page=' + page + pagerUrlFunc();
    }
    
    var columnWidth = {'任务名称':220,'互动统计':190,'地区':140,'任务附件':220,'学校名称':180,'发布班级':100,'创建时间':90,'任务说明':400};
    var columnHidden = {'电脑链接':true};
    </script>
	
	<div class="container">
        <div class="row">
            <div class="form-inline">
                <div class="form-group">
                    <span class="margin15">省：</span>
                    <select id="province" class="form-control"></select>
                    <span class="margin15">市：</span>
                    <select id="city" class="form-control">
                    </select>
                    <span class="margin15">区：</span>
                    <select id="district" class="form-control">
                    </select>
                    <input id='school' type="text" class="form-control margin15 width265" value=""  placeholder="学校名称"/>
                    <input id='name' type="text" class="form-control  margin15 width265" value="" placeholder="发布人姓名"/>
                </div>
            </div>
            <div class="form-inline margintb15">
                <div class="form-group">
                    <span class="margin15">起始时间：</span>
                    <input type="text" class="form-control" style="width:120px;" id="startTime" readonly="readonly"
                        onclick="WdatePicker({isShowClear:true,maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                    <span class="margin15">参与率：</span>
                    <select id="interactRate" class="form-control">
                        <option value="0"><a href="javascript:;">全部</a></option>
                        <option value="3"><a href="javascript:;">高互动率</a></option>
                        <option value="2"><a href="javascript:;">中互动率</a></option>
                        <option value="1"><a href="javascript:;">低互动率</a></option>
                    </select>
                    <input type="button" id="search" onclick="search();" value="搜索" class="btn btn-primary margin80" />
                    <input type="button" id="search" onclick="setNoCondition();" value="条件置空" class="btn btn-danger margin80" />
                    <input type="button" id="search" onclick="excel();" value="导出excel" class="btn btn-default margin80" />
                </div>
            </div>
        </div>
        <div style="margin-bottom:10px;">默认起始时间是2017年1月1日，选定的起始时间为当天的0点 ,高互动率：&gt;50% ,中互动率：&gt;30%且&lt;=50% ,低互动率：&lt;=30%</div>
        <div style="margin-bottom:10px;">注意:学校和区域数据有10分钟延迟</div>
    </div>
    
    <%@ include file="/WEB-INF/jsp/common/controls/statisticTableSelectView.jsp" %>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
	 <script type="text/javascript">
	 
	    function search(){
	        var url = '?&page=1' + pagerUrlFunc();
	        
	        window.location = url;
	    }
	    
	    function setNoCondition(){
	    	$('#province').val('');
	    	$('#city').val('');
	    	$('#district').val('');
	    	$('#startTime').val('2017-01-01');
	    	$('#name').val('');
	    	$('#school').val('');
	    	$('#interactRate').val('0');
	    }
	    
	    function excel(){
            var url = '?'+pagerUrlFunc()+"&exportExcel=true";
            
            window.location = url;
	    }
	    
	    function pagerUrlFunc(){
            return ''+
            '&province='+($('#province').val()?$('#province').val():'')+'&city='+($('#city').val()?$('#city').val():'')+'&district='+($('#district').val()?$('#district').val():'')+
            '&school='+$('#school').val()+
            '&name='+$('#name').val()+'&startTime='+$('#startTime').val()+'&interactRate='+$('#interactRate').val();
	    }
	     
        $(function(){
            initPlace('province','city','district', {
                enableNull:true,
                province:'<%=province%>',
                city:'<%=city%>',
                district:'<%=district%>',
            });
            
       
            $('#school').val('<%=school%>');
            $('#name').val('<%=name%>');
            $('#startTime').val('<%=startTime%>');
            $('#interactRate').val('<%=interactRate%>');

            initPager("pager", pi, function (page) {
                return pageUrl(page)+ '&pageSize=<%=pageSize%>';
            });
            
            $('#pager ul').append('<li><a>-第'+(pi.pageNumber+1)+'页-共'+pi.pageCount+'页-共'+pi.totalCount+'条-</a></li>');
	        
		    var columns = {taskClass:'',linkClass:'',author:''};
            $.each($('.table tr:first td'),function(index,obj){
                var c = $(obj).html();
                if(c == '任务名称')
                	columns.taskClass = $(obj).attr("class");
                if(c == '电脑链接')
                	columns.linkClass = $(obj).attr("class");
                if(c == '发布人')
                       	columns.author = $(obj).attr("class");
            });
            
            $.each($('.table tr:gt(0)'),function(index,obj){
            	var childColumns = {taskValue:'',linkValue:'',authorValue:''};
                
          		$.each($(obj).children(),function(k,v){
          			if($(v).attr("class") == columns.taskClass)
          		        childColumns.taskValue = $(v).text();
          		    if($(v).attr("class") == columns.linkClass)
                        childColumns.linkValue = $(v).text();
          		    if($(v).attr("class") == columns.author)
          		        childColumns.authorValue = $(v).text();
          		});
          		
          		$.each($(obj).children(),function(k,v){
                    if($(v).attr("class") == columns.taskClass)
                        $(v).html('<a href="'+childColumns.linkValue+'">'+childColumns.taskValue+'</a>');
                    if($(v).attr("class") == columns.author){
                    	var arr = childColumns.authorValue.split(",");
                    	$(v).html(arr[0]+"<br>"+arr[1]);
                    }
                });
            });
        });
        
        function initPagerFunc(){
            //不自动加载, 保证变量初始化
        }
    </script>
    
</body>
</html>