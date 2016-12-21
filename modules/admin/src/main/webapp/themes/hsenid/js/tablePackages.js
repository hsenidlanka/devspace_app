$(document).ready(function () {

    var pgLimit = 10;
    var pkgName = $("#txtViewSearchPkg").val();

    $.ajax({
        url: "https://localhost:8443/admin/packages/loadSearchPackage",
        dataType: "json",
        data: {"pkgName": pkgName, "initPage": "1", "pgLimit": pgLimit},
        success: function (result) {

            $("#tblPackages").bootstrapTable({
                height: 400,
                striped: true,
                pagination: false,
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
                }, {
                    field: 'price',
                    title: 'Package Price (LKR)',
                    align: 'right',
                    sortable: true
                }, {
                    field: 'operate',
                    title: 'Operations',
                    align: 'center',
                    formatter: operateFormatter,
                    events: operateEvents
                }],
                data: result
            });
        }
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
                    location.reload();
                }, 3000);
            },
            error: function (e) {
                $.toaster({priority: 'danger', title: 'Error', message: 'Cannot delete the item ' + pkgName});
                setTimeout(function () {
                    location.reload();
                }, 3000);
            }
        })
    });

    /*
     * typeahead function for load pkgNames
     * */
    $("#txtViewSearchPkg").keyup(function () {
        $.ajax({
            type: "GET",
            url: "https://localhost:8443/admin/packages/typeahedPkgNm",
            dataType: "JSON",
            success: function (data) {
                console.log(data);
                $('#txtViewSearchPkg').typeahead({
                    source: data
                }).focus();
            },
            error: function (er) {
                console.log("error in typeahead " + er)
            }
        })
    });

    var pag2 = $('#pagination4').simplePaginator({

        // the number of total pages
        totalPages: 7,

        // maximum of visible buttons
        maxButtonsVisible: 5,

        // page selected
        currentPage: 1,

        // text labels for buttons
        nextLabel: 'next',
        prevLabel: 'prev',
        firstLabel: 'first',
        lastLabel: 'last',

        // specify if the paginator click in the currentButton
        clickCurrentPage: true,

        // called when a page is changed.
        pageChange: function (page) {

            var initpg = (page-1)*pgLimit;
            $.ajax({
                url: 'https://localhost:8443/admin/packages/loadSearchPackage',
                dataType: "json",
                data: {"srchPkgNm": $("#txtViewSearchPkg").val(), "initPage": initpg, "pgLimit": pgLimit},
                success: function (data) {

                    $('#tblPackages').bootstrapTable('load', data);
                }
            })
        }
    });

    /*
     * load data on request typeahead
     **/
    $("#btnViewSearchPkg").click(function () {

        // var srchPkgNm = $("#txtViewSearchPkg").val();

        if ($("#txtViewSearchPkg").val().length > 0) {

            $('#pagination3').hide();
            $('#pagination4').show();

            $.ajax({
                url: "https://localhost:8443/admin/packages/loadSearchPackage",
                datatype: "JSON",
                data: {"srchPkgNm": $("#txtViewSearchPkg").val(), "initPage": "1", "pgLimit": pgLimit},
                success: function (data) {
                    $("#tblPackages").bootstrapTable('load', data);
                    console.log(data);
                },
                error: function (e) {
                    alert("error, load search item" + e);
                    console.log("error, load search item" + e)
                }
            })

            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/packages/searchPkgCount',
                data: {"srchPkgNm": $("#txtViewSearchPkg").val()},
                success: function (recCount) {

                    pag2.simplePaginator('setTotalPages', Math.ceil(recCount / 10));
                }
            })

        } else {
            $('#pagination3').show();
            $('#pagination4').hide();

            $.ajax({
                url: 'https://localhost:8443/admin/packages/loadSearchPackage',
                dataType: 'JSON',
                data: {"initPage": "1", "pgLimit": pgLimit},
                success: function (data) {

                    $('#tblPackages').bootstrapTable('load', data);
                }
            })
        }
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


/*
 * function for editing package content
 **/
function contentPackageEdit() {

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

    var pkgName = $("#txtEditPkgNm").val();

    $.ajax({
        url: "https://localhost:8443/admin/packages/edit_content",
        data: {"test2": JSON.stringify(jsonObj2)},
        type: "GET",
        success: function () {
            $('.btnAddItmPkg').replaceWith("<div class=\"add-status\"><span class=\"glyphicon glyphicon-ok\"></span> Content Created</div>");
        },
        error: function (e) {
            $.toaster({priority: 'danger', title: 'Error', message: 'Cannot update the package ' + pkgName});
            alert("error ddddd" + e);
            console.log("error occurred  ", e);
        }
    });
}

