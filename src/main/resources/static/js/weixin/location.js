$.getScript("http://res.wx.qq.com/open/js/jweixin-1.1.0.js", function () {
    /*if($.cookie("VPIAO_MOBILE_CURRENTCITY1")){
        return;
    }*/
    $.ajax({
        type: "get",
        dataType: "json",
        cache: false,
        url: "/sport/wechat/getApi?url="+encodeURIComponent(window.location.href),
        success: function (data) {
            wx.config({
                debug: true,
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

                    }
                }
            });
                wx.getLocation({
                    success: function (res) {
                        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                        var speed = res.speed; // 速度，以米/每秒计
                        var accuracy = res.accuracy; // 位置精度

                        var coordinate = latitude + "," + longitude;
                        console.log(coordinate);
                        $.cookie('VPIAO_MOBILE_CURRENTCITY1', coordinate, { expires: 1, path: '/' });
                        getStoreInfo(coordinate);
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
