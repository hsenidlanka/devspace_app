$(document).ready(function () {

    $('#tableBannedcustomer').bootstrapTable({

        url: 'https://localhost:8443/admin/users/view/bannedcustomerTable',
        height: 375,
        pagination: true,
        pageSize: 7,
        clickToSelect: true,
        singleSelect: true,
        minimumCountColumns: 3,
        columns: [{
            //field: 'id',
            field:'id',
            title: 'ID :',
            sortable: true,
            align:'left'
        },{
            field: 'username',
            title: 'Username :',
            sortable: true,
            align:'left',
            searchable:true
        }, {
            field: 'first_name',
            title: 'First Name :',
            align:'left',
            sortable: true
        }, {
            field: 'mobile',
            title: 'Phone No :',
            align:'left'
        }, {
            field: 'email',
            title: 'Email :',
            align:'left'
        }, {
            field: 'address_line3',
            title: 'City:',
            align:'left'
        }, {
                field: 'Options',
                title: 'Operations :',
                align: 'center',
                formatter: operateFormatter4,
                events: operateEvents4
        }]
    });
});

function operateFormatter4(value, row, index) {
    return [
        '<a class="likebc" href="javascript:void(0)" title="LikeCustomer" >',
        '<i class="glyphicon glyphicon-eye-open">View</i>',
        //'<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="removebc" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-refresh">Restore</i>',
        '</a>'
    ].join('');
}

window.operateEvents4 = {

    'click .likebc': function() {
        $('#blockCustomerModal').modal({show:true});

    },

    'click .removebc': function() {
        $('#replaceUserModal').modal({show:true});

    }
}
