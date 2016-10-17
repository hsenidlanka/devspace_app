<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../signupmodal.jsp" %>


<nav class="navbar navbar-default ">
  <div class="container-fluid">
    <div class="col-sm-4"><a href="/web-selfcare/home"> <img src="<c:url value="/resources/images/logo.png"/>" style="width:75px;height:65px;"></a></div>

    <div style="padding: 10px"></div>
    <div>

    </div>
    <div class="col-sm-8">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="menu"><fmt:message key="new.header.menu" bundle="${lang}"/></a></li>
        <li><a href="locations"><fmt:message key="new.header.location" bundle="${lang}"/></a></li>
        <li><a href="createfeedback"><fmt:message key="new.header.feedback" bundle="${lang}"/></a></li>
        <li><a href="contactus"><fmt:message key="new.header.contact" bundle="${lang}"/></a></li>
        <li><a href="aboutus"><fmt:message key="new.header.about" bundle="${lang}"/></a></li>
        <li>

          <a href="shopping-cart"><img src="<c:url value="/resources/images/shopping-cart.png"/>"
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
        <center><p class="modal-title" style="font-size: x-large"><h1 style="font-size: larger;color: #22ca1f"><fmt:message key="new.header.loginto" bundle="${lang}"/></h1></p></center>
      </div>

      <div class="modal-body" >
          <form name="loginForm" id="loginForm" novalidate="novalidate" action="ltest" method="post">
              <div class="row form-group">
                  <div class="col-sm-3">
                      <h3><label for="username">Username</label></h3>
                  </div>
                  <div class="">
                      <input id="username" class="form-control" min="3" name="username" required="" type="text" data-error-msg="Username required !!!">
                  </div>

              </div>


              <div class="row  form-group">
                  <label for="password">Password</label>
                  <input id="password" name="password" class="form-control" min="3" required="" data-error-msg="Please provide Valid password !!!"
                         type="password">
              </div>

              <div class="row col-sm-offset-4">
                  <button id="btn-submit" type="submit" class="btn btn-primary btn-block">Submit</button>
              </div>


          </form>
<%--
<center>
          <form novalidate="novalidate" action="login" method="post">
              <div class="row form-group form-inline">
                  <label class="control-label col-sm-3" for="name">
                      Name
                  </label>
                  <input  class="col-sm-6" id="username" name="username" type="text" required/>
                  <span class="col-sm-3"></span>
              </div>

              <div class="row form-group form-inline">
                  <label class="control-label col-sm-3" for="password" >
                      Password
                  </label>

                  <input class="col-sm-6" id="password" name="password" type="password" required/>
                  <span class="col-sm-3"></span>

              </div>
              <input type="submit" name="Sign In" value="<fmt:message key="new.header.login" bundle="${lang}"/>"/>
          </form>

</center>
--%>

        <%--<form class="contact_form" action="login" method="post" name="contact_form">
            <div class="row  form-group">
                <label for="uname">Username</label>
                <input type="text" class="textbox1" id="uname" name="username" placeholder="<fmt:message key="signup.username" bundle="${lang}"/>"/>

            </div>



                    <label></label>

                    <p><img src="<c:url value="/resources/images/contact.png"/>" alt=""></p>



                    <input type="password" name="password" class="textbox2"
                           placeholder="<fmt:message key="signup.password" bundle="${lang}"/>">

                   <p><img src="<c:url value="/resources/images/lock.png"/>" alt=""></p>

          <input type="submit" name="Sign In" value="<fmt:message key="new.header.login" bundle="${lang}"/>"/>

          &lt;%&ndash;<div class="clear"></div>&ndash;%&gt;



        </form>



          <label class="checkbox"><input type="checkbox" name="checkbox" checked><i></i><fmt:message key="new.header.remember" bundle="${lang}"/>
          </label>
          <div class="forgot">
              <a href="forgotpassword"><fmt:message key="new.header.forget" bundle="${lang}"/></a>
          </div>
          <div class="col-sm-10">
              <h2><a class="form-group-sm" data-toggle="modal" data-target="#modal-signup" data-dismiss="modal"><fmt:message key="new.header.dont.have" bundle="${lang}"/></a></h2>
          </div>

        --%><!-- end-form -->

       <%-- <!-- start-account -->
        <div class="account">
          <div class="row">


          </div>

          &lt;%&ndash;<div class="span"><a href="#"><img src="<c:url value="/resources/images/facebook.png"/>" alt=""/><i><fmt:message key="new.header.facebook" bundle="${lang}"/></i>&ndash;%&gt;

            <div class="clear"></div>
          </a></div>
          &lt;%&ndash;<div class="span1"><a href="#"><img src="<c:url value="/resources/images/twitter.png"/>" alt=""/><fmt:message key="new.header.twitter" bundle="${lang}"/><i></i>&ndash;%&gt;

            <div class="clear"></div>
          </a></div>
          &lt;%&ndash;<div class="span2"><a href="#"><img src="<c:url value="/resources/images/gplus.png"/>" alt=""/><i><fmt:message key="new.header.google" bundle="${lang}"/></i>&ndash;%&gt;

            <div class="clear"></div>
          </a></div>
        </div>

        <!-- end-account -->--%>
        <div class="clear"></div>

      </div>

    </div>
  </div>
</div>
