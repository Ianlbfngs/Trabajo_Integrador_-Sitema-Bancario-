<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Informes</title>

<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<script src="JavaScript/filtros.js" type="text/JavaScript"></script>
<script src="JavaScript/validaciones.js" type="text/JavaScript"></script>
</head>
<body class="bg-gray-100">
<div class="flex min-h-screen">

    <!-- Sidebar -->
   <aside class="w-64 bg-gray-800 text-white min-h-screen flex flex-col">
    <!-- Logo y encabezado -->
    <div class="p-4 bg-gray-900 flex flex-col items-center justify-center">
        <h1 class="text-lg font-bold">Banco XYZ</h1>
              <%
      String Nombre;
    
      if(request.getAttribute("usuario") != null){
    	  
    	  Nombre = (String)request.getAttribute("usuario");
      }else{
    	  Nombre ="";
      }
    
    %>
    
<h2> <%= Nombre %></h2>
    </div>
    <!-- Navegación -->
        <nav class="flex-1 mt-4">
            <ul class="space-y-2">
                <li>
                    <a href="ServletControladores?Pagina=menuAdmin" 
                       class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                        <svg class="w-5 h-5 mr-3 text-gray-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 9h18M3 15h18M3 21h18" />
                        </svg>
                        Menú Principal
                    </a>
                </li>
                <li>
                    <a
                    
                       class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                       
                        </svg>
                         Cliente
                    </a>
                </li>
                <li>
                    <a href="ServletControladores?Pagina=altaCliente" 
                       class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                        <svg class="w-5 h-5 mr-3 text-gray-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
                        </svg>
                        Alta de Cliente
                    </a>
                </li>
                <li>
                    <a href="ServletControladores?Pagina=bajaCliente" 
                       class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                        <svg class="w-5 h-5 mr-3 text-gray-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                        </svg>
                        Baja de Cliente
                    </a>
                </li>
                <li>
                       <li>
                    <a href="ServletControladores?Pagina=listarClientes" 
                       class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                        <svg class="w-5 h-5 mr-3 text-gray-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
                        </svg>
                        Lista de Cliente
                    </a>
                    
                </li>
                  <li>
                       <li>
                    <a href="ServletControladores?Pagina=autorizarPrestamo" 
                       class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                        <svg class="w-5 h-5 mr-3 text-gray-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
                        </svg>
                        Autorizacion de Prestamos 
                    </a>
                    
                </li>
                    <a
                          class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                       
                        </svg>
                         Cuenta
                    </a>
                </li>
                 <li>
                    <a href="ServletControladores?Pagina=altaCuenta" 
                       class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                        <svg class="w-5 h-5 mr-3 text-gray-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
                        </svg>
                        Alta de Cuenta
                    </a>
                    
                </li>
                    
                  <li>
                        <a href="ServletControladores?Pagina=bajaCuenta"
                             class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                        <svg class="w-5 h-5 mr-3 text-gray-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                        </svg>
                            Baja de Cuenta
                        </a>
                    </li>
                     <li>
                    <a href="ServletControladores?Pagina=listarCuentas" 
                       class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                        <svg class="w-5 h-5 mr-3 text-gray-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
                        </svg>
                        Lista de Cuenta
                    </a>
                    
                </li>
                     <li>
                    <a href="ServletControladores?Pagina=informesAdmin" 
                       class="flex items-center px-4 py-2 text-sm font-medium text-gray-200 hover:bg-gray-700 rounded">
                        <svg class="w-5 h-5 mr-3 text-gray-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h14M12 5l7 7-7 7" />
                        </svg>
                        Informes y Reportes
                    </a>
                    
                </li>
                
            </ul>
        </nav>
    <!-- Footer del Sidebar -->
    <div class="bg-gray-900 p-4">
        <p class="text-xs text-gray-400 text-center">&copy; 2024 Banco XYZ</p>
    </div>
  </aside>
    <!-- Contenido principal -->
    <div class="flex-1">
        <header class="bg-gray-700 text-white p-4 flex justify-between items-center">
            <h2 class="text-xl font-semibold">Sistema Bancario</h2>
            <a href="ServletControladores?Pagina=menuAdmin" class="text-sm text-gray-300 hover:text-white">Regresar al menú</a>
        </header>
                <main class="p-6">
                     <div class="bg-white p-6 rounded-lg shadow-md max-w-3xl mx-auto">
                <h2 class="text-xl font-bold text-gray-700 mb-6">Informes</h2>
                 <form name="form_informe" action="ServletControladores" class="space-y-6" method="post">
            <p >Informe 1: La cantidad de cuentas creadas en el año 
            	<%
            		if(request.getAttribute("resultado_informe_1")==null){
            	%>
            	<select id="informe_1_filtro_id" name="informe_1_filtro" >
 					<option value="0">----</option>
                    <% 
						for(int i = 2020;i<=LocalDate.now().getYear();i++){
                    %>
                     <option value="<%=i%>"><%=i%></option>
                    <% 
						}
                    %>
                </select>
                es de: <label > X </label>
            
           		<input type="hidden" name="btn_informe_1" value="-1">
           		<%
           			}else{
          		%>
           		<label id="informe_1_filtro_seleccionado_id" ><%= request.getAttribute("filtro_informe_1") %></label>
         	    es de <label ><%= request.getAttribute("resultado_informe_1") %></label>
				<input type="hidden" name="informe_1_filtro_seleccionado" value="<%= request.getAttribute("filtro_informe_1") %>">
           		<input type="hidden"  name="btn_informe_1" value="<%= request.getAttribute("resultado_informe_1") %>">
           		
           		<%
         		  	}
          		%>
          		<input type="hidden" id="informe_1_flag_id"name="informe_1_flag" value="0">
            </p>    
       
                <p class="">
            	<%
            		if(request.getAttribute("resultado_informe_2")==null){
            	%>
            	Informe 2: El cliente con el DNI <input  class="p-2 border rounded w-24" id="informe_2_filtro_dni_id" name="informe_2_filtro_dni" type="text" maxlength="8" style="width: 5% ;" onkeypress="return (event.charCode >= 48 && event.charCode <= 57)">
                en el año
            	<select id="informe_2_filtro_fecha_id" name="informe_2_filtro_fecha" >
 					<option value="0">----</option>
                    <% 
						for(int i = 2020;i<=LocalDate.now().getYear();i++){
                    %>
                     <option value="<%=i%>"><%=i%></option>
                    <% 
						}
                    %>
                </select>
                gasto un total de <label > X$ </label>
            
           		<input type="hidden" name="btn_informe_2" value="-1">
           		<%
           			}else{
          		%>
          		Informe 2: El cliente con el DNI <label><%= request.getAttribute("filtro_dni_informe_2") %></label>
                en el año
           		<label ><%= request.getAttribute("filtro_fecha_informe_2") %></label>
         	    gasto un total de <label ><%= request.getAttribute("resultado_informe_2") %>$</label>
				<input type="hidden" name="informe_2_filtro_dni_seleccionado" value="<%= request.getAttribute("filtro_dni_informe_2") %>">
				<input type="hidden" name="informe_2_filtro_fecha_seleccionado" value="<%= request.getAttribute("filtro_fecha_informe_2") %>">
           		<input type="hidden"  name="btn_informe_2" value="<%= request.getAttribute("resultado_informe_2") %>">
           		
           		<%
         		  	}
          		%>
          		<input type="hidden" id="informe_2_flag_id"name="informe_2_flag" value="0">
            </p>  
            
            
            
            <p>Informe 3: El porcentaje total de cuotas 
                        	<%
            		if(request.getAttribute("resultado_informe_3")==null){
            	%>
                <select id="informe_3_filtro_id" name="informe_3_filtro" >
                    <option value="0">----</option>
                    <option value="1">pagas</option>
                    <option value="2">impagas</option>
                </select> 
                es del <label >X%</label>
            
         	  <input type="hidden" name="btn_informe_3" value="-1">
                      		<%
           			}else{
          		%>
          		<label id="informe_3_filtro_seleccionado_id" ><%if(request.getAttribute("filtro_informe_3").equals("1")||request.getAttribute("filtro_informe_3").equals(1) ){%>pagas<%}else{ %>impagas<%} %></label>
         	    es del <label><%= request.getAttribute("resultado_informe_3")+"%" %></label>
				<input type="hidden" name="informe_3_filtro_seleccionado" value="<%= request.getAttribute("filtro_informe_3") %>">
           		<input type="hidden"  id="informe_3_resultado_id" name="btn_informe_3" value="<%= request.getAttribute("resultado_informe_3") %>">
                      		<%
         		  	}
          		%>
          		<input type="hidden" id="informe_3_flag_id"name="informe_3_flag" value="0">
          	</p>
            </form>
              <div class="flex gap-6 p-6">
            <button name="btn_informe_1" class="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600 transition"  type="button" onclick="javascript:informe();">Generar informes</button>
            <button name="btn_limpiar_informe_1" class="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400 transition" " type="button" onclick="javascript:informe_1_limpiar();">Limpiar primer informe</button>
            <button name="btn_limpiar_informe_2" class="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400 transition" type="button" onclick="javascript:informe_2_limpiar();">Limpiar segundo informe</button>
            <button name="btn_limpiar_informe_3" class="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400 transition" type="button" onclick="javascript:informe_3_limpiar();">Limpiar tercer informe</button>

        </div>
        </main>
    </div>

</div>

</body>
</html>
