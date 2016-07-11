<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <script>
        $(window).load(function () {
            $('#myModal').modal('show');
        });
    </script>

    <style>
        body {
            background-image: url("HMS-Logo-2013.png");
            background-repeat: no-repeat;
        }
    </style>

</head>
<body>

<div class="container container-fluid">
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog" data-backdrop="static" style="left: 15%; top: 23%;">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <center><h4 class="modal-title">Login Form</h4></center>
                </div>
                <div class="modal-body">
                    <form:form action="loginform" commandName="loginForm" method="post">

                    <div class="form-group">
                        <label for="userName" class="col-sm-3 control-label textclr">Username : </label>

                        <div class="col-sm-9"><form:input path="userName" id="userName"/></div>
                    </div>
                    <br>
<br>
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label textclr"> Password :</label>

                        <div class="col-sm-9"><form:password path="password" id="password"/></div>
                    </div>

                </div>
                <br>
                <center><input type="submit" value="Submit" class="btn btn-danger btn-large"/></center>
                <br><br>
                </form:form>
            </div>
        </div>
    </div>
</div>
</div></body>


</body>
</html>