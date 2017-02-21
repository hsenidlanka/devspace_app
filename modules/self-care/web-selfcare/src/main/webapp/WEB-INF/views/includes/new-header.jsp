<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="signup-modal.jsp" %>--%>
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

    /*----------------------------------*/
    /* -------------------- Page Styles (not required) */

    /* -------------------- Select Box Styles: bavotasan.com Method (with special adaptations by ericrasch.com) */
    /* -------------------- Source: http://bavotasan.com/2011/style-select-box-using-only-css/ */
    .styled-select {
        /*background: url(http://i62.tinypic.com/15xvbd5.png) no-repeat 96% 0;*/
        height: 29px;
        overflow: hidden;
        width: 240px;
    }

    .styled-select select {
        background: transparent;
        border: none;
        font-size: 14px;
        height: 29px;
        padding: 5px; /* If you add too much padding here, the options won't show in IE */
        width: 268px;
    }

    .styled-select.slate {
        background: url(http://i62.tinypic.com/2e3ybe1.jpg) no-repeat right center;
        height: 34px;
        width: 240px;
    }

    .styled-select.slate select {
        border: 1px solid #ccc;
        font-size: 16px;
        height: 34px;
        width: 268px;
    }

    /* -------------------- Rounded Corners */
    .rounded {
        -webkit-border-radius: 20px;
        -moz-border-radius: 20px;
        border-radius: 20px;
    }

    .semi-square {
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
    }

    /* -------------------- Colors: Background */
    .slate {
        background-color: #ddd;
    }

    .green {
        background-color: #779126;
    }

    .blue {
        background-color: #3b8ec2;
    }

    .yellow {
        background-color: #eec111;
    }

    .black {
        background-color: #000;
    }

    /* -------------------- Colors: Text */
    .slate select {
        color: #000;
    }

    .green select {
        color: #fff;
    }

    .blue select {
        color: #fff;
    }

    .yellow select {
        color: #000;
    }

    .black select {
        color: #fff;
    }

    /* -------------------- Select Box Styles: danielneumann.com Method */
    /* -------------------- Source: http://danielneumann.com/blog/how-to-style-dropdown-with-css-only/ */
    #mainselection select {
        border: 0;
        color: #EEE;
        background: transparent;
        font-size: 20px;
        font-weight: bold;
        padding: 2px 10px;
        width: 378px;
        *width: 350px;
        *background: #58B14C;
        -webkit-appearance: none;
    }

    #mainselection {
        overflow: hidden;
        width: 350px;
        -moz-border-radius: 9px 9px 9px 9px;
        -webkit-border-radius: 9px 9px 9px 9px;
        border-radius: 9px 9px 9px 9px;
        box-shadow: 1px 1px 11px #330033;
        background: #58B14C url("http://i62.tinypic.com/15xvbd5.png") no-repeat scroll 319px center;
    }

    /* -------------------- Select Box Styles: stackoverflow.com Method */
    /* -------------------- Source: http://stackoverflow.com/a/5809186 */
    select#soflow, select#soflow-color {
        -webkit-appearance: button;
        -webkit-border-radius: 2px;
        -webkit-box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.1);
        -webkit-padding-end: 20px;
        -webkit-padding-start: 2px;
        -webkit-user-select: none;
        background-image: url(http://i62.tinypic.com/15xvbd5.png), -webkit-linear-gradient(#FAFAFA, #F4F4F4 40%, #E5E5E5);
        background-position: 97% center;
        background-repeat: no-repeat;
        border: 1px solid #AAA;
        color: #555;
        font-size: inherit;
        margin: 20px;
        overflow: hidden;
        padding: 5px 10px;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 300px;
    }

    select#soflow-color {
        color: #fff;
        background-image: url(http://i62.tinypic.com/15xvbd5.png), -webkit-linear-gradient(#779126, #779126 40%, #779126);
        background-color: #779126;
        -webkit-border-radius: 20px;
        -moz-border-radius: 20px;
        border-radius: 20px;
        padding-left: 15px;
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
                <li><a href="/web-selfcare/contact-us"><fmt:message key="new.header.contact" bundle="${lang}"/></a></li>
                <li><a href="/web-selfcare/about-us"><fmt:message key="new.header.about" bundle="${lang}"/></a></li>

                <li>
                    <a href="/web-selfcare/shopping-cart"><img src="<c:url value="/resources/images/shopping-cart.png"/>" style="padding-left: 30px;vertical-align: top;"><span class="badge"><c:out value="${fn:length(cartItemsMap)}"/></span></a>
                </li>
                <c:if test="${empty username}">
                    <li>
                        <a href="/web-selfcare/register" id="signUpBtn" class="form-group" data-toggle="modal" ><img src="<c:url value="/resources/images/signup.png"/>" ></a>
                    </li>

                    <li>

                        <a id="loginBtn" class="form-group-sm" data-toggle="modal" data-target="#modal-login" data-backdrop="static"><img src="<c:url value="/resources/images/user2.png"/>" style=""></span> </a>
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
                                            <p class="text-center"><strong><fmt:message key="new.header.welcome" bundle="${lang}"/></strong></p>
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
                                            <a href="/web-selfcare/profile" class="btn btn-primary"><fmt:message key="new.header.view.profile" bundle="${lang}"/></a>
                                        </div>
                                        <div class="col-xs-6">
                                            <a href="/web-selfcare/logout" class="btn btn-danger"><span style="vertical-align: middle"><i class="fa fa-power-off" aria-hidden="true"></i><fmt:message key="new.header.log.out" bundle="${lang}"/></span></a>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>

                    </li>
                </c:if>
                <li>
                    <div style="margin-top: 15px;">
                        <label><fmt:message key="header.language" bundle="${lang}"/></label>
                        <select id="lang-select" class="styled-select black" style="width: 100px;color: white;font-size: 12px;">
                            <option value="en">English</option>
                            <option value="ja">Japanese</option>
                        </select>
                    </div>
                </li>
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
                            <h3 style="margin-top: 15px;display: inline-block;"><fmt:message key="login.modal.please" bundle="${lang}"/></h3>
                        </div>
                        <div class="col-sm-2">
                            <a type="button" class="btn" data-dismiss="modal" style="margin-top: 10px; margin-left: 35px; color: #888"><i class="fa fa-window-close fa-2x" aria-hidden="true"></i></a>
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
                            <label for="loginUsername" class="control-label" style="text-align: center"><h4><strong><fmt:message key="signup.username" bundle="${lang}"/></strong></h4></label>
                            <input type="text" style="border:1px solid #cccccc" id="loginUsername" class="form-control input-md" name="loginUsername" placeholder="Enter username">

                            <div style="margin-left: 15px; margin-top: 16px" id="usernameError" class="error-labels"></div>
                        </div>

                        <div class="form-group">
                            <label for="loginPassword" class="control-label" style="text-align: center"><h4><strong><fmt:message key="signup.password" bundle="${lang}"/></strong></h4></label>
                            <input type="password" style="border:1px solid #cccccc" id="loginPassword" class="form-control input-md" name="loginPassword" placeholder="Enter password">
                            <div style="margin-left: 15px; margin-top: 16px" id="passwordError2" class="error-labels"></div>
                        </div>
                        <br>
                        <center>
                            <div class="form-group">
                                <button style="width: 100%" id="loginFormSubmit" name="loginFormSubmit" type="button" class="btn btn-success" data-dismiss="modal"><i class="fa fa-sign-in" aria-hidden="true"></i> <fmt:message key="new.header.login" bundle="${lang}"/></button>
                            </div>
                        </center>
                    </form>
                </div>
            </div>

        </div>
    </div>

</div>