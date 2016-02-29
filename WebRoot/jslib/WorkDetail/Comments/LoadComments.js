/** 参数：1.分页元素 2.板块类型 3.展示类型**/
var k1,k2;
function LoadComments(PageParameter, BoardParameter,ShowType) {

	//展示分页
	var Node = $("#CommentsSplitPage");
	Node.show();
	
	//隐藏展开评论的按钮
	$(".LoadCommentButton").hide();
	$(".HideCommentButton").show();
	
	if (PageParameter == BoardParameter + "FirstPage") {
		page = 1;
	} else if (PageParameter == BoardParameter + "NextPage") {
		page = $("div[id='" + BoardParameter + "SplitPage']").children(
				"input[name='" + BoardParameter + "NextPage']").attr("page");
	} else if (PageParameter == BoardParameter + "LastPage") {
		page = $("div[id='" + BoardParameter + "SplitPage']").children(
				"input[name='" + BoardParameter + "LastPage']").attr("page");
	} else if (PageParameter == BoardParameter + "PreviousPage") {
		page = $("div[id='" + BoardParameter + "SplitPage']").children(
				"input[name='" + BoardParameter + "PreviousPage']")
				.attr("page");
	} else if (PageParameter == BoardParameter + "SkipPage") {
		page = $("input[type='text'][name='"+BoardParameter+"SkipCount']").attr(
				"value");

	} else {
		page = 1;

	}
	
	var WorkID = document.getElementById("CommentWorkID").value;

	var url = "/MicroClass/foreground/LoadCommentsServlet?WorkID=" + WorkID+"&page="+page+"&ShowType=All";	

	
	
    $.ajax({
        type:"post",
        url:url,
        dataType:"text/xml",
        async:false,               
success:function(data) {


		var jqueryObj = $(data);

		var UserNameXmlNodes = jqueryObj.find("UserName");
		var CreateTimeXmlNodes = jqueryObj.find("CreateTime");
		var ContentsXmlNodes = jqueryObj.find("Contents");
		var UserHeadPictureXmlNodes = jqueryObj.find("UserHeadPicture");
		var CommentIDXmlNodes = jqueryObj.find("CommentID");
		


		var RowCountXmlNodes = jqueryObj.find("RowCount");
		var PageCountXmlNodes = jqueryObj.find("PageCount");
		var ShowPageXmlNodes = jqueryObj.find("ShowPage");

		var countFromServlet = UserNameXmlNodes.length;

		
		var UserNameArray = new Array(countFromServlet);
		var CreateTimeArray = new Array(countFromServlet);
		var ContentsArray = new Array(countFromServlet);
		var UserHeadPictureArray = new Array(countFromServlet);
		var CommentIDArray = new Array(countFromServlet);

		k1 =countFromServlet;
		
		RowCount = $(RowCountXmlNodes).text();
		PageCount = $(PageCountXmlNodes).text();
		ShowPage = $(ShowPageXmlNodes).text();

		var AllMessage;
		
		CommentIDXmlNodes.each(function(i) {
			AllMessage = $(this).text();
			CommentIDArray[i] = AllMessage;
		});

		k2 =CommentIDArray;
		
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
		
		UserHeadPictureXmlNodes.each(function(i) {
			AllMessage = $(this).text();
			UserHeadPictureArray[i] = AllMessage;

		});
	
		//在每次载入的时候,消除所有节点
		for ( var i = 0; i < 10; i++) {
			$("#CommentsFloor"+i).remove();
		}
		



		for ( var j = 0; j < countFromServlet; j++) {

			//加载评论中的评论
			
			
			

			
   if(j%2==0){
       CreateDivNodeAppendToAnotherDiv("CommentsFloor"+j, "CommentsFloorOdd", "", "","", "CommentWindow");

   }else{
       CreateDivNodeAppendToAnotherDiv("CommentsFloor"+j, "CommentsFloorEven", "", "","", "CommentWindow");

   }
	
   
   CreateDivNodeAppendToAnotherDiv("CommentsAuthorInfo"+j, "CommentsAuthorInfo", "", "","", "CommentsFloor"+j);
   
   
   CreateDivNodeAppendToAnotherDiv("CommentsUserHeadPictureBoss"+j, "CommentsUserHeadPicture", "", "","", "CommentsAuthorInfo"+j);
   
	   var imgUrl = "Upload/UserHeadPicture/"+ UserHeadPictureArray[j];
	   
   CreateImgNodeAppendToAnotherDiv("CommentsUserHeadPicture"+j,"CommentsUserHeadPicture",imgUrl,"","CommentsUserHeadPictureBoss"+j);
  
   CreateDivNodeAppendToAnotherDiv("CommentsUserName"+j, "CommentsUserName", "", "","", "CommentsAuthorInfo"+j);
   
	$("#CommentsUserName" + j).html(UserNameArray[j]);
	
   CreateDivNodeAppendToAnotherDiv("CommentContextMain"+j, "CommentContextMain", "", "","", "CommentsFloor"+j);
  
   
   CreateDivNodeAppendToAnotherDiv("CommentsContents"+j, "CommentsContents", "", "","", "CommentContextMain"+j);
   CreateDivNodeAppendToAnotherDiv("CommentsTime"+j, "CommentsTime", "", "","", "CommentContextMain"+j);

   $("#CommentsContents" + j).html(ContentsArray[j]);
   $("#CommentsTime" + j).html(CreateTimeArray[j]);
   
   //这里创建子评论窗口，用来放子评论的内容.
   CreateDivNodeAppendToAnotherDiv("CommentReply"+j, "CommentReply", "", "","", "CommentsFloor"+j);	

   
   
  

  //这里放的是楼层的参数CommentID
   CreateInputNodeAppendToAnotherDiv("", "ReplyCommentToFloorHiddenCommentID"+j, "", "hidden", "CommentsFloor"+j, "");
   $(".ReplyCommentToFloorHiddenCommentID"+j).attr('id',CommentIDArray[j]);

   
   //这个事隐藏的评论框，通过点击   我也来说一句 之后就会显示出来
  CreateDivNodeAppendToAnotherDiv("ReplyCommentWindow"+j, "ReplyCommentWindow", "", "","", "CommentsFloor"+j);	      

   

   //为每层楼的评论附上回复按钮
   
   CreateInputNodeAppendToAnotherDiv("","ReplyCommentToFloorButton","ShowReplyCommentToFloorWindow(this,"+j+")","button","CommentsFloor"+j,"我也来说一句");

   
   //为每个楼层添加一个 展开子评论 按钮
  //CreateInputNodeAppendToAnotherDiv("","ShowReplyCommentButton","LoadCommentsReply("+CommentIDArray[j]+","+j+")","button","CommentsFloor"+j,"加载子评论");


	LoadCommentsReply(CommentIDArray[j],j);
		

			
	}
		

		
		
		
		
		
		

			$("#"+BoardParameter+"RowCount").html("共" + RowCount + "条评论");
			$("#"+BoardParameter+"PageCount").html("共" + PageCount + "页");
			$("#"+BoardParameter+"ShowPage").html("当前是第" + ShowPage + "页");


			JudgeShowSplitPage(RowCount, PageCount, ShowPage,BoardParameter);

		
     
        
}
    
    
    
    
    }
    
)
	
	

}




function HideComments() {
	$(".HideCommentButton").hide();
	$(".LoadCommentButton").show();
	
	//把内容全部清除
	$("#CommentWindow").empty();
	
	$("#CommentsSplitPage").hide();

	
}