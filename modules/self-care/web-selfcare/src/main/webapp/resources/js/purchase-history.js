var paginationBar;
var currentPage = 1;
var recPerPage = 5;

$(document).ready(function () {
    var date_input = $('input[name="date"]'); //our date input has the name "date"
    var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
    var options = {
        format: 'yyyy-mm-dd',
        weekStart: 1,
        //startDate: "TODAY",
        maxViewMode: 0,
        todayHighlight: true,
        autoclose: true
    };
    date_input.datepicker(options);

    initPurchaseHistoryTable();
    pagination();

    $('#comboRecCount').change(function () {
        loadDataByPageSize();
    });

    $('#comboPages').change(function () {
        jumpToPage();
    });

    $('#btnSearchOrder').click(function () {
        searchOrder();
    });
});

// Initialize the bootstrap table with data
function initPurchaseHistoryTable() {
    $.ajax({
        url: "purchase-history/orders",
        type: "GET",
        dataType: "json",
        data: {'page': currentPage, "records": recPerPage},
        success: function (data) {
            $('#table-purchaseHistory').bootstrapTable({
                height: 300,
                pageSize: recPerPage,
                data: data,
                singleSelect: true,
                columns: [{
                    field: 'orderId',
                    title: 'Order id',
                    align: 'left',
                    valign: 'bottom',
                    sortable: true
                }, {
                    field: 'date',
                    title: 'Order date',
                    align: 'right',
                    valign: 'bottom',
                    sortable: true
                }, {
                    field: 'time',
                    title: 'Order time',
                    align: 'right',
                    valign: 'bottom',
                    sortable: true
                }, {
                    field: 'netCost',
                    title: 'Net amount (Rs.)',
                    align: 'right',
                    valign: 'bottom',
                    sortable: true
                }, {
                    field: 'operate',
                    title: 'View details',
                    align: 'center',
                    valign: 'middle',
                    formatter: operateFormatter,
                    events: operateEvents
                }]
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Error loading data. Message: " + errorThrown);
        }
    });

    function operateFormatter(value, row, index) {
        return [
            '<center>',
            '<a class="view-items m-btn mini blue" style="height: auto;" href="javascript:void(0)" title="View items of an order">',
            'Order items',
            '</a>&nbsp;&nbsp;',
            '<a class="view-del-pay m-btn mini green" style="height: auto;" href="javascript:void(0)" title="View payment/delivery status of the order">',
            'Delivery & Payment',
            '</a></center>'
        ].join('');
    }

    window.operateEvents = {
        'click .view-items': function (e, value, row, index) {
            var js = JSON.stringify(row);
            var obj = JSON.parse(js);
            $.ajax({
                url: "purchase-history/order-items",
                type: "GET",
                dataType: "json",
                data: {"orderId": obj['orderId']},
                success: function (result) {
                    $("#order-items-popup").modal('show');
                    var content = "<table class='table-bordered table-condensed table-ordItems' style='width: 100%; font-size: 12px;'>";
                    content += '<thead class="thead-ordItems">';
                    content += '<th>Item name</th>';
                    content += '<th>Size</th>';
                    content += '<th>Quantity</th>';
                    content += '<th>Topping 01</th>';
                    content += '<th>Topping 02</th>';
                    content += '<th>Instructions</th>';
                    content += '</thead>';
                    content += '<tbody class="tbody-ordItems">';
                    $.each(result, function (idx, obj) {
                        content += '<tr class="tr-ordItems">';
                        content += '<td>' + obj['itemName'] + '</td>';
                        content += '<td>' + obj['size'] + '</td>';
                        content += '<td align="right">' + obj['quantity'] + '</td>';
                        var topping1 = "-";
                        var topping2 = "-";
                        var instructions = "-";
                        if ($.trim(obj['topping1']) != "") {
                            topping1 = obj['topping1'];
                        }
                        if ($.trim(obj['topping2']) != "") {
                            topping2 = obj['topping2'];
                        }
                        if ($.trim(obj['instructions']) != "") {
                            instructions = obj['instructions'];
                        }
                        content += '<td>' + topping1 + '</td>';
                        content += '<td>' + topping2 + '</td>';
                        content += '<td>' + instructions + '</td>';
                        content += '</tr>';
                    });
                    content += "</tbody>";
                    content += "</table>";
                    $("#table-orderItems").html(content);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error loading order items. Message: " + errorThrown);
                }
            });
        },
        'click .view-del-pay': function (e, value, row, index) {
            var js = JSON.stringify(row);
            var obj = JSON.parse(js);
            alert(obj['orderId']);
            $("#modal-payment-delivery").modal('show');
        }
    };
}

// Define the pagination bar
function pagination() {
    paginationBar = $('#pagination').simplePaginator({
        totalPages: 1,
        maxButtonsVisible: 5,
        currentPage: 1,
        nextLabel: 'Next',
        prevLabel: 'Prev',
        firstLabel: 'First',
        lastLabel: 'Last',
        pageChange: function (page) {
            currentPage = page;
            $.ajax({
                url: "purchase-history/orders",
                type: "GET",
                data: {"page": page, "records": recPerPage},
                success: function (result) {
                    $('#table-purchaseHistory').bootstrapTable('load', result);
                    $('#comboPages').val(page);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error initializing pagination. Message: " + errorThrown);
                }
            });
        }
    });

    // Get the user count and set the page count of the pagination bar
    $.ajax({
        type: "get",
        url: "purchase-history/order-count",
        success: function (result) {
            var pageCount = Math.ceil(result / recPerPage);
            if (pageCount == 0) {
                pageCount = 1;
            }
            paginationBar.simplePaginator('setTotalPages', pageCount);
            loadPageSelect(pageCount);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Error getting orders count. Message: " + errorThrown);
        }
    });
}

// Load page amount to the go to page dropdown
function loadPageSelect(pages) {
    var select = $("#comboPages"), options = '';
    select.empty();
    for (var i = 1; i <= pages; i++) {
        options += "<option>" + i + "</option>";
    }
    select.append(options);
}

// Load selected amount of records per page
function loadDataByPageSize() {
    recPerPage = $('#comboRecCount').val();
    $.ajax({
        type: "GET",
        url: "purchase-history/orders",
        dataType: "json",
        data: {"page": "1", "records": recPerPage},
        success: function (result) {
            $('#table-purchaseHistory').bootstrapTable('load', result);
            paginationBar.simplePaginator('changePage', 1);
        }
    });

    $.ajax({
        url: "purchase-history/order-count",
        success: function (result) {
            var totalPages = Math.ceil(result / recPerPage);
            if (totalPages == 0) {
                totalPages = 1;
            }
            paginationBar.simplePaginator('setTotalPages', totalPages);
            loadPageSelect(totalPages);
        }
    });
}

// Go to the page selected by the dropdown
function jumpToPage() {
    var selectedPage = $('#comboPages').val();
    $.ajax({
        type: "GET",
        url: "purchase-history/orders",
        dataType: "json",
        data: {"page": selectedPage, "records": recPerPage},
        success: function (result) {
            $('#table-purchaseHistory').bootstrapTable('load', result);
            $('#comboPages').val(selectedPage);
            paginationBar.simplePaginator('changePage', parseInt(selectedPage));
        }
    });
}

// Search for an order details by order date
function searchOrder() {
    if ($("#txtSearchOrder").val().length == 0) {
        $('#pagination').show();
        $('#pagination2').hide();
        $('#comboPages').show();
        $('#comboRecCount').show();
        $('.pagiTexts').show();

        $.ajax({
            type: "GET",
            url: "purchase-history/orders",
            dataType: "json",
            data: {"page": "1", "records": recPerPage},
            success: function (result) {
                $('#table-purchaseHistory').bootstrapTable('load', result);
                paginationBar.simplePaginator('changePage', 1);
            }
        });
    }
    else {
        $('#pagination').hide();
        $('#pagination2').show();
        $('#comboPages').hide();
        $('#comboRecCount').hide();
        $('.pagiTexts').hide();

        var searchDate = $("#txtSearchOrder").val();

        $.ajax({
            url: "purchase-history/orders/date",
            type: "GET",
            data: {"date": searchDate, "page": 1, "records": recPerPage},
            success: function (result) {
                $('#table-purchaseHistory').bootstrapTable('load', result);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error initializing search table data. Message: " + errorThrown);
            }
        });

        // New pagination bar count
        $.ajax({
            type: "get",
            url: "purchase-history/order-count/date",
            data: {"date": searchDate},
            success: function (result) {
                var pageCount = Math.ceil(result / recPerPage);
                if (pageCount == 0) {
                    pageCount = 1;
                }
                $('#pagination2').simplePaginator('setTotalPages', pageCount);
            }
        });

        paginationBar2 = $('#pagination2').simplePaginator({
            totalPages: 1,
            maxButtonsVisible: 5,
            currentPage: 1,
            nextLabel: 'Next',
            prevLabel: 'Prev',
            firstLabel: 'First',
            lastLabel: 'Last',
            pageChange: function (page) {
                $.ajax({
                    url: "purchase-history/orders/date",
                    type: "GET",
                    data: {"date": searchDate, "page": page, "records": recPerPage},
                    success: function (result) {
                        $('#table-purchaseHistory').bootstrapTable('load', result);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("Error initializing pagination. Message: " + errorThrown);
                    }
                });
            }
        });
    }
}
