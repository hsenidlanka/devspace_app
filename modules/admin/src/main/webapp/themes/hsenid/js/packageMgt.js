$(document).ready(function(){

    /*
     * Function for loading package content to modal for editing
     */
    $("#lnkModalView").click(function () {
        $("#pkgViewModal").modal();
    });


   /* $("#chkPkgPzza").check(function(){
        //onclick="document.getElementById('txtLrgPrc').disabled=!this.checked;"
        // onclick="document.getElementsByClassName('rowpizza').disabled=!this.checked;"
        $('.rowpizza').prop("disabled",false);
    });
*/

    /*
     * populating the item list in add Package.jsp
     **/
    $(".chkbxPkgCat").click(function () {
        var categoryNm = $(this).val();
        var slctElement =$(this).parent().parent().next().find(".form-control").attr('id');
        console.log($(this).parent().parent().next().find(".form-control").css('color','green'));
        alert(slctElement);
        setItemList(categoryNm,slctElement);
    });


});
function setItemList(categoryNm,slctElement) {
    $.ajax({
        type: "POST",
        url: "https://localhost:8443/admin/packages/getItemNames",
        data: {"categoryNm": categoryNm},

        success: function (data) {
            var slctItemsPkg = $("#"+slctElement), option = "";
            slctItemsPkg.empty();

            for (var itm = 0; itm < data.length; itm++) {
                option = option + "<option value='" + data[itm].itemName + "'>" + data[itm].itemName + "</option>";
            }
            slctItemsPkg.append(option);
        },
        error: function (e) {
            alert("error " + e.message);
        }
    });
}