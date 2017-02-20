<!DOCTYPE html>
<html>
<head>
  <title>Order Process</title>
  <!-- include common CSS, fonts and js -->
  <%@include file="../adminTop.jsp" %>

  <spring:url value="/themes/hsenid/css/orderMgt.css" var="cssOrder"/>
  <link href="${cssOrder}" rel="stylesheet">

  <spring:url value="/themes/hsenid/js/orderProcess_table.js" var="ordertable"/>
  <script src="${ordertable}"></script>

  <spring:url value="/themes/hsenid/js/orderProcessPagination.js" var="ordertablePagination"/>
  <script src="${ordertablePagination}"></script>

</head>
<body>

<%--<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />--%>
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
  <div style="position: relative; left: -50%;">
    <fmt:message key="order.heading" />
  </div>
</div>
<br>

<%--breadcrump--%>
<div>
  <div id="view-order-breadcrumb-position">
    <ul class="breadcrumb breadcrumb-menu">
      <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
      <fmt:message key="admin.ordermanagement.view.url" var="url2" bundle="${bundle2}"/>

      <li><a href="<c:out value="${url1}"/>"><fmt:message key="orderprocess.breadcrumb.home"/></a></li>
      <li><a href="<c:out value="${url1}"/>"><fmt:message key="order.heading" /> </a></li>
      <li class="active"><a href="<c:out value="${url2}"/>"><fmt:message key="orderprocess.breadcrumb.view" /> </a></li>
    </ul>
  </div>
</div>


<%--table body--%>
<div class="form-box" id="process-order">
  <div class="panel panel-default">
    <div class="panel-heading  common-form-headings">
      <h3 class="default-panel-headings">
        <fmt:message key="orderprocess.delivery.process.view.table" />
      </h3>
    </div>

    <div class="panel-body">
      <div class="row itemSearchBar">
        <div class="col-sm-1"></div>
        <div class="col-sm-4">
          <label class="control-label" style="float: right;">
            <fmt:message key="orderprocess.order.id" />
          </label>

        </div>
        <div class="col-sm-3">
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
          <legend class="scheduler-border" id="orderLegend"><fmt:message key="orderprocess.form.legend"/></legend>

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

<%--modal to assign the delivery agent to an order and change the status --%>
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
          <fmt:message key="orderprocess.assignorder.modal.heading"  />
        </div>
      </div>
      <div class="modal-body">
        <form role="form"   method="POST" class="form-horizontal">
            <div class="form-group opModalTitle">
              <label class="control-label ">
                <fmt:message key="orderprocess.assignorder.modal.title"  />
              </label>
              <label id="lblOrderId"></label>
            </div>
          <fieldset class="scheduler-border">
            <div class="form-group"></div>
            <div class="form-group">
              <div class="col-xs-2"></div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label class="control-label ">
                    <fmt:message key="orderprocess.assignorder.modal.selectbranch" />
                  </label>
                </div>
                <div class="form-group">
                  <select class="form-control" id="selectBranch" style="width: 150px">
                    <option value="--Select--"><fmt:message key="user.customer.select" /></option>
                    <option value="Colombo"><fmt:message key="user.staff.branch1" /></option>
                    <option value="Gampaha"><fmt:message key="user.staff.branch2" /></option>
                    <option value="Ja-Ela"><fmt:message key="user.staff.branch3"  /></option>
                    <option value="Kandana"><fmt:message key="user.staff.branch4" /></option>
                  </select>
                </div>
              </div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label  class="control-label ">
                    <fmt:message key="orderprocess.assignorder.modal.selectagent" />
                  </label>
                </div>
                <div class="form-group">
                  <select class="form-control" id="selectAgent" style="width: 150px">
                    <option value="--Select--">
                      <fmt:message key="user.customer.select" />
                    </option>
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-xs-2"></div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label  class="control-label ">
                    <fmt:message key="orderprocess.assignorder.modal.branch.selection" />
                  </label>
                </div>
              </div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label class="control-label dynamic " id="lblBranchId"></label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-xs-2"></div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label  class="control-label ">
                    <fmt:message key="orderprocess.assignorder.modal.agent.selection" />
                  </label>
                </div>
              </div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label  class="control-label dynamic" id="lblAgentId"></label>
                </div>
              </div>
            </div>
          </fieldset>
        </form>
      </div>
      <div class="modal-footer" style="height:70px">
        <div class="form-group row" style="text-align: center">
          <div class="col-xs-3"></div>
          <div class="col-xs-2" >
            <button class="btn btn-success"  data-dismiss="modal" aria-hidden="true">
              <fmt:message key="category.categorydelete.modal.cancel" />
              <span class="glyphicon glyphicon-remove"></span>
            </button>
          </div>
          <div class="col-xs-2"></div>
          <div class="col-xs-2" >
            <button class="btn btn-success" id="btnAssignDelivery" type="submit">
              <fmt:message key="orderprocess.assignorder.modal.footer.assign.button" />
              <span class="glyphicon glyphicon-ok"></span>
            </button>
          </div>
          <div class="col-xs-3"></div>
        </div>
      </div>
    </div>
  </div>
