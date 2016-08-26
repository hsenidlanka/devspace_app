$(document).ready(function() {

   function validateInputs(){

       var itmName = document.getElementById('txtItemName');
       if(itmName.value == ''){
            document.getElementById('itmNmErr').innerHTML= 'Item Name should not be empty !';
           $(this).addClass('invalid-input');
       }
   }

    function goHome() {
        window.location.href='home.html';
    }
})
