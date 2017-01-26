<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <%@include file="../includes/include.jsp" %>

</head>
<body>

<div class="panel panel-success">
    <div class="panel-heading">
        <img class="icons" src="<c:url value="/resources/images/icons/changepassword.png"/>">
        <h3 style="display: inline-block;"><fmt:message key="view.change" bundle="${lang}"/></h3>
    </div>
    <div class="panel-body">
        <div class="col-sm-1"></div>
        <div style="height: 400px; margin-top: 20px" class="container col-sm-10">
            <c:if test="${empty updateSuccessful}">
                <c:if test="${not empty formFailed}">
                    Password update failed. Possible reason -> invalid current password
                </c:if>
                <form:form action="/web-selfcare/change-password-post" method="post" modelAttribute="changepassword">
                    <%--<c:if test="${empty validForm}"></c:if>--%>
                    <div class="row form-group">
                        <form:label cssClass="control-label" path="currentPassword"><strong>Current
                            Password</strong></form:label>
                        <form:input cssStyle="margin-top: 5px" cssClass="form-control input-md" type="password"
                                    path="currentPassword"/>
                        <form:errors path="currentPassword" cssStyle="color: red"/>
                    </div>

                    <div class="row form-group">
                        <form:label cssClass="control-label" path="newPassword"><strong>New
                            Password</strong></form:label>
                        <form:input cssStyle="margin-top: 5px" cssClass="form-control input-md" type="password"
                                    path="newPassword"/>
                        <form:errors path="newPassword" cssStyle="color: red"/>
                    </div>

                    <div class="row form-group">
                        <form:label cssClass="control-label" path="confirmNewPassword"><strong>Confirm New
                            Password</strong></form:label>
                        <form:input cssStyle="margin-top: 5px" cssClass="form-control input-md" type="password"
                                    path="confirmNewPassword"/>
                        <form:errors path="confirmNewPassword" cssStyle="color: red"/>
                    </div>

                    <div class="row form-group">
                        <div class="col-sm-5"></div>
                        <div class="col-sm-2">
                            <button style="margin-top: 40px" class="btn btn-warning" type="submit"><i
                                    class="fa fa-unlock-alt" aria-hidden="true"></i> Update Password
                            </button>
                        </div>
                        <div class="col-sm-5"></div>
                    </div>
                </form:form>
            </c:if>

            <c:if test="${not empty updateSuccessful}">
                <div style="border-style: outset;" class="row form-group">
                    <center>
                    <h1><i class="fa fa-cogs" aria-hidden="true"></i> Password update successful! </h1>
                    </center>
                </div>
                <br>
                <div class="row form-group">
                    <center><strong>What do you want to do next?</strong></center>
                    
                    <center style="margin-top: 10px">Here are some options ...</center>
                </div>
                <br>
                <div class="row form-group">
                    <div class="col-sm-3"></div>
                    <div class="col-sm-6">
                        <center>
                            <a href="profile-info" class="btn btn-warning"><i class="fa fa-user" aria-hidden="true"></i> Go to Profile</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="update-user" class="btn btn-warning"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Update Profile</a>
                        </center>
                    </div>
                    <div class="col-sm-3"></div>
                </div>
            </c:if>
        </div>
        <div class="col-sm-1"></div>
    </div>
</div>
</body>
</html>
