$(document).ready(function () {

    $('#tableBannedstaff').bootstrapTable({

        url: 'https://localhost:8443/admin/users/view/bannedstaffTable',
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
        },  {
            field: 'mobile',
            title: 'Mobile :',
            align:'left'
        }, {
            field: 'designation',
            title: 'Designation :',
            align:'left'
        }, {
            field: 'department',
            title: 'Department:',
            align:'left'
        }, {
            field: 'branch',
            title: 'Branch :',
            sortable: true,
            align:'left'
        },{
            field: 'Options',
            title: 'Operations :',
            align: 'center',
            formatter: operateFormatter3,
            events: operateEvents3
        }]
    });
});

function operateFormatter3(value, row, index) {
    return [
        '<a class="likeb" href="javascript:void(0)" title="LikeCustomer" >',
        '<i class="glyphicon glyphicon-eye-open">View</i>',
        //'<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-refresh">Restore</i>',
        '</a>'
    ].join('');
}

window.operateEvents3 = {

    'click .likeb': function() {
        $('#blockedStaffModal').modal({show:true});

    },

    'click .remove': function() {
        $('#replaceUserModal').modal({show:true});

    }
}
