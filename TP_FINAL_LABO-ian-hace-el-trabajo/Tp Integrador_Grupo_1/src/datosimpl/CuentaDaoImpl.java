package datosimpl;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.CuentaDao;
import entidad.Cuenta;
import entidad.TipoDeCuenta;

public class CuentaDaoImpl implements CuentaDao {

	private static final String insert = "INSERT INTO cuentas VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "UPDATE cuentas SET estado = 0 WHERE numeroDeCuenta = ? and saldo <= 0 and estado=1";
	private static final String deletecondni = "UPDATE cuentas SET estado = 0 WHERE dniCliente = ? and saldo <=0 and estado=1";
	private static final String modificar = "update cuentas set idTipoCuenta = ?, dniCliente = ?, fecha = ?, CBU = ?, saldo = ? where numeroDeCuenta = ? and estado = 1;";
	private static final String readone = "SELECT cuentas.numeroDeCuenta, cuentas.idTipoCuenta, cuentas.dniCliente, cuentas.fecha, cuentas.CBU, cuentas.saldo,cuentas.estado, tiposcuentas.descripcion as descripcionTipoCuenta from cuentas inner join tiposcuentas on cuentas.idTipoCuenta = tiposcuentas.id where cuentas.numeroDeCuenta = ? and estado = 1;";
	private static final String readall = "SELECT cuentas.numeroDeCuenta, cuentas.idTipoCuenta, cuentas.dniCliente, cuentas.fecha, cuentas.CBU, cuentas.saldo,cuentas.estado, tiposcuentas.descripcion as descripcionTipoCuenta from cuentas inner join tiposcuentas on cuentas.idTipoCuenta = tiposcuentas.id where estado = 1;";
	private static final String CBURead = "select * from cuentas where CBU = ? and estado = 1;";
	private static final String MaxID = "SELECT MAX(numeroDeCuenta) AS maxId FROM cuentas";
	private static final String ContDni = "SELECT COUNT(*) AS cantidad FROM cuentas WHERE DniCliente = ? and estado = 1;";
	private static final String DniRepetidoModificacion = "select dniCliente from cuentas where dniCliente = ? and numeroDeCuenta != ? and estado = 1;";
	private static final String CBURepetidoModificacion = "select CBU from cuentas where CBU = ? and numeroDeCuenta != ? and estado = 1;";
	private static final String readcuentascondni = "SELECT cuentas.numeroDeCuenta, cuentas.idTipoCuenta, cuentas.dniCliente, cuentas.fecha, cuentas.CBU, cuentas.saldo,cuentas.estado, tiposcuentas.descripcion as descripcionTipoCuenta from cuentas inner join tiposcuentas on cuentas.idTipoCuenta = tiposcuentas.id where cuentas.dniCliente = ? and  estado = 1;";
	private static final String readsaldo = "select cuentas.numeroDeCuenta, cuentas.idTipoCuenta, cuentas.dniCliente, cuentas.fecha, cuentas.CBU, cuentas.saldo,cuentas.estado, tiposcuentas.descripcion as descripcionTipoCuenta from cuentas inner join tiposcuentas on cuentas.idTipoCuenta = tiposcuentas.id where cuentas.numeroDeCuenta = ? and cuentas.estado = 1";
	private static final String updatesaldoOrigen = "update cuentas set saldo = saldo + ? where numeroDeCuenta = ? and estado = 1 ";
	private static final String updatesaldoDestino = "update cuentas set saldo = saldo - ? where CBU = ? and estado = 1 ";
	private static final String filtrarCuentas = "SELECT cuentas.numeroDeCuenta, cuentas.idTipoCuenta, cuentas.dniCliente, cuentas.fecha, cuentas.CBU, cuentas.saldo,cuentas.estado, tiposcuentas.descripcion as descripcionTipoCuenta from cuentas inner join tiposcuentas on cuentas.idTipoCuenta = tiposcuentas.id where (saldo<= ? or ? is null) AND (saldo>= ? or ? is null) and estado = 1";

