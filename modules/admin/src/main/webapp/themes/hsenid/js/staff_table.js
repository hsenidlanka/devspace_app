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
        '<a class="likes" href="../pages/userStaff_edit.html" title="LikeStaff" >',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        '</a>  ',
        '<a class="removes" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-remove">Block</i>',
        '</a>'
    ].join('');
}

window.operateEvents2 = {


      'click .removes': function (e, value, row, index) {
          var data2 = JSON.stringify(row);
          var objc2 = JSON.parse(data2);
          $('#lblBlockStaffId').text(objc2["username"]);
          $('#lblBlockStaffName').text("Name :"+objc2["first_name"]);
        $('#removeStaffModal').modal({show:true});

        //alert('You click remove action, row: ');

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