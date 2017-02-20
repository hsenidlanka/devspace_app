<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include.jsp" %>
    <link href="<c:url value="/resources/css/bootstrap-table.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/bootstrap-datepicker3.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/resources/js/bootstrap-datepicker.min.js"/>" type="text/javascript"></script>
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
            background-color: rgba(66, 68, 68, 0.88);
            font-weight: bold;
            color: #f5f5f5;
            text-align: center;
        }

        /*#table-purchaseHistory th{
            background-color: #00a5ff !important;
        }*/
    </style>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <img class="icons" src="<c:url value="/resources/images/icons/purchase_history.png"/>">

        <h3 class="header-panel"><fmt:message key="purchase.history.header.text" bundle="${lang}"/></h3>
    </div>
    <div class="panel-body">
        <div class="input-group pull-right" style="margin-bottom: 5px;">
            <div class="input-group">
                <input type="text" id="txtSearchOrder" name="date" class="search form-control"
                       placeholder="<fmt:message key="purchase.history.search.placeholder" bundle="${lang}"/>"
                       style="width: 150px">

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
            <label class="pagiTexts" style="display: inline;"><fmt:message key="purchase.history.model.gotopage"
                                                                           bundle="${lang}"/></label>
            <select id="comboPages" style="height: 32px; border-radius:4px; background-color: transparent;">
            </select>
            &nbsp;&nbsp;
            <label class="pagiTexts" style="display: inline;"><fmt:message key="purchase.history.model.recsperpage"
                                                                           bundle="${lang}"/></label>
            <select id="comboRecCount" style="height: 32px; border-radius:4px; background-color: transparent;">
                <option value="5" selected><fmt:message key="purchase.history.model.rec1"
                                                        bundle="${lang}"/></option>
                <option value="10"><fmt:message key="purchase.history.model.rec2"
                                                bundle="${lang}"/></option>
                <option value="20"><fmt:message key="purchase.history.model.rec3"
                                                bundle="${lang}"/></option>
            </select>
        </div>
        <div id="pagination" style="float: right;font-size: 12px;"></div>
        <div id="pagination2" style="float: right;font-size: 12px;"></div>
    </div>
</div>

<%@include file="modals.jsp" %>
</body>
</html>
