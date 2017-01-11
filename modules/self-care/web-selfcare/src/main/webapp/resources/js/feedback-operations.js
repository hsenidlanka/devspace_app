$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "feedbacks/purchased-items",
        success: function (response) {
            $("#feedback-content-div").html(response);
        }
    });

    $(document.body).on('click', '.btn-viewfeedback' ,function(){
        var itemName = $(this).closest('div').find('h5.h5-itemname').text();
        alert(itemName);
    });
});