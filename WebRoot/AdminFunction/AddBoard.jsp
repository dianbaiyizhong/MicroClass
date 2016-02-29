<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="hhm.admin.function.impl.BoardsImpl"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <link type="text/css" rel="stylesheet" href="css/AdminFunction/tablecloth.css" />
    
    <title>添加种类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <link type="text/css" rel="stylesheet" href="css/AdminFunction/style.css" />

<script type="text/javascript" src="jslib/jquery.js"></script>
<script type="text/javascript" src="jslib/adminFunction/UpdateDataByID.js"></script>
  </head>
  
  <body>
  
  <%
  BoardsImpl boardsImpl = new BoardsImpl();
  ResultSet rs = null;
  rs =  boardsImpl.GetAllBoards();
  %>
      <form method="post" action="adminFunction/AddBoardServlet" target="_self">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr align="center"> 
        <td width="24%" height="25" class="admintitlecolumn">添加课程</td>
      </tr>
	<tr align="center">
	    <td width="24%" height="25" align="right" nowrap bgcolor="#ECF5FF">课程名称：
	    <input type="text" name="BoardName">
	    </td>		
	</tr>
	  	 <tr width="24%" height="25" align="right" nowrap bgcolor="#ECF5FF">
	    <td>课程介绍：
	     <input type="text" name="Introdunce">
		</td>
	     </tr>
	</table>
	<input type="submit"  name="sub"  value="添加">
	<input type="reset"  name="res" value="重置">        
</form>

<table cellspacing="0" cellpadding="0">
<tr>

<td>课程编号</td>
<td>预览图片</td>
<td>图片设置</td>
<td>课程名称</td>
<td>课程介绍</td>
<td>删除</td>

</tr>
   <%while (rs.next()) {%>
	<tr>
			<td id="BoardID"> <%=rs.getString("BoardID")%> </td>
	
	    <td><img src="Upload/UserUploadWorkIndexPicture/<%=rs.getString("IndexPictureUrl") %>" width="100px"/></td>

		<td>
	    <form method="post" action="adminFunction/UpdateBoardPicServlet" target="_self" enctype="multipart/form-data">
	    大图:<input type="file" value="重新上传图片" name="IndexPicUrl"><br>
	    小图:<input type="file" value="重新上传图片" name="IndexPicUrlThumb"><br>
	    <input type="hidden" name="BoardID" value="<%=rs.getInt("BoardID")%>">
	    <input type="hidden" name="OriginalIndexPicUrl" value="<%=rs.getString("IndexPictureUrl") %>"> 
	    <input type="submit" value="确认上传"><br>
	    </form>
	   </td> 	
		<td id="BoardName" onclick="UpdateDataByID('<%=rs.getInt("BoardID")%>',this,'UpdateBoardServlet')"><%=rs.getString("BoardName") %></td>
		<td id="Introduce" onclick="UpdateDataByID('<%=rs.getInt("BoardID")%>',this,'UpdateBoardServlet')"><%=rs.getString("Introduce") %></td>		 
	   	<td><a href="adminFunction/DeleteBoardServlet?BoardID=<%=rs.getInt("BoardID")%>" >删除</a></td>	
	   
	    <%}%>
	    
	</tr>
</table>
  
  
  
  
  

  </body>
</html>
