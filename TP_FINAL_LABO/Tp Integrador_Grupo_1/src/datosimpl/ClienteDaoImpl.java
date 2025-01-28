package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.ClienteDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;
import entidad.Sexo;

public class ClienteDaoImpl implements ClienteDao {

	private static final String insert = "INSERT INTO clientes VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String deleteClientes = "UPDATE clientes SET estado = 0 WHERE DNI = ? and estado=1";
	private static final String modificar = "update clientes set idProvincia = ?, idLocalidad = ?, idGenero = ?, CUIL = ?, apellido = ?, nombre = ?, nacionalidad = ?, fechaNacimiento = ?, direccion = ?, correo = ?, telefono = ?, usuario = ?, contrasena = ? where DNI = ?;";
	private static final String readall = "select  DNI,CUIL, apellido, nombre, nacionalidad, fechaNacimiento,direccion,correo,telefono,usuario,contrasena, clientes.idProvincia, provincias.descripcion as descProvincia, idLocalidad, localidades.descripcion as descLocalidad,  idGenero, generos.descripcion as descGenero from clientes inner join generos on clientes.idGenero = generos.id inner join localidades on clientes.idLocalidad = localidades.id inner join provincias on localidades.idProvincia = provincias.id where clientes.estado = 1;";
	private static final String readFiltrado = "select  DNI,CUIL, apellido, nombre, nacionalidad, fechaNacimiento,direccion,correo,telefono,usuario,contrasena, clientes.idProvincia, provincias.descripcion as descProvincia, idLocalidad, localidades.descripcion as descLocalidad,  idGenero, generos.descripcion as descGenero from clientes inner join generos on clientes.idGenero = generos.id inner join localidades on clientes.idLocalidad = localidades.id inner join provincias on localidades.idProvincia = provincias.id where clientes.estado = 1 and (CAST(clientes.DNI AS UNSIGNED) >= ? or ? is null) AND (CAST(clientes.DNI AS UNSIGNED) <= ? or ? is null);";
	private static final String readone = "select * from clientes where usuario = ? and contrasena = ? and estado = 1;";
	private static final String dniRead = "select * from clientes where DNI = ? and estado = 1;";
	private static final String usuarioRead = "select * from clientes where usuario = ? and estado = 1;";
	private static final String usuarioReadModificacion = "select * from clientes where usuario = ? and DNI != ? and estado = 1;";
	private static final String CUILrepetido = "select CUIL from clientes where CUIL = ? and estado = 1";
	private static final String CUILrepetidoModificacion = "select CUIL from clientes where CUIL = ? and DNI != ? and estado = 1";
	private static final String buscarDni = "select DNI from clientes where usuario = ? and contrasena = ? and estado = 1";
	private static final String buscarClientePorDni = "select  DNI,CUIL, apellido, nombre, nacionalidad, fechaNacimiento,direccion,correo,telefono,usuario,contrasena, clientes.idProvincia, provincias.descripcion as descProvincia, idLocalidad, localidades.descripcion as descLocalidad,  idGenero, generos.descripcion as descGenero from clientes inner join generos on clientes.idGenero = generos.id inner join localidades on clientes.idLocalidad = localidades.id inner join provincias on localidades.idProvincia = provincias.id where clientes.DNI = ? and clientes.estado = 1;";

