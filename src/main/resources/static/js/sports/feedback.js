$(document).ready(function () {
    //获取类型
    $("input[name=type]").change(function () {
        var typename=$(this).val();
        //layer.alert(typename);
        getType(typename);
    });
    //提交消息
    $("#sendMsg").click(dosaveFeedback);
})
function dosaveFeedback(){
   //验证表单
    if(!checkFeedback()){
       return;
   }

   //提交表单
   $("#feedbackForm").submit();
}
function checkFeedback(){
    var type=$("#description").val();
    if(!type){
        layer.alert("请选择类型");
        return false;
    }
    var $FCONTENT = $.trim($("#userask").val());
    if ($FCONTENT.length < 1) {
        layer.alert("请填写问题详情");
        return false;
    }
    if ($FCONTENT.length > 500) {
        layer.alert("问题详情不能超过500个字");
        return false;
    }
    return true;
}
//获取类型
function getType(type){
    var url="/user/getType?type="+type;
    $("#description").attr("name",type);
    $.get(url,function(data){
        setSelect(data);
    })
}
//设置选择类型
function setSelect(data){
    var select=$("#description");
    select.empty();
    var op=$('<option value="">选择问题类型</option>');
    select.append(op);
    for(var i in data){
        op=$('<option value="'+data[i].value+'">'+data[i].label+'</option>');
        select.append(op);
    }
}
