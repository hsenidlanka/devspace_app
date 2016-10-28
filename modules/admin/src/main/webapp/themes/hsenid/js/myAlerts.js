$(document).ready(function(){

    $('#btnAddNewPkg1').click(function () {

        //if success
       // $.toaster({ priority : 'success', title : 'Success', message : 'Added  new package .'});

        // if error occurred
        $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot add package. Check again ! '})
    })

    $('#btnUpdtPkgDelt1').click(function () {
        //if success
        $.toaster({priority: 'success', title: 'Success', message: 'Package updates done .'})

        //if error occurred
       // $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot update package. Check again ! '})

    })

    $('#btnAddItem1').click(function () {
        //if success
        $.toaster({priority: 'success', title: 'Success', message: 'Added new item .'})

        //if error occurred
        // $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot add item. Check again ! '})

    })

    $('#btnEditItem1').click(function (){

        //if success
        $.toaster({priority: 'success', title: 'Success', message: 'Updated item details .'})

        //if error occurred
        // $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot update item. Check again ! '})
    })
})



/*
* $.toaster({ priority : 'info', title : 'Title', message : 'Your message here'});
 $.toaster({ priority : 'warning', title : 'Title', message : 'Your message here'});
;*/
