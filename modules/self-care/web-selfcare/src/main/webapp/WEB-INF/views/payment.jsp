<!DOCTYPE html>
<html>
<head lang="en">
    <%@include file="includes/include.jsp" %>
    <title><fmt:message key="delivery.payment" bundle="${lang}"/></title>
    <link rel="shortcut icon" href="">
    <script src="<c:url value="/resources/js/payment-operations.js"/>"></script>
    <%@include file="includes/smart-wizard.jsp" %>
</head>
<body>
<div class="loader-anim"></div>
<%@include file="includes/new-header.jsp" %>

<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <img class="icons" src="<c:url value="/resources/images/icons/payment-method.png"/>">

                <h3 class="header-panel"><fmt:message key="payment.setup" bundle="${lang}"/></h3>
            </div>
            <div class="panel-body">
                <div id="smartwizard">
                    <ul>
                        <li class="done"><a href=""><fmt:message key="delivery" bundle="${lang}"/><br/>
                            <small><fmt:message key="delivery.msg" bundle="${lang}"/></small>
                        </a></li>
                        <li><a href=""><fmt:message key="delivery.payment" bundle="${lang}"/><br/>
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

                <div class="container" style="width:1000px; margin-top: 30px;">
                    <div class="row" style="padding-left: 50px;">
                        <div class="a col-md-3" style="padding: 5px;">
                            <a href="success" id="pay-delivery"
                               data-toggle="tooltip" data-placement="top" title="Make payment on delivery."><img
                                    class="icons2" src="<c:url value="/resources/images/icons/delivery-xxl.png"/>"/></a>

                            <h3 class="payment-icon-headings">Pay on Delivery</h3>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-3">
                            <a href="pay-mobile" id="pay-mobile" data-toggle="tooltip"
                               data-placement="top" title="Make payment using mobile phone."><img class="icons2"
                                                                                                  src="<c:url value="/resources/images/icons/mobile-payment.png"/>"/></a>

                            <h3 class="payment-icon-headings">Pay by Mobile</h3>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-3">
                            <a href="pay-card" id="pay-card" data-toggle="tooltip"
                               data-placement="top" title="Make payment using Visa/Master card."><img class="icons2"
                                                                                                      src="<c:url value="/resources/images/icons/credit-cards-icon-25.png"/>"/></a>

                            <h3 class="payment-icon-headings">Pay by Card</h3>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="#" class="scrollup"></a>
<script>
    $('#smartwizard').smartWizard({
        selected: 1,
        theme: 'arrows',
        anchorSettings: {
            anchorClickable: false
        }
    });
</script>
</body>
</html>