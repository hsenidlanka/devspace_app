$(document).ready(function () {

    $("#fromDateOdr").datepicker({
        "changeMonth":true,
        "changeYear":true
    });

    $("#toDateOdr").datepicker({
        "changeMonth":true,
        "changeYear":true
    });
 /*   var pgLimit = 10;
    var pkgName = $("#txtViewSearchPkg").val();*/

    /* $.ajax({
       url: "https://localhost:8443/admin/packages/loadSearchPackage",
        dataType: "json",
        data: {"pkgName": pkgName, "initPage": "1", "pgLimit": pgLimit},
        success: function (result) {*/

            $("#tblOrders").bootstrapTable({
                dataType:'JSON',
                url: 'https://localhost:8443/admin/orders/view/orderTable',
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
                    field: 'order_id',
                    title: 'Order ID',
                    sortable: true
                },/*{
                    field: 'date',
                    title: 'Order Date',
                    sortable: true
                },*/ {
                    field: 'net_cost',
                    title: 'Order Price (LKR)',
                    align: 'right',
                    sortable: true
                },{
                    field: 'customer_id',
                    title: 'Customer ID',
                    align: 'right',
                    sortable: true
                },{
                    field: 'guest_id',
                    title: 'Guest ID',
                    align: 'right',
                    sortable: true
                },{
                    field: 'order_time',
                    title: 'Time',
                    align: 'right',
                    sortable: true
                },{
                    field: 'order_date',
                    title: 'Date',
                    align: 'right',
                    sortable: true
                }/*, {
                    field: 'operate',
                    title: 'Operations',
                    align: 'center',
                    formatter: operateFormatter,
                    events: operateEvents
                }*/]
           // })
       // }
    });

    $("#toDateOdr").click(function(){
        if(!$(this).null || ""){
            if($("#fromDateOdr").null || ""){
                var todate = $(this).val();
                var fromDate = $("#fromDateOdr").val();
                $.ajax({
                    type:'get',
                    url:'',
                    data:{'todate':todate, 'fromDate':fromDate},
                    success:function(data){

                    }
                })
            }


        }
    });

    /*
     * typeahead function for load pkgNames
     * */
    /*$("#txtViewSearchOrdr").keyup(function () {
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
    });*/

/*
    var pag2 = $('#pagination6').simplePaginator({

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

            $.ajax({
                url: 'https://localhost:8443/admin/packages/loadSearchPackage',
                dataType: "json",
                data: {"srchPkgNm": $("#txtViewSearchPkg").val(), "initPage": page, "pgLimit": pgLimit},
                success: function (data) {

                    $('#tblPackages').bootstrapTable('load', data);
                }
            })
        }
    });
*/

    /*
     * load data on request typeahead
     **/
   /* $("#btnViewSearchOrdr").click(function () {

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

            *//**
             *Setting the number of pages according to the number of records
             *//*
            $.ajax({
                url: 'https://localhost:8443/admin/packages/packagePaginationTable',
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
    });*/
});

function operateFormatter(value, row, index) {
    return [
        '<center>',
        '<a class="editOrdr" title="Edit" id="linkEditOrdr">',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="deleteOrdr" title="Cancel" id="linkDeleteOrdr">',
        '<i class="glyphicon glyphicon-remove">Cancel</i>',
        '</a></center>'
    ].join('');
}

window.operateEvents = {

    'click .editOrdr': function (e, value, row, index) {

        var data = JSON.stringify(row);
        var objct = JSON.parse(data);

        var pkgId = objct["id"];

        $('#ordrEditModal').modal('show');
    },

    'click .deleteOrdr': function (e, value, row, index) {

        var dataDelt = JSON.stringify(row);
        var deltObj = JSON.parse(dataDelt);

        $('#lblDeltPkgId').text(deltObj["packageId"]);
        $('#lblDeltPkgName').text(deltObj["packName"]);

        $('#pkgDeleteModal').modal('show');
    }
};


