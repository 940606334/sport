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
    var integral=parseInt($("#giveintegral").val());
    if(!integral){
        YDUI.dialog.toast("请输入打赏积分"+integral, 'error', 1500);
        return false;
    }
    var hasintegral=parseInt($("#hasintegral").val());
    if(integral>hasintegral){
        YDUI.dialog.toast("您的积分不足", 'error', 1500);
        return false;
    }
   /* var type=$("input[name=type]").val();
    if(!type){
        layer.alert("请选择");
        return false;
    }*/
    return true;
}