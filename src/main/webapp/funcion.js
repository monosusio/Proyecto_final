

var formularioCollection = document.getElementById("ex");
formularioCollection.addEventListener("submit",function(e){
    e.preventDefault();

    var data = {

       "email":  localStorage.getItem("email"),

    };

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    var newdata= fetch("./api/collection/form",{method: "POST",
        body: JSON.stringify(data),
        headers: myHeaders
    })
        .then(res =>
            res.json()
        )
        .then(dato => {

            localStorage.setItem("email",dato["email"]);


            console.log("Si sirve esta piroba mrda en el fetch")


            console.log("Este es el email del que le corresponde a la coleccion: " + dato["email"]);

        })
})