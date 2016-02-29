<%@ page contentType="text/html; charset=GBK" language="java" %>
<html><head>
<link href="../css/cs.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="javascript">
function checkform()
{   theform=document.logonForm;
  	if (theform.username.value == "")      
	{      
		alert("请输入用户名！");      
		theform.username.focus();      
		return false;      
	}
	else if (theform.password.value == "")      
	{      
		alert("请输入密码！");      
		theform.password.focus();      
		return false;      
	}
}
</SCRIPT>
<title>5iTech我爱技术后台登陆页面</title></head>
<body leftMargin=0 topMargin=0 marginheight="0" marginwidth="0" bgcolor="#FFFFFF">
<TABLE width="100%" height="600" border="0" align="center" cellPadding="0" cellSpacing=0 bgcolor="#FFFFFF">
  <TBODY>
  <TR>
    <TD>&nbsp;</TD></TR>
  <TR>
    <TD height="571" valign="middle">
      <TABLE width="552" height="498" border="0" align="center" cellPadding="0" cellSpacing="0">
        <TBODY>
        <TR>
          <TD width=552
            height=498 vAlign=top background="../picture/AdminFunction/loginbg.jpg"><TABLE width="100%" 
              border=0 align="center" cellPadding=0 cellSpacing=0>
            <TBODY>
              <TR>
                <TD height=87>&nbsp;</TD>
              </TR>
              <TR>
                <TD>
                  <TABLE class=newsfont_song9 height="189" cellSpacing=0 cellPadding=4 
                  width="552" border=0>
                    <form name = "logonForm" method="post" action="index.jsp" onsubmit="return checkform();">
                    <TBODY>

                    <TR>
                      <TD width="28%" height="43">&nbsp;</TD>
                      <TD width="19%" height="25" align=right>&nbsp;&nbsp;用户名称</TD>
                      <TD width="50%"><input type="text" name="username"/></TD>
                      <TD width="3%">&nbsp; </TD></TR>
                    <TR>
                      <TD width="28%" height="37">&nbsp;</TD>
                      <TD width="19%" height="25" align=right noWrap>&nbsp;&nbsp;用户密码</TD>
                      <TD width="50%"><input type="password" name="password"/></TD>
                      <TD width="3%">&nbsp; </TD></TR>
                    <TR>
                      <TD width="28%" height="37">&nbsp;</TD>
                      <TD width="19%" height="25" align=right noWrap><input type="submit" value="登录"/></TD>
                      <TD width="50%"><input type="reset"/></TD>
                      <TD width="3%">&nbsp; </TD></TR>
                    <TR>
                      <TD width="28%" height=28>&nbsp;</TD>
                      <TD align=right width="19%">&nbsp;</TD>
                      <TD width="50%">
                          </TR></TBODY></form></TABLE></body></html>
