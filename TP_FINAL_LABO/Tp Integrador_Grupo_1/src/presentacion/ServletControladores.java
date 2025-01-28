package presentacion;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.GroupLayout;

import org.apache.tomcat.jni.Local;

import com.sun.org.apache.xpath.internal.operations.And;

import datos.SexoDao;
import datosimpl.AdministradorDaoImpl;
import datosimpl.PrestamoDaoImpl;
import datosimpl.SexoDaoImpl;
import entidad.Administrador;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Localidad;
import entidad.Movimiento;
import entidad.PagoDeCuotas;
import entidad.Prestamo;
import entidad.Provincia;
import entidad.Sexo;
import entidad.TipoDeCuenta;
import exceptions.ClienteDuplicadoException;
import exceptions.MovimientoCanceladoException;
import jdk.internal.org.objectweb.asm.tree.InvokeDynamicInsnNode;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocioImpl.AdministradorNegImpl;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;
import negocioImpl.LocalidadNegImpl;
import negocioImpl.MovimientoNegImpl;
import negocioImpl.PagoDeCuotaNegImpl;
import negocioImpl.PrestamoNegImpl;
import negocioImpl.ProvinciaNegImpl;
import negocioImpl.SexoNegImpl;
import negocioImpl.TipoCuentaNegImpl;

/**
 * Servlet implementation class ServletControladores
 */
