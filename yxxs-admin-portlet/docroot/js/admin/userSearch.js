
function searchUserByEmail(emailAddress, func){
	searchUserBy_Id_Email(null, emailAddress, func);	
}
function searchUserByEmailAppKey(emailAddress,appKey, func){
	searchUserBy_Id_Email_AppKey(null,appKey, emailAddress, func);	
}
function searchUserById(userId, func){
	searchUserBy_Id_Email(userId, null, func);	
}
function searchUserByIdAppKey(userId,appKey, func){
	searchUserBy_Id_Email_AppKey(userId,appKey, null, func);	
}

/**
 * @param emailAddress
 */
function searchUserBy_Id_Email(userId, emailAddress, func){
	
	var url = "/yxxs-main-portlet/api/getUser?";
	if(userId){
		url+="&userId="+userId;
	}
	if(emailAddress){
		url+="&emailAddress="+emailAddress;
	}
	var uInfo = {};
	$.ajax({
        url:"/yxxs-package-serv-portlet/api/package",
        data:{
            paramHashJsonStr:JSON.stringify({
                url:url,
                props:[
                    {
                        url:"/yxxs-wx-serv-portlet/api/secure/jsonws/bindweixin/find-by-user-email?emailAddress=${$parent.emailAddress}",
                        alias:"bindInfo",
                        condition:"($parent && typeof $parent.userId != 'undefined' && $parent.userId != 0)",
                        props:[
                               {
                            	   url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?openid=${$parent.bindWeixin.openId}",
                            	   condition:"($parent.bindWeixin.openId?true:false)",
                            	   alias:"weixinInfo",
                               }
                        ]
                    },
                    {
                        url:"/yxxs-main-portlet/api/userClass?userId=${$parent.userId}",
                        alias:"classInfo",
                        condition:"($parent && typeof $parent.userId != 'undefined' && $parent.userId != 0)",
                    },
                    {
                        url:"/yxxs-main-portlet/api/getUser?userId=${$parent.userId}",
                        alias:"userInfo",
                        condition:"($parent && typeof $parent.userId != 'undefined' && $parent.userId != 0)",
                    }
                ]
            })
        },
        type: "POST",
        dataType: "json",
        success: function (data) {
        	
            if(!data || !data.userId){
            	if(func){
            		func({userId:null,emailAddress:null});
            	}
                return;
            }

            uInfo.userId = data.userId;
            uInfo.rName = data.lastName+data.firstName;
            
            if(data.userInfo && data.userInfo.mobilePhone){
            	uInfo.rMobile = data.userInfo.mobilePhone;
            }else{
            	uInfo.rMobile = '-';
            }
            
            var nickName = null;
            if(data.bindInfo && data.bindInfo.weixinInfo){
            	var rdata = data.bindInfo.weixinInfo;
                if(rdata.entity && rdata.entity.nickname && rdata.entity.nickname != ''){
                    nickName = rdata.entity.nickname;
                }
            }
            
            var info = data.lastName+data.firstName+','+data.emailAddress;
            if(nickName){
                info +=','+nickName;
            }
            if(data.classInfo.length !=0){
            	uInfo.rSchool = data.classInfo[0].name;
                info +=','+data.classInfo[0].name;
                for(var i in data.classInfo[0].classes){
                    info += ','+data.classInfo[0].classes[i];
                    break;
                }
                if(data.classInfo[0].classCnt >1){
                    info +='等'+data.classInfo[0].classCnt+'个班级';
                }
            }else{
            	uInfo.rSchool = '-';
            }
                
            var types = [{k: 1, v: "学生"},{k: 2, v: "家长"},{k: 3, v: "老师"} ];
            for(var i=0;i<types.length;i++){
                if(types[i].k == data.type){
                    info += ','+types[i].v;
                    break;
                }
            }
            
            if(func){
            	func({uInfo:uInfo,userId:data.userId,emailAddress:data.emailAddress, isBind:(null==nickName?false:true), desc:info});
            }
        }
	});
}
/**
 * @param emailAddress
 */
function searchUserBy_Id_Email_AppKey(userId,appKey, emailAddress, func){
	
	var url = "/yxxs-main-portlet/api/getUser?";
	if(userId){
		url+="&userId="+userId;
	}
	if(emailAddress){
		url+="&emailAddress="+emailAddress;
	}
	var uInfo = {};
	$.ajax({
        url:"/yxxs-package-serv-portlet/api/package",
        data:{
            paramHashJsonStr:JSON.stringify({
                url:url,
                props:[
                    {
                        url:"/yxxs-wx-serv-"+appKey+"-portlet/api/secure/jsonws/bindweixin"+appKey+"/find-by-user-email?appKey="+appKey+"&emailAddress=${$parent.emailAddress}",
                        alias:"bindInfo",
                        condition:"($parent && typeof $parent.userId != 'undefined' && $parent.userId != 0)",
                        props:[
                               {
                            	   url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?appKey="+appKey+"&openid=${$parent.bindWeixin.openId}",
                            	   condition:"($parent.bindWeixin.openId?true:false)",
                            	   alias:"weixinInfo",
                               }
                        ]
                    },
                    {
                        url:"/yxxs-main-portlet/api/userClass?userId=${$parent.userId}",
                        alias:"classInfo",
                        condition:"($parent && typeof $parent.userId != 'undefined' && $parent.userId != 0)",
                    },
                    {
                        url:"/yxxs-main-portlet/api/getUser?userId=${$parent.userId}",
                        alias:"userInfo",
                        condition:"($parent && typeof $parent.userId != 'undefined' && $parent.userId != 0)",
                    }
                ]
            })
        },
        type: "POST",
        dataType: "json",
        success: function (data) {
        	
            if(!data || !data.userId){
            	if(func){
            		func({userId:null,emailAddress:null});
            	}
                return;
            }

            uInfo.userId = data.userId;
            uInfo.rName = data.lastName+data.firstName;
            uInfo.openId = '';
            if(data.userInfo && data.userInfo.mobilePhone){
            	uInfo.rMobile = data.userInfo.mobilePhone;
            }else{
            	uInfo.rMobile = '-';
            }
            var nickName = null;
            if(data.bindInfo && data.bindInfo.weixinInfo){
            	var rdata = data.bindInfo.weixinInfo;
                if(rdata.entity && rdata.entity.nickname && rdata.entity.nickname != ''){
                    nickName = rdata.entity.nickname;
                    uInfo.openId = rdata.entity.openId;
                }
            }
            
            var info = data.lastName+data.firstName+','+data.emailAddress;
            if(nickName){
                info +=','+nickName;
            }
            if(data.classInfo.length !=0){
            	uInfo.rSchool = data.classInfo[0].name;
                info +=','+data.classInfo[0].name;
                for(var i in data.classInfo[0].classes){
                    info += ','+data.classInfo[0].classes[i];
                    break;
                }
                if(data.classInfo[0].classCnt >1){
                    info +='等'+data.classInfo[0].classCnt+'个班级';
                }
            }else{
            	uInfo.rSchool = '-';
            }
                
            var types = [{k: 1, v: "学生"},{k: 2, v: "家长"},{k: 3, v: "老师"} ];
            for(var i=0;i<types.length;i++){
                if(types[i].k == data.type){
                    info += ','+types[i].v;
                    break;
                }
            }
            
            if(func){
            	func({uInfo:uInfo,userId:data.userId,emailAddress:data.emailAddress, isBind:(null==nickName?false:true), desc:info});
            }
        }
	});
}