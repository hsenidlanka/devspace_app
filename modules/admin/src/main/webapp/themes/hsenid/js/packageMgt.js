var categoryNm;
var accumulatedPrice;
var abc;
$(document).ready(function () {

    $("#txtAddPkgName").focusout(function () {
        if ($(this).val() == '') {
            document.getElementById('pkgNmErr').innerHTML = 'Package Name should not be empty !';
            $(this).addClass('invalid-input');
        }
    });

    $("#txtAddPkgPrice").change(function () {

        if ($(this).val() != null) {
            if ($(this).val() == '0.0' || $(this).val() == '0.00' || $(this).val() == '') {
                $(this).addClass('invalid-input');
                document.getElementById('pkgPriceErr').innerHTML = 'Package price is invalid !';
            } else {
                $(this).val(parseFloat($(this).val()).toFixed(2));
                document.getElementById('pkgPriceErr').innerHTML = " ";
                $(this).removeClass('invalid-input');
            }
        }
        else {
            document.getElementById('pkgPriceErr').innerHTML = 'Package price should not be empty !';
            $(this).addClass('invalid-input');
        }


        /* if ($(this).val() == '0.0' || null) {
         document.getElementById('pkgPriceErr').innerHTML = 'Package price should not be empty !';
         $(this).addClass('invalid-input');
         } else {
         document.getElementById('pkgPriceErr').innerHTML =" ";
         $(this).removeClass('invalid-input');
         $(this).val(parseFloat($(this).val()).toFixed(2));
         }*/
    });
    $("#editPkgPrice").focusout(function () {
        if ($(this).val() == '0.00' || null) {
            document.getElementById('pkgPriceErrEdt').innerHTML = 'Package price should not be empty !';
            $(this).addClass('invalid-input');
        } else {
            $(this).removeClass('invalid-input');
        }
        if ($(this).val != null) {
            $(this).val(parseFloat($(this).val()).toFixed(2));
        }
    });

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
            console.log("Error dd ", e);
            console.log(e.message);
        }
    });

    /*
     * populating the item list in add Package.jsp and contentEdit in viewPackage.jsp
     **/
    $(document.body).on('click', '.chkbxPkgCat', function () {

        categoryNm = $(this).val();
        var slctElement = $(this).parent().parent().next().find(".form-control").attr('id');
        var selectedItm = $(this).parent().parent().next().next().find(".form-control").attr('id');
        var selectedQty = $(this).parent().parent().next().next().next().find(".form-control");

        //
        console.log($(this).val());
        console.log($(this).parent().parent().next().find(".form-control").css({
            'color': 'green',
            'font-weight': 'bold'
        }));
        console.log($(this).parent().parent().next().next().find(".form-control").css({
            'color': 'rgb(49, 49, 64)',
            'font-weight': 'bold'
        }));
        //

        if ($(this).is(":checked")) {
            setItemList(categoryNm, slctElement, selectedItm, selectedQty);
            $(this).parent().parent().next().next().next().find(".form-control").val('1');

            selectedQty.click(function () {
                if ($.trim($(this).val()) == "0") {
                    selectedQty.css({"border-color": "red", "border-width": "2px"});
                }
                else {
                    selectedQty.css({"border-color": "", "border-width": ""});
                    priceCounting($("#" + selectedItm + " option:selected").text(), $(selectedQty).val());
                }
            });
        } else {
            $(this).parent().parent().next().find(".form-control").val('');
            $(this).parent().parent().next().next().find(".form-control").val('');
            $(this).parent().parent().next().next().next().find(".form-control").val('0');
            selectedQty.css({"border-color": "", "border-width": ""});
        }
    });

    /*if(($("#editPkgPrice").val()) != null) {
     $("#editPkgPrice").val(parseFloat($("#editPkgPrice").val()).toFixed(2));
     }*/
});


/*
 * function for getting item name list according to the selected category
 **/
