<!DOCTYPE html>
<html>
<head>

    <%@include file="../includes/include.jsp" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <div class="loader-anim"></div>

    <title><fmt:message key="home.title" bundle="${lang}"/></title>
    <script src="<c:url value="/resources/js/validate-bootstrap.jquery.js"/>"></script>

    <style>
        .carousel-inner > .item > img,
        .carousel-inner > .item > a > img {
            width: 58%;
            height: 52%;
            margin: auto;
        }

        #tst {
            color: red;
            font-weight: lighter;
            font-size: smaller;
            text-align: left;
            display: block;
        }

        #modal-login {
            position: absolute;
            left: 50%;
            top: 50%;
            -moz-transform: translate(-50%, -50%);
            -webkit-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }


    </style>





</head>
<!-------Header------------------------------------->
<body>

<%@include file="../includes/new-header.jsp" %>

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
            <img src="<c:url value="/resources/images/53debcce3a030pizza_transparent_png.png"/>" alt="" width="100"
                 height="220">
        </div>

        <div class="item">
            <img src="<c:url value="/resources/images/SeafoodDeluxe.png"/>"
                 alt="" width="100" height="100">
        </div>

        <div class="item">
            <img src="<c:url value="/resources/images/tagliatelle_roasted_squash.png"/>" alt=""
                 width="460" height="300">
        </div>
        <div class="item">
            <img src="<c:url value="/resources/images/Kids_Ice_Cream_Sundae.png"/>"
                 alt="" width="460" height="300">
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
            <a href="payment"> <img src="<c:url value="/resources/images/ordernow3.png"/>" style="width: 185px;height: 148px"></a>
        </span>
    </div>

    <div class="col-sm-2">
        <span>
            <a href="menu"> <img src="<c:url value="/resources/images/ordernow2.png"/>" style="width: 185px;height: 148px"></a>
        </span>
    </div>
    <div class="col-sm-2"></div>


    <div class="col-sm-4" style="padding-top: 80px;padding-left: 260px">
        <span>
            <a href="http://www.facebook.com/"> <img src="<c:url value="/resources/images/facebook-flat.png"/>" style="padding-right: 10px"></a>
        </span>

        <span>
            <a href="http://www.twitter.com"> <img src="<c:url value="/resources/images/twitter-flat.png"/>" style="padding-right: 10px"></a>
        </span>

        <span>
            <a href="#"> <img src="<c:url value="/resources/images/google-plus-flat.png"/>" style="padding-right: 10px"></a>
        </span>
    </div>
</div>

<!-------Footer------------------------------------->

<div id="footer" style="position: fixed;height: 50px">
    <div class="form-group row static">

        <label class="col-sm-4 control-label">
            <div style="height: 22px"></div>

            <p class="text-muted" style="color: #545454"><fmt:message key="footer.text" bundle="${lang}"/> <a href="https://www.hsenidmobile.com/"
                                                                        style="color:#313131 "><fmt:message key="company" bundle="${lang}"/> </a></p>
        </label>

        <div class="col-sm-4">

            <label class="  ">
                <div style="height: 22px"></div>

                <h5 style="color: #313131"><fmt:message key="footer.hotline" bundle="${lang}"/>  <fmt:message key="footer.mobile" bundle="${lang}"/></h5>


            </label>
        </div>
        <div class="col-sm-4">

            <label class="radio-inline">
                <a href="https://play.google.com/store/apps"><img style="width: 130px;height: 55px" src="<c:url value="/resources/images/google-play-store-button.png"/>" ></a>
            </label>
        </div>

    </div>
    <ul class="nav pull-right scroll-top">
        <li><a href="#" title="Scroll to top"><i class="glyphicon glyphicon-chevron-up"></i></a></li>
    </ul>
</div>

</body>
</html>


