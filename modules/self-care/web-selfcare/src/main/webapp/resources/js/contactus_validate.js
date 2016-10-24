/*
window.onload = function() {
    //get elements
    //var name1 = document.getElementById("name1");
    var name1 = $("#txt-fname").val();
    var email1 =  $("txt-email").val();
    var phone1 = $("txt-contactno").val();
    var message1 = $("txt-inquirymsg").val();
    var typeselectIMGNm = document.getElementById("typeselectIMGNm");
    var typeselectIMGEm = document.getElementById("typeselectIMGEm");
    var typeselectIMGPh = document.getElementById("typeselectIMGPh");
    var typeselectIMGMs = document.getElementById("typeselectIMGMs");

    //select event
    name1.onchange = function () {
        if (this.value != null||this.value!='') {
            typeselectIMGNm.style.visibility = 'visible';
        } else {
            typeselectIMGNm.style.visibility = 'hidden';

        }
    };
    email1.onchange = function () {
        if (this.value != null) {
            typeselectIMGEm.style.visibility = 'visible';
        } else {
            typeselectIMGEm.style.visibility = 'hidden';

        }
    };
    phone1.onchange = function () {
        if (this.value.maxLength==10) {
            typeselectIMGPh.style.visibility = 'visible';
        } else {
            typeselectIMGPh.style.visibility = 'hidden';

        }
    };
    message1.onchange = function () {
        if (this.value!=null) {
            typeselectIMGMs.style.visibility = 'visible';
        } else {
            typeselectIMGMs.style.visibility = 'hidden';

        }
    };
    quantInput.oninput = function(){
        if(this.value != ''){
            quantImg.style.visibility = 'visible';
        }else{
            quantImg.style.visibility = 'hidden';
        }
    };

}
*/
