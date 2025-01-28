package negocioImpl;

import java.math.BigDecimal;
import java.util.List;

import datosimpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.CuentaNeg;

public class CuentaNegImpl implements CuentaNeg {

	CuentaDaoImpl cuentaDao = new CuentaDaoImpl();

	@Override
	public int insert(Cuenta cuenta) {
		// TODO Auto-generated method stub
		int resultado = 0;
		if (cuenta.getDniCliente_Cuen().length() == 8 && cuenta.getCBU_Cuen().length() == 22) {
			resultado = cuentaDao.insert(cuenta);
		}
		return resultado;
	}

	@Override
	public boolean delete(Cuenta cuenta) {

		return cuentaDao.delete(cuenta);

	}

	@Override
	public boolean CantidadCuentasRepetido(Cuenta cuenta) {

		return cuentaDao.CantidadCuentasRepetido(cuenta);
	}

	@Override
	public boolean modificar(Cuenta cuenta) {
		return cuentaDao.modificar(cuenta);
	}

	@Override
	public List<Cuenta> readAll() {
		return cuentaDao.readAll();

	}

	@Override
	public boolean CBURepetidoModificacion(Cuenta cuenta) {
		return cuentaDao.CBUrepetidoModificacion(cuenta);
	}

	@Override
	public boolean DniRepetidoModificacion(Cuenta cuenta) {
		return cuentaDao.DNIrepetidoModificacion(cuenta);
	}

	@Override
	public List<Cuenta> readCuentasConDNI(String dni) {
		return cuentaDao.readCuentasConDNI(dni);
	}

	@Override
	public boolean readsaldo(String numCuenta, BigDecimal monto) {
		return cuentaDao.readsaldo(numCuenta, monto);
	}

	@Override
	public boolean updateSaldoOrigen(String numCuenta, BigDecimal monto) {
		return cuentaDao.updateSaldoOrigen(numCuenta, monto);
	}

	@Override
	public boolean updateSaldoDestino(String CBU, BigDecimal monto) {
		return cuentaDao.updateSaldoDestino(CBU, monto);
	}

	@Override
	public Cuenta readone(String numCuenta) {
		return cuentaDao.readone(numCuenta);
	}

	@Override
	public boolean CBURepetido(Cuenta cuenta) {
		return cuentaDao.CBURepetido(cuenta);
	}

	@Override
	public boolean deletecondni(String dni) {
		return cuentaDao.deletecondni(dni);
	}
	
	public List<Cuenta> FlitrarCuentas(String saldoMayor, String saldoMenor) {			
		return cuentaDao.FlitrarCuentas(saldoMayor, saldoMenor);
	}

}
