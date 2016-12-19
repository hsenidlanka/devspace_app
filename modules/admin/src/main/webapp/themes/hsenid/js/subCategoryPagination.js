/*
 * default pagination function for sub-category view
 */

$(document).ready(function () {
    var pgLimit = 3;
    /**
     * ajax function for getting records count
     */
    $("#pagination2SubCat").hide();

    $.ajax({
        url: 'https://localhost:8443/admin/SubCategoryPaginationTable',
        success: function (recCount) {
            pag.simplePaginator('setTotalPages', Math.ceil(recCount / 3));
        }
    });

    var pag = $('#paginationSubCat').simplePaginator({
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
            var value1=$("table").attr("data-value");
            $.ajax({
                url: 'https://localhost:8443/admin/view/subcategoryTable/'+value1,
                dataType: "json",
                data: {"initPage": page, "pgLimit": pgLimit},
                success: function (data) {
                    $('#tableCategory').bootstrapTable('load', data);
                }
            })
        }
    });
});

