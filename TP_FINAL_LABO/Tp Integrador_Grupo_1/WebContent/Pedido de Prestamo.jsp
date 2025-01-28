<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ListIterator"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pedido de Préstamo</title>
<script src="https://cdn.tailwindcss.com"></script>
<script src="JavaScript/filtros.js" type="text/javascript"></script>
<script src="JavaScript/validaciones.js" type="text/JavaScript"></script>
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
		<h2 class="text-3xl font-semibold">Pedido de Préstamo</h2>
		<p class="text-gray-400">Complete el formulario para realizar un
			pedido de préstamo.</p>
	</header>

	<section>
		<%
			String mensaje;
			String color = "text-gray-400";
			if (request.getAttribute("mensaje") != null) {
				mensaje = (String) request.getAttribute("mensaje");
				if (mensaje.equals("Prestamo solicitado exitosamente")) {
					color = "text-green-500";
				} else {
					color = "text-red-500";
				}
			} else {
				mensaje = "";
			}
		%>
		<p class="<%=color%> mb-4"><%=mensaje%></p>

		<!-- Formulario -->
		<form name="form_pedido_prestamo" action="ServletControladores"
			onsubmit="return validarFormulario(3);" method="POST"
			class="space-y-6">
			<input type="hidden" value="<%=request.getAttribute("dniCliente")%>"
				name="dniClientePr">

			<div>
				<label for="seleccion_cuenta_prestamo" class="block text-lg">Seleccione
					una cuenta</label> <select id="seleccion_cuenta_prestamo"
					name="cuenta_prestamo"
					class="w-full bg-gray-800 text-gray-100 rounded-md px-4 py-2"
					required>
					<option value="" disabled selected>-- Seleccione una
						cuenta --</option>
					<%
						List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listaCnPr");
						if (listaCuentas != null) {
							for (Cuenta cuenta : listaCuentas) {
					%>
					<option value="<%=cuenta.getNumeroDecuenta_Cuen()%>">Cuenta
						número:
						<%=cuenta.getNumeroDecuenta_Cuen()%></option>
					<%
						}
						}
					%>
				</select>
			</div>

			<div>
				<label for="plazo_pago_prestamo" class="block text-lg">Plazo
					de Pago</label> <select id="plazo_pago_prestamo" name="plazo_pago"
					class="w-full bg-gray-800 text-gray-100 rounded-md px-4 py-2"
					onchange="javascript:monto_pago_prestamo_dinamico()" required>
					<option value="" disabled selected>-- Seleccione el plazo
						de pago --</option>
					<option value="3">3 Meses</option>
					<option value="6">6 Meses</option>
					<option value="12">12 Meses</option>
					<option value="18">18 Meses</option>
					<option value="24">24 Meses</option>
				</select>
			</div>

			<div>
				<label for="importe_soliciado_prestamo" class="block text-lg">Importe
					solicitado</label> <input type="text" id="importe_soliciado_prestamo"
					name="importe_solicitado"
					class="w-full bg-gray-800 text-gray-100 rounded-md px-4 py-2"
					oninput="javascript:monto_pago_prestamo_dinamico()"
					onkeypress="return (event.charCode >= 48 && event.charCode <= 57)"
					required>
			</div>

			<div>
				<label for="Valor_Cuotas" class="block text-lg">Valor de
					cuotas (cada una)</label> <input type="text" id="Valor_Cuotas"
					name="Valor_Cuotas"
					class="w-full bg-gray-800 text-gray-100 rounded-md px-4 py-2"
					value="0.0" readonly>
			</div>

			<div>
				<label for="Importe_Total" class="block text-lg">Importe
					total (más intereses)</label> <input type="text" id="Importe_Total"
					name="Importe_Total"
					class="w-full bg-gray-800 text-gray-100 rounded-md px-4 py-2"
					value="0.0" readonly>
			</div>

			<div>
				<button type="submit"
					onclick="javascript:validarFormularioSolicitudPr();"
					class="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded-md">
					Solicitar Préstamo</button>
			</div>
		</form>
	</section>
	</main>
</body>
</html>
