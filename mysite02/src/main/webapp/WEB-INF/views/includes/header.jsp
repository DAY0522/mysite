<%--
  Created by IntelliJ IDEA.
  User: day
  Date: 2024/12/18
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
  <h1>MySite</h1>
  <ul>
    <li><a href="<%= request.getContextPath() %>/user?a=loginform">로그인</a><li>
    <li><a href="<%= request.getContextPath() %>/user?a=joinform">회원가입</a><li>
    <li><a href="<%= request.getContextPath() %>/user?a=updateform">회원정보수정</a><li>
    <li><a href="<%= request.getContextPath() %>/user?a=logoutform">로그아웃</a><li>
    <li>님 안녕하세요 ^^;</li>
  </ul>
</div>
