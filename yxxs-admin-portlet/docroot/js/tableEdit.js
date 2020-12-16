
/*
config = {
    columns: [ 
        { name: "FieldName", desc: "FileDesc", format: function (v) { return v ;} },
    ],
    operations: [
        {
            name: "操作名称",
            visable: function (obj) { return true },
            getUrl: function (obj) { return "?mode=edit&ID=" + obj.ID; }
        },
    ],
    pageUrlFunc:funciton(page){
        return '?page=' + (page - 1);
    }
}
*/
function initTableList(divId, pagerResult, config ) {
    var tejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/viewTable/tableView.ejs.txt' });

    $('#'+divId).html(tejs.render({
        list: pagerResult.obj,
        columns: config.columns,
        operations: config.operations
    }));

    $('#pager').html('');
    if(typeof initPager != 'undefined'){
        initPager("pager", pagerResult, function (page) {
            return config.pageUrlFunc(page);
        });
        
        if($('#pager ul').length == 0){
        	$('#pager').append('<ul class="pagination pagination-md"></ul>');
            $('#pager').removeClass('pagination');
            $('#pager').show();
        }
        
        if(pagerResult.pageCount != 0){
            $('#pager ul').append('<li><span>-第'+(pagerResult.pageNumber+1)+'页-</span></li>');
        }
        
        $('#pager ul').append('<li><span>共'+pagerResult.totalCount+'条,'+pagerResult.pageCount+'页</span></li>');
    }

}

/*

var fieldBaseInfoList = [
    { name: ['Name', '名称'] },
    { name: ['Desc', '描述'], type: 'textarea' },
    { name: ['Password', '密码'], type: 'password' },
    { name: ['Sex', '性别'], type: 'dropdown', data: [{ k: 0, v: '未指定' }, { k: 1, v: '男' }, { k: 2, v: '女'}] },
    { name: ['Sex', '性别'], type: 'checkbox', data: [{ k: 0, v: '未指定' }, { k: 1, v: '男' }, { k: 2, v: '女'}] },
];
var tabInfoList = [
    { name: "baseTab", desc: "配置", data: fieldBaseInfoList },
];
//conf = {label: label, FileName: FileName, desc: desc, data, setting: setting };
setDataFunc = function(conf){
    
}
*/

function initModelEdit(divId, data, tabInfoList, setDataFunc) {

    var tabEJS = new EJS({ url: '/yxxs-admin-portlet/tmpl/tabs.ejs.txt' });
    $('#' + divId).html(tabEJS.render({ tabs: tabInfoList }));

    var vejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/ViewRow/textViewRow.ejs.txt' });
    var vimgEjs = new EJS({ url: '/yxxs-admin-portlet/tmpl/ViewRow/imgViewRow.ejs.txt' });

    var tejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/EditRow/textEditRow.ejs.txt' });
    var taejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/EditRow/textAreaEditRow.ejs.txt' });
    var pejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/EditRow/passwordEditRow.ejs.txt' });
    var ddejs = new EJS({ url: '/yxxs-admin-portlet/tmpl/EditRow/dropdownEditRow.ejs.txt' });
    var cbEjs = new EJS({ url: '/yxxs-admin-portlet/tmpl/EditRow/checkboxEditRow.ejs.txt' });
    
    var vejsMapper = {
        'image': vimgEjs,
    };

    var ejsMapper = {
        'textarea': taejs,
        'password': pejs,
        'dropdown': ddejs,
        'checkbox': cbEjs,
    };

    for (var j = 0; j < tabInfoList.length; j++) {
                    
        for (var i = 0; i < tabInfoList[j].data.length; i++) {
            var fieldInfoList = tabInfoList[j].data;

            var ejs = null;
            if(!fieldInfoList[i].mode){
                if (!fieldInfoList[i].type) {
                    ejs = tejs;
                }else{
                    ejs = ejsMapper[fieldInfoList[i].type];
                }
            }else{
                if (!fieldInfoList[i].type) {
                    ejs = vejs;
                }else{
                    ejs = vejsMapper[fieldInfoList[i].type];
                }
            }

            if (!ejs) {
                continue;
            }
            
            var value = data[fieldInfoList[i].name[0]];
            if(typeof fieldInfoList[i].format == 'function'){
            	value = fieldInfoList[i].format(value, data);
            }

            var conf = {
                label: fieldInfoList[i].name[1],
                FileName: fieldInfoList[i].name[0],
                value: value,
                desc: fieldInfoList[i].desc,
                data: fieldInfoList[i].data, 
                setting: fieldInfoList[i].setting 
            };

            $('#' + tabInfoList[j].name).append(ejs.render(conf));
            
            if (fieldInfoList[i].type == 'password') {
                continue;
            }

            if (fieldInfoList[i].type == 'checkbox') {
            	if(data.length > 1){
            		//页面处理初始化设置checked问题；
            	}else{
            		if(document.getElementById(conf.FileName)){
            			document.getElementById(conf.FileName).checked = data?data[conf.FileName]:false;
            		}
            	}
                
                continue;
            }

            $('#' + conf.FileName).val(conf.value);
            
            if (setDataFunc) {
                setDataFunc(conf);
            }
        }
    }
}

function getModelHash(tabInfoList) {
    var hash = {};

    for (var j = 0; j < tabInfoList.length; j++) {

        for (var i = 0; i < tabInfoList[j].data.length; i++) {
            var fieldInfoList = tabInfoList[j].data;

            if (fieldInfoList[i].type == 'checkbox') {
            	var o = document.getElementsByName(fieldInfoList[i].name[0]);
            	//if(o.length >1){
            		var check_val = [];
                	for(k in o){
                        if(o[k].checked){
                        	check_val.push(o[k].value);
                        }
                    }
                	hash[fieldInfoList[i].name[0]] = check_val;
            	//}else{
            	//	hash[fieldInfoList[i].name[0]] = document.getElementById(fieldInfoList[i].name[0]).checked ? 1 : 0;
            	//}
            	
                
                continue;
            }

            hash[fieldInfoList[i].name[0]] = $('#' + fieldInfoList[i].name[0]).val();
        }
    }
    return hash;
}

function fieldDropDownData(fieldBaseInfoList, fileName, data){
    for(var i=0;i<fieldBaseInfoList.length;i++){
        if(fieldBaseInfoList[i].name[0] == fileName && fieldBaseInfoList[i].type == 'dropdown'){
            if (data == undefined) {
                return fieldBaseInfoList[i].data;
            }else{
                fieldBaseInfoList[i].data = data;
                break;
            }
        }
    }
}

function exportExcel(columns,data,fileName){
	var str = "";
	for(var i = 0;i < columns.length;i++){
		str += columns[i].desc+",";
	}
	str = str.substring(0, str.length-1)+"\n";
	for(var i = 0;i < data.length;i++){
		for(var j=0;j<columns.length;j++){
			var v = data[i][columns[j].name];
			if(columns[j].format){
				v= columns[j].format(v,data[i]);
			}
			str += v + '\t,';
		}
        str+='\n';
	}
	var uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str);
	var link = document.createElement("a");
    link.href = uri;
    //对下载的文件命名
    link.download =  fileName+".csv";
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}