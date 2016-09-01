$(document).ready(function () {

    $('#tableBannedusers').bootstrapTable({

        url: '../js/customer.json',
        height: 375,
        pagination: true,
        pageSize: 8,
        clickToSelect: true,
        singleSelect: true,
        minimumCountColumns: 3,
        columns: [{
            //field: 'id',
            field:'ID',
            title: 'ID :',
            sortable: true,
            align:'left'
        },{
            field: 'Username',
            title: 'Username :',
            sortable: true,
            align:'left',
            searchable:true
        }, {
            field: 'f_name',
            title: 'First Name :',
            align:'left',
            sortable: true
        }, {
            field: 'l_name',
            title: 'Last Name :',
            align:'left',
            sortable: true
        }, {
            field: 'mobile',
            title: 'Phone No :',
            align:'left'
        }, {
            field: 'e_mail',
            title: 'Email :',
            align:'left'
        }, {
            field: 'address',
            title: 'Address :',
            align:'left'
        }, {
            field: 'Options',
            title: 'Operations :',
            align: 'center',
            formatter: operateFormatter3,
            events: operateEvents3
        }]
    });
})

function operateFormatter3(value, row, index) {
    return [
        '<a class="likeb" href="../pages/userCustomer_edit.html" title="LikeCustomer" >',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        //'<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-remove">Delete</i>',
        '</a>'
    ].join('');
}

window.operateEvents3 = {
    'click .remove': function() {
        $('#removeCustomerModal').modal({show:true});

    }
}