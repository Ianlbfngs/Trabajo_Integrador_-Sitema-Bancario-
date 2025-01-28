<%@page import="java.util.ListIterator"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Sexo"%>
<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Listado de Clientes</title>
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
            $('#tabla_paginada_clientes').DataTable({
                paging: true,
                info: false,
                lengthChange: false,
                searching: true,
                pageLength: 5,
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
    function setDniSeleccionado(dni) {
        const input = document.getElementById('dniSeleccionado');
        if (input) {
            input.value = dni;
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
			<h1 class="text-2xl font-semibold mb-4">Listado de Clientes</h1>
			<form class="grid grid-cols-1 sm:grid-cols-2 gap-4 mb-4" name="form_filtro_listadoClientes_dni" action="ServletControladores" method="post">
				<div>
					<label for="filtro_dni_mayor"
						class="block text-sm font-medium text-gray-700"> DNI Menor
						a: </label> <input type="text" id="filtro_dni_mayor"
						name="filtro_dni_ma" maxlength="8" pattern="^[0-9]{8}$" onkeypress="return (event.charCode >= 48 && event.charCode <= 57)"
						class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
				</div>
				<div>
					<label for="filtro_dni_menor"
						class="block text-sm font-medium text-gray-700"> DNI Mayor
						a: </label> <input type="text" id="filtro_dni_menor"
						name="filtro_dni_me" maxlength="8" pattern="^[0-9]{8}$" onkeypress="return (event.charCode >= 48 && event.charCode <= 57)"
						class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
				</div>
				<div class="flex items-end">
					<button type="submit" name="btn_filtro_listadoClientes"
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
					if (mensaje.equals("*Cliente actualizado correctamente*")) {
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
				<form name="form_actualizacion_cliente"
					action="ServletControladores" method="post">
					<!-- Campo oculto para enviar el DNI seleccionado -->
					<input type="hidden" id="dniSeleccionado" name="dniSeleccionado"
						value="">

					<table id="tabla_paginada_clientes"
						class="min-w-full divide-y divide-gray-200 table-auto text-sm">
						<thead class="bg-gray-50">
							<tr>
								<th class="px-4 py-2 text-left">DNI</th>
								<th class="px-4 py-2 text-left">CUIL</th>
								<th class="px-4 py-2 text-left">Apellido</th>
								<th class="px-4 py-2 text-left">Nombre</th>
								<th class="px-4 py-2 text-left">Sexo</th>
								<th class="px-4 py-2 text-left">Nacionalidad</th>
								<th class="px-4 py-2 text-left">Provincia</th>
								<th class="px-4 py-2 text-left">Localidad</th>
								<th class="px-4 py-2 text-left">Fecha de Nacimiento</th>
								<th class="px-4 py-2 text-left">Dirección</th>
								<th class="px-4 py-2 text-left">Correo</th>
								<th class="px-4 py-2 text-left">Teléfono</th>
								<th class="px-4 py-2 text-left">Usuario</th>
								<th class="px-4 py-2 text-left">Contraseña</th>
								<th class="px-4 py-2 text-left">Editable</th>
							</tr>
						</thead>
						<tbody class="bg-white divide-y divide-gray-200">
							<%
								ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) request.getAttribute("listaClientes");
								String dniCliente = "";
								if (listaClientes != null) {
									for (Cliente cliente : listaClientes) {
										dniCliente = cliente.getDni_cli();
										if (!dniCliente.equals("00000000")) {
							%>
							<tr>
								<td class="px-4 py-2"><span id="<%=dniCliente%>_1_datoOG"><%=cliente.getDni_cli()%></span></td>
								<td class="px-4 py-2"><span id="<%=dniCliente%>_2_datoOG"><%=cliente.getCuil_cli()%></span></td>
								<td class="px-4 py-2"><span id="<%=dniCliente%>_3_datoOG"><%=cliente.getApellido_cli()%></span></td>
								<td class="px-4 py-2"><span id="<%=dniCliente%>_4_datoOG"><%=cliente.getNombre_cli()%></span></td>
								<td class="px-4 py-2">
									<!-- Texto original mostrado por defecto --> <span
									id="<%=dniCliente%>_5_datoOG"> <%=cliente.getDescGenero_cli()%>
								</span> <!-- Input oculto para almacenar el valor actual --> <input
									type="hidden" value="<%=cliente.getIdGenero_cli()%>">

									<!-- Select oculto, que se muestra al activar edición --> <select
									id="<%=dniCliente%>_5_ID" name="<%=dniCliente%>_5"
									class="hidden w-24 border border-gray-300">
										<%
											// Obtener la lista de géneros desde el request
														List<Sexo> listaSexos = (List<Sexo>) request.getAttribute("listaS");
														if (listaSexos != null) {
															for (Sexo sexo : listaSexos) {
										%>
										<!-- Crear una opción para cada género -->
										<option value="<%=sexo.getIDSexo()%>">
											<%=sexo.getDescripcion()%>
										</option>
										<%
											}
														}
										%>
								</select>
								</td>

								<td class="px-4 py-2"><span id="<%=dniCliente%>_6_datoOG"><%=cliente.getNacionalidad_cli()%></span></td>
								<td class="px-4 py-2">
									<!-- Texto original mostrado por defecto --> <span
									id="<%=dniCliente%>_7_datoOG"> <%=cliente.getDescProvincia_loc_cli()%>
								</span> <!-- Valor oculto para facilitar la sincronización con el select -->
									<input type="hidden"
									value="<%=cliente.getIdProvincia_loc_cli()%>"> <!-- Select oculto por defecto, se muestra al editar -->
									<select id="<%=dniCliente%>_7_ID" name="<%=dniCliente%>_7"
									onchange="javascript:carga_select_localidades_update('<%=dniCliente%>')"
									class="hidden w-24 border border-gray-300">
										<%
											// Obtener la lista de provincias desde el request
														List<Provincia> listaProvincias = (List<Provincia>) request.getAttribute("listaP");
														if (listaProvincias != null) {
															for (Provincia provincia : listaProvincias) {
										%>
										<!-- Crear una opción para cada provincia -->
										<option value="<%=provincia.getIdProvincia()%>">
											<%=provincia.getDescripcion()%>
										</option>
										<%
											}
														}
										%>
								</select>
								</td>

								<td class="px-4 py-2">
									<!-- Texto original visible --> <span
									id="<%=dniCliente%>_8_datoOG"> <%=cliente.getDescLoc_cli()%>
								</span> <!-- Valor oculto --> <input type="hidden"
									value="<%=cliente.getIdLocalidad_cli()%>"> <!-- Select oculto -->
									<select id="<%=dniCliente%>_8_ID" name="<%=dniCliente%>_8"
									class="hidden w-24 border border-gray-300">
										<%
											List<Localidad> listaLocalidades = (List<Localidad>) request.getAttribute("listaL");
														if (listaLocalidades != null) {
															for (Localidad localidad : listaLocalidades) {
										%>
										<option value="<%=localidad.getIdLocalidad_Loc()%>"
											data-provincia="<%=localidad.getIdProvincia_Loc()%>">
											<%=localidad.getDescripcion_Loc()%>
										</option>
										<%
											}
														}
										%>
								</select>
								</td>
								<td class="px-4 py-2"><span id="<%=dniCliente%>_9_datoOG"><%=cliente.getFechaNacimiento_cli()%></span></td>
								<td class="px-4 py-2"><span id="<%=dniCliente%>_10_datoOG"><%=cliente.getDireccion_cli()%></span></td>
								<td class="px-4 py-2"><span id="<%=dniCliente%>_11_datoOG"><%=cliente.getCorreo_cli()%></span></td>
								<td class="px-4 py-2"><span id="<%=dniCliente%>_12_datoOG"><%=cliente.getTelefono_cli()%></span></td>
								<td class="px-4 py-2"><span id="<%=dniCliente%>_13_datoOG"><%=cliente.getUsuario_cli()%></span></td>
								<td class="px-4 py-2"><span id="<%=dniCliente%>_14_datoOG"><%=cliente.getContrasena_cli()%></span></td>
								<td class="px-4 flex gap-4 justify-between py-2">
									<button type="submit" name="btnEditarCliente"
										onclick="setDniSeleccionado(<%=dniCliente%>)"
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

