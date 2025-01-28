<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Panel de Cliente</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-900 text-gray-100 min-h-screen flex flex-col">
	<!-- Header -->
	<header
		class="bg-gray-800 text-gray-100 py-4 px-8 flex justify-between items-center">
	<h1 class="text-2xl font-bold">Panel de Cliente</h1>

	<%
		String Nombre;

		if (request.getAttribute("nombre") != null) {

			Nombre = (String) request.getAttribute("nombre");
		} else {
			Nombre = "";
		}
	%>

	<h2>
		Bienvenido/a
		<%=Nombre%>
	</h2>

	<a href="ServletControladores?Pagina=login"
		class="text-red-400 hover:text-red-500 font-medium"> Cerrar sesión
	</a> </header>

	<!-- Main Content -->
	<main
		class="flex-1 flex flex-col md:flex-row gap-8 justify-center items-center px-6 py-8">
	<!-- Gestión de Cuentas -->
	<div class="bg-gray-800 w-full md:w-1/3 p-6 rounded-lg shadow-lg">
		<h2 class="text-center text-2xl font-semibold text-gray-100 mb-6">Gestión
			de Cuentas</h2>
		<ul class=" text-xl space-y-4">
			<li><a href="ServletControladores?Pagina=verMovimientos"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Ver Movimientos </a></li>
			<li><a href="ServletControladores?Pagina=transferencias"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Transferencias </a></li>
		</ul>
	</div>

	<!-- Gestión de Préstamos -->
	<div class="bg-gray-800 w-full md:w-1/3 p-6 rounded-lg shadow-lg">
		<h2 class="text-center text-2xl font-semibold text-gray-100 mb-6">Gestión
			de Préstamos</h2>
		<ul class=" text-xl space-y-4">
			<li><a href="ServletControladores?Pagina=pedidoPrestamo"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Pedido de Préstamo </a></li>
			<li><a href="ServletControladores?Pagina=pagarPrestamo"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Pago de Préstamos </a></li>
		</ul>
	</div>

	<!-- Información Personal -->
	<div class="bg-gray-800 w-full md:w-1/3 p-6 rounded-lg shadow-lg">
		<h2 class="text-center text-2xl font-semibold text-gray-100 mb-6">Mi
			Información</h2>
		<ul class=" text-xl space-y-4">
			<li><a href="ServletControladores?Pagina=revisarInfoPersonal"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Revisar Información Personal </a></li>
		</ul>
	</div>
	</main>

	<!-- Footer -->
	<footer class="bg-gray-800 text-center py-4 text-sm text-gray-400">
	© 2024 Banco XYZ. Todos los derechos reservados. </footer>
</body>
</html>