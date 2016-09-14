<nav class="navbar navbar  navbar-custom" style="color: #e32c6d;">
    <div class="container-fluid">
        <div class="col-sm-8" style="height: 50px"><a href="home"><img src="<c:url value="/resources/images/logo.png"/>" style="width:90px;height:90px;"></a>
        </div>
        <div class="col-sm-4">
            <ul class="nav navbar-nav navbar-right">
                <div style="padding: 10px">
                    <li>

                        <a href="shopping-cart"><img src="<c:url value="/resources/images/shopping-cart.png"/>"
                                                          style="padding-right: 10px"></span>
                        </a>

                        <a class="form-group" data-toggle="modal" data-target="#modal-signup"><img
                                src="<c:url value="/resources/images/signup.png"/>" style="padding-right: 10px"></span>
                        </a>
                        <a class="form-group-sm" data-toggle="modal" data-target="#modal-login"><img
                                src="<c:url value="/resources/images/user2.png"/>" style="padding-right: 10px"></span>
                        </a>


                    </li>
                </div>
            </ul>
        </div>
        <div class="navbar-header">

        </div>
        <div style="padding: 10px"></div>
        <div>

        </div>
        <ul class="nav navbar-nav navbar-right" style="padding-right: 30px;">
            <li><a href="menu.html">Menu</a></li>
            <li><a href="locations">Locations</a></li>
            <li><a href="createfeedback">Feedbacks</a></li>
            <li><a href="contactus">Contact Us</a></li>
            <li><a href="aboutus">About us</a></li>
        </ul>
    </div>

</nav>

<!--------------------------------login modal----------------------------->
<div id="modal-login" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content" style="width: 750px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">x</button>
                <p class="modal-title" style="font-size: x-large">Log In</p>
            </div>
            <div class="modal-body">
                <%@include file="loginmodaloptional.jsp" %>
            </div>
        </div>
    </div>
</div>
<!------------------------------signup modal --------------------------------------------->

<%@include file="signupmodal.jsp" %>


<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>" />

<!-----------------Datepicker scripts----------------------------------->
<script src="<c:url value="/resources/js/jquery-1.9.1.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-ui.js"/>" </script>
<script>
    $(document).ready(
            /* This is the function that will get executed after the DOM is fully loaded */
            function () {
                $("#datepicker").datepicker({
                    changeMonth: true,//this option for allowing user to select month
                    changeYear: true //this option for allowing user to select from year range
                });
            }
    );
</script>
<style>
     .transparent  {
        border-width: 0.5px;
        -webkit-box-shadow: 0px 0px;
        box-shadow: 0px 0px;
        background-color: rgb(253, 253, 255);
        background-image: -webkit-gradient(linear, 50.00% 0.00%, 50.00% 100.00%, color-stop(0%, rgba(119, 50, 42, 0.90)), color-stop(100%, rgba(248, 123, 159, 0.76)));
        background-image: -webkit-linear-gradient(270deg, rgba(223, 229, 231, 0.84) 0%, rgba(0, 0, 0, 0.44) 100%);
        background-image: linear-gradient(180deg, rgba(104, 119, 118, 0.44) 0%, rgba(207, 210, 231, 0.71) 100%);

    }


</style>

