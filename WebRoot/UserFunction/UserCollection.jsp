<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="hhm.user.impl.CollectionsImpl"%>
<%@page import="java.sql.ResultSet"%>
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

		<title>我的收藏</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link type="text/css" rel="stylesheet"
			href="css/userFunction/UserCollection/style.css" />
	</head>

	<body>
		<%
			HttpSession userSession = request.getSession();
			long l_UserID = 0;
			try {
				l_UserID = (Long) userSession.getAttribute("UserID");
			} catch (Exception e) {

				System.out.println("用户登录失效");
				response.sendRedirect("/MicroClass/errorPage/IsNotUser.html");
				return;

			}

			CollectionsImpl collectionsImpl = new CollectionsImpl();
			ResultSet rs = null;
			rs = collectionsImpl.GetAllCollection(l_UserID);
		%>



		<div id="container">

			<table class="zebra">
				<caption>
					我的收藏
				</caption>
				<thead>
					<tr>
						<th>
							图片
						</th>
						<th>
							标题
						</th>
						<th>
							收藏时间
						</th>
						<th>
							访问量
						</th>
						<th>
							评论数
						</th>
						<th>
							取消收藏
						</th>
					</tr>
				</thead>
				<tbody>

					<%
						while (rs.next()) {
					%>
					<tr>
						<td>
							<img
								src="/MicroClass/Upload/UserUploadWorkIndexPicture/<%=rs.getString("IndexPicUrl")%>">
						</td>
						<td><%=rs.getString("WorkTitle")%></td>
						<td><%=rs.getString("CollectionCreateTime")%></td>
						<td><%=rs.getString("ReadCount")%></td>
						<td><%=rs.getString("ReCount")%></td>
						<td>
							<a
								href="userFunction/DeleteUserCollectionServlet?CollectionID=<%=rs.getString("CollectionID")%>">取消收藏</a>
						</td>
					</tr>

					<%
						}
					%>

				</tbody>
			</table>
		</div>





























	</body>
</html>
