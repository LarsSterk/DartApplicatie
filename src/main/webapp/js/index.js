function spelAanmaken() {

    var formData = new FormData(document.querySelector('#spelForm'));
    var encData = new URLSearchParams(formData.entries());

    // Fetch naar SpelService, is NOG niet geimplementeerd;
    fetch("/restservices/spel/aanmaken", {
        method: 'POST',
        headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")},
        body: encData
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data);
            // iteratie 4: rechtstreeks naar spel spelen via session storage ipv via backend en azure
            sessionStorage.setItem("SelectId1", formData.get("id1"));
            sessionStorage.setItem("SelectId2", formData.get("id2"));
            sessionStorage.setItem("spelType", formData.get("spelType"));
            window.location.replace("spel.html");

        })
        .catch(function (err) {
            console.log('error: ' + err);
        });
};

function getSpelersLijstNieuwSpel() {

    fetch("/restservices/spelers/spelerslijst", {
        method: 'GET',
        headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {

            let replaceHTML = "";
            //Verwerk het json antwoord van de service in een tabel (via de array van spelers)
            let spelerArr = data;
            let selectSpeler1 = document.getElementById('idSpeler1');
            let selectSpeler2 = document.getElementById('idSpeler2');
            selectSpeler1.options.length = 1;
            selectSpeler2.options.length = 1;

            for (let i = 0; i < spelerArr.length; i++) {


                // Voeg de speler toe aan de twee lijsten

                let spelerObj = spelerArr[i];
                let opt3 = document.createElement('option');
                opt3.value = spelerObj.id;
                opt3.innerHTML = spelerObj.id + " " + spelerObj.voornaam + " " + spelerObj.achternaam;
                selectSpeler1.appendChild(opt3);

                let opt4 = document.createElement('option');
                opt4.value = spelerObj.id;
                opt4.innerHTML = spelerObj.id + " " + spelerObj.voornaam + " " + spelerObj.achternaam;
                selectSpeler2.appendChild(opt4);


            }

        })
        .catch(function (err) {
            console.log('error: ' + err);

        });
}


function nieuwSpel() {
    const startSpelButton = document.getElementById('nieuw-spel')

    startSpelButton.addEventListener('click', function () {

    })
}


function openNieuwSpelDialog() {
    const spelBtn = document.getElementById('nieuw-spel');
    const spelDialog = document.getElementById('nieuw-spel-dialog');

    spelBtn.addEventListener('click', function onOpen() {
        if (typeof spelDialog.showModal === "function") {
            spelDialog.showModal();
            getSpelersLijstNieuwSpel();
        } else {
            alert("Dialog not supported.") // Als er niks getoond kan worden komt er een error message.
        }
    })
}

function closeSpelDialog() {
    const backBtn = document.getElementById('backBtn');
    const spelDialog = document.getElementById('nieuw-spel-dialog');

    backBtn.addEventListener('click', function onCancel() {
        spelDialog.close();
    })
}