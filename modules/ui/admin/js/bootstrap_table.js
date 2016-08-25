$(document).ready(function () {


            $('#table').bootstrapTable({
                pagination: true,
                pageSize: 10,
                // pageList: [10, 25, 50, 100, 200],
                //search: true,
                //showColumns: true,
                //showRefresh: true,
                //showToggle:true,
                //detailView:true,
                //detailFormatter: detailFormatter,
                clickToSelect: true,
                singleSelect: true,
                //toolbar: '#toolbar',

                minimumCountColumns: 3,
                columns: [/*{
                 field: 'state',
                 checkbox: true

                 },*/{
                    //field: 'id',
                    title: 'ID :',
                    sortable: true,
                    align:'left'
                },{
                    //field: 'user_name',
                    title: 'Username :',
                    sortable: true,
                    align:'left',
                    searchable:true
                }, {
                    field: 'f_name',
                    title: 'First Name :',
                    align:'left',
                    sortable: true
                }, {
                    field: 'l_name',
                    title: 'Last Name :',
                    align:'left',
                    sortable: true
                }, {
                    field: 'mobile',
                    title: 'Phone No :',
                    align:'left'
                }, {
                    field: 'e_mail',
                    title: 'Email :',
                    align:'left'
                }, {
                    field: 'address',
                    title: 'Address :',
                    align:'left'
                },/*{
                 field: 'group',
                 title: 'Role :',
                 align:'left'
                 },*/ {
                    field: 'Options',
                    title: 'Options :',
                    align: 'center',
                    formatter: operateFormatter,
                    events: operateEvents
                }]

            });
})



function operateFormatter(value, row, index) {
    return [
        '<a class="like" href="#" title="Like" >',
        '<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="remove" href="#" title="Delete">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click .like': function (e, value, row, index) {
      /*  var data1 = JSON.stringify(row);
        var objc1 = JSON.parse(data1);*/
/*
        $('#update-form-username').val(objc1["user_name"]);
        $('#update-first-name').val(objc1["f_name"]);
        $('#update-last-name').val(objc1["l_name"]);
        $('#date2').val(objc1["niceDate"]);
        $('#update-form-mobile').val(objc1["mobile"]);
        $('#update-form-email').val(objc1["e_mail"]);
        $('#update-country.value').val(objc1["country"]);
        $('#update-form-city').val(objc1["city_id"]);*/
        //$('#update-password').val(objc1["niceDate"]);


        //$('#myModal2').modal('show');

        //alert('You click like action, row: ' + data1);

    },

    'click .remove': function (e, value, row, index) {
        var data2 = JSON.stringify(row);
        var objc2 = JSON.parse(data2);
        $('#lblUname').text(objc2["user_name"]);
        $('#myModal1').modal('show');

        //alert('You click like action, row: ' + objc["user_name"]);

    }
};