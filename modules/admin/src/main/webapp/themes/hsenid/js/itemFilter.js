$(document).ready(function () {

});


function loadCategories() {
    $.ajax({
        type: 'GET',
        url: "https://localhost:8443/admin/itemFilters/categoryList",
        success: function (data) {
            var slctCatList = $("#selectCatFltr"), option = "";
           // slctCatList.empty();

            for (var cat = 0; cat < data.length; cat++) {
                option = option + "<option value='" + data[cat] + "'>" + data[cat] + "</option>";
            }
            slctCatList.append(option);
        }, error: function (e) {
            alert("error in loading category list" + e);
        }
    });
    return true;
}


function getAllSubcats() {
    $.ajax({
        type: 'GET',
        url: "https://localhost:8443/admin/itemFilters/subcategoryList",
        success: function (data) {
            var slctsubcatList2 = $("#selectSubCatFltr"), option = "";
            slctsubcatList2.empty();

            for (var sb2 = 0; sb2 < data.length; sb2++) {
                option = option + "<option value='" + data[sb2] + "'>" + data[sb2] + "</option>";
            }
            slctsubcatList2.append(option);
        }, error: function (e) {
            alert("error in loading sub-category list 2" + e);
        }
    });
}


function loadItemDetails(catName) {
    $.ajax({
        type: 'GET',
        url: "https://localhost:8443/admin/itemFilters/subcategoryList",
        data: {"catName": catName},
        success: function (data) {
            var slctsubcatList = $("#selectSubCatFltr"), option = "";
            slctsubcatList.empty();

            for (var sb = 0; sb < data.length; sb++) {
                option = option + "<option value='" + data[sb] + "'>" + data[sb] + "</option>";
            }
            slctsubcatList.append(option);

            //Load filtered data to table
            $("#selectSubCatFltr").attr("disabled",false);
            var itmName = $("#txtViewSearchItem").val();
            var pgLimit = 10;
            loadDatatoTable(itmName,pgLimit,"0",$("#selectCatFltr").val(),$("#selectSubCatFltr").val());


        }, error: function (e) {
            alert("error in loading sub-category list 1" + e);
        }
    });
    return $("#selectSubCatFltr").val();
}

function loadDatatoTable(srchItem, pgLimit, initPg, cat, subCat) {

    $.ajax({
        url: "https://localhost:8443/admin/itemFilters/loadSearchItem2",
        datatype: "JSON",
        data: {"srchItmNm": srchItem, "pgLimit": pgLimit, "initPage": initPg, "cat": cat, "subcat": subCat},
        success: function (data) {
            $("#tblItems").bootstrapTable('load', data);
            console.log("load data by common function 21478 " + data);
        },
        error: function (e) {
            alert("error, load search item by common function " + e);
            console.log("error, load data by common function" + e)
        }
    });
}
