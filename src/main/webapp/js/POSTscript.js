document.querySelector('#postNoJackson').addEventListener("click", function () {
    var formData = new FormData(document.querySelector('#POSTspelerForm'));
    var encData = new URLSearchParams(formData.entries());

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
    });