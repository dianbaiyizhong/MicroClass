<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="hhm.user.impl.UsersImpl"%>
<%@page import="hhm.user.pojo.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>修改用户信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<%
			HttpSession userSession = request.getSession();
			long l_UserID = 0;
			try {
				l_UserID = (Long) userSession.getAttribute("UserID");
			} catch (Exception e) {

				request.setAttribute("TipsInfo", "用户登录失效");
			    request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(request, response); 
			    return;


			}
		%>

		<%
			UsersImpl usersImpl = new UsersImpl();
			Users users = new Users();
			users.setUserID(l_UserID);
			usersImpl.UserIDToInit(users);
		%>
		
		<input type="text" value="<%=users.getUserName()%>" disabled="disabled"> <br><br>
		
		<input type="text" value="<%=users.getCreateTime()%>" disabled="disabled"> <br><br>
		<input type="text" value="<%=users.getEmail()%>" disabled="disabled"> <br><br>
		
		
		<form action="userFunction/ModifyUserInfoServlet" method="post">
		<input type="password" value="请输入原密码" name="OriginalOldPassword"> <br><br>		
		<input type="hidden" value="<%=users.getUserPassword() %>" name="OriginalPassword">
		<input type="password" value="请输入新密码" name="NewPassword"> <br><br>
		<input type="password" value="确认原密码" name="ConfirmNewPassword"> <br><br>
		<input type="submit" value="提交">
		</form>

	</body>
</html>
