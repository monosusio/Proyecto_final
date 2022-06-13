
var formularioArt = document.getElementById("pi");
formularioArt.addEventListener("submit",function(e){
    e.preventDefault();


    var data = {
        "name": document.getElementById("nameArt").value,
        "price": document.getElementById("price").value,
        "co_id":  document.getElementById("co_id").value

    };

    console.log(data);

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    fetch("./api/art/agregar",{method: "POST",
        body: JSON.stringify(data),
        headers: myHeaders
    })
        .then(res =>
            res.json()
        )
        .then(dato => {


        })
})