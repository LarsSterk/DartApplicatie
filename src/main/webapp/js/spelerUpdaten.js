function updateSpeler() {
    document.querySelector('#PUTnoJackson').addEventListener("click", function () {
        let id = document.getElementById('#idPUT').value;
        let formData = new FormData(document.querySelector('#PUTspelerForm'));
        let encData = new URLSearchParams(formData.entries());

        debugger;
        fetch("/restservices/spelers/spelerslijst/update/" + id, {
            method: 'PUT',
            headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")},
            body: encData
        })
            .then(response => response.json())
            .then(function (myJson) {
                console.log(myJson)
            });

    });
}