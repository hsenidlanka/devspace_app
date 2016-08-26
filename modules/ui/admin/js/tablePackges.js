$(document).ready(function () {

    $('#tblPackages').bootstrapTable({
        /*method: '',*/
        url: '../js/data2.json',
        height: 400,
        striped: true,
        pagination: true,
        pageSize: 50,
        pageList: [10, 25, 50, 100, 200],
        search: false,
        showColumns: false,
        showRefresh: false,
        minimumCountColumns: 2,
        columns: [{
            field: 'id',
            title: 'Item ID',
            sortable: true
        }, {
            field: 'name',
            title: 'Item Name',
            sortable: true
        },{
            field: 'category',
            title: 'Category',
            sortable: true
        },{
            field: 'subcategory',
            title: 'Sub-category',
            sortable: true
        }, {
            field: 'type',
            title: 'Type',
            sortable: true
        }, {
            field: 'price',
            title: 'Item Price',
            align: 'right',
            sortable: true
        }, {
            field: 'operate',
            title: 'Operations',
            align: 'center',
            formatter: operateFormatter,
            events: operateEvents
        }]
    });
});

function operateFormatter(value, row, index){
    return[
        '<center>',
        '<a class="view" title="View" id="linkViewPkg">',
        '<i class="glyphicon glyphicon-eye-open">View</i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="edit" title="Edit" id="linkEditPkg">',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="delete" title="Delete" id="linkDeletePkg">',
        '<i class="glyphicon glyphicon-remove">Delete</i>',
        '</a></center>'
    ].join('');
}

window.operateEvents = {
    'click .delete':function(){
        $('#pkgDeleteModal').modal('show');
    },
    'click .edit':function(){
        $('#pkgEditModal').modal('show');
    }
}
