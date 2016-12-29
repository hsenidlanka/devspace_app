<!DOCTYPE html>
<html>
<head lang="en">
    <title>Search Results</title>
    <link rel="shortcut icon" href="">

    <%@include file="includes/include.jsp" %>

    <script src="<c:url value="/resources/js/menu-operations.js"/>"></script>
    <script src="<c:url value="/resources/js/search-operations.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.contenthover.js"/>"></script>

    <script src="<c:url value="/resources/js/menu-operations.js"/>"></script>
    <link href="<c:url value="/resources/css/component.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/default.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/teskly.viewitle.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/js/modernizr.custom.js"/>"></script>
    <script src="<c:url value="/resources/js/teskly.viewitle.js"/>"></script>
</head>
<body>
<div class="loader-anim"></div>
<%@include file="includes/new-header.jsp" %>
<%--<c:out value="${category}"/>
<c:out value="${it}"/>--%>
<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div>
                    <div style="display: inline-block;">
                        <img class="icons" src="<c:url value="/resources/images/icons/search-result.png"/>">

                        <h3 id="menu-panel-heading" class="header-panel"><fmt:message
                                key="searrchresults.main.panel.header"
                                bundle="${lang}"/></h3>
                    </div>
                    <div style="display: inline-block; float:right;">
                        <div class="form-group row">
                            <div class="col-sm-6" style="padding-right: 0px;">
                                <input type="text" class="form-control input-md" placeholder="<fmt:message key="searchresults.search.placeholder"
                                                                                      bundle="${lang}"/>"
                                       name="txt-search-menu" id="txt-menu-search">
                            </div>
                            <div class="col-sm-4" id="div_source1">
                                <select class="form-control input-md" id="ch_user1">
                                    <%--<option value="select"></option>--%>
                                </select>
                            </div>
                            <script>
                                $.ajax({
                                    type: "GET",
                                    url: "http://localhost:2222/pizza-shefu/api/v1.0/categories/list",
                                    dataType: "json",
                                    success: function (data) {
                                        $.each(data.data, function (i, obj) {
//                                            alert(obj.name + ":" + obj.name);
                                            var div_data = "<option value=" + obj.name + ">" + obj.name + "</option>";
                                            // alert(div_data);
                                            $(div_data).appendTo('#ch_user1');
                                        });
                                    }
                                });
                            </script>
                            <div class="col-sm-2" style="padding-left: 2px;">
                                <button class="btn btn-primary input-md" id="btn-menu-search" type="button"><span><i
                                        class="glyphicon glyphicon-search"></i></span></button>
                            </div>


                        </div>

                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9">
                            <div class="panel-group" id="search-pizza-collapse">
                                <c:choose>
                                    <c:when test="${ empty it}">
                                        <div style="color: #28921f;height: 30px;background-color: #628c10;padding: 6%;margin-bottom: 10px;">
                                            <h1 style="font-size: larger;color: #e6ceac"><c:out
                                                    value="No matching items found ...Surf our menu instead!!!"/></h1>
                                        </div>
                                        <script>
                                            $.notify(" No matching items found...", {
                                                align: "center",
                                                verticalAlign: "top",
                                                delay: 2000,
                                                animationType: "fade",
                                                color: "#fff",
                                                background: "#58b068"
                                            });
                                        </script>

                                    </c:when>
                                    <c:otherwise>
                                        <%--<c:if test="${category=='Pizza'}">--%>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a data-toggle="collapse" href="#search-pizza"><c:out
                                                                value="${category}"/></a>
                                                    </h4>
                                                </div>

                                                <div id="search-pizza" class="panel-collapse collapse in">
                                                    <div class="panel-body">
                                                        <div class="row">

                                                            <c:forEach items="${it}" var="item">

                                                                <div class="col-md-3">
                                                                    <ul class="grid cs-style-3">
                                                                        <li data-teskly-viewitle="<c:out value="${item.description}"/>">
                                                                            <figure>
                                                                                    <%--<img src="<c:url value="/resources/images/image_placeholder.gif"/>"
                                                                                         class="menu-images">--%>
                                                                                <c:set var="itemName"
                                                                                       value="${fn:replace(item.itemName, ' ', '')}"/>
                                                                                <img class="menu-images"
                                                                                     src="${itemImageUrl}/${itemName}.jpg"
                                                                                     width="200px" height="200px">

                                                                                <figcaption class="caption">
                                                                                    <input type="hidden"
                                                                                           id="menu-item-desc"
                                                                                           value="<c:out value="${item.description}"/>"/>

                                                                                    <c:set var="sizePriceLength"
                                                                                           value="${fn:length(item.price)}"/>

                                                                                    <c:if test="${category=='Pizza'}">
                                                                                        <%--<c:when test="${category eq 'Pizza'}">--%>
                                                                                    <c:forEach begin="0"
                                                                                               end="${sizePriceLength-1}"
                                                                                               varStatus="loop">

                                                                                        <c:set var="size"
                                                                                               value="${item.price[loop.index]['size']}"/>
                                                                                        <c:set var="substringSize"
                                                                                               value="${fn:substring(size, 0, 1)}"/>
                                                                                        <c:set var="formattedSize"
                                                                                               value="${fn:toUpperCase(substringSize)}"/>

                                                                                        <c:out value="${formattedSize}"/>:&nbsp;<label id="pizza-price-<c:out
                                                                                            value="${item.price[loop.index]['size']}"/>"><fmt:formatNumber
                                                                                            type="currency"
                                                                                            currencySymbol=""
                                                                                            maxFractionDigits="2"
                                                                                            groupingUsed="false"
                                                                                            value="${item.price[loop.index]['price']}"/>
                                                                                        </label><br>
                                                                                    </c:forEach>

                                                                                    <a class="btn menu-add-to-cart-btn btn-success btn-xs btn-addtocart-pizza"/>
                                                                                    Add
                                                                                    to
                                                                                    cart
                                                                                    </a>
                                                                                    </c:if>
                                                                                    <c:if test="${category!='Pizza'}">
                                                                                        <div class="row"
                                                                                             style="margin-top: 15px;">
                                                                                            <label style="float: left; font-size: small">Price:
                                                                                                <br>(Rs.)</label>
                                                                                            <label id="lbl-sizeprice"
                                                                                                   style="float: left;"><fmt:formatNumber
                                                                                                    type="currency"
                                                                                                    currencySymbol=""
                                                                                                    maxFractionDigits="2"
                                                                                                    groupingUsed="false"
                                                                                                    value="${item.price[0]['price']}"/></label>
                                                                                            <select id="sizes"
                                                                                                    class="sizes-dropdowns"
                                                                                                    style="text-transform: capitalize; font-size: small; float: right; width: 80px; height: 20px">
                                                                                                <c:forEach begin="0"
                                                                                                           end="${sizePriceLength-1}"
                                                                                                           varStatus="loop">
                                                                                                    <option value="<c:out value="${item.price[loop.index]['size']}"/>">
                                                                                                        <c:out value="${item.price[loop.index]['size']}"/></option>
                                                                                                </c:forEach>
                                                                                            </select>
                                                                                            <c:forEach begin="0"
                                                                                                       end="${sizePriceLength-1}"
                                                                                                       varStatus="loop">
                                                                                                <input type="hidden"
                                                                                                       id="hiddenFld-<c:out value="${item.price[loop.index]['size']}"/>"
                                                                                                       value="<fmt:formatNumber type="currency" currencySymbol=""
                                                                                                                  maxFractionDigits="2" groupingUsed="false" value="${item.price[loop.index]['price']}"/>"/>
                                                                                            </c:forEach>

                                                                                        </div>
                                                                                        <div style="float: left;margin-top: 10px;">
                                                                                            <label class="add-to-cart-label-qty">QTY:</label>
                                                                                            <input class="spin add-to-cart-spin"
                                                                                                   type="number"
                                                                                                   min="1" max="100"
                                                                                                   value="1">
                                                                                        </div>
                                                                                        <a class="btn menu-add-to-cart-btn btn-success btn-xs btn-addtocart"
                                                                                           style="float: right;margin-bottom: -15px;">
                                                                                            Add to cart
                                                                                        </a>
                                                                                    </c:if>

                                                                                </figcaption>

                                                                            </figure>
                                                                        </li>
                                                                    </ul>
                                                                    <div class="itemdata-name"
                                                                         style="text-align: center;">
                                                                        <label id="menu-item-name">${item.itemName}</label>
                                                                    </div>
                                                                </div>

                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <%-----------------------------SUGGESTIONS-------------------------------------------------------------------------------%>

                        <div class="col-md-3 panel panel-default">
                            <%-- <h4><fmt:message key="searchresults.suggestions"
                                              bundle="${lang}"/></h4>--%>
                            <%--<div class="panel" style="float: right">--%>
                            <div class="panel-heading">

                                <h4 style="font-size: larger"><fmt:message key="searchresults.suggestions"
                                                                           bundle="${lang}"/></h4>
                            </div>

                            <div id="myCarousel1" class="carousel slide" data-ride="carousel1">
                                <!-- Indicators -->
                                <ol class="carousel-indicators">
                                    <li data-target="#myCarousel1" data-slide-to="0" class="active"></li>
                                    <li data-target="#myCarousel1" data-slide-to="1"></li>
                                    <li data-target="#myCarousel1" data-slide-to="2"></li>
                                    <li data-target="#myCarousel1" data-slide-to="3"></li>
                                </ol>

                                <!-- Wrapper for slides -->
                                <div class="carousel-inner" role="listbox">
                                    <div class="item active">
                                        <img src="<c:url value="/resources/images/beverages/Bergers-Catering-Cold-Beverages-400x600.jpg"/>">
                                    </div>

                                    <div class="item">
                                        <img src="<c:url value="/resources/images/desserts/flourless-chocolate-cake-8-400x600.jpg"/>">
                                    </div>

                                    <div class="item">
                                        <img src="<c:url value="/resources/images/pizzas/c3fc09ff6816d3ec759a68680c61f736.jpg"/>">
                                    </div>

                                    <div class="item">
                                        <img src="<c:url value="/resources/images/desserts/millionaires-shortbread-bars-no-bake-dessert-400x600.jpg"/>">
                                    </div>
                                </div>

                                <!-- Left and right controls -->
                                <a class="left carousel-control" href="#myCarousel1" role="button" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="right carousel-control" href="#myCarousel1" role="button" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>
                        <%------------------------------------------------------------------------------------------------------------%>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</div>
</div>

<!-- Add to cart with customization popup -->
<%@include file="includes/modals.jsp" %>

<a href="#" class="scrollup"></a>
<script>
    $("#btn-menu-search").click(function () {
        var searchKey = $("#txt-menu-search").val();
        var category = $("#ch_user1").val();
        if ($("#txt-menu-search").val().length == 0) {
            $("#txt-menu-search").css("border-color", "red");
            $.notify(" Search field is empty...", {
                align: "center",
                verticalAlign: "top",
                delay: 2000,
                animationType: "fade",
                color: "#fff",
                background: "#D44950"
            });
        } else {
            window.location.href = "http://localhost:8080/web-selfcare/search-menu/" + searchKey + "/" + category;

        }

    });
</script>
<script>
    jQuery(document).viewitle();
</script>
</body>
</html>