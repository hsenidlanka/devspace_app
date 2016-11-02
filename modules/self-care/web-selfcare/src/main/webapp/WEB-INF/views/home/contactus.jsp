<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title><fmt:message key="contactus" bundle="${lang}"/></title>
    <link href="<c:url value="/resources/css/prettify.css"/>">
    <script src="<c:url value="/resources/js/prettify.js"/>"></script>

    <%--<script src="<c:url value="/resources/js/contactus_validate.js"/>" ></script>--%>
    <script src="<c:url value="/resources/js/loader.js"/>"></script>
    <%--<script src="<c:url value="/resources/js/validate-inputs.js"/>" ></script>--%>

</head>
<div class="loader-anim"></div>
<style>
    .status {
        display: inline;
        visibility: hidden;
        width: 15px
    }

    .IMGself {
        vertical-align: -10px;
        width: 15px;
        visibility: hidden
    }
</style>

<script>
    $(document).ready(function () {
        var username = "${username}";
        var name = "${name}";
        var mobile = "${mobile}";
        var email = "${email}";

        $("#txt-fname").val(name);
        $("#txt-email").val(email);
        $("#txt-contactno").val(mobile);
    });
</script>
<body style="overflow: visible">
<%@include file="../includes/NewHeader.jsp" %>

<div class="brand"></div>
<center>


    <%--<c:if test="${empty validForm}">--%>

        <div class="inner-bg">
            <div class="container">
                <div class="col-sm-12" style="height: 25px"></div>

                <div class="row">
                    <div class="col-sm-8 col-sm-offset-2 text">

                    </div>
                </div>

                <div class="panel panel-success" style="width: 900px">
                    <div class="panel-heading" style="height: 50px">
                        <p style="font-size: xx-large;text-align: left"><fmt:message key="contactus"
                                                                                     bundle="${lang}"/></p>
                    </div>
                    <div class="panel-body">
                        <form:form action="/web-selfcare/contactus" method="post" modelAttribute="contactus"
                                   cssClass="form-horizontal">

                            <div class="form-group">

                                <form:label cssClass="col-sm-4 control-label" path="inquiryType" for="typeOfRespond">
                                    <fmt:message key="contactus.inquiry" bundle="${lang}"/>
                                </form:label>

                                <div class="col-sm-2">
                                    <div class="dropdown">

                                        <form:select cssClass="form-control" path="inquiryType">

                                            <form:option value="1"><fmt:message key="contactus.complaint"
                                                                                bundle="${lang}"/></form:option>
                                            <form:option value="2"><fmt:message key="contactus.suggestion"
                                                                                bundle="${lang}"/></form:option>
                                            <form:option value="3"><fmt:message key="contactus.clarification"
                                                                                bundle="${lang}"/></form:option>
                                            <form:option value="4"><fmt:message key="contactus.other" bundle="${lang}"/></form:option>

                                        </form:select>
                                    </div>


                                </div>
                            </div>
                            <div class="form-group">

                                <form:label cssClass="col-sm-4 control-label " path="title" for="title">
                                    Your Name
                                </form:label>

                                <div class="dropdown col-sm-2">

                                    <form:select class="form-control" path="title">
                                        <form:option value="mr"><fmt:message key="signup.mr"
                                                                             bundle="${lang}"/></form:option>
                                        <form:option value="mrs"><fmt:message key="signup.mrs"
                                                                              bundle="${lang}"/></form:option>
                                        <form:option value="miss"><fmt:message key="signup.miss"
                                                                               bundle="${lang}"/></form:option>
                                        <form:option value="miss"><fmt:message key="signup.dr"
                                                                               bundle="${lang}"/></form:option>
                                        <form:option value="miss"><fmt:message key="signup.rev"
                                                                               bundle="${lang}"/></form:option>
                                        "<fmt:message key="contactus.yourn" bundle="${lang}"/>"
                                    </form:select>
                                </div>
                                    <%--<spring:message code="contactus.yourn" var="yourName"/>--%>
                                <div class="col-sm-4 row">
                                    <form:input path="name" cssClass="form-control" id="txt-fname" type="text"
                                                placeholder="Test"/>
                                    <label id="fname_error" class="error-labels"></label>

                                </div>
                            </div>

                            <div class="form-group">
                                    <%--<form:input path="contactEmail"></form:input>--%>
                                <form:label cssClass="col-sm-4 control-label" path="contactEmail">
                                    <fmt:message key="signup.email" bundle="${lang}"/> </form:label>

                                <div class="col-sm-6">

                                    <form:input cssClass="form-control" id="txt-email" type="text"
                                                placeholder="example@domain.com" path="contactEmail"></form:input>
                                    <label id="email_error" class="error-labels"></label>

                                </div>
                            </div>

                            <div class="form-group">

                                <form:label cssClass="col-sm-4 control-label" path="">
                                    <fmt:message key="signup.phone" bundle="${lang}"/></form:label>

                                <div class="col-sm-6">

                                    <form:input cssClass="form-control" id="txt-contactno" type="text"
                                                placeholder="+94-XXXXXXXXXX" path="contactNo"/>
                                        <%--<label id="contactno_error" class="error-labels"></label>--%>

                                </div>
                            </div>
                            <div class="form-group">

                                <form:label cssClass="col-sm-4 control-label" path="msg">
                                    <fmt:message key="contactus.message" bundle="${lang}"/></form:label>

                                <div class="col-sm-6">
                                        <%--<spring:message code="contactus.your.inquiry" var="EnterMsg"/>--%>

                                    <form:textarea path="msg" cssClass="form-control" id="txt-inquirymsg" type="text"
                                                   placeholder="Your Message"/>
                                        <%--<label id="inquirymsg_error" class="error-labels"></label>--%>

                                </div>
                                <form:errors path="msg" class="help-inline"/>
                            </div>


                            <div class="form-group">
                                <button id="btn-proceed-submit" type="submit" class="btn btn-success">
                                    <fmt:message key="contactus.submit" bundle="${lang}"/>
                                </button>

                            </div>


                        </form:form>

                    </div>

                </div>
            </div>
        </div>
    <%--</c:if>--%>

    <c:if test="${not empty validForm}">
        <div class="inner-bg">
            <div class="container">
                <div class="col-sm-12" style="height: 25px"></div>

                <div class="row">
                    <div class="col-sm-8 col-sm-offset-2 text">

                    </div>
                </div>
                <div class="panel panel-success" style="width: 900px">
                    <div class="panel-heading" style="height: 50px">
                        <p style="font-size: xx-large;text-align: left"><fmt:message key="contactus"
                                                                                     bundle="${lang}"/></p>
                    </div>
                    <div class="panel-body">
                        Contact us form successfully submitted!!!

                    </div>

                </div>
            </div>
        </div>
    </c:if>
    <div><a href="http://gazpo.com/downloads/tutorials/jquery/scrolltop/#" class="scrollup" style="display: none;">Scroll</a>
    </div>

    <a href="http://gazpo.com/downloads/tutorials/jquery/scrolltop/#" class="scrollup" style="display: none;">Scroll</a>


</center>

<!-- contact details incorrect popup -->
<div id="validation-error-popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><img src="<c:url value="/resources/images/icons/error.png"/>">&nbsp;&nbsp;Validation
                    errors
                </h2>
            </div>
            <div class="modal-body">
                <p style="font-size: medium;"><fmt:message key="contactus.cant" bundle="${lang}"/>
                    <b><fmt:message key="contactus.valid" bundle="${lang}"/></b><br>
                    <fmt:message key="contactus.please" bundle="${lang}"/>
                </p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal">Ok</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>