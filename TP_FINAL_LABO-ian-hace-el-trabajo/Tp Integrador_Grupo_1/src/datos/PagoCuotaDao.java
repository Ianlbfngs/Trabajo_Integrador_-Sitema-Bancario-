package datos;

import java.util.List;

import entidad.PagoDeCuotas;

public interface PagoCuotaDao {
	public List<PagoDeCuotas> read(String dni);
	public boolean registrarPago(PagoDeCuotas pagoCuota);
}
