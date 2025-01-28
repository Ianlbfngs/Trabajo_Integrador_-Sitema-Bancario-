package negocioImpl;

import java.util.List;

import datosimpl.SexoDaoImpl;
import entidad.Sexo;
import negocio.SexoNeg;

public class SexoNegImpl implements SexoNeg {

	SexoDaoImpl sexoDao = new SexoDaoImpl();

	public List<Sexo> readAll() {
		return sexoDao.readAll();
	}

}
