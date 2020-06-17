function updateSpeler() {

    let id = document.querySelector("#idPUT").value
  //      document.getElementById('#idPUT').value;
        let formData = new FormData(document.querySelector('#PUTspelerForm'));
        let encData = new URLSearchParams(formData.entries());


        fetch("/restservices/spelers/spelerslijst/update/" + id, {
            method: 'PUT',
            headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")},
            body: encData
        })
            .then(response => response.json())
            .then(function (myJson) {
                alert("Speler succesvol geupdate")
                window.location.replace("spelersLijst.html")})
            .catch(function (err) {
                console.log('error: ' + err);
                alert("Speler heeft geen toegang.")
            });
}