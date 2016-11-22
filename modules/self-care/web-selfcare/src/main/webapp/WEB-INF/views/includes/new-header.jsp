<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="signup-modal.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
                        <a class="form-group" data-toggle="modal" data-target="#modal-signup"><img
                                src="<c:url value="/resources/images/signup.png"/>" style=""></span>
                        </a>
                    </li>

                    <li>

                        <a id="loginBtn" class="form-group-sm" data-toggle="modal" data-target="#modal-login"><img src="<c:url value="/resources/images/user2.png"/>" style=""></span> </a>
                    </li>

                </c:if>
                <c:if test="${not empty username}">
                    <li>
                        <div class="dropdown">
                            <button class="btn btn-lg dropdown-toggle" type="button" data-toggle="dropdown">${name}<span
                                    class="caret"></span></button>

                            <ul class="dropdown-menu ">
                                <li><a class="btn-lg" href="/web-selfcare/update-user"><fmt:message key="update.user.profile" bundle="${lang}"/></a></li>
                                <li><a class="btn-lg" href="/web-selfcare/logout"><fmt:message key="new.header.log.out" bundle="${lang}"/></a></li>
                            </ul>
                        </div>
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

<div id="modal-login" class="modal-login modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content" >

            <div class="modal-header">
                <div class="panel-heading" style="width: 87%">
                    <div class="row">
                        <div class="col-sm-4">
                            <p style="font-size: xx-large;text-align: left"><fmt:message key="new.header.login" bundle="${lang}"/></p>
                        </div>
                        <div class="col-sm-5"></div>
                        <div class="col-sm-3">
                            <button data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                    </div>
                </div>

            </div>

            <div class="modal-body" style="width: 87%">
                <form name="loginForm" id="loginForm" novalidate="novalidate" action="3" method="post">


                    <div class="row form-group">
                        <div class="col-sm-2">
                            <label class="control-label" for="loginUsername">Username</label>
                        </div>
                        <div class="">
                            <input id="loginUsername" style="width:60%;" class="col-sm-10" min="3" name="loginUsername"
                                   required="" type="text" data-error-msg="Username required !!!">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-2"></div>
                        <div id="usernameError" class="error-labels col-sm-10" ></div>
                        <%--<div class="col-sm-4"></div>--%>
                    </div>

                    <div class="row  form-group">
                        <div class="col-sm-2"><label for="loginPassword">Password</label></div>
                        <input id="loginPassword" style="width:60%;" name="loginPassword" class="col-sm-10" min="3"
                               required="" data-error-msg="Please provide Valid password !!!" type="password">
                    </div>

                    <div class="row">
                        <div class="col-sm-2"></div>
                        <div id="passwordError" class="error-labels col-sm-10"></div>
                        <%--<div class="col-sm-4"></div>--%>
                    </div>

                    <div class="row">
                        <div class="col-sm-4"></div>
                        <div class="col-sm-3">
                            <button id="loginFormSubmit" name="loginFormSubmit" type="button"
                                    class="btn btn-success"><fmt:message key="new.header.login" bundle="${lang}"/>
                            </button>
                        </div>
                        <div class="col-sm-6"></div>
                    </div>
                    <div class="row"><br></div>
                    <div class="row">
                        <div class="col-sm-10">
                            <p align="center"><a href="#">Forget my password?</a></p>
                        </div>
                        <div class="col-sm-2"></div>
                    </div>
                </form>

                <!-- end-account -->
                <div class="clear"></div>

            </div>
            <!------------------------------signup modal --------------------------------------------->
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