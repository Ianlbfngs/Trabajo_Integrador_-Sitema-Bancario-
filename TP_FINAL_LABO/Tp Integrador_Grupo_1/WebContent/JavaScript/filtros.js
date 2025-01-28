function cargar_select_localidades(){
		let provinciaSeleccionada = document.getElementById("provincia_alta").value;
        let localidades = document.getElementById("localidad_alta").options;
    
        for (let i = 0; i < localidades.length; i++) {
   
            if (localidades[i].getAttribute("data-provincia") === provinciaSeleccionada) {
                localidades[i].hidden = false;  
            } else {
                localidades[i].hidden = true;   
            }
        }
    
        document.getElementById("localidad_alta").value = "0";
}




function activar_edicion(dniEditable) {
    // Verificar si el elemento checkbox existe
    let checkBox = document.getElementById(dniEditable + "_15_cb");
    if (checkBox) {
        checkBox.hidden = true; // Atributo HTML
        checkBox.checked = false;
    } else {
        console.error("Checkbox no encontrado:", dniEditable + "_15_cb");
    }

    let spanOriginal;
    let txtEditable;
    let inputHidenSpan;

    for (let i = 1; i < 15; i++) {
        txtEditable = document.getElementById(dniEditable + "_" + i + "_ID");
        spanOriginal = document.getElementById(dniEditable + "_" + i + "_datoOG");

        if (!txtEditable || !spanOriginal) {
            console.warn(`Elementos no encontrados: ${dniEditable}_${i}_ID o ${dniEditable}_${i}_datoOG`);
            continue;
        }

        if (i === 5 || i === 7 || i === 8) {
            inputHidenSpan = spanOriginal.querySelector('input[type="hidden"]');
            if (inputHidenSpan) {
                txtEditable.value = inputHidenSpan.value;
            }
            txtEditable.style.borderColor = "black";

            spanOriginal.classList.add("hidden");
            txtEditable.classList.remove("hidden");
        } else {
            txtEditable.value = spanOriginal.innerText;
            txtEditable.style.borderColor = "white";

            spanOriginal.hidden = true; 
            txtEditable.hidden = false; 
        }

        txtEditable.style.width = "140px";
        txtEditable.style.maxWidth = "140px";
    }

    let btnUpdate = document.getElementById(dniEditable + "_btn_Y");
    let btnCancelar = document.getElementById(dniEditable + "_btn_C");

    if (btnUpdate) btnUpdate.hidden = false; // Mostrar botón actualizar
    if (btnCancelar) btnCancelar.hidden = false; // Mostrar botón cancelar

    // Manejo del <select>
    let selectElement = document.getElementById(dniEditable + "_8_ID");
    if (!selectElement) {
        console.error("Select no encontrado:", dniEditable + "_8_ID");
        return;
    }

    selectElement.classList.remove("hidden"); // Mostrar el <select>

    let idProvinciaElement = document.getElementById(dniEditable + "_7_ID");
    let idProvincia = null;

    // Verifica si el elemento existe
    if (idProvinciaElement) {
        idProvincia = idProvinciaElement.value; // Obtiene el valor si existe
    } else {
        console.error("Elemento no encontrado:", dniEditable + "_7_ID");
    }
    let options = selectElement.options;

    if (idProvincia) {
        for (let i = 0; i < options.length; i++) {
            if (options[i].dataset.provincia === idProvincia) {
                options[i].classList.remove("hidden"); // Mostrar opciones relevantes
            } else {
                options[i].classList.add("hidden"); // Ocultar opciones irrelevantes
            }
        }
    } else {
        console.warn("ID de provincia no encontrado o no válido.");
    }
}


function desactivar_edicion(dniEditable) {
    // Mostrar el checkbox nuevamente
    let checkBox = document.getElementById(dniEditable + "_15_cb");
    if (checkBox) {
        checkBox.hidden = false;
    }

    let spanOriginal;
    let txtEditable;
    let btnUpdate;
    let btnCancelar;

    for (let i = 1; i < 15; i++) {
        // Obtener los elementos
        spanOriginal = document.getElementById(dniEditable + "_" + i + "_datoOG");
        txtEditable = document.getElementById(dniEditable + "_" + i + "_ID");

        if (spanOriginal && txtEditable) {
            // Manejar clases para TailwindCSS
            if (i === 5 || i === 7 || i === 8) {
                spanOriginal.classList.remove("hidden"); // Mostrar el span
                txtEditable.classList.add("hidden"); // Ocultar el campo editable
            } else {
                spanOriginal.hidden = false; // Usar atributo HTML como fallback
                txtEditable.hidden = true; // Usar atributo HTML como fallback
            }

            // Restaurar el valor original en el campo editable
            txtEditable.value = spanOriginal.innerText;
        }
    }

    // Ocultar botones de actualización y cancelación
    btnUpdate = document.getElementById(dniEditable + "_btn_Y");
    if (btnUpdate) {
        btnUpdate.hidden = true;
    }

    btnCancelar = document.getElementById(dniEditable + "_btn_C");
    if (btnCancelar) {
        btnCancelar.hidden = true;
    }
}


