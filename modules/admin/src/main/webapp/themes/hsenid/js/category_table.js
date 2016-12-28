$(document).ready(function () {


    var pgLimit = 3;
    var catName = $("#txtViewSearchCategory").val();

    $.ajax({
        url: 'https://localhost:8443/admin/category/view/categoryTable',
        data: {"initPage": "0", "pgLimit": pgLimit},
        success: function (result) {

            $('#tableCategory').bootstrapTable({
                height: 370,
                pagination: false,
                search: false,
                showColumns: false,
                showRefresh: false,
                minimumCountColumns: 2,
                columns: [{
                    field: 'id',
                    title: 'ID :',
                    sortable: true,
                    align: 'left',
                    //class: 'col-xs-1'
                }, {
                    field: 'name',
                    title: 'Name :',
                    sortable: true,
                    align: 'left',
                    searchable: true,
                    //class: 'col-xs-2',
                    formatter: linkFormatter,
                    events: linkEvents
                }, {
                    field: 'description',
                    title: 'Description :',
                    align: 'left',
                    sortable: true,
                    class: 'col-xs-4'
                }, {
                    field: 'creator',
                    title: 'Creator:',
                    align: 'left',
                    sortable: true
                    //class: 'col-xs-1'
                }, {
                    field: 'created_date',
                    title: 'Created Date:',
                    align: 'left',
                    sortable: true
                    //class: 'col-xs-1'
                }, {
                    field: 'status',
                    title: 'Visibility :',
                    align: 'left',
                    sortable: true
                    //class: 'col-xs-1'
                }, {
                    field: 'Options',
                    title: 'Operations :',
                    align: 'center',
                    //class: 'col-xs-2',
                    formatter: operateFormatter5,
                    events: operateEvents5
                }],
                data: result
            });
        }
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
        var name= objc1["name"];
        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/category/edit",
            data: {"id":id},
            success: function(msg){
                var name=msg["name"];
                var image1=msg["image"];

                var  url="https://localhost:8443/admin/themes/hsenid/images/categories/" +image1;
                //alert(url);
                $('#imageUrl').attr('src',url);
                $('#categoryid').val(id);
                $('#editcategoryname').val(msg["name"]);
                $('#catName').val(msg["name"]);

                $('#editcategorydes').val(msg["description"]);
                $('#editvisibility').val(msg["status"]);

                $('#modifyModel').modal({show:true});
            },
            error:function(e){
                alert("ajax failed");
            }
        });
    }
};
    //to enable the upload image button on image edit
    $('#imageEditButton').click(function()
    {
        document.getElementById("editbtnUpldImage").disabled = false;

    });

//function to delete a category selected

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


    /*
     * typeahead function for load category names
     * */
    $("#txtViewSearchCategory").keyup(function () {

        $.ajax({
            type: "GET",
            url: "https://localhost:8443/admin/category/typeahedCategoryNm",
            dataType: "JSON",
            success: function (data) {
                console.log(data);
                $('#txtViewSearchCategory').typeahead({
                    source: data
                }).focus();
            },
            error: function (er) {
                console.log("error in typeahead " + er)
            }
        })
    });


});
