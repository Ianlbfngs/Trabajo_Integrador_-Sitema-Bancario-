<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@page import="entidad.Cliente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Información del Usuario</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-900 text-gray-100 flex">
	<!-- Sidebar -->
	<aside class="w-64 bg-gray-800 min-h-screen px-6 py-8">
	<h1 class="text-2xl font-bold text-center mb-8">Banco XYZ</h1>
	<%
		String Nombre;

		if (request.getAttribute("nombre") != null) {

			Nombre = (String) request.getAttribute("nombre");
		} else {
			Nombre = "";
		}
	%>

	<h2
		class="flex justify-center text-xl font-bold text-center mb-8	 text-center">
		<%=Nombre%></h2>
	<nav>
	<ul class="space-y-4">
		<li><a href="ServletControladores?Pagina=menuCliente"
			class="block px-4 py-2 rounded-md hover:bg-gray-700">Menú
				Principal</a></li>
		<li><a href="ServletControladores?Pagina=verMovimientos"
			class="block px-4 py-2 rounded-md hover:bg-gray-700">Movimientos</a></li>
		<li><a href="ServletControladores?Pagina=transferencias"
			class="block px-4 py-2 rounded-md hover:bg-gray-700">Transferencias</a></li>
		<li><a href="ServletControladores?Pagina=pedidoPrestamo"
			class="block px-4 py-2 rounded-md hover:bg-gray-700">Pedidos de
				Préstamos</a></li>
		<li><a href="ServletControladores?Pagina=pagarPrestamo"
			class="block px-4 py-2 rounded-md hover:bg-gray-700">Pago de
				Préstamos</a></li>
		<li><a href="ServletControladores?Pagina=revisarInfoPersonal"
			class="block px-4 py-2 rounded-md hover:bg-gray-700">Mi
				Información</a></li>
		<li><a href="ServletControladores?Pagina=login"
			class="block px-4 py-2 text-red-400 hover:text-red-500">Cerrar
				Sesión</a></li>
	</ul>
	</nav> </aside>


	<!-- Main Content -->
	<main class="flex-1 p-8"> <header class="mb-8">
	<h2 class="text-3xl font-semibold">Información del Usuario</h2>
	<p class="text-gray-400">Revise los datos de su cuenta.</p>
	</header> <%
 	Cliente cliente = (Cliente) request.getAttribute("cliente");
 %> <section class="bg-gray-800 p-6 rounded-md shadow-md">
	<div class="flex items-center space-x-6 mb-8">
		<div
			class="bg-gray-700 rounded-full h-20 w-20 flex items-center justify-center">
			<span class="text-2xl font-bold"><%=cliente != null ? cliente.getNombre_cli().charAt(0) : "U"%></span>
		</div>
		<div>
			<h3 class="text-2xl font-semibold">
				<%=cliente != null ? cliente.getNombre_cli() + " " + cliente.getApellido_cli()
					: "-- Nombre Usuario --"%>
			</h3>
			<p class="text-gray-400">
				DNI:
				<%=cliente != null ? cliente.getDni_cli() : "No disponible"%>
			</p>
		</div>
	</div>

	<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
		<div>
			<label class="block text-sm text-gray-400">Sexo:</label> <input
				type="text"
				class="w-full bg-gray-700 text-gray-100 px-4 py-2 rounded-md"
				value="<%=cliente != null ? cliente.getDescripcionGenero_cli() : ""%>"
				readonly>
		</div>
		<div>
			<label class="block text-sm text-gray-400">Provincia:</label> <input
				type="text"
				class="w-full bg-gray-700 text-gray-100 px-4 py-2 rounded-md"
				value="<%=cliente != null ? cliente.getDescProvincia_loc_cli() : ""%>"
				readonly>
		</div>
		<div>
			<label class="block text-sm text-gray-400">Localidad:</label> <input
				type="text"
				class="w-full bg-gray-700 text-gray-100 px-4 py-2 rounded-md"
				value="<%=cliente != null ? cliente.getDescLoc_cli() : ""%>"
				readonly>
		</div>
		<div>
			<label class="block text-sm text-gray-400">CUIL:</label> <input
				type="text"
				class="w-full bg-gray-700 text-gray-100 px-4 py-2 rounded-md"
				value="<%=cliente != null ? cliente.getCuil_cli() : ""%>" readonly>
		</div>
		<div>
			<label class="block text-sm text-gray-400">Fecha de
				Nacimiento:</label> <input type="date"
				class="w-full bg-gray-700 text-gray-100 px-4 py-2 rounded-md"
				value="<%=cliente != null ? cliente.getFechaNacimiento_cli() : ""%>"
				readonly>
		</div>
		<div>
			<label class="block text-sm text-gray-400">Dirección:</label> <input
				type="text"
				class="w-full bg-gray-700 text-gray-100 px-4 py-2 rounded-md"
				value="<%=cliente != null ? cliente.getDireccion_cli() : ""%>"
				readonly>
		</div>
		<div>
			<label class="block text-sm text-gray-400">Correo:</label> <input
				type="email"
				class="w-full bg-gray-700 text-gray-100 px-4 py-2 rounded-md"
				value="<%=cliente != null ? cliente.getCorreo_cli() : ""%>"
				readonly>
		</div>
		<div>
			<label class="block text-sm text-gray-400">Teléfono:</label> <input
				type="text"
				class="w-full bg-gray-700 text-gray-100 px-4 py-2 rounded-md"
				value="<%=cliente != null ? cliente.getTelefono_cli() : ""%>"
				readonly>
		</div>
		<div>
			<label class="block text-sm text-gray-400">Usuario:</label> <input
				type="text"
				class="w-full bg-gray-700 text-gray-100 px-4 py-2 rounded-md"
				value="<%=cliente != null ? cliente.getUsuario_cli() : ""%>"
				readonly>
		</div>
		<div>
			<label class="block text-sm text-gray-400">Contraseña:</label> <input
				type="password"
				class="w-full bg-gray-700 text-gray-100 px-4 py-2 rounded-md"
				value="<%=cliente != null ? cliente.getContrasena_cli() : ""%>"
				readonly>
		</div>
	</div>
	</section> </main>
</body>
</html>
