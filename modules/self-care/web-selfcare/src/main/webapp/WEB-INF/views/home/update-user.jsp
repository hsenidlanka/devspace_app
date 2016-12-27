<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%@include file="../includes/include.jsp" %>
    <%--<title><fmt:message key="update.user.title" bundle="${lang}"/> ${username}</title>--%>
    <script>
        $(document).ready(function () {

            var usernamejs = "${username}";
            $("#usernamejs").val(usernamejs);

            $.ajax({

                type: "GET",
                // The URL for the request
                url: "sendUserData",
                // The data to send (will be converted to a query string)
                data: {
                    username: usernamejs
                },

                // The type of data we expect back
                dataType: "json",
                success: function (data2) {
                    $('#' + data2.title).prop('checked', true);
                    $("#firstNamejs").val(data2.firstName);
                    $("#lastNamejs").val(data2.lastName);
                    $("#addr1js").val(data2.addressLine01);
                    $("#addr2js").val(data2.addressLine02);
                    $("#addr3js").val(data2.addressLine03);
                    $("#mobilejs").val(data2.mobile);
                    $("#emailjs").val(data2.email);
                },
                error: function (data) {
                    alert("Data Retrive failed");
                    console.log("error work");
                }
            });

        });

    </script>


</head>
<%--<style>

    hr {
        margin-top: 12px;
        margin-bottom: 12px;
        box-shadow: 0 10px 10px -10px #88C470 inset !important;
    }

    .updatesuccess {
        /*font-size: 27px;*/
    }


</style>--%>

<body style="height: 100%">
<%--<%@include file="../includes/new-header.jsp" %>--%>

<%--<center>--%>
<div>

    <div class="panel panel-success">

        <form:form action="/web-selfcare/update-user" method="post" modelAttribute="updateuser"
                   cssClass="form-horizontal">

            <div class="panel-heading">
                    <%--<p style="font-size: xx-large;text-align: left">--%>
                <img class="icons" src="<c:url value="/resources/images/icons/useredit.png"/>">

                <h3 style="display: inline-block;"><fmt:message key="update.user.title" bundle="${lang}"/></h3>
            </div>

            <div class="panel-body">
                <center>
                    <div class="updatesuccess">${updatedMsg}</div>
                </center>

                <div class="col-sm-6">

                    <h4><strong><fmt:message key="update.user.personal.info" bundle="${lang}"/></strong></h4>

                    <div class="form-group">

                        <div class="row">
                            <label class="">
                                <strong>
                                    <fmt:message key="signup.title" bundle="${lang}"/>
                                </strong>
                            </label>
                        </div>
                        <div class="row">
                            <form:label cssClass="radio-inline"path="">
                                <form:radiobutton path="title" id="Mr" name="titlejs" value="Mr"/>Mr
                            </form:label>
                            <form:label path="">
                                <form:radiobutton path="title" id="Mrs" name="titlejs" value="Mrs"/>Mrs
                            </form:label>
                            <form:label path="">
                                <form:radiobutton path="title" id="Ms" name="titlejs" value="Ms"/>Ms
                            </form:label>
                            <form:label path="">
                                <form:radiobutton path="title" id="Dr" name="titlejs" value="Dr"/>Dr
                            </form:label>
                            <form:label path="">
                                <form:radiobutton path="title" id="Rev" name="titlejs" value="Rev"/>Rev
                            </form:label>

                            <form:errors path="title" cssClass="help-inline" cssStyle="color: red"/>
                        </div>
                    </div>

                    <div class="form-group">
                    <div class="row">
                    <form:label path="firstName" for="firstName">
                        <strong>
                            <fmt:message key="signup.first.name" bundle="${lang}"/>
                        </strong>
                    </form:label>
                    </div>
                        <div class="row">
                    <form:input id="firstNamejs" path="firstName" cssClass="form-control input-sm" cssStyle="width: 72%"/>
                    <form:errors path="firstName" cssClass="help-inline" cssStyle="color: red"/>
                        </div>
                    </div>

                    <form:label path="lastName" for="lastName">
                        <p><strong><fmt:message key="signup.last.name" bundle="${lang}"/></strong></p>
                    </form:label>

                    <form:input path="lastName" id="lastNamejs" cssClass="form-control input-sm" cssStyle="width: 72%"/>
                    <form:errors path="lastName" cssClass="help-inline" cssStyle="color: red"/>


                    <form:label path="username" for="username">
                        <p><strong><fmt:message key="signup.username" bundle="${lang}"/></strong></p>
                    </form:label>

                    <form:input id="usernamejs" path="username" cssClass="form-control input-sm" cssStyle="width:72%"
                                readonly="true"/>

                    <form:errors path="username" cssClass="help-inline" cssStyle="color: red"/>

                </div>

                    <%----%>
                <div class="col-sm-6">
                    <div class="form-group">
                        <h4><strong><fmt:message key="update.user.contact.info" bundle="${lang}"/></strong></h4>
                    </div>
                    <div class="form-group">
                        <form:label path="email" for="email">
                            <p><strong><fmt:message key="signup.email" bundle="${lang}"/></strong></p>
                        </form:label>

                        <div class="">
                            <div class="row inline">
                                <form:input id="emailjs" path="email" cssClass="form-control input-sm"
                                            cssStyle="width:72%"/>
                            </div>
                            <div class="row">
                                <form:errors path="email" cssClass="help-inline" cssStyle="color: red"/>
                            </div>
                        </div>

                    </div>

                    <div class="form-group">
                        <form:label path="mobile" for="mobile">
                            <p><strong><fmt:message key="signup.email" bundle="${lang}"/>Mobile</strong></p>
                        </form:label>

                        <div class="">
                            <div class="row inline">
                                <form:input id="mobilejs" path="mobile" cssClass="form-control input-sm"
                                            cssStyle="width:72%"/>
                            </div>
                            <div class="row">
                                <form:errors path="mobile" cssClass="help-inline" cssStyle="color: red"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <form:label path="addressLine01" for="addressLine01" cssClass="text-left">
                            <p><strong><fmt:message key="signup.email" bundle="${lang}"/>Address</strong></p>
                        </form:label>
                        <div class="">
                            <div class="row">
                                <div class="row inline">
                                    <form:input id="addr1js" path="addressLine01" cssClass="form-control input-sm"
                                                cssStyle="width:72%"/>
                                </div>
                                <div class="row">
                                    <form:errors path="addressLine01" cssClass="help-inline" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 7px">
                                <div class="row inline">
                                    <form:input id="addr2js" path="addressLine02" cssClass="form-control input-sm"
                                                cssStyle="width:72%"/>
                                </div>
                                <div class="row">
                                    <form:errors path="addressLine02" cssClass="help-inline" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 7px">
                                <div class="row inline">
                                    <form:input id="addr3js" path="addressLine03" cssClass="form-control input-sm"
                                                cssStyle="width:72%"/>
                                </div>
                                <div class="row">
                                    <form:errors path="addressLine03" cssClass="help-inline" cssStyle="color: red"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <br>

                <div class="row">
                    <div class="col-sm-4"></div>
                    <div class="col-sm-4">

                        <center>
                            <button type="submit" class="btn btn-warning">
                                <fmt:message key="update.user.profile" bundle="${lang}"/> <span
                                    class="glyphicon glyphicon-send"></span>
                            </button>
                        </center>
                    </div>
                    <div class="col-sm-4"></div>
                </div>

            </div>
        </form:form>
    </div>
</div>
</center>
</body>

</html>
