$(document).ready(function () {

    var pgLimit = 5;
    var catName = $("#txtViewSearchCategory").val();
    //alert(pgLimit);

    $.ajax({
        url: 'https://localhost:8443/admin/processOrders/view/orderProcessTable',
        data: {"initPage": "0", "pgLimit": pgLimit},
        success: function (result) {
            //alert("table success");
            $('#tableprocessOrder').bootstrapTable({
                height: 380,
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
                    searchable: true,
                    class: 'col-xs-1'
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
                    field: 'branch',
                    title: 'Serving Branch :',
                    align: 'center',
                    //width:1,
                    //class: 'col-xs-2',
                    formatter: branchFormatter,
                    events: branchEvents
                }, {
                    field: 'agent_name',
                    title: 'Delivery Agent:',
                    align: 'left',
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
            var name= objc1["name"];
            //alert("value"+ value);
            window.location.href = "https://localhost:8443/admin/subCategory/"+value;
        }
    };


    function branchFormatter(value, row, index) {
        return [
            ' <select class="branchSelect"><option value="--Select--">--Select--</option><option value="Colombo">Colombo</option> <option value="Gampaha">Gampaha</option>' +
            ' <option value="Ja-Ela">Ja-Ela</option> <option value="Kandana">Kandana</option> </select> '
        ].join('');
    }


    window.branchEvents = {
        'click .removecat': function(e, value, row, index) {
            var data2 = JSON.stringify(row);
            var objc2 = JSON.parse(data2);
            $('#lblDeleteCategoryId').text(objc2["name"]);
            $('#deleteModal1').modal({show:true});
        },
        'click .likecat': function(e, value, row, index) {
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
});
