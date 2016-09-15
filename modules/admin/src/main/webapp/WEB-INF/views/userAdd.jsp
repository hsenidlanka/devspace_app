<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <!--include common CSS, fonts and js-->
  <jsp:include page="adminTop.jsp"/>

  <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
  <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>

  <link href="${css1}" rel="stylesheet">
  <link href="${css2}" rel="stylesheet">

  <!--include the jQuery toaster plugin's script-->
  <spring:url value="/themes/hsenid/js/jquery.toaster.js" var="js1"/>
  <spring:url value="/themes/hsenid/js/userMgt.js" var="js2"/>
  <spring:url value="/themes/hsenid/js/simpleToastMessage.js" var="js3"/>


  <script src="${js1}"></script>
  <script src="${js2}"></script>
  <script src="${js3}"></script>

</head>
<body>

<jsp:include page="header.jsp"/>



<div class="brand">
  <div style="position: relative; left: -50%;">
    User Management
  </div>
</div>
<br>
<br>
<div class="breadcrumbPosition">
  <div style="position: relative; left: -50%;">
    <ul class="breadcrumb breadcrumb-menu" >
      <li><a href="home.html">Home</a></li>
      <li><a href="userAdd.html">User Management</a></li>
      <li class="active"><a href="userAdd.html">Add User</a></li>
    </ul>
  </div>
</div>

