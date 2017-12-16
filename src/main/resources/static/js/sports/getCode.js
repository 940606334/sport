!function (win, $) {

    var dialog = win.YDUI.dialog;

    var $getCode = $('#J_GetCode');

    // 定义参数
    $getCode.sendCode({
        disClass: 'btn-disabled', // 禁用按钮样式【必填】
        secs: 15, // 倒计时时长 [可选，默认：60秒]
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
        var getcodeurl='/sport/getCode';
        param={"mobile":mobile};
        $.post(getcodeurl,param,function (result) {
            var data=JSON.parse(result);
            console.log(data);
            dialog.loading.close();
            if(data.status==1){
                dialog.toast('已发送', 'success', 1500);
                $("#v_code").val(data.info);
            }
            $this.sendCode('start');
        });
        // ajax 成功发送验证码后调用【start】
        /*setTimeout(function () { //模拟ajax发送
            dialog.loading.close();
            $this.sendCode('start');
            dialog.toast('已发送', 'success', 1500);
        }, 800);*/
    });

}(window, jQuery);