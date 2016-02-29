/** 参数：1.分页元素 2.板块类型**/
function LoadNotes(PageParameter, BoardParameter) {
	var ShowType = $(".NotesShowType").attr("id");

	var Node = $("#NotesWindowBoss");
    Node.show();
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
	
	var WorkID = document.getElementById("NotesWorkID").value;
	
		var url ="userFunction/LoadNotesServlet?WorkID=" + WorkID+"&page="+page+"&ShowType="+ShowType;	
	

	

	$.post(url, null, function(data) {

				var jqueryObj = $(data);

				var UserNameXmlNodes = jqueryObj.find("UserName");
				var UserHeadPictureXmlNodes = jqueryObj.find("UserHeadPicture");
				var TimeXmlNodes = jqueryObj.find("Time");
				var ContentsXmlNodes = jqueryObj.find("Contents");
				var LikeCountXmlNodes = jqueryObj.find("LikeCount");
				var NoteIDXmlNodes = jqueryObj.find("NoteID");

				var RowCountXmlNodes = jqueryObj.find("RowCount");
				var PageCountXmlNodes = jqueryObj.find("PageCount");
				var ShowPageXmlNodes = jqueryObj.find("ShowPage");

				var countFromServlet = UserNameXmlNodes.length;

				var UserNameArray = new Array(countFromServlet);
				var UserHeadPictureArray = new Array(countFromServlet);
				var TimeArray = new Array(countFromServlet);
				var ContentsArray = new Array(countFromServlet);
				var LikeCountArray = new Array(countFromServlet);
				var NoteIDArray = new Array(countFromServlet);

				
				
				RowCount = $(RowCountXmlNodes).text();
				PageCount = $(PageCountXmlNodes).text();
				ShowPage = $(ShowPageXmlNodes).text();

				var AllMessage;

				UserNameXmlNodes.each(function(i) {
					AllMessage = $(this).text();
					UserNameArray[i] = AllMessage;

				});
				
				UserHeadPictureXmlNodes.each(function(i) {
					AllMessage = $(this).text();
					UserHeadPictureArray[i] = AllMessage;

				});

				TimeXmlNodes.each(function(i) {
					AllMessage = $(this).text();
					TimeArray[i] = AllMessage;

				});

				ContentsXmlNodes.each(function(i) {
					AllMessage = $(this).text();
					ContentsArray[i] = AllMessage;

				});
				LikeCountXmlNodes.each(function(i) {
					AllMessage = $(this).text();
					LikeCountArray[i] = AllMessage;

				});
				
				NoteIDXmlNodes.each(function(i) {
					AllMessage = $(this).text();
					NoteIDArray[i] = AllMessage;

				});
				
				//在每次载入的时候,消除所有节点
				for ( var i = 0; i < 100; i++) {
					$("#"+BoardParameter+"Floor"+i).remove();
				}
				

				
				for ( var j = 0; j < countFromServlet; j++) {

		
		CreateDivNodeAppendToAnotherDiv(BoardParameter+"Floor"+j, "NotesFloor","","","","NotesBody");
		CreateDivNodeAppendToAnotherDiv("NotesUserHeadPicture"+j,"NotesUserHeadPicture","","","",BoardParameter+"Floor"+j);
		 
		var imgUrl = "Upload/UserHeadPicture/"+ UserHeadPictureArray[j];
 		
		CreateDivNodeAppendToAnotherDiv("NotesTime"+j,"NotesTime","","","",BoardParameter+"Floor"+j);
		CreateDivNodeAppendToAnotherDiv("NotesContents"+j,"","","","",BoardParameter+"Floor"+j);
		CreateDivNodeAppendToAnotherDiv("ClickLikeForNote"+j,"ClickLikeForNote","","","",BoardParameter+"Floor"+j);

		var ANodeString = "<a onclick='ClickLikeForNote("+NoteIDArray[j]+")' class='ClickLikeForNoteButton'>点赞</a>";
		var ANode =$(ANodeString);
		ANode.appendTo($("#"+BoardParameter+"Floor"+j));
		
		CreateDivNodeAppendToAnotherDiv("LikeCount"+j,"LikeCount","","","",BoardParameter+"Floor"+j);
        CreateImgNodeAppendToAnotherDiv("NotesUserHeadPicture"+j,"NotesUserHeadPicture",imgUrl,"",BoardParameter+"Floor"+j);	

		
		$("#LikeCount"+j).html(LikeCountArray[j]);
		
		
		$("#NotesContents" + j).html(ContentsArray[j]);
		
		$("#NotesTime" + j).html(TimeArray[j]);
		var Line = $("<hr>");
		Line.appendTo($("#"+BoardParameter+"Floor"+j));
			
				
				}

		

					$("#"+BoardParameter+"RowCount").html("共" + RowCount + "条笔记");
					$("#"+BoardParameter+"PageCount").html("共" + PageCount + "页");
					$("#"+BoardParameter+"ShowPage").html("当前是第" + ShowPage + "页");


					JudgeShowSplitPage(RowCount, PageCount, ShowPage,BoardParameter);

				}, "xml");
	
	
	


}
