<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

      <form:form class="form-horizontal" role="form" id="frmAddItem" action="" method="post">
        <fieldset class="scheduler-border">
          <legend class="scheduler-border">(*) Fields are read only</legend>


          <div class="form-group">
            <div class="row">
              <form:label for="selectCat" class="col-xs-3 control-label" path="/item/addItem">
                Category
              </form:label>

              <div class="col-xs-5">
                <form:select class="form-control" id="selectCat" path="slctCat">
                  <form:option value="0">--Select Category--</form:option>
                  <form:option value="pizza">Pizza</form:option>
                  <form:option value="pasta">Pasta</form:option>
                  <form:option value="salad">Salad / Appetizer</form:option>
                  <form:option value="dessert">Dessert</form:option>
                  <form:option value="topping">Topping</form:option>
                  <form:option value="crust">Crust</form:option>
                </form:select>
                <span id="catErr" class="input-group-error"></span>
              </div>

              <div class="col-xs-4">
                <form:button type="button" class="btn btn-success" id="btnAddCat"
                        onclick="window.location='addcategory.html'"><span
                        class="glyphicon glyphicon-plus"></span> Add New Category
                </form:button>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <form:label path="" for="selectSubCat" class="col-xs-3 control-label">
                Sub-category
              </form:label>

              <div class="col-xs-5">
                <form:select class="form-control" id="selectSubCat" path="slctSbcat">
                  <form:option value="">--Select sub-catrgory--</form:option>
                  <form:option value="">Deep dish Pizza</form:option>
                  <form:option value="">Italiano Pizza</form:option>
                  <form:option value="">Gourmet</form:option>
                  <form:option value="">NY Thin crust</form:option>
                  <form:option value="">Big Pizza</form:option>
                </form:select>
                <span id="subCatErr" class="input-group-error"></span>
              </div>

              <div class="col-xs-4">
                <form:button type="button" class="btn btn-success" id="btnAddSubCat"
                        onclick="window.location='addcategory.html'"><span
                        class="glyphicon glyphicon-plus"></span> Add
                  Sub-category
                </form:button>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <form:label for="txtItemId" class="col-xs-3 control-label" path="lblItmid">
                Item ID *
              </form:label>

              <div class="col-xs-5">
                <form:input class="form-control" id="txtItemId" type="text" path="itmId" readonly="true">
                </form:input>
              </div>
              <div class="col-xs-4">
                <span id="itmIdErr" class="input-group-error"></span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <form:label for="txtItemName" class="col-xs-3 control-label" path="lblItmNm">
                Item Name
              </form:label>

              <div class="col-xs-5">
                <form:input class="form-control" id="txtItemName" type="text" path="txtItmNm">
                </form:input>
              </div>
              <div class="col-xs-4">
                <span id="itmNmErr" class="input-group-error"></span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <form:label class="col-xs-3 control-label" path="lblItmprc">
                Item Price
              </form:label>

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
                    <form:label class="checkbox-inline" path="lblchk1">
                      <form:input type="checkbox" value="" class="checkbox" path="chk1"> Regular</form:input>
                    </form:label>
                  </div>
                  <div class="col-xs-7">
                    <form:input type="text" value="" class="form-control price" path="txtPrc1">
                    </form:input>
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxMed">

                  <div class="col-xs-5">
                    <form:label class="checkbox-inline" path="lblchk2">
                      <form:input type="checkbox" value="" class="checkbox" path="chk2"> Medium</form:input>
                    </form:label>
                  </div>
                  <div class="col-xs-7">
                    <form:input type="text" value="" class="form-control price" path="txtPrc2">
                    </form:input>
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxLrg">

                  <div class="col-xs-5">
                    <form:label class="checkbox-inline" path="lblchk3">
                      <form:input type="checkbox" value="" class="checkbox" path="chk3"> Large
                      </form:input>.
                    </form:label>
                  </div>
                  <div class="col-xs-7">
                    <form:input type="text" value="" class="form-control price" path="txtPrc3">
                    </form:input>
                  </div>
                </div>

                <div class="row item-tbl-row" id="addItmChkbxOthr">
                  <div class="col-xs-5">
                    <form:label class="checkbox-inline" path="lblchk4">
                    <form:input type="checkbox" value="" class="checkbox" path="chk4"> Other</form:input>
                    </form:label>
                  </div>
                  <div class="col-xs-7">
                    <form:input type="text" value="" class="form-control price" path="txtPrc4">
                    </form:input>
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
              <form:label class="col-xs-3 control-label" path="lblType">
                Item Type
              </form:label>

              <div class="col-xs-5">
                <form:label class="radio-inline" path="lblrd1">
                  <form:input type="radio" name="optAddType" path="rd1"> Veg </form:input>
                </form:label>
                <form:label class="radio-inline" path="lblrd2">
                  <form:input type="radio" name="optAddType" path="rd2">Non-veg </form:input>
                </form:label>
              </div>

              <div class="col-xs-4">
                <span id="itmTypeErr" class="input-group-error"></span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <form:label for="btnUpldImage" class="col-xs-3 control-label" path="lblbtnupld">
                Add Image/s
              </form:label>

              <div class="col-xs-5">
                <form:button type="button" class="btn btn-success" id="btnUpldImage">
                  <span class="glyphicon glyphicon-upload"></span> Browse to Upload
                  Images
                </form:button>
              </div>
              <div class="col-xs-4">
                <span id="itmImgErr" class="input-group-error"> </span>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="row">
              <form:label for="txtDesc" class="col-xs-3 control-label" path="lbldesc">
                Description
              </form:label>

              <div class="col-xs-5">
                <form:textarea class="form-control" rows="5" id="txtdesc" path="txtarea">
                </form:textarea>
              </div>
            </div>
          </div>

          <br>

          <div style="text-align: center; z-index: 50000; width: 50%; margin: auto;" id="toaster"></div>

          <div class="row" align="right">
            <div class="col-xs-3">
            </div>
            <div class="col-xs-3" align="right">
              <form:button type="button" class="btn btn-success btn-group-xs bckToHome"
                      id="Back"><span
                      class="glyphicon glyphicon-chevron-left"></span> back to home
              </form:button>
            </div>
            <div class="col-xs-3" align="center">
              <form:button type="button" class="btn btn-success btn-group-xs" id="btnAddItem"><span
                      class="glyphicon glyphicon-plus"></span> Add
                Item
              </form:button>
            </div>
            <div class="col-xs-3" align="left">
              <form:button type="button" class="btn btn-success" id="btnAddClear" onclick="this.form.reset();">Clear</form:button>
            </div>
          </div>
        </fieldset>
      </form:form>
    </div>
  </div>
</div>
</body>
</html>
