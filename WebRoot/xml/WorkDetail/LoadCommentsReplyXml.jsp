<%@ page language="java" contentType="text/xml; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<comments>
    <c:forEach var="commentsList" items="${commentsList}">
            <UserName>${commentsList.userName}</UserName>
           <CreateTime>${commentsList.createTime}</CreateTime> 
         <Contents>${commentsList.contents}</Contents> 
         <UserHeadPicture>${commentsList.userHeadPicture}</UserHeadPicture>
         <CommentID>${commentsList.commentID}</CommentID>
         <UserID>${commentsList.userID}</UserID>
         <CommentRepliedUserName>${commentsList.commentRepliedUserName}</CommentRepliedUserName>
         <RepliedCommentID>${commentsList.commentRepliedID}</RepliedCommentID>
    </c:forEach>
    <pages>
       <RowCount>${pages.rowCount}</RowCount>
       <PageCount>${pages.pageCount}</PageCount>
       <ShowPage>${pages.showPage}</ShowPage>   
    </pages>

</comments>





