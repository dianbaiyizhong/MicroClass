
function showSearchWindow() {
	var windNode = $("#SearchWindowBoss");

	windNode.fadeIn("slow");
	// $( "#SearchWindowDiv" ).draggable({ handle: "" });

	// windNode.css("z-index", "10");

}

function hideSearchWindow() {
	var windNode = $("#SearchWindowBoss");

	windNode.fadeOut("slow");

}





function ShowWindowByClass(e) {
	if(e=="RegisterWindow"){
		
		//如果是注册窗口，先隐藏登陆窗口
		HideWindowByClass("LoginWindow");
		
		
	}
	
	var Node = $("."+e);
	Node.show();
	
}

function HideWindowByClass(e) {
	var Node = $("."+e);
	Node.hide();
	
}