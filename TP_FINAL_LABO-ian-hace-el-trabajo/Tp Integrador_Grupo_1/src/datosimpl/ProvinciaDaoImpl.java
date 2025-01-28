package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.ProvinciaDao;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {

	private static final String readall = "SELECT * FROM provincias";

	@Override
	public List<Provincia> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			statement = conexion.connection.prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				provincias.add(getProvincia(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		conexion.close();
		return provincias;
	}

	private Provincia getProvincia(ResultSet resultSet) throws SQLException {
		int idProv = Integer.parseInt(resultSet.getString(1));
		String descripcionProv = resultSet.getString(2);
		return new Provincia(idProv, descripcionProv);
	}
}
