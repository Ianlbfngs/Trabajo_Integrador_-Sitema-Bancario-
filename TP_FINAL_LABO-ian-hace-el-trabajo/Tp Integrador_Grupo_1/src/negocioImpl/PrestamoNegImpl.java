package negocioImpl;

import java.util.List;

import datosimpl.PrestamoDaoImpl;
import entidad.Prestamo;
import negocio.PrestamoNeg;

public class PrestamoNegImpl implements PrestamoNeg {
	PrestamoDaoImpl prDaoImpl = new PrestamoDaoImpl();

	@Override
	public int insert(Prestamo prestamo) {

		return prDaoImpl.insert(prestamo);
	}

	@Override
	public List<Prestamo> readAll() {
		return prDaoImpl.readAll();
	}

	@Override
	public boolean autorizarprestamo(String idPrestamo) {
		return prDaoImpl.autorizarprestamo(idPrestamo);
	}

	@Override
	public List<Prestamo> readAllFiltrado(String dni) {
		return prDaoImpl.readAllFiltrado(dni);
	}

	@Override
	public Prestamo buscarPrestamoPorID(String id) {
		return prDaoImpl.buscarPrestamoPorID(id);
	}

	@Override
	public boolean modificar(Prestamo prestamo) {
		return prDaoImpl.modificar(prestamo);
	}
	@Override
	public double informe() {
		return prDaoImpl.informe();
	}

	@Override
	public boolean rechazarPrestamo(int id) {
		return prDaoImpl.rechazarPrestamo(id);
	}

}
