<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>

  <!-- CSS -->
  <spring:url value="/themes/hsenid/bootstrap/css/bootstrap.min.css" var="css1"/>
  <%--<spring:url value="/themes/hsenid/bootstrap/css/bootstrap.css" var="css2"/>--%>
  <spring:url value="/themes/hsenid/css/business-casual.css" var="css3"/>

  <link href="${css1}" rel="stylesheet">
  <%--<link href="${css2}" rel="stylesheet">--%>
  <link href="${css3}" rel="stylesheet">

  <!-- Javascript -->
  <%--
    <spring:url value="/themes/hsenid/bootstrap/js/jquery.min.js" var="js1"/>
    <spring:url value="/themes/hsenid/bootstrap/js/bootstrap.min.js" var="js2"/>
    <script src="${js1}"></script>
    <script src="${js2}"></script>
  --%>

  <!-- Fonts -->
  <spring:url value="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic
  ,400,300,600,700,800" var="css4"/>

  <spring:url value="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" var="css5"/>
  <link href="${css4}" rel="stylesheet" type="text/css">
  <link href="${css5}" rel="stylesheet" type="text/css">


</head>
<body>




<div class="brand">User Page</div>


<center>
  <div class="form-box">

    <div class="form-top">

      <div class="form-top-left">

        <h3>New User Sign up</h3>
        <p>Fill in the form below to add a new user:</p>
      </div>

      <div class="form-top-right">
        <i class="fa fa-pencil"></i>
      </div>

    </div>

    <div class="form-bottom">

      <form role="form" id="adduser_form" action="AddUserServlet" method="post" class="form-horizontal" >


        <div class="form-group">
          <label class="col-sm-4">First name *</label>
          <div class="col-sm-8">
            <input type="text" name="form-first-name" placeholder="First name..."
                   class="form-control" id="form-first-name" required>


          </div>
        </div>


        <div class="form-group">
          <label class="col-sm-4 " >Last name</label>
          <div class="col-sm-8">
            <input type="text" name="form-last-name" placeholder="Last name..."
                   class="form-control" id="form-last-name">
          </div>
        </div>


        <div class="form-group">
          <label for="date" class="col-sm-4 " >  Date of Birth *:</label>
          <div class="col-sm-8">
            <input type="text" name="date" class="form-control" id="date" required="">
          </div>
        </div>



        <div class="form-group">
          <label for="country" class="col-sm-4 " >Country *</label>
          <div class="col-sm-8">
            <select class="form-control" name="country" id="country" required>
              <option>--Select--</option>
              <option value="Sri Lanka">Sri Lanka</option>
              <option value="India">India</option>
              <option value="Japan">Japan</option>
              <option value="Australia">Australia</option>
            </select>

          </div>
        </div>




        <div class="form-group">
          <label for="form-city" class="col-sm-4 ">City*</label>
          <div class="col-sm-8">
            <select class="form-control" id="form-city" name="form-city" required>
              <option>--Select--</option>
            </select>
          </div>

        </div>




        <div class="form-group">
          <label class="col-sm-4 ">Email* (eg:xxx@gmail.com)</label>
          <div class="col-sm-8">
            <input type="text" name="form-email" placeholder="Email..." class="form-email form-control"
                   id="form-email" pattern="^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.
                                   [a-zA-Z0-9-]+)*\.(([0-9]{1,3})|([a-zA-Z]{2,3}))$" required>
          </div>
        </div>



        <div class="form-group">
          <label class="col-sm-4">Mobile* (+94xxxxxxxxx)</label>
          <div class="col-sm-8">
            <input type="text" name="form-mobile" placeholder="94xxxxxxxxx"
                   class="form-mobile form-control" id="form-mobile" pattern="^\(?(\+94)\)?([0-9]{9})$"
                   required>
          </div>
        </div>



        <div class="form-group ">
          <label class="col-sm-4 " >Username *</label>
          <div class="col-sm-8">
            <input type="text" name="username" placeholder="Username..." class="user_name form-control"
                   id="form-username" required/>

          </div>

        </div>


        <div class="form-group">
          <label class="col-sm-4 " >Password *</label>
          <div class="col-sm-8">
            <input type="password" name="password" placeholder="Password..."
                   class="form-password form-control"  id="form-password"
                   onchange=" validatePassword(password)" required></span>
          </div>

        </div>



        <div class="form-group">
          <label  class="col-sm-4 " >Confirm pw *</label>
          <div class="col-sm-8">
            <input type="password" name="cnpassword" placeholder="Confirm Password..."
                   class="form-password form-control" id="form-password-confirm"
                   onchange=" passwordsEqual(cnpassword,password)" required>
          </div>

        </div>

        <div class="form-group">
          <label for="group" class="col-sm-4" >Country *</label>
          <div class="col-sm-8">
            <select class="form-control" name="group" id="group" required>
              <option>--Select--</option>
              <option value="Administrator">Administrator</option>
              <option value="Customer Care">Customer Care</option>
              <option value="Translater">Translater</option>

            </select>

          </div>
        </div>


        <div class="form-group">

          <button type="submit" class="btn" id="addbtn">Add User</button>

        </div>



      </form>

    </div>
  </div>
</center>



</body>
</html>