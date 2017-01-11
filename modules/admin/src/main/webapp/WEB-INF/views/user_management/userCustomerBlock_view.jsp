<html xmlns:jsp="http://java.sun.com/JSP/Page">


<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

    <spring:url value="/themes/hsenid/js/bannedCustomer_table.js" var="tableCustomerB"/>
    <script src="${tableCustomerB}"></script>
    <spring:url value="/themes/hsenid/js/customerPagination.js" var="pagination"/>
    <script src="${pagination}"></script>

    <script>
        //datepicker
        $(document).ready(function() {
            $("#fromDateb").datepicker({
                "changeMonth":true,
                "changeYear":true

            });

            $("#toDateb").datepicker({
                "changeMonth":true,
                "changeYear":true
            });
        });
    </script>

</head>
<body>
<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />

<!--<div  id="toolbar">-->

<div class="col-xs-12">
    <fieldset class="scheduler-border">
        <legend class="scheduler-border" id="searchCriteria"></legend>

        <div class="form-group">
            <div class="row">
                <div class="col-xs-8" style="width: 45%  ">
                    <div class="col-xs-3"  style="text-align: right">
                        <label class=" control-label" >
                            <fmt:message key="user.userview.customer.label.filter" bundle="${bundle1}"/>
                        </label>
                    </div>
                    <!--checkboxes to select-->
                    <div class="col-xs-9" id="checkboxDiv">
                        <div class="col-xs-5">
                            <label class="checkbox-inline">
                                <input  type="checkbox" value="2" id="bcCheck" onchange="showMeBlocked()" checked>
                                <fmt:message key="user.userview.customer.filter.name" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <div class="col-xs-5">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="3" id="citycCheck" onchange="showMeBlocked()">
                                <fmt:message key="user.userview.customer.filter.livingArea" bundle="${bundle1}"/>
                            </label>
                        </div>

                    </div>
                    <!--<div class="col-xs-2"></div>-->
                </div>

                <div class="col-xs-4" style="width: 50%">
                    <div class="col-xs-4">
                        <select class="form-control" id="blockcCity"  style="display: none;">
                            <option><fmt:message key="user.customer.select" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.customer.city1" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.customer.city2" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.customer.city3" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.customer.city4" bundle="${bundle1}" /></option>
                        </select>
                    </div>
                    <div class="col-xs-6">
                        <input class="form-control"  placeholder=" Name" type="text" id="bcnameSearch"  >
                    </div>
                    <div class="col-xs-1">
                        <button type="button" class="btn btn-success" id="filterButtonCustomerB">
                            <span class="glyphicon glyphicon-search"></span>
                            <fmt:message key="user.userview.customer.search" bundle="${bundle1}"/>
                        </button>
                    </div>
                    <div class="col-xs-1"></div>

                </div>
            </div>
        </div>

    </fieldset>
</div>


<table  id="tableBannedcustomer">
</table>
<div id="paginationBannedCustomer" class="text-center">
</div>
<div id="pagination2BannedCustomer" class="text-center">
</div>


<!--modelto appear when replacing a banned Customer or Staff user -->
<div class="modal fade" id="replaceCustomerModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-refresh"></span>
                    <fmt:message key="user.customerreplace.modal.title" bundle="${bundle1}" />
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label id="lblBlockStaffMsg"><fmt:message key="user.block.modal.heading" bundle="${bundle1}" />
                    </label><br><br>

                    <div align="center">
                        <label id="lblUnblockCustomerId"></label><br>
                        <label id="lblUnblockCustomerName"></label><br>
                    </div>
                </div>
            </div>
            <div class="modal-footer" align="right">
                <div class="col-xs-3"></div>
                <div class="col-xs-2" >
                    <button class="btn btn-success" type="button" value="cancel" id="btnCnclBlockStaff"
                            data-dismiss="modal" aria-hidden="true">
                        <fmt:message key="user.block.modal.button.cancel" bundle="${bundle1}" />
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                </div>
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                    <button class="btn btn-success" type="button" value="Yes" id="btnUnblockCustomer">
                        <fmt:message key="user.block.modal.button.yes" bundle="${bundle1}" />
                        <span class="glyphicon glyphicon-ok"></span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<!--modelto appear when editing the Customer user -->
<div class="modal fade" id="blockedCustomerModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-edit"></span>
                    <fmt:message key="user.blockuserview.customer.detail.modal.title" bundle="${bundle1}" />
                    <label id="currentbc_user"></label>
                </div>
            </div>
            <div class="modal-body">
                <form role="form"  method="POST" class="form-horizontal">
                    <br>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                                <fmt:message key="user.useradd.form.name" bundle="${bundle1}"/>
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-5">
                                        <%--<label class="control-label" id="staffDetail_title" ></label>--%>
                                        <input class="form-control details" id="customerDetail_title" type="text" readonly/>
                                    </div>
                                    <div class="col-xs-7">
                                        <input class="form-control details" id="customerDetail_first-name" type="text" readonly
                                               style="width: 140px" />
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <%--<div class="col-xs-4"></div>--%>
                                    <div class="col-xs-12">
                                        <input  class="form-control details" id="customerDetail_last-name" type="text"
                                                style="width: 160px" readonly/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><br>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                                <fmt:message key="user.useradd.form.address" bundle="${bundle1}"/>
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-12">
                                    <div class="col-xs-12">
                                        <input class="form-control details" id="customerDetail_addLine1" style="width:379px"
                                               type="text" readonly />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-12">
                                    <div class="col-xs-12">
                                        <input  class="form-control details" id="customerDetail_addLine2" type="text"
                                                style="width: 379px" readonly/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input  class="form-control details" id="customerDetail_city" type="text"
                                                style="width: 219px" readonly/>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input  class="form-control details" id="province" type="text" readonly/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><br>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                                <fmt:message key="user.useradd.form.mobile" bundle="${bundle1}" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input  class="form-control details" id="customerDetail_mobile" type="text"
                                                style="width: 220px" readonly/>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                                <fmt:message key="user.useradd.form.email" bundle="${bundle1}" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input  class="form-control details" id="customerDetail_email" type="text"
                                                style="width: 220px" readonly/>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                                <fmt:message key="user.useradd.form.username" bundle="${bundle1}" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input  class="form-control details" id="customerDetail_username" type="text"
                                                style="width: 220px" readonly/>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                                <fmt:message key="user.useradd.form.status" bundle="${bundle1}" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input class="form-control details" id="customerDetail_status" type="text"
                                               style="width: 220px" readonly/>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer" style="text-align: center">
                <button class="btn btn-success" type="button" value="cancel" id="buttonView" data-dismiss="modal"
                        aria-hidden="true">
                    <fmt:message key="user.userview.button.ok" bundle="${bundle1}" />
                </button>
            </div>
        </div>
    </div>
    </div>


</body>
</html>