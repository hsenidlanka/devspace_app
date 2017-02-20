<%@ page contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>

    <spring:url value="/themes/hsenid/js/staff_table.js" var="tableStaff"/>
    <script src="${tableStaff}"></script>
    <spring:url value="/themes/hsenid/js/staffPagination.js" var="paginationStaff"/>
    <script src="${paginationStaff}"></script>

</head>
<body>
<%--<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />--%>


<div  id="toolbar">
    <%--<div class="col-sm-1"></div>--%>
    <div class="col-xs-12">
        <fieldset class="scheduler-border">
            <legend class="scheduler-border" id="searchCriteria"></legend>

            <div class="form-group">
                <div class="row">
                    <div class="col-xs-8" style="width: 64%">
                        <div class="col-xs-2">
                            <label class=" control-label">
                                <fmt:message key="user.userview.staff.label.filter" />
                            </label>
                        </div>
                        <!--checkboxes to select-->
                        <div class="col-xs-10" id="checkboxDiv">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="1" id="desigCheck" onchange="showMe()">
                                <fmt:message key="user.userview.staff.filter.designation"/>
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="2" id="depCheck" onchange="showMe()">
                                <fmt:message key="user.userview.staff.filter.department" />
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="3" id="branchCheck" onchange="showMe()">
                                <fmt:message key="user.userview.staff.filter.branch" />
                            </label>
                        </div>
                    </div>

                    <div class="col-xs-4" style="width: 36%">
                        <div class="col-xs-8">
                            <input class="form-control typeahead"  placeholder=" Username" type="text" id="snameSearch" >

                        </div>
                        <div class="col-xs-1">
                            <button type="button" class="btn btn-success" id="filterButtonStaff">
                                <span class="glyphicon glyphicon-search"></span>
                                <fmt:message key="user.userview.staff.search" />
                            </button>
                        </div>
                        <div class="col-xs-2"></div>
                    </div>
                </div>
            </div>

            <!--filter by elements selected by checboxes are defined here-->
            <div class="form-group">
                <div class="row">
                    <!--designation -->
                    <div class="col-xs-4" id="designation"  style="display: none">
                        <div class="col-xs-4">
                            <label class=" control-label">
                                <fmt:message key="user.userview.staff.filter.designation" />
                            </label>
                        </div>
                        <div class="col-xs-8">
                            <select class="form-control" id="designation_d" >
                                <option><fmt:message key="user.customer.select" /></option>
                                <option><fmt:message key="user.staff.designation1" /></option>
                                <option><fmt:message key="user.staff.designation2" /></option>
                                <option><fmt:message key="user.staff.designation3" /></option>
                                <option><fmt:message key="user.staff.designation4" /></option>
                                <option><fmt:message key="user.staff.designation5" /></option>
                                <option><fmt:message key="user.staff.designation6" /></option>
                            </select>
                        </div>
                    </div>
                    <!--department-->
                    <div class="col-xs-4"  id="department"  style="display: none">
                        <div class="col-xs-4">
                            <label class=" control-label">
                                <fmt:message key="user.userview.staff.filter.department" />
                            </label>
                        </div>
                        <div class="col-xs-8">
                            <select class="form-control" id="department_d">
                                <option><fmt:message key="user.customer.select" /></option>
                                <option><fmt:message key="user.staff.department1"  /></option>
                                <option><fmt:message key="user.staff.department2"  /></option>
                                <option><fmt:message key="user.staff.department3" /></option>
                                <option><fmt:message key="user.staff.department4"/></option>
                            </select>
                        </div>
                    </div>
                    <!--branch-->
                    <div class="col-xs-4" id="branch" style="display: none">
                        <div class="col-xs-4">
                            <label class=" control-label">
                                <fmt:message key="user.userview.staff.filter.branch" />
                            </label>
                        </div>
                        <div class="col-xs-8">
                            <select class="form-control" id="branch_d" >
                                <option><fmt:message key="user.customer.select" /></option>
                                <option><fmt:message key="user.staff.branch1" /></option>
                                <option><fmt:message key="user.staff.branch2" /></option>
                                <option><fmt:message key="user.staff.branch3" /></option>
                                <option><fmt:message key="user.staff.branch4" /></option>

                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </div>
    <%--<div class="col-sm-1"></div>--%>
</div>

<!--staff detail table is included -->
<table  id="tableStaff">
</table>
<div id="paginationStaff" class="text-center">
</div>
<div id="pagination2Staff" class="text-center">
</div>


