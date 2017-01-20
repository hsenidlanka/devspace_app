/*
 * default pagination function
 */

$(document).ready(function () {
    var pgLimit = 10;
    /**
     * ajax function for getting records count
     */
    $("#pagination6").hide();

    $.ajax({
        url: 'https://localhost:8443/admin/orders/orderPaginationTable',
        success: function (recCount) {
            pag.simplePaginator('setTotalPages', Math.ceil(recCount / 10));
        }
    });

    var pag = $('#pagination5').simplePaginator({
        // options here

        // the number of total pages
        totalPages: 7,

        // maximum of visible buttons
        maxButtonsVisible: 5,

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

            var initpg = (page-1)*pgLimit;
            $.ajax({
                url: 'https://localhost:8443/admin/orders/view/orderTable',
                dataType: "json",
                data: {"initPage": initpg, "pgLimit": pgLimit},
                success: function (data) {
                    $('#tblOrders').bootstrapTable('load', data);
                }
            })
        }
    });
})

