<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="hhm.foreground.impl.WorksImpl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="hhm.foreground.impl.BoardsImpl"%>
<%@page import="hhm.foreground.impl.UsersImpl"%>
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
    
    <title>5iTech我爱技术 微课学习平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="微课，计算机，视频课程">
	<meta http-equiv="description" content="多媒体设计大赛作品">

<script type="text/javascript" src="jslib/jquery.js"></script>


<script type="text/javascript" src="jslib/index/Verify/VerifyUserLogin.js"></script>
<script type="text/javascript" src="jslib/index/Window/Window.js"></script>
<script type="text/javascript" src="jslib/index/LoadSearchResult.js"></script>
<script type="text/javascript" src="jslib/index/TopNav/menu.js"></script>
<script type="text/javascript" src="jslib/index/camera.min.js"></script>
<script type="text/javascript" src="jslib/index/jquery.easing.1.3.js"></script>


<script src="jslib/General/CreateNodeAppendToAnotherDiv.js"></script>
<script src="jslib/General/MouseOverAndOutControlCss.js"></script>
<script  src="jslib/General/JudgeShowSplitPage.js"></script>

<script type="text/javascript" src="jslib/index/Compile.js"></script>

<script type="text/javascript" src="jslib/General/AlertWindow.js"></script>
<script type="text/javascript" src="jslib/userFunction/Logout.js"></script>
<script type="text/javascript" src="jslib/userRegister/VerifyIsUserExist.js"></script>

<link type="text/css" rel="stylesheet" href="css/General/AlertWindow.css" />
<link type="text/css" rel="stylesheet" href="css/Index/SearchWindow.css" />
<link type="text/css" rel="stylesheet" href="css/Index/Works.css" />
<link type="text/css" rel="stylesheet" href="css/Index/TopNav/menu.css" />
<link type="text/css" rel="stylesheet" href="css/Index/Camera/camera.css" />
<link type="text/css" rel="stylesheet" href="css/Index/style.css" />
<link type="text/css" rel="stylesheet" href="css/Index/Window.css" />
<link type="text/css" rel="stylesheet" href="css/Index/SearchWindow.css" />

<link type="text/css" rel="stylesheet" href="css/Index/Compile.css" />

    <script>
		jQuery(function(){
			jQuery('#camera_wrap').camera({
				height: '400px',
				loader: 'bar',
				pagination: false,
				thumbnails: true
			});
		});
	</script>

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
  </head>
  <%
  	HttpSession userSession = request.getSession();
  	long l_UserID = 0;
  	try {
  		l_UserID = (Long) userSession.getAttribute("UserID");
  	} catch (Exception e) {
  		System.out.println("用户登录失效");

  	}
  %>
  
  
  <%
      	BoardsImpl boardsImpl = new BoardsImpl();
      	WorksImpl worksImpl = new WorksImpl();
      	ResultSet Board_rs = null;
      	ResultSet Work_rs = null;

      	Board_rs = boardsImpl.GetAllBoards();
      %>
  
  <script type="text/javascript">