<!--modelto appear when banning a Customer or Staff user -->
<div class="modal fade" id="removeStaffModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-trash"></span>
                   <fmt:message key="user.staffblock.modal.title" />
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label id="lblBlockStaffMsg">
                        <fmt:message key="user.block.modal.heading" />
                    </label><br><br>

                    <div align="center">
                        <label id="lblBlockStaffId"> </label><br>
                        <label id="lblBlockStaffName"></label><br>
                    </div>
                </div>
                <%--<div class="toaster" style="position: absolute; top: 230px; right: 520px;width: 300px;  z-index: 50000;"></div>--%>
                <toaster toaster-options="{'time-out': 3000,'position-class': 'toast-top-right'}"></toaster>

            </div>
            <div class="modal-footer" align="right">
                <div class="col-xs-3"></div>
                <div class="col-xs-2" >
                    <button class="btn btn-success" type="button" value="cancel" id="btnCnclBlockStaff" data-dismiss="modal"
                            aria-hidden="true">
                        <fmt:message key="user.block.modal.button.cancel"  />
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                </div>
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                    <button class="btn btn-success" type="button" value="Yes" id="btnBlockStaff">
                        <fmt:message key="user.block.modal.button.yes"  />
                        <span class="glyphicon glyphicon-ok"></span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


    <%--model to edit a staff user--%>
    <div class="modal fade" id="editStaffModal">
        <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-edit"></span>
                    <fmt:message key="user.staff.update.modal.heading" />
                    <label id="currents_user"></label>
                </div>
            </div>
            <div class="modal-body">
                <form:form role="form" id="update_customer_form"  method="POST" class="form-horizontal"
                           action="/admin/users/staff/editStaff">
                    <br>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                                <fmt:message key="user.useradd.form.name" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-5">

                                        <form:input path="title" class="form-control" id="titles"
                                                    type="text" disabled="true"/>
                                    </div>
                                    <div class="col-xs-7">
                                        <form:input path="firstName" class="form-control" id="updates-first-name"
                                                    type="text"  style="width: 140px" required="required"/>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                        <%--<div class="col-xs-4"></div>--%>
                                    <div class="col-xs-12">
                                        <form:input path="lastName" class="form-control" id="updates-last-name" type="text"
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
                                        <form:input path="addressL1" class="form-control" id="updates_addLine1"
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
                                        <form:input path="addressL2" class="form-control" id="updates_addLine2" type="text"
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
                                        <form:input path="addressL3" class="form-control" id="updates_city" type="text"
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
                                <fmt:message key="user.useradd.form.mobile"  />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="mobile" class="form-control" id="updates_mobile" type="text"
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
                                <fmt:message key="user.useradd.form.email" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="email" class="form-control" id="updates_email" type="text"
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
                                <fmt:message key="user.useradd.form.username" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="username" class="form-control" id="updates_username" type="text"
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
                                <fmt:message key="user.useradd.form.password"  />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="password" class="form-control" id="updates_password" type="password"
                                                    style="width: 220px"   onchange="validatePassword(updates_password)"
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
                                <fmt:message key="user.useradd.form.cpassword" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:input path="password" class="form-control" id="updates_cpassword" type="password"
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
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                                <fmt:message key="user.useradd.form.designation"  />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:select path="designation" class="form-control" id="updates_designation"
                                                     style="width: 220px">
                                            <form:option value="System Admin">
                                                <fmt:message key="user.staff.designation1"  />
                                            </form:option>
                                            <form:option value="System Manager">
                                                <fmt:message key="user.staff.designation2" />
                                            </form:option>
                                            <form:option value="Customer Manager">
                                                <fmt:message key="user.staff.designation3"  />
                                            </form:option>
                                            <form:option value="Cashier">
                                                <fmt:message key="user.staff.designation4" />
                                            </form:option>
                                            <form:option value="Main Chef">
                                                <fmt:message key="user.staff.designation5" />
                                            </form:option>
                                            <form:option value="Reporting Manager">
                                                <fmt:message key="user.staff.designation6"  />
                                            </form:option>
                                        </form:select>
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
                                <fmt:message key="user.useradd.form.department" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:select path="department" class="form-control" id="updates_department"
                                                     style="width: 220px">
                                            <form:option value="Finance">
                                                <fmt:message key="user.staff.department1" />
                                            </form:option>
                                            <form:option value="Administration">
                                                <fmt:message key="user.staff.department2"  />
                                            </form:option>
                                            <form:option value="Logistic">
                                                <fmt:message key="user.staff.department3"  />
                                            </form:option>
                                            <form:option value="Reporting">
                                                <fmt:message key="user.staff.department4" />
                                            </form:option>
                                        </form:select>
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
                                <fmt:message key="user.useradd.form.branch" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <form:select path="branch" class="form-control" id="updates_branch" style="width: 220px">
                                            <form:option value="Colombo">
                                                <fmt:message key="user.staff.branch1"  />
                                            </form:option>
                                            <form:option value="Gampaha">
                                                <fmt:message key="user.staff.branch2" />
                                            </form:option>
                                            <form:option value="Ja-Ela">
                                                <fmt:message key="user.staff.branch3"/>
                                            </form:option>
                                            <form:option value="Kadana">
                                                <fmt:message key="user.staff.branch4" />
                                            </form:option>
                                        </form:select>
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
                            <button class="btn btn-success" type="button" value="cancel" id="btnCnclUpdateStaff" data-dismiss="modal"
                                    aria-hidden="true">
                                <fmt:message key="user.block.modal.button.cancel"  />
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </div>
                        <div class="col-xs-2"></div>
                        <div class="col-xs-2" >
                            <form:button class="btn btn-success" type="submit" value="Update" id="btnUpdateStaff">
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