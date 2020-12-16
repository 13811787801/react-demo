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
              <li><a href="javascript:makeConfig();">生成nginx配置</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    <style>
    </style>
    
    <div id="wsDataRow" class="container">
    </div>
    <div id="dataRow" class="container">
    </div>
    
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
    <script type="text/javascript">
        function initList() {

            $.ajax({
                url: "/yxxs-soap-serv-portlet/api/wsServerInfo",
                type: "GET",
                dataType: "json",
                success: function (data) {
                	$('#wsDataRow').html('ws服务 '+data.address+":"+data.port);
                }
            });
            
            $.ajax({
                url: "/yxxs-soap-serv-portlet/api/serversInfo",
                type: "GET",
                dataType: "json",
                success: function (data) {
                	
                	var defaultAdd = '<span style="color:blue;">'+data.mainServ+'</span>';
                	
                	var list = [];
                	for(var i=0;i<data.servNameArr.length;i++){
                		var pname = data.servNameArr[i];
                		var info = {portletName:pname};
                		info.servList = [defaultAdd];
                		if(data.onlineServs[pname]){
                			info.servList = data.onlineServs[pname];
                		}
                		list.push(info);
                	}

                    var tejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/viewTable/tableView.ejs.txt' });

                    $('#dataRow').html(tejs.render({
                        list: list,
                        columns: [
                            { name: "portletName", desc: "服务名称" },
                            { name: "servList", desc: "在线服务器", format: function (v) {
                                return v.join('<br/>') ;
                            } }
                        ],
                        operations: []
                    }));
                }
            });
        }
        initList();
    </script>
</body>
<script>
function makeConfig(){
    $.ajax({
        url: "/yxxs-soap-serv-portlet/api/makeNginxConf",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	alert('生成成功');
        }
    });
}
</script>
</html>