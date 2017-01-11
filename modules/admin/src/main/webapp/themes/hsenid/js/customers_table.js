$(document).ready(function () {

    var pageLimit = 10;
    //alert(pageLimit);
    $.ajax({
        url: 'https://localhost:8443/admin/users/view/customerTable',
        data: {"initPage": "0", "pageLimit": pageLimit},
        success: function (result) {

            $('#tableCustomer').bootstrapTable({
                height: 415,
                pagination: false,
                search: false,
                showColumns: false,
                showRefresh: false,
                clickToSelect: true,
                singleSelect: true,
                minimumCountColumns: 3,
                columns: [{
                    //field: 'id',
                    field: 'id',
                    title: 'ID :',
                    sortable: true,
                    align: 'left',
                    font: 14
                }, {
                    field: 'username',
                    title: 'Username :',
                    sortable: true,
                    align: 'left',
                    searchable: true,
                    font: 14
                }, {
                    field: 'first_name',
                    title: 'First Name :',
                    align: 'left',
                    sortable: true,
                    font: 14
                }, {
                    field: 'mobile',
                    title: 'Phone No :',
                    align: 'left',
                    size: 14
                }, {
                    field: 'address_line3',
                    title: 'City:',
                    align: 'left',
                    size: 14
                }, {
                    field: 'registered_date',
                    title: 'Registered Date:',
                    align: 'left',
                    font: 14
                }, {
                    field: 'Options',
                    title: 'Operations :',
                    align: 'center',
                    font_size: 14,
                    formatter: operateFormatter1,
                    events: operateEvents1
                }],
                data: result
            });
        }
});

function operateFormatter1(value, row, index) {
    return [
        '<a class="likec" href="javascript:void(0)" title="LikeCustomer" data-toggle="modal" >',
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
        $('#lblBlockCustomerId').text(objc2["username"]);
        $('#lblBlockCustomerName').text("Name :"+objc2["first_name"]);
        $('#removeCustomerModal').modal({show:true});
    },
    'click .likec': function(e, value, row, index) {
        var data1 = JSON.stringify(row);
        var objc1 = JSON.parse(data1);
        var uname= objc1["username"];

        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/users/customer/edit",
            data: {"uname": uname},
            success: function(msg){


                $('#current_user').text(msg["firstName"]);
                $('#title').val(msg["title"]);
                $('#update-first-name').val(msg["firstName"]);
                $('#update-last-name').val(msg["lastName"]);
                $('#update_addLine1').val(msg["addressL1"]);
                $('#update_addLine2').val(msg["addressL2"]);
                $('#update_city').val(msg["addressL3"]);
                $('#update_mobile').val(msg["mobile"]);
                $('#update_email').val(msg["email"]);
                $('#update_username').val(msg["username"]);
                $('#update_password').val(msg["password"]);
                $('#update_cpassword').val(msg["password"]);

                $('#editCustomerModal').modal({show:true});
                //alert(msg+""+a+""+b);
            },
            error:function(e){
                alert("ajax failed" +uname+ ""+e);
            }
        });
    }
};

//function to bann a customer user selected
    $("#btnBlockCustomer").click(function(){
        var uname = $('#lblBlockCustomerId').text();
        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/users/block/customer",
            data: {"uname": uname},
            success: function(msg){
                if(msg == 1){

                    $.toaster({priority: 'success', title: 'Success', message: 'Blocked the user successfully  '+uname});
                    $("#removeCustomerModal").modal('hide');
                    setTimeout(function(){location.reload();}, 3000);

                }else{

                    $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot block the user ' +uname});
                    $("#removeCustomerModal").modal('hide');
                    setTimeout(function(){location.reload();}, 3000);
                }

            },
            error:function(){
                alert("ajax failed" +uname);
            }
        });

    });
});

