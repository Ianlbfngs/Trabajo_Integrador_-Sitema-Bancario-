package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.AdministradorDao;
import entidad.Administrador;
import entidad.Cliente;

public class AdministradorDaoImpl implements AdministradorDao {

	private static final String readone = "select * from administradores where usuario = ? and contrasena = ?;";
	private static final String usuarioRepetido = "select * from administradores where usuario = ?";
	private static final String BuscarPorID = "select * from administradores where id = ?";
	private static final String buscarId = "select id from administradores where usuario = ? and contrasena = ?";

	private Administrador getAdmin(ResultSet resultSet) throws SQLException {
		Administrador admin = new Administrador();

		admin.setContrasenia_Admin(resultSet.getString("contrasena"));
		admin.setUsuario_Admin(resultSet.getString("usuario"));
		admin.setIdAdministraodr_Admin(resultSet.getInt("id"));

		return admin;
	}

	@Override
	public boolean readOne(Administrador admin) {
		Boolean existeAdmin = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(readone);
			statement.setString(1, admin.getUsuario_Admin().trim());
			statement.setString(2, admin.getContrasenia_Admin().trim());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeAdmin = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeAdmin;
	}

	@Override
	public boolean usuarioRepetido(Administrador admin) {
		Boolean existeAdmin = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(usuarioRepetido);
			statement.setString(1, admin.getUsuario_Admin().trim());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeAdmin = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeAdmin;
	}

	@Override
	public int BuscarId(Administrador admin) {

		int ID = 0;
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {

			statement = conexion.connection.prepareStatement(buscarId);
			statement.setString(1, admin.getUsuario_Admin());
			statement.setString(2, admin.getContrasenia_Admin());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ID = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return ID;

	}

	@Override
	public Administrador buscarAdminPorid(Administrador admin) {
		Administrador adminaux = new Administrador();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {

			statement = conexion.connection.prepareStatement(BuscarPorID);
			statement.setInt(1, admin.getIdAdministraodr_Admin());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				adminaux = getAdmin(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return adminaux;
	}

}
