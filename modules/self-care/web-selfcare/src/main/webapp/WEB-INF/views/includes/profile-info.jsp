<!DOCTYPE html>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <%--<script>
        function load_update_profile() {
            document.getElementById("content").innerHTML='<object type="text/html" data="update-user" style="width:105%; height: 85%;"></object>';
        }
    </script>--%>
</head>

<body>
<div class="panel panel-success">

    <div class="panel-heading">
        <img class="icons" src="<c:url value="/resources/images/icons/usericon.png"/>">

        <h3 style="display: inline-block;">Welcome ${name}</h3>
    </div>

    <div class="panel-body">
        <div class="container">
            <div style="height: 300px ;border-right: 1px solid #cccccc; width: 45%;" class="col-sm-6">
                <div class="form-group">
                    <h4><strong><fmt:message key="update.user.personal.info" bundle="${lang}"/></strong></h4>
                </div>
                <div style="margin-bottom: 12px; margin-top: 20px" class="row">
                    <div class="col-sm-4"><strong>First Name</strong></div>
                    <div class="col-sm-8">${userDataProfile.firstName}</div>
                </div>
                <div style="margin-bottom: 12px;" class="row">

                    <div class="col-sm-4"><strong>Last Name</strong></div>
                    <div class="col-sm-8">${userDataProfile.lastName}</div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <strong>Username</strong>
                    </div>
                    <div class="col-sm-8">${userDataProfile.username}</div>
                </div>
            </div>

            <div style="margin-left: 45px" class="col-sm-6">

                <div class="form-group">
                    <h4><strong><fmt:message key="update.user.contact.info" bundle="${lang}"/></strong></h4>
                </div>

                <div style="margin-bottom: 12px; margin-top: 20px" class="row">
                    <div class="col-sm-4"><strong>Email</strong></div>
                    <div class="col-sm-8">${userDataProfile.email}</div>
                </div>
                <div style="margin-bottom: 12px;" class="row">
                    <div class="col-sm-4"><strong>Mobile</strong></div>
                    <div class="col-sm-8">${userDataProfile.mobile}</div>
                </div>

                <div style="margin-bottom: 12px;" class="row">
                    <div class="col-sm-4"><strong>Address Line 1</strong></div>
                    <div class="col-sm-8">${userDataProfile.addressLine01}</div>
                </div>
                <div style="margin-bottom: 12px;" class="row">
                    <div class="col-sm-4"><strong>Address Line 2</strong></div>
                    <div class="col-sm-8">${userDataProfile.addressLine02}</div>
                </div>
                <div style="margin-bottom: 12px;" class="row">
                    <div class="col-sm-4"><strong>Address Line 3</strong></div>
                    <div class="col-sm-8">${userDataProfile.addressLine03}</div>
                </div>

            </div>

        </div>


        <div style="margin-top: 65px; margin-bottom: 15px" class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">

                <center>
                    <a href="update-user" class="btn btn-warning"><i class="fa fa-pencil-square-o"
                                                                     aria-hidden="true"></i> Update Profile</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="update-user" class="btn btn-warning"><i class="fa fa-unlock-alt" aria-hidden="true"></i>
                        Change Password</a>
                </center>

            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>

</div>
</body>