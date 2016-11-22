<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Pending Comments</title>
    <jsp:include page="../adminTop.jsp"/>

    <spring:url value="/themes/hsenid/css/notificationMgt.css" var="css1"/>
    <link href="${css1}" rel="stylesheet">



</head>
<body>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="notification.heading" bundle="${bundle1}"/>
    </div>
</div>
<br>

<div class="breadcrumbPosition" id="notificationBreadcrump">
    <ul class="breadcrumb breadcrumb-menu" >

        <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
        <fmt:message key="admin.notificationmanagement.comments.view.url" var="url2" bundle="${bundle2}"/>

        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home" bundle="${bundle1}"/></a>
        </li>
        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="notification.breadcrumb.commentsmanagemnet" bundle="${bundle1}"/></a>
        </li>
        <li class="active">
            <a href="<c:out value="${url2}"/>"><fmt:message key="notification.breadcrumb.viewcomments" bundle="${bundle1}"/></a>
        </li>
    </ul>
</div>

<div class="row">
<center>
    <div class="maintable">
        <center>
            <div class="panel panel-default" style="width:90%" >
                <div class="panel-heading common-form-headings" style="vertical-align:middle">
                    <h3 class="default-panel-headings">View All Pending Comments</h3>
                    <br>
                </div>
                <div class="panel-body">
                    <div class="row commentsSearch">
                    </div>
                    <br>
                    <table class="tableComments">
                            <thead>
                            <tr>
                                <th class="col-md-1">Id</th>
                                <th class="col-md-3">Username</th>
                                <th class="col-md-5">Comment</th>
                                <th class="col-md-3">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td style="vertical-align:middle">001</td>
                                <td style="vertical-align:middle">Harold Finch</td>
                                <td style="vertical-align:middle">Lorem Ipsum is simply dummy text of the printing and</td>

                                <td style="vertical-align:middle">
                                    <a data-toggle="modal" data-target="#approveModel" href="#approveModel">Approve <span class="glyphicon glyphicon-edit"></span></a>
                                    &nbsp;&nbsp;
                                    <a data-toggle="modal" data-target="#discardModel" href="#discardModel">Discard <span class="glyphicon glyphicon-remove"></span></a>
                                </td>

                            </tr>
                            <tr>
                                <td style="vertical-align:middle">001</td>
                                <td style="vertical-align:middle">John Reese</td>
                                <td style="vertical-align:middle">Lorem Ipsum is simply dummy text of the printing and</td>
                                <td style="vertical-align:middle">
                                    <a data-toggle="modal" data-target="#approveModel" href="#approveModel">Approve <span class="glyphicon glyphicon-edit"></span></a>
                                    &nbsp;&nbsp;
                                    <a data-toggle="modal" data-target="#discardModel" href="#discardModel">Discard <span class="glyphicon glyphicon-remove"></span></a>
                                </td>
                            </tr>
                            <tr>
                                <td style="vertical-align:middle">001</td>
                                <td style="vertical-align:middle">Joss Carter</td>
                                <td style="vertical-align:middle">Lorem Ipsum is simply dummy text of the printing and</td>
                                <td style="vertical-align:middle">
                                    <a data-toggle="modal" data-target="#approveModel" href="#approveModel">Approve <span class="glyphicon glyphicon-edit"></span></a>
                                    &nbsp;&nbsp;
                                    <a data-toggle="modal" data-target="#discardModel" href="#discardModel">Discard <span class="glyphicon glyphicon-remove"></span></a>
                                </td>
                            </tr>
                            <tr>
                                <td style="vertical-align:middle">001</td>
                                <td style="vertical-align:middle">Root</td>
                                <td style="vertical-align:middle">Lorem Ipsum is simply dummy text of the printing and</td>
                                <td style="vertical-align:middle">
                                    <a data-toggle="modal" data-target="#approveModel" href="#approveModel">Approve <span class="glyphicon glyphicon-edit"></span></a>
                                    &nbsp;&nbsp;
                                    <a data-toggle="modal" data-target="#discardModel" href="#discardModel">Discard <span class="glyphicon glyphicon-remove"></span></a>
                                </td>
                            </tr>
                            <tr>
                                <td style="vertical-align:middle">001</td>
                                <td style="vertical-align:middle">Sameen Shaw</td>
                                <td style="vertical-align:middle">Lorem Ipsum is simply dummy text of the printing and</td>
                                <td style="vertical-align:middle">
                                    <a data-toggle="modal" data-target="#approveModel" href="#approveModel">Approve <span class="glyphicon glyphicon-edit"></span></a>
                                    &nbsp;&nbsp;
                                    <a data-toggle="modal" data-target="#discardModel" href="#discardModel">Discard <span class="glyphicon glyphicon-remove"></span></a>
                                </td>
                            </tr>
                            <tr>
                                <td style="vertical-align:middle">001</td>
                                <td style="vertical-align:middle">Lionel Fusco</td>
                                <td style="vertical-align:middle">Lorem Ipsum is simply dummy text of the printing and</td>
                                <td style="vertical-align:middle">
                                    <a data-toggle="modal" data-target="#approveModel" href="#approveModel">Approve <span class="glyphicon glyphicon-edit"></span></a>
                                    &nbsp;&nbsp;
                                    <a data-toggle="modal" data-target="#discardModel" href="#discardModel">Discard <span class="glyphicon glyphicon-remove"></span></a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                </div>
            </div>
        </center>

    </div>
</center>
</div>

<div class="paginationPosition">
    <ul class="pagination">
        <li><a href="#">1</a></li>
        <li class="active"><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
    </ul>
</div>

<div class="modal fade modalposition" id="approveModel" role="dialog" style="top: 15%">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="text-align: center">

                <h3 class="modal-title"> <span class="glyphicon glyphicon-edit"></span> Approve Comment&nbsp;?</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <fieldset>


                        <div class="form-group" style="text-align: center">
                            <p>You are about to approve comment</p>
                            <p><b>Lorem Ipsum is simply dummy text of the printing and</b></p>
                            <p>By</p>
                            <p><b>John Reese</b></p>
                        </div>
                        <div class="form-group row" style="text-align: center">
                            <div class="col-xs-3"></div>
                            <div class="col-xs-2" ><button class="btn btn-success"> <span class="glyphicon glyphicon-ok"></span> Approve </button></div>
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

<div class="modal fade modalposition" id="discardModel" role="dialog" style="top: 15%">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="text-align: center">

                <h3 class="modal-title"> <span class="glyphicon glyphicon-trash"></span> Discard Comment&nbsp;?</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <fieldset>


                        <div class="form-group" style="text-align: center">
                            <p>You are about to discard comment</p>
                            <p><b>Lorem Ipsum is simply dummy text of the printing and</b></p>
                            <p>By</p>
                            <p><b>John Reese</b></p>
                        </div>
                        <div class="form-group row" style="text-align: center">
                            <div class="col-xs-3"></div>
                            <div class="col-xs-2" ><button class="btn btn-success"> <span class="glyphicon glyphicon-trash"></span> Discard </button></div>
                            <div class="col-xs-2"></div>
                            <div class="col-xs-2" ><button class="btn btn-success"><span class="glyphicon glyphicon-remove"></span> Cancel </button></div>
                            <div class="col-xs-3"></div>
                        </div>
                    </fieldset>
                </form>
            </div>

        </div>

    </div>
</div>


</body>
</html>