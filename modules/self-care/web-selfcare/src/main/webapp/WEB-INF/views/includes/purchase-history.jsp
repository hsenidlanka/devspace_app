<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include.jsp" %>
    <link href="<c:url value="/resources/css/bootstrap-table.min.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/js/bootstrap-table.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap3-typeahead.min.js"/>"></script>
    <script src="<c:url value="/resources/js/simple-bootstrap-paginator.js"/>"></script>
    <script src="<c:url value="/resources/js/purchase-history.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/m-buttons.css"/>"/>

    <style>
        /*.tbody-ordItems {
            display: block;
            height: 50px;
            overflow: auto;
        }

        .thead-ordItems, .tbody-ordItems, .tr-ordItems {
            display: table;
            width: 100%;
            table-layout: fixed; *//* even columns width , fix width of table too *//*
        }

        .table-ordItems {
            width: 100%;
        }*/

        .thead-ordItems {
            background-color: #0094c0;
            font-weight: bold;
            color: #f5f5f5;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <img class="icons" src="<c:url value="/resources/images/icons/pay.png"/>">

        <h3 class="header-panel"><fmt:message key="purchase.history.header.text" bundle="${lang}"/></h3>
    </div>
    <div class="panel-body">
        <div class="input-group pull-right" style="margin-bottom: 5px;">
            <div class="input-group">
                <input type="text" id="txtSearchOrder" class="search form-control"
                       placeholder="Search customer by first name">

                <div class="input-group-btn">
                    <button class="m-btn blue icn-only" id="btnSearchOrder" type="submit"
                            style="margin-top: 0px !important;"><i class="fa fa-search" aria-hidden="true"></i>
                    </button>
                </div>
            </div>
        </div>
        <div>
            <table id="table-purchaseHistory" style="font-size: 12px;"></table>
        </div>
        <div class="input-group" style="margin-top:21px; margin-left:5px; float: right; font-size: 12px;">
            <label class="pagiTexts" style="display: inline;">Go to page: </label>
            <select id="comboPages" style="height: 32px; border-radius:4px; background-color: transparent;">
            </select>
            &nbsp;&nbsp;
            <label class="pagiTexts" style="display: inline;">Records per page: </label>
            <select id="comboRecCount" style="height: 32px; border-radius:4px; background-color: transparent;">
                <option value="5" selected>5</option>
                <option value="10">10</option>
                <option value="20">20</option>
            </select>
        </div>
        <div id="pagination" style="float: right;font-size: 12px;"></div>
    </div>
</div>

<%-- Modal shows details of the items of an order --%>
<div id="order-items-popup" class="modal fade">
    <div class="modal-dialog" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <img class="icons" src="<c:url value="/resources/images/icons/pay-confirm.png"/>">
                <h4 class="modal-title header-panel"><fmt:message key="purchase.history.order.items.header"
                                                                  bundle="${lang}"/></h4>
            </div>
            <div class="modal-body">
                <div id="table-orderItems"></div>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal"><fmt:message key="purchase.history.model.close"
                                                                             bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
