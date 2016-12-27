<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title><fmt:message key="menu.category.title" bundle="${lang}"/></title>
    <link rel="shortcut icon" href="">
    <script src="<c:url value="/resources/js/menu-operations.js"/>"></script>
    <link href="<c:url value="/resources/css/component.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/default.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/teskly.viewitle.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/js/modernizr.custom.js"/>"></script>
    <script src="<c:url value="/resources/js/teskly.viewitle.js"/>"></script>
</head>
<body>
<%@include file="../includes/new-header.jsp" %>

<div class="loader-anim"></div>

<div class="main-div">
    <div class="section" style="min-width: 1200px;">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div style="display: inline-block;">
                    <img class="icons" src="<c:url value="/resources/images/icons/menu.png"/>"/>

                    <h3 id="menu-panel-heading" class="header-panel">
                        <fmt:message key="menu.main.panel.header" bundle="${lang}"/>
                        <c:out value="${category}"/>
                    </h3>
                </div>
            </div>

            <div class="panel panel-body">
                <div class="row">
                    <c:forEach items="${subcategories}" var="subcategory">
                        <c:set var="subCat1" value="${subcategory.name}"/>
                        <c:set var="subCat2" value="${fn:replace(subCat1,
                                ' ', '')}"/>
                        <div style="margin-top: 15px;">
                            <h4><c:out value="${subcategory.name}"/></h4>
                            <c:out value="${subcategory.description}"/>
                        </div>
                        <div class="row" style="margin-top: 15px;">
                        <c:choose>
                            <c:when test="${not empty requestScope[subCat2]}">
                                <c:forEach items="${requestScope[subCat2]}" var="itemData" varStatus="loop">
                                    <c:if test="${not loop.first and loop.index % 4 == 0}">
                                        </div>
                                        <div class="row" style="margin-top: 10px;">
                                    </c:if>
                                    <div class="col-md-3">
                                        <ul class="grid cs-style-3">
                                            <li data-teskly-viewitled="<c:out value="${itemData.description}"/>">
                                                <figure>
<%--To change--%>

                                                    <c:set var="itemName" value="${fn:replace(itemData.name, ' ', '')}" />
                                                    <img class="menu-images" src="${itemImageUrl}/${itemName}.jpg" width="200px" height="200px">
                                                    <figcaption class="caption">
                                                        <input type="hidden" id="menu-item-desc"
                                                               value="<c:out value="${itemData.description}"/>"/>

                                                        <c:set var="sizePriceLength"
                                                               value="${fn:length(itemData.price)}"/>

                                                        <c:choose>
                                                            <c:when test="${category eq 'Pizza'}">
                                                                <c:forEach begin="0" end="${sizePriceLength-1}"
                                                                           varStatus="loop">

                                                                    <c:set var="size"
                                                                           value="${itemData.price[loop.index]['size']}"/>
                                                                    <c:set var="substringSize"
                                                                           value="${fn:substring(size, 0, 1)}"/>
                                                                    <c:set var="formattedSize"
                                                                           value="${fn:toUpperCase(substringSize)}"/>

                                                                    <c:out value="${formattedSize}"/>:&nbsp;<label id="pizza-price-<c:out
                                                                        value="${itemData.price[loop.index]['size']}"/>"><fmt:formatNumber type="currency" currencySymbol=""
                                                                                                                                           maxFractionDigits="2" groupingUsed="false" value="${itemData.price[loop.index]['price']}"/>
                                                                    </label><br>
                                                                </c:forEach>
                                                                <a class="btn menu-add-to-cart-btn btn-success btn-xs btn-addtocart-<c:out value="${category}"/>">
                                                                    Add
                                                                    to
                                                                    cart
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="row" style="margin-top: 15px;">
                                                                    <label style="float: left; font-size: small">Price: <br>(Rs.)</label>
                                                                    <label id="lbl-sizeprice"
                                                                           style="float: left;"><fmt:formatNumber type="currency" currencySymbol=""
                                                                                                                  maxFractionDigits="2" groupingUsed="false" value="${itemData.price[0]['price']}"/></label>
                                                                    <select id="sizes" class="sizes-dropdowns"
                                                                            style="text-transform: capitalize; font-size: small; float: right; width: 80px; height: 20px">
                                                                        <c:forEach begin="0" end="${sizePriceLength-1}"
                                                                                   varStatus="loop">
                                                                            <option value="<c:out value="${itemData.price[loop.index]['size']}"/>">
                                                                                <c:out value="${itemData.price[loop.index]['size']}"/></option>
                                                                        </c:forEach>
                                                                    </select>
                                                                    <c:forEach begin="0" end="${sizePriceLength-1}"
                                                                               varStatus="loop">
                                                                        <input type="hidden"
                                                                               id="hiddenFld-<c:out value="${itemData.price[loop.index]['size']}"/>"
                                                                               <%--value="<c:out value="${itemData.price[loop.index]['price']}"/>"/>--%>
                                                                               value="<fmt:formatNumber type="currency" currencySymbol=""
                                                                                                                  maxFractionDigits="2" groupingUsed="false" value="${itemData.price[loop.index]['price']}"/>"/>
                                                                    </c:forEach>

                                                                </div>
                                                                <div style="float: left;margin-top: 10px;">
                                                                    <label class="add-to-cart-label-qty">QTY:</label>
                                                                    <input class="spin add-to-cart-spin" type="number"
                                                                           min="1" max="100" value="1">
                                                                </div>
                                                                <a class="btn menu-add-to-cart-btn btn-success btn-xs btn-addtocart"
                                                                   style="float: right;margin-bottom: -15px;">
                                                                    Add to cart
                                                                </a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </figcaption>
                                                </figure>
                                            </li>
                                        </ul>
                                        <div class="itemdata-name" style="text-align: center;">
                                            <label id="menu-item-name">${itemData.name}</label>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <storng>No items to show</storng>
                            </c:otherwise>
                        </c:choose>

                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../includes/modals.jsp" %>
<script>
    jQuery(document).viewitle();
</script>
</body>
</html>