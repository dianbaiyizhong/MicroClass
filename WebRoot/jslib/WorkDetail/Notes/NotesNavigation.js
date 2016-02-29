$(function() {
	var speed = 200;
	$('#NotesNavigationAll>li>a').mouseup(function() {
		// $(this).find('ul').stop(false, true).slideUp(speed);
			// $(this).find('ul').stop(false, true).slideDown(speed);
			var li_a_id = $(this).attr("id");
			if (li_a_id == "NewlyNotes") {
				$("#NewlyNotes").attr("style", "color:white");
				$("#MyNotes").attr("style", "color:#AA8977");
				$("#GoodNotes").attr("style", "color:#AA8977");

				$(".NotesShowType").attr("id", li_a_id);
				LoadNotes("", "Notes");

			} else if (li_a_id == "MyNotes") {

				if (isUserLogin()) {
					$("#MyNotes").attr("style", "color:white");
					$("#GoodNotes").attr("style", "color:#AA8977");
					$("#NewlyNotes").attr("style", "color:#AA8977");
					$(".NotesShowType").attr("id", li_a_id);
					LoadNotes("", "Notes");
				}else{
					
				ShowAlertWindowFromDiv("AlertWindowContent","你还没登陆,不能查看自己的笔记");	
				}

			} else if (li_a_id == "GoodNotes") {
				$("#NewlyNotes").attr("style", "color:#AA8977");
				$("#MyNotes").attr("style", "color:#AA8977");
				$("#GoodNotes").attr("style", "color:white");
				$(".NotesShowType").attr("id", li_a_id);
				LoadNotes("", "Notes");

			}

		});
});