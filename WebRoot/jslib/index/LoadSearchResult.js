

function LoadSearchResult(PageParameter,BoardParameter) {

	
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


	var IsSelectBlur = $("#IsSelectBlur").attr("checked");

	var KeyWord = document.getElementById("KeyWord").value;
	// 先取得前台的节点
	var SearchResultDivNode = $("#SearchWindowDiv");

	var url = "";
	if(IsSelectBlur==false){
	 url = "foreground/LoadSearchResultByLuceneServlet?KeyWord=" + KeyWord + "&page="
			+ page;
	}
	else{
		 url = "foreground/LoadSearchResultByLikeServlet?KeyWord=" + KeyWord + "&page="
		+ page;	
		
	}
	
	
	
	$.post(url, null, function(data) {


				var jqueryObj = $(data).find("SearchResult");
				var BoardIDXmlNodes = jqueryObj.find("BoardID");
				var WorkIDXmlNodes = jqueryObj.find("WorkID");
				var WorkContentXmlNodes = jqueryObj.find("WorkContent");
				var WorkTitleXmlNodes = jqueryObj.find("WorkTitle");
				var IndexPicNodes = jqueryObj.find("IndexPic");
				var ReCountNodes = jqueryObj.find("ReCount");
				var ReadCountNodes = jqueryObj.find("ReadCount");



				var RowCountXmlNodes = jqueryObj.find("RowCount");
				var PageCountXmlNodes = jqueryObj.find("PageCount");
				var ShowPageXmlNodes = jqueryObj.find("ShowPage");

				// 从服务器端传回来的用户回复内容数据数量<=5
					var countFromServlet = WorkIDXmlNodes.length;

					var BoardIDArray = new Array(countFromServlet);
					var WorkIDArray = new Array(countFromServlet);
					var WorkContentArray = new Array(countFromServlet);
					var WorkTitleArray = new Array(countFromServlet);
					var IndexPicArray = new Array(countFromServlet);
					var ReCountArray = new Array(countFromServlet);
					var ReadCountArray = new Array(countFromServlet);

					RowCount = $(RowCountXmlNodes).text();
					PageCount = $(PageCountXmlNodes).text();
					ShowPage = $(ShowPageXmlNodes).text();

					var SearchResultMessage;

					BoardIDXmlNodes.each(function(i) {
						// 除了火狐外的浏览器都不支持.html（）方法
							 //SearchResultMessage = $(this).text();//这个问题日后再说
						SearchResultMessage = $(this).text();
							BoardIDArray[i] = SearchResultMessage;

						});

					WorkIDXmlNodes.each(function(i) {
						SearchResultMessage = $(this).text();
						WorkIDArray[i] = SearchResultMessage;

					});

					WorkContentXmlNodes.each(function(i) {
						SearchResultMessage = $(this).text();
						WorkContentArray[i] = SearchResultMessage;

					});

					WorkTitleXmlNodes.each(function(i) {
						SearchResultMessage = $(this).text();
						WorkTitleArray[i] = SearchResultMessage;

					});
					IndexPicNodes.each(function(i) {
						SearchResultMessage = $(this).text();
						IndexPicArray[i] = SearchResultMessage;

					});
					
					ReadCountNodes.each(function(i) {
						SearchResultMessage = $(this).text();
						ReadCountArray[i] = SearchResultMessage;

					});
					
					ReCountNodes.each(function(i) {
						SearchResultMessage = $(this).text();
						ReCountArray[i] = SearchResultMessage;

					});
					
					//在每次载入的时候,消除所有节点
					for ( var i = 0; i < 5; i++) {
						$("#"+BoardParameter+"Floor"+i).remove();
					}
					
					for ( var j = 0; j < countFromServlet; j++) {
						CreateDivNodeAppendToAnotherDiv(BoardParameter+"Floor"+j, BoardParameter+"Floor", "GoWorkDetailFromSearch("+WorkIDArray[j]+")", "AddMouseOverCss('"+BoardParameter+'Floor'+j+"','','#aaa5a5')","AddMouseOutCss('"+BoardParameter+'Floor'+j+"','','')", "SearchResultBoss");
						var imgUrl = "Upload/UserUploadWorkIndexPicture/"+ IndexPicArray[j];
						CreateImgNodeAppendToAnotherDiv("SearchWorkPic"+j, "SearchWorkPic", imgUrl, "", BoardParameter+"Floor"+j);
						CreateDivNodeAppendToAnotherDiv("SearchWorkContents"+j,"SearchWorkContents","","","",BoardParameter+"Floor"+j);
						CreateDivNodeAppendToAnotherDiv("SearchPicDiv"+j, "SearchPicDiv", "", "","", BoardParameter+"Floor"+j);

					
						CreateDivNodeAppendToAnotherDiv("SearchWorkInfo"+j, "SearchWorkInfo", "", "","", BoardParameter+"Floor"+j);
						
						CreateSpanNodeAppendToAnotherDiv("span_ReCount"+j, "span_ReCount", "", "","", "SearchWorkInfo"+j);
						CreateSpanNodeAppendToAnotherDiv("span_ReadCount"+j, "span_ReadCount", "", "","", "SearchWorkInfo"+j);
                        $("#span_ReCount"+j).html("<p>"+ReCountArray[j]+"</p>");
						
                        $("#span_ReadCount"+j).html("<p>"+ReadCountArray[j]+"</p>");

						$("#SearchWorkContents"+j).html(WorkTitleArray[j]+"_______"+WorkContentArray[j]);

					}

					
					
					$("#"+BoardParameter+"RowCount").html("共" + RowCount + "条结果");
					$("#"+BoardParameter+"PageCount").html("共" + PageCount + "页 ,");
					$("#"+BoardParameter+"ShowPage").html("当前是第" + ShowPage + "页");

	
					
					JudgeShowSplitPage(RowCount, PageCount, ShowPage,BoardParameter);

				}, "xml");

}






function GoWorkDetailFromSearch(e) {

//	var WorkID =  $(e).children(".SearchWorkID").text();
	window.open("foreground/LoadWorkDetailServlet?WorkID="+e+"&status=WorkDetail");

}
