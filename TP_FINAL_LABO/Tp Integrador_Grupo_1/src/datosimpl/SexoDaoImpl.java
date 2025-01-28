package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.SexoDao;
import entidad.Sexo;

public class SexoDaoImpl implements SexoDao {

	private static final String readall = "SELECT * FROM generos";

	@Override
	public List<Sexo> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Sexo> sexos = new ArrayList<Sexo>();
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			statement = conexion.connection.prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				sexos.add(getSexo(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		conexion.close();
		return sexos;
	}

	private Sexo getSexo(ResultSet resultSet) throws SQLException {

		int idSexo = Integer.parseInt(resultSet.getString(1));
		String descripcionSexo = resultSet.getString(2);

		return new Sexo(idSexo, descripcionSexo);
	}

}
