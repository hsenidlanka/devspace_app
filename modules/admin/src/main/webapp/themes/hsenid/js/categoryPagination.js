/*
 * default pagination function for category view
 */

$(document).ready(function () {
    var pgLimit = 3;
    /**
     * ajax function for getting records count
     */
    $("#pagination2Cat").hide();

    $.ajax({
        url: 'https://localhost:8443/admin/category/CategoryPaginationTable',
        success: function (recCount) {
            pag.simplePaginator('setTotalPages', Math.ceil(recCount / 3));
        }
    });

    var pag = $('#paginationCat').simplePaginator({
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

            $.ajax({
                url: 'https://localhost:8443/admin/category/view/categoryTable',
                dataType: "json",
                data: {"initPage": page, "pgLimit": pgLimit},
                success: function (data) {
                    $('#tableCategory').bootstrapTable('load', data);
                }
            })
        }
    });
});

