$(document).ready(function () {

    var pageLimit = 10;
    //alert(pageLimit);
    $.ajax({
        url: 'https://localhost:8443/admin/users/view/bannedstaffTable',
        data: {"initPage": "0", "pageLimit": pageLimit},
        success: function (result) {
            $('#tableBannedstaff').bootstrapTable({

                height: 410,
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
                    title: 'Mobile :',
                    align: 'left'
                }, {
                    field: 'designation',
                    title: 'Designation :',
                    align: 'left'
                }, {
                    field: 'department',
                    title: 'Department:',
                    align: 'left'
                }, {
                    field: 'branch',
                    title: 'Branch :',
                    sortable: true,
                    align: 'left'
                }, {
                    field: 'Options',
                    title: 'Operations :',
                    align: 'center',
                    formatter: operateFormatter3,
                    events: operateEvents3
                }],
                data: result
            });
        }
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

    'click .likeb': function(e, value, row, index) {
        var data8 = JSON.stringify(row);
        var objc8 = JSON.parse(data8);
        var bstaff_uname=objc8["username"];

        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/users/bannedStaff/detail",
            data: {"bstaff_uname": bstaff_uname},
            success: function(msg){

                $('#currentbs_user').text(objc8["first_name"]);
                $('#staffDetail_title').val(msg["title"]);
                $('#staffDetail_first-name').val(msg["firstName"]);
                $('#staffDetail_last-name').val(msg["lastName"]);
                $('#staffDetail_addLine1').val(msg["addressL1"]);
                $('#staffDetail_addLine2').val(msg["addressL2"]);
                $('#staffDetail_city').val(msg["addressL3"]);
                $('#staffDetail_mobile').val(msg["mobile"]);
                $('#staffDetail_email').val(msg["email"]);
                $('#staffDetail_username').val(msg["username"]);
                $('#staffDetail_designation').val(msg["designation"]);
                $('#staffDetail_department').val(msg["department"]);
                $('#staffDetail_branch').val(msg["branch"]);
                $('#staffDetail_status').val(msg["status"]);
                $('#bannedStaffModal').modal({show:true});

            },
            error:function(e){
                alert("ajax failed" +bstaff_uname+ ""+e);
            }
        });
    },

    'click .remove': function(e, value, row, index) {
        var data7 = JSON.stringify(row);
        var objc7 = JSON.parse(data7);
        $('#lblUnblockStaffId').text(objc7["username"]);
        $('#lblUnblockStaffName').text("Name :"+objc7["first_name"]);
        $('#replaceStaffModal').modal({show:true});
    }
};

//function to unbann a staff user selected
    $("#btnUnblockStaff").click(function(){
        var uname = $('#lblUnblockStaffId').text();
        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/users/unblock/staff",
            data: {"uname": uname},
            success: function(msg){
                if(msg == 1){

                    $.toaster({priority: 'success', title: 'Success', message: 'Unblocked the Staff user successfully  '+uname});
                    $("#replaceStaffModal").modal('hide');
                    setTimeout(function(){location.reload();}, 2000);

                }else{
                    $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot unblock the Staff user ' +uname});
                    $("#replaceStaffModal").modal('hide');
                    setTimeout(function(){location.reload();}, 2000);
                }

            },
            error:function(){
                alert("ajax failed" +uname);
            }
        });

    });
});