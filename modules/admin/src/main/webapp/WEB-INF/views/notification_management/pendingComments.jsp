<!DOCTYPE html>
<head>
    <title>Pending Comments</title>
    <%@include file="../adminTop.jsp" %>

    <spring:url value="/themes/hsenid/css/notificationMgt.css" var="css1"/>
    <link href="${css1}" rel="stylesheet">

    <spring:url value="/themes/hsenid/css/jquery_ui.css" var="jqueryCss"/>
    <link href="${jqueryCss}" rel="stylesheet">

    <spring:url value="/themes/hsenid/js/jquery_ui.min.js" var="jQuary"/>
    <script src="${jQuary}"></script>

    <spring:url value="/themes/hsenid/js/comments_table.js" var="tableComments"/>
    <script src="${tableComments}"></script>

    <spring:url value="/themes/hsenid/js/commentsPagination.js" var="commentPaging"/>
    <script src="${commentPaging}"></script>



    <script>
        //datepicker
        $(document).ready(function() {
            $("#fromDateComments").datepicker({
                "changeMonth":true,
                "changeYear":true

            });

            $("#toDateComments").datepicker({
                "changeMonth":true,
                "changeYear":true

            });
        });
    </script>
</head>
<body>
<%--<fmt:setBundle basename="messages_en" var="bundle1" />--%>
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="notification.heading"/>
    </div>
</div>
<br>

<div class="breadcrumbPosition" id="notificationBreadcrump">
    <ul class="breadcrumb breadcrumb-menu" >

        <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
        <fmt:message key="admin.notificationmanagement.comments.view.url" var="url2" bundle="${bundle2}"/>

        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home" /></a>
        </li>
        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="notification.breadcrumb.commentsmanagemnet"/></a>
        </li>
        <li class="active">
            <a href="<c:out value="${url2}"/>"><fmt:message key="notification.breadcrumb.viewcomments"/></a>
        </li>
    </ul>
</div>

<center>
    <%--<div class="form-box" >--%>
        <div class="panel panel-default" style="width:90%" >
            <div class="panel-heading common-form-headings" style="vertical-align:middle">
                <h3 class="default-panel-headings"><fmt:message key="notification.commentsview.panel.heading" /></h3>
                <br>
            </div>
            <%--panel body--%>
            <div class="panel-body">
                <%--comments filter criteria--%>
                    <div class="col-xs-12">
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border" id="commentSearch" >
                                <fmt:message key="user.userview.customer.label.filter"/>
                            </legend>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-9" style="width: 64%">
                                        <div class="col-xs-6" style="text-align: left;">
                                            <div class="col-xs-2" style="width: 50%;">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" value="1" id="postCheck" onchange="showMeComments()">
                                                    <fmt:message key="notification.commentsview.searchcriteria.date" />
                                                </label>
                                            </div>
                                        </div>
                                        <!--checkboxes to select-->
                                        <div class="col-xs-6" style="text-align:left;margin: 0 -10px 0 -10px;">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" value="2" id="statusCheck" onchange="showMeComments()">
                                                        <fmt:message key="notification.commentsview.searchcriteria.status" />
                                            </label>
                                        </div>
                                    </div>

                                    <div class="col-xs-4" style="width: 36%">
                                        <div class="col-xs-8">
                                            <input class="form-control typeahead"  placeholder="Search by Customer" type="text" id="cmntSearch" >
                                        </div>
                                        <div class="col-xs-1">
                                            <button type="button" class="btn btn-success" id="filterButtonComment" >
                                                <span class="glyphicon glyphicon-search"></span>
                                                    <fmt:message key="user.userview.customer.search" />
                                            </button>
                                        </div>
                                        <div class="col-xs-3"></div>
                                    </div>
                                </div>
                            </div>

                            <!--Filtering elements to be displayed as checkbox is checked     -->
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-9"  style="width: 64%; ">
                                        <div class="col-xs-6"  id="postedPeriod"  style="text-align: left; margin: -25px -2px 0 0; display:none">
                                            <div class="col-xs-6">
                                                <label class=" control-label">
                                                    <fmt:message key="user.userview.customer.filter.regDate.from" />
                                                </label>
                                                <input class="form-control" id="fromDateComments" type="text"
                                                       placeholder="Click on me" style="width: 130px" required="required"/>
                                            </div>
                                            <div class="col-xs-6">
                                                <label class=" control-label">
                                                    <fmt:message key="user.userview.customer.filter.regDate.to" />
                                                </label>
                                                <input class="form-control" id="toDateComments" placeholder="Click on me"
                                                       type="text" style="width: 130px"  required="required"/>
                                            </div>
                                        </div>
                                        <div class="col-xs-6"   >
                                            <div class="col-xs-8" >
                                                <select class="form-control" id="statusSearch" style="text-align: right;margin:0px -25px 0 -70px;;display: none;">
                                                    <option value="--Select--">
                                                        <fmt:message key="notification.commentsview.status.select"  />
                                                    </option>
                                                    <option value="Active">
                                                        <fmt:message key="notification.commentsview.status.active"  />
                                                    </option>
                                                    <option value="Inactive">
                                                        <fmt:message key="notification.commentsview.status.inactive" />
                                                    </option>
                                                    <option value="Blocked">
                                                        <fmt:message key="notification.commentsview.status.block"  />
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <!--next half-->
                                    <!--name form-group-->
                                    <div class="col-xs-4" style="width: 36%">
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </div>

                    <br>
                    <table id="tableComments">
                    </table>
                    <div id="paginationComments" class="text-center">
                    </div>
                    <div id="pagination2Comments" class="text-center">
                    </div>
            </div>
        </div>
    <%--</div>--%>
