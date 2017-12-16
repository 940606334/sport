$(document).ready(function(){
    $("#J_OpenKeyBoard").click(dosaveInfo);
})
function dosaveInfo(){
    console.log(111);
    if(!checkInfoData()){
        return
    }
    $("#vipInfoForm").submit();
}
function checkInfoData(){
    var data=$("#v_name").val();
    if(!data){
        layer.alert("姓名不能为空");
        return false;
    }
    return true;
}
