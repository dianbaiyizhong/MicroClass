

var xmlhttp;
function SubmitFloorComment() {

	var Content = $(".SubmitFloorCommentTextarea").attr("value");
	
	var WorkID =$("#CommentWorkID").attr("value");
		
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
		if (xmlhttp.overrideMimeType) {
			xmlhttp.overrideMimeType("text/xml");
		}
	} else if (window.ActiveXObject) {

		var activexName = [ "MSXML2.XMLHTTP", "Microsoft.XMLHTTP" ];
		for ( var i = 0; i < activexName.length; i++) {
			try {
				// 取出一个控件名进行创建，如果创建成功就终止循环
				// 如果创建失败，回抛出异常，然后可以继续循环，继续尝试创建
				xmlhttp = new ActiveXObject(activexName[i]);
				break;
			} catch (e) {
			}
		}
	}
	if (!xmlhttp) {
		alert("XMLHttpRequest对象创建失败!!");
		return;
	} else {
		// alert(xmlhttp.readyState);
	}

	xmlhttp.onreadystatechange = callbackFromSubmitReplyCommentServlet;

	xmlhttp.open("POST", "userFunction/SubmitFloorCommentServlet",
			true);
	xmlhttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");

	xmlhttp.send("Content=" + Content +"&WorkID="+WorkID);

	// xmlhttp.send(null);
}

// 回调函数
function callbackFromSubmitReplyCommentServlet() {

	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			var domObj = xmlhttp.responseXML;
			if (domObj) {
				var messageNodes = domObj.getElementsByTagName("message");
				var textNode = messageNodes[0].firstChild;
				var responseMessage = textNode.nodeValue;
				if (responseMessage == "AddCommentSuccess") {
					alert("评论成功");

				} else {
					alert("评论失败");

				}
			} else {
				alert("XML数据格式错误，原始文本内容为：" + xmlhttp.responseText);
			}
		} else {
			alert("出错了！！！");
		}
	}
}
