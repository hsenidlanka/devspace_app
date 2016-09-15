<div class="modal fade" id="modal-signup" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true"
     style="width: 1200px;height: 700px;">
    <!--<div class="modal-dialog">-->
    <div class="modal-body" style="width: 1500px;height: 700px;">
        <div class="brand"></div>
        <center>
            <div class="inner-bg">
                <div class="container">

                    <div class="panel panel-success">
                        <div class="panel-heading" style="height: 50px">
                            <div class="row">
                                <div class="col-sm-4">
                                    <p style="font-size: xx-large;text-align: left">Create New Account</p>
                                </div>
                                <div class="col-sm-5">
                                </div>
                                <div class="col-sm-3">
                                    <button data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <form role="form" id="adduser_form" action="" method="post"
                                  class="form-horizontal">

                                <div class="form-group">

                                    <label class="col-sm-2 control-label">
                                        Title
                                    </label>

                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="optradio">Mr
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="optradio">Mrs
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="optradio">Miss
                                        </label>
                                    </div>

                                </div>

                                <div class="form-group">

                                    <label class="col-sm-2 control-label">
                                        Name
                                    </label>

                                    <div class="col-sm-4">
                                        <input class="form-control" id="fname" type="text"
                                               placeholder="First Name*" required>
                                    </div>

                                    <div class="col-sm-4">
                                        <input class="form-control" id="lname" type="text" placeholder="Last Name" required>
                                    </div>
                                </div>
                                <div class="form-group">

                                    <label class="col-sm-2 control-label">
                                        Address
                                    </label>

                                    <div class="col-sm-4">
                                        <input class="form-control" id="address1" type="text" placeholder="Line1*"
                                               required>
                                    </div>

                                    <div class="col-sm-4">
                                        <input class="form-control" id="address2" type="text" placeholder="Line2*"
                                               requied>
                                    </div>
                                </div>

                                <div class="form-group">

                                    <label class="col-sm-2 control-label">
                                        City
                                    </label>

                                    <div class="col-sm-2">

                                        <div class="dropdown">

                                            <select class="form-control" required>
                                                <option value="nesrest">select nearest</option>
                                                <option value="col7">Colombo 7</option>
                                                <option value="nug">Nugegoda</option>
                                            </select>
                                        </div>
                                    </div>
                                    <label class="col-sm-4 control-label">
                                        Date Of Birth
                                    </label>

                                    <div class='col-sm-2'>
                                        <input class="form-control" id="datepicker" type="text" placeholder="22/08/2016"
                                               required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="mobileNo" class="col-sm-2 control-label">
                                        Phone No
                                    </label>

                                    <div class="col-sm-4">
                                        <input class="form-control" id="mobileNo"
                                               placeholder="+94-XXXXXXXXXX" required>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        E-Mail
                                    </label>

                                    <div class="col-sm-4">
                                        <input class="form-control" id="email" type="text"
                                               placeholder="example@domain.com" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="username" class="col-sm-2 control-label">
                                        Username
                                    </label>

                                    <div class="col-sm-4">
                                        <input class="form-control" id="username" type="text"
                                               placeholder="username">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">
                                        Password
                                    </label>

                                    <div class="col-sm-4">
                                        <input class="form-control" id="password" type="text"
                                               placeholder="password" required>
                                    </div>

                                    <div class="col-sm-4">
                                        <input class="form-control" id="rePassword" type="text"
                                               placeholder="Re-Enter Password" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success">Create Account</button>
                                    <button type="reset" class="btn btn-warning">Clear</button>

                                </div>
                                <div class="form-group">
                                    <a class="form-group-sm" data-toggle="modal" data-target="#modal-login" data-dismiss="modal">Already Have An Account ?</a>

                                </div>


                            </form>

                        </div>

                    </div>
                </div>
            </div>

        </center>


    </div>
</div>
