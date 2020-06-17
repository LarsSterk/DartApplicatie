function getSpelersLijst() {

    fetch("/restservices/spelers/spelerslijst", {method: 'GET', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}})
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            //debugger;
            let replaceHTML ="";
            //Verwerk het json antwoord van de service in een tabel (via de array van spelers)
            let spelerArr = data;
            //per array rij een speler

            //per array rij een speler


            for (let i = 0; i < spelerArr.length; i++) {

                // Voeg de speler toe aan de lijst
                let spelerObj = spelerArr[i];
                replaceHTML += "<tr><td>"+spelerObj.id +"</td><td>"+spelerObj.voornaam+ "</td><td>"+spelerObj.achternaam+"</td><td>"+spelerObj.leeftijd+"</td><td>"+spelerObj.niveau+ "</td></tr>";

                // voeg het id toe aan het update scherm
                let opt = document.createElement('option');
                opt.value = spelerObj.id;
                opt.innerHTML = spelerObj.id + " " +spelerObj.voornaam+ " "+ spelerObj.achternaam;
                document.getElementById('idPUT').appendChild(opt);

                // voeg het id toe aan het delete scherm
                let opt2 = document.createElement('option');
                opt2.value = spelerObj.id;
                opt2.innerHTML = spelerObj.id + " " + spelerObj.voornaam+ " "+ spelerObj.achternaam;
                document.getElementById('idDel').appendChild(opt2);


            }
            //Vervang de rijen in de html met de nieuwe rijen
            document.getElementById("Spelers").innerHTML = replaceHTML;

        })
        .catch(function (err) {
            console.log('error: ' + err);

        });
}

function openUpdateDialog() {
    const updateSpelerButton = document.getElementById('open-dialog-btn');
    const updateDialog = document.getElementById('updateDialog');

    updateSpelerButton.addEventListener('click', function onOpen() {
        if (typeof updateDialog.showModal === "function"){
            updateDialog.showModal();
        }else{
            alert("Dialog not supported.") // Als er niks getoond kan worden komt er een error message.
        }
    })

    //Haal de detail gegevens op voor het update scherm als het id wordt aangepast

    document.querySelector('#idPUT').addEventListener("change", function () {
        let selectedId = document.querySelector("#idPUT").value;

        fetch("/restservices/spelers/spelerslijst", {method: 'GET', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}})
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {

                let spelerArr = data;

                for (let i = 0; i < spelerArr.length; i++) {

                    let spelerObj = spelerArr[i];
                    debugger ;
                    if (spelerObj.id === parseInt(selectedId)) {

                        //Zet de detailgegevens in het updatescherm
                        document.getElementById('voornaamPUT').value = spelerObj.voornaam;
                        document.getElementById('achternaamPUT').value = spelerObj.achternaam;
                        document.getElementById('leeftijdPUT').value = spelerObj.leeftijd;
                        document.getElementById('niveauPUT').value = spelerObj.niveau;
                        document.getElementById('voornaamPUT').autofocus = true;

                    }

                }

            })
            .catch(function (err) {
                console.log('error: ' + err);

            });

    });

}

function openDeleteDialog() {
    const deleteBtn = document.getElementById('verwijder-btn');
    const deleteDialog = document.getElementById('delete-dialog');
    deleteBtn.addEventListener('click', function onOpen() {
        if (typeof deleteDialog.showModal === "function"){
        deleteDialog.showModal();
        }else{
            alert("Dialog not supported.") // Als er niks getoond kan worden komt er een error message.
        }
    })
}

function closeUpdateDialog() {
    const upBackBtn =document.getElementById('upBackBtn');
    const updateDialog = document.getElementById('updateDialog');

    upBackBtn.addEventListener('click', function onCancel() {
    updateDialog.close();
    })
}

function closeDeleteDialog() {
    const delBackBtn =document.getElementById('delBackBtn');
    const deleteDialog = document.getElementById('delete-dialog');

    delBackBtn.addEventListener('click', function onCancel() {
        deleteDialog.close();
    })
}


