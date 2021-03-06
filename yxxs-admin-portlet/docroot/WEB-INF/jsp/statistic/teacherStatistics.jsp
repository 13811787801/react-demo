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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="/yxxs-static-portlet/js/place/place.js"></script>
    <script src="/yxxs-static-portlet/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
	<style>
	   .margintb15{
	       margin:15px 0;
	   }
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	
    <script>
    function pageUrl(page){
        return '?&page=' + page + pagerUrlFunc();
    }
    var columnWidth = {'学校':180,'所在班级':100,'最后发任务时间':90,'最近登陆':90,'任务总数(16-01-01)':82,'活跃任务数(16-01-01)':85,'地区':140};
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
                    <input type="button" id="search" onclick="search();" value="搜索" class="btn btn-primary margin80" />
                    <input type="button" id="search" onclick="setNoCondition();" value="条件置空" class="btn btn-danger margin80" />
                    <input type="button" id="search" onclick="excel();" value="导出excel" class="btn btn-default margin80" />
                </div>
            </div>
        </div>
        <div style="margin-bottom:10px;">默认起始时间是2017年1月1日，选定的起始时间为当天的0点 ,筛选时间对应带有"阶段"的数据 </div>
        <div style="margin-bottom:10px;">注意:最终统计数据更新时间每日凌晨4点</div>
        <div style="margin-bottom:10px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:red">点击蓝色字体表头，按相应列重新排序，默认为“最后发布任务时间”</font></div>
    </div>
    <%@ include file="/WEB-INF/jsp/common/controls/statisticTableSelectView.jsp" %>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
	 <script type="text/javascript">
	 
	    function search(){
            var url = '?&page=1' + pagerUrlFunc();
	        
	        window.location = url;
	    }
	    /*
	    obj:
	    index:columnIndex column_number;
	    */
	    function sort(obj,index){
			var url = '?order='+index+'&page=1'+ pagerUrlFunc();
	        
	        window.location = url;
	    }
	    function regSortFunc(){
	    	$.each($('.table tr:nth-child(1) td'),function(index,obj){
	    		if( $(obj).attr('cname') == 'column_19' || $(obj).attr('cname') == 'column_15' || $(obj).attr('cname') == 'column_5'){
	    			$(obj).attr("onclick","sort(this,'"+$(obj).attr('cname')+"')");
	    			$(obj).attr("style","color:#2196f3;font-weight:bold");
	    			//td = $(obj).attr('cname');
	    		}
	    	});
	    }
	    function setNoCondition(){
	    	$('#province').val('');
	    	$('#city').val('');
	    	$('#district').val('');
	    	$('#startTime').val('2017-01-01');
	    	$('#name').val('');
	    	$('#school').val('');
	    }
	    
	    function excel(){
            var url = '?'+pagerUrlFunc()+"&exportExcel=true";
            
            window.location = url;
	    }
        
        function pagerUrlFunc(){
            return '' +
            '&province='+($('#province').val()?$('#province').val():'')+'&city='+($('#city').val()?$('#city').val():'')+'&district='+($('#district').val()?$('#district').val():'')+
            '&school='+$('#school').val()+
            '&name='+$('#name').val()+'&startTime='+$('#startTime').val();
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
            
            initPager("pager", pi, function (page) {
                return pageUrl(page)+ '&pageSize=<%=pageSize%>';
            });

            $('#pager ul').append('<li><a>-第'+(pi.pageNumber+1)+'页-共'+pi.pageCount+'页-共'+pi.totalCount+'条-</a></li>');
            regSortFunc();
        });
        
        function initPagerFunc(){
        	//不自动加载, 保证变量初始化
        }
    </script>
    
</body>
</html>