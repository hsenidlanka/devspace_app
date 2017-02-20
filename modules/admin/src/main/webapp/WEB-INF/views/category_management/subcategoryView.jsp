<!DOCTYPE html>
<head>

    <title>View Sub Categories</title>
    <%@include file="../adminTop.jsp" %>


    <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
    <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>
    <spring:url value="/themes/hsenid/css/view_categories.css" var="css3"/>

    <link href="${css3}" rel="stylesheet">
    <link href="${css1}" rel="stylesheet">
    <link href="${css2}" rel="stylesheet">

    <spring:url value="/themes/hsenid/js/subcategory_table.js" var="table"/>
    <script src="${table}"></script>
    <spring:url value="/themes/hsenid/js/subCategoryPagination.js" var="pagination"/>
    <script src="${pagination}"></script>
</head>

<body>
<%--<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />--%>
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="category.heading"/>
    </div>
</div>
<br>
<br>

<div class="breadcrumbPosition" id="subcatBreadcrump">
    <ul class="breadcrumb breadcrumb-menu" >

        <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
        <fmt:message key="admin.categorymanage.categoryview.url" var="url2" bundle="${bundle2}"/>
        <fmt:message key="admin.categorymanage.subcategoryview.url" var="url3" bundle="${bundle2}"/>


        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home" /></a>
        </li>
        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="category.breadcrumb.categorymanagement"/></a>
        </li>
        <li>
            <a href="<c:out value="${url2}"/>"><fmt:message key="category.categoryadd.breadcrumb.viewcat"/></a>
        </li>
        <li class="active">
            <a href="<c:out value="${url3}"/>"><fmt:message key="category.categoryadd.breadcrumb.viewsubcat"/></a>
        </li>
    </ul>
</div>

<div class="row">
<div class="viewcategorysize">
    <center>
        <div class="panel panel-default " style="width: 80%">

            <div class="panel-heading common-form-headings">
                <h3 class="default-panel-headings">
                    <fmt:message key="category.subcategoryview.panel.heading"/>  ${catName}
                </h3>
            </div>

            <div class="panel-body" >

                <%--search tab--%>
                <div class="row categorySearch" >
                    <div class="col-sm-1"></div>
                    <div class="col-sm-3">
                        <label class="control-label" style="float: right;">
                            <fmt:message key="category.subcategoryview.searchbar.label" />
                        </label>

                    </div>
                    <div class="col-sm-4">
                        <input class="form-control typeahead" id="txtViewSearchSubCategory" type="text"/>
                    </div>
                    <div class="col-sm-3">
                        <button type="button" class="btn btn-success" id="btnViewSearchSubCat"><span class="glyphicon glyphicon-search">
                            </span><fmt:message key="category.subcategoryview.searchbar.button" />
                        </button>
                    </div>
                    <div class="col-sm-1"></div>
                </div>
                <br>

                <table  id="tableSubCategory" data-value="${catName}">
                </table>
                    <div id="paginationSubCat" class="text-center">
                    </div>
                    <div id="pagination2SubCat" class="text-center">
                    </div>

            </div>

        </div>
    </center>

</div>
</div>

<%--modal to delete the sub-category selected--%>
<div class="modal fade" id="deleteModal2">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style" style="text-align: center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span>
                </button>
                <div align="center">
                    <span class="glyphicon glyphicon-trash"></span>
                    <fmt:message key="category.subcategorydelete.modal.heading" />
                </div>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <fieldset>
                        <div class="form-group" style="text-align: center">
                            <label id="lblDeleteCategoryMsg">
                                <fmt:message key="category.subcategorydelete.modal.message1"/>
                            </label><br>
                            <label id="lblDeleteSubCategoryId"></label><br>
                        </div>
                      <%--  <div class="form-group" style="text-align: center">
                            <label style="font-style: italic">
                                <fmt:message key="category.categorydelete.modal.message2" bundle="${bundle1}" />
                            </label>
                        </div>--%>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer" align="right">
                <div class="form-group row" style="text-align: center">
                    <div class="col-xs-3"></div>
                    <div class="col-xs-2" >
                        <button class="btn btn-success" id="btnDeleteSubCategory">
                            <fmt:message key="category.categorydelete.modal.approve"/>
                            <span class="glyphicon glyphicon-ok"></span>
                        </button>
                    </div>
                    <div class="col-xs-2"></div>
                    <div class="col-xs-2" >
                        <button class="btn btn-success"  data-dismiss="modal" aria-hidden="true" >
                            <fmt:message key="category.categorydelete.modal.cancel" />
                            <span class="glyphicon glyphicon-remove"></span></button></div>
                    <div class="col-xs-3"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--modal to edit the sub-category selected--%>
<div class="modal fade" id="subcatmodifyModel">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style" style="text-align: center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span>
                </button>
                <div align="center">
                    <span class="glyphicon glyphicon-trash"></span>
                    <fmt:message key="category.categoryedit.modal.approve"/>
                </div>
            </div>
            <div class="modal-body">
                <form:form role="form" id="admin_editsubcatform"  method="POST" class="form-horizontal"
                           action="/admin/editSubCategory">
                    <fieldset style="margin: 0 40px 0 -30px;">
                        <br>
                        <div class="form-group">
                            <label for="subcategoryid" class="control-label col-sm-4">
                                <fmt:message key="category.categoryedit.modal.id" />
                            </label>

                            <div class="col-sm-8">
                                <form:input path="subCategoryId" type="text" class="form-control" id="subcategoryid"
                                            readonly="true"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="editsubcategoryname" class="control-label col-sm-4">
                                <fmt:message key="category.categoryedit.modal.name"  />
                            </label>

                            <div class="col-sm-8">
                                <form:input path="subCategoryName" type="text" class="form-control" id="editsubcategoryname"
                                            required="required"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editsubcategorydes" class="control-label col-sm-4">
                                <fmt:message key="category.categoryedit.modal.description" />

                            </label>
                            <div class="col-sm-8">
                                <form:textarea path="subcatDescription" type="text" class="form-control" id="editsubcategorydes"></form:textarea>
                            </div>
                            <form:input  path="categoryName" type="hidden" name="cat_name" id="cat_name"/> >
                        </div>

                        <div class="modal-footer" style="height:10px">
                            <div class="form-group row" style="text-align: center">
                                <div class="col-xs-3"></div>
                                <div class="col-xs-2" >
                                    <button class="btn btn-success" id="btnEditCategory" type="submit">
                                        <fmt:message key="category.categoryedit.modal.approve" />
                                        <span class="glyphicon glyphicon-ok"></span>
                                    </button>
                                </div>
                                <div class="col-xs-2"></div>
                                <div class="col-xs-2" >
                                    <button class="btn btn-success"  data-dismiss="modal" aria-hidden="true">
                                        <fmt:message key="category.categorydelete.modal.cancel"/>
                                        <span class="glyphicon glyphicon-remove"></span></button></div>
                                <div class="col-xs-3"></div>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="footerposition">
    <%--<jsp:include page="../footer.jsp"/>--%>
</div>

</body>

</html>