function setItemList(categoryNmVal, slctElementVal, selectedItmVal, itmQty) {

    $.ajax({
        url: "https://localhost:8443/admin/packages/getItemNames",
        data: {"categoryNm": categoryNmVal},

        success: function (data) {
            var slctItemsPkg = $("#" + slctElementVal), option = "";
            slctItemsPkg.empty();

            for (var itm = 0; itm < data.length; itm++) {
                option = option + "<option value='" + data[itm].itemName + "'>" + data[itm].itemName + "</option>";
            }
            slctItemsPkg.append(option);

            //get default size price on load
            generate(selectedItmVal, slctElementVal, itmQty);


            //get size price on change
            $("#" + slctElementVal).change(function () {
                generate(selectedItmVal, slctElementVal);
                priceCounting($("#" + selectedItmVal + " option:selected").text(), $(itmQty).val());
            });

            /*******************/
            $("#" + selectedItmVal).change(function () {
                priceCounting($("#" + selectedItmVal + " option:selected").text(), $(itmQty).val());
            });

        },
        error: function (e) {
            alert("error 2" + e);
        }
    });
}

/*
 * Common function for populate item size and price in
 **/
function generate(selectedItm, slctElement, itmQty) {
    var selectedName = $("#" + slctElement).val();

    $.ajax({
        type: "POST",
        url: "https://localhost:8443/admin/packages/getSizePrice",
        data: {"itemName": selectedName},

        success: function (dataList) {

            var parsedData = JSON.parse(dataList);
            var listlength = Object.keys(parsedData).length;
            console.log("size list " + dataList);
            console.log("sz-pc1 " + parsedData);

            var keys = Object.keys(parsedData);
            var abc = null;
            var sizePriceList = $("#" + selectedItm), option = "";
            sizePriceList.empty();
            $.each(keys, function (index, value) {
                option = option + "<option value='" + value + "'>" + value + "-" + parsedData[value] + "</option>";
                //abc= parsedData[value];
            });
            sizePriceList.append(option);

            //priceCounting(abc,$(itmQty).val());
            priceCounting($("#" + selectedItm + " option:selected").text(), $(itmQty).val());
        },
        error: function (er) {
            alert(er);
        }
    });
}

/*
 * function for creating package content
 **/
function contentCreate() {
    jsonObj2 = [];

    $('.chkbxPkgCat').each(function () {
        content = {};
        if ($(this).is(':checked')) {

            var itmval = $(this).parent().parent().next().find(".form-control").val();
            var slcted = $(this).parent().parent().next().next().find(".form-control").val();
            var selctqty = $(this).parent().parent().next().next().next().find(".form-control").val();

            content["itemName"] = itmval;
            content["size"] = slcted;
            content["quantity"] = selctqty;

            jsonObj2.push(content);
            jsonString = JSON.stringify(jsonObj2);
        }
    });
    console.log("pp " + jsonObj2);
    console.log("pp " + jsonObj2[0]['quantity']);
    console.log("pp24 " + jsonString);

    $.ajax({
        url: "https://localhost:8443/admin/packages/add_content",
        data: {"test": JSON.stringify(jsonObj2)},
        type: "GET",
        success: function () {
            $('.btnAddItmPkg').replaceWith("<div class=\"add-status\"><span class=\"glyphicon glyphicon-ok\"></span> Content Created</div>");
        },
        error: function (e) {
            alert("error occurred  ", e);
            console.log("error occurred  ", e);
            //   $.toaster({priority: 'danger', title: 'Error', message: 'Cannot add the package ' + pkgName});
        }
    });
}


/*
 * function for calculating accumulated package price
 **/
function priceCounting(prcField, qtyField) {
//TODO
    var totPrc = 0;
    var itmQty = qtyField;
    var resPrc = parseFloat((prcField.split('-')).pop());

    totPrc = totPrc +(resPrc * itmQty);
    alert(totPrc);

   var finalPrc = $(".lblAccumPrice1").text(totPrc);

    totPrc += parseFloat(finalPrc);
    alert(totPrc);
}
