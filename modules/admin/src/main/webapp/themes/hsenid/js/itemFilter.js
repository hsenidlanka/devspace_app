function showItemCheck(){

    var categoryChk = document.getElementById("catCheck");
    var subcatChk = document.getElementById("subCatCheck");
    var slctCat = document.getElementById("filterCatDiv");
    var slctSubcat = document.getElementById("filterSubcatDiv");

    //category & subCat filter elements
    if ($("#catCheck").is(":checked")){
        slctCat.style.display = "block";

        $.ajax({
            type:'GET',
            url:"https://localhost:8443/admin/itemFilters/categoryList",
            success:function(data){
                var slctCatList = $("#selectCatFltr"), option = "";
                slctCatList.empty();

                for (var cat = 0; cat < data.length; cat++) {
                    option = option + "<option value='" + data[cat] + "'>" + data[cat] + "</option>";
                }
                slctCatList.append(option);
            }, error: function (e) {
                alert("error in loading category list" + e);
            }
        });

    } else{
        slctCat.style.display = "none";
    }


    if ($("#subCatCheck").is(":checked")){
        slctSubcat.style.display = "block";
        var catName = $("#selectCatFltr").val();

        $("#selectCatFltr").change(function(){
            $.ajax({
                type:'GET',
                url:"https://localhost:8443/admin/itemFilters/subcategoryList",
                data:{"catName":catName},
                success:function(data){
                    var slctsubcatList = $("#selectSubCatFltr"), option = "";
                    slctsubcatList.empty();

                    for (var sb = 0; sb < data.length; sb++) {
                        option = option + "<option value='" + data[sb] + "'>" + data[sb] + "</option>";
                    }
                    slctsubcatList.append(option);
                }, error: function (e) {
                    alert("error in loading sub-category list" + e);
                }
            });
        })

    } else{
        slctSubcat.style.display = "none";
    }


}