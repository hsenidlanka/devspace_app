$(document).ready(function () {
    var value1=$("table").attr("data-value");
    //alert("category"+value1);

    var pgLimit = 3;
    var subcatName = $("#txtViewSearchSubCategory").val();
    //alert("subcatName"+subcatName);

    $.ajax({
        url: 'https://localhost:8443/admin/view/subcategoryTable/'+value1,
        data: {"searchSubCatNm":subcatName, "initPage": "1", "pgLimit": pgLimit},
        success: function (result) {

            $('#tableSubCategory').bootstrapTable({
                height: 380,
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
            data: {"subcatId":subcatId},
            success: function(msg){
                $('#subcategoryid').val(msg["id"]);
                $('#editsubcategoryname').val(msg["name"]);
                $('#editsubcategorydes').val(msg["description"]);
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
            url: "https://localhost:8443/admin/typeahedSubCategoryNm"+value1,
            dataType: "JSON",
            success: function (data) {
                console.log(data);
                $('#txtViewSearchSubCategory').typeahead({
                    source: data
                }).focus();
            },
            error: function (er) {
                console.log("error in typeahead " + er)
            }
        })
    });

    //typical pagination
    var pag2 = $('#pagination2SubCat').simplePaginator({

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
                url: 'https://localhost:8443/admin/view/subcategoryTable/'+value1,
                dataType: "json",
                data: {"searchSubCatNm": $("#txtViewSearchSubCategory").val(), "initPage": page, "pgLimit": pgLimit},
                success: function (data) {

                    $('#tableSubCategory').bootstrapTable('load', data);
                }
            })
        }
    });

    /*
     * load data on request typeahead
     **/
    $("#btnViewSearchSubCat").click(function () {

        if ($("#txtViewSearchSubCategory").val().length > 0) {

            $('#paginationSubCat').hide();
            $('#pagination2SubCat').show();

            $.ajax({
                url: "https://localhost:8443/admin/view/subcategoryTable/"+value1,
                datatype: "JSON",
                data: {"searchSubCatNm": $("#txtViewSearchSubCategory").val(), "pgLimit": pgLimit, "initPage": "1"},
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
                url: 'https://localhost:8443/admin/SubCategoryPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {
                    pag2.simplePaginator('setTotalPages', Math.ceil(recCount / 3));
                }
            })
        }
        else {
            $('#paginationSubCat').show();
            $('#pagination2SubCat').hide();

            $.ajax({
                url: 'https://localhost:8443/admin/view/subcategoryTable/'+value1,
                dataType: 'JSON',
                data: {"searchCatNm": $("#txtViewSearchSubCategory").val(),"initPage": "1", "pgLimit": pgLimit},
                success: function (data) {

                    $('#tableSubCategory').bootstrapTable('load', data);
                }
            })
        }
    });
});