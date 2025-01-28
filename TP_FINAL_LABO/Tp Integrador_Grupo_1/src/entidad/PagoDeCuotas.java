package entidad;

public class PagoDeCuotas {
	private Prestamo prestamo_Pcuota = new Prestamo();
	private Movimiento movimiento_Pcuota = new Movimiento();
	private int NumeroDeCuota_Pcouta;

	public PagoDeCuotas() {

	}

	public PagoDeCuotas(int idPrestamo_Pcouta, int idMovimiento_Pcouta, int numeroDeCuota_Pcouta) {
		prestamo_Pcuota.setIdPrestamos(idPrestamo_Pcouta);
		movimiento_Pcuota.setIDMovimientos(idMovimiento_Pcouta);
		NumeroDeCuota_Pcouta = numeroDeCuota_Pcouta;
	}

	public int getIdPrestamo_Pcouta() {
		return prestamo_Pcuota.getIdPrestamos();
	}

	public void setIdPrestamo_Pcouta(int idPrestamo_Pcouta) {
		prestamo_Pcuota.setIdPrestamos(idPrestamo_Pcouta);
	}

	public int getIdMovimiento_Pcouta() {
		return movimiento_Pcuota.getIDMovimientos();
	}

	public void setIdMovimiento_Pcouta(int idMovimiento_Pcouta) {
		movimiento_Pcuota.setIDMovimientos(idMovimiento_Pcouta);
	}

	public int getNumeroDeCuota_Pcouta() {
		return NumeroDeCuota_Pcouta;
	}

	public void setNumeroDeCuota_Pcouta(int numeroDeCuota_Pcouta) {
		NumeroDeCuota_Pcouta = numeroDeCuota_Pcouta;
	}

	public Prestamo getPrestamo_Pcuota() {
		return prestamo_Pcuota;
	}

	public void setPrestamo_Pcuota(Prestamo prestamo_Pcuota) {
		this.prestamo_Pcuota = prestamo_Pcuota;
	}

	public Movimiento getMovimiento_Pcuota() {
		return movimiento_Pcuota;
	}

	public void setMovimiento_Pcuota(Movimiento movimiento_Pcuota) {
		this.movimiento_Pcuota = movimiento_Pcuota;
	}

}
