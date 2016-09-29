<!DOCTYPE html>
<html>
<head lang="en">
    <title>Payment</title>
    <link rel="shortcut icon" href="">

    <%@include file="include.jsp" %>

    <link rel="stylesheet" href="<c:url value="/resources/css/progress-wizard.min.css"/>" >
    <script src="<c:url value="/resources/js/payment-operations.js"/>" ></script>

    <script src="<c:url value="/resources/js/validate-pay.js"/>" ></script>

</head>
<body>

<div class="loader-anim"></div>

<%@include file="headertest2.jsp" %>

<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <img class="icons" src="<c:url value="/resources/images/icons/pay.png"/>" >
                <h3 class="header-panel">Pay</h3>
            </div>
            <div class="panel-body">
                <ul class="progress-indicator">
                    <li class="completed"><span class="bubble"></span> Delivery</li>
                    <li class="completed"><span class="bubble"></span> Payment</li>
                    <li class="active"><span class="bubble"></span> Verify</li>
                    <li><span class="bubble"></span> Success</li>
                </ul>
                <div style="text-align: center; margin-top: 40px;">
                    <img src="<c:url value="/resources/images/payment/visa.png"/>" >
                    <img src="<c:url value="/resources/images/payment/master.png"/>" >
                </div>
                <div class="container" style="width:800px;;">
                    <div class="row">
                        <div class="col-md-6">
                            <form role="form">
                                <h4><strong>Total Amount (Rs.): 2000.00</strong></h4>

                                <div class="form-group">
                                    <label class="control-label" for="txt-card-name">Name on card</label>
                                    <label id="cardname_error" class="error-labels"></label>
                                    <input class="form-control" id="txt-card-name"
                                           placeholder="Enter name as appeared on the card" type="text">
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="txt-card-no">Card number</label>
                                    <label id="cardno_error" class="error-labels"></label>
                                    <input class="form-control" id="txt-card-no"
                                           placeholder="XXXX-XXXX-XXXX-XXXX" type="text">
                                </div>
                                <div>
                                    <label class="control-label">Expiration date</label>
                                </div>
                                <label for="month">Month:</label>

                                <div class="form-group" style="display:inline-block;">
                                    <select class="form-control" id="month">
                                        <option>Jan</option>
                                        <option>Feb</option>
                                        <option>Mar</option>
                                    </select>
                                </div>
                                <label for="year">Year:</label>

                                <div class="form-group" style="display:inline-block;">
                                    <select class="form-control" id="year">
                                        <option>2016</option>
                                        <option>2017</option>
                                        <option>2018</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="txt-cvv-no">Security code/CVV number</label>
                                    <label id="cvv_error" class="error-labels"></label>
                                    <input class="form-control" id="txt-cvv-no"
                                           placeholder="XXXX" type="text" size="3" style="width: 70px;">
                                </div>
                                <div style="text-align: right;">
                                    <a class="btn btn-success btn-proceed-payment" id="btn-confirm-pay"
                                       style="width: 45%; margin-left: 25%;"><span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;Pay (Rs. 2000.00)</a>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-6" style="margin-top: 10%;">
                            <img src="<c:url value="/resources/images/payment/Credit-Card-Security-Code-Locations.jpg"/>" width="100%;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="confirm-payment-popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <img class="icons" src="<c:url value="/resources/images/icons/pay-confirm.png"/>" >
                <h4 class="modal-title header-panel">Confirm Payment</h4>
            </div>
            <div class="modal-body">
                <p>You are about to pay Rs. 2000 via the card number ending with 3423.
                    <br>Click 'Pay 'to make th payment or cancel to go to the previis page.</p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" id="btn-pay-cancel" data-dismiss="modal">Cancel</a>
                <a class="btn btn-success" id="btn-pay-popup">Pay</a>
            </div>
        </div>
    </div>
</div>

<!-- Payment details incorrect popup -->
<div id="pay-validation-error-popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><img src="<c:url value="/resources/images/icons/error.png"/>" width="100px" height="100px">&nbsp;&nbsp;Validation errors</h2>
            </div>
            <div class="modal-body">
                <p style="font-size: medium;">Can not proceed due to the
                    <b>validation errors in the form.</b><br>
                    Please correct them and try again.
                </p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal">Ok</a>
            </div>
        </div>
    </div>
</div>
<a href="#" class="scrollup"></a>
</body>
</html>