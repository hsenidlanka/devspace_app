<div class="modal fade" id="modal-signup" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true"
     style="width: 1200px;height: 700px;">

    <!--<div class="modal-dialog">-->
    <%--<fmt:message key="footer.hotline" bundle="${lang}"/>--%>

    <div class="modal-body" style="width: 1500px;height: 700px;">
        <div class="brand"></div>
        <center>
            <div class="inner-bg">
                <div class="container">

                    <div class="panel panel-success">
                        <div class="panel-heading" style="height: 50px">
                            <div class="row">
                                <div class="col-sm-4">
                                    <p style="font-size: xx-large;text-align: left"><fmt:message key="signup.create.new" bundle="${lang}"/></p>
                                </div>
                                <div class="col-sm-5">
                                </div>
                                <div class="col-sm-3">
                                    <button data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                            </div>
                        </div>

                        <%--         Add user form      --%>

                        <div class="panel-body">
                            <form novalidate="novalidate" role="form" id="adduser_form" method="post" name="adduser_form" action="#" class="form form-horizontal">

                                <div class="form-group">

                                    <label class="col-sm-2 control-label">
                                        <fmt:message key="signup.title" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <label class="radio-inline">
                                            <input type="radio" required="" name="title" data-error-msg="Select the Title" value="Mr."><fmt:message key="signup.mr" bundle="${lang}" />
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" required="" name="title" data-error-msg="Select the Title" value="Mrs."><fmt:message key="signup.mrs" bundle="${lang}"/>
                                        </label>
                                        <label class="radio-inline" >
                                            <input type="radio" required="" name="title" data-error-msg="Select the Title" value="Miss"><fmt:message key="signup.miss" bundle="${lang}"/>
                                        </label>
                                    </div>

                                </div>



                                <div class="form-group">

                                    <label class="col-sm-2 control-label">
                                        <fmt:message key="signup.name" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" id="fname" name="fname" type="text"
                                               placeholder="<fmt:message key="signup.first.name" bundle="${lang}"/>" required="" data-error-msg="Enter First Name">
                                    </div>

                                    <%--<div class="col-sm-4">
                                        <input class="form-control" id="lname" name="lname" type="text" placeholder="<fmt:message key="signup.last.name" bundle="${lang}"/>" required="" data-error-msg="Enter Valid Last Name" >
                                    </div>--%>
                                </div>

                                <%----%>

                                <div class="form-group">
                                    <label for="lname" class="col-sm-2 control-label">
                                        Last Name
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" id="lname" name="lname" type="text" placeholder="<fmt:message key="signup.last.name" bundle="${lang}"/>" required="" data-error-msg="Enter Last Name" >
                                    </div>
                                </div>

                                <%----%>

                                <div class="form-group">

                                    <label class="col-sm-2 control-label">
                                        <fmt:message key="signup.address" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input name="address1" id="address1" class="form-control" type="text" placeholder="<fmt:message key="signup.addr1" bundle="${lang}"/>" required>

                                        <%--<input class="form-control" required="" id="address1" name="address1" type="text" placeholder="<fmt:message key="signup.addr1" bundle="${lang}"/>" >--%>
                                    </div>
<%--
                                    <div class="col-sm-4">
                                        <input class="form-control" name="address2" id="address2" type="text" placeholder="<fmt:message key="signup.addr2" bundle="${lang}"/>" required>
                                        <input class="form-control" id="address2" name="address2" type="text" placeholder="<fmt:message key="signup.addr2" bundle="${lang}"/>" requied="" >
                                    </div>--%>
                                </div>

                                <%----%>
                                <div class="form-group">
                                    <label for="address2" class="col-sm-2 control-label">
                                        &nbsp;
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" name="address2" id="address2" type="text" placeholder="<fmt:message key="signup.addr2" bundle="${lang}"/>" required>
                                    </div>
                                </div>


                                <%----%>
                                <div class="form-group">
                                    <label for="address3" class="col-sm-2 control-label">
                                        &nbsp;
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" id="address3" name="address3" type="text"
                                               placeholder="<fmt:message key="signup.addr3" bundle="${lang}"/>" >
                                    </div>
                                </div>

                                    <div class="form-group">
                                        <label for="mobileNo" class="col-sm-2 control-label">
                                            <fmt:message key="signup.phone" bundle="${lang}"/>
                                        </label>

                                        <div class="col-sm-5">
                                            <input class="mobileno form-control" id="mobileNo" name="mobileNo" type="text"
                                                   placeholder="0-XXXXXXXXXX" pattern="/^0[0-9]{9}$/" required="" data-error-msg="Enter Mobile No">
                                        </div>

                                    </div>

                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        <fmt:message key="signup.email" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" id="email" name="email" type="email"
                                               placeholder="<fmt:message key="signup.email.example" bundle="${lang}"/>" required="" data-error-msg="Enter Email">
                                    </div>
                                </div>
                                <div class="form-group" id="usernameblock">
                                    <label for="username" class="col-sm-2 control-label">
                                        <fmt:message key="signup.username" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="username form-control" id="username" name="username" type="text" required="" data-error-msg="Enter an Username"
                                               placeholder="<fmt:message key="signup.username" bundle="${lang}"/>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">
                                        <fmt:message key="signup.password" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="password form-control" id="password" name="password" type="password"
                                               placeholder="<fmt:message key="signup.password" bundle="${lang}"/>" required="" data-error-msg="Passwords should match!!!">
                                    </div>


                                </div>

                                <%----%>


                                <div class="form-group">
                                    <label for="rePassword" class="col-sm-2 control-label">
                                        Confirm Password
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" name="rePassword" id="rePassword" type="password"
                                               placeholder="<fmt:message key="signup.password.re" bundle="${lang}"/>" >
                                    </div>
                                </div>


                                <%----%>
                                <div class="form-group">
                                    <button id="submitBtn" name="submitBtn" class="btn btn-success"><fmt:message key="signup.create.account" bundle="${lang}"/> </button>
                                    <button type="reset" class="btn btn-warning"><fmt:message key="signup.clear" bundle="${lang}"/></button>

                                </div>
                                <div class="form-group">
                                    <a class="form-group-sm" data-toggle="modal" data-target="#modal-login"
                                       data-dismiss="modal"><fmt:message key="signup.already" bundle="${lang}"/></a>

                                </div>


                            </form>

                        </div>

                    </div>
                </div>
            </div>

        </center>


    </div>
</div>
