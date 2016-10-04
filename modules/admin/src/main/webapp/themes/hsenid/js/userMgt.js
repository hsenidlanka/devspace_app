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

//customerView js
    function showMe(){

        var regPeriod= document.getElementById("regPeriod");
        var fname= document.getElementById("cname");
        var city= document.getElementById("city");
        var designation= document.getElementById("designation");
        var department= document.getElementById("department");
        var branch= document.getElementById("branch");

        //customer filter elements
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

        //staff filter elements
        if (  $("#desigCheck").is(":checked")){
            designation.style.display = "block";
        } else{
            designation.style.display = "none";
        }

        if (  $("#depCheck").is(":checked")){
            department.style.display = "block";
        } else{
            department.style.display = "none";
        }

        if (  $("#branchCheck").is(":checked")){
            branch.style.display = "block";
        } else{
            branch.style.display = "none";
        }


    }


function showMeBlocked(){

    //blocked staff users
    var blockPeriod= document.getElementById("blockPeriod");
    var bsname= document.getElementById("bsname");
    var branch= document.getElementById("branch");

    //blocked customers
    var blockcPeriod= document.getElementById("blockcPeriod");
    var bcname= document.getElementById("bcname");
    var blockcCity= document.getElementById("blockcCity");


    //blocked users filter
    if (  $("#blockCheck").is(":checked")){
        blockPeriod.style.display = "block";
    } else{
        blockPeriod.style.display = "none";
    }

    if (  $("#bsCheck").is(":checked")){
        bsname.style.display = "block";
    } else{
        bsname.style.display = "none";
    }

    if (  $("#branchCheck").is(":checked")){
        branch.style.display = "block";
    } else{
        branch.style.display = "none";
    }


    //blocked customer filters
    if (  $("#blockcCheck").is(":checked")){
        blockcPeriod.style.display = "block";
    } else{
        blockcPeriod.style.display = "none";
    }

    if (  $("#bcCheck").is(":checked")){
        bcname.style.display = "block";
    } else{
        bcname.style.display = "none";
    }

    if (  $("#citycCheck").is(":checked")){
        blockcCity.style.display = "block";
    } else{
        blockcCity.style.display = "none";
    }


}

//in userAdd form to validate the password entered

function validatePassword() {
    var fld= document.getElementById("password1")
    var error = "";
    var illegalChars = /[\W_]/; // allow only letters and numbers

    if (fld.value == "") {
        fld.style.background = 'Yellow';
        error = "You didn't enter a password.\n";
        alert(error);
        return false;

    } else if ((fld.value.length < 7) || (fld.value.length > 15)) {
        error = "The password is the wrong length. \n";
        fld.style.background = 'Yellow';
        alert(error);
        return false;

    }  else if ( (fld.value.search(/[a-zA-Z]+/)==-1) || (fld.value.search(/[0-9]+/)==-1) ) {
        error = "The password must contain at least one numeral.\n";
        fld.style.background = 'Yellow';
        alert(error);
        return false;

    } else {
        fld.style.background = 'White';
    }
    return true;
}


<!--javascript to check two passwords are equal -->
function passwordsEqual(fld1,fld2) {
    var error2 = "";

    if (fld1.value == "") {
        fld1.style.background = 'Yellow';
        error2 = "You have to confirm the password.\n";
        alert(error2);
        return false;

    }

    else if ((fld1.value) != (fld2.value)) {
        error = "Two passwords have to be Equal. \n";
        fld1.style.background = 'Yellow';
        alert(error);
        return false;

    }  else {
        fld1.style.background = 'White';
    }
    return true;
}



<!--javascript to check the availability of the username in the database are equal -->
$(document).ready(function(){
    $("#username_admin").change(function(){
        var uname = $(this).val();
        $.ajax({
            type: "POST",
            url: "https://localhost:8443/admin/users/uniqueUsername",
            data: {"uname": uname},
            success: function(msg){
            /*    if(msg == 0)
                    alert("OK");
                else
                alert("User name exists !... try another");*/
                alert(msg + uname);


            },
            error:function(){
                alert("ajax failed" +uname);
            }
        });

    });
});

/*function doAjaxPost(){

    var  name= $('#fname').val();
    $.ajax({
        type:"POST",
        url: "https://localhost:8443/admin/users/addCustomer",
        data: name,
        success:function(data){

            alert(data );
        },
        error:function(e){
            alert('Error' +e);
        }

    });
}*/



