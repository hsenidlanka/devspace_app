<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head lang="en">

    <title>View Order</title>

    <!-- include common CSS, fonts and js -->
    <jsp:include page="../adminTop.jsp"/>

    <%-- Other css --%>
    <spring:url value="/themes/hsenid/css/itemMgt.css" var="cssItem"/>
    <spring:url value="/themes/hsenid/css/packageMgt.css" var="cssPackage"/>
    <spring:url value="/themes/hsenid/css/orderMgt.css" var="cssOrder"/>
    <link href="${cssItem}" rel="stylesheet">
    <link href="${cssPackage}" rel="stylesheet">
    <link href="${cssOrder}" rel="stylesheet">

    <%--  other javascripts --%>

    <spring:url value="/themes/hsenid/js/itemMgt.js" var="itemJs"/>
    <spring:url value="/themes/hsenid/js/packageMgt.js" var="pkgMgt"/>
    <spring:url value="/themes/hsenid/js/myAlerts.js" var="myAlerts"/>
    <spring:url value="/themes/hsenid/js/tableOrder.js" var="ordrTblView"/>

    <script src="${itemJs}"></script>
    <script src="${pkgMgt}"></script>
    <script src="${ordrTblView}"></script>
    <script src="${myAlerts}"></script>
</head>

<body>

<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1"/>
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="order.heading" bundle="${bundle1}"/>
    </div>
</div>
<br>

<div>
    <div id="view-order-breadcrumb-position">
        <ul class="breadcrumb breadcrumb-menu">
            <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
            <fmt:message key="admin.ordermanagement.view.url" var="url2" bundle="${bundle2}"/>

            <li><a href="<c:out value="${url1}"/>"><fmt:message key="orderprocess.breadcrumb.home"
                                                                bundle="${bundle1}"/></a></li>
            <li><a href="<c:out value="${url1}"/>"><fmt:message key="order.heading" bundle="${bundle1}"/> </a></li>
            <li class="active"><a href="<c:out value="${url2}"/>"><fmt:message key="orderprocess.breadcrumb.view"
                                                                               bundle="${bundle1}"/> </a></li>
        </ul>
    </div>
</div>

<div class="form-box" id="view-order-form-box">
    <div class="panel panel-default">
        <div class="panel-heading  common-form-headings">
            <h3 class="default-panel-headings">
                <fmt:message key="orderprocess.vieworder" bundle="${bundle1}"/>
            </h3>
        </div>

        <div class="panel-body">

            <%--  <div class="row itemSearchBar">
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
                  </div>
                  <div class="col-sm-1"></div>
              </div>--%>

            <%--  ////--%>


            <legend class="scheduler-border" id="ordrCriteria"></legend>
            <div class="col-xs-12">
                <fieldset class="scheduler-border">
                    <div class="form-group">
                        <div class="row">
                            <%-- <div class="col-xs-1 ordrViewDiv">
                                 <button class="btn btn-link orderPill">View All Orders</button>
                             </div>--%>
                            <div class="col-xs-2 orderFilter">
                                <label class="control-label">
                                    Filter By:
                                </label>
                            </div>
                            <div class="col-xs-3">
                                <select class="form-control" id="selectOrdrType">
                                    <option value="0">--Select Order Type--</option>
                                </select>
                            </div>
                            <div class="col-xs-3">
                                <select class="form-control" id="" disabled="">
                                    <option value="0">-Select-</option>
                                </select>
                            </div>
                            <div class="col-xs-3">
                                <input class="form-control" id="txtViewSearchOrdr" placeholder="type order ID.."
                                       type="text">
                            </div>
                        </div>

                    </div>
                </fieldset>
            </div>
            <br>

            <form:form class="form-horizontal" role="form" id="frmViewOdr" action="" method="post">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border"><fmt:message key="orderprocess.form.legend"
                                                                  bundle="${bundle1}"/></legend>

                    <!--table of order details-->
                    <table id="tblOrders">

                    </table>

                    <div id="pagination5" class="text-center">
                    </div>
                    <div id="pagination6" class="text-center">
                    </div>
                </fieldset>
            </form:form>
        </div>

    </div>
</div>

