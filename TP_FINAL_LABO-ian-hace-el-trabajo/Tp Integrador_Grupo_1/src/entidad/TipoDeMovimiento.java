package entidad;

public class TipoDeMovimiento {
	private int TipodeMovimiento_Tmov;
	private String Descripcion_Tmov;

	public TipoDeMovimiento() {
	};

	public TipoDeMovimiento(int tipodeMovimiento, String descripcion) {
		super();
		TipodeMovimiento_Tmov = tipodeMovimiento;
		Descripcion_Tmov = descripcion;
	}

	public int getTipodeMovimiento() {
		return TipodeMovimiento_Tmov;
	}

	public void setTipodeMovimiento(int tipodeMovimiento) {
		TipodeMovimiento_Tmov = tipodeMovimiento;
	}

	public String getDescripcion() {
		return Descripcion_Tmov;
	}

	public void setDescripcion(String descripcion) {
		Descripcion_Tmov = descripcion;
	}

}
