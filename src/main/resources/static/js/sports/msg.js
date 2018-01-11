$(document).ready(function(){
    var msg=$("#msg").text();
    if(msg){
        var dialog = YDUI.dialog;
        dialog.toast(msg, 1500);
    }
})