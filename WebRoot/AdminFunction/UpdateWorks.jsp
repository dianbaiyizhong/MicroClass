<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="hhm.admin.function.impl.WorksImpl"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改作品课程数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link type="text/css" rel="stylesheet" href="css/AdminFunction/tablecloth.css" />

<script type="text/javascript" src="jslib/jquery.js"></script>

<script type="text/javascript" src="jslib/adminFunction/UpdateDataByID.js"></script>
 
  </head>
  
  <body>
    <%
		WorksImpl worksImpl = new WorksImpl();

		ResultSet rs = worksImpl.GetAllWorks();
	%>

<table cellspacing="0" cellpadding="0">
<tr>
<td>图片</td>
<td>标题</td>
<td>介绍</td>
<td>删除</td>
<td>重新上传图片</td>

</tr>


	<%while (rs.next()) {%>
	<tr>
	    <td><img src="Upload/UserUploadWorkIndexPicture/<%=rs.getString("IndexPicUrl") %>" width="100px"/></td>
		<td id="WorkTitle" onclick="UpdateDataByID('<%=rs.getInt("WorkID")%>',this,'UpdateWorksServlet')"><%=rs.getString("WorkTitle") %></td>	
		<%if(rs.getString("Introduce").length()>100){ %>
				<td id="Introduce" onclick="UpdateDataByID('<%=rs.getInt("WorkID")%>',this,'UpdateWorksServlet')"><%=rs.getString("Introduce").substring(0,100) %></td>
		
		<%}else{ %>
			   <td id="Introduce" onclick="UpdateDataByID('<%=rs.getInt("WorkID")%>',this,'UpdateWorksServlet')"><%=rs.getString("Introduce") %></td>
		<%}%>
        <td><a href="adminFunction/DeleteWorksServlet?WorkID=<%=rs.getInt("WorkID")%>" >删除</a></td>	
	       <td>
	    <form method="post" action="/MicroClass/adminFunction/UpdateWorksPicServlet" target="_self" enctype="multipart/form-data">
	    <input type="file" value="重新上传图片" name="IndexPicUrl">
	    <input type="hidden" name="WorkID" value="<%=rs.getInt("WorkID")%>">
	    <input type="hidden" name="OriginalIndexPicUrl" value="<%=rs.getString("IndexPicUrl") %>"> 
	  	<input type="hidden" name="OriginalVideoUrl" value="<%=rs.getString("VideoUrl") %>">      
	    <input type="submit" value="确认上传">
	    </form>
	    </td> 
	    
	    <%}%>
	
	</tr>
	
</table>
  </body>
</html>
