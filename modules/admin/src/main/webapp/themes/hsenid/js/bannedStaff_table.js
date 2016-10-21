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
        $('#bannedStaffModal').modal({show:true});

    },

    'click .remove': function(e, value, row, index) {
        var data3 = JSON.stringify(row);
        var objc3 = JSON.parse(data3);
        $('#lblUnblockStaffId').text(objc3["username"]);
        $('#lblUnblockStaffName').text("Name :"+objc3["first_name"]);
        $('#replaceStaffModal').modal({show:true});

    }
};

//function to unbann a staff user selected
$(document).ready(function(){
    $("#btnUnblockStaff").click(function(){
        var uname = $('#lblUnblockStaffId').text();
        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/users/unblock/staff",
            data: {"uname": uname},
            success: function(msg){
                if(msg == 1){
                    alert("Unblocked the  user" + uname+" successfully!");
                    $("#replaceStaffModal").modal('hide');

                }else{
                    alert("Error in unblocking the user !");
                    $("#replaceStaffModal").modal('hide');
                }

            },
            error:function(){
                alert("ajax failed" +uname);
            }
        });

    });
});