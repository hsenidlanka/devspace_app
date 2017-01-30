
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
    var out1=true;

    if (fld.value == "") {
        fld.style.background = 'Yellow';
        error = "You didn't enter a password.\n";
        alert(error);
        out1=false;

    } else if ((fld.value.length < 7) || (fld.value.length > 15)) {
        error = "The password is the wrong length. \n";
        fld.style.background = 'Yellow';
        alert(error);
        out1=false;

    }  else if ( (fld.value.search(/[a-zA-Z]+/)==-1) || (fld.value.search(/[0-9]+/)==-1) ) {
        error = "The password must contain at least one numeral.\n";
        fld.style.background = 'Yellow';
        alert(error);
        out1=false;

    } else {
        fld.style.background = 'White';
    }
    return out1;
}


<!--javascript to check two passwords are equal -->
function passwordsEqual(fld1,fld2) {
    var error2 = "";
    var out=true;

    if (fld1.value == "") {
        fld1.style.background = 'Yellow';
        error2 = "You have to confirm the password.\n";
        alert(error2);
        out=false;

    }

    else if ((fld1.value) != (fld2.value)) {
        error = "Two passwords have to be Equal. \n";
        fld1.style.background = 'Yellow';
        alert(error);
        out=false;

    }  else {
        fld1.style.background = 'White';
    }
    return out;
}


