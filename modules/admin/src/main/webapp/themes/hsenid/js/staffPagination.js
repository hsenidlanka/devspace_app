
/////////////////////////////////////////  default pagination function for Staff view (ACTIVE)////////////////////////////////

$(document).ready(function () {
    var pgLimit = 5;
    /**
     * ajax function for getting records count
     */
    $("#pagination2Staff").hide();

    $.ajax({
        url: 'https://localhost:8443/admin/users/StaffPaginationTable',
        success: function (recCount) {
            //alert(recCount);
            pag.simplePaginator('setTotalPages', Math.ceil(recCount / 5));
        }
    });

    var pag = $('#paginationStaff').simplePaginator({
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
                url: 'https://localhost:8443/admin/users/view/staffTable',
                dataType: "json",
                data: {"initPage":pageSend, "pageLimit": pgLimit},
                success: function (data) {
                    $('#tableStaff').bootstrapTable('load', data);
                }
            })
        }
    });
});

/////////////////////////////////////////  default pagination function for Staff view (INACTIVE)////////////////////////////////

$(document).ready(function () {
    var pgLimit = 5;
    /**
     * ajax function for getting records count
     */
    $("#pagination2BannedStaff").hide();

    $.ajax({
        url: 'https://localhost:8443/admin/users/BannedStaffPaginationTable',
        success: function (recCount) {
            //alert(recCount);
            pag.simplePaginator('setTotalPages', Math.ceil(recCount / 5));
        }
    });

    var pag = $('#paginationBannedStaff').simplePaginator({
        // the number of total pages
        totalPages: 7,

        // maximum of visible buttons
        maxButtonsVisible: 10,

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
                url: 'https://localhost:8443/admin/users/view/bannedstaffTable',
                dataType: "json",
                data: {"initPage": pageSend, "pageLimit": pgLimit},
                success: function (data) {
                    $('#tableBannedstaff').bootstrapTable('load', data);
                }
            })
        }
    });
});