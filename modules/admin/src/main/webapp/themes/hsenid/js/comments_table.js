$(document).ready(function () {

    $('#tableComments').bootstrapTable({

        url: 'https://localhost:8443/admin/notification/commentsView',
        height: 380,
        pagination: true,
        pageSize: 4,
        pageList:[10,20,30],
        clickToSelect: true,
        singleSelect: true,
        minimumCountColumns: 3,
        columns: [{
            //field: 'id',
            field:'id',
            title: 'ID :',
            sortable: true,
            align:'left',
            class:'col-xs-1'
        },{
            field: 'username',
            title: 'Customer :',
            sortable: true,
            align:'left',
            searchable:true,
            class:'col-xs-2'
        }, {
            field: 'date',
            title: 'Date :',
            align:'left',
            sortable: true,
            class:'col-xs-2'
        },  {
            field: 'comment',
            title: 'Comment :',
            align:'left',
            class:'col-xs-5'
        }, {
            field: 'Options',
            title: 'Operations :',
            align: 'center',
            class:'col-xs-2',
            formatter: operateFormatterComments,
            events: operateEventsComments
        }]
    });
});

function operateFormatterComments(value, row, index) {
    return [
        '<a class="likeb" href="javascript:void(0)" title="LikeCustomer" >',
        '<i class="glyphicon glyphicon-eye-open">Approve</i>',
        //'<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-refresh">Discard</i>',
        '</a>'
    ].join('');
}

window.operateEventsComments = {

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
                alert("ajax failed" +uname+ ""+e);
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