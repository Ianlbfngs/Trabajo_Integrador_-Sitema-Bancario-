package datos;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

import entidad.Movimiento;

public interface MovimientoDao {
	
	public boolean insert(Movimiento movimiento);
	public List<Movimiento> readAll();
	public List<Movimiento> readMismoDniCliente(String cuenta, String cbu);
	public BigDecimal informe_2(String dni, String fecha);
	public int informe_1(int filtro);
	public int obtenerId(Movimiento movimiento);
}
