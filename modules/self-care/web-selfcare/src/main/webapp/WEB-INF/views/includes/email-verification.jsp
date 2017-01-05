<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title>Verify Your account</title>
</head>
<body>
<%@include file="../includes/new-header.jsp" %>
<script>
    $(document).ready(function () {
        function GetURLParameter(sParam) {
            var sPageURL = window.location.search.substring(1);
            var sURLVariables = sPageURL.split('&');
            for (var i = 0; i < sURLVariables.length; i++) {
                var sParameterName = sURLVariables[i].split('=');
                if (sParameterName[0] == sParam) {
                    return sParameterName[1];
                }
            }
        }

        var user = GetURLParameter('username');
        $("#hiddenUsername").val(user);
        $("#testuser").val(user);
        var test = $("#hiddenUsername").val();
//            alert("Mama Thama -> "+test);
    })
</script>

<div class="container">
    <%--<%= request.getParameter("username") %>--%>
    <div class="panel panel-success">

        <div class="panel-heading">
            <img class="icons" src="<c:url value="/resources/images/icons/emailVerify.png"/>">

            <h3 style="display: inline-block;"><fmt:message key="email.verify.title" bundle="${lang}"/></h3>
        </div>
        <div class="panel-body">
            <c:if test="${empty verifyPass}">
                <form:form action="/web-selfcare/email-verification" method="post"
                           modelAttribute="emailverification">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-2">
                                <label class="control-label">Username : </label>
                            </div>
                            <div class="col-sm-6">
                                <strong><%= request.getParameter("username") %>
                                </strong>
                            </div>
                            <div class="col-sm-2"></div>
                        </div>
                        <form:input id="hiddenUsername" type="hidden" path="username"/>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-2">
                                <label class="control-label" for="verificationCode">Verification Code</label>
                            </div>
                            <div class="col-sm-6">
                                <form:input cssClass="form-control input-sm" id="verificationCode"
                                            path="verificationCode"/>
                            </div>
                            <div class="col-sm-2"></div>

                        </div>
                        <div class="row">
                            <div class="col-sm-4"></div>
                            <div class="col-sm-6">
                                <form:errors path="verificationCode" cssStyle="color: red"/>
                            </div>
                            <div class="col-sm-2"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-5"></div>
                            <div class="col-sm-2">
                                <button type="submit" class="btn btn-warning"><i class="fa fa-envelope-o"
                                                                                 aria-hidden="true"></i> Verify Email
                                </button>
                            </div>
                            <div class="col-sm-5"></div>
                        </div>
                    </div>
                </form:form>
            </c:if>
            <c:if test="${not empty verifyPass}">
                Passed
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
