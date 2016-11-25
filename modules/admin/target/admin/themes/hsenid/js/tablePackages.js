$(document).ready(function(){

    $("#tblPackages").bootstrapTable({
        dataType:'JSON',
        url: 'https://localhost:8443/admin/packages/view/packageTable',
        height: 400,
        striped: true,
        pagination: true,
        pageSize: 10,
        pageList: [10, 25, 50, 100, 200],
        search: false,
        showColumns: false,
        showRefresh: false,
        minimumCountColumns: 2,
        columns: [{
            field: 'packageId',
            title: 'Package ID',
            sortable: true
        }, {
            field: 'packName',
            title: 'Package Name',
            sortable: true
        },{
            field: 'price',
            title: 'Package Price',
            align: 'right',
            sortable: true
        },{
            field: 'image',
            title: 'Package Image',
            sortable: true
        },{
            field: 'operate',
            title: 'Operations',
            align: 'center',
            formatter: operateFormatter,
            events: operateEvents
        }]
    });


    $("#btnDeltPkg").click(function () {
        var pkgName = $("#lblDeltPkgName").text();
        $.ajax({
            type: "POST",
            url: "https://localhost:8443/admin/packages/delete_package",
            data: {"pkgName": pkgName},

            success: function (data) {
                $.toaster({priority: 'success', title: 'Success', message: 'Deleted the item  ' + pkgName});
                setTimeout(function () {
                    location.reload(); }, 3000);
            },
            error: function (e) {
                $.toaster({priority: 'danger', title: 'Error', message: 'Cannot delete the item ' + pkgName});
                setTimeout(function () {
                    location.reload(); }, 3000);
            }
        })
    });
});

function operateFormatter(value, row, index) {
    return [
        '<center>',
        '<a class="editPkg" title="Edit" id="linkEditPkg">',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="deletePkg" title="Delete" id="linkDeletePkg">',
        '<i class="glyphicon glyphicon-remove">Delete</i>',
        '</a></center>'
    ].join('');
}

window.operateEvents = {

    'click .editPkg': function (e, value, row, index) {

        var data = JSON.stringify(row);
        var objct = JSON.parse(data);

        var pkgId = objct["packageId"];

        $("#txtEditPkgNm").val(objct["packName"]);
        $("#editPkgPrice").val(objct["price"]);

        $('#pkgEditModal').modal('show');
    },

    'click .deletePkg': function (e, value, row, index) {

        var dataDelt = JSON.stringify(row);
        var deltObj = JSON.parse(dataDelt);

        $('#lblDeltPkgId').text(deltObj["packageId"]);
        $('#lblDeltPkgName').text(deltObj["packName"]);

        $('#pkgDeleteModal').modal('show');
    }
};

function contentPackgeEdit() {
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
        jsonString = JSON.stringify(jsonObj2);
    }
    var pkgName = $("#txtEditPkgNm").val();
    var pkgPrice = $("#editPkgPrice").val();
    var pkgImg = $("#btnUpldImageEdit").val();

    console.log("pp " + jsonObj2);
   //console.log("pp2 " + jsonString);


    $.ajax({
        url: "https://localhost:8443/admin/packages/edit_package",
        data: {"test2": JSON.stringify(jsonObj2), "pkgName2": pkgName, "pkgPrice2": pkgPrice, "pkgImg2": pkgImg},
        type: "POST",
        success: function (result) {
            $("#btnUpdtPkg").submit(function (event) {
                event.preventDefault();
                showloadingif();

                setTimeout(function () {
                    $("#frmEditPkg").submit();
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