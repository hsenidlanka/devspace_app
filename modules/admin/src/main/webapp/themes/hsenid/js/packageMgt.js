$(document).ready(function () {

    /*
     * Price validation function to allow only 2 decimals
     * */
    $('.price').keypress(function (event) {
        var $this = $(this);
        if ((event.which != 46 || $this.val().indexOf('.') != -1) &&
            ((event.which < 48 || event.which > 57) &&
            (event.which != 0 && event.which != 8))) {
            event.preventDefault();
        }

        var text = $(this).val();
        if ((event.which == 46) && (text.indexOf('.') == -1)) {
            setTimeout(function () {
                if ($this.val().substring($this.val().indexOf('.')).length > 3) {
                    $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
                }
            }, 1);
        }

        if ((text.indexOf('.') != -1) &&
            (text.substring(text.indexOf('.')).length > 2) &&
            (event.which != 0 && event.which != 8) &&
            ($(this)[0].selectionStart >= text.length - 2)) {
            event.preventDefault();
        }
    })

/*
* Function for clone and appending content table dynamically
**/
    $.ajax({
        type: 'POST',
        url: 'https://localhost:8443/admin/packages/getCatListContent',
        success: function (res) {

            console.log("dddd = " + res + res.length);

            var n = res.length;

            for (i = 0; i < n; i++) {
                $('.rowtbl:first').clone().after("#id")
                    .appendTo('.tstDiv')
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
            console.log("Error dd ",e);
            console.log(e.message);
        }
    });

    /*
     * populating the item list in add Package.jsp
     **/
    $(document.body).on('click', '.chkbxPkgCat', function () {
        var categoryNm = $(this).val();
        var slctElement = $(this).parent().parent().next().find(".form-control").attr('id');
        var selectedItm = $(this).parent().parent().next().next().find(".form-control").attr('id');

        console.log($(this).val());
        console.log($(this).parent().parent().next().find(".form-control").css('color', 'green'));
        console.log($(this).parent().parent().next().next().find(".form-control").css('color', 'rgb(167, 68, 7)'));

        setItemList(categoryNm, slctElement, selectedItm);
    });

    /* function disableTxt(t, textBoxId) {
     $("#" + textBoxId).prop("disabled", !$(t).prop("checked"));
     $("#" + textBoxId).val(" ");
     }*/


});
function setItemList(categoryNm, slctElement, selectedItm) {
    $.ajax({
        //type: "POST",
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
            alert("error " + e);
        }

    });
}
/*
 * Common function for populate item size and price in
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
                option = option + "<option value='" + value + "'>" + value + "-" + parsedData[value] + "</option>";
            });
            sizePriceList.append(option);
        },
        error: function (er) {
            alert(er);
        }
    });
}

function contentPackge(event) {
    jsonObj2 = [];
    content = {};

    if ($('#chkPkgCat0').is(':checked')) {
        var itemVal = $('#slctItmPkgCat0').val();
        var sizeVal = $("#szpr0").val();
        var qtyVal = $("#contQty0").val();

        content = {};
        content["itemName"] = itemVal;
        content["size"] = sizeVal;
        content["quantity"] = qtyVal;

        jsonObj2.push(content);
        jsonString = JSON.stringify(jsonObj2);
    }
    if ($('#chkPkgCat1').is(':checked')) {
        var itemVal2 = $('#slctItmPkgCat1').val();
        var sizeVal2 = $("#szpr1").val();
        var qtyVal2 = $("#contQty1").val();

        content = {};
        content["itemName"] = itemVal2;
        content["size"] = sizeVal2;
        content["quantity"] = qtyVal2;

        jsonObj2.push(content);
        jsonString = JSON.stringify(jsonObj2);
    }
    if ($('#chkPkgCat2').is(':checked')) {
        var itemVal3 = $('#slctItmPkgCat2').val();
        var sizeVal3 = $("#szpr2").val();
        var qtyVal3 = $("#contQty2").val();

        content = {};
        content["itemName"] = itemVal3;
        content["size"] = sizeVal3;
        content["quantity"] = qtyVal3;

        jsonObj2.push(content);
        jsonString = JSON.stringify(jsonObj2);
    }
    if ($('#chkPkgCat3').is(':checked')) {
        var itemVal4 = $('#slctItmPkgCat3').val();
        var sizeVal4 = $("#szpr3").val();
        var qtyVal4 = $("#contQty3").val();

        content = {};
        content["itemName"] = itemVal4;
        content["size"] = sizeVal4;
        content["quantity"] = qtyVal4;

        jsonObj2.push(content);
        jsonString = JSON.stringify(jsonObj2);
    }
    if ($('#chkPkgCat4').is(':checked')) {
        var itemVal5 = $('#slctItmPkgCat4').val();
        var sizeVal5 = $("#szpr4").val();
        var qtyVal5 = $("#contQty4").val();

        content = {};
        content["itemName"] = itemVal5;
        content["size"] = sizeVal5;
        content["quantity"] = qtyVal5;

        jsonObj2.push(content);

    }
    var pkgName = $("#txtAddPkgName").val();
    var pkgPrice = $("#txtAddPkgPrice").val();
    var pkgImg = $("#savePkgImg").val();

    console.log("pp " + jsonObj2);
    console.log("pp2 " + jsonString);

 /*   if (jsonObj2.length() == 0) {
        $.toaster({priority: 'warning', title: 'Warning !', message: 'Please fill the package content for ' + pkgName});
    }*/

    $.ajax({
        url: "https://localhost:8443/admin/packages/add_package",
        data: {"test": JSON.stringify(jsonObj2), "pkgName": pkgName, "pkgPrice": pkgPrice, "pkgImg": pkgImg},
        type: "POST",
        success: function () {
            $("#btnAddNewPkg").click(function(event) {
                event.preventDefault();
                showloadingif();

                setTimeout(function() {
                    $("#frmAddPkg").submit();
                }, 3000);
            });
            window.location.href += "#last";
            location.reload(true);
        },
        error: function (e) {
         //   $.toaster({priority: 'danger', title: 'Error', message: 'Cannot add the package ' + pkgName});
        }
    });
}

/*

 $.each( '.chkbxPkgCat', function( key, value ) {
 jsonObj2 = [];
 if($('.chkbxPkgCat').is(':checked')){
 var itemVal = $('#slctItmPkgPzza').val();
 var sizeVal = $("#szpr1").val();
 var qtyVal = $("#pzzaQty").val();

 content = {};
 content["itemName"] = itemVal;
 content["size"] = sizeVal;
 content["quantity"] = qtyVal;

 jsonObj2.push(content);
 jsonString = JSON.stringify(jsonObj2);
 }
 alert(key, value);
 console.log(jsonObj2);
 console.log(jsonString);
 });*/

///
/*if((result.length) != 0){
 $(".btnAddItmPkg").replaceWith("<div class=\"add-status\"><span class=\"glyphicon glyphicon-ok\"></span> Finish</div>");
 }*/

/*TO DO*/
//automate jsonObject fetching*/