/*
 * default pagination function for category view
 */

$(document).ready(function () {
    var pgLimit = 5;
    /**
     * ajax function for getting records count
     */
    $("#pagination2Comments").hide();

    $.ajax({
        url: 'https://localhost:8443/admin/notification/CommentsPaginationTable',
        success: function (recCount) {
            pagComment1.simplePaginator('setTotalPages', Math.ceil(recCount /pgLimit));
        }
    });

    var pagComment1 = $('#paginationComments').simplePaginator({
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
                url: 'https://localhost:8443/admin/notification/commentsView',
                dataType: "json",
                data: {"initPage":pageSend, "pgLimit": pgLimit},
                success: function (data) {
                    $('#tableComments').bootstrapTable('load', data);
                }
            })
        }
    });


    //typical extra  pagination for search functionality
    var pagComment2 = $('#pagination2Comments').simplePaginator({

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

            var from = $('#fromDateComments').val();
            var to=$('#toDateComments').val();
            var status=$('#statusSearch').val();
            var cmntName=$('#cmntSearch').val();


            if ((from == "") && (to == "") && (cmntName == "") && (status == "--Select--")) {
                $.ajax({
                    //type: "POST",
                    url: 'https://localhost:8443/admin/notification/commentsView',
                    data: {"initPage": pageSend, " pgLimit": pgLimit},
                    success: function (msg) {

                        $('#tableComments').bootstrapTable('load', msg);
                    },
                    error: function (e) {
                        alert("ajax failed in comment table load" + e);
                    }
                });
            }

            if ((cmntName != "")) {
                $.ajax({
                    url: "https://localhost:8443/admin/notification/commentsView/typeheadName/data",
                    dataType: "json",
                    data: {"cmntName": cmntName, "initPage": pageSend, "pageLimit": pgLimit},
                    success: function (data) {
                        $('#tableComments').bootstrapTable('load', data);
                    }
                })
            }

            if ((from != "") || (to != "") || (!(city == "--Select--"))) {
                $.ajax({
                    //type: "POST",
                    url: "https://localhost:8443/admin/notification/commentsView/date/status",
                    data: {"from": from, "to": to, "status": status, "initPage": pageSend, "pageLimit": pgLimit},
                    success: function (msg) {
                        //alert("ajax succ" + from +name +city);
                        $('#tableCustomer').bootstrapTable('load', msg);
                    },
                    error: function (e) {
                        alert("ajax failed" + e);
                    }
                });
            }
        }
    });


    $("#cmntSearch").keyup(function () {

        $('#paginationComments').hide();
        $('#pagination2Comments').show();

        $.ajax({
            url: "https://localhost:8443/admin/notification/commentsView/typeheadName/data",
            datatype: "JSON",
            data: {"cmntName": $("#cmntSearch").val(), "pageLimit": pgLimit, "initPage": "0"},
            success: function (data) {
                $("#tableComments").bootstrapTable('load', data);
                console.log(data);
            },
            error: function (e) {
                alert("error, load search comment user" + e);
                console.log("error, load search comment user" + e)
            }
        });

        //Setting the number of pages according to the number of records
        $.ajax({
            url: 'https://localhost:8443/admin/notification/CommentsPaginationTable',
            success: function (recCount) {
                pagComment2.simplePaginator('setTotalPages', Math.ceil(recCount /pgLimit));
            }
        });

    });

    /*
     * load data on request typeahead with the Search bbutton cilcked
     **/
  /*  $("#btnViewSearchCat").click(function () {
        var catSearchName=$("#txtViewSearchCategory").val().length;
        //alert(catSearchName);



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

            *//**
             *Setting the number of pages according to the number of records
             *//*
            $.ajax({
                url: 'https://localhost:8443/admin/category/CategoryPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {

                    pag2.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                }
            })
        }
    });



  */
});

