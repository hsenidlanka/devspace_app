<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="admin.login.page.title"/></title>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="css/cas.css" rel="stylesheet"/>
    <link href="css/cass.css" rel="stylesheet"/>
    <link href="css/fonts.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />

<div id="container">
<div class="home-title"><spring:message code="admin.panel.banner"/></div>
<div class="address-bar"><spring:message code="admin.panel.address"/></div>
<div id="content">
