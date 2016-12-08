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


//in the new sub-category addition page to add grid rows
$(document).ready(function(){
    var counter = 1;
    $('.subcat_add_table').on('click','.addsub',function(){

        event.preventDefault();

        var newRow=jQuery('<div class="row subcat-tbl-row"><div class="col-xs-4">' +
        '<label class="checkbox-inline"><div contenteditable><input name="subcategory_name[]" type="text" class="form-control" id="subcategoryname" required="required" placeholder="Enter Category Name"/>' +
        '</div></label></div><div class="col-xs-5"><div contenteditable><textarea name="subcategory_des[]" class="form-control" id="subcategorydes" placeholder="Enter Category Description" >' +
        '</textarea></div></div> <div class="col-xs-3"> <a href="#" class="addsub"> <span class=" glyphicon glyphicon-plus"></span> ' +
        '</a> <a href="#" class="deletesub"> <span class="glyphicon glyphicon-minus"></span></a></div>');

        $('.subcat_add_table').append(newRow);
        $(".deletesub").on('click',function(){
            $(this).parent().parent().remove();
        });
    });
});


//in the category addition page to add grid rows for a subcategory addition
$(document).ready(function(){
    var counter = 1;
    $('.cat_add_table').on('click','.addcat',function(){

        event.preventDefault();

        var newRow=jQuery('<div class="row subcat-tbl-row"><div class="col-xs-4">' +
        '<label class="checkbox-inline"><div contenteditable><input name="subcategory_name[]" type="text" class="form-control" id="subcategoryname" required="required" placeholder="Enter Category Name"/>' +
        '</div></label></div><div class="col-xs-5"><div contenteditable><textarea name="subcategory_des[]" class="form-control" id="subcategorydes" placeholder="Enter Category Description" >' +
        '</textarea></div></div> <div class="col-xs-3"> <a href="#" class="addcat"> <span class=" glyphicon glyphicon-plus"></span> ' +
        '</a> <a href="#" class="deletecat"> <span class=" glyphicon glyphicon-minus"></span> </a> </div>');

        $('.cat_add_table').append(newRow);
        $(".deletecat").on('click',function(){
            $(this).parent().parent().remove();
        });


    });
});
