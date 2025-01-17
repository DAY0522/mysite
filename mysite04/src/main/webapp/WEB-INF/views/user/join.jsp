<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
    <script>
        $(function () {
            var el = $("#btn-check");
            el.click(function () { // () -> 로 function을 대체할 수 있음
                var email = $("#email").val();
                if (email == "") {
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath }/api/user/checkemail?email=" + email,
                    type: "get",
                    dataType: "json",
                    success: function (response) {
                        // console.log(response)
                        if (response.result != "success") {
                            console.error(response.message);
                            return;

                        }
                        if (response.data.exist) {
                            // alert("") //  alret는 안쓰는 게 좋음.
                            alert("이메일이 존재합니다. 다른 이메일을 사용해 주세요.");
                            $("#email").val(""); // 기존 내용 지우기
                            $("#email").focus(); // 기존 폼으로 마우스 이동(키보드 바로 입력할 수 있도록)

                            return;
                        }

                        $("#img-check").show();
                        $("#btn-check").hide();
                    },
                    error: function (xhr, status, err) { // xhr: 통신하는 객체
                        console.error(err);
                    }
                })
            });
        });
    </script>
</head>
<body>
<div id="container">
    <jsp:include page="/WEB-INF/views/includes/header.jsp"/>
    <div id="content">
            <form:form
                    modelAttribute="user"
                    action="${pageContext.request.contextPath}/user/join"
                    method="post"
                    id="join-form">
                <label class="block-label" for="name"><spring:message code="user.join.label.name"/></label>
                <form:input path="name" id="name"/>
                <br>
                <form:errors path="name" cssClass="error-message"/>

                <spring:message code="user.join.label.email.check" var="userJoinLabelEmailCheck"/>
                <label class="block-label" for="email"><spring:message code="user.join.label.email"/></label>
                <form:input path="email"/>
                <input id="btn-check" type="button" value="${userJoinLabelEmailCheck }" style="display:;">
                <img id="img-check" src="${pageContext.request.contextPath}/assets/images/check.png"
                     style="vertical-align:bottom; width:24px; display: none">
                <p style="color:#f00; text-align:left; padding:0">
                    <form:errors path="email"/>
                </p>

                <label class="block-label"><spring:message code="user.join.label.password"/></label>
                <form:input path="password" id="password"/>
                <br>
                <form:errors path="password" cssClass="error-message"/>

                <fieldset>
                    <legend><spring:message code="user.join.label.gender"/></legend>
                    <label><spring:message code="user.join.label.gender.female"/></label> <input type="radio"
                                                                                                 name="gender"
                                                                                                 value="female"
                                                                                                 checked="checked">
                    <label><spring:message code="user.join.label.gender.male"/></label> <input type="radio"
                                                                                               name="gender"
                                                                                               value="male">
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
