function getSpelersLijst() {

    fetch("/restservices/spelers/spelerslijst", {method: 'GET', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}})
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            //debugger;
            var replaceHTML ="";
            //Verwerk het json antwoord van de service in een tabel (via de array van spelers)
            var spelerArr = data;
            //per array rij een speler
            for (var i = 0; i < spelerArr.length; i++) {
                var spelerObj = spelerArr[i];
                replaceHTML += "<tr><td>"+spelerObj.id +"</td><td>"+spelerObj.voornaam+ "</td><td>"+spelerObj.achternaam+"</td><td>"+spelerObj.leeftijd+"</td><td>"+spelerObj.niveau+"</td></tr>";
            };
            //Vervang de rijen in de html met de nieuwe rijen
            document.getElementById("Spelers").innerHTML = replaceHTML;

        })
        .catch(function (err) {
            console.log('error: ' + err);

        });
}