	@Override
	public int insert(Cliente cliente) {
		int resultado = 0;
		if (dniRepetido(cliente)) {
			return 2; // 2 = dni repetido
		} else if (usuarioRepetido(cliente)) {
			return 3; // 3 = usuario repetido
		} else if (CUILrepetido(cliente)) {
			return 4; // 4 = cuil repetido
		}
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			conexion.connection.setAutoCommit(false);
			statement = conexion.connection.prepareStatement(insert);
			statement.setString(1, cliente.getDni_cli());
			statement.setInt(2, cliente.getIdProvincia_loc_cli());
			statement.setInt(3, cliente.getIdLocalidad_cli());
			statement.setInt(4, cliente.getIdGenero_cli());
			statement.setString(5, cliente.getCuil_cli());
			statement.setString(6, cliente.getApellido_cli());
			statement.setString(7, cliente.getNombre_cli());
			statement.setString(8, cliente.getNacionalidad_cli());
			statement.setDate(9, cliente.getFechaNacimiento_cli());
			statement.setString(10, cliente.getDireccion_cli());
			statement.setString(11, cliente.getCorreo_cli());
			statement.setString(12, cliente.getTelefono_cli());
			statement.setString(13, cliente.getUsuario_cli());
			statement.setString(14, cliente.getContrasena_cli());
			statement.setString(15, "1");

			if (statement.executeUpdate() > 0) {
				conexion.connection.commit();
				resultado = 1; // 1 = cliente dado de alta
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return -1; // -1 = error sql
			}
		}
		conexion.close();
		return resultado;
	}

	@Override
	public boolean delete(Cliente cliente) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(deleteClientes);
			statement.setString(1, cliente.getDni_cli().trim());
			statement.executeUpdate();

			System.out.println("Numero de dni a eliminar: " + cliente.getDni_cli().trim());

			if (statement.executeUpdate() > 0) {
				resultado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		System.out.println(resultado);
		return resultado;
	}

	public boolean modificar(Cliente cliente) {
		boolean resultado = false;

		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			conexion.connection.setAutoCommit(false);
			statement = conexion.connection.prepareStatement(modificar);
			statement.setInt(1, cliente.getIdProvincia_loc_cli());
			statement.setInt(2, cliente.getIdLocalidad_cli());
			statement.setInt(3, cliente.getIdGenero_cli());
			statement.setString(4, cliente.getCuil_cli());
			statement.setString(5, cliente.getApellido_cli());
			statement.setString(6, cliente.getNombre_cli());
			statement.setString(7, cliente.getNacionalidad_cli());
			statement.setDate(8, cliente.getFechaNacimiento_cli());
			statement.setString(9, cliente.getDireccion_cli());
			statement.setString(10, cliente.getCorreo_cli());
			statement.setString(11, cliente.getTelefono_cli());
			statement.setString(12, cliente.getUsuario_cli());
			statement.setString(13, cliente.getContrasena_cli());
			statement.setString(14, cliente.getDni_cli());
			if (statement.executeUpdate() > 0) {
				conexion.connection.commit();
				resultado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		conexion.close();
		return resultado;
	}

	@Override
	public List<Cliente> readAll() {
		List<Cliente> listaClientes = new ArrayList<>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Cliente cliente = getCliente(resultSet);
				listaClientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return listaClientes;
	}

	private Cliente getCliente(ResultSet resultSet) throws SQLException {
		Cliente cliente = new Cliente();
		Sexo sexoCliente = new Sexo();
		Provincia provinciaLocalidad = new Provincia();
		Localidad localidadCliente = new Localidad();
		cliente.setDni_cli(resultSet.getString("DNI"));
		cliente.setCuil_cli(resultSet.getString("CUIL"));
		cliente.setApellido_cli(resultSet.getString("apellido"));
		cliente.setNombre_cli(resultSet.getString("nombre"));
		cliente.setNacionalidad_cli(resultSet.getString("nacionalidad"));
		cliente.setFechaNacimiento_cli(resultSet.getDate("fechaNacimiento"));
		cliente.setDireccion_cli(resultSet.getString("direccion"));
		cliente.setCorreo_cli(resultSet.getString("correo"));
		cliente.setTelefono_cli(resultSet.getString("telefono"));
		cliente.setUsuario_cli(resultSet.getString("usuario"));
		cliente.setContrasena_cli(resultSet.getString("contrasena"));
		cliente.setEstado_cli(true);
		sexoCliente.setIDSexo(resultSet.getInt("idGenero"));
		sexoCliente.setDescripcionSex(resultSet.getString("descGenero"));

		provinciaLocalidad.setIdProvincia(resultSet.getInt("idProvincia"));
		provinciaLocalidad.setDescripcionProv(resultSet.getString("descProvincia"));

		localidadCliente.setProvincia_loc(provinciaLocalidad);
		localidadCliente.setIdLocalidad_Loc(resultSet.getInt("idLocalidad"));
		localidadCliente.setDescripcion_Loc(resultSet.getString("descLocalidad"));

		localidadCliente.setProvincia_loc(provinciaLocalidad);
		cliente.setLocalidad_cli(localidadCliente);
		cliente.setGenero_cli(sexoCliente);

		return cliente;
	}

	public boolean readOne(Cliente cliente) {
		Boolean existeCliente = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(readone);
			statement.setString(1, cliente.getUsuario_cli());
			statement.setString(2, cliente.getContrasena_cli());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeCliente = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeCliente;
	}

	@Override
	public boolean dniRepetido(Cliente cliente) {
		Boolean existeCliente = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(dniRead);
			statement.setString(1, cliente.getDni_cli());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeCliente = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeCliente;
	}

	@Override
	public boolean usuarioRepetido(Cliente cliente) {
		Boolean existeCliente = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(usuarioRead);
			statement.setString(1, cliente.getUsuario_cli());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeCliente = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeCliente;
	}

	public boolean usuarioRepetidoModificacion(Cliente cliente) {
		Boolean existeCliente = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(usuarioReadModificacion);
			statement.setString(1, cliente.getUsuario_cli());
			statement.setString(2, cliente.getDni_cli());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeCliente = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeCliente;
	}

	@Override
	public String BuscarDni(Cliente cliente) {
		String Dni = null;
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {

			statement = conexion.connection.prepareStatement(buscarDni);
			statement.setString(1, cliente.getUsuario_cli());
			statement.setString(2, cliente.getContrasena_cli());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Dni = resultSet.getString("DNI");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return Dni;
	}

	@Override
	public Cliente buscarClientePorDni(Cliente cliente) {
		Cliente clienteaux = new Cliente();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {

			statement = conexion.connection.prepareStatement(buscarClientePorDni);
			statement.setString(1, cliente.getDni_cli());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				clienteaux = getCliente(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return clienteaux;
	}

	public boolean CUILrepetido(Cliente cliente) {
		Boolean existeCliente = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(CUILrepetido);
			statement.setString(1, cliente.getCuil_cli());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeCliente = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeCliente;
	}

	public boolean CUILrepetidoModificacion(Cliente cliente) {
		Boolean existeCliente = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(CUILrepetidoModificacion);
			statement.setString(1, cliente.getCuil_cli());
			statement.setString(2, cliente.getDni_cli());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeCliente = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeCliente;
	}

	@Override
	public List<Cliente> readFiltrado(String mayor, String menor) {
		List<Cliente> listaClientes = new ArrayList<>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();
		System.out.println(mayor);
		System.out.println(menor);
		try {
			statement = conexion.connection.prepareStatement(readFiltrado);
			if(menor == null) {
				statement.setString(1, null);
				statement.setString(2, null);
			}else{
				statement.setString(1, menor);
				statement.setString(2, menor);
			}
			if(mayor ==null) {
				statement.setString(3, null);
				statement.setString(4, null);
			}else {
				statement.setString(3, mayor);
				statement.setString(4, mayor);
			}


			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Cliente cliente = getCliente(resultSet);
				listaClientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return listaClientes;
	}

}