<!--Modal for cancel order-->
<%--<div class="modal fade" id="pkgDeleteModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header item-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-trash"></span> Delete Package
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label id="lblDeltPkgMsg"><fmt:message key="package.deletemodal.warning.question" bundle="${bundle1}"/></label><br><br>

                    <div align="center" class="row">
                        <div class="col-xs-6" style="text-align: right"> <label ><fmt:message key="package.deletemodal.packageid" bundle="${bundle1}"/></label></div>
                        <div class="col-xs-6" style="text-align: left">  <label id="lblDeltPkgId" class="delete-lables"> </label></div><br>
                        <div class="col-xs-6" style="text-align: right"> <label ><fmt:message key="package.deletemodal.packagename" bundle="${bundle1}"/></label></div>
                        <div class="col-xs-6" style="text-align: left"> <label id="lblDeltPkgName" class="delete-lables"> </label></div><br>
                    </div>
                </div>
                <div style="text-align: center; width: 75%; margin: auto;" id="toaster"></div>
            </div>
            <div class="modal-footer" align="right">
                <button class="btn btn-success" type="button" value="Yes" id="btnDeltPkg"><fmt:message key="package.deletemodal.button.yes" bundle="${bundle1}"/>
                </button>
                <button class="btn btn-success" type="button" value="cancel" id="btnCnclDeltPkg" data-dismiss="modal"><fmt:message key="package.deletemodal.button.no" bundle="${bundle1}"/>
                </button>
            </div>
        </div>
    </div>
</div>--%>


<!--Modal for edit package-->
<div class="modal fade" id="ordrEditModal">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header item-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-pencil"></span><fmt:message
                        key="orderprocess.panel.heading" bundle="${bundle1}"/>
                </div>
            </div>
            <div class="modal-body">

                <form:form class="form-horizontal" role="form" method="post" id="frmEditOrdr" action="">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border"><fmt:message key="orderprocess.form.edit.legend"
                                                                  bundle="${bundle1}"/></legend>

                    <div class="form-group">

                        <div class="row">
                            <div class="col-xs-4" align="right">
                                <label><fmt:message key="orderprocess.form.ordr.id" bundle="${bundle1}"/></label>
                            </div>
                            <div class="col-xs-4">
                                <form:input class="form-control" type="text" id="txtOrdrId" path="" readonly="true"/>
                            </div>
                            <div class="col-xs-4">
                                <span id="" class="input-group-error"></span>
                            </div>
                        </div>
                        <br>

                        <div class="row">
                            <div class="col-xs-4" align="right">
                                <label><fmt:message key="orderprocess.form.order.customer.id"
                                                    bundle="${bundle1}"/></label>
                            </div>
                            <div class="col-xs-4">
                                <form:input class="form-control" type="text" id="txtOrdrCusId" path="" readonly=""/>
                            </div>
                            <div class="col-xs-4">
                                <span id="" class="input-group-error"></span>
                            </div>
                        </div>
                        <br>

                        <div class="row">
                            <div class="col-xs-4" align="right">
                                <label><fmt:message key="orderprocess.form.order.guest.id" bundle="${bundle1}"/></label>
                            </div>
                            <div class="col-xs-4">
                                <form:input class="form-control" type="text" id="txtOrdrGstId" path="" readonly=""/>
                            </div>
                            <div class="col-xs-4">
                                <span id="" class="input-group-error"></span>
                            </div>
                        </div>
                        <br>

                        <div class="row">
                            <div class="col-xs-4" align="right">
                                <label><fmt:message key="orderprocess.form.order.date" bundle="${bundle1}"/></label>
                            </div>
                            <div class="col-xs-4">
                                <form:input class="form-control" type="date" id="txtOrdrDate" path="" readonly=""/>
                            </div>
                            <div class="col-xs-4">
                                <span id="" class="input-group-error"></span>
                            </div>
                        </div>
                        <br>

                        <div class="row">
                            <div class="col-xs-4" align="right">
                                <label><fmt:message key="orderprocess.form.order.time" bundle="${bundle1}"/></label>
                            </div>
                            <div class="col-xs-4">
                                <form:input class="form-control" type="time" id="txtOrdrtime" path="" readonly=""/>
                            </div>
                            <div class="col-xs-4">
                                <span id="" class="input-group-error"></span>
                            </div>
                        </div>
                        <br>

                        <div class="row">
                            <div class="col-xs-4" align="right">
                                <label><fmt:message key="orderprocess.form.order.price" bundle="${bundle1}"/> </label>
                            </div>
                            <div class="col-xs-4">
                                <form:input type="text" class="form-control price" id="txtOrdrPrice" value="0.00"
                                            path=""/>
                            </div>
                            <div class="col-xs-4">
                                <span id="ordrPriceErr" class="input-group-error"></span>
                            </div>
                        </div>
                        <br>

                    </div>

            </div>
            <div style="text-align: center; z-index: 50000; width: 50%; margin: auto;" id="toaster"></div>
            <div class="modal-footer" align="right">
                <form:button class="btn btn-success" type="submit" value="Yes" id="btnUpdtOrdr"><fmt:message
                        key="orderprocess.form.button.submit" bundle="${bundle1}"/>
                </form:button>
                <form:button class="btn btn-success" type="reset" value="cancel" id="btnOrdrRst"
                             data-dismiss="modal"><fmt:message key="package.packageedit.form.button.reset"
                                                               bundle="${bundle1}"/>
                </form:button>
            </div>
            </fieldset>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>