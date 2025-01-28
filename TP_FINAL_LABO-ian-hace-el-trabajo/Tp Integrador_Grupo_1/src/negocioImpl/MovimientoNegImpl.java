package negocioImpl;

import java.math.BigDecimal;
import java.util.List;

import datosimpl.MovimientoDaoImpl;
import entidad.Movimiento;
import negocio.MovimientoNeg;

public class MovimientoNegImpl implements MovimientoNeg {

	MovimientoDaoImpl movDaoImpl = new MovimientoDaoImpl();

	@Override
	public List<Movimiento> readAll() {
		return movDaoImpl.readAll();
	}

	@Override
	public List<Movimiento> readMismoDniCliente(String cuenta, String cbu) {
		return movDaoImpl.readMismoDniCliente(cuenta, cbu);
	}

	@Override
	public boolean insert(Movimiento movimiento) {
		return movDaoImpl.insert(movimiento);
	}

	@Override
	public BigDecimal informe_2(String dni, String fecha) {
		return movDaoImpl.informe_2(dni, fecha);
	}

	@Override
	public int informe_1(int filtro) {
		return movDaoImpl.informe_1(filtro);
	}



	@Override
	public int obtenerId(Movimiento movimiento) {
		return movDaoImpl.obtenerId(movimiento);
	}

}
