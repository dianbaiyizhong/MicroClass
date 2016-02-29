
function ShowReplyCommentWindow(e,floor,i,UserName){
	
	//先把窗口显示出来，之前已经建立好的
	var Node = $("#ReplyCommentWindow"+floor);
	Node.show();
	
	//先清除残留节点
	$("#ReplyComment"+floor).remove();
	$("#ReplyCommentButton"+floor).remove();
	 CreateInputNodeAppendToAnotherDiv("ReplyComment"+floor, "", "", "text", "ReplyCommentWindow"+floor, "回复给 "+UserName);
	
	 
	 CreateInputNodeAppendToAnotherDiv("ReplyCommentButton"+floor, "", "SubmitComment("+floor+","+i+")", "button", "ReplyCommentWindow"+floor,"提交回复");
	
	
}

function HideReplyCommentWindow(e){
	var Node = $("#ReplyCommentWindow");
	Node.hide();
	
}

 
function ShowReplyCommentToFloorWindow(e,floor) {
	$(".ReplyCommentToFloorButton").remove();
	
	var Node = $("#ReplyCommentWindow"+floor);
	Node.show();
	//创建评论框
	

	   CreateInputNodeAppendToAnotherDiv("ReplyComment"+floor, "", "", "text", "ReplyCommentWindow"+floor, "");

	   CreateInputNodeAppendToAnotherDiv("", "", "SubmitComment("+floor+")", "button", "ReplyCommentWindow"+floor,"提交回复");


	   
	
}

