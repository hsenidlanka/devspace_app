
<form class="contact_form" action="#" method="post" name="contact_form">

    <h1 style="font-size: larger;color: #22ca1f">Login Into Your Account!</h1>
    <ul>
        <li>
            <input type="text" class="textbox1" name="email" placeholder="username" required/>
            <span class="form_hint">Enter a valid username</span>

            <p><img src="<c:url value="/resources/images/contact.png"/>" alt=""></p>
        </li>
        <li>
            <input type="password" name="website" class="textbox2" placeholder="password">

            <p><img src="<c:url value="/resources/images/contact.png"/>" "../images/lock.png" alt=""></p>
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
            <h2><a class="form-group-sm" data-toggle="modal" data-target="#modal-signup" data-dismiss="modal">Don't have an account?</a></h2>
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






