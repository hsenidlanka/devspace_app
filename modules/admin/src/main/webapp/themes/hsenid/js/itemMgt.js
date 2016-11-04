$(document).ready(function () {

    function validateInputs() {

        var itmName = document.getElementById('txtItemName');
        if (itmName.value == '') {
            document.getElementById('itmNmErr').innerHTML = 'Item Name should not be empty !';
            $(this).addClass('invalid-input');
        }
    }

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
     * populating the sub-category list in addItem.jsp
     **/
    $("#selectCat").change(function () {
        var categoryNm = $(this).val();
        $.ajax({
            type: "POST",
            url: "https://localhost:8443/admin/items/getSubcats",
            data: {"categoryNm": categoryNm},

            success: function (data) {
                var slctSubcat = $("#selectSubCat"), option = "";
                slctSubcat.empty();

                for (var sb = 0; sb < data.length; sb++) {
                    option = option + "<option value='" + data[sb] + "'>" + data[sb] + "</option>";
                }
                slctSubcat.append(option);
            },
            error: function (e) {
                alert("error " + e);
            }
        });

    });

    /*
     * populating the sub-category list in editItem.jsp
     **/
    $("#selectCatedt").change(function () {
        var categoryNm = $(this).val();
        setSubCats(categoryNm);
    });

});

/*
* common function for loading sub-categories according to the selected category in edit item form
**/
function setSubCats(categoryNm) {
    $.ajax({
        type: "POST",
        url: "https://localhost:8443/admin/items/getSubcats",
        data: {"categoryNm": categoryNm},

        success: function (data) {
            var slctSubcatEdt = $("#slctEditSubCat"), option = "";
            slctSubcatEdt.empty();

            for (var sb = 0; sb < data.length; sb++) {
                option = option + "<option value='" + data[sb] + "'>" + data[sb] + "</option>";
            }
            slctSubcatEdt.append(option);
        },
        error: function (e) {
            alert("error " + e);
        }
    });
}

/**
 * validate checkbox unclick-->textbox value = " " & disabling
 */
function disableTxt(t, textBoxId) {
    //noinspection JSJQueryEfficiency
    $("#" + textBoxId).prop("disabled", !$(t).prop("checked"));
    $("#" + textBoxId).val(" ");
}
