document.querySelector('#DELETENoJackson').addEventListener("click", function () {
    let id = document.querySelector("#idDel").value;

    fetch("/restservices/spelers/spelerslijst/" + id, {method: "DELETE"})
        .then(response => response.json())
        .then(function (myJson) {
            console.log(myJson)
        });

    let input = document.getElementById(id).value;
    alert("Speler met id " + input + " is succesvol verwijderd.")
});
