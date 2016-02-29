<%@ page language="java" contentType="text/xml; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<MyNotesByWowokID>
    <c:forEach var="notesList" items="${notesList}">
        <Time>${notesList.time}</Time>
        <CreateTime>${notesList.createTime}</CreateTime>
        <Contents>${notesList.contents}</Contents>  
        <LikeCount>${notesList.likeCount}</LikeCount>   
        <NoteID>${notesList.noteID}</NoteID> 
    </c:forEach>
    

    <IndexPicUrl>${indexPicUrl}</IndexPicUrl>
    
</MyNotesByWowokID>





