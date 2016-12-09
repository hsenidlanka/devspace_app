$(document).ready(function(){


    $("#logout").click(function () {

        $.ajax({
            url: "https://localhost:8443/admin/PizzaShefu/logout",
            success: function (data) {
                window.location.href = "https://localhost:8443/admin/";
            },
            error: function (e) {
                $.toaster({priority: 'danger', title: 'Error', message: 'Error in Logout' });
                setTimeout(function () {
                    location.reload();
                }, 3000);
            }
        })
    });

});