<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

    <spring:url value="/themes/hsenid/js/bannedStaff_table.js" var="tableBannedStaff"/>
    <script src="${tableBannedStaff}"></script>
    <spring:url value="/themes/hsenid/js/staffPagination.js" var="paginationStaff"/>
    <script src="${paginationStaff}"></script>

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
                    <div class="col-xs-2">
                        <label class=" control-label" >
                            <fmt:message key="user.userview.staff.label.filter" bundle="${bundle1}"/>
                        </label>
                    </div>
                    <!--checkboxes to select-->
                    <div class="col-xs-10" id="checkboxDiv">
                        <div class="col-xs-5">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="1" id="blockCheck" onchange="showMeBlocked()">
                                <fmt:message key="user.userview.staff.filter.designation" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <div class="col-xs-3">
                            <label class="checkbox-inline">
                                <input  type="checkbox" value="2" id="bsCheck" onchange="showMeBlocked()" checked>
                                <fmt:message key="user.userview.staff.filter.department" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <div class="col-xs-4">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="3" id="branchbCheck" onchange="showMeBlocked()">
                                <fmt:message key="user.userview.staff.filter.branch" bundle="${bundle1}"/>
                            </label>
                        </div>
                    </div>
                    <!--<div class="col-xs-2"></div>-->
                </div>

                <div class="col-xs-4" style="width: 36%">
                    <div class="col-xs-8">
                        <input class="form-control"  placeholder=" Name" type="text" id="bsname"  >
                    </div>
                    <div class="col-xs-1">
                        <button type="button" class="btn btn-success" id="filterButtonStaffB">
                            <span class="glyphicon glyphicon-search"></span>
                            <fmt:message key="user.userview.staff.search" bundle="${bundle1}"/>
                        </button>
                    </div>
                    <div class="col-xs-4"></div>
                </div>
            </div>
        </div>

        <!--filter by elements selected by checboxes are defined here-->
        <div class="form-group">
            <div class="row">
                <!--designation -->
                <div class="col-xs-4" id="designationb"  style="display: none">
                    <div class="col-xs-4">
                        <label class=" control-label">
                            <fmt:message key="user.userview.staff.filter.designation" bundle="${bundle1}"/>
                        </label>
                    </div>
                    <div class="col-xs-8">
                        <select class="form-control" id="designation_db">
                            <option><fmt:message key="user.customer.select" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.designation1" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.designation2" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.designation3" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.designation4" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.designation5" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.designation6" bundle="${bundle1}" /></option>
                        </select>
                    </div>
                </div>
                <!--department-->
                <div class="col-xs-4"  id="departmentb"  style="display: none">
                    <div class="col-xs-4">
                        <label class=" control-label">
                            <fmt:message key="user.userview.staff.filter.department" bundle="${bundle1}"/>
                        </label>
                    </div>
                    <div class="col-xs-8">
                        <select class="form-control" id="department_db">
                            <option><fmt:message key="user.customer.select" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.department1" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.department2" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.department3" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.department4" bundle="${bundle1}" /></option>
                        </select>
                    </div>
                </div>
                <!--branch-->
                <div class="col-xs-4" id="branchb" style="display: none">
                    <div class="col-xs-4">
                        <label class=" control-label">
                            <fmt:message key="user.userview.staff.filter.branch" bundle="${bundle1}"/>
                        </label>
                    </div>
                    <div class="col-xs-8">
                        <select class="form-control" id="branch_db" >
                            <option><fmt:message key="user.customer.select" bundle="${bundle1}" /></option>
                            <option><fmt:message key="user.staff.branch1" bundle="${bundle1}"/></option>
                            <option><fmt:message key="user.staff.branch2" bundle="${bundle1}"/></option>
                            <option><fmt:message key="user.staff.branch3" bundle="${bundle1}"/></option>
                            <option><fmt:message key="user.staff.branch4" bundle="${bundle1}"/></option>

                        </select>
                    </div>
                </div>
            </div>
        </div>
    </fieldset>
</div>


<table  id="tableBannedstaff">
</table>
<div id="paginationBannedStaff" class="text-center">
</div>
<div id="pagination2BannedStaff" class="text-center">
</div>



<!--modelto appear when replacing a banned Customer or Staff user -->
<div class="modal fade" id="replaceStaffModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-refresh"></span>
                    <fmt:message key="user.staffreplace.modal.title" bundle="${bundle1}" />
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label id="lblBlockStaffMsg"><fmt:message key="user.replace.modal.heading" bundle="${bundle1}" /></label><br><br>

                    <div align="center">
                        <label id="lblUnblockStaffId"></label><br>
                        <label id="lblUnblockStaffName"></label><br>
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
                    <button class="btn btn-success" type="button" value="Yes" id="btnUnblockStaff">
                        <fmt:message key="user.block.modal.button.yes" bundle="${bundle1}" />
                        <span class="glyphicon glyphicon-ok"></span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<!--modelto appear when viewing the details of the blocked user (STAFF) -->
<div class="modal fade" id="bannedStaffModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-edit"></span>
                    <fmt:message key="user.blockuserview.customer.detail.modal.title" bundle="${bundle1}" />
                    <label id="currentbs_user"></label>
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
                                        <input class="form-control details" id="staffDetail_title" type="text" readonly/>
                                    </div>
                                    <div class="col-xs-7">
                                        <input class="form-control details" id="staffDetail_first-name" type="text"
                                               style="width: 140px" readonly/>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                        <%--<div class="col-xs-4"></div>--%>
                                    <div class="col-xs-12">
                                        <input  class="form-control details" id="staffDetail_last-name" type="text"
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
                                        <input class="form-control details" id="staffDetail_addLine1" style="width:379px"
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
                                        <input  class="form-control details" id="staffDetail_addLine2" type="text"
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
                                        <input  class="form-control details" id="staffDetail_city" type="text"
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
                                        <input  class="form-control details" id="staffDetail_mobile" type="text"
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
                                        <input  class="form-control details" id="staffDetail_email" type="text"
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
                                        <input  class="form-control details" id="staffDetail_username" type="text"
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
                                <fmt:message key="user.useradd.form.designation" bundle="${bundle1}" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input class="form-control details" id="staffDetail_designation" type="text"
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
                                <fmt:message key="user.useradd.form.department" bundle="${bundle1}" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input class="form-control details" id="staffDetail_department" type="text"
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
                                <fmt:message key="user.useradd.form.branch" bundle="${bundle1}" />
                            </label>
                            <div class="col-xs-9">
                                <div class="col-xs-6">
                                    <div class="col-xs-12">
                                        <input class="form-control details" id="staffDetail_branch" type="text"
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
                                        <input class="form-control details" id="staffDetail_status" type="text"
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