 function openAdminQRCode(url_, title_, describe_){
	var title = (title_||'二维码');
	
	var url = '/yxxs-qrcode-serv-portlet/api/getUrlQrCode?url='+encodeURIComponent(url_);
	
	if(describe_ && describe_.trim().length){
		describe_ = describe_;
    }
	var content = '<img src="'+url+'"/>';
	if(describe_){
		content +='<br/>'+describe_;
	}
	
    layer.open({
        title:title,
        shadeClose: false,
        content: '<div style="text-align:center;">'+content+'</div>',
        end:function(){

        }
    });
 }