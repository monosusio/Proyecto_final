

var formularioFCoins = document.getElementById("recarga");
formularioFCoins.addEventListener("submit",function(e){
    e.preventDefault();

    console.log("estos son los fcoins "+document.getElementById("fcoins").value);

    console.log("este es el email "+localStorage.getItem("email"));
    const timestamp = Date.now();
    const fecha = new Date(timestamp);
    const registeredat = toString(fecha);

    var data = {

        "email":  localStorage.getItem("email"),
        "fcoins": document.getElementById("fcoins").value,

    };

    console.log("la fecha es"+registeredat);

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    fetch("./api/wallethistory/agregar",{method: "POST",
        body: JSON.stringify(data),
        headers: myHeaders
    })
        .then(res =>
            res.json()
        )
        .then(dato => {

            console.log("Este es el email del que le corresponde a la recarga: " + dato["email"]);

        })
})