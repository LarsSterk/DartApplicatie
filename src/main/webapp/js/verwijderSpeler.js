function verwijderSpeler() {
    document.querySelector('#DELETENoJackson').addEventListener("click", function () {
        let id = document.querySelector("#idDel").value;

        fetch("/restservices/spelers/spelerslijst/" + id, {
            method: "DELETE",
            headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                alert("Speler succesvol verwijderd")
                window.location.replace("spelersLijst.html")


            })
            .catch(function (err) {
                console.log('error: ' + err);
                alert("Speler heeft geen toegang.")

            });

        let input = document.getElementById(id).value;
    });
}