<center>
  <div class="form-box">
    <div class="panel panel-default">

      <div class="panel-heading common-form-headings">
        <h3 class="default-panel-headings ">Add New User</h3>
      </div>
      <div class="panel-body">
        <form role="form" id="admin_adduser_form"  method="post" class="form-horizontal">

          <fieldset class="scheduler-border">
            <legend class="scheduler-border" id="legendHeading"></legend>

            <div class="form-group">
              <div class="col-sm-6">
                <div class="row">
                  <label class="col-sm-4 control-label">
                    User Type
                  </label>
                  <div class="col-sm-3">
                    <label class="radio-inline">
                      <input type="radio" name="optradio" id="customer" checked>Customer
                    </label>
                  </div>
                  <div class="col-sm-4">
                    <label class="radio-inline">
                      <input type="radio" name="optradio"  id="staff">Staff Member
                    </label>
                  </div>
                </div>
              </div>
            </div>

            <div class="form-group">
              <div class="col-sm-6">
                <div class="row">
                  <label  class="col-sm-4 control-label">
                    Name
                  </label>

                  <div class="col-sm-2">
                    <select class="form-control" id="selectTitle" style="width: 65px">
                      <option>Mr</option>
                      <option>Mrs</option>
                      <option>Miss</option>
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <input class="form-control" id="fname" placeholder="First Name" type="text"  style="width: 190px">
                  </div>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="row">
                  <div class="col-sm-4">
                    <input class="form-control" id="lname" placeholder="Last Name" type="text"  style="width: 200px">
                  </div>
                </div>

              </div>
            </div> <br>

            <div class="form-group">
              <div class="col-sm-6">
                <div class="row">
                  <label  class="col-sm-4 control-label">
                    Phone No*
                  </label>
                  <div class="col-sm-8">
                    <input class="form-control" id="mobileNo" placeholder="+94XXXXXXXXX" type="text">
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-6">
                <div class="row">
                  <label class="col-sm-4 control-label">
                    E-Mail
                  </label>
                  <div class="col-sm-8">
                    <input class="form-control" id="email" placeholder="myname@example.com" type="text">
                  </div>
                </div>
              </div>
            </div>

            <div class="form-group">
              <!--address-->
              <div class="col-sm-6">
                <br>
                <div class="row">
                  <label class="col-sm-4 control-label">
                    Address
                  </label>
                  <div class="col-sm-8">
                    <input class="form-control" id="addLine1" placeholder="Street Address" type="text" >
                  </div>
                </div>
                <div class="row"> <label class="control-label"></label>   </div>
                <div class="row">
                  <label class="col-sm-4 control-label">
                  </label>
                  <div class="col-sm-8">
                    <input class="form-control" id="addLine2" placeholder="Address Line 2" type="text" >
                  </div>
                </div>
                <div class="row"><label class="control-label"></label></div>
                <div class="row">
                  <label class="col-sm-4 control-label">
                  </label>
                  <div class="col-sm-8">
                    <input class="form-control" id="city" placeholder="City" type="text">
                  </div>
                </div>
                <div class="row"><label class="control-label"></label></div>
                <div class="row">
                  <label class="col-sm-4 control-label">
                  </label>
                  <div class="col-sm-8">
                    <input class="form-control" id="province" placeholder="Province" type="text">
                  </div>
                </div>
                <div class="row"><label class="control-label"></label></div>
              </div>

              <!--when the user is a customer-->
              <div class="col-sm-6">
                <br>
                <fieldset class="scheduler-border" id="staffDetail">
                  <legend class="scheduler-border" id="legendStaff">when the user is staff</legend>
                  <div class="row">
                    <label class="col-sm-4 control-label">
                      Designation
                    </label>
                    <div class="col-sm-8">
                      <select class="form-control" id="designation">
                        <option>System Admin</option>
                        <option>CustomerCare Manager</option>
                        <option>Miss</option>
                      </select>
                    </div>
                  </div>
                  <div class="row">  <label class=" control-label"></label></div>
                  <div class="row">
                    <label class="col-sm-4 control-label">
                      Department
                    </label>
                    <div class="col-sm-8">
                      <select class="form-control" id="department">
                        <option>Finance</option>
                        <option>Administration</option>
                        <option>Customer Service</option>
                      </select>
                    </div>
                  </div>
                  <div class="row">  <label class=" control-label"></label></div>
                  <div class="row">
                    <label class="col-sm-4 control-label">
                      Branch
                    </label>
                    <div class="col-sm-8">
                      <select class="form-control" id="branch">
                        <option>Colombo</option>
                        <option>Gampaha</option>
                        <option>Ja-Ela</option>
                        <option>Kadana</option>
                      </select>
                    </div>
                  </div>
                </fieldset>
              </div>
            </div>

            <!--login details-->
            <div class="form-group">
              <!--<div class="col-xs-1"></div>-->
              <div class="col-xs-12">
                <fieldset class="scheduler-border" id="loginInfo">
                  <legend class="scheduler-border" id="legendInfo">Login Credentials</legend>
                  <div class="row">
                    <label class="col-xs-4 control-label" style="text-align: left">
                      User Name
                    </label>
                    <label class="col-xs-4 control-label" style="text-align: left">
                      Password
                    </label>
                    <label class="col-xs-4 control-label" style="text-align: left">
                      Confirm Password
                    </label>
                  </div>
                  <div class="row">
                    <div class="col-xs-4">
                      <input class="form-control" id="username" placeholder="User Name" type="text" >
                    </div>
                    <div class="col-xs-4">
                      <input class="form-control" id="password" placeholder="Password" type="text" >
                    </div>
                    <div class="col-xs-4">
                      <input class="form-control" id="cpassword" placeholder="Password Confirm" type="text" >
                    </div>
                  </div>.
                </fieldset>
              </div>
              <!--<div class="col-xs-1"></div>-->

            </div>
            <!--<div id="toaster"></div>-->
            <div class="form-group">
              <div class="col-xs-3"></div>
              <div class="col-xs-8">
                <div class="col-xs-4">
                  <button type="reset" value="Reset" class="btn btn-success btnAddItem">Reset</button>
                </div>
                <div class="col-xs-4">
                  <button type="button" class="btn btn-success btnAddItem" id="btnAddUser"
                          onclick="successToast('You have successfully added the user')">Add User</button>
                </div>
              </div>
              <div class="col-xs-1"></div>
            </div>
          </fieldset>
        </form>
      </div>
    </div>
  </div>
</center>



</body>
</html>
