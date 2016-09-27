<!DOCTYPE html>
<html>
<head lang="en">
    <%@include file="include.jsp" %>

    <title> <fmt:message key="delivery" bundle="${lang}"/></title>
    <link rel="shortcut icon" href="">


    <link href="<c:url value="/resources/css/bootstrap-datepicker3.css"/>" rel="stylesheet" />
    <script src="<c:url value="/resources/js/bootstrap-datepicker.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/delivery-operations.js"/>" ></script>
    <link href="<c:url value="/resources/css/progress-wizard.min.css"/>" rel="stylesheet" >
    <script src="<c:url value="/resources/js/datepicker.js"/>" ></script>
    <script src="<c:url value="/resources/js/common.js"/>" ></script>
    <script src="<c:url value="/resources/js/validate-delivery.js"/>" ></script>

</head>

<body>
<div class="loader-anim"></div>
<%@include file="headertest2.jsp" %>
<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <img class="icons" src="<c:url value="/resources/images/icons/pizzadeliveryman.png"/>" >

                <h3 class="header-panel">Delivery</h3>
            </div>
            <div class="panel-body">
                <ul class="progress-indicator">
                    <li class="active"><span class="bubble"></span> <fmt:message key="delivery" bundle="${lang}"/></li>
                    <li><span class="bubble"></span><fmt:message key="delivery.pay" bundle="${lang}"/></li>
                    <li><span class="bubble"></span><fmt:message key="delivery.verify" bundle="${lang}"/></li>
                    <li><span class="bubble"></span><fmt:message key="delivery.success" bundle="${lang}"/></li>
                </ul>

                <div style="margin-top: 30px;">
                    <div class="radio" style="text-align: center; font-size: medium;">
                        <label><fmt:message key="delivery.prefer" bundle="${lang}"/></label>
                        <label><input type="radio" name="optradio" id="radio-delivery" value="delivery" checked>A
                            <fmt:message key="delivery" bundle="${lang}"/></label>&nbsp;
                        <label><input type="radio" name="optradio" id="radio-pickup" value="pickup"><fmt:message key="delivery.pickup" bundle="${lang}"/></label>
                    </div>
                    <div id="delivery-div" style="margin-top: 30px;">
                        <h4><fmt:message key="delivery.when" bundle="${lang}"/></h4>
                        <hr>
                        <div class="radio">
                            <label><input type="radio" name="radiodelwhen" value="del-now" checked><fmt:message key="delivery.pickup" bundle="${lang}"/>Deliver now</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" name="radiodelwhen" value="del-later">Deliver later:&nbsp;&nbsp;&nbsp;<fmt:message key="delivery.pickup" bundle="${lang}"/>Date:</label>
                            <input type="text" id="date" name="date">
                            &nbsp;&nbsp;Time:
                            <div class="input-group" style="display: inline-block; vertical-align: middle;">
                                <input id="timepicker1" type="text">
                            </div>
                        </div>

                        <div style="margin-top: 30px;">
                            <h4><fmt:message key="delivery.pickup" bundle="${lang}"/> Where to deliver</h4>
                            <hr>
                        </div>
                        <div class="container" style="width: 1000px;">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="control-label" for="txt-fname"><fmt:message key="delivery.pickup" bundle="${lang}"/>First name</label>
                                        <label id="fname_error" class="error-labels"></label>
                                        <input class="form-control" id="txt-fname"
                                               placeholder="<fmt:message key="delivery.pickup" bundle="${lang}"/>Enter your first name" type="email">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="txt-lname">Last name</label>
                                        <label id="lname_error" class="error-labels"></label>
                                        <input class="form-control" type="text" placeholder="Enter your last name"
                                               id="txt-lname">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Contact no</label>
                                        <label id="contactno_error" class="error-labels"></label>
                                        <input id="txt-contactno" class="form-control" type="text"
                                               placeholder="Enter your contact no (e.g: 94712345678)">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Email</label>
                                        <label id="email_error" class="error-labels"></label>
                                        <input id="txt-email" class="form-control" type="text"
                                               placeholder="Enter your email address">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <form role="form">
                                        <div class="form-group">
                                            <label class="control-label">Delivery address</label>
                                            <label id="address_error" class="error-labels"></label>
                                            <input class="form-control" type="text" id="txt-deliadd01"
                                                   placeholder="Building name/no and street name">

                                            <div style="margin-top: 5px;">
                                                <input class="form-control" id="txt-deliadd02" type="text"
                                                       placeholder="Address line 02">
                                            </div>
                                            <div style="margin-top: 5px;">
                                                <input class="form-control" id="txt-deliadd03" type="text"
                                                       placeholder="Address line 03">
                                            </div>
                                            <div style="margin-top: 5px;">
                                                <input class="form-control" id="txt-deliadd04" type="text"
                                                       placeholder="City">
                                            </div>
                                        </div>
                                        <div class="form-group" style="margin-top: 20px;">
                                            <label class="control-label">Description of the delivery location</label>
                                            <textarea class="form-control"
                                                      placeholder="Describe the location to ease the delivery process"></textarea>
                                        </div>
                                        <div>
                                            <a class="btn btn-success btn-proceed-payment btn-submit-proceed"
                                               id="btn-delivery-submit">
                                                <span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;Proceed
                                            </a>
                                            <button type="reset" class="btn btn-success btn-delivery-reset"
                                                    value="Reset">
                                                <span class="glyphicon glyphicon-refresh"></span>&nbsp;&nbsp;Reset
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="pickup-div" style="margin-top: 30px;">
                        <h4>When to pickup</h4>
                        <hr>
                        <div class="radio">
                            <label><input type="radio" name="radiopickwhen" value="pick-now" checked>Pickup now</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" name="radiopickwhen" value="pick-later">Pickup later:&nbsp;
                            </label>
                            &nbsp;&nbsp;Time:
                            <div class="input-group" style="display: inline-block; vertical-align: middle;">
                                <input id="timepicker2" type="text">
                            </div>
                        </div>

                        <div style="margin-top: 30px;">
                            <h4>From where to pickup</h4>
                            <hr>
                        </div>

                        <div class="container" style="width: 1000px;">
                            <div class="row">
                                <div class="col-md-6">
                                    <form role="form">
                                        <div class="form-group">
                                            <label class="control-label">Pickup branch</label>
                                            <select class="form-control">
                                                <option>Colombo</option>
                                                <option>Galle</option>
                                                <option>Kaluthara</option>
                                            </select>

                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Branch address</label>
                                            <textarea class="form-control" disabled></textarea>
                                        </div>
                                        <div>
                                            <a class="btn btn-success btn-proceed-payment"
                                               style="width: 45%; display: inline-block; float: right;"
                                               href="pickup-summary"><span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;Proceed</a>
                                            <button type="reset" class="btn btn-success" value="Reset"
                                                    style="width: 45%; display: inline-block; float: right; margin-right:5px">
                                                <span class="glyphicon glyphicon-refresh"></span>&nbsp;&nbsp;Reset
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-6">
                                    <label class="control-label">Locate branch</label>
                                </div>
                                <img class="center-block img-responsive" src="<c:url value="/resources/images/map.png"/>"  width="300px"
                                     height="300px;">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Delivery details incorrect popup -->
<div id="validation-error-popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><img src="<c:url value="/resources/images/icons/error.png"/>"  width="100px" height="100px">&nbsp;&nbsp;Validation
                    errors</h2>
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