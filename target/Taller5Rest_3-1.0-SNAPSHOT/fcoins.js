

var formularioFCoins = document.getElementById("recarga");
formularioFCoins.addEventListener("submit",function(e){
    e.preventDefault();

    console.log("estos son los fcoins "+document.getElementById("fcoins").value);

    console.log("este es el email "+localStorage.getItem("email"));
    const fecha = new Date();
    const timestamp = fecha.getTime();
    var data = {

        "email":  localStorage.getItem("email"),
        "fcoins": document.getElementById("fcoins").value,

    };

    console.log(data);
    console.log(timestamp);

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    fetch("./api/wallethistory/agregar",{method: "POST",
        body: JSON.stringify(data,timestamp),
        headers: myHeaders
    })
        .then(res =>
            res.json()
        )
        .then(dato => {

            console.log("Este es el email del que le corresponde a la recarga: " + dato["email"]);

        })
})