/**用户登录验证**/
var xmlhttp;
function verifyUserLogin() {

	var UserName = document.getElementById("LoginUserName").value;
	var Password = document.getElementById("LoginPassWord").value;

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


	xmlhttp.onreadystatechange = callbackFromVerifyUserLoginServlet;


	xmlhttp.open("POST", "foreground/VerifyUserLoginServlet", true);
	xmlhttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");


	xmlhttp.send("UserName=" + UserName + "&Password="
					+ Password);


	// xmlhttp.send(null);
}

// 回调函数
function callbackFromVerifyUserLoginServlet() {

    if (xmlhttp.readyState == 4) {
        if (xmlhttp.status == 200) {
            var domObj = xmlhttp.responseXML;
            if (domObj) {
                var messageNodes = domObj.getElementsByTagName("message");
               
                var textNode = messageNodes[0].firstChild;
                var responseMessage = textNode.nodeValue;

                if(messageNodes.length==2){
                    var textNode1 = messageNodes[1].firstChild;
                    var responseMessage1 = textNode1.nodeValue;

                }

                if (responseMessage == "LoginSucess") {   

                	ShowAlertWindowFromDiv("AlertWindowContent","登录成功");
                	
                	
                	//替换用户头像
                	var UserHeadNode = $(".UserHead");
                	
                	UserHeadNode.attr("style","background-image:url('Upload/UserHeadPicture/"+responseMessage1+"')");

                	//把菜单栏里面的文字改成 注销
                	
                  	var LoginAndLogoutNode = document.getElementById("LoginAndLogout");
                	LoginAndLogoutNode.setAttribute("onclick", "logout()")    		

                	LoginAndLogoutNode.innerHTML="注销";
                	
                	//把登陆窗口隐藏
                    HideWindowByClass('LoginWindow');

                	
                    
                } else if(responseMessage == "LoginFail"){
                	ShowAlertWindowFromDiv("AlertWindowContent","登录失败,用户名错误");
                	
                } else if(responseMessage=="IsMaster"){

        
                    HideWindowByClass('LoginWindow');

//                	
                	alert("管理员登录");
                	//跳转到管理员界面
                	//window.open("/MicroClass/AdminFunction/index.jsp");
                	window.location.href="/MicroClass/AdminFunction/index.jsp";
                	
                }
            } else {
                alert("XML数据格式错误，原始文本内容为：" + xmlhttp.responseText);
            }
        } else {
            alert("出错了！！！");
        }
    }
}



