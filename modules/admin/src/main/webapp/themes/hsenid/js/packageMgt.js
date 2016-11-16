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


    ///////////////////////////////////////////
    $.ajax({
        type: 'POST',
        url: 'https://localhost:8443/admin/packages/getCatListContent',
        success: function (res) {

             console.log("dddd = " + res + res.length);

             var n = res.length;

             for (i = 0; i < n; i++) {
             $('.rowtbl:first').clone().after("#id")
             .appendTo('#tstDiv')
             .find('.chkbxPkgCat').val(res[i]).end()
             .find('.chkbxPkgCat1').text(res[i]).end()
             .find('*[id]').each(function () {
             $(this).attr('id', $(this).attr('id') + i)
             })
             }
              $('.rowtbl:first').hide();
        },
        error: function (e) {
            alert(e);
        }
    });

    /*
     * populating the item list in add Package.jsp
     **/
    $(document.body).on('click','.chkbxPkgCat',function(){
        var categoryNm = $(this).val();
        var slctElement = $(this).parent().parent().next().find(".form-control").attr('id');
        var selectedItm = $(this).parent().parent().next().next().find(".form-control").attr('id');

        console.log($(this).val());
        console.log($(this).parent().parent().next().find(".form-control").css('color', 'green'));
        console.log($(this).parent().parent().next().next().find(".form-control").css('color', 'rgb(167, 68, 7)'));

        setItemList(categoryNm, slctElement, selectedItm);
    });

    $(".chkbxPkgCat").click(function () {

    });

    /*
     * Package summary
     **/
    $('#txtAddPkgName').focusout(function () {
        var pkgName = $(this).val();
        $('#newPkgName').text(pkgName);

    });
    $('#txtAddPkgPrice').focusout(function () {
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

function contentPackge() {
    jsonObj = [];

    if ($('#chkPkgCat0').is(':checked')) {
        var itemVal = $('#slctItmPkgCat0').val();
        var sizeVal = $("#szpr0").val();
        var qtyVal = $("#contQty0").val();

        content = {};
        content["item"] = itemVal;
        content["size"] = sizeVal;
        content["qty"] = qtyVal;

        jsonObj.push(content);
        jsonString = JSON.stringify(jsonObj);
    }
    if ($('#chkPkgCat1').is(':checked')) {
        var itemVal2 = $('#slctItmPkgCat1').val();
        var sizeVal2 = $("#szpr1").val();
        var qtyVal2 = $("#contQty1").val();

        content = {};
        content["item"] = itemVal2;
        content["size"] = sizeVal2;
        content["qty"] = qtyVal2;

        jsonObj.push(content);
        jsonString = JSON.stringify(jsonObj);
    }
    if ($('#chkPkgCat2').is(':checked')) {
        var itemVal3 = $('#slctItmPkgCat2').val();
        var sizeVal3 = $("#szpr2").val();
        var qtyVal3 = $("#contQty2").val();

        content = {};
        content["item"] = itemVal3;
        content["size"] = sizeVal3;
        content["qty"] = qtyVal3;

        jsonObj.push(content);
        jsonString = JSON.stringify(jsonObj);
    }
    if ($('#chkPkgCat3').is(':checked')) {
        var itemVal4 = $('#slctItmPkgCat3').val();
        var sizeVal4 = $("#szpr3").val();
        var qtyVal4 = $("#contQty3").val();

        content = {};
        content["item"] = itemVal4;
        content["size"] = sizeVal4;
        content["qty"] = qtyVal4;

        jsonObj.push(content);
        jsonString = JSON.stringify(jsonObj);
    }
    if ($('#chkPkgCat4').is(':checked')) {
        var itemVal5 = $('#slctItmPkgCat4').val();
        var sizeVal5 = $("#szpr4").val();
        var qtyVal5 = $("#contQty4").val();

        content = {};
        content["item"] = itemVal5;
        content["size"] = sizeVal5;
        content["qty"] = qtyVal5;

        jsonObj.push(content);

    }

    console.log("pp "+jsonObj);
    console.log("pp2 "+jsonString);
    jsonString = JSON.stringify(jsonObj);

    $.ajax({
        url: "https://localhost:8443/admin/packages/add_package",
        data:{"test":JSON.stringify(jsonObj)},
        // type: "POST",
        success: function (result) {
        }
    });
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