jQuery(document).ready(function(){
	var qcloud={};
	$('[_t_nav]').hover(function(){
		var _nav = $(this).attr('_t_nav');
		clearTimeout( qcloud[ _nav + '_timer' ] );
		qcloud[ _nav + '_timer' ] = setTimeout(function(){
		$('[_t_nav]').each(function(){
		$(this)[ _nav == $(this).attr('_t_nav') ? 'addClass':'removeClass' ]('nav-up-selected');
		});
		$('#'+_nav).stop(true,true).slideDown(200);
		}, 150);
	},function(){
		var _nav = $(this).attr('_t_nav');
		clearTimeout( qcloud[ _nav + '_timer' ] );
		qcloud[ _nav + '_timer' ] = setTimeout(function(){
		$('[_t_nav]').removeClass('nav-up-selected');
		$('#'+_nav).stop(true,true).slideUp(200);
		}, 150);
	});
});
</script>
  <body style="background-color:  #E6E6E6">
  
  
  <section id ="content">
  
     <!-- 顶部导航栏 开始-->
  <div class="head-v3">
  <div class="Logo"></div>
	<div class="navigation-up">
		<div class="navigation-inner">
			<div class="navigation-v3">
				<ul>
					<li class="nav-up-selected-inpage" _t_nav="home">
						<h2>
							<a href="foreground/index.jsp">首页</a>
						</h2>
					</li>
					<li class="" _t_nav="product">
						<h2>
							<a href="">课程精选</a>
						</h2>
					</li>
					<li class="" _t_nav="wechat">
						<h2>
							<a href="foreground/userRegister.jsp">注册</a>
						</h2>
					</li>
					<li class="" _t_nav="cooperate">
						<h2>
						<%if(l_UserID==0){ %>
							<a onclick="ShowWindowByClass('LoginWindow')" id="LoginAndLogout" style="cursor: pointer;">登陆</a>
						<%}else{ %>
						<a onclick="logout()" id="LoginAndLogout">注销</a>

						<%} %>
						</h2>
					</li>				
				</ul>
			</div>
		</div>
	</div>
	<div class="navigation-down">
		<div id="product" class="nav-down-menu menu-1" style="display: none;" _t_nav="product">
			<div class="navigation-down-inner">
			
			<% 
			//得到精品的板块，把他陈列在菜单栏里
			ResultSet GoodBoard_Rs = boardsImpl.GetGoodBoards(); %>
			
			<%while(GoodBoard_Rs.next()){ %>
				<dl style="margin-left: 100px;">
					<dt><%=GoodBoard_Rs.getString("BoardName") %></dt>
					<%int BoardID = GoodBoard_Rs.getInt("BoardID"); 
					ResultSet GoodBoard_Work_Rs = worksImpl.GetWorksByID(BoardID);
					%>
					<%while(GoodBoard_Work_Rs.next()){ %>
					<dd>
						<a href="foreground/LoadWorkDetailServlet?WorkID=<%=GoodBoard_Work_Rs.getInt("WorkID") %>&status=WorkDetail" target="_blank"><%=GoodBoard_Work_Rs.getString("WorkTitle") %></a>
					</dd>
					<%} %>
				</dl>
				
				<%} %>

		
			</div>
		</div>





	</div>

              <div class="ShowSearchWindowButton" onclick="showSearchWindow()"></div>


<!-- 用户功能 开始 -->

              <%if(l_UserID==0) {
              //用户没有登录
               %>
                <div class="UserHead" onmouseover="ShowWindowByClass('UserMenuWindow')" onclick="HideWindowByClass('UserMenuWindow')" style="background: url('picture/Index/TopNav/UserHead.png'); background-size:23px 27px"></div>
               <%}else{                
               //获取用户头像
               UsersImpl usersImpl =  new UsersImpl();
               String UserHeadPic = usersImpl.GetUserHeadPicByID(l_UserID);
               %>               
                <div id="UserHead" class="UserHead" onmouseover="ShowWindowByClass('UserMenuWindow')" onclick="HideWindowByClass('UserMenuWindow')" style="background: url('Upload/UserHeadPicture/<%=UserHeadPic %>');	background-size:23px 27px;"></div>
               <%} %>
               
               
               

         <div class="UserMenuWindow">
          <ul>
          <li class="UserMenuButton" id="UserMenuButton1" onmouseover="AddMouseOverCss('UserMenuButton1','','#e6e6e6')" onmouseout="AddMouseOutCss('UserMenuButton1','','')"><a href="UserFunction/UserCollection.jsp" target="_blank">我的收藏</a></li>
          <li class="UserMenuButton" id="UserMenuButton2" onmouseover="AddMouseOverCss('UserMenuButton2','','#e6e6e6')" onmouseout="AddMouseOutCss('UserMenuButton2','','')"><a href="UserFunction/ModifyUserInfo.jsp" target="_blank">我的设置</a></li>
          <li class="UserMenuButton" id="UserMenuButton3" onmouseover="AddMouseOverCss('UserMenuButton3','','#e6e6e6')" onmouseout="AddMouseOutCss('UserMenuButton3','','')"><a href="UserFunction/MyNotes.jsp" target="_blank">我的笔记</a></li>         
          </ul>
         </div>
         
