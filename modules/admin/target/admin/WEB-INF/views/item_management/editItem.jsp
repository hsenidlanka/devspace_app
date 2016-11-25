<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Item</title>
    <!-- include common CSS, fonts and js -->
    <jsp:include page="../adminTop.jsp"/>

    <%-- Other css --%>
    <spring:url value="/themes/hsenid/css/itemMgt.css" var="cssItem"/>
    <link href="${cssItem}" rel="stylesheet">

    <%--  other javascripts --%>
    <spring:url value="/themes/hsenid/js/itemMgt.js" var="itemJs"/>
    <spring:url value="/themes/hsenid/js/myAlerts.js" var="myAlerts"/>
    <spring:url value="/themes/hsenid/js/tableItems.js" var="tblViewItm"/>

    <script src="${itemJs}"></script>
    <script src="${myAlerts}"></script>
    <script src="${tblViewItm}"></script>


</head>
<body>
<fmt:bundle basename="messages_en">
<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="item.itemedit.heading"/>
    </div>
</div>
<br>

<div>
    <div id="add-item-breadcrumb-position">
        <ul class="breadcrumb breadcrumb-menu">
            <li><a href="https://localhost:8443/admin/users/list"><fmt:message key="item.itemedit.breadcrumb.home"/> </a></li>
            <li><a href="#"><fmt:message key="item.itemedit.breadcrumb.itemmanagement"/> </a></li>
            <li class="active"><a href="#"><fmt:message key="item.itemedit.breadcrumb.edititem"/> </a></li>
        </ul>
    </div>
</div>

