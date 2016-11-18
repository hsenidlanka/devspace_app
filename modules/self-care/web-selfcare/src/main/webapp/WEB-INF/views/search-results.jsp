<!DOCTYPE html>
<html>
<head lang="en">
    <title>Search Results</title>
    <link rel="shortcut icon" href="">

    <%@include file="includes/include.jsp" %>

    <script src="<c:url value="/resources/js/menu-operations.js"/>"></script>
    <script src="<c:url value="/resources/js/search-operations.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.contenthover.js"/>"></script>
</head>
<body>
<div class="loader-anim"></div>
<%@include file="includes/new-header.jsp" %>
<%--<c:out value="${test}"/>
<c:out value="${it}"/>--%>
<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div>
                    <div style="display: inline-block;">
                        <img class="icons" src="<c:url value="/resources/images/icons/search-result.png"/>">

                        <h3 id="menu-panel-heading" class="header-panel">Search Results</h3>
                    </div>
                    <div style="display: inline-block; float:right;">
                        <div class="form-group">
                            <div class="col-sm-10" style="padding-right: 0px;">
                                <input type="text" class="form-control input-sm" placeholder="Search"
                                       name="txt-search-menu" id="txt-menu-search">
                            </div>
                            <div class="col-sm-2" style="padding-left: 2px;">
                                <button class="btn btn-primary input-sm" id="btn-menu-search" type="button"><span><i
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
                                        <div style="color: #28921f;height: 30px;background-color: #628c10;padding: 6%;margin-bottom: 10px">
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
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" href="#search-pizza">Pizza</a>
                                                </h4>
                                            </div>

                                            <div id="search-pizza" class="panel-collapse collapse in">
                                                <div class="panel-body">
                                                    <div class="row">
                                                            <%--<c:if test="${ empty it}">
                                                                <div style="color: #28921f;height: 40px;background-color: #628c10;padding: 6%">
                                                                    <h1 style="font-size: larger;color: #e6ceac"><c:out value="No matching items found ...Surf our menu instead!!!"/></h1>
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

                                                            </c:if>--%>
                                                        <c:forEach items="${it}" var="item">
                                                            <c:choose>
                                                                <c:when test="${item.categoryName=='Pizza'}">

                                                                    <div style="margin-top: 15px;">

                                                                        <div class="col-md-3">
                                                                            <img src="<c:url value="/resources/images/pizzas/22.png"/>"
                                                                                 class="img-responsive menu-images">

                                                                            <div class="contenthover">
                                                                                <h3><c:out
                                                                                        value="${item.itemName}"/></h3>


                                                                                <c:out value="${item.description}"/>
                                                                                <div>
                                                                                    <button class="btn btn-success btn-xs btn-addtocart-pizza">
                                                                                        Add
                                                                                        to
                                                                                        cart
                                                                                    </button>
                                                                                </div>
                                                                            </div>

                                                                        </div>

                                                                    </div>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" href="#search-pizza2">Beverage</a>
                                                </h4>
                                            </div>

                                            <div id="search-pizza2" class="panel-collapse collapse in">
                                                <div class="panel-body">
                                                    <div class="row">

                                                        <c:forEach items="${it}" var="item">
                                                            <c:choose>
                                                                <c:when test="${item.categoryName=='Beverage'}">

                                                                    <div style="margin-top: 15px;">

                                                                        <div class="col-md-3">
                                                                            <img src="<c:url value="/resources/images/beverages/coffee%2004.jpg"/>"
                                                                                 class="img-responsive menu-images">

                                                                            <div class="contenthover">
                                                                                <h3><c:out
                                                                                        value="${item.itemName}"/></h3>


                                                                                <c:out value="${item.description}"/>
                                                                                <div>
                                                                                    <button class="btn btn-success btn-xs btn-addtocart-pizza">
                                                                                        Add
                                                                                        to
                                                                                        cart
                                                                                    </button>
                                                                                </div>
                                                                            </div>

                                                                        </div>

                                                                    </div>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" href="#search-pizza3">Topping</a>
                                                </h4>
                                            </div>

                                            <div id="search-pizza3" class="panel-collapse collapse in">
                                                <div class="panel-body">
                                                    <div class="row">

                                                        <c:forEach items="${it}" var="item">
                                                            <c:choose>
                                                                <c:when test="${item.categoryName=='Topping'}">

                                                                    <div style="margin-top: 15px;">

                                                                        <div class="col-md-3">
                                                                            <img src="<c:url value="/resources/images/desserts/pudding%2001.jpg"/>"
                                                                                 class="img-responsive menu-images">

                                                                            <div class="contenthover">
                                                                                <h3><c:out
                                                                                        value="${item.itemName}"/></h3>


                                                                                <c:out value="${item.description}"/>
                                                                                <div>
                                                                                    <button class="btn btn-success btn-xs btn-addtocart-pizza">
                                                                                        Add
                                                                                        to
                                                                                        cart
                                                                                    </button>
                                                                                </div>
                                                                            </div>

                                                                        </div>

                                                                    </div>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" href="#search-pizza4">Desserts</a>
                                                </h4>
                                            </div>

                                            <div id="search-pizza4" class="panel-collapse collapse in">
                                                <div class="panel-body">
                                                    <div class="row">

                                                        <c:forEach items="${it}" var="item">
                                                            <c:choose>
                                                                <c:when test="${item.categoryName=='Desserts'}">

                                                                    <div style="margin-top: 15px;">

                                                                        <div class="col-md-3">
                                                                            <img src="<c:url value="/resources/images/desserts/pudding%2001.jpg"/>"
                                                                                 class="img-responsive menu-images">

                                                                            <div class="contenthover">
                                                                                <h3><c:out
                                                                                        value="${item.itemName}"/></h3>


                                                                                <c:out value="${item.description}"/>
                                                                                <div>
                                                                                    <button class="btn btn-success btn-xs btn-addtocart-pizza">
                                                                                        Add
                                                                                        to
                                                                                        cart
                                                                                    </button>
                                                                                </div>
                                                                            </div>

                                                                        </div>

                                                                    </div>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" href="#search-pizza5">Salad</a>
                                                </h4>
                                            </div>

                                            <div id="search-pizza5" class="panel-collapse collapse in">
                                                <div class="panel-body">
                                                    <div class="row">

                                                        <c:forEach items="${it}" var="item">
                                                            <c:choose>
                                                                <c:when test="${item.categoryName=='Salad'}">

                                                                    <div style="margin-top: 15px;">

                                                                        <div class="col-md-3">
                                                                            <img src="<c:url value="/resources/images/desserts/salads%2001.jpg"/>"
                                                                                 class="img-responsive menu-images">

                                                                            <div class="contenthover">
                                                                                <h3><c:out
                                                                                        value="${item.itemName}"/></h3>


                                                                                <c:out value="${item.description}"/>
                                                                                <div>
                                                                                    <button class="btn btn-success btn-xs btn-addtocart-pizza">
                                                                                        Add
                                                                                        to
                                                                                        cart
                                                                                    </button>
                                                                                </div>
                                                                            </div>

                                                                        </div>

                                                                    </div>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>


                        <div class="col-md-3">
                            <h4>Sugessions for you...</h4>

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
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>

