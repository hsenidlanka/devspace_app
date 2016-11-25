<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <title>Add New Item</title>
  <!-- include common CSS, fonts and js -->
  <jsp:include page="../adminTop.jsp"/>

  <%-- Other css --%>
  <spring:url value="/themes/hsenid/css/itemMgt.css" var="cssItem"/>
  <link href="${cssItem}" rel="stylesheet">

  <%--  other javascripts --%>

  <spring:url value="/themes/hsenid/js/itemMgt.js" var="itemJs"/>
  <spring:url value="/themes/hsenid/js/myAlerts.js" var="myAlerts"/>

  <script src="${itemJs}"></script>
  <script src="${myAlerts}"></script>


</head>
<body>

<fmt:bundle basename="messages_en">
<jsp:include page="../header.jsp"/>

<div class="brand">
  <div style="position: relative; left: -50%;">
   <fmt:message key="item.itemadd.heading"/>
  </div>
</div>
<br>

<div>
  <div id="add-item-breadcrumb-position">
    <ul class="breadcrumb breadcrumb-menu">
      <li><a href="https://localhost:8443/admin/users/list"><fmt:message key="item.itemadd.breadcrumb.home"/></a></li>
      <li><a href="#"><fmt:message key="item.itemadd.breadcrumb.itemmanagement"/></a></li>
      <li class="active"><a href="#"><fmt:message key="item.itemadd.breadcrumb.additem"/></a></li>
    </ul>
  </div>
</div>

