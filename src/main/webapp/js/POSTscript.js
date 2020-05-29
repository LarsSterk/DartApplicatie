document.querySelector('#postNoJackson').addEventListener("click", function () {
    var formData = new FormData(document.querySelector('#POSTspelerForm'));
    var encData = new URLSearchParams(formData.entries());

//    fetch("/restservices/spelers/aanmaken", {method: 'POST', body: encData})
//        .then(response => response.json())
//        .then(function (myJson) {
//            console.log(myJson)
//        });

    fetch("/restservices/spelers/aanmaken", {method: 'POST', body: encData})
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data)
           // appendData(data);
        })
        .catch(function (err) {
            console.log('error: ' + err);
        });


    // return message
    let inputVnm = document.getElementById("voornaam").value;
    let inputAnm = document.getElementById("achternaam").value;

    // let klas = document.querySelector('DELETENoJackson');
    // let id = document.querySelector("#id").value;
    // let inputId = document.getElementById(id).value;

    function appendData(data) {
        var mainContainer = document.getElementById("resultaatPOST");

        //     for (var i = 0; i < data.length; i++) {
        var div = document.createElement("div");
        div.innerHTML = '<h2>gelukt!: </h2>' + data.toString();
        //   div.innerHTML = 'Name: ' + data[i].firstName + ' ' + data[i].lastName;
        mainContainer.appendChild(div);
    }

        alert("Speler " + inputVnm + " " + inputAnm + " met id nummer: " + " is succesvol aangemaakt!");

    });