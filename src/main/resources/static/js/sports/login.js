$(document).ready(function(){
	$("#J_OpenKeyBoard").click(vipcheck);
	//$("#J_OpenKeyBoard").attr("href",url);
	//console.log(url);
})
function vipcheck(){
	//var host=document.domain;
	//console.log(host);
	//var url="http://"+host+"/sport/wechat/authorize";
	var url="/sport/wechat/authorize";
	window.location.href=url;
	/*$.get(url,function(result){
		if(result.code=1){
            window.location.href=result.data
		}else{
			console.log("认证失败");
		}
	})*/
}
