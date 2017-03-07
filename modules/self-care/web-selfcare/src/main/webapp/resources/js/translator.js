function checkLang(){
 var lang = $("#lang-select").val();
 return lang;
}

/*
 function processData(jsonData, lang, key) {
 $(jsonData[lang]).each(function (index, value) {
 translatedText = value[key];
 });
 console.log(translatedText);
 }

 function getranslation(lang, key) {
 $.ajax({
 url: "resources/js/translations/langs.json",
 dataType: "json",
 type: "get",
 success: function (data) {
 //processData(data, lang, key);
 $(data[lang]).each(function (index, value) {
 translatedText = value[key];
 });
 }
 });
 console.log(translatedText);
 }*/
