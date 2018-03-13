$(document).ready(function(){
    $("#save_info").click(doGivereward);
})
function doGivereward() {
    var b=checkGivereward();
    if(b){
        $("#giverewarForm").submit();
    }
}
function checkGivereward() {
    var integral=$("#giveintegral");
    if(!integral){
        var reg=/^[1-9]\d*$/;
        if(!reg.test(integral)){
            YDUI.dialog.toast("只能输入正整数", 'error', 1500);
            return false;
        }
    }
   /* var type=$("input[name=type]").val();
    if(!type){
        layer.alert("请选择");
        return false;
    }*/
    return true;
}