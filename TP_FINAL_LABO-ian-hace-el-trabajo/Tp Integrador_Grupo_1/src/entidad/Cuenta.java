package entidad;

import java.math.BigDecimal;
import java.sql.Date;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Cuenta {
	private String NumeroDecuenta_Cuen;
	private TipoDeCuenta tipoCuenta_Cuen = new TipoDeCuenta();
	private String DniCliente_Cuen;
	private Date FechaCreacion_Cuen;
	private String CBU_Cuen;
	private BigDecimal Saldo_Cuen;
	private boolean Estado_Cuen;

	public Cuenta(String numeroDecuenta_Cuen, int idTipoDeCuenta_Cuen, String dniCliente_Cuen, Date fechaCreacion_Cuen,
			String cBU_Cuen, BigDecimal saldo_Cuen, boolean estado_Cuen) {
		NumeroDecuenta_Cuen = numeroDecuenta_Cuen;
		tipoCuenta_Cuen.setIdTipoDeCuenta(idTipoDeCuenta_Cuen);
		DniCliente_Cuen = dniCliente_Cuen;
		FechaCreacion_Cuen = fechaCreacion_Cuen;
		CBU_Cuen = cBU_Cuen;
		Saldo_Cuen = saldo_Cuen;
		Estado_Cuen = estado_Cuen;
	}

	public Cuenta() {
	}

	public void setTipoCuenta_Cuen(TipoDeCuenta tdCuenta) {
		tipoCuenta_Cuen = tdCuenta;
	}

	public TipoDeCuenta getTipoCuenta_Cuen() {
		return tipoCuenta_Cuen;
	}

	public String getNumeroDecuenta_Cuen() {
		return NumeroDecuenta_Cuen;
	}

	public void setNumeroDecuenta_Cuen(String numeroDecuenta_Cuen) {
		NumeroDecuenta_Cuen = numeroDecuenta_Cuen;
	}

	public int getIdTipoDeCuenta_Cuen() {
		return tipoCuenta_Cuen.getIdTipoDeCuenta();
	}

	public void setIdTipoDeCuenta_Cuen(int idTipoDeCuenta_Cuen) {
		tipoCuenta_Cuen.setIdTipoDeCuenta(idTipoDeCuenta_Cuen);
	}

	public String getDniCliente_Cuen() {
		return DniCliente_Cuen;
	}

	public void setDniCliente_Cuen(String dniCliente_Cuen) {
		DniCliente_Cuen = dniCliente_Cuen;
	}

	public Date getFechaCreacion_Cuen() {
		return FechaCreacion_Cuen;
	}

	public void setFechaCreacion_Cuen(Date fechaCreacion_Cuen) {
		FechaCreacion_Cuen = fechaCreacion_Cuen;
	}

	public String getCBU_Cuen() {
		return CBU_Cuen;
	}

	public void setCBU_Cuen(String cBU_Cuen) {
		CBU_Cuen = cBU_Cuen;
	}

	public BigDecimal getSaldo_Cuen() {
		return Saldo_Cuen;
	}

	public void setSaldo_Cuen(BigDecimal saldo_Cuen) {
		Saldo_Cuen = saldo_Cuen;
	}

	public boolean getEstado_Cuen() {
		return Estado_Cuen;
	}

	public void setEstado_Cuen(boolean estado_Cuen) {
		Estado_Cuen = estado_Cuen;
	}

	public String getDescTipoCuenta_Cuen() {
		return tipoCuenta_Cuen.getDescripcion();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CBU_Cuen == null) ? 0 : CBU_Cuen.hashCode());
		result = prime * result + ((DniCliente_Cuen == null) ? 0 : DniCliente_Cuen.hashCode());
		result = prime * result + (Estado_Cuen ? 1231 : 1237);
		result = prime * result + ((FechaCreacion_Cuen == null) ? 0 : FechaCreacion_Cuen.hashCode());
		result = prime * result + ((NumeroDecuenta_Cuen == null) ? 0 : NumeroDecuenta_Cuen.hashCode());
		result = prime * result + ((Saldo_Cuen == null) ? 0 : Saldo_Cuen.hashCode());
		result = prime * result + ((tipoCuenta_Cuen == null) ? 0 : tipoCuenta_Cuen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		if (CBU_Cuen == null) {
			if (other.CBU_Cuen != null)
				return false;
		} else if (!CBU_Cuen.equals(other.CBU_Cuen))
			return false;
		if (DniCliente_Cuen == null) {
			if (other.DniCliente_Cuen != null)
				return false;
		} else if (!DniCliente_Cuen.equals(other.DniCliente_Cuen))
			return false;
		if (Estado_Cuen != other.Estado_Cuen)
			return false;
		if (FechaCreacion_Cuen == null) {
			if (other.FechaCreacion_Cuen != null)
				return false;
		} else if (!FechaCreacion_Cuen.equals(other.FechaCreacion_Cuen))
			return false;
		if (NumeroDecuenta_Cuen == null) {
			if (other.NumeroDecuenta_Cuen != null)
				return false;
		} else if (!NumeroDecuenta_Cuen.equals(other.NumeroDecuenta_Cuen))
			return false;
		if (Saldo_Cuen == null) {
			if (other.Saldo_Cuen != null)
				return false;
		} else if (!Saldo_Cuen.equals(other.Saldo_Cuen))
			return false;
		if (tipoCuenta_Cuen == null) {
			if (other.tipoCuenta_Cuen != null)
				return false;
		} else if (!tipoCuenta_Cuen.equals(other.tipoCuenta_Cuen))
			return false;
		return true;
	}

}
