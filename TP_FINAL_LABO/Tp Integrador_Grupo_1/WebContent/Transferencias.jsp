<%@page import="java.util.ListIterator"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Transferencias</title>
<script src="https://cdn.tailwindcss.com"></script>
<script type="text/javascript">
	function validarMonto() {
		var monto = document.getElementById("Monto-transferencia").value;
		monto = monto.replace(",", ".");
		if (monto === "" || isNaN(monto) || parseFloat(monto) <= 0) {
			alert("Por favor, ingrese un monto válido mayor a cero.");
			return false;
		}
		var confirmar = confirm("¿Está seguro de que desea realizar esta transferencia?");
		return confirmar;
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
	<main class="flex-1 p-8"> <header class="mb-8">
		<h2 class="text-3xl font-semibold">Transferencias</h2>
		<p class="text-gray-400">Complete el formulario para realizar una
			transferencia.</p>
	</header>

	<section>
		<%
			String mensaje;
			String color = "text-gray-400";
			if (request.getAttribute("mensaje") != null) {
				mensaje = (String) request.getAttribute("mensaje");
				if (mensaje.equals("Transferencia realizada exitosamente")) {
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
		<form action="ServletControladores" onsubmit="return validarMonto();"
			method="POST" class="space-y-6">
			<div>
				<label for="Cuenta-origen-transferencia" class="block text-lg">Cuenta
					Origen</label> <select id="Cuenta-origen-transferencia"
					name="Cuenta-origen"
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
						<%=cuenta.getNumeroDecuenta_Cuen()%> - Saldo: $<%=cuenta.getSaldo_Cuen()%>
					</option>

					<%
						}
						}
					%>
				</select>
			</div>

			<div>
				<label for="CBU-destino-transferencia" class="block text-lg">CBU
					Destino</label> <input id="CBU-destino-transferencia" name="CBU-destino"
					type="text" maxlength="22"
					class="w-full bg-gray-800 text-gray-100 rounded-md px-4 py-2"
					onkeypress="return (event.charCode >= 48 && event.charCode <= 57)"
					required>
			</div>

			<div>
				<label for="Monto-transferencia" class="block text-lg">Monto</label>
				<input id="Monto-transferencia" name="Monto-transferencia"
					type="text"
					class="w-full bg-gray-800 text-gray-100 rounded-md px-4 py-2"
					onkeypress="return (event.charCode >= 48 && event.charCode <= 57) || event.charCode === 46"
					required>
			</div>

			<div>
				<button type="submit" name="btn_transferencia"
					class="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded-md">
					Realizar Transferencia</button>
			</div>
		</form>
	</section>
	</main>
</body>
</html>

