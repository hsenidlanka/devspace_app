$(document).ready(function () {


    var pgLimit = 3;
    var catName = $("#txtViewSearchCategory").val();

    $.ajax({
        url: 'https://localhost:8443/admin/category/view/categoryTable',
        data: {"searchCatNm":catName, "initPage": "1", "pgLimit": pgLimit},
        success: function (result) {

            $('#tableCategory').bootstrapTable({
                height: 350,
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
                    class: 'col-xs-1'
                }, {
                    field: 'name',
                    title: 'Name :',
                    sortable: true,
                    align: 'left',
                    searchable: true,
                    class: 'col-xs-2',
                    formatter: linkFormatter,
                    events: linkEvents
                }, {
                    field: 'description',
                    title: 'Description :',
                    align: 'left',
                    sortable: true,
                    class: 'col-xs-5'
                }, {
                    field: 'status',
                    title: 'Visibility :',
                    align: 'left',
                    sortable: true,
                    class: 'col-xs-1'
                }, {
                    field: 'creator',
                    title: 'Creator:',
                    align: 'left',
                    sortable: true,
                    class: 'col-xs-1'
                }, {
                    field: 'Options',
                    title: 'Operations :',
                    align: 'center',
                    class: 'col-xs-2',
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
                //alert("image name"+image1);
                var  url="https://localhost:8443/admin/themes/hsenid/images/categories/" +image1;
                //alert(url);
                $('#imageUrl').attr('src',url);
                $('#categoryid').val(id);
                $('#editcategoryname').val(msg["name"]);
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

    //typical extra  pagination
    var pag2 = $('#pagination2Cat').simplePaginator({

        // the number of total pages
        totalPages: 7,

        // maximum of visible buttons
        maxButtonsVisible: 5,

        // page selected
        currentPage: 1,

        // text labels for buttons
        nextLabel: 'next',
        prevLabel: 'prev',
        firstLabel: 'first',
        lastLabel: 'last',

        // specify if the paginator click in the currentButton
        clickCurrentPage: true,

        // called when a page is changed.
        pageChange: function (page) {

            $.ajax({
                url: 'https://localhost:8443/admin/category/view/categoryTable',
                dataType: "json",
                data: {"searchCatNm": $("#txtViewSearchCategory").val(), "initPage": page, "pgLimit": pgLimit},
                success: function (data) {

                    $('#tableCategory').bootstrapTable('load', data);
                }
            })
        }
    });

    /*
     * load data on request typeahead
     **/
    $("#btnViewSearchCat").click(function () {

        if ($("#txtViewSearchCategory").val().length > 0) {

            $('#paginationCat').hide();
            $('#pagination2Cat').show();

            $.ajax({
                url: "https://localhost:8443/admin/category/view/categoryTable",
                datatype: "JSON",
                data: {"searchCatNm": $("#txtViewSearchCategory").val(), "pgLimit": pgLimit, "initPage": "1"},
                success: function (data) {
                    $("#tableCategory").bootstrapTable('load', data);
                    console.log(data);
                },
                error: function (e) {
                    alert("error, load search item" + e);
                    console.log("error, load search item" + e)
                }
            });

            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/category/CategoryPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {

                    pag2.simplePaginator('setTotalPages', Math.ceil(recCount / 3));
                }
            })
        }
        else {
            $('#paginationCat').show();
            $('#pagination2Cat').hide();

            $.ajax({
                url: 'https://localhost:8443/admin/category/view/categoryTable"',
                dataType: 'JSON',
                data: {"searchCatNm": $("#txtViewSearchCategory").val(),"initPage": "1", "pgLimit": pgLimit},
                success: function (data) {

                    $('#tableCategory').bootstrapTable('load', data);
                }
            })
        }
    });
});
