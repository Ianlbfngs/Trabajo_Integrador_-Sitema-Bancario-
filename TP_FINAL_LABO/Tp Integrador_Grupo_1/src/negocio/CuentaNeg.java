package negocio;

import java.math.BigDecimal;
import java.util.List;

import entidad.Cuenta;

public interface CuentaNeg {
	public int insert(Cuenta cuenta);

	public boolean delete(Cuenta cuenta);

	public boolean deletecondni(String dni);

	public boolean modificar(Cuenta cuenta);

	public Cuenta readone(String numCuenta);

	public List<Cuenta> readAll();

	public boolean CantidadCuentasRepetido(Cuenta cuenta);

	public boolean CBURepetidoModificacion(Cuenta cuenta);

	public boolean DniRepetidoModificacion(Cuenta cuenta);

	public List<Cuenta> readCuentasConDNI(String dni);

	public boolean readsaldo(String numCuenta, BigDecimal monto);

	public boolean updateSaldoOrigen(String numCuenta, BigDecimal monto);

	public boolean updateSaldoDestino(String CBU, BigDecimal monto);

	public boolean CBURepetido(Cuenta cuenta);
	
	public List<Cuenta> FlitrarCuentas(String saldoMayor, String saldoMenor);

}
