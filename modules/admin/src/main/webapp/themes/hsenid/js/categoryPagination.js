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
            var pageSend=(page-1)*pgLimit;

            $.ajax({
                url: 'https://localhost:8443/admin/category/view/categoryTable',
                dataType: "json",
                data: {"initPage":pageSend, "pgLimit": pgLimit},
                success: function (data) {
                    $('#tableCategory').bootstrapTable('load', data);
                }
            })
        }
    });


    //typical extra  pagination for search functionality
    var pag2 = $('#pagination2Cat').simplePaginator({

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
                url: 'https://localhost:8443/admin/category/view/categoryTable/catName',
                dataType: "json",
                data: {"searchCatNm": $("#txtViewSearchCategory").val(), "initPage": pageSend, "pgLimit": pgLimit},
                success: function (data) {

                    $('#tableCategory').bootstrapTable('load', data);
                }
            })
        }
    });

    /*
     * load data on request typeahead with the Search bbutton cilcked
     **/
    $("#btnViewSearchCat").click(function () {
        var catSearchName=$("#txtViewSearchCategory").val().length;
        alert(catSearchName);



        if ($("#txtViewSearchCategory").val().length > 0) {
            $('#paginationCat').hide();
            $('#pagination2Cat').show();

            $.ajax({
                url: "https://localhost:8443/admin/category/view/categoryTable/catName",
                data: {"searchCatNm": $("#txtViewSearchCategory").val(), "initPage": "0", "pgLimit": pgLimit},
                success: function (data) {
                    $("#tableCategory").bootstrapTable('load', data);
                    console.log(data);
                },
                error: function (e) {
                    alert("error, load search category With name" + e);
                    console.log("error, load search category With name" + e)
                }
            });

            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/category/CategoryPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {

                    pag2.simplePaginator('setTotalPages', Math.ceil(recCount / 3));
                }
            })
        }
    });



    $("#txtViewSearchCategory").keyup(function () {

        $('#paginationCat').hide();
        $('#pagination2Cat').show();

        $.ajax({
            url: "https://localhost:8443/admin/category/view/categoryTable/catName",
            datatype: "JSON",
            data: {"searchCatNm": $("#txtViewSearchCategory").val(), "pgLimit": pgLimit, "initPage": "0"},
            success: function (data) {
                $("#tableCategory").bootstrapTable('load', data);
                console.log(data);
            },
            error: function (e) {
                alert("error, load search item" + e);
                console.log("error, load search item" + e)
            }
        });


        //Setting the number of pages according to the number of records

        $.ajax({
            url: 'https://localhost:8443/admin/category/CategoryPaginationTable',
            //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
            success: function (recCount) {

                pag2.simplePaginator('setTotalPages', Math.ceil(recCount / 3));
            }
        })

    })
});