//userAdd js
$(document).ready(function () {
    $("#staffDetail").hide();

    $("#staff").click(function(){
        $("#staffDetail").show();
    });

    $("#customer").click(function(){
        $("#staffDetail").hide();
    });
/////////////////////////////////////////////PAGINATION EXTRA => when page change ////////////////////////////////////////////////////////////////////
    var pgLimit = 10;

    // Active Customer  pagination in SEARCH scenarios
    var activeC= $('#pagination2Customer').simplePaginator({

        // the number of total pages
        totalPages: 7,

        // maximum of visible buttons
        maxButtonsVisible: 10,

        // page selected
        currentPage: 1,

        // text labels for buttons
        nextLabel: 'next',
        prevLabel: 'prev',
        firstLabel: 'first',
        lastLabel: 'last',

        // specify if the paginator click in the currentButton
        clickCurrentPage: true,

        // called when a page is changed.
        pageChange: function (page) {

            var pageSend=(page-1)*pgLimit;

            var cname= $("#cnameSearch").val();
            var from = $('#fromDate').val();
            var to=$('#toDate').val();
            var city=$('#citySearch').val();
            var customerVerification1=$('#selectVerifyOrNot').val();
            //alert(customerVerification);

            if(customerVerification1 == "Verified") {
                if ((from == "") && (to == "") && (cname == "") && (city == "--Select--")) {
                    $.ajax({
                        //type: "POST",
                        url: "https://localhost:8443/admin/users/view/customerTable",
                        data: {"initPage": pageSend, "pageLimit": pgLimit},
                        success: function (msg) {

                            $('#tableCustomer').bootstrapTable('load', msg);
                        },
                        error: function (e) {
                            alert("ajax failed" + e);
                        }
                    });
                }

                if ((cname != "")) {
                    $.ajax({
                        url: "https://localhost:8443/admin/userFilters/customerTable/typeheadName/data",
                        dataType: "json",
                        data: {"cname": cname, "initPage": pageSend, "pageLimit": pgLimit},
                        success: function (data) {
                            $('#tableCustomer').bootstrapTable('load', data);
                        }
                    })
                }

                if ((from != "") || (to != "") || (!(city == "--Select--"))) {
                    $.ajax({
                        //type: "POST",
                        url: "https://localhost:8443/admin/userFilters/customerTable/date/city",
                        data: {"from": from, "to": to, "city": city, "initPage": pageSend, "pageLimit": pgLimit},
                        success: function (msg) {
                            //alert("ajax succ" + from +name +city);
                            $('#tableCustomer').bootstrapTable('load', msg);
                        },
                        error: function (e) {
                            alert("ajax failed" + e);
                        }
                    });
                }
            }
            if(customerVerification1 == "Not_Verified"){
                if ((from == "") && (to == "") && (cname == "") && (city == "--Select--")) {
                    $.ajax({

                        url: "https://localhost:8443/admin/userFilters/view/customerTable/nonVerified",
                        data: {"initPage": pageSend, "pageLimit": pgLimit},
                        success: function (msg) {

                            $('#tableCustomer').bootstrapTable('load', msg);
                        },
                        error: function (e) {
                            alert("ajax failed" + e);
                        }
                    });
                }
                if ((cname != "")) {
                    $.ajax({
                        url: "https://localhost:8443/admin/userFilters/customerTable/typeheadName/data/nonVerified",
                        dataType: "json",
                        data: {"cname": cname, "initPage": pageSend, "pageLimit": pgLimit},
                        success: function (data) {
                            $('#tableCustomer').bootstrapTable('load', data);
                        }
                    })
                }
                if ((from != "") || (to != "") || (!(city == "--Select--"))) {
                    $.ajax({
                        //type: "POST",
                        url: "https://localhost:8443/admin/userFilters/customerTable/date/city/nonVerified",
                        data: {"from": from, "to": to, "city": city, "initPage": pageSend, "pageLimit": pgLimit},
                        success: function (msg) {
                            //alert("ajax succ" + from +name +city);
                            $('#tableCustomer').bootstrapTable('load', msg);
                        },
                        error: function (e) {
                            alert("ajax failed" + e);
                        }
                    });
                }
            }
        }
    });

    // Active Staff  pagination in SEARCH scenarios
    var activeS= $('#pagination2Staff').simplePaginator({

       // the number of total pages
        totalPages: 7,

        // maximum of visible buttons
        maxButtonsVisible: 10,

        // page selected
        currentPage: 1,

        // text labels for buttons
        nextLabel: 'next',
        prevLabel: 'prev',
        firstLabel: 'first',
        lastLabel: 'last',

        // specify if the paginator click in the currentButton
        clickCurrentPage: true,

        // called when a page is changed.
        pageChange: function (page) {

            var pageSend=(page-1)*pgLimit;
            var sname = $("#snameSearch").val();
            var designation= $('#designation_d').find(':selected').text();
            var department=$('#department_d').find('option:selected').text();
            var branch=$('#branch_d').find(':selected').text();

            if((designation == "--Select--") && (department == "--Select--") && (sname == "") && (branch == "--Select--")  ){

                $.ajax({
                    //type: "POST",
                    url: "https://localhost:8443/admin/users/view/staffTable",
                    data:{"initPage":pageSend, "pageLimit": pgLimit},
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
                    data:{"designation":designation,"department":department,"branch":branch,"name":sname,
                        "initPage":pageSend, "pageLimit": pgLimit},
                    success: function (msg) {

                        $('#tableStaff').bootstrapTable('load', msg);
                    },
                    error: function (e) {
                        alert("ajax failed" + e);
                    }
                });
            }

            if((sname != "") ) {
                $.ajax({
                    url: "https://localhost:8443/admin/userFilters/staffTable/typeheadName/data",
                    dataType: "json",
                    data: {"sname": sname, "initPage": pageSend, "pageLimit": pgLimit},
                    success: function (data) {

                        $('#tableStaff').bootstrapTable('load', data);
                    }
                })
            }
        }
    });

    // InActive Customer  pagination in SEARCH scenarios
    var inactiveC= $('#pagination2BannedCustomer').simplePaginator({
        // the number of total pages
        totalPages: 7,

        // maximum of visible buttons
        maxButtonsVisible: 5,

        // page selected
        currentPage: 1,

        // text labels for buttons
        nextLabel: 'next',
        prevLabel: 'prev',
        firstLabel: 'first',
        lastLabel: 'last',

        // specify if the paginator click in the currentButton
        clickCurrentPage: true,

        // called when a page is changed.
        pageChange: function (page) {

            var pageSend = (page - 1) * pgLimit;
            var bcname = $("#bcnameSearch").val();
            var city = $('#blockcCity').val();

            if ((bcname == "") && (city == "--Select--")) {
                $.ajax({
                    //type: "POST",
                    url: "https://localhost:8443/admin/users/view/bannedcustomerTable",
                    data: {"initPage": pageSend, "pageLimit": pgLimit},
                    success: function (msg) {

                        $('#tableBannedcustomer').bootstrapTable('load', msg);
                    },
                    error: function (e) {
                        alert("ajax failed" + e);
                    }
                });
            }
            if ((city != "--Select--") || (bcname != "")) {
                $.ajax({
                    //type: "POST",
                    url: "https://localhost:8443/admin/userFilters/bannedcustomerTable/city",
                    data: {"city": city, "name": bcname, "initPage": pageSend, "pageLimit": pgLimit},
                    success: function (msg) {
                        //alert("city ajax" + city);
                        $('#tableBannedcustomer').bootstrapTable('load', msg);
                    },
                    error: function (e) {
                        alert("ajax failed" + city + e);
                    }
                });
            }
        }

    });

    //InActive Staff  pagination in SEARCH scenarios
    var inactiveS= $('#pagination2BannedStaff').simplePaginator({

        // the number of total pages
        totalPages: 7,

        // maximum of visible buttons
        maxButtonsVisible: 5,

        // page selected
        currentPage: 1,

        // text labels for buttons
        nextLabel: 'next',
        prevLabel: 'prev',
        firstLabel: 'first',
        lastLabel: 'last',

        // specify if the paginator click in the currentButton
        clickCurrentPage: true,

        // called when a page is changed.
        pageChange: function (page) {

            var pageSend=(page-1)*pgLimit;
            var bsname = $("#bsname").val();
            var designation= $('#designation_db').find(':selected').text();
            var department=$('#department_db').find('option:selected').text();
            var branch=$('#branch_db').find(':selected').text();

            if((designation == "--Select--") && (department == "--Select--") && (bsname == "") && (branch == "--Select--")  ){

                $.ajax({
                    //type: "POST",
                    url: "https://localhost:8443/admin/users/view/bannedstaffTable",
                    data: {"initPage": pageSend, "pageLimit": pgLimit},
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
                    data:{"designation":designation,"department":department,"branch":branch,"name":bsname,"initPage": pageSend, "pageLimit": pgLimit},
                    success: function (msg) {

                        $('#tableBannedstaff').bootstrapTable('load', msg);
                    },
                    error: function (e) {
                        alert("ajax failed" + e);
                    }
                });
            }
        }
    });



/////////////////////////////////////////// TYPEHEAD FUNCTION FOR NAME FILTER ACTIVE USERS/////////////////////////////////
/*
 * typeahead function to filter data in Customer table
 */


    //to get the typeahead name list
    $("#cnameSearch").keyup(function () {
        var cname = $("#cnameSearch").val();
        var customerVerification3=$('#selectVerifyOrNot').val();

        if(customerVerification3 == "Verified") {
            $.ajax({
                url: "https://localhost:8443/admin/userFilters/customerTable/typeheadName",
                data: {"cname": cname},
                success: function (data) {
                    //alert(" value"+ data);

                    $('#cnameSearch').typeahead({
                        source: data
                    }).focus();
                }
            })
        }
        if(customerVerification3 == "Not_Verified"){
            $.ajax({
                url: "https://localhost:8443/admin/userFilters/customerTable/typeheadName/nonVerified",
                data: {"cname": cname},
                success: function (data) {
                    //alert(" value"+ data);

                    $('#cnameSearch').typeahead({
                        source: data
                    }).focus();
                }
            })

        }
    });

    //to get the data in the table according to the typeahead keyup event
    $("#cnameSearch").keyup(function () {

        var cname = $("#cnameSearch").val();
        var customerVerification2=$('#selectVerifyOrNot').val();

        $('#paginationCustomer').hide();
        $('#pagination2Customer').show();

        if(customerVerification2 == "Verified") {
            $.ajax({
                url: "https://localhost:8443/admin/userFilters/customerTable/typeheadName/data",
                dataType: "json",
                data: {"cname": cname, "initPage": "0", "pageLimit": pgLimit},
                success: function (data) {

                    $('#tableCustomer').bootstrapTable('load', data);
                },
                error: function (e) {
                    alert("error, load search customer" + e);
                    console.log("error, load search customer" + e)
                }
            });

            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/users/CustomerPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {

                    activeC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                }
            });
        }
        if(customerVerification2 == "Not_Verified"){
            $.ajax({
                url: "https://localhost:8443/admin/userFilters/customerTable/typeheadName/data/nonVerified",
                dataType: "json",
                data: {"cname": cname, "initPage": "0", "pageLimit": pgLimit},
                success: function (data) {

                    $('#tableCustomer').bootstrapTable('load', data);
                },
                error: function (e) {
                    alert("error, load search customer" + e);
                    console.log("error, load search customer" + e)
                }
            });

            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/userFilters/NonVerifiedCustomerPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {

                    activeC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                }
            });

        }
    });



    /*
     * typeahead function to filter data in Staff table
     */
    $("#snameSearch").keyup(function () {
        var sname = $("#snameSearch").val();

        $('#paginationStaff').hide();
        $('#pagination2Staff').show();

        $.ajax({
            url: "https://localhost:8443/admin/userFilters/staffTable/typeheadName/data",
            data: {"sname": sname,"initPage": "0", "pageLimit": pgLimit},
            success: function (data) {
                //alert(" value"+ data);
                $('#tableStaff').bootstrapTable('load', data);
            },
            error: function (e) {
                alert("error, load search staff" + e);
                console.log("error, load search staff" + e)
            }
        });
        /**
         *Setting the number of pages according to the number of records
         */
        $.ajax({
            url: 'https://localhost:8443/admin/users/StaffPaginationTable',
            //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
            success: function (recCount) {
                activeS.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
            }
        });
    });

    $("#snameSearch").keyup(function () {
        var sname = $("#snameSearch").val();
        $.ajax({
            url: "https://localhost:8443/admin/userFilters/staffTable/typeheadName",
            data: {"sname": sname},
            success: function (data) {
                //alert(" value"+ data);

                $('#snameSearch').typeahead({
                    source: data
                }).focus();
            }
        })
    });



