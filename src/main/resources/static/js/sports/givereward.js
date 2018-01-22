$(document).ready(function(){
    $("#sendMsg").click(doGivereward);
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
            layer.alert("只能输入正整数");
            return false;
        }
    }

    var type=$("input[name=type]").val();
    if(!type){
        layer.alert("请选择");
        return false;
    }
    return true;
}