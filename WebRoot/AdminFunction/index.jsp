<%@ page contentType="text/html; charset=GBK" language="java" buffer="32kb"%>

<html>
<head>

<script>
function switchBar()
{
	if (switchPoint.innerText==3){
	switchPoint.innerText=4
	document.all("leftFrame").style.display="none"
    }
	else
	{
		switchPoint.innerText=3
		document.all("leftFrame").style.display=""
	}
}


</script>

<title>5iTech�Ұ�����΢��ѧϰƽ̨-��������</title>
<META content="MSHTML 6.00.2600.0" name=GENERATOR>
<link href="../css/cs.css" rel="stylesheet" type="text/css">
</head>
<body scroll = "no" style="MARGIN: 0px" >
<table height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TR>
    <TD id=leftFrame vAlign = "center" noWrap align = "middle" name = "leftFrame">
    <IFRAME id=carnoc style="Z-INDEX: 2; VISIBILITY: inherit; WIDTH: 185px; HEIGHT: 100%" name=carnoc src="left.jsp" frameBorder=0 scrolling=yes>
    </IFRAME></TD>
    <TD bgcolor=428eff style="WIDTH: 9pt">
      <TABLE height="100%" cellSpacing=0 cellPadding=0 border=0>
        <TR>
          <TD title=��/�ر�ȫ�� style="HEIGHT: 100%" onclick=switchBar()><FONT style="FONT-SIZE: 9pt; CURSOR: default; COLOR: #ffffff"><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
           <SPAN class=navPoint id=switchPoint title=��/�ر�ȫ��>3</SPAN><BR>
           <BR><BR><BR><BR><BR>��Ļ�л� 
        </FONT></TD></TR></TABLE></TD>
    <TD style="WIDTH: 100%">
      <IFRAME id=main style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 100%; HEIGHT: 100%" name = "main" src="UpdateWorks.jsp" frameBorder=0 scrolling=yes>
    </IFRAME></TD></TR></table>
</body>
</html>
