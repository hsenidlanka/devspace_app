<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!--
modal content for edit Package content
-->
<form:form class="form-horizontal" role="form" id="frmEditPkg" action="/edit_package" method="post">
    <fieldset class="scheduler-border">
        <legend class="scheduler-border"> Edit Package Content</legend>

        <div class="form-group">
            <div class="row">
                <form:label class="col-xs-3 control-label" path="lblPkgDetl">
                    Package Detail :
                </form:label>
            </div>
            <br>

            <div class="row"  style="margin: auto;">

                <div class="col-xs-12">

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
                            <form:label class="checkbox-inline" path="lblchkedt1">
                            <form:input type="checkbox" value="" class="checkbox" path="chkpkgeEdt1"></form:input>
                            Pizza
                            </form:label>
                        </div>
                        <div class="col-xs-4">
                            <form:input type="text" value="" class="form-control price" path="txtpkgEdtprc1"></form:input>
                        </div>
                        <div class="col-xs-2">
                            <form:input class="qty-spinner" type="number" value="0" path="qtyspEdt1"></form:input>
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
                            <form:label class="checkbox-inline" path="lblchkpkgEdt2">
                            <form:input type="checkbox" value="" class="checkbox" path="chkpkgEdt2"></form:input>
                            Pasta/Appetizer
                            </form:label>
                        </div>
                        <div class="col-xs-4">
                            <form:input type="text" value="" class="form-control price" path="txtpkgEdtprc2"></form:input>
                        </div>
                        <div class="col-xs-2">
                            <form:input class="qty-spinner" type="number" value="0" path="qtyspEdt2"></form:input>
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
                            <form:label class="checkbox-inline" path="lblchkpkgEdt3">
                            <form:input type="checkbox" value="" class="checkbox" path="chkpkgEdt3"></form:input>
                            Bevarage
                            </form:label>
                        </div>
                        <div class="col-xs-4">
                            <form:input type="text" value="" class="form-control price" path="txtpkgedtprc3"></form:input>
                        </div>
                        <div class="col-xs-2">
                            <form:input class="qty-spinner" type="number" value="0" path="qtyspEdt3"></form:input>
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
                            <form:label class="checkbox-inline" path="lblchkEdt4">
                            <form:input type="checkbox" value="" class="checkbox" path="chkpkgEdt4"></form:input>
                            Salad
                            </form:label>
                        </div>
                        <div class="col-xs-4">
                            <form:input type="text" value="" class="form-control price" path="txtpkgEdtprc4"></form:input>
                        </div>
                        <div class="col-xs-2">
                            <form:input class="qty-spinner" type="number" value="0" path="qtyspEdt4"></form:input>
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
                            <form:label class="checkbox-inline" path="lblchkpkgEdt5">
                            <form:input type="checkbox" value="" class="checkbox" path="chkpkgedt5"></form:input>
                                Dessert
                            </form:label>
                        </div>
                        <div class="col-xs-4">
                            <form:input type="text" value="" class="form-control price" path="txtpkgEdtprc5"></form:input>
                        </div>
                        <div class="col-xs-2">
                            <form:input class="qty-spinner" type="number" value="0" path="qtyspEdtpkg5"></form:input>
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
    </fieldset>
</form:form>
<script src="../js/itemMgt.js"></script>