
//datepicker
$(document).ready(function() {
    $("#fromDate").datepicker({
        "changeMonth":true,
        "changeYear":true

    });

    $("#toDate").datepicker({
        "changeMonth":true,
        "changeYear":true

    });
});

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

//function to populate the filter by criteria
function configureDropDownLists(ddl1,ddl2) {
    var customer= ['None','Name', 'Registered Period', 'Area'];
    var staff = ['None','Designation', 'Department', 'Branch'];


    switch (ddl1.value) {
        case 'Customer':
            ddl2.options.length = 0;
            for (i = 0; i < customer.length; i++) {
                createOption(ddl2, customer[i], customer[i]);
            }
            break;
        case 'Staff Member':
            ddl2.options.length = 0;
            for (i = 0; i < staff.length; i++) {
                createOption(ddl2, staff[i], staff[i]);
            }
            break;

        default:
            ddl2.options.length = 0;
            break;
    }

}

function createOption(ddl, text, value) {
    var opt = document.createElement('option');
    opt.value = value;
    opt.text = text;
    ddl.options.add(opt);
}



    function showMe(e){
        var status= e.options[e.selectedIndex].value;
        var regPeriod= document.getElementById("regPeriod");
        var fname= document.getElementById("name");

        if (status == "Registered Period"){
            regPeriod.style.display = "block";
        } else{
            regPeriod.style.display = "none";
        }
    }



