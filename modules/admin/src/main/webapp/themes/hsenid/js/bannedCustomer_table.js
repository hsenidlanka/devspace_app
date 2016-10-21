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
        '<a class="viewc" href="javascript:void(0)" title="LikeCustomer" >',
        '<i class="glyphicon glyphicon-eye-open">View</i>',
        //'<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="replacec" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-refresh">Restore</i>',
        '</a>'
    ].join('');
}


window.operateEvents4 = {
    'click .viewc': function() {
        $('#blockedCustomerModal').modal({show:true});

    },
    'click .replacec': function(e, value, row, index) {
        var data4 = JSON.stringify(row);
        var objc4 = JSON.parse(data4);
        $('#lblUnblockCustomerId').text(objc4["username"]);
        $('#lblUnblockCustomerName').text("Name :"+objc4["first_name"]);
        $('#replaceCustomerModal').modal({show:true});
    }
};

//function to unbann a customer user selected
$(document).ready(function(){
    $("#btnUnblockCustomer").click(function(){
        var uname = $('#lblUnblockCustomerId').text();
        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/users/unblock/customer",
            data: {"uname": uname},
            success: function(msg){
                if(msg == 1){
                    alert("Unblocked the  user" + uname+" successfully!");
                    $("#replaceCustomerModal").modal('hide');

                }else{
                    alert("Error in unblocking the user !");
                    $("#replaceCustomerModal").modal('hide');
                }

            },
            error:function(){
                alert("ajax failed" +uname);
            }
        });

    });
});

