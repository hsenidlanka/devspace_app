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
$(document).ready(function(){
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

//function to edit a customer user selected
/*
$(document).ready(function(){
    $("#btnUpdateCustomer").click(function(){

        var user=$("#update-form-username").val();

        var uf_name = $("#update-first-name").val();
        var ul_name = $("#update-last-name").val();
        var udate = $("#date2").val();
        var ucountry = $("#update-country").val();
        var ucity = $("#update-city").val();
        var uemail = $("#update-form-email").val();
        var umobile = $("#update-form-mobile").val();
        var upw = $("#update_password").val();
        var ugroup = $("#ugroup").val();

        // alert("Updated User details of " + ucity);

        $.ajax({

            type:"POST",
            url:"UpdateUserServlet",
            data:{"uu_name":user,"uf_name":uf_name,"ul_name":ul_name,"udate":udate ,"ucountry":ucountry,"ucity":ucity,
                "uemail":uemail,"umobile":umobile,"upw":upw, "ugroup":ugroup},

            success:function(msg){

                if(msg==1){
                    alert("Updated User details of " + user);


                }else{
                    alert("Error in updating!");
                    $("#myModal1").modal('hide');
                }
            }
        })
        ;
    });
});*/
