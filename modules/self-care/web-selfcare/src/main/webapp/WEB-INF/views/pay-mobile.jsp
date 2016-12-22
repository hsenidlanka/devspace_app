<!DOCTYPE html>
<html>
<head lang="en">
    <%@include file="includes/include.jsp" %>
    <title><fmt:message key="delivery.payment" bundle="${lang}"/>Payment</title>

    <link rel="shortcut icon" href="">
    <script src="<c:url value="/resources/js/validate-pay.js"/>"></script>
    <script src="<c:url value="/resources/js/pay-operations.js"/>"></script>

    <%@include file="includes/smart-wizard.jsp" %>
</head>
<body>

<div class="loader-anim"></div>

<%@include file="includes/new-header.jsp" %>

<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <img class="icons" src="<c:url value="/resources/images/icons/pay.png"/>">

                <h3 class="header-panel"><fmt:message key="pay" bundle="${lang}"/></h3>
            </div>
            <div class="panel-body">
                <div id="smartwizard">
                    <ul>
                        <li class="done"><a href=""><fmt:message key="delivery" bundle="${lang}"/><br/>
                            <small><fmt:message key="delivery.msg" bundle="${lang}"/></small>
                        </a></li>
                        <li class="done"><a href=""><fmt:message key="delivery.payment" bundle="${lang}"/><br/>
                            <small><fmt:message key="delivery.payment.msg" bundle="${lang}"/></small>
                        </a></li>
                        <li><a href=""><fmt:message key="delivery.verify" bundle="${lang}"/><br/>
                            <small><fmt:message key="delivery.verify.msg" bundle="${lang}"/></small>
                        </a></li>
                        <li><a href=""><fmt:message key="delivery.success" bundle="${lang}"/><br/>
                            <small><fmt:message key="delivery.success.msg" bundle="${lang}"/></small>
                        </a></li>
                    </ul>
                </div>
                <div class="container" style="width:1000px;">
                    <div class="row">
                        <div class="col-md-6" style="margin-top: 20px;">
                            <form role="form">
                                <div style="margin-top: 20px; margin-bottom: 20px; border: groove; border-color: #62c462; border-width: thin;">
                                    <p id="net-amount-text"><strong><fmt:message key="pay.amount.payable"
                                                                                 bundle="${lang}"/></strong></p>

                                    <p id="payment-amount-field">0.00</p>
                                </div>
                                <div class="form-group">
                                    <label class="control-label"><fmt:message key="pay.mobile.no"
                                                                              bundle="${lang}"/></label>
                                    <label id="mobileno_error" class="error-labels"></label>
                                    <input class="form-control" id="txt-mobile-no"
                                           placeholder="<fmt:message key="pay.enter.mobile" bundle="${lang}"/>"
                                           type="text">
                                </div>
                                <div style="margin-top: 5px; width: 40%; margin-left: 25%; float: right;">
                                    <a class="btn btn-success btn-proceed-payment" id="btn-confirm-mobilepay"
                                       style="width:100%;"><span
                                            class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;<fmt:message
                                            key="pay.amount" bundle="${lang}"/></a>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-6">
                            <img src="<c:url value="/resources/images/payment/mobile-payments-icon@2x-1.png"/>"
                                 width="100%;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Payment confirm popup -->
<div id="confirm-payment-popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <img class="icons" src="<c:url value="/resources/images/icons/pay-confirm.png"/>">
                <h4 class="modal-title header-panel"><fmt:message key="pay.confirm" bundle="${lang}"/></h4>
            </div>
            <div class="modal-body">
                <fmt:message key="pay.msg" bundle="${lang}"/>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" id="btn-pay-cancel" data-dismiss="modal"><fmt:message key="pay.cancel"
                                                                                                 bundle="${lang}"/></a>
                <a class="btn btn-success" id="btn-pay-popup"><fmt:message key="pay.modal.ok" bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>

<!-- Payment details incorrect popup -->
<div id="pay-validation-error-popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><img src="<c:url value="/resources/images/icons/error.png"/>" width="100px"
                                             height="100px">&nbsp;&nbsp;<fmt:message key="pay.vali.err"
                                                                                     bundle="${lang}"/></h2>
            </div>
            <div class="modal-body">
                <p style="font-size: medium;"><fmt:message key="pay.error" bundle="${lang}"/>
                </p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal"><fmt:message key="pay.ok" bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>

<!-- Payment fail popup -->
<div id="pay-fail-popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><img src="<c:url value="/resources/images/icons/error.png"/>" width="100px"
                                             height="100px">&nbsp;&nbsp;<fmt:message key="pay.fail.error"
                                                                                     bundle="${lang}"/></h2>
            </div>
            <div class="modal-body">
                <p style="font-size: medium;"><fmt:message key="pay.fail.error.msg" bundle="${lang}"/>
                </p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal"><fmt:message key="pay.ok" bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>
<a href="#" class="scrollup"></a>
<script>
    $('#smartwizard').smartWizard({
        selected: 2,
        theme: 'arrows',
        anchorSettings: {
            anchorClickable: false
        }
    });
</script>
</body>
</html>