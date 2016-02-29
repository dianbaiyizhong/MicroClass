<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="hhm.foreground.impl.BoardsImpl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="hhm.foreground.impl.WorksImpl"%>
<%@page import="hhm.splitPage.SplitPage"%>
<%@page import="hhm.foreground.pojo.Pages"%>
<%
//展示方式
String ShowType = "Default";


String str_ShowType = request.getParameter("ShowType");

if(str_ShowType!=null){
	ShowType = str_ShowType;
}

%>

    <%
   String s_BoardID = request.getParameter("BoardID");
   int i_BoardID = Integer.parseInt(s_BoardID);
  
    %>

	
	<%
	        WorksImpl worksImpl = new WorksImpl();
			BoardsImpl boardsImpl = new BoardsImpl();
			ResultSet BoardRs = boardsImpl.GetBoardByID(i_BoardID);
			BoardRs.first();
			
     %>
     
     <%

ResultSet WorkRs = null; 
	
 if(ShowType.equals("Default")){
	 WorkRs = worksImpl.GetAllNewlyWorksByBoardID(i_BoardID);

 }else if(ShowType.equals("ReCount")){
	 WorkRs = worksImpl.GetAllWorksByIDOfReadCount(i_BoardID);

 }else if(ShowType.equals("ReadCount")){
	 WorkRs = worksImpl.GetAllWorksByIDOfReCount(i_BoardID);
 }
     
SplitPage splitPage = new SplitPage();
splitPage.initialize(WorkRs, 5);
int showPage = 1;

String str_page = request.getParameter("page");

if (str_page == null) {
	showPage = 1;
} else {
	try {
		showPage = Integer.parseInt(str_page);

	} catch (NumberFormatException e) {
		showPage = 1;

	}
	if (showPage < 1) {
		showPage = 1;

	}
	if (showPage > splitPage.getPageCount()) {
		showPage = splitPage.getPageCount();

	}

}

Vector vData = splitPage.getPage(showPage);


%>



	
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>




<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
  <head>
    <base href="<%=basePath%>">
    
    <title><%=BoardRs.getString("BoardName") %></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <!-- Mobile Specific Metas
  ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    
    <!-- CSS
  ================================================== -->
	<link rel="stylesheet" href="css/WorkDetail/style.css">
    <link rel="stylesheet" href="css/WorkDetail/responsive.css">
	
	<!--[if lt IE 8]>
       <div style=' clear: both; text-align:center; position: relative;'>
         <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
           <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
      </div>
    <![endif]-->
    <!--[if lt IE 9]>
		<script src="js/html5.js"></script>
		<script src="js/css3-mediaqueries.js"></script>
	<![endif]-->
	
    
	<script type='text/javascript' src='jslib/jquery-1.8.3.min.js'></script>
	<script type='text/javascript' src='jslib/jquery.easing.1.3.js'></script> 
    

  </head>
  
  <body>



<!--------------Content--------------->

<section id="content">

<!--------------Header--------------->

<header> 
	<div class="pagetop">
		<div class="wrap-pagetop">
		
			
			<div id="search">
				<div class="button-search"></div>
				<input type="text" value="Search..." onfocus="if (this.value == &#39;Search...&#39;) {this.value = &#39;&#39;;}" onblur="if (this.value == &#39;&#39;) {this.value = &#39;Search...&#39;;}">
			</div>
		</div>
	</div>
	<div class="wrap-header">
		<div id="logo"><a href="#"><img src="./images/logo.png"/></a></div>
		<nav>
			<ul>
				<li><a href="foreground/index.jsp">主页</a></li>
				
				<%if(ShowType.equals("ReCount")){ %>
				<li class="current"><a href="foreground/MoreWorks.jsp?TypeID=<%=i_BoardID %>&ShowType=ReCount">按评论数排序</a></li>
		        <%}else{ %>
		        
				<li><a href="foreground/MoreWorks.jsp?BoardID=<%=i_BoardID %>&ShowType=ReCount">按评论数排序</a></li>
		        
		        <%} %>
		        <%if(ShowType.equals("ReadCount")){ %>
				
				<li class="current"><a href="MoreWorks.jsp?BoardID=<%=i_BoardID %>&ShowType=ReadCount">按访问量排序</a></li>
				
				<%}else{ %>
				<li><a href="foreground/MoreWorks.jsp?BoardID=<%=i_BoardID %>&ShowType=ReadCount">按访问量排序</a></li>
				<%} %>
				
				
				
				<%if(ShowType.equals("Default")){ %>
				<li class="current"><a href="foreground/MoreWorks.jsp?BoardID=<%=i_BoardID %>">默认</a></li>
				<%}else{ %>
				<li><a href="foreground/MoreWorks.jsp?BoardID=<%=i_BoardID %>">默认</a></li>
				
				<%} %>
			</ul>
		</nav>
	</div>
</header>







	<div class="zerogrid">		
		<div class="row">
			<div id="main-content" class="col-left">
			<%for (int j = 0; j < vData.size(); j++) {
				String[] sData = (String[]) vData.get(j); 
				
				String WorkPic = sData[6];
				String Url = "";
				if(WorkPic.startsWith("http")){
					Url = WorkPic.replaceAll(" ","");
					
				}else{
					Url ="Upload/UserUploadWorkIndexPicture/"+WorkPic;
				}
				
				
				%>
				<article>
					<div class="heading">
						<h2><%=sData[2] %></h2>
						<div class="info">>>> 访问量:<%=sData[5] %> | 评论数:<%=sData[4] %> | </div>
					</div>
					<div class="content">
						<img src="<%=Url %>"/>
						<p> <%=sData[3] %></p>
					</div>
				</article>
	             <%} %>		
				<section>
					<ul id="pagi">
					<%for(int i = 1;i<=splitPage.getPageCount();i++){ %>
					<%if(i==showPage){ %>
						<li><a class="current" href="#"><%=i %></a></li>
						<%} else{%>
					    <li><a href="foreground/MoreWorks.jsp?BoardID=<%=i_BoardID %>&page=<%=i %>&ShowType=<%=ShowType %>"><%=i %></a></li>
						<%} %>
						<%} %>
						
						
						<%if(showPage!=splitPage.getPageCount()) {%>
						 <li><a href="foreground/MoreWorks.jsp?BoardID=<%=i_BoardID %>&page=<%=showPage+1 %>&ShowType=<%=ShowType %>">下一页</a></li>
						<%}%>
						
					</ul>
				</section>
			</div>
			<div id="sidebar" class="col-right">
	
				<section>
					<div class="heading"><h2><%=BoardRs.getString("BoardName") %>-品种介绍</h2></div>
					<div class="content">
						<a href="#" target="_blank"><img src="images/zerotheme.png" style="border:none;"/></a>
						<p><%=BoardRs.getString("Introduce") %></p>
					</div>
				</section>
				
			<%
			 BoardRs = boardsImpl.GetAllBoards();
			%>
				<section>
					<div class="heading"><h2>种类</h2></div>
					<div class="content">
						<ul>
									<%while(BoardRs.next()){ %>
						<li><a href="foreground/MoreWorks.jsp?BoardID=<%=BoardRs.getString("BoardID") %>"><%=BoardRs.getString("BoardName") %></a></li>
						<%} %>
						</ul>
					</div>
				</section>				
			</div>
		</div>
	</div>
</section>
 
 </body>
</html>
