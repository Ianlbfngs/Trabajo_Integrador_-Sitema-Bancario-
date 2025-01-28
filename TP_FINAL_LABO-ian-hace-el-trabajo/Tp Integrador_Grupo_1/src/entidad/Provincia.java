package entidad;

public class Provincia {
	private int IdProvincia_Prov;
	private String Descripcion_Prov;;

	public Provincia() {
	};

	public Provincia(int idProvincia, String descripcion) {
		IdProvincia_Prov = idProvincia;
		Descripcion_Prov = descripcion;
	}

	public int getIdProvincia() {
		return IdProvincia_Prov;
	}

	public void setIdProvincia(int idProvincia) {
		IdProvincia_Prov = idProvincia;
	}

	public String getDescripcion() {
		return Descripcion_Prov;
	}

	public void setDescripcionProv(String descripcion) {
		Descripcion_Prov = descripcion;
	}

}
