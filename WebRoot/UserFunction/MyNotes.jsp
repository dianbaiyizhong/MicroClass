<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="hhm.user.impl.NotesImpl"%>
<%@page import="java.sql.ResultSet"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的笔记</title>
    
    <!-- jquery插件 -->
     <script src="jslib/jquery.js"></script>
     	<link type="text/css" rel="stylesheet" href="css/userFunction/MyNotesAccordion/style.css" />
    
  	<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.4/jquery.ui.all.css" />
        <script src="jslib/jquery-1.10.2.js"></script>
    
	<script src="jslib/jquery-1.10.4/jquery.ui.core.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.widget.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.mouse.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.sortable.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.accordion.js"></script>
	
		<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.4/demos.css" />
	
	<script src="jslib/General/CreateNodeAppendToAnotherDiv.js"></script>

	
      <script src="jslib/userFunction/MyNotesAccordion/MyNotesAccordion.js"></script>
      <script src="jslib/userFunction/MyNotesAccordion/LoadMyNotesByWorkID.js"></script>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
	/* IE has layout issues when sorting (see #5413) */
	.group { zoom: 1 }
	</style>
  

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
	NotesImpl notesImpl = new NotesImpl();
	ResultSet rs = null;
	rs = notesImpl.GetWorkOfMyNotes(l_UserID);
	
	
	%>	
	
	<div id="MyNotesAccordion">
	<%while(rs.next()){ 
	int WorkID = rs.getInt("WorkID");
	%>
	<h3 onclick="LoadMyNotesByWorkID(<%=WorkID %>,'Notes')"><%=rs.getString("WorkTitle") %></h3>
	<div id="MyNotesFloor<%=WorkID %>">		
	</div>
	<%} %>
	</div>
	
	
	
	
	
	

	

	
  
  </body>
</html>
