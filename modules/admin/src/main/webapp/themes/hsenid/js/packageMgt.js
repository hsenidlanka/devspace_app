$(document).ready(function () {

    /*
     * Function for loading package content to modal for editing
     */
    $("#lnkModalView").click(function () {
        $("#pkgViewModal").modal();
    });

    $('.btnAddItmPkg').click(function () {
       // if(($('.chkbxPkgCat').prop('checked()',true))){
            $(this).replaceWith("<div class=\"add-status\"><span class=\"glyphicon glyphicon-ok\"></span> Added</div>");
      //  }
    });

    /*
     * populating the item list in add Package.jsp
     **/
    $(".chkbxPkgCat").click(function () {
        var categoryNm = $(this).val();
        var slctElement = $(this).parent().parent().next().find(".form-control").attr('id');
        var selectedItm = $(this).parent().parent().next().next().find(".form-control").attr('id');

        console.log($(this).parent().parent().next().find(".form-control").css('color', 'green'));
        console.log($(this).parent().parent().next().next().find(".form-control").css('color', 'rgb(167, 68, 7)'));

        setItemList(categoryNm, slctElement, selectedItm);
    });

    /*
    * Package summary
    **/
$('#txtAddPkgName').focusout(function(){
   var pkgName = $(this).val();
    $('#newPkgName').text(pkgName);

});
$('#txtAddPkgPrice').focusout(function(){
   var pkgPrice = $(this).val();
    $('#newPkgPice').text(pkgPrice);

});



});
function setItemList(categoryNm, slctElement, selectedItm) {
    $.ajax({
        type: "POST",
        url: "https://localhost:8443/admin/packages/getItemNames",
        data: {"categoryNm": categoryNm},

        success: function (data) {
            var slctItemsPkg = $("#" + slctElement), option = "";
            slctItemsPkg.empty();

            for (var itm = 0; itm < data.length; itm++) {
                option = option + "<option value='" + data[itm].itemName + "'>" + data[itm].itemName + "</option>";
            }
            slctItemsPkg.append(option);

            //get default size price on load
            generate(selectedItm, slctElement);

            //get size price on change
            $("#" + slctElement).change(function () {
                generate(selectedItm, slctElement);
            });

        },
        error: function (e) {
            alert("error " + e.message);
        }

    });
}
/*
 * Common function for populate item size and price
 **/
function generate(selectedItm, slctElement) {
    var selectedName = $("#" + slctElement).val();

    $.ajax({
        // dataType:'JSON',
        type: "POST",
        url: "https://localhost:8443/admin/packages/getSizePrice",
        data: {"itemName": selectedName},

        success: function (dataList) {

            var parsedData = JSON.parse(dataList);
            var listlength = Object.keys(parsedData).length;
            console.log("size list " + dataList);
            console.log(parsedData);
            console.log(listlength);

            var keys = Object.keys(parsedData);
            var sizePriceList = $("#" + selectedItm), option = "";
            sizePriceList.empty();
            $.each(keys, function (index, value) {
                option = option + "<option value='" + parsedData[value] + "'>" + value + "-" + parsedData[value] + "</option>";
            });
            sizePriceList.append(option);
        },
        error: function (er) {
            alert(er);
        }
    });
}

function contentPackge(){
    jsonObj = [];

    if($('#chkPkgPzza').is(':checked')){
        var itemVal = $('#slctItmPkgPzza').val();
        var sizeVal = $("#szpr1").val();
        var qtyVal = $("#pzzaQty").val();

        content = {};
        content["item"] = itemVal;
        content["size"] = sizeVal;
        content["qty"] = qtyVal;

        jsonObj.push(content);
        jsonString = JSON.stringify(jsonObj);
    }
    if($('#chkPkgSld').is(':checked')){
        var itemVal2 = $('#slctItmPkgSalad').val();
        var sizeVal2 = $("#szprc2").val();
        var qtyVal2 = $("#sldQty").val();

        content = {};
        content["item"] = itemVal2;
        content["size"] = sizeVal2;
        content["qty"] = qtyVal2;

        jsonObj.push(content);
        jsonString = JSON.stringify(jsonObj);
    }
    if($('#chkPkgBvrg').is(':checked')){
        var itemVal3 = $('#slctItmPkgBvrg').val();
        var sizeVal3 = $("#szprc3").val();
        var qtyVal3 = $("#bvgQty").val();

        content = {};
        content["item"] = itemVal3;
        content["size"] = sizeVal3;
        content["qty"] = qtyVal3;

        jsonObj.push(content);
        jsonString = JSON.stringify(jsonObj);
    }
    if($('#chkPkgTppg').is(':checked')){
        var itemVal4 = $('#slctItmPkgToppng').val();
        var sizeVal4 = $("#szpr4").val();
        var qtyVal4 = $("#tppgQty").val();

        content = {};
        content["item"] = itemVal4;
        content["size"] = sizeVal4;
        content["qty"] = qtyVal4;

        jsonObj.push(content);
        jsonString = JSON.stringify(jsonObj);
    }
    if($('#chkPkgDssrt').is(':checked')){
        var itemVal5 = $('#slctItmPkgDssrt').val();
        var sizeVal5 = $("#szpr5").val();
        var qtyVal5 = $("#dssrtQty").val();

        content = {};
        content["item"] = itemVal5;
        content["size"] = sizeVal5;
        content["qty"] = qtyVal5;

        jsonObj.push(content);
        jsonString = JSON.stringify(jsonObj);
    }

    console.log(jsonObj);
    console.log(jsonString);
}

/*

$.each( '.chkbxPkgCat', function( key, value ) {
    jsonObj = [];
    if($('.chkbxPkgCat').is(':checked')){
        var itemVal = $('#slctItmPkgPzza').val();
        var sizeVal = $("#szpr1").val();
        var qtyVal = $("#pzzaQty").val();

        content = {};
        content["item"] = itemVal;
        content["size"] = sizeVal;
        content["qty"] = qtyVal;

        jsonObj.push(content);
        jsonString = JSON.stringify(jsonObj);
    }
    alert(key, value);
    console.log(jsonObj);
    console.log(jsonString);
});*/