<div class="form-box" id="add-item-form-box">
  <div class="panel panel-default">
    <div class="panel-heading common-form-headings">
      <h3 class="default-panel-headings"><fmt:message key="item.itemadd.panel.heading"/></h3>
    </div>
    <div class="panel-body">

      <form:form class="form-horizontal" role="form" id="frmAddItem" action="/admin/items/add_item" method="post" enctype="multipart/form-data">
        <fieldset class="scheduler-border">
          <legend class="scheduler-border"><fmt:message key="item.itemadd.form.legend"/></legend>


          <div class="form-group">
            <div class="row">
              <label class="col-xs-3 control-label"><fmt:message key="item.itemadd.form.category"/></label>

              <div class="col-xs-5">

                      <form:select class="form-control" id="selectCat" path="categoryName">
                        <form:option value="-" label="--Select Category--"/>
                         <c:forEach var="list" items="${listCat}">
                           <form:option id="${list}" value="${list}">${list}</form:option>
                         </c:forEach>
                      </form:select>
                <span id="catErr" class="input-group-error"></span>
              </div>

              <div class="col-xs-4">
                <button type="button" class="btn btn-success" id="btnAddCat"><span class="glyphicon glyphicon-plus"></span><fmt:message key="item.itemadd.form.category.addnew"/> </button>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label class="col-xs-3 control-label"><fmt:message key="item.itemadd.form.subcategory"/> <div id="output"></div>
              </label>

              <div class="col-xs-5">
                <form:select class="form-control" id="selectSubCat" path="subCategoryName">

                </form:select>
                <span id="subCatErr" class="input-group-error"></span>
              </div>

              <div class="col-xs-4">
                <button type="button" class="btn btn-success" id="btnAddSubCat"
                        onclick="window.location='#'"><span
                        class="glyphicon glyphicon-plus"></span><fmt:message key="item.itemadd.form.subcategory.addnew"/></button>
              </div>
            </div>
          </div>


          <div class="form-group">
            <div class="row">
              <label for="txtItemName" class="col-xs-3 control-label"><fmt:message key="item.itemadd.form.itemname"/></label>

              <div class="col-xs-5">
                <form:input class="form-control" id="txtItemName" type="text" path="itemName" required="true"/>
              </div>
              <div class="col-xs-4">
                <span id="itmNmErr" class="input-group-error"></span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label class="col-xs-3 control-label"><fmt:message key="item.itemadd.form.itemprice"/></label>

              <div class="col-xs-5">

                <div class="row item-tbl-row item-tbl-hdr" style="text-align: center;">
                  <div class="col-xs-5">
                    <strong><fmt:message key="item.itemadd.form.item.check.size"/></strong>
                  </div>
                  <div class="col-xs-7">
                    <strong><fmt:message key="item.itemadd.form.item.text.price"/></strong>
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxReg">

                  <div class="col-xs-5">
                    <label class="checkbox-inline">
                      <form:checkbox value="regular" class="checkbox sizechkbx" path="size" label="Regular" id="chkReg" onclick="disableTxt(this, 'txtRegPrc')"/>
                    </label>
                  </div>
                  <div class="col-xs-7">
                    <form:input type="text" class="form-control price" path="price" id="txtRegPrc" disabled="true"/>
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxMed">

                  <div class="col-xs-5">
                    <label class="checkbox-inline">
                      <form:checkbox value="medium" class="checkbox sizechkbx" path="size" label="Medium" onclick="disableTxt(this, 'txtMedPrc')"/>
                    </label>
                  </div>
                  <div class="col-xs-7">
                    <form:input type="text" class="form-control price" path="price" id="txtMedPrc" disabled="true"/>
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxLrg">

                  <div class="col-xs-5">
                    <label class="checkbox-inline">
                      <form:checkbox value="large" class="checkbox sizechkbx" path="size" label="Large" onclick="disableTxt(this, 'txtLrgPrc')"/>
                    </label>
                  </div>
                  <div class="col-xs-7">
                    <form:input type="text" class="form-control price" path="price" id="txtLrgPrc" disabled="true"/>
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxOthr">
                  <div class="col-xs-5">
                    <label class="checkbox-inline">
                    <form:checkbox value="other" class="checkbox sizechkbx" path="size" label="Other" onclick="disableTxt(this, 'txtOthrPrc')"/>
                    </label>
                  </div>
                  <div class="col-xs-7">
                    <form:input type="text" class="form-control price" path="price" id="txtOthrPrc" disabled="true" />
                  </div>
                </div>

              </div>

              <div class="col-xs-4">
                <span id="itmPriceErr" class="input-group-error"></span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label class="col-xs-3 control-label"><fmt:message key="item.itemadd.form.itemtype"/></label>

              <div class="col-xs-7">
                <label class="radio-inline">
                  <form:radiobutton path="type" value="veg" label="Veg" checked="true"/>
                </label>
                <label class="radio-inline">
                  <form:radiobutton path="type" value="non-veg" label="Non-veg"/>
                </label>
                <label class="radio-inline">
                  <form:radiobutton path="type" value="carbonated" label="Carbonated"/>
                </label>
                <label class="radio-inline">
                  <form:radiobutton path="type" value="non-carbonated" label="Non-carbonated"/>
                </label>
              </div>

              <div class="col-xs-2">
                <span id="itmTypeErr" class="input-group-error"></span>
              </div>
            </div>
            <br>
          </div>

          <div class="form-group">
            <div class="row">
              <label for="btnUpldImage" class="col-xs-3 control-label">
                <fmt:message key="item.itemadd.form.itemimages"/>
              </label>

              <div class="col-xs-5">
                <form:input type="file" class="file_upload btn btn-default" path="imageUrl" id="btnUpldImage" value="Browse" />
              </div>
              <div class="col-xs-4">
                <span id="itmImgErr" class="input-group-error"> </span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label for="txtDesc" class="col-xs-3 control-label">
                Description
              </label>

              <div class="col-xs-5">
                <form:textarea class="form-control" rows="5" id="txtdesc" path="description"/>
              </div>
            </div>
          </div>

          <br>

          <div style="text-align: center; z-index: 50000; width: 50%; margin: auto;" id="toaster"></div>

          <div class="row" align="right">
            <div class="col-xs-3">
            </div>
            <div class="col-xs-3">
            </div>
            <div class="col-xs-3" align="center">
              <form:button type="submit" class="btn btn-success btn-group-xs" id="btnAddItem"><span
                      class="glyphicon glyphicon-plus"></span><fmt:message key="item.itemadd.form.button.submit"/></form:button>
            </div>
            <div class="col-xs-3" align="left">
              <form:button type="button" class="btn btn-success" id="btnAddClear" onclick="this.form.reset();"><fmt:message key="item.itemadd.form.button.reset"/> </form:button>
            </div>
          </div>
        </fieldset>
      </form:form>
    </div>
  </div>
</div>
  </fmt:bundle>
</body>
</html>
