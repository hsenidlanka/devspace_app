<!DOCTYPE html>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
</head>

<body>
<div class="panel panel-success">

    <div class="panel-heading">
        <img class="icons" src="<c:url value="/resources/images/icons/usericon.png"/>">

        <h3 style="display: inline-block;"><fmt:message key="profile.info.welcome" bundle="${lang}"/> ${name}</h3>
    </div>

    <div class="panel-body">
        <div class="container">
            <div style="height: 300px ;border-right: 1px solid #cccccc; width: 45%;" class="col-sm-6">
                <div class="form-group">
                    <h4><strong><fmt:message key="update.user.personal.info" bundle="${lang}"/></strong></h4>
                </div>
                <div style="margin-bottom: 12px; margin-top: 20px" class="row">
                    <div class="col-sm-4"><strong><fmt:message key="signup.first.name" bundle="${lang}"/></strong></div>
                    <div class="col-sm-8">${userDataProfile.firstName}</div>
                </div>
                <div style="margin-bottom: 12px;" class="row">
                    <div class="col-sm-4"><strong><fmt:message key="signup.last.name" bundle="${lang}"/></strong></div>
                    <div class="col-sm-8">${userDataProfile.lastName}</div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <strong><fmt:message key="signup.username" bundle="${lang}"/></strong>
                    </div>
                    <div class="col-sm-8">${userDataProfile.username}</div>
                </div>
            </div>

            <div style="margin-left: 45px" class="col-sm-6">

                <div class="form-group">
                    <h4><strong><fmt:message key="update.user.contact.info" bundle="${lang}"/></strong></h4>
                </div>

                <div style="margin-bottom: 12px; margin-top: 20px" class="row">
                    <div class="col-sm-4"><strong><fmt:message key="signup.email" bundle="${lang}"/></strong></div>
                    <div class="col-sm-8">${userDataProfile.email}</div>
                </div>
                <div style="margin-bottom: 12px;" class="row">
                    <div class="col-sm-4"><strong><fmt:message key="pay.mobile.no" bundle="${lang}"/></strong></div>
                    <div class="col-sm-8">${userDataProfile.mobile}</div>
                </div>

                <div style="margin-bottom: 12px;" class="row">
                    <div class="col-sm-4"><strong><fmt:message key="delivery.addr1" bundle="${lang}"/></strong></div>
                    <div class="col-sm-8">${userDataProfile.addressLine01}</div>
                </div>
                <div style="margin-bottom: 12px;" class="row">
                    <div class="col-sm-4"><strong><fmt:message key="delivery.addr2" bundle="${lang}"/></strong></div>
                    <div class="col-sm-8">${userDataProfile.addressLine02}</div>
                </div>
                <div style="margin-bottom: 12px;" class="row">
                    <div class="col-sm-4"><strong><fmt:message key="delivery.addr3" bundle="${lang}"/></strong></div>
                    <div class="col-sm-8">${userDataProfile.addressLine03}</div>
                </div>

            </div>

        </div>


        <div style="margin-top: 65px; margin-bottom: 15px" class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">

                <center>
                    <a href="update-user" class="btn btn-warning"><i class="fa fa-pencil-square-o"
                                                                     aria-hidden="true"></i> <fmt:message key="update.user.profile" bundle="${lang}"/></a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="change-password" class="btn btn-warning"><i class="fa fa-unlock-alt" aria-hidden="true"></i>
                        <fmt:message key="view.change" bundle="${lang}"/></a>
                </center>

            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>

</div>
</body>