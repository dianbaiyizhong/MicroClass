
function LoadCommentsReply(CommentID,floor) {

	
	var url = "foreground/LoadCommentsReplyServlet?CommentID="
			+ CommentID;
	$.post(url, null, function(data) {		
		var jqueryObj = $(data);


		var UserNameXmlNodes = jqueryObj.find("UserName");
		var CreateTimeXmlNodes = jqueryObj.find("CreateTime");
		var ContentsXmlNodes = jqueryObj.find("Contents");
		var UserHeadPictureXmlNodes = jqueryObj.find("UserHeadPicture");
		var RepliedUserNameXmlNodes = jqueryObj.find("CommentRepliedUserName");
		var CommentIDXmlNodes = jqueryObj.find("CommentID");
		var UserIDXmlNodes = jqueryObj.find("UserID");
		var RepliedCommentIDXmlNodes = jqueryObj.find("RepliedCommentID");
		
		
		var countFromServlet = UserNameXmlNodes.length;
		
 

		
		if(countFromServlet!=0){
			
	
			
			var UserNameArray = new Array(countFromServlet);
			var CreateTimeArray = new Array(countFromServlet);
			var ContentsArray = new Array(countFromServlet);
			var RepliedUserNameArray = new Array(countFromServlet);
			var UserHeadPictureArray = new Array(countFromServlet);
			var CommentIDArray = new Array(countFromServlet);
			var UserIDArray = new Array(countFromServlet);
			var RepliedCommentIDArray = new Array(countFromServlet);
			
			var AllMessage="";
			
			RepliedCommentIDXmlNodes.each(function(i) {
				AllMessage = $(this).text();
				RepliedCommentIDArray[i] = AllMessage;

			});
			UserNameXmlNodes.each(function(i) {
				AllMessage = $(this).text();
				UserNameArray[i] = AllMessage;

			});

			CreateTimeXmlNodes.each(function(i) {
				AllMessage = $(this).text();
				CreateTimeArray[i] = AllMessage;

			});

			ContentsXmlNodes.each(function(i) {
				AllMessage = $(this).text();
				ContentsArray[i] = AllMessage;

			});
			
			
			RepliedUserNameXmlNodes.each(function(i) {
				AllMessage = $(this).text();
				RepliedUserNameArray[i] = AllMessage;

			});
			
			UserHeadPictureXmlNodes.each(function(i) {
				AllMessage = $(this).text();
				UserHeadPictureArray[i] = AllMessage;

			});
			

			CommentIDXmlNodes.each(function(i) {
				AllMessage = $(this).text();
				CommentIDArray[i] = AllMessage;

			});
			

			
			UserIDXmlNodes.each(function(i) {
				AllMessage = $(this).text();
				UserIDArray[i] = AllMessage;

			});
			

			



			for (var i = 0; i < countFromServlet; i++) {
				//从这里开始，把子评论的内容装进CommentReply+j的div里面（已经创建好的）
				CreateDivNodeAppendToAnotherDiv("LittleCommentFloor"+floor+"_"+i,"LittleCommentFloor","","","","CommentReply"+floor);
				
				//这里放的是CommentID的参数
				CreateInputNodeAppendToAnotherDiv(CommentIDArray[i],"ReplyCommentHiddenCommentID"+floor+"_"+i,"","hidden","LittleCommentFloor"+floor+"_"+i,"");

				//放头像不解释
				CreateDivNodeAppendToAnotherDiv("LittleCommentsUserHeadPictureBoss"+floor+"_"+i, "LittleCommentsUserHeadPicture", "", "","", "LittleCommentFloor"+floor+"_"+i);
		           
		   		var imgUrl = "Upload/UserHeadPicture/"+ UserHeadPictureArray[i];
		   		   
		        CreateImgNodeAppendToAnotherDiv("LittleCommentsUserHeadPicture"+floor+"_"+i,"LittleCommentsUserHeadPicture",imgUrl,"","LittleCommentsUserHeadPictureBoss"+floor+"_"+i);
				
		        //放子评论内容
				CreateDivNodeAppendToAnotherDiv("CommentsReplyContents"+floor+"_"+i, "", "", "","", "LittleCommentFloor"+floor+"_"+i);
				
				
				$("#"+"CommentsReplyContents"+floor+"_"+i).html(UserNameArray[i]+" 回复 "+RepliedUserNameArray[i]+": "+ContentsArray[i]);
				
				
				

				//放时间和回复按钮
				CreateDivNodeAppendToAnotherDiv("ReplyTimeAndReplyButton"+floor+"_"+i, "ReplyTimeAndReplyButton", "", "","", "LittleCommentFloor"+floor+"_"+i);

				CreateDivNodeAppendToAnotherDiv("ReplyTime"+i, "LittleReplyTime", "", "","", "ReplyTimeAndReplyButton"+floor+"_"+i);
				$("#ReplyTime"+i).html(CreateTimeArray[i]);


				//输入框
				var String = "ShowReplyCommentWindow(this,"+floor+","+i+","+"'"+UserNameArray[i]+"'"+")";
				CreateInputNodeAppendToAnotherDiv("LittleReplyButton","LittleReplyButton",String, "button", "ReplyTimeAndReplyButton"+floor+"_"+i, "回复");

				//这里是参数RepliedCommentID
				CreateInputNodeAppendToAnotherDiv("", "RepliedCommentID"+floor+"_"+i, "", "hidden", "LittleCommentFloor"+floor+"_"+i, "");
				 $(".RepliedCommentID"+floor+"_"+i).attr('id',RepliedCommentIDArray[i]);

				var Line = $("<hr>");
				Line.appendTo($("#"+"LittleCommentFloor"+floor+"_"+i));
				
				





								
			}
			


			
		}

		
	
		
		
		
	});

}