<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<div id="navigation">
  <ul>
    <li><a href="${pageContext.request.contextPath}">김다영</a></li>
    <li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
    <li><a href="${pageContext.request.contextPath}/board">게시판</a></li>
  </ul>
</div>
