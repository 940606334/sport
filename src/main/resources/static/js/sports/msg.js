$(document).ready(function(){
    var msg=$("#msg").text();
    if(msg){
       YDUI.dialog.toast(msg, 1500);
       //alert(msg);
       console.log(msg);
    }
})