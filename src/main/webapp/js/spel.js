function startSpel() {
    //variabelen
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

    //haal spelersnaam 1 op dmv id
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

    //haal speler 2 op dmv id
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

function herstartSpel() {
    window.location.replace('spel.html');
}

function clearText() {
    document.getElementById('worp').value = "";
    document.getElementById('worp').focus();
    document.getElementById('worp').select();
}

function controleerInvoerWorp(worp) {
    if (isNaN(worp)) {
        alert("Let op: ingevulde waarde moet bestaan uit cijfers tussen 0 en 180.")
        return false;
    } else {
        if (parseInt(worp) > 180) {
            alert("De score kan niet hoger zijn dan 180.")
            return false;
        } else if (parseInt(worp) < 0) {
            alert("De score kan niet lager zijn dan 0.")
            return false;
        } else if (parseInt(worp) === 163 ||  // Check of de score geen onmogelijke score is
            parseInt(worp) === 166 ||
            parseInt(worp) === 169 ||
            parseInt(worp) === 172 ||
            parseInt(worp) === 173 ||
            parseInt(worp) === 175 ||
            parseInt(worp) === 176 ||
            parseInt(worp) === 178 ||
            parseInt(worp) === 179) {
            alert("Onmogelijke invoer: Deze worp score is niet haalbaar met drie pijlen.")
            return false;
        }
    }
    return true;
}

function addScore() {
    let spelernaam1 = document.getElementById('naam1');
    let spelernaam2 = document.getElementById('naam2');
    let worpInvoer = document.getElementById('worp').value;
    let worpWaarde = parseInt(worpInvoer);

    if (controleerInvoerWorp(worpInvoer)) {
        // Als score juist is dan :
        if (sessionStorage.getItem("beurt") === "1") { // Check of speler 1 aan de beurt is
            if ((parseInt(sessionStorage.getItem("sp1Score")) - worpWaarde !== 1) && (worpWaarde < parseInt(sessionStorage.getItem("sp1Score")))) {
                //hoog de worp beurt op als speler 1 aan de beurt is
                let worpCount = parseInt(sessionStorage.getItem("worpCount"));
                worpCount = worpCount + 1;
                sessionStorage.setItem("worpCount", worpCount);

                let score = parseInt(sessionStorage.getItem("sp1Score"));
                score = score - worpWaarde;

                //sla de score op
                sessionStorage.setItem("sp1Score", score);

                // Volgende beurt is voor speler 2 (Kleur veranderd)
                spelernaam1.style.color = "black";
                sessionStorage.setItem("beurt", "2");
                spelernaam2.style.color = "green";

                //zet de nieuwe score van speler 1 op het scherm
                let scoreboard = document.getElementById('scoreboard');
                scoreboard.innerHTML = scoreboard.innerHTML + "<tr><td>" + worpCount + "</td><td>" + sessionStorage.getItem("sp1Score") + "<td id=\"worp" + worpCount + "\"></td>"

            } else if (parseInt(sessionStorage.getItem("sp1Score")) === worpWaarde) { // Check of de score 0 is zodat er een winnaar is.
                let worpCount = parseInt(sessionStorage.getItem("worpCount"));
                worpCount = worpCount + 1;
                sessionStorage.setItem("worpCount", worpCount);

                let score = parseInt(sessionStorage.getItem("sp1Score"));
                score = score - worpWaarde;

                //sla de score op
                sessionStorage.setItem("sp1Score", score);

                let scoreboard = document.getElementById('scoreboard');
                scoreboard.innerHTML = scoreboard.innerHTML + "<tr><td>" + worpCount + "</td><td>" + "WINNAAR!" + "<td id=\"worp" + worpCount + "\"></td>"

                document.getElementById('addScore').disabled = true;

                alert(sessionStorage.getItem("sp1Naam") + " heeft gewonnen!!")


            } else { // Nu is speler 2 aan de beurt, omdat de getitem(beurt) niet meer op 1 staat
                let worpCount = parseInt(sessionStorage.getItem("worpCount"));
                worpCount = worpCount + 1;
                sessionStorage.setItem("worpCount", worpCount);

                // Volgende beurt is voor speler 2
                spelernaam1.style.color = "black";
                sessionStorage.setItem("beurt", "2");
                spelernaam2.style.color = "green";

                //zet de nieuwe score van speler 1 op het scherm
                let scoreboard = document.getElementById('scoreboard');
                scoreboard.innerHTML = scoreboard.innerHTML + "<tr><td>" + worpCount + "</td><td>" + sessionStorage.getItem("sp1Score") + "<td id=\"worp" + worpCount + "\"></td>"
                alert(sessionStorage.getItem("sp1Naam") + " bust")

            }
        } else {
            if ((parseInt(sessionStorage.getItem("sp2Score")) - worpWaarde !== 1) && (worpWaarde < parseInt(sessionStorage.getItem("sp2Score")))) {
                score = parseInt(sessionStorage.getItem("sp2Score"));
                score = score - worpWaarde;

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
            } else if (parseInt(sessionStorage.getItem("sp2Score")) === worpWaarde) {

                let score = parseInt(sessionStorage.getItem("sp2Score"));
                score = score - worpWaarde;

                //sla de score op
                sessionStorage.setItem("sp2Score", score);

                //zet de nieuwe score van speler 2 op het scherm
                worpCount = parseInt(sessionStorage.getItem("worpCount"));
                //De kolom bestaat al dus alleen op de goede plek zetten voor speler 2
                scoreboard = document.getElementById('worp' + worpCount);
                scoreboard.innerHTML = "WINNAAR!";

                document.getElementById('addScore').disabled = true;

                alert(sessionStorage.getItem("sp2Naam") + " heeft gewonnen!!");

            } else {
                score = parseInt(sessionStorage.getItem("sp2Score"));

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

                alert(sessionStorage.getItem("sp1Naam") + " bust");

            }
        }
        clearText(); // maakt de invoer balk leeg
    }

}