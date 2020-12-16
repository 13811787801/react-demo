/**
 * 留言展示要求倒序输出，最新消息在最上方加载;
 * 数据库返回数据为倒叙数组;
 */
var msgList = [];//old
var _msgList = [];//current
var msgLength = 0;
var index = 0;//old index
var firstIdx = -1;
var _index = 0;//current index
var t = 1;//Interval
var maxlength = 5;
var maxRefDataLength = 10*maxlength;
var _interval = null;
var _intervalOfPage = null;
var allCnt = 0;
var refreshCnt = 0;
var refreshIdx = 0;
var refreshData = [];
var pageCnt = 0;
var ejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/wechatwall/messagePanel.ejs.txt?t='+ STARTUP_TS });
//加载用户发言信息

function updateAllCnt(){
	$('#allCnt').html('<img src="../../images/wechatwall/ti_06.png"/>'+allCnt+'条信息');
}
function update(){
	pageCnt++;
	if(pageCnt==10){
		refresh();
	}
	getWallData(null);
	
	if(index != _index){
		//增加页面数据清理逻辑，设置页面展示数据最大记录数；
		updatePanel(_msgList);
		updateRefData(_msgList[0]);
		//setPageBeats();
	}
	
}
function setBeats(){
	_interval = setInterval(function(){
		update();
	}, 1*t*1000);
	//setPageBeats();
};
function stopBeats(){;
	clearInterval(_interval);
};

function setPageBeats(){
	_intervalOfPage = setInterval(function(){
		refresh();
	}, 10*t*1000);
};
function stopPageBeats(){
	clearInterval(_intervalOfPage);
};
function initData(){
	index = 0;
	_index = 0;
	var td = getWallData(function(){
		$('#cDate').html((new Date()).Format("yyyy-MM-dd"));
		$('#wallTitle').html(wallTitle);
	});
	if(td){
		if(td.length >= maxlength){
			msgList = td.slice(0, maxlength);
		}else{
			msgList = td;
		}
	}
}

function imgLoad(img, callback) {
	var timer = setInterval(function() {
	if (img.complete) {
		callback(img);
		clearInterval(timer);
	}
	}, 50);
}
function initPanel(d){
	updateAllCnt();
	if(d && d.length > 0){
		index = _index;
		_index = d[0].id;
	    $('.chat_list').html(ejs.render({dataList:d,allCnt:allCnt}));
    	/*
    	for(var i = 0; i < $('img').length ;i++){
    		imgLoad($('img')[i], function() {
    			scrollToEnd();
    			});
    	};*/
	    
	}
	setBeats();
}
function refreshPanel(d){
	if(d && d.length > 0){
	    $('.chat_list').html(ejs.render({dataList:d,allCnt:allCnt}));
	}
}
function scrollToEnd(){//滚动到底部
    var h = $(document).height()-$(window).height();
    $(document).scrollTop(h); 
}
function scrollToTop(){//滚动到顶部
    $(document).scrollTop(0); 
}
/*data 为倒序数组；
 * */
