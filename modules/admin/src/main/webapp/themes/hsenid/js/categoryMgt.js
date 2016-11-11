$(document).ready(function(){

    //js to load the subcategory list as radio button is clicked
    $(".cat").change(function(){
        var categorySel=$('input:radio[name=catName]:checked').val();
        var path="/admin/themes/hsenid/images/categories/"+categorySel+".jpg";
        path = path.replace(/\s/g, '');
        //alert("generated path is"+ path);

        document.getElementById('image').src= path;
        document.getElementById('image').style.visibility="visible";
        document.getElementById('subcat_label').style.visibility="visible";
        document.getElementById('subcat').style.visibility="visible";

        //alert("category"+" "+categorySel );

            $.ajax({

                url: "https://localhost:8443/admin/category/populateSubCategory",
                data: {"categorySel":categorySel},
                success: function (msg) {

                    var  subcat_label = $("#subcat"), label = "";
                    subcat_label.empty();

                    for (var C = 0; C < msg.length; C++) {
                        //alert("each subcat" + msg[C]);
                       label =label + "<label>" + msg[C]+"</label>";
                    }
                    subcat_label.append(label)
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }

            });
    });

});

//js to load the image as radio button is clicked
/*function imgurl_display(){
    var  img=
    document.getElementById("category").innerHTML = "<img src='/themes/hsenid/images/categories/cat2.png'>";



}*/
