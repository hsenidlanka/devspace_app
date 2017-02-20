<%@ page contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>

    <spring:url value="/themes/hsenid/js/customers_table.js" var="table"/>
    <script src="${table}"></script>
    <spring:url value="/themes/hsenid/js/customerPagination.js" var="pagination"/>
    <script src="${pagination}"></script>

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
<%--<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />--%>

<!--<div  id="toolbar">-->
    <%--<div class="col-xs-1"></div>--%>
    <div class="col-xs-12">
        <fieldset class="scheduler-border">
            <legend class="scheduler-border" id="searchCriteria">

            </legend>

            <div class="form-group">
                <div class="row">
                    <div class="col-xs-9" style="width: 64% ">
                            <div class="col-xs-2">
                                <%--<div class="col-xs-2" style="width: 50%;">--%>
                                    <label class="checkbox-inline">
                                        <fmt:message key="user.userview.customer.label.filter" />

                                    </label>
                                <%--</div>--%>
                            </div>
                            <div class="col-xs-7" style="text-align: left;margin: 0 10px 0 30px;">
                                <input type="checkbox" value="1" id="regCheck" onchange="showMe()">
                                <fmt:message key="user.userview.customer.filter.regDate" />
                            </div>
                            <div class="col-xs-3" style="margin: 0 -100px 0 -45px;">
                                <select id="selectVerifyOrNot">
                                    <option value="Verified">
                                        <fmt:message key="user.userview.customer.selectOption.verified"/>
                                    </option>
                                    <option value="Not_Verified">
                                        <fmt:message key="user.userview.customer.selectOption.notverified" />
                                    </option>
                                </select>
                            </div>
                        <%--</div>--%>
                        <!--<div class="col-xs-2"></div>-->
                    </div>

                    <div class="col-xs-3" style="width: 36%">
                        <div class="col-xs-8">
                            <input class="form-control typeahead"  placeholder="Username" type="text" id="cnameSearch" >
                        </div>
                        <div class="col-xs-1">
                            <button type="button" class="btn btn-success" id="filterButtonCustomer" >
                                <span class="glyphicon glyphicon-search"></span>
                                <fmt:message key="user.userview.customer.search"/>
                            </button>
                        </div>
                        <div class="col-xs-4"></div>
                    </div>
                </div>
            </div>

       <!--Filtering elements to be displayed as checkbox is checked     -->
            <div class="form-group">
                <div class="row">
                    <div class="col-xs-9"  style="width: 64%; ">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-7"  id="regPeriod" style="text-align: left;margin: -20px 10px 0 20px; display:none">
                            <div class="col-xs-6">
                                <label class=" control-label" >
                                    <fmt:message key="user.userview.customer.filter.regDate.from" />
                                </label>
                                <input class="form-control" id="fromDate" type="text"
                                               placeholder="Click on me" style="width: 130px"/>
                            </div>
                            <div class="col-xs-6">
                                <label class=" control-label">
                                    <fmt:message key="user.userview.customer.filter.regDate.to"/>
                                </label>

                                <input class="form-control" id="toDate" placeholder="Click on me"
                                               type="text" style="width: 130px" />
                            </div>
                        </div>
                        <div class="col-xs-3" >

                        </div>
                    </div>

                    <div class="col-xs-4" style="width: 36%">
                    </div>
                </div>
            </div>
        </fieldset>
    </div>
    <%--<div class="col-sm-1"></div>--%>

    <table  id="tableCustomer">
    </table>
    <div id="paginationCustomer" class="text-center">
    </div>
    <div id="pagination2Customer" class="text-center">
    </div>

<!--div to include modals-->
<!--<div id="includedContent1"></div>-->


<!--modelto appear when banning a Customer or Staff user -->
<div class="modal fade" id="removeCustomerModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span>
                </button>
                <div align="center"><span class="glyphicon glyphicon-trash"></span>
                    <fmt:message key="user.customerblock.modal.title" />
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label id="lblBlockCustomerMsg"><fmt:message key="user.block.modal.heading" />
                    </label><br><br>

                    <div align="center">
                        <label id="lblBlockCustomerId"></label><br>
                        <label id="lblBlockCustomerName"></label><br>
                    </div>
                </div>
                <div  class="toaster"></div>

            </div>
            <div class="modal-footer" align="right">
                <div class="col-xs-3"></div>
                <div class="col-xs-2" >
                    <button class="btn btn-success" type="button" id="btnCnclBlockCustomer" value="cancel"
                            data-dismiss="modal" aria-hidden="true">
                        <fmt:message key="user.block.modal.button.cancel"/>
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                </div>
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                    <button class="btn btn-success" type="button" value="Yes" id="btnBlockCustomer" >
                        <fmt:message key="user.block.modal.button.yes" />
                        <span class="glyphicon glyphicon-ok"></span>
                    </button>
                </div>
                <div class="col-xs-3"></div>
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
                    <fmt:message key="user.customer.update.modal.heading" />
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
                                <fmt:message key="user.useradd.form.name"/>
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
                                <fmt:message key="user.useradd.form.address" />
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
                                                    style="width: 219px"  />
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
                                <fmt:message key="user.useradd.form.mobile"  />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="mobile" class="form-control" id="update_mobile" type="text"
                                                    style="width: 220px" pattern="^\(?(\+94)\)?([0-9]{9})$" required="required"/>
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
                                <fmt:message key="user.useradd.form.email" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="email" class="form-control" id="update_email" type="text"
                                                    style="width: 220px" pattern="^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.
                                              [a-zA-Z0-9-]+)*\.(([0-9]{1,3})|([a-zA-Z]{2,3}))$" required="required"/>
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
                                <fmt:message key="user.useradd.form.username"  />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="username" class="form-control" id="update_username" type="text"
                                                    style="width: 220px" readonly="true"/>
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
                                <fmt:message key="user.useradd.form.password"/>
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="password" class="form-control" id="update_password" type="password"
                                                    style="width: 220px"   onchange="validatePassword(update_password)"
                                                    required="required"/>
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
                                <fmt:message key="user.useradd.form.cpassword"/>
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="password" class="form-control" id="update_cpassword" type="password"
                                                   onchange="passwordsEqual(update_cpassword,update_password)"
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
                    <div class="modal-footer" align="right">
                        <div class="col-xs-3"></div>
                        <div class="col-xs-2" >
                            <form:button class="btn btn-success" type="button" value="cancel" id="btnCnclUpdateStaff" data-dismiss="modal"
                                     aria-hidden="true">
                                <fmt:message key="user.block.modal.button.cancel"/>
                                <span class="glyphicon glyphicon-remove"></span>
                            </form:button>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-2" >
                            <form:button class="btn btn-success" type="submit" value="Update" id="btnUpdateCustomer">
                                <fmt:message key="user.customer.update.form.button" />
                                <span class="glyphicon glyphicon-ok"></span>
                            </form:button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>