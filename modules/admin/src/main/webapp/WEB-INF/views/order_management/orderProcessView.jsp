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
          <legend class="scheduler-border" id="orderLegend"><fmt:message key="orderprocess.form.legend" bundle="${bundle1}"/></legend>

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


<%------------------------------------==============models=================---------------------------------------------------->

<%--modal to edit the category selected--%>
<div class="modal fade" id="assignOrderModel">
  <div class="modal-dialog" id="orderModalDialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header assignorder-modal-header-style" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                class="glyphicon glyphicon-remove"></span>
        </button>
        <div align="center">
          <span class="glyphicon glyphicon-edit"></span>
          <fmt:message key="orderprocess.assignorder.modal.heading" bundle="${bundle1}" />
        </div>
      </div>
      <div class="modal-body">
        <form:form role="form"   method="POST" class="form-horizontal">
          <fieldset>
            <div class="form-group">
              <div class="col-xs-2"></div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label  class="control-label ">
                    <fmt:message key="orderprocess.assignorder.modal.selectbranch" bundle="${bundle1}" />
                  </label>
                  <select class="form-control" id="selectBranch" style="width: 100px">
                    <option value="Mr">
                      <fmt:message key="user.useradd.form.title.mr" bundle="${bundle1}"/>
                    </option>
                    <option value="Mrs">
                      <fmt:message key="user.useradd.form.title.mrs" bundle="${bundle1}" />
                    </option>
                    <option value="Miss">
                      <fmt:message key="user.useradd.form.title.miss" bundle="${bundle1}" />
                    </option>
                  </select>
                </div>
              </div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label  class="control-label ">
                    <fmt:message key="orderprocess.assignorder.modal.selectagent" bundle="${bundle1}" />
                  </label>
                  <select class="form-control" id="selectAgent" style="width: 100px">
                    <option value="Mr">
                      <fmt:message key="user.useradd.form.title.mr" bundle="${bundle1}"/>
                    </option>
                    <option value="Mrs">
                      <fmt:message key="user.useradd.form.title.mrs" bundle="${bundle1}" />
                    </option>
                    <option value="Miss">
                      <fmt:message key="user.useradd.form.title.miss" bundle="${bundle1}" />
                    </option>
                  </select>
                </div>
              </div>
            </div>


         <%--   <br>
            <div class="form-group">
              <label for="categoryid" class="control-label col-sm-4">
                <fmt:message key="category.categoryedit.modal.id" bundle="${bundle1}" />
              </label>
              <div class="col-sm-8">
                <form:input path="category_id" type="text" class="form-control" id="categoryid"
                            readonly="true"/>
              </div>
            </div>
            <div class="form-group">
              <label for="editcategoryname" class="control-label col-sm-4">
                <fmt:message key="category.categoryedit.modal.name" bundle="${bundle1}" />
              </label>

              <div class="col-sm-8">
                <form:input path="categoryName" type="text" class="form-control" id="editcategoryname"
                            required="required"/>
              </div>

            </div>
            <input name="catName" type="hidden" id="catName">
            <div class="form-group">
              <label for="editcategorydes" class="control-label col-sm-4">
                <fmt:message key="category.categoryedit.modal.description" bundle="${bundle1}" />

              </label>
              <div class="col-sm-8">
                <form:textarea path="catDescription" type="text" class="form-control" id="editcategorydes"></form:textarea>
              </div>
            </div>

            <div class="form-group">
              <label for="editbtnUpldImage" class="control-label col-sm-4" >
                <fmt:message key="category.categoryedit.modal.image" bundle="${bundle1}" />
              </label>
              <div class="col-sm-8">
                <img  id="imageUrl"/>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-sm-4">

              </label>
              <div class="col-sm-8" style="margin: 0 0 -100px 0;">
                <a href="#" id="imageEditButton">
                  <fmt:message key="category.categoryedit.modal.imageChange" bundle="${bundle1}" /></a>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-sm-4">

              </label>
              <div class="col-sm-8">
                <form:input type="file" class="file_upload btn btn-default" path="imageUrl"
                            id="editbtnUpldImage" disabled="true"/>
              </div>
            </div>
            <div class="form-group">
              <label for="editvisibility" class="control-label col-sm-4" style="">
                <fmt:message key="category.categoryedit.modal.visibility" bundle="${bundle1}" />
              </label>
              <div class="col-sm-8">
                <div class="col-xs-2">
                  <form:select path="status" class="form-control" id="editvisibility" style="width:75px">
                    <form:option value="1">
                      <fmt:message key="category.categoryadd.form.catvisibility.option1"
                                   bundle="${bundle1}"/>
                    </form:option>
                    <form:option value="0">
                      <fmt:message key="category.categoryadd.form.catvisibility.option2"
                                   bundle="${bundle1}" />
                    </form:option>
                  </form:select>
                </div>
              </div>
            </div>
            <div class="modal-footer" style="height:10px">
              <div class="form-group row" style="text-align: center">
                <div class="col-xs-3"></div>
                <div class="col-xs-2" >
                  <button class="btn btn-success"  data-dismiss="modal" aria-hidden="true">
                    <fmt:message key="category.categorydelete.modal.cancel" bundle="${bundle1}" />
                    <span class="glyphicon glyphicon-remove"></span>
                  </button>
                </div>
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                  <button class="btn btn-success" id="btnEditCategory" type="submit">
                    <fmt:message key="category.categoryedit.modal.approve" bundle="${bundle1}" />
                    <span class="glyphicon glyphicon-ok"></span>
                  </button>
                </div>
                <div class="col-xs-3"></div>
              </div>
            </div>
          </fieldset>--%>
          </fieldset>
        </form:form>
      </div>
    </div>
  </div>
</div>

</body>
</html>
