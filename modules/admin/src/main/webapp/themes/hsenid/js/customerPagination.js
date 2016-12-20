
////////////////////////////////  default pagination function for Customers view (ACTIVE)///////////////////////////////////////////

$(document).ready(function () {
    var pgLimit = 5;
    /**
     * ajax function for getting records count
     */
    $("#pagination2Customer").hide();

    $.ajax({
        url: 'https://localhost:8443/admin/users/CustomerPaginationTable',
        success: function (recCount) {
            pag.simplePaginator('setTotalPages', Math.ceil(recCount /5));
        }
    });

    var pag = $('#paginationCustomer').simplePaginator({
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
                url: 'https://localhost:8443/admin/users/view/customerTable',
                dataType: "json",
                data: {"initPage": pageSend, "pageLimit": pgLimit},
                success: function (data) {
                    $('#tableCustomer').bootstrapTable('load', data);
                }
            })
        }
    });
});

////////////////////////////////  default pagination function for Customers view (INACTIVE)///////////////////////////////////////////


$(document).ready(function () {
    var pgLimit = 5;
    /**
     * ajax function for getting records count
     */
    $("#pagination2BannedCustomer").hide();

    $.ajax({
        url: 'https://localhost:8443/admin/users/BannedCustomerPaginationTable',
        success: function (recCount) {
            pag.simplePaginator('setTotalPages', Math.ceil(recCount /5));
        }
    });

    var pag = $('#paginationBannedCustomer').simplePaginator({
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

            $.ajax({
                url: 'https://localhost:8443/admin/users/view/bannedcustomerTable',
                dataType: "json",
                data: {"initPage": pageSend, "pageLimit": pgLimit},
                success: function (data) {
                    $('#tableBannedcustomer').bootstrapTable('load', data);
                }
            })
        }
    });
});