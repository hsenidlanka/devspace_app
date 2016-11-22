<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head lang="en">

    <meta charset="UTF-8">
    <title>Add new package</title>
    <!-- include common CSS, fonts and js -->
    <jsp:include page="../adminTop.jsp"/>

    <%-- Other css --%>
    <spring:url value="/themes/hsenid/css/itemMgt.css" var="cssItem"/>
    <spring:url value="/themes/hsenid/css/packageMgt.css" var="cssPackage"/>
    <link href="${cssItem}" rel="stylesheet">
    <link href="${cssPackage}" rel="stylesheet">

    <%--  other javascripts --%>

    <spring:url value="/themes/hsenid/js/packageMgt.js" var="packgeJs"/>
    <spring:url value="/themes/hsenid/js/myAlerts.js" var="myAlerts"/>

    <script src="${packgeJs}"></script>
    <script src="${myAlerts}"></script>
</head>
<body>
<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="package.heading" bundle="${bundle1}"/>
    </div>
</div>

<br>

<div>
    <div id="add-pkg-breadcrumb-position">
        <ul class="breadcrumb breadcrumb-menu">

            <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
            <fmt:message key="admin.packagemanagement.pckgadd.url" var="url2" bundle="${bundle2}"/>

            <li>
                <a href="<c:out value="${url1}"/>"><fmt:message key="package.packageadd.breadcrumb.home" bundle="${bundle1}"/></a>
            </li>
            <li>
                <a href="<c:out value="${url1}"/>"><fmt:message key="package.addpackage.heading" bundle="${bundle1}"/></a>
            </li>
            <li class="active">
                <a href="<c:out value="${url2}"/>"><fmt:message key="package.addpackage.breadcrumb.addpkg" bundle="${bundle1}"/></a>
            </li>
        </ul>
    </div>

</div>

<div class="form-box" id="add-pkg-form-box">
    <div class="panel panel-default">
        <div class="panel-heading common-form-headings">
            <h3 class="default-panel-headings"><fmt:message key="package.packageadd.panel.heading" bundle="${bundle1}"/></h3>
        </div>
        <div class="panel-body">

            <form:form class="form-horizontal" role="form" id="frmAddPkg" method="post" action="/admin/packages/add_package" enctype="multipart/form-data">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border"><fmt:message key="package.packageadd.form.legend" bundle="${bundle1}"/></legend>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-7">
                                <label class="col-xs-4 control-label">
                                    <fmt:message key="package.packageadd.form.name" bundle="${bundle1}"/>
                                </label>

                                <div class="col-xs-8">
                                    <form:input class="form-control" type="text" id="txtAddPkgName" path="packName"/>
                                </div>
                                <span id="pkgNmErr" class="input-group-error"> </span>
                            </div>
                            <div class="col-xs-5">
                                <label class="col-xs-4 control-label">
                                    <fmt:message key="package.packageadd.form.packageprice" bundle="${bundle1}"/>
                                </label>

                                <div class="col-xs-8" align="right">
                                    <form:input class="form-control price" type="text" id="txtAddPkgPrice" path="price"/>
                                    <span id="pkgPriceErr" class="input-group-error" style="float: left"> </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-7">

                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label class="col-xs-3 control-label">
                                <fmt:message key="package.packageadd.form.packagedetails" bundle="${bundle1}"/>
                            </label>
                        </div>
                        <br>

                        <div class="row col-xs-12" style="margin-left: 1%;">

                                <div class="row item-tbl-row item-tbl-hdr">
                                    <div class="col-xs-2">
                                        <strong><fmt:message key="package.packageadd.form.packagedetails.content" bundle="${bundle1}"/></strong>
                                    </div>
                                    <div class="col-xs-4">
                                        <strong><fmt:message key="package.packageadd.form.packagedetails.itemname" bundle="${bundle1}"/></strong>
                                    </div>
                                <div class="col-xs-3">
                                        <strong><fmt:message key="package.packageadd.form.packagedetails.itemsize" bundle="${bundle1}"/></strong>
                                    </div>
                                    <div class="col-xs-3">
                                        <strong><fmt:message key="package.packageadd.form.packagedetails.qty" bundle="${bundle1}"/></strong>
                                    </div>
                                </div>

                                <div class="row rowtbl item-tbl-row" id="tbladdPkgCont">
                                    <div class="col-xs-2">
                                        <label class="checkbox-inline">
                                        <form:checkbox value="pizza" class="checkbox chkbxPkgCat" path="" id="chkPkgCat"/>
                                            <label class="chkbxPkgCat1"></label>
                                            <%--<fmt:message key="package.packageadd.form.checkbox.pizza" bundle="${bundle1}"/>--%>
                                        </label>
                                    </div>
                                    <div class="col-xs-4">
                                        <form:select type="text" class="form-control" path="itemName" id="slctItmPkgCat">
                                            <form:option value="-" label="---"/>
                                        </form:select>
                                    </div>
                                    <div class="col-xs-3">
                                        <form:select type="text" class="form-control" path="size" id="szpr">
                                            <form:option value="-" label="---"/>
                                        </form:select>
                                    </div>
                                    <div class="col-xs-3">
                                        <form:input class="qty-spinner" type="number" min="0" id="contQty" path="quantity"/>
                                    </div>
                                </div>
                            <div class="tstDiv"></div>
                            <br>
                        </div>
                    </div>
<br>
                    <div class="form-group">
                        <div class="row">
                            <label class="col-xs-3 control-label">
                                <fmt:message key="package.packageadd.form.packageimages" bundle="${bundle1}"/>
                            </label>

                            <div class="col-xs-5">
                                <form:input type="file" class="file_upload btn btn-default" path="" id="savePkgImg" value="Browse" />
                                <span id="pkgImgErr" class="input-group-error"> </span>
                            </div>
                        </div>
                    </div>
                    <br>

                    <div style="text-align: center; z-index: 50000; width: 50%; margin: auto;" id="toaster"></div>
                    <br>

                    <div class="row">
                        <div class="col-xs-4">
                        </div>
                        <div class="col-xs-4" align="right">
                            <form:button type="button" class="btn btn-success" id="btnAddNewPkg" onclick="contentPackge();"><span
                                    class="glyphicon glyphicon-plus"></span>
                                <fmt:message key="package.packageadd.form.button.add" bundle="${bundle1}"/>
                            </form:button>
                        </div>
                        <div class="col-xs-4" align="left">
                            <form:button type="reset" class="btn btn-success btn-group-xs" id="btnPkgCancel">
                                <fmt:message key="package.packageadd.form.button.reset" bundle="${bundle1}"/>
                            </form:button>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>

    </div>
</div>
</body>
</html>