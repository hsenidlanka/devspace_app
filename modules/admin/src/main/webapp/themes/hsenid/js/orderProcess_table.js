$(document).ready(function () {

    var pgLimit = 10;
    var catName = $("#txtViewSearchCategory").val();
    //alert(pgLimit);

    $.ajax({
        url: 'https://localhost:8443/admin/processOrders/view/orderProcessTable',
        data: {"initPage": "0", "pgLimit": pgLimit},
        success: function (result) {
            //alert("table success");
            $('#tableprocessOrder').bootstrapTable({
                height: 530,
                pagination: false,
                search: false,
                showColumns: false,
                showRefresh: false,
                minimumCountColumns: 2,
                columns: [{
                    field: 'order_id',
                    title: 'Order :',
                    sortable: true,
                    //width:50,
                    //class: 'col-xs-1',
                    align: 'left'
                }, {
                    field: 'recepient_name',
                    title: 'Reciepient Name:',
                    sortable: true,
                    align: 'left',
                    searchable: true
                    //class: 'col-xs-1'
                    //width:25,
                }, {
                    field: 'delivery_date',
                    title: 'Delivery Date :',
                    align: 'left',
                    //width:10,
                    //class: 'col-xs-5',
                    sortable: true

                }, {
                    field: 'delivery_time',
                    title: 'Delivery Time:',
                    align: 'left',
                    //width:25,
                    //class: 'col-xs-1',
                    sortable: true

                }, {
                    field: 'recepient_address',
                    title: 'Destination Address :',
                    align: 'left',
                    //width:25,
                    //class: 'col-xs-1',
                    sortable: true

                }, {
                    field: 'sbranch',
                    title: 'Serving Branch :',
                    align: 'center',
                    //width:1,
                    //class: 'col-xs-2',
                    formatter: branchFormatter
                    //events: branchEvents
                }, {
                    field: 'agent_name',
                    title: 'Delivery Agent:',
                    align: 'center',
                    formatter: agentFormatter,
                    events: agentEvents,
                    //width:25,
                    //class: 'col-xs-1',
                    sortable: true
                }, {
                    field: 'delivery_status',
                    title: 'Delivery Status:',
                    align: 'left',
                    //width:25,
                    //class: 'col-xs-1',
                    sortable: true
                }],
                data: result
            });
        }
    });

    function agentFormatter(value, row, index) {
        return [
            ' <select class="agentSelect"> <option value="volvo">Volvo</option> <option value="saab">Saab</option>' +
            ' <option value="mercedes">Mercedes</option> <option value="audi">Audi</option> </select> '

        ].join('');

    }

    window.agentEvents = {
        'click .link': function(e, value, row, index) {
            var data1 = JSON.stringify(row);
            var objc1 = JSON.parse(data1);
            var name= objc1["branch"];
            //alert("value"+ value);
            window.location.href = "https://localhost:8443/admin/subCategory/"+value;
        }
    };


    function branchFormatter(value, row, index) {
        return [
            ' <select class="branchSelect" id="sss"><option value="--Select--\">--Select--</option><option value="Colombo\">Colombo</option> <option value="Gampaha\">Gampaha</option>' +
            ' <option value="Ja-Ela\">Ja-Ela</option> <option value="Kandana\">Kandana</option> </select> ',
            value,index
        ].join('');
    }



/*    window.branchEvents = {
        'click #sss': function(e, value, row, index) {
            var cells = new Array();
            //for (i=0,)
            var branch1=$('.branchSelect').val();
            alert(branch1);
            var branch=$('.branchSelect').find(':selected').val();
            alert(branch);
            //var branch=.find('option:selected').text();
       *//*     $('#sss').each(function(){
                cells.push($(this).text());
            });
            alert(cells);
            alert(cells[0]);
            alert(cells[index]);*//*


            $.ajax({
                url: 'https://localhost:8443/admin/processOrders/delivery/agents',
                data: {"branch":branch},
                success: function (msg) {
                    alert(msg);

                    *//* var  subcat_label = $("#subcat"), label = "";
                     subcat_label.empty();

                     for (var C = 0; C < msg.length; C++) {
                     //alert("each subcat" + msg[C]);
                     label =label + "<label>" + msg[C]+"</label>";
                     }
                     subcat_label.append(label)*//*
                },
                error: function (e) {
                    alert("ajax failed in populating the delivery agents" + e);
                }
            });
        }
    };*/

    //to populate the delivery agents list based on the branch selected
    $('#tableprocessOrder').on('click','<tbody>.branchSelect',function(){
        var branch=$('.branchSelect').find(':selected').val();
        alert(branch);
    $.ajax({
        url: 'https://localhost:8443/admin/processOrders/delivery/agents',
        data: {"branch":branch},
        success: function (msg) {
            alert(msg);

            /* var  subcat_label = $("#subcat"), label = "";
             subcat_label.empty();

             for (var C = 0; C < msg.length; C++) {
             //alert("each subcat" + msg[C]);
             label =label + "<label>" + msg[C]+"</label>";
             }
             subcat_label.append(label)*/
        },
        error: function (e) {
            alert("ajax failed in populating the delivery agents" + e);
        }
    });
    });

});

