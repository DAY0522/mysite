<%@ page import="mysite.vo.UserVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<div id="header">
    <h1>MySite</h1>
    <ul>
        <!-- 로그인하지 않은 사용자 -->
        <c:if test="${sessionScope.authUser == null}">
            <li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
            <li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
        </c:if>

        <!-- 로그인한 사용자 -->
        <c:if test="${sessionScope.authUser != null}">
            <li><a href="${pageContext.request.contextPath}/user/update">회원정보수정</a></li>
            <li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
            <li>${sessionScope.authUser.name}님 안녕하세요 ^^;</li>
        </c:if>
    </ul>
</div>
