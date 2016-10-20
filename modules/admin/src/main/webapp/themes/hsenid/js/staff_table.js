$(document).ready(function () {

    $('#tableStaff').bootstrapTable({

        url: 'https://localhost:8443/admin/users/view/staffTable',
        height: 350,
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
        },{
            field: 'branch',
            title: 'Branch:',
            align:'left'
        }, {
            field: 'Options',
            title: 'Operations :',
            align: 'center',
            formatter: operateFormatter2,
            events: operateEvents2
        }]
    });
});

function operateFormatter2(value, row, index) {
    return [
        '<a class="likes" href="javascript:void(0)" title="LikeStaff" >',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        '</a>  ',
        '<a class="removes" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-remove">Block</i>',
        '</a>'
    ].join('');
}

window.operateEvents2 = {


      'click .removes': function (e, value, row, index) {
          var data3 = JSON.stringify(row);
          var objc3 = JSON.parse(data3);
          $('#lblBlockStaffId').text(objc2["username"]);
          $('#lblBlockStaffName').text("Name :"+objc3["first_name"]);
          $('#removeStaffModal').modal({show:true});

        //alert('You click remove action, row: ');
      },

      'click .likes': function(e, value, row, index) {
          var data4 = JSON.stringify(row);
          var objc4 = JSON.parse(data4);
          var staff_uname= objc4["username"];

          $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/users/staff/edit",
            data: {"staff_uname": staff_uname},
            success: function(msg){

                $('#currents_user').text(msg["firstName"]);
                $('#titles').val(msg["title"]);
                $('#updates-first-name').val(msg["firstName"]);
                $('#updates-last-name').val(msg["lastName"]);
                $('#updates_addLine1').val(msg["addressL1"]);
                $('#updates_addLine2').val(msg["addressL2"]);
                $('#updates_city').val(msg["addressL3"]);
                $('#updates_mobile').val(msg["mobile"]);
                $('#updates_email').val(msg["email"]);
                $('#updates_username').val(msg["username"]);
                $('#updates_password').val(msg["password"]);
                $('#updates_designation').val(msg["designation"]);
                $('#updates_department').val(msg["department"]);
                $('#updates_branch').val(msg["branch"]);
                $('#editStaffModal').modal({show:true});

        },
        error:function(e){
            alert("ajax failed" +uname+ ""+e);
        }
    });
}
};

//function to bann a staff user selected
$(document).ready(function(){
    $("#btnBlockStaff").click(function(){
        var uname = $('#lblBlockStaffId').text();
        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/users/block/staff",
            data: {"uname": uname},
            success: function(msg){
                if(msg == 1){
                    alert("Blocked the  user" + uname+" successfully!");
                    $("#removeStaffModal").modal('hide');

                }else{
                    alert("Error in blocking the user !");
                    $("#removeStaffModal").modal('hide');
                }

            },
            error:function(){
                alert("ajax failed" +uname);
            }
        });

    });
});