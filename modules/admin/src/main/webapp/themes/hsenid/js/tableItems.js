$(document).ready(function () {

        $('#tblItems').bootstrapTable({
            dataType:'JSON',
            url: 'https://localhost:8443/admin/items/view/itemTable',
            height: 400,
            striped: true,
            pagination: true,
            pageSize: 10,
            pageList: [10, 25, 50, 100, 200],
            search: false,
            showColumns: false,
            showRefresh: false,
            minimumCountColumns: 2,
            columns: [{
                field: 'id',
                title: 'Item ID',
                sortable: true
            }, {
                field: 'item_name',
                title: 'Item Name',
                sortable: true
            },{
                field: 'category_name',
                title: 'Category',
                sortable: true
            },{
                field: 'sub_category_name',
                title: 'Sub-category',
                sortable: true
            }, {
                field: 'type',
                title: 'Type',
                sortable: true
            }, {
                field: 'price',
                title: 'Item Price',
                align: 'right',
                sortable: true
            },{
                field: 'description',
                title: 'Item desc',
                align: 'right',
                sortable: true
            },
                {
                field: 'image',
                title: 'Images',
                sortable: true
            },
                {
                field: 'operate',
                title: 'Operations',
                align: 'center',
                formatter: operateFormatter,
                events: operateEvents
            }]
        });

    $("#btnDeltItm").click(function(){
        var itemName = $("#lblDeltItmName").text();
        $.ajax({
            type:"POST",
            url:"https://localhost:8443/admin/items/delete_item",
            data:{"itemName": itemName},

            success: function(data){
                    $.toaster({priority: 'success', title: 'Success', message: 'Deleted the item  '+itemName});
                    setTimeout(function(){location.reload();}, 2000);
            },
            error: function(e){
                $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot delete the item ' +itemName});
                setTimeout(function(){location.reload();}, 2000);
            }
        })
    })
    
});

function operateFormatter(value, row, index){
    return[
        '<center>',
        '<a class="edit" title="Edit" id="linkEditItem">',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="delete" title="Delete" id="linkDeleteItem">',
        '<i class="glyphicon glyphicon-remove">Delete</i>',
        '</a></center>'
    ].join('');
}

window.operateEvents = {

    'click .edit': function (e, value, row,index){

        var data =JSON.stringify(row);
        var objct = JSON.parse(data);

        var itemId = objct["id"];
        $.ajax({
            type:"POST",
            url:"https://localhost:8443/admin/items/edit_item",
            data:{"itemId": itemId},
            success: function(data){
                alert(data);

                $("#txtEditName").val(objct["item_name"]); // set item_name field
                $("#selectCatedt").val(objct["category_name"]); // set category_name as selected
                setSubCats(objct["category_name"]); // set sub-category name
                $(":radio[value='" + objct["type"] + "']").prop('checked', true); // set item_type as checked

                /*setPriceSize(itemId);*/
                $('#itemEditModal').modal('show');
            },
            error:function(a,s,error){
                alert(error);
             alert("error in edit item "+ e +' '+ itemId)
            }
        })
    },

    'click .delete':function(e, value, row,index){

        var dataDelt = JSON.stringify(row);
        var deltObj = JSON.parse(dataDelt);

      $('#lblDeltItmId').text(deltObj["id"]);
        $('#lblDeltItmName').text(deltObj["item_name"]);
        $('#lblDeltItmType').text(deltObj["category_name"]);

        $('#itemDeleteModal').modal('show');
    }
}


