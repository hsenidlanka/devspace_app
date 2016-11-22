<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <!--include common CSS, fonts and js-->
  <jsp:include page="adminTop.jsp"/>
  <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>
  <link href="${css2}" rel="stylesheet">

</head>
<body>
<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="header.jsp"/>

<%--urls read from system file--%>
<fmt:message key="admin.itemmanagement.itemadd.url" var="itemAdd" bundle="${bundle2}"/>
<fmt:message key="admin.itemmanagement.itemview.url" var="itemView" bundle="${bundle2}"/>

<fmt:message key="admin.categorymanage.categoryadd.url" var="categoryAdd" bundle="${bundle2}"/>
<fmt:message key="admin.categorymanage.categoryview.url" var="categoryView" bundle="${bundle2}"/>

<fmt:message key="admin.usermanage.useradd.url" var="userAdd" bundle="${bundle2}"/>
<fmt:message key="admin.usermanage.userview.url" var="userView" bundle="${bundle2}"/>
<fmt:message key="admin.usermanage.blocked.userview.url" var="blocked_userView" bundle="${bundle2}"/>

<fmt:message key="admin.packagemanagement.pckgadd.url" var="pckgAdd" bundle="${bundle2}"/>
<fmt:message key="admin.packagemanagement.pckgview.url" var="pckgView" bundle="${bundle2}"/>

<fmt:message key="admin.notificationmanagement.comments.view.url" var="commentsView" bundle="${bundle2}"/>
<fmt:message key="admin.notificationmanagement.ratings.view.url" var="ratingsView" bundle="${bundle2}"/>

<%--<spring:url value="header.jsp" var="page1"/>
<link href="${page1}" rel="import">--%>


<div class="home-title"><fmt:message key="homepage.page.title" bundle="${bundle1}"/></div>
<div class="address-bar"><fmt:message key="homepage.page.address" bundle="${bundle1}"/></div>

<center>
  <div class="container">
    <div class="panel-group" id="accordion">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h2 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
              <fmt:message key="item.itemadd.heading" bundle="${bundle1}"/>
            </a>
          </h2>
        </div>
        <div id="collapseOne" class="panel-collapse collapse ">
          <div class="panel-body">
            <ul class="nav navbar-nav">
              <li>
                <a href="<c:out value="${itemAdd}"/>" class="panel-sub-menu2" style="font-weight: bold;
                font-size: 18px;">
                  <fmt:message key="item.itemadd.breadcrumb.additem" bundle="${bundle1}"/>
                </a>
              </li>
              <li>
                <a href="<c:out value="${itemView}"/>" class="panel-sub-menu2" style="font-weight:bold;
                font-size:18px;">
                  <fmt:message key="item.itemview.breadcrumb.viewitem" bundle="${bundle1}"/>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">Package Management</a>
          </h4>
        </div>
        <div id="collapseTwo" class="panel-collapse collapse">
          <div class="panel-body">
            <ul class="nav navbar-nav">
              <li>
                <a href="<c:out value="${pckgAdd}"/>" class="panel-sub-menu2" style="font-weight:bold;
                font-size:18px;">
                <fmt:message key="package.addpackage.breadcrumb.addpkg" bundle="${bundle1}"/>
                </a>
              </li>
              <li>
                <a href="<c:out value="${pckgView}"/>" class="panel-sub-menu2" style="font-weight:bold;
                font-size:18px;">
                <fmt:message key="package.viewpackage.breadcrumb.viewpkg" bundle="${bundle1}"/>
                </a>
              </li>

            </ul>
          </div>
        </div>
      </div>


      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
              <fmt:message key="category.heading" bundle="${bundle1}"/>
            </a>
          </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse">
          <div class="panel-body">
            <ul class="nav navbar-nav">
              <li>
                <a href="<c:out value="${categoryAdd}"/>" class="panel-sub-menu2" style="font-weight:bold;
                font-size:18px;">
                  <fmt:message key="category.categoryadd.breadcrumb.addcat" bundle="${bundle1}"/>
                </a>
              </li>
              <li>
                <a href="<c:out value="${categoryView}"/>" class="panel-sub-menu2" style="font-weight:bold;
                font-size:18px;">
                  <fmt:message key="category.categoryadd.breadcrumb.viewcat" bundle="${bundle1}"/>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
              <fmt:message key="user.heading" bundle="${bundle1}"/>
            </a>
          </h4>
        </div>
        <div id="collapseFour" class="panel-collapse collapse">
          <div class="panel-body">
            <ul class="nav navbar-nav">
              <li>
                <a href="<c:out value="${userAdd}"/>" class="panel-sub-menu1" style="font-weight:bold;
                font-size:18px;">
                  <fmt:message key="user.useradd.breadcrumb.adduser" bundle="${bundle1}"/>
                </a>
              </li>
              <li>
                <a href="<c:out value="${userView}"/>" class="panel-sub-menu1" style="font-weight:bold;
                font-size:18px;">
                  <fmt:message key="user.userview.breadcrumb.viewuser" bundle="${bundle1}"/>
                </a>
              </li>
              <li>
                <a href="<c:out value="${blocked_userView}"/>" class="panel-sub-menu1" style="font-weight:bold;
                font-size:18px;">
                  <fmt:message key="user.blockuserview.breadcrumb.viewuser" bundle="${bundle1}"/>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive">Notifications Management</a>
          </h4>
        </div>
        <div id="collapseFive" class="panel-collapse collapse">
          <div class="panel-body">
            <ul class="nav navbar-nav">
              <li>
                <a href="<c:out value="${commentsView}"/>" class="panel-sub-menu2" style="font-weight:bold;
                font-size:18px;">
                  <fmt:message key="notification.commentsview.breadcrumb.view" bundle="${bundle1}"/>
                </a>
              </li>
              <li>
                <a href="<c:out value="${ratingsView}"/>" class="panel-sub-menu2" style="font-weight:bold;
                font-size:18px;">
                  <fmt:message key="notification.ratingssview.breadcrumb.view" bundle="${bundle1}"/>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>

    </div>
  </div>
</center>
</body>
</html>