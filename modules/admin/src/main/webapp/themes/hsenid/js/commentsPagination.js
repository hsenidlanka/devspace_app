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
                    dataType: "json",
                    data: {"initPage":pageSend, "pgLimit": pgLimit},
                    success: function (msg) {

                        $('#tableComments').bootstrapTable('load', msg);
                    }
                });
            }

            if ((cmntName != "")) {
                $.ajax({
                    url: "https://localhost:8443/admin/notification/commentsView/typeheadName/data",
                    dataType: "json",
                    data: {"cmntName": cmntName, "initPage": pageSend, "pgLimit": pgLimit},
                    success: function (data) {
                        $('#tableComments').bootstrapTable('load', data);
                    }
                })
            }

            if ((from != "") || (to != "") || (!(status == "--Select--"))) {
                $.ajax({
                    //type: "POST",
                    url: "https://localhost:8443/admin/notification/commentsView/date/status",
                    data: {"from": from, "to": to, "status": status, "initPage": pageSend, "pgLimit": pgLimit},
                    success: function (msg) {
                        //alert("ajax succ" + from +name +city);
                        $('#tableComments').bootstrapTable('load', msg);
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
            data: {"cmntName": $("#cmntSearch").val(), "pgLimit": pgLimit, "initPage": "0"},
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
     * load data on selection of status or date range and the Search button cilcked
     **/
    $("#filterButtonComment").click(function() {
        var from = $('#fromDateComments').val();
        var to=$('#toDateComments').val();
        var status=$('#statusSearch').val();
        var cmntName=$('#cmntSearch').val();
        alert(status);


        $('#paginationComments').hide();
        $('#pagination2Comments').show();

        if ((from == "") && (to == "") && (cmntName == "") && (status == "--Select--")) {
            alert("inside the all fields null");
            $.ajax({
                //type: "POST",
                url: 'https://localhost:8443/admin/notification/commentsView',
                dataType: "json",
                data: {"initPage": "0", "pgLimit": pgLimit},
                success: function (msg) {
                    $('#tableComments').bootstrapTable('load', msg);
                }
            });
            /**
             *Setting the number of pages according to the number of records
             */
                //Setting the number of pages according to the number of records
            $.ajax({
                url: 'https://localhost:8443/admin/notification/CommentsPaginationTable',
                success: function (recCount) {
                    pagComment2.simplePaginator('setTotalPages', Math.ceil(recCount /pgLimit));
                }
            });
        }

        if ((from != "") || (to != "") || (!(status == "--Select--"))) {
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/notification/commentsView/date/status",
                data: {"from": from, "to": to, "status": status, "initPage": "0", "pgLimit": pgLimit},
                success: function (msg) {
                    //alert("ajax succ" + from +name +city);
                    $('#tableComments').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/notification/CommentsPaginationTable',
                success: function (recCount) {
                    pagComment2.simplePaginator('setTotalPages', Math.ceil(recCount /pgLimit));
                }
            });
        }
    });

});

