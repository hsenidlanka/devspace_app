<html xmlns:jsp="http://java.sun.com/JSP/Page">


<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>

    <spring:url value="/themes/hsenid/js/staff_table.js" var="js1"/>
    <script src="${js1}"></script>

</head>
<body>
<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />


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
                                <fmt:message key="user.userview.staff.label.filter" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <!--checkboxes to select-->
                        <div class="col-xs-10" id="checkboxDiv">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="1" id="desigCheck" onchange="showMe()">
                                <fmt:message key="user.userview.staff.filter.designation" bundle="${bundle1}"/>
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="2" id="depCheck" onchange="showMe()">
                                <fmt:message key="user.userview.staff.filter.department" bundle="${bundle1}"/>
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="3" id="branchCheck" onchange="showMe()">
                                <fmt:message key="user.userview.staff.filter.branch" bundle="${bundle1}"/>
                            </label>
                        </div>
                    </div>

                    <div class="col-xs-4" style="width: 36%">
                        <div class="col-xs-8">
                            <input class="form-control"  placeholder=" Name" type="text" id="cname" >

                        </div>
                        <div class="col-xs-1">
                            <button type="button" class="btn btn-success">
                                <span class="glyphicon glyphicon-search"></span>
                                <fmt:message key="user.userview.staff.search" bundle="${bundle1}"/>
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
                                <fmt:message key="user.userview.staff.filter.designation" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <div class="col-xs-8">
                            <select class="form-control" >
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
                    <div class="col-xs-4"  id="department"  style="display: none">
                        <div class="col-xs-4">
                            <label class=" control-label">
                                <fmt:message key="user.userview.staff.filter.department" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <div class="col-xs-8">
                            <select class="form-control">
                                <option><fmt:message key="user.staff.department1" bundle="${bundle1}" /></option>
                                <option><fmt:message key="user.staff.department2" bundle="${bundle1}" /></option>
                                <option><fmt:message key="user.staff.department3" bundle="${bundle1}" /></option>
                                <option><fmt:message key="user.staff.department4" bundle="${bundle1}" /></option>
                            </select>
                        </div>
                    </div>
                    <!--branch-->
                    <div class="col-xs-4" id="branch" style="display: none">
                        <div class="col-xs-4">
                            <label class=" control-label">
                                <fmt:message key="user.userview.staff.filter.branch" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <div class="col-xs-8">
                            <select class="form-control"  >
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
    <%--<div class="col-sm-1"></div>--%>
</div>

<!--staff detail table is included -->
<table  id="tableStaff">
</table>


<!--modelto appear when banning a Customer or Staff user -->
<div class="modal fade" id="removeStaffModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-trash"></span>
                   <fmt:message key="user.staffblock.modal.title" bundle="${bundle1}" />
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label id="lblBlockStaffMsg">
                        <fmt:message key="user.block.modal.heading" bundle="${bundle1}" />
                    </label><br><br>

                    <div align="center">
                        <label id="lblBlockStaffId"> </label><br>
                        <label id="lblBlockStaffName"></label><br>
                    </div>
                </div>
            </div>
            <div class="modal-footer" align="right">
                <button class="btn btn-success" type="button" value="Yes" id="btnBlockStaff">
                    <fmt:message key="user.block.modal.button.yes" bundle="${bundle1}" />
                </button>
                <button class="btn btn-success" type="button" value="cancel" id="btnCnclBlockStaff" data-dismiss="modal"
                        aria-hidden="true">
                        <fmt:message key="user.block.modal.button.cancel" bundle="${bundle1}" />
                </button>
            </div>
        </div>
    </div>
</div>


<!--modelto appear when editing the Staff user -->
<div class="modal fade" id="editStaffModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-trash"></span>
                    <fmt:message key="user.staffblock.modal.title" bundle="${bundle1}" />
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">


                </div>
            </div>
            <div class="modal-footer" align="right">
                <button class="btn btn-success" type="button" value="Yes" id="btnBlockStaff">
                    <fmt:message key="user.block.modal.button.yes" bundle="${bundle1}" />
                </button>
                <button class="btn btn-success" type="button" value="cancel" id="btnCnclBlockStaff" data-dismiss="modal"
                        aria-hidden="true">
                    <fmt:message key="user.block.modal.button.cancel" bundle="${bundle1}" />
                </button>
            </div>
        </div>
    </div>
</div>

</body>
</html>