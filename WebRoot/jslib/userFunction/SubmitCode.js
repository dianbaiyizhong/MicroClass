$(document).ready(function() {
	$(function() {
		$("#rerun").button().click(function() {
			alert("Running the last action");
		}).next().button( {
			text : false,
			icons : {
				primary : "ui-icon-triangle-1-s"
			}
		}).click(function() {
			var menu = $(this).parent().next().show().position( {
				my : "left top",
				at : "left bottom",
				of : this
			});
			$(document).one("click", function() {
				menu.hide();
			});
			return false;
		}).parent().buttonset().next().hide().menu();
	});

});

function SubmitCode(e) {

	var Language = $(e).attr("id");

	var Code = $(".Code").val();



	
	var url =  "userFunction/CompileAndRunJavaFile?Code=" + Code;
	
	
	
	
	
	
	
	
	
	$.post(url, null, function(data) {	
		
		
		var jqueryObj = $(data);

		var StartTimeNodes = jqueryObj.find("StartTime");
		var EndTimeXmlNodes = jqueryObj.find("EndTime");
		var UseTimeXmlNodes = jqueryObj.find("UseTime");
		var UseMemoryXmlNodes = jqueryObj.find("UseMemory");
		var ContentXmlNodes = jqueryObj.find("Content");

		StartTime = $(StartTimeNodes).text();
		EndTime = $(EndTimeXmlNodes).text();
		UseTime = $(UseTimeXmlNodes).text();
		UseMemory = $(UseMemoryXmlNodes).text();
		Content = $(ContentXmlNodes).text();
		
		
		document.getElementById("Result").value = Content;
		
		
		

		
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
}
