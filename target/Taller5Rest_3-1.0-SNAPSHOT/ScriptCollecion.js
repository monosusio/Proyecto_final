
/*var alerta1 = document.getElementById("crear");
alerta1.addEventListener("submit",function(e) {
    alert("¡Se ha creado el usuario!");

})*/

var formulario = document.getElementById("pi");
formulario.addEventListener("submit",function(e) {
    e.preventDefault();

    var data = {
        "co_id": document.getElementById("co_id").value,
        "password": document.getElementById("password").value,

    };


    document.addEventListener('click', e => {
        if (e.target === $btnIniciarSesion || e.target === $btnRegistrase) {
            $IniciarSesion.classList.toggle('active');
            $Registrarse.classList.toggle('active')
        }
    })
})