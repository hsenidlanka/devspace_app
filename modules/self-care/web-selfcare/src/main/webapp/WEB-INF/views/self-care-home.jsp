<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Pizza Shefu</title>
    <%@include file="include.jsp" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <div class="loader-anim"></div>

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
<%@include file="NewHeader.jsp" %>


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



<%--<div id="modal-login" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content" style="width: 750px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">x</button>
                <p class="modal-title" style="font-size: x-large">Log In</p>
            </div>
            <div class="modal-body">


                <form class="contact_form" action="test" method="post" >

                    <h1 style="font-size: larger;color: #22ca1f">Login Into Your Account!</h1>
                    <ul>
                        <li>
                            &lt;%&ndash;<form:input type="text" class="textbox1" name="email" placeholder="username" path="username"/>&ndash;%&gt;
                                &lt;%&ndash;<form:input path="username"/>&ndash;%&gt;
                            <span class="form_hint">Enter a valid username</span>

                            <p><img src="<c:url value="/resources/images/contact.png"/>" alt=""></p>
                        </li>
                        <li>
                            &lt;%&ndash;<form:input type="password" name="website" class="textbox2" placeholder="password" path="password"/>&ndash;%&gt;

                                &lt;%&ndash;<form:input path="password"/>&ndash;%&gt;
                            <p><img src="<c:url value="/resources/images/lock.png"/>" alt=""></p>
                        </li>
                    </ul>
                    &lt;%&ndash;<input type="submit" name="Sign In" value="Log In"/>&ndash;%&gt;
                    <button>Log In</button>

                    <div class="clear"></div>
                    <label class="checkbox"><input type="checkbox" name="checkbox" checked><i></i>Remember
                    </label>

                    <div class="forgot">
                        <a href="forgotpassword">forgot password?</a>
                    </div>
                    <div class="clear"></div>
                </form>
                <!-- end-form -->





                <!-- start-account -->
                <div class="account">
                    <div class="row">
                        <div class="col-sm-10">
                            <h2><a class="form-group-sm" data-toggle="modal" data-target="#modal-signup"
                                   data-dismiss="modal">Don't have an account?</a></h2>
                        </div>

                    </div>

                    <div class="span"><a href="#"><img src="<c:url value="/resources/images/facebook.png"/>" alt=""/><i>Sign
                        In with
                        Facebook</i>

                        <div class="clear"></div>
                    </a></div>
                    <div class="span1"><a href="#"><img src="<c:url value="/resources/images/twitter.png"/>" alt=""/><i>Sign
                        In with
                        Twitter</i>

                        <div class="clear"></div>
                    </a></div>
                    <div class="span2"><a href="#"><img src="<c:url value="/resources/images/gplus.png"/>" alt=""/><i>Sign
                        In with
                        Google+</i>

                        <div class="clear"></div>
                    </a></div>
                </div>

                <!-- end-account -->
                <div class="clear"></div>

            </div>

        </div>
    </div>
</div>--%>

<!-------Footer------------------------------------->

<div id="footer" style="position: fixed;height: 50px">
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

<!------------------------------signup modal --------------------------------------------->
<%@include file="signupmodal.jsp" %>

</body>
</html>


