$(document).ready(function(){
    $(".cat").change(function(){

        var category= document.getElementsByName("catRadio");
        var category2= $(".cat").text();
        alert("category"+category+" "+category2+" " );
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/category/populateSubCategory",

                success: function (msg) {



                },
                error: function (e) {
                    alert("ajax failed" + e);
                }

            });
    });
});