<!-- 用户功能 结束 -->         
         
         
         
         <!-- 搜索窗口 开始-->		
			 <div id="SearchWindowBoss">

        



          <div id="SearchWindowNav">
         		 <!-- 搜索框 -->
			 <input type="text" id="KeyWord" value="" >
			 
			 <input type="submit" value="搜索" onclick="LoadSearchResult('SearchFirstPage','Search')" class="keywordSubmit" autofocus x-webkit-speech>	
			
			<input type="button" id="SearchWindowDivClose" onclick="hideSearchWindow()" value="关闭" class="HideSearchWindow">
			  <br/>
			 <input type="checkbox"  id="IsSelectBlur" >模糊搜索	 
			                   		<div id="SearchRowCount" ></div>
			                   		
			                   		
			                   		
		         <!-- 搜索分页 -->
 <div id="SearchSplitPage">
 
 <div id="SearchPageCount" style="float: left;"></div>  
 <div id="SearchShowPage"></div>    
 <input type="button" value="首页" onclick="LoadSearchResult('SearchFirstPage','Search')" id="SearchFirstPage" name="SearchFirstPage" class="page" disabled="disabled">
 <input type="button" value="上一页" onclick="LoadSearchResult('SearchPreviousPage','Search')" id="SearchPreviousPage"  name="SearchPreviousPage" class="page" disabled="disabled">
 <input type="button" value="下一页" onclick="LoadSearchResult('SearchNextPage','Search')" id="SearchNextPage"  name="SearchNextPage" class="page" disabled="disabled">
 <input type="button" value="尾页" onclick="LoadSearchResult('SearchLastPage','Search')" id="SearchLastPage" name="SearchLastPage" class="page" disabled="disabled">
 <br>
 <input type="text" value="请输入要跳转的页面数" name="SearchSkipCount" id ="SplitPageInput" />
 <input type="submit" onclick="LoadSearchResult('SearchSkipPage','Search')" class="page" value="跳转">        
 </div>   
   
 <!-- 搜索分页 /-->  	        		 
			 
         </div> 
         
 
          <div id="SearchResultBoss"> 
                        
         </div>  
 </div>
         
  <!-- 搜索窗口 结束-->
         
         
         
         
         
         
         
         
         
         
         
         
         
