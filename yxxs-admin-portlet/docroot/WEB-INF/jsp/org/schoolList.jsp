<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	
    <script src="/yxxs-static-portlet/js/place/place.js"></script>
    
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
    
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
              <li><a href="/yxxs-admin-portlet/admin/org/schoolForm">添加学校</a></li>
              <!-- 
              <li class="divider"></li>
              <li><a href="javascript:;"></a></li>
               -->
           </ul>
       </div>
    </div>
    
    
    <%
    String sName = "";
    if(null != request.getParameter("sName")){
    	sName = request.getParameter("sName");
    }
    Integer sProvince = null;
    if(null != request.getParameter("sProvince")){
    	try{
            sProvince = Integer.parseInt(request.getParameter("sProvince"));
    	}catch(Exception ex){}
    }
    Integer sCity = null;
    if(null != request.getParameter("sCity")){
        try{
            sCity = Integer.parseInt(request.getParameter("sCity"));
        }catch(Exception ex){}
    }
    Integer sDistrict = null;
    if(null != request.getParameter("sDistrict")){
        try{
            sDistrict = Integer.parseInt(request.getParameter("sDistrict"));
        }catch(Exception ex){}
    }
    Integer sStageLevel = null;
    if(null != request.getParameter("sStageLevel")){
        try{
            sStageLevel = Integer.parseInt(request.getParameter("sStageLevel"));
        }catch(Exception ex){}
    }
    String sMemberSchool = null;
    if(null != request.getParameter("sMemberSchool")){
        try{
        	sMemberSchool = request.getParameter("sMemberSchool");
        }catch(Exception ex){}
    }
    %>
    
    <div class="container">
        <div class="row">
        	<div class="col-xs-3" style="
			    width: 4%;
			    padding: 0;
			">学校：</div>
            <div class="col-xs-3">
               <div class="form-group">
                  <input type="text" class="form-control" id="sName" name="sName"  value="<%=sName %>"
                     placeholder="请输入学校名">
               </div>
            </div>
            <div class="col-xs-3" style="
			    width: 4%;
			    padding: 0;
			">学段：</div>
            <div class="col-xs-3" style="width: 10%;">
               <div class="form-group">
                  <select class="form-control" id="sStageLevel" name="sStageLevel">
                    <option value="">全部</option>
                    <option value="1007">小学</option>
                    <option value="1008">初中</option>
                    <option value="1009">高中</option>
                  </select>
               </div>
            </div>
            <div class="col-xs-3" style="
			    width: 6%;
			    padding: 0;
			">会员学校：</div>
            <div class="col-xs-3" style="width: 10%;">
               <div class="form-group">
                  <select class="form-control" id="sMemberSchool" name="sMemberSchool">
                    <option value="">全部</option>
                    <option value="1">会员</option>
                    <option value="0">非会员</option>
                  </select>
               </div>
            </div>
            
            <div class="col-xs-6" style="width: 40%;">
               <div class="form-inline">
                    省：<select id="province" class="form-control"></select>
                    市：<select id="city" class="form-control"></select>
                    区：<select id="district" class="form-control"></select>
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
        function initSearchPlace(){
            initPlace('province','city','district', {
            	   enableNull:true,
            	   province:'<%=null ==sProvince?"":sProvince%>',
            	   city:'<%=null ==sCity?"":sCity%>',
            	   district:'<%=null ==sDistrict?"":sDistrict%>',
            	});
        }
        initSearchPlace();

        function initStageLevel(){
        	$('#sStageLevel').val('<%=null ==sStageLevel?"":sStageLevel%>');
        }
        initStageLevel();
    
        function initMemberSchool(){
        	$('#sMemberSchool').val('<%=null ==sMemberSchool?"":sMemberSchool%>');
        	//$("input:radio[value='<%=null ==sMemberSchool?"0":sMemberSchool%>']").attr('checked','true');
        }
        initMemberSchool();
        
        function getRadio(){
        	/*
        	var item = null;
        	$("input[type=radio]:checked").each(function() {
                item = $(this).val();
                //alert(item);
            }) ;
        	return item;*/
        	return $("input[type='radio']:checked").val();
        }
	    function search(){
	        var page = <%=pageNum%>;
	        if('<%=sName%>'!= $('#sName').val()
        		|| '<%=null ==sProvince?"":sProvince%>'!= $('#province').val()
        		|| '<%=null ==sCity?"":sCity%>'!= $('#city').val()
        		|| '<%=null ==sDistrict?"":sDistrict%>'!= $('#district').val()
                || '<%=null ==sStageLevel?"":sStageLevel%>'!= $('#sStageLevel').val()
                || '<%=null ==sMemberSchool?"":sMemberSchool%>'!= $('#sMemberSchool').val()
                		//||'<%=null ==sMemberSchool?"0":sMemberSchool%>'!= getRadio()
        		){
	            page = 1;
	        }
	        window.location.href = '?sName='+$('#sName').val()+
	        		'&sProvince='+$('#province').val()+'&sCity='+$('#city').val()+'&sDistrict='+$('#district').val()+
	        		'&sStageLevel='+$('#sStageLevel').val()+'&sMemberSchool='+$('#sMemberSchool').val()+
	        		'&page=' + page;
	    }
    
	    /**
	    *比较两个数组对象的内容
	    *array1数组1，key1数组1对比项,array2数组2,key2数组2对比项,isExist true表示返回array2中相同元素，false相反
	    */
	    function compareArray(array1,key1,array2,key2,isExist){
	    	var result = [];
            for(var i = 0; i < array2.length; i++){
                var obj = array2[i];
                var num = obj[key2];
                var _isExist = false;
                for(var j = 0; j < array1.length; j++){
                    var aj = array1[j];
                    var n = aj[key1];
                    if(n == num){
                        _isExist = true;
                        break;
                    }
                }
                if(isExist == _isExist){
                    result.push(obj);
                }
            }
            return result;
	    }
        function initList() {
            
            var param = {page : <%=pageNum - 1 %>, pageSize: 10};
            if($('#sName').val()){
                param.schoolName = $('#sName').val();
            }
            if($('#province').val()){
                param.province = $('#province').val();
                if($('#city').val()){
                    param.city = $('#city').val();
                    if($('#district').val()){
                        param.district = $('#district').val();
                    }
                }
            }
            if($('#sStageLevel').val()){
                param.stageLevel = $('#sStageLevel').val();
            }
            if($('#sMemberSchool').val()){
                param.member = $('#sMemberSchool').val();
            }
            
            $.ajax({
                url: "/yxxs-main-portlet/api/listSchool",
                type: "POST",
                dataType: "json",
                data: param,
                success: function (data) {
                	/*
                    if($('#sMemberSchool').val() != ''){
                    	var members = null;
                        var group = null;
                    	$.ajax({
                        	url: "/yxxs-security-serv-portlet/sec/group/api/search?code=member-school&page=0&pageSize=1",
                            type: "POST",
                            dataType: "json",
                            data: {},
                            async:false,
                            success: function (data) {
                            	group = data;
                            }
                        });
                        $.ajax({
                        	url: "/yxxs-security-serv-portlet/sec/group/user/api/search?group_id="+group.obj[0].id+"&user_type=1&page=0&pageSize=1",
                            type: "POST",
                            dataType: "json",
                            data: {},
                            async:false,
                            success: function (data) {
                            	members = data;
                            }
                        });
                        //var _obj = [];
                        
                    	data.obj = compareArray(members.obj,'user_id',data.obj,'organizationId',$('#sMemberSchool').val() == 1?true:false);
                    }*/
                	
                    initTableList('dataRow', data, {
                        columns: [
                            { name: "organizationId", desc: "organizationId" },
                            { name: "name", desc: "学校名称", format:function(v, obj){
                                var s = v;
                                if(obj.statusId == -1){
                                    s += "<span style='color:red;'>[删除中]</span>";
                                }
                                return s;
                            } },
                            { name: "classCnt", desc: "班级数量" },
                        ],
                        operations: [{
                            name: "查看班级",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/classList?schoolId=" + obj.organizationId; }
                        },{
                            name: "查看教师",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/schoolTeachersList?schoolId=" + obj.organizationId; }
                        },{
                            name: "查看教学负责人",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/schoolTeachingLeaderList?schoolId=" + obj.organizationId; }
                        },
                        {
                            name: "查看优秀引导教师",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "javascript:showTopGuideTeachers(" + obj.organizationId+");"; }
                        },{
                            name: "编辑学校",
                            visable: function (obj) { return true },
                            getUrl: function (obj) { return "/yxxs-admin-portlet/admin/org/schoolForm?schoolId=" + obj.organizationId; }
                        },{
                            name: "删除学校",
                            visable: function (obj) { return (obj.classCnt==0)?true:false; },
                            getUrl: function (obj) { return "javascript:delSchool(" + obj.organizationId+");"; }
                        },
                        {
                            name: "生成加入学校链接",
                            visable: function (obj){ return true; },
                            getUrl: function (obj) {
                            	return "javascript:copyTextToClipboard('http://"+servHost+"/yxxs-wx-portlet/share/inviteJoinSchool?schoolId=" + obj.organizationId+"');";
                            }
                        },
                        {
                            name: "微信分享",
                            visable: function (obj){ return true; },
                            getUrl: function (obj) {
                                return "javascript:openAdminQRCode('http://"+servHost+"/yxxs-wx-portlet/share/inviteJoinSchool?startUser=true&schoolId=" + obj.organizationId+"','"+obj.name+"二维码','微信扫一扫，分享链接邀请加入学校');";
                            }
                        },
                        {
                            name: "开通会员学校",
                            visable: function (obj){ return true; },
                            getUrl: function (obj) {
                                return "/yxxs-admin-portlet/admin/org/memberSchoolForm?schoolId=" + obj.organizationId+'&sName='+$('#sName').val()+
            	        		'&sProvince='+$('#province').val()+'&sCity='+$('#city').val()+'&sDistrict='+$('#district').val()+
            	        		'&sStageLevel='+$('#sStageLevel').val()+
            	        		'&page=<%=pageNum%>';
                            }
                        }],
                        pageUrlFunc: function(page){
                            return '?sName='+$('#sName').val()+
                            '&sProvince='+$('#province').val()+'&sCity='+$('#city').val()+'&sDistrict='+$('#district').val()+
                            '&sStageLevel='+$('#sStageLevel').val()+'&sMemberSchool='+$('#sMemberSchool').val()+
                            '&page=' + page;
                        }
                    });
                }
            });
        }
        initList();
    </script>
    
    <script>
    function showTopGuideTeachers(schoolId){

        $.ajax({
            url: "/yxxs-package-serv-portlet/api/package",
            type: "POST",
            dataType: "json",
            data: {
            	paramHashJsonStr:JSON.stringify({
                    url: "/yxxs-main-serv-portlet/api/listToken?page=0&pageSize=10&objectName=schoolTopSampleTeacher&objectId="+schoolId+"&expired=false",
                    props:[
                       {
                           url:"/yxxs-main-portlet/api/user_<%="${$parent.accessId}"%>",
                           alias:"teacherInfo",
                           parentType:"list",
                           parentExp:".obj"
                       }
                   ]
               })
            },
            async:false,
            success: function (data) {

                var titleDiv = '<div style="text-align:center;">校内引导当前优秀教师列表</div>';

                var index = layer.open({
                      type: 1,
                      title: false,
                      area: ['450px', '400px'],
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
                    list: data.obj,
                    columns: [
                        { name: 'teacher', desc: '教师', format:function(v, ob){
                            var a = ob.teacherInfo.screenName + "<br/>" + ob.teacherInfo.emailAddress;
                            return a;
                        } },
                        { name: 'createDate', desc: '创建时间', format:function(v, ob){
                            return new Date(v).Format("yyyy-MM-dd hh:mm:ss") ;
                        } },
                        { name: 'order', desc: '排名', format:function(v, ob){
                        	if(ob.cacheData){
                        		return ob.cacheData;
                        	}
                            return '-';
                        }  },
                    ],
                    operations: []
                }));
            }
        });
    }
    </script>
    
    <script>
    function delSchool(schoolId){
        if(confirm('是否删除?')){
            $.ajax({
                url: "/yxxs-main-portlet/api/delSchool",
                type: "POST",
                dataType: "json",
                data: {schoolId: schoolId },
                async:false,
                success: function (data) {
                	if(data.error){
                        alert('删除失败:'+data.error+'!');
                        return;
                	}
                    alert('删除成功!');
                    initList();
                }
            });
        }
    }
    </script>
</body>
</html>