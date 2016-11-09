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

        var slctElementVal = $(this).parent().parent().next().find(".form-control").val();
        var selectedItmVal = $(this).parent().parent().next().next().find(".form-control").val();
        var qtyVal = $(this).parent().parent().next().next().next().find(".form-control").val();
        var btnId = $(this).parent().parent().next().next().next().next().find(".form-control").val();

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
/*
* big deal package
* */


    function contentPackge(){


    }


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
};

function addtoSummary(itemNm, qty, btnID){

}