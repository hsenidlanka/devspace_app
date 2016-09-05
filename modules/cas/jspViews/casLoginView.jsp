<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:directive.include file="top.jsp" />
<form:form method="post" id="lginFrm1" cssClass="fm-v clearfix" htmlEscape="true">
    <div id="wrapper">
    <div id="login-window">
        <h2>Loginqq</h2>
        <br/>

        <%--<form:errors path="*" cssClass="error_message" id="status" element="div" />--%>

        <div class="clearfix">
            <label for="username"><spring:message code="screen.welcome.label.netid" /></label>
           <%-- <c:if test="${not empty sessionScope.openIdLocalId}">
                <strong>${sessionScope.openIdLocalId}</strong>
                <input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" />
            </c:if>

            <c:if test="${empty sessionScope.openIdLocalId}">
                <spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
            </c:if>--%>
            <form:input cssClass="text_box" id="username" path="username" autocomplete="false" htmlEscape="true"/>
        </div>

        <div class="clearfix">
            <label for="password">
                <spring:message code="screen.welcome.label.password" />
            </label>
            <spring:message code="screen.welcome.label.password.accesskey"/>
            <form:password cssClass="text_box" id="password"  path="password" htmlEscape="true" autocomplete="off" />
        </div>

        <div class="row btn-row">
            <input type="hidden" name="lt"/>
            <input type="hidden" name="execution" />
            <input type="hidden" name="_eventId" value="submit" />
        </div>

        <div class="clearfix">
            <input class="button casLoginButton" accesskey="l" tabindex="2" type="submit" value="Login"/>
        </div>

    </div>
</form:form>
<%--<script type="text/javascript">
    window.onload = function() {
        document.getElementById("username").focus();
    }
</script>--%>
<jsp:directive.include file="bottom.jsp" />

<%--

      <div id="content">
 <div id="msg" class="errors">
        <h2>Non-secure Connection</h2>
        <p>You are currently accessing CAS over a non-secure connection. Single Sign On WILL NOT WORK. In order to have single sign on work, you MUST log in over HTTPS.</p>
    </div>


<div id="cookiesDisabled" class="errors" style="display:none;">
    <h2>Browser cookies disabled</h2>
    <p>Your browser does not accept cookies. Single Sign On WILL NOT WORK.</p>
</div>




