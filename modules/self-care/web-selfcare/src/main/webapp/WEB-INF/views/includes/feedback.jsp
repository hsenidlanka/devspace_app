<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/m-buttons.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.rateyo.min.css"/>"/>
    <script src="<c:url value="/resources/js/feedback-operations.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.rateyo.min.js"/>"></script>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <img class="icons" src="<c:url value="/resources/images/icons/purchase_history.png"/>">

        <h3 class="header-panel"><fmt:message key="feedback.header.text" bundle="${lang}"/></h3>
    </div>
    <div class="panel-body">
        <div id="feedback-content-div"></div>
    </div>
</div>

<!-- Start Feedback modal -->
<div class="modal fade" id="modal-addFeedback">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i>
                </button>
                <img class="icons" src="<c:url value="/resources/images/icons/feedback-icon.png"/> "/>
                <h4 class="modal-title header-panel">&nbsp;<fmt:message key="feedback.modal.header.text"
                                                                        bundle="${lang}"/></h4>
            </div>
            <div class="modal-body">
                <div>
                    <label><fmt:message key="feedback.rating.fld"
                                        bundle="${lang}"/></label>

                    <div id="rateYo"></div>
                </div>
                <div class="form-group" style="margin-top: 10px;">
                    <label><fmt:message key="feedback.comment.fld"
                                        bundle="${lang}"/></label>
                    <label class="lbl-errors" id="feedback-comment-error"></label>
                    <textarea class="form-control" rows="5" id="feedback-comment"
                              style="resize: vertical;margin-bottom: -25px;"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                        key="purchase.history.model.close"
                        bundle="${lang}"/></button>
                <button type="button" class="btn btn-success" id="btn-add-feedback-ok"><fmt:message
                        key="feedback.addbtn.text"
                        bundle="${lang}"/></button>
            </div>
        </div>
    </div>
</div>
<!-- End Feedback modal -->
</body>
</html>
