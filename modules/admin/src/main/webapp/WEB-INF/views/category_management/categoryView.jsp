<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>

    <title>View Categories</title>
    <jsp:include page="../adminTop.jsp"/>

    <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
    <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>

    <link href="${css1}" rel="stylesheet">
    <link href="${css2}" rel="stylesheet">

    <spring:url value="/themes/hsenid/js/category_table.js" var="js2"/>
    <script src="${js2}"></script>


</head>

<body>
<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="category.heading" bundle="${bundle1}"/>
    </div>
</div>
<br>
<br>

<div class="breadcrumbPosition" id="userBreadcrump">
    <ul class="breadcrumb breadcrumb-menu" >

        <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
        <fmt:message key="admin.categorymanage.categoryadd.url" var="url2" bundle="${bundle2}"/>

        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home" bundle="${bundle1}"/></a>
        </li>
        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="category.breadcrumb.categorymanagement" bundle="${bundle1}"/></a>
        </li>
        <li class="active">
            <a href="<c:out value="${url2}"/>"><fmt:message key="category.categoryadd.breadcrumb.viewcat" bundle="${bundle1}"/></a>
        </li>
    </ul>
</div>


<div class="row">
<div class="viewcategorysize">
    <center>
        <div class="panel panel-default " style="width:85%">

            <div class="panel-heading common-form-headings">
                <h3 class="default-panel-headings">View All Sub Category</h3>
            </div>


            <div class="panel-body" >

                <%--search tab--%>
                <div class="row" style="text-align: center">
                    <div class="col-md-3"></div>

                    <div class="col-md-2">
                        <input type="text">
                    </div>

                    <div class="col-md-2"></div>

                    <div class="col-md-2">
                        <button class="btn btn-success" style="width: 100%">
                            Search
                        </button>
                    </div>

                    <div class="col-md-3"></div>
                </div>
                <br>

                    <%--include the bootstrap table with category list--%>
                    <table  id="tableCategory">
                    </table>
            </div>

        </div>
    </center>
</div>
</div>

<div class="modal fade" id="deleteModal1">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header deleteuser-modal-header-style" style="text-align: center">

                <h3 class="modal-title"> <span class="glyphicon glyphicon-trash"></span> Are you sure&nbsp;?</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <fieldset>


                        <div class="form-group" style="text-align: center">
                            <p>You are about to delete category "Lorem" <br>and <br>It's Sub categories</p>
                        </div>
                        <div class="form-group row" style="text-align: center">
                            <div class="col-xs-3"></div>
                            <div class="col-xs-2" ><button class="btn btn-success">  Approve <span class="glyphicon glyphicon-ok"></span></button></div>
                            <div class="col-xs-2"></div>
                            <div class="col-xs-2" ><button class="btn btn-success"> Cancel <span class="glyphicon glyphicon-remove"></span></button></div>
                            <div class="col-xs-3"></div>
                        </div>
                    </fieldset>
                </form>
            </div>

        </div>
    </div>
</div>


<div class="modal fade" id="modifyModel" role="dialog" style="top: 15%">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="text-align: center">

                <h3 class="modal-title">Modify Category &nbsp;<span class="glyphicon glyphicon-edit"></span></h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <fieldset>


                        <div class="form-group">
                            <label for="categoryid" class="control-label col-sm-4" style="">Category Id :</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="categoryid" placeholder="Please Enter Category Id">

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="categoryname" class="control-label col-sm-4">Category Name :</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="categoryname" placeholder="Please Enter Category Name">

                            </div>
                        </div>



                        <div class="form-group">
                            <label for="categoryname" class="control-label col-sm-4">Category Description :</label>

                            <div class="col-sm-8">
                                <input class="form-control" placeholder="Please Enter Description" type="text">

                            </div>
                        </div>


                        <div class="form-group" style="text-align: center">
                            <div class="col-sm-6" style="text-align: right">
                                <button class="btn btn-success btn-lg" type="button" data-toggle="modal" data-target="#myModal">
                                    <span class="glyphicon glyphicon-ok"></span> Submit Category
                                </button>
                            </div>
                            <div class="col-sm-6">
                                <button class="btn btn-success btn-lg">
                                    <span class="glyphicon glyphicon-remove"></span> Cancel
                                </button>
                            </div>
                        </div>

                    </fieldset>
                </form>
            </div>
            
        </div>

    </div>
</div>
<br>
<br><br>
<br>
<div class="footerposition">
    <%--<object width="100%" data="footer.html"></object>--%>
    <%--<jsp:include page="../footer.jsp"/>--%>
</div>

</body>

</html>