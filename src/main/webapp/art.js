
var formularioCollection = document.getElementById("pi");
formularioCollection.addEventListener("submit",function(e){
    e.preventDefault();

    console.log("este es el titulo "+document.getElementById("name").value);
    console.log("esta es la category "+document.getElementById("category").value);
    console.log("esta es la descripcion "+document.getElementById("description").value);
    console.log("este es el email "+localStorage.getItem("email"));

    var data = {
        "name": document.getElementById("name").value,
        "category": document.getElementById("category").value,
        "description": document.getElementById("description").value,
        "email":  localStorage.getItem("email"),

    };

    console.log(data);

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    fetch("./api/collection/agregar",{method: "POST",
        body: JSON.stringify(data),
        headers: myHeaders
    })
        .then(res =>
            res.json()
        )
        .then(dato => {

            console.log("Este es el email del que le corresponde a la coleccion: " + dato["email"]);

        })
})