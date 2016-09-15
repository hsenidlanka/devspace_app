<!DOCTYPE html>
<html>
<head lang="en">
    <title>Order Success</title>
    <link rel="shortcut icon" href="">

    <%@include file="include.jsp" %>

    <link rel="stylesheet" href="<c:url value="/resources/css/progress-wizard.min.css"/>" >
    <script src="<c:url value="/resources/js/common.js"/>" ></script>
</head>
<body>
<div class="loader-anim"></div>
<%@include file="headertest2.jsp" %>
<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <img class="icons" src="<c:url value="/resources/images/icons/list-correct.png"/>">
                <h3 class="header-panel">Order placed</h3>
            </div>
            <div class="panel-body">
                <ul class="progress-indicator">
                    <li class="completed"><span class="bubble"></span> Delivery</li>
                    <li class="completed"><span class="bubble"></span> Payment</li>
                    <li class="completed"><span class="bubble"></span> Verify</li>
                    <li class="completed"><span class="bubble"></span> Success</li>
                </ul>

                <div class="container" style="width:800px;;">
                    <div style="text-align: center;">
                        <img src="<c:url value="/resources/images/icons/success.png"/>"  width="100px" height="100px">

                        <h1>Thank you</h1>
                    </div>
                    <div>
                        <h4>Your order has placed successfully...Here are some details of your order.</h4>
                    </div>
                    <div><label>Order id:</label></div>
                    <div><label>Order date:</label></div>
                    <div><label>Total:</label></div>
                    <div><label>Type:</label></div>
                    <div><label>Status:</label></div>

                    <div>
                        <a href="home">Click here to go to homepage</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="#" class="scrollup"></a>
</body>
</html>