<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
  <title>View Item</title>
  <!-- include common CSS, fonts and js -->
  <jsp:include page="adminTop.jsp"/>

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

<jsp:include page="header.jsp"/>

<div class="brand">
  <div style="position: relative; left: -50%;">
    Item Management
  </div>
</div>
<br>

<div>
  <div id="add-item-breadcrumb-position">
    <ul class="breadcrumb breadcrumb-menu">
      <li><a href="https://localhost:8443/users/list">Home</a></li>
      <li><a href="#">Item Management</a></li>
      <li class="active"><a href="#">View Item</a></li>
    </ul>
  </div>
</div>

<div class="form-box" id="view-item-form-box">
  <div class="panel panel-default">
    <div class="panel-heading common-form-headings">
      <h3 class="default-panel-headings">View Item</h3>
    </div>

    <div class="panel-body">

      <div class="row itemSearchBar">
        <div class="col-sm-1"></div>
        <div class="col-sm-3">
          <form:label class="control-label" style="float: right;" path="lblItmNm">
            Item Name :
          </form:label>

        </div>
        <div class="col-sm-4">
          <form:input class="form-control" id="txtViewSearchItem" type="text" path="txtSrchItem"> </form:input>
        </div>
        <div class="col-sm-3">
          <form:button type="button" class="btn btn-success" id="btnViewSearchItem"><span
                  class="glyphicon glyphicon-search"></span> Search
          </form:button>
        </div>
        <div class="col-sm-1"></div>
      </div>
      <br>

      <form:form class="form-horizontal" role="form" id="frmViewItem">
        <fieldset class="scheduler-border">
          <legend class="scheduler-border">Item Details</legend>

          <!--table of all Item details-->
          <table id="tblItems">

          </table>

        </fieldset>

        <br>

        <div class="row" align="left">
          <div class="col-sm-4"></div>
          <div class="col-sm-4"></div>
          <div class="col-sm-4">
            <form:button type="button" class="btn btn-success" id="btnViewItemBack"><span
                    class="glyphicon glyphicon-chevron-left"></span> Back to home
            </form:button>
          </div>
        </div>
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
        <form:button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                class="glyphicon glyphicon-remove"></span></form:button>
        <div align="center"><span class="glyphicon glyphicon-trash"></span> Delete Item
        </div>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <form:label id="lblDeltItmMsg" path="lbllDltItmMsg">Do you really want to delete this Item ?</form:label><br><br>

          <div align="center">
            <form:label id="lblDeltItmId" path="lblDltItmId">Item ID : xxxxxx </form:label><br>
            <form:label id="lblDeltItmName" path="lblDltItmName">Item Name : yyyyyy </form:label><br>
          </div>
        </div>
      </div>

      <div class="modal-footer" align="right">
        <form:button class="btn btn-success" type="button" value="Yes" id="btnDeltItm">Yes
        </form:button>
        <form:button class="btn btn-success" type="button" value="cancel" id="btnCnclDeltItm">
          No
        </form:button>
      </div>
    </div>
  </div>
</div>
</body>
</html>