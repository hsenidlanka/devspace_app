$(document).ready(function () {

            $('#tableCustomer').bootstrapTable({

                url: 'https://localhost:8443/admin/users/view/customerTable',
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
                    field: 'address_line3',
                    title: 'City:',
                    align:'left'
                },{
                    field: 'registered_date',
                    title: 'Registered Date:',
                    align:'left'
                }, {
                    field: 'Options',
                    title: 'Operations :',
                    align: 'center',
                    formatter: operateFormatter1,
                    events: operateEvents1
                }]
            });
})

function operateFormatter1(value, row, index) {
    return [
        '<a class="likec" href="../pages/userCustomer_edit.html" title="LikeCustomer" >',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        //'<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="removec" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-remove">Block</i>',
        '</a>'
    ].join('');
}

window.operateEvents1 = {
    'click .removec': function(e, value, row, index) {
        var data2 = JSON.stringify(row);
        var objc2 = JSON.parse(data2);
        $('#lblBlockCustomerId').text("Username :"+objc2["username"]);
        $('#lblBlockCustomerName').text("Name :"+objc2["first_name"]);
        $('#removeCustomerModal').modal({show:true});

    },
    'click .likec': function(e, value, row, index) {
        var data1 = JSON.stringify(row);
        var objc1 = JSON.parse(data1);

        $('#update-form-username').val(objc1["user_name"]);
        $('#update-first-name').val(objc1["f_name"]);
        $('#update-last-name').val(objc1["l_name"]);
        $('#date2').val(objc1["niceDate"]);
        $('#update-form-mobile').val(objc1["mobile"]);
        $('#update-form-email').val(objc1["e_mail"]);
        $('#update-country.value').val(objc1["country"]);
        $('#update-form-city').val(objc1["city_id"]);
        $('#replaceUserModal').modal({show:true});

    }
}