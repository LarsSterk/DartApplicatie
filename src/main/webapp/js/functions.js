// !!! Werkt nog niet !!!

 async function loadSpeler(event) {
    let element = document.querySelector("#getresponse")

    let id = document.querySelector("#spelertoload").value;
    element.textContent = "";
    document.querySelector("#spelerview")

    let response = await fetch("/restservices/spelers/spelerslijst/" + id);

    if (response.status === 200){
        let jsonData = await response.json();
        showSpeler(jsonData);
    } else element.textContent = "Statuscode: " + response.status;

}

function showSpeler(myJson) {
    let template = document.querySelector("#accounttemplate");
    let templateDiv = template.content.querySelector("div");
    let newDiv = document.importNode(templateDiv, true);

    newDiv.querySelector(".id").innerText = myJson.id;
    newDiv.querySelector(".voornaam").innerText = myJson.voornaam;
    newDiv.querySelector(".achternaam").innerText = myJson.achternaam;
    newDiv.querySelector(".leeftijd").innerText = myJson.leeftijd;
    newDiv.querySelector(".niveau").innerText = myJson.niveau;

    document.querySelector("#spelerview").appendChild(newDiv);

}