function updatePanel(data){
	var d = data.slice(0,data.length);//不改变data
	updateAllCnt();
	if(d && d.length > 0){
		index = _index;
		_index = d[0].id;
		//更新页面数据缓存
		if(msgList.length+msgLength > maxlength){
			var ta = msgList.slice(0, maxlength-msgLength);
			msgList = ta.reverse().concat(d.reverse()).reverse();
			delLi(msgLength);
			$('.chat_list').prepend(ejs.render({dataList:data,allCnt:allCnt}));
			/*var ta = msgList.slice(msgLength, maxlength);
			msgList = ta.concat(d);
			*/
		}else{
			var ta = msgList.slice(0,msgList.length);
			msgList = ta.reverse().concat(d.reverse()).reverse();
			$('.chat_list').prepend(ejs.render({dataList:data,allCnt:allCnt}));
			//msgList = msgList.concat(d);
		}
		
	}
	scrollToTop();
}
function delLi(n){
	
	for(var i = 0;i < n;i++){
		var len = $(".chat_list").children().length-1;
		if(len >=0){
		 $(".chat_list li:eq("+len+")").remove();  //表示删除最后一个元素
		}
	}
	

}
function initRefData(){
	var date = new Date();
	var eDate = new Date(new Date().setDate(date.getDate() + 1));
    //actionId="+actionId+"&
	var url = "/yxxs-action-serv-portlet/wechatwall/api/search?appKey="+appKey+"&page=0&pageSize="+maxRefDataLength+"&showFlag=1" +
			"&startDate="+date.Format("yyyy-MM-dd")+"&endDate="+eDate.Format("yyyy-MM-dd");
	$.ajax({
		type:'GET',
        url:"/yxxs-package-serv-portlet/api/package",
        data:{
            paramHashJsonStr:JSON.stringify({
                url:url,
                props:[
                    {   
                        url:"/yxxs-main-portlet/api/user_${$parent.userId}",
                        alias:"userInfo",
                        parentExp:".obj",
                        parentType:"list",
                        props:[
                        ]
                    },
                    {   
                        url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?openid=${$parent.openId}&appKey="+appKey,
                        alias:"wechatInfo",
                        parentExp:".obj",
                        parentType:"list",
                        props:[
                        ]
                    }
                ]
            })
        },
        async:false,
        dataType:'json',
        success:function(data){
        	//当前data.obj为倒叙数组；
        	refreshData = data.obj;
        }
	});
}
function getWallData(func){
	var date = new Date();
	var eDate = new Date(new Date().setDate(date.getDate() + 1));
    //actionId="+actionId+"&
	var url = "/yxxs-action-serv-portlet/wechatwall/api/search?appKey="+appKey+"&page=0&pageSize="+maxlength+"&showFlag=1" +
			"&startDate="+date.Format("yyyy-MM-dd")+"&endDate="+eDate.Format("yyyy-MM-dd");
	if(_index > 0){
		url += '&startId='+_index;
	}
	$.ajax({
		type:'GET',
        url:"/yxxs-package-serv-portlet/api/package",
        data:{
            paramHashJsonStr:JSON.stringify({
                url:url,
                props:[
                    {   
                        url:"/yxxs-main-portlet/api/user_${$parent.userId}",
                        alias:"userInfo",
                        parentExp:".obj",
                        parentType:"list",
                        props:[
                        ]
                    },
                    {   
                        url:"/yxxs-wechat-serv-portlet/wechat/user/detail/get?openid=${$parent.openId}&appKey="+appKey,
                        alias:"wechatInfo",
                        parentExp:".obj",
                        parentType:"list",
                        props:[
                        ]
                    }
                ]
            })
        },
        async:false,
        dataType:'json',
        success:function(data){
        	//当前data.obj为倒叙数组；
        	if(data && data.obj.length > 0 && _index != data.obj[0].id){
        		
        		index = _index;
        		_index = data.obj[0].id;//最后留言id
        		if(firstIdx < 0){
        			firstIdx = _index;
        		}
        		_msgList = data.obj;
        		allCnt = data.totalCount;
            	msgLength = _msgList.length;
        	}
        	if(func){
        		func();
        	}
        }
	});
	return _msgList.slice(0,_msgList.length);
}
var actionInfo = {};
function getActiponInfo(){
	var params = {page:0,pageSize:1};
	params.startDate = new Date().Format("yyyy-MM-dd");
	params.appKey = appKey;
	params.flag = "1";
	var paramList = [];
    for(var k in params){
        paramList.push(k+"="+params[k]);
    }
	$.ajax({
        url:"/yxxs-package-serv-portlet/api/package",
        data:{
            paramHashJsonStr:JSON.stringify({
                url:"/yxxs-action-serv-portlet/action/api/search?"+paramList.join('&'),
                props:[
                    {
                        url:"/yxxs-main-portlet/api/user_${$parent.createId}",
                        alias:"userInfo",
                        condition:"($parent.createId>0?true:false)",
                        parentType:"list",
                        parentExp:".obj",
                    }
                ]
            })
        },
        type: "POST",
        dataType: "json",
        async:false,
        success: function (data) {
        	actionInfo = data;
        }
	});
}

function initPage(){
	initData();
	initPanel(msgList);
	//refreshData = msgList.slice(0,msgList.length);
	initRefData();
}
/*更新页面缓存数据*/
function updateRefData(d){
	if(refreshData.length < maxRefDataLength){
		refreshData.push(d);
	}else{
		if(index != _index){
			refreshData = refreshData.slice(1);
			refreshData[maxRefDataLength-1] = d;
		}else{
			refreshData = refreshData.slice(1,refreshData.length).concat(refreshData.slice(0,1));
			//var t = refreshData[0];
			//refreshData = refreshData.slice(1);
			//refreshData[maxRefDataLength-1] = t;
		}
	}
}
var _refreshData = [];
function refresh(){
	getWallData(null);
	if(firstIdx == _index){
		stopBeats();
		stopPageBeats();
		if(refreshData && refreshData.length > 0){
			if(refreshData.length < maxRefDataLength){
				refreshPanel(getRefData(refreshData,refreshCnt%refreshData.length));
			}else{
				refreshPanel(getRefData(refreshData,refreshCnt%maxRefDataLength));
			}
			
			//refreshPanel(refreshData.slice(1,5).concat(refreshData.slice(0,1)));
			//refreshPanel(refreshData.sort(randomsort));
		}
		refreshCnt++;
    	setBeats();
	}
	firstIdx = _index;
	pageCnt = 0;
}
function getRefData(arr,index){
	var t = arr.slice(0-index).concat(arr.slice(0,arr.length-index));//(index,arr.length).concat(arr.slice(0,index));
	if(index == 0){
		t = arr;
	}
	
	return t.slice(0,5);
}
function randomsort(a, b) {
   return Math.random()>.5 ? -1 : 1;
}
