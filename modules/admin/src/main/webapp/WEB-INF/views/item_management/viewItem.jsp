<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <title>View Item</title>
  <!-- include common CSS, fonts and js -->
  <jsp:include page="../adminTop.jsp"/>

  <%-- Other css --%>
  <spring:url value="/themes/hsenid/css/itemMgt.css" var="cssItem"/>
  <link href="${cssItem}" rel="stylesheet">

  <%--  other javascripts --%>
  <spring:url value="/themes/hsenid/js/itemMgt.js" var="itemJs"/>
  <spring:url value="/themes/hsenid/js/myAlerts.js" var="myAlerts"/>
  <spring:url value="/themes/hsenid/js/tableItems.js" var="tblViewItm"/>

  <script src="${itemJs}"></script>
  <script src="${myAlerts}"></script>
  <script src="${tblViewItm}"></script>


</head>
<body>
<fmt:bundle basename="messages_en">
<jsp:include page="../header.jsp"/>

<div class="brand">
  <div style="position: relative; left: -50%;">
    <fmt:message key="item.itemview.heading"/>
  </div>
</div>
<br>

<div>
  <div id="add-item-breadcrumb-position">
    <ul class="breadcrumb breadcrumb-menu">
      <li><a href="https://localhost:8443/admin/users/list"><fmt:message key="item.itemview.breadcrumb.home"/> </a></li>
      <li><a href="#"><fmt:message key="item.itemview.breadcrumb.itemmanagement"/> </a></li>
      <li class="active"><a href="#"><fmt:message key="item.itemview.breadcrumb.viewitem"/> </a></li>
    </ul>
  </div>
</div>

<div class="form-box" id="view-item-form-box">
  <div class="panel panel-default">
    <div class="panel-heading common-form-headings">
      <h3 class="default-panel-headings"><fmt:message key="item.itemview.panel.heading"/> </h3>
    </div>

    <div class="panel-body">

      <div class="row itemSearchBar">
        <div class="col-sm-1"></div>
        <div class="col-sm-3">
          <label class="control-label" style="float: right;">
            <fmt:message key="item.itemview.searchitem.name"/>
          </label>

        </div>
        <div class="col-sm-4">
          <input class="form-control" id="txtViewSearchItem" type="text"/>
        </div>
        <div class="col-sm-3">
          <button type="button" class="btn btn-success" id="btnViewSearchItem"><span
                  class="glyphicon glyphicon-search"></span><fmt:message key="item.itemview.form.button.search"/>
          </button>
        </div>
        <div class="col-sm-1"></div>
      </div>
      <br>

      <form:form class="form-horizontal" role="form" id="frmViewItem" commandName="viewItem" modelAttribute="viewItem" method="post" action="/view_item">
        <fieldset class="scheduler-border">
          <legend class="scheduler-border"><fmt:message key="item.itemview.form.legend"/> </legend>

          <!--table of all Item details-->
          <table id="tblItems">

          </table>

        </fieldset>

        <br>

        <%--<div class="row" align="left">
          <div class="col-sm-4"></div>
          <div class="col-sm-4"></div>
          <div class="col-sm-4">
            <form:button type="button" class="btn btn-success" id="btnViewItemBack"><span
                    class="glyphicon glyphicon-chevron-left"></span> Back to home
            </form:button>
          </div>
        </div>--%>
        <br>
      </form:form>
    </div>

  </div>
</div>

<!--Modal for delete item-->
<div class="modal fade" id="itemDeleteModal">
  <div class="modal-dialog ">
    <div class="modal-content">
      <div class="modal-header item-modal-header-style">
        <button class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove"></span></button>
        <div align="center"><span class="glyphicon glyphicon-trash"></span><fmt:message key="item.deletemodal.title"/>
        </div>
      </div>
      <form:form class="form-horizontal" role="form" id="form-Item-Delete">
        <fieldset class="scheduler-border">
          <legend class="scheduler-border"><fmt:message key="item.deletemodal.warning.message"/> </legend>
      <div class="modal-body">
        <div class="form-group">
          <label id="lblDeltItmMsg"><fmt:message key="item.deletemodal.warning.question"/> </label><br><br>

          <div align="center">
            <label id="lblDeltItmId"><fmt:message key="item.deletemodal.itemid"/> xxx</label><br>
            <label id="lblDeltItmName"><fmt:message key="item.deletemodal.itemname"/> yyyyyy</label><br>
          </div>
        </div>
      </div>

      <div class="modal-footer" align="right">
        <button class="btn btn-success" type="button" value="Yes" id="btnDeltItm"><fmt:message key="item.deletemodal.button.yes"/>
        </button>
        <button class="btn btn-success" type="button" value="cancel" id="btnCnclDeltItm"><fmt:message key="item.deletemodal.button.no"/>
        </button>
      </div>
        </fieldset>
      </form:form>
    </div>
  </div>
</div>
</fmt:bundle>
</body>
</html>