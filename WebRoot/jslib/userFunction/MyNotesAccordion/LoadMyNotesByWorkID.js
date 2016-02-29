function LoadMyNotesByWorkID(WorkID,BoardParameter) {
	var url = "userFunction/LoadMyNotesByWorkIDServlet?WorkID=" + WorkID;
	$.post(url, null, function(data) {

		var jqueryObj = $(data);

		var TimeXmlNodes = jqueryObj.find("Time");
		var CreateTimeXmlNodes = jqueryObj.find("CreateTime");
		var ContentsXmlNodes = jqueryObj.find("Contents");
		var LikeCountXmlNodes = jqueryObj.find("LikeCount");
		var NoteIDXmlNodes = jqueryObj.find("NoteID");
		var IndexPicUrlXmlNodes = jqueryObj.find("IndexPicUrl");

		var countFromServlet = TimeXmlNodes.length;
		
		var TimeArray = new Array(countFromServlet);
		var CreateTimeArray = new Array(countFromServlet);
		var ContentsArray = new Array(countFromServlet);
		var LikeCountArray = new Array(countFromServlet);
		var NoteIDArray = new Array(countFromServlet);
		
		
		var  IndexPicUrl = $(IndexPicUrlXmlNodes).text();

		var AllMessage;


		
		CreateTimeXmlNodes.each(function(i) {
			AllMessage = $(this).text();
			CreateTimeArray[i] = AllMessage;

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
		

		
		$(".WorkIndexPic").remove();
		
		var imgUrl = "Upload/UserUploadWorkIndexPicture/"+ IndexPicUrl;
		CreateImgNodeAppendToAnotherDiv("WorkPic"+j, "WorkIndexPic", imgUrl, "", "MyNotesFloor"+WorkID);
		
		for ( var j = 0; j < countFromServlet; j++) {


CreateDivNodeAppendToAnotherDiv(BoardParameter+"Floor"+j, "NotesFloor","","","","MyNotesFloor"+WorkID);



CreateDivNodeAppendToAnotherDiv("NotesTime"+j,"NotesTime","","","",BoardParameter+"Floor"+j);
CreateDivNodeAppendToAnotherDiv("NotesContents"+j,"","","","",BoardParameter+"Floor"+j);

//var ANodeString = "<a onclick='ClickLikeForNote("+NoteIDArray[j]+")' class='ClickLikeForNoteButton'>编辑</a>";
//var ANode =$(ANodeString);
//ANode.appendTo($("#"+BoardParameter+"Floor"+j));

CreateDivNodeAppendToAnotherDiv("CreateTime"+j, "CreateTime", "", "", "", BoardParameter+"Floor"+j);
CreateDivNodeAppendToAnotherDiv("LikeCount"+j,"","","","",BoardParameter+"Floor"+j);
$("#LikeCount"+j).html("赞:"+LikeCountArray[j]);

var Line = $("<hr>");
Line.appendTo($("#"+BoardParameter+"Floor"+j));

$("#NotesContents" + j).html(ContentsArray[j]);
$("#NotesTime" + j).html(TimeArray[j]);
$("#CreateTime" + j).html(CreateTimeArray[j]);
	}



			

		}, "xml");

}