<!-- Add to cart with customization popup -->
<div id="add-to-cart-popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Customize and Add to Cart</h4>
            </div>
            <div class="modal-body">
                <p>Customize the following item as you wish and click add to cart to add
                    the item to cart.</p>

                <div class="thumbnail" style="height:120px; border: none;">
                    <img src="<c:url value="/resources/images/Whole-Pizza-100x100.jpg"/>"
                         class="img-responsive" align="left">

                    <div class="caption">
                        <h4>&nbsp; Item Title</h4>

                        <p>&nbsp; Some description about the item.</p>
                    </div>
                </div>
                <form role="form" style="margin-top:-20px;">
                    <div class="form-group">
                        <div class="col-sm-6" style="display:inline-block;">
                            <label class="control-label">Crust</label>
                            <select class="form-control">
                                <option>Sausage</option>
                                <option>Topping 02</option>
                            </select>
                        </div>
                        <div class="col-sm-6" style="display:inline-block;">
                            <label class="control-label">Size</label>
                            <select class="form-control">
                                <option>Small</option>
                                <option>Medium</option>
                                <option>Large</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="display:inline-block; margin-top:10px; margin-left:15px;">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox">Extra Cheese (Rs.150.00)</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-6" style="display:inline-block; margin-top:-10px;">
                            <label class="control-label">Toppings</label>
                            <select class="form-control">
                                <option>Topping 01</option>
                                <option>Topping 02</option>
                                <option>Topping 03</option>
                            </select>
                        </div>
                        <div class="col-sm-6" style="display:inline-block; margin-top:-10px;">
                            <label class="control-label">&nbsp;</label>
                            <select class="form-control">
                                <option>Topping 01</option>
                                <option>Topping 02</option>
                                <option>Topping 03</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="display:inline-block; margin-top:10px; margin-left:15px;">
                        <label class="control-label">Special Instructions</label>
                        <textarea class="form-control" cols="35"
                                  placeholder="Mention any special instructions"></textarea>
                    </div>
                    <div class="form-group" style="margin-left:15px;">
                        <label class="control-label" style="padding-right:15px;">Quantity:</label>
                        <input class="spin" type="number" min="1" max="100" value="1">
                        <label class="control-label"
                               style="padding-left:30px; padding-right:10px;">Price:&nbsp;&nbsp;</label>
                        <input type="text" disabled="" style="width:10em">
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="margin-top:-20px;">
                <a class="btn btn-default" data-dismiss="modal">Cancel</a>
                <button class="btn btn-success btn-popup-add-to-cart-pizza">Add to Cart</button>
            </div>
        </div>
    </div>
</div>

<a href="#" class="scrollup"></a>
<script>
    $("#btn-menu-search").click(function () {
        var searchKey = $("#txt-menu-search").val();
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
            window.location.href = "http://localhost:8080/web-selfcare/search-menu/" + searchKey;

        }

    });


</script>
</body>
</html>