
//userAdd js
$(document).ready(function () {
    $("#staffDetail").hide();

    $("#staff").click(function(){
        $("#staffDetail").show();
    });

    $("#customer").click(function(){
        $("#staffDetail").hide();
    });

});


//usersView js
    function showMe(){

        var regPeriod= document.getElementById("regPeriod");
        var fname= document.getElementById("cname");
        var city= document.getElementById("city");

        if (  $("#regCheck").is(":checked")){
            regPeriod.style.display = "block";
        } else{
            regPeriod.style.display = "none";
        }

        if (  $("#nameCheck").is(":checked")){
            fname.style.display = "block";
        } else{
            fname.style.display = "none";
        }


        if (  $("#liveCheck").is(":checked")){
            city.style.display = "block";
        } else{
            city.style.display = "none";
        }


    }


/*
$(document).ready(function () {
    $("#regCheck").click(function(){
        $("#regPeriod").style.display="block";
    });
});*/
