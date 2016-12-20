<!DOCTYPE html>
<html>
<head>
    <%@include file="../includes/include.jsp" %>

</head>

<body>
<div class="panel panel-success">

    <div class="panel-heading">
        <img class="icons" src="<c:url value="/resources/images/icons/usericon.png"/>">
        <h3  style="display: inline-block;">Welcome ${name}</h3>
    </div>

    <div class="panel-body">
        <div class="container">
            <table class="table table-striped" style="width: 50%">
                <tbody>
                <tr>
                    <td><strong>First Name</strong></td>
                    <td>Menuka</td>

                </tr>
                <tr>
                    <td><strong>Last Name</strong></td>
                    <td>Ishan</td>
                </tr>
                <tr>
                    <td><strong>Username</strong></td>
                    <td>testing</td>
                </tr>
                <tr>
                    <td><strong>Email</strong></td>
                    <td>eladena@gmail.com</td>
                </tr>
                <tr>
                    <td><strong>Address Line 1</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td><strong>Address Line 2</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td><strong>Address Line 3</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td><strong>Mobile</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>


        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <center>
                    <a href="#" onclick="load_update_profile();" class="btn btn-warning">Update Profile</a>
                </center>
            </div>
            <div class="col-sm-4"></div>
        </div>
    </div>

</div>
</body>