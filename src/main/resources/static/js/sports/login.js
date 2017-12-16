$(document).ready(function(){
	$("#J_OpenKeyBoard").click(vipcheck);
	//$("#J_OpenKeyBoard").attr("href",url);
	//console.log(url);
})
function vipcheck(){
	//验证表单信息
	var param=checkMobileForm();
	if(!param){
		return;
	}
	$("#mobileForm").submit();
}
function checkMobileForm(){
    var dialog = YDUI.dialog;
    var pattern=/(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
    var mobile=$("#mobile").val();
    if(!pattern.test(mobile)){
        dialog.toast('手机号格式不对', 1500);
        return false;
    }
    var checkcode=$("#v_code").val();
    if(!checkcode){
        dialog.toast('请输入验证码', 1500);
        return false;
    }
    var param={mobile:mobile,checkcode:checkcode};
    return param;
}
//var host=document.domain;
//console.log(host);
//var url="http://"+host+"/sport/wechat/authorize";
// var url="/sport/wechat/authorize";
// window.location.href=url;
/*$.get(url,function(result){
    if(result.code=1){
        window.location.href=result.data
    }else{
        console.log("认证失败");
    }
})*/