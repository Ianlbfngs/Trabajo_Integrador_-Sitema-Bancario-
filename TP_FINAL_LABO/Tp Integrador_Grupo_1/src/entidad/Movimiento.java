package entidad;

import java.math.BigDecimal;
import java.sql.Date;

public class Movimiento {
	private int IDMovimientos;
	private TipoDeMovimiento tipoMovimiento_Mov = new TipoDeMovimiento();
	private Cuenta cuentaOrigen_Mov = new Cuenta();
	private Cuenta cuentaDestino_Mov = new Cuenta();
	private Date FechaDeMovimiento_Mov;
	private String Detalle_Mov;
	private BigDecimal Importe_Mov;

	public Movimiento() {
	};

	public Movimiento(int iDMovimientos, int tipoDeMovimiento, Cuenta cuentaOrg, Cuenta cuentaDst,
			Date fechaDeMovimiento, String detalleDeMovimiento, BigDecimal importeDeMovimiento) {
		IDMovimientos = iDMovimientos;
		tipoMovimiento_Mov.setTipodeMovimiento(tipoDeMovimiento);
		cuentaOrigen_Mov = cuentaOrg;
		cuentaDestino_Mov = cuentaDst;
		FechaDeMovimiento_Mov = fechaDeMovimiento;
		Detalle_Mov = detalleDeMovimiento;
		Importe_Mov = importeDeMovimiento;
	}

	public int getIDMovimientos() {
		return IDMovimientos;
	}

	public void setIDMovimientos(int iDMovimientos) {
		IDMovimientos = iDMovimientos;
	}

	public int getTipoDeMovimiento() {
		return tipoMovimiento_Mov.getTipodeMovimiento();
	}

	public void setTipoDeMovimiento(int tipoDeMovimiento) {
		tipoMovimiento_Mov.setTipodeMovimiento(tipoDeMovimiento);
	}

	public Date getFechaDeMovimiento() {
		return FechaDeMovimiento_Mov;
	}

	public void setFechaDeMovimiento(Date fechaDeMovimiento) {
		FechaDeMovimiento_Mov = fechaDeMovimiento;
	}

	public String getDetalleDeMovimiento() {
		return Detalle_Mov;
	}

	public void setDetalleDeMovimiento(String detalleDeMovimiento) {
		Detalle_Mov = detalleDeMovimiento;
	}

	public BigDecimal getImporteDeMovimiento() {
		return Importe_Mov;
	}

	public void setImporteDeMovimiento(BigDecimal importeDeMovimiento) {
		Importe_Mov = importeDeMovimiento;
	}

	public Cuenta getCuentaOrigen_Mov() {
		return cuentaOrigen_Mov;
	}

	public void setCuentaOrigen_Mov(Cuenta cuentaOrigen_Mov) {
		this.cuentaOrigen_Mov = cuentaOrigen_Mov;
	}

	public Cuenta getCuentaDestino_Mov() {
		return cuentaDestino_Mov;
	}

	public void setCuentaDestino_Mov(Cuenta cuentaDestino_Mov) {
		this.cuentaDestino_Mov = cuentaDestino_Mov;
	}

}
