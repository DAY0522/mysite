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
    <link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <jsp:include page="/WEB-INF/views/includes/header.jsp"/>
    <div id="content">
        <div id="user">

            <form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath}/user">
                <input type="hidden" name="a" value="update">
                <label class="block-label" for="name">이름</label>
                <input id="name" name="name" type="text" value="${vo.name}">

                <label class="block-label" for="email">이메일</label>
                <h3>${vo.email}</h3>
                <input type="button" value="id 중복체크">

                <label class="block-label">패스워드</label>
                <input name="password" type="password" value="">

                <fieldset>
                    <legend>성별</legend>
                    <c:if test="${vo.gender eq 'female'}">
                        <label>여</label> <input type="radio" name="gender" value="female" checked="checked">
                        <label>남</label> <input type="radio" name="gender" value="male">
                    </c:if>
                    <c:if test="${vo.gender eq 'male'}">
                        <label>여</label> <input type="radio" name="gender" value="female">
                        <label>남</label> <input type="radio" name="gender" value="male" checked="checked">
                    </c:if>
                </fieldset>
                <input type="submit" value="수정하기">
            </form>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
    <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
</div>
</body>
</html>
