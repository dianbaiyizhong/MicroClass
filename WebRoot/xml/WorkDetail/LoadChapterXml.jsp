<%@ page language="java" contentType="text/xml; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<Chapter>
    <c:forEach var="worksList" items="${worksList}">
        <WorkTitle>${worksList.workTitle}</WorkTitle> 
        <WorkID>${worksList.workID}</WorkID>
        <IndexPicUrl>${worksList.indexPicUrl}</IndexPicUrl>
    </c:forEach>
</Chapter>





