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
/*
        alert(index);
        alert(deliveryStatus);*/

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
            '<a class="assignOrder" href="javascript:void(0)" title="LikeCustomer" data-toggle="modal" >',
            '<i class="glyphicon glyphicon-edit">Assign</i>',
            //'<em class="fa fa-pencil"></em>',
            '</a>  ',
            '<a class="reassignOrder" href="javascript:void(0)" title="Delete">',
            '<i class="glyphicon glyphicon-refresh">Change</i>',
            '</a>'
        ].join('');
    }

    window.processEvents = {

        'click .assignOrder': function(e, value, row, index) {
            var data2 = JSON.stringify(row);
            var objc2 = JSON.parse(data2);
            $('#lblDeleteCategoryId').text(objc2["order_id"]);
            $('#assignOrderModel').modal({show:true});
        },
        'click .reassignOrder': function(e, value, row, index) {
            var data1 = JSON.stringify(row);
            var objc1 = JSON.parse(data1);
            var id= objc1["id"];
            var name= objc1["name"];
            $.ajax({
                //type: "POST",
                url: "https://localhost:8443/admin/category/edit",
                data: {"id":id},
                success: function(msg){
                    var name=msg["name"];
                    var image1=msg["image"];

                    var  url="https://localhost:8443/admin/themes/hsenid/images/categories/" +image1;
                    //alert(url);
                    $('#imageUrl').attr('src',url);
                    $('#categoryid').val(id);
                    $('#editcategoryname').val(msg["name"]);
                    $('#catName').val(msg["name"]);

                    $('#editcategorydes').val(msg["description"]);
                    $('#editvisibility').val(msg["status"]);

                    $('#modifyModel').modal({show:true});
                },
                error:function(e){
                    alert("ajax failed");
                }
            });
        }
    };







   /* function branchFormatter(value, row, index) {
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
*/

});

