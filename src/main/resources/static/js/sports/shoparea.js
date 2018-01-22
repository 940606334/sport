$.getScript("http://res.wx.qq.com/open/js/jweixin-1.1.0.js", function () {
    YDUI.dialog.loading.open("门店加载中....");
    /*var coordinate=$.cookie("VPIAO_MOBILE_CURRENTCITY1");
    if(coordinate){
        var reg =/^[-\+]?\d+(\.\d+)\,[-\+]?\d+(\.\d+)$/;
        if(reg.test(coordinate)){
            ajaxloadinfo(coordinate);
            return;
        }
    }*/
    $.ajax({
        type: "get",
        dataType: "json",
        cache: false,
        url: "/wechat/getApi?url="+encodeURIComponent(window.location.href),
        success: function (data) {
            wx.config({
                debug: false,
                appId: data.appid,
                timestamp: data.timestamp,
                nonceStr: data.nonceStr,
                signature: data.signature,

                jsApiList: [// 所有要调用的 API 都要加到这个列表中
                    'checkJsApi',
                    'openLocation',
                    'getLocation']
            });
            wx.ready(function () {
                // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
                wx.checkJsApi({
                    jsApiList: ['getLocation'],
                    success: function (res) {
                        //alert(JSON.stringify(res));
                        //alert(JSON.stringify(res.checkResult.getLocation));
                        if (res.checkResult.getLocation == false) {
                            alert('你的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！');
                            return;
                        }else if(res.checkResult.openLocation==false){
                            alert('你的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！');
                            return;
                        }
                    }
                });
                wx.getLocation({
                    success: function (res) {
                        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                        var speed = res.speed; // 速度，以米/每秒计
                        var accuracy = res.accuracy; // 位置精度
                        var coordinate=null;
                        if(latitude&&longitude){
                            coordinate = latitude + "," + longitude;
                            $.cookie('VPIAO_MOBILE_CURRENTCITY1', coordinate, { expires: 1, path: '/' });
                        }
                        if(!coordinate){
                            dialog.toast("手机版本过低,无法获取地理位置",1500);
                        }
                         ajaxloadinfo(coordinate);

                        //ajaxloadinfo();
                        //window.location.replace("/area.aspx");
                    },
                    cancel: function (res) {
                        alert('用户拒绝授权获取地理位置');
                    }

                });

            });
        },
        complete: function (msg) {


        }
    });

});

function ajaxloadinfo(coordinate){
    var url="/getStore?coordinate="+coordinate;
    console.log(coordinate);
    $.get(url,function(json){
        YDUI.dialog.loading.close();
        if(json.status==1){
            var storenumber=json.item.storenumber;
            setStoreInfoList(json.lists,storenumber);
        }else{
            YDUI.dialog.toast("加载失败",1500);
        }
    });
}
function setStoreInfoList(list,storenumber){
    var $list=$("#list");
    //console.log(list);
    for(var i=0;i<storenumber;i++){
        var $store=$("#store").clone().show();
        $store.find(".name").html(list[i].name);
        $store.find(".address").html(list[i].address);
        $store.find(".phone").html(list[i].phone);
        var distance=list[i].distance+"";
        distance=distance.substr(0,distance.indexOf(".")+3);
        $store.find(".distance").html(distance+"km");
        $store.find("input[name=coordinate]").val(list[i].coordinate);
        $store.appendTo($list);
    }
    //YDUI.dialog.loading.close();
    //setClickThing();
}


function setClickThing(obj) {
    //alert("123");
    // $(".weui-media-box_appmsg").click(function(){
    var coordinate = $(obj).find("input[name=coordinate]").val();
    var name = $(obj).find(".name").html();
    var address = $(obj).find(".address").html();
    var arr = coordinate.split(",");
    var latitude = Number(arr[0].substr(0, arr[0].indexOf(".") + 6)); // 纬度，浮点数，范围为90 ~ -90
    //console.log(latitude);
    var longitude = Number(arr[1].substr(0, arr[1].indexOf(".") + 6)); // 经度，浮点数，范围为180 ~ -180。
    //console.log(longitude);
    //console.log(latitude);
    //console.log(longitude);
    //alert(coordinate);
    wx.openLocation({
        latitude: latitude, // 纬度，浮点数，范围为90 ~ -90
        longitude: longitude, // 经度，浮点数，范围为180 ~ -180。
        name: name, // 位置名
        address: address, // 地址详情说明
        scale: 10, // 地图缩放级别,整形值,范围从1~28。默认为最大
        infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
    });
    //})

}
