
$(document).ready(function () {
    $("#staffDetail").hide();

    $("#staff").click(function(){
        $("#staffDetail").show();

    });
    $("#customer").click(function(){

        $("#staffDetail").hide();
    });

});

