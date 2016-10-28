<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title>Update user ${username}</title>

    <script>
        $(document).ready(function () {

            var username = "${username}";
            var name = "${name}";

            var title = "${title}";
            var firstName = "${firstName}";
            var lastName = "${lastName}";
            var addr1 = "${addr1}";
            var addr2 = "${addr2}";
            var addr3 = "${addr3}";
            var mobile = "${mobile}";
            var email = "${email}";

            $("#title").val(title);
            $("#firstName").val(firstName);
            $("#lastName").val(lastName);
            $("#addr1").val(addr1);
            $("#addr2").val(addr2);
            $("#addr3").val(addr3);
            $("#mobile").val(mobile);
            $("#notEmail").val(email);

        });
    </script>

</head>
<body>
<%@include file="../includes/NewHeader.jsp" %>
${email}
<div class="container" style="width: 65%">

    <form:form cssClass="well form-horizontal" action="/sendUpdateUser" method="post" modelAttribute="" id="contact_form">
        <fieldset>

            <!-- Form Name -->
            <legend>Update User Profile</legend>

            <div class="form-group">
                <form:label path="title2" for="title" cssClass="col-md-4 control-label">Title</form:label>

                <div class="col-md-4 selectContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                        <form:select path="title" id="title" name="title" cssClass="form-control">
                            <form:option value="Mr">Mr</form:option>
                            <form:option value="Mrs">Mrs</form:option>
                            <form:option value="Ms">Ms</form:option>
                            <form:option value="Dr">Dr</form:option>
                            <form:option value="Rev">Rev</form:option>
                        </form:select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <form:label path="username" for="username" cssClass="col-md-4 control-label">First Name</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:input path="username" id="notUsername" name="notUsername" placeholder="Username" cssClass="form-control" type="text"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <form:label path="firstName" for="firstName" cssClass="col-md-4 control-label">First Name</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:input path="firstName" id="firstName" name="firstName" placeholder="First Name" cssClass="form-control" type="text"/>
                    </div>
                </div>
            </div>

            <!--Last Name -->

            <div class="form-group">
                <form:label path="lastName" for="lastName" cssClass="col-md-4 control-label">Last Name</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:input path="lastName" name="lastName" id="lastName" placeholder="Last Name" cssClass="form-control" type="text"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <form:label path="email" for="email" cssClass="col-md-4 control-label">E-Mail</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                        <form:input path="email" name="email" id="notEmail" placeholder="E-Mail Address" cssClass="form-control" type="text"/>
                    </div>
                </div>
            </div>

                <%--Address with three lines--%>
            <div class="form-group">
                <form:label path="addressLine01" cssClass="col-md-4 control-label">Address</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                        <form:input path="addressLine01" id="addr1" name="addr1" placeholder="Address Line 1" cssClass="form-control" type="text"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                        <form:input path="addressLine02" id="addr2" name="addr2" placeholder="Address Line 2" cssClass="form-control" type="text"/>
                            <%--<input name="addr2" id="addr2" placeholder="Address Line 2" class="form-control" type="text">--%>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                        <form:input path="addressLine03" id="addr3" name="addr3" placeholder="Address Line 3" cssClass="form-control" type="text"/>
                            <%--<input name="addr3" id="addr3" placeholder="Address Line 3" class="form-control" type="text">--%>
                    </div>
                </div>
            </div>

                <%--Mobile number--%>
            <div class="form-group">
                <form:label path="mobile" for="mobile" modelAttribute="updateuser" cssClass="col-md-4 control-label">Mobile No</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                        <form:input path="mobile" name="mobile" id="mobile" placeholder="(94)xx-xxxxxxx" cssClass="form-control" type="text"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label"></label>

                <div class="col-md-4">
                    <button type="submit" class="btn btn-warning">Update profile <span class="glyphicon glyphicon-send"></span></button>
                </div>
            </div>

        </fieldset>
    </form:form>
</div>

</body>
</html>
