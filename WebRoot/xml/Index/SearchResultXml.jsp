<%@ page language="java" contentType="text/xml; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<SearchResult>
    <c:forEach var="SearchResultList" items="${SearchResultList}">
        <BoardID>${SearchResultList.boardID}</BoardID>
        <WorkID>${SearchResultList.workID}</WorkID>
        <WorkContent>${SearchResultList.workContent}</WorkContent>  
        <WorkTitle>${SearchResultList.workTitle}</WorkTitle> 
        <ReCount>${SearchResultList.reCount}</ReCount>   
        <ReadCount>${SearchResultList.readCount}</ReadCount>
        <IndexPic>${SearchResultList.indexPic}</IndexPic>
    </c:forEach>
    <pages>
       <RowCount>${pages.rowCount}</RowCount>
       <PageCount>${pages.pageCount}</PageCount>
       <ShowPage>${pages.showPage}</ShowPage>   
    </pages>
</SearchResult>





