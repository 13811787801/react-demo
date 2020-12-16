function initTagsInput(id, fullTagList){
	
	$('#'+id).hide();
	var values = [];
	if($('#'+id).val()){
		if($('#'+id).attr('multiple') == 'multiple'){
			values = $('#'+id).val();
		}else{
			values.push($('#'+id).val());
		}
	}

	if(!fullTagList){
		fullTagList = [];
		var l = $('#'+id).find('option');
		for(var i=0;i<l.length;i++){
			fullTagList.push({k:$(l[i]).val(),v:$(l[i]).html()});
		}
	}
	
	var ops = '';
	for(var i=0;i<fullTagList.length;i++){
		ops +='<option value="'+fullTagList[i].k+'">'+fullTagList[i].v+'</option>';
	}
	$('#'+id).html(ops);
	
	if($('#'+id).parent().find('.tagsInputContainer').length == 0){
		$('#'+id).after('<span class="tagsInputContainer"></span>');

		var container = $('#'+id).parent().find('.tagsInputContainer');
		container.html(''+
				'<style>'+
					'.autocomplete-suggestions{overflow-y:scroll; background-color:#fff;border:1px solid #ccc;}'+
					'.autocomplete-suggestion{border-bottom:1px solid #eee;}'+
					'.tagsList span{margin-right:5px;}'+
					'.tagsList a.del{cursor:pointer}'+
				'</style>'+
				'<span class="tagsList"></span><br/>'+
				'<input class="tagsSearchInput" style="width:400px;height:30px;vertical-align:middle;border:1px solid #ccc;"/>'+
				'<a class="btn btn-default btn-sm addBtn">添加关键词</a>'
			);

		container.find('.addBtn').click(function(){
			var v = container.find('.tagsSearchInput').attr("tagId");
        	if(!v){
        		alert('关键词未选中');
        		return;
        	}
        	if(values.indexOf(v) == -1){
        		values.push(v);
        	}
        	setFunc();
        	container.find('.tagsSearchInput').val('');
        	container.find('.tagsSearchInput').attr("tagId",null);
		});
	}
	
	var initFunc = function(){

		var container = $('#'+id).parent().find('.tagsInputContainer');
		container.find('.addBtn').attr('disabled','disabled');

		var dataArr = [];
		var selectedIndex = -1;
		for(var i=0;i<fullTagList.length;i++){
			if(values && values.indexOf(fullTagList[i].k) > -1){
				selectedIndex = i;
			}
			dataArr.push({value:fullTagList[i].v, data:fullTagList[i].k});
		}
		
		
	    container.find('.tagsSearchInput').autocomplete({
	    	selectedIndex:selectedIndex,
	        lookup: dataArr,
	        minChars:0,
	        onSelect: function(suggestion) {
	        	container.find('.tagsSearchInput').attr("tagId",suggestion.data);
	        },
	        onHint: function (hint) {
	        },
	        onInvalidateSelection: function() {
	        	container.find('.tagsSearchInput').attr("tagId",null);
	        }
	    });
		
		container.find('.tagsSearchInput').click(function(){
			if(container.find('.tagsSearchInput').val().length > 0){
				return;
			}
			container.find('.tagsSearchInput').autocomplete("search");
		});
	}
	
	var setFunc = function(){
		
		var container = $('#'+id).parent().find('.tagsInputContainer');
		container.find('.tagsList').html('');
		
		container.find('.addBtn').attr('disabled','disabled');
		if($('#'+id).attr('multiple') == 'multiple' || values.length == 0){
			container.find('.addBtn').attr('disabled',null);
		}
		
		for(var i=0;i<values.length;i++){
			container.find('.tagsList').append('<span tagId='+values[i]+'>'+$('#'+id).find('option[value='+values[i]+']').html()+'<a href="javascript:;" class="del">X</a></span>');
		}
		container.find('.tagsList a.del').click(function(){
			var tagId = $(this).parents('span').attr('tagId');
			var nvalues = [];
			for(var i=0;i<values.length;i++){
				if(values[i] != tagId){
					nvalues.push(values[i]);
				}
			}
			values = nvalues;
			setFunc();
		});

		if($('#'+id).attr('multiple') == 'multiple'){
			$('#'+id).val(values);
		}else{
			$('#'+id).val(values.length == 0? null : values[0]);
		}
	}
	
	if(!$.Autocomplete){
		var path = '/yxxs-static-portlet/js/autocomplete/jquery.autocomplete.js';

        var oHead = document.getElementsByTagName('HEAD').item(0);
        
        var oscript = document.createElement("script");
        oscript.type = "text/javascript";
        oscript.src = path;
        
        oscript.onload = function () {
			initFunc();
			setFunc();
        }
        
        oHead.appendChild(oscript);
        
        /*
        //cdn 跨域加载问题
		$.getScript(path,function(){
			initFunc();
			setFunc();
		});
		*/
	}else{
		initFunc();
		setFunc();
	}
}

