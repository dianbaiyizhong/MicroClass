
function SubmitNotes() {
		var NotesContents =  document.getElementById("NotesContent").value;
		var VideoTime = $("#VideoTime").text();
		
		if(VideoTime==""){
			alert("请先播放视频");
			return;
			
		}
		
		var IsOpen  = $("#IsOpen").attr("checked");

		var WorkID = $("#NotesWorkID").val();
		
	var statu = ""; 
    $.ajax({type:"post",
                url:"userFunction/SubmitNotesServlet?NotesContents="+NotesContents+"&VideoTime="+VideoTime+"&IsOpen="+IsOpen+"&WorkID="+WorkID,
                dataType:"text/xml",
                async:false,               
                success:function(data) {    	
                    if (data == "SubmitNotesSuccess") {
              
                    	
        				LoadNotes("","Notes");

                    	//再次读取笔记
                    	statu = true;
                    } else if(data == "SubmitNotesFail") {
                    	alert("笔记记录失败");

                    	statu = false;
                    }
                }
            }
    )

    return statu;

}