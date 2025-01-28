function validarFormulario(tipo = 0){

    let confirmarAccion = confirm("Estas seguro de que deseas proceder con esta accion?");
    if (!confirmarAccion) {
        return false; 
    }
	
    let formulario_valido = true;

    switch(tipo){
        case 1:
            if(document.forms["form_alta_cliente"]["sexo"].selectedIndex == 0){
                document.getElementById("error_sexo").textContent = " *Debe elegir un sexo*";
                formulario_valido = false;
            }else{
                document.getElementById("error_sexo").textContent = "";
            }
            if(document.forms["form_alta_cliente"]["provincia"].selectedIndex == 0){
                document.getElementById("error_provincia").textContent = " *Debe elegir una provincia*";
                formulario_valido = false;
            }else{
                document.getElementById("error_provincia").textContent = "";
            }
            if(document.forms["form_alta_cliente"]["localidad"].selectedIndex == 0){
                document.getElementById("error_localidad").textContent = " *Debe elegir una localidad*";
                formulario_valido = false;
            }else{
                document.getElementById("error_localidad").textContent = "";
            }
            if(document.forms["form_alta_cliente"]["contrasena"].value != document.forms["form_alta_cliente"]["reingreso_contrasena"].value) {
                document.getElementById("error_reingreso_contrasena").textContent = " *Las contrasenas deben coincidir*";
                formulario_valido = false;
            }else{
                document.getElementById("error_reingreso_contrasena").textContent = "";
            }

            let fechaNacimiento = document.getElementById("fecha_nacimiento_alta").value;
            if (fechaNacimiento) {
                let fechaActual = new Date();
                let anoNacimiento = new Date(fechaNacimiento).getFullYear();
                let anoMinimo = fechaActual.getFullYear() - 18;

                if (anoNacimiento < 1900) {
                    document.getElementById("error_ano").textContent = " *Debe ser una fecha logica*";
                    formulario_valido = false;
                } else if (anoNacimiento > anoMinimo) {
                    document.getElementById("error_ano").textContent = " *Debe ser mayor de 18 anos*";
                    formulario_valido = false;
                } else {
                    document.getElementById("error_ano").textContent = "";
                }
            } else {
                document.getElementById("error_ano").textContent = " *Debe ingresar una fecha de nacimiento*";
                formulario_valido = false;
            }

            break;

            
        case 2:
            if(document.forms["form_alta_cuenta"]["tipo_de_cuenta"].selectedIndex == 0){
                document.getElementById("error_tipo_cuenta").textContent = " *Debe elegir un tipo de cuenta*";
                formulario_valido = false;
            }else{
                document.getElementById("error_tipo_cuenta").textContent = "";
            }
            break;
            
        case 3:
            if(document.forms["form_pedido_prestamo"]["cuenta_prestamo"].selectedIndex == 0){
                document.getElementById("error_cuenta_prestamo").textContent = " *Debe elegir una cuenta*";
                formulario_valido = false;
            }else{
                document.getElementById("error_cuenta_prestamo").textContent = "";
            }
            if(document.forms["form_pedido_prestamo"]["plazo_pago"].selectedIndex == 0){
                document.getElementById("error_plazo_pago").textContent = " *Debe elegir la cantidad de cuotas*";
                formulario_valido = false;
            }else{
                document.getElementById("error_plazo_pago").textContent = "";
            }
            break;
        
        case 4:
            formulario_valido = true;

            // Validar selección de cuenta
            if (document.forms["form_pagar_preatamo"]["prestamo_cuenta"].selectedIndex == 0) {
                document.getElementById("error_prestamo_cuenta").textContent = " *Debe seleccionar una cuenta*";
                formulario_valido = false;
            } else {
                document.getElementById("error_prestamo_cuenta").textContent = "";
            }

            // Validar selección de préstamo
            if (document.forms["form_pagar_preatamo"]["prestamo_seleccion"].selectedIndex == 0) {
                document.getElementById("error_prestamo_seleccion").textContent = " *Debe seleccionar un préstamo*";
                formulario_valido = false;
            } else {
                document.getElementById("error_prestamo_seleccion").textContent = "";
            }

            // Validar selección de cuota
            if (document.forms["form_pagar_preatamo"]["prestamo_cuota"].selectedIndex == 0) {
                document.getElementById("error_prestamo_cuota").textContent = " *Debe seleccionar una cuota a pagar*";
                formulario_valido = false;
            } else {
                document.getElementById("error_prestamo_cuota").textContent = "";
            }
            
            if (!formulario_valido)
            	{
            	document.getElementById("mensaje").textContent = ""
            	}


            break;

        	
        case 5:
        	break;
        	
        case 6:
        	break;
        	
        case 7:
        	break;
        	

        	
        default:
            break;
    }
    
    
    return formulario_valido;
}


