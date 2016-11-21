$(document).ready(function () {

    $('#tableCategory').bootstrapTable({

        url: 'https://localhost:8443/admin/category/view/categoryTable',
        height: 375,
        pagination: true,
        pageSize: 3,
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
            field: 'name',
            title: 'Name :',
            sortable: true,
            align:'left',
            searchable:true,
            class:'col-xs-2',
            formatter:linkFormatter,
            events: linkEvents
        }, {
            field: 'description',
            title: 'Description :',
            align:'left',
            sortable: true,
            class:'col-xs-5'

        }, {
            field: 'status',
            title: 'Visibility :',
            align:'left',
            sortable: true,
            class:'col-xs-1'

        }, {
            field: 'creator',
            title: 'Creator:',
            align:'left',
            sortable: true,
            class:'col-xs-1'

        }, {
            field: 'Options',
            title: 'Operations :',
            align: 'center',
            class:'col-xs-2',
            formatter: operateFormatter5,
            events: operateEvents5
        }]
    });
});

function linkFormatter(value, row, index) {
    return [
        '<a class="link" href="javascript:void(0)" title="Like">',
        value,
        '</a>'].join('');
}

window.linkEvents = {
    'click .link': function(e, value, row, index) {
        var data1 = JSON.stringify(row);
        var objc1 = JSON.parse(data1);
        var name= objc1["name"];
        //alert("value"+ value);
        window.location.href = "https://localhost:8443/admin/subCategory/"+value;

    }
};


function operateFormatter5(value, row, index) {
    return [
        '<a class="likecat" href="javascript:void(0)" title="LikeCustomer" data-toggle="modal" >',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        //'<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="removecat" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-remove">Delete</i>',
        '</a>'
    ].join('');
}


window.operateEvents5 = {
    'click .removecat': function(e, value, row, index) {
        var data2 = JSON.stringify(row);
        var objc2 = JSON.parse(data2);
        $('#lblDeleteCategoryId').text(objc2["name"]);
        $('#deleteModal1').modal({show:true});
    },
    'click .likecat': function(e, value, row, index) {
        var data1 = JSON.stringify(row);
        var objc1 = JSON.parse(data1);
        var id= objc1["id"];

        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/category/edit",
            data: {"id":id},
            success: function(msg){
                var name=msg["name"];
                $('#categoryid').val(id);
                $('#editcategoryname').val(msg["name"]);
                $('#editcategorydes').val(msg["description"]);
                $('#editvisibility').val(msg["status"]);
                //$('#imagecat').text(msg["image"]);

                $('#modifyModel').modal({show:true});
                //alert(msg+""+a+""+b);
            },
            error:function(e){
                alert("ajax failed");
            }
        });
    }
};


//function to delete a category selected
$(document).ready(function(){
    $("#btnDeleteCategory").click(function(){
        var catName = $('#lblDeleteCategoryId').text();

        $.ajax({
            url: "https://localhost:8443/admin/category/delete",
            data: {"catName": catName},
            success: function(msg){
                if(msg == 1){
                    $.toaster({priority: 'success', title: 'Success', message: 'Deleted category successfully  '+catName});
                    $("#deleteModal1").modal('hide');
                    setTimeout(function(){location.reload();}, 3000);

                }else{

                    $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot delete the category' +catName});
                    $("#deleteModal1").modal('hide');
                    setTimeout(function(){location.reload();}, 3000);
                }
            },
            error:function(){
                alert("ajax failed" +catName);
            }
        });

    });
});
