<%@ page contentType="text/html; charset=GBK" language="java" buffer="32kb"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<style type="text/css">

BODY {
	BACKGROUND:#428eff; MARGIN: 0px; FONT: 9pt ����;
	SCROLLBAR-HIGHLIGHT-COLOR: ;
	SCROLLBAR-ARROW-COLOR:#428eff;
	SCROLLBAR-TRACK-COLOR:#ECF5FF;
	SCROLLBAR-BASE-COLOR:#C8E3FF;
}
TD { FONT: 12px ����}
A { FONT: 12px ����; COLOR: #000000; TEXT-DECORATION: none }
A:hover { COLOR: #428eff; TEXT-DECORATION: underline}

.sec_menu 
{
	BORDER-RIGHT: white 1px solid; BACKGROUND: #ECF5FF; 
	OVERFLOW: hidden; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid 
}

.menu_title SPAN { FONT-WEIGHT: bold; LEFT: 8px; COLOR: #215dc6; POSITION: relative; TOP: 2px }

.menu_title2 SPAN { FONT-WEIGHT: bold; LEFT: 8px; COLOR: #215dc6; POSITION: relative; TOP: 2px }
</style>
<script type="text/javascript" src="jslib/userFunction/Logout.js"></script>

<script type="text/javascript">
function logoutToIndex(){

	alert("sss");
	logout();

	
}


</script>

</head>

<html>

<table cellspacing="0" cellpadding="0" width="158" align="center">
<tr><td valign="bottom" height="42"><img height="38" src="../picture/AdminFunction/title.gif" width="158" border="0"></td></tr>
<tr>
    <td class="menu_title" onMouseOver="this.className='menu_title2';" onMouseOut="this.className='menu_title';" background="../picture/AdminFunction/title_bg_quit.gif" height="25"> 
      <span><a target="rightFrame" title="���ع�����ҳ" href="UpdateWorks.jsp"><b><font color="215DC6">������ҳ</font></b></a>|<a onclick="logoutToIndex()" target="_parent" title="��ȫ�˳�����ϵͳ" ><b><font color="215DC6">��ȫ�˳�</font></b></a></span> 
	  
<tr><td>
	<div class="sec_menu" style="WIDTH: 158px">
	<table cellspacing="0" cellpadding="0" width="135" align="center">
		<tr>
		    <td height="22">�û�����<font color = "red"><%=session.getAttribute("UserName")%></font></td>
		</tr>
		<tr>
		    <td height="22">��&nbsp;&nbsp;�ݣ� ����Ա</td>
		</tr>
	</table>
	</div>
</td></tr>

</table>
<table cellspacing="0" cellpadding="0" width="158" align="center">
<tr>
    <td class="menu_title" id="menuTitle3" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(3)" onMouseOut="this.className='menu_title2';" style="cursor:hand" background="../picture/AdminFunction/title_bg_show.gif" height="25"><span>�û�����</span></td>
</tr>
<tr><td id="submenu3" style="DISPLAY: none">
	<div class="sec_menu" style="WIDTH: 158px">
	<table cellspacing="0" cellpadding="0" width="135" align="center">
		<tr>
		    <td height="22"><a href="#" title="�û�����" target="rightFrame">�û�����</a></td>
		</tr>
	</table>
	</div>
</td></tr>
</table>
<table cellspacing="0" cellpadding="0" width="158" align="center">
<tr>
    <td class="menu_title" id="menuTitle4" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(4)" onMouseOut="this.className='menu_title2';" style="cursor:hand" background="../picture/AdminFunction/title_bg_show.gif" height="25"><span>�γ̹���</span></td>
</tr>
<tr><td id="submenu4">
	<div class="sec_menu" style="WIDTH: 158px">
	<table cellspacing="0" cellpadding="0" width="135" align="center">
		<tr>
		    <td height="22"><a href="AddBoard.jsp" title="��ӿγ�" target="main">��ӹ���γ�</a></td>
		</tr>
		<tr>
		    <td height="22"><a href="UploadWork.html" title="��ӿγ�֪ʶ��" target="main">�����ӿγ�֪ʶ��</a></td>
		</tr>
        <tr>
		    <td height="22"><a href="UpdateWorks.jsp" title="�޸�֪ʶ����Ϣ" target="main">�޸�֪ʶ����Ϣ</a></td>
		</tr>
	</table>
	</div>
</td></tr>
</table>



<table cellspacing="0" cellpadding="0" width="158" align="center">
<tr>
    <td class="menu_title" id="menuTitle9" onMouseOver="this.className='menu_title2';" onMouseOut="this.className='menu_title';" background="../picture/AdminFunction/admin_left_9.gif" height="25"> 
      <span>ϵͳ��Ϣ</span> </td>
</tr><tr><td><div class="sec_menu" style="WIDTH: 158px">
<table cellspacing="0" cellpadding="0" width="135" align="center">
<tr><td height="22"><br>    
�汾��V1 <br>
    <br>��Ȩ���У�<br>
    ��5iTech�Ұ�����΢��ѧϰ<br>
<br></td></tr>
</table>
</div></td></tr>
</table>
</html>
<script>
function showsubmenu(sid)
{
	whichEl = eval("submenu" + sid);
	menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none")
	{
	eval("submenu" + sid + ".style.display=\"\";");
	menuTitle.background="../picture/AdminFunction/title_bg_hide.gif";
	}
	else
	{
	eval("submenu" + sid + ".style.display=\"none\";");
	menuTitle.background="../picture/AdminFunction/title_bg_show.gif";
	}
}
</script>

