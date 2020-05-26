document.querySelector('#postNoJackson').addEventListener("click", function () {
    var formData = new FormData(document.querySelector('#POSTspelerForm'));
    var encData = new URLSearchParams(formData.entries());

    fetch("/restservices/spelers/aanmaken", {method: 'POST', body: encData})
        .then(response => response.json())
        .then(function (myJson) {
            console.log(myJson)
        });
    let inputVnm = document.getElementById("voornaam").value;
    let inputAnm = document.getElementById("achternaam").value;

    // let klas = document.querySelector('DELETENoJackson');
    // let id = document.querySelector("#id").value;
    // let inputId = document.getElementById(id).value;


    alert("Speler " + inputVnm + " " + inputAnm + " met id nummer: "  + " is succesvol aangemaakt!");

});