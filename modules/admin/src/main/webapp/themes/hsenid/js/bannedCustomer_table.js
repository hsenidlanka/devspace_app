$(document).ready(function () {

    var pageLimit = 5;
    //alert(pageLimit);
    $.ajax({
        url: 'https://localhost:8443/admin/users/view/bannedcustomerTable',
        data: {"initPage": "0", "pageLimit": pageLimit},
        success: function (result) {
            $('#tableBannedcustomer').bootstrapTable({
                height: 420,
                pagination: false,
                search: false,
                showColumns: false,
                showRefresh: false,
                clickToSelect: true,
                singleSelect: true,
                minimumCountColumns: 3,
                columns: [{
                    field: 'id',
                    title: 'ID :',
                    sortable: true,
                    align: 'left'
                }, {
                    field: 'username',
                    title: 'Username :',
                    sortable: true,
                    align: 'left',
                    searchable: true
                }, {
                    field: 'first_name',
                    title: 'First Name :',
                    align: 'left',
                    sortable: true
                }, {
                    field: 'mobile',
                    title: 'Phone No :',
                    align: 'left'
                }, {
                    field: 'email',
                    title: 'Email :',
                    align: 'left'
                }, {
                    field: 'address_line3',
                    title: 'City:',
                    align: 'left'
                }, {
                    field: 'Options',
                    title: 'Operations :',
                    align: 'center',
                    formatter: operateFormatter4,
                    events: operateEvents4
                }],
                data: result
            });
        }
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
    'click .viewc': function(e, value, row, index) {
        var data6 = JSON.stringify(row);
        var objc6 = JSON.parse(data6);
        var bcustomer_uname=objc6["username"];

        $.ajax({
            url: "https://localhost:8443/admin/users/bannedCustomer/detail",
            data: {"bcustomer_uname": bcustomer_uname},
            success: function(msg){

                $('#currentbc_user').text(objc6["first_name"]);
                $('#customerDetail_title').val(msg["title"]);
                $('#customerDetail_first-name').val(msg["firstName"]);
                $('#customerDetail_last-name').val(msg["lastName"]);
                $('#customerDetail_addLine1').val(msg["addressL1"]);
                $('#customerDetail_addLine2').val(msg["addressL2"]);
                $('#customerDetail_city').val(msg["addressL3"]);
                $('#customerDetail_mobile').val(msg["mobile"]);
                $('#customerDetail_email').val(msg["email"]);
                $('#customerDetail_username').val(msg["username"]);
                $('#customerDetail_status').val(msg["status"]);
                $('#customerDetail_reg').val(msg["registered_date"]);
                $('#blockedCustomerModal').modal({show:true});
            },
            error:function(e){
                alert("ajax failed" +uname+ ""+e);
            }
        });


    },
    'click .replacec': function(e, value, row, index) {
        var data5 = JSON.stringify(row);
        var objc5 = JSON.parse(data5);
        $('#lblUnblockCustomerId').text(objc5["username"]);
        $('#lblUnblockCustomerName').text("Name :"+objc5["first_name"]);
        $('#replaceCustomerModal').modal({show:true});
    }
};

//function to unbann a customer user selected
    $("#btnUnblockCustomer").click(function(){
        var uname = $('#lblUnblockCustomerId').text();
        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/users/unblock/customer",
            data: {"uname": uname},
            success: function(msg){
                if(msg == 1){
                    $.toaster({priority: 'success', title: 'Success', message: 'Unblocked the user successfully  '+uname});
                    setInterval(3000);
                    $("#replaceCustomerModal").modal('hide');
                    setTimeout(function(){location.reload();}, 2000);

                }else{
                    $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot unblock the user ' +uname});
                    $("#replaceCustomerModal").modal('hide');
                    setTimeout(function(){location.reload();}, 2000);
                }

            },
            error:function(){
                alert("ajax failed" +uname);
            }
        });

    });
});