</div>


   
    <!-- 顶部导航栏 结束-->
  
  
  
  
  
  <!-- 图片滑动 开始 -->
  <%
  	ResultSet SlidePic_rs = boardsImpl.GetAllBoards();
  %>
  	<div class="CameraBoss">
		<div class="camera_wrap camera_magenta_skin" id="camera_wrap">
            
           <%
                       	while (SlidePic_rs.next()) {
                       %> 
            <div data-thumb="Upload/UserUploadWorkIndexPictureThumb/<%=SlidePic_rs.getString("IndexPictureUrl")%>"  data-src="Upload/UserUploadWorkIndexPicture/<%=SlidePic_rs.getString("IndexPictureUrl")%>">
                <div class="camera_caption fadeFromBottom">
               <%=SlidePic_rs.getString("BoardName")%> <em> <%=SlidePic_rs.getString("Introduce")%></em>
            </div>  
            </div> 
            <%
             	}
             %>                   
        </div>
		<div style="clear:both; display:block; height:10px"></div>
	</div>
  
  <!-- 图片滑动 结束 -->





		<%
			ResultSet Boards_rs = boardsImpl.GetAllBoards();
		%>
		<%
			while (Boards_rs.next()) {
				//获取板块的ID
				int BoardID = Boards_rs.getInt("BoardID");
		%>

		<%
			ResultSet Works_rs = worksImpl.GetWorksByID(BoardID);
		%>
		
		<div class="BoardShowBoss">
			   <div class="BoardShow">
					<!-- 首先第一张是该板块的大图,放在第一个位置 -->
			    <article>
				<a href="foreground/BoardDetail.jsp?BoardID=<%=BoardID %>"><img src="Upload/UserUploadWorkIndexPicture/<%=Boards_rs.getString("IndexPictureUrl")%>"
					class="grayscale"/></a>
				<h2 class="Boardh2"><%=Boards_rs.getString("BoardName") %></h2>
				
				<%
				//获取板块的信息，如播放数和评论数
				int Board_ReCount = boardsImpl.GetBoardReCountByID(BoardID);
				int Board_ReadCount = boardsImpl.GetBoardReadCountByID(BoardID);

				%>
				<div class="Board_ReCount"><p><%=Board_ReCount %></p></div>
				<div class="Board_ReadCount"><p><%=Board_ReadCount %></p></div>
					
				</article>
			    </div>
				<div class="WorkShowBoss">
				<%
				   int WorkCount = 0;
					while (Works_rs.next()) {
						WorkCount++;
						if(WorkCount>4)
						{
							continue;
					     }
				%>
				
				<div class="WorkShow">
					<article>
					<a href="foreground/LoadWorkDetailServlet?WorkID=<%=Works_rs.getInt("WorkID") %>&status=WorkDetail" target="_blank"><img src="Upload/UserUploadWorkIndexPicture/<%=Works_rs.getString("IndexPicUrl")%>"
						class="grayscale" /></a>
					<h2><a href="foreground/LoadWorkDetailServlet?WorkID=<%=Works_rs.getInt("WorkID") %>&status=WorkDetail" target="_blank"><%=Works_rs.getString("WorkTitle")%></a></h2>
					<div class="Work_ReCount"><p><%=Works_rs.getInt("ReCount") %></p></div>	<div class="Work_ReadCount"><p><%=Works_rs.getInt("ReadCount") %></p></div>
					</article>
				</div>
				
			<%
				}
			%>
			</div>
		</div>
			
			 
			 
			
		<%
			}
		%>

























  <!-- 登录窗口 开始-->
  <div class="LoginWindow">
  <div class="LoginWindowTop"><span>登陆</span><a onclick="HideWindowByClass('LoginWindow')">X</a></div>
  
  
  <div class="UserNameInput">
  <input type="text" id="LoginUserName" >
  </div>
  
    
  <div class="PassWordInput" >
  <input type="password" id="LoginPassWord">
  </div>
  <input type="button" id="LoginButton" onclick="verifyUserLogin()" value="登录">
  <input type="button" id="RegisterButton" value="注册"  onclick="window.open('foreground/userRegister.jsp')" >
  
  </div>
  <!-- 登录窗口 结束-->
    
    
  <!-- 注册窗口 开始 -->  
     <div class="RegisterWindow">
     
     
  <div class="RegisterWindowTop"><span>注册</span><a onclick="HideWindowByClass('RegisterWindow')">X</a></div>
  
 <form name="registerForm" method="post" action="/MicroClass/userFunction/UserRegisterServlet?status=register">
  
  <div class="UserNameInput">
<input id="UserName" type="text" name="UserName" >
  </div>
  
    
  <div class="PassWordInput" >
 <input type="password" name="UserPassword">
  </div>
  
    <div class="PassWordInput" >
  <input type="password" name="CUserPassword">
  </div>
  
  <input type="button" id="VerifyIsUserExistButton" onclick="verifyIsUserExist()" value="检测用户名是否可用"><br/>
  
  <input type="button" id="RegisterCheckButton" value="注册"  onclick="window.open('foreground/userRegister.jsp')" >
  
  </form>
  </div> 
    
    
    
    
    
   <!-- 注册窗口 结束 -->  
    
    
    
<!-- 警告窗口 开始-->
<div id="AlertWindow" align="center"></div>
<!-- 警告窗口 结束-->




  <div id="foot">
  <span> Copyright © 2014
  
  </span>
  <br>
    <div class="span2"> 
  5iTech我爱技术微课学习平台版权所有2014-2015  
  </div>
  </div>

  
  </section>
  







  <!-- 编译窗口  -->
  <div id="CompileWindow" onmouseover=toBig() onmouseout=toSmall()>
  
    
<TABLE style="FLOAT: left" border=0 cellSpacing=0 cellPadding=0 width=400>
  <TBODY>
  <TR>
    <TD class=main_head height=39 vAlign=top>&nbsp;</TD></TR>
  <TR>
    <TD class=info vAlign=top>
    <iframe src="foreground/Compile.jsp" align="middle" hspace="-100px" vspace="-150px"
			frameborder="0" height="450" scrolling="no" width="815">
    </iframe>
    </TD>
  </TR>

  </TBODY></TABLE><DIV class=Obtn></DIV>
</DIV>
   <!-- 编译窗口 -->
 
  
  
  

 
  
  
  
  
  
  
  
  </body>
  </html>
