<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Item</title>
    <!-- include common CSS, fonts and js -->
    <jsp:include page="../adminTop.jsp"/>

    <%-- Other css --%>
    <spring:url value="/themes/hsenid/css/itemMgt.css" var="cssItem"/>
    <link href="${cssItem}" rel="stylesheet">

    <%--  other javascripts --%>
    <spring:url value="/themes/hsenid/js/itemMgt.js" var="itemJs"/>
    <spring:url value="/themes/hsenid/js/itemFilter.js" var="itmFltr"/>
    <spring:url value="/themes/hsenid/js/myAlerts.js" var="myAlerts"/>
    <spring:url value="/themes/hsenid/js/tableItems.js" var="tblViewItm"/>
    <spring:url value="/themes/hsenid/js/itemPagination.js" var="itmPaging"/>

    <script src="${itemJs}"></script>
    <script src="${itmFltr}"></script>
    <script src="${myAlerts}"></script>
    <script src="${tblViewItm}"></script>
    <script src="${itmPaging}"></script>


</head>
<body>
<fmt:setBundle basename="messages_en" var="bundle1"/>
<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="item.itemview.heading" bundle="${bundle1}"/>
    </div>
</div>
<br>

<div>
    <div id="add-item-breadcrumb-position">
        <ul class="breadcrumb breadcrumb-menu">
            <li><a href="https://localhost:8443/admin/users/list"><fmt:message key="item.itemview.breadcrumb.home"
                                                                               bundle="${bundle1}"/> </a></li>
            <li><a href="https://localhost:8443/admin/users/list"><fmt:message
                    key="item.itemview.breadcrumb.itemmanagement" bundle="${bundle1}"/> </a></li>
            <li class="active"><a href="#"><fmt:message key="item.itemview.breadcrumb.viewitem"
                                                        bundle="${bundle1}"/> </a></li>
        </ul>
    </div>
</div>

<div class="form-box" id="view-item-form-box">
    <div class="panel panel-default">
        <div class="panel-heading common-form-headings">
            <h3 class="default-panel-headings"><fmt:message key="item.itemview.panel.heading" bundle="${bundle1}"/></h3>
        </div>

        <div class="panel-body">

            <%--checkboxes for filters and...--%>
            <div class="col-xs-12">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border" id="itemCriteria"></legend>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-2" style="text-align: right">
                                <label class=" control-label">
                                    <fmt:message key="item.itemview.label.filter" bundle="${bundle1}"/>
                                </label>
                            </div>
                            <!--checkboxes to select-->
                            <div class="col-xs-10" id="checkItmDiv" style="vertical-align: middle">
                                <div class="col-xs-3">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" value="1" id="catCheck" onchange="showItemCheck()">
                                        <fmt:message key="item.itemview.label.chkbx.cat" bundle="${bundle1}"/>
                                    </label>
                                </div>
                                <div class="col-xs-3">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" value="3" id="subCatCheck"
                                               onchange="showItemCheck()">
                                        <fmt:message key="item.itemview.label.chkbx.subcat" bundle="${bundle1}"/>
                                    </label>
                                </div>
                                <div class="col-xs-6">
                                    <div class="row itemSearchBar">

                                        <div class="col-sm-8">
                                            <input class="form-control" id="txtViewSearchItem" type="text"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <button type="button" class="btn btn-success" id="btnViewSearchItem"><span
                                                    class="glyphicon glyphicon-search"></span> Search
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div id="filterCatDiv" style="display: none" class="col-xs-4">
                                <div class="col-xs-3" style="text-align: right">
                                </div>
                                <div class="col-xs-9">
                                    <select class="form-control" id="selectCatFltr">
                                        <option value="">--Category--</option>
                                    </select>
                                </div>
                            </div>
                            <div id="filterSubcatDiv" style="display: none" class="col-xs-4">
                                <div class="col-xs-3"></div>
                                <div class="col-xs-9">
                                    <select class="form-control" id="selectSubCatFltr">
                                        <option>--Sub-category--</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </div>
            <br>

            <form:form class="form-horizontal" role="form" id="frmViewItem" commandName="viewItem"
                       modelAttribute="viewItem" method="post" action="/view_item">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border"><fmt:message key="item.itemview.form.legend"
                                                                  bundle="${bundle1}"/></legend>

                    <!--table of all Item details-->
                    <table id="tblItems">

                    </table>
                    <div id="pagination" class="text-center">
                    </div>
                    <div id="pagination2" class="text-center">
                    </div>
                </fieldset>

                <br>
                <br>
            </form:form>
        </div>

    </div>
</div>


