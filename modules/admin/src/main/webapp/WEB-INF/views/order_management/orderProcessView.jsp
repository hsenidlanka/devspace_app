<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <title>Order Process</title>

  <!-- include common CSS, fonts and js -->
  <jsp:include page="../adminTop.jsp"/>

  <spring:url value="/themes/hsenid/css/orderMgt.css" var="cssOrder"/>
  <link href="${cssOrder}" rel="stylesheet">

  <spring:url value="/themes/hsenid/js/orderProcess_table.js" var="ordertable"/>
  <script src="${ordertable}"></script>

  <spring:url value="/themes/hsenid/js/orderProcessPagination.js" var="ordertablePagination"/>
  <script src="${ordertablePagination}"></script>

</head>
<body>

<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
  <div style="position: relative; left: -50%;">
    <fmt:message key="order.heading" bundle="${bundle1}"/>
  </div>
</div>
<br>

<%--breadcrump--%>
<div>
  <div id="view-order-breadcrumb-position">
    <ul class="breadcrumb breadcrumb-menu">
      <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
      <fmt:message key="admin.ordermanagement.view.url" var="url2" bundle="${bundle2}"/>

      <li><a href="<c:out value="${url1}"/>"><fmt:message key="orderprocess.breadcrumb.home" bundle="${bundle1}"/></a></li>
      <li><a href="<c:out value="${url1}"/>"><fmt:message key="order.heading" bundle="${bundle1}"/> </a></li>
      <li class="active"><a href="<c:out value="${url2}"/>"><fmt:message key="orderprocess.breadcrumb.view" bundle="${bundle1}"/> </a></li>
    </ul>
  </div>
</div>


<%--table body--%>
<div class="form-box" id="process-order">
  <div class="panel panel-default">
    <div class="panel-heading  common-form-headings">
      <h3 class="default-panel-headings">
        <fmt:message key="orderprocess.delivery.process.view.table" bundle="${bundle1}"/>
      </h3>
    </div>

    <div class="panel-body">
      <div class="row itemSearchBar">
        <div class="col-sm-1"></div>
        <div class="col-sm-3">
          <label class="control-label" style="float: right;">
            <fmt:message key="orderprocess.order.id" bundle="${bundle1}"/>
          </label>

        </div>
        <div class="col-sm-4">
          <input class="form-control" id="txtViewSearchOrdr" type="text"/>
        </div>
        <div class="col-sm-3">
          <button type="button" class="btn btn-success" id="btnViewSearchOrdr"><span
                  class="glyphicon glyphicon-search"></span> Search
          </button>
        </div>
        <div class="col-sm-1"></div>
      </div>
      <br>

      <%--<form:form class="form-horizontal" role="form" id="frmViewOdr" action="" method="post">--%>
        <fieldset class="scheduler-border">
          <legend class="scheduler-border"><fmt:message key="orderprocess.form.legend" bundle="${bundle1}"/></legend>

          <!--table of order details-->
          <table id="tableprocessOrder">

          </table>

          <div id="pagination1Delivery" class="text-center">
          </div>
          <div id="pagination2Delivery" class="text-center">
          </div>
        </fieldset>
      <%--</form:form>--%>
    </div>

  </div>
</div>

</body>
</html>
