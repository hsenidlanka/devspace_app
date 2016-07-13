<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form:form method="post" id="loginForm" commandName="loginForm" modelAttribute="loginForm" htmlEscape="true">
    <div class="login_header_bar">
        <div class="login_header_content clearfix">
            <img class="login_header_logo" src="<c:url value="/themes/${theme}/images/logo-rewards.png" />"
                 alt="Rewards"/>

            <div class="sign_up_header">
                <fmt:message key="sc.new.to.rewards"/> <input class="sign_up_button"
                                                              type="submit" value="Register"/>
            </div>
        </div>

    </div>
    <div class="login_main_content">
        <div class="sign_in_content">
            <div class="sign_in_box">
                <img class="sign_in_logo" src="<c:url value="/themes/${theme}/images/logo-rewards.png" />"
                     alt="Rewards"/>
                <form:input cssClass="sign_in_text_box" placeholder="Username" id="username"
                            path="username" autocomplete="false" htmlEscape="true"/>

                <form:password cssClass="sign_in_text_box" placeholder="Password" id="password" path="password"
                               htmlEscape="true" autocomplete="off"/>
                <form:checkbox type="checkbox" path="keepSigned" id="keepSigned"/>
                <label for="keepSigned" class="minor_margin_left label_for_checkbox">
                    <fmt:message key="sc.keep.signed.in"/>
                </label>

                <input class="sign_in_button"
                       type="submit" value="Sign In"/>

            </div>
        </div>
    </div>
</form:form>
<script type="text/javascript">
    window.onload = function () {
        document.getElementById("username").focus();
    }
</script>



