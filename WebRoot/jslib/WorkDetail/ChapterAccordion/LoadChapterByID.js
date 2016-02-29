function LoadChapterByID(Chapter,BoardParameter,BoardID) {
	var url = "foreground/LoadChapterServlet?Chapter=" + Chapter+"&BoardID="+BoardID;
	$.post(url, null, function(data) {

		var jqueryObj = $(data);

		var WorkTitleNodes = jqueryObj.find("WorkTitle");
		var WorkIDNodes = jqueryObj.find("WorkID");
		var IndexPicUrlNodes = jqueryObj.find("IndexPicUrl");
		

		var countFromServlet = WorkTitleNodes.length;
		
		var WorkTitleArray = new Array(countFromServlet);
		var WorkIDArray = new Array(countFromServlet);
		var IndexPicUrlArray = new Array(countFromServlet);
		
		

		var AllMessage;


		
		WorkTitleNodes.each(function(i) {
			AllMessage = $(this).text();
			WorkTitleArray[i] = AllMessage;

		});
		
		WorkIDNodes.each(function(i) {
			AllMessage = $(this).text();
			WorkIDArray[i] = AllMessage;

		});
		IndexPicUrlNodes.each(function(i) {
			AllMessage = $(this).text();
			IndexPicUrlArray[i] = AllMessage;

		});
		


		
		//在每次载入的时候,消除所有节点
		for ( var i = 0; i < 100; i++) {
			$("#"+BoardParameter+"FloorChapter"+i).remove();
		}
		

		$("."+"ChapterHr").remove();
		
		
		for ( var j = 0; j < countFromServlet; j++) {

CreateDivNodeAppendToAnotherDiv(BoardParameter+"FloorChapter"+j, BoardParameter+"Floor","","","",BoardParameter+"Floor"+Chapter);



//var imgUrl = "Upload/UserUploadWorkIndexPicture/"+ IndexPicUrlArray[j];
//CreateImgNodeAppendToAnotherDiv("ChapterIndexPicUrl"+j, "ChapterIndexPicUrl", imgUrl, "",  BoardParameter+"FloorChapter"+j);

$("<br>").appendTo($("#"+BoardParameter+"FloorChapter"+j));
var ANodeString = "<a href=foreground/LoadWorkDetailServlet?WorkID="+WorkIDArray[j]+"&status=WorkDetail>"+WorkTitleArray[j]+"</a>";
var ANode =$(ANodeString);
ANode.appendTo($("#"+BoardParameter+"FloorChapter"+j));


var Line = $("<hr class='ChapterHr'>");
Line.appendTo($("#"+BoardParameter+"Floor"+Chapter));

	}



			

		}, "xml");

}
