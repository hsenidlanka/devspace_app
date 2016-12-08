<!-- Add to cart with customization popup -->
<div id="add-to-cart-popup" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
        <img class="icons" src="<c:url value="/resources/images/icons/shopping-cart-add.png"/>">
        <h4 class="modal-title header-panel">Customize and Add to Cart</h4>
      </div>
      <div class="modal-body">
        <p>Customize the following item as you wish and click add to cart to add
          the item to cart.</p>

        <div class="thumbnail" style="height:120px; border: none;">
          <img src="<c:url value="/resources/images/image_placeholder.gif"/>"
          class="img-responsive" align="left" height="100px" width="100px">

          <div class="caption">
            <h4 id="item-title-modal">&nbsp;&nbsp; Item Title</h4>
            <p id="item-desc-modal">&nbsp;&nbsp; Some description about the item.</p>
          </div>
        </div>
        <form role="form" style="margin-top:-20px;">
          <div class="form-group row">
            <div class="col-sm-6" style="display:inline-block;">
              <label class="control-label">Size</label>
              <select class="form-control" id="addtocart-pizza-sizes">
              </select>
            </div>
            <div class="col-sm-6"></div>
          </div>
          <div class="form-group row">
            <div class="col-sm-6" style="display:inline-block; margin-top:5px;">
              <label class="control-label">Toppings</label>
              <select class="form-control" id="addtocart-pizza-toppings1">
              </select>
            </div>
            <div class="col-sm-6" style="display:inline-block; margin-top:5px;">
              <label class="control-label">&nbsp;</label>
              <select class="form-control" id="addtocart-pizza-toppings2">
              </select>
            </div>
          </div>
          <div class="form-group" style="display:inline-block; margin-top:5px; margin-left:15px;">
            <label class="control-label">Special Instructions</label>
            <textarea class="form-control" cols="80"
                      placeholder="Mention any special instructions" id="addtocart-pizza-instructs"></textarea>
          </div>
          <div class="form-group" style="margin-left:15px;">
            <label class="control-label" style="padding-right:15px;">Quantity:</label>
            <input id="addtocart-pizza-qty" class="spin" type="number" min="1" max="100" value="1" style="width: 3em;">
            <label class="control-label"
                   style="padding-left:30px; padding-right:10px;">Price:&nbsp;&nbsp;</label>
            <input type="text" id="addtocart-pizza-price" readonly style="width:4em">
            <label class="control-label"
                   style="padding-left:30px; padding-right:10px;">Total:&nbsp;&nbsp;</label>
            <input type="text" id="addtocart-pizza-total" readonly style="width:8em">
          </div>
        </form>
      </div>
      <div class="modal-footer" style="margin-top:-20px;">
        <a class="btn btn-default" data-dismiss="modal">Cancel</a>
        <button class="btn btn-success btn-popup-add-to-cart-pizza">Add to Cart</button>
      </div>
    </div>
  </div>
</div>