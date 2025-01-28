package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.LocalidadDao;
import entidad.Localidad;

public class LocalidadDaoImpl implements LocalidadDao {

	private static final String readall = "SELECT * FROM localidades";

	@Override
	public List<Localidad> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Localidad> localidades = new ArrayList<Localidad>();
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			statement = conexion.connection.prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				localidades.add(getLocalidad(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		conexion.close();
		return localidades;
	}

	private Localidad getLocalidad(ResultSet resultSet) throws SQLException {
		int idLoc = Integer.parseInt(resultSet.getString(1));
		int idProv = Integer.parseInt(resultSet.getString(2));
		String descripcionLoc = resultSet.getString(3);
		return new Localidad(idLoc, idProv, descripcionLoc);
	}
}
