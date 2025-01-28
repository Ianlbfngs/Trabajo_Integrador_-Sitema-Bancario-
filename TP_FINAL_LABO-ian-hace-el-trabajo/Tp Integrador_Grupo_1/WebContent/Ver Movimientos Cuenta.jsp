<%@page import="java.util.ListIterator"%>
<%@page import="entidad.TipoDeCuenta"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Movimiento"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimientos de Cuenta</title>
<script src="https://cdn.tailwindcss.com"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

<script>
	$(document)
			.ready(
					function() {
						// Inicializar DataTables
						const table = $('#tabla_paginada_movimientos')
								.DataTable(
										{
											paging : true,
											info : false,
											lengthChange : false,
											searching : true,
											pageLength : 7,
											language : {
												emptyTable : "No hay datos disponibles en la tabla",
												paginate : {
													first : "Primero",
													last : "Último",
													next : "Siguiente",
													previous : "Anterior"
												},
												search : "Buscar:",
												zeroRecords : "No se encontraron resultados",
											},
										});

						// Filtrar movimientos según la cuenta seleccionada
						$('#filtroCuenta')
								.change(
										function() {
											// Obtener el valor seleccionado
											var cuentaSeleccionada = $(this)
													.val();

											// Redirigir a la misma página con el filtro aplicado
											window.location.href = "ServletControladores?Pagina=verMovimientos&filtroCuenta="
													+ cuentaSeleccionada;
										});
					});
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
	<main class="flex-1 p-8"> <header class="mb-8">
		<h2 class="text-3xl font-semibold">Movimientos de Cuenta</h2>
		<p class="text-gray-400">Revise los movimientos de sus cuentas y
			utilice los filtros disponibles.</p>
	</header> <!-- Filtros -->
	<section class="mb-8">
		<div class="flex gap-4 items-center">
			<label for="filtroCuenta" class="text-lg">Seleccione una
				cuenta:</label> <select id="filtroCuenta" name="filtroCuenta"
				class="bg-gray-800 text-gray-100 rounded-md px-4 py-2">
				<%
					// Obtener el valor del filtro enviado al servlet
					String filtroCuentaSeleccionada = request.getParameter("filtroCuenta");
				%>
				<option value="0"
					<%=("0".equals(filtroCuentaSeleccionada) || filtroCuentaSeleccionada == null) ? "selected" : ""%>>Listar
					todos</option>
				<%
					ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentasdelUsuario");
					if (listaCuentas != null) {
						for (Cuenta cuenta : listaCuentas) {
							String cuentaValue = cuenta.getNumeroDecuenta_Cuen() + "," + cuenta.getCBU_Cuen();
				%>
				<option value="<%=cuentaValue%>"
					<%=cuentaValue.equals(filtroCuentaSeleccionada) ? "selected" : ""%>>
					<%=cuenta.getNumeroDecuenta_Cuen()%>
				</option>
				<%
					}
					} else {
				%>
				<option value="">No hay cuentas disponibles</option>
				<%
					}
				%>
			</select>
		</div>
	</section>

	<!-- Tabla de Movimientos -->
	<section>
		<table id="tabla_paginada_movimientos"
			class="table-auto w-full bg-gray-800 text-gray-100 rounded-md shadow-lg">
			<thead>
				<tr
					class="bg-gray-700 text-gray-300 uppercase text-sm font-semibold">
					<th class="py-3 px-4 text-left border-b border-gray-600">ID de
						Movimiento</th>
					<th class="py-3 px-4 text-left border-b border-gray-600">Número
						de Cuenta</th>
					<th class="py-3 px-4 text-left border-b border-gray-600">Fecha</th>
					<th class="py-3 px-4 text-left border-b border-gray-600">Detalle</th>
					<th class="py-3 px-4 text-left border-b border-gray-600">Importe</th>
					<th class="py-3 px-4 text-left border-b border-gray-600">CBU
						de Destino</th>
				</tr>
			</thead>
			<tbody class="divide-y text-black divide-gray-700">
				<%
					ArrayList<Movimiento> listaMovimientos = (ArrayList<Movimiento>) request
							.getAttribute("listaMovimientosCuentas");
					ArrayList<String> listaCBUs = (ArrayList<String>) request.getAttribute("listaCBUs");
					boolean flag = true;
					if (listaMovimientos != null) {
						for (Movimiento movimiento : listaMovimientos) {
				%>

				<tr
					class="hover:bg-gray-700 hover:text-white transition duration-200 ease-in-out">
					<td class="py-3 px-4 border-gray-600"><%=movimiento.getIDMovimientos()%></td>
					<td class="py-3 px-4 border-gray-600"><%=movimiento.getCuentaOrigen_Mov().getNumeroDecuenta_Cuen().equals("0000")
							? "Sistema Bancario"
							: movimiento.getCuentaOrigen_Mov().getNumeroDecuenta_Cuen()%></td>
					<td class="py-3 px-4 border-gray-600"><%=movimiento.getFechaDeMovimiento()%></td>
					<td class="py-3 px-4 border-gray-600"><%=movimiento.getDetalleDeMovimiento()%></td>
					<%
						if (movimiento.getCuentaOrigen_Mov().getNumeroDecuenta_Cuen().equals("0000")) {
									movimiento.setImporteDeMovimiento(movimiento.getImporteDeMovimiento().abs());

								} else {
									if (request.getAttribute("filtroAplicado") == null) {
										if (listaCBUs != null) {
											for (String cbu : listaCBUs) {
												if (cbu.equals(movimiento.getCuentaDestino_Mov().getCBU_Cuen()) && flag) {
													movimiento.setImporteDeMovimiento(movimiento.getImporteDeMovimiento().negate());
													flag = false;
													break;
												} else if (cbu.equals(movimiento.getCuentaDestino_Mov().getCBU_Cuen()) && !flag) {
													flag = true;
													break;
												}
											}
										}
									} else {
										if (!request.getAttribute("filtroAplicado")
												.equals(movimiento.getCuentaOrigen_Mov().getNumeroDecuenta_Cuen())) {
											movimiento.setImporteDeMovimiento(movimiento.getImporteDeMovimiento().negate());
										}
									}
								}
					%>
					<td
						class="py-3 px-4 border-gray-600 <%=movimiento.getImporteDeMovimiento().compareTo(BigDecimal.ZERO) >= 0
							? "text-green-400 font-semibold"
							: "text-red-400 font-semibold"%>">
						<%=movimiento.getImporteDeMovimiento()%>
					</td>
					<td class="py-3 px-4 border-gray-600"><%=movimiento.getCuentaDestino_Mov().getCBU_Cuen().equals("0000000000000000000000")
							? "Sistema Bancario"
							: movimiento.getCuentaDestino_Mov().getCBU_Cuen()%></td>
				</tr>
				<%
					}
					} else {
				%>
				<tr>
					<td colspan="6" class="py-6 text-center text-gray-400">No hay
						movimientos disponibles.</td>
				</tr>
				<%
					}
				%>
			</tbody>

		</table>

	</section>
	</main>
</body>
</html>
