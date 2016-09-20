<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

<div id="add-item-breadcrumb-position">
  <div style="">
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
          <label class="control-label" style="float: right;">
            Item Name :
          </label>

        </div>
        <div class="col-sm-4">
          <input class="form-control" id="txtSearchItem" type="text">
        </div>
        <div class="col-sm-3">
          <button type="button" class="btn btn-success" id="btnSearchItem"><span class="glyphicon glyphicon-search"></span> Search</button>
        </div>
        <div class="col-sm-1"></div>
      </div>
      <br>

      <form class="form-horizontal" role="form" id="frmEditItem">
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
                  <input class="form-control" id="txtEditID" type="text" readonly>
                </div>

              </div>
              <div class="col-xs-6">
                <div class="col-xs-3">
                  <label class="control-label">
                    Name :
                  </label>
                </div>
                <div class="col-xs-9">
                  <input class="form-control" id="txtEditeName" type="text">
                </div>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <div class="col-xs-6">
                <div class="col-xs-4">
                  <label class="control-label" style="float:right;">
                    Category :
                  </label>
                </div>
                <div class="col-xs-8">
                  <select class="form-control" id="editSlctCat">
                    <option value="Pizza">Pizza</option>
                    <option value="Pasta">Pasta</option>
                    <option value="Salad">Salad / Appetizer</option>
                    <option value="Dessert">Dessert</option>
                    <option value="Topping">Topping</option>
                    <option value="Crust">Crust</option>
                  </select>
                </div>

              </div>
              <div class="col-xs-6">
                <div class="col-xs-4">
                  <label class="control-label" style="float: left;">
                    Item Type :
                  </label>
                </div>
                <div class="col-xs-8">
                  <label class="radio-inline">
                    <input type="radio" name="optEditType" value="V" id="radioVeg">Veg
                  </label>
                  <label class="radio-inline">
                    <input type="radio" name="optEditType" value="N" id="radioNveg">Non-veg
                  </label>
                </div>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <div class="col-xs-6">
                <div class="col-xs-4">
                  <label class="control-label" style="float: right">
                    Sub-category :
                  </label>
                </div>
                <div class="col-xs-8">
                  <select class="form-control" id="slctEditSubCat">
                    <option value="Deep dish Pizza">Deep dish Pizza</option>
                    <option value="Italiano Pizza">Italiano Pizza</option>
                    <option value="Gourmet">Gourmet</option>
                    <option value="NY Thin crust">NY Thin crust</option>
                    <option value="Big Pizza">Big Pizza</option>
                  </select>
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
                  <label class="control-label" style="float: right">
                    Item Price :
                  </label>
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
                      <label class="checkbox-inline"><input type="checkbox" value="" class="checkbox">Regular</label>
                    </div>
                    <div class="col-xs-7">
                      <input type="text" value="" class="form-control price">
                    </div>
                  </div>

                  <div class="row item-tbl-row" id="edtItmChkbxMed">

                    <div class="col-xs-5">
                      <label class="checkbox-inline"><input type="checkbox" value="" class="checkbox">Medium</label>
                    </div>
                    <div class="col-xs-7">
                      <input type="text" value="" class="form-control price">
                    </div>
                  </div>

                  <div class="row item-tbl-row" id="edtItmChkbxLrg">

                    <div class="col-xs-5">
                      <label class="checkbox-inline"><input type="checkbox" value="" class="checkbox">Large</label>
                    </div>
                    <div class="col-xs-7">
                      <input type="text" value="" class="form-control price">
                    </div>
                  </div>

                  <div class="row item-tbl-row" id="edtItmChkbxOthr">
                    <div class="col-xs-5">
                      <label class="checkbox-inline"><input type="checkbox" value="" class="checkbox">Other</label>
                    </div>
                    <div class="col-xs-7">
                      <input type="text" value="" class="form-control price">
                    </div>
                  </div>

                </div>
              </div>
              <div class="col-xs-6">
                <div class="col-xs-4">
                  <label class="control-label" style="">
                    Item Images :
                  </label>
                </div>

                <div class="col-xs-8">
                  <button type="button" class="btn btn-success" id="btnEditImage"><span class="glyphicon glyphicon-open"></span> Browse to
                    Upload Images
                  </button>
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
                  <label for="txtEditDesc" class="control-label">
                    Description
                  </label>
                </div>

                <div class="col-xs-8">
                  <textarea class="form-control" rows="5" id="txtEditDesc"></textarea>
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
              <button type="button" class="btn btn-success bckToHome" id="btnEditBack"><span
                      class="glyphicon glyphicon-chevron-left"></span> Back to home</button>
            </div>
            <div class="col-xs-3" align="center">
              <button type="button" class="btn btn-success btn-group-xs" id="btnEditItem"><span
                      class="glyphicon glyphicon-ok"></span> Save edits
              </button>
            </div>
            <div class="col-xs-3" align="left">
              <button type="button" class="btn btn-success" id="btnEditClear" onclick="this.form.reset();"><span
                      class="glyphicon glyphicon-remove"></span> Clear All
              </button>
            </div>

          </div>
        </fieldset>
      </form>
    </div>

  </div>
</div>


</body>
</html>
