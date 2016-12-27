<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="signup-modal.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
    .navbar-login {
        width: 305px;
        padding: 10px;
        padding-bottom: 0px;
    }

    .navbar-login-session {
        padding: 10px;
        padding-bottom: 0px;
        padding-top: 0px;
    }

    .icon-size {
        font-size: 87px;
    }
</style>

<nav class="navbar navbar-default ">
    <div class="container-fluid">
        <div class="col-sm-4"><a href="/web-selfcare/home"> <img src="<c:url value="/resources/images/logo.png"/>"
                                                                 style="width:75px;height:65px;"></a></div>

        <div style="padding: 10px"></div>
        <div>

        </div>
        <% request.setAttribute("cartItemsMap", request.getSession().getAttribute("cartItems")); %>
        <div class="col-sm-8">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/web-selfcare/menu"><fmt:message key="new.header.menu" bundle="${lang}"/></a></li>
                <li><a href="/web-selfcare/locations"><fmt:message key="new.header.location" bundle="${lang}"/></a></li>
                <li><a href="/web-selfcare/contact-us"><fmt:message key="new.header.contact" bundle="${lang}"/></a></li>
                <li><a href="/web-selfcare/about-us"><fmt:message key="new.header.about" bundle="${lang}"/></a></li>

                <li>
                    <a href="/web-selfcare/shopping-cart"><img
                            src="<c:url value="/resources/images/shopping-cart.png"/>"
                            style="padding-left: 30px;vertical-align: top;"><span class="badge"><c:out
                            value="${fn:length(cartItemsMap)}"/></span></a>
                </li>
                <c:if test="${empty username}">
                    <li>
                        <a id="signUpBtn" class="form-group" data-toggle="modal" data-target="#modal-signup"><img
                                src="<c:url value="/resources/images/signup.png"/>" style=""></a>
                    </li>

                    <li>

                        <a id="loginBtn" class="form-group-sm" data-toggle="modal" data-target="#modal-login" data-backdrop="static"><img
                                src="<c:url value="/resources/images/user2.png"/>" style=""></span> </a>
                    </li>

                </c:if>
                <c:if test="${not empty username}">
                    <li class="dropdown">

                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <%--<span class="glyphicon glyphicon-user"></span> --%>
                            <strong>${name}</strong>
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </a>

                        <ul class="dropdown-menu">
                            <li>
                                <div class="navbar-login">
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <p class="text-center">
                                                <span class="glyphicon glyphicon-user icon-size"></span>
                                            </p>
                                        </div>
                                        <div class="col-lg-8">
                                            <p class="text-center"><strong>Welcome Back !</strong></p>

                                            <p class="text-center"><strong>${name}</strong></p>

                                            <p class="text-center small">${email}</p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="navbar-login navbar-login-session">
                                    <div class="row">
                                        <div class="col-xs-6" >
                                            <a href="/web-selfcare/profile" class="btn btn-primary" >View Profile</a>
                                        </div>
                                        <div class="col-xs-6" >
                                                <a href="/web-selfcare/logout" class="btn btn-danger">
                                                    <span style="vertical-align: middle"><i class="fa fa-power-off" aria-hidden="true"></i> Log Out</span>
                                                </a>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>

                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<style>
    body.modal-open {
        overflow: auto;
    }
</style>

<!------------------------------login modal--------------------------->

<script>
    (function ($) {
        "use strict";
        function centerModal() {
            $(this).css('display', 'block');
            var $dialog = $(this).find(".modal-dialog"),
                    offset = ($(window).height() - $dialog.height()) / 2,
                    bottomMargin = parseInt($dialog.css('marginBottom'), 10);

            // Make sure you don't hide the top part of the modal w/ a negative margin if it's longer than the screen height, and keep the margin equal to the bottom margin of the modal
            if (offset < bottomMargin) offset = bottomMargin;
            $dialog.css("margin-top", offset);
        }

        $(document).on('show.bs.modal', '.modal', centerModal);
        $(window).on("resize", function () {
            $('.modal:visible').each(centerModal);
        });
    }(jQuery));
</script>

<div class="container">

    <!-- Modal -->
    <div class="modal fade" id="modal-login" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">

                <div class="panel-heading">
                    <div class="row">
                        <div class="col-sm-4">
                            <img src="/web-selfcare/resources/images/logo.png" style="width:50px;height:50px;">
                        </div>

                        <div class="col-sm-6">
                            <h3 style="margin-top: 15px;">Please Log in</h3>
                        </div>
                        <div class="col-sm-2">
                            <a type="button" class="btn" data-dismiss="modal" style="margin-top: 10px; margin-left: 35px"><i class="fa fa-window-close fa-2x" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </div>

                <div class="modal-body" style="margin-top: 10px; margin-bottom: 10px">
                    <div id="blockedUser">

                    </div>
                    <br>
                    <form>
                        <div class="row">
                            <div class="row">
                                <label for="loginUsername" class="col-sm-4" style="text-align: left">Username</label>
                                <input id="loginUsername" class="col-sm-8" name="loginUsername">
                            </div>

                            <div style="margin-top: 20px; margin-left: 200px" id="usernameError" class="error-labels"></div>

                        </div>

                        <div class="row">
                            <label for="loginPassword" class="col-sm-4" style="text-align: left">Password</label>
                            <input id="loginPassword" class="col-sm-8" name="loginPassword">
                            <div style="margin-top: 45px; margin-left: 200px;" id="passwordError2" class="error-labels"></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer" style="margin-top: 5px;">
                    <center>
                        <div class="row">
                            <button id="loginFormSubmit" name="loginFormSubmit" type="button" class="btn btn-success" data-dismiss="modal">
                                <i class="fa fa-sign-in" aria-hidden="true"></i> Log in
                            </button>
                        </div>
                    </center>
                </div>
            </div>

        </div>
    </div>

</div>


<%--Login Successful modal--%>

<div class="modal fade" id="registerSuccesful" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">

            <div class="modal-body">
                <p>User Account Creation Successful</p><br>

                <p>Please login </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
<%--Login Successful modal end--%>