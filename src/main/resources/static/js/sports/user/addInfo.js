$(document).ready(function(){
    $("#save_info").click(dosubmit);
})
function dosubmit() {
    if(checkFormData()){
        $("#addInfoForm").submit();
    }
}

function checkFormData() {
    var email=$("#email").val();
    var regemail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    if(!email){
        YDUI.dialog.toast('不能为空', 'error', 1500);
        return false;
    }
    if(!regemail.test(email)){
        YDUI.dialog.toast('邮箱格式不对', 'error', 1500)
        return false;
    }
    var weblog=$("#weblog").val();
    if(!weblog){
        YDUI.dialog.toast('不能为空', 'error', 1500);
        return false;
    }
    var qq=$("#qq").val();
    if(!qq){
        YDUI.dialog.toast('不能为空', 'error', 1500);
        return false;
    }
    return true;
}