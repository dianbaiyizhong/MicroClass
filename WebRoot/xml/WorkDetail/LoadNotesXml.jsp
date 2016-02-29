<%@ page language="java" contentType="text/xml; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<Notes>
    <c:forEach var="notesList" items="${notesList}">
            <UserName>${notesList.userName}</UserName>
         <UserHeadPicture>${notesList.userHeadPicture}</UserHeadPicture>
        <Time>${notesList.time}</Time> 
         <Contents>${notesList.contents}</Contents> 
           <LikeCount>${notesList.likeCount}</LikeCount> 
           <NoteID>${notesList.noteID}</NoteID>
    </c:forEach>
    <pages>
       <RowCount>${pages.rowCount}</RowCount>
       <PageCount>${pages.pageCount}</PageCount>
       <ShowPage>${pages.showPage}</ShowPage>   
    </pages>

</Notes>





