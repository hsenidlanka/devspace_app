<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head lang="en">

    <title>View Package</title>

    <!-- include common CSS, fonts and js -->
    <jsp:include page="../adminTop.jsp"/>

    <%-- Other css --%>
    <spring:url value="/themes/hsenid/css/itemMgt.css" var="cssItem"/>
    <spring:url value="/themes/hsenid/css/packageMgt.css" var="cssPackage"/>
    <link href="${cssItem}" rel="stylesheet">
    <link href="${cssPackage}" rel="stylesheet">

    <%--  other javascripts --%>

    <spring:url value="/themes/hsenid/js/itemMgt.js" var="itemJs"/>
    <spring:url value="/themes/hsenid/js/packageMgt.js" var="pkgMgt"/>
    <spring:url value="/themes/hsenid/js/tablePackages.js" var="pkgTblView"/>
    <spring:url value="/themes/hsenid/js/myAlerts.js" var="myAlerts"/>
    <spring:url value="/themes/hsenid/js/packagePagination.js" var="pkgPaginate"/>

    <script src="${itemJs}"></script>
    <script src="${pkgMgt}"></script>
    <script src="${pkgTblView}"></script>
    <script src="${myAlerts}"></script>
    <script src="${pkgPaginate}"></script>
</head>

<body>

<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="package.addpackage.heading" bundle="${bundle1}"/>
    </div>
</div>
<br>
<div>
    <div id="add-item-breadcrumb-position">
        <ul class="breadcrumb breadcrumb-menu">
            <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
            <fmt:message key="admin.packagemanagement.pckgadd.url" var="url2" bundle="${bundle2}"/>

            <li><a href="<c:out value="${url1}"/>"><fmt:message key="package.packageadd.breadcrumb.home" bundle="${bundle1}"/></a></li>
            <li><a href="<c:out value="${url1}"/>"><fmt:message key="package.heading" bundle="${bundle1}"/> </a></li>
            <li class="active"><a href="<c:out value="${url2}"/>"><fmt:message key="package.viewpackage.breadcrumb.viewpkg" bundle="${bundle1}"/> </a></li>
        </ul>
    </div>
</div>

<div class="form-box" id="view-pkg-form-box">
    <div class="panel panel-default">
        <div class="panel-heading  common-form-headings">
            <h3 class="default-panel-headings">View Package</h3>
        </div>

        <div class="panel-body">

            <div class="row itemSearchBar">
                <div class="col-sm-1"></div>
                <div class="col-sm-3">
                    <label class="control-label" style="float: right;">
                        Package Name :
                    </label>

                </div>
                <div class="col-sm-4">
                    <input class="form-control" id="txtViewSearchPkg" type="text"/>
                </div>
                <div class="col-sm-3">
                    <button type="button" class="btn btn-success" id="btnViewSearchPkg"><span
                            class="glyphicon glyphicon-search"></span> Search
                    </button>
                </div>
                <div class="col-sm-1"></div>
            </div>
            <br>

            <form:form class="form-horizontal" role="form" id="frmViewPkg" action="" method="post">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border">Available Packages</legend>

                    <!--table of package details-->
                    <table id="tblPackages">

                    </table>

                    <div id="pagination3" class="text-center">
                    </div>
                    <div id="pagination4" class="text-center">
                    </div>
                </fieldset>
            </form:form>
        </div>

    </div>
</div>

<!--Modal for delete package-->
<div class="modal fade" id="pkgDeleteModal">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header item-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-trash"></span> Delete Package
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label id="lblDeltPkgMsg"><fmt:message key="package.deletemodal.warning.question" bundle="${bundle1}"/></label><br><br>

                    <div align="center" class="row">
                        <div class="col-xs-6" style="text-align: right"> <label ><fmt:message key="package.deletemodal.packageid" bundle="${bundle1}"/></label></div>
                        <div class="col-xs-6" style="text-align: left">  <label id="lblDeltPkgId" class="delete-lables"> </label></div><br>
                        <div class="col-xs-6" style="text-align: right"> <label ><fmt:message key="package.deletemodal.packagename" bundle="${bundle1}"/></label></div>
                        <div class="col-xs-6" style="text-align: left"> <label id="lblDeltPkgName" class="delete-lables"> </label></div><br>
                    </div>
                </div>
                <div style="text-align: center; width: 75%; margin: auto;" id="toaster"></div>
            </div>
            <div class="modal-footer" align="right">
                <button class="btn btn-success" type="button" value="Yes" id="btnDeltPkg"><fmt:message key="package.deletemodal.button.yes" bundle="${bundle1}"/>
                </button>
                <button class="btn btn-success" type="button" value="cancel" id="btnCnclDeltPkg" data-dismiss="modal"><fmt:message key="package.deletemodal.button.no" bundle="${bundle1}"/>
                </button>
            </div>
        </div>
    </div>
