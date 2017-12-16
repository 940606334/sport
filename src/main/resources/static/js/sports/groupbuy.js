$(document).ready(function(){
    $("#showIOSDialog2").click(function(){
        $("#iosDialog2").css("display",'block');
    });
    $("#closeDialog").click(function(){
        $("#iosDialog2").css("display",'none');
    });
    $("#sumbitGroupBuy").click(doSaveGroupbuy);
})

function doSaveGroupbuy(){

    if(!checkFormData()){
        return;
    }
    $("#groupbuyForm").submit();
}
function checkFormData(){
    var $tel = $.trim($("#mobile").val());
    if ($tel.length < 1) {
        layer.alert("请填写联系电话");
        return false;
    }

    var $address = $.trim($("#address").val());
    if ($address.length < 1) {
        layer.alert("请填写寄送地址");
        return false;
    }
    var $VNAME = $.trim($("#truename").val());
    if ($VNAME.length < 1) {
        layer.alert("请填写真实姓名");
        return false;
    }

    var $VNOTE = $.trim($("#note").val());
    if ($VNOTE.length > 500) {
        layer.alert("备注说明不能超过500个字");
        return false;
    }
    return true;
}