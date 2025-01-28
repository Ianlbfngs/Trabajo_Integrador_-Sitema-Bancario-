package entidad;

public class Sexo {

	private int IDSexo_Sex;
	private String Descripcion_Sex;

	public Sexo() {
	};

	public Sexo(int iDSexo, String descripcion) {
		super();
		IDSexo_Sex = iDSexo;
		Descripcion_Sex = descripcion;
	}

	public int getIDSexo() {
		return IDSexo_Sex;
	}

	public void setIDSexo(int iDSexo) {
		IDSexo_Sex = iDSexo;
	}

	public String getDescripcion() {
		return Descripcion_Sex;
	}

	public void setDescripcionSex(String descripcion) {
		Descripcion_Sex = descripcion;
	}

}
