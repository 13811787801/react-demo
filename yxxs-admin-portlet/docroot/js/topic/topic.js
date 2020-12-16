function getTopicApprise(id, topicId){
	var hash = {};

    $.ajax({
        url:"/yxxs-package-serv-portlet/api/package",
        type: "POST",
        dataType: "json",
        data: { 
        	paramHashJsonStr:JSON.stringify({
        		dealFunc:"function(ob){return {appriseInfo:{apprise:{praiseId:"+id+"},topic:{topic:{topicDiscussionId:"+topicId+"}}}}}",
                props:[
                   {
                       url:"/yxxs-main-portlet/api/getTopicAppriseList_${$parent.appriseInfo.topic.topic.topicDiscussionId}",
                       condition:"(($parent.appriseInfo.apprise.praiseId == 0 && $parent.appriseInfo.topic.topic.topicDiscussionId != 0)?true:false)",
                       dealFunc:"function(ob){if(ob.length){return ob[0]} return {apprise:{praiseId:"+id+"},topic:{topic:{topicDiscussionId:"+topicId+"}}};}",
                       alias:"appriseInfo"
                   },
                   {
                       url:"/yxxs-main-portlet/api/getTopicApprise_${$parent.appriseInfo.apprise.praiseId}",
                       condition:"($parent.appriseInfo.apprise.praiseId != 0?true:false)",
                       alias:"appriseInfo"
                   },
                   {
                       url:"/yxxs-main-portlet/api/model/get_TopicDiscussionApprise_${$parent.appriseInfo.apprise.praiseId}",
                       alias:"appriseModel"
                   },
                   {
                        url:"/yxxs-main-portlet/api/getTopicDiscussionDto_${$parent.topic.topic.topicDiscussionId}",
                        alias:"topic",
                        parentExp:".appriseInfo",
                        props:[
                            {   
                                url:"/yxxs-main-portlet/api/userClass?userId=${$parent.user.userId}",
                                alias:"classInfo"
                            },
                            {   
                                url:"/yxxs-wx-serv-portlet/api/secure/jsonws/bindweixin/find-by-user-email?emailAddress=${$parent.user.emailAddress}",
                                alias:"bindWxInfo",
                                props:[
                                   {   
                                       url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?openid=${$parent.bindWeixin.openId}",
                                       condition:"($parent.bindWeixin.openId?true:false)",
                                       alias:"wxInfo"
                                   }
                               ]
                            }
                        ]
                   }
                ]
        	})
        },
        async:false,
        success: function (data) {
        	hash = data.appriseModel;
        	
        	hash.topic = data.appriseInfo.topic;

        	var tdata = data.appriseInfo.topic;
        	
            var cls = [];
            for(var i=0;tdata.classes && i<tdata.classes.length;i++){
                cls.push(tdata.classes[i].className);
            }
            hash.topicClasses = cls.join(',');
            
            var kws = [];
            if(tdata.topicKnowledge){
            	kws.push('章节名称:'+tdata.topicKnowledge);
            }
            if(tdata.topicTeachPurpos){
            	kws.push('任务类型:'+tdata.topicTeachPurpos);
            }
            hash.keywords = kws.join(',');

            var cdata = data.appriseInfo.topic.classInfo;
            var schoolName = '';
            if(cdata && cdata.length !=0){
                schoolName =cdata[0].name;
            }
            hash.schoolName = schoolName;

            var rdata = data.appriseInfo.topic.bindWxInfo.wxInfo
            var nickName = null;
            if(rdata && rdata.err == 0){
                if(rdata.entity.nickname && rdata.entity.nickname != ''){
                    nickName = rdata.entity.nickname;
                }
            }
            
            hash.nickName = nickName;
        }
    });
	
    return hash;
}



function initTopicKeywordInputByTags(inputId,type,conf,tags){

	var arr = [];
    for(var i=0;i<tags.length;i++){
    	if(tags[i].type == type){
    		arr.push(tags[i].key);
    	}
    }
    
    initTopicKeywordInput(inputId, type,conf, function(){
        $('#'+inputId).attr('multiple','multiple');
    	$('#'+inputId).val(arr);
    	initTagsInput(inputId);
    });
}



function saveTopicTagsData(topicId, tags, func){

    $.ajax({
        url: "/yxxs-main-serv-portlet/api/clearTargetObjectTag",
        type: "POST",
        dataType: "json",
        async:false,
        data: {
        	targetType:'TopicDiscussion',
        	targetId:topicId
        },
        success: function (data) {
            for(var i=0;i<tags.length;i++){
                $.ajax({
                    url: "/yxxs-main-serv-portlet/api/addTargetObjectTag",
                    type: "POST",
                    dataType: "json",
                    data: {
                        targetType:'TopicDiscussion',
                        targetId:topicId,
                        tagType:'TopicKeyWordTag',
                        tagId:tags[i]
                    },
                    async:false,
                    success: function (data) {
                    }
                });
            }
            
            if(func){
            	func();
            }
        }
    });
}