function cargar_DNI_Update(dni){
    document.getElementById("dni_Update_Form_Parametro").value=dni;
    	let estaTodoBien = true;
    	let txtCuil =  document.getElementById(dni+"_2_ID");
        let txtApellido =  document.getElementById(dni+"_3_ID");
        let txtNombre =  document.getElementById(dni+"_4_ID");
        let txtNacionalidad =  document.getElementById(dni+"_6_ID");
        let txtFecha = new Date(document.getElementById(dni+"_9_ID").value);
        let fechaActual = new Date();
        let txtDireccion = document.getElementById(dni+"_10_ID");
        let txtMail = document.getElementById(dni+"_11_ID");
        let txtTelefono = document.getElementById(dni+"_12_ID");
        let txtUsuario = document.getElementById(dni+"_13_ID");
        let txtContrasena = document.getElementById(dni+"_14_ID");
        //RG
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(11> txtCuil.value.length || txtCuil.value.length >11){
        	estaTodoBien = false;
            txtCuil.style="border-color: red; width: 90px;";
        }
        if(0>= txtApellido.value.length || txtApellido.value.length >39){
            estaTodoBien = false;
            txtApellido.style="border-color: red; width: 90px;";
        }
        if(0>= txtNombre.value.length || txtNombre.value.length >39){
            estaTodoBien = false;
            txtNombre.style="border-color: red; width: 90px;";
        }
        if(0>= txtNacionalidad.value.length || txtNacionalidad.value.length >39){
            estaTodoBien = false;
            txtNacionalidad.style="border-color: red; width: 90px;";
        }
        if(emailRegex.test(txtMail.value) == false){
            estaTodoBien = false;
            txtMail.style="border-color: red; width: 90px;";
        }
        if(0>= txtDireccion.value.length || txtDireccion.value.length >39){
            estaTodoBien = false;
            txtDireccion.style="border-color: red; width: 90px;";
        }
        if(0>= txtTelefono.value.length || txtTelefono.value.length >39){
            estaTodoBien = false;
            txtTelefono.style="border-color: red; width: 90px;";
        }
        if(0>= txtUsuario.value.length || txtUsuario.value.length >39){
            estaTodoBien = false;
            txtUsuario.style="border-color: red; width: 90px;";
        }
        if(0>= txtContrasena.value.length || txtContrasena.value.length >39){
            estaTodoBien = false;
            txtContrasena.style="border-color: red; width: 90px;";
        }
        if(1800 > txtFecha.getFullYear() || txtFecha.getFullYear()> fechaActual){
            txtFecha.style="border-color: red; width: 90px;";
        }
        if(estaTodoBien){
            document.forms["form_actualizacion_cliente"].submit();
        }
}


function carga_select_localidades_update(dniEditable) {
    let valueFinal = -1; // Inicializamos en -1 para manejar el caso en que no se encuentre la provincia
    let idProvincia = document.getElementById(dniEditable + "_7_ID").value;
    let options = document.getElementById(dniEditable + "_8_ID").options;

    // Iteramos sobre las opciones para mostrar u ocultar según la provincia
    for (let i = 0; i < options.length; i++) {
        if (options[i].dataset.provincia == idProvincia) {
            options[i].classList.remove("hidden"); // Mostramos la opción
            if (valueFinal === -1) {
                valueFinal = i; // Guardamos el índice de la primera opción coincidente
            }
        } else {
            options[i].classList.add("hidden"); // Ocultamos la opción
        }
    }

    // Si encontramos alguna opción válida, la seleccionamos
    if (valueFinal !== -1) {
        document.getElementById(dniEditable + "_8_ID").selectedIndex = valueFinal;
    }
}


