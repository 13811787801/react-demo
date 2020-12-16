/**
 * hash.classId
 * hash.schoolId
 * hash.userId
 * hash.termStartDate
 * hash.termEndDate
 * @param func
 * func(hash,already)
 */
function saveClass(hash, func){

	var isNew = (hash.classId != 0 && hash.classId)?false:true;
    hash.className = hash.name;
    hash.page = 0;
    hash.pageSize = 1000;
    
    $.ajax({
        url: "/yxxs-main-portlet/api/listClass",
        type: "POST",
        dataType: "json",
        data: hash,
        success: function (ldata) {
        	
        	//检查重复
            if(ldata.obj.length >0){
            	for(var i=0;i<ldata.obj.length;i++){
            		if(ldata.obj[i].name == hash.name && ldata.obj[i].organizationId != hash.classId){
                        if(func){
                        	hash.classId = ldata.obj[i].organizationId;
                        	func(hash, true);
                        }
                        return;
            		}
            	}
            }

            var url = "/yxxs-main-portlet/api/addClass";
            if(!isNew){
                url = "/yxxs-main-portlet/api/updateClass";
            }
            
            $.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                data: hash,
                success: function (data) {

                	//新增更新id
                    if(isNew){
                        hash.classId = data.organizationId;
                    }

                    if(isNew){

                        //加入班级小管
                        $.ajax({
                            url: "/yxxs-main-portlet/api/getSchool?orgId="+hash.schoolId,
                            type: "POST",
                            dataType: "json",
                            async:false,
                            data: { },
                            success: function (sdata) {
                                if(sdata.defaultManagerId){
                                    $.ajax({
                                        url: "/yxxs-main-portlet/api/directAddUserToClass",
                                        type: "POST",
                                        dataType: "json",
                                        async:false,
                                        data: {
                                            userId:sdata.defaultManagerId,
                                            classId:hash.classId,
                                            roleId:14217
                                        },
                                        success: function (adata) {
                                        }
                                    });
                                }
                            }
                        });
                        
                        //添加学期
                        $.ajax({
                            url: '/yxxs-main-portlet/api/addClassStage',
                            type: "POST",
                            dataType: "json",
                            data: {
                                orgId: hash.classId,
                                userId:hash.userId,
                            },
                            async:false,
                            success: function (adata) {

                                //激活
                                $.ajax({
                                    url: "/yxxs-main-portlet/api/listClassStage",
                                    type: "POST",
                                    dataType: "json",
                                    data: {classId:hash.classId},
                                    async:false,
                                    success: function (ldata) {

                                        for(var i=0;i<ldata.length;i++){

                                            var shash = ldata[i];
                                            shash.classStageId = ldata[i].id;
                                            shash.active = 1023;
                                            shash.startDate = hash.termStartDate;
                                            shash.endDate = hash.termEndDate;
                                            
                                            $.ajax({
                                                url: "/yxxs-main-portlet/api/updateClassStage",
                                                type: "POST",
                                                dataType: "json",
                                                data: shash,
                                                async:false,
                                                success: function (udata) {
                                                    //alert("保存成功");
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        });
                    }
                    
                    if(func){
                    	func(hash);
                    }
                }
            });
        }
    });
}