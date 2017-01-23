
/////////////////////////////////////////  default pagination function for order Process////////////////////////////////

$(document).ready(function () {
    var pgLimit = 10;

    /**
     * ajax function for getting records count
     */
    $("#pagination2Delivery").hide();

    $.ajax({
        url: 'https://localhost:8443/admin/processOrders/delivery/count',
        success: function (recCount) {
            //alert(recCount);
            pagOrder1.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
        }
    });

    var pagOrder1 = $('#pagination1Delivery').simplePaginator({
        // the number of total pages
        totalPages: 7,

        // maximum of visible buttons
        maxButtonsVisible: 10,

        /*setTotalPages:10,*/

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
            var pageSend=(page-1)*pgLimit;
            //alert(pageSend);
            $.ajax({
                url: 'https://localhost:8443/admin/processOrders/view/orderProcessTable',
                dataType: "json",
                data: {"initPage":pageSend, "pgLimit": pgLimit},
                success: function (data) {
                    $('#tableprocessOrder').bootstrapTable('load', data);
                }
            })
        }
    });


    //typical extra  pagination for search functionality
    var pagOrder2 = $('#pagination2Delivery').simplePaginator({

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
            var pageSend=(page-1)*pgLimit;
            var searchDelivery = $("#txtViewSearchOrdr").val();

            if(searchDelivery == "") {
                $.ajax({
                    url: 'https://localhost:8443/admin/processOrders/view/orderProcessTable',
                    dataType: "json",
                    data: {"initPage": pageSend, "pgLimit": pgLimit},
                    success: function (data) {
                        $('#tableprocessOrder').bootstrapTable('load', data);
                    }
                })
            }else{
                $.ajax({
                    url: 'https://localhost:8443/admin/processOrders/filter/byDeliverStatus',
                    dataType: "json",
                    data: {"searchDelivery": searchDelivery,"initPage": pageSend, "pgLimit": pgLimit},
                    success: function (data) {
                        $('#tableprocessOrder').bootstrapTable('load', data);
                    }
                })
            }
        }
    });

    //for banned staff table data filter
    $("#txtViewSearchOrdr").keyup(function () {
        var searchDelivery = $("#txtViewSearchOrdr").val();

        $('#pagination1Delivery').hide();
        $('#pagination2Delivery').show();

        $.ajax({
            url: "https://localhost:8443/admin/processOrders/filter/byDeliverStatus",
            data: {"searchDelivery ": searchDelivery ,"initPage": "0", "pgLimit": pgLimit},
            dataType: "json",
            success: function (data) {
                //alert(" value"+ data);
                $('#tableprocessOrder').bootstrapTable('load', data);
            },
            error: function (e) {
                alert("error, load search staff" + e);
                //console.log("error, load search staff" + e)
            }
        });
        /**
         *Setting the number of pages according to the number of records
         */
        $.ajax({
            url: 'https://localhost:8443/admin/processOrders/delivery/filteredCount',
            data: {"searchDelivery ": searchDelivery},
            success: function (recCount) {
               pagOrder2.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
            }
        });
    });






});