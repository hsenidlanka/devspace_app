<!DOCTYPE html>
<html>
<head>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link href="../bootstrap-3.3.7/css/bootstrap.min_spacelab.css" rel="stylesheet"
          integrity="sha384-L/tgI3wSsbb3f/nW9V6Yqlaw3Gj7mpE56LWrhew/c8MIhAYWZ/FNirA64AVkB5pI" crossorigin="anonymous">
    <link href="../css/homeStyle.css" rel="stylesheet" type="text/css">
    <link href="../css/footer.css" rel="stylesheet" type="text/css">
    <link href="../css/style2.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="../css/loader.css" rel="stylesheet">
    <div class="loader-anim"></div>

    <style>
        .carousel-inner > .item > img,
        .carousel-inner > .item > a > img {
            width: 58%;
            height: 52%;
            margin: auto;
        }
    </style>
    <!--<div w3-include-html="header.html"></div>-->
    <script src="../js/w3data.js"></script>
    <script>
        w3IncludeHTML();
    </script>
</head>
<!-------Header------------------------------------->
<body>
<c:url var="imageUrl" value="../images/bg.png">
    <img src="${imageUrl}">
<nav class="navbar navbar-default ">
    <div class="container-fluid">
        <div class="col-sm-4"><a href="self-care-home.html"> <img
                src="../images/logo.png"
                style="width:75px;height:65px;"></a></div>

        <div style="padding: 10px"></div>
        <div>

        </div>
        <div class="col-sm-8">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="menu.html">Menu</a></li>
                <li><a href="../pages/locations.html">Locations</a></li>
                <li><a href="../pages/createfeedback.html">Feedbacks</a></li>
                <li><a href="contactus.html">Contact Us</a></li>
                <li><a href="aboutus.html">About us</a></li>
                <li>

                    <a href="shopping-cart.html"><img src="../images/shopping-cart.png"
                                                      style="padding-left: 30px"></span>
                    </a>
                </li>
                <li>
                    <a class="form-group" data-toggle="modal" data-target="#modal-signup"><img
                            src="../images/signup.png" style=""></span>
                    </a>
                </li>

                <li>
                    <a class="form-group-sm" data-toggle="modal" data-target="#modal-login"><img
                            src="../images/user2.png" style=""></span>
                    </a>


                </li>
            </ul>
        </div>
    </div>

</nav>

<!------------------------------------------------------------------------->
<!--------------Body------------------------->

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
            <img src="<c:url value="../images/53debcce3a030pizza_transparent_png.png"/>" alt="" width="100"
                 height="220">
        </div>

        <div class="item">
            <img src="../images/SeafoodDeluxe.png"
                 alt="" width="100" height="100">
        </div>

        <div class="item">
            <img src="../images/tagliatelle_roasted_squash.png" alt=""
                 width="460" height="300">
        </div>
        <div class="item">
            <img src="../images/Kids_Ice_Cream_Sundae.png?mh=367"
                 alt="" width="460" height="300">
        </div>


    </div>

</div>

<!----------------------------order online,surf menu,hot deals--------------->
<div class="row">

    <div class="col-sm-2">
         <span>
                      <a href="#"> <img src="../images/hotdeals.png"
                                        style="width: 150px;height: 140px"></a>
                    </span>
    </div>
    <div class="col-sm-2">
                    <span>
                       <a href="../pages/payment.html"> <img src="../images/ordernow3.png"
                                                             style="width: 185px;height: 148px">
                       </a>
                    </span>
    </div>
    <div class="col-sm-2">
                    <span>
                       <a href="../pages/menu.html"> <img src="../images/ordernow2.png"
                                                          style="width: 185px;height: 148px">
                       </a>
                    </span>
    </div>
    <div class="col-sm-2"></div>


    <div class="col-sm-4" style="padding-top: 80px;padding-left: 260px">
                    <span>
                        <a href="#"> <img src="../images/facebook-flat.png" style="padding-right: 10px"></a>
                    </span>
        <span>
            <a href="#"> <img src="../images/twitter-flat.png" style="padding-right: 10px"></a>
        </span>
        <span>
            <a href="#"> <img src="../images/google-plus-flat.png" style="padding-right: 10px"></a>
        </span>
    </div>