	@Override
	public int insert(Cuenta cuenta) {
		// TODO Auto-generated method stub
		int resultado = 0;
		if (CBURepetido(cuenta)) {
			return 2; // 2 = CBU repetido
		}
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			conexion.connection.setAutoCommit(false);
			statement = conexion.connection.prepareStatement(insert);

			statement.setString(1, obtenerSiguienteID());
			statement.setInt(2, cuenta.getIdTipoDeCuenta_Cuen());
			statement.setString(3, cuenta.getDniCliente_Cuen());
			statement.setDate(4, cuenta.getFechaCreacion_Cuen());
			statement.setString(5, cuenta.getCBU_Cuen());
			statement.setBigDecimal(6, cuenta.getSaldo_Cuen());
			statement.setString(7, "1");

			if (statement.executeUpdate() > 0) {
				conexion.connection.commit();
				resultado = 1; // 1 = cuenta dada de alta
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
	public boolean delete(Cuenta cuenta) {
		boolean isDeleted = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(delete);
			statement.setString(1, cuenta.getNumeroDecuenta_Cuen().trim());
			System.out.println("Numero de cuenta a eliminar: " + cuenta.getNumeroDecuenta_Cuen().trim());

			if (statement.executeUpdate() > 0) {
				isDeleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		return isDeleted;
	}

	public boolean deletecondni(String dni) {
		boolean isDeleted = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(deletecondni);
			statement.setString(1, dni);

			if (statement.executeUpdate() > 0) {
				isDeleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		return isDeleted;
	}

	@Override
	public boolean modificar(Cuenta cuenta) {
		boolean resultado = false;

		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			conexion.connection.setAutoCommit(false);
			statement = conexion.connection.prepareStatement(modificar);
			statement.setInt(1, cuenta.getIdTipoDeCuenta_Cuen());
			statement.setString(2, cuenta.getDniCliente_Cuen());
			statement.setDate(3, cuenta.getFechaCreacion_Cuen());
			statement.setString(4, cuenta.getCBU_Cuen());
			statement.setBigDecimal(5, cuenta.getSaldo_Cuen());
			statement.setString(6, cuenta.getNumeroDecuenta_Cuen());
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
	public List<Cuenta> readAll() {
		List<Cuenta> listaCuentas = new ArrayList<>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Cuenta cuenta = getCuenta(resultSet);
				listaCuentas.add(cuenta);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return listaCuentas;
	}

	public boolean CantidadCuentasRepetido(Cuenta cuenta) {
		boolean limiteExcedido = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {

			statement = conexion.connection.prepareStatement(ContDni);
			statement.setString(1, cuenta.getDniCliente_Cuen());

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int cantidad = resultSet.getInt("cantidad");
				if (cantidad >= 3) {
					limiteExcedido = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return limiteExcedido;
	}

	@Override
	public boolean CBURepetido(Cuenta cuenta) {
		Boolean existeCBU = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(CBURead);
			statement.setString(1, cuenta.getCBU_Cuen());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeCBU = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeCBU;
	}

	public String obtenerSiguienteID() {
		int idSeguro = 0;

		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {
			statement = conexion.connection.prepareStatement(MaxID);

			ResultSet resultado = statement.executeQuery();

			if (resultado.next()) {
				idSeguro = resultado.getInt("maxId");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return String.valueOf(idSeguro + 1);
	}

	private Cuenta getCuenta(ResultSet resultSet) throws SQLException {
		Cuenta cuenta = new Cuenta();
		TipoDeCuenta tipoDeCuenta = new TipoDeCuenta();

		cuenta.setNumeroDecuenta_Cuen(resultSet.getString("numeroDeCuenta"));
		cuenta.setDniCliente_Cuen(resultSet.getString("dniCliente"));
		cuenta.setFechaCreacion_Cuen(resultSet.getDate("fecha"));
		cuenta.setCBU_Cuen(resultSet.getString("CBU"));
		cuenta.setSaldo_Cuen(resultSet.getBigDecimal("saldo"));
		tipoDeCuenta.setIdTipoDeCuenta(resultSet.getInt("idTipoCuenta"));
		tipoDeCuenta.setDescripcion(resultSet.getString("descripcionTipoCuenta"));
		cuenta.setTipoCuenta_Cuen(tipoDeCuenta);
		cuenta.setEstado_Cuen(resultSet.getBoolean("estado"));
		return cuenta;
	}

	public boolean CBUrepetidoModificacion(Cuenta cuenta) {
		Boolean existeCBU = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(CBURepetidoModificacion);
			statement.setString(1, cuenta.getCBU_Cuen());
			statement.setString(2, cuenta.getNumeroDecuenta_Cuen());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeCBU = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeCBU;
	}

	public boolean DNIrepetidoModificacion(Cuenta cuenta) {
		Boolean existeCBU = false;
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();
		try {

			statement = conexion.connection.prepareStatement(DniRepetidoModificacion);
			statement.setString(1, cuenta.getDniCliente_Cuen());
			statement.setString(2, cuenta.getNumeroDecuenta_Cuen());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				existeCBU = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.close();
		return existeCBU;
	}

	public List<Cuenta> readCuentasConDNI(String dniCuenta) {
		List<Cuenta> listaCuentas = new ArrayList<>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(readcuentascondni);
			statement.setNString(1, dniCuenta);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Cuenta cuenta = getCuenta(resultSet);
				listaCuentas.add(cuenta);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return listaCuentas;
	}

	@Override
	public boolean readsaldo(String numCuenta, BigDecimal monto) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(readsaldo);
			statement.setNString(1, numCuenta);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Cuenta cuenta = getCuenta(resultSet);
				if (cuenta.getSaldo_Cuen().compareTo(monto) >= 0) { // -1, el saldo es menor al monto, 0 es igual, 1 el
																	// saldo es mayor al monto
					return true; // se puede hacer el movimiento
				} else {
					return false; // no se puede hacer el movimiento
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		return false;
	}

	@Override
	public boolean updateSaldoOrigen(String numCuenta, BigDecimal monto) {
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(updatesaldoOrigen);
			statement.setBigDecimal(1, monto);
			statement.setString(2, numCuenta);

			if (statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		return false;
	}

	@Override
	public boolean updateSaldoDestino(String CBU, BigDecimal monto) {
		PreparedStatement statement;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(updatesaldoDestino);
			statement.setBigDecimal(1, monto);
			statement.setString(2, CBU);

			if (statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		return false;
	}

	@Override
	public Cuenta readone(String numCuenta) {
		Cuenta cuenta = new Cuenta();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		try {
			statement = conexion.connection.prepareStatement(readone);
			statement.setString(1, numCuenta);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				cuenta = getCuenta(resultSet);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return cuenta;
	}
	
	public List<Cuenta> FlitrarCuentas(String saldoMayor, String saldoMenor) {
		List<Cuenta> listaCuentas = new ArrayList<>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = new Conexion();
		conexion.Open();

		System.out.println("saldo mayor"+saldoMayor);
		System.out.println("saldo menor"+saldoMenor);
		
		try {
			statement = conexion.connection.prepareStatement(filtrarCuentas);
			if(saldoMenor==null) {
			
				statement.setString(1, null);
				statement.setString(2, null);
			}			
			else {
				statement.setString(1, saldoMenor);
				statement.setString(2, saldoMenor);
			}
			if(saldoMayor==null) {
				
				statement.setString(3, null);
				statement.setString(4, null);
			}			
			else {
				statement.setString(3, saldoMayor);
				statement.setString(4, saldoMayor);
			}			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Cuenta cuenta = getCuenta(resultSet);
				listaCuentas.add(cuenta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		return listaCuentas;		
	}

}
