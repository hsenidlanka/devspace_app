<!DOCTYPE html>
<html>
<head>

    <%@include file="../includes/include.jsp" %>
    <div class="loader-anim"></div>

    <title>
        <fmt:message key="home.title" bundle="${lang}"/>
    </title>

    <style>
        .carousel-inner > .item > img,
        .carousel-inner > .item > a > img {
            width: 58%;
            height: 52%;
            margin: auto;
        }

    </style>


</head>

<!-------Header------------------------------------->

<body>

<%@include file="../includes/new-header.jsp" %>
<%--<%@include file="../includes/loginmodal.jsp" %>--%>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators" hidden="hidden">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">

        <div class="item active">
            <img src="<c:url value="/resources/images/carousel1.png"/>">
        </div>

        <div class="item">
            <img src="<c:url value="/resources/images/caraousel2.png"/>">
        </div>

        <div class="item">
            <img src="<c:url value="/resources/images/carousel3.png"/>">
        </div>
        <div class="item">
            <img src="<c:url value="/resources/images/carousel4.png"/>">
        </div>

    </div>

</div>

<!----------------------------order online,surf menu,hot deals--------------->
<div class="row">

    <div class="col-sm-2">
         <span>
             <a href="#"> <img src="<c:url value="/resources/images/hotdeals.png"/>" style="width: 150px;height: 140px"></a>
         </span>
    </div>

    <div class="col-sm-2">
        <span>
            <a href="payment"> <img src="<c:url value="/resources/images/ordernow3.png"/>"
                                    style="width: 185px;height: 148px"></a>
        </span>
    </div>

    <div class="col-sm-2">
        <span>
            <a href="menu"> <img src="<c:url value="/resources/images/ordernow2.png"/>"
                                 style="width: 185px;height: 148px"></a>
        </span>
    </div>
    <div class="col-sm-2"></div>


    <div class="col-sm-4" style="padding-top: 80px;padding-left: 260px">
        <span>
            <a href="http://www.facebook.com/" target="_blank"> <img
                    src="<c:url value="/resources/images/facebook-flat.png"/>"
                    style="padding-right: 10px"></a>
        </span>

        <span>
            <a href="http://www.twitter.com" target="_blank"> <img
                    src="<c:url value="/resources/images/twitter-flat.png"/>"
                    style="padding-right: 10px"></a>
        </span>

        <span>
            <a href="https://plus.google.com/" target="_blank"> <img
                    src="<c:url value="/resources/images/google-plus-flat.png"/>" style="padding-right: 10px"></a>
        </span>
    </div>
</div>

<!-------Footer------------------------------------->

<div id="footer" style="position: fixed;height: 50px">
    <div class="form-group row static">

        <label class="col-sm-4 control-label">
            <div style="height: 22px"></div>

            <p class="text-muted" style="color: #545454"><fmt:message key="footer.text" bundle="${lang}"/> <a
                    href="https://www.hsenidmobile.com/"
                    style="color:#313131 "><fmt:message key="company" bundle="${lang}"/> </a></p>
        </label>

        <div class="col-sm-4">
            <label class="  ">
                <div style="height: 22px"></div>
                <h5 style="color: #313131"><fmt:message key="footer.hotline" bundle="${lang}"/> <fmt:message
                        key="footer.mobile" bundle="${lang}"/></h5>
            </label>
        </div>
        <div class="col-sm-4">

            <label class="radio-inline">
                <a href="https://play.google.com/store/apps" target="_blank"><img style="width: 130px;height: 55px"
                                                                                  src="<c:url value="/resources/images/google-play-store-button.png"/>"></a>
            </label>
        </div>

    </div>
    <%--<ul class="nav pull-right scroll-top">
        <li><a href="#" title="Scroll to top"><i class="glyphicon glyphicon-chevron-up"></i></a></li>
    </ul>--%>
</div>

</body>
</html>


