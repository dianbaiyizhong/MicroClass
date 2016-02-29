<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>

<link rel=stylesheet type=text/css href="./lib/bbs.css">
<script type="text/javascript" src="jslib/jquery.js"></script>
<script type="text/javascript" src="jslib/userRegister/VerifyIsUserExist.js"></script>

<script type="text/javascript" src="jslib/General/AlertWindow.js"></script>


<link rel=stylesheet type=text/css href="css/General/AlertWindow.css">


        
       
        <link rel="stylesheet" href="JsAndCss/Register/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="JsAndCss/Register/assets/css/style.css">
        
        
        <script src="JsAndCss/Register/assets/js/jquery-1.8.2.min.js"></script>
        <script src="JsAndCss/Register/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="JsAndCss/Register/assets/js/jquery.backstretch.min.js"></script>
        <script src="JsAndCss/Register/assets/js/scripts.js"></script>


</head>

<body>

  

<div id="AlertWindow" align="center">
		 </div>











        <div class="register-container container">
            <div class="row">
                <div class="iphone span5">
                    <img src="JsAndCss/Register/assets/img/iphone.png" alt="">
                </div>
                <div class="register span6">
               <button id="VerifyIsUserExistButton" onclick="verifyIsUserExist()">检测用户名是否可用</button>
                
                    <form action="/MicroClass/userFunction/UserRegisterServlet?status=register" method="post">
                        <h2>微课 <span class="red"><strong>注册</strong></span></h2>
                        <label for="firstname">用户名</label>
                        
                        <input type="text" name="UserName" id ="UserName"  placeholder="请输入你的用户名">
                    
                        <label for="username">密码</label>
                        <input type="password"  name="UserPassword" placeholder="请输入密码">
                      
                        <label for="password">密码确认</label>
                        <input type="password"  name="CUserPassword" placeholder="确认密码">
                          <label for="email">邮箱</label>
                        <input type="text"  name="Email" placeholder="请输入你的邮箱">
                         <label for="Rand">请输入验证码</label>
                         <img src="foreground/identifyingCode.jsp" name="myImage">
                        <input name="Rand">
                        
                        
                        <button type="submit">注册</button>
                    </form>
                </div>
            </div>
        </div>



       















</body>
</html>