</center>

<%--modal to approve the comment selected--%>
<div class="modal fade" id="approveCommentModel">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header comment-modal-header-style" style="text-align: center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span>
                </button>
                <div align="center">
                    <span class="glyphicon glyphicon-plus"></span>
                    <fmt:message key="notification.commentsview.approve.modal.heading"/>
                </div>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <fieldset>
                        <div class="form-group" >
                            <div class="col-xs-1"></div>
                            <div class="col-xs-7">
                                <fmt:message key="notification.commentsview.approve.modal.lable"  />
                            </div>
                            <div class="col-xs-4">
                                <label id="lblApproveCommentUser" style="font-size: 20px"></label>
                            </div>
                        </div>
                        <div class="form-group" >
                            <div class="col-xs-1"></div>
                            <div class="col-xs-3">
                                <fmt:message key="notification.commentsview.approve.modal.lable.comment"  />
                            </div>
                            <div class="col-xs-8">
                                <label id="lblApproveComment">
                                </label>
                            </div>
                        </div>
                        <input id="commentId" type="hidden"/>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer" align="right">
                <div class="form-group row" style="text-align: center">
                    <div class="col-xs-3"></div>
                    <div class="col-xs-2" >
                        <button class="btn btn-success"  data-dismiss="modal" aria-hidden="true">
                            <fmt:message key="category.categorydelete.modal.cancel"  />
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                    </div>
                    <div class="col-xs-2"></div>
                    <div class="col-xs-2" >
                        <button class="btn btn-success" id="btnApproveComment">
                            <fmt:message key="notification.commentsview.approve.modal.submit.button" />
                            <span class="glyphicon glyphicon-ok"></span>
                        </button>
                    </div>
                    <div class="col-xs-3"></div>
                </div>
            </div>

        </div>
    </div>
</div>




<%--modal to delete a comment selected--%>
<div class="modal fade" id="discardCommentModel">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header comment-modal-header-style" style="text-align: center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span>
                </button>
                <div align="center">
                    <span class="glyphicon glyphicon-trash"></span>
                    <fmt:message key="notification.commentsview.discard.modal.heading"  />
                </div>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <fieldset>
                        <div class="form-group" >
                            <div class="col-xs-1"></div>
                            <div class="col-xs-7">
                                <fmt:message key="notification.commentsview.discard.modal.lable"  />
                            </div>
                            <div class="col-xs-4">
                                <label id="lblDiscardCommentUser" style="font-size: 20px"></label>
                            </div>
                        </div>
                        <div class="form-group" >
                            <div class="col-xs-1"></div>
                            <div class="col-xs-3">
                                <fmt:message key="notification.commentsview.approve.modal.lable.comment"  />
                            </div>
                            <div class="col-xs-8">
                                <label id="lblDiscardComment">
                                </label>
                            </div>
                        </div>
                        <input id="discardCommentId" type="hidden"/>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer" align="right">
                <div class="form-group row" style="text-align: center">
                    <div class="col-xs-3"></div>
                    <div class="col-xs-2" >
                        <button class="btn btn-success"  data-dismiss="modal" aria-hidden="true">
                            <fmt:message key="category.categorydelete.modal.cancel"/>
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                    </div>
                    <div class="col-xs-2"></div>
                    <div class="col-xs-2" >
                        <button class="btn btn-success" id="btnDiscardComment">
                            <fmt:message key="notification.commentsview.discard.modal.submit.button" />
                            <span class="glyphicon glyphicon-ok"></span>
                        </button>
                    </div>
                    <div class="col-xs-3"></div>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>