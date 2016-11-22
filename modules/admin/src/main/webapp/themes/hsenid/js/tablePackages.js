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