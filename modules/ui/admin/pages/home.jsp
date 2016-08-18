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

  <div class="brand">Control Panel</div>
  <div class="address-bar">3481 Pizza Shefu | Ward Place, Colombo 7 | 123.456.7890</div>

  <center>

    <%--panel for Item Management--%>
    <div class="form-box">

      <div class="form-top">
        <div class="form-top-left panel_hh">Item Management</div>
          <div class="form-top-right">
            <i class="fa fa-pencil"></i>
          </div>
      </div>

      <div class="form-bottom">
        <!-- Navigation -->
        <nav class="navbar navbar-default" role="navigation">
        <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="container-fluid" >
            <ul class="nav navbar-nav">
              <li>
                <a href="success.jsp">View Item</a>
              </li>
              <li>
                <a href="about.html">Add Item</a>
              </li>
              <li>
                <a href="blog.html">Edit Item</a>
              </li>
              <li>
                <a href="contact.html">Delete Item</a>
              </li>
            </ul>
          </div>
        </nav>
      </div>

    </div>
  <br>

    <%--panel for Package Management--%>
    <div class="form-box">

      <div class="form-top">
        <div class="form-top-left panel_hh">Package Management</div>
          <div class="form-top-right">
            <i class="fa fa-pencil"></i>
          </div>
      </div>

      <div class="form-bottom">
        <!-- Navigation -->
        <nav class="navbar navbar-default" role="navigation">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="container-fluid" >
            <ul class="nav navbar-nav">
              <li>
                <a href="success.jsp">View Package</a>
              </li>
              <li>
                <a href="about.html">Add Package</a>
              </li>
              <li>
                <a href="blog.html">Edit Package</a>
              </li>
              <li>
                <a href="contact.html">Delete Package</a>
              </li>
            </ul>
          </div>
        </nav>
      </div>

    </div>
  <br>

    <%--panel for Category Management--%>
    <div class="form-box">

      <div class="form-top">
        <div class="form-top-left panel_hh">Category  Management</div>
          <div class="form-top-right">
            <i class="fa fa-pencil"></i>
          </div>
        </div>

      <div class="form-bottom">
        <!-- Navigation -->
        <nav class="navbar navbar-default" role="navigation">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" >
            <ul class="nav navbar-nav">
              <li>
                <a href="success.jsp">Add Category</a>
              </li>
              <li>
                <a href="about.html">View Category</a>
              </li>

            </ul>
          </div>
        </nav>
      </div>

    </div>
  <br>

    <%--panel for User Management--%>
    <div class="form-box">

      <div class="form-top">
        <div class="form-top-left panel_hh">User  Management</div>
          <div class="form-top-right">
            <i class="fa fa-pencil"></i>
          </div>
      </div>

      <div class="form-bottom">
        <!-- Navigation -->
        <nav class="navbar navbar-default" role="navigation">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" >
            <ul class="nav navbar-nav">
              <li>
                <a href="http://localhost:8080/users/userAdd">Add Users</a>
              </li>
              <li>
                <a href="userView.jsp">View Users</a>
              </li>
              <li>
                <a href="about.html">Banned Users</a>
              </li>
            </ul>
          </div>
        </nav>
      </div>

    </div>
  <br>

     <%--panel for Notification Management--%>
    <div class="form-box">

      <div class="form-top">
        <div class="form-top-left panel_hh">View Notifications</div>
          <div class="form-top-right">
            <i class="fa fa-pencil"></i>
          </div>
        </div>
      <div class="form-bottom">
        <!-- Navigation -->
        <nav class="navbar navbar-default" role="navigation">
         <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" >
            <ul class="nav navbar-nav">
              <li>
                <a href="success.jsp">Comments</a>
              </li>
              <li>
                <a href="about.html">Ratings</a>
              </li>
            </ul>
          </div>
        </nav>
      </div>

    </div>
    <br>

  </center>
</body>
</html>