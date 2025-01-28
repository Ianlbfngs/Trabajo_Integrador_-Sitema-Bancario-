<%@page import="entidad.Cuenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Administrador - Baja de Cuenta</title>
<script src="https://cdn.tailwindcss.com"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

<script>
	$(document).ready(function() {
		$('#tabla_paginada_BCuenta').DataTable({
			paging : true,
			info : false,
			lengthChange : false,
			searching : true,
			pageLength : 5,
			language : {
				emptyTable : "No hay datos disponibles en la tabla",
				zeroRecords : "No se encontraron resultados",
				paginate : {
					first : "Primero",
					last : "Último",
					next : "Siguiente",
					previous : "Anterior"
				}
			}
		});
	});

	function ConfirmDelete() {
		const numeroCuenta = document.getElementById("numeroCuenta").value;
		if (numeroCuenta === "" || numeroCuenta.length !== 4
				|| isNaN(numeroCuenta)) {
			alert("Por favor, ingresa un número de cuenta válido de 4 dígitos.");
			return false;
		}
		return confirm("¿Estás seguro de que quieres eliminar esta cuenta?");
	}
</script>
</head>
<body class="bg-gray-100 min-h-screen">
	<div class="flex">
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
		<!-- Main Content -->
		<main class="flex-1 p-6">
		<div class="bg-white p-6 rounded-md shadow-md">
			<h1 class="text-2xl font-semibold mb-4">Baja de Cuenta</h1>
			<!-- Formulario -->
			<form action="ServletControladores" method="post"
				onsubmit="return ConfirmDelete()">
				<div class="grid grid-cols-1 sm:grid-cols-2 gap-4 mb-4">
					<div>
						<label for="numeroCuenta"
							class="block text-sm font-medium text-gray-700"> Número
							de la Cuenta a Eliminar: </label> <input type="text" id="numeroCuenta"
							name="numeroCuenta" maxlength="4" required
							onkeypress="return (event.charCode >= 48 && event.charCode <= 57)"
							class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
					</div>
					<div class="flex items-end">
						<button type="submit" name="btnEliminarCuenta"
							class="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded-md font-medium shadow-md">
							Eliminar Cuenta</button>
					</div>
				</div>

				<!-- Mensaje -->
				<%
					String mensaje;
					String color = "text-gray-700";
					if (request.getAttribute("mensaje") != null) {
						mensaje = "*" + (String) request.getAttribute("mensaje") + "*";
						if (mensaje.equals("*Cuenta eliminada exitosamente.*")) {
							color = "text-green-600";
						} else {
							color = "text-red-600";
						}
					} else {
						mensaje = "";
					}
				%>
				<p class="<%=color%> text-center mb-4 font-medium"><%=mensaje%></p>
			</form>
		</div>
		<!-- Tabla -->
		<div class="bg-white mt-6 p-6 rounded-md shadow-md">
			<h2 class="text-xl font-semibold mb-4">Lista de Cuentas</h2>
			<div class="overflow-x-auto">
				<table id="tabla_paginada_BCuenta"
					class="min-w-full divide-y divide-gray-200 table-auto text-sm">
					<thead class="bg-gray-50">
						<tr>
							<th class="px-4 py-2 text-left">Número de Cuenta</th>
							<th class="px-4 py-2 text-left">Tipo</th>
							<th class="px-4 py-2 text-left">DNI Cliente</th>
							<th class="px-4 py-2 text-left">Fecha Creación</th>
							<th class="px-4 py-2 text-left">CBU</th>
							<th class="px-4 py-2 text-left">Saldo</th>
						</tr>
					</thead>
					<tbody class="bg-white divide-y divide-gray-200">
						<%
							List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
							if (listaCuentas != null) {
								for (Cuenta cuenta : listaCuentas) {
									if (!cuenta.getNumeroDecuenta_Cuen().equals("0000")) {
						%>
						<tr>
							<td class="px-4 py-2"><%=cuenta.getNumeroDecuenta_Cuen()%></td>
							<td class="px-4 py-2"><%=cuenta.getIdTipoDeCuenta_Cuen()%></td>
							<td class="px-4 py-2"><%=cuenta.getDniCliente_Cuen()%></td>
							<td class="px-4 py-2"><%=cuenta.getFechaCreacion_Cuen()%></td>
							<td class="px-4 py-2"><%=cuenta.getCBU_Cuen()%></td>
							<td class="px-4 py-2"><%=cuenta.getSaldo_Cuen()%></td>
						</tr>
						<%
							}
								}
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
		</main>
	</div>
</body>
</html>