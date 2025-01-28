package negocio;

import java.math.BigDecimal;
import java.util.List;

import entidad.Movimiento;

public interface MovimientoNeg {

	public boolean insert(Movimiento movimiento);

	public List<Movimiento> readAll();

	public List<Movimiento> readMismoDniCliente(String cuenta, String cbu);

	public int obtenerId(Movimiento movimiento);

	public BigDecimal informe_2(String dni, String fecha);


	public int informe_1(int filtro);
}
