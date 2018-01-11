$(document).ready(function () {
    $("#relieve").click(function(){
        console.log("123");
        var b=confirm("确定要解除绑定吗");
        if(b){
            window.location.href="/delId";
        }
    })
})