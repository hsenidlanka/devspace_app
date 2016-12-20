<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title>View Profile</title>
    <style>
        .navbar-fixed-left {
            width: 200px;
            position: fixed;
            border-radius: 0;
            height: 100%;
            background: #EAEAEA;
            color: red;
        }

        .navbar-fixed-left .navbar-nav > li {
            float: none;  /* Cancel default li float: left */
            width: 139px;
        }

        .navbar-fixed-left + .container {
            padding-left: 160px;
        }

        /* On using dropdown menu (To right shift popuped) */
        .navbar-fixed-left .navbar-nav > li > .dropdown-menu {
            margin-top: -50px;
            margin-left: 140px;
        }
        .leftsidemenu{
            margin-top: -20px;
        }
        .navbar-brand {
            color: #666666;
        }

        .navbar-nav>li>a {
            color: #777777 !important;
        }
    </style>


    <script>

        $( document ).ready(function() {
            load_home()
        });

        function load_home() {
            document.getElementById("content").innerHTML='<object type="text/html" data="profile-info" style="width:105%; height: 80%;"></object>';
        }
    </script>
</head>
<body>
<%@include file="../includes/new-header.jsp" %>

<div class="navbar navbar-inverse navbar-fixed-left leftsidemenu" style="height: 100%">
    <%--<a class="navbar-brand" href="#">Brand</a>--%>
    <ul class="nav navbar-nav">
        <li><a href="#">Link2</a></li>
        <li><a href="#">Link3</a></li>
        <li><a href="#">Link4</a></li>
        <li><a href="#">Link5</a></li>
    </ul>
</div>
<div class="container" id="content"></div>
</body>
</html>