<!--Modal for edit item-->
<div class="modal fade" id="itemEditModal" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header item-modal-header-style">
                <button id="editModelClose" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-pencil"></span><fmt:message
                        key="item.itemedit.panel.heading" bundle="${bundle1}"/>
                </div>
            </div>
            <form:form class="form-horizontal" role="form" id="frmEditItem" method="post"
                       action="/admin/items/update_item" enctype="multipart/form-data">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border">
                        <fmt:message key="item.itemedit.form.legend" bundle="${bundle1}"/>
                    </legend>
                    <div class="modal-body">

                        <div class="row">
                            <div class="col-xs-6">
                                <div class="form-group">
                                    <div class="row">

                                        <div class="col-xs-4">
                                            <label class="control-label" style="float: right;">
                                                <fmt:message key="item.itemedit.form.name" bundle="${bundle1}"/>
                                            </label>
                                        </div>
                                        <div class="col-xs-8">
                                            <form:input class="form-control" id="txtEditName" type="text"
                                                        path="itemName" readonly="true"/>
                                            <form:hidden path="itemId" id="hiddenId"/>
                                        </div>

                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="row">

                                        <div class="col-xs-4">
                                            <label class="control-label" style="float:right;">
                                                <fmt:message key="item.itemedit.form.category" bundle="${bundle1}"/>
                                            </label>
                                        </div>
                                        <div class="col-xs-8">
                                            <form:select class="form-control" id="selectCatedt" path="categoryName">
                                                <form:option value="-" label="--Select Category--"/>
                                                <c:forEach var="listEdit" items="${listCatEdit}">
                                                    <form:option id="${listEdit}"
                                                                 value="${listEdit}">${listEdit}</form:option>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <label class="control-label" style="float: right">
                                                <fmt:message key="item.itemedit.form.subcategory" bundle="${bundle1}"/>
                                            </label>
                                        </div>
                                        <div class="col-xs-8">
                                            <form:select class="form-control" id="slctEditSubCat"
                                                         path="subCategoryName">

                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6">

                                <div class="col-xs-4" style="text-align: right">
                                    <label for="txtEditDesc" class="control-label">
                                        <fmt:message key="item.itemedit.form.desciption" bundle="${bundle1}"/>
                                    </label>
                                </div>

                                <div class="col-xs-8">
                                    <form:textarea class="form-control" rows="5" id="txtEditDesc" path="description"/>
                                </div>

                            </div>
                        </div>


                        <div class="form-group">
                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="col-xs-4">
                                        <label class="control-label" style="float: right">
                                            <fmt:message key="item.itemedit.form.itemprice" bundle="${bundle1}"/>
                                        </label>
                                    </div>

                                    <div class="col-xs-8">
                                        <div class="row item-tbl-row item-tbl-hdr">
                                            <div class="col-xs-5">
                                                <strong><fmt:message key="item.itemedit.form.item.check.size"
                                                                     bundle="${bundle1}"/> </strong>
                                            </div>
                                            <div class="col-xs-7">
                                                <strong><fmt:message key="item.itemedit.form.item.text.price"
                                                                     bundle="${bundle1}"/> </strong>
                                            </div>
                                        </div>

                                        <div class="row item-tbl-row" id="edtItmChkbxReg">

                                            <div class="col-xs-5">
                                                <label class="checkbox-inline">
                                                    <form:checkbox id="chkedtReg" value="regular" class="checkbox aa"
                                                                   path="size" label="Regular"
                                                                   onclick="disableTxt(this, 'txtEdtPrcReg')"/>
                                                </label>
                                            </div>
                                            <div class="col-xs-7">
                                                <form:input id="txtEdtPrcReg" type="text" class="form-control price"
                                                            path="price" disabled="true"/>
                                            </div>
                                        </div>

                                        <div class="row item-tbl-row" id="edtItmChkbxMed">

                                            <div class="col-xs-5">
                                                <label class="checkbox-inline">
                                                    <form:checkbox id="chkedtMed" value="medium" class="checkbox aa"
                                                                   path="size" label="Medium"
                                                                   onclick="disableTxt(this, 'txtEdtPrcMed')"/>
                                                </label>
                                            </div>
                                            <div class="col-xs-7">
                                                <form:input type="text" id="txtEdtPrcMed" class="form-control price"
                                                            path="price" disabled="true"/>
                                            </div>
                                        </div>

                                        <div class="row item-tbl-row" id="edtItmChkbxLrg">

                                            <div class="col-xs-5">
                                                <label class="checkbox-inline">
                                                    <form:checkbox id="chkedtLrg" value="large" class="checkbox aa"
                                                                   path="size" label="Large"
                                                                   onclick="disableTxt(this, 'txtEdtPrcLrg')"/>

                                                </label>
                                            </div>
                                            <div class="col-xs-7">
                                                <form:input id="txtEdtPrcLrg" type="text" class="form-control price"
                                                            path="price" disabled="true"/>
                                            </div>
                                        </div>

                                        <div class="row item-tbl-row" id="edtItmChkbxOthr">
                                            <div class="col-xs-5">
                                                <label class="checkbox-inline">
                                                    <form:checkbox id="chkedtOthr" value="other" class="checkbox aa"
                                                                   path="size" label="Other"
                                                                   onclick="disableTxt(this, 'txtEdtPrcOthr')"/>

                                                </label>
                                            </div>
                                            <div class="col-xs-7">
                                                <form:input type="text" id="txtEdtPrcOthr" class="form-control price"
                                                            path="price" disabled="true"/>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-4" style="text-align: right">
                                        <label class="control-label">
                                            <fmt:message key="item.itemedit.form.itemimages" bundle="${bundle1}"/>
                                        </label>
                                    </div>

                                    <div class="col-xs-8">
                                            <%--<form:button type="button" class="btn btn-success" id="btnEditImage"><span
                                                    class="glyphicon glyphicon-open"></span><fmt:message key="item.itemedit.form.button.upldimages"/>
                                            </form:button>--%>
                                        <form:input type="file" class="file_upload-item btn btn-default" path="imageUrl"
                                                    id="btnEditImage" value="Browse"/>
                                        <br><br>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="col-xs-2" style="text-align: right">
                                        <label class="control-label">
                                            <fmt:message key="item.itemedit.form.itemtype" bundle="${bundle1}"/>
                                        </label>
                                    </div>

                                    <div class="col-xs-10">
                                        <label class="radio-inline">
                                            <form:radiobutton name="optEditType" value="veg" id="radioVeg" path="type"
                                                              label="Veg"/>
                                        </label>
                                        <label class="radio-inline">
                                            <form:radiobutton name="optEditType" value="non-veg" id="radioNveg"
                                                              path="type" label="Non-veg"/>
                                        </label>
                                        <label class="radio-inline">
                                            <form:radiobutton name="optEditType" value="carbonated" id="radioCarbon"
                                                              path="type" label="Carbonated"/>
                                        </label>
                                        <label class="radio-inline">
                                            <form:radiobutton name="optEditType" value="non-carbonated"
                                                              id="radioNoncarbon" path="type" label="Non-carbonated"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div style="text-align: center; width: 75%; margin: auto;" id="toaster2"></div>
                    </div>

                    <div class="modal-footer" align="right">
                        <button class="btn btn-success" type="submit" value="Yes" id="btnUpdtItm">
                            <fmt:message key="item.itemedit.form.button.save" bundle="${bundle1}"/>
                        </button>
                        <button class="btn btn-success" type="reset" value="cancel" id="btnCnclUpdtItm"
                                data-dismiss="modal">
                            <fmt:message key="item.itemedit.form.button.cancel" bundle="${bundle1}"/>
                        </button>
                    </div>
                </fieldset>
            </form:form>
        </div>
    </div>
