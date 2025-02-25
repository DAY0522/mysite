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
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div id="container">
		<c:import url = "/WEB-INF/views/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img src="${pageContext.request.contextPath }${vo.profile }" style="width:500px">
					<h2>${vo.welcome}</h2>
					<p>
						${vo.description}
						<br><br>
						<a href="${pageContext.request.contextPath}/guestbook">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<c:import url = "/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url = "/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>
