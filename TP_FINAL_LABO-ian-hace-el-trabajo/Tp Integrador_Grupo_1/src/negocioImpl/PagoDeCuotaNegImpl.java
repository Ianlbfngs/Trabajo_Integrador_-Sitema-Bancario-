package negocioImpl;

import java.util.List;

import datos.PagoCuotaDao;
import datosimpl.PagoCuotaDaoImpl;
import entidad.PagoDeCuotas;
import negocio.PagoDeCuotaNeg;

public class PagoDeCuotaNegImpl implements PagoDeCuotaNeg {
	PagoCuotaDaoImpl pagoCuotaDaoImpl = new PagoCuotaDaoImpl();

	@Override
	public List<PagoDeCuotas> read(String dni) {
		return pagoCuotaDaoImpl.read(dni);
	}

	@Override
	public boolean registrarPago(PagoDeCuotas pagoCuota) {
		return pagoCuotaDaoImpl.registrarPago(pagoCuota);
	}

}