//Listar y modificar cuentas, lo anterior es de carga de localidades y de clientes
function activar_edicion_cuentas(numCuentaEditable) {
    // Manejar el checkbox
    let checkBox = document.getElementById(numCuentaEditable + "_7_cb");
    if (checkBox) {
        checkBox.hidden = true;
        checkBox.checked = false;
    } else {
        console.error("Checkbox no encontrado:", numCuentaEditable + "_7_cb");
    }

    let spanOriginal;
    let txtEditable;
    let inputHiddenSpan;

    for (let i = 1; i < 7; i++) {
        txtEditable = document.getElementById(numCuentaEditable + "_" + i + "_ID");
        spanOriginal = document.getElementById(numCuentaEditable + "_" + i + "_datoOG");

        if (!txtEditable || !spanOriginal) {
            console.warn(`Elementos no encontrados: ${numCuentaEditable}_${i}_ID o ${numCuentaEditable}_${i}_datoOG`);
            continue;
        }

        if (i === 2) {
            // Manejar campos con un input hidden dentro del span
            inputHiddenSpan = spanOriginal.querySelector('input[type="hidden"]');
            if (inputHiddenSpan) {
                txtEditable.value = inputHiddenSpan.value;
            }
            txtEditable.style.borderColor = "black";

            // Usar Tailwind para ocultar el span y mostrar el campo editable
            spanOriginal.classList.add("hidden");
            txtEditable.classList.remove("hidden");
        } else {
            txtEditable.value = spanOriginal.innerText;
            txtEditable.style.borderColor = "white";

            spanOriginal.hidden = true; // Atributo HTML
            txtEditable.hidden = false; // Atributo HTML
        }

        // Ajustar estilos para el campo editable
        txtEditable.style.width = "180px";
        txtEditable.style.maxWidth = "180px";
    }

    // Manejar botones de actualización y cancelación
    let btnUpdate = document.getElementById(numCuentaEditable + "_btn_Y");
    let btnCancelar = document.getElementById(numCuentaEditable + "_btn_N");

    if (btnUpdate) btnUpdate.hidden = false; // Mostrar botón actualizar
    if (btnCancelar) btnCancelar.hidden = false; // Mostrar botón cancelar
}


function desactivar_edicion_cuenta(numCuentaEditable) {
    // Mostrar el checkbox nuevamente
    let checkBox = document.getElementById(numCuentaEditable + "_7_cb");
    if (checkBox) {
        checkBox.hidden = false; // Atributo HTML para mostrar el checkbox
    } else {
        console.error("Checkbox no encontrado:", numCuentaEditable + "_7_cb");
    }

    let spanOriginal;
    let txtEditable;
    let btnUpdate;
    let btnCancelar;

    for (let i = 1; i < 7; i++) {
        // Obtener los elementos
        spanOriginal = document.getElementById(numCuentaEditable + "_" + i + "_datoOG");
        txtEditable = document.getElementById(numCuentaEditable + "_" + i + "_ID");

        if (!spanOriginal || !txtEditable) {
            console.warn(`Elementos no encontrados: ${numCuentaEditable}_${i}_datoOG o ${numCuentaEditable}_${i}_ID`);
            continue;
        }

        // Manejar clases para TailwindCSS
        if (i === 2) {
            spanOriginal.classList.remove("hidden");
            txtEditable.classList.add("hidden");
        } else {
            spanOriginal.hidden = false;
            txtEditable.hidden = true;
        }

        txtEditable.value = spanOriginal.innerText;
    }

    btnUpdate = document.getElementById(numCuentaEditable + "_btn_Y");
    if (btnUpdate) {
        btnUpdate.hidden = true; 
    }

    btnCancelar = document.getElementById(numCuentaEditable + "_btn_N");
    if (btnCancelar) {
        btnCancelar.hidden = true; 
    }
}

function cargar_numCuenta_Update(numCuenta){
    document.getElementById("numCuenta_Update_Form_Parametro").value=numCuenta;
    	let estaTodoBien = true;
        let txtDNI =  document.getElementById(numCuenta+"_3_ID");

        let txtFecha = new Date(document.getElementById(numCuenta+"_4_ID").value);
        let fechaActual = new Date();
        let txtCBU =  document.getElementById(numCuenta+"_6_ID");

        if(8> txtDNI.value.length || txtDNI.value.length >8){
        	estaTodoBien = false;
            txtCuil.style="border-color: red; width: 90px;";
        }
        if(22> txtCBU.value.length || txtCBU.value.length >22){
        	estaTodoBien = false;
            txtCuil.style="border-color: red; width: 90px;";
        }
       
        if(1800 > txtFecha.getFullYear() || txtFecha.getFullYear()> fechaActual){
            estaTodoBien = false;
            txtFecha.style="border-color: red; width: 90px;";
        }
        
        if(estaTodoBien){
            document.forms["form_actualizacion_cuenta"].submit();
        }


}

