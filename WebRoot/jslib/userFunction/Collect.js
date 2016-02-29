function CollectThisCourse(WorkID) {

	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
		if (xmlhttp.overrideMimeType) {
			xmlhttp.overrideMimeType("text/xml");
		}
	} else if (window.ActiveXObject) {

		var activexName = [ "MSXML2.XMLHTTP", "Microsoft.XMLHTTP" ];
		for ( var i = 0; i < activexName.length; i++) {
			try {
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

	xmlhttp.onreadystatechange = callbackFromCollectThisWorkServlet;

	xmlhttp.open("POST", "/MicroClass/userFunction/CollectThisWorkServlet",
			true);
	xmlhttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");

	xmlhttp.send("WorkID=" + WorkID);

}

// 回调函数
function callbackFromCollectThisWorkServlet() {

	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			var domObj = xmlhttp.responseXML;
			if (domObj) {
				var messageNodes = domObj.getElementsByTagName("message");
				var textNode = messageNodes[0].firstChild;
				var responseMessage = textNode.nodeValue;
				if (responseMessage == "CollectThisWorkSuccess") {

					alert("收藏成功");

				} else if (responseMessage == "isNotLoginUser") {
					alert("请先登录才能收藏");

				} else if (responseMessage == "CollectThisWorkFailure") {
					alert("收藏失败");
				}else if(responseMessage =="UserHaveCollectThisWork"){
					
					alert("您已经收藏过了");

					
				}
			} else {
				alert("XML数据格式错误，原始文本内容为：" + xmlhttp.responseText);
			}
		} else {
			alert("出错了！！！");
		}
	}
}
