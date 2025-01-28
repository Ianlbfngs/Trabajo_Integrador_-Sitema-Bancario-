package entidad;

import java.math.BigDecimal;
import java.sql.Date;

public class Prestamo {
	private int IdPrestamos;
	private Cliente cliente_Pres = new Cliente();
	private Cuenta cuenta_Pres = new Cuenta();
	private Date FechaDePedido_Pres;
	private BigDecimal ImporteFinal_Pres;
	private BigDecimal ImporteOriginal_Pres;
	private int PlazoEnMeses_pres;
	private int MesesRestantes_pres;
	private BigDecimal ValorDeCuota_pres;
	private boolean Autorizado_pres = false;
	private boolean Pagado_pres = false;
	private boolean estado_pres = false;

	public Prestamo() {
	};

	public int getIdPrestamos() {
		return IdPrestamos;
	}

	public void setIdPrestamos(int idPrestamos) {
		IdPrestamos = idPrestamos;
	}

	public String getDniDelCliente_Pres() {
		return cliente_Pres.getDni_cli();
	}

	public void setDniDelCliente_Pres(String dniDelCliente_Pres) {
		cliente_Pres.setDni_cli(dniDelCliente_Pres);
	}

	public Date getFechaDePedido_Pres() {
		return FechaDePedido_Pres;
	}

	public void setFechaDePedido_Pres(Date fechaDePedido_Pres) {
		FechaDePedido_Pres = fechaDePedido_Pres;
	}

	public BigDecimal getImporteFinal_Pres() {
		return ImporteFinal_Pres;
	}

	public void setImporteFinal_Pres(BigDecimal importeFinal_Pres) {
		ImporteFinal_Pres = importeFinal_Pres;
	}

	public BigDecimal getImporteOriginal_Pres() {
		return ImporteOriginal_Pres;
	}

	public void setImporteOriginal_Pres(BigDecimal importeOriginal_Pres) {
		ImporteOriginal_Pres = importeOriginal_Pres;
	}

	public int getPlazoEnMeses_pres() {
		return PlazoEnMeses_pres;
	}

	public void setPlazoEnMeses_pres(int plazoEnMeses_pres) {
		PlazoEnMeses_pres = plazoEnMeses_pres;
	}

	public BigDecimal getValorDeCuota_pres() {
		return ValorDeCuota_pres;
	}

	public void setValorDeCuota_pres(BigDecimal valorDeCuota_pres) {
		ValorDeCuota_pres = valorDeCuota_pres;
	}

	public Cliente getCliente_Pres() {
		return cliente_Pres;
	}

	public void setCliente_Pres(Cliente cliente_Pres) {
		this.cliente_Pres = cliente_Pres;
	}

	public boolean isAutorizado_pres() {
		return Autorizado_pres;
	}

	public void setAutorizado_pres(boolean autorizado_pres) {
		Autorizado_pres = autorizado_pres;
	}

	public boolean isPagado_pres() {
		return Pagado_pres;
	}

	public void setPagado_pres(boolean pagado_pres) {
		Pagado_pres = pagado_pres;
	}

	public Cuenta getCuenta_Pres() {
		return cuenta_Pres;
	}

	public void setCuenta_Pres(Cuenta cuenta_Pres) {
		this.cuenta_Pres = cuenta_Pres;
	}

	public int getMesesRestantes_pres() {
		return MesesRestantes_pres;
	}

	public void setMesesRestantes_pres(int mesesRestantes_pres) {
		MesesRestantes_pres = mesesRestantes_pres;
	}

	public boolean isEstado_pres() {
		return estado_pres;
	}

	public void setEstado_pres(boolean estado_pres) {
		this.estado_pres = estado_pres;
	}

}