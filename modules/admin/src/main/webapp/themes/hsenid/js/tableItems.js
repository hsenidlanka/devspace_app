var catVal;
var subCatVal;

$(document).ready(function () {

    $("#selectCatFltr").hide();
    $("#selectSubCatFltr").hide();

    var pgLimit = 10;
    loadCategories();
    if (loadCategories() == true) {
        loadSubCats($("#selectCatFltr").val());
    }

    var cat;
    var subcat;


    var itmName = $("#txtViewSearchItem").val();

    $.ajax({
        url: "https://localhost:8443/admin/items/loadSearchItem",
        dataType: "json",
        data: {"srchItmNm": itmName, "initPage": "0", "pgLimit": pgLimit},
        success: function (result) {

            $('#tblItems').bootstrapTable({
                height: 400,
                striped: true,
                pagination: false,
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
                }, {
                    field: 'category_name',
                    title: 'Category',
                    sortable: true
                }, {
                    field: 'sub_category_name',
                    title: 'Sub-category',
                    sortable: true
                }, {
                    field: 'type',
                    title: 'Type',
                    sortable: true
                }, {
                    field: 'created_date',
                    title: 'Added Date',
                    sortable: true
                }, {
                    field: 'creator',
                    title: 'Creator',
                    sortable: true
                }, {
                    field: 'description',
                    title: 'Item description',
                    align: 'left',
                    sortable: true
                }, {
                    field: 'operate',
                    title: 'Operations',
                    align: 'center',
                    formatter: operateFormatter,
                    events: operateEvents
                }],
                data: result
            });
        }
    });

    $("#btnDeltItm").click(function () {
        var itemName = $("#lblDeltItmName").text();
        $.ajax({
            type: "POST",
            url: "https://localhost:8443/admin/items/delete_item",
            data: {"itemName": itemName},

            success: function (data) {
                $.toaster({priority: 'success', title: 'Success', message: 'Deleted the item  ' + itemName});
                setTimeout(function () {
                    location.reload();
                }, 3000);
            },
            error: function (e) {
                $.toaster({priority: 'danger', title: 'Error', message: 'Cannot delete the item ' + itemName});
                setTimeout(function () {
                    location.reload();
                }, 3000);
            }
        })
    });

    //reset edit item details form form on hide
    $('#itemEditModal').on('hidden.bs.modal', function () {
        $(this).find('form')[0].reset();
        $(".price").prop("disabled", true);
    });

    /*
     * typeahead function for load item names
     * */
    $("#txtViewSearchItem").keyup(function () {

        if ($("#catCheck").is(":checked")) {
            cat = $("#selectCatFltr").val();
        } else {
            cat = null;
        }

        if ($("#subCatCheck").is(":checked")) {
            subcat = $("#selectSubCatFltr").val();
        } else {
            subcat = null;
        }
        // alert(cat +" "+ subcat);
        $.ajax({
            type: "GET",
            url: "https://localhost:8443/admin/items/typeahedItmNm",
            dataType: "JSON",
            data: {"cat": cat, "subcat": subcat},
            success: function (data) {
                console.log("typeahead json " + data);
                $('#txtViewSearchItem').typeahead({
                    source: data
                }).focus();
            },
            error: function (er) {
                console.log("error in typeahead " + er)
            }
        })
    });