<div class="form-box" id="edit-item-form-box">
    <div class="panel panel-default">
        <div class="panel-heading common-form-headings">
            <h3 class="default-panel-headings"><span class="glyphicon glyphicon-pencil"></span><fmt:message key="item.itemedit.panel.heading"/> </h3>
        </div>

        <div class="panel-body">

            <div class="row itemSearchBar">
                <div class="col-sm-1"></div>
                <div class="col-sm-3">
                    <label class="control-label" style="float: right;" path="lblItmNm">
                       <fmt:message key="item.itemedit.search.itemname"/>
                    </label>

                </div>
                <div class="col-sm-4">
                    <input class="form-control" id="txtSearchItem" type="text"> </input>
                </div>
                <div class="col-sm-3">
                    <button type="button" class="btn btn-success" id="btnSearchItem"><span
                            class="glyphicon glyphicon-search"></span><fmt:message key="item.itemedit.search.button"/> </button>
                </div>
                <div class="col-sm-1"></div>
            </div>
            <br>

           <%-- <form:form class="form-horizontal" role="form" id="frmEditItem" action="/edit_item" method="post" commandName="editItem" modelAttribute="editItem">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border"><fmt:message key="item.itemedit.form.legend"/> </legend>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <label class="control-label" style="float: right;">
                                        <fmt:message key="item.itemedit.form.name"/>
                                    </label>
                                </div>
                                <div class="col-xs-8">
                                    <form:input class="form-control" id="txtEditName" type="text" path="itemName"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <label class="control-label" style="float:right;">
                                        <fmt:message key="item.itemedit.form.category"/>
                                    </label>
                                </div>
                                <div class="col-xs-8">
                                        <form:select class="form-control" id="selectCatedt" path="categoryName">
                                            <form:option value="-" label="--Select Category--"/>
                                            <c:forEach var="listEdit" items="${listCatEdit}">
                                                <form:option id="${listEdit}" value="${listEdit}">${listEdit}</form:option>
                                            </c:forEach>
                                        </form:select>
                                </div>

                            </div>
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <label class="control-label" style="float: left;">
                                        <fmt:message key="item.itemedit.form.itemtype"/>
                                    </label>
                                </div>
                                <div class="col-xs-8">
                                    <label class="radio-inline">
                                        <form:radiobutton name="optEditType" value="veg" id="radioVeg" path="type" label="Veg" checked="true"/>
                                    </label>
                                    <label class="radio-inline">
                                        <form:radiobutton name="optEditType" value="non-veg" id="radioNveg" path="type" label="Non-veg"/>
                                    </label><br>
                                    <label class="radio-inline">
                                        <form:radiobutton name="optEditType" value="carbonated" id="radioCarbon" path="type" label="Carbonated"/>
                                    </label>
                                    <label class="radio-inline">
                                        <form:radiobutton name="optEditType" value="non-carbonated" id="radioNoncarbon" path="type" label="Non-carbonated"/>
                                    </label>
                                </div>
                            </div>
                            &lt;%&ndash;<div class="col-xs-6">
                                <div class="col-xs-4">
                                    <label for="txtEditDesc" class="control-label">
                                        <fmt:message key="item.itemedit.form.desciption"/>
                                    </label>
                                </div>

                                <div class="col-xs-8">
                                    <form:textarea class="form-control" rows="5" id="txtEditDesc" path="description"/>
                                </div>
                            </div>&ndash;%&gt;
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <label class="control-label" style="float: right">
                                       <fmt:message key="item.itemedit.form.subcategory"/>
                                    </label>
                                </div>
                                <div class="col-xs-8">
                                     <select class="form-control" id="slctEditSubCat">

                                     </select>
                                </div>

                            </div>
                            <div class="col-xs-6">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <label class="control-label" style="float: right">
                                        <fmt:message key="item.itemedit.form.itemprice"/>
                                    </label>
                                </div>

                                <div class="col-xs-8">
                                    <div class="row item-tbl-row item-tbl-hdr">
                                        <div class="col-xs-5">
                                            <strong><fmt:message key="item.itemedit.form.item.check.size"/> </strong>
                                        </div>
                                        <div class="col-xs-7">
                                            <strong><fmt:message key="item.itemedit.form.item.text.price"/> </strong>
                                        </div>
                                    </div>

                                    <div class="row item-tbl-row" id="edtItmChkbxReg">

                                        <div class="col-xs-5">
                                            <label class="checkbox-inline">
                                                <form:checkbox value="regular" class="checkbox" path="size" label="Regular" onclick="document.getElementById('txtEdtPrcReg').disabled=!this.checked;"/>
                                            </label>
                                        </div>
                                        <div class="col-xs-7">
                                            <form:input id="txtEdtPrcReg" type="text" class="form-control price" path="price" disabled="true"/>
                                        </div>
                                    </div>

                                    <div class="row item-tbl-row" id="edtItmChkbxMed">

                                        <div class="col-xs-5">
                                            <label class="checkbox-inline">
                                            <form:checkbox value="medium" class="checkbox" path="size" label="Medium" onclick="document.getElementById('txtEdtPrcMed').disabled=!this.checked;"/>
                                            </label>
                                        </div>
                                        <div class="col-xs-7">
                                            <form:input type="text" id="txtEdtPrcMed" class="form-control price" path="price" disabled="true"/>
                                        </div>
                                    </div>

                                    <div class="row item-tbl-row" id="edtItmChkbxLrg">

                                        <div class="col-xs-5">
                                            <label class="checkbox-inline">
                                            <form:checkbox value="large" class="checkbox" path="size" label="Large" onclick="document.getElementById('txtEdtPrcLrg').disabled=!this.checked;"/>

                                            </label>
                                        </div>
                                        <div class="col-xs-7">
                                            <form:input id="txtEdtPrcLrg" type="text" class="form-control price" path="price" disabled="true"/>
                                        </div>
                                    </div>

                                    <div class="row item-tbl-row" id="edtItmChkbxOthr">
                                        <div class="col-xs-5">
                                            <label class="checkbox-inline">
                                            <form:checkbox value="other" class="checkbox" path="size" label="Other" onclick="document.getElementById('txtEdtPrcOthr').disabled=!this.checked;"/>

                                            </label>
                                        </div>
                                        <div class="col-xs-7">
                                            <form:input type="text" id="txtEdtPrcOthr" class="form-control price" path="price" disabled="true"/>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <label class="control-label">
                                        <fmt:message key="item.itemedit.form.itemimages"/>
                                    </label>
                                </div>

                                <div class="col-xs-8">
                                    <form:button type="button" class="btn btn-success" id="btnEditImage"><span
                                            class="glyphicon glyphicon-open"></span><fmt:message key="item.itemedit.form.button.upldimages"/>
                                    </form:button>
                                    <br><br>

                                    <div class="col-xs-8">
                                        <table class="table table-hover table-bordered table-condensed" id="">
                                            <tbody>
                                            <tr>
                                                <td><fmt:message key="item.itemedit.form.table.imagetag"/> </td>
                                                <td><input type="button" value="Update" class="form-control"></td>
                                                <td><input type="button" value="X" class="form-control"></td>
                                            </tr>
                                            <tr>
                                                <td><fmt:message key="item.itemedit.form.table.imagetag"/> </td>
                                                <td><input type="button" value="Update" class="form-control"></td>
                                                <td><input type="button" value="X" class="form-control"></td>
                                            </tr>
                                            <tr>
                                                <td><fmt:message key="item.itemedit.form.table.imagetag"/> </td>
                                                <td><input type="button" value="Update" class="form-control"></td>
                                                <td><input type="button" value="X" class="form-control"></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="col-xs-2" style="text-align: right">
                                    <label for="txtEditDesc" class="control-label">
                                        <fmt:message key="item.itemedit.form.itemtype"/>
                                    </label>
                                </div>

                                <div class="col-xs-10">
                                    <label class="radio-inline">
                                        <form:radiobutton name="optEditType" value="veg" id="radioVeg" path="type" label="Veg"/>
                                    </label>
                                    <label class="radio-inline">
                                        <form:radiobutton name="optEditType" value="non-veg" id="radioNveg" path="type" label="Non-veg"/>
                                    </label>
                                    <label class="radio-inline">
                                        <form:radiobutton name="optEditType" value="carbonated" id="radioCarbon" path="type" label="Carbonated"/>
                                    </label>
                                    <label class="radio-inline">
                                        <form:radiobutton name="optEditType" value="non-carbonated" id="radioNoncarbon" path="type" label="Non-carbonated"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                    &lt;%&ndash;<div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <label for="txtEditDesc" class="control-label">
                                        <fmt:message key="item.itemedit.form.desciption"/>
                                    </label>
                                </div>

                                <div class="col-xs-8">
                                    <form:textarea class="form-control" rows="5" id="txtEditDesc" path="description"/>
                                </div>
                            </div>
                        </div>
                    </div>&ndash;%&gt;

                    <br>

                    <div style="text-align: center; z-index: 50000; width: 50%; margin: auto;" id="toaster"></div>

                    <div class="row" align="center">
                        <div class="col-xs-3">
                        </div>
                        <div class="col-xs-3">
                        </div>
                        <div class="col-xs-3" align="center">
                            <form:button type="button" class="btn btn-success btn-group-xs" id="btnEditItem"><span class="glyphicon glyphicon-ok"></span><fmt:message key="item.itemedit.form.button.save"/>
                            </form:button>
                        </div>
                        <div class="col-xs-3" align="left">
                            <form:button type="button" class="btn btn-success" id="btnEditClear" onclick="this.form.reset();"><span class="glyphicon glyphicon-remove"></span><fmt:message key="item.itemedit.form.button.reset"/>
                            </form:button>
                        </div>

                    </div>
                </fieldset>
            </form:form>--%>
        </div>

    </div>
</div>

</fmt:bundle>
</body>
</html>
