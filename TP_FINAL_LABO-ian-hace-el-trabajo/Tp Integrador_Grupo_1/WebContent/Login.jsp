<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login de Usuario</title>

<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">

<!-- Tus estilos adicionales -->
<link rel="stylesheet" type="text/css" href="CSS/Estilos9.css">
</head>
<body
	class="bg-gradient-to-br from-gray-900 via-gray-800 to-blue-900 flex items-center justify-center min-h-screen">

	<div class="bg-white max-w-xl w-full p-12 rounded-lg shadow-2xl">
		<!-- Encabezado -->
		<header class="text-center mb-8">
		<h2
			class="text-4xl font-bold text-blue-700 border-b-2 border-blue-300 inline-block pb-2">
			Sistema Bancario</h2>
		</header>

		<!-- Formulario de login -->
		<main class="login-container">

		<h1 class="text-3xl font-semibold text-gray-800 mb-8 text-center">Iniciar
			Sesión</h1>
		<form action="ServletControladores" method="POST" class="space-y-6">

			<!-- Mensaje -->
			<%
				String mensaje;
				String color = "text-gray-700";
				if (request.getAttribute("mensaje") != null) {
					mensaje = "*" + (String) request.getAttribute("mensaje") + "*";
					if (mensaje.equals("*Cliente registrado exitosamente*")) {
						color = "text-green-600";
					} else {
						color = "text-red-600";
					}
				} else {
					mensaje = "";
				}
			%>
			<p class="<%=color%> text-center mb-4 font-semibold"><%=mensaje%></p>


			<!-- Usuario -->
			<div class="form-group">
				<label for="usuario" class="block text-lg font-medium text-gray-700">Usuario:</label>
				<input type="text" id="usuario" name="usuario" maxlength="40"
					required
					class="block w-full px-5 py-3 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500">
			</div>
			<div class="form-group">
				<label for="password"
					class="block text-lg font-medium text-gray-700">Contraseña:</label>
				<input type="password" id="password" name="password" maxlength="40"
					required
					class="block w-full px-5 py-3 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500">
			</div>
			<div class="form-group text-center">
				<button type="submit" value="Ingresar" name="btnLogin"
					class="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 px-6 rounded-md shadow-md transition-all text-lg">
					Ingresar</button>
			</div>
		</form>
		</main>
	</div>

</body>
</html>