</div>


<!--Modal for edit package-->
<div class="modal fade" id="pkgEditModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header item-modal-header-style">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></button>
                <div align="center"><span class="glyphicon glyphicon-pencil"></span><fmt:message key="package.packageedit.panel.heading" bundle="${bundle1}"/>
                </div>
            </div>
            <div class="modal-body">

                <form:form class="form-horizontal" role="form" method="post" id="frmEditPkg" action="/admin/packages/edit_package" enctype="multipart/form-data">
                    <fieldset class="scheduler-border">
                        <legend class="scheduler-border"><fmt:message key="package.packageedit.form.legend" bundle="${bundle1}"/></legend>

                        <div class="form-group">

                            <div class="row">
                                <div class="col-xs-3" align="right">
                                    <label id="lblEditPkgNm"><fmt:message key="package.packageedit.form.pkgname" bundle="${bundle1}"/></label>
                                </div>
                                <div class="col-xs-4">
                                    <form:input class="form-control" type="text" id="txtEditPkgNm" path="packName" readonly="true"/>
                                </div>
                                <div class="col-xs-4">
                                    <span id="pkgNmErrEdt" class="input-group-error"></span>
                                </div>
                            </div>

                            <br><br>

                            <div class="row">
                                <div class="col-xs-3" align="right">
                                    <label><fmt:message key="package.packageedit.form.packagedetails" bundle="${bundle1}"/> </label>
                                </div>
                                <div class="row col-xs-9" style="padding-left: 30px;">

                                       <div class="row col-xs-12">

                                           <div class="row item-tbl-row item-tbl-hdr">
                                               <div class="col-xs-2">
                                                   <strong><fmt:message key="package.packageedit.form.packagedetails.content" bundle="${bundle1}"/></strong>
                                               </div>
                                               <div class="col-xs-4">
                                                   <strong><fmt:message key="package.packageedit.form.packagedetails.itemname" bundle="${bundle1}"/></strong>
                                               </div>
                                               <div class="col-xs-3">
                                                   <strong><fmt:message key="package.packageedit.form.packagedetails.itemsize" bundle="${bundle1}"/></strong>
                                               </div>
                                               <div class="col-xs-3">
                                                   <strong><fmt:message key="package.packageedit.form.packagedetails.qty" bundle="${bundle1}"/></strong>
                                               </div>
                                           </div>

                                           <div class="row rowtbl item-tbl-row" id="tbleditPkgCont">
                                               <div class="col-xs-2">
                                                   <label class="checkbox-inline">
                                                       <form:checkbox value="pizza" class="checkbox chkbxPkgCat" path="" id="chkPkgCat"/>
                                                       <label class="chkbxPkgCat1"></label>
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
                                                   <form:input class="form-control qty-spinner" type="number" min="0" id="contQty" path="quantity"/>
                                               </div>
                                           </div>
                                           <div class="tstDiv"></div>
                                           <br>
                                           <div class="addTo" style="text-align: right">
                                               <button class="btn btn-success btnAddItmPkg" type="button" id="addToEdit" value="add" onclick="contentPackageEdit()"><span
                                                       class="glyphicon glyphicon-plus"></span>Create Package Content</button>
                                           </div>
                                           <br>
                                       </div>

                                </div>
                            </div>

                            <br><br>

                            <div class="row">
                                <div class="col-xs-3" align="right">
                                    <label><fmt:message key="package.packageeditform.packageimages" bundle="${bundle1}"/></label>
                                </div>
                                <div class="col-xs-5">
                                    <form:input type="file" class="file_upload btn btn-default" path="imageUrl" id="btnUpldImageEdit" value="Browse" />
                                    <br>
                                </div>
                            </div>
                            <br><br>

                            <div class="row">
                                <div class="col-xs-3" align="right">
                                    <label><fmt:message key="package.packageeditform.packageprice" bundle="${bundle1}"/> </label>
                                </div>
                                <div class="col-xs-3">
                                    <form:input type="text" class="form-control price" id="editPkgPrice" value="0.00" path="price"/>
                                </div>
                                <div class="col-xs-3">
                                    <span id="pkgPriceErrEdt" class="input-group-error"></span>
                                </div>
                            </div>

                        </div>



            </div>
            <div style="text-align: center; z-index: 50000; width: 50%; margin: auto;" id="toaster"></div>
            <div class="modal-footer" align="right">
                <form:button class="btn btn-success" type="submit" value="Yes" id="btnUpdtPkg"><fmt:message key="package.packageedit.form.button.submit" bundle="${bundle1}"/>
                </form:button>
                <form:button class="btn btn-success" type="reset" value="cancel" id="btnUpPkgCancel" data-dismiss="modal"><fmt:message key="package.packageedit.form.button.reset" bundle="${bundle1}"/>
                </form:button>
            </div>
            </fieldset>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>