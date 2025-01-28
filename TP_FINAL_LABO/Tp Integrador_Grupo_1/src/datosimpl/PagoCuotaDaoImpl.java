package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.PagoCuotaDao;
import entidad.Cuenta;
import entidad.PagoDeCuotas;

public class PagoCuotaDaoImpl implements PagoCuotaDao {
	private static final String read = "select * from pagosdecuotas inner join prestamos on idPrestamo = id where dniCliente = ?;";
	private static final String registrarPago = "insert into pagosdecuotas (idPrestamo, idMovimiento, numeroCuota) values (?,?,?);";

	@Override
	public List<PagoDeCuotas> read(String idPrestamo) {
		List<PagoDeCuotas> listaPagos = new ArrayList<>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(read);
			statement.setString(1, idPrestamo);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				PagoDeCuotas pagoDeCuotas = getPago(resultSet);
				listaPagos.add(pagoDeCuotas);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return listaPagos;
	}

	public PagoDeCuotas getPago(ResultSet resultSet) throws SQLException {
		PagoDeCuotas pago = new PagoDeCuotas();
		pago.getPrestamo_Pcuota().setIdPrestamos(resultSet.getInt(1));
		pago.getMovimiento_Pcuota().setIDMovimientos(resultSet.getInt(2));
		pago.setNumeroDeCuota_Pcouta(resultSet.getInt(3));
		return pago;
	}

	@Override
	public boolean registrarPago(PagoDeCuotas pagoCuota) {
		boolean resultado = false;

		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			conexion.connection.setAutoCommit(false);
			statement = conexion.connection.prepareStatement(registrarPago);
			statement.setInt(1, pagoCuota.getIdPrestamo_Pcouta());
			statement.setInt(2, pagoCuota.getIdMovimiento_Pcouta());
			statement.setInt(3, pagoCuota.getNumeroDeCuota_Pcouta());

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

}
