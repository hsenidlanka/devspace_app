$(document).ready(function () {

    function validateInputs() {

        var itmName = document.getElementById('txtItemName');
        if (itmName.value == '') {
            document.getElementById('itmNmErr').innerHTML = 'Item Name should not be empty !';
            $(this).addClass('invalid-input');
        }
    }


    /*
     * Function for redirecting to admin home page
     * */
    $(".bckToHome").click(function () {
        location.href = 'home.html';
    })


    /*
     * Function for loading package content to modal for edditing
     */
    $("#lnkModalView").click(function () {
        $("#pkgViewModal").modal();
        load_modal('editPackage.html');
    })

    function load_modal(url) {
        $('#editModalContent').load(url, function () {
        });
    }

    /*
     * Price validation function to allow only 2 decimals
     * */
    $('.price').keypress(function (event) {
        var $this = $(this);
        if ((event.which != 46 || $this.val().indexOf('.') != -1) &&
            ((event.which < 48 || event.which > 57) &&
            (event.which != 0 && event.which != 8))) {
            event.preventDefault();
        }

        var text = $(this).val();
        if ((event.which == 46) && (text.indexOf('.') == -1)) {
            setTimeout(function () {
                if ($this.val().substring($this.val().indexOf('.')).length > 3) {
                    $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
                }
            }, 1);
        }

        if ((text.indexOf('.') != -1) &&
            (text.substring(text.indexOf('.')).length > 2) &&
            (event.which != 0 && event.which != 8) &&
            ($(this)[0].selectionStart >= text.length - 2)) {
            event.preventDefault();
        }
    })
})
