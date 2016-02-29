
var xmlhttp;
function verifyIsUserExist() {
	
	var userName = document.getElementById("UserName").value;
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

	
	xmlhttp.onreadystatechange = callbackFromUserRegisterServlet;

	
	xmlhttp.open("POST", "/MicroClass/userFunction/UserRegisterServlet", true);
	xmlhttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlhttp.send("userName=" + userName);


	// xmlhttp.send(null);
}

// 回调函数
function callbackFromUserRegisterServlet() {

	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			var domObj = xmlhttp.responseXML;
			if (domObj) {
	
				var messageNodes = domObj.getElementsByTagName("message");
				if (messageNodes.length > 0) {
					
					var textNode = messageNodes[0].firstChild;
					var responseMessage = textNode.nodeValue;
                    ShowAlertWindowFromDiv("AlertWindowContent",responseMessage);

					
//					var divNode = document.getElementById("result");
//					divNode.innerHTML = responseMessage;
				} else {
					alert("XML数据格式错误，原始文本内容为：" + xmlhttp.responseText);
				}
			} else {
				alert("XML数据格式错误，原始文本内容为：" + xmlhttp.responseText);
			}
		} else {
			alert("出错了！！！");
		}
	}
}
