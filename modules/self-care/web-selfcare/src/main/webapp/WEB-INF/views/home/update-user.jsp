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
                    <h3 style="display: inline-block;"><fmt:message key="update.user.title" bundle="${lang}"/>?</h3>
            </div>

            <div class="panel-body">
                <center>
                    <div class="updatesuccess">${updatedMsg}</div>
                </center>

                <div class="col-sm-6">

                    <div style="margin-left: 20px">
                        <div class="form-group">
                            <h4><strong>Personal Information</strong></h4>
                        </div>
                        <div class="form-group">
                            <label class="row"><p><fmt:message key="signup.title" bundle="${lang}"/></p></label>

                            <div class="row">

                                <form:radiobutton path="title" id="Mr" name="titlejs" value="Mr"/>Mr
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <form:radiobutton path="title" id="Mrs" name="titlejs" value="Mrs"/>Mrs
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <form:radiobutton path="title" id="Ms" name="titlejs" value="Ms"/>Ms
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <form:radiobutton path="title" id="Dr" name="titlejs" value="Dr"/>Dr
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <form:radiobutton path="title" id="Rev" name="titlejs" value="Rev"/>Rev

                            </div>
                            <div class="row">
                                <form:errors path="title" cssClass="help-inline" cssStyle="color: red"/>
                            </div>

                        </div>

                        <div class="form-group">
                            <form:label path="firstName" for="firstName">
                                <p>First Name</p>
                            </form:label>

                            <div class="">
                                <div class="row inline">
                                    <form:input id="firstNamejs" path="firstName" cssStyle="width: 72%"/>
                                </div>
                                <div class="row">
                                    <form:errors path="firstName" cssClass="help-inline" cssStyle="color: red"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">

                            <form:label path="lastName" for="lastName">
                                <p>Last Name</p>
                            </form:label>

                            <div class="">
                                <div class="row inline">
                                    <form:input path="lastName" id="lastNamejs" cssStyle="width: 72%"/>
                                </div>
                                <div class="row">
                                    <form:errors path="lastName" cssClass="help-inline" cssStyle="color: red"/>
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <form:label path="username" for="username">
                                <p>Username</p>
                            </form:label>

                            <div class="">
                                <div class="row inline">
                                    <form:input id="usernamejs" path="username" cssStyle="width:72%" readonly="true"/>
                                </div>
                                <div class="row">
                                    <form:errors path="username" cssClass="help-inline" cssStyle="color: red"/>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                    <%----%>
                <div class="col-sm-6">
                    <div class="form-group">
                        <h4><strong>Contact Information</strong></h4>
                    </div>
                    <div class="form-group">
                        <form:label path="email" for="email">
                            <p>Email</p>
                        </form:label>

                        <div class="">
                            <div class="row inline">
                                <form:input id="emailjs" path="email" cssStyle="width:72%"/>
                            </div>
                            <div class="row">
                                <form:errors path="email" cssClass="help-inline" cssStyle="color: red"/>
                            </div>
                        </div>

                    </div>

                    <div class="form-group">
                        <form:label path="mobile" for="mobile">
                            <p>Mobile</p>
                        </form:label>

                        <div class="">
                            <div class="row inline">
                                <form:input id="mobilejs" path="mobile" cssStyle="width:72%"/>
                            </div>
                            <div class="row">
                                <form:errors path="mobile" cssClass="help-inline" cssStyle="color: red"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <form:label path="addressLine01" for="addressLine01" cssClass="text-left">
                            <p>Address</p>
                        </form:label>
                        <div class="">
                            <div class="row">
                                <div class="row inline">
                                    <form:input id="addr1js" path="addressLine01" cssStyle="width:72%"/>
                                </div>
                                <div class="row">
                                    <form:errors path="addressLine01" cssClass="help-inline" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 7px">
                                <div class="row inline">
                                    <form:input id="addr2js" path="addressLine02" cssStyle="width:72%"/>
                                </div>
                                <div class="row">
                                    <form:errors path="addressLine02" cssClass="help-inline" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 7px">
                                <div class="row inline">
                                    <form:input id="addr3js" path="addressLine03" cssStyle="width:72%"/>
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
                                <fmt:message key="update.user.profile" bundle="${lang}"/> <span class="glyphicon glyphicon-send"></span>
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
