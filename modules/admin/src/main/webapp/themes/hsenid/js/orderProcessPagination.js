
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




});