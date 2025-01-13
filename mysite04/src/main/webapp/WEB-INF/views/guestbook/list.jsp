<%@ page import="mysite.vo.GuestbookVo" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%
	List<GuestbookVo> list = (List<GuestbookVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
	<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
	<div id="content">
		<div id="guestbook">
			<form action="${pageContext.request.contextPath}/guestbook/insert" method="post">
				<table>
					<tr>
						<td>이름</td><td><input type="text" name="name"></td>
						<td>비밀번호</td><td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td colspan=4><textarea name="contents" id="content"></textarea></td>
					</tr>
					<tr>
						<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
					</tr>
				</table>
			</form>
			<ul>
				<li>
					<c:forEach var="vo" items="${list}" varStatus="status">
						<table>
							<tr>
								<td>[${list.size() - status.index}]</td>
								<td>${vo.name}</td>
								<td>${vo.regDate}</td>
								<td><a href="${pageContext.request.contextPath}/guestbook/delete/${vo.id}">삭제</a></td>
							</tr>
							<tr>
								<td colspan="4">${vo.contents}</td>
							</tr>
						</table>
						<br>
					</c:forEach>
				</li>
			</ul>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
	<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
</div>
</body>
</html>
