<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <%@include file="includes/include.jsp" %>
    <title><fmt:message key="summary" bundle="${lang}"/></title>
    <link rel="shortcut icon" href="">
    <link rel="stylesheet" href="<c:url value="/resources/css/progress-wizard.min.css"/>">
    <script src="<c:url value="/resources/js/delivery-summary.js"/>"></script>

</head>
<body>

<div class="loader-anim"></div>

<%@include file="includes/new-header.jsp" %>
<% request.setAttribute("cartItemsMap", request.getSession().getAttribute("cartItems")); %>
<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <img class="icons" src="<c:url value="/resources/images/icons/Summary.png"/>">

                <h3 class="header-panel"><fmt:message key="summary.order" bundle="${lang}"/></h3>
            </div>
            <div class="panel-body">
                <h2 style="text-align: center;"><u><fmt:message key="summary.order" bundle="${lang}"/></u></h2>

                <div class="section1">
                    <div class="container" style="width:800px;">
                        <h4><fmt:message key="summary.cart" bundle="${lang}"/></h4>
                        <hr>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="summary-table">
                                    <div class="summary-row summary-table-header">
                                        <div class="summary-cell"><fmt:message key="summary.description"
                                                                               bundle="${lang}"/></div>
                                        <div class="summary-cell"><fmt:message key="summary.price"
                                                                               bundle="${lang}"/></div>
                                        <div class="summary-cell qty-cell"><fmt:message key="summary.qty"
                                                                                        bundle="${lang}"/></div>
                                        <div class="summary-cell"><fmt:message key="summary.total"
                                                                               bundle="${lang}"/></div>
                                    </div>
                                    <c:forEach items="${cartItemsMap}" var="entry" varStatus="iteration">
                                        <div class="summary-row">
                                            <c:set var="itemPrice" value="${entry.itemPrice}"/>
                                            <c:set var="itemQty" value="${entry.itemQty}"/>
                                            <c:set var="itemTotal" value="${itemPrice*itemQty}"/>

                                            <div class="summary-cell"><c:out value="${entry.itemTitle}"/></div>
                                            <div class="summary-cell price-cell">
                                                <fmt:formatNumber type="currency" currencySymbol=""
                                                                  maxFractionDigits="2" value="${entry.itemPrice}"/>
                                            </div>
                                            <div class="summary-cell qty-cell"><c:out value="${entry.itemQty}"/></div>
                                            <div class="summary-cell price-cell">
                                                <fmt:formatNumber type="currency" currencySymbol=""
                                                                  maxFractionDigits="2" value="${itemTotal}"/>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="margin-top: 20px;">
                    <button class="btn btn-primary btn-editcart" onclick="location.href='shopping-cart'">
                        <span class="glyphicon glyphicon-edit"></span>&nbsp;<fmt:message key="summary.edit"
                                                                                         bundle="${lang}"/>
                    </button>
                </div>

                <div class="section2">
                    <div class="container" style="width:800px;">
                        <h4><fmt:message key="summary.details" bundle="${lang}"/></h4>
                        <hr>
                        <div class="row">
                            <div class="col-md-6">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label"><fmt:message key="summary.date"
                                                                                      bundle="${lang}"/></label>
                                        </div>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="delsum-text-date"
                                                   placeholder="<fmt:message key="summary.date" bundle="${lang}"/>"
                                                   readonly>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label"><fmt:message key="summary.time"
                                                                                      bundle="${lang}"/></label>
                                        </div>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="delsum-text-time"
                                                   placeholder="Time"
                                                   readonly>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label"><fmt:message key="delivery.first"
                                                                                      bundle="${lang}"/></label>
                                        </div>
                                        <div class="col-sm-10">
                                            <input type="text" id="del-fname" class="form-control" readonly>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label"><fmt:message key="signup.last.name"
                                                                                      bundle="${lang}"/></label>
                                        </div>
                                        <div class="col-sm-10">
                                            <input type="text" id="del-lname" class="form-control" readonly>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="col-md-6">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label"><fmt:message key="delivery.contact"
                                                                                      bundle="${lang}"/></label>
                                        </div>
                                        <div class="col-sm-10">
                                            <input type="text" id="del-contactno" class="form-control" readonly>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label"><fmt:message key="signup.address"
                                                                                      bundle="${lang}"/></label>
                                        </div>
                                        <div class="col-sm-10">
                                            <textarea class="form-control" id="del-address" readonly
                                                      style="resize: vertical;"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label"><fmt:message key="summary.description"
                                                                                      bundle="${lang}"/></label>
                                        </div>
                                        <div class="col-sm-10">
                                            <textarea class="form-control" id="del-description" readonly
                                                      style="resize: vertical;"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10"></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <button class="btn btn-primary btn-editdelidetails" onclick="location.href='delivery'">
                    <span class="glyphicon glyphicon-edit"></span>&nbsp;<fmt:message key="summary.edit.delivery"
                                                                                     bundle="${lang}"/>
                </button>

                <div class="section3">
                    <div class="container" style="width:800px;">
                        <h4><fmt:message key="summary.terms" bundle="${lang}"/></h4>
                        <hr>
                        <div class="row">
                            <div class="col-md-12">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <textarea class="form-control" readonly rows="10" disabled
                                                      style="resize: vertical;"><fmt:message key="order.summary.content"
                                                                                             bundle="${lang}"/></textarea>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="btn btn-success btn-agree" href="payment"><span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;<fmt:message
                        key="summary.agree" bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>
<a href="#" class="scrollup"></a>
</body>
</html>