/////////////////////////////////////////////////////////////////////
    /*
     function getTypeaheadResult(){
     $.ajax({
     type: "GET",
     url: "https://localhost:8443/admin/items/typeahedItmNm",
     dataType: "JSON",
     data:{"cat":cat, "subcat":subcat},
     success: function (data) {
     //  alert(data);
     console.log(data);
     $('#txtViewSearchItem').typeahead({
     source: data
     }).focus();
     },
     error: function (er) {
     console.log("error in typeahead " + er)
     }
     })
     }
     */


    var pag2 = $('#pagination2').simplePaginator({

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

            var initpg = (page - 1) * pgLimit;
            $.ajax({
                url: 'https://localhost:8443/admin/items/loadSearchItem',
                dataType: "json",
                data: {"srchItmNm": $("#txtViewSearchItem").val(), "initPage": initpg, "pgLimit": pgLimit},
                success: function (data) {

                    $('#tblItems').bootstrapTable('load', data);
                }
            })
        }
    });

    /*
     * load data on request typeahead
     **/
    $("#btnViewSearchItem").click(function () {

        if ($("#txtViewSearchItem").val().length > 0) {

            $('#pagination').hide();
            $('#pagination2').show();

            if (!$("#subCatCheck").is(":checked")) {
                if (!$("#catCheck").is(":checked")) {

                    $.ajax({
                        url: "https://localhost:8443/admin/items/loadSearchItem",
                        datatype: "JSON",
                        data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0"},
                        success: function (data) {
                            $("#tblItems").bootstrapTable('load', data);
                            // console.log(data);
                        },
                        error: function (e) {
                            alert("error, load search item" + e);
                            console.log("error, load search item" + e)
                        }
                    })
                }
                else {
                    cat = $("#selectCatFltr").val();
                    subcat = "";
                    var itmNm = $("#txtViewSearchItem").val();

                    loadDatatoTable(itmNm, pgLimit, "0", cat, subcat);


                    /* $.ajax({
                     url: "https://localhost:8443/admin/itemFilters/loadSearchItem2",
                     datatype: "JSON",
                     data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0","cat":cat,"subcat":subcat},
                     success: function (data) {
                     $("#tblItems").bootstrapTable('load', data);
                     // console.log(data);
                     },
                     error: function (e) {
                     alert("error, load search item" + e);
                     console.log("error, load search item-cat" + e)
                     }
                     })*/
                }
            }
            else {
                cat = "";
                subcat = $("#selectSubCatFltr").val();
                var itmNm = $("#txtViewSearchItem").val();

                loadDatatoTable(itmNm, pgLimit, "0", cat, subcat);

                /*     $.ajax({
                 url: "https://localhost:8443/admin/itemFilters/loadSearchItem2",
                 datatype: "JSON",
                 data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0", "cat":cat, "subcat":subcat},
                 success: function (data) {
                 $("#tblItems").bootstrapTable('load', data);
                 // console.log(data);
                 },
                 error: function (e) {
                 alert("error, load search item" + e);
                 console.log("error, load search item-subcat" + e)
                 }
                 })*/
            }


            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/items/itemSearchCount',
                data: {"srchItmNm": $("#txtViewSearchItem").val()},
                success: function (recCount) {

                    pag2.simplePaginator('setTotalPages', Math.ceil(recCount / 10));
                }
            })
        }
        else {
            $('#pagination').show();
            $('#pagination2').hide();

            /* $.ajax({
             url: 'https://localhost:8443/admin/items/loadSearchItem',
             dataType: 'JSON',
             data: {"initPage": "1", "pgLimit": pgLimit},
             success: function (data) {

             $('#tblItems').bootstrapTable('load', data);
             }
             })*/


            ////
            if (!$("#subCatCheck").is(":checked")) {
                if (!$("#catCheck").is(":checked")) {

                    $.ajax({
                        url: "https://localhost:8443/admin/items/loadSearchItem",
                        datatype: "JSON",
                        data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0"},
                        success: function (data) {
                            $("#tblItems").bootstrapTable('load', data);
                            // console.log(data);
                        },
                        error: function (e) {
                            alert("error, load search item" + e);
                            console.log("error, load search item" + e)
                        }
                    })
                }
                else {
                    cat = $("#selectCatFltr").val();
                    subcat = "";
                    var itmNm = $("#txtViewSearchItem").val();

                    loadDatatoTable(itmNm, pgLimit, "0", cat, subcat);

                    /* $.ajax({
                     url: "https://localhost:8443/admin/itemFilters/loadSearchItem2",
                     datatype: "JSON",
                     data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0","cat":cat,"subcat":subcat},
                     success: function (data) {
                     $("#tblItems").bootstrapTable('load', data);
                     // console.log(data);
                     },
                     error: function (e) {
                     alert("error, load search item" + e);
                     console.log("error, load search item-cat" + e)
                     }
                     })*/
                }
            }
        }
    });

    $("#txtViewSearchItem").keyup(function () {

        if ($("#txtViewSearchItem").val().length > 0) {

            $('#pagination').hide();
            $('#pagination2').show();

            if (!$("#subCatCheck").is(":checked")) {
                if (!$("#catCheck").is(":checked")) {

                    $.ajax({
                        url: "https://localhost:8443/admin/items/loadSearchItem",
                        datatype: "JSON",
                        data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0"},
                        success: function (data) {
                            $("#tblItems").bootstrapTable('load', data);
                            // console.log(data);
                        },
                        error: function (e) {
                            alert("error, load search item" + e);
                            console.log("error, load search item" + e)
                        }
                    })
                }
                else {
                    cat = $("#selectCatFltr").val();
                    subcat = "";
                    var itmNm = $("#txtViewSearchItem").val();

                    loadDatatoTable(itmNm, pgLimit, "0", cat, subcat)
                }
            }
            else {
                cat = "";
                subcat = $("#selectSubCatFltr").val();
                var itmNm = $("#txtViewSearchItem").val();

                loadDatatoTable(itmNm, pgLimit, "0", cat, subcat);
            }

            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/items/itemSearchCount',
                data: {"srchItmNm": $("#txtViewSearchItem").val()},
                success: function (recCount) {

                    pag2.simplePaginator('setTotalPages', Math.ceil(recCount / 10));
                }
            })
        }
        else {
            $('#pagination').show();
            $('#pagination2').hide();

            /* $.ajax({
             url: 'https://localhost:8443/admin/items/loadSearchItem',
             dataType: 'JSON',
             data: {"initPage": "1", "pgLimit": pgLimit},
             success: function (data) {

             $('#tblItems').bootstrapTable('load', data);
             }
             })*/

            /////
            if (!$("#subCatCheck").is(":checked")) {
                if (!$("#catCheck").is(":checked")) {

                    $.ajax({
                        url: "https://localhost:8443/admin/items/loadSearchItem",
                        datatype: "JSON",
                        data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0"},
                        success: function (data) {
                            $("#tblItems").bootstrapTable('load', data);
                            // console.log(data);
                        },
                        error: function (e) {
                            alert("error, load search item" + e);
                            console.log("error, load search item" + e)
                        }
                    })
                }
                else {
                    cat = $("#selectCatFltr").val();
                    subcat = "";
                    var itmNm = $("#txtViewSearchItem").val();

                    loadDatatoTable(itmNm, pgLimit, "0", cat, subcat);

                    /*  $.ajax({
                     url: "https://localhost:8443/admin/itemFilters/loadSearchItem2",
                     datatype: "JSON",
                     data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0","cat":cat,"subcat":subcat},
                     success: function (data) {
                     $("#tblItems").bootstrapTable('load', data);
                     // console.log(data);
                     },
                     error: function (e) {
                     alert("error, load search item" + e);
                     console.log("error, load search item-cat" + e)
                     }
                     })*/
                }
            }
        }
    });


    /*
     * for loading data in table on dropdown change event
     * */
    /*
     $("#catCheck").click(function () {

     if($("#catCheck").is(":checked")){

     categoryLoading(pgLimit);
     $("#selectCatFltr").show();
     subCategoryLoading(pgLimit);
     // $('#pagination').show();
     //  $('#pagination2').hide();

     $("#selectCatFltr").change(function(){
     categoryLoading(pgLimit);
     console.log("slctCat onchange evnt ");
     })
     }

     if(!$("#catCheck").is(":checked")){
     $("#selectCatFltr").hide();
     getAllSubcats();
     subCategoryLoading(pgLimit);
     // loadDatatoTable($("#txtViewSearchItem").val(),pgLimit,"0","",$("#selectSubCatFltr").val());
     $.ajax({
     url: "https://localhost:8443/admin/items/loadSearchItem",
     datatype: "JSON",
     data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0"},
     success: function (data) {
     $("#tblItems").bootstrapTable('load', data);
     // console.log(data);
     },
     error: function (e) {
     alert("error, load search item" + e);
     console.log("error, load search item" + e)
     }
     })

     }
     });


     $("#subCatCheck").click(function () {
     if($("#subCatCheck").is(":checked")){

     // $('#pagination').show();
     //  $('#pagination2').hide();
     subCategoryLoading(pgLimit);
     $("#selectSubCatFltr").show();

     $("#selectSubCatFltr").change(function(){
     subCategoryLoading(pgLimit);

     })
     }

     if(!$("#subCatCheck").is(":checked")){
     $("#selectSubCatFltr").hide();

     if($("#catCheck").is(":checked")){
     $("#selectCatFltr").show();

     cat=$("#selectCatFltr").val();
     subcat="";
     subCategoryLoading(pgLimit);
     //loadDatatoTable("",pgLimit,"0",cat,subcat);
     }else{
     $.ajax({
     url: "https://localhost:8443/admin/items/loadSearchItem",
     datatype: "JSON",
     data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0"},
     success: function (data) {
     $("#tblItems").bootstrapTable('load', data);
     // console.log(data);
     },
     error: function (e) {
     alert("error, load search item" + e);
     console.log("error, load search item" + e)
     }
     })
     }
     }
     });
     */