//solicitar prestamo
function monto_pago_prestamo_dinamico(){
	   let cantidadCuotas = document.getElementById("plazo_pago_prestamo").value;
	   let importeSolicitado = document.getElementById("importe_soliciado_prestamo").value;
       let inputImporte = document.getElementById("Importe_Total");
       let inputValor = document.getElementById("Valor_Cuotas");


	   if(importeSolicitado >0 && cantidadCuotas != 0){
		   importeSolicitado = Number(importeSolicitado)+ (Number(importeSolicitado)*0.20)
		   inputValor.value = (Number(importeSolicitado)/Number(cantidadCuotas)).toFixed(2);
		   inputImporte.value = importeSolicitado;
	   }else if(importeSolicitado.length == 0){
        inputValor.value = (Number(importeSolicitado)/Number(cantidadCuotas)).toFixed(2);
        inputImporte.value = importeSolicitado;
	   }
	   
	   
	   

	}

function validarFormularioSolicitudPr(){
	let cuenta =document.forms["form_pedido_prestamo"]["cuenta_prestamo"] ;
	let meses = document.forms["form_pedido_prestamo"]["plazo_pago"];
	let monto = document.forms["form_pedido_prestamo"]["importe_solicitado"];
	let todoBien = true;
	if(cuenta.selectedIndex == 0){
		cuenta.style="border-color: red;";
		todoBien=false;
	}
	if(meses.selectedIndex == 0){
		meses.style="border-color: red;";
		todoBien=false;
	}
	if(monto.value <1 || monto.value == null ){
        meses.style="border-colo: red;";
        todoBien = false;
    }
	if(todoBien){document.forms["form_pedido_prestamo"].submit();}
}
//pagar prestamos
function filtrarCuotas() {
    const idPrestamo = document.getElementById("prestamo_seleccion_id").value;

    const opcionesCuotas = document.getElementById("prestamo_cuota_id").options;

    // Itera sobre las opciones y oculta/muestra según el ID del préstamo
    for (let i = 0; i < opcionesCuotas.length; i++) {
        const opcion = opcionesCuotas[i];
        if (opcion.value === "0" || opcion.value.startsWith(idPrestamo + "_")) {
            opcion.style.display = ""; // Muestra la opción
        } else {
            opcion.style.display = "none"; // Oculta la opción
        }
    }

    // Reinicia la selección del select de cuotas
    document.getElementById("prestamo_cuota_id").value = "0";
}




function informe_1_limpiar(){
	document.getElementById("informe_1_flag_id").value = 1; //pone la flag en modo borrar
	informe(); //llama a la funcion informe
	return;
}
//esto se repite para los tres limpiar, con sus cambios
function informe_2_limpiar(){
	document.getElementById("informe_2_flag_id").value = 1;
	informe();
	return;
}

function informe_3_limpiar(){
	document.getElementById("informe_3_flag_id").value = 1;
	informe();
	return;
}

function informe(){
	if(document.getElementById("informe_1_filtro_id")!= null){ //esto verifica si el filtro existe, por el if de los scriplets, si ya esta hecho el informe, no existe
		let filtro_inf_1 = document.getElementById("informe_1_filtro_id").value;
		if(filtro_inf_1 == 0){ //es basicamente para que si no hay seleccion pase por el servlet pero no haga nada
	        document.getElementById("informe_1_flag_id").value = 2;
	    }
	}
	if(document.getElementById("informe_2_filtro_fecha_id")!=null && document.getElementById("informe_2_filtro_dni_id") != null){
		let filtro_inf_2 = document.getElementById("informe_2_filtro_fecha_id").value;
		let filtro_inf_2_dni = document.getElementById("informe_2_filtro_dni_id").value;
		if(filtro_inf_2 == 0 || filtro_inf_2_dni.length <8){
			document.getElementById("informe_2_flag_id").value=2;
		}
	}
	
	if(document.getElementById("informe_3_filtro_id")!=null){
		let filtro_inf_3 = document.getElementById("informe_3_filtro_id").value;
		if(filtro_inf_3 == 0){
			document.getElementById("informe_3_flag_id").value=2;
		}
	}

	
	document.forms["form_informe"].submit();
	return;
}