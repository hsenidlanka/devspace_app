
function hide(elm) {
    if (elm.checked) {
        document.getElementById('staffDetail').style.visibility = 'hidden';

    }
}


function show(elm) {
    if (elm.checked) {
        document.getElementById('staffDetail').style.visibility = 'visible';

    }
}

function hideDefault(){
    document.getElementById('staffDetail').style.visibility = 'hidden';
}