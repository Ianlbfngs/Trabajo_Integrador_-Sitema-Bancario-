package datosimpl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import datos.MovimientoDao;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoDeCuenta;

public class MovimientoDaoImpl implements MovimientoDao {

	private static final String insert = "insert into movimientos (idTipoMovimiento, numeroCuenta,destinoCBU, fecha, detalle, importe) values (?,?,?,?,?,?);";
	// private static final String readAll="";
	private static final String informe2Consulta = "select SUM(m.importe) as Resultado from movimientos as m inner join cuentas as cu on m.numeroCuenta = cu.numeroDeCuenta inner join clientes as cl on cu.dniCliente = cl.DNI where (m.idTipoMovimiento = 3 or m.idTipoMovimiento = 4) and cl.DNI = ? and m.importe < 0 and YEAR(m.fecha)=?;";
	private static final String informe1Consulta = "select count(movimientos.id) as Resultado from movimientos where YEAR(movimientos.fecha) = ? and movimientos.idTipoMovimiento = 1;";
	private static final String readMismoDni = "SELECT m.id, m.idTipoMovimiento, m.numeroCuenta, c.CBU as origenCBU,m.destinoCBU, m.fecha, m.detalle, m.importe FROM bdsistemabancario.movimientos m JOIN bdsistemabancario.cuentas c ON m.numeroCuenta = c.numeroDeCuenta WHERE m.numeroCuenta = ? OR m.destinoCBU = ?;";
	private static final String obtenerId = "SELECT id FROM movimientos WHERE idTipoMovimiento = ? AND numeroCuenta = ? AND destinoCBU = ? AND fecha = ?  AND detalle = ? AND importe = ?";
	private static final String informe5Consulta = "SELECT SUM(m.importe) AS Resultado " + "FROM movimientos m "
			+ "WHERE YEAR(m.fecha) BETWEEN ? AND ?";

	@Override
	public boolean insert(Movimiento movimiento) {
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			conexion.connection.setAutoCommit(false);
			statement = conexion.connection.prepareStatement(insert);

			statement.setInt(1, movimiento.getTipoDeMovimiento());
			statement.setString(2, movimiento.getCuentaOrigen_Mov().getNumeroDecuenta_Cuen());
			statement.setString(3, movimiento.getCuentaDestino_Mov().getCBU_Cuen());
			statement.setDate(4, movimiento.getFechaDeMovimiento());
			statement.setString(5, movimiento.getDetalleDeMovimiento());
			statement.setBigDecimal(6, movimiento.getImporteDeMovimiento());

			if (statement.executeUpdate() > 0) {
				conexion.connection.commit();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		conexion.close();
		return false;
	}

	@Override
	public List<Movimiento> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private Movimiento getMovimiento(ResultSet resultSet) throws SQLException {
		Movimiento movimiento = new Movimiento();

		movimiento.setIDMovimientos(resultSet.getInt("id"));
		movimiento.setTipoDeMovimiento(resultSet.getInt("idTipoMovimiento"));

		// Configurar las cuentas origen y destino
		Cuenta cuentaOrigen = new Cuenta();
		cuentaOrigen.setNumeroDecuenta_Cuen(resultSet.getString("numeroCuenta"));
		if (resultSet.getString("origenCBU") != null) {
			cuentaOrigen.setCBU_Cuen(resultSet.getString("origenCBU"));
		}
		movimiento.setCuentaOrigen_Mov(cuentaOrigen);

		Cuenta cuentaDestino = new Cuenta();
		cuentaDestino.setCBU_Cuen(resultSet.getString("destinoCBU"));
		movimiento.setCuentaDestino_Mov(cuentaDestino);

		movimiento.setFechaDeMovimiento(resultSet.getDate("fecha"));
		movimiento.setDetalleDeMovimiento(resultSet.getString("detalle"));
		movimiento.setImporteDeMovimiento(resultSet.getBigDecimal("importe"));

		return movimiento;
	}

	@Override
	public List<Movimiento> readMismoDniCliente(String cuenta, String cbu) {
		List<Movimiento> listaMovimiento = new ArrayList<>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(readMismoDni);
			statement.setString(1, cuenta);
			statement.setString(2, cbu);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Movimiento movimiento = getMovimiento(resultSet);
				listaMovimiento.add(movimiento);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return listaMovimiento;
	}

	@Override
	public int obtenerId(Movimiento movimiento) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Conexion conexion = new Conexion();
		int id = -1; 

		try {
			conexion.Open(); 

			statement = conexion.connection.prepareStatement(obtenerId);
			statement.setInt(1, movimiento.getTipoDeMovimiento());
			statement.setString(2, movimiento.getCuentaOrigen_Mov().getNumeroDecuenta_Cuen());
			statement.setString(3, movimiento.getCuentaDestino_Mov().getCBU_Cuen());
			statement.setDate(4, movimiento.getFechaDeMovimiento());
			statement.setString(5, movimiento.getDetalleDeMovimiento());
			statement.setBigDecimal(6, movimiento.getImporteDeMovimiento());

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	@Override
	public BigDecimal informe_2(String dni, String fecha) {
		BigDecimal resultado = new BigDecimal(0);
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(informe2Consulta);
			statement.setString(1, dni);
			statement.setString(2, fecha);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getBigDecimal("Resultado") != null) {
					resultado = resultSet.getBigDecimal("Resultado");

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return resultado;
	}

	public int informe_1(int filtro) {
		int resultado = -1;
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(informe1Consulta);
			statement.setInt(1, filtro);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				resultado = resultSet.getInt("Resultado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return resultado;
	}



}
