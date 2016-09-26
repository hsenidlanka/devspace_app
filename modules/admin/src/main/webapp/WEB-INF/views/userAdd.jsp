<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
  <%--<spring:url value="/themes/hsenid/js/jquery.toaster.js" var="js1"/>--%>
  <spring:url value="/themes/hsenid/js/userMgt.js" var="js2"/>
  <%--<spring:url value="/themes/hsenid/js/simpleToastMessage.js" var="js3"/>--%>


  <%--<script src="${js1}"></script>--%>
  <script src="${js2}"></script>
  <%--<script src="${js3}"></script>--%>

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
      <li><a href="https://localhost:8443/admin/users/list">Home</a></li>
      <li><a href="https://localhost:8443/admin/users/list">User Management</a></li>
      <li class="active"><a href="https://localhost:8443/admin/users/add">Add User</a></li>
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
        <form:form role="form" id="admin_adduser_form"  method="POST" class="form-horizontal" action="/users/addCustomer">

          <fieldset class="scheduler-border">
            <legend class="scheduler-border" id="legendHeading"></legend>

            <div class="form-group">
              <div class="col-sm-6">
                <div class="row">
                  <form:label path="user" class="col-sm-4 control-label">
                    User Type
                  </form:label>
                  <div class="col-sm-3">
                    <form:label path="customer" class="radio-inline">
                      <form:input path="customerin" type="radio" name="optradio" id="customer" checked="checked"/>Customer
                    </form:label>
                  </div>
                  <div class="col-sm-4">
                    <form:label path="staff" class="radio-inline">
                      <form:input path="staffin" type="radio" name="optradio"  id="staff"/>Staff Member
                    </form:label>
                  </div>
                </div>
              </div>
            </div>

            <div class="form-group">
              <div class="col-sm-6">
                <div class="row">
                  <form:label path="name"  class="col-sm-4 control-label">
                    Name
                  </form:label>
                  <div class="col-sm-2">
                    <form:select path="title" class="form-control" id="selectTitle" style="width: 65px">
                      <form:option value="mr">Mr</form:option>
                      <form:option value="mrs">Mrs</form:option>
                      <form:option value="miss">Miss</form:option>
                    </form:select>
                  </div>
                  <div class="col-sm-4">
                    <form:input path="fnamein" class="form-control" id="fname" placeholder="First Name" type="text"  style="width: 190px"/>
                  </div>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="row">
                  <div class="col-sm-4">
                    <form:input path="lnamein" class="form-control" id="lname" placeholder="Last Name" type="text"  style="width: 200px"/>
                  </div>
                </div>
              </div>
            </div> <br>

            <div class="form-group">
              <div class="col-sm-6">
                <div class="row">
                  <form:label path="phone"  class="col-sm-4 control-label">
                    Phone No*
                  </form:label>
                  <div class="col-sm-8">
                    <form:input path="phonein" class="form-control" id="mobileNo" placeholder="+94XXXXXXXXX" type="text"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-6">
                <div class="row">
                  <form:label path="email" class="col-sm-4 control-label">
                    E-Mail
                  </form:label>
                  <div class="col-sm-8">
                    <form:input path="emailin" class="form-control" id="email" placeholder="myname@example.com" type="text"/>
                  </div>
                </div>
              </div>
            </div>

            <div class="form-group">
              <!--address-->
              <div class="col-sm-6">
                <br>
                <div class="row">
                  <form:label path="add1" class="col-sm-4 control-label">
                    Address
                  </form:label>
                  <div class="col-sm-8">
                    <form:input path="add1in" class="form-control" id="addLine1" placeholder="Street Address" type="text"/> >
                  </div>
                </div>
                <div class="row"> <label class="control-label"></label>   </div>
                <div class="row">
                  <form:label path="add2" class="col-sm-4 control-label">
                  </form:label>
                  <div class="col-sm-8">
                    <form:input path="add2in" class="form-control" id="addLine2" placeholder="Address Line 2" type="text" />
                  </div>
                </div>
                <div class="row"><label class="control-label"></label></div>
                <div class="row">
                  <form:label path="city" class="col-sm-4 control-label">
                  </form:label>
                  <div class="col-sm-8">
                    <form:input path="cityin" class="form-control" id="city" placeholder="City" type="text"/>
                  </div>
                </div>
                <div class="row"><label class="control-label"></label></div>
                <div class="row">
                  <form:label path="province" class="col-sm-4 control-label">
                  </form:label>
                  <div class="col-sm-8">
                    <form:input path="provincein" class="form-control" id="province" placeholder="Province" type="text"/>
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
                    <form:label path="designation" class="col-sm-4 control-label">
                      Designation
                    </form:label>
                    <div class="col-sm-8">
                      <form:select path="designationin" class="form-control" id="designation">
                        <form:option value="sysAdmin">System Admin</form:option>
                        <form:option value="ccManager">CustomerCare Manager</form:option>
                        <form:option value="adminManager">Admin Manager</form:option>
                      </form:select>
                    </div>
                  </div>
                  <div class="row">  <label class=" control-label"></label></div>
                  <div class="row">
                    <form:label path="department" class="col-sm-4 control-label">
                      Department
                    </form:label>
                    <div class="col-sm-8">
                      <form:select path="depin" class="form-control" id="department">
                        <form:option value="finance">Finance</form:option>
                        <form:option value="administration">Administration</form:option>
                        <form:option value="cc">Customer Service</form:option>
                      </form:select>
                    </div>
                  </div>
                  <div class="row">  <label class=" control-label"></label></div>
                  <div class="row">
                    <form:label path="branch" class="col-sm-4 control-label">
                      Branch
                    </form:label>
                    <div class="col-sm-8">
                      <form:select path="branchin" class="form-control" id="branch">
                        <form:option value="1">Colombo</form:option>
                        <form:option value="2">Gampaha</form:option>
                        <form:option value="3">Ja-Ela</form:option>
                        <form:option value="4">Kadana</form:option>
                      </form:select>
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
                    <form:label path="uname" class="col-xs-4 control-label" style="text-align: left">
                      User Name
                    </form:label>
                    <form:label path="password" class="col-xs-4 control-label" style="text-align: left">
                      Password
                    </form:label>
                    <form:label path="cpassword" class="col-xs-4 control-label" style="text-align: left">
                      Confirm Password
                    </form:label>
                  </div>
                  <div class="row">
                    <div class="col-xs-4">
                      <form:input path="unamein" class="form-control" id="username" placeholder="User Name" type="text"/> >
                    </div>
                    <div class="col-xs-4">
                      <form:input path="passwordin" class="form-control" id="password" placeholder="Password" type="text" />
                    </div>
                    <div class="col-xs-4">
                      <form:input path="cpasswordin" class="form-control" id="cpassword" placeholder="Password Confirm" type="text" />
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
                  <form:button type="reset" value="Reset" class="btn btn-success btnAddItem">Reset</form:button>
                </div>
                <div class="col-xs-4">
                  <form:button type="submit" class="btn btn-success btnAddItem" >Add User</form:button>
                </div>
              </div>
              <div class="col-xs-1"></div>
            </div>
          </fieldset>
        </form:form>
      </div>
    </div>
  </div>
</center>



</body>
</html>
