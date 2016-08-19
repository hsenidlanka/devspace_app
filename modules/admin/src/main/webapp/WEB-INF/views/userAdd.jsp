<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>

  <!-- CSS -->
  <spring:url value="/themes/hsenid/bootstrap/css/bootstrap.min.css" var="css1"/>
  <%--<spring:url value="/themes/hsenid/bootstrap/css/bootstrap.css" var="css2"/>--%>
  <spring:url value="/themes/hsenid/css/business-casual.css" var="css3"/>
  <spring:url value="/themes/hsenid/css/form-elements.css" var="css4"/>

  <link href="${css1}" rel="stylesheet">
  <%--<link href="${css2}" rel="stylesheet">--%>
  <link href="${css3}" rel="stylesheet">
  <link href="${css4}" rel="stylesheet">


  <!-- Javascript -->
  <spring:url value="/themes/hsenid/bootstrap/js/jquery.min.js" var="js1"/>
  <spring:url value="/themes/hsenid/bootstrap/js/bootstrap.min.js" var="js2"/>
  <script src="${js1}"></script>
  <script src="${js2}"></script>

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
    <div class="panel panel-default">
      <div class="panel-heading">
          <h3 id="panel-heading-title">New User Sign up</h3>
      </div>
      <div class="panel-body">
        <form role="form" id="adduser_form" action="AddUserServlet" method="post" class="form-horizontal" >

          <div class="form-group">
            <label class="col-sm-8">Name *</label>
            <label class="col-sm-4">User Type *</label>

            <div class="col-sm-4">
              <input type="text" name="form-first-name" placeholder="First name..."
                     class="form-control" id="form-first-name" required>
            </div>
            <div class="col-sm-4">
              <input type="text" name="form-second-name" placeholder="Second name..."
                     class="form-control" id="form-second-name" required>
            </div>
            <div class="col-sm-2">
              <label><input type="radio" name="optradio">Option 1</label>
            </div>
            <div class="col-sm-2">
              <label><input type="radio" name="optradio">Option 2</label>
            </div>
          </div>
          <br>

          <div class="form-group">
            <label class="col-sm-4 " >Mobile Number *</label>
            <label class="col-sm-4 " >Phone Number</label>
            <label class="col-sm-4 " >Birth Date *</label>

            <div class="col-sm-4">
              <input type="text" name="form-mobile" placeholder="94xxxxxxxxx"
                     class="form-control" id="form-mobile" pattern="^\(?(\+94)\)?([0-9]{9})$" required="">
            </div>
            <div class="col-sm-4">
              <input type="text" name="form-phone" placeholder="94xxxxxxxxx"
                     class="form-control" id="form-phone" pattern="^\(?(\+94)\)?([0-9]{9})$">
            </div>
            <div class="col-sm-4">
              <input type="text" name="date" class="form-control" id="date" required="">
            </div>
          </div>


          <div class="form-group">
            <div class="col-sm-6">

            </div>
            <div class="col-sm-6">

            </div>
          </div>
      </form>

      </div>

    </div>
  </div>
</center>



</body>
</html>