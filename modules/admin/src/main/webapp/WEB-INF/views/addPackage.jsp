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
        Add New Package
    </div>
</div>
<br>

<div class="breadcrumbPosition">
    <div style="position: relative; left: -50%;">
        <ul class="breadcrumb breadcrumb-menu">
            <li><a href="home.html">Home</a></li>
            <li><a href="#">Package Management</a></li>
            <li class="active"><a href="#">Add New Package</a></li>
        </ul>
    </div>
</div>

<div class="form-box" id="add-pkg-form-box">
    <div class="panel panel-default">
        <div class="panel-heading common-form-headings">
            <h3 class="default-panel-headings">Add New Package</h3>
        </div>
        <div class="panel-body">

            <form:form class="form-horizontal" role="form" id="frmAddPkg" method="post" action="/addPkg">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border">(*) Fields are read only</legend>


                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-7">
                                <form:label class="col-xs-5 control-label" path="lblpkgID">
                                    Package ID : &nbsp; *
                                </form:label>

                                <div class="col-xs-7">
                                    <form:input class="form-control" type="text" id="txtAddPkgId" readonly="true" path="txtpkgId"></form:input>
                                </div>
                            </div>
                            <div class="col-xs-5">
                                <form:label class="col-xs-5 control-label" path="lblprice">
                                    Price :
                                </form:label>

                                <div class="col-xs-7" align="right">
                                    <form:input class="form-control price" type="text" id="txtAddPkgPrice" path="txtpkgprc"></form:input>
                                    <span id="pkgPriceErr" class="input-group-error" style="float: left"> </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-7">
                                <form:label class="col-xs-5 control-label" path="lblpkgNm">
                                    Name :
                                </form:label>

                                <div class="col-xs-7">
                                    <form:input class="form-control" type="text" id="txtAddPkgName" path="txtpkgNm"></form:input>
                                </div>
                                <span id="pkgNmErr" class="input-group-error"> </span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <form:label class="col-xs-3 control-label" path="lblpkgDetl">
                                Package Detail :
                            </form:label>
                        </div>
                        <br>

                        <div class="row">
                            <div class="col-xs-2"></div>

                            <div class="col-xs-10">

                                <div class="row item-tbl-row item-tbl-hdr">
                                    <div class="col-xs-3">
                                        <strong>Content</strong>
                                    </div>
                                    <div class="col-xs-4">
                                        <strong>Name</strong>
                                    </div>
                                    <div class="col-xs-2">
                                        <strong>Qty</strong>
                                    </div>
                                    <div class="col-xs-3">
                                        <strong>Status</strong>
                                    </div>
                                </div>

                                <div class="row item-tbl-row">
                                    <div class="col-xs-3">
                                        <form:label class="checkbox-inline" path="lblchkp1">
                                        <form:input type="checkbox" value="" class="checkbox" path="pkgchk1"></form:input>
                                        Pizza
                                        </form:label>
                                    </div>
                                    <div class="col-xs-4">
                                        <form:input type="text" value="" class="form-control price" path="txtpkgPrc1"></form:input>
                                    </div>
                                    <div class="col-xs-2">
                                        <form:input class="qty-spinner" type="number" value="0" path="qtysp1"></form:input>
                                    </div>
                                    <div class="col-xs-3">
                                        <form:button type="button" value="" class="btn btn-success btnAddItmPkg"
                                                id="btnAddPkgPizza">
                                            <span class="glyphicon glyphicon-plus"></span>Add
                                        </form:button>
                                    </div>
                                </div>

                                <div class="row item-tbl-row">
                                    <div class="col-xs-3">
                                        <form:label class="checkbox-inline" path="lblchk2">
                                        <form:input type="checkbox" value="" class="checkbox" path="pkgchk2"></form:input>
                                        Pasta/Appetizer
                                        </form:label>
                                    </div>
                                    <div class="col-xs-4">
                                        <form:input type="text" value="" class="form-control price" path="txtpkgPrc2"></form:input>
                                    </div>
                                    <div class="col-xs-2">
                                        <form:input class="qty-spinner" type="number" value="0" path="qtysp2"></form:input>
                                    </div>
                                    <div class="col-xs-3">
                                        <form:button type="button" value="" class="btn btn-success btnAddItmPkg"
                                                id="btnAddPkgPasta">
                                            <span class="glyphicon glyphicon-plus"></span>Add
                                        </form:button>
                                    </div>
                                </div>

                                <div class="row item-tbl-row">
                                    <div class="col-xs-3">
                                        <form:label class="checkbox-inline" path="lblchk3">
                                        <form:input type="checkbox" value="" class="checkbox" path="pkgchk3"></form:input>
                                        Bevarage
                                        </form:label>
                                    </div>
                                    <div class="col-xs-4">
                                        <form:input type="text" value="" class="form-control price" path="txtpkgPrc3"></form:input>
                                    </div>
                                    <div class="col-xs-2">
                                        <form:input class="qty-spinner" type="number" value="0" path="qtysp3"></form:input>
                                    </div>
                                    <div class="col-xs-3">
                                        <form:button type="button" value="" class="btn btn-success btnAddItmPkg"
                                                id="btnAddPkgBvrg">
                                            <span class="glyphicon glyphicon-plus"></span>Add
                                        </form:button>
                                    </div>
                                </div>

                                <div class="row item-tbl-row">
                                    <div class="col-xs-3">
                                        <form:label class="checkbox-inline" path="lblchk4">
                                        <form:input type="checkbox" value="" class="checkbox" path="pkgchk4"></form:input>
                                        Salad
                                        </form:label>
                                    </div>
                                    <div class="col-xs-4">
                                        <form:input type="text" value="" class="form-control price" path="txtxpkgPrc4"></form:input>
                                    </div>
                                    <div class="col-xs-2">
                                        <form:input class="qty-spinner" type="number" value="0" path="qtysp4"></form:input>
                                    </div>
                                    <div class="col-xs-3">
                                        <form:button type="button" value="" class="btn btn-success btnAddItmPkg"
                                                id="btnAddPkgSalad">
                                            <span class="glyphicon glyphicon-plus"></span>Add
                                        </form:button>
                                    </div>
                                </div>

                                <div class="row item-tbl-row">
                                    <div class="col-xs-3">
                                        <form:label class="checkbox-inline" path="lblchk5">
                                        <form:input type="checkbox" value="" class="checkbox" path="chkpkg5"></form:input>
                                        Dessert
                                        </form:label>
                                    </div>
                                    <div class="col-xs-4">
                                        <form:input type="text" value="" class="form-control price" path="txtpkgPrc5"></form:input>
                                    </div>
                                    <div class="col-xs-2">
                                        <form:input class="qty-spinner" type="number" value="0" path="qtysp5"></form:input>
                                    </div>
                                    <div class="col-xs-3">
                                        <form:button type="button" value="" class="btn btn-success btnAddItmPkg"
                                                id="btnAddPkgDssrt">
                                            <span class="glyphicon glyphicon-plus"></span>Add
                                        </form:button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <form:label class="col-xs-3 control-label" path="lblpkgImg">
                                Add Image/s :
                            </form:label>

                            <div class="col-xs-5">
                               <form:button type="button" class="btn btn-success" id="btnupldPkgImg"><span
                                        class="glyphicon glyphicon-open"></span> Browse to Upload
                                    Images
                                </form:button><%--<input type="file" value="fg">--%>
                                <span id="pkgImgErr" class="input-group-error"> </span>
                            </div>
                        </div>
                    </div>
                    <br>

                    <div class="list-group row" style="margin:auto; width:50%; border:2px solid black; padding: 10px;">
                        <form:label path="lblhidden"><h4>Hidden field</h4></form:label><br>
                        <form:label id="newPkgName" path="lblpkgNmNew"><strong>Pacakage Name</strong></form:label> <br>
                        <ul class="list-group">
                            <li class="list-group-item">Pkg item1
                                <span class="badge">
                                  <div class="itemList">
                                    <span class="glyphicon glyphicon-remove">
                                    </span> <a href="">Remove</a>
                                  </div>
                                </span>
                            </li>
                            <li class="list-group-item">Pkg item2
                                <span class="badge">
                                  <div class="itemList">
                                    <span class="glyphicon glyphicon-remove">
                                    </span> <a href="">Remove</a>
                                  </div>
                                </span>
                            </li>
                            <li class="list-group-item">Pkg item3
                                <span class="badge">
                                  <div class="itemList">
                                    <span class="glyphicon glyphicon-remove">
                                    </span> <a href="">Remove</a>
                                  </div>
                                </span>
                            </li>
                            <li class="list-group-item">Price
                                <span class="badge">
                                  <div class="itemList">
                                    <span class="glyphicon glyphicon-remove">
                                    </span> <a href="">Remove</a>
                                  </div>
                                </span>
                            </li>
                            <li class="list-group-item">Images
                                <span class="badge">
                                  <div class="itemList">
                                    <span class="glyphicon glyphicon-remove">
                                    </span> <a href="">Remove</a>
                                  </div>
                                </span>
                            </li>

                        </ul>
                    </div>
                    <br><br>

                    <div style="text-align: center; z-index: 50000; width: 50%; margin: auto;" id="toaster"></div>
                    <br>

                    <div class="row">
                        <div class="col-xs-4">
                        </div>
                        <div class="col-xs-4" align="right">
                            <form:button type="button" class="btn btn-success bckToHome" id="btnPkgCancel"><span
                                    class="glyphicon glyphicon-chevron-left"></span> Back to home
                            </form:button>
                        </div>
                        <div class="col-xs-4" align="left">
                            <form:button type="button" class="btn btn-success btn-group-xs" id="btnAddNewPkg">Add
                                Package
                            </form:button>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>

    </div>
</div>
</body>
</html>