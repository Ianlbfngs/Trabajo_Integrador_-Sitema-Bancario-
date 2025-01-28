<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Panel de Administración</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-900 text-gray-100 min-h-screen flex flex-col">
	<!-- Header -->
	<header
		class="bg-gray-800 text-gray-100 py-4 px-8 flex justify-between items-center">
		<h1 class="text-2xl font-bold">Panel de Administración</h1>

		<%
			String Nombre;

			if (request.getAttribute("usuario") != null) {

				Nombre = (String) request.getAttribute("usuario");
			} else {
				Nombre = "";
			}
		%>

		<h2>
			Bienvenido/a
			<%=Nombre%></h2>
		<a href="ServletControladores?Pagina=login"
			class="text-red-400 hover:text-red-500 font-medium"> Cerrar
			sesión </a>
	</header>

	<!-- Main Content -->
	<main
		class="flex-1 flex flex-col md:flex-row gap-8 justify-center items-center px-6 py-8">
	<!-- Gestión de Clientes -->
	<div class="bg-gray-800 w-full md:w-1/3 p-6 rounded-lg shadow-lg">
		<h2 class="text-center text-xl font-semibold text-gray-100 mb-6">Gestión
			de Clientes</h2>
		<ul class="space-y-4">
			<li><a href="ServletControladores?Pagina=altaCliente"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Alta de Clientes </a></li>
			<li><a href="ServletControladores?Pagina=bajaCliente"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Baja de Clientes </a></li>
			<li><a href="ServletControladores?Pagina=listarClientes"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Lista de Clientes </a></li>
			<li><a href="ServletControladores?Pagina=autorizarPrestamo"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Autorización de Préstamos </a></li>
		</ul>
	</div>

	<!-- Gestión de Cuentas -->
	<div class="bg-gray-800 w-full md:w-1/3 p-6 rounded-lg shadow-lg">
		<h2 class="text-center text-xl font-semibold text-gray-100 mb-6">Gestión
			de Cuentas</h2>
		<ul class="space-y-4">
			<li><a href="ServletControladores?Pagina=altaCuenta"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Alta de Cuentas </a></li>
			<li><a href="ServletControladores?Pagina=bajaCuenta"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Baja de Cuentas </a></li>
			<li><a href="ServletControladores?Pagina=listarCuentas"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Lista de Cuentas </a></li>
			<li><a href="ServletControladores?Pagina=informesAdmin"
				class="block bg-gray-700 text-center py-3 rounded-md hover:bg-gray-600">
					Informes y Reportes </a></li>
		</ul>
	</div>
	</main>

	<!-- Footer -->
	<footer class="bg-gray-800 text-center py-4 text-sm text-gray-400">
		© 2024 Panel de Administración. Todos los derechos reservados. </footer>
</body>
</html>
