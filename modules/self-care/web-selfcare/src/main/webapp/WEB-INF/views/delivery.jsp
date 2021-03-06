<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes/include.jsp" %>
    <title><fmt:message key="delivery" bundle="${lang}"/></title>
    <link rel="shortcut icon" href="">
    <link href="<c:url value="/resources/css/bootstrap-datepicker3.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/jquery.timepicker.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/resources/js/bootstrap-datepicker.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jquery.timepicker.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/delivery-operations.js"/>"></script>
    <script src="<c:url value="/resources/js/datepicker.js"/>"></script>
    <script src="<c:url value="/resources/js/validate-delivery.js"/>"></script>

    <%@include file="includes/smart-wizard.jsp" %>
</head>

<body>
<div class="loader-anim"></div>
<%@include file="includes/new-header.jsp" %>
<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <img class="icons" src="<c:url value="/resources/images/icons/pizzadeliveryman.png"/>">

                <h3 class="header-panel"><fmt:message key="delivery" bundle="${lang}"/></h3>
            </div>
            <div class="panel-body">
                <%--<ul class="progress-indicator">
                    <li class="active"><span class="bubble"></span> <fmt:message key="delivery" bundle="${lang}"/></li>
                    <li><span class="bubble"></span><fmt:message key="delivery.payment" bundle="${lang}"/></li>
                    <li><span class="bubble"></span><fmt:message key="delivery.verify" bundle="${lang}"/></li>
                    <li><span class="bubble"></span><fmt:message key="delivery.success" bundle="${lang}"/></li>
                </ul>--%>

                <div id="smartwizard">
                    <ul>
                        <li><a href=""><fmt:message key="delivery" bundle="${lang}"/><br/>
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

                <div style="margin-top: 30px;">
                    <div class="radio" style="text-align: center; font-size: medium;">
                        <label><fmt:message key="delivery.prefer" bundle="${lang}"/></label>
                        <label><input type="radio" name="optradio" id="radio-delivery" value="delivery" checked>
                            <fmt:message key="delivery" bundle="${lang}"/></label>&nbsp;
                        <label><input type="radio" name="optradio" id="radio-pickup" value="pickup"><fmt:message
                                key="delivery.pickup" bundle="${lang}"/></label>
                    </div>
                    <div id="delivery-div" style="margin-top: 30px;">
                        <h4><fmt:message key="delivery.when" bundle="${lang}"/></h4>
                        <hr>
                        <div class="radio">
                            <label><input type="radio" name="radiodelwhen" value="del-now" checked><fmt:message
                                    key="delivery.now" bundle="${lang}"/></label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" name="radiodelwhen" value="del-later"><fmt:message
                                    key="delivery.later" bundle="${lang}"/>&nbsp;&nbsp;&nbsp;<fmt:message
                                    key="delivery.date" bundle="${lang}"/></label>
                            <input type="text" class="del-date" id="date" name="date" style="height:auto;">
                            &nbsp;&nbsp;<fmt:message key="delivery.time" bundle="${lang}"/>
                            <div class="input-group" style="display: inline-block; vertical-align: middle;">
                                <input id="timepicker1" class="bootstrap-timepicker1 del-time" type="text"
                                       style="height: auto;">
                            </div>
                        </div>

                        <div style="margin-top: 30px;">
                            <h4><fmt:message key="delivery.where" bundle="${lang}"/></h4>
                            <hr>
                        </div>
                        <div class="container" style="width: 1000px;">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="control-label" for="txt-fname"><fmt:message key="delivery.first"
                                                                                                  bundle="${lang}"/></label>
                                        <label id="fname_error" class="error-labels"></label>
                                        <input class="form-control" id="txt-fname"
                                               placeholder="<fmt:message key="delivery.enter.first" bundle="${lang}"/>"
                                               type="text">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="txt-lname"><fmt:message key="delivery.last"
                                                                                                  bundle="${lang}"/></label>
                                        <label id="lname_error" class="error-labels"></label>
                                        <input class="form-control" type="text"
                                               placeholder="<fmt:message key="delivery.enter.last" bundle="${lang}"/>"
                                               id="txt-lname">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label"><fmt:message key="delivery.contact"
                                                                                  bundle="${lang}"/></label>
                                        <label id="contactno_error" class="error-labels"></label>
                                        <input id="txt-contactno" class="form-control" type="text"
                                               placeholder="<fmt:message key="delivery.enter.contact" bundle="${lang}"/>">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label"><fmt:message key="delivery.email"
                                                                                  bundle="${lang}"/></label>
                                        <label id="email_error" class="error-labels"></label>
                                        <input id="txt-email" class="form-control" type="text"
                                               placeholder="<fmt:message key="delivery.enter.email" bundle="${lang}"/>">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <form role="form">
                                        <div class="form-group">
                                            <label class="control-label"><fmt:message key="delivery.address"
                                                                                      bundle="${lang}"/></label>
                                            <label id="address_error" class="error-labels"></label>
                                            <input class="form-control" type="text" id="txt-deliadd01"
                                                   placeholder="<fmt:message key="delivery.addr1" bundle="${lang}"/>">

                                            <div style="margin-top: 13px;">
                                                <input class="form-control" id="txt-deliadd02" type="text"
                                                       placeholder="<fmt:message key="delivery.addr2" bundle="${lang}"/>">
                                            </div>
                                            <div style="margin-top: 13px;">
                                                <input class="form-control" id="txt-deliadd03" type="text"
                                                       placeholder="<fmt:message key="delivery.addr3" bundle="${lang}"/>">
                                            </div>
                                        </div>
                                        <div class="form-group" style="margin-top: 16px;">
                                            <label class="control-label"><fmt:message key="delivery.description"
                                                                                      bundle="${lang}"/></label>
                                            <textarea class="form-control txtarea-delivery-description"
                                                      id="txtarea-delivery-description"
                                                      placeholder="<fmt:message key="delivery.describe" bundle="${lang}"/>"
                                                      rows="3"></textarea>
                                        </div>
                                        <div>
                                            <a class="btn btn-success btn-proceed-payment btn-submit-proceed"
                                               id="btn-delivery-submit">
                                                <span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;<fmt:message
                                                    key="delivery.proceed" bundle="${lang}"/>
                                            </a>
                                            <button type="reset" class="btn btn-success btn-delivery-reset"
                                                    value="<fmt:message key="delivery.reset" bundle="${lang}"/>">
                                                <span class="glyphicon glyphicon-refresh"></span>&nbsp;&nbsp;<fmt:message
                                                    key="delivery.reset" bundle="${lang}"/>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="pickup-div" style="margin-top: 30px;">
                        <h4><fmt:message key="delivery.when.pick" bundle="${lang}"/></h4>
                        <hr>
                        <div class="radio">
                            <label><input type="radio" name="radiopickwhen" value="pick-now" checked><fmt:message
                                    key="delivery.pickup.now" bundle="${lang}"/></label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" name="radiopickwhen" value="pick-later"><fmt:message
                                    key="delivery.pickup.later" bundle="${lang}"/>&nbsp;
                            </label>
                            &nbsp;&nbsp;<fmt:message key="delivery.time" bundle="${lang}"/>
                            <div class="input-group" style="display: inline-block; vertical-align: middle;">
                                <input id="timepicker2" class="bootstrap-timepicker2 pickup-time" type="text"
                                       style="height: auto;">
                            </div>
                        </div>

                        <div style="margin-top: 30px;">
                            <h4><fmt:message key="delivery.from" bundle="${lang}"/></h4>
                            <hr>
                        </div>

                        <div class="container" style="width: 1000px;">
                            <div class="row">
                                <div class="col-md-6">
                                    <form role="form">
                                        <div class="form-group">
                                            <label class="control-label"><fmt:message key="delivery.pickup.branch"
                                                                                      bundle="${lang}"/></label>
                                            <select class="form-control" id="pickup-branch">
                                                <option value="<fmt:message key="delivery.colombo" bundle="${lang}"/>">
                                                    <fmt:message key="delivery.colombo" bundle="${lang}"/></option>
                                                <%--<option value="<fmt:message key="delivery.galle" bundle="${lang}"/>">
                                                    <fmt:message key="delivery.galle" bundle="${lang}"/></option>
                                                <option value="<fmt:message key="delivery.matara" bundle="${lang}"/>">
                                                    <fmt:message key="delivery.matara" bundle="${lang}"/></option>--%>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label"><fmt:message key="delivery.branch.address"
                                                                                      bundle="${lang}"/></label>
                                            <textarea class="form-control txtarea-branch-address" rows="6"
                                                      readonly><fmt:message key="delivery.branch.address.value"
                                                                            bundle="${lang}"/></textarea>
                                        </div>
                                        <div>
                                            <a class="btn btn-success btn-proceed-payment"
                                               id="btn-pickup-submit"
                                               style="width: 45%; display: inline-block; float: right;"><span
                                                    class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;<fmt:message
                                                    key="delivery.proceed" bundle="${lang}"/></a>
                                            <button type="reset" class="btn btn-success" value="Reset"
                                                    style="width: 45%; display: inline-block; float: right; margin-right:5px">
                                                <span class="glyphicon glyphicon-refresh"></span>&nbsp;&nbsp;<fmt:message
                                                    key="delivery.reset" bundle="${lang}"/>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-6">
                                    <label class="control-label"><fmt:message key="delivery.locate"
                                                                              bundle="${lang}"/></label>
                                </div>

                                <img class="center-block img-responsive"
                                         src="<c:url value="/resources/images/map.png"/>" width="300px"
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
                <h2 class="modal-title"><img src="<c:url value="/resources/images/icons/error.png"/>" width="100px"
                                             height="100px">&nbsp;&nbsp;<fmt:message key="validation.error.popup.title"
                                                                                     bundle="${lang}"/></h2>
            </div>
            <div class="modal-body">
                <p style="font-size: medium;"><fmt:message key="delivery.error" bundle="${lang}"/>
                </p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal"><fmt:message key="validation.error.popup.ok"
                                                                             bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>
<a href="#" class="scrollup"></a>
<script>
    $('#smartwizard').smartWizard({
        selected: 0,
        theme: 'arrows',
        anchorSettings: {
            anchorClickable: false
        }
    });
</script>
</body>
</html>