</div>


<%--modal to change and  reassign the agent --%>
<div class="modal fade" id="reassignOrderModel">
  <div class="modal-dialog" id="reassignorderModalDialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header assignorder-modal-header-style" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                class="glyphicon glyphicon-remove"></span>
        </button>
        <div align="center">
          <span class="glyphicon glyphicon-edit"></span>
          <fmt:message key="orderprocess.changeorder.modal.heading" />
        </div>
      </div>
      <div class="modal-body">
        <form:form role="form"   method="POST" class="form-horizontal">
          <div class="form-group opModalTitle">
            <label class="control-label ">
              <fmt:message key="orderprocess.assignorder.modal.title" />
            </label>
            <label id="lblChangeOrderId"></label>
          </div>
          <fieldset class="scheduler-border">
            <div class="form-group"></div>
            <div class="form-group">
              <div class="col-xs-2"></div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label class="control-label ">
                    <fmt:message key="orderprocess.changeorder.modal.selectbranch" />
                  </label>
                </div>
                <div class="form-group">
                  <select class="form-control" id="changeBranch" style="width: 150px">
                    <option value="Null"><fmt:message key="user.customer.select" /></option>
                    <option value="Colombo"><fmt:message key="user.staff.branch1" /></option>
                    <option value="Gampaha"><fmt:message key="user.staff.branch2"/></option>
                    <option value="Ja-Ela"><fmt:message key="user.staff.branch3" /></option>
                    <option value="Kandana"><fmt:message key="user.staff.branch4"  /></option>
                  </select>
                </div>
              </div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label  class="control-label ">
                    <fmt:message key="orderprocess.changeorder.modal.selectagent" />
                  </label>
                </div>
                <div class="form-group">
                  <select class="form-control" id="changeAgent" style="width: 150px">
                    <option value="Mr">
                      <fmt:message key="user.customer.select"/>
                    </option>
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-xs-2"></div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label  class="control-label ">
                    <fmt:message key="orderprocess.assignorder.modal.branch.selection"  />
                  </label>
                </div>
              </div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label class="control-label dynamic " id="lblChangedBranchId">
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-xs-2"></div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label  class="control-label ">
                    <fmt:message key="orderprocess.assignorder.modal.agent.selection" />
                  </label>
                </div>
              </div>
              <div class="col-xs-5">
                <div class="form-group">
                  <label class="control-label dynamic" id="lblChangeAgentId"></label>
                </div>
              </div>
            </div>
          </fieldset>
        </form:form>
      </div>
      <div class="modal-footer" style="height:70px">
        <div class="form-group row" style="text-align: center">
          <div class="col-xs-3"></div>
          <div class="col-xs-2" >
            <button class="btn btn-success"  data-dismiss="modal" aria-hidden="true">
              <fmt:message key="category.categorydelete.modal.cancel"  />
              <span class="glyphicon glyphicon-remove"></span>
            </button>
          </div>
          <div class="col-xs-2"></div>
          <div class="col-xs-2" >
            <button class="btn btn-success" id="btnReassignDelivery" type="submit">
              <fmt:message key="orderprocess.changeorder.modal.footer.assign.button" />
              <span class="glyphicon glyphicon-ok"></span>
            </button>
          </div>
          <div class="col-xs-3"></div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
