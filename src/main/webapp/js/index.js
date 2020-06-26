function nieuwSpel(){
    const startSpelButton = document.getElementById('nieuw-spel')

    startSpelButton.addEventListener('click', function () {

    })
}


function openNieuwSpelDialog() {
    const spelBtn = document.getElementById('nieuw-spel');
    const spelDialog = document.getElementById('nieuw-spel-dialog');

    spelBtn.addEventListener('click', function onOpen() {
        if (typeof spelDialog.showModal === "function"){
            spelDialog.showModal();
            // getSpelersLijst();
        }else{
            alert("Dialog not supported.") // Als er niks getoond kan worden komt er een error message.
        }
    })
}

function closeSpelDialog() {
    const backBtn =document.getElementById('backBtn');
    const spelDialog = document.getElementById('nieuw-spel-dialog');

    backBtn.addEventListener('click', function onCancel() {
        spelDialog.close();
    })
}