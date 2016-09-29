<!DOCTYPE html>
<html>
<head lang="en">
    <title>Summary-Pickup</title>
    <link rel="shortcut icon" href="">
    <%@include file="include.jsp" %>

    <link href="<c:url value="/resources/css/progress-wizard.min.css"/>" rel="stylesheet" >


</head>
<body>
<div class="loader-anim"></div>

<%@include file="headertest2.jsp" %>

<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <img class="icons" src="<c:url value="/resources/images/icons/Summary.png"/>" >

                <h3 class="header-panel">Order summary</h3>
            </div>
            <div class="panel-body">
                <h2 style="text-align: center;"><u>Order Summary</u></h2>

                <div class="section1">
                    <div class="container" style="width:800px;">
                        <h4>Shopping cart items</h4>
                        <hr>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="summary-table">
                                    <div class="summary-row summary-table-header">
                                        <div class="summary-cell">Item description</div>
                                        <div class="summary-cell">Price (Rs.)</div>
                                        <div class="summary-cell qty-cell">QTY</div>
                                        <div class="summary-cell">Total (Rs.)</div>
                                    </div>
                                    <div class="summary-row">
                                        <div class="summary-cell">Sample item 02</div>
                                        <div class="summary-cell price-cell">450.00</div>
                                        <div class="summary-cell qty-cell">1</div>
                                        <div class="summary-cell price-cell">450.00</div>
                                    </div>
                                    <div class="summary-row">
                                        <div class="summary-cell">Sample item 01</div>
                                        <div class="summary-cell price-cell">650.00</div>
                                        <div class="summary-cell qty-cell">1</div>
                                        <div class="summary-cell price-cell">650.00</div>
                                    </div>
                                    <div class="summary-row">
                                        <div class="summary-cell">Sample item 03</div>
                                        <div class="summary-cell price-cell">450.00</div>
                                        <div class="summary-cell qty-cell">1</div>
                                        <div class="summary-cell price-cell">450.00</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="margin-top: 20px;">
                    <button class="btn btn-primary btn-proceed-payment btn-editcart"
                            onclick="location.href='shopping-cart.html'">
                        <span class="glyphicon glyphicon-edit"></span>&nbsp;Edit cart
                    </button>
                </div>
                <div class="section2">
                    <div class="container" style="width:800px;">
                        <h4>Pickup details</h4>
                        <hr>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label" for="text-picktime">Time</label>
                                    <input class="form-control" id="text-picktime" type="text" disabled>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label" for="text-branch">Branch</label>
                                    <input class="form-control" id="text-branch" type="text" disabled>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary btn-proceed-payment btn-editpickdetails"
                        onclick="location.href='delivery.html'">
                    <span class="glyphicon glyphicon-edit"></span>&nbsp;Edit pickup details
                </button>
                <div class="section3">
                    <div class="container" style="width:800px;">
                        <h4>Terms and Conditions</h4>
                        <hr>
                        <div class="row">
                            <div class="col-md-12">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <textarea class="form-control" disabled="" rows="10" disabled></textarea>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="btn btn-success btn-agree" href="payment"><span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;Agree
                    and continue to checkout</a>
            </div>
        </div>
    </div>
</div>
<a href="#" class="scrollup"></a>
</body>
</html>