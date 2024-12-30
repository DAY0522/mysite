<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <jsp:include page="/WEB-INF/views/includes/header.jsp"/>
    <div id="content">
        <div id="board">
            <form id="search_form" action="${pageContext.request.contextPath}/board" method="post">
                <input type="text" id="keyword" name="keyword" value="${param.keyword}">
                <input type="submit" value="찾기">
            </form>
            <table class="tbl-ex">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>&nbsp;</th>
                </tr>
                <c:forEach items="${map.list}" var="board" varStatus="status">
                    <tr>
                        <td><c:out value="${map.totalCount - (map.currentPage - 1) * map.page_size - status.index}"/></td>
                        <td style="text-align:left; padding-left:${board.depth*20}px">
                            <c:if test="${board.depth ne 0}">
                                <img src="${pageContext.request.contextPath}/assets/images/reply.png"/>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/board/view/${board.id}">${board.title}</a></td>
                        <td><c:out value="${board.userName}"/></td>
                        <td><c:out value="${board.hit}"/></td>
                        <td><c:out value="${board.regDate}"/></td>
                        <td><a href="${pageContext.request.contextPath}/board/delete/${board.id}" class="del">삭제</a></td>
                    </tr>
                </c:forEach>
            </table>

            <div class="pager">
                <ul>
                    <c:if test="${not empty map.list and map.search}">
                        <c:if test="${map.currentPage ne 1}">
                            <li><a href="?p=${map.currentPage-1}&keyword=${param.keyword}">◀</a></li>
                        </c:if>
                        <c:forEach begin="${map.currentStartPage}" end="${map.currentEndPage}" var="i">
                            <li class="${map.currentPage == i? 'selected' : ''}">
                                <a href="?p=${i}&keyword=${param.keyword}">${i}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${map.currentPage ne map.endPage}">
                            <li><a href="?p=${map.currentPage+1}&keyword=${param.keyword}">▶</a></li>
                        </c:if>
                    </c:if>
                </ul>
            </div>

            <div class="bottom">
                <a href="${pageContext.request.contextPath}/board/write" id="new-book">글쓰기</a>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
    <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
</div>
</body>
</html>