/////////////////////////////////////////// TYPEHEAD FUNCTION FOR NAME FILTER BANNED USERS/////////////////////////////////

    /*
     * typeahead function to filter data in Banned Customers table
     */
    $("#bcnameSearch").keyup(function () {
        var bcname = $("#bcnameSearch").val();

        $.ajax({
            url: "https://localhost:8443/admin/userFilters/bannedCustomerTable/typeheadName",
            data: {"bcname": bcname},
            success: function (data) {
                //alert(" value"+ data);
                $('#bcnameSearch').typeahead({
                    source: data
                }).focus();
            }
        })
    });
    $("#bcnameSearch").keyup(function () {
        var bcname = $("#bcnameSearch").val();

        $('#paginationBannedCustomer').hide();
        $('#pagination2BannedCustomer').show();

        $.ajax({
            url: "https://localhost:8443/admin/userFilters/bannedCustomerTable/typeheadName/data",
            data: {"bcname": bcname,"initPage": "0", "pageLimit": pgLimit},
            success: function (data) {
                //alert(" value"+ data);
                $('#tableBannedcustomer').bootstrapTable('load', data);
            },
            error: function (e) {
                alert("error, load search customer" + e);
                console.log("error, load search customer" + e)
            }
        });
        /**
         *Setting the number of pages according to the number of records
         */
        $.ajax({
            url: 'https://localhost:8443/admin/users/BannedCustomerPaginationTable',
            //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
            success: function (recCount) {
                inactiveC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
            }
        });
    });


    /*
     * typeahead function to filter data in Banned Staff table
     */
    $("#bsname").keyup(function () {
        var bsname = $("#bsname").val();
        $.ajax({
            url: "https://localhost:8443/admin/userFilters/bannedStaffTable/typeheadName",
            data: {"bsname": bsname},
            success: function (data) {
                //alert(" value"+ data);

                $('#bsname').typeahead({
                    source: data
                }).focus();
            }
        })
    });

    //for banned staff table data filter
    $("#bsname").keyup(function () {
        var bsname = $("#bsname").val();

        $('#paginationBannedStaff').hide();
        $('#pagination2BannedStaff').show();

        $.ajax({
            url: "https://localhost:8443/admin/userFilters/bannedStaffTable/typeheadName/data",
            data: {"bsname": bsname,"initPage": "0", "pageLimit": pgLimit},
            success: function (data) {
                //alert(" value"+ data);
                $('#tableBannedstaff').bootstrapTable('load', data);
            },
            error: function (e) {
                alert("error, load search staff" + e);
                console.log("error, load search staff" + e)
            }
        });
        /**
         *Setting the number of pages according to the number of records
         */
        $.ajax({
            url: 'https://localhost:8443/admin/users/BannedStaffPaginationTable',
            //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
            success: function (recCount) {
                inactiveS.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
            }
        });
    });

