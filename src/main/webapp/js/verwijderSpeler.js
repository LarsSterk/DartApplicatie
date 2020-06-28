function verwijderSpeler() {
    let id = document.querySelector("#idDel").value;

    fetch("/restservices/spelers/spelerslijst/delete/" + id, {
        method: "DELETE",
        headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            alert("Speler succesvol verwijderd")
            window.location.replace("spelersOverzicht.html")


        })
        .catch(function (err) {
            console.log('error: ' + err);
            alert("Speler heeft geen toegang.")

        });

    let input = document.getElementById(id).value;

}