</div>


<!--Modal for delete item-->
<div class="modal fade" id="itemDeleteModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header item-modal-header-style">
                <button class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-trash"></span><fmt:message
                        key="item.deletemodal.title" bundle="${bundle1}"/>
                </div>
            </div>
            <form:form class="form-horizontal" role="form" id="form-Item-Delete">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border"><fmt:message key="item.deletemodal.warning.message"
                                                                  bundle="${bundle1}"/></legend>
                    <div class="modal-body">
                        <div class="form-group">
                            <label id="lblDeltItmMsg"><fmt:message key="item.deletemodal.warning.question"
                                                                   bundle="${bundle1}"/> </label><br><br>

                            <div align="center" class="row">
                                <div class="col-xs-6" style="text-align: right">
                                    <label>
                                        <fmt:message key="item.deletemodal.itemid" bundle="${bundle1}"/>
                                    </label>
                                </div>
                                <div class="col-xs-6" style="text-align: left"><label id="lblDeltItmId"
                                                                                      class="delete-lables"> </label>
                                </div>
                                <br>

                                <div class="col-xs-6" style="text-align: right"><label><fmt:message
                                        key="item.deletemodal.itemname" bundle="${bundle1}"/></label></div>
                                <div class="col-xs-6" style="text-align: left"><label id="lblDeltItmName"
                                                                                      class="delete-lables"> </label>
                                </div>
                                <br>

                                <div class="col-xs-6" style="text-align: right">
                                    <label>
                                        <fmt:message key="item.deletemodal.itemtype" bundle="${bundle1}"/>
                                    </label>
                                </div>
                                <div class="col-xs-6" style="text-align: left">
                                    <label id="lblDeltItmType" class="delete-lables">
                                    </label>
                                </div>
                                <br>
                            </div>
                        </div>
                        <div style="text-align: center; z-index: 50000; width: 75%; margin: auto;" id="toaster"></div>
                    </div>

                    <div class="modal-footer" align="right">
                        <button class="btn btn-success" type="button" value="Yes" id="btnDeltItm">
                            <fmt:message key="item.deletemodal.button.yes" bundle="${bundle1}"/>
                        </button>
                        <button class="btn btn-success" type="button" value="cancel" id="btnCnclDeltItm"
                                data-dismiss="modal">
                            <fmt:message key="item.deletemodal.button.no" bundle="${bundle1}"/>
                        </button>
                    </div>
                </fieldset>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
