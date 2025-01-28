<%@page import="entidad.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Autorización de Préstamos</title>
<script src="https://cdn.tailwindcss.com"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script>
	$(document).ready(function() {
		$('#tabla_paginada_prestamos').DataTable({
			paging : true,
			info : false,
			lengthChange : false,
			searching : true,
			pageLength : 10,
			language : {
				emptyTable : "No hay datos disponibles en la tabla",
				zeroRecords : "No se encontraron resultados",
				paginate : {
					first : "Primero",
					last : "Último",
					next : "Siguiente",
					previous : "Anterior"
				},
				search : "Buscar:"
			}
		});
	});
</script>
</head>
<body class="bg-gray-100 flex min-h-screen">

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
	<main class="flex-1 p-6">
	<h2 class="text-2xl font-semibold mb-4">Autorización de Préstamos</h2>

	<!-- Mensaje de estado --> <%
 	String mensaje;
 	String color = "text-gray-700";
 	if (request.getAttribute("mensaje") != null) {
 		mensaje = (String) request.getAttribute("mensaje");
 		if (mensaje.equals("Prestamo autorizado con exito")) {
 			color = "text-green-600";
 		} else {
 			color = "text-red-600";
 		}
 	} else {
 		mensaje = "";
 	}
 %>
	<p class="mb-4 <%=color%>"><%=mensaje%></p>

	<!-- Tabla -->
	<form name="form_autorizar_prestamo" action="ServletControladores"
		method="post">
		<div class="overflow-x-auto bg-white shadow-md rounded-md p-4">
			<table id="tabla_paginada_prestamos"
				class="min-w-full divide-y divide-gray-200 text-sm">
				<thead class="bg-gray-50">
					<tr>
						<th class="px-4 py-2 text-left">ID</th>
						<th class="px-4 py-2 text-left">Cliente</th>
						<th class="px-4 py-2 text-left">Fecha</th>
						<th class="px-4 py-2 text-left">Importe Total</th>
						<th class="px-4 py-2 text-left">Importe Original</th>
						<th class="px-4 py-2 text-left">Plazo (Meses)</th>
						<th class="px-4 py-2 text-left">Valor Cuota</th>
						<th class="px-4 py-2 text-left">Pago</th>
						<th class="px-4 py-2 text-left">Autorizado</th>
						<th class="px-4 py-2 text-center">Acción</th>
					</tr>
				</thead>
				<tbody class="bg-white divide-y divide-gray-200">
					<%
						ArrayList<Prestamo> listaPr = (ArrayList<Prestamo>) request.getAttribute("listaPrAuth");
						if (listaPr != null) {
							for (Prestamo pr : listaPr) {
					%>
					<tr>
						<td class="px-4 py-2"><%=pr.getIdPrestamos()%></td>
						<td class="px-4 py-2"><%=pr.getDniDelCliente_Pres()%> <input
							type="hidden" name="<%=pr.getIdPrestamos()%>_1"
							value="<%=pr.getCuenta_Pres().getNumeroDecuenta_Cuen()%>">
						</td>
						<td class="px-4 py-2"><input type="hidden"
							name="<%=pr.getIdPrestamos()%>_2"
							value="<%=pr.getFechaDePedido_Pres()%>"> <%=pr.getFechaDePedido_Pres()%>
						</td>
						<td class="px-4 py-2"><%=pr.getImporteFinal_Pres()%></td>
						<td class="px-4 py-2"><input type="hidden"
							name="<%=pr.getIdPrestamos()%>_3"
							value="<%=pr.getImporteOriginal_Pres()%>"> <%=pr.getImporteOriginal_Pres()%>
						</td>
						<td class="px-4 py-2"><%=pr.getPlazoEnMeses_pres()%></td>
						<td class="px-4 py-2"><%=pr.getValorDeCuota_pres()%></td>
						<td class="px-4 py-2 text-center"><input type="checkbox"
							<%=pr.isPagado_pres() ? "checked disabled" : "disabled"%>>
						</td>
						<td class="px-4 py-2 text-center"><input type="checkbox"
							<%=pr.isAutorizado_pres() ? "checked disabled" : "disabled"%>>
						</td>
						<td class="px-4 py-2 text-center">
							<%
								if (!pr.isAutorizado_pres()) {
							%>
							<button name="btnAutorizarPrestamo"
								value="<%=pr.getIdPrestamos()%>" type="submit"
								class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md font-medium shadow-md">Autorizar</button>
							<button name="btnRechazarPrestamo"
								value="<%=pr.getIdPrestamos()%>" type="submit"
								class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md font-medium shadow-md">Rechazar</button>
							<%
								} else {
							%>
							<button disabled
								class="bg-gray-300 text-gray-500 px-4 py-2 rounded-md font-medium shadow-md">Autorizado</button>
							<%
								}
							%>
						</td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
		</div>
	</form>
	</main>
</body>
</html>