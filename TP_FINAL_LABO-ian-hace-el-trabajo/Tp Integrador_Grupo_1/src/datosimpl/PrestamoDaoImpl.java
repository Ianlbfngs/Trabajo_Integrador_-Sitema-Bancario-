package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.PrestamoDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao {

	private static final String insert = "insert into prestamos (dniCliente, fechaDePedido,importeFinal,importeOriginal,valorCuota,cantidadMeses,cantidadMesesRestantes,numeroCuentaDestino,estadoAutorizacion,estadoPago) values (?,?,?,?,?,?,?,?,?,?)";
	private static final String readall = "select * from prestamos inner join clientes on prestamos.dniCliente = clientes.DNI and prestamos.estado=true ";
	private static final String readallfiltrado = "select * from prestamos inner join clientes on prestamos.dniCliente = clientes.DNI where prestamos.dniCliente = ? and estadoPago=false and  prestamos.estado = true ";
	private static final String buscarporID = "select * from prestamos where prestamos.id = ? and  prestamos.estado = true";
	private static final String autorizarprestamo = "update prestamos set prestamos.estadoAutorizacion = 1 where prestamos.id = ?";
	private static final String modificar = "update prestamos set dniCliente=?, fechaDePedido=?, importeFinal=?, importeOriginal=?, valorCuota=?, cantidadMeses=?, cantidadMesesRestantes=?, numeroCuentaDestino=?, estadoAutorizacion=? where id=? and estadoPago=? ";
	private static final String rechazarprestamo = "update prestamos set estado = false where id=?";

	@Override
	public int insert(Prestamo prestamo) {
		int resultado = 0;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			conexion.connection.setAutoCommit(false);
			statement = conexion.connection.prepareStatement(insert);

			statement.setString(1, prestamo.getDniDelCliente_Pres());
			statement.setDate(2, prestamo.getFechaDePedido_Pres());
			statement.setBigDecimal(3, prestamo.getImporteFinal_Pres());
			statement.setBigDecimal(4, prestamo.getImporteOriginal_Pres());
			statement.setBigDecimal(5, prestamo.getValorDeCuota_pres());
			statement.setInt(6, prestamo.getPlazoEnMeses_pres());
			statement.setInt(7, prestamo.getPlazoEnMeses_pres());
			statement.setString(8, prestamo.getCuenta_Pres().getNumeroDecuenta_Cuen());
			statement.setBoolean(9, false);
			statement.setBoolean(10, false);

			if (statement.executeUpdate() > 0) {
				conexion.connection.commit();
				resultado = 1;
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
	public List<Prestamo> readAll() {
		List<Prestamo> listaPrestamos = new ArrayList<>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Prestamo prestamo = getPrestamo(resultSet);
				listaPrestamos.add(prestamo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return listaPrestamos;
	}

	public List<Prestamo> readAllFiltrado(String dni) {
		List<Prestamo> listaPrestamos = new ArrayList<>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(readallfiltrado);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Prestamo prestamo = getPrestamo(resultSet);
				listaPrestamos.add(prestamo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return listaPrestamos;
	}

	private Prestamo getPrestamo(ResultSet resultSet) throws SQLException {
		Prestamo pr = new Prestamo();
		Cliente clientePr = new Cliente();

		pr.setIdPrestamos(resultSet.getInt("id"));
		clientePr.setDni_cli(resultSet.getString("dniCliente"));
		pr.setCliente_Pres(clientePr);
		pr.setFechaDePedido_Pres(resultSet.getDate("fechaDePedido"));
		pr.setImporteFinal_Pres(resultSet.getBigDecimal("importeFinal"));
		pr.setImporteOriginal_Pres(resultSet.getBigDecimal("ImporteOriginal"));
		pr.setPlazoEnMeses_pres(resultSet.getInt("cantidadMeses"));
		pr.setMesesRestantes_pres(resultSet.getInt("cantidadMesesRestantes"));
		pr.setValorDeCuota_pres(resultSet.getBigDecimal("valorCuota"));
		pr.getCuenta_Pres().setNumeroDecuenta_Cuen(resultSet.getString("numeroCuentaDestino"));
		pr.setAutorizado_pres(resultSet.getBoolean("estadoAutorizacion"));
		pr.setPagado_pres(resultSet.getBoolean("estadoPago"));
		pr.setEstado_pres(resultSet.getBoolean("estado"));

		return pr;
	}

	@Override
	public boolean autorizarprestamo(String idPrestamo) {
		boolean resultado = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			conexion.connection.setAutoCommit(false);
			statement = conexion.connection.prepareStatement(autorizarprestamo);

			statement.setString(1, idPrestamo);

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
				return false;
			}
		}
		conexion.close();
		return resultado;
	}

	@Override
	public Prestamo buscarPrestamoPorID(String id) {
		Prestamo prestamo = new Prestamo();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(buscarporID);
			statement.setString(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				prestamo = getPrestamo(resultSet);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		return prestamo;
	}

	@Override
	public boolean modificar(Prestamo prestamo) {
		boolean resultado = false;

		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			conexion.connection.setAutoCommit(false);
			statement = conexion.connection.prepareStatement(modificar);
			statement.setString(1, prestamo.getDniDelCliente_Pres());
			statement.setDate(2, prestamo.getFechaDePedido_Pres());
			statement.setBigDecimal(3, prestamo.getImporteFinal_Pres());
			statement.setBigDecimal(4, prestamo.getImporteOriginal_Pres());
			statement.setBigDecimal(5, prestamo.getValorDeCuota_pres());
			statement.setInt(6, prestamo.getPlazoEnMeses_pres());
			statement.setInt(7, prestamo.getMesesRestantes_pres());
			statement.setString(8, prestamo.getCuenta_Pres().getNumeroDecuenta_Cuen());
			statement.setBoolean(9, prestamo.isAutorizado_pres());
			statement.setInt(10, prestamo.getIdPrestamos());
			statement.setBoolean(11, prestamo.isPagado_pres());
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
	public double informe() {
		double promedio = -1;
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement("CALL sp_informe3();");
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				promedio = resultSet.getDouble("Promedio");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return promedio;
	}

	@Override
	public boolean rechazarPrestamo(int id) {
		boolean resultado = false;

		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			conexion.connection.setAutoCommit(false);
			statement = conexion.connection.prepareStatement(rechazarprestamo);
			statement.setInt(1, id);
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