</div>
<!------------------------------login modal--------------------------->

<div id="modal-login" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content" style="width: 750px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">x</button>
                <p class="modal-title" style="font-size: x-large">Log In</p>
            </div>
            <div class="modal-body">
                <!--<div w3-include-html="loginmodaloptional.html"></div>-->
                <form class="contact_form" action="#" method="post" name="contact_form">

                    <h1 style="font-size: larger;color: #22ca1f">Login Into Your Account!</h1>
                    <ul>
                        <li>
                            <input type="text" class="textbox1" name="email" placeholder="username" required/>
                            <span class="form_hint">Enter a valid username</span>

                            <p><img src="../images/contact.png" alt=""></p>
                        </li>
                        <li>
                            <input type="password" name="website" class="textbox2" placeholder="password">

                            <p><img src="../images/lock.png" alt=""></p>
                        </li>
                    </ul>
                    <input type="submit" name="Sign In" value="Log In"/>

                    <div class="clear"></div>
                    <label class="checkbox"><input type="checkbox" name="checkbox" checked><i></i>Remember
                    </label>

                    <div class="forgot">
                        <a href="../pages/forgotpassword.html">forgot password?</a>
                    </div>
                    <div class="clear"></div>
                </form>
                <!-- end-form -->

                <!-- start-account -->
                <div class="account">
                    <div class="row">
                        <div class="col-sm-10">
                            <h2><a class="form-group-sm" data-toggle="modal" data-target="#modal-signup"
                                   data-dismiss="modal">Don' have an account?</a></h2>
                        </div>

                    </div>

                    <div class="span"><a href="#"><img src="../images/facebook.png" alt=""/><i>Sign In with
                        Facebook</i>

                        <div class="clear"></div>
                    </a></div>
                    <div class="span1"><a href="#"><img src="../images/twitter.png" alt=""/><i>Sign In with
                        Twitter</i>

                        <div class="clear"></div>
                    </a></div>
                    <div class="span2"><a href="#"><img src="../images/gplus.png" alt=""/><i>Sign In with
                        Google+</i>

                        <div class="clear"></div>
                    </a></div>
                </div>

                <!-- end-account -->
                <div class="clear"></div>


                <script src="../js/w3data.js"></script>
                <script>
                    w3IncludeHTML();
                </script>
            </div>

        </div>
    </div>
</div>

<!-------Footer------------------------------------->

<div id="footer" style="position: fixed;height: 50px">
    <!--<div style="height: 22px"></div>-->
    <div class="form-group row static">

        <label class="col-sm-4 control-label">
            <div style="height: 22px"></div>

            <p class="text-muted" style="color: #545454">@Powered by <a href="https://www.hsenidmobile.com/"
                                                                        style="color:#313131 ">hSenid </a></p>
        </label>

        <div class="col-sm-4">

            <label class="  ">
                <div style="height: 22px"></div>

                <h5 style="color: #313131">Hotline : (+94)7676576765</h5>


            </label>
        </div>
        <div class="col-sm-4">

            <label class="radio-inline">
                <img style="width: 130px;height: 55px" src="http://www.moppadop.com/img/google-play-store-button.png">
            </label>
        </div>

    </div>
    <ul class="nav pull-right scroll-top">
        <li><a href="#" title="Scroll to top"><i class="glyphicon glyphicon-chevron-up"></i></a></li>
    </ul>
</div>

<script src="../js/w3data.js"></script>

</body>

<!------------------------------signup modal --------------------------------------------->

<div w3-include-html="signupmodal.html"></div>
</html>
<script>
    w3IncludeHTML();
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/loader.js"></script>
