<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="hhm.foreground.impl.BoardsImpl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="hhm.foreground.impl.WorksImpl"%>
<%@page import="hhm.splitPage.SplitPage"%>
<%@page import="hhm.foreground.pojo.Pages"%>


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
	
    
	
  
     <script src="jslib/jquery.js"></script>
       
     <script src="jslib/jquery-1.10.2.js"></script>
        <script type="text/javascript">
         var j_1_10_2 = jQuery.noConflict(true);
          </script>
           	     <script src="jslib/General/CreateNodeAppendToAnotherDiv.js"></script>
    
    
      	<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.4/jquery.ui.all.css" />
    
	<script src="jslib/jquery-1.10.4/jquery.ui.core.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.widget.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.mouse.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.sortable.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.accordion.js"></script>
	<script src="jslib/WorkDetail/ChapterAccordion/ChapterAccordion.js"></script>
	<script src="jslib/WorkDetail/ChapterAccordion/LoadChapterByID.js"></script>
	
	  
	<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.4/demos.css" />

  </head>
  
  <body>



<!--------------Content--------------->

<section id="content">

<!--------------Header--------------->

<header> 
	<div class="pagetop">
		<div class="wrap-pagetop">
		
			
		
		</div>
	</div>
	
	<div class="wrap-header">
		<div id="BoardLogo" style="background-image: url('/MicroClass/Upload/UserUploadWorkIndexPictureThumb/<%=BoardRs.getString("IndexPictureUrl") %>');"></div>		
	</div>
</header>







	<div class="zerogrid">		
		<div class="row">
			<div id="main-content" class="col-left">
			
			
			
			
			<!-- 课程抽屉 开始 -->
			  <%
	ResultSet rs = null;
	//long l_BoardID =(Long) request.getAttribute("boardID");
	
	rs = worksImpl.GetWorksByID(Integer.parseInt(s_BoardID));
	HashSet<Integer> List = new HashSet<Integer>();
	while(rs.next()){
		List.add(rs.getInt("Chapter"));
	}
	Iterator<Integer> ChapterIterator=List.iterator();

	ArrayList<Integer> ChapterList = new ArrayList<Integer>();
	while(ChapterIterator.hasNext()){
		ChapterList.add(ChapterIterator.next());
		
	}
	
	%>	
	
	<div id="ChapterAccordion">
	<%for(int i = 0;i<ChapterList.size();i++){
		
	int Chapter = ChapterList.get(i);
	
	%>
	<h3 onclick="LoadChapterByID(<%=Chapter %>,'Chapter','<%=s_BoardID %>')">第<%=Chapter %>章</h3>
	<div id="ChapterFloor<%=Chapter %>">		
	</div>
	<%} %>
	</div>
			
			
			
			
			<!--课程抽屉 结束 -->
			
			
			
			
			
			
			

			</div>
			<div id="sidebar" class="col-right">
	
				<section>
					<div class="heading"><h2><%=BoardRs.getString("BoardName") %>-课程介绍</h2></div>
					<div class="content">
						<p><%=BoardRs.getString("Introduce") %></p>
					</div>
				</section>
				
			<%
			 BoardRs = boardsImpl.GetAllBoards();
			%>
				<section>
					<div class="heading"><h2>分类</h2></div>
					<div class="content">
						<ul>
									<%while(BoardRs.next()){ %>
						<li><a href="foreground/BoardDetail.jsp?BoardID=<%=BoardRs.getString("BoardID") %>"><%=BoardRs.getString("BoardName") %></a></li>
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
