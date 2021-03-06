$(document).ready(function () {
    var value1=$("table").attr("data-value");
    //alert("category"+value1);

    var pgLimit = 3;
    var subcatName = $("#txtViewSearchSubCategory").val();
    //alert("subcatName"+subcatName);

    $.ajax({
        url: 'https://localhost:8443/admin/view/subcategoryTable/'+value1,
        data: {"initPage": "0", "pgLimit": pgLimit},
        success: function (result) {

            $('#tableSubCategory').bootstrapTable({
                height: 360,
                pagination: false,
                clickToSelect: true,
                singleSelect: true,
                search: false,
                showColumns: false,
                showRefresh: false,
                minimumCountColumns: 3,
                columns: [{
                    field: 'id',
                    title: 'ID :',
                    sortable: true,
                    align: 'left'
                }, {
                    field: 'name',
                    title: 'Name :',
                    sortable: true,
                    align: 'left',
                    searchable: true
                }, {
                    field: 'description',
                    title: 'Description :',
                    align: 'left',
                    class: 'col-xs-5',
                    sortable: true
                }, {
                    field: 'creator',
                    title: 'Creator:',
                    align: 'left'
                },{
                    field: 'created_date',
                    title: 'Created Date:',
                    align: 'left',
                    sortable: true

                }, {
                    field: 'Options',
                    title: 'Operations :',
                    align: 'center',
                    formatter: operateFormatter6,
                    events: operateEvents6
                }],
                data: result
            });
        }
});


function operateFormatter6(value, row, index) {
    return [
        '<a class="likesubcat" href="javascript:void(0)" title="LikeCustomer" data-toggle="modal" >',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        //'<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="removesubcat" href="javascript:void(0)" title="Delete">',
        '<i class="glyphicon glyphicon-remove">Delete</i>',
        '</a>'
    ].join('');
}


window.operateEvents6 = {
    'click .removesubcat': function(e, value, row, index) {
        var data2 = JSON.stringify(row);
        var objc2 = JSON.parse(data2);
        $('#lblDeleteSubCategoryId').text(objc2["name"]);
        $('#deleteModal2').modal({show:true});
    },
    'click .likesubcat': function(e, value, row, index) {
        var dataEdit = JSON.stringify(row);
        var objcEdit = JSON.parse(dataEdit);
        var subcatId= objcEdit["id"];
        //alert("success function subcat id"+subcatId);

        $.ajax({
            //type: "POST",
            url: "https://localhost:8443/admin/subcategory/edit",
            data: {"subcatId":subcatId,"catValue":value1},
            success: function(msg){
                $('#subcategoryid').val(msg["id"]);
                $('#editsubcategoryname').val(msg["name"]);
                $('#editsubcategorydes').val(msg["description"]);
                $('#cat_name').val(msg["catName"]);

                $('#subcatmodifyModel').modal({show:true});
            },
            error:function(e){
                alert("ajax failed" +subcatId+ ""+ e);
            }
        });
    }
};

//function to delete a sub-category selected

    $("#btnDeleteSubCategory").click(function(){
        var subcatName = $('#lblDeleteSubCategoryId').text();

        $.ajax({
            url: "https://localhost:8443/admin/subcategory/delete",
            data: {"subcatName": subcatName},
            success: function(msg){
                if(msg == 1){
                    $.toaster({priority: 'success', title: 'Success', message: 'Deleted category successfully  '+subcatName});
                    $("#deleteModal2").modal('hide');
                    setTimeout(function(){location.reload();}, 3000);

                }else{

                    $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot delete the category' +subcatName});
                    $("#deleteModal2").modal('hide');
                    setTimeout(function(){location.reload();}, 3000);
                }
            },
            error:function(){
                alert("ajax failed" +subcatName);
            }
        });

    });


    /*
     * typeahead function for load category names
     * */
    $("#txtViewSearchSubCategory").keyup(function () {

        $.ajax({
            type: "GET",
            url: 'https://localhost:8443/admin/typeahedSubCategoryNm',
            data: {"catName": value1},
            success: function (data) {

                $('#txtViewSearchSubCategory').typeahead({
                    source: data
                }).focus();
            },
            error: function (er) {
                console.log("error in typeahead " + er)
            }
        })
    });

});