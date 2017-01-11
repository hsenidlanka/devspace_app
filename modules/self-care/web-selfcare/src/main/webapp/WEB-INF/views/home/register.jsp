<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title>Register</title>
    <%--<style type="text/css">
        body {
            overflow:hidden;
        }
    </style>--%>
</head>
<body>
<%@include file="../includes/new-header.jsp" %>


<div style="margin-top: -19px;" class="panel panel-success">
<%--style="height: 80px"--%>
    <div class="panel-heading">
        <img class="icons" src="<c:url value="/resources/images/icons/useredit.png"/>">

        <h3 style="display: inline-block;"><fmt:message key="register.page.title" bundle="${lang}"/></h3>
    </div>
    <div class="panel-body">
        <form:form action="/web-selfcare/register" method="post" modelAttribute="register" cssClass="form-horizontal">


        <div class="col-sm-1"></div>
        <div class="col-sm-4">
            <div class="form-group">
                <h4><strong><fmt:message key="update.user.personal.info" bundle="${lang}"/></strong></h4>
            </div>
            <div class="form-group">

                <div class="row">
                    <label>
                        <strong>
                            <fmt:message key="signup.title" bundle="${lang}"/>
                        </strong>
                    </label>
                </div>
                <div style="margin-top:-10px" class="row">
                    <label class="radio-inline" for="Mr">
                        <form:radiobutton path="title" id="Mr" name="titlejs" value="Mr"/>Mr
                    </label>
                    <label class="radio-inline" path="Mrs">
                        <form:radiobutton path="title" id="Mrs" name="titlejs" value="Mrs"/>Mrs
                    </label>
                    <label class="radio-inline" for="Ms">
                        <form:radiobutton path="title" id="Ms" name="titlejs" value="Ms"/>Ms
                    </label>
                    <label class="radio-inline" for="Dr">
                        <form:radiobutton path="title" id="Dr" name="titlejs" value="Dr"/>Dr
                    </label>
                    <label class="radio-inline" for="Rev">
                        <form:radiobutton path="title" id="Rev" name="titlejs" value="Rev"/>Rev
                    </label>
                </div>
                <form:errors path="title" cssClass="help-inline" cssStyle="color: red"/>
            </div>

            <div class="form-group">
                <div class="row">
                    <form:label path="firstName" for="firstName">
                        <strong><fmt:message key="signup.first.name" bundle="${lang}"/></strong>
                    </form:label>
                </div>
                <div class="row">
                    <form:input id="firstNamejs" path="firstName" cssClass="form-control input-sm"/>
                    <form:errors path="firstName" cssClass="help-inline" cssStyle="color: red"/>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <form:label path="lastName" for="lastName">
                        <p><strong><fmt:message key="signup.last.name" bundle="${lang}"/></strong></p>
                    </form:label>
                        <%--</div>--%>
                        <%--<div class="row">--%>
                    <form:input cssStyle="margin-top: -10px" cssClass="form-control input-sm" path="lastName"
                                id="lastNamejs"/>
                    <form:errors path="lastName" cssClass="help-inline" cssStyle="color: red"/>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <form:label path="username" for="username">
                        <p><strong><fmt:message key="signup.username" bundle="${lang}"/></strong></p>
                    </form:label>

                    <form:input cssStyle="margin-top: -10px" id="usernamejs" path="username"
                                cssClass="form-control input-sm"/>

                    <form:errors path="username" cssClass="help-inline" cssStyle="color: red"/>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <form:label path="password" for="password" cssStyle="text-left">
                        <p><strong><fmt:message key="signup.password" bundle="${lang}"/></strong></p>
                    </form:label>
                    <form:input cssStyle="margin-top: -10px" type="password" id="password" path="password"
                                cssClass="form-control input-sm"/>
                    <form:errors path="password" cssClass="help-inline" cssStyle="color: red"/>
                </div>
            </div>

        </div>


        <div class="col-sm-2"></div>
            <%----%>
        <div class="col-sm-4">
            <div class="form-group">
                <h4 style=""><strong><fmt:message key="update.user.contact.info" bundle="${lang}"/></strong>
                </h4>
            </div>
            <div class="form-group">
                <div class="row">
                    <form:label path="email" for="email">
                        <p><strong><fmt:message key="signup.email" bundle="${lang}"/></strong></p>
                    </form:label>

                        <%--<div class="">--%>
                        <%--<div class="ine">--%>
                    <form:input cssStyle="margin-top: -10px" id="emailjs" path="email" cssClass="form-control input-sm"/>
                        <%--</div>--%>
                        <%--<div class="row">--%>
                    <form:errors path="email" cssClass="help-inline" cssStyle="color: red"/>
                        <%--</div>--%>
                </div>

            </div>

            <div class="form-group">
                <div class="row">
                    <form:label path="mobile" for="mobile">
                        <p><strong><fmt:message key="pay.mobile.no" bundle="${lang}"/></strong></p>
                    </form:label>

                        <%--<div class="">--%>
                        <%--<div class="row inline">--%>
                    <form:input cssStyle="margin-top: -10px" id="mobilejs" path="mobile" cssClass="form-control input-sm"/>
                        <%--</div>--%>
                        <%--<div class="row">--%>
                    <form:errors path="mobile" cssClass="help-inline" cssStyle="color: red"/>
                        <%--</div>--%>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <form:label path="addressLine01" for="addressLine01" cssClass="text-left">
                        <p><strong><fmt:message key="signup.address" bundle="${lang}"/></strong></p>
                    </form:label>
                        <%--<div class="row">--%>
                    <div style="margin-top: -10px" class="row inline">
                        <form:input id="addr1js" path="addressLine01" cssClass="form-control input-sm"/>
                    </div>
                    <div class="row">
                        <form:errors path="addressLine01" cssClass="help-inline" cssStyle="color: red"/>
                    </div>
                </div>
                <div class="row">
                    <div class="row inline">
                        <form:input id="addr2js" path="addressLine02" cssClass="form-control input-sm"/>
                    </div>
                    <div class="row">
                        <form:errors path="addressLine02" cssClass="help-inline" cssStyle="color: red"/>
                    </div>
                </div>
                <div class="row">
                    <div class="row inline">
                        <form:input id="addr3js" path="addressLine03" cssClass="form-control input-sm"/>
                    </div>
                    <div class="row">
                        <form:errors path="addressLine03" cssClass="help-inline" cssStyle="color: red"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <form:label path="confirmPassword" for="confirmPassword" cssClass="text-left">
                        <p><strong><fmt:message key="sign.confirm.password" bundle="${lang}"/></strong></p>
                    </form:label>
                    <form:input cssStyle="margin-top: -10px" type="password" id="confirmPassword" path="confirmPassword"
                                cssClass="form-control input-sm"/>
                        <%--<div class="row">--%>
                    <form:errors path="confirmPassword" cssClass="help-inline" cssStyle="color: red"/>
                </div>
            </div>
        </div>
        <div class="col-sm-1"></div>
        <br>

        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <center>
                    <button type="submit" class="btn btn-warning">
                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i> <fmt:message
                            key="update.user.profile" bundle="${lang}"/>
                    </button>
                </center>
            </div>
            <div class="col-sm-4"></div>
        </div>
    </div>
</div>
</form:form>
</div>


</body>
</html>
