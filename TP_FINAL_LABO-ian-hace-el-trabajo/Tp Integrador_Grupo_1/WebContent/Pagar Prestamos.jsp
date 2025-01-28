<%@page import="entidad.PagoDeCuotas"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pago de Préstamos</title>
<script src="https://cdn.tailwindcss.com"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script src="JavaScript/filtros.js" type="text/JavaScript"></script>
<script src="JavaScript/validaciones.js" type="text/JavaScript"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script>
        $(document).ready(function () {
            $('#tabla_paginada_cuotas').DataTable({
                paging: true,
                info: false,
                lengthChange: false,
                searching: true,
                pageLength: 6,
                language: {
                    emptyTable: "No hay datos disponibles en la tabla",
                    paginate: { first: "Primero", last: "Último", next: "Siguiente", previous: "Anterior" },
                    search: "Buscar:",
                    zeroRecords: "No se encontraron resultados",
                }
            });
        });

        function filtrarCuotas() {
            const idPrestamo = document.getElementById("prestamo_seleccion_id").value;
            const opcionesCuotas = document.querySelectorAll("#prestamo_cuota_id option");

            opcionesCuotas.forEach((opcion) => {
                if (opcion.value === "0" || opcion.classList.contains(idPrestamo)) {
                    opcion.style.display = ""; // Mostrar
                } else {
                    opcion.style.display = "none"; // Ocultar
                }
            });

            // Resetear la selección del campo de cuotas
            document.getElementById("prestamo_cuota_id").value = "0";
        }

    </script>
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
		</nav>
	</aside>


	<!-- Main Content -->
	<main class="flex-1 p-8"> <!-- Mensaje --> <%
 	String mensaje;
 	String color = "text-gray-700";
 	if (request.getAttribute("error") != null) {
 		mensaje = "" + (String) request.getAttribute("error") + "";
 		if (mensaje.equals("Pago realizado con éxito.")) {
 			color = "text-green-600";
 		} else {
 			color = "text-red-600";
 		}
 	} else {
 		mensaje = "";
 	}
 %>
	<p id="mensaje" class="<%=color%> text-center mb-4 font-semibold"><%=mensaje%></p>
	<header class="mb-8">
		<h2 class="text-3xl font-semibold">Pago de Préstamos</h2>
		<p class="text-gray-400">Seleccione un préstamo y realice el pago
			de sus cuotas.</p>
	</header> <!-- Tabla de Préstamos -->
	<section class="mb-8">
		<h3 class="text-xl font-semibold mb-4">Lista de Préstamos</h3>
		<table id="tabla_paginada_cuotas"
			class="table-auto w-full bg-gray-800 text-gray-100 rounded-md shadow-lg">
			<thead>
				<tr
					class="bg-gray-700 text-gray-300 uppercase text-sm font-semibold">
					<th>ID</th>
					<th>Cliente (DNI)</th>
					<th>Fecha</th>
					<th>Importe Total</th>
					<th>Importe Original</th>
					<th>Plazo</th>
					<th>Cuotas Faltantes</th>
					<th>Valor de Cuota</th>
				</tr>
			</thead>
			<tbody class="divide-y text-black divide-gray-700">
				<%
					ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");
					if (listaPrestamos != null) {
						for (Prestamo prestamo : listaPrestamos) {
							if (prestamo.isAutorizado_pres()) {
				%>
				<tr class="hover:bg-gray-700 hover:text-white">
					<td><%=prestamo.getIdPrestamos()%></td>
					<td><%=prestamo.getCliente_Pres().getDni_cli()%></td>
					<td><%=prestamo.getFechaDePedido_Pres()%></td>
					<td class="text-green-400"><%=prestamo.getImporteFinal_Pres()%></td>
					<td><%=prestamo.getImporteOriginal_Pres()%></td>
					<td><%=prestamo.getPlazoEnMeses_pres()%> meses</td>
					<td><%=prestamo.getMesesRestantes_pres()%> Cuotas</td>
					<td class="text-yellow-400"><%=prestamo.getValorDeCuota_pres()%></td>
				</tr>
				<%
					}
						}
					} else {
				%>
				<tr>
					<td colspan="7" class="text-center py-6 text-gray-400">No hay
						préstamos disponibles.</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</section>

	<!-- Formulario de Pago -->
	<section>
		<h3 class="text-xl font-semibold mb-4">Pagar Cuotas</h3>
		<form class="flex  w-1/2 flex-col gap-4" name="form_pagar_preatamo"
			action="ServletControladores" onsubmit="return validarFormulario(4)"
			method="POST">

			<div class="flex flex-col gap-4">
				<div>
					<label for="prestamo_cuenta_id" class="block text-lg">Seleccione
						una Cuenta:</label> <select id="prestamo_cuenta_id" name="prestamo_cuenta"
						class="w-full bg-gray-800 text-gray-100 rounded-md px-4 py-2"
						required>
						<option value="" disabled selected>-- Seleccione una
							cuenta --</option>
						<%
							ArrayList<Cuenta> listCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCnPPr");
							if (listCuentas != null) {
								for (Cuenta cuenta : listCuentas) {
						%>
						<option value="<%=cuenta.getNumeroDecuenta_Cuen()%>">Cuenta
							<%=cuenta.getNumeroDecuenta_Cuen()%></option>
						<%
							}
							}
						%>
					</select> <span id="error_prestamo_cuenta" class="text-red-600 text-sm"></span>
				</div>
				<div>
					<label for="prestamo_seleccion_id">Seleccione un Préstamo:</label>
					<select onchange="filtrarCuotas()"
						class="w-full bg-gray-800 text-gray-100 rounded-md px-4 py-2"
						id="prestamo_seleccion_id" name="prestamo_seleccion" required>
						<option value="" disabled selected>-- Seleccione un
							préstamo --</option>
						<%
							if (listaPrestamos != null) {
								for (Prestamo prestamo : listaPrestamos) {
									if (prestamo.isAutorizado_pres()) {
						%>
						<option value="<%=prestamo.getIdPrestamos()%>">ID:
							<%=prestamo.getIdPrestamos()%></option>
						<%
							}
								}
							}
						%>
					</select> <span id="error_prestamo_seleccion" class="text-red-600 text-sm"></span>
				</div>
				<div>
					<label for="prestamo_cuota_label">Cuota a Pagar:</label> <select
						id="prestamo_cuota_id"
						class="w-full bg-gray-800 text-gray-100 rounded-md px-4 py-2"
						name="prestamo_cuota">
						<option value="" disabled selected>-- Seleccione la cuota
							a pagar --</option>
						<%
							if (listaPrestamos != null) {
								for (Prestamo prestamo : listaPrestamos) {
									int cuotasRestantes = prestamo.getMesesRestantes_pres();
									if (cuotasRestantes > 0) {
						%>
						<option style="display: none;"
							value="<%=prestamo.getIdPrestamos()%>_ultima"
							class="cuota <%=prestamo.getIdPrestamos()%>">Pagar 1
							cuota</option>
						<%
							if (cuotasRestantes >= 3) {
						%>
						<option style="display: none;"
							value="<%=prestamo.getIdPrestamos()%>_3"
							class="cuota <%=prestamo.getIdPrestamos()%>">Pagar 3
							cuotas</option>
						<%
							}
										if (cuotasRestantes >= 6) {
						%>
						<option style="display: none;"
							value="<%=prestamo.getIdPrestamos()%>_6"
							class="cuota <%=prestamo.getIdPrestamos()%>">Pagar 6
							cuotas</option>
						<%
							}
										if (cuotasRestantes >= 12) {
						%>
						<option style="display: none;"
							value="<%=prestamo.getIdPrestamos()%>_12"
							class="cuota <%=prestamo.getIdPrestamos()%>">Pagar 12
							cuotas</option>
						<%
							}
									}
								}
							}
						%>
					</select> <span id="error_prestamo_cuota" class="text-red-600 text-sm"></span>
				</div>
			</div>
			<button type="submit" name="btnPagarCuota"
				class="bg-blue-600 w-1/2 hover:bg-blue-700 text-white px-6 py-2 rounded-md">Pagar
				Cuota</button>
		</form>
	</section>
	</main>
</body>
</html>