@WebServlet("/ServletControladores")
public class ServletControladores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletControladores() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		String dni = null;
		String IDAdmin = null;

		// Buscar la cookie con el nombre "usuarioDni" y "AdminUser"
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("usuarioDni")) {
					dni = cookie.getValue();
					break;
				} else if (cookie.getName().equals("AdminUser")) {
					IDAdmin = cookie.getValue();
					break;
				}
			}
		}

		if (request.getParameter("Pagina") != null) {

			String Pagina = request.getParameter("Pagina");

			switch (Pagina) {

			/* Login */
			case "login":

				if (cookies != null) {
					for (Cookie cookie : cookies) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
				RequestDispatcher rdLogin = request.getRequestDispatcher("/Login.jsp");
				rdLogin.forward(request, response);
				break;

			/* Menus */
			case "menuAdmin":
				System.out.println(IDAdmin);
				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);
					System.out.println("test: " + request.getParameter("usuario"));

					RequestDispatcher rdMenuCliente = request.getRequestDispatcher("/Menu Administrador.jsp");
					rdMenuCliente.forward(request, response);
				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				break;

			case "menuCliente":
				// Validar si se encontró la cookie
				if (dni != null) {
					// Buscar al cliente utilizando el DNI de la cookie
					ClienteNegImpl clienteNegImp = new ClienteNegImpl();
					Cliente clienRs = new Cliente();
					clienRs.setDni_cli(dni);
					clienRs = clienteNegImp.buscarClientePorDni(clienRs);

					// Configurar atributos para pasar a la JSP
					String nombre = clienRs.getNombre_cli() + " " + clienRs.getApellido_cli();
					request.setAttribute("nombre", nombre);
					request.setAttribute("dni", dni);

					// Redirigir a la página de menú del cliente
					RequestDispatcher rdMenuCliente = request.getRequestDispatcher("/Menu Cliente.jsp");
					rdMenuCliente.forward(request, response);
				} else {
					// Si no hay cookie, redirigir al login con un mensaje de error
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}
				break;

			/* MenuAdministrador */
			case "altaCliente":

				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);
					System.out.println("test: " + request.getParameter("usuario"));

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				if (request.getParameter("opcion") != null) {
					switch (Integer.parseInt(request.getParameter("opcion"))) {
					case -1:
						request.setAttribute("mensaje", "No fue posible dar de alta el cliente");
					case 0:
						request.setAttribute("mensaje", "No fue posible dar de alta el cliente");
					case 1:
						request.setAttribute("mensaje", "Cliente registrada exitosamente");
						break;
					case 2:
						request.setAttribute("mensaje", "El dni ya esta en uso");
						break;
					case 3:
						request.setAttribute("mensaje", "Elija otro usuario");
						break;
					case 4:
						request.setAttribute("mensaje", "El CUIL ya esta en uso");

					}

				}

				// carga la lista de sexos
				SexoNegImpl sxNegImpl = new SexoNegImpl();
				List<Sexo> listaS = sxNegImpl.readAll();
				request.setAttribute("listaS", listaS);
				// carga la lista de provincias
				ProvinciaNegImpl pvNegImpl = new ProvinciaNegImpl();
				List<Provincia> listaP = pvNegImpl.readAll();
				request.setAttribute("listaP", listaP);
				// carga la lista de localidades, se filtra despues
				LocalidadNegImpl lcNegImp = new LocalidadNegImpl();
				List<Localidad> listaL = lcNegImp.readAll();
				request.setAttribute("listaL", listaL);

				RequestDispatcher rdAltaCliente = request.getRequestDispatcher("/Alta Cliente.jsp");
				rdAltaCliente.forward(request, response);

				break;

			case "altaCuenta":

				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);
					System.out.println("test: " + request.getParameter("usuario"));

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				// carga el desplegable de tipo de cuenta
				TipoCuentaNegImpl tcuentaNegImpl = new TipoCuentaNegImpl();
				List<TipoDeCuenta> listaTC = tcuentaNegImpl.readAll();
				request.setAttribute("listaTC", listaTC);

				if (request.getParameter("opcion") != null) {
					switch (Integer.parseInt(request.getParameter("opcion"))) {
					case -1:
						request.setAttribute("mensaje", "No fue posible registrar la cuenta.");
					case 0:
						break;
					case 1:
						request.setAttribute("mensaje", "Cuenta registrada exitosamente.");
						break;
					case 2:
						request.setAttribute("mensaje", "El CBU ya esta en uso, por favor elija otro");
						break;
					case 3:
						request.setAttribute("mensaje", "El cliente no existe en la base de datos");
						break;
					case 4:
						request.setAttribute("mensaje", "El cliente ya tiene 3 cuentas creadas");
						break;
					}

				}

				RequestDispatcher rdAltaCuenta = request.getRequestDispatcher("/Alta Cuenta.jsp");
				rdAltaCuenta.forward(request, response);
				break;

			case "autorizarPrestamo":

				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				PrestamoNegImpl prNegImpl = new PrestamoNegImpl();
				List<Prestamo> listaPrAuth = prNegImpl.readAll();
				request.setAttribute("listaPrAuth", listaPrAuth);

				if (request.getParameter("opcion") != null) {
					if (Integer.parseInt(request.getParameter("opcion")) == 1) {
						request.setAttribute("mensaje", "Prestamo autorizado con exito");

					} else if (Integer.parseInt(request.getParameter("opcion")) == 0) {
						request.setAttribute("mensaje", "No fue posible autorizar el prestamo");

					} else if (Integer.parseInt(request.getParameter("opcion")) == 2) {
						request.setAttribute("mensaje", "Prestamo rechazado con exito");

					} else if (Integer.parseInt(request.getParameter("opcion")) == 3) {
						request.setAttribute("mensaje", "No fue posible rechazar el prestamo");

					}
				}

				RequestDispatcher rdAutorizarPrestamo = request.getRequestDispatcher("/Autorizacion de Prestamo.jsp");
				rdAutorizarPrestamo.forward(request, response);
				break;

			case "bajaCliente":

				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				ClienteNegImpl cliNegImpl = new ClienteNegImpl();
				List<Cliente> listacli = cliNegImpl.readAll();
				request.setAttribute("listaClientes", listacli);

				if (request.getParameter("opcion") != null) {
					switch (Integer.parseInt(request.getParameter("opcion"))) {
					case 0:
						request.setAttribute("mensaje",
								"No existe el DNI del cliente ingresado/El cliente ya esta dada de baja");
						break;
					case 1:
						request.setAttribute("mensaje", "Cliente eliminado con exito. ");

						break;
					case 2:
						request.setAttribute("mensaje", "Las cuentas del cliente tienen saldo");
						break;
					default:
						break;
					}

				}

				RequestDispatcher rdBajaCliente = request.getRequestDispatcher("/Baja Cliente.jsp");
				rdBajaCliente.forward(request, response);
				break;

			case "bajaCuenta":
				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}
				CuentaNegImpl cueNegImpl = new CuentaNegImpl();
				List<Cuenta> listacuentas = cueNegImpl.readAll();
				request.setAttribute("listaCuentas", listacuentas);

				if (request.getParameter("opcion") != null) {
					if (Integer.parseInt(request.getParameter("opcion")) == 1) {
						request.setAttribute("mensaje", "Cuenta eliminada exitosamente.");
					} else {
						request.setAttribute("mensaje",
								"No existe el numero de cuenta/La cuenta ya esta dada de baja/La cuenta todavia tiene saldo");
					}
				}

				RequestDispatcher rdBajaCuenta = request.getRequestDispatcher("/Baja Cuenta.jsp");
				rdBajaCuenta.forward(request, response);
				break;

			case "informesAdmin":

				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}
				RequestDispatcher rdInformesAdmin = request.getRequestDispatcher("/Informes Administrador.jsp");
				rdInformesAdmin.forward(request, response);
				break;

			case "listarClientes":

				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);
					
															
					
				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}
				cliNegImpl = new ClienteNegImpl();
				List<Cliente> listacli1 =null;
				if(request.getParameter("filtro_dni_ma") == null && request.getParameter("filtro_dni_me") == null) {
					 listacli1 = cliNegImpl.readAll();
				}else{
					String mayor = request.getParameter("filtro_dni_ma");
					String menor = request.getParameter("filtro_dni_me");

					listacli1 = cliNegImpl.readFiltrado(mayor, menor);
				}
				
				request.setAttribute("listaClientes", listacli1);
				sxNegImpl = new SexoNegImpl();
				List<Sexo> listagen = sxNegImpl.readAll();
				request.setAttribute("listaS", listagen);

				pvNegImpl = new ProvinciaNegImpl();
				List<Provincia> listaPModificar = pvNegImpl.readAll();
				request.setAttribute("listaP", listaPModificar);
				lcNegImp = new LocalidadNegImpl();
				List<Localidad> listaL_Update = lcNegImp.readAll();
				request.setAttribute("listaL", listaL_Update);

				RequestDispatcher rdListarClientes = request.getRequestDispatcher("/Listar Clientes.jsp");
				rdListarClientes.forward(request, response);
				break;

			case "listarCuentas":

				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;

				}

				CuentaNegImpl cueNegImpl4 = new CuentaNegImpl();
				
				
				List<Cuenta> listacuentas1 =null;
				if(request.getParameter("filtroMayor") == null && request.getParameter("filtroMenor") == null) {
					listacuentas1 = cueNegImpl4.readAll();
				}else{
					String mayor = request.getParameter("filtroMayor");
					String menor = request.getParameter("filtroMenor");

					listacuentas1 = cueNegImpl4.FlitrarCuentas(mayor, menor);
				}
																											
				request.setAttribute("listaCuentas", listacuentas1);

				RequestDispatcher rdListarCuentas = request.getRequestDispatcher("/Listar Cuentas.jsp");
				rdListarCuentas.forward(request, response);																			
				
				
				break;
				
				

			case "EditarClientes":

				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);
					System.out.println("test: " + request.getParameter("usuario"));

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				if (request.getParameter("ClienteEditar") != null) {
					String Dni = request.getParameter("ClienteEditar");
					ClienteNegImpl clienteNegImp = new ClienteNegImpl();
					Cliente clienRs = new Cliente();
					clienRs.setDni_cli(Dni);
					clienRs = clienteNegImp.buscarClientePorDni(clienRs);
					request.setAttribute("ClienteEditar", clienRs);

				}
				// carga la lista de sexos
				SexoNegImpl sxNegImpl1 = new SexoNegImpl();
				List<Sexo> listaS1 = sxNegImpl1.readAll();
				request.setAttribute("listaS", listaS1);
				// carga la lista de provincias
				ProvinciaNegImpl pvNegImpl1 = new ProvinciaNegImpl();
				List<Provincia> listaP1 = pvNegImpl1.readAll();
				request.setAttribute("listaP", listaP1);
				// carga la lista de localidades, se filtra despues
				LocalidadNegImpl lcNegImp1 = new LocalidadNegImpl();
				List<Localidad> listaL1 = lcNegImp1.readAll();
				request.setAttribute("listaL", listaL1);

				if (request.getParameter("opcion") != null) {
					switch (Integer.parseInt(request.getParameter("opcion"))) {
					case 0:
						request.setAttribute("mensaje", "No fue posible actualizar el cliente");
						break;
					case 1:
						request.setAttribute("mensaje", "Cliente actualizado correctamente");
						break;
					case 2:
						request.setAttribute("mensaje", "No es posible actualizar el usuario del cliente");
						break;
					case 3:
						request.setAttribute("mensaje", "No es posible actualizar el CUIL del cliente");
						break;
					default:
						break;
					}
				}

				RequestDispatcher rdEditarCliente = request.getRequestDispatcher("/Editar Cliente.jsp");
				rdEditarCliente.forward(request, response);
				break;

			case "EditarCuentas":

				if (IDAdmin != null) {
					// Buscar al cliente utilizando el ID de la cookie
					AdministradorNegImpl AdminNegImp = new AdministradorNegImpl();
					Administrador AdminRs = new Administrador();
					AdminRs.setIdAdministraodr_Admin(Integer.valueOf(IDAdmin));
					AdminRs = AdminNegImp.buscarAdminPorid(AdminRs);

					String User = AdminRs.getUsuario_Admin();

					request.setAttribute("usuario", User);
					System.out.println("test: " + request.getParameter("usuario"));

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				if (request.getParameter("CuentaEditar") != null) {
					TipoCuentaNegImpl tcuentaNegImplModificar = new TipoCuentaNegImpl();
					List<TipoDeCuenta> listaTCmodificar = tcuentaNegImplModificar.readAll();
					request.setAttribute("listaTC", listaTCmodificar);
					String id = request.getParameter("CuentaEditar");
					Cuenta cuentaEditar = new Cuenta();
					CuentaNegImpl cuentaNegImp = new CuentaNegImpl();
					cuentaEditar = cuentaNegImp.readone(id);
					request.setAttribute("CuentaEditar", cuentaEditar);

				}

				if (request.getParameter("opcion") != null) {
					switch (Integer.parseInt(request.getParameter("opcion"))) {
					case 0:
						request.setAttribute("mensaje", "No fue posible actualizar la cuenta");
						break;
					case 1:
						request.setAttribute("mensaje", "Cuenta actualizada correctamente");
						break;
					case 2:
						request.setAttribute("mensaje", "No es posible actualizar el DNI de la cuenta");
						break;
					case 3:
						request.setAttribute("mensaje", "No es posible actualizar el CBU de la cuenta");
						break;
					case 4:
						request.setAttribute("mensaje", "No existe ningun cliente con el DNI");
						break;
					default:
						break;
					}
				}

				RequestDispatcher rdEditarCuentas = request.getRequestDispatcher("/Editar Cuenta.jsp");
				rdEditarCuentas.forward(request, response);
				break;

			/* MenuCliente */
			case "pagarPrestamo":

				if (dni != null) {
					// Buscar al cliente utilizando el DNI de la cookie
					ClienteNegImpl clienteNegImp = new ClienteNegImpl();
					Cliente clienRs = new Cliente();
					clienRs.setDni_cli(dni);
					clienRs = clienteNegImp.buscarClientePorDni(clienRs);

					// Configurar atributos para pasar a la JSP
					String nombre = clienRs.getNombre_cli() + " " + clienRs.getApellido_cli();
					request.setAttribute("nombre", nombre);
					request.setAttribute("dni", dni);

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				PrestamoNegImpl prListarNegImpl = new PrestamoNegImpl();
				List<Prestamo> prLista = prListarNegImpl.readAllFiltrado(dni);
				request.setAttribute("listaPrestamos", prLista);

				CuentaNegImpl cuentaPPrNegImpl = new CuentaNegImpl(); // PPr = Pagar Pr-estamo
				List<Cuenta> listaCnPPr = cuentaPPrNegImpl.readCuentasConDNI(dni);
				request.setAttribute("listaCnPPr", listaCnPPr);
				RequestDispatcher rdPagarPrestamo = request.getRequestDispatcher("/Pagar Prestamos.jsp");
				rdPagarPrestamo.forward(request, response);
				break;

			case "pedidoPrestamo":

				if (dni != null) {
					// Buscar al cliente utilizando el DNI de la cookie
					ClienteNegImpl clienteNegImp = new ClienteNegImpl();
					Cliente clienRs = new Cliente();
					clienRs.setDni_cli(dni);
					clienRs = clienteNegImp.buscarClientePorDni(clienRs);

					// Configurar atributos para pasar a la JSP
					String nombre = clienRs.getNombre_cli() + " " + clienRs.getApellido_cli();
					request.setAttribute("nombre", nombre);
					request.setAttribute("dni", dni);

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				CuentaNegImpl cuentaPrNegImpl = new CuentaNegImpl();
				List<Cuenta> listaCnPr = cuentaPrNegImpl.readCuentasConDNI(dni);

				request.setAttribute("listaCnPr", listaCnPr);
				request.setAttribute("dniCliente", dni);
				if (request.getParameter("opcion") != null) {
					switch (Integer.parseInt(request.getParameter("opcion"))) {
					case 0:
						request.setAttribute("mensaje", "No fue posible solicitar el prestamo");
						break;
					case 1:
						request.setAttribute("mensaje", "Prestamo solicitado exitosamente");
						break;
					default:

						break;
					}
				}
				RequestDispatcher rdPedidoPrestamo = request.getRequestDispatcher("/Pedido de Prestamo.jsp");
				rdPedidoPrestamo.forward(request, response);
				break;

			case "revisarInfoPersonal":

				if (dni != null) {
					// Buscar al cliente utilizando el DNI de la cookie
					ClienteNegImpl clienteNegImp = new ClienteNegImpl();
					Cliente clienRs = new Cliente();
					clienRs.setDni_cli(dni);
					clienRs = clienteNegImp.buscarClientePorDni(clienRs);

					// Configurar atributos para pasar a la JSP
					String nombre = clienRs.getNombre_cli() + " " + clienRs.getApellido_cli();
					request.setAttribute("nombre", nombre);
					request.setAttribute("dni", dni);

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;

				}

				RequestDispatcher rdInfoPersonal = request.getRequestDispatcher("/Revisar Informacion Personal.jsp");
				// Buscar al cliente utilizando el DNI de la cookie
				Cliente clienRs = new Cliente();
				ClienteNegImpl clienteNegImp = new ClienteNegImpl();

				clienRs.setDni_cli(dni);
				clienRs = clienteNegImp.buscarClientePorDni(clienRs);
				request.setAttribute("cliente", clienRs);
				rdInfoPersonal.forward(request, response);

				break;

			case "transferencias":
				if (dni != null) {
					// Buscar al cliente utilizando el DNI de la cookie
					ClienteNegImpl clienteNegImp1 = new ClienteNegImpl();
					Cliente clienRs1 = new Cliente();
					clienRs1.setDni_cli(dni);
					clienRs1 = clienteNegImp1.buscarClientePorDni(clienRs1);

					// Configurar atributos para pasar a la JSP
					String nombre = clienRs1.getNombre_cli() + " " + clienRs1.getApellido_cli();
					request.setAttribute("nombre", nombre);
					request.setAttribute("dni", dni);

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				CuentaNegImpl cuentaTrNegImpl = new CuentaNegImpl();
				List<Cuenta> listaCnTr = cuentaTrNegImpl.readCuentasConDNI(dni);
				request.setAttribute("listaCnPr", listaCnTr);
				if (request.getParameter("opcion") != null) {
					switch (Integer.parseInt(request.getParameter("opcion"))) {
					case 1:
						request.setAttribute("mensaje", "Transferencia realizada exitosamente");
						break;
					case 2:
						request.setAttribute("mensaje", "No fue posible realizar la transferencia");
						break;
					case 3:
						request.setAttribute("mensaje", "Saldo de cuenta insuficiente");
						break;
					case 4:
						request.setAttribute("mensaje",
								"No puede realizar transferencias a la cuenta misma cuenta de la cual se pretende hacer la transferencia");
						break;
					case 5:
						request.setAttribute("mensaje", "CBU inexistente");
						break;
					default:

						break;
					}
				}
				RequestDispatcher rdTransferencias = request.getRequestDispatcher("Transferencias.jsp");
				rdTransferencias.forward(request, response);
				break;

			case "verMovimientos":

				if (dni != null) {
					// Buscar al cliente utilizando el DNI de la cookie
					ClienteNegImpl clienteNegImp1 = new ClienteNegImpl();
					Cliente clienRs1 = new Cliente();
					clienRs1.setDni_cli(dni);
					clienRs1 = clienteNegImp1.buscarClientePorDni(clienRs1);

					// Configurar atributos para pasar a la JSP
					String nombre = clienRs1.getNombre_cli() + " " + clienRs1.getApellido_cli();
					request.setAttribute("nombre", nombre);
					request.setAttribute("dni", dni);

				} else {
					request.setAttribute("mensajeError", "Sesion expirada");
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=login");
					break;
				}

				CuentaNegImpl cueNegImpl21 = new CuentaNegImpl();
				MovimientoNegImpl movNegImpl1 = new MovimientoNegImpl();

				// Obtener la lista de cuentas del usuario
				List<Cuenta> listacuentas2 = cueNegImpl21.readCuentasConDNI(dni);
				List<Movimiento> listamovimientos1 = new ArrayList<>();
				// Lista de CBUs
				List<String> listaCBUs = new ArrayList<>();

				for (Cuenta cuenta : listacuentas2) {
					listaCBUs.add(cuenta.getCBU_Cuen());
				}

				String filtroCuenta = request.getParameter("filtroCuenta");

				if (filtroCuenta == null || filtroCuenta.equals("0")) {
					// Opcion "Listar todos": No aplicar filtros, incluir todos los movimientos
					if (listacuentas2 != null) {
						for (Cuenta cuenta : listacuentas2) {
							List<Movimiento> movimientos = movNegImpl1
									.readMismoDniCliente(cuenta.getNumeroDecuenta_Cuen(), cuenta.getCBU_Cuen());
							if (movimientos != null) {
								listamovimientos1.addAll(movimientos);
							}
						}
					}
				} else {
					// Filtrar por una cuenta especifico
					String[] cuentaData = filtroCuenta.split(",");
					if (cuentaData.length == 2) {
						String numeroCuenta = cuentaData[0];
						String cbuCuenta = cuentaData[1];

						List<Movimiento> movimientos = movNegImpl1.readMismoDniCliente(numeroCuenta, cbuCuenta);
						if (movimientos != null) {
							listamovimientos1.addAll(movimientos);
						}
						request.setAttribute("filtroAplicado", numeroCuenta);
					}
				}
				// Pasar las listas de cuentas y movimientos al request
				listamovimientos1.sort(Comparator.comparingInt(Movimiento::getIDMovimientos));
				request.setAttribute("listaCuentasdelUsuario", listacuentas2);
				request.setAttribute("listaMovimientosCuentas", listamovimientos1);
				request.setAttribute("listaCBUs", listaCBUs);

				// Redirigir a la JSP
				RequestDispatcher rdVerMovimientos = request.getRequestDispatcher("Ver Movimientos Cuenta.jsp");
				rdVerMovimientos.forward(request, response);
				break;

			/* Default no hace nada */
			default:
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		String dni = null;

		// Buscar la cookie con el nombre "usuarioDni"
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("usuarioDni")) {
					dni = cookie.getValue();
					break;
				}
			}
		}
		// JSP AGREGAR LOGIN
		if (request.getParameter("btnLogin") != null) {
			AdministradorNegImpl adminNegImp = new AdministradorNegImpl();
			ClienteNegImpl clienteNegImp = new ClienteNegImpl();
			Cliente clienRs = new Cliente();
			clienRs.setUsuario_cli(request.getParameter("usuario").trim());
			clienRs.setContrasena_cli(request.getParameter("password").trim());

			Administrador adminRs = new Administrador();
			adminRs.setUsuario_Admin(request.getParameter("usuario").trim());
			adminRs.setContrasenia_Admin(request.getParameter("password").trim());
			try {
				if (adminNegImp.readOne(adminRs)) {
					adminRs.setIdAdministraodr_Admin(adminNegImp.BuscarId(adminRs));
					adminRs = adminNegImp.buscarAdminPorid(adminRs);

					String idAdminStr = String.valueOf(adminRs.getIdAdministraodr_Admin());
					Cookie CuentaAdmin = new Cookie("AdminUser", idAdminStr);
					CuentaAdmin.setPath("/");
					CuentaAdmin.setMaxAge(60 * 60 * 24);
					response.addCookie(CuentaAdmin);

					request.setAttribute("usuario", adminRs.getUsuario_Admin());
					RequestDispatcher rdMenuAdmin = request.getRequestDispatcher("/Menu Administrador.jsp");
					rdMenuAdmin.forward(request, response);

				} else if (clienteNegImp.readOne(clienRs)) {
					clienRs.setDni_cli(clienteNegImp.BuscarDni(clienRs));
					clienRs = clienteNegImp.buscarClientePorDni(clienRs);
					String nombre = clienRs.getNombre_cli() + " " + clienRs.getApellido_cli();
					String dni1 = clienRs.getDni_cli();

					Cookie CuentaUsuario = new Cookie("usuarioDni", dni1);
					CuentaUsuario.setPath("/");
					CuentaUsuario.setMaxAge(60 * 60 * 24);
					response.addCookie(CuentaUsuario);

					request.setAttribute("nombre", nombre);
					request.setAttribute("dni", dni1);

					RequestDispatcher rdMenuCliente = request.getRequestDispatcher("/Menu Cliente.jsp");
					rdMenuCliente.forward(request, response);
				} else {
					RequestDispatcher rdLogin = request.getRequestDispatcher("/Login.jsp");
					request.setAttribute("mensaje", "Usuario/Contraseña incorrecto/s");
					rdLogin.forward(request, response);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// JSP AGREGAR CLIENTE
		if (request.getParameter("btnAltaCliente") != null) {
			Cliente clienteAlta = new Cliente();
			ClienteNegImpl clienteNegImp = new ClienteNegImpl();
			Administrador admin = new Administrador();
			AdministradorNegImpl adminNegimpl = new AdministradorNegImpl();
			admin.setUsuario_Admin(request.getParameter("usuario").trim());
			try {
				if (adminNegimpl.usuarioRepetido(admin)) {
					throw new ClienteDuplicadoException();
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("mensajeError", "El nombre de usuario ya está en uso. Por favor, elija otro.");
				response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=altaCliente");
				return;
			}

			clienteAlta.setDni_cli(request.getParameter("dni").trim());
			clienteAlta.setIdGenero_cli(Integer.parseInt(request.getParameter("sexo")));
			clienteAlta.setIdProvincia_loc_cli(Integer.parseInt(request.getParameter("provincia")));
			clienteAlta.setIdLocalidad_cli(Integer.parseInt(request.getParameter("localidad")));
			clienteAlta.setCuil_cli(request.getParameter("cuil").trim());
			clienteAlta.setApellido_cli(request.getParameter("apellido").trim());
			clienteAlta.setNombre_cli(request.getParameter("Nombre").trim());
			clienteAlta.setNacionalidad_cli(request.getParameter("nacionalidad").trim());
			String fecha = request.getParameter("fecha_nacimiento");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date fechaNacimiento = formatoFecha.parse(fecha);
				java.sql.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());
				clienteAlta.setFechaNacimiento_cli(sqlFechaNacimiento);
				clienteAlta.setDireccion_cli(request.getParameter("direccion").trim());
				clienteAlta.setCorreo_cli(request.getParameter("correo").trim());
				clienteAlta.setTelefono_cli(request.getParameter("telefono").trim());
				clienteAlta.setUsuario_cli(request.getParameter("usuario").trim());
				clienteAlta.setContrasena_cli(request.getParameter("contrasena").trim());
				clienteAlta.setEstado_cli(true);
				try {
					response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=altaCliente&opcion="
							+ clienteNegImp.insert(clienteAlta));

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// JSP AGREGAR CUENTA
		if (request.getParameter("btnAltaCuenta") != null) {
			int resultado = 0;
			Cuenta cuentaAlta = new Cuenta();
			CuentaNegImpl cuentaNegImp = new CuentaNegImpl();

			Cliente cliAlta = new Cliente();
			ClienteNegImpl negCli = new ClienteNegImpl();

			cuentaAlta.setDniCliente_Cuen(request.getParameter("dni").trim());
			cuentaAlta.setIdTipoDeCuenta_Cuen(Integer.parseInt(request.getParameter("tipo_de_cuenta")));
			cuentaAlta.setCBU_Cuen(request.getParameter("CBU").trim());

			if (cuentaNegImp.CantidadCuentasRepetido(cuentaAlta)) {

				response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=altaCuenta&opcion=4");
				return;
			}

			LocalDate fecha = LocalDate.now(); /// tomar la fecha actual del sistema
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date fechaCreacion = formatoFecha.parse(fecha.toString());
				java.sql.Date sqlFechaCreacion = new java.sql.Date(fechaCreacion.getTime());
				cuentaAlta.setFechaCreacion_Cuen(sqlFechaCreacion);

				cuentaAlta.setSaldo_Cuen(new BigDecimal("10000.0"));
				cuentaAlta.setEstado_Cuen(true);

				cliAlta.setDni_cli(request.getParameter("dni").trim());
				try {
					if (negCli.DniRepetido(cliAlta)) {
						resultado = cuentaNegImp.insert(cuentaAlta);
						response.sendRedirect(request.getContextPath()
								+ "/ServletControladores?Pagina=altaCuenta&opcion=" + resultado);
						if (resultado == 1) {
							Movimiento movAltaCuenta = new Movimiento();
							MovimientoNegImpl movNegImplTrAuth = new MovimientoNegImpl();

							java.util.Date fechaActual = new java.util.Date();
							java.sql.Date sqlFechaActual = new java.sql.Date(fechaActual.getTime());
							movAltaCuenta.setTipoDeMovimiento(1);
							movAltaCuenta.getCuentaOrigen_Mov().setNumeroDecuenta_Cuen("0000"); // 0000 = banco
							movAltaCuenta.getCuentaDestino_Mov().setCBU_Cuen(request.getParameter("CBU"));
							System.out.println(movAltaCuenta.getCuentaDestino_Mov().getCBU_Cuen());

							movAltaCuenta.setImporteDeMovimiento(cuentaAlta.getSaldo_Cuen());
							movAltaCuenta.setDetalleDeMovimiento("Alta de cuenta");
							movAltaCuenta.setFechaDeMovimiento(sqlFechaActual);
							try {
								if (!movNegImplTrAuth.insert(movAltaCuenta)) {
									throw new MovimientoCanceladoException();
								}

							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					} else {
						response.sendRedirect(
								request.getContextPath() + "/ServletControladores?Pagina=altaCuenta&opcion=3");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// JSP ELIMINAR CUENTAS
		if (request.getParameter("btnEliminarCuenta") != null) {
			Cuenta cuentaBaja = new Cuenta();
			CuentaNegImpl cuentaNegImp = new CuentaNegImpl();

			// Asegúrate de que el nombre del parámetro coincida con el del formulario
			cuentaBaja.setNumeroDecuenta_Cuen(request.getParameter("numeroCuenta").trim());
			System.out.println("Número de cuenta a eliminar: " + request.getParameter("numeroCuenta"));

			try {
				if (cuentaNegImp.delete(cuentaBaja)) {

					response.sendRedirect(
							request.getContextPath() + "/ServletControladores?Pagina=bajaCuenta&&opcion=1");
				} else {
					response.sendRedirect(
							request.getContextPath() + "/ServletControladores?Pagina=bajaCuenta&&opcion=2");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// JSP ELIMINAR CLIENTES
		if (request.getParameter("btnEliminarCliente") != null) {
			System.out.println("click sobre boton eliminar cliente");

			Cliente clientBaja = new Cliente();
			ClienteNegImpl clienteNegImpl = new ClienteNegImpl();
			CuentaNegImpl cuentaNegImpl = new CuentaNegImpl();
			if (cuentaNegImpl.deletecondni(request.getParameter("dni"))) {
				clientBaja.setDni_cli(request.getParameter("dni"));

				try {
					if (clienteNegImpl.delete(clientBaja)) {
						response.sendRedirect(
								request.getContextPath() + "/ServletControladores?Pagina=bajaCliente&&opcion=1");
					} else {
						response.sendRedirect(
								request.getContextPath() + "/ServletControladores?Pagina=bajaCliente&&opcion=0");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=bajaCliente&&opcion=2");
			}

		}

		// JSP MODIFICAR CLIENTE
		if (request.getParameter("btnModficiarCliente") != null) {
			Cliente clienteUpdate = new Cliente();
			ClienteNegImpl clienteNegImp = new ClienteNegImpl();
			Administrador adminUsuarioVerificacion = new Administrador();
			AdministradorNegImpl adminNegImplUsVerificacion = new AdministradorNegImpl();
			String dni_a_updatear = request.getParameter("dniUpdate");
			System.out.println(dni_a_updatear);
			clienteUpdate.setDni_cli(dni_a_updatear);
			clienteUpdate.setCuil_cli(request.getParameter("cuil").trim());
			clienteUpdate.setUsuario_cli(request.getParameter("usuario").trim());
			adminUsuarioVerificacion.setUsuario_Admin(clienteUpdate.getUsuario_cli());

			if (clienteNegImp.usuarioRepetidoModificar(clienteUpdate)) {
				response.sendRedirect(request.getContextPath()
						+ "/ServletControladores?Pagina=EditarClientes&opcion=2&ClienteEditar=" + dni_a_updatear);
				return;

			} else if (adminNegImplUsVerificacion.usuarioRepetido(adminUsuarioVerificacion)) {
				response.sendRedirect(request.getContextPath()
						+ "/ServletControladores?Pagina=EditarClientes&opcion=2&ClienteEditar=" + dni_a_updatear);
				return;

			} else if (clienteNegImp.CUILrepetidoModificar(clienteUpdate)) {
				response.sendRedirect(request.getContextPath()
						+ "/ServletControladores?Pagina=EditarClientes&opcion=3&ClienteEditar=" + dni_a_updatear);
				return;
			}

			clienteUpdate.setIdGenero_cli(Integer.parseInt(request.getParameter("sexo")));
			clienteUpdate.setIdProvincia_loc_cli(Integer.parseInt(request.getParameter("provincia")));
			clienteUpdate.setIdLocalidad_cli(Integer.parseInt(request.getParameter("localidad")));
			clienteUpdate.setApellido_cli(request.getParameter("apellido").trim());
			clienteUpdate.setNombre_cli(request.getParameter("Nombre").trim());
			clienteUpdate.setNacionalidad_cli(request.getParameter("nacionalidad").trim());
			String fecha = request.getParameter("fecha_nacimiento");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date fechaNacimiento = formatoFecha.parse(fecha);
				java.sql.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());
				clienteUpdate.setFechaNacimiento_cli(sqlFechaNacimiento);
				clienteUpdate.setDireccion_cli(request.getParameter("direccion").trim());
				clienteUpdate.setCorreo_cli(request.getParameter("correo").trim());
				clienteUpdate.setTelefono_cli(request.getParameter("telefono").trim());
				clienteUpdate.setContrasena_cli(request.getParameter("contrasena").trim());
				clienteUpdate.setEstado_cli(true);
				try {
					if (clienteNegImp.modificar(clienteUpdate)) {
						response.sendRedirect(request.getContextPath()
								+ "/ServletControladores?Pagina=EditarClientes&opcion=1&ClienteEditar="
								+ dni_a_updatear);

					} else {
						response.sendRedirect(request.getContextPath()
								+ "/ServletControladores?Pagina=EditarClientes&opcion=0&ClienteEditar="
								+ dni_a_updatear);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		/// JSP LISTAR CUENTAS
		if (request.getParameter("btn_filtrar_ListarCuentas") != null) {
			String saldoMayor= request.getParameter("filtro_Saldo_ma");
			String saldoMenor= request.getParameter("filtro_Saldo_me");
						
			/*CuentaNegImpl cuentaNegImpl = new CuentaNegImpl();						
			
			List<Cuenta> listCuentas = cuentaNegImpl.FlitrarCuentas(saldoMayor, saldoMenor);
			request.setAttribute("filtro_cuentas", listCuentas);*/
			
			/*RequestDispatcher rdListarCuenta = request.getRequestDispatcher("/Listar Cuentas.jsp");
			rdListarCuenta.forward(request, response);*/
			response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=listarCuentas&filtroMayor="+saldoMayor+"&filtroMenor="+saldoMenor);
			
		}
		

		// JSP MODIFICAR CUENTAS
		if (request.getParameter("btnModficiarCuenta") != null) {
			Cuenta cuentaUpdate = new Cuenta();
			CuentaNegImpl cuentaNegImplUpd = new CuentaNegImpl();
			Cliente clienteVerificarDNI = new Cliente();
			ClienteNegImpl cliNegImplVer = new ClienteNegImpl();
			String cuenta_a_updatear = request.getParameter("numCuenta");
			System.out.println(cuenta_a_updatear);
			cuentaUpdate.setNumeroDecuenta_Cuen(cuenta_a_updatear);
			cuentaUpdate.setDniCliente_Cuen(request.getParameter("dni").trim());
			cuentaUpdate.setCBU_Cuen(request.getParameter("CBU").trim());
			clienteVerificarDNI.setDni_cli(cuentaUpdate.getDniCliente_Cuen());
			System.out.println(cuentaUpdate.getDniCliente_Cuen());
			if (!cliNegImplVer.DniRepetido(clienteVerificarDNI)) {
				response.sendRedirect(request.getContextPath()
						+ "/ServletControladores?Pagina=EditarCuentas&opcion=4&CuentaEditar=" + cuenta_a_updatear);
				return;
			

			} else if (cuentaNegImplUpd.CBURepetidoModificacion(cuentaUpdate)) {
				response.sendRedirect(request.getContextPath()
						+ "/ServletControladores?Pagina=EditarCuentas&opcion=3&CuentaEditar=" + cuenta_a_updatear);
				return;
			}

			cuentaUpdate.setIdTipoDeCuenta_Cuen(Integer.parseInt(request.getParameter("id_tipodeCuetna").trim()));

			String fecha = request.getParameter("fecha_creacion");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date fechaNacimiento = formatoFecha.parse(fecha);
				java.sql.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());
				cuentaUpdate.setFechaCreacion_Cuen(sqlFechaNacimiento);

				cuentaUpdate.setSaldo_Cuen(new BigDecimal(request.getParameter("saldo_cuenta")));
				cuentaUpdate.setEstado_Cuen(true);
				try {
					if (cuentaNegImplUpd.modificar(cuentaUpdate)) {
						response.sendRedirect(request.getContextPath()
								+ "/ServletControladores?Pagina=EditarCuentas&opcion=1&CuentaEditar="
								+ cuenta_a_updatear);

					} else {
						response.sendRedirect(request.getContextPath()
								+ "/ServletControladores?Pagina=EditarCuentas&opcion=0&CuentaEditar="
								+ cuenta_a_updatear);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		// JSP EDITAR CLIENTE
		if (request.getParameter("btnEditarCliente") != null) {

			String Dni = request.getParameter("dniSeleccionado");
			response.sendRedirect(
					request.getContextPath() + "/ServletControladores?Pagina=EditarClientes&ClienteEditar=" + Dni);

		}
		
		// JSP EDITAR CUENTA
		if (request.getParameter("btnEditarCuenta") != null) {

			String ID = request.getParameter("idSeleccionado");
			response.sendRedirect(
					request.getContextPath() + "/ServletControladores?Pagina=EditarCuentas&CuentaEditar=" + ID);

		}
		
		// JSP PEDIDO DE PRESTAMO
		if (request.getParameter("dniClientePr") != null) { // esto es para el alta de prestamo, el parametro es ese
															// porque no me dejaba usar js para hacer el submit ¿Se
															// puede cambiar? quizas

			Prestamo pr = new Prestamo();
			PrestamoNegImpl prNegImpl = new PrestamoNegImpl();
			pr.setDniDelCliente_Pres(request.getParameter("dniClientePr"));
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date sqlFechaActual = new java.sql.Date(fechaActual.getTime());
			pr.setFechaDePedido_Pres(sqlFechaActual);
			pr.setImporteOriginal_Pres(new BigDecimal(request.getParameter("importe_solicitado")));
			pr.setImporteFinal_Pres(new BigDecimal(request.getParameter("Importe_Total")));
			pr.getCuenta_Pres().setNumeroDecuenta_Cuen(request.getParameter("cuenta_prestamo"));
			pr.setValorDeCuota_pres(new BigDecimal(request.getParameter("Valor_Cuotas")));
			pr.setPlazoEnMeses_pres(Integer.parseInt(request.getParameter("plazo_pago")));
			try {
				response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=pedidoPrestamo&opcion="
						+ prNegImpl.insert(pr));

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// JSP ALTA DE PRESTAMO -- aceptar prestamo
		if (request.getParameter("btnAutorizarPrestamo") != null) {
			String resultado = "0";
			PrestamoNegImpl prNegImplAuth = new PrestamoNegImpl();
			String idPrestamo = request.getParameter("btnAutorizarPrestamo");

			if (prNegImplAuth.autorizarprestamo(idPrestamo)) {
				Movimiento movPrAuth = new Movimiento();
				MovimientoNegImpl movNegImplPrAuth = new MovimientoNegImpl();
				CuentaNegImpl cuentaNegImpl = new CuentaNegImpl();

				movPrAuth.setTipoDeMovimiento(2);
				movPrAuth.getCuentaDestino_Mov()
						.setCBU_Cuen(cuentaNegImpl.readone(request.getParameter(idPrestamo + "_1")).getCBU_Cuen());

				movPrAuth.getCuentaOrigen_Mov().setNumeroDecuenta_Cuen("0000"); // 0000 es el banco
				movPrAuth.setImporteDeMovimiento(new BigDecimal(request.getParameter(idPrestamo + "_3")).negate());
				movPrAuth.setDetalleDeMovimiento("Alta de prestamo");
				String fecha = request.getParameter(idPrestamo + "_2");
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				try {
					java.util.Date fechaNacimiento = formatoFecha.parse(fecha);
					java.sql.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());
					movPrAuth.setFechaDeMovimiento(sqlFechaNacimiento);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (movNegImplPrAuth.insert(movPrAuth)) {
						if (cuentaNegImpl.updateSaldoDestino(movPrAuth.getCuentaDestino_Mov().getCBU_Cuen(),
								movPrAuth.getImporteDeMovimiento())) {
							resultado = "1";
						}

					} else {
						throw new MovimientoCanceladoException();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			response.sendRedirect(
					request.getContextPath() + "/ServletControladores?Pagina=autorizarPrestamo&opcion=" + resultado);

		}
		
		//  JSP ALTA DE PRESTAMO -- rechazar prestamo
		if (request.getParameter("btnRechazarPrestamo") != null) {
			PrestamoNegImpl prNegImplAuth = new PrestamoNegImpl();
			String idPrestamo = request.getParameter("btnRechazarPrestamo");
			if (prNegImplAuth.rechazarPrestamo(Integer.parseInt(idPrestamo))) {
				response.sendRedirect(
						request.getContextPath() + "/ServletControladores?Pagina=autorizarPrestamo&opcion=2");

			} else {
				response.sendRedirect(
						request.getContextPath() + "/ServletControladores?Pagina=autorizarPrestamo&opcion=3");

			}
		}

		// JSP PAGO CUOTAS
		if (request.getParameter("btnPagarCuota") != null) {

			// Instanciar clases necesarias
			PagoDeCuotaNegImpl PCNegImpl = new PagoDeCuotaNegImpl();
			PrestamoNegImpl prListarNegImpl = new PrestamoNegImpl();
			CuentaNegImpl cuentaNegImpl = new CuentaNegImpl();
			MovimientoNegImpl movNegImpl1 = new MovimientoNegImpl();

			try {
				String prestamoIdStr = request.getParameter("prestamo_seleccion");
				String cuentaIdStr = request.getParameter("prestamo_cuenta");
				String cuotaInfo = request.getParameter("prestamo_cuota");

				// Validar valores
				if (prestamoIdStr == null || cuentaIdStr == null || cuotaInfo == null || prestamoIdStr.equals("0")
						|| cuentaIdStr.equals("0") || cuotaInfo.equals("0")) {
					request.setAttribute("error", "Por favor complete todos los campos correctamente.");
					cargarVistaPagarPrestamo(dni, request, response);
					return;
				}

				int prestamoId = Integer.parseInt(prestamoIdStr);
				Prestamo prestamo = prListarNegImpl.buscarPrestamoPorID(prestamoIdStr);

				// Calcular el importe según la cuota seleccionada
				int cuotasAPagar = 1; // Por defecto, una sola cuota
				if (cuotaInfo.endsWith("_3")) {
					cuotasAPagar = 3;
				} else if (cuotaInfo.endsWith("_6")) {
					cuotasAPagar = 6;
				} else if (cuotaInfo.endsWith("_12")) {
					cuotasAPagar = 12;
				}

				BigDecimal valorCuota = prestamo.getValorDeCuota_pres();
				int cuotasRestantes = prestamo.getMesesRestantes_pres();
				int totalCuotas = prestamo.getPlazoEnMeses_pres();

				// Verificar si la cuenta tiene saldo suficiente
				Cuenta cuenta = cuentaNegImpl.readone(cuentaIdStr);
				BigDecimal saldoCuenta = cuenta.getSaldo_Cuen();
				BigDecimal importeTotal = valorCuota.multiply(BigDecimal.valueOf(cuotasAPagar));
				if (cuenta == null || saldoCuenta.compareTo(importeTotal) < 0) {
					request.setAttribute("error", "Saldo insuficiente en la cuenta seleccionada.");
					cargarVistaPagarPrestamo(dni, request, response);
					return;
				}

				Cuenta cuentaDestino = new Cuenta();
				cuentaDestino.setCBU_Cuen("0000000000000000000000");

				// Procesar cada cuota a pagar
				for (int i = 0; i < cuotasAPagar; i++) {
					if (cuotasRestantes <= 0) {
						request.setAttribute("error", "No hay cuotas restantes para este préstamo.");
						cargarVistaPagarPrestamo(dni, request, response);
						break;
					}

					Movimiento movimiento = new Movimiento();
					java.util.Date fechaActual = new java.util.Date(System.currentTimeMillis());
					Date fechaSql = new Date(fechaActual.getTime());
					movimiento.setTipoDeMovimiento(2);
					movimiento.setCuentaOrigen_Mov(cuenta);
					movimiento.setCuentaDestino_Mov(cuentaDestino);
					movimiento.setDetalleDeMovimiento("Pago de cuota número: " + (totalCuotas - cuotasRestantes + 1)
							+ " del prestamo: " + prestamo.getIdPrestamos());
					movimiento.setImporteDeMovimiento(valorCuota.negate());
					movimiento.setFechaDeMovimiento(fechaSql);

					// Registrar movimiento
					try {
						if (!movNegImpl1.insert(movimiento)) {
							throw new MovimientoCanceladoException();
						}
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("error", "Ocurrió un error al registrar el movimiento.");
						cargarVistaPagarPrestamo(dni, request, response);
						return;
					}

					int movimientoID = movNegImpl1.obtenerId(movimiento);

					// Registrar el pago de cuota
					PagoDeCuotas pagoCuotas = new PagoDeCuotas();
					pagoCuotas.setIdPrestamo_Pcouta(prestamoId);
					pagoCuotas.setIdMovimiento_Pcouta(movimientoID);
					pagoCuotas.setNumeroDeCuota_Pcouta(totalCuotas - cuotasRestantes + 1);

					if (!PCNegImpl.registrarPago(pagoCuotas)) {
						request.setAttribute("error", "Ocurrió un error al registrar el pago de la cuota.");
						cargarVistaPagarPrestamo(dni, request, response);
						return;
					}

					// Actualizar saldo de la cuenta y cuotas restantes
					saldoCuenta = saldoCuenta.subtract(valorCuota);
					cuenta.setSaldo_Cuen(saldoCuenta);
					cuotasRestantes--;

					// Actualizar en base de datos
					cuentaNegImpl.modificar(cuenta);
					prestamo.setMesesRestantes_pres(cuotasRestantes);
					prListarNegImpl.modificar(prestamo);
				}

				request.setAttribute("error", "Pago realizado con éxito.");
				cargarVistaPagarPrestamo(dni, request, response);

			} catch (NumberFormatException e) {
				request.setAttribute("error", "Formato inválido en los datos ingresados.");
			} catch (Exception e) {
				request.setAttribute("error", "Ocurrió un error inesperado: " + e.getMessage());
			}

		}

		// JSP TRANSFERENCIAS
		if (request.getParameter("btn_transferencia") != null) {
			String opcion = "2";
			Cuenta cuentaCBU = new Cuenta();
			cuentaCBU.setCBU_Cuen(request.getParameter("CBU-destino"));
			CuentaNegImpl cuentaNegImpl = new CuentaNegImpl();
			if (!cuentaNegImpl.readsaldo(request.getParameter("Cuenta-origen"),
					new BigDecimal(request.getParameter("Monto-transferencia")))) { // consulta si el saldo alcanza para
																					// la transferencia
				response.sendRedirect(
						request.getContextPath() + "/ServletControladores?Pagina=transferencias&opcion=3"); // si no hay suficiente saldo, no se realiza la transferencia
				return;
			} else if (cuentaNegImpl.readone(request.getParameter("Cuenta-origen")).getCBU_Cuen()
					.equals(request.getParameter("CBU-destino"))) { // se verifica que la transferencia no sea a la
																	// propia cuenta origen (ej de cuenta 1 a cuenta 1)
				response.sendRedirect(
						request.getContextPath() + "/ServletControladores?Pagina=transferencias&opcion=4");
				return;
			} else if (cuentaCBU.getCBU_Cuen().equals("0000000000000000000000")
					|| !cuentaNegImpl.CBURepetido(cuentaCBU)) {
				// se verifica que no se intente transferir al banco ni a un cbu que no existe
				response.sendRedirect(
						request.getContextPath() + "/ServletControladores?Pagina=transferencias&opcion=5");
				return;
			}
			Movimiento movTrAuth = new Movimiento();
			MovimientoNegImpl movNegImplTrAuth = new MovimientoNegImpl();
			BigDecimal montoTransferencia = new BigDecimal(request.getParameter("Monto-transferencia"));

			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date sqlFechaActual = new java.sql.Date(fechaActual.getTime());
			movTrAuth.setTipoDeMovimiento(4);
			movTrAuth.getCuentaOrigen_Mov().setNumeroDecuenta_Cuen(request.getParameter("Cuenta-origen"));
			movTrAuth.getCuentaDestino_Mov().setCBU_Cuen(request.getParameter("CBU-destino"));
			movTrAuth.setImporteDeMovimiento(new BigDecimal(request.getParameter("Monto-transferencia")).negate());
			movTrAuth.setDetalleDeMovimiento("Transferencia a CBU: " + request.getParameter("CBU-destino"));
			movTrAuth.setFechaDeMovimiento(sqlFechaActual);

			// Mostrar en consola el monto que se está transfiriendo.
			System.out.println("Monto a transferir: " + montoTransferencia);

			try {
				if (movNegImplTrAuth.insert(movTrAuth)) {
					if (cuentaNegImpl.updateSaldoOrigen(movTrAuth.getCuentaOrigen_Mov().getNumeroDecuenta_Cuen(),
							movTrAuth.getImporteDeMovimiento())
							&& cuentaNegImpl.updateSaldoDestino(movTrAuth.getCuentaDestino_Mov().getCBU_Cuen(),
									movTrAuth.getImporteDeMovimiento())) {
						opcion = "1";
					}
				}
				response.sendRedirect(
						request.getContextPath() + "/ServletControladores?Pagina=transferencias&opcion=" + opcion);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//FILTRO LISTADO CLIENTES
		if(request.getParameter("btn_filtro_listadoClientes")!=null) {
			String filtroDniMa = request.getParameter("filtro_dni_ma");
		    String filtroDniMe = request.getParameter("filtro_dni_me");
		    if(filtroDniMa.isEmpty() && filtroDniMe.isEmpty()) {
			    response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=listarClientes");

		    }else if(filtroDniMa.isEmpty()){
			    response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=listarClientes&filtro_dni_me=" + filtroDniMe );

		    }else if(filtroDniMe.isEmpty()) {
			    response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=listarClientes&filtro_dni_ma=" + filtroDniMa );

		    }else {
			    response.sendRedirect(request.getContextPath() + "/ServletControladores?Pagina=listarClientes&filtro_dni_ma=" + filtroDniMa + "&filtro_dni_me=" + filtroDniMe);

		    }
		}
		// JSP INFORMES
	    if(request.getParameter("btn_informe_1") != null) {
    		String bandera_1 = request.getParameter("informe_1_flag");  // si la bandera es 0, va normal, si es 1, se repite el resultado, si no es 0 o 1 no hace nada
    		if(bandera_1.equals("0")) {
    			if("-1".equals(request.getParameter("btn_informe_1"))) {
	    			int resultado = 0;
	    			int filtro = Integer.parseInt(request.getParameter("informe_1_filtro"));
		    		MovimientoNegImpl movimientoNegImplInforme1 = new MovimientoNegImpl();
		    		if(filtro != 0) {
			    		resultado = movimientoNegImplInforme1.informe_1(filtro);

		    		}
		    		request.setAttribute("resultado_informe_1", resultado);
		    		request.setAttribute("filtro_informe_1", filtro);
	    		}else {
		    		request.setAttribute("resultado_informe_1", request.getParameter("btn_informe_1"));
		    		request.setAttribute("filtro_informe_1", request.getParameter("informe_1_filtro_seleccionado"));
	    		}
    		}else if(bandera_1.equals("1")){
    			request.setAttribute("resultado_informe_1", null);
    		}
    }
    
    if(request.getParameter("btn_informe_2") != null) {
    	String bandera_2 = request.getParameter("informe_2_flag");
    	if(bandera_2.equals("0")) {
    		if("-1".equals(request.getParameter("btn_informe_2"))) {
    			BigDecimal resultado;
		    	MovimientoNegImpl movNegImpl = new MovimientoNegImpl();
		    	String dniFiltro = request.getParameter("informe_2_filtro_dni");
		    	String fecha = request.getParameter("informe_2_filtro_fecha");
		    	resultado = movNegImpl.informe_2(dniFiltro,fecha).abs();
	    		request.setAttribute("resultado_informe_2", resultado);
	    		request.setAttribute("filtro_dni_informe_2", dniFiltro);
	    		request.setAttribute("filtro_fecha_informe_2", fecha);
    		}else {
	    		request.setAttribute("resultado_informe_2", request.getParameter("btn_informe_2"));
	    		request.setAttribute("filtro_dni_informe_2", request.getParameter("informe_2_filtro_dni_seleccionado"));
	    		request.setAttribute("filtro_fecha_informe_2", request.getParameter("informe_2_filtro_fecha_seleccionado"));
    		}
    	}else if(bandera_2.equals("1")){
    		request.setAttribute("resultado_informe_2", null);

    	}
    }
    
    if(request.getParameter("btn_informe_3") != null) {
    	String bandera_3 = request.getParameter("informe_3_flag");
		if(bandera_3.equals("0")) {
			if("-1".equals(request.getParameter("btn_informe_3"))) {
				
    	    	PrestamoNegImpl prNegImplInforme3 = new PrestamoNegImpl();
	    		double porcentaje = (prNegImplInforme3.informe())*100; // Porcentaje de cuotas impagas
    	    	int filtro = Integer.parseInt(request.getParameter("informe_3_filtro"));
    	    	if(filtro == 1) { // cuotas pagas, basicamente 100% - porcentaje de impagas
        			porcentaje = 100-porcentaje;
    	    		
    	    	}
        		String porcentajeRedondeado = String.format("%.0f",porcentaje); //redondea
        		
	    		request.setAttribute("resultado_informe_3", porcentajeRedondeado);
	    		request.setAttribute("filtro_informe_3", filtro);
    		}else {
    			System.out.println(request.getParameter("informe_3_filtro_seleccionado"));
	    		request.setAttribute("resultado_informe_3", request.getParameter("btn_informe_3"));
	    		request.setAttribute("filtro_informe_3", request.getParameter("informe_3_filtro_seleccionado"));
    		}
		}else if(bandera_3.equals("1")){
			request.setAttribute("resultado_informe_3", null);
		}
   		 RequestDispatcher rdInformesAdmin = request.getRequestDispatcher("/Informes Administrador.jsp");
         rdInformesAdmin.forward(request, response);	
   }

		



	}

	// Métodos auxiliares
	private void cargarVistaPagarPrestamo(String dni, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrestamoNegImpl prListarNegImpl = new PrestamoNegImpl();
		List<Prestamo> prLista = prListarNegImpl.readAllFiltrado(dni);
		request.setAttribute("listaPrestamos", prLista);

		CuentaNegImpl cuentaPPrNegImpl = new CuentaNegImpl();
		List<Cuenta> listaCnPPr = cuentaPPrNegImpl.readCuentasConDNI(dni);
		request.setAttribute("listaCnPPr", listaCnPPr);

		request.getRequestDispatcher("/Pagar Prestamos.jsp").forward(request, response);
	}
}
