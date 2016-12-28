<div class="modal fade" id="modal-signup" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true" style="width: 77%; margin-left: 5%;" data-keyboard="false" data-backdrop="static">

    <%--<button id="testId">Test</button>--%>
    <div class="modal-body" style="width: 1500px;height: 700px;">
        <div class="brand"></div>
        <center>
            <div class="inner-bg">
                <div class="container">

                    <div class="panel panel-success">
                        <div class="panel-heading" style="height: 50px">
                            <div class="row">
                                <div class="col-sm-6">
                                    <p style="font-size: xx-large;text-align: left"><fmt:message key="signup.create.new"
                                                                                                 bundle="${lang}"/></p>
                                </div>
                                <div class="col-sm-4">
                                    <a  type="button" class="btn" data-dismiss="modal" style="margin-left: 35px; color:#888"><i class="fa fa-window-close fa-2x" aria-hidden="true"></i></a>
                                    <%--<button data-dismiss="modal" aria-hidden="true"><i class="fa fa-window-close" aria-hidden="true"></i></button>--%>
                                </div>
                                <div class="col-sm-2"></div>
                            </div>
                        </div>

                        <%--         Add user form      --%>

                        <div class="panel-body">
                            <form novalidate="novalidate" role="form" id="adduser_form" method="post"
                                  name="adduser_form" action="#" class="form form-horizontal">

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-2"></div>
                                        <div id="formSubmitError" class="error-labels col-sm-10"></div>
                                        <%--<div class="col-sm-4"></div>--%>
                                    </div>
                                    <label class="col-sm-2 control-label">
                                        <fmt:message key="signup.title" bundle="${lang}"/>
                                    </label>


                                    <div class="col-sm-5">
                                        <label class="radio-inline">
                                            <input type="radio" name="title" value="Mr"><fmt:message key="signup.mr" bundle="${lang}"/>
                                        </label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label class="radio-inline">
                                            <input type="radio" name="title" value="Mrs"><fmt:message key="signup.mrs" bundle="${lang}"/>
                                        </label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label class="radio-inline">
                                            <input type="radio" name="title" value="Miss"><fmt:message key="signup.miss" bundle="${lang}"/>
                                        </label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label class="radio-inline">
                                            <input type="radio" name="title" value="Dr"><fmt:message key="signup.dr" bundle="${lang}"/>
                                        </label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label class="radio-inline">
                                            <input type="radio" name="title" value="Rev"><fmt:message key="signup.rev" bundle="${lang}"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="titleError" class="error-labels col-sm-10"></div>
                                    <%--<div class="col-sm-4"></div>--%>
                                </div>


                                <div class="form-group">

                                    <label class="col-sm-2 control-label">
                                        <fmt:message key="signup.first.name" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" id="fname" name="fname" type="text" placeholder="<fmt:message key="signup.first.name" bundle="${lang}"/>*">
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="fnameError" class="error-labels col-sm-10"></div>
                                </div>


                                <div class="form-group">
                                    <label for="lname" class="col-sm-2 control-label">
                                        <fmt:message key="signup.last.name" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" id="lname" name="lname" type="text" placeholder="<fmt:message key="signup.last.name" bundle="${lang}"/>*">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="lnameError" class="error-labels col-sm-10"></div>
                                </div>
                                <%----%>

                                <div class="form-group">

                                    <label class="col-sm-2 control-label">
                                        <fmt:message key="signup.address" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input name="address1" id="address1" class="form-control" type="text" placeholder="<fmt:message key="signup.addr1" bundle="${lang}"/>">

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="address1Error" class="error-labels col-sm-10"></div>
                                </div>
                                <%----%>
                                <div class="form-group">
                                    <label for="address2" class="col-sm-2 control-label">
                                        &nbsp;
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" name="address2" id="address2" type="text" placeholder="<fmt:message key="signup.addr2" bundle="${lang}"/>">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="address2Error" class="error-labels col-sm-10"></div>
                                </div>

                                <%----%>
                                <div class="form-group">
                                    <label for="address3" class="col-sm-2 control-label">
                                        &nbsp;
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" id="address3" name="address3" type="text" placeholder="<fmt:message key="signup.addr3" bundle="${lang}"/>">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="address3Error" class="error-labels col-sm-10"></div>
                                </div>

                                <div class="form-group">
                                    <label for="mobileNo" class="col-sm-2 control-label">
                                        <fmt:message key="signup.phone" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="mobileno form-control" id="mobileNo" name="mobileNo" type="text" placeholder="0-XXXXXXXXXX*">
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="mobileNoError" class="error-labels col-sm-10"></div>
                                </div>

                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        <fmt:message key="signup.email" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" id="email" name="email" type="email" placeholder="<fmt:message key="signup.email.example" bundle="${lang}"/>*">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="emailError" class="error-labels col-sm-10"></div>
                                </div>

                                <div class="form-group" id="usernameblock">
                                    <label for="uname" class="col-sm-2 control-label">
                                        <fmt:message key="signup.username" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="username form-control" id="uname" name="uname" type="text" placeholder="<fmt:message key="signup.username" bundle="${lang}"/>*">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="unameError" class="error-labels col-sm-10"></div>
                                </div>

                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">
                                        <fmt:message key="signup.password" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="password form-control" id="password" name="password" type="password" placeholder="<fmt:message key="signup.password" bundle="${lang}"/>*">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="passwordError" class="error-labels col-sm-10"></div>
                                </div>

                                <div class="form-group">
                                    <label for="rePassword" class="col-sm-2 control-label">
                                        <fmt:message key="sign.confirm.password" bundle="${lang}"/>
                                    </label>

                                    <div class="col-sm-5">
                                        <input class="form-control" name="rePassword" id="rePassword" type="password" placeholder="<fmt:message key="signup.password.re" bundle="${lang}"/>*">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2"></div>
                                    <div id="rePasswordError" class="error-labels col-sm-10"></div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2"></div>
                                    <div class="col-sm-4">
                                        <button id="submitBtn" name="submitBtn" class="btn btn-success"><fmt:message key="signup.create.account" bundle="${lang}"/></button>

                                        <button type="reset" class="btn btn-success"><fmt:message key="signup.clear" bundle="${lang}"/></button>
                                    </div>
                                    <div class="col-sm-6"></div>

                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2"></div>
                                    <div class="col-sm-4"><a class="form-group-sm" data-toggle="modal"
                                                             data-target="#modal-login"
                                                             data-dismiss="modal"><fmt:message key="signup.already"
                                                                                               bundle="${lang}"/></a>
                                    </div>
                                    <div class="col-sm-6"></div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </center>


    </div>
</div>