///////////////////////////////////////////  AJAX CALLS TO FILTER ACTIVE USERS  ////////////////////////////////////////////

 //ajax functions to filter the search results of Customer Users if an option is selected

    $("#filterButtonCustomer").click(function(){
        var from = $('#fromDate').val();
        var to=$('#toDate').val();
        var name=$('#cnameSearch').val();
        var city=$('#citySearch').val();
        var customerVerification4=$('#selectVerifyOrNot').val();

        $('#paginationCustomer').hide();
        $('#pagination2Customer').show();

        if(customerVerification4 == "Verified") {

            if ((from == "") && (to == "") && (name == "") && (city == "--Select--")) {
                $.ajax({
                    //type: "POST",
                    url: "https://localhost:8443/admin/users/view/customerTable",
                    data: {"initPage": "0", "pageLimit": pgLimit},
                    success: function (msg) {

                        $('#tableCustomer').bootstrapTable('load', msg);
                    },
                    error: function (e) {
                        alert("ajax failed" + e);
                    }
                });
                /**
                 *Setting the number of pages according to the number of records
                 */
                $.ajax({
                    url: 'https://localhost:8443/admin/users/CustomerPaginationTable',
                    //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                    success: function (recCount) {

                        activeC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                    }
                });
            }

            if ((from != "") || (to != "") || (!(city == "--Select--"))) {
                $.ajax({
                    //type: "POST",
                    url: "https://localhost:8443/admin/userFilters/customerTable/date/city",
                    data: {"from": from, "to": to, "city": city, "initPage": "0", "pageLimit": pgLimit},
                    success: function (msg) {
                        //alert("ajax succ" + from +name +city);
                        $('#tableCustomer').bootstrapTable('load', msg);
                    },
                    error: function (e) {
                        alert("ajax failed" + e);
                    }
                });
                /**
                 *Setting the number of pages according to the number of records
                 */
                $.ajax({
                    url: 'https://localhost:8443/admin/users/CustomerPaginationTable',
                    //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                    success: function (recCount) {

                        activeC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                    }
                });
            }
            if ((name != "")) {
                $.ajax({
                    url: "https://localhost:8443/admin/userFilters/customerTable/typeheadName/data",
                    dataType: "json",
                    data: {"cname": name, "initPage": "0", "pageLimit": pgLimit},
                    success: function (data) {
                        $('#tableCustomer').bootstrapTable('load', data);
                    }
                })
            }
        }
        if(customerVerification4 == "Not_Verified"){
            if ((from == "") && (to == "") && (name == "") && (city == "--Select--")) {
                $.ajax({
                    //type: "POST",
                    url: "https://localhost:8443/admin/userFilters/view/customerTable/nonVerified",
                    data: {"initPage": "0", "pageLimit": pgLimit},
                    success: function (msg) {

                        $('#tableCustomer').bootstrapTable('load', msg);
                    },
                    error: function (e) {
                        alert("ajax failed" + e);
                    }
                });
                /**
                 *Setting the number of pages according to the number of records
                 */
                $.ajax({
                    url: 'https://localhost:8443/admin/userFilters/NonVerifiedCustomerPaginationTable',
                    //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                    success: function (recCount) {

                        activeC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                    }
                });
            }

            if ((from != "") || (to != "") || (!(city == "--Select--"))) {
                $.ajax({
                    //type: "POST",
                    url: "https://localhost:8443/admin/userFilters/customerTable/date/city/nonVerified",
                    data: {"from": from, "to": to, "city": city, "initPage": "0", "pageLimit": pgLimit},
                    success: function (msg) {
                        //alert("ajax succ" + from +name +city);
                        $('#tableCustomer').bootstrapTable('load', msg);
                    },
                    error: function (e) {
                        alert("ajax failed" + e);
                    }
                });
                /**
                 *Setting the number of pages according to the number of records
                 */
                $.ajax({
                    url: 'https://localhost:8443/admin//userFilters/NonVerifiedCustomerPaginationTable',
                    //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                    success: function (recCount) {

                        activeC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                    }
                });
            }
            if ((name != "")) {
                $.ajax({
                    url: "https://localhost:8443/admin/userFilters/customerTable/typeheadName/data/nonVerified",
                    dataType: "json",
                    data: {"cname": name, "initPage": "0", "pageLimit": pgLimit},
                    success: function (data) {
                        $('#tableCustomer').bootstrapTable('load', data);
                    }
                })
            }

        }

    });



