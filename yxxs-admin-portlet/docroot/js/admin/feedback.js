/**
 * 
 * @param feedbackId
 * @param approvalFlag
 * @param userId userInfo.userId或者${admin['userId']}
 * @param conf:{func:function(){}}
 * 
 * agree130(10000,true,{func:function(infoHash, okFunc){
		
        var fhash = {};
        fhash.feedbackId = infoHash.feedbackId;
        fhash.userId = infoHash.userId;
        fhash.content = infoHash.logContent;
        fhash.status = infoHash.status;

        $.ajax({
            url: "/yxxs-main-portlet/api/feedback/addFeedBackDealLog",
            type: "POST",
            dataType: "json",
            data: fhash,
            success: function (fdata) {
            	
            	alert("审核通过");
                initList();
            }
        });
	};)
 */
function agree130(feedbackId,approvalFlag,userId,conf){

        $.ajax({
            url: "/yxxs-main-portlet/api/model/get_CommonFeedBack_"+feedbackId,
            type: "POST",
            dataType: "json",
            data: { },
            success: function (data) {

                var cdata = JSON.parse(data.content);
                var appKey = '';//2019-02-10 用户反馈增加公众号标识，根据公众号处理用户反馈
                if(data.appKey){
                	appKey = data.appKey;
                }
                if(data.type == 130){
                	
                	var hash = {};
                	hash.name = cdata.className;
                	hash.schoolYear =  parseInt('<%=year%>') + 1001 - cdata.grade;
                    hash.schoolId = cdata.schoolId;
                    hash.userId = userId;
                    hash.appKey = appKey;

                    hash.termStartDate = '<%=DateUtil.getFormatDate(DateTermUtil.getCurrentTermStartDate(),"yyyy-MM-dd")%>';
                    hash.termEndDate = '<%=DateUtil.getFormatDate(DateTermUtil.getCurrentTermEndDate(),"yyyy-MM-dd")%>';

                    saveClass(hash, function(h,already){
                    	hash = h;
                    	if(already){
                    	}
                       	//加入班级
                        jQuery.ajax(
                            {
                                type: "get",
                                url: "/yxxs-wx-serv-portlet/api/secure/jsonws/bindweixin/find-by-weixin-open-id",
                                data:{
                                    t:'<%=ts%>',
                                    openId:data.openId,
                                    appKey:appKey
                                },
                                dataType:'json',
                                async:false,
                                success: function(wdata){

                                	var ahash = {};
                                	ahash.classId = hash.classId;
                                	ahash.userId = wdata.user.userId;
                                	ahash.roleId = 14216;
                                    
                                    $.ajax({
                                        url: "/yxxs-main-portlet/api/directAddUserToClass",
                                        type: "POST",
                                        dataType: "json",
                                        data: ahash,
                                        async:false,
                                        success: function (data) {
                                        }
                                    });
                                }
                            });
                        if(conf && conf.func){
                        	conf.func();
                        }
                    });
                    return;
                }

                alert("审核通过");
                initList();
            }
        });
    }
    
    function refuse130(feedbackId,approvalFlag,userId){

    	var hash = {};
        hash.feedbackId = feedbackId;
        hash.userId = userId;/*From head.jsp*/
        hash.content = '';
        hash.status = approvalFlag;

        $.ajax({
            url: "/yxxs-main-portlet/api/feedback/addFeedBackDealLog",
            type: "POST",
            dataType: "json",
            data: hash,
            success: function (data) {
                alert("审核拒绝");
                initList();
            }
        });
    }