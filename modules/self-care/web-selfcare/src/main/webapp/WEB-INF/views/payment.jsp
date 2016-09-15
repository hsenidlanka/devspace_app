<!DOCTYPE html>
<html>
<head lang="en">
    <title>Payment</title>
    <link rel="shortcut icon" href="">
    <%@include file="include.jsp" %>

    <link rel="stylesheet" href="<c:url value="/resources/css/progress-wizard.min.css"/>" >
    <script src="<c:url value="/resources/js/payment-operations.js"/>" ></script>
    <script src="<c:url value="/resources/js/common.js"/>" ></script>

</head>
<body>
<div class="loader-anim"></div>

<%@include file="headertest2.jsp" %>

<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <img class="icons" src="<c:url value="/resources/images/icons/payment-method.png"/>" >
                <h3 class="header-panel">Setup payment</h3>
            </div>
            <div class="panel-body">
                <ul class="progress-indicator">
                    <li class="completed"><span class="bubble"></span> Delivery</li>
                    <li class="active"><span class="bubble"></span> Payment</li>
                    <li><span class="bubble"></span> Verify</li>
                    <li><span class="bubble"></span> Success</li>
                </ul>

                <div class="container" style="width:1000px;">
                    <div class="row">
                        <div class="col-md-6">
                            <h4>Sender details</h4>
                            <hr>
                            <div class="col-md-12">
                                <form role="form">
                                    <div class="form-group">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox">Same as the recipient</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">First name</label>
                                        <input class="form-control" type="text">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Last name</label>
                                        <input class="form-control" type="text">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Email</label>
                                        <input class="form-control" type="text">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Contact no</label>
                                        <input class="form-control" type="text">
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <h4>Preferred payment method</h4>
                            <hr>
                            <label>Payment method:&nbsp;</label>

                            <div class="radio">
                                <label>
                                    <input type="radio" name="paymentradio" id="radio-delivery" value="payondel"
                                           checked="">Pay on delivery</label>&nbsp;&nbsp;
                                <label>
                                    <input type="radio" name="paymentradio" id="radio-pickup" value="payonline">Pay
                                    online</label>
                            </div>
                            <a class="btn btn-success btn-proceed-payment" id="btn-pay"><span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;Continue</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="#" class="scrollup"></a>
</body>
</html>