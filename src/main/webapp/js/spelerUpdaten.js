document.querySelector('#PUTnoJackson').addEventListener("click", function () {
    let id = document.getElementById('#idPUT').value;
    let formData = new FormData(document.querySelector('#PUTspelerForm'));
    let encData = new URLSearchParams(formData.entries());

    fetch("/restservices/spelers/spelerslijst/update/" + id, {method:'PUT', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}, body: encData})
        .then(response => response.json())
        .then(function (myJson) {
            console.log(myJson)
        });

});



function open() {
    const updateSpelerButton = document.getElementById('open-dialog-btn');
    const updateDialog = document.getElementById('updateDialog');

// addCityButton event listener om de dialog te tonen wanneer er op de button addCityButton geklikt word
    updateSpelerButton.addEventListener('click', function onOpen() {
        if (typeof updateDialog.showModal === "function"){
            updateDialog.showModal();
        }else{
            alert("Dialog not supported.") // Als er niks getoond kan worden komt er een error message.
        }
    })

}