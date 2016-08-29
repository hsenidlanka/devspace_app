$(document).ready(function(){

    $('#btnAddNewPkg').click(function () {

        //if success
       // $.toaster({ priority : 'success', title : 'Success', message : 'Added  new package .'});

        // if errorsome
        $.toaster({ priority : 'danger', title : 'Error', message : 'Cannot add package. Check again ! '})
    })
})



/*
* $.toaster({ priority : 'info', title : 'Title', message : 'Your message here'});
 $.toaster({ priority : 'warning', title : 'Title', message : 'Your message here'});
;*/
