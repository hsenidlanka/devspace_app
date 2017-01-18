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
                rowStyle: rowStyle(1,0),
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
                    field: 'process',
                    title: 'Process the Order :',
                    align: 'center',
                    //width:1,
                    //class: 'col-xs-2',
                    formatter: processFormatter,
                    events: processEvents
                }, /*{
                    field: 'agent_name',
                    title: 'Delivery Agent:',
                    align: 'center',
                    formatter: agentFormatter,
                    events: agentEvents,
                    //width:25,
                    //class: 'col-xs-1',
                    sortable: true
                },*/ {
                    field: 'delivery_status',
                    title: 'Delivery Status:',
                    align: 'left',
                    //width:25,
                    //class: 'danger',
                   //events:statusFormatter,
                    sortable: true
                }],
                data: result
            });
        }
    });

   /* function statusFormatter(value, row, index) {
        //alert(value);
        return [
            value,

          rowStyle(row,index)

        ].join('');

    }*/

    function rowStyle(row, index) {
        var data1 = JSON.stringify(row);
        var objc1 = JSON.parse(data1);
        var id= objc1["delivery_status"];
        var id2= objc1["recepient_address"];
        alert(index);
        alert(row);

      /*  alert(id);
        alert(id2);*/

        return {
            class: 'danger',
            css: {"color": "blue", "font-size": "15px"}
        };
    }

    function processFormatter(value, row, index) {
        return [
            '<a class="likecat" href="javascript:void(0)" title="LikeCustomer" data-toggle="modal" >',
            '<i class="glyphicon glyphicon-edit">Process</i>',
            //'<em class="fa fa-pencil"></em>',
            '</a>  ',
            '<a class="removecat" href="javascript:void(0)" title="Delete">',
            '<i class="glyphicon glyphicon-remove">Change</i>',
            '</a>'
        ].join('');
    }

    window.processEvents = {
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
            ' <select class="branchSelect"  name="branchSelectN"><option value="--Select--">--Select-- </option><option value="Colombo\">Colombo </option> <option value="Gampaha\">Gampaha </option>' +
            ' <option value="Ja-Ela\">Ja-Ela </option> <option value="Kandana\">Kandana </option> </select>'

        ].join('');
    }



    window.branchEvents = {
        'click .branchSelect': function(e, value, row, index) {

            var branch=$('.branchSelect').find(':selected').text();
            var nodeArray= [];
            nodeArray=branch.split(" ");

            var selectedBranch=nodeArray[index];
            alert(selectedBranch);

            $.ajax({
                url: 'https://localhost:8443/admin/processOrders/delivery/agents',
                data: {"selectedBranch":selectedBranch},
                success: function (msg) {
                    alert(msg);
                    alert(index);
                    alert("value:"+value);

                    var data1 = JSON.stringify(row);
                    var objc1 = JSON.parse(data1);
                    var id= objc1["sbranch"];
                    alert(id);

                    var slctBranchAgent = $(".agentSelect"), option = "";
                    alert(slctBranchAgent);
                    slctBranchAgent.empty();

                    option="<option>" +"--Select-- "+"</option>";
                    for (var itm = 0; itm < msg.length; itm++) {
                        option = option + "<option value='" + msg[itm]+ "'>" + msg[itm]+ "</option>";
                    }
                    slctBranchAgent.append(option);
                },
                error: function (e) {
                    alert("ajax failed in populating the delivery agents" + e);
                }
            });
        }
    };

    //to populate the delivery agents list based on the branch selected
  /*  $('#tableprocessOrder').on('click','.branchSelect',function(){
        var branch=$('.branchSelect').find(':selected').val();
        alert(branch);
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
    });*/

});

