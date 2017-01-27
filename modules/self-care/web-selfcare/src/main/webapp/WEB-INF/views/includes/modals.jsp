<!-- Add to cart with customization popup -->
<div id="add-to-cart-popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i>
                </button>
                <img class="icons" src="<c:url value="/resources/images/icons/shopping-cart-add.png"/>">
                <h4 class="modal-title header-panel"><fmt:message key="add.to.cart.model.header"
                                                                  bundle="${lang}"/></h4>
            </div>
            <div class="modal-body">
                <p><fmt:message key="add.to.cart.model.description" bundle="${lang}"/></p>

                <div class="thumbnail" style="height:115px; border: none;">
                    <img src="<c:url value="/resources/images/image_placeholder.gif"/>"
                         class="img-responsive" align="left" height="100px" width="100px">

                    <div class="caption">
                        <h4 id="item-title-modal">&nbsp;&nbsp; <fmt:message key="shopping.cart.item.title"
                                                                            bundle="${lang}"/></h4>

                        <p id="item-desc-modal">&nbsp;&nbsp; <fmt:message key="add.to.cart.modal.text"
                                                                          bundle="${lang}"/></p>
                    </div>
                </div>
                <form role="form" style="margin-top:-20px;">
                    <div class="form-group row">
                        <div class="col-sm-6" style="display:inline-block;">
                            <label class="control-label"><fmt:message key="add.to.cart.item.size"
                                                                      bundle="${lang}"/></label>
                            <select class="form-control" id="addtocart-pizza-sizes">
                            </select>
                        </div>
                        <div class="col-sm-6"></div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6" style="display:inline-block; margin-top:2px;">
                            <label class="control-label"><fmt:message key="add.to.cart.item.toppings"
                                                                      bundle="${lang}"/></label>
                            <select class="form-control" id="addtocart-pizza-toppings1">
                            </select>
                        </div>
                        <div class="col-sm-6" style="display:inline-block; margin-top:2px;">
                            <label class="control-label">&nbsp;</label>
                            <select class="form-control" id="addtocart-pizza-toppings2">
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="display:inline-block; margin-top:2px; margin-left:15px;">
                        <label class="control-label"><fmt:message key="add.to.cart.item.instr"
                                                                  bundle="${lang}"/></label>
                        <textarea class="form-control" cols="80"
                                  placeholder="Mention any special instructions" id="addtocart-pizza-instructs"
                                  style="resize: vertical;"></textarea>
                    </div>
                    <div class="form-group" style="margin-left:15px;">
                        <label class="control-label" style="padding-right:15px;"><fmt:message key="add.to.cart.item.qty"
                                                                                              bundle="${lang}"/></label>
                        <input id="addtocart-pizza-qty" class="spin" type="number" min="1" max="100" value="1"
                               style="width: 3em;">
                        <label class="control-label"
                               style="padding-left:30px; padding-right:10px;"><fmt:message key="add.to.cart.item.price"
                                                                                           bundle="${lang}"/>&nbsp;&nbsp;</label>
                        <input type="text" id="addtocart-pizza-price" readonly style="width:4em">
                        <label class="control-label"
                               style="padding-left:30px; padding-right:10px;"><fmt:message key="add.to.cart.item.total"
                                                                                           bundle="${lang}"/>&nbsp;&nbsp;</label>
                        <input type="text" id="addtocart-pizza-total" readonly style="width:8em">
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="margin-top:-30px;">
                <a class="btn btn-default btn-sm" data-dismiss="modal"><fmt:message key="btn.cancel.text"
                                                                                    bundle="${lang}"/></a>
                <button class="btn btn-success btn-popup-add-to-cart-pizza btn-sm"><fmt:message key="btn.addtocart.text"
                                                                                                bundle="${lang}"/></button>
            </div>
        </div>
    </div>
</div>
<%-- End Add to cart modal --%>

<%-- Modal shows details of the items of an order --%>
<div id="order-items-popup" class="modal fade">
    <div class="modal-dialog" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <img class="icons" src="<c:url value="/resources/images/icons/item_list.ico"/>">
                <h4 class="modal-title header-panel"><fmt:message key="purchase.history.order.items.header"
                                                                  bundle="${lang}"/></h4>
            </div>
            <div class="modal-body">
                <div id="table-orderItems"></div>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default btn-sm" data-dismiss="modal"><fmt:message key="purchase.history.model.close"
                                                                                    bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>
<%-- End Item details modal --%>

<!-- Start Payment data modal -->
<div class="modal fade" id="modal-payment">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i>
                </button>
                <img class="icons" src="<c:url value="/resources/images/icons/payment_history.png"/>"/>
                <h4 class="modal-title header-panel">&nbsp;<fmt:message key="payment.modal.header"
                                                                        bundle="${lang}"/></h4>
            </div>
            <div class="modal-body" style="padding: 10px;">
                <div id="payment-header-div"></div>
                <div class="row">
                    <div class="col-md-7" id="payment-data-div" style="font-size: 16px;"></div>
                    <div class="col-md-5">
                        <img src="<c:url value="/resources/images/paid.png"/>" width="180px" height="180px"
                             style="margin-left: -50px;">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default btn-sm" data-dismiss="modal"><fmt:message key="payment.modal.close"
                                                                                    bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>
<!-- End Payment data modal -->

<!-- Start Delivery data modal -->
<div class="modal fade" id="modal-delivery">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i>
                </button>
                <img class="icons" src="<c:url value="/resources/images/icons/delviery_history.png"/> "/>
                <h4 class="modal-title header-panel">&nbsp;<fmt:message key="delivery.modal.header"
                                                                        bundle="${lang}"/></h4>
            </div>
            <div class="modal-body" style="padding: 10px;">
                <div id="delivery-header-div"></div>
                <div class="row">
                    <div class="col-md-7" id="delivery-data-div" style="font-size: 16px;"></div>
                    <div class="col-md-5" id="delivery-data-img-div">dgdfgd</div>
                </div>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default btn-sm" data-dismiss="modal"><fmt:message key="delivery.modal.close"
                                                                                    bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>
<!-- End Delivery data modal -->