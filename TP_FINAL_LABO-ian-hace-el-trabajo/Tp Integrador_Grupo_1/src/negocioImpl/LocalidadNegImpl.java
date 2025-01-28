package negocioImpl;

import java.util.List;

import datosimpl.LocalidadDaoImpl;
import entidad.Localidad;
import negocio.LocalidadNeg;

public class LocalidadNegImpl implements LocalidadNeg {

	LocalidadDaoImpl locDaoImpl = new LocalidadDaoImpl();

	@Override

	public List<Localidad> readAll() {
		return locDaoImpl.readAll();
	}

}
