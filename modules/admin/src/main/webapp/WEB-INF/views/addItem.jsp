<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <title>Add New Item</title>
  <!-- include common CSS, fonts and js -->
  <jsp:include page="adminTop.jsp"/>

  <%-- Other css --%>
  <spring:url value="/themes/hsenid/css/itemMgt.css" var="cssItem"/>
  <link href="${cssItem}" rel="stylesheet">

  <%--  other javascripts --%>

  <spring:url value="/themes/hsenid/js/itemMgt.js" var="itemJs"/>
  <spring:url value="/themes/hsenid/js/myAlerts.js" var="myAlerts"/>

  <script src="${itemJs}"></script>
  <script src="${myAlerts}"></script>


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
      <li class="active"><a href="#">Add Item</a></li>
    </ul>
  </div>
</div>

<div class="form-box" id="add-item-form-box">
  <div class="panel panel-default">
    <div class="panel-heading common-form-headings">
      <h3 class="default-panel-headings">Add New Item</h3>
    </div>
    <div class="panel-body">

      <form class="form-horizontal" role="form" id="frmAddItem">
        <fieldset class="scheduler-border">
          <legend class="scheduler-border">(*) Fields are read only</legend>


          <div class="form-group">
            <div class="row">
              <label for="selectCat" class="col-xs-3 control-label">
                Category
              </label>

              <div class="col-xs-5">
                <select class="form-control" id="selectCat">
                  <option>--Select Category--</option>
                  <option>Pizza</option>
                  <option>Pasta</option>
                  <option>Salad / Appetizer</option>
                  <option>Dessert</option>
                  <option>Topping</option>
                  <option>Crust</option>
                </select>
                <span id="catErr" class="input-group-error"></span>
              </div>

              <div class="col-xs-4">
                <button type="button" class="btn btn-success" id="btnAddCat"
                        onclick="window.location='addcategory.html'"><span
                        class="glyphicon glyphicon-plus"></span> Add New Category
                </button>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label for="selectSubCat" class="col-xs-3 control-label">
                Sub-category
              </label>

              <div class="col-xs-5">
                <select class="form-control" id="selectSubCat">
                  <option>--Select sub-catrgory--</option>
                  <option>Deep dish Pizza</option>
                  <option>Italiano Pizza</option>
                  <option>Gourmet</option>
                  <option>NY Thin crust</option>
                  <option>Big Pizza</option>
                </select>
                <span id="subCatErr" class="input-group-error"></span>
              </div>

              <div class="col-xs-4">
                <button type="button" class="btn btn-success" id="btnAddSubCat"
                        onclick="window.location='addcategory.html'"><span
                        class="glyphicon glyphicon-plus"></span> Add
                  Sub-category
                </button>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label for="txtItemId" class="col-xs-3 control-label">
                Item ID *
              </label>

              <div class="col-xs-5">
                <input class="form-control" id="txtItemId" type="text" readonly>
              </div>
              <div class="col-xs-4">
                <span id="itmIdErr" class="input-group-error"></span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label for="txtItemName" class="col-xs-3 control-label">
                Item Name
              </label>

              <div class="col-xs-5">
                <input class="form-control" id="txtItemName" type="text">
              </div>
              <div class="col-xs-4">
                <span id="itmNmErr" class="input-group-error"></span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label class="col-xs-3 control-label">
                Item Price
              </label>

              <div class="col-xs-5">

                <div class="row item-tbl-row item-tbl-hdr" style="text-align: center;">
                  <div class="col-xs-5">
                    <strong>Size</strong>
                  </div>
                  <div class="col-xs-7">
                    <strong>Price</strong>
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxReg">

                  <div class="col-xs-5">
                    <label class="checkbox-inline"><input type="checkbox" value="" class="checkbox">Regular</label>
                  </div>
                  <div class="col-xs-7">
                    <input type="text" value="" class="form-control price">
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxMed">

                  <div class="col-xs-5">
                    <label class="checkbox-inline"><input type="checkbox" value="" class="checkbox">Medium</label>
                  </div>
                  <div class="col-xs-7">
                    <input type="text" value="" class="form-control price">
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxLrg">

                  <div class="col-xs-5">
                    <label class="checkbox-inline"><input type="checkbox" value="" class="checkbox">Large</label>
                  </div>
                  <div class="col-xs-7">
                    <input type="text" value="" class="form-control price">
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxOthr">
                  <div class="col-xs-5">
                    <label class="checkbox-inline"><input type="checkbox" value="" class="checkbox">Other</label>
                  </div>
                  <div class="col-xs-7">
                    <input type="text" value="" class="form-control price">
                  </div>
                </div>


              </div>

              <div class="col-xs-4">
                <span id="itmPriceErr" class="input-group-error"></span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label class="col-xs-3 control-label">
                Item Type
              </label>

              <div class="col-xs-5">
                <label class="radio-inline">
                  <input type="radio" name="optAddType">Veg
                </label>
                <label class="radio-inline">
                  <input type="radio" name="optAddType">Non-veg
                </label>
              </div>

              <div class="col-xs-4">
                <span id="itmTypeErr" class="input-group-error"></span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label for="btnUpldImage" class="col-xs-3 control-label">
                Add Image/s
              </label>

              <div class="col-xs-5">
                <button type="button" class="btn btn-success" id="btnUpldImage">
                  <span class="glyphicon glyphicon-upload"></span> Browse to Upload
                  Images
                </button>
              </div>
              <div class="col-xs-4">
                <span id="itmImgErr" class="input-group-error"> </span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <label for="txtDesc" class="col-xs-3 control-label">
                Description
              </label>

              <div class="col-xs-5">
                <textarea class="form-control" rows="5" id="txtdesc"></textarea>
              </div>
            </div>
          </div>

          <br>

          <div style="text-align: center; z-index: 50000; width: 50%; margin: auto;" id="toaster"></div>

          <div class="row" align="right">
            <div class="col-xs-3">
            </div>
            <div class="col-xs-3" align="right">
              <button type="button" class="btn btn-success btn-group-xs bckToHome"
                      id=Back"><span
                      class="glyphicon glyphicon-chevron-left"></span> back to home
              </button>
            </div>
            <div class="col-xs-3" align="center">
              <button type="button" class="btn btn-success btn-group-xs" id="btnAddItem"><span
                      class="glyphicon glyphicon-plus"></span> Add
                Item
              </button>
            </div>
            <div class="col-xs-3" align="left">
              <button type="button" class="btn btn-success" id="btnAddClear" onclick="this.form.reset();">Clear</button>
            </div>
          </div>
        </fieldset>
      </form>
    </div>
  </div>
</div>
</body>
</html>
