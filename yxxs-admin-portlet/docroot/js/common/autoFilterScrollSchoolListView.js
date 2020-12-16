function filterSchoolName(originalSchoolData,schoolNameContent){
    
    var finalShoolListData = {obj:[],totalCount:0};
    if(typeof schoolNameContent == 'undefinded'){
    	schoolNameContent = '';
    }
    
    var arr = schoolNameContent.split('');
    var schoolNameArray = [];
    for(var i=0;i<arr.length;i++){
        if(schoolNameArray.indexOf(arr[i])==-1){
            schoolNameArray.push(arr[i]);
        }
    }
    
    var bl = true;
    for(var i=0;i<originalSchoolData.obj.length;i++){
        bl = true;
        for(var j=0;j<schoolNameArray.length;j++){
            var index = originalSchoolData.obj[i].name.indexOf(schoolNameArray[j]);
            if(index<0){
                bl = false;
                break;
            }
        }
        if(bl == true){
            finalShoolListData.obj[finalShoolListData.obj.length] = {};
            finalShoolListData.obj[finalShoolListData.obj.length-1].name = originalSchoolData.obj[i].name;
            finalShoolListData.obj[finalShoolListData.obj.length-1].organizationId = originalSchoolData.obj[i].organizationId;
            finalShoolListData.obj[finalShoolListData.obj.length-1].redName = finalShoolListData.obj[finalShoolListData.obj.length-1].name;
            for(var j=0;j<schoolNameArray.length;j++){
                finalShoolListData.obj[finalShoolListData.obj.length-1].redName = finalShoolListData.obj[finalShoolListData.obj.length-1].redName.replace(new RegExp(schoolNameArray[j],"gm"),'<font color="red">'+schoolNameArray[j]+'</font>');
            }
            
        }
    }
    finalShoolListData.totalCount = finalShoolListData.obj.length;
    return finalShoolListData;
}

/*
 var config = {
 				 //学校输入框id，与页面上的输入框id一致即可
        		 schoolInputId:'school',
        		 //点击学校输入框，click事件之前，执行该方法
        		 beforeClick:function(){
        		 },
        		 //用来放modal层的最外层divId，需要在页面上写出，与页面一致即可
        		 containerDiv:'schoolContainerDiv',
        		 //学校列表里面的点击事件
                 schoolListItemAfterClickFunc:function(){
                 }
         };
*/

function AutoFilterScrollSchoolListView(config){
	 var lEJS = new EJS({ url: '/yxxs-admin-portlet/tmpl/autoFilterScrollSchoolListView.ejs.txt' });
     $('#'+config.containerDiv).html(lEJS.render(config));
     
     this.tempSchoolData = null;
     var _self = this;
     
     var $bootstrapModalDiv = $('#'+config.containerDiv +' .fade');
     var $schoolNameInput = $('#'+config.containerDiv+' .modal-body .form-control');
     var $scrollDiv = $('#'+config.containerDiv+' .scrollSchool');
     
     
     this.queryParam = 
 	 {
 		 province:1,
         city:1,
         district:4,
         page:0,
         pageSize:10000,
         //stageLevel:1007,
         showClassCnt:false
 	 };
     
     var cannotReceiveParam = ['page','pageSize','showClassCnt'];
     
     this.getTempSchoolData = function(){
    	 return this.tempSchoolData;
     }
     
     this.setTempSchoolData = function(data){
         this.tempSchoolData = data;
     }
     
     this.clearSchool = function(){
    	 $schoolNameInput.val('');
    	 $schoolNameInput.attr('key','');
         _self.setTempSchoolData(null);
     };
     
	 this.setQueryParam = function(hash){
		 $.each(this.queryParam,function(k,v){
			 var index = $.inArray(k,cannotReceiveParam);
			 if(index != -1){
				 return;
			 }
			 if(typeof(hash[k]) != 'undefined'){
				 if(hash[k] != v){
					 _self.clearSchool();
				 }
				 _self.queryParam[k] = hash[k];
			 }
		 });
	 };
	 
	 this.getQueryParam = function(){
		 return this.queryParam;
	 }
     
     this.schoolListItemClickFunc = function(key,value){
    	 $scrollDiv.attr('key',key);
    	 $scrollDiv.attr('value',value);
    	 $bootstrapModalDiv.modal('hide');
    	 $schoolNameInput.val('');
         $('#'+config.schoolInputId).val($scrollDiv.attr('value'));
         $('#'+config.schoolInputId).attr('key',$scrollDiv.attr('key'));
         
         if(config.schoolListItemAfterClickFunc){
        	 config.schoolListItemAfterClickFunc();
         }
     }
     
     $('#'+config.schoolInputId).bind('click',function(){
    	 $scrollDiv.attr('key','');
    	 $scrollDiv.attr('value','');
         if(config.beforeClick){
             var bool = config.beforeClick();
             if(!bool){
            	 return;
             }
         }
         if(!_self.getTempSchoolData()){
        	 _self.searchSchoolData(_self.getQueryParam());
         }
         _self.setSchoolList(_self.getTempSchoolData());
     });     
     this.filterSchoolByStageLevel = function (schoolDataParam,stageLevelArray){
         var result = $.extend({},schoolDataParam);
         var tempSchoolArray = [];
         $.each(result.obj,function(index,obj){
             if($.inArray(obj.stageLevel,stageLevelArray) != -1){
                 tempSchoolArray.push(obj);
             }
         });
         result.obj = tempSchoolArray;
         result.totalCount = tempSchoolArray.length;
         return result;
     };
     this.searchSchoolData = function(queryParam){
 		jQuery.ajax({
 	        url: "/yxxs-main-portlet/api/listSchool",
 	        type: "GET",
 	        dataType: "json",
 	        data:queryParam,
 	        async:false,
 	        success: function (data) {
 	        	var obj = _self.filterSchoolByStageLevel(data,[1007,1008]);
 	        	_self.setTempSchoolData(obj);
 	        }
 	    });
 	 };
     
     this.setSchoolList = function(data,showRed){
         var options = '';
         for(var i=0;i<data.obj.length;i++){
             options += '<li value="'+data.obj[i].organizationId+'">'+
             (showRed?data.obj[i].redName:data.obj[i].name)+
             '</li>';
        }
         
        $scrollDiv.html(options);
        $scrollDiv.find('li').click(function(){
        	 _self.schoolListItemClickFunc($(this).attr("value"), $(this).text());
        });
        $bootstrapModalDiv.modal('show');
     }
     
     this.changeSchoolName = function(schoolData,schoolNameContent){
    	    var finalShoolData = filterSchoolName(schoolData,schoolNameContent);
    	    _self.setSchoolList(finalShoolData,true);
     }
     
     $schoolNameInput.autocomplete({
         lookup: [],
         onSearchStart: function() {
        	 _self.changeSchoolName(_self.getTempSchoolData(),$('#'+config.containerDiv+' .modal-body .form-control').val());
         }
     });
     
     $schoolNameInput.bind('keyup',function(){
         if($schoolNameInput.val() == ''){
             _self.changeSchoolName(_self.getTempSchoolData(),'');
         }
     });
}
