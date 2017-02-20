<!DOCTYPE html>
<head>
  <%@include file="../adminTop.jsp" %>

  <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
  <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>
  <spring:url value="/themes/hsenid/css/view_categories.css" var="css3"/>
  <spring:url value="/themes/hsenid/js/categoryMgt.js" var="js1"/>

  <script src="${js1}"></script>
  <link href="${css1}" rel="stylesheet">
  <link href="${css2}" rel="stylesheet">
  <link href="${css3}" rel="stylesheet">


  <title>Add Category</title>

</head>
<body>
<%--<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />--%>
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
  <div style="position: relative; left: -50%;">
    <fmt:message key="category.heading"/>
  </div>
</div>
<br>
<br>

<div class="breadcrumbPosition" id="userBreadcrump">
  <ul class="breadcrumb breadcrumb-menu" >

    <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
    <fmt:message key="admin.categorymanage.categoryadd.url" var="url2" bundle="${bundle2}"/>

    <li>
      <a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home" /></a>
    </li>
    <li>
      <a href="<c:out value="${url1}"/>"><fmt:message key="category.breadcrumb.categorymanagement" /></a>
    </li>
    <li class="active">
      <a href="<c:out value="${url2}"/>"><fmt:message key="category.categoryadd.breadcrumb.addcat"/></a>
    </li>
  </ul>
</div>

<center>
  <div class="form-box" id="categoryAdd_box">
    <div class="panel panel-default">
      <div class="panel-heading common-form-headings" style="vertical-align:middle">
        <h3 class="default-panel-headings"><fmt:message key="category.categoryadd.panel.heading" /></h3>
        <br>
      </div>

      <div class="panel-body">

        <ul class="nav nav-pills red">
          <li class="active"><a data-toggle="pill" href="#home">
            <fmt:message key="category.add.navpill.category" /></a></li>
          <li><a data-toggle="pill" href="#menu1">
            <fmt:message key="category.add.navpill.subcategory" /></a></li>
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