//ajax functions to filter the search results of Staff Users if an option is selected

    $("#filterButtonStaff").click(function(){

        var designation= $('#designation_d').find(':selected').text();
        var department=$('#department_d').find('option:selected').text();
        var name=$("#snameSearch").val();
        var branch=$('#branch_d').find(':selected').text();

        $('#paginationStaff').hide();
        $('#pagination2Staff').show();

        if((designation == "--Select--") && (department == "--Select--") && (name == "") && (branch == "--Select--")  ){

            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/users/view/staffTable",
                data:{"initPage":"0", "pageLimit": pgLimit},
                success: function (msg) {

                    $('#tableStaff').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/users/StaffPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {
                    activeS.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                }
            });
        }
        if((!(designation == "--Select--")) || (!(department == "--Select--")) || (!(branch == "--Select--")) ){
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/userFilters/staffTable",
                data:{"designation":designation,"department":department,"branch":branch,"name":name,"initPage":"0", "pageLimit": pgLimit},
                success: function (msg) {

                    $('#tableStaff').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/users/StaffPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {
                    activeS.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                }
            });
        }

    });


////////////////////////////////////   AJAX CALLS TO FILTER INACTIVE USERS ///////////////////////////////////////////////


//ajax functions to filter the search results of Customer Users if an option is selected(INACTIVE)

    $("#filterButtonCustomerB").click(function(){

        var name=$('#bcnameSearch').val();
        var city=$('#blockcCity').val();
        //alert(name);

        $('#paginationBannedCustomer').hide();
        $('#pagination2BannedCustomer').show();

        if((name == "") && (city == "--Select--")  ){
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/users/view/bannedcustomerTable",
                data: {"initPage":"0", "pageLimit": pgLimit},
                success: function (msg) {

                    $('#tableBannedcustomer').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/users/BannedCustomerPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {

                    inactiveC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                }
            });

        }
        if( (city != "--Select--") || (name != "")  ){
            $.ajax({
                //type: "POST",
                url:"https://localhost:8443/admin/userFilters/bannedcustomerTable/city",
                data: {"city":city, "name":name,"initPage":"0", "pageLimit": pgLimit},
                success: function (msg) {
                    //alert("city ajax" + city);
                    $('#tableBannedcustomer').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + city +e);
                }
            });
            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/users/BannedCustomerPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {

                    activeC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                }
            });
        }
    });



