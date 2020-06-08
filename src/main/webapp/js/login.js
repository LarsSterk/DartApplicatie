function login(event) {
    const urlParams = new URLSearchParams(window.location.search);
    let srcRedirect = urlParams.get('src');

    let formData = new FormData(document.querySelector("#loginForm"));
    let encData = new URLSearchParams(formData.entries());
    // debugger;
    fetch("/restservices/authentication", {method: 'POST', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}, body: encData})
        .then(function (response) {
            if (response.ok){
                return response.json();
            }else {
                throw "Wrong username or password"; // Gaat naar de catch waar de alert wordt uitgevoerd.
            }

        })
        .then(function (myJson) {
            window.sessionStorage.setItem("myJWT", myJson.JWT);
            window.sessionStorage.setItem("status", "loggedIn");
            if (srcRedirect === null){
                srcRedirect = "index.html";
            }
            window.location.replace(srcRedirect);

        })
        .catch(function (error) {

            console.log(error);
            alert("Gebruiker of wachtwoord incorrect")

        })
}
document.querySelector('#login').addEventListener("click", login)