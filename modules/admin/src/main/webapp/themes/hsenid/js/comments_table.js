$(document).ready(function () {

    var pgLimit = 5;
    //var catName = $("#txtViewSearchCategory").val();

    $.ajax({
        url: 'https://localhost:8443/admin/notification/commentsView',
        data: {"initPage": "0", "pgLimit": pgLimit},
        success: function (result) {
            //alert(result);

            $('#tableComments').bootstrapTable({
                height: 380,
              /*  pagination: true,
                pageSize: 4,
                pageList: [10, 20, 30],*/
                clickToSelect: true,
                singleSelect: true,
                minimumCountColumns: 3,
                columns: [{
                    //field: 'id',
                    field: 'id',
                    title: 'ID :',
                    sortable: true,
                    align: 'left'
                    //class: 'col-xs-1'
                }, {
                    field: 'username',
                    title: 'Customer :',
                    sortable: true,
                    align: 'left',
                    searchable: true
                    //class: 'col-xs-2'
                }, {
                    field: 'date',
                    title: 'Date :',
                    align: 'left',
                    sortable: true
                    //class: 'col-xs-2'
                }, {
                    field: 'item',
                    title: 'Item :',
                    align: 'left',
                    sortable: true
                    //class: 'col-xs-2'
                },{
                    field: 'time',
                    title: 'Time :',
                    align: 'left',
                    sortable: true
                        //class: 'col-xs-2'
                },{
                    field: 'comment',
                    title: 'Comment :',
                    align: 'left',
                    class: 'col-xs-3'
                },{
                    field: 'status',
                    title: 'Status :',
                    align: 'left'
                    //class: 'col-xs-5'
                    },{
                    field: 'Options',
                    title: 'Operations :',
                    align: 'center',
                    //class: 'col-xs-2',
                    formatter: operateFormatterComments,
                    events: operateEventsComments
                }],
                data: result
            })
        }
});

function operateFormatterComments(value, row, index) {
    return [
        '<a class="likeComment" href="javascript:void(0)" title="LikeCustomer" >',
        '<i class="glyphicon glyphicon-eye-open">Approve</i>',
        //'<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="removeComment" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-remove">Discard</i>',
        '</a>'
    ].join('');
}

window.operateEventsComments = {

    'click .likeComment': function(e, value, row, index) {
        var data8 = JSON.stringify(row);
        var objc8 = JSON.parse(data8);
        $('#lblApproveCommentUser').text(objc8["username"]);
        $('#lblApproveComment').text(objc8["comment"]);
        $('#commentId').val(objc8["id"]);
        $('#approveCommentModel').modal({show:true});
    },

    'click .removeComment': function(e, value, row, index) {
        var data7 = JSON.stringify(row);
        var objc7 = JSON.parse(data7);
        $('#lblDiscardCommentUser').text(objc7["username"]);
        $('#lblDiscardComment').text(objc7["comment"]);
        $('#discardCommentId').val(objc7["id"]);
        $('#discardCommentModel').modal({show:true});
    }
};

    //function to approve a comment selected
    $("#btnApproveComment").click(function(){
        var cmntId = $('#commentId').val();
        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/notification/commentApprove",
            data: {"cmntId": cmntId},
            success: function(msg){
                if(msg == 1){

                    $.toaster({priority: 'success', title: 'Success', message: 'Comment Approve is successful '
                    +cmntId});
                    $("#approveCommentModel").modal('hide');
                    setTimeout(function(){location.reload();}, 2000);

                }else{
                    $.toaster({ priority : 'danger', title : 'Error', message : 'Failure in comment approve ' +cmntId});
                    $("#approveCommentModel").modal('hide');
                    setTimeout(function(){location.reload();}, 2000);
                }
            },
            error:function(){
                alert("ajax failed in comment approve" +cmntId);
            }
        });
    });


    //function to discard a comment selected
    $("#btnDiscardComment").click(function(){
        var dcmntId = $('#discardCommentId').val();
        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/notification/commentDiscard",
            data: {"dcmntId": dcmntId},
            success: function(msg){
                if(msg == 1){

                    $.toaster({priority: 'success', title: 'Success', message: 'Comment Discard is successful '
                    +dcmntId});
                    $("#discardCommentModel").modal('hide');
                    setTimeout(function(){location.reload();}, 2000);

                }else{
                    $.toaster({ priority : 'danger', title : 'Error', message : 'Failure in comment discard ' +dcmntId});
                    $("#discardCommentModel").modal('hide');
                    setTimeout(function(){location.reload();}, 2000);
                }
            },
            error:function(){
                alert("ajax failed in comment approve" +dcmntId);
            }
        });
    });
});


//customerView js
function showMeComments(){
    var postedPeriod= document.getElementById("postedPeriod");
    //var customer= document.getElementById("customerSearch");
    var status= document.getElementById("statusSearch");


    //customer filter elements
    if (  $("#postCheck").is(":checked")){
        postedPeriod.style.display = "block";
    } else{
        postedPeriod.style.display = "none";
    }


    if (  $("#statusCheck").is(":checked")){
        status.style.display = "block";
    } else{
        status.style.display = "none";
    }
}


