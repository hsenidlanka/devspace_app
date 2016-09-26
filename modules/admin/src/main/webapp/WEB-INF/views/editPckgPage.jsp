<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head lang="en">

    <meta charset="UTF-8">
    <title>Add new package</title>

    <!--include common CSS, fonts and js-->
    <link rel="import" href="adminTop.html">

    <link href="../css/itemMgt.css" rel="stylesheet">
    <script src="../js/jquery.toaster.js"></script>
    <script src="../js/itemMgt.js"></script>
    <script src="../js/myAlerts.js"></script>
</head>
<body>


<object width="100%" data="header.html"></object>

<div class="brand">
    <div style="position: relative; left: -50%;">
        Edit Package
    </div>
</div>
<br>

<div class="breadcrumbPosition">
    <div style="position: relative; left: -50%;">
        <ul class="breadcrumb breadcrumb-menu">
            <li><a href="home.html">Home</a></li>
            <li><a href="#">Package Management</a></li>
            <li class="active"><a href="#">Edit Package</a></li>
        </ul>
    </div>
</div>

<div class="form-box" id="edit-pkg-form-box">
    <div class="panel panel-default">
        <div class="panel-heading common-form-headings">
            <h3 class="default-panel-headings">Edit Package</h3>
        </div>
        <div class="panel-body">

            <form:form class="form-horizontal" role="form" id="frmEditPkg">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border">(*) Edit relevant fields</legend>

                    <div class="form-group">

                        <div class="row">
                            <div class="col-xs-4" align="right">
                                <form:label id="lblEditPkgId" path="lblpkgid">Package ID : * </form:label>
                            </div>
                            <div class="col-xs-5">
                                <form:input type="text" class="form-control" readonly="true" path="txtpkgID"></form:input>
                            </div>
                            <div class="col-xs-3" align="center">
                            </div>
                        </div>
                        <br>

                        <div class="row">
                            <div class="col-xs-4" align="right">
                                <form:label id="lblEditPkgNm" path="lblpkgnm">Package Name : </form:label>
                            </div>
                            <div class="col-xs-5">
                                <form:input class="form-control" type="text" path="txtpkgnm"></form:input>
                            </div>
                            <div class="col-xs-3">
                            </div>
                        </div>

                        <br>

                        <div class="row">
                            <div class="col-xs-4" align="right">
                                <form:label path="lblpkgcont">Package Content : </form:label>
                            </div>
                            <div class="col-xs-5">
                                <form:textarea class="form-control" id="editPkgCont" path="txtarpkgcont"></form:textarea>
                            </div>
                            <div class="col-xs-3" align="left">
                                <form:label id="lblEditPkgCont" onclick="" path="lblpkgcntedt"> <span
                                        class="badge edit-pkg-Badges glyphicon glyphicon-edit"><a href="#" id="lnkModalView">
                                    Edit</a></span>
                                </form:label>
                            </div>
                        </div>

                        <br>

                        <div class="row">
                            <div class="col-xs-4" align="right">
                                <form:label path="lblpkgimg"> Package Images : </form:label>
                            </div>
                            <div class="col-xs-5">
                                <div class="list-group row" style="margin:auto; padding: 5px;">

                                    <ul class="list-group">
                                        <li class="list-group-item">image 1.png
                                <span class="badge">
                                  <div class="itemList">
                                    <span class="glyphicon glyphicon-remove">
                                    </span> <a href="">Remove</a>
                                  </div>
                                </span>
                                        </li>
                                        <li class="list-group-item">image 1.png
                                <span class="badge">
                                  <div class="itemList">
                                    <span class="glyphicon glyphicon-remove">
                                    </span> <a href="">Remove</a>
                                  </div>
                                </span>
                                        </li>
                                        <li class="list-group-item">image 1.png
                                <span class="badge">
                                  <div class="itemList">
                                    <span class="glyphicon glyphicon-remove">
                                    </span> <a href="">Remove</a>
                                  </div>
                                </span>
                                        </li>

                                        <li class="list-group-item">&nbsp;
                                <span class="badge">
                                  <div class="edit-pkg-Badges">
                                    <span class="glyphicon glyphicon-plus">
                                    </span> <a href="">Add New</a>
                                  </div>
                                </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <br>

                            <div class="row">
                                <div class="col-xs-4" align="right">
                                    <label>Package Price : </label>
                                </div>
                                <div class="col-xs-5">
                                    <form:input type="text" class="form-control price" id="editPkgPrice" placeholder="LKR 0.00" path="txtpkgprcedt"></form:input>
                                </div>
                                <div class="col-xs-3" align="center">
                                </div>
                            </div>

                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>

    </div>
</div>

<!--Modal for edit package content -->
<div class="modal fade" id="pkgViewModal">
    <div class="modal-dialog modal-md">
        <div class="modal-content">

            <div class="modal-body">
                <form:button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                        class="glyphicon glyphicon-remove"></span></form:button>
                <br>

                <!--Package content id loaded to this div from editPackage.jsp-->
                <div id="editModalContent">

                </div>

            </div>

            <div class="modal-footer" align="right">
                <form:button class="btn btn-success" type="button" value="Yes" id="btnSvEditPkgCont">Save edits
                </form:button>
                <form:button class="btn btn-success" type="button" value="cancel">Back
                </form:button>
                <form:button class="btn btn-success" type="button" value="clear">Clear
                </form:button>
            </div>
        </div>
    </div>
</div>
</body>
</html>