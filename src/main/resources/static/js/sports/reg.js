$(document).ready(function(){
    //$("#demo").hide();
    //getLocation();
    //getStoreInfo();
    $("#J_OpenKeyBoard").click(regvip);

})
/*var x=document.getElementById("demo");
function getLocation()
{
    if (navigator.geolocation)
    {
        navigator.geolocation.getCurrentPosition(showPosition);
    }
    else{x.innerHTML="Geolocation is not supported by this browser.";}
}
function showPosition(position)
{
    x.innerHTML=position.coords.latitude + "," + position.coords.longitude;

}*/
//获取商店信息
function getStoreInfo(coordinate){
    var dialog = YDUI.dialog;
    //var coordinate=x.innerHTML;
    var url="/getStore";
    //var coordinate=$.cookie("VPIAO_MOBILE_CURRENTCITY1");
    var param={coordinate:coordinate};

    $.post(url,param,function(data){
        dialog.loading.close();
        var msg=$("#msg").text();
        if(msg){
            dialog.toast(msg, 1500);
        }
        //var data=JSON.parse(result);
        if(data.status=1){
            var storenumber=data.item.storenumber;
            setStoreList(data.lists,storenumber);
        }else{
            dialog.toast(data.msg, 1500);
        }
    })
}

function setStoreList(list,storenumber){
    var storeid=$("#storeid");
    storeid.empty();
    var option=$('<option value="" >请选择</option>');

    storeid.append(option);
    for(var i=0;i<storenumber;i++){
        var distance=list[i].distance;
        if(distance){
            option=$('<option value="'+list[i].id+'">'+list[i].name+'('+distance+'km)'+'</option>');
        }else{
            option=$('<option value="'+list[i].id+'">'+list[i].name+'</option>');
        }
       storeid.append(option);
    }
}
function regvip(){
    var b=checkvipinfo();
    if(!b){
        return;
    }
    $("#vipregform").submit();
    YDUI.dialog.loading.open("正在提交中");
}

function checkvipinfo(){
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
    var truename=$("#truename").val();
    if(!truename){
        dialog.toast('姓名不能为空', 1500);
        return false;
    }
    var namereg=/^[\u2E80-\u9FFF]+$/;//Unicode编码中的汉字范围
    if(!namereg.test(truename)){
        dialog.toast('输入的姓名包含字母或数字', 1500);
        return false;
    }
    var vipsex=$("#vipsex").val();
    console.log(vipsex);
    if(!vipsex){
        dialog.toast('请选择性别', 1500);
        return false;
    }
    var birthday=$("#showDate").html();
    console.log(birthday);
    if(birthday=='请选择时间'){
        dialog.toast("请选择时间",1500);
        return false;
    }
    $("#vipbirthday").val(birthday);
    /*pattern=/^(19|20)\d{2}(1[0-2]|0?[1-9])(0?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(!pattern.test(vipbirthday)){
        dialog.toast("请输入生日,正确格式如20110506",1500);
        return false;
    }*/
    var storeid=$("#storeid").val();
    if(!storeid){
        dialog.toast("请选择门店",1500);
        return false;
    }
    var vippassword=$("#vippassword").val();
    var reg=/^\d{6}$/;
    if(vippassword){
        if(!reg.test(vippassword)){
            dialog.toast("密码必须六位纯数字",1500);
            return false;
        }
    }
   /* var param={mobile:mobile,
               checkcode:checkcode,
               vipsex:vipsex,
               vipbirthday:birthday,
               storeid:storeid,
               vippassword:vippassword};*/
    return true;
}