//*******///
    $("#catCheck").click(function () {

        if ($("#catCheck").is(":checked")) {
            $("#selectCatFltr").show();

            if ($("#subCatCheck").is(":checked")) {

                $("#selectSubCatFltr").show();
                //      subCatVal = $("#selectSubCatFltr").val();

                loadSubCats($("#selectCatFltr").val());
                loadDatatoTable("", pgLimit, "0", $("#selectCatFltr").val(), $("#selectSubCatFltr").val());
                //subCategoryLoading(pgLimit);
            } else {
                $("#selectSubCatFltr").hide();
                //  loadSubCats($("#selectCatFltr").val());
            }
        }
        else {
            $("#selectCatFltr").hide();
            loadCategories();
            getAllSubcats();
            //$.ajax({
            //    url: "https://localhost:8443/admin/items/loadSearchItem",
            //    datatype: "JSON",
            //    data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0"},
            //    success: function (data) {
            //        $("#tblItems").bootstrapTable('load', data);
            //        console.log(data);
            //    },
            //    error: function (e) {
            //        alert("error, load search item" + e);
            //        console.log("error, load search item" + e)
            //    }
            //});
            if ($("#subCatCheck").is(":checked")) {
                // $("#selectSubCatFltr").show();
                getAllSubcats();
                loadDatatoTable("", pgLimit, "0", "", $("#selectSubCatFltr").val());
            } else {
                loadDatatoTable("", pgLimit, "0", "", "");
                console.log("Fffeeaa");
            }

        }
        //loadCategories();
        //loadSubCats($("#selectCatFltr").val());
        categoryLoading(pgLimit);
        // subCategoryLoading(pgLimit);

    });


    $("#subCatCheck").click(function () {
        if ($("#subCatCheck").is(":checked")) {
            $("#selectSubCatFltr").show();

            if ($("#catCheck").is(":checked")) {
                $("#selectCatFltr").show();
            }
            else {
                $("#selectCatFltr").hide();
                getAllSubcats();
            }
        }
        else {
            $("#selectSubCatFltr").hide();
            loadCategories();
            loadSubCats($("#selectCatFltr").val());

            //$.ajax({
            //    url: "https://localhost:8443/admin/items/loadSearchItem",
            //    datatype: "JSON",
            //    data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0"},
            //    success: function (data) {
            //        $("#tblItems").bootstrapTable('load', data);
            //        console.log(data);
            //    },
            //    error: function (e) {
            //        alert("error, load search item" + e);
            //        console.log("error, load search item" + e)
            //    }
            //});
            if (!$("#catCheck").is(":checked")) {

                $.ajax({
                    url: "https://localhost:8443/admin/items/loadSearchItem",
                    datatype: "JSON",
                    data: {"srchItmNm": $("#txtViewSearchItem").val(), "pgLimit": pgLimit, "initPage": "0"},
                    success: function (data) {
                        $("#tblItems").bootstrapTable('load', data);
                        console.log(data);
                    },
                    error: function (e) {
                        alert("error, load search item" + e);
                        console.log("error, load search item" + e)
                    }
                })
            } else {
                loadDatatoTable("", pgLimit, "0", $("#selectCatFltr").val(), "");
            }
        }
        subCategoryLoading(pgLimit);
    });


    ///////

    $('#selectCatFltr').change(function () {

        if ($("#selectSubCatFltr").is(":visible")) {
            $("#selectSubCatFltr").val("");
        }
        subCategoryLoading(pgLimit);
        loadSubCats($(this).val());

        categoryLoading(pgLimit);
        console.log("onchange 123456777 ");
    });

    $("#selectSubCatFltr").change(function () {
        subCategoryLoading(pgLimit);
        console.log("onchange 4578 147");
    });

    // categoryLoading(pgLimit,$("#selectCatFltr").val(),$("#selectSubCatFltr").val());
    //subCategoryLoading(pgLimit,$("#selectCatFltr").val(),$("#selectSubCatFltr").val());
});

