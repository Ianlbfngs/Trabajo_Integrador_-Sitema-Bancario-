package negocioImpl;

import java.util.List;

import datosimpl.ProvinciaDaoImpl;
import entidad.Provincia;
import negocio.ProvinciaNeg;

public class ProvinciaNegImpl implements ProvinciaNeg {

	ProvinciaDaoImpl provinciaDao = new ProvinciaDaoImpl();

	@Override

	public List<Provincia> readAll() {
		return provinciaDao.readAll();
	}

}
