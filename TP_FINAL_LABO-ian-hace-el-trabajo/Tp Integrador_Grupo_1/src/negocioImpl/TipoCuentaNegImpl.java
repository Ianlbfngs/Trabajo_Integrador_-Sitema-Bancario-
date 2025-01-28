package negocioImpl;

import java.util.List;

import datosimpl.TipoDeCuentaDaoImpl;
import entidad.TipoDeCuenta;
import negocio.TipoCuentaNeg;;

public class TipoCuentaNegImpl implements TipoCuentaNeg {
	TipoDeCuentaDaoImpl cuentaDao = new TipoDeCuentaDaoImpl();

	public List<TipoDeCuenta> readAll() {
		return cuentaDao.readAll();
	}
}
