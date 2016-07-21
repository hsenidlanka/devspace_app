<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:directive.include file="top.jsp" />
<form:form method="post" id="fm1" cssClass="fm-v clearfix" commandName="${commandName}" htmlEscape="true">
    <div id="wrapper">
    <div id="login-window">
        <h2>Login</h2>
        <br/>

        <form:errors path="*" cssClass="error_message" id="status" element="div" />

        <div class="clearfix">
            <label for="username"><spring:message code="screen.welcome.label.netid" /></label>
            <c:if test="${not empty sessionScope.openIdLocalId}">
                <strong>${sessionScope.openIdLocalId}</strong>
                <input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" />
            </c:if>

            <c:if test="${empty sessionScope.openIdLocalId}">
                <spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
            </c:if>
            <form:input cssClass="text_box" id="username"  accesskey="${userNameAccessKey}"
                        path="username" autocomplete="false" htmlEscape="true"/>
        </div>

        <div class="clearfix">
            <label for="password">
                <spring:message code="screen.welcome.label.password" />
            </label>
            <spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />
            <form:password cssClass="text_box" id="password"  path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
        </div>

        <div class="row btn-row">
            <input type="hidden" name="lt" value="${loginTicket}"/>
            <input type="hidden" name="execution" value="${flowExecutionKey}" />
            <input type="hidden" name="_eventId" value="submit" />
        </div>

        <div class="clearfix">
            <input class="button casLoginButton" accesskey="l" tabindex="2" type="submit" value="Login"/>
        </div>

    </div>
</form:form>
<script type="text/javascript">
    window.onload = function() {
        document.getElementById("username").focus();
    }
</script>
<jsp:directive.include file="bottom.jsp" />