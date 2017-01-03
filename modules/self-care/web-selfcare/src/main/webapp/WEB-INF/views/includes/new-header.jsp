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

                        <a id="loginBtn" class="form-group-sm" data-toggle="modal" data-target="#modal-login"
                           data-backdrop="static"><img
                                src="<c:url value="/resources/images/user2.png"/>" style=""></span> </a>
                    </li>

                </c:if>
                <c:if test="${not empty username}">
                    <li class="dropdown">

                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
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
                                        <div class="col-xs-6">
                                            <a href="/web-selfcare/profile" class="btn btn-primary">View Profile</a>
                                        </div>
                                        <div class="col-xs-6">
                                            <a href="/web-selfcare/logout" class="btn btn-danger">
                                                <span style="vertical-align: middle"><i class="fa fa-power-off"
                                                                                        aria-hidden="true"></i> Log Out</span>
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

<div class="container ">

    <!-- Modal -->
    <div class="modal fade" id="modal-login" role="dialog">
        <div class="modal-dialog panel panel-success">

            <!-- Modal content-->
            <div class="modal-content">

                <div class="panel-heading">
                    <div class="row">
                        <div class="col-sm-10">
                            <img src="/web-selfcare/resources/images/logo.png" style=" width:50px;height:50px;">

                            <h3 style="margin-top: 15px;display: inline-block;">Please Log in</h3>
                        </div>
                        <div class="col-sm-2">
                            <a type="button" class="btn" data-dismiss="modal"
                               style="margin-top: 10px; margin-left: 35px; color: #888"><i
                                    class="fa fa-window-close fa-2x" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </div>

                <div class="modal-body panel-body" style="margin-top: 1px;">
                    <div id="blockedUser"></div>
                    <form>
                        <div class="form-group">
                            <div style="margin-left: 15px; margin-top: 16px" id="NotRegisteredUser" class="error-labels"></div>
                        </div>
                        <div class="form-group">
                            <label for="loginUsername" class="control-label" style="text-align: center"><h4><strong>Username</strong></h4></label>
                            <input type="text" style="border:1px solid #cccccc" id="loginUsername"
                                   class="form-control input-md" name="loginUsername"
                                   placeholder="Enter username">

                            <div style="margin-left: 15px; margin-top: 16px" id="usernameError" class="error-labels"></div>
                        </div>

                        <div class="form-group">
                            <label for="loginPassword" class="control-label" style="text-align: center"><h4><strong>Password</strong></h4></label>
                            <input type="password" style="border:1px solid #cccccc" id="loginPassword"
                                   class="form-control input-md" name="loginPassword"
                                   placeholder="Enter password">

                            <div style="margin-left: 15px; margin-top: 16px" id="passwordError2" class="error-labels"></div>
                        </div>
                        <br>
                        <center>
                            <div class="form-group">
                                <button style="width: 100%" id="loginFormSubmit" name="loginFormSubmit" type="button"
                                        class="btn btn-success" data-dismiss="modal">
                                    <i class="fa fa-sign-in" aria-hidden="true"></i> Log in
                                </button>
                            </div>
                        </center>
                    </form>
                </div>
            </div>

        </div>
    </div>

</div>