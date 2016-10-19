<html xmlns:jsp="http://java.sun.com/JSP/Page">


<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

    <spring:url value="/themes/hsenid/js/bannedCustomer_table.js" var="js1"/>
    <script src="${js1}"></script>

    <script>
        //datepicker
        $(document).ready(function() {
            $("#fromDate").datepicker({
                "changeMonth":true,
                "changeYear":true

            });

            $("#toDate").datepicker({
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
                <div class="col-xs-8" style="width: 64%  ">
                    <div class="col-xs-2"  style="text-align: right">
                        <label class=" control-label" >
                            <fmt:message key="user.userview.customer.label.filter" bundle="${bundle1}"/>
                        </label>
                    </div>
                    <!--checkboxes to select-->
                    <div class="col-xs-10" id="checkboxDiv">
                        <div class="col-xs-5">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="1" id="blockcCheck" onchange="showMeBlocked()">
                                <fmt:message key="user.blockuserview.customer.filter.blockDate" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <div class="col-xs-3">
                            <label class="checkbox-inline">
                                <input  type="checkbox" value="2" id="bcCheck" onchange="showMeBlocked()" checked>
                                <fmt:message key="user.userview.customer.filter.name" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <div class="col-xs-4">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="3" id="citycCheck" onchange="showMeBlocked()">
                                <fmt:message key="user.userview.customer.filter.livingArea" bundle="${bundle1}"/>
                            </label>
                        </div>
                    </div>
                    <!--<div class="col-xs-2"></div>-->
                </div>

                <div class="col-xs-4" style="width: 36%">
                    <div class="col-xs-8">
                        <input class="form-control"  placeholder=" Name" type="text" id="bcname"  >
                    </div>
                    <div class="col-xs-1">
                        <button type="button" class="btn btn-success" >
                            <span class="glyphicon glyphicon-search"></span>
                            <fmt:message key="user.userview.customer.search" bundle="${bundle1}"/>
                        </button>
                    </div>
                    <div class="col-xs-4"></div>
                </div>
            </div>
        </div>

        <!--Filtering elements to be displayed as checkbox is checked     -->
        <div class="form-group">
            <div class="row">
                <div class="col-xs-8" id="blockcPeriod"style="display: none; width: 64%" >
                    <div class="col-xs-2"  style="text-align: right">
                        <label class=" control-label">
                            <fmt:message key="user.userview.customer.filter.regDate.from" bundle="${bundle1}"/>
                        </label>
                    </div>
                    <div class="col-xs-10">
                        <div class="col-xs-4">
                            <input class="form-control" id="fromDate" type="text"
                                   placeholder="Click on me" style="width: 130px">
                        </div>
                        <div class="col-xs-1">
                            <label class=" control-label">
                                <fmt:message key="user.userview.customer.filter.regDate.to" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <div class="col-xs-4">
                            <input class="form-control" id="toDate" placeholder="Click on me"
                                   type="text" style="width: 130px">
                        </div>
                    </div>
                </div>

                <!--next half-->
                <!--name form-group-->
                <div class="col-xs-4" style="width: 36%">
                    <div class="col-xs-8">
                        <select class="form-control" id="blockcCity"  style="display: none;">
                            <option><fmt:message key="user.customer.city1" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.customer.city2" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.customer.city3" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.customer.city4" bundle="${bundle1}" /></option>
                        </select>
                    </div>
                    <div class="col-xs-4"></div>
                </div>
            </div>
        </div>
    </fieldset>
</div>


<table  id="tableBannedcustomer">
</table>


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
                <button class="btn btn-success" type="button" value="Yes" id="btnUnblockCustomer">
                    <fmt:message key="user.block.modal.button.yes" bundle="${bundle1}" />
                </button>
                <button class="btn btn-success" type="button" value="cancel" id="btnCnclBlockStaff"
                        data-dismiss="modal" aria-hidden="true">
                    <fmt:message key="user.block.modal.button.cancel" bundle="${bundle1}" />
                </button>
            </div>
        </div>
    </div>
</div>

</body>
</html>