const $btnIniciarSesion= document.querySelector('.Singboton'),
    $btnRegistrase = document.querySelector('.Regboton'),
    $Registrarse = document.querySelector('.Registrarse'),
    $IniciarSesion = document.querySelector('.IniciarSesion');

var formulario = document.getElementById("login");
formulario.addEventListener("submit",function(e){
    e.preventDefault();

    var data = {
        "email": document.getElementById("email").value,
        "password": document.getElementById("password").value,

    };

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    var newdata= fetch("./api/users/found",{method: "POST",
        body: JSON.stringify(data),
        headers: myHeaders
    })
        .then(res =>
            res.json()
        )
        .then(dato => {
            localStorage.setItem("email",dato["email"]);
            localStorage.setItem("name",dato["name"]);
            localStorage.setItem("role",dato["role"]);

            console.log(dato)

            if(dato["role"] == "Artist"){
                window.location.href = "http://localhost:8080/Taller5Rest_3-1.0-SNAPSHOT/artista.html";
                console.log("Es Artist");
            }else if(dato["role"] === "Customer"){
                window.location.href = "http://localhost:8080/Taller5Rest_3-1.0-SNAPSHOT/comprador.html";
                console.log("Es Customer");

            }

       })
})


document.addEventListener('click', e=>{
    if (e.target === $btnIniciarSesion || e.target === $btnRegistrase){
        $IniciarSesion.classList.toggle('active');
        $Registrarse.classList.toggle('active')
    }
})

