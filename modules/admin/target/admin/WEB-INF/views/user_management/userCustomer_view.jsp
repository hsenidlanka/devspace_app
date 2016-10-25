<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>


    <spring:url value="/themes/hsenid/js/customers_table.js" var="js1"/>
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
    <%--<div class="col-xs-1"></div>--%>
    <div class="col-xs-12">
        <fieldset class="scheduler-border">
            <legend class="scheduler-border" id="searchCriteria"></legend>

            <div class="form-group">
                <div class="row">
                    <div class="col-xs-8" style="width: 64%  ">
                        <div class="col-xs-2" style="text-align: right">
                            <label class=" control-label" >
                                <fmt:message key="user.userview.customer.label.filter" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <!--checkboxes to select-->
                        <div class="col-xs-10" id="checkboxDiv">
                            <div class="col-xs-5">
                                <label class="checkbox-inline">
                                    <input type="checkbox" value="1" id="regCheck" onchange="showMe()">
                                    <fmt:message key="user.userview.customer.filter.regDate" bundle="${bundle1}"/>
                                </label>
                            </div>
                            <div class="col-xs-3">
                                <label class="checkbox-inline">
                                    <input  type="checkbox" value="2" id="nameCheck" onchange="showMe()" checked>
                                    <fmt:message key="user.userview.customer.filter.name" bundle="${bundle1}"/>
                                </label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline">
                                    <input type="checkbox" value="3" id="liveCheck" onchange="showMe()">
                                    <fmt:message key="user.userview.customer.filter.livingArea" bundle="${bundle1}"/>
                                </label>
                            </div>
                        </div>
                        <!--<div class="col-xs-2"></div>-->
                    </div>

                    <div class="col-xs-4" style="width: 36%">
                        <div class="col-xs-8">
                            <input class="form-control"  placeholder=" Name" type="text" id="cnameSearch"  >
                        </div>
                        <div class="col-xs-1">
                            <button type="button" class="btn btn-success" id="filterButton" >
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
                    <div class="col-xs-8" id="regPeriod"  style="width: 64%; display:none">
                        <div class="col-xs-2"  style="text-align: right">
                            <label class=" control-label">
                                <fmt:message key="user.userview.customer.filter.regDate.from" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <div class="col-xs-10">
                            <div class="col-xs-4">
                                <input class="form-control" id="fromDate" type="text"
                                               placeholder="Click on me" style="width: 130px"/>
                            </div>
                            <div class="col-xs-1">
                                <label class=" control-label">
                                    <fmt:message key="user.userview.customer.filter.regDate.to" bundle="${bundle1}"/>
                                </label>
                            </div>
                            <div class="col-xs-4">
                                <input class="form-control" id="toDate" placeholder="Click on me"
                                               type="text" style="width: 130px" />
                            </div>
                        </div>
                    </div>

                    <!--next half-->
                    <!--name form-group-->
                    <div class="col-xs-4" style="width: 36%">
                        <div class="col-xs-8">
                                <select class="form-control" id="citySearch"  style="display: none;">
                                    <option><fmt:message key="user.customer.select" bundle="${bundle1}" /></option>
                                    <option><fmt:message key="user.customer.city1" bundle="${bundle1}" /></option>
                                    <option><fmt:message key="user.customer.city2" bundle="${bundle1}" /></option>
                                    <option><fmt:message key="user.customer.city3" bundle="${bundle1}" /></option>
                                    <option><fmt:message key="user.customer.city4" bundle="${bundle1}" /></option>
                                </select>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </div>
    <%--<div class="col-sm-1"></div>--%>

<table  id="tableCustomer">
</table>

<!--div to include modals-->
<!--<div id="includedContent1"></div>-->


