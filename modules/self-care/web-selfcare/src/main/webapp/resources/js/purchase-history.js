var paginationBar;
var currentPage = 1;
var recPerPage = 5;

$(document).ready(function () {
    initPurchaseHistoryTable();
    pagination();
    $('#comboRecCount').change(function () {
        loadDataByPageSize();
    });
    $('#comboPages').change(function () {
        jumpToPage();
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
                    valign: 'bottom'
                }, {
                    field: 'operate',
                    title: 'Operations',
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
            '<a class="edit m-btn mini blue" style="height: auto;" href="javascript:void(0)" title="Edit">',
            'View order details',
            '</a>&nbsp;&nbsp;',
            '<a class="delete m-btn mini green" style="height: auto;" href="javascript:void(0)" title="Delete">',
            'View delivery/payment details',
            '</a></center>'
        ].join('');
    }

    window.operateEvents = {
        'click .edit': function (e, value, row, index) {
            var js = JSON.stringify(row);
            var obj = JSON.parse(js);

        },
        'click .delete': function (e, value, row, index) {
            var js = JSON.stringify(row);
            var obj = JSON.parse(js);

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
