/*
 * default pagination function for sub-category view
 */

$(document).ready(function () {
    var pgLimit = 3;
    var value1=$("table").attr("data-value");

    /**
     * ajax function for getting records count
     */
    $("#pagination2SubCat").hide();

    /**
     * ajax function for getting records count
     */

    $.ajax({
        url: 'https://localhost:8443/admin/SubCategoryPaginationTable',
        data:{"catName":value1},
        success: function (recCount) {
            pagSubCat.simplePaginator('setTotalPages', Math.ceil(recCount / 3));
        }
    });

    var pagSubCat = $('#paginationSubCat').simplePaginator({
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
            var pageSend=(page-1)*pgLimit;
            $.ajax({
                url: 'https://localhost:8443/admin/view/subcategoryTable/'+value1,
                dataType: "json",
                data: {"initPage":pageSend, "pgLimit": pgLimit},
                success: function (data) {
                    $('#tableSubCategory').bootstrapTable('load', data);
                }
            })
        }
    });




    //typical pagination for sub category search
    var pagSubCat2 = $('#pagination2SubCat').simplePaginator({

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
                url: 'https://localhost:8443/admin/view/subcategoryTable/search/'+value1,
                dataType: "json",
                data: {"searchSubCatNm": $("#txtViewSearchSubCategory").val(), "initPage": pageSend, "pgLimit": pgLimit},
                success: function (data) {

                    $('#tableSubCategory').bootstrapTable('load', data);
                }
            })
        }
    });

    $("#btnViewSearchSubCat").click(function () {

        if ($("#txtViewSearchSubCategory").val().length > 0) {

            $('#paginationSubCat').hide();
            $('#pagination2SubCat').show();

            $.ajax({
                url: "https://localhost:8443/admin/view/subcategoryTable/search/"+value1,
                dataType: "json",
                data: {"searchSubCatNm": $("#txtViewSearchSubCategory").val(), "pgLimit": pgLimit, "initPage": "0"},
                success: function (data) {
                    $("#tableSubCategory").bootstrapTable('load', data);
                    console.log(data);
                },
                error: function (e) {
                    alert("error, load search item" + e);
                    console.log("error, load search item" + e)
                }
            });

            /**
             * ajax function for getting records count
             */

            $.ajax({
                url: 'https://localhost:8443/admin/SubCategoryPaginationTable',
                data:{"catName":value1},
                success: function (recCount) {
                    pagSubCat2.simplePaginator('setTotalPages', Math.ceil(recCount / 3));
                }
            });
        }

    });


    $("#txtViewSearchSubCategory").keyup(function () {

        $('#paginationSubCat').hide();
        $('#pagination2SubCat').show();

        $.ajax({
            url: "https://localhost:8443/admin/view/subcategoryTable/search/"+value1,
            dataType: "json",
            data: {"searchSubCatNm": $("#txtViewSearchSubCategory").val(), "pgLimit": pgLimit, "initPage": "0"},
            success: function (data) {
                $("#tableSubCategory").bootstrapTable('load', data);
                console.log(data);
            },
            error: function (e) {
                alert("error, load search item" + e);
                console.log("error, load search item" + e)
            }
        });
        /**
         * ajax function for getting records count
         */

        $.ajax({
            url: 'https://localhost:8443/admin/SubCategoryPaginationTable',
            data:{"catName":value1},
            success: function (recCount) {
                pagSubCat2.simplePaginator('setTotalPages', Math.ceil(recCount / 3));
            }
        });

    })

});

