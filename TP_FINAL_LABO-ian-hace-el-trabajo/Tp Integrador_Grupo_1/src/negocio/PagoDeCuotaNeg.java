package negocio;

import java.util.List;

import entidad.PagoDeCuotas;

public interface PagoDeCuotaNeg {
	public List<PagoDeCuotas> read(String dni);

	public boolean registrarPago(PagoDeCuotas pagoCuota);
}
