!function (win, $) {

    var dialog = win.YDUI.dialog;

    var $getCode = $('#J_GetCode');

    // 定义参数
    $getCode.sendCode({
        disClass: 'btn-disabled', // 禁用按钮样式【必填】
        secs: 60, // 倒计时时长 [可选，默认：60秒]
        run: false,// 是否初始化自动运行 [可选，默认：false]
        runStr: '{%s}秒后重新获取',// 倒计时显示文本 [可选，默认：58秒后重新获取]
        resetStr: '重新获取验证码'// 倒计时结束后按钮显示文本 [可选，默认：重新获取验证码]
    });


    $getCode.on('click', function () {
        var $this = $(this);
        var pattern=/(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
        var mobile=$("#mobile").val();
        if(!pattern.test(mobile)){
            dialog.toast('手机号格式不对', 1500);
            return;
        }
        dialog.loading.open('发送中...');
        var getcodeurl='/getCode';
        param={"mobile":mobile};
        var checkmobile='/checkmobile';
        $.post(checkmobile,param,function(result){
            if(result.status==1){
                dialog.loading.close();
                dialog.toast(result.msg, 1500);
            }else{
                $.post(getcodeurl,param,function (data) {
                    //var data=JSON.parse(result);
                    console.log(data);
                    dialog.loading.close();
                    if(data.status==1){
                        dialog.toast('已发送', 'success', 1500);
                        //$("#v_code").val(data.info);测试
                    }else{
                        dialog.toast(data.msg,1500);
                    }
                    $this.sendCode('start');
                });
            }
        });

        // ajax 成功发送验证码后调用【start】
        /*setTimeout(function () { //模拟ajax发送
            dialog.loading.close();
            $this.sendCode('start');
            dialog.toast('已发送', 'success', 1500);
        }, 800);*/
    });
   /* $("input[name=checkcode]").focus(function(){
        $getCode.click()
    });*/
}(window, jQuery);

