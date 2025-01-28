<%@page import="entidad.Localidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ListIterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Sexo"%>
<%@page import="entidad.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<script src="JavaScript/validaciones.js" type="text/JavaScript"></script>
<script src="JavaScript/filtros.js" type="text/JavaScript"></script>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Alta de Cliente</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="text-gray-100 min-h-screen flex">

	<!-- Sidebar -->
	<aside class="w-64 bg-gray-800 text-white min-h-screen flex flex-col">
	<!-- Logo y encabezado -->
	<div class="p-4 bg-gray-900 flex flex-col items-center justify-center">
		<h1 class="text-lg font-bold">Banco XYZ</h1>
		<%
			String Nombre;

			if (request.getAttribute("usuario") != null) {

				Nombre = (String) request.getAttribute("usuario");
			} else {
				Nombre = "";
			}
		%>

		<h2>
			<%=Nombre%></h2>
	</div>
	<!-- Navegación --> <nav class="flex-1 mt-4">
	<ul class="space-y-2">
		<li><a href="ServletControladores?Pagina=menuAdmin"
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
				<svg class="w-5 h-5 mr-3 text-gray-400"
					xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
					stroke="currentColor"> <path stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2"
					d="M3 3h18M3 9h18M3 15h18M3 21h18" /> </svg> Menú Principal
		</a></li>
		<li><a
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">

				</svg> Cliente
		</a></li>
		<li><a href="ServletControladores?Pagina=altaCliente"
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
				<svg class="w-5 h-5 mr-3 text-gray-400"
					xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
					stroke="currentColor"> <path stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
				</svg> Alta de Cliente
		</a></li>
		<li><a href="ServletControladores?Pagina=bajaCliente"
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
				<svg class="w-5 h-5 mr-3 text-gray-400"
					xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
					stroke="currentColor"> <path stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
				</svg> Baja de Cliente
		</a></li>
		<li>
		<li><a href="ServletControladores?Pagina=listarClientes"
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
				<svg class="w-5 h-5 mr-3 text-gray-400"
					xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
					stroke="currentColor"> <path stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
				</svg> Lista de Cliente
		</a></li>
		<li>
		<li><a href="ServletControladores?Pagina=autorizarPrestamo"
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
				<svg class="w-5 h-5 mr-3 text-gray-400"
					xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
					stroke="currentColor"> <path stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
				</svg> Autorizacion de Prestamos
		</a></li>
		<a
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">

			</svg> Cuenta
		</a>
		</li>
		<li><a href="ServletControladores?Pagina=altaCuenta"
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
				<svg class="w-5 h-5 mr-3 text-gray-400"
					xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
					stroke="currentColor"> <path stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
				</svg> Alta de Cuenta
		</a></li>

		<li><a href="ServletControladores?Pagina=bajaCuenta"
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
				<svg class="w-5 h-5 mr-3 text-gray-400"
					xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
					stroke="currentColor"> <path stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
				</svg> Baja de Cuenta
		</a></li>
		<li><a href="ServletControladores?Pagina=listarCuentas"
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
				<svg class="w-5 h-5 mr-3 text-gray-400"
					xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
					stroke="currentColor"> <path stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
				</svg> Lista de Cuenta
		</a></li>
		<li><a href="ServletControladores?Pagina=informesAdmin"
			class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
				<svg class="w-5 h-5 mr-3 text-gray-400"
					xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
					stroke="currentColor"> <path stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
				</svg> Informes y Reportes
		</a></li>

	</ul>
	</nav> <!-- Footer del Sidebar -->
	<div class="bg-gray-900 p-4">
		<p class="text-xs text-gray-400 text-center">&copy; 2024 Banco XYZ</p>
	</div>
	</aside>

	<!-- Contenido principal -->
	<div class="flex-1 p-6">
		<header class="bg-white p-6 rounded-md shadow-md">
		<h1 class="text-2xl  text-black font-semibold mb-4">Editar de
			Cliente</h1>
		</header>

		<!-- Formulario -->
		<main class="mt-6 bg-white text-gray-800 p-6 rounded-md shadow-md">
		<%
			Cliente cliente = (Cliente) request.getAttribute("ClienteEditar");
		%> <!-- Mensaje --> <%
 	String mensaje;
 	String color = "text-gray-700";
 	if (request.getAttribute("mensaje") != null) {
 		mensaje = "*" + (String) request.getAttribute("mensaje") + "*";
 		if (mensaje.equals("*Cliente actualizado correctamente*")) {
 			color = "text-green-600";
 		} else {
 			color = "text-red-600";
 		}
 	} else {
 		mensaje = "";
 	}
 %>
		<p class="<%=color%> text-center mb-4 font-semibold"><%=mensaje%></p>
		<p class="<%=color%> text-center mb-4 font-semibold"></p>

		<form name="form_alta_cliente" action="ServletControladores"
			onsubmit="return validarFormulario(1)" method="post">
			<div class="grid grid-cols-1 md:grid-cols-2 gap-4">
				<!-- DNI -->
				<div>
					<label for="dni" class="block mb-2 font-medium text-gray-700">DNI:</label>
					<input type="text" id="dni_alta" name="dniUpdate"
						onkeypress="return (event.charCode >= 48 && event.charCode <= 57)"
						minlength="8" maxlength="8" readonly pattern="^[0-9]{8}$"
						title="Ingrese exactamente 8 números"
						value="<%=cliente != null ? cliente.getDni_cli() : ""%>"
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
				</div>

				<!-- Sexo -->
				<div>
					<label for="sexo" class="block mb-2 font-medium text-gray-700">Sexo:</label>
					<select id="sexo_alta" name="sexo" required
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
						<option value="" disabled selected>-- Seleccione un sexo
							--</option>
						<%
							List<Sexo> listaSexos = null;
							if (request.getAttribute("listaS") != null) {
								listaSexos = (List<Sexo>) request.getAttribute("listaS");
								for (Sexo sexo : listaSexos) {
						%>
						<option value="<%=sexo.getIDSexo()%>"
							<%=(sexo.getIDSexo() == cliente.getIdGenero_cli()) ? "selected" : ""%>>

							<%=sexo.getDescripcion()%>
						</option>
						<%
							}
							}
						%>


					</select> <span id="error_sexo" class="text-red-500 text-sm"></span>
				</div>

				<!-- Provincia -->
				<div>
					<label for="provincia" class="block mb-2 font-medium text-gray-700">Provincia:</label>
					<select id="provincia_alta" name="provincia"
						onchange="javascript:cargar_select_localidades();" required
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
						<option value="" disabled selected>-- Seleccione una
							provincia --</option>
						<%
							List<Provincia> listaProvincias = (List<Provincia>) request.getAttribute("listaP");
							if (listaProvincias != null && !listaProvincias.isEmpty()) {
								for (Provincia provincia : listaProvincias) {
						%>
						<option value="<%=provincia.getIdProvincia()%>"
							<%=(provincia.getIdProvincia() == cliente.getIdProvincia_loc_cli()) ? "selected" : ""%>><%=provincia.getDescripcion()%></option>
						<%
							}
							}
						%>
					</select> <span id="error_provincia" class="text-red-500 text-sm"></span>
				</div>

				<!-- Localidad -->
				<div>
					<label for="localidad" class="block mb-2 font-medium text-gray-700">Localidad:</label>
					<span id="error_localidad" class="text-red-500 text-sm"></span> <select
						id="localidad_alta" name="localidad" required
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
						<option value="" disabled selected>-- Seleccione una
							localidad --</option>
						<%
							List<Localidad> listaLocalidades = null;
							if (request.getAttribute("listaL") != null) {
								listaLocalidades = (List<Localidad>) request.getAttribute("listaL");
								ListIterator<Localidad> it = listaLocalidades.listIterator();
								while (it.hasNext()) {
									Localidad localidad = it.next();
									it.remove();
						%>
						<option value="<%=localidad.getIdLocalidad_Loc()%>"
							<%=(localidad.getIdLocalidad_Loc() == cliente.getIdLocalidad_cli()) ? "selected" : ""%>
							data-provincia="<%=localidad.getIdProvincia_Loc()%>" hidden><%=localidad.getDescripcion_Loc()%></option>
						<%
							}
							}
						%>
					</select>
				</div>

				<!-- Nacionalidad -->
				<div>
					<label class="block mb-2 font-medium text-gray-700"
						for="nacionalidad">Nacionalidad:</label> <select
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
						id="nacionalidad_alta" name="nacionalidad" required>
						<option value="" disabled selected>Seleccione su
							nacionalidad</option>
						<option value="Argentina"
							<%="argentina".equals(cliente.getNacionalidad_cli().toLowerCase()) ? "selected" : ""%>>Argentina</option>
						<option value="Boliviana"
							<%="boliviana".equals(cliente.getNacionalidad_cli().toLowerCase()) ? "selected" : ""%>>Boliviana</option>
						<option value="Brasilena"
							<%="brasilena".equals(cliente.getNacionalidad_cli().toLowerCase()) ? "selected" : ""%>>Brasileña</option>
						<option value="Chilena"
							<%="chilena".equals(cliente.getNacionalidad_cli().toLowerCase()) ? "selected" : ""%>>Chilena</option>
						<option value="Colombiana"
							<%="colombiana".equals(cliente.getNacionalidad_cli().toLowerCase()) ? "selected" : ""%>>Colombiana</option>
						<option value="Paraguaya"
							<%="paraguaya".equals(cliente.getNacionalidad_cli().toLowerCase()) ? "selected" : ""%>>Paraguaya</option>
						<option value="Peruana"
							<%="peruana".equals(cliente.getNacionalidad_cli().toLowerCase()) ? "selected" : ""%>>Peruana</option>
						<option value="Uruguaya"
							<%="uruguaya".equals(cliente.getNacionalidad_cli().toLowerCase()) ? "selected" : ""%>>Uruguaya</option>
						<option value="Venezolana"
							<%="venezolana".equals(cliente.getNacionalidad_cli().toLowerCase()) ? "selected" : ""%>>Venezolana</option>
						<option value="Otra"
							<%="otra".equals(cliente.getNacionalidad_cli().toLowerCase()) ? "selected" : ""%>>Otra</option>
					</select>
				</div>

				<!-- CUIL -->
				<div>
					<label for="cuil" class="block mb-2 font-medium text-gray-700">CUIL:</label>
					<input type="text" id="cuil_alta" name="cuil"
						onkeypress="return (event.charCode >= 48 && event.charCode <= 57)"
						minlength="11" maxlength="11" required pattern="^[0-9]{11}$"
						title="Ingrese exactamente 11 números"
						value="<%=cliente != null ? cliente.getCuil_cli() : ""%>"
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
				</div>

				<div class="flex flex-col mb-4">
					<label for="Nombre" class=" block mb-2 font-medium text-gray-700">Nombre:</label>
					<input type="text" id="Nombre_alta" name="Nombre"
						onkeypress="return (event.charCode >= 65 && event.charCode <= 90) || (event.charCode >= 97 && event.charCode <= 122) || event.charCode == 32"
						value="<%=cliente != null ? cliente.getNombre_cli() : ""%>"
						maxlength="40" pattern="[A-Za-z\s]*" required
						title="Ingrese solo letras"
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
				</div>
				<div class="flex flex-col mb-4">
					<label for="apellido" class=" block mb-2 font-medium text-gray-700">Apellido:</label>
					<input type="text" id="apellido_alta" name="apellido"
						onkeypress="return (event.charCode >= 65 && event.charCode <= 90) || (event.charCode >= 97 && event.charCode <= 122) || event.charCode == 32"
						value="<%=cliente != null ? cliente.getApellido_cli() : ""%>"
						maxlength="40" pattern="[A-Za-z\s]*" required
						title="Ingrese solo letras"
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
				</div>

				<div class="flex flex-col mb-4">
					<label for="direccion" class="block mb-2 font-medium text-gray-700">Dirección:</label>
					<input type="text" id="direccion_alta" name="direccion"
						value="<%=cliente != null ? cliente.getDireccion_cli() : ""%>"
						maxlength="40" required
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
				</div>

				<div class="flex flex-col mb-4">
					<label for="correo" class="block mb-2 font-medium text-gray-700">Correo:</label>
					<input type="email" id="correo_alta" name="correo" maxlength="40"
						value="<%=cliente != null ? cliente.getCorreo_cli() : ""%>"
						required
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
				</div>

				<div class="flex flex-col mb-4">
					<label for="telefono" class="block mb-2 font-medium text-gray-700">Teléfono:</label>
					<input type="text" id="telefono_alta" name="telefono"
						minlength="10" maxlength="13"
						onkeypress="return (event.charCode >= 48 && event.charCode <= 57)"
						value="<%=cliente != null ? cliente.getTelefono_cli() : ""%>"
						required pattern="^[0-9]+$" title="Ingrese entre 10 y 13 números"
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
				</div>

				<div class="flex flex-col mb-4">
					<label for="usuario" class="block mb-2 font-medium text-gray-700">Usuario:</label>
					<input type="text" id="usuario_alta" name="usuario" maxlength="40"
						value="<%=cliente != null ? cliente.getUsuario_cli() : ""%>"
						readonly
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
				</div>

				<div class="flex flex-col mb-4">
					<label for="contrasena"
						class="block mb-2 font-medium text-gray-700">Contraseña:</label> <input
						type="password" id="contrasena_alta" name="contrasena"
						maxlength="40"
						value="<%=cliente != null ? cliente.getContrasena_cli() : ""%>"
						required
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
				</div>

				<div class="form-alta">
					<label class="block mb-2 font-medium text-gray-700"
						for="fecha_nacimiento">Fecha de Nacimiento: <span
						id="error_ano" style="color: red; font-size: medium;"></span></label> <input
						class="w-full p-2 bg-gray-100 text-gray-800 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
						type="date" id="fecha_nacimiento_alta" name="fecha_nacimiento"
						value="<%=cliente != null ? cliente.getFechaNacimiento_cli() : ""%>"
						required>
				</div>
			</div>
			<!-- Botón de Enviar -->
			<div class="text-center mt-6">
				<button type="submit" name="btnModficiarCliente"
					class="bg-blue-500 hover:bg-blue-600 text-white font-semibold px-4 py-2 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400">
					Editar Cliente</button>

				<a href="ServletControladores?Pagina=listarClientes" type="submit"
					name="btnSalirMOdificar"
					class="bg-blue-500 hover:bg-blue-600 text-white font-semibold px-4 py-2 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400">
					Salir </a>
			</div>
		</form>
		</main>

	</div>
</body>
</html>