<div class="box" id="login">
    <form id="fm1" action="/cas/login" method="post">



        <h2>Enter your Username and Password</h2>

        <section class="row">
            <label for="username"><span class="accesskey">U</span>sername:</label>




                    <input id="username" name="username" class="required" tabindex="1" accesskey="u" type="text" value="" size="25" autocomplete="off"/>


        </section>

        <section class="row">
            <label for="password"><span class="accesskey">P</span>assword:</label>


            <input id="password" name="password" class="required" tabindex="2" accesskey="p" type="password" value="" size="25" autocomplete="off"/>
            <span id="capslock-on" style="display:none;"><p><img src="images/warning.png" valign="top"> CAPSLOCK key is turned on!</p></span>
        </section>

        <!--
        <section class="row check">
            <p>
                <input id="warn" name="warn" value="true" tabindex="3" accesskey="w" type="checkbox" />
                <label for="warn"><span class="accesskey">W</span>arn me before logging me into other sites.</label>
                <br/>
                <input id="publicWorkstation" name="publicWorkstation" value="false" tabindex="4" type="checkbox" />
                <label for="publicWorkstation">I am at a public workstation.</label>
                <br/>
                <input type="checkbox" name="rememberMe" id="rememberMe" value="true" tabindex="5"  />
                <label for="rememberMe">Remember Me</label>
            </p>
        </section>
        -->

        <section class="row btn-row">
            <input type="hidden" name="lt" value="LT-10-xsAW60EFwuabzJlDG3vP0nqLXrmAJD-cas01.example.org" />
            <input type="hidden" name="execution" value="6bc1595f-5c8f-43d0-89df-6350aef212fd_AAAAIgAAABAYNVHH/oRa/HuOVZj0+e3CAAAABmFlczEyOPcVcSrSyNsM0qhjQBq8yhOgQ2ykrlM0H/asbd9YYGVN9ZcSV0/PlDD7diTTXBgf40WEQsTV5NRbIGj0JZOR6r//PimW8gNfxX/3Qn7JP5Pzrw2xaCsTyZEiiUlOVzPCBZ+IxHeaia7PIXa1jaAwvDPfLAI1pKp+n8KT6tZ+zcEhuVmLRK+yQHtl9KXoDJBWs9lerFDQ3WcXYquJ+sfb9Z5KcXUqWXls9ZyIC/jiqeNSXvHb/FkLJBC1B7ssaH8lsmCyM7qSNYUbkJGBvDFzKJp1Tu7knfAPRqUuVSoVJvHw6rSw5osWxD4ARqMdaeYoWDE6miJF61sPJUbdxtWF0WiEez5T/zCVr8lWbKPeGssV39vg4FO2u/uuXqwIY1G2hdhKv4dkp7MwM5uEirQIrsoGGuHG4pomN8lvdVeKn+Jl5NZrCe9EfHNms3XaH99kPsnG6WbCndzhmo8I1dIKPhDA+o5h9bomBwyVgfyfiazPPhRO/dpYW41fKoOkXumnEzC5ZCij0OAYp6gkpJNtQfjei9FSGtJNsZDpL+cfvU3ozIc0BbzcyCAA5auxMI85XqWoEeMjIgDnRjMVtDCzAfJ7pwn4ziQdgddSuBERORz9taq9sUatfW+zf4mcSj7TWwSW3cJ4lE4fclsJm3reaU2Fm/VZcl2VcZxf1wcz7Rj82370opJ7vNuk3myzYrIA7J1I/CTFDnrbGNc7AknEe3kNue5xkLgRQUnK6vtbAoY2uVkCnwB42jLWKl0Yblz49vc06pQWnh+qV+MEeF5Cmi404urdiRIJidOlXjbtRCEru4cPoCKxzifPi36prCowL7Mtav0sDdAMXeHTftlUqVwgOuPN04KpbirkyLsH5vMPxEjhyHIo9EpZ3qddVTYX7p6nqwhfrAUYw9dIGqwaXjo85s2YesLx//8MFQgWOjFS9Snl9rWNyhxqInh+1WgDUwVugBmH4b/vjgwBLwzql/SC8DMMU8Mp2RCGbl12t86p" />
            <input type="hidden" name="_eventId" value="submit" />

            <input class="btn-submit" name="submit" accesskey="l" value="LOGIN" tabindex="6" type="submit" />
            <input class="btn-reset" name="reset" accesskey="c" value="CLEAR" tabindex="7" type="reset" />
        </section>
    <div>
</div></form>
</div>

<div id="sidebar">
    <div class="sidebar-content">
        <p>For security reasons, please Log Out and Exit your web browser when you are done accessing services that require authentication!</p>

        <div id="list-languages">




            <h3>Languages:</h3>





                    <ul>
                        <li class="first"><a href="login?locale=en">English</a></li>
                        <li><a href="login?locale=es">Spanish</a></li>
                        <li><a href="login?locale=fr">French</a></li>
                        <li><a href="login?locale=ru">Russian</a></li>
                        <li><a href="login?locale=nl">Nederlands</a></li>
                        <li><a href="login?locale=sv">Svenska</a></li>
                        <li><a href="login?locale=it">Italiano</a></li>
                        <li><a href="login?locale=ur">Urdu</a></li>
                        <li><a href="login?locale=zh_CN">Chinese (Simplified)</a></li>
                        <li><a href="login?locale=zh_TW">Chinese (Traditional)</a></li>
                        <li><a href="login?locale=de">Deutsch</a></li>
                        <li><a href="login?locale=ja">Japanese</a></li>
                        <li><a href="login?locale=hr">Croatian</a></li>
                        <li><a href="login?locale=uk">Ukranian</a></li>
                        <li><a href="login?locale=cs">Czech</a></li>
                        <li><a href="login?locale=sl">Slovenian</a></li>
                        <li><a href="login?locale=ca">Catalan</a></li>
                        <li><a href="login?locale=mk">Macedonian</a></li>
                        <li><a href="login?locale=fa">Farsi</a></li>
                        <li><a href="login?locale=ar">Arabic</a></li>
                        <li><a href="login?locale=pt_PT">Portuguese</a></li>
                        <li><a href="login?locale=pt_BR">Portuguese (Brazil)</a></li>
                        <li class="last"><a href="login?locale=pl">Polish</a></li>
                    </ul>


        </div>
    </div>
</div>




</div> <!-- END #content -->

<footer>
--%>