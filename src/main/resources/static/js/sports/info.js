$(document).ready(function(){
    $("#J_OpenKeyBoard").click(dosaveInfo);
})
function dosaveInfo(){
    //console.log(111);
    if(!checkInfoData()){
        return
    }
    $("#vipInfoForm").submit();
}
function checkInfoData(){
    var dialog = YDUI.dialog;
    var data=$("#v_name").val();
    if(!data){
        dialog.toast("姓名不能为空",1500);
        return false;
    }
    var vippassword=$("#v_passwd").val();
    var reg=/^\d{6}$/;
    if(!reg.test(vippassword)){
        dialog.toast("密码必须六位纯数字",1500);
        return false;
    }
    return true;
}
