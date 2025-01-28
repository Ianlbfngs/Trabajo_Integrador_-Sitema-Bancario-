package entidad;

public class TipoDeCuenta {
	private int IdTipoDeCuenta_TCuent;
	private String Descripcion_TCuent;

	public TipoDeCuenta() {
	};

	public TipoDeCuenta(int idTipoDeCuenta, String descripcion) {
		super();
		IdTipoDeCuenta_TCuent = idTipoDeCuenta;
		Descripcion_TCuent = descripcion;
	}

	public int getIdTipoDeCuenta() {
		return IdTipoDeCuenta_TCuent;
	}

	public void setIdTipoDeCuenta(int idTipoDeCuenta) {
		IdTipoDeCuenta_TCuent = idTipoDeCuenta;
	}

	public String getDescripcion() {
		return Descripcion_TCuent;
	}

	public void setDescripcion(String descripcion) {
		Descripcion_TCuent = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Descripcion_TCuent == null) ? 0 : Descripcion_TCuent.hashCode());
		result = prime * result + IdTipoDeCuenta_TCuent;
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
		TipoDeCuenta other = (TipoDeCuenta) obj;
		if (Descripcion_TCuent == null) {
			if (other.Descripcion_TCuent != null)
				return false;
		} else if (!Descripcion_TCuent.equals(other.Descripcion_TCuent))
			return false;
		if (IdTipoDeCuenta_TCuent != other.IdTipoDeCuenta_TCuent)
			return false;
		return true;
	}

}