function operateFormatter(value, row, index) {
    return [
        '<center>',
        '<a class="edit" title="Edit" id="linkEditItem">',
        '<i class="glyphicon glyphicon-edit">Edit</i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="deleteItem" title="Delete" id="linkDeleteItem">',
        '<i class="glyphicon glyphicon-remove">Delete</i>',
        '</a></center>'
    ].join('');
}

window.operateEvents = {

    'click .edit': function (e, value, row, index) {

        var data = JSON.stringify(row);
        var objct = JSON.parse(data);

        var itemId = objct["id"];
        $.ajax({
            //dataType:"JSON",
            type: "POST",
            url: "https://localhost:8443/admin/items/edit_item",
            data: {"itemId": itemId},
            success: function (data) {

                var parsedData = JSON.parse(data);
                $.each(parsedData, function (key, value) {

                    switch (key) {
                        case "regular":
                            $('#chkedtReg').prop("checked", true);
                            $('#txtEdtPrcReg').prop("disabled", false).val(value);
                            break;
                        case "medium":
                            $('#chkedtMed').prop("checked", true);
                            $('#txtEdtPrcMed').prop("disabled", false).val(value);
                            break;
                        case "large":
                            $('#chkedtLrg').prop("checked", true);
                            $('#txtEdtPrcLrg').prop("disabled", false).val(value);
                            break;
                        case "other":
                            $('#chkedtOthr').prop("checked", true);
                            $('#txtEdtPrcOthr').prop("disabled", false).val(value);
                            break;
                        default:
                            $(".aa").prop("checked", false);
                            $(".price").prop("disabled", true);
                            alert("error");
                            break;
                    }
                });

                $("#hiddenId").val(objct["id"]); // set id to a hidden field
                $("#txtEditName").val(objct["item_name"]); // set item_name field
                $("#selectCatedt").val(objct["category_name"]); // set category_name as selected
                setSubCats(objct["category_name"]); // set sub-category name
                $(":radio[value='" + objct["type"] + "']").prop('checked', true); // set item_type as checked
                $("#txtEditDesc").val(objct["description"]);

                $('#itemEditModal').modal('show');
            },
            error: function (a, s, error) {
                alert(error);
                alert("error in edit item " + e + ' ' + itemId)
            }
        })
    },

    'click .deleteItem': function (e, value, row, index) {

        var dataDelt = JSON.stringify(row);
        var deltObj = JSON.parse(dataDelt);

        $('#lblDeltItmId').text(deltObj["id"]);
        $('#lblDeltItmName').text(deltObj["item_name"]);
        $('#lblDeltItmType').text(deltObj["category_name"]);

        $('#itemDeleteModal').modal('show');
    }
};


