$(document).ready(function () {

            $('#tableCustomer').bootstrapTable({

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
                    title: 'Options :',
                    align: 'center',
                    formatter: operateFormatter,
                    events: operateEvents
                }]
            });
})

function operateFormatter(value, row, index) {
    return [
        '<a class="like" href="../pages/userCustomer_edit.html" title="Like" >',
        '<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click .like': function(){
          //$('#editCustomerModal').modal('show');

          //alert('You click like action, row: ' );
    },

    'click .remove': function() {
        $('#removeCustomerModal').modal({show:true});

        //alert('You click remove action, row: ');

    }
}