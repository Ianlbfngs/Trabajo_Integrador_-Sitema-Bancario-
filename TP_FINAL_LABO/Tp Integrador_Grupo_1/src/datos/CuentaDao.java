package datos;

import java.math.BigDecimal;
import java.util.List;
import entidad.Cuenta;;

public interface CuentaDao {
	
	public int insert(Cuenta cuenta);
	public boolean delete(Cuenta cuenta);
	public boolean deletecondni(String dni);
	public boolean modificar(Cuenta cuenta);
	public Cuenta readone(String numCuenta);
	public List<Cuenta> readAll();
	public boolean CBURepetido(Cuenta cuenta);
	public String obtenerSiguienteID();
	public List<Cuenta> readCuentasConDNI(String dniCuenta);
	public boolean readsaldo(String numCuenta, BigDecimal monto);
	public boolean updateSaldoOrigen(String numCuenta, BigDecimal monto);
	public boolean updateSaldoDestino(String CBU, BigDecimal monto);
	public List<Cuenta> FlitrarCuentas(String saldoMayor, String saldoMenor);
	}
