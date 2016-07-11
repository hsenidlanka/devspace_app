<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:directive.page contentType="text/html;charset=windows-1252"/>
<jsp:directive.page import="java.util.Date"/>
<jsp:directive.page import="java.text.SimpleDateFormat"/>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.User" %>

<fmt:bundle basename="messages">
    <div class="maincontent" id="wrapper">
        <script type="text/javascript">
            function logoutConfirm(url) {
                window.location.replace(url);
            }
        </script>
        <div class="topnav">
            <div class="logo">
                <a href="/campaign-management"> <img src="<c:url value="/themes/${theme}/images/logo-rewards.png" />" alt="" width="114" /> </a>
                <img src="<c:url value="/themes/${theme}/images/company-logo.png" />" alt="" width="50" height="50"/>

            </div>
            <div class="quicklinks">
                <ul>
                    <%
                        if (request.getParameter("page")!=null) {
                            out.print("<li><a href=\"\">" + request.getParameter("page") + "</a></li>");
                        } %>
                     <%
                        if (request.getParameter("subPage")!=null) {
                            out.print("<li><a href=\"\">" + request.getParameter("subPage") + "</a></li>");
                        } %>
                </ul>
            </div>
            <div class="top-right"><fmt:message key="header.username"/><authz:authentication property="principal.username"/>
                <%User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                  out.print(user.getUsername());%> | <a
                    href="/admin">Admin</a> | <a
                        href="/viewer">Reporting</a> | <a
                        href="/customercare">Customercare</a> | <a
                        href="<c:url value="/j_spring_security_logout" />">Logout</a>    <br/>
                <jsp:useBean id="now" class="java.util.Date"/>
                <fmt:formatDate value="${now}" pattern="EEEE, dd/MM/yyyy"/>

            </div>
            <%--<div class="toplinks">
                <map name="Map" id="Map">
                    <jsp:element name="area">
                        <jsp:attribute name="shap">rect</jsp:attribute>
                        <jsp:attribute name="coords">77,75,136,105</jsp:attribute>
                        <jsp:attribute name="href">#</jsp:attribute>
                        <jsp:attribute name="value">Expand all the tabs</jsp:attribute>
                        <jsp:attribute name="onclick">myMenu.expandAll()</jsp:attribute>
                    </jsp:element>
                    <jsp:element name="area">
                        <jsp:attribute name="shap">rect</jsp:attribute>
                        <jsp:attribute name="coords">152,75,215,106</jsp:attribute>
                        <jsp:attribute name="href">#</jsp:attribute>
                        <jsp:attribute name="value">Collapse all</jsp:attribute>
                        <jsp:attribute name="onclick">myMenu.collapseAll()</jsp:attribute>
                    </jsp:element>
                    <jsp:element name="area">
                        <jsp:attribute name="shap">rect</jsp:attribute>
                        <jsp:attribute name="coords">761,74,826,105</jsp:attribute>
                        <jsp:attribute name="href">home.html</jsp:attribute>
                        <jsp:attribute name="alt">Home</jsp:attribute>
                    </jsp:element>
                    <jsp:element name="area">
                        <jsp:attribute name="shap">rect</jsp:attribute>
                        <jsp:attribute name="coords">844,73,902,105</jsp:attribute>
                        <jsp:attribute name="href">#</jsp:attribute>
                        <jsp:attribute name="alt">Help</jsp:attribute>
                    </jsp:element>
                    <jsp:element name="area">
                        <jsp:attribute name="shap">rect</jsp:attribute>
                        <jsp:attribute name="coords">917,72,984,105</jsp:attribute>
                        <jsp:attribute name="href">logout.html</jsp:attribute>
                        <jsp:attribute name="alt">Logout</jsp:attribute>
                    </jsp:element>
                </map>
            </div>--%>
        </div>
    </div>
</fmt:bundle>