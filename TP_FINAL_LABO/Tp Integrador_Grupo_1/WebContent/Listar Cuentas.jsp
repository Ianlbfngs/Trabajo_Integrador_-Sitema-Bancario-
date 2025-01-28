<%@page import="java.util.ListIterator"%>
<%@page import="entidad.TipoDeCuenta"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Listado de Cuentas</title>
<script src="https://cdn.tailwindcss.com"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script src="JavaScript/filtros.js" type="text/JavaScript"></script>
<script src="JavaScript/validaciones.js" type="text/JavaScript"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script type="text/javascript">
        $(document).ready(function () {
            $('#tabla_paginada_cuentas').DataTable({
                paging: true,
                info: false,
                lengthChange: false,
                searching: true,
                pageLength: 10,
                language: {
                    emptyTable: "No hay datos disponibles en la tabla",
                    zeroRecords: "No se encontraron resultados",
                    paginate: {
                        first: "Primero",
                        last: "Último",
                        next: "Siguiente",
                        previous: "Anterior"
                    }
                }
            });
        });
    </script>
<script>
    function setIdSeleccionado(id) {
        const input = document.getElementById('idSeleccionado');
        if (input) {
            input.value = id;
        } else {
            console.error('Elemento con ID dniSeleccionado no encontrado.');
        }
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
			<h1 class="text-2xl font-semibold mb-4">Listado de Cuentas</h1>
			<form class="grid grid-cols-1 sm:grid-cols-2 gap-4 mb-4" name="form_listarCuentasFiltrado" action="ServletControladores" method="post">
				<div>
					<label for="filtro_Saldo_mayor"
						class="block text-sm font-medium text-gray-700"> Saldo
						Mayor a: </label> <input type="number" id="filtro_Saldo_mayor"
						name="filtro_Saldo_ma"
						class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
				</div>
				<div>
					<label for="filtro_Saldo_menor"
						class="block text-sm font-medium text-gray-700"> Saldo
						Menor a: </label> <input type="number" id="filtro_Saldo_menor"
						name="filtro_Saldo_me"
						class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
				</div>
				<div class="flex items-end">
					<button type="submit" name="btn_filtrar_ListarCuentas"
						class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md font-medium shadow-md">
						Filtrar</button>
				</div>
			</form>

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

			<!-- Tabla -->
			<div class="overflow-x-auto">
				<form name="form_actualizacion_cuenta" action="ServletControladores"
					method="post">
					<!-- Campo oculto para enviar el ID seleccionado -->
					<input type="hidden" id="idSeleccionado" name="idSeleccionado"
						value="">
					<table id="tabla_paginada_cuentas"
						class="min-w-full divide-y divide-gray-200 table-auto text-sm">
						<thead class="bg-gray-50">
							<tr>
								<th class="px-4 py-2 text-left">Número de Cuenta</th>
								<th class="px-4 py-2 text-left">Tipo de Cuenta</th>
								<th class="px-4 py-2 text-left">DNI Cliente</th>
								<th class="px-4 py-2 text-left">Fecha</th>
								<th class="px-4 py-2 text-left">Saldo</th>
								<th class="px-4 py-2 text-left">CBU</th>
								<th class="px-4 py-2 text-left">Editable</th>
							</tr>
						</thead>
						<tbody class="bg-white divide-y divide-gray-200">
							<%
								ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");																						
								if (listaCuentas != null) {
									for (Cuenta cuenta : listaCuentas) {
										String numCuenta = cuenta.getNumeroDecuenta_Cuen();
										if (!numCuenta.equals("0000")) {
							%>
							<tr>
								<td class="px-4 py-2"><span id="<%=numCuenta%>_1_datoOG"><%=cuenta.getNumeroDecuenta_Cuen()%></span></td>
								<td class="px-4 py-2">
									<!-- Texto original visible --> <span
									id="<%=numCuenta%>_2_datoOG"> <%=cuenta.getDescTipoCuenta_Cuen()%>
								</span> <!-- Valor oculto --> <input type="hidden"
									value="<%=cuenta.getIdTipoDeCuenta_Cuen()%>"> <!-- Select oculto -->
									<select id="<%=numCuenta%>_2_ID" name="<%=numCuenta%>_2"
									class="hidden w-24 border border-gray-300">
										<%
											List<TipoDeCuenta> listaTiposCuenta = (List<TipoDeCuenta>) request.getAttribute("listaTC");
														if (listaTiposCuenta != null) {
															for (TipoDeCuenta tipoCuenta : listaTiposCuenta) {
										%>
										<option value="<%=tipoCuenta.getIdTipoDeCuenta()%>">
											<%=tipoCuenta.getDescripcion()%>
										</option>
										<%
											}
														}
										%>
								</select>
								</td>
								<td class="px-4 py-2"><span id="<%=numCuenta%>_3_datoOG"><%=cuenta.getDniCliente_Cuen()%>
								</span></td>
								<td class="px-4 py-2"><span id="<%=numCuenta%>_4_datoOG"><%=cuenta.getFechaCreacion_Cuen()%></span></td>
								<td class="px-4 py-2"><span id="<%=numCuenta%>_5_datoOG"><%=cuenta.getSaldo_Cuen()%></span></td>
								<td class="px-4 py-2"><span id="<%=numCuenta%>_6_datoOG"><%=cuenta.getCBU_Cuen()%></span></td>
								<td class="px-4 flex gap-4 justify-between py-2">
									<button type="submit" name="btnEditarCuenta"
										onclick="setIdSeleccionado(<%=numCuenta%>)"
										class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md font-medium shadow-md">Seleccionar</button>
								</td>
							</tr>
							<%
								}
									}
								}
								
							%>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		</main>
	</div>
</body>
</html>