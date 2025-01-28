<%@page import="java.util.List"%>
<%@page import="java.util.ListIterator"%>
<%@page import="entidad.TipoDeCuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Alta de Cuenta</title>
<script src="JavaScript/validaciones.js" type="text/javascript"></script>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
</head>
<body class="bg-white text-gray-800">

	<div class="flex min-h-screen">
		<!-- Sidebar -->
		<aside class="w-64 bg-gray-800 text-white min-h-screen flex flex-col">
			<!-- Logo y encabezado -->
			<div
				class="p-4 bg-gray-900 flex flex-col items-center justify-center">
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
			<!-- Navegación -->
			<nav class="flex-1 mt-4">
				<ul class="space-y-2">
					<li><a href="ServletControladores?Pagina=menuAdmin"
						class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
							<svg class="w-5 h-5 mr-3 text-gray-400"
								xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
								fill="none" stroke="currentColor">
                            <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M3 3h18M3 9h18M3 15h18M3 21h18" />
                        </svg> Menú Principal
					</a></li>
					<li><a
						class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">

							</svg> Cliente
					</a></li>
					<li><a href="ServletControladores?Pagina=altaCliente"
						class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
							<svg class="w-5 h-5 mr-3 text-gray-400"
								xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M5 12h14M12 5l7 7-7 7" />
                        </svg> Alta de Cliente
					</a></li>
					<li><a href="ServletControladores?Pagina=bajaCliente"
						class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
							<svg class="w-5 h-5 mr-3 text-gray-400"
								xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M6 18L18 6M6 6l12 12" />
                        </svg> Baja de Cliente
					</a></li>
					<li>
					<li><a href="ServletControladores?Pagina=listarClientes"
						class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
							<svg class="w-5 h-5 mr-3 text-gray-400"
								xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M5 12h14M12 5l7 7-7 7" />
                        </svg> Lista de Cliente
					</a></li>
					<li>
					<li><a href="ServletControladores?Pagina=autorizarPrestamo"
						class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
							<svg class="w-5 h-5 mr-3 text-gray-400"
								xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M5 12h14M12 5l7 7-7 7" />
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
								xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M5 12h14M12 5l7 7-7 7" />
                        </svg> Alta de Cuenta
					</a></li>

					<li><a href="ServletControladores?Pagina=bajaCuenta"
						class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
							<svg class="w-5 h-5 mr-3 text-gray-400"
								xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M6 18L18 6M6 6l12 12" />
                        </svg> Baja de Cuenta
					</a></li>
					<li><a href="ServletControladores?Pagina=listarCuentas"
						class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
							<svg class="w-5 h-5 mr-3 text-gray-400"
								xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M5 12h14M12 5l7 7-7 7" />
                        </svg> Lista de Cuenta
					</a></li>
					<li><a href="ServletControladores?Pagina=informesAdmin"
						class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
							<svg class="w-5 h-5 mr-3 text-gray-400"
								xmlns="http://www.w3.org/2000/svg" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M5 12h14M12 5l7 7-7 7" />
                        </svg> Informes y Reportes
					</a></li>

				</ul>
			</nav>
			<!-- Footer del Sidebar -->
			<div class="bg-gray-900 p-4">
				<p class="text-xs text-gray-400 text-center">&copy; 2024 Banco
					XYZ</p>
			</div>
		</aside>

		<!-- Contenido principal -->
		<div class="flex-1 p-6">
			<!-- Header -->
			<header class="border-b pb-4">
				<h2 class="text-2xl font-bold">Alta de Cuenta</h2>
			</header>

			<!-- Main Content -->
			<main class="mt-6">
			<div class="bg-gray-100 p-6 rounded-md shadow-md">
				<!-- Mensaje -->
				<%
					String mensaje;
					String color = "text-gray-700";
					if (request.getAttribute("mensaje") != null) {
						mensaje = "*" + (String) request.getAttribute("mensaje") + "*";
						if (mensaje.equals("*Cuenta registrada exitosamente.*")) {
							color = "text-green-600";
						} else {
							color = "text-red-600";
						}
					} else {
						mensaje = "";
					}
				%>
				<p class="<%=color%> text-center mb-4 font-medium"><%=mensaje%></p>

				<!-- Formulario -->
				<form name="form_alta_cuenta" action="ServletControladores"
					onsubmit="return validarFormulario(2)" method="post"
					class="space-y-4">
					<!-- DNI -->
					<div>
						<label for="dni" class="block mb-2 font-medium">DNI:</label> <input
							type="text" id="dni" name="dni" minlength="8" maxlength="8"
							required
							onkeypress="return (event.charCode >= 48 && event.charCode <= 57)"
							class="w-full p-2 bg-white border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
					</div>

					<!-- Tipo de Cuenta -->
					<div>
						<label for="tipo_de_cuenta" class="block mb-2 font-medium">Tipo
							de cuenta:</label> <select id="tipo_de_cuenta_alta" name="tipo_de_cuenta"
							required
							class="w-full p-2 bg-white border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
							<option value="" disabled selected>-- Seleccione un tipo
								de cuenta --</option>
							<%
								List<TipoDeCuenta> listaTCuenta = null;
								if (request.getAttribute("listaTC") != null) {
									listaTCuenta = (List<TipoDeCuenta>) request.getAttribute("listaTC");
									ListIterator<TipoDeCuenta> it = listaTCuenta.listIterator();
									while (it.hasNext()) {
										TipoDeCuenta TCuenta = it.next();
										it.remove();
										if (TCuenta.getIdTipoDeCuenta() != 3) {
							%>
							<option value="<%=TCuenta.getIdTipoDeCuenta()%>">
								<%=TCuenta.getDescripcion()%>
							</option>
							<%
								}
									}
								}
							%>
						</select>
					</div>

					<!-- CBU -->
					<div>
						<label for="CBU" class="block mb-2 font-medium">CBU:</label> <input
							type="text" id="CBU" name="CBU" minlength="22" maxlength="22"
							required
							onkeypress="return (event.charCode >= 48 && event.charCode <= 57)"
							class="w-full p-2 bg-white border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
					</div>

					<!-- Botón -->
					<div class="text-center">
						<button type="submit" name="btnAltaCuenta"
							class="bg-blue-500 hover:bg-blue-600 text-white font-medium px-4 py-2 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400">
							Guardar Cuenta</button>
					</div>
				</form>
			</div>
			</main>
		</div>
	</div>
</body>
</html>