//ajax functions to filter the search results of Staff Users if an option is selected(INACTIVE)

    $("#filterButtonStaffB").click(function(){
        var designation= $('#designation_db').find(':selected').text();
        var department=$('#department_db').find('option:selected').text();
        var name=$('#bsname').val();
        var branch=$('#branch_db').find(':selected').text();

        $('#paginationBannedStaff').hide();
        $('#pagination2BannedStaff').show();

        if((designation == "--Select--") && (department == "--Select--") && (name == "") && (branch == "--Select--")  ){

            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/users/view/bannedstaffTable",
                data: {"initPage":"0", "pageLimit": pgLimit},
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
                data:{"designation":designation,"department":department,"branch":branch,"name":name,"initPage":"0", "pageLimit": pgLimit},
                success: function (msg) {

                    $('#tableBannedstaff').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
        }
    });


/////////////////////////////////////////////////FOR NON-VERIFIED USERS ////////////////////////////////////////////////////

    $('#selectVerifyOrNot').change(function() {
        //alert($(this).val());

        $('#paginationCustomer').hide();
        $('#pagination2Customer').show();

        if($(this).val() == "Not_Verified"){

            $.ajax({
                url: "https://localhost:8443/admin/userFilters/view/customerTable/nonVerified",
                data:{"initPage":"0", "pageLimit": pgLimit},
                success: function (msg) {
                    $('#tableCustomer').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed in loading non-verified users" + e);
                }
            });
            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/userFilters/NonVerifiedCustomerPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {

                    activeC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                }
            });
        }
        else if($(this).val() == "Verified"){
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/users/view/customerTable",
                data: {"initPage": "0", "pageLimit": pgLimit},
                success: function (msg) {

                    $('#tableCustomer').bootstrapTable('load', msg);
                },
                error: function (e) {
                    alert("ajax failed in loading verified users" + e);
                }
            });
            /**
             *Setting the number of pages according to the number of records
             */
            $.ajax({
                url: 'https://localhost:8443/admin/users/CustomerPaginationTable',
                //data: {"searchCatNm": $("#txtViewSearchCategory").val()},
                success: function (recCount) {

                    activeC.simplePaginator('setTotalPages', Math.ceil(recCount / pgLimit));
                }
            });
        }
    });

});

