<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>

  <jsp:include page="../adminTop.jsp"/>

  <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
  <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>
  <spring:url value="/themes/hsenid/js/categoryMgt.js" var="js1"/>

  <script src="${js1}"></script>
  <link href="${css1}" rel="stylesheet">
  <link href="${css2}" rel="stylesheet">

  <meta charset="UTF-8">
  <title>Add Category</title>

  <%-- <link rel="import" href="adminTop.html">
   <link rel="stylesheet" href="../css/add-category.css">--%>

</head>
<body>
<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
  <div style="position: relative; left: -50%;">
    <fmt:message key="category.heading" bundle="${bundle1}"/>
  </div>
</div>
<br>
<br>

<div class="breadcrumbPosition" id="userBreadcrump">
  <ul class="breadcrumb breadcrumb-menu" >

    <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
    <fmt:message key="admin.categorymanage.categoryadd.url" var="url2" bundle="${bundle2}"/>

    <li>
      <a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home" bundle="${bundle1}"/></a>
    </li>
    <li>
      <a href="<c:out value="${url1}"/>"><fmt:message key="category.breadcrumb.categorymanagement" bundle="${bundle1}"/></a>
    </li>
    <li class="active">
      <a href="<c:out value="${url2}"/>"><fmt:message key="category.categoryadd.breadcrumb.addcat" bundle="${bundle1}"/></a>
    </li>
  </ul>
</div>

<center>
  <div class="form-box" id="categoryAdd_box">
    <div class="panel panel-default">
      <div class="panel-heading common-form-headings" style="vertical-align:middle">
        <h3 class="default-panel-headings"><fmt:message key="category.categoryadd.panel.heading" bundle="${bundle1}"/></h3>
        <br>
      </div>

      <div class="panel-body">

        <ul class="nav nav-pills red">
          <li class="active"><a data-toggle="pill" href="#home">
            <fmt:message key="category.add.navpill.category" bundle="${bundle1}"/></a></li>
          <li><a data-toggle="pill" href="#menu1">
            <fmt:message key="category.add.navpill.subcategory" bundle="${bundle1}"/></a></li>
        </ul>
        <br>


        <div class="tab-content">
          <!--div to get customer info-->
          <div id="home" class="tab-pane fade in active">
            <div>
              <jsp:include page="categoryAdd.jsp"/>
            </div>
          </div>
          <!--div to present staff info-->
          <div id="menu1" class="tab-pane fade">
            <div >
              <jsp:include page="subcategoryAdd.jsp"/>
            </div>
          </div>
        </div>
      </div>


</div>
</div>
</center>
</body>
</html>