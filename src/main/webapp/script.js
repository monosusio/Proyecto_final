const $btnIniciarSesion= document.querySelector('.Singboton'),
    $btnRegistrase = document.querySelector('.Regboton'),
    $Registrarse = document.querySelector('.Registrarse'),
    $IniciarSesion = document.querySelector('.IniciarSesion');


/*var alerta1 = document.getElementById("crear");
alerta1.addEventListener("submit",function(e) {
    alert("¡Se ha creado el usuario!");

})*/

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


            console.log("cambipo nuevo en javascript");
            console.log("Si sirve esta piroba mrda")

            console.log(dato)

            console.log("Este es el username" + dato["username"]);
            console.log("Este es el email" + dato["email"]);

            console.log("Este es el rol que deberia coger el IF:  " + dato["role"])

            if(dato["role"] == "Artist"){
                window.location.href = "http://localhost:8080/Taller5Rest_3-1.0-SNAPSHOT/artista.html";
                console.log("Es Artist");
            }else if(dato["role"] === "Customer"){
                window.location.href = "http://localhost:8080/Taller5Rest_3-1.0-SNAPSHOT/usuario.html";
                console.log("Es Customer");

            }

       })
    //(Response.status == 200){  window.location.href="./index.html"; }
})


document.addEventListener('click', e=>{
    if (e.target === $btnIniciarSesion || e.target === $btnRegistrase){
        $IniciarSesion.classList.toggle('active');
        $Registrarse.classList.toggle('active')
    }
})

