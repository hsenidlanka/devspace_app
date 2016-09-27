<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default ">
  <div class="container-fluid">
    <div class="col-sm-4"><a href="home"> <img src="<c:url value="/resources/images/logo.png"/>" style="width:75px;height:65px;"></a></div>

    <div style="padding: 10px"></div>
    <div>

    </div>
    <div class="col-sm-8">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="menu">Menu</a></li>
        <li><a href="locations">Locations</a></li>
        <li><a href="createfeedback">Feedbacks</a></li>
        <li><a href="contactus">Contact Us</a></li>
        <li><a href="aboutus">About us</a></li>
        <li>

          <a href="shopping-cart.html"><img src="<c:url value="/resources/images/shopping-cart.png"/>"
                                            style="padding-left: 30px"></a>
        </li>
        <li>
          <a class="form-group" data-toggle="modal" data-target="#modal-signup"><img
                  src="<c:url value="/resources/images/signup.png"/>" style=""></span>
          </a>
        </li>

        <li>

          <a class="form-group-sm" data-toggle="modal" data-target="#modal-login"><img src="<c:url value="/resources/images/user2.png"/>" style=""></span> </a>

        </li>
      </ul>
    </div>
  </div>

</nav>

<!------------------------------login modal--------------------------->

<div id="modal-login" class="modal fade">
  <div class="modal-dialog modal-lg"  >
    <div class="modal-content" style="width: 750px">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" >x</button>
        <p class="modal-title" style="font-size: x-large">Log In</p>
      </div>
      <div class="modal-body" >
        <!--<div w3-include-html="loginmodaloptional.html"></div>-->
        <form class="contact_form" action="login" method="post" name="contact_form">

          <h1 style="font-size: larger;color: #22ca1f">Login Into Your Account!</h1>
          <ul>
            <li>
              <input type="text" class="textbox1" name="username" placeholder="username" required/>
              <span class="form_hint">Enter a valid username</span>

              <p><img src="<c:url value="/resources/images/contact.png"/>" alt=""></p>
            </li>
            <li>
              <input type="password" name="password" class="textbox2" placeholder="password">

              <p><img src="<c:url value="/resources/images/lock.png"/>" alt=""></p>
            </li>
          </ul>
          <input type="submit" name="Sign In" value="Log In"/>

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
              <h2><a class="form-group-sm" data-toggle="modal" data-target="#modal-signup" data-dismiss="modal">Don' have an account?</a></h2>
            </div>

          </div>

          <div class="span"><a href="#"><img src="<c:url value="/resources/images/facebook.png"/>" alt=""/><i>Sign In with
            Facebook</i>

            <div class="clear"></div>
          </a></div>
          <div class="span1"><a href="#"><img src="<c:url value="/resources/images/twitter.png"/>" alt=""/><i>Sign In with
            Twitter</i>

            <div class="clear"></div>
          </a></div>
          <div class="span2"><a href="#"><img src="<c:url value="/resources/images/gplus.png"/>" alt=""/><i>Sign In with
            Google+</i>

            <div class="clear"></div>
          </a></div>
        </div>

        <!-- end-account -->
        <div class="clear"></div>

      </div>

    </div>
  </div>
</div>