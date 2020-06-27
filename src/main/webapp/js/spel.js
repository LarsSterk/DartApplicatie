function startSpel() {

    //andere variabelen
    let spelernaam1 = document.getElementById('naam1');
    let spelernaam2 = document.getElementById('naam2');
    let spelerObj1;
    let spelerObj2;
    sessionStorage.setItem("worpCount", "0");

    //Haal de geselecteerde spelers op uit de sessionstorage
    let id1 = sessionStorage.getItem("SelectId1");
    let id2 = sessionStorage.getItem("SelectId2");

    //Zet de beginscore vanuit het gekozen speltype
    sessionStorage.setItem("sp1Score", sessionStorage.getItem("spelType"));
    sessionStorage.setItem("sp2Score", sessionStorage.getItem("spelType"));
    //Zet de beginscore ook op het scherm
    let worpstart1 = document.getElementById('worpstart1');
    let worpstart2 = document.getElementById('worpstart2');
    worpstart1.innerHTML = sessionStorage.getItem("sp1Score")
    worpstart2.innerHTML = sessionStorage.getItem("sp2Score")
    // Speler1 begint
    sessionStorage.setItem("beurt", "1");
    spelernaam1.style.color = "green";


    //haal spelersnaam 1 op

    fetch("/restservices/spelers/spelerslijst/" + id1, {
        method: 'GET',
        headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            spelerObj1 = data[0];
            sessionStorage.setItem("sp1Naam", spelerObj1.voornaam + " " + spelerObj1.achternaam);
            spelernaam1.innerHTML = sessionStorage.getItem("sp1Naam");
        })
        .catch(function (err) {
            console.log('error: ' + err);

        });

    //haal speler 2 op
    fetch("/restservices/spelers/spelerslijst/" + id2, {
        method: 'GET',
        headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            spelerObj2 = data[0];
            sessionStorage.setItem("sp2Naam", spelerObj2.voornaam + " " + spelerObj2.achternaam);
            spelernaam2.innerHTML = sessionStorage.getItem("sp2Naam");

        })
        .catch(function (err) {
            console.log('error: ' + err);

        });


}

function addScore() {
    let spelernaam1 = document.getElementById('naam1');
    let spelernaam2 = document.getElementById('naam2');

    // chck waardes
    if (parseInt(document.getElementById('worp').value) > 180){
        alert("De score kan niet hoger zijn dan 180")
        // if (parseInt(document.getElementById('worp').value) < 0){
        //     alert("De score kan niet lager zijn dan 0")
        // }

    }else {
        if (sessionStorage.getItem("beurt") === "1") {
        //hoog de worp beurt op als speler 1 aan de beurt is
        let worpCount = parseInt(sessionStorage.getItem("worpCount"));
        worpCount = worpCount + 1;
        sessionStorage.setItem("worpCount", worpCount);

        let worp = parseInt(document.getElementById('worp').value);
        let score = parseInt(sessionStorage.getItem("sp1Score"));
        score = score - worp;

        //sla de score op
        sessionStorage.setItem("sp1Score", score);

        // Volgende beurt is voor speler 2
        spelernaam1.style.color = "black";
        sessionStorage.setItem("beurt", "2");
        spelernaam2.style.color = "green";

        //zet de nieuwe score van speler 1 op het scherm
        let scoreboard = document.getElementById('scoreboard');
        scoreboard.innerHTML = scoreboard.innerHTML + "<tr><td>" + worpCount + "</td><td>" + sessionStorage.getItem("sp1Score") + "<td id=\"worp" + worpCount + "\"></td>"

    } else {
        worp = parseInt(document.getElementById('worp').value);
        score = parseInt(sessionStorage.getItem("sp2Score"));
        score = score - worp;

        //sla de score op
        sessionStorage.setItem("sp2Score", score);

        // Volgende beurt is voor speler 2
        spelernaam2.style.color = "black";
        sessionStorage.setItem("beurt", "1");
        spelernaam1.style.color = "green";

        //zet de nieuwe score van speler 2 op het scherm
        worpCount = parseInt(sessionStorage.getItem("worpCount"));
        //De kolom bestaat al dus alleen op de goede plek zetten voor speler 2
        scoreboard = document.getElementById('worp' + worpCount);
        scoreboard.innerHTML = sessionStorage.getItem("sp2Score");
    }
    }

}