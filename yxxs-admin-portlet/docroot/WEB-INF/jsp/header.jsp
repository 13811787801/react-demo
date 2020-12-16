<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<%
Long ts = com.yxxs.util.PropsConfig.STARTUP_TS;
String _mode = com.yxxs.util.PropsConfig.CONFIGMODE;
%>
    <title><%=".dev".equals(_mode)?"翻转教学":"优学向上" %>后台管理</title>

    <script type="text/javascript" src="/yxxs-static-portlet/js/jquery-2.1.4.min.js?t=<%=ts%>"></script>
 	
	 <link href="/yxxs-static-portlet/css/bootstrap.min.css?t=<%=ts%>" rel="stylesheet">
	 <link rel="stylesheet" href="/yxxs-static-portlet/css/bootstrap-treeview.min.css" />
	 <script src="/yxxs-static-portlet/js/bootstrap/bootstrap.min.js?t=<%=ts%>"></script>
     <script type="text/javascript" src="/yxxs-static-portlet/js/bootstrap/bootstrap-treeview.min.js" ></script>
    <script src="/yxxs-static-portlet/js/tmpl/ejs/ejs_production.js"></script>
    
    <script src="/yxxs-static-portlet/js/pager/pager.js"></script>
    <script src="/yxxs-static-portlet/js/bootstrap-paginator-1.0.2/bootstrap-paginator.min.js"></script>
    
    <script src="/yxxs-admin-portlet/js/tableEdit.js?t=<%=ts%>"></script>
     <script src="/yxxs-static-portlet/js/form/jquery_formValidatorRegex.js?t=<%=ts%>"></script>
     <script src="/yxxs-static-portlet/js/layer/layer.js"></script>
     <script src="/yxxs-static-portlet/js/layer/layer-v2.0/layer/layer.js"></script>
    <script src="/yxxs-static-portlet/js/mmsg/modal.js?t=<%=ts%>"></script>
    
    <script src="/yxxs-admin-portlet/js/common/copy.js?t=<%=ts%>"></script>
    <script src="/yxxs-admin-portlet/js/common/qrcode.js?t=<%=ts%>"></script>
    <script>
      var STARTUP_TS = '<%=ts%>';
      var servHost = '<%=com.yxxs.util.PropsConfig.SERVER_HOST %>';
    </script>
    <script type="text/javascript">
	 // 对Date的扩展，将 Date 转化为指定格式的String   
	 // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
	 // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
	 // 例子：   
	 // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
	 // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
	 Date.prototype.Format = function(fmt)   
	 { //author: meizz   
	   var o = {   
	     "M+" : this.getMonth()+1,                 //月份   
	     "d+" : this.getDate(),                    //日   
	     "h+" : this.getHours(),                   //小时   
	     "m+" : this.getMinutes(),                 //分   
	     "s+" : this.getSeconds(),                 //秒   
	     "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	     "S"  : this.getMilliseconds()             //毫秒   
	   };   
	   if(/(y+)/.test(fmt))   
	     fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	   for(var k in o)   
	     if(new RegExp("("+ k +")").test(fmt))   
	   fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	   return fmt;   
	 }  
	</script>
	
 	<script>
 	/*
 	conf = {
 		qrTitle:'xxxx二维码',
 		qrDesc:'微信扫一扫',
 		showQr:false
 	}
 	*/
 	function showLink(url, conf){
 		
 		var _conf = {
	 		qrTitle:null,
	 		qrDesc:null,
	 		showQr:true,
	 	};
 		if(conf){
 	 		for(var k in _conf){
 	 			if(typeof conf[k] != 'undefined'){
 	 				_conf[k] = conf[k];
 	 			}
 	 		}
 		}
 		var index = $('#qrCodeContainer').attr('index');
 		if(index){
 			layer.close(index);
 		}
 		
 		var content = '';
 		content += '<input class="form-control linkUrl" value="'+url+'"/>';
 		content += '<br/>';
 		if(_conf.showQr){
 	 		content += '<a class="btn btn-default showQrcode" href="javascript:;">查看二维码</a>';
 	 		content += '  ';
 		}
 		content += '<a class="btn btn-default copyLink" href="javascript:;">复制链接</a>';
 		
 	    var nIndex = layer.open({
 	        title:'查看链接',
 	        shadeClose: false,
 	        content: '<div id="qrCodeContainer" style="text-align:center;">'+content+'</div>',
 	        end:function(){
 	        	
 	        }
 	    });
 	   $('#qrCodeContainer').attr('index',nIndex);
 	    
 	   $('#qrCodeContainer').find('.linkUrl').val(url);
 	   $('#qrCodeContainer').find('.showQrcode').click(function(){
 		  openAdminQRCode(url,_conf.qrTitle, _conf.qrDesc);
 	   });
 	   $('#qrCodeContainer').find('.copyLink').click(function(){
 		  copyTextToClipboard(url);
 	   });
 	}
 	</script>
 	<script>
 	function makeParamUrl(param){

		var arr = [];
		for(var k in param){
			arr.push(k+'='+param[k]);
		}
		return arr.join('&');
 	}
 	</script>
 	<script>

    function loadjs(path, func) {

        var oHead = document.getElementsByTagName('HEAD').item(0);
        
        var oscript = document.createElement("script");
        oscript.type = "text/javascript";
        oscript.src = path;
        
        oscript.onload = function () {
            if (func) {
                func();
            }
        }
        
        oHead.appendChild(oscript);
    }
 	</script>
	<script>
	  function checkEmail(email){
	         var bool = false;
	         var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	         if(reg.test(email)){
	             bool = true;
	         }
	         return bool;
	  }
	</script>
 
    <style>
    .navbar-nav{margin:0px 0px!important;}
    .nav>li{float:left;}
    .container{margin-top:5px;margin-bottom:5px;}
    .container .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9{line-height:28px;}
    </style>
    <style>
     .margin15{
         margin-left:15px;
     }
     .margin80{
         margin-left:80px;
     }
     .marginb10{
        margin-bottom:10px;
     }
     .margintb15{
        margin:15px 0;
     }
     .scrollDiv{
        max-height:300px;
        overflow-y:scroll;
     }
     .width265{
        width:265px;
     }
    </style>