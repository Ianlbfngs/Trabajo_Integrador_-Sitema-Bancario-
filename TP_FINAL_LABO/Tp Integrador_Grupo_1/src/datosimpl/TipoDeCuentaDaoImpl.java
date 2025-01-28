package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.TipoDeCuentaDao;
import entidad.TipoDeCuenta;;

public class TipoDeCuentaDaoImpl implements TipoDeCuentaDao {

	private static final String readall = "SELECT * FROM tiposcuentas";

	@Override
	public List<TipoDeCuenta> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<TipoDeCuenta> tipoCuenta = new ArrayList<TipoDeCuenta>();
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			statement = conexion.connection.prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tipoCuenta.add(getTipoCuenta(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		conexion.close();
		return tipoCuenta;
	}

	private TipoDeCuenta getTipoCuenta(ResultSet resultSet) throws SQLException {

		int idTipoCuenta = Integer.parseInt(resultSet.getString(1));
		String descripcionCuenta = resultSet.getString(2);

		return new TipoDeCuenta(idTipoCuenta, descripcionCuenta);
	}
}
