<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Item</title>
    <!-- include common CSS, fonts and js -->
    <jsp:include page="adminTop.jsp"/>

    <%-- Other css --%>
    <spring:url value="/themes/hsenid/css/itemMgt.css" var="cssItem"/>
    <link href="${cssItem}" rel="stylesheet">

    <%--  other javascripts --%>
    <spring:url value="/themes/hsenid/js/itemMgt.js" var="itemJs"/>
    <spring:url value="/themes/hsenid/js/myAlerts.js" var="myAlerts"/>
    <spring:url value="/themes/hsenid/js/tableItems.js" var="tblViewItm"/>

    <script src="${itemJs}"></script>
    <script src="${myAlerts}"></script>
    <script src="${tblViewItm}"></script>


</head>
<body>

<jsp:include page="header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        Item Management
    </div>
</div>
<br>

<div>
    <div id="add-item-breadcrumb-position">
        <ul class="breadcrumb breadcrumb-menu">
            <li><a href="https://localhost:8443/users/list">Home</a></li>
            <li><a href="#">Item Management</a></li>
            <li class="active"><a href="#">Edit Item</a></li>
        </ul>
    </div>
</div>

<div class="form-box" id="edit-item-form-box">
    <div class="panel panel-default">
        <div class="panel-heading common-form-headings">
            <h3 class="default-panel-headings"><span class="glyphicon glyphicon-pencil"></span> Edit Item</h3>
        </div>

        <div class="panel-body">

            <div class="row itemSearchBar">
                <div class="col-sm-1"></div>
                <div class="col-sm-3">
                    <form:label class="control-label" style="float: right;" path="lblItmNm">
                        Item Name :
                    </form:label>

                </div>
                <div class="col-sm-4">
                    <form:input class="form-control" id="txtSearchItem" type="text" path=""> </form:input>
                </div>
                <div class="col-sm-3">
                    <form:button type="button" class="btn btn-success" id="btnSearchItem"><span
                            class="glyphicon glyphicon-search"></span> Search</form:button>
                </div>
                <div class="col-sm-1"></div>
            </div>
            <br>

            <form:form class="form-horizontal" role="form" id="frmEditItem">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border">(*) Fields are read only</legend>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <label class="control-label" style="float:right;">
                                        Item ID : &nbsp; *
                                    </label>
                                </div>
                                <div class="col-xs-8">
                                    <form:input class="form-control" id="txtEditID" type="text" readonly="true"
                                                path="txtEditID"></form:input>
                                </div>

                            </div>
                            <div class="col-xs-6">
                                <div class="col-xs-3">
                                    <form:label class="control-label" path="lblNm">
                                        Name :
                                    </form:label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input class="form-control" id="txtEditeName" type="text"
                                                path="txteditNm"></form:input>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <form:label class="control-label" style="float:right;" path="lblCat">
                                        Category :
                                    </form:label>
                                </div>
                                <div class="col-xs-8">
                                    <form:select class="form-control" id="editSlctCat" path="slctCat">
                                        <form:option value="Pizza">Pizza</form:option>
                                        <form:option value="Pasta">Pasta</form:option>
                                        <form:option value="Salad">Salad / Appetizer</form:option>
                                        <form:option value="Dessert">Dessert</form:option>
                                        <form:option value="Topping">Topping</form:option>
                                        <form:option value="Crust">Crust</form:option>
                                    </form:select>
                                </div>

                            </div>
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <form:label class="control-label" style="float: left;" path="lblType">
                                        Item Type :
                                    </form:label>
                                </div>
                                <div class="col-xs-8">
                                    <form:label class="radio-inline" path="lblRdoV">
                                        <form:input type="radio" name="optEditType" value="V" id="radioVeg"
                                                    path="rdoVg">Veg </form:input>
                                    </form:label>
                                    <form:label class="radio-inline" path="lblRdoNg">
                                        <form:input type="radio" name="optEditType" value="N" id="radioNveg"
                                                    path="rdoNvg">Non-veg </form:input>
                                    </form:label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <form:label class="control-label" style="float: right" path="lblSbcat">
                                        Sub-category :
                                    </form:label>
                                </div>
                                <div class="col-xs-8">
                                    <form:select class="form-control" id="slctEditSubCat" path="slctSbct">
                                        <form:option value="Deep dish Pizza">Deep dish Pizza</form:option>
                                        <form:option value="Italiano Pizza">Italiano Pizza</form:option>
                                        <form:option value="Gourmet">Gourmet</form:option>
                                        <form:option value="NY Thin crust">NY Thin crust</form:option>
                                        <form:option value="Big Pizza">Big Pizza</form:option>
                                    </form:select>
                                </div>

                            </div>
                            <div class="col-xs-6">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <form:label class="control-label" style="float: right" path="lblItmPrc">
                                        Item Price :
                                    </form:label>
                                </div>

                                <div class="col-xs-8">
                                    <div class="row item-tbl-row item-tbl-hdr">
                                        <div class="col-xs-5">
                                            <strong>Size</strong>
                                        </div>
                                        <div class="col-xs-7">
                                            <strong>Price</strong>
                                        </div>
                                    </div>

                                    <div class="row item-tbl-row" id="edtItmChkbxReg">

                                        <div class="col-xs-5">
                                            <form:label class="checkbox-inline" path="lblchk1"><form:input
                                                    type="checkbox" value="" class="checkbox"
                                                    path="chk1">Regular </form:input></form:label>
                                        </div>
                                        <div class="col-xs-7">
                                            <form:input type="text" value="" class="form-control price" path="txtprc1"></form:input>
                                        </div>
                                    </div>

                                    <div class="row item-tbl-row" id="edtItmChkbxMed">

                                        <div class="col-xs-5">
                                            <form:label class="checkbox-inline" path="lblchk2">
                                            <form:input type="checkbox" value="" class="checkbox" path="txtchk2">Medium </form:input>
                                            </form:label>
                                        </div>
                                        <div class="col-xs-7">
                                            <form:input type="text" value="" class="form-control price" path="txtprc2"> </form:input>
                                        </div>
                                    </div>

                                    <div class="row item-tbl-row" id="edtItmChkbxLrg">

                                        <div class="col-xs-5">
                                            <form:label class="checkbox-inline" path="lblchk3">
                                            <form:input type="checkbox" value=""  class="checkbox" path="chk3">Large
                                            </form:input>
                                            </form:label>
                                        </div>
                                        <div class="col-xs-7">
                                            <form:input type="text" value="" class="form-control price" path="txtprc3"></form:input>
                                        </div>
                                    </div>

                                    <div class="row item-tbl-row" id="edtItmChkbxOthr">
                                        <div class="col-xs-5">
                                            <form:label class="checkbox-inline" path="lblchk4">
                                            <form:input type="checkbox" value="" class="checkbox" path="chk4">Other
                                            </form:input>
                                            </form:label>
                                        </div>
                                        <div class="col-xs-7">
                                            <form:input type="text" value="" class="form-control price" path="txtprc4"></form:input>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <form:label class="control-label" style="" path="lblImgs">
                                        Item Images :
                                    </form:label>
                                </div>

                                <div class="col-xs-8">
                                    <form:button type="button" class="btn btn-success" id="btnEditImage"><span
                                            class="glyphicon glyphicon-open"></span> Browse to
                                        Upload Images
                                    </form:button>
                                    <br><br>

                                    <div class="col-xs-8">
                                        <table class="table table-hover table-bordered table-condensed" id="">
                                            <tbody>
                                            <tr>
                                                <td>Image</td>
                                                <td><input type="button" value="Update" class="form-control"></td>
                                                <td><input type="button" value="X" class="form-control"></td>
                                            </tr>
                                            <tr>
                                                <td>Image</td>
                                                <td><input type="button" value="Update" class="form-control"></td>
                                                <td><input type="button" value="X" class="form-control"></td>
                                            </tr>
                                            <tr>
                                                <td>Image</td>
                                                <td><input type="button" value="Update" class="form-control"></td>
                                                <td><input type="button" value="X" class="form-control"></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4">
                                    <form:label for="txtEditDesc" class="control-label" path="lblDesc">
                                        Description
                                    </form:label>
                                </div>

                                <div class="col-xs-8">
                                    <form:textarea class="form-control" rows="5" id="txtEditDesc" path="txtareaDesc"></form:textarea>
                                </div>
                            </div>
                        </div>
                    </div>

                    <br>

                    <div style="text-align: center; z-index: 50000; width: 50%; margin: auto;" id="toaster"></div>

                    <div class="row" align="center">
                        <div class="col-xs-3">
                        </div>
                        <div class="col-xs-3" align="right">
                            <form:button type="button" class="btn btn-success bckToHome" id="btnEditBack"><span
                                    class="glyphicon glyphicon-chevron-left"></span> Back to home
                            </form:button>
                        </div>
                        <div class="col-xs-3" align="center">
                            <form:button type="button" class="btn btn-success btn-group-xs" id="btnEditItem"><span
                                    class="glyphicon glyphicon-ok"></span> Save edits
                            </form:button>
                        </div>
                        <div class="col-xs-3" align="left">
                            <form:button type="button" class="btn btn-success" id="btnEditClear"
                                    onclick="this.form.reset();"><span
                                    class="glyphicon glyphicon-remove"></span> Clear All
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
