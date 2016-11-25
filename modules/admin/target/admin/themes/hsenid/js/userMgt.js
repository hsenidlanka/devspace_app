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
    var fname= document.getElementById("cnameSearch");
    var city= document.getElementById("citySearch");
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
    var designationb= document.getElementById("designationb");
    var departmentb= document.getElementById("departmentb");
    var branchb= document.getElementById("branchb");

    //blocked customers
    var bcname= document.getElementById("bcnameSearch");
    var blockcCity= document.getElementById("blockcCity");

    //blocked staff filter
    if (  $("#blockCheck").is(":checked")){
        designationb.style.display = "block";
    } else{
        designationb.style.display = "none";
    }

    if (  $("#bsCheck").is(":checked")){
        departmentb.style.display = "block";
    } else{
        departmentb.style.display = "none";
    }

    if (  $("#branchbCheck").is(":checked")){
        branchb.style.display = "block";
    } else{
        branchb.style.display = "none";
    }


    //blocked customer filters


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

function validatePassword(fld) {
    //var fld= document.getElementById("password1")
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

///////////////////////////////////////////  AJAX CALLS TO FILTER ACTIVE USERS  ////////////////////////////////////////////

 //ajax functions to filter the search results of Customer Users if an option is selected
$(document).ready(function(){
    $("#filterButtonCustomer").click(function(){
        var from = $('#fromDate').val();
        var to=$('#toDate').val();
        var name=$('#cnameSearch').val();
        var city=$('#citySearch').val();

        if((from == "") && (to == "") && (name == "") && (city == "--Select--")  ){
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/users/view/customerTable",
                success: function (msg) {

                    $('#tableCustomer').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });

        }
        if( (city != "--Select--")  ){
            $.ajax({
                //type: "POST",
                url:"https://localhost:8443/admin/userFilters/customerTable/city",
                data: {"city":city},
                success: function (msg) {
                    //alert("city ajax" + city);
                    $('#tableCustomer').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + city +e);
                }
            });
        }
        if( (from != "") && (to != "")){
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/userFilters/customerTable/date",
                data: {"from": from, "to": to, "name":name},
                success: function (msg) {
                    //alert("ajax succ" + from +name +city);
                    $('#tableCustomer').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
        }
        if( (from != "") && (to != "") && (city != "--Select--")){
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/userFilters/customerTable/date/city",
                data: {"from": from, "to": to, "city":city},
                success: function (msg) {
                    //alert("ajax succ" + from +name +city);
                    $('#tableCustomer').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
        }

    });
});


//ajax functions to filter the search results of Staff Users if an option is selected
$(document).ready(function(){
    $("#filterButtonStaff").click(function(){
        var designation= $('#designation_d').find(':selected').text();
        var department=$('#department_d').find('option:selected').text();
        var name=$('#cname').val();
        var branch=$('#branch_d').find(':selected').text();

        if((designation == "--Select--") && (department == "--Select--") && (name == "") && (branch == "--Select--")  ){

            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/users/view/staffTable",
                success: function (msg) {

                    $('#tableStaff').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
        }
        if((!(designation == "--Select--")) || (!(department == "--Select--")) || (!(branch == "--Select--")) ){
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/userFilters/staffTable",
                data:{"designation":designation,"department":department,"branch":branch,"name":name},
                success: function (msg) {

                    $('#tableStaff').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
        }

    });
});

////////////////////////////////////   AJAX CALLS TO FILTER INACTIVE USERS ///////////////////////////////////////////////


//ajax functions to filter the search results of Customer Users if an option is selected(INACTIVE)
$(document).ready(function(){
    $("#filterButtonCustomerB").click(function(){
        var from = $('#fromDateb').val();//means blocked date
        var to=$('#toDateb').val();  //means to blocked date
        var name=$('#bcnameSearch').val();
        var city=$('#blockcCity').val();
        alert(name);

        if((from == "") && (to == "") && (name == "") && (city == "--Select--")  ){
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/users/view/bannedcustomerTable",
                success: function (msg) {

                    $('#tableBannedcustomer').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });

        }
        if( (city != "--Select--") || (name != "")  ){
            $.ajax({
                //type: "POST",
                url:"https://localhost:8443/admin/userFilters/bannedcustomerTable/city",
                data: {"city":city, "name":name},
                success: function (msg) {
                    //alert("city ajax" + city);
                    $('#tableBannedcustomer').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + city +e);
                }
            });
        }


    });
});


//ajax functions to filter the search results of Staff Users if an option is selected(INACTIVE)
$(document).ready(function(){
    $("#filterButtonStaffB").click(function(){
        var designation= $('#designation_db').find(':selected').text();
        var department=$('#department_db').find('option:selected').text();
        var name=$('#bsname').val();
        var branch=$('#branch_db').find(':selected').text();

        if((designation == "--Select--") && (department == "--Select--") && (name == "") && (branch == "--Select--")  ){

            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/users/view/bannedstaffTable",
                success: function (msg) {

                    $('#tableBannedstaff').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
        }
        if((!(designation == "--Select--")) || (!(department == "--Select--")) || (!(branch == "--Select--")) ){
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/userFilters/bannedstaffTable",
                data:{"designation":designation,"department":department,"branch":branch,"name":name},
                success: function (msg) {

                    $('#tableBannedstaff').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
        }

    });
});

