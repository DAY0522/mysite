<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<form:form
						modelAttribute="user"
						action="${pageContext.request.contextPath}/user/join"
						method="post"
						id="join-form">
					<label class="block-label" for="name"><spring:message code="user.join.label.name"/></label>
					<form:input path="name" id="name" />
					<br>
					<form:errors path="name" cssClass="error-message"/>

					<label class="block-label" for="email"><spring:message code="user.join.label.email"/></label>
					<form:input path="email" id="email" />
					<input type="button" value="<spring:message code="user.join.label.email.check"/>">
					<br>
					<form:errors path="email" cssClass="error-message"/>

					<label class="block-label"><spring:message code="user.join.label.password"/></label>
					<form:input path="password" id="password" />
					<br>
					<form:errors path="password" cssClass="error-message"/>

					<fieldset>
						<legend><spring:message code="user.join.label.gender"/></legend>
						<label><spring:message code="user.join.label.gender.female"/></label> <input type="radio" name="gender" value="female" checked="checked">
						<label><spring:message code="user.join.label.gender.male"/></label> <input type="radio" name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend><spring:message code="user.join.label.terms"/></legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label><spring:message code="user.join.label.terms.message"/></label>
					</fieldset>

					<input type="submit" value="<spring:message code="user.join.button.signup"/>">

				</form:form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>
