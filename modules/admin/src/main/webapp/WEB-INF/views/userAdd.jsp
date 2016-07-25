<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>

  <title><fmt:message key="adduser.title"/></title>

  <!-- CSS -->
<%--  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
  <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
  <link href="css/style.css" rel="stylesheet">
  <link rel="stylesheet" href="css/form-elements.css">--%>


  <!--javascript for user validation and city load-->
<%--  <script src="js/adduser.js"></script>


  <script>

    $("#dataCombo").selectpicker({
      multiple:true
    });
  </script>--%>

  <script>
    function myFunction() {
      document.getElementById("adduser_form").reset();
    }
  </script>
</head>


<body>

<fmt:setLocale value="En"/>
<fmt:bundle basename="messages_en.properties">

  <!-- Top content -->

  <center>

    <div class="form-box">

      <div class="form-top">

        <div class="form-top-left">

          <h3><fmt:message key="adduser.heading"/></h3>
          <%--<p><fmt:message key="adduser.heading.paragraph"/></p>--%>
        </div>

        <div class="form-top-right">
          <i class="fa fa-pencil"></i>
        </div>

      </div>

      <div class="form-bottom">

        <form:form  id="adduser_form" action="saveUser" method="post" modelAttribute="user" >


          <div class="form-group">
            <label class="col-sm-4"><fmt:message key="adduser.form.firstname"/></label>
            <div class="col-sm-8">
              <input type="text" name="form-first-name" placeholder="First name..."
                     class="form-control" id="form-first-name" required>
            </div>
          </div>


          <div class="form-group">
            <label class="col-sm-4 " ><fmt:message key="adduser.form.lastname"/></label>
            <div class="col-sm-8">
              <input type="text" name="form-last-name" placeholder="Last name..."
                     class="form-control" id="form-last-name">
            </div>
          </div>


          <div class="form-group">
            <label for="country" class="col-sm-4 " ><fmt:message key="adduser.form.activity"/></label>
            <div class="col-sm-8">
              <select class="form-control" name="country" id="country" required>
                <option><fmt:message key="adduser.form.select"/></option>
                <option value="enabled">Enabled</option>
                <option value="expired">Expired</option>

              </select>
            </div>
          </div>


          <div class="form-group">
            <label for="form-address" class="col-sm-4 "><fmt:message key="adduser.form.adderss"/></label>
            <div class="col-sm-8">
              <input type="text" name="address" placeholder="Address..." class=" form-control"
                     id="form-address" required/>
            </div>
          </div>


          <div class="form-group">
            <label class="col-sm-4 "><fmt:message key="adduser.form.email"/></label>
            <div class="col-sm-8">
              <input type="text" name="form-email" placeholder="Email..." class="form-email form-control"
                     id="form-email" pattern="^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.
                                   [a-zA-Z0-9-]+)*\.(([0-9]{1,3})|([a-zA-Z]{2,3}))$" required>
            </div>
          </div>


          <div class="form-group">
            <label class="col-sm-4"><fmt:message key="adduser.form.mobile"/></label>
            <div class="col-sm-8">
              <input type="text" name="form-mobile" placeholder="+94xxxxxxxxx"
                     class="form-mobile form-control" id="form-mobile" pattern="^\(?(\+94)\)?([0-9]{9})$"
                     required>
            </div>
          </div>



          <div class="form-group ">
            <label class="col-sm-4 " ><fmt:message key="adduser.form.username"/></label>
            <div class="col-sm-8">
              <input type="text" name="username" placeholder="Username..." class="user_name form-control"
                     id="form-username" required/>

            </div>

          </div>


          <div class="form-group">
            <label class="col-sm-4 " ><fmt:message key="adduser.form.password"/></label>
            <div class="col-sm-8">
              <input type="password" name="password" placeholder="Password..."
                     class="form-password form-control"  id="form-password"
                     onchange=" validatePassword(password)" required></span>
            </div>

          </div>



          <div class="form-group">
            <label  class="col-sm-4 " ><fmt:message key="adduser.form.cpassword"/></label>
            <div class="col-sm-8">
              <input type="password" name="cnpassword" placeholder="Confirm Password..."
                     class="form-password form-control" id="form-password-confirm"
                     onchange=" passwordsEqual(cnpassword,password)" required>
            </div>

          </div>

          <div class="form-group">
            <label class="col-sm-4" ><fmt:message key="adduser.form.creater"/></label>
            <div class="col-sm-8">
              <select class="form-control selectpicker" name="group" id="group"  multiple="multiple" required>
                <option><fmt:message key="adduser.form.select"/></option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <button type="submit" class="btn btn-primary" id="addbtn">
              <fmt:message key="adduser.form.addbutton"/></button>
            <input id="resetbtn" type="button" onclick="myFunction()" value="Reset form" >
          </div>

        </form:form>

      </div>
    </div>
  </center>

</fmt:bundle>
</body>
</html>
