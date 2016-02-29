<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<title>微课自助编译平台</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	    <script src="jslib/jquery-1.10.2.js"></script>
    
      <link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.4/demos.css" />
    
    <script src="jslib/jquery-1.10.4/jquery.ui.core.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.widget.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.button.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.position.js"></script>
	<script src="jslib/jquery-1.10.4/jquery.ui.menu.js"></script>
	<script src="jslib/userFunction/SubmitCode.js"></script>

   <link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.4/jquery.ui.all.css" />
	
	   <link type="text/css" rel="stylesheet" href="css/userFunction/Compile/style.css" />
	
	<style>
		.ui-menu { position: absolute; width: 100px; }
	</style>

	</head>

	<body>
	
	
	<div class="boss">
		<!-- java编译 开始 -->
			<textarea name="code" class="Code" cols="50" rows="10" id="Code">
            </textarea>
			
			
			<!-- 平台选择框 开始 -->
<div>
	<div>
		<button id="rerun">请选择一个语言平台</button>
		<button id="select">请选择一个语言平台</button>
	</div>
	<ul>
		<li><a onclick="SubmitCode(this)" id="Java">Java</a></li>
		<li><a>C</a></li>
		<li><a>C++</a></li>
	</ul>
</div>

<!-- 平台选择框 结束 -->
			
			
			
			
			<textarea name="result"  class="Result" cols="50" rows="10"
				value="" id="Result">
            </textarea>

		<!-- java编译 结束 -->


</div>



	</body>
</html>
