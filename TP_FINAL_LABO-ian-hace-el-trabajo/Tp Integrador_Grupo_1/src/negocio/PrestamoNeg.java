package negocio;

import java.util.List;

import entidad.Prestamo;

public interface PrestamoNeg {
	public int insert(Prestamo prestamo);

	public List<Prestamo> readAll();

	public List<Prestamo> readAllFiltrado(String dni);

	public boolean autorizarprestamo(String idPrestamo);

	public Prestamo buscarPrestamoPorID(String id);

	public boolean modificar(Prestamo prestamo);

	public boolean rechazarPrestamo(int id);

	public double informe();
}
