$(document).ready(function(){
    $(".cat").change(function(){

        //var catSel= $(".cat").text();
        var categorySel=$('input:radio[name=catName]:checked').val();
        alert("category"+" "+categorySel );
            $.ajax({

                url: "https://localhost:8443/admin/category/populateSubCategory",
                data: {"categorySel":categorySel},
                success: function (msg) {
                    alert("ajax success" + msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }

            });
    });
});