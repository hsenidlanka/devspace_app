<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title>Update user ${username}</title>

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

            $("#titlejs").val(titlejs);
            $("#firstNamejs").val(firstNamejs);
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
<body>

<%@include file="../includes/NewHeader.jsp" %>

<div class="container" style="width: 70%">

    <form:form action="/web-selfcare/updateuser" method="post" modelAttribute="updateuser" cssClass="well form-horizontal">

        <legend>Update profile</legend>
        <br>
        <center>${updatedMsg}</center>
        <br>
        <div class="form-group">
            <label class="col-md-4 control-label">State</label>
            <div class="col-md-4" style="width: 36%">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                    <form:select path="title" id="titlejs" cssClass="form-control selectpicker">
                        <form:option value="Mr">Mr</form:option>
                        <form:option value="Mrs">Mrs</form:option>
                        <form:option value="Ms">Ms</form:option>
                        <form:option value="Dr">Dr</form:option>
                        <form:option value="Rev">Rev</form:option>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="title" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="username" for="username" cssClass="col-md-4 control-label">Username</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <form:input id="usernamejs" path="username" readonly="true"/>
                    <div class="has-error">
                        <form:errors path="username" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="firstName" for="firstName" cssClass="col-md-4 control-label">First Name</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <form:input id="firstNamejs" path="firstName"/>
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="lastName" for="lastName" cssClass="col-md-4 control-label">Last Name</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <form:input path="lastName" id="lastNamejs" />
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="email" for="email" cssClass="col-md-4 control-label">Email</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                    <form:input id="emailjs" path="email"/>
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="mobile" for="mobile" cssClass="col-md-4 control-label">Mobile</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                    <form:input id="mobilejs" path="mobile"/>
                    <div class="has-error">
                        <form:errors path="mobile" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="addressLine01" for="addressLine01" cssClass="col-md-4 control-label">Address</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                    <form:input id="addr1js" path="addressLine01"/>
                    <div class="has-error">
                        <form:errors path="addressLine01" class="help-inline"/>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                    <form:input id="addr2js" path="addressLine02"/>
                    <div class="has-error">
                        <form:errors path="addressLine02" class="help-inline"/>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                    <form:input id="addr3js" path="addressLine03"/>
                    <div class="has-error">
                        <form:errors path="addressLine03" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label"></label>

            <div class="col-md-4">
                <button type="submit" class="btn btn-warning" >Update Profile<span class="glyphicon glyphicon-send"></span></button>
            </div>
        </div>
    </form:form>
</div>


</body>
</html>
