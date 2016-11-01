<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 10/27/16
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Menu-Categories</title>
    <link rel="shortcut icon" href="">
    <%@include file="../includes/include.jsp" %>
    <script src="<c:url value="/resources/js/menu-operations.js"/>"></script>
    <link href="<c:url value="/resources/css/component.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/default.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/js/modernizr.custom.js"/>"></script>
</head>
<body>
<%@include file="../includes/NewHeader.jsp" %>
<div class="loader-anim"></div>

<div class="main-div">
    <div class="section" style="min-width: 1200px;">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div style="display: inline-block;">
                    <img class="icons" src="<c:url value="/resources/images/icons/menu.png"/>"/>

                    <h3 id="menu-panel-heading" class="header-panel"><fmt:message key="menu.main.panel.header"
                                                                                  bundle="${lang}"/> <c:out
                            value="${category}"/></h3>
                </div>
            </div>

            <div class="panel panel-body">
                <div class="row">
                    <c:forEach items="${subcategories}" var="subcategory">
                        <c:set var="subCat1" value="${subcategory.name}"/>
                        <c:set var="subCat2" value="${fn:replace(subCat1,
                                ' ', '')}"/>
                        <div style="margin-top: 15px;">
                            <c:out value="${subcategory.name}"/>
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
                                            <li>
                                                <figure>
                                                    <img src="<c:url value="/resources/images/image_placeholder.gif"/>"
                                                         class="menu-images">
                                                    <figcaption class="caption">
                                                        <input type="hidden" id="menu-item-desc"
                                                               value="<c:out value="${itemData.description}"/>"/>

                                                        <c:choose>
                                                            <c:when test="${category eq 'Pizza'}">
                                                                <c:set var="sizePrizeLength" value="${fn:length(itemData.price)}"/>

                                                                <c:forEach begin="0" end="${sizePrizeLength-1}" varStatus="loop">
                                                                    <c:out value="${loop}"/>
                                                                </c:forEach>

                                                                <c:out value="${itemData.price[1]['size']}-${itemData.price[1]['price']}"/>
                                                                <a class="btn btn-success btn-xs btn-addtocart-<c:out value="${category}"/>">
                                                                    Add
                                                                    to
                                                                    cart
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div>
                                                                    <label>Price:</label>
                                                                </div>
                                                                <div style="margin-top: 15px;">
                                                                    <label class="add-to-cart-label-qty">QTY:</label>
                                                                    <input class="spin add-to-cart-spin" type="number"
                                                                           min="1" max="100" value="1">
                                                                </div>
                                                                <a class="btn btn-success btn-xs btn-addtocart">
                                                                    Add
                                                                    to
                                                                    cart
                                                                </a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </figcaption>
                                                </figure>
                                            </li>
                                        </ul>
                                        <div style="text-align: center;">
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


<%--<c:forEach items="${subcategories}" var="subcategory">
    <c:set var="subCat1" value="${subcategory.name}"/>
    <c:set var="subCat2" value="${fn:replace(subCat1,
                                ' ', '')}"/>
    <c:out value="${subcategory.name}"/>

    <c:forEach items="${requestScope[subCat2]}" var="itemData">
        <c:out value="${itemData.name}"/><br>
    </c:forEach>
</c:forEach>--%>
<%@include file="../includes/modals.jsp" %>
</body>
</html>
