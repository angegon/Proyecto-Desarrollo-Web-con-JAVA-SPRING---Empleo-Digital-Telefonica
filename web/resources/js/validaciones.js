
var dni;
var longitud;
var email;
var us;
var pass;

function validarRegistroUsuario() {

/////Validación Dni
    dni = document.querySelector('[id*="Dni"]').value;
    longitud = dni.length;
    expresion_regular_dni = /^\d{8}[a-zA-Z]$/;
    var numero;
    var letr;
    var letra;
    if (longitud < 1 || longitud > 9) {
        alert("El campo dni: " + dni + " tiene que tener entre 1 y 9 caracteres");
        document.querySelector('[id*="Dni"]').value = "";
    } else {
        if (expresion_regular_dni.test(dni) == true) {
            numero = dni.substr(0, dni.length - 1);
            letr = dni.substr(dni.length - 1, 1);
            numero = numero % 23;
            letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
            letra = letra.substring(numero, numero + 1);
            if (letra != letr.toUpperCase()) {
                alert('Dni ' + dni + ' erroneo, la letra del NIF no se corresponde.');
                document.querySelector('[id*="Dni"]').value = "";
            } else {
                //alert('Dni correcto');
            }
        } else {
            alert('Dni ' + dni + ' erroneo, formato no valido');
            document.querySelector('[id*="Dni"]').value = "";
        }
    }

////Validación Email
    emailRegex = /^(?:[^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*|"[^\n"]+")@(?:[^<>()[\].,;:\s@"]+\.)+[^<>()[\]\.,;:\s@"]{2,63}$/i;
    email = document.querySelector('[id*="Email"]').value;

    if (!emailRegex.test(email)) {
        alert("El email " + email + " introducido no es valido.");
        document.querySelector('[id*="Email"]').value = "";
    }

}

function validarNuevoCurso(){
    var idCurso = document.querySelector('[id*="idCurso"]').value;
    if (idCurso.length < 1 || idCurso.length > 10) { 
        alert("El Id del curso " + idCurso + " tiene que tener entre 1 y 10 caracteres");
        document.querySelector('[id*="idCurso"]').value = "";    
    }   
}

function validarLogin(){
    us = document.querySelector('[id*="us"]').value;
    pass = document.querySelector('[id*="pass"]').value;
    
    expresion_regular_dni = /^\d{8}[a-zA-Z]$/;
    document.getElementById("labelError").innerHTML = "";
    var numero;
    var letr;
    var letra;    
    if (us.length < 1 || us.length > 9) {
        //alert("El campo usuario(dni): " + us + " tiene que tener entre 1 y 9 caracteres");
        document.getElementById("labelError").innerHTML ="El campo usuario(dni): " + us + " tiene que tener entre 1 y 9 caracteres";
        document.querySelector('[id*="us"]').value = "";
    } else {
        if (expresion_regular_dni.test(us) == true) {
            numero = us.substr(0, us.length - 1);
            letr = us.substr(us.length - 1, 1);
            numero = numero % 23;
            letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
            letra = letra.substring(numero, numero + 1);
            if (letra != letr.toUpperCase()) {               
                document.getElementById("labelError").innerHTML ='Usuario ' + us + ' erroneo, la letra del NIF no se corresponde.';
                document.querySelector('[id*="us"]').value = "";
                return false;
            } else {
                //alert('Dni correcto');
            }
        } else {            
            document.querySelector('[id*="us"]').value = "";
            document.getElementById("labelError").innerHTML ='Usuario ' + us + ' erroneo, el dni del usuario no ha sido reconocido';
            return false;
        }
        
        
    }
    if (pass.length==0){
        document.getElementById("labelError").innerHTML +=" -&nbsp; La contrase\u00f1a esta en blanco!";
        return false;
    }
}