<%-- Start-Pizza carousel --%>
<img src="<c:url value="/resources/images/icons/favorite.png"/>" width="30px"
     height="30px"/>
<h4 style="display:inline-block;">Top rated pizzas</h4>

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
            <img src="<c:url value="/resources/images/pizzas/pizza1-nota.jpg"/>">
        </div>

        <div class="item">
            <img src="<c:url value="/resources/images/pizzas/Margherita-Pizza-With-Salami-2-400x600.png"/>">
        </div>

        <div class="item">
            <img src="<c:url value="/resources/images/pizzas/c3fc09ff6816d3ec759a68680c61f736.jpg"/>">
        </div>

        <div class="item">
            <img src="<c:url value="/resources/images/pizzas/pizza_diavolo.jpg"/>">
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
<%-- End-Pizza carousel --%>