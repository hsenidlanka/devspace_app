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
                rowStyle: rowStyle,
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
                },{
                    field: 'agent_name',
                    title: 'Delivery Agent:',
                    align: 'center'

                }, {
                    field: 'delivery_status',
                    title: 'Delivery Status:',
                    align: 'left',
                    sortable: true
                }],
                data: result
            });
        }
    });



    function rowStyle(row, index) {
        var data1 = JSON.stringify(row);
        var objc1 = JSON.parse(data1);
        var deliveryStatus= objc1["delivery_status"];
        //alert(deliveryStatus);

        if(deliveryStatus == "Pending") {
            return {
                css: {"color": "#191970", "background-color": "rgba(193, 164, 112, 0.55)","font-size":"14px"}
            };
        }else {
            return {
                css: {"color": "#191970","font-size":"14px"}
            };
        }
    }

    function processFormatter(value, row, index) {

            return [
                '<a class="assignOrder" id="assignOrder" href="javascript:void(0)" title="LikeCustomer" data-toggle="modal" >',
                '<i class="glyphicon glyphicon-edit">Assign</i>',
                //'<em class="fa fa-pencil"></em>',
                '</a>  '
            ].join('');

    }

    window.processEvents = {

        'click .assignOrder': function(e, value, row, index) {
            var data2 = JSON.stringify(row);
            var objc2 = JSON.parse(data2);
            $('#lblOrderId').text(objc2["order_id"]);
            $('#assignOrderModel').modal({show:true});
        }
    };


    //ajax call to load the agents list when branch is selected
    $("#selectBranch").change(function () {
        var selectedBranch=$('#selectBranch').find(':selected').text();
        //alert(selectedBranch);
        $("#lblBranchId").text(selectedBranch);
        $("#lblAgentId").text("");

        $.ajax({
            url: 'https://localhost:8443/admin/processOrders/delivery/agents',
            data: {"selectedBranch":selectedBranch},
            success: function (msg) {
                //alert(msg);
                var slctBranchAgent = $("#selectAgent"), option = "";

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
    });

    //javascript to show the chosen agent name on a label
    $("#selectAgent").change(function () {
        var selectedAgent=$(this).find(':selected').text();
        //alert(selectedAgent);
        $("#lblAgentId ").text(selectedAgent);

    });

    $("#btnAssignDelivery").click(function() {
        //var branchName=$('#selectBranch').find(':selected').text();
        //var agentName=$('#selectAgent').find(':selected').text();
        var branchName=$('#lblBranchId').text();
        var agentName=$('#lblAgentId').text();
        var orderId=$('#lblOrderId').text();
        alert(orderId);
        alert(agentName);
        alert(branchName);

        if(agentName == ""){
            $.toaster({ priority : 'danger', title : 'Error', message : 'Selection is NOT complete'});

        }
        else if( agentName != "" ){
            $.ajax({
                url: "https://localhost:8443/admin/processOrders/agentAssign",
                data: {"orderId": orderId, "agentName": agentName},
                success: function (msg) {

                    if (msg == 1) {
                        $.toaster({
                            priority: 'success', title: 'Success', message: 'Deliver agent assigned successfully: '
                            + agentName
                        });
                        $("#assignOrderModel").modal('hide');
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    } else {
                        $.toaster({
                            priority: 'danger',
                            title: 'Error',
                            message: 'Cannot assign the delivery agent ' + agentName
                        });
                    }
                },
                error: function (e) {
                    alert("ajax failed" + e);
                }
            });
        }
    });



});