<!--modelto appear when banning a Customer or Staff user -->
<div class="modal fade" id="removeCustomerModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-trash"></span>
                    <fmt:message key="user.customerblock.modal.title" bundle="${bundle1}" />
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label id="lblBlockCustomerMsg"><fmt:message key="user.block.modal.heading" bundle="${bundle1}" />
                    </label><br><br>

                    <div align="center">
                        <label id="lblBlockCustomerId"></label><br>
                        <label id="lblBlockCustomerName"></label><br>
                    </div>
                </div>
                <div style="text-align: center; z-index: 50000; width: 75%; margin: auto;" id="toaster"></div>

            </div>
            <div class="modal-footer" align="right">
                <button class="btn btn-success" type="button" value="Yes" id="btnBlockCustomer" >
                    <fmt:message key="user.block.modal.button.yes" bundle="${bundle1}" />
                </button>
                <button class="btn btn-success" type="button" id="btnCnclBlockCustomer" value="cancel"
                        data-dismiss="modal" aria-hidden="true">
                    <fmt:message key="user.block.modal.button.cancel" bundle="${bundle1}" />
                </button>
            </div>
        </div>
    </div>
</div>

<!--modelto appear when editing the Customer user -->
<div class="modal fade" id="editCustomerModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-edit"></span>
                    <fmt:message key="user.customer.update.modal.heading" bundle="${bundle1}" />
                    <label id="current_user"></label>
                </div>
            </div>
            <div class="modal-body">
                <form:form role="form" id="update_customer_form"  method="POST" class="form-horizontal"
                           action="/admin/users/customer/editCustomer">
                    <br>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                                <fmt:message key="user.useradd.form.name" bundle="${bundle1}"/>
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-5">
                                        <form:input path="title" class="form-control" id="title"
                                                    type="text" disabled="true"/>
                                    </div>
                                    <div class="col-xs-7">
                                        <form:input path="firstName" class="form-control" id="update-first-name"
                                                    type="text"  style="width: 140px" required="required"/>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                        <%--<div class="col-xs-4"></div>--%>
                                    <div class="col-xs-12">
                                        <form:input path="lastName" class="form-control" id="update-last-name" type="text"
                                                    style="width: 160px"/>
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
                                        <form:input path="addressL1" class="form-control" id="update_addLine1"
                                                    required="required" type="text"/>
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
                                        <form:input path="addressL2" class="form-control" id="update_addLine2" type="text"
                                                    required="required"/>
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
                                        <form:input path="addressL3" class="form-control" id="update_city" type="text"
                                                    style="width: 219px" required="required"/>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input  class="form-control" id="province" type="text"/>
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
                                        <form:input path="mobile" class="form-control" id="update_mobile" type="text"
                                                    style="width: 220px" required="required"/>
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
                                        <form:input path="email" class="form-control" id="update_email" type="text"
                                                    style="width: 220px" required="required"/>
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
                                        <form:input path="username" class="form-control" id="update_username" type="text"
                                                    style="width: 220px" required="required"/>
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
                                <fmt:message key="user.useradd.form.password" bundle="${bundle1}" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="password" class="form-control" id="update_password" type="password"
                                                    style="width: 220px"   onchange="validatePassword(update_password)" required="required"/>
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
                                <fmt:message key="user.useradd.form.cpassword" bundle="${bundle1}" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input class="form-control" id="update_cpassword" type="password" style="width: 220px"
                                               onchange="passwordsEqual(update_cpassword,update_password)" required="required"/>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>

            <div class="modal-footer" align="right">
                <button class="btn btn-success" type="button" value="Update" id="btnUpdateCustomer">
                    <fmt:message key="user.customer.update.form.button" bundle="${bundle1}" />
                </button>
                <button class="btn btn-success" type="button" value="cancel" id="btnCnclUpdateStaff" data-dismiss="modal"
                        aria-hidden="true">
                    <fmt:message key="user.block.modal.button.cancel" bundle="${bundle1}" />
                </button>
            </div>
        </div>
    </div>
</div>

</body>
</html>