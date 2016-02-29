<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="hhm.foreground.impl.BoardsImpl"%>
<%@page import="hhm.foreground.impl.WorksImpl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="hhm.foreground.impl.UsersImpl"%>
<%@page import="hhm.foreground.impl.CommentsImpl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${requestScope.works.workTitle}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
          <script src="jslib/jquery.js"></script>
       
     <script src="jslib/jquery-1.10.2.js"></script>
        <script type="text/javascript">
         var j_1_10_2 = jQuery.noConflict(true);
          </script>
     <script src="jslib/WorkDetail/Notes/SubmitNotes.js"></script>
     <script src="jslib/WorkDetail/Notes/LoadNotes.js"></script>
     <script src="jslib/WorkDetail/Comments/LoadComments.js"></script>
     <script src="jslib/WorkDetail/Comments/LoadCommentsReply.js"></script>
     <script src="jslib/WorkDetail/Comments/SubmitComment.js"></script>

     <script src="jslib/WorkDetail/Window/Window.js"></script>
     <script src="jslib/General/CreateNodeAppendToAnotherDiv.js"></script>
     <script src="jslib/General/ClickLike.js"></script>
     <script src="jslib/userFunction/Collect.js"></script>
     
  <script type="text/javascript" src="jslib/index/Verify/VerifyUserLogin.js"></script>
<script type="text/javascript" src="jslib/index/Window/Window.js"></script>
<script type="text/javascript" src="jslib/index/LoadSearchResult.js"></script>
<script type="text/javascript" src="jslib/index/TopNav/menu.js"></script>
<script src="jslib/General/MouseOverAndOutControlCss.js"></script>
     
     <script src="jslib/General/JudgeShowSplitPage.js"></script>
     <script src="jslib/General/VerifyIsUserLogin.js"></script>
     <script src="jslib/General/AlertWindow.js"></script>
    
 <link href="css/WorkDetail/Notes/NotesNavigation.css" rel="stylesheet" type="text/css" />

<script src="jslib/General/MouseOverAndOutControlCss.js"></script>
<script  src="jslib/General/JudgeShowSplitPage.js"></script>
 <script src="jslib/WorkDetail/Notes/NotesNavigation.js" type="text/javascript"></script>
 <script type="text/javascript" src="jslib/userFunction/Logout.js"></script>
     
     
     
     
     
       	<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.4/jquery.ui.all.css" />
    
	<script src="jslib/jquery-1.10.4/jquery.ui.core.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.widget.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.mouse.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.sortable.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.accordion.js"></script>
	<script src="jslib/WorkDetail/ChapterAccordion/ChapterAccordion.js"></script>
	<script src="jslib/WorkDetail/ChapterAccordion/LoadChapterByID.js"></script>
	
	<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.4/demos.css" />
	
     
     
     
       <script src="jslib/WorkDetail/Comments/SubmitFloorComment.js"></script>
     
     
     
     
     


<!-- 视频播放器 -->
<script src="jslib/WorkDetail/VideoPlayer/mediaelement-and-player.min.js"></script>
<script src="jslib/WorkDetail/VideoPlayer/VideoPlayer.js"></script>
<link rel="stylesheet" href="css/WorkDetail/VideoPlayer/VideoPlayerStyle.css" media="screen">
<!-- /视频播放器 -->

<link rel="stylesheet" href="css/WorkDetail/Notes/NotesWindow.css" media="screen">
<link rel="stylesheet" href="css/WorkDetail/Comments.css">
<link rel="stylesheet" href="css/WorkDetail/Window.css">
<link type="text/css" rel="stylesheet" href="css/Index/Window.css" />
<link type="text/css" rel="stylesheet" href="css/Index/SearchWindow.css" />
<link rel="stylesheet" href="css/WorkDetail/WorkDetail.css">


 <link href="css/WorkDetail/style.css" rel="stylesheet" type="text/css" />
  
     <link type="text/css" rel="stylesheet" href="css/Index/TopNav/menu.css" />
     <link type="text/css" rel="stylesheet" href="css/General/AlertWindow.css" />
<link type="text/css" rel="stylesheet" href="css/WorkDetail/TopNav/menu.css" />
 <script type="text/javascript" src="jslib/index/Compile.js"></script>
  <link type="text/css" rel="stylesheet" href="css/Index/Compile.css" />
  
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
  
  
  <body style="background-color: white">
  
  <section id="content">
   		
     <!-- 顶部导航栏 开始-->
  <div class="head-v3">
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
							<a onclick="ShowWindowByClass('LoginWindow')" id="LoginAndLogout">登陆</a>
						<%}else{ %>
						<a onclick="logout()" id="LoginAndLogout">注销</a>

						<%} %>
						</h2>
					</li>
					
							<li class="" _t_nav="weschat">
						<h2>
			<a onclick="CollectThisCourse(${requestScope.works.workID})">收藏该课程</a>

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
   		

	
   		
   		
   		
   	<div class="VideoAndNote">	
   	   	<!-- 课程小标题 -->	
   	   	<div class="WorkTitleBoss">
   	   	   	<p>${requestScope.works.workTitle}</p>
   	</div>
   		<!-- 视频播放器 开始 -->
   		
   		<div id="Video" align="center" >
 			<video width="600" height="400" poster="Upload/UserUploadWorkIndexPicture/${requestScope.works.indexPicUrl}" id="player1" controls="controls">
            <source src="Upload/UserUploadWorkVideo/${requestScope.works.videoUrl}" type="video/mp4">
	        </video>		        
	    <span id="VideoTime" style="display: none"></span>     				
        </div>
       
       <!-- 视频播放器 结束 -->
 



 
 
 <div id="NotesBoss">
    <div class="NotesNavigationBoss">
				<ul id="NotesNavigationAll">
					<li>
       <a  id="NewlyNotes">最新笔记</a>
					</li>
				    <li>
       <a  id="MyNotes">我的笔记</a>
					</li>		
					<li>
       <a  id="GoodNotes">最优笔记</a>
					</li>
                </ul> 
         </div>
       
       
       
     
     
       
           <!-- 笔记显示窗口 开始 -->
       <div id ="NotesWindowBoss">
       
       <div id="NotesBody">
       
       </div>
       
       
        <!-- 提交日记 开始 -->
        
        <div class="SubmitNoteWindow">
       <input type="text" id="NotesContent">
              <input type="button" value="提交笔记" onclick="SubmitNotes()">
       
                  是否公开<input type="checkbox" id="IsOpen">
       </div>
       <!-- 提交日记 结束 -->
       
       <input type="hidden" value=${requestScope.works.workID} id="NotesWorkID">
       <input type="hidden" id="NewlyNotes" class="NotesShowType">
       
       <!-- 笔记内容 开始 -->      

 <!-- 笔记内容分页 开始 -->
 <div id="NotesSplitPage" align="center">
 <span id="NotesRowCount"></span>
 <span id="NotesPageCount"></span>  
 <span id="NotesShowPage"></span> <br>   
 <input type="button" value="首页" onclick="LoadNotes('NotesFirstPage','Notes')" id="NotesFirstPage" name="NotesFirstPage" class="page" disabled="disabled">
 <input type="button" value="上一页" onclick="LoadNotes('NotesPreviousPage','Notes')" id="NotesPreviousPage"  name="NotesPreviousPage" class="page" disabled="disabled">
 <input type="button" value="下一页" onclick="LoadNotes('NotesNextPage','Notes')" id="NotesNextPage"  name="NotesNextPage" class="page" disabled="disabled">
 <input type="button" value="尾页" onclick="LoadNotes('NotesLastPage','Notes')" id="NotesLastPage" name="NotesLastPage" class="page" disabled="disabled">
 <input type="submit" onclick="LoadNotes('NotesSkipPage','Notes')" class="page" value="跳转">
  <input type="text" value="1" name="NotesSkipCount" id ="SplitPageInput" />
         
 </div> 
 <!-- 笔记内容分页 结束/ --> 
</div>
      <!--笔记内容 结束 -->   
           <!-- 笔记显示窗口 结束 -->  
       
    
    
    
    </div>
  
       

      </div> 
    

       
   	
	

    
    
    
    
    
    
<input type="button" value="评论" onclick="ShowWindowByClass('SubmitFloorCommentWindow')" class="ShowFloorCommentWindowButton" >

    

    
    
       
<%
String WorkID = (String)request.getAttribute("workID");

CommentsImpl commentsImpl = new CommentsImpl();
if(commentsImpl.isExistComment(WorkID)){
%>

 <div class="LoadCommentButtonBoss"> 
<input type="button" value="点击展开评论" onclick="LoadComments('CommentsFirstPage','Comments','All')" class="LoadCommentButton" >

<input type="button" value="隐藏评论" onclick="HideComments()"  class="HideCommentButton">

</div>


<%}else{ %>
       
       <div class="LoadCommentButtonBoss"> 
<input type="button" value="沙发欠缺中~~" onclick="" class="LoadCommentButton" >
</div>
  <%} %>  

      
      
           	   	    <div class="Introduce"><p>${requestScope.works.introduce}</p></div>  
      
         
<!-- 显示评论的窗口 开始 -->

<div id="CommentWindowBoss" > 


<input type="hidden" value=${requestScope.works.workID} id="CommentWorkID">

<div id="CommentWindow">
 
 


</div>




<!-- 评论分页 开始 --> 
 
 <div id="CommentsSplitPage" align="center" >

 
 
 
 
 <span id="CommentsRowCount"></span>
 <span id="CommentsPageCount"></span>  
 <span id="CommentsShowPage"></span>    
 <input type="button" value="首页" onclick="LoadComments('CommentsFirstPage','Comments','All')" id="CommentsFirstPage" name="CommentsFirstPage" class="page" disabled="disabled">
 <input type="button" value="上一页" onclick="LoadComments('CommentsPreviousPage','Comments','All')" id="CommentsPreviousPage"  name="CommentsPreviousPage" class="page" disabled="disabled">
 <input type="button" value="下一页" onclick="LoadComments('CommentsNextPage','Comments','All')" id="CommentsNextPage"  name="CommentsNextPage" class="page" disabled="disabled">
 <input type="button" value="尾页" onclick="LoadComments('CommentsLastPage','Comments','All')" id="CommentsLastPage" name="CommentsLastPage" class="page" disabled="disabled">
 <input type="text" value="1" name="CommentsSkipCount" id ="SplitPageInput" />
 <input type="submit" onclick="LoadComments('CommentsSkipPage','Comments','All')" class="page" value="跳转">        
 </div>   
 <!-- 评论分页 结束 -->
 


</div>





    

<!-- 警告窗口 开始-->
<div id="AlertWindow" align="center"></div>
<!-- 警告窗口 结束-->









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



<!-- 评论窗口 开始 -->

<div class="SubmitFloorCommentWindow">

<div class="SubmitFloorCommentWindowTop"><span>评论</span><a onclick="HideWindowByClass('SubmitFloorCommentWindow')">X</a></div>
 <textarea  class ="SubmitFloorCommentTextarea" ></textarea>

<input type="button" onclick="SubmitFloorComment()" value="提交" class="SubmitFloorCommentButton">
</div>














<!-- 评论窗口 结束 -->

  </body>
</html>
