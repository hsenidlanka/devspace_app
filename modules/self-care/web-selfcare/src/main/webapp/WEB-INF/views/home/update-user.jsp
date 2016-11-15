<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%@include file="../includes/include.jsp" %>
    <title><fmt:message key="update.user.title" bundle="${lang}"/> ${username}</title>

    <script>
        $(document).ready(function () {

            var usernamejs = "${username}";
            var namejs = "${name}";

            var titlejs = "${title}";
            var firstNamejs = "${firstName}";
            var lastNamejs = "${lastName}";
            var addr1js = "${addr1}";
            var addr2js = "${addr2}";
            var addr3js = "${addr3}";
            var mobilejs = "${mobile}";
            var emailjs = "${email}";

            $('input:radio[name="titlejs"]').filter('[value=' + titlejs + ']').attr('checked', true);

//            $("#titlejs").val(titlejs);

            $("#firstNamejs").val($.trim(firstNamejs));
            $("#lastNamejs").val(lastNamejs);
            $("#addr1js").val(addr1js);
            $("#addr2js").val(addr2js);
            $("#addr3js").val(addr3js);
            $("#mobilejs").val(mobilejs);
            $("#emailjs").val(emailjs);
            $("#usernamejs").val(usernamejs);

        });


    </script>


</head>
<style>

    hr {
        margin-top: 12px;
        margin-bottom: 12px;
        box-shadow: 0 10px 10px -10px #88C470 inset !important;
    }

    .updatesuccess {
        font-size: 27px;
    }

</style>

<body>
<%@include file="../includes/new-header.jsp" %>
<div class="container" style="width: 60%">
    <div class="panel panel-success">
        <form:form action="/web-selfcare/update-user" method="post" modelAttribute="updateuser"
                   cssClass="well form-horizontal">

            <div class="panel-heading">
                <p style="font-size: xx-large;text-align: left"><fmt:message key="update.user.title"
                                                                             bundle="${lang}"/>?</p>
            </div>
            <p class="updatesuccess">${updatedMsg}</p>

            <div class="panel-body">
                <div class="form-group">
                    <label class="col-sm-3 control-label"><p><fmt:message key="signup.title" bundle="${lang}"/></p></label>

                    <div class="row col-sm-9">

                        <form:radiobutton path="title" name="titlejs" id="titlejs" value="Mr"/>Mr
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <form:radiobutton path="title" id="titlejs" name="titlejs" value="Mrs"/>Mrs
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <form:radiobutton path="title" id="titlejs" name="titlejs" value="Ms"/>Ms
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <form:radiobutton path="title" id="titlejs" value="Dr"/>Dr
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <form:radiobutton path="title" id="titlejs" value="Rev"/>Rev

                    </div>
                    <div class="row">
                        <form:errors path="title" cssClass="help-inline" cssStyle="color: red"/>
                    </div>

                </div>

                <hr>

                <div class="form-group">
                    <form:label path="firstName" for="firstName" cssClass="col-md-3 control-label">
                        <p >First Name</p>
                    </form:label>

                    <div class="col-md-9">
                        <div class="row inline">
                            <form:input id="firstNamejs" path="firstName" cssStyle="width: 72%"/>
                        </div>
                        <div class="row">
                            <form:errors path="firstName" cssClass="help-inline" cssStyle="color: red"/>
                        </div>
                    </div>
                </div>
                    <%----%>
                <hr>
                <div class="form-group">

                    <form:label path="lastName" for="lastName" cssClass="col-md-3 control-label">
                        <p>Last Name</p>
                    </form:label>

                    <div class="col-md-9">
                        <div class="row inline">
                            <form:input path="lastName" id="lastNamejs" cssStyle="width: 72%"/>
                        </div>
                        <div class="row">
                            <form:errors path="lastName" cssClass="help-inline" cssStyle="color: red"/>
                        </div>
                    </div>

                </div>
                <hr>
                <div class="form-group">
                    <form:label path="username" for="username" cssClass="control-label col-sm-3">
                        <p>Username</p>
                    </form:label>

                    <div class="col-sm-9">
                        <div class="row inline">
                            <form:input id="usernamejs" path="username" cssStyle="width:72%" readonly="true"/>
                        </div>
                        <div class="row">
                            <form:errors path="username" cssClass="help-inline" cssStyle="color: red"/>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <form:label path="email" for="email" cssClass="col-md-3 control-label">
                        <p>Email</p>
                    </form:label>

                    <div class="col-sm-9">
                        <div class="row inline">
                            <form:input id="emailjs" path="email" cssStyle="width:72%"/>
                        </div>
                        <div class="row">
                            <form:errors path="email" cssClass="help-inline" cssStyle="color: red"/>
                        </div>
                    </div>

                </div>
                <hr>
                <div class="form-group">
                    <form:label path="mobile" for="mobile" cssClass="col-md-3 control-label">
                        <p>Mobile</p>
                    </form:label>

                    <div class="col-sm-9">
                        <div class="row inline">
                            <form:input id="mobilejs" path="mobile" cssStyle="width:72%"/>
                        </div>
                        <div class="row">
                            <form:errors path="mobile" cssClass="help-inline" cssStyle="color: red"/>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <form:label path="addressLine01" for="addressLine01" cssClass="col-md-3 control-label">
                        <p>Address</p>
                    </form:label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="row inline">
                                <form:input id="addr1js" path="addressLine01" cssStyle="width:72%"/>
                            </div>
                            <div class="row">
                                <form:errors path="addressLine01" cssClass="help-inline" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="row inline">
                                <form:input id="addr2js" path="addressLine02" cssStyle="width:72%"/>
                            </div>
                            <div class="row">
                                <form:errors path="addressLine02" cssClass="help-inline" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="row inline">
                                <form:input id="addr3js" path="addressLine03" cssStyle="width:72%"/>
                            </div>
                            <div class="row">
                                <form:errors path="addressLine03" cssClass="help-inline" cssStyle="color: red"/>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
<br>
                <div class="form-group">
                    <div class="col-sm-4"></div>
                    <div class="col-sm-4">
                        <button type="submit" class="btn btn-warning">
                            <fmt:message key="update.user.profile" bundle="${lang}"/> <span
                                class="glyphicon glyphicon-send"></span>
                        </button>
                    </div>
                    <div class="col-sm-4"></div>

                </div>
            </div>
        </form:form>
    </div>
</div>
</body>

</html>