//function categoryLoading(pgLimit,cat,subcat){
function categoryLoading(pgLimit) {

    if (!$("#subCatCheck").is(":checked")) {

        catVal = $("#selectCatFltr").val();
        subCatVal = "";
        //alert(catVal+" 1 "+subCatVal);
        loadDatatoTable("", pgLimit, "0", catVal, subCatVal)

    }
    if ($("#subCatCheck").is(":checked")) {
        catVal = $("#selectCatFltr").val();
        subCatVal = $("#selectSubCatFltr").val();
        //alert(catVal+" 2 "+subCatVal);
        loadDatatoTable("", pgLimit, "0", catVal, subCatVal);
    }

}

//function subCategoryLoading(pgLimit,cat,subcat){
function subCategoryLoading(pgLimit) {

    if (!$("#catCheck").is(":checked")) {

        catVal = "";
        subCatVal = $("#selectSubCatFltr").val();
        // alert(catVal+" 3 "+subCatVal);
        loadDatatoTable("", pgLimit, "0", "", $("#selectSubCatFltr").val());
    }
    if ($("#catCheck").is(":checked")) {
        catVal = $("#selectCatFltr").val();
        subCatVal = $("#selectSubCatFltr").val();
        //alert(catVal+" 4 "+subCatVal);
        loadDatatoTable("", pgLimit, "0", catVal, subCatVal);

    }
}





