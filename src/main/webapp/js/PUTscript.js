document.querySelector('#PUTnoJackson').addEventListener("click", function () {
    let id = document.getElementById('#idPUT').value;
    let formData = new FormData(document.querySelector('#PUTspelerForm'));
    let encData = new URLSearchParams(formData.entries());

    fetch("/restservices/spelers/spelerslijst/update/" + id, {method:'PUT', body: encData})
        .then(response => response.json())
        .then(function (myJson) {
            